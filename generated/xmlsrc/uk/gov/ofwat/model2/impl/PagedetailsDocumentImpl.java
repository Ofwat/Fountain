/*
 * An XML document type.
 * Localname: pagedetails
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.PagedetailsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one pagedetails(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class PagedetailsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.PagedetailsDocument
{
    private static final long serialVersionUID = 1L;
    
    public PagedetailsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName PAGEDETAILS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "pagedetails");
    
    
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
     * An XML pagedetails(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class PagedetailsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.PagedetailsDocument.Pagedetails
    {
        private static final long serialVersionUID = 1L;
        
        public PagedetailsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName CODE$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "code");
        private static final javax.xml.namespace.QName DESCRIPTION$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "description");
        private static final javax.xml.namespace.QName TEXT$4 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "text");
        private static final javax.xml.namespace.QName COMPANYTYPE$6 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "company-type");
        private static final javax.xml.namespace.QName HEADING$8 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "heading");
        private static final javax.xml.namespace.QName COMMERCIALINCONFIDENCE$10 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "commercial-in-confidence");
        private static final javax.xml.namespace.QName HIDDEN$12 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "hidden");
        private static final javax.xml.namespace.QName TEXTCODE$14 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "text-code");
        
        
        /**
         * Gets the "code" element
         */
        public java.lang.String getCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODE$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "code" element
         */
        public org.apache.xmlbeans.XmlString xgetCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CODE$0, 0);
                return target;
            }
        }
        
        /**
         * Sets the "code" element
         */
        public void setCode(java.lang.String code)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODE$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CODE$0);
                }
                target.setStringValue(code);
            }
        }
        
        /**
         * Sets (as xml) the "code" element
         */
        public void xsetCode(org.apache.xmlbeans.XmlString code)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CODE$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(CODE$0);
                }
                target.set(code);
            }
        }
        
        /**
         * Gets the "description" element
         */
        public java.lang.String getDescription()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DESCRIPTION$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "description" element
         */
        public org.apache.xmlbeans.XmlString xgetDescription()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DESCRIPTION$2, 0);
                return target;
            }
        }
        
        /**
         * Sets the "description" element
         */
        public void setDescription(java.lang.String description)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DESCRIPTION$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DESCRIPTION$2);
                }
                target.setStringValue(description);
            }
        }
        
        /**
         * Sets (as xml) the "description" element
         */
        public void xsetDescription(org.apache.xmlbeans.XmlString description)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DESCRIPTION$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(DESCRIPTION$2);
                }
                target.set(description);
            }
        }
        
        /**
         * Gets the "text" element
         */
        public java.lang.String getText()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TEXT$4, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "text" element
         */
        public org.apache.xmlbeans.XmlString xgetText()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TEXT$4, 0);
                return target;
            }
        }
        
        /**
         * True if has "text" element
         */
        public boolean isSetText()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TEXT$4) != 0;
            }
        }
        
        /**
         * Sets the "text" element
         */
        public void setText(java.lang.String text)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TEXT$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TEXT$4);
                }
                target.setStringValue(text);
            }
        }
        
        /**
         * Sets (as xml) the "text" element
         */
        public void xsetText(org.apache.xmlbeans.XmlString text)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TEXT$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TEXT$4);
                }
                target.set(text);
            }
        }
        
        /**
         * Unsets the "text" element
         */
        public void unsetText()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TEXT$4, 0);
            }
        }
        
        /**
         * Gets the "company-type" element
         */
        public java.lang.String getCompanyType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COMPANYTYPE$6, 0);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COMPANYTYPE$6, 0);
                return target;
            }
        }
        
        /**
         * True if has "company-type" element
         */
        public boolean isSetCompanyType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(COMPANYTYPE$6) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COMPANYTYPE$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(COMPANYTYPE$6);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COMPANYTYPE$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(COMPANYTYPE$6);
                }
                target.set(companyType);
            }
        }
        
        /**
         * Unsets the "company-type" element
         */
        public void unsetCompanyType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(COMPANYTYPE$6, 0);
            }
        }
        
        /**
         * Gets the "heading" element
         */
        public java.lang.String getHeading()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(HEADING$8, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "heading" element
         */
        public org.apache.xmlbeans.XmlString xgetHeading()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(HEADING$8, 0);
                return target;
            }
        }
        
        /**
         * True if has "heading" element
         */
        public boolean isSetHeading()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(HEADING$8) != 0;
            }
        }
        
        /**
         * Sets the "heading" element
         */
        public void setHeading(java.lang.String heading)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(HEADING$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(HEADING$8);
                }
                target.setStringValue(heading);
            }
        }
        
        /**
         * Sets (as xml) the "heading" element
         */
        public void xsetHeading(org.apache.xmlbeans.XmlString heading)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(HEADING$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(HEADING$8);
                }
                target.set(heading);
            }
        }
        
        /**
         * Unsets the "heading" element
         */
        public void unsetHeading()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(HEADING$8, 0);
            }
        }
        
        /**
         * Gets the "commercial-in-confidence" element
         */
        public boolean getCommercialInConfidence()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COMMERCIALINCONFIDENCE$10, 0);
                if (target == null)
                {
                    return false;
                }
                return target.getBooleanValue();
            }
        }
        
        /**
         * Gets (as xml) the "commercial-in-confidence" element
         */
        public org.apache.xmlbeans.XmlBoolean xgetCommercialInConfidence()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(COMMERCIALINCONFIDENCE$10, 0);
                return target;
            }
        }
        
        /**
         * True if has "commercial-in-confidence" element
         */
        public boolean isSetCommercialInConfidence()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(COMMERCIALINCONFIDENCE$10) != 0;
            }
        }
        
        /**
         * Sets the "commercial-in-confidence" element
         */
        public void setCommercialInConfidence(boolean commercialInConfidence)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COMMERCIALINCONFIDENCE$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(COMMERCIALINCONFIDENCE$10);
                }
                target.setBooleanValue(commercialInConfidence);
            }
        }
        
        /**
         * Sets (as xml) the "commercial-in-confidence" element
         */
        public void xsetCommercialInConfidence(org.apache.xmlbeans.XmlBoolean commercialInConfidence)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(COMMERCIALINCONFIDENCE$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(COMMERCIALINCONFIDENCE$10);
                }
                target.set(commercialInConfidence);
            }
        }
        
        /**
         * Unsets the "commercial-in-confidence" element
         */
        public void unsetCommercialInConfidence()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(COMMERCIALINCONFIDENCE$10, 0);
            }
        }
        
        /**
         * Gets the "hidden" element
         */
        public boolean getHidden()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(HIDDEN$12, 0);
                if (target == null)
                {
                    return false;
                }
                return target.getBooleanValue();
            }
        }
        
        /**
         * Gets (as xml) the "hidden" element
         */
        public org.apache.xmlbeans.XmlBoolean xgetHidden()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(HIDDEN$12, 0);
                return target;
            }
        }
        
        /**
         * True if has "hidden" element
         */
        public boolean isSetHidden()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(HIDDEN$12) != 0;
            }
        }
        
        /**
         * Sets the "hidden" element
         */
        public void setHidden(boolean hidden)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(HIDDEN$12, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(HIDDEN$12);
                }
                target.setBooleanValue(hidden);
            }
        }
        
        /**
         * Sets (as xml) the "hidden" element
         */
        public void xsetHidden(org.apache.xmlbeans.XmlBoolean hidden)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(HIDDEN$12, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(HIDDEN$12);
                }
                target.set(hidden);
            }
        }
        
        /**
         * Unsets the "hidden" element
         */
        public void unsetHidden()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(HIDDEN$12, 0);
            }
        }
        
        /**
         * Gets the "text-code" element
         */
        public java.lang.String getTextCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TEXTCODE$14, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "text-code" element
         */
        public org.apache.xmlbeans.XmlString xgetTextCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TEXTCODE$14, 0);
                return target;
            }
        }
        
        /**
         * True if has "text-code" element
         */
        public boolean isSetTextCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TEXTCODE$14) != 0;
            }
        }
        
        /**
         * Sets the "text-code" element
         */
        public void setTextCode(java.lang.String textCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TEXTCODE$14, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TEXTCODE$14);
                }
                target.setStringValue(textCode);
            }
        }
        
        /**
         * Sets (as xml) the "text-code" element
         */
        public void xsetTextCode(org.apache.xmlbeans.XmlString textCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TEXTCODE$14, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TEXTCODE$14);
                }
                target.set(textCode);
            }
        }
        
        /**
         * Unsets the "text-code" element
         */
        public void unsetTextCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TEXTCODE$14, 0);
            }
        }
    }
}
