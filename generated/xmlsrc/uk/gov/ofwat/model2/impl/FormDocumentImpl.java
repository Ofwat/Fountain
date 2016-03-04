/*
 * An XML document type.
 * Localname: form
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.FormDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one form(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class FormDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.FormDocument
{
    private static final long serialVersionUID = 1L;
    
    public FormDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FORM$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "form");
    
    
    /**
     * Gets the "form" element
     */
    public uk.gov.ofwat.model2.FormDocument.Form getForm()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.FormDocument.Form target = null;
            target = (uk.gov.ofwat.model2.FormDocument.Form)get_store().find_element_user(FORM$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "form" element
     */
    public void setForm(uk.gov.ofwat.model2.FormDocument.Form form)
    {
        generatedSetterHelperImpl(form, FORM$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "form" element
     */
    public uk.gov.ofwat.model2.FormDocument.Form addNewForm()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.FormDocument.Form target = null;
            target = (uk.gov.ofwat.model2.FormDocument.Form)get_store().add_element_user(FORM$0);
            return target;
        }
    }
    /**
     * An XML form(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class FormImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.FormDocument.Form
    {
        private static final long serialVersionUID = 1L;
        
        public FormImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName FORMDETAILS$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "form-details");
        private static final javax.xml.namespace.QName FORMHEADINGSTOP$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "form-headings-top");
        private static final javax.xml.namespace.QName FORMHEADINGSLEFT$4 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "form-headings-left");
        private static final javax.xml.namespace.QName FORMCELLS$6 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "form-cells");
        
        
        /**
         * Gets the "form-details" element
         */
        public uk.gov.ofwat.model2.FormDetailsDocument.FormDetails getFormDetails()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.FormDetailsDocument.FormDetails target = null;
                target = (uk.gov.ofwat.model2.FormDetailsDocument.FormDetails)get_store().find_element_user(FORMDETAILS$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "form-details" element
         */
        public void setFormDetails(uk.gov.ofwat.model2.FormDetailsDocument.FormDetails formDetails)
        {
            generatedSetterHelperImpl(formDetails, FORMDETAILS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "form-details" element
         */
        public uk.gov.ofwat.model2.FormDetailsDocument.FormDetails addNewFormDetails()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.FormDetailsDocument.FormDetails target = null;
                target = (uk.gov.ofwat.model2.FormDetailsDocument.FormDetails)get_store().add_element_user(FORMDETAILS$0);
                return target;
            }
        }
        
        /**
         * Gets the "form-headings-top" element
         */
        public uk.gov.ofwat.model2.FormHeadingsTopDocument.FormHeadingsTop getFormHeadingsTop()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.FormHeadingsTopDocument.FormHeadingsTop target = null;
                target = (uk.gov.ofwat.model2.FormHeadingsTopDocument.FormHeadingsTop)get_store().find_element_user(FORMHEADINGSTOP$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * True if has "form-headings-top" element
         */
        public boolean isSetFormHeadingsTop()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(FORMHEADINGSTOP$2) != 0;
            }
        }
        
        /**
         * Sets the "form-headings-top" element
         */
        public void setFormHeadingsTop(uk.gov.ofwat.model2.FormHeadingsTopDocument.FormHeadingsTop formHeadingsTop)
        {
            generatedSetterHelperImpl(formHeadingsTop, FORMHEADINGSTOP$2, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "form-headings-top" element
         */
        public uk.gov.ofwat.model2.FormHeadingsTopDocument.FormHeadingsTop addNewFormHeadingsTop()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.FormHeadingsTopDocument.FormHeadingsTop target = null;
                target = (uk.gov.ofwat.model2.FormHeadingsTopDocument.FormHeadingsTop)get_store().add_element_user(FORMHEADINGSTOP$2);
                return target;
            }
        }
        
        /**
         * Unsets the "form-headings-top" element
         */
        public void unsetFormHeadingsTop()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(FORMHEADINGSTOP$2, 0);
            }
        }
        
        /**
         * Gets the "form-headings-left" element
         */
        public uk.gov.ofwat.model2.FormHeadingsLeftDocument.FormHeadingsLeft getFormHeadingsLeft()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.FormHeadingsLeftDocument.FormHeadingsLeft target = null;
                target = (uk.gov.ofwat.model2.FormHeadingsLeftDocument.FormHeadingsLeft)get_store().find_element_user(FORMHEADINGSLEFT$4, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * True if has "form-headings-left" element
         */
        public boolean isSetFormHeadingsLeft()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(FORMHEADINGSLEFT$4) != 0;
            }
        }
        
        /**
         * Sets the "form-headings-left" element
         */
        public void setFormHeadingsLeft(uk.gov.ofwat.model2.FormHeadingsLeftDocument.FormHeadingsLeft formHeadingsLeft)
        {
            generatedSetterHelperImpl(formHeadingsLeft, FORMHEADINGSLEFT$4, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "form-headings-left" element
         */
        public uk.gov.ofwat.model2.FormHeadingsLeftDocument.FormHeadingsLeft addNewFormHeadingsLeft()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.FormHeadingsLeftDocument.FormHeadingsLeft target = null;
                target = (uk.gov.ofwat.model2.FormHeadingsLeftDocument.FormHeadingsLeft)get_store().add_element_user(FORMHEADINGSLEFT$4);
                return target;
            }
        }
        
        /**
         * Unsets the "form-headings-left" element
         */
        public void unsetFormHeadingsLeft()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(FORMHEADINGSLEFT$4, 0);
            }
        }
        
        /**
         * Gets the "form-cells" element
         */
        public uk.gov.ofwat.model2.FormCellsDocument.FormCells getFormCells()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.FormCellsDocument.FormCells target = null;
                target = (uk.gov.ofwat.model2.FormCellsDocument.FormCells)get_store().find_element_user(FORMCELLS$6, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "form-cells" element
         */
        public void setFormCells(uk.gov.ofwat.model2.FormCellsDocument.FormCells formCells)
        {
            generatedSetterHelperImpl(formCells, FORMCELLS$6, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "form-cells" element
         */
        public uk.gov.ofwat.model2.FormCellsDocument.FormCells addNewFormCells()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.FormCellsDocument.FormCells target = null;
                target = (uk.gov.ofwat.model2.FormCellsDocument.FormCells)get_store().add_element_user(FORMCELLS$6);
                return target;
            }
        }
    }
}
