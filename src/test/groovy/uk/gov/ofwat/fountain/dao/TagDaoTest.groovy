package uk.gov.ofwat.fountain.dao
import java.util.List;
import uk.gov.ofwat.fountain.domain.Data;
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate3.SessionFactoryUtils
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertTrue
import uk.gov.ofwat.fountain.HibernateTransactionalSpringContextTests;
import uk.gov.ofwat.fountain.domain.Value
import uk.gov.ofwat.fountain.domain.tag.Tag
import uk.gov.ofwat.fountain.domain.tag.TagLink
import uk.gov.ofwat.fountain.domain.tag.TagLinkType

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners([])
@ContextConfiguration(locations = ["classpath:test_beans.xml"])
class TagDaoTest extends HibernateTransactionalSpringContextTests {

	@Autowired
	private TagDao tagDao
	@Autowired
	private TagLinkTypeDao tagLinkTypeDao

	private static TAG_NAME = "Test Tag Name"
	private static TAG_DISPLAY_NAME = "Test Tag Display Name"
	private static String USER_NAME = "OFWAT/testuser";

	@Test
	@Rollback(true)
	public void testCreateTag(){
		println("TEST: ${this.class.name}:testCreateTag")
		Tag tag = new Tag()
		tag.name = TAG_NAME
		tag.displayName = TAG_DISPLAY_NAME
		tag.createdBy = USER_NAME
		tag.note = "A Note"
		tagDao.create(tag)
		//println("\n\n\n***${tag.id}***\n\n\n")
		assertNotNull(tag)
		assertTrue(tag.id > 0)
	}

	@Test
	public void testRetrieveTag(){
		println("TEST: ${this.class.name}:testRetrieveTag")
		//Create and store a tag.
		Tag tag = new Tag()
		tag.name = TAG_NAME
		tag.displayName = TAG_DISPLAY_NAME
		tag.createdBy = USER_NAME
		tag.note = "A Note"
		tagDao.create(tag)
		assertNotNull(tag)
		assertTrue(tag.id > 0)
		//Keep a ref to the ID.
		Serializable id = tag.id
		tag = null;
		tag = tagDao.get(id)
		//Make sure we got the same tag back!
		assertNotNull(tag)
		assertTrue(tag.id.equals(id))
	}

	@Test
	public void testRemoveTag(){
		println("TEST: ${this.class.name}:testRemoveTag")
		//Create and store a tag.
		Tag tag = new Tag()
		inTestTx{
			tag.name = TAG_NAME
			tag.displayName = TAG_DISPLAY_NAME
			tag.createdBy = USER_NAME
			tag.note = "A Note"
			tagDao.create(tag)
			assertNotNull(tag)
			assertTrue(tag.id > 0)
		}
		Serializable id = tag.id
		//Delete the tag.
		inTestTx{
			tagDao.deleteById(id)
			tag = null;
			tag = tagDao.get(id)
		}
		//Make sure we got the same tag back!
		assertTrue(tag == null)
	}

	@Test
	public void testAddTagLink(){
		println("TEST: ${this.class.name}:testAddTagLink")
		Tag tag = new Tag()
		inTestTx{
			tag.name = TAG_NAME
			tag.displayName = TAG_DISPLAY_NAME
			tag.createdBy = USER_NAME
			tag.note = "A Note"
			tagDao.create(tag)
			assertNotNull(tag)
			assertTrue(tag.id > 0)
	
			TagLink tagLink1 = new TagLink()
			tagLink1.createdBy = USER_NAME
			tagLink1.dateCreated = new Date();
			TagLinkType tagLinkType = new TagLinkType(String.class.toString())
			tagLinkTypeDao.create(tagLinkType)
			tagLink1.entityType = tagLinkType
			tagLink1.entityId = 9999l
	
			TagLink tagLink2 = new TagLink()
			tagLink2.createdBy = USER_NAME
			tagLink2.dateCreated = new Date()
			tagLink2.entityType = tagLinkType
			tagLink2.entityId = 9999l
	
			tag.addTagLink(tagLink1)
			tag.addTagLink(tagLink2)
			tagDao.update(tag)
			
		}
		assertTrue(tag.getTagLinks().size() == 2)
		def savedTagLink = tag.getTagLinks().first()
		assertNotNull(savedTagLink.id)
		println("\n\n\n***${savedTagLink.id}***\n\n\n")
		assertTrue(savedTagLink.id > 0)

	}

