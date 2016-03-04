/*
 * An XML document type.
 * Localname: cellrange
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.CellrangeDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one cellrange(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class CellrangeDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.CellrangeDocument
{
    private static final long serialVersionUID = 1L;
    
    public CellrangeDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CELLRANGE$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "cellrange");
    
    
    /**
     * Gets the "cellrange" element
     */
    public uk.gov.ofwat.model2.CellrangeDocument.Cellrange getCellrange()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.CellrangeDocument.Cellrange target = null;
            target = (uk.gov.ofwat.model2.CellrangeDocument.Cellrange)get_store().find_element_user(CELLRANGE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "cellrange" element
     */
    public void setCellrange(uk.gov.ofwat.model2.CellrangeDocument.Cellrange cellrange)
    {
        generatedSetterHelperImpl(cellrange, CELLRANGE$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "cellrange" element
     */
    public uk.gov.ofwat.model2.CellrangeDocument.Cellrange addNewCellrange()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.CellrangeDocument.Cellrange target = null;
            target = (uk.gov.ofwat.model2.CellrangeDocument.Cellrange)get_store().add_element_user(CELLRANGE$0);
            return target;
        }
    }
    /**
     * An XML cellrange(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class CellrangeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.CellrangeDocument.Cellrange
    {
        private static final long serialVersionUID = 1L;
        
        public CellrangeImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName STARTYEAR$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "startyear");
        private static final javax.xml.namespace.QName ENDYEAR$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "endyear");
        
        
        /**
         * Gets the "startyear" element
         */
        public java.lang.String getStartyear()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STARTYEAR$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "startyear" element
         */
        public org.apache.xmlbeans.XmlString xgetStartyear()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(STARTYEAR$0, 0);
                return target;
            }
        }
        
        /**
         * Sets the "startyear" element
         */
        public void setStartyear(java.lang.String startyear)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STARTYEAR$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(STARTYEAR$0);
                }
                target.setStringValue(startyear);
            }
        }
        
        /**
         * Sets (as xml) the "startyear" element
         */
        public void xsetStartyear(org.apache.xmlbeans.XmlString startyear)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(STARTYEAR$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(STARTYEAR$0);
                }
                target.set(startyear);
            }
        }
        
        /**
         * Gets the "endyear" element
         */
        public java.lang.String getEndyear()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ENDYEAR$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "endyear" element
         */
        public org.apache.xmlbeans.XmlString xgetEndyear()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ENDYEAR$2, 0);
                return target;
            }
        }
        
        /**
         * Sets the "endyear" element
         */
        public void setEndyear(java.lang.String endyear)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ENDYEAR$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ENDYEAR$2);
                }
                target.setStringValue(endyear);
            }
        }
        
        /**
         * Sets (as xml) the "endyear" element
         */
        public void xsetEndyear(org.apache.xmlbeans.XmlString endyear)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ENDYEAR$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(ENDYEAR$2);
                }
                target.set(endyear);
            }
        }
    }
}
