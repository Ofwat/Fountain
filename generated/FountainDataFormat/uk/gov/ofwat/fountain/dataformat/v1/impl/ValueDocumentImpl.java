/*
 * An XML document type.
 * Localname: value
 * Namespace: http://www.ofwat.gov.uk/fountain/dataformat/v1
 * Java type: uk.gov.ofwat.fountain.dataformat.v1.ValueDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.fountain.dataformat.v1.impl;
/**
 * A document containing one value(@http://www.ofwat.gov.uk/fountain/dataformat/v1) element.
 *
 * This is a complex type.
 */
public class ValueDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.fountain.dataformat.v1.ValueDocument
{
    private static final long serialVersionUID = 1L;
    
    public ValueDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName VALUE$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/fountain/dataformat/v1", "value");
    
    
    /**
     * Gets the "value" element
     */
    public uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value getValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value target = null;
            target = (uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value)get_store().find_element_user(VALUE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "value" element
     */
    public void setValue(uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value value)
    {
        generatedSetterHelperImpl(value, VALUE$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "value" element
     */
    public uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value addNewValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value target = null;
            target = (uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value)get_store().add_element_user(VALUE$0);
            return target;
        }
    }
    /**
     * An XML value(@http://www.ofwat.gov.uk/fountain/dataformat/v1).
     *
     * This is an atomic type that is a restriction of uk.gov.ofwat.fountain.dataformat.v1.ValueDocument$Value.
     */
    public static class ValueImpl extends org.apache.xmlbeans.impl.values.JavaStringHolderEx implements uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value
    {
        private static final long serialVersionUID = 1L;
        
        public ValueImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType, true);
        }
        
        protected ValueImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
        {
            super(sType, b);
        }
        