	@Test
	public void testGetTagLinks(){
		println("TEST: ${this.class.name}:testGetTagLinks")
		Tag tag = new Tag()
		inTestTx{
			tag.name = TAG_NAME
			tag.displayName = TAG_DISPLAY_NAME
			tag.createdBy = USER_NAME
			tag.note = "A Note"
			tagDao.create(tag)
			assertNotNull(tag)
			assertTrue(tag.id > 0)
	
			TagLink tagLink1 = new TagLink()
			tagLink1.createdBy = USER_NAME
			tagLink1.dateCreated = new Date();
			TagLinkType tagLinkType = new TagLinkType(String.class.toString())
			tagLinkTypeDao.create(tagLinkType)
			tagLink1.entityType = tagLinkType
			tagLink1.entityId = 9999l
	
			TagLink tagLink2 = new TagLink()
			tagLink2.createdBy = USER_NAME
			tagLink2.dateCreated = new Date();
			tagLink2.entityType = tagLinkType
			tagLink2.entityId = 9999l
	
			tag.addTagLink(tagLink1)
			tag.addTagLink(tagLink2)
			tagDao.update(tag)
			
		}
		def id = tag.id
		assertTrue(tag.getTagLinks().size() == 2)
		def savedTagLink = tag.getTagLinks().first()
		assertNotNull(savedTagLink.id)
		tag = null
		
		inTestTx{
			tag = tagDao.get(id)
			assertNotNull(tag)
			assertTrue(tag.getTagLinks().size() == 2)
			assertEquals(tag.id, id)
		}
		
	}


	@Test
	public void removeAllTagLinks(){
		println("TEST: ${this.class.name}:removeAllTagLinks")
		Tag tag = new Tag()
		inTestTx{
			tag.name = TAG_NAME
			tag.displayName = TAG_DISPLAY_NAME
			tag.createdBy = USER_NAME
			tag.note = "A Note"
			tagDao.create(tag)
			assertNotNull(tag)
			assertTrue(tag.id > 0)
	
			TagLink tagLink1 = new TagLink()
			tagLink1.createdBy = USER_NAME
			tagLink1.dateCreated = new Date();
			TagLinkType tagLinkType = new TagLinkType(String.class.toString())
			tagLinkTypeDao.create(tagLinkType)
			tagLink1.entityType = tagLinkType
			tagLink1.entityId = 9999l
	
			TagLink tagLink2 = new TagLink()
			tagLink2.createdBy = USER_NAME
			tagLink2.dateCreated = new Date();
			tagLink2.entityType = tagLinkType
			tagLink2.entityId = 9999l
	
			tag.addTagLink(tagLink1)
			tag.addTagLink(tagLink2)
			tagDao.update(tag)
			
		}
		assertTrue(tag.getTagLinks().size() == 2)
		def savedTagLink = tag.getTagLinks().first()
		assertNotNull(savedTagLink.id)
		println("\n\n\n***${savedTagLink.id}***\n\n\n")
		assertTrue(savedTagLink.id > 0)
		
		//Remove all the tagLinks
		inTestTx{
			tagDao.deleteAllTagLinks(tag.name)
		}
		tag = tagDao.findTagByName(tag.name)
		assertTrue(tag.getTagLinks().size() == 0)
	}
	

	@Test
	public void removeTagLinks(){
		println("TEST: ${this.class.name}:removeTagLinks")
		Tag tag = new Tag()
		inTestTx{
			tag.name = TAG_NAME
			tag.displayName = TAG_DISPLAY_NAME
			tag.createdBy = USER_NAME
			tag.note = "A Note"
			tagDao.create(tag)
			assertNotNull(tag)
			assertTrue(tag.id > 0)
	
			TagLink tagLink1 = new TagLink()
			tagLink1.createdBy = USER_NAME
			tagLink1.dateCreated = new Date();
			TagLinkType tagLinkType = new TagLinkType(String.class.toString())
			tagLinkTypeDao.create(tagLinkType)
			tagLink1.entityType = tagLinkType
			tagLink1.entityId = 9999l
	
			TagLink tagLink2 = new TagLink()
			tagLink2.createdBy = USER_NAME
			tagLink2.dateCreated = new Date();
			tagLink2.entityType = tagLinkType
			tagLink2.entityId = 9999l
	
			tag.addTagLink(tagLink1)
			tag.addTagLink(tagLink2)
			tagDao.update(tag)
			
		}
		assertTrue(tag.getTagLinks().size() == 2)
		def savedTagLink = tag.getTagLinks().first()
		assertNotNull(savedTagLink.id)
		println("\n\n\n***${savedTagLink.id}***\n\n\n")
		assertTrue(savedTagLink.id > 0)
		
		//Remove all the tagLinks
		inTestTx{
			tag.removeAllTagLinks()
			tagDao.update(tag)
		}
		
		assertTrue(tag.getTagLinks().size() == 0)
	}
	
