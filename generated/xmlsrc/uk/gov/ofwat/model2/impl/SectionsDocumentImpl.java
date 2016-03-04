/*
 * An XML document type.
 * Localname: sections
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.SectionsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one sections(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class SectionsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.SectionsDocument
{
    private static final long serialVersionUID = 1L;
    
    public SectionsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SECTIONS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "sections");
    
    
    /**
     * Gets the "sections" element
     */
    public uk.gov.ofwat.model2.SectionsDocument.Sections getSections()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.SectionsDocument.Sections target = null;
            target = (uk.gov.ofwat.model2.SectionsDocument.Sections)get_store().find_element_user(SECTIONS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "sections" element
     */
    public void setSections(uk.gov.ofwat.model2.SectionsDocument.Sections sections)
    {
        generatedSetterHelperImpl(sections, SECTIONS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "sections" element
     */
    public uk.gov.ofwat.model2.SectionsDocument.Sections addNewSections()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.SectionsDocument.Sections target = null;
            target = (uk.gov.ofwat.model2.SectionsDocument.Sections)get_store().add_element_user(SECTIONS$0);
            return target;
        }
    }
    /**
     * An XML sections(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class SectionsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.SectionsDocument.Sections
    {
        private static final long serialVersionUID = 1L;
        
        public SectionsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName SECTION$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "section");
        
        
        /**
         * Gets array of all "section" elements
         */
        public uk.gov.ofwat.model2.SectionDocument.Section[] getSectionArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(SECTION$0, targetList);
                uk.gov.ofwat.model2.SectionDocument.Section[] result = new uk.gov.ofwat.model2.SectionDocument.Section[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "section" element
         */
        public uk.gov.ofwat.model2.SectionDocument.Section getSectionArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.SectionDocument.Section target = null;
                target = (uk.gov.ofwat.model2.SectionDocument.Section)get_store().find_element_user(SECTION$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "section" element
         */
        public int sizeOfSectionArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(SECTION$0);
            }
        }
        
        /**
         * Sets array of all "section" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setSectionArray(uk.gov.ofwat.model2.SectionDocument.Section[] sectionArray)
        {
            check_orphaned();
            arraySetterHelper(sectionArray, SECTION$0);
        }
        
        /**
         * Sets ith "section" element
         */
        public void setSectionArray(int i, uk.gov.ofwat.model2.SectionDocument.Section section)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.SectionDocument.Section target = null;
                target = (uk.gov.ofwat.model2.SectionDocument.Section)get_store().find_element_user(SECTION$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(section);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "section" element
         */
        public uk.gov.ofwat.model2.SectionDocument.Section insertNewSection(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.SectionDocument.Section target = null;
                target = (uk.gov.ofwat.model2.SectionDocument.Section)get_store().insert_element_user(SECTION$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "section" element
         */
        public uk.gov.ofwat.model2.SectionDocument.Section addNewSection()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.SectionDocument.Section target = null;
                target = (uk.gov.ofwat.model2.SectionDocument.Section)get_store().add_element_user(SECTION$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "section" element
         */
        public void removeSection(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(SECTION$0, i);
            }
        }
    }
}
