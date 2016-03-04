/*
 * An XML document type.
 * Localname: page
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.PageDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one page(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class PageDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.PageDocument
{
    private static final long serialVersionUID = 1L;
    
    public PageDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName PAGE$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "page");
    
    
    /**
     * Gets the "page" element
     */
    public uk.gov.ofwat.model2.PageDocument.Page getPage()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.PageDocument.Page target = null;
            target = (uk.gov.ofwat.model2.PageDocument.Page)get_store().find_element_user(PAGE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "page" element
     */
    public void setPage(uk.gov.ofwat.model2.PageDocument.Page page)
    {
        generatedSetterHelperImpl(page, PAGE$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "page" element
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
     * An XML page(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class PageImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.PageDocument.Page
    {
        private static final long serialVersionUID = 1L;
        
        public PageImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName PAGEDETAILS$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "pagedetails");
        private static final javax.xml.namespace.QName DOCUMENTS$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "documents");
        private static final javax.xml.namespace.QName SECTIONS$4 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "sections");
        
        
        /**
         * Gets the "pagedetails" element
         */
        public uk.gov.ofwat.model2.PagedetailsDocument.Pagedetails getPagedetails()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.PagedetailsDocument.Pagedetails target = null;
                target = (uk.gov.ofwat.model2.PagedetailsDocument.Pagedetails)get_store().find_element_user(PAGEDETAILS$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "pagedetails" element
         */
        public void setPagedetails(uk.gov.ofwat.model2.PagedetailsDocument.Pagedetails pagedetails)
        {
            generatedSetterHelperImpl(pagedetails, PAGEDETAILS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "pagedetails" element
         */
        public uk.gov.ofwat.model2.PagedetailsDocument.Pagedetails addNewPagedetails()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.PagedetailsDocument.Pagedetails target = null;
                target = (uk.gov.ofwat.model2.PagedetailsDocument.Pagedetails)get_store().add_element_user(PAGEDETAILS$0);
                return target;
            }
        }
        
        /**
         * Gets the "documents" element
         */
        public uk.gov.ofwat.model2.DocumentsDocument.Documents getDocuments()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.DocumentsDocument.Documents target = null;
                target = (uk.gov.ofwat.model2.DocumentsDocument.Documents)get_store().find_element_user(DOCUMENTS$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * True if has "documents" element
         */
        public boolean isSetDocuments()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(DOCUMENTS$2) != 0;
            }
        }
        
        /**
         * Sets the "documents" element
         */
        public void setDocuments(uk.gov.ofwat.model2.DocumentsDocument.Documents documents)
        {
            generatedSetterHelperImpl(documents, DOCUMENTS$2, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "documents" element
         */
        public uk.gov.ofwat.model2.DocumentsDocument.Documents addNewDocuments()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.DocumentsDocument.Documents target = null;
                target = (uk.gov.ofwat.model2.DocumentsDocument.Documents)get_store().add_element_user(DOCUMENTS$2);
                return target;
            }
        }
        
        /**
         * Unsets the "documents" element
         */
        public void unsetDocuments()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(DOCUMENTS$2, 0);
            }
        }
        
        /**
         * Gets the "sections" element
         */
        public uk.gov.ofwat.model2.SectionsDocument.Sections getSections()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.SectionsDocument.Sections target = null;
                target = (uk.gov.ofwat.model2.SectionsDocument.Sections)get_store().find_element_user(SECTIONS$4, 0);
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
            generatedSetterHelperImpl(sections, SECTIONS$4, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
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
                target = (uk.gov.ofwat.model2.SectionsDocument.Sections)get_store().add_element_user(SECTIONS$4);
                return target;
            }
        }
    }
}