	@Test
	public void findTagByName(){
		println("TEST: ${this.class.name}:findTagByName")
		Tag tag = new Tag()
		inTestTx{
			tag.name = TAG_NAME
			tag.displayName = TAG_DISPLAY_NAME
			tag.createdBy = USER_NAME
			tag.note = "A Note"
			tagDao.create(tag)
			
			TagLink tagLink1 = new TagLink()
			tagLink1.createdBy = USER_NAME
			tagLink1.dateCreated = new Date();
			TagLinkType tagLinkTypeL = new TagLinkType(Long.class.toString())
			tagLinkTypeDao.create(tagLinkTypeL)
			tagLink1.entityType = tagLinkTypeL
			tagLink1.entityId = 9999l
	
			TagLink tagLink2 = new TagLink()
			tagLink2.createdBy = USER_NAME
			tagLink2.dateCreated = new Date();
			TagLinkType tagLinkTypeS = new TagLinkType(String.class.toString())
			tagLinkTypeDao.create(tagLinkTypeS)
			tagLink2.entityType = tagLinkTypeS
			tagLink2.entityId = 9999l
	
			tag.addTagLink(tagLink1)
			tag.addTagLink(tagLink2)
			tagDao.update(tag)
			
		}
		
		assertNotNull(tag)
		assertTrue(tag.id > 0)
		assertTrue(tag.getTagLinks().size() == 2)
		Serializable id = tag.id;
		
		tag = null
		
		//Test that the DAO query only brings back one tag and two tag links!
		tag = tagDao.findTagByName(TAG_NAME)
		assertNotNull(tag)
		assertTrue(tag.id == id)
		assertTrue(tag.getTagLinks().size() == 2)
		assertTrue(!tag.getTagLinks().toArray()[0].entityType.equalsIgnoreCase(tag.getTagLinks().toArray()[1].entityType))
		
	}
	/*
	@Test
	public void testCachingMultipleDifferentTypesOfEnities(){
		//Make sure that caching is enabled for this test!
		println("TEST: ${this.class.name}:testCachingMultipleDifferentTypesOfEnities")
		Tag tag = new Tag()
		inTestTx{
			tag.name = TAG_NAME
			tag.displayName = TAG_DISPLAY_NAME
			tag.createdBy = USER_NAME
			tag.note = "A Note"
			//tag.entityType = String.class
			tagDao.create(tag)
			assertNotNull(tag)
			assertTrue(tag.id > 0)
	
			TagLink tagLink1 = new TagLink()
			tagLink1.createdBy = USER_NAME
			tagLink1.dateCreated = new Date();
			tagLink1.entityType = String.class.toString()
			tagLink1.entityId = 10001l
	
			TagLink tagLink2 = new TagLink()
			tagLink2.createdBy = USER_NAME
			tagLink2.dateCreated = new Date();
			tagLink2.entityType = String.class.toString()
			tagLink2.entityId = 10000l
	
			tag.addTagLink(tagLink1)
			tag.addTagLink(tagLink2)
			tagDao.update(tag)
			
		}
		assertTrue(tag.tagLinks.size() == 2)
		def savedTagLink = tag.tagLinks.first()
		assertNotNull(savedTagLink.id)
		println("\n\n\n***${savedTagLink.id}***\n\n\n")
		assertTrue(savedTagLink.id > 0)
		
		//Add some more tag links of a different type!
		inTestTx{
	
			TagLink tagLink1 = new TagLink()
			tagLink1.createdBy = USER_NAME
			tagLink1.dateCreated = new Date();
			tagLink1.entityType = Long.class.toString()
			tagLink1.entityId = 9999l
	
			TagLink tagLink2 = new TagLink()
			tagLink2.createdBy = USER_NAME
			tagLink2.dateCreated = new Date();
			tagLink2.entityType = Long.class.toString()
			tagLink2.entityId = 9998l
	
			tag.addTagLink(tagLink1)
			tag.addTagLink(tagLink2)
			tagDao.update(tag)
			
		}
		inTestTx{
			TagLink tagLink1 = new TagLink()
			tagLink1.createdBy = USER_NAME
			tagLink1.dateCreated = new Date();
			tagLink1.entityType = Integer.class.toString()
			tagLink1.entityId = 9997l
			
			TagLink tagLink2 = new TagLink()
			tagLink2.createdBy = USER_NAME
			tagLink2.dateCreated = new Date();
			tagLink2.entityType = Integer.class.toString()
			tagLink2.entityId = 9996l
			
			tag.addTagLink(tagLink1)
			tag.addTagLink(tagLink2)
			tagDao.update(tag)
				
		}
		
		//Query to get only the 'Long' tag links...
		def foundTag
		inTestTx{
			foundTag = tagDao.findTagByNameAndType(TAG_NAME, Long.class)
		}
		foundTag.tagLinks.each{
			println "\n***$it.entityType***\n"
		}
		assertTrue(foundTag.tagLinks.size() == 2)
		assertTrue(foundTag.tagLinks.toArray()[0].entityType.equalsIgnoreCase(Long.class.toString()))
		assertTrue(foundTag.tagLinks.toArray()[1].entityType.equalsIgnoreCase(Long.class.toString()))
		assertTrue(tagDao.count() == 1)
				
		
	}
	*/
	/*
	@Test
	public void findTagByNameAndType(){
		println("TEST: ${this.class.name}:findTagByNameAndType")
		Tag tag = new Tag()
		inTestTx{
			tag.name = TAG_NAME
			tag.displayName = TAG_DISPLAY_NAME
			tag.createdBy = USER_NAME
			tag.note = "A Note"
			//tag.entityType = String.class
			tagDao.create(tag)
			assertNotNull(tag)
			assertTrue(tag.id > 0)
	
			TagLink tagLink1 = new TagLink()
			tagLink1.createdBy = USER_NAME
			tagLink1.dateCreated = new Date();
			tagLink1.entityType = String.class.toString()
			tagLink1.entityId = 9999l
	
			TagLink tagLink2 = new TagLink()
			tagLink2.createdBy = USER_NAME
			tagLink2.dateCreated = new Date();
			tagLink2.entityType = String.class.toString()
			tagLink2.entityId = 9999l
	
			tag.addTagLink(tagLink1)
			tag.addTagLink(tagLink2)
			tagDao.update(tag)
			
		}
		assertTrue(tag.tagLinks.size() == 2)
		def savedTagLink = tag.tagLinks.first()
		assertNotNull(savedTagLink.id)
		println("\n\n\n***${savedTagLink.id}***\n\n\n")
		assertTrue(savedTagLink.id > 0)

		
		Tag tag1 = new Tag()
		inTestTx{
			tag1.name = TAG_NAME + "1"
			tag1.displayName = TAG_DISPLAY_NAME
			tag1.createdBy = USER_NAME
			tag1.note = "A Note"
			//tag1.entityType = Long.class
			tagDao.create(tag1)
			assertNotNull(tag1)
			assertTrue(tag1.id > 0)
	
			TagLink tagLink3 = new TagLink()
			tagLink3.createdBy = USER_NAME
			tagLink3.dateCreated = new Date();
			tagLink3.entityType = Long.class.toString()
			tagLink3.entityId = 9999l
	
			TagLink tagLink4 = new TagLink()
			tagLink4.createdBy = USER_NAME
			tagLink4.dateCreated = new Date();
			tagLink4.entityType = Integer.class.toString()
			tagLink4.entityId = 9999l
	
			tag1.addTagLink(tagLink3)
			tag1.addTagLink(tagLink4)
			tagDao.update(tag1)
			
		}
		assertTrue(tag1.tagLinks.size() == 2)
		
		//Test that the DAO query only brings back one tag and two tag links! 
		def foundTag = (Tag)tagDao.findTagByNameAndType(TAG_NAME + "1", Long.class)
		assertTrue(foundTag.tagLinks.size() == 1)
		assertTrue(foundTag.tagLinks.toArray()[0].entityType.equalsIgnoreCase(Long.class.toString()))
		//Make sure we have two tags in total - one for each type. 
		assertTrue(tagDao.count() == 2) //Shouldn't this fail?
	}
	*/
	@Test
	public void testGetTagLinkCounts(){
		println("TEST: ${this.class.name}:testGetTagLinkCounts")
		Tag tag = new Tag()
		inTestTx{
			tag.name = TAG_NAME
			tag.displayName = TAG_DISPLAY_NAME
			tag.createdBy = USER_NAME
			tag.note = "A Note"
			//tag.entityType = String.class
			tagDao.create(tag)
			assertNotNull(tag)
			assertTrue(tag.id > 0)
	
			TagLink tagLink1 = new TagLink()
			tagLink1.createdBy = USER_NAME
			tagLink1.dateCreated = new Date();
			TagLinkType tagLinkTypeS = new TagLinkType(String.class.toString())
			tagLinkTypeDao.create(tagLinkTypeS)
			tagLink1.entityType = tagLinkTypeS
			tagLink1.entityId = 9999l
	
			TagLink tagLink2 = new TagLink()
			tagLink2.createdBy = USER_NAME
			tagLink2.dateCreated = new Date();
			tagLink2.entityType = tagLinkTypeS
			tagLink2.entityId = 9999l
			
			TagLink tagLink3 = new TagLink()
			tagLink3.createdBy = USER_NAME
			tagLink3.dateCreated = new Date();
			TagLinkType tagLinkTypeL = new TagLinkType(Long.class.toString())
			tagLinkTypeDao.create(tagLinkTypeL)
			tagLink3.entityType = tagLinkTypeL
			tagLink3.entityId = 9999l
	
			tag.addTagLink(tagLink1)
			tag.addTagLink(tagLink2)
			tag.addTagLink(tagLink3)
			tagDao.update(tag)
		}

		assertTrue(tag.getTagLinks().size() == 3)
		
		//Make sure we get all the tagLinks counted correctly...
		assertTrue(tagDao.count(TAG_NAME) == 3) 
		//Make sure we get them by type...
		assertTrue(tagDao.count(TAG_NAME, String.class) == 2)
		assertTrue(tagDao.count(TAG_NAME, Long.class) == 1)
		
	}
	
