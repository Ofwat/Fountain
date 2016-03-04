/*
 * An XML document type.
 * Localname: cell
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.CellDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one cell(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class CellDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.CellDocument
{
    private static final long serialVersionUID = 1L;
    
    public CellDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CELL$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "cell");
    
    
    /**
     * Gets the "cell" element
     */
    public uk.gov.ofwat.model2.CellDocument.Cell getCell()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.CellDocument.Cell target = null;
            target = (uk.gov.ofwat.model2.CellDocument.Cell)get_store().find_element_user(CELL$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "cell" element
     */
    public void setCell(uk.gov.ofwat.model2.CellDocument.Cell cell)
    {
        generatedSetterHelperImpl(cell, CELL$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "cell" element
     */
    public uk.gov.ofwat.model2.CellDocument.Cell addNewCell()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.CellDocument.Cell target = null;
            target = (uk.gov.ofwat.model2.CellDocument.Cell)get_store().add_element_user(CELL$0);
            return target;
        }
    }
    /**
     * An XML cell(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class CellImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.CellDocument.Cell
    {
        private static final long serialVersionUID = 1L;
        
        public CellImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName CODE$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "code");
        private static final javax.xml.namespace.QName YEAR$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "year");
        private static final javax.xml.namespace.QName EQUATION$4 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "equation");
        private static final javax.xml.namespace.QName TYPE$6 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "type");
        private static final javax.xml.namespace.QName CGTYPE$8 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "cgtype");
        
        
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
         * True if has "code" element
         */
        public boolean isSetCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(CODE$0) != 0;
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
         * Unsets the "code" element
         */
        public void unsetCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(CODE$0, 0);
            }
        }
        
        /**
         * Gets the "year" element
         */
        public java.lang.String getYear()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(YEAR$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "year" element
         */
        public org.apache.xmlbeans.XmlString xgetYear()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(YEAR$2, 0);
                return target;
            }
        }
        
        /**
         * Sets the "year" element
         */
        public void setYear(java.lang.String year)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(YEAR$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(YEAR$2);
                }
                target.setStringValue(year);
            }
        }
        
        /**
         * Sets (as xml) the "year" element
         */
        public void xsetYear(org.apache.xmlbeans.XmlString year)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(YEAR$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(YEAR$2);
                }
                target.set(year);
            }
        }
        
        /**
         * Gets the "equation" element
         */
        public java.lang.String getEquation()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EQUATION$4, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "equation" element
         */
        public org.apache.xmlbeans.XmlString xgetEquation()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(EQUATION$4, 0);
                return target;
            }
        }
        
        /**
         * True if has "equation" element
         */
        public boolean isSetEquation()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(EQUATION$4) != 0;
            }
        }
        
        /**
         * Sets the "equation" element
         */
        public void setEquation(java.lang.String equation)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EQUATION$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(EQUATION$4);
                }
                target.setStringValue(equation);
            }
        }
        
        /**
         * Sets (as xml) the "equation" element
         */
        public void xsetEquation(org.apache.xmlbeans.XmlString equation)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(EQUATION$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(EQUATION$4);
                }
                target.set(equation);
            }
        }
        
        /**
         * Unsets the "equation" element
         */
        public void unsetEquation()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(EQUATION$4, 0);
            }
        }
        
        /**
         * Gets the "type" element
         */
        public java.lang.String getType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TYPE$6, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "type" element
         */
        public org.apache.xmlbeans.XmlString xgetType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TYPE$6, 0);
                return target;
            }
        }
        
        /**
         * Sets the "type" element
         */
        public void setType(java.lang.String type)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TYPE$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TYPE$6);
                }
                target.setStringValue(type);
            }
        }
        
        /**
         * Sets (as xml) the "type" element
         */
        public void xsetType(org.apache.xmlbeans.XmlString type)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TYPE$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TYPE$6);
                }
                target.set(type);
            }
        }
        
        /**
         * Gets the "cgtype" element
         */
        public java.lang.String getCgtype()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CGTYPE$8, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "cgtype" element
         */
        public org.apache.xmlbeans.XmlString xgetCgtype()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CGTYPE$8, 0);
                return target;
            }
        }
        
        /**
         * True if has "cgtype" element
         */
        public boolean isSetCgtype()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(CGTYPE$8) != 0;
            }
        }
        
        /**
         * Sets the "cgtype" element
         */
        public void setCgtype(java.lang.String cgtype)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CGTYPE$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CGTYPE$8);
                }
                target.setStringValue(cgtype);
            }
        }
        
        /**
         * Sets (as xml) the "cgtype" element
         */
        public void xsetCgtype(org.apache.xmlbeans.XmlString cgtype)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CGTYPE$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(CGTYPE$8);
                }
                target.set(cgtype);
            }
        }
        
        /**
         * Unsets the "cgtype" element
         */
        public void unsetCgtype()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(CGTYPE$8, 0);
            }
        }
    }
}
