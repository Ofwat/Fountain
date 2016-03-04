/*
 * An XML document type.
 * Localname: company-pages
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.CompanyPagesDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one company-pages(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class CompanyPagesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.CompanyPagesDocument
{
    private static final long serialVersionUID = 1L;
    
    public CompanyPagesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName COMPANYPAGES$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "company-pages");
    
    
    /**
     * Gets the "company-pages" element
     */
    public uk.gov.ofwat.model2.CompanyPagesDocument.CompanyPages getCompanyPages()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.CompanyPagesDocument.CompanyPages target = null;
            target = (uk.gov.ofwat.model2.CompanyPagesDocument.CompanyPages)get_store().find_element_user(COMPANYPAGES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "company-pages" element
     */
    public void setCompanyPages(uk.gov.ofwat.model2.CompanyPagesDocument.CompanyPages companyPages)
    {
        generatedSetterHelperImpl(companyPages, COMPANYPAGES$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "company-pages" element
     */
    public uk.gov.ofwat.model2.CompanyPagesDocument.CompanyPages addNewCompanyPages()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.CompanyPagesDocument.CompanyPages target = null;
            target = (uk.gov.ofwat.model2.CompanyPagesDocument.CompanyPages)get_store().add_element_user(COMPANYPAGES$0);
            return target;
        }
    }
    /**
     * An XML company-pages(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class CompanyPagesImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.CompanyPagesDocument.CompanyPages
    {
        private static final long serialVersionUID = 1L;
        
        public CompanyPagesImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName COMPANYPAGE$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "company-page");
        
        
        /**
         * Gets array of all "company-page" elements
         */
        public uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage[] getCompanyPageArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(COMPANYPAGE$0, targetList);
                uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage[] result = new uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "company-page" element
         */
        public uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage getCompanyPageArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage target = null;
                target = (uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage)get_store().find_element_user(COMPANYPAGE$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "company-page" element
         */
        public int sizeOfCompanyPageArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(COMPANYPAGE$0);
            }
        }
        
        /**
         * Sets array of all "company-page" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setCompanyPageArray(uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage[] companyPageArray)
        {
            check_orphaned();
            arraySetterHelper(companyPageArray, COMPANYPAGE$0);
        }
        
        /**
         * Sets ith "company-page" element
         */
        public void setCompanyPageArray(int i, uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage companyPage)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage target = null;
                target = (uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage)get_store().find_element_user(COMPANYPAGE$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(companyPage);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "company-page" element
         */
        public uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage insertNewCompanyPage(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage target = null;
                target = (uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage)get_store().insert_element_user(COMPANYPAGE$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "company-page" element
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
         * Removes the ith "company-page" element
         */
        public void removeCompanyPage(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(COMPANYPAGE$0, i);
            }
        }
    }
}