	@Test
	public void testDeleteTagLink(){
		println("TEST: ${this.class.name}:testDeleteTagLink")
		Tag tag = new Tag()
		inTestTx{
			tag.name = TAG_NAME
			tag.displayName = TAG_DISPLAY_NAME
			tag.createdBy = USER_NAME
			tag.note = "A Note"
			//tag.entityType = String.class
			tagDao.create(tag)
			assertNotNull(tag)
			assertTrue(tag.id > 0)
	
			TagLink tagLink1 = new TagLink()
			tagLink1.createdBy = USER_NAME
			tagLink1.dateCreated = new Date();
			TagLinkType tagLinkTypeS = new TagLinkType(String.class.toString())
			tagLinkTypeDao.create(tagLinkTypeS)
			tagLink1.entityType = tagLinkTypeS
			tagLink1.entityId = 1000l
	
			TagLink tagLink2 = new TagLink()
			tagLink2.createdBy = USER_NAME
			tagLink2.dateCreated = new Date();
			tagLink2.entityType = tagLinkTypeS
			tagLink2.entityId = 1001l
			
			TagLink tagLink3 = new TagLink()
			tagLink3.createdBy = USER_NAME
			tagLink3.dateCreated = new Date();
			TagLinkType tagLinkTypeL = new TagLinkType(Long.class.toString())
			tagLinkTypeDao.create(tagLinkTypeL)
			tagLink3.entityType = tagLinkTypeL
			tagLink3.entityId = 1002l
	
			tag.addTagLink(tagLink1)
			tag.addTagLink(tagLink2)
			tag.addTagLink(tagLink3)
			tagDao.update(tag)
		}
		//Make sure we get all the tagLinks counted correctly...
		assertTrue(tagDao.count(TAG_NAME) == 3)
		//Make sure we get them by type...
		assertTrue(tagDao.count(TAG_NAME, String.class) == 2)
		assertTrue(tagDao.count(TAG_NAME, Long.class) == 1)

		//Delete a tag link!
		inTestTx{
			tag = tagDao.deleteTagLink(TAG_NAME, 1001, String.class.toString())
		}	
		
		long count = tagDao.count(TAG_NAME)
		
		assertTrue(tagDao.count(TAG_NAME) == 2)
		//Make sure we get them by type...
		assertTrue(tagDao.count(TAG_NAME, String.class) == 1)
		assertTrue(tagDao.count(TAG_NAME, Long.class) == 1)
				
	}
	
