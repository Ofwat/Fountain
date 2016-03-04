/*
 * An XML document type.
 * Localname: item
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.ItemDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one item(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class ItemDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.ItemDocument
{
    private static final long serialVersionUID = 1L;
    
    public ItemDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ITEM$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "item");
    
    
    /**
     * Gets the "item" element
     */
    public uk.gov.ofwat.model2.ItemDocument.Item getItem()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.ItemDocument.Item target = null;
            target = (uk.gov.ofwat.model2.ItemDocument.Item)get_store().find_element_user(ITEM$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "item" element
     */
    public void setItem(uk.gov.ofwat.model2.ItemDocument.Item item)
    {
        generatedSetterHelperImpl(item, ITEM$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "item" element
     */
    public uk.gov.ofwat.model2.ItemDocument.Item addNewItem()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.ItemDocument.Item target = null;
            target = (uk.gov.ofwat.model2.ItemDocument.Item)get_store().add_element_user(ITEM$0);
            return target;
        }
    }
    /**
     * An XML item(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class ItemImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.ItemDocument.Item
    {
        private static final long serialVersionUID = 1L;
        
        public ItemImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName CODE$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "code");
        private static final javax.xml.namespace.QName VERSIONNUMBER$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "version-number");
        private static final javax.xml.namespace.QName PRICEBASECODE$4 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "pricebase-code");
        private static final javax.xml.namespace.QName PURPOSECODE$6 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "purpose-code");
        private static final javax.xml.namespace.QName TEXTCODE$8 = 
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
         * Gets the "version-number" element
         */
        public java.lang.String getVersionNumber()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(VERSIONNUMBER$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "version-number" element
         */
        public org.apache.xmlbeans.XmlString xgetVersionNumber()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(VERSIONNUMBER$2, 0);
                return target;
            }
        }
        
        /**
         * Sets the "version-number" element
         */
        public void setVersionNumber(java.lang.String versionNumber)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(VERSIONNUMBER$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(VERSIONNUMBER$2);
                }
                target.setStringValue(versionNumber);
            }
        }
        
        /**
         * Sets (as xml) the "version-number" element
         */
        public void xsetVersionNumber(org.apache.xmlbeans.XmlString versionNumber)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(VERSIONNUMBER$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(VERSIONNUMBER$2);
                }
                target.set(versionNumber);
            }
        }
        
        /**
         * Gets the "pricebase-code" element
         */
        public java.lang.String getPricebaseCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PRICEBASECODE$4, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "pricebase-code" element
         */
        public org.apache.xmlbeans.XmlString xgetPricebaseCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PRICEBASECODE$4, 0);
                return target;
            }
        }
        
        /**
         * True if has "pricebase-code" element
         */
        public boolean isSetPricebaseCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(PRICEBASECODE$4) != 0;
            }
        }
        
        /**
         * Sets the "pricebase-code" element
         */
        public void setPricebaseCode(java.lang.String pricebaseCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PRICEBASECODE$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PRICEBASECODE$4);
                }
                target.setStringValue(pricebaseCode);
            }
        }
        
        /**
         * Sets (as xml) the "pricebase-code" element
         */
        public void xsetPricebaseCode(org.apache.xmlbeans.XmlString pricebaseCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PRICEBASECODE$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PRICEBASECODE$4);
                }
                target.set(pricebaseCode);
            }
        }
        
        /**
         * Unsets the "pricebase-code" element
         */
        public void unsetPricebaseCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(PRICEBASECODE$4, 0);
            }
        }
        
        /**
         * Gets the "purpose-code" element
         */
        public java.lang.String getPurposeCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PURPOSECODE$6, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "purpose-code" element
         */
        public org.apache.xmlbeans.XmlString xgetPurposeCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PURPOSECODE$6, 0);
                return target;
            }
        }
        
        /**
         * True if has "purpose-code" element
         */
        public boolean isSetPurposeCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(PURPOSECODE$6) != 0;
            }
        }
        
        /**
         * Sets the "purpose-code" element
         */
        public void setPurposeCode(java.lang.String purposeCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PURPOSECODE$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PURPOSECODE$6);
                }
                target.setStringValue(purposeCode);
            }
        }
        
        /**
         * Sets (as xml) the "purpose-code" element
         */
        public void xsetPurposeCode(org.apache.xmlbeans.XmlString purposeCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PURPOSECODE$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PURPOSECODE$6);
                }
                target.set(purposeCode);
            }
        }
        
        /**
         * Unsets the "purpose-code" element
         */
        public void unsetPurposeCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(PURPOSECODE$6, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TEXTCODE$8, 0);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TEXTCODE$8, 0);
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
                return get_store().count_elements(TEXTCODE$8) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TEXTCODE$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TEXTCODE$8);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TEXTCODE$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TEXTCODE$8);
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
                get_store().remove_element(TEXTCODE$8, 0);
            }
        }
    }
}
