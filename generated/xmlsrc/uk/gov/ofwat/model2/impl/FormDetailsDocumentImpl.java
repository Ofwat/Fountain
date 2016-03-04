/*
 * An XML document type.
 * Localname: form-details
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.FormDetailsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one form-details(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class FormDetailsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.FormDetailsDocument
{
    private static final long serialVersionUID = 1L;
    
    public FormDetailsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FORMDETAILS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "form-details");
    
    
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
     * An XML form-details(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class FormDetailsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.FormDetailsDocument.FormDetails
    {
        private static final long serialVersionUID = 1L;
        
        public FormDetailsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName COMPANYTYPE$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "company-type");
        
        
        /**
         * Gets the "company-type" element
         */
        public java.lang.String getCompanyType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COMPANYTYPE$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "company-type" element
         */
        public org.apache.xmlbeans.XmlString xgetCompanyType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COMPANYTYPE$0, 0);
                return target;
            }
        }
        
        /**
         * Sets the "company-type" element
         */
        public void setCompanyType(java.lang.String companyType)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COMPANYTYPE$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(COMPANYTYPE$0);
                }
                target.setStringValue(companyType);
            }
        }
        
        /**
         * Sets (as xml) the "company-type" element
         */
        public void xsetCompanyType(org.apache.xmlbeans.XmlString companyType)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COMPANYTYPE$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(COMPANYTYPE$0);
                }
                target.set(companyType);
            }
        }
    }
}
