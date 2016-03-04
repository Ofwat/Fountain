/*
 * An XML document type.
 * Localname: text-block
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.TextBlockDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one text-block(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class TextBlockDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TextBlockDocument
{
    private static final long serialVersionUID = 1L;
    
    public TextBlockDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TEXTBLOCK$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "text-block");
    
    
    /**
     * Gets the "text-block" element
     */
    public uk.gov.ofwat.model2.TextBlockDocument.TextBlock getTextBlock()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.TextBlockDocument.TextBlock target = null;
            target = (uk.gov.ofwat.model2.TextBlockDocument.TextBlock)get_store().find_element_user(TEXTBLOCK$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "text-block" element
     */
    public void setTextBlock(uk.gov.ofwat.model2.TextBlockDocument.TextBlock textBlock)
    {
        generatedSetterHelperImpl(textBlock, TEXTBLOCK$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "text-block" element
     */
    public uk.gov.ofwat.model2.TextBlockDocument.TextBlock addNewTextBlock()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.TextBlockDocument.TextBlock target = null;
            target = (uk.gov.ofwat.model2.TextBlockDocument.TextBlock)get_store().add_element_user(TEXTBLOCK$0);
            return target;
        }
    }
    /**
     * An XML text-block(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class TextBlockImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TextBlockDocument.TextBlock
    {
        private static final long serialVersionUID = 1L;
        
        public TextBlockImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName DESCRIPTION$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "description");
        private static final javax.xml.namespace.QName VERSIONNUMBER$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "version-number");
        private static final javax.xml.namespace.QName TEXTFORMATCODE$4 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "text-format-code");
        private static final javax.xml.namespace.QName TEXTTYPECODE$6 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "text-type-code");
        private static final javax.xml.namespace.QName RETIRED$8 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "retired");
        private static final javax.xml.namespace.QName DATA$10 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "data");
        
        
        /**
         * Gets the "description" element
         */
        public java.lang.String getDescription()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DESCRIPTION$0, 0);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DESCRIPTION$0, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DESCRIPTION$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DESCRIPTION$0);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DESCRIPTION$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(DESCRIPTION$0);
                }
                target.set(description);
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
         * Gets the "text-format-code" element
         */
        public java.lang.String getTextFormatCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TEXTFORMATCODE$4, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "text-format-code" element
         */
        public org.apache.xmlbeans.XmlString xgetTextFormatCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TEXTFORMATCODE$4, 0);
                return target;
            }
        }
        
        /**
         * Sets the "text-format-code" element
         */
        public void setTextFormatCode(java.lang.String textFormatCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TEXTFORMATCODE$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TEXTFORMATCODE$4);
                }
                target.setStringValue(textFormatCode);
            }
        }
        
        /**
         * Sets (as xml) the "text-format-code" element
         */
        public void xsetTextFormatCode(org.apache.xmlbeans.XmlString textFormatCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TEXTFORMATCODE$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TEXTFORMATCODE$4);
                }
                target.set(textFormatCode);
            }
        }
        
        /**
         * Gets the "text-type-code" element
         */
        public java.lang.String getTextTypeCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TEXTTYPECODE$6, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "text-type-code" element
         */
        public org.apache.xmlbeans.XmlString xgetTextTypeCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TEXTTYPECODE$6, 0);
                return target;
            }
        }
        
        /**
         * Sets the "text-type-code" element
         */
        public void setTextTypeCode(java.lang.String textTypeCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TEXTTYPECODE$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TEXTTYPECODE$6);
                }
                target.setStringValue(textTypeCode);
            }
        }
        
        /**
         * Sets (as xml) the "text-type-code" element
         */
        public void xsetTextTypeCode(org.apache.xmlbeans.XmlString textTypeCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TEXTTYPECODE$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TEXTTYPECODE$6);
                }
                target.set(textTypeCode);
            }
        }
        
        /**
         * Gets the "retired" element
         */
        public boolean getRetired()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(RETIRED$8, 0);
                if (target == null)
                {
                    return false;
                }
                return target.getBooleanValue();
            }
        }
        
        /**
         * Gets (as xml) the "retired" element
         */
        public org.apache.xmlbeans.XmlBoolean xgetRetired()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(RETIRED$8, 0);
                return target;
            }
        }
        
        /**
         * True if has "retired" element
         */
        public boolean isSetRetired()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(RETIRED$8) != 0;
            }
        }
        
        /**
         * Sets the "retired" element
         */
        public void setRetired(boolean retired)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(RETIRED$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(RETIRED$8);
                }
                target.setBooleanValue(retired);
            }
        }
        
        /**
         * Sets (as xml) the "retired" element
         */
        public void xsetRetired(org.apache.xmlbeans.XmlBoolean retired)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(RETIRED$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(RETIRED$8);
                }
                target.set(retired);
            }
        }
        
        /**
         * Unsets the "retired" element
         */
        public void unsetRetired()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(RETIRED$8, 0);
            }
        }
        
        /**
         * Gets the "data" element
         */
        public java.lang.String getData()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DATA$10, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "data" element
         */
        public org.apache.xmlbeans.XmlString xgetData()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DATA$10, 0);
                return target;
            }
        }
        
        /**
         * Sets the "data" element
         */
        public void setData(java.lang.String data)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DATA$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DATA$10);
                }
                target.setStringValue(data);
            }
        }
        
        /**
         * Sets (as xml) the "data" element
         */
        public void xsetData(org.apache.xmlbeans.XmlString data)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DATA$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(DATA$10);
                }
                target.set(data);
            }
        }
    }
}