	@Test
	public void testGetAllTagsForEntity(){
		//testing tagDao.getTagsForEntity(id, clazz) Should return a list of tags for an entity i.e they have more than one!
		println("TEST: ${this.class.name}:testGetAllTagsForEntity")
		long entityId = 1000l
	
		Tag tag = new Tag()
		inTestTx{
			tag.name = TAG_NAME
			tag.displayName = TAG_DISPLAY_NAME
			tag.createdBy = USER_NAME
			tag.note = "A Note"
			
			TagLink tagLink = new TagLink()
			tagLink.createdBy = USER_NAME
			tagLink.dateCreated = new Date();
			TagLinkType tagLinkTypeS = new TagLinkType(String.class.toString())
			tagLinkTypeDao.create(tagLinkTypeS)
			tagLink.entityType = tagLinkTypeS
			tagLink.entityId = entityId
			
			//tag.entityType = String.class
			tagDao.create(tag)
			assertNotNull(tag)
			assertTrue(tag.id > 0)
			tag.addTagLink(tagLink)
			tagDao.update(tag)
		}
		
		Tag tag1 = new Tag()
		inTestTx{
			tag1.name = TAG_NAME + " 1"
			tag1.displayName = TAG_DISPLAY_NAME + " 1"
			tag1.createdBy = USER_NAME
			tag1.note = "A Note"
			
			TagLink tagLink1 = new TagLink()
			tagLink1.createdBy = USER_NAME
			tagLink1.dateCreated = new Date()
			def tagLinkTypeS = tagLinkTypeDao.findByType(String.class.toString())
			tagLink1.entityType = tagLinkTypeS
			tagLink1.entityId = entityId
			
			//tag.entityType = String.class
			tagDao.create(tag1)
			assertNotNull(tag1)
			assertTrue(tag1.id > 0)
			tag1.addTagLink(tagLink1)
			tagDao.update(tag1)
		}

		Tag tag2 = new Tag()
		inTestTx{
			tag2.name = TAG_NAME + " 2"
			tag2.displayName = TAG_DISPLAY_NAME + " 2"
			tag2.createdBy = USER_NAME
			tag2.note = "A Note"
			
			TagLink tagLink2 = new TagLink()
			tagLink2.createdBy = USER_NAME
			tagLink2.dateCreated = new Date();
			def tagLinkTypeS = tagLinkTypeDao.findByType(String.class.toString())
			tagLink2.entityType = tagLinkTypeS
			tagLink2.entityId = entityId
			
			//tag.entityType = String.class
			tagDao.create(tag2)
			assertNotNull(tag2)
			assertTrue(tag2.id > 0)
			tag2.addTagLink(tagLink2)
			tagDao.update(tag2)
		}
			
		List<Tag> tags = tagDao.getTagsForEntity(entityId, String.class)
		assertTrue(tags.size == 3)
		
	}
	
