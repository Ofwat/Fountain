/*
 * An XML document type.
 * Localname: year
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.YearDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one year(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class YearDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.YearDocument
{
    private static final long serialVersionUID = 1L;
    
    public YearDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName YEAR$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "year");
    
    
    /**
     * Gets the "year" element
     */
    public uk.gov.ofwat.model2.YearDocument.Year getYear()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.YearDocument.Year target = null;
            target = (uk.gov.ofwat.model2.YearDocument.Year)get_store().find_element_user(YEAR$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "year" element
     */
    public void setYear(uk.gov.ofwat.model2.YearDocument.Year year)
    {
        generatedSetterHelperImpl(year, YEAR$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "year" element
     */
    public uk.gov.ofwat.model2.YearDocument.Year addNewYear()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.YearDocument.Year target = null;
            target = (uk.gov.ofwat.model2.YearDocument.Year)get_store().add_element_user(YEAR$0);
            return target;
        }
    }
    /**
     * An XML year(@http://www.ofwat.gov.uk/model2).
     *
     * This is an atomic type that is a restriction of uk.gov.ofwat.model2.YearDocument$Year.
     */
    public static class YearImpl extends org.apache.xmlbeans.impl.values.JavaStringHolderEx implements uk.gov.ofwat.model2.YearDocument.Year
    {
        private static final long serialVersionUID = 1L;
        
        public YearImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType, true);
        }
        
        protected YearImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
        {
            super(sType, b);
        }
        
        private static final javax.xml.namespace.QName TYPE$0 = 
            new javax.xml.namespace.QName("", "type");
        
        
        /**
         * Gets the "type" attribute
         */
        public java.lang.String getType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TYPE$0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "type" attribute
         */
        public org.apache.xmlbeans.XmlString xgetType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(TYPE$0);
                return target;
            }
        }
        
        /**
         * Sets the "type" attribute
         */
        public void setType(java.lang.String type)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TYPE$0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(TYPE$0);
                }
                target.setStringValue(type);
            }
        }
        
        /**
         * Sets (as xml) the "type" attribute
         */
        public void xsetType(org.apache.xmlbeans.XmlString type)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(TYPE$0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(TYPE$0);
                }
                target.set(type);
            }
        }
    }
}
