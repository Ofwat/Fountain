/*
 * An XML document type.
 * Localname: pages
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.PagesDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one pages(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class PagesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.PagesDocument
{
    private static final long serialVersionUID = 1L;
    
    public PagesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName PAGES$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "pages");
    
    
    /**
     * Gets the "pages" element
     */
    public uk.gov.ofwat.model2.PagesDocument.Pages getPages()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.PagesDocument.Pages target = null;
            target = (uk.gov.ofwat.model2.PagesDocument.Pages)get_store().find_element_user(PAGES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "pages" element
     */
    public void setPages(uk.gov.ofwat.model2.PagesDocument.Pages pages)
    {
        generatedSetterHelperImpl(pages, PAGES$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "pages" element
     */
    public uk.gov.ofwat.model2.PagesDocument.Pages addNewPages()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.PagesDocument.Pages target = null;
            target = (uk.gov.ofwat.model2.PagesDocument.Pages)get_store().add_element_user(PAGES$0);
            return target;
        }
    }
    /**
     * An XML pages(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class PagesImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.PagesDocument.Pages
    {
        private static final long serialVersionUID = 1L;
        
        public PagesImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName PAGE$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "page");
        
        
        /**
         * Gets array of all "page" elements
         */
        public uk.gov.ofwat.model2.PageDocument.Page[] getPageArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(PAGE$0, targetList);
                uk.gov.ofwat.model2.PageDocument.Page[] result = new uk.gov.ofwat.model2.PageDocument.Page[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "page" element
         */
        public uk.gov.ofwat.model2.PageDocument.Page getPageArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.PageDocument.Page target = null;
                target = (uk.gov.ofwat.model2.PageDocument.Page)get_store().find_element_user(PAGE$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "page" element
         */
        public int sizeOfPageArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(PAGE$0);
            }
        }
        
        /**
         * Sets array of all "page" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setPageArray(uk.gov.ofwat.model2.PageDocument.Page[] pageArray)
        {
            check_orphaned();
            arraySetterHelper(pageArray, PAGE$0);
        }
        
        /**
         * Sets ith "page" element
         */
        public void setPageArray(int i, uk.gov.ofwat.model2.PageDocument.Page page)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.PageDocument.Page target = null;
                target = (uk.gov.ofwat.model2.PageDocument.Page)get_store().find_element_user(PAGE$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(page);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "page" element
         */
        public uk.gov.ofwat.model2.PageDocument.Page insertNewPage(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.PageDocument.Page target = null;
                target = (uk.gov.ofwat.model2.PageDocument.Page)get_store().insert_element_user(PAGE$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "page" element
         */
        public uk.gov.ofwat.model2.PageDocument.Page addNewPage()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.PageDocument.Page target = null;
                target = (uk.gov.ofwat.model2.PageDocument.Page)get_store().add_element_user(PAGE$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "page" element
         */
        public void removePage(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(PAGE$0, i);
            }
        }
    }
}
