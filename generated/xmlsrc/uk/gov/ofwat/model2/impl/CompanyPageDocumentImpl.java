/*
 * An XML document type.
 * Localname: company-page
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.CompanyPageDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one company-page(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class CompanyPageDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.CompanyPageDocument
{
    private static final long serialVersionUID = 1L;
    
    public CompanyPageDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName COMPANYPAGE$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "company-page");
    
    
    /**
     * Gets the "company-page" element
     */
    public uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage getCompanyPage()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage target = null;
            target = (uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage)get_store().find_element_user(COMPANYPAGE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "company-page" element
     */
    public void setCompanyPage(uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage companyPage)
    {
        generatedSetterHelperImpl(companyPage, COMPANYPAGE$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "company-page" element
     */
    public uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage addNewCompanyPage()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage target = null;
            target = (uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage)get_store().add_element_user(COMPANYPAGE$0);
            return target;
        }
    }
    /**
     * An XML company-page(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class CompanyPageImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage
    {
        private static final long serialVersionUID = 1L;
        
        public CompanyPageImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName COMPANYCODE$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "company-code");
        private static final javax.xml.namespace.QName PAGECODE$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "page-code");
        
        
        /**
         * Gets the "company-code" element
         */
        public java.lang.String getCompanyCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COMPANYCODE$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "company-code" element
         */
        public org.apache.xmlbeans.XmlString xgetCompanyCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COMPANYCODE$0, 0);
                return target;
            }
        }
        
        /**
         * Sets the "company-code" element
         */
        public void setCompanyCode(java.lang.String companyCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COMPANYCODE$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(COMPANYCODE$0);
                }
                target.setStringValue(companyCode);
            }
        }
        
        /**
         * Sets (as xml) the "company-code" element
         */
        public void xsetCompanyCode(org.apache.xmlbeans.XmlString companyCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COMPANYCODE$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(COMPANYCODE$0);
                }
                target.set(companyCode);
            }
        }
        
        /**
         * Gets the "page-code" element
         */
        public java.lang.String getPageCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PAGECODE$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "page-code" element
         */
        public org.apache.xmlbeans.XmlString xgetPageCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PAGECODE$2, 0);
                return target;
            }
        }
        
        /**
         * Sets the "page-code" element
         */
        public void setPageCode(java.lang.String pageCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PAGECODE$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PAGECODE$2);
                }
                target.setStringValue(pageCode);
            }
        }
        
        /**
         * Sets (as xml) the "page-code" element
         */
        public void xsetPageCode(org.apache.xmlbeans.XmlString pageCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PAGECODE$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PAGECODE$2);
                }
                target.set(pageCode);
            }
        }
    }
}
