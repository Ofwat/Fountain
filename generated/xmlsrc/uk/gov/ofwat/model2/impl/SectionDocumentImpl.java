/*
 * An XML document type.
 * Localname: section
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.SectionDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one section(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class SectionDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.SectionDocument
{
    private static final long serialVersionUID = 1L;
    
    public SectionDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SECTION$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "section");
    
    
    /**
     * Gets the "section" element
     */
    public uk.gov.ofwat.model2.SectionDocument.Section getSection()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.SectionDocument.Section target = null;
            target = (uk.gov.ofwat.model2.SectionDocument.Section)get_store().find_element_user(SECTION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "section" element
     */
    public void setSection(uk.gov.ofwat.model2.SectionDocument.Section section)
    {
        generatedSetterHelperImpl(section, SECTION$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "section" element
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
     * An XML section(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class SectionImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.SectionDocument.Section
    {
        private static final long serialVersionUID = 1L;
        
        public SectionImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName SECTIONDETAILS$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "sectiondetails");
        private static final javax.xml.namespace.QName FORMS$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "forms");
        private static final javax.xml.namespace.QName DOCUMENTS$4 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "documents");
        private static final javax.xml.namespace.QName LINES$6 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "lines");
        
        
        /**
         * Gets the "sectiondetails" element
         */
        public uk.gov.ofwat.model2.SectiondetailsDocument.Sectiondetails getSectiondetails()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.SectiondetailsDocument.Sectiondetails target = null;
                target = (uk.gov.ofwat.model2.SectiondetailsDocument.Sectiondetails)get_store().find_element_user(SECTIONDETAILS$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "sectiondetails" element
         */
        public void setSectiondetails(uk.gov.ofwat.model2.SectiondetailsDocument.Sectiondetails sectiondetails)
        {
            generatedSetterHelperImpl(sectiondetails, SECTIONDETAILS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "sectiondetails" element
         */
        public uk.gov.ofwat.model2.SectiondetailsDocument.Sectiondetails addNewSectiondetails()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.SectiondetailsDocument.Sectiondetails target = null;
                target = (uk.gov.ofwat.model2.SectiondetailsDocument.Sectiondetails)get_store().add_element_user(SECTIONDETAILS$0);
                return target;
            }
        }
        
        /**
         * Gets the "forms" element
         */
        public uk.gov.ofwat.model2.FormsDocument.Forms getForms()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.FormsDocument.Forms target = null;
                target = (uk.gov.ofwat.model2.FormsDocument.Forms)get_store().find_element_user(FORMS$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * True if has "forms" element
         */
        public boolean isSetForms()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(FORMS$2) != 0;
            }
        }
        
        /**
         * Sets the "forms" element
         */
        public void setForms(uk.gov.ofwat.model2.FormsDocument.Forms forms)
        {
            generatedSetterHelperImpl(forms, FORMS$2, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "forms" element
         */
        public uk.gov.ofwat.model2.FormsDocument.Forms addNewForms()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.FormsDocument.Forms target = null;
                target = (uk.gov.ofwat.model2.FormsDocument.Forms)get_store().add_element_user(FORMS$2);
                return target;
            }
        }
        
        /**
         * Unsets the "forms" element
         */
        public void unsetForms()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(FORMS$2, 0);
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
                target = (uk.gov.ofwat.model2.DocumentsDocument.Documents)get_store().find_element_user(DOCUMENTS$4, 0);
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
                return get_store().count_elements(DOCUMENTS$4) != 0;
            }
        }
        
        /**
         * Sets the "documents" element
         */
        public void setDocuments(uk.gov.ofwat.model2.DocumentsDocument.Documents documents)
        {
            generatedSetterHelperImpl(documents, DOCUMENTS$4, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
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
                target = (uk.gov.ofwat.model2.DocumentsDocument.Documents)get_store().add_element_user(DOCUMENTS$4);
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
                get_store().remove_element(DOCUMENTS$4, 0);
            }
        }
        
        /**
         * Gets the "lines" element
         */
        public uk.gov.ofwat.model2.LinesDocument.Lines getLines()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.LinesDocument.Lines target = null;
                target = (uk.gov.ofwat.model2.LinesDocument.Lines)get_store().find_element_user(LINES$6, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * True if has "lines" element
         */
        public boolean isSetLines()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(LINES$6) != 0;
            }
        }
        
        /**
         * Sets the "lines" element
         */
        public void setLines(uk.gov.ofwat.model2.LinesDocument.Lines lines)
        {
            generatedSetterHelperImpl(lines, LINES$6, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "lines" element
         */
        public uk.gov.ofwat.model2.LinesDocument.Lines addNewLines()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.LinesDocument.Lines target = null;
                target = (uk.gov.ofwat.model2.LinesDocument.Lines)get_store().add_element_user(LINES$6);
                return target;
            }
        }
        
        /**
         * Unsets the "lines" element
         */
        public void unsetLines()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(LINES$6, 0);
            }
        }
    }
}
