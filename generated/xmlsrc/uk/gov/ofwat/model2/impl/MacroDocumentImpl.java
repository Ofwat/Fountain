/*
 * An XML document type.
 * Localname: macro
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.MacroDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one macro(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class MacroDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.MacroDocument
{
    private static final long serialVersionUID = 1L;
    
    public MacroDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName MACRO$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "macro");
    
    
    /**
     * Gets the "macro" element
     */
    public uk.gov.ofwat.model2.MacroDocument.Macro getMacro()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.MacroDocument.Macro target = null;
            target = (uk.gov.ofwat.model2.MacroDocument.Macro)get_store().find_element_user(MACRO$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "macro" element
     */
    public void setMacro(uk.gov.ofwat.model2.MacroDocument.Macro macro)
    {
        generatedSetterHelperImpl(macro, MACRO$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "macro" element
     */
    public uk.gov.ofwat.model2.MacroDocument.Macro addNewMacro()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.MacroDocument.Macro target = null;
            target = (uk.gov.ofwat.model2.MacroDocument.Macro)get_store().add_element_user(MACRO$0);
            return target;
        }
    }
    /**
     * An XML macro(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class MacroImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.MacroDocument.Macro
    {
        private static final long serialVersionUID = 1L;
        
        public MacroImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName NAME$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "name");
        private static final javax.xml.namespace.QName DESCRIPTION$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "description");
        private static final javax.xml.namespace.QName BLOCK$4 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "block");
        private static final javax.xml.namespace.QName CONDITIONALITEMCODE$6 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "conditional-item-code");
        private static final javax.xml.namespace.QName PAGECODE$8 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "page-code");
        
        
        /**
         * Gets the "name" element
         */
        public java.lang.String getName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NAME$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "name" element
         */
        public org.apache.xmlbeans.XmlString xgetName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NAME$0, 0);
                return target;
            }
        }
        
        /**
         * Sets the "name" element
         */
        public void setName(java.lang.String name)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NAME$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(NAME$0);
                }
                target.setStringValue(name);
            }
        }
        
        /**
         * Sets (as xml) the "name" element
         */
        public void xsetName(org.apache.xmlbeans.XmlString name)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NAME$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(NAME$0);
                }
                target.set(name);
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
         * Gets the "block" element
         */
        public java.lang.String getBlock()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BLOCK$4, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "block" element
         */
        public org.apache.xmlbeans.XmlString xgetBlock()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(BLOCK$4, 0);
                return target;
            }
        }
        
        /**
         * Sets the "block" element
         */
        public void setBlock(java.lang.String block)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BLOCK$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(BLOCK$4);
                }
                target.setStringValue(block);
            }
        }
        
        /**
         * Sets (as xml) the "block" element
         */
        public void xsetBlock(org.apache.xmlbeans.XmlString block)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(BLOCK$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(BLOCK$4);
                }
                target.set(block);
            }
        }
        
        /**
         * Gets the "conditional-item-code" element
         */
        public java.lang.String getConditionalItemCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CONDITIONALITEMCODE$6, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "conditional-item-code" element
         */
        public org.apache.xmlbeans.XmlString xgetConditionalItemCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CONDITIONALITEMCODE$6, 0);
                return target;
            }
        }
        
        /**
         * True if has "conditional-item-code" element
         */
        public boolean isSetConditionalItemCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(CONDITIONALITEMCODE$6) != 0;
            }
        }
        
        /**
         * Sets the "conditional-item-code" element
         */
        public void setConditionalItemCode(java.lang.String conditionalItemCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CONDITIONALITEMCODE$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CONDITIONALITEMCODE$6);
                }
                target.setStringValue(conditionalItemCode);
            }
        }
        
        /**
         * Sets (as xml) the "conditional-item-code" element
         */
        public void xsetConditionalItemCode(org.apache.xmlbeans.XmlString conditionalItemCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CONDITIONALITEMCODE$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(CONDITIONALITEMCODE$6);
                }
                target.set(conditionalItemCode);
            }
        }
        
        /**
         * Unsets the "conditional-item-code" element
         */
        public void unsetConditionalItemCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(CONDITIONALITEMCODE$6, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PAGECODE$8, 0);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PAGECODE$8, 0);
                return target;
            }
        }
        
        /**
         * True if has "page-code" element
         */
        public boolean isSetPageCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(PAGECODE$8) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PAGECODE$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PAGECODE$8);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PAGECODE$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PAGECODE$8);
                }
                target.set(pageCode);
            }
        }
        
        /**
         * Unsets the "page-code" element
         */
        public void unsetPageCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(PAGECODE$8, 0);
            }
        }
    }
}