        private static final javax.xml.namespace.QName COMPANY$0 = 
            new javax.xml.namespace.QName("", "company");
        private static final javax.xml.namespace.QName YEAR$2 = 
            new javax.xml.namespace.QName("", "year");
        private static final javax.xml.namespace.QName ITEM$4 = 
            new javax.xml.namespace.QName("", "item");
        private static final javax.xml.namespace.QName GROUPTYPE$6 = 
            new javax.xml.namespace.QName("", "grouptype");
        private static final javax.xml.namespace.QName GROUP$8 = 
            new javax.xml.namespace.QName("", "group");
        private static final javax.xml.namespace.QName CG$10 = 
            new javax.xml.namespace.QName("", "cg");
        private static final javax.xml.namespace.QName TIMESTAMP$12 = 
            new javax.xml.namespace.QName("", "timestamp");
        
        
        /**
         * Gets the "company" attribute
         */
        public java.lang.String getCompany()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(COMPANY$0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "company" attribute
         */
        public org.apache.xmlbeans.XmlString xgetCompany()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(COMPANY$0);
                return target;
            }
        }
        
        /**
         * Sets the "company" attribute
         */
        public void setCompany(java.lang.String company)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(COMPANY$0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(COMPANY$0);
                }
                target.setStringValue(company);
            }
        }
        
        /**
         * Sets (as xml) the "company" attribute
         */
        public void xsetCompany(org.apache.xmlbeans.XmlString company)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(COMPANY$0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(COMPANY$0);
                }
                target.set(company);
            }
        }
        
        /**
         * Gets the "year" attribute
         */
        public java.lang.String getYear()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(YEAR$2);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "year" attribute
         */
        public org.apache.xmlbeans.XmlString xgetYear()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(YEAR$2);
                return target;
            }
        }
        
        /**
         * Sets the "year" attribute
         */
        public void setYear(java.lang.String year)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(YEAR$2);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(YEAR$2);
                }
                target.setStringValue(year);
            }
        }
        
        /**
         * Sets (as xml) the "year" attribute
         */
        public void xsetYear(org.apache.xmlbeans.XmlString year)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(YEAR$2);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(YEAR$2);
                }
                target.set(year);
            }
        }
        
        /**
         * Gets the "item" attribute
         */
        public java.lang.String getItem()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ITEM$4);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "item" attribute
         */
        public org.apache.xmlbeans.XmlString xgetItem()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(ITEM$4);
                return target;
            }
        }
        
        /**
         * Sets the "item" attribute
         */
        public void setItem(java.lang.String item)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ITEM$4);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ITEM$4);
                }
                target.setStringValue(item);
            }
        }
        
        /**
         * Sets (as xml) the "item" attribute
         */
        public void xsetItem(org.apache.xmlbeans.XmlString item)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(ITEM$4);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(ITEM$4);
                }
                target.set(item);
            }
        }
        
        /**
         * Gets the "grouptype" attribute
         */
        public java.lang.String getGrouptype()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(GROUPTYPE$6);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "grouptype" attribute
         */
        public org.apache.xmlbeans.XmlString xgetGrouptype()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(GROUPTYPE$6);
                return target;
            }
        }
        
        /**
         * True if has "grouptype" attribute
         */
        public boolean isSetGrouptype()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(GROUPTYPE$6) != null;
            }
        }
        
        /**
         * Sets the "grouptype" attribute
         */
        public void setGrouptype(java.lang.String grouptype)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(GROUPTYPE$6);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(GROUPTYPE$6);
                }
                target.setStringValue(grouptype);
            }
        }
        
        /**
         * Sets (as xml) the "grouptype" attribute
         */
        public void xsetGrouptype(org.apache.xmlbeans.XmlString grouptype)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(GROUPTYPE$6);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(GROUPTYPE$6);
                }
                target.set(grouptype);
            }
        }
        
        /**
         * Unsets the "grouptype" attribute
         */
        public void unsetGrouptype()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(GROUPTYPE$6);
            }
        }
        
        /**
         * Gets the "group" attribute
         */
        public java.lang.String getGroup()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(GROUP$8);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "group" attribute
         */
        public org.apache.xmlbeans.XmlString xgetGroup()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(GROUP$8);
                return target;
            }
        }
        
        /**
         * True if has "group" attribute
         */
        public boolean isSetGroup()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(GROUP$8) != null;
            }
        }
        
        /**
         * Sets the "group" attribute
         */
        public void setGroup(java.lang.String group)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(GROUP$8);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(GROUP$8);
                }
                target.setStringValue(group);
            }
        }
        
        /**
         * Sets (as xml) the "group" attribute
         */
        public void xsetGroup(org.apache.xmlbeans.XmlString group)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(GROUP$8);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(GROUP$8);
                }
                target.set(group);
            }
        }
        
        /**
         * Unsets the "group" attribute
         */
        public void unsetGroup()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(GROUP$8);
            }
        }
        
        /**
         * Gets the "cg" attribute
         */
        public java.lang.String getCg()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(CG$10);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "cg" attribute
         */
        public org.apache.xmlbeans.XmlString xgetCg()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(CG$10);
                return target;
            }
        }
        
        /**
         * True if has "cg" attribute
         */
        public boolean isSetCg()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(CG$10) != null;
            }
        }
        
        /**
         * Sets the "cg" attribute
         */
        public void setCg(java.lang.String cg)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(CG$10);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(CG$10);
                }
                target.setStringValue(cg);
            }
        }
        
        /**
         * Sets (as xml) the "cg" attribute
         */
        public void xsetCg(org.apache.xmlbeans.XmlString cg)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(CG$10);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(CG$10);
                }
                target.set(cg);
            }
        }
        
        /**
         * Unsets the "cg" attribute
         */
        public void unsetCg()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(CG$10);
            }
        }
        
        /**
         * Gets the "timestamp" attribute
         */
        public long getTimestamp()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TIMESTAMP$12);
                if (target == null)
                {
                    return 0L;
                }
                return target.getLongValue();
            }
        }
        
        /**
         * Gets (as xml) the "timestamp" attribute
         */
        public org.apache.xmlbeans.XmlLong xgetTimestamp()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlLong target = null;
                target = (org.apache.xmlbeans.XmlLong)get_store().find_attribute_user(TIMESTAMP$12);
                return target;
            }
        }
        
        /**
         * Sets the "timestamp" attribute
         */
        public void setTimestamp(long timestamp)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TIMESTAMP$12);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(TIMESTAMP$12);
                }
                target.setLongValue(timestamp);
            }
        }
        
        /**
         * Sets (as xml) the "timestamp" attribute
         */
        public void xsetTimestamp(org.apache.xmlbeans.XmlLong timestamp)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlLong target = null;
                target = (org.apache.xmlbeans.XmlLong)get_store().find_attribute_user(TIMESTAMP$12);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlLong)get_store().add_attribute_user(TIMESTAMP$12);
                }
                target.set(timestamp);
            }
        }
    }
}