	@Test
	public void testBatchCreateTagLinks(){
		//testing tagDao.getTagsForEntity(id, clazz) Should return a list of tags for an entity i.e they have more than one!
		println("TEST: ${this.class.name}:testBatchCreateTagLinks")
		def tagLinkCount = 80000
		//Create a few thousand data items!
		Data data
		Tag tag = new Tag();
		
		inTestTx{
			tag.name = TAG_NAME
			tag.displayName = TAG_DISPLAY_NAME
			tag.createdBy = USER_NAME
			tag.note = "A Note"
			tagDao.create(tag)
			assertNotNull(tag)
			assertTrue(tag.id > 0)
		}
		
		def dataList = []
		for(i in 1..tagLinkCount){
			data = new Data()
			data.id = i
			data.value = "Value:" + i
			dataList.add(data)
		}
		long time = System.currentTimeMillis();
		println "Starting to create tagLinks..."
		inTestTx{
			tagDao.saveTagLinks(tag, dataList, Data.class, "Test User")
		}
		long completedIn = System.currentTimeMillis() - time;
		println "Finished creating $tagLinkCount tagLinks in $completedIn ms."
		//Test that we have all the data items tagged!
		//get the tag 
		inTestTx{
			tag = tagDao.findTagByName(TAG_NAME)
		}
		
		assert(tag.tagLinks.size() == tagLinkCount)
		
	}
	
}
