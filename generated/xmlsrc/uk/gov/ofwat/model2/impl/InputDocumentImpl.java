/*
 * An XML document type.
 * Localname: input
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.InputDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one input(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class InputDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.InputDocument
{
    private static final long serialVersionUID = 1L;
    
    public InputDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName INPUT$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "input");
    
    
    /**
     * Gets the "input" element
     */
    public uk.gov.ofwat.model2.InputDocument.Input getInput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.InputDocument.Input target = null;
            target = (uk.gov.ofwat.model2.InputDocument.Input)get_store().find_element_user(INPUT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "input" element
     */
    public void setInput(uk.gov.ofwat.model2.InputDocument.Input input)
    {
        generatedSetterHelperImpl(input, INPUT$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "input" element
     */
    public uk.gov.ofwat.model2.InputDocument.Input addNewInput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.InputDocument.Input target = null;
            target = (uk.gov.ofwat.model2.InputDocument.Input)get_store().add_element_user(INPUT$0);
            return target;
        }
    }
    /**
     * An XML input(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class InputImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.InputDocument.Input
    {
        private static final long serialVersionUID = 1L;
        
        public InputImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName CODE$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "code");
        private static final javax.xml.namespace.QName MODEL$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "model");
        private static final javax.xml.namespace.QName TAG$4 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "tag");
        private static final javax.xml.namespace.QName VERSION$6 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "version");
        private static final javax.xml.namespace.QName COMPANY$8 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "company");
        private static final javax.xml.namespace.QName DEFAULT$10 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "default");
        
        
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
         * Gets the "model" element
         */
        public java.lang.String getModel()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MODEL$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "model" element
         */
        public org.apache.xmlbeans.XmlString xgetModel()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(MODEL$2, 0);
                return target;
            }
        }
        
        /**
         * Sets the "model" element
         */
        public void setModel(java.lang.String model)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MODEL$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(MODEL$2);
                }
                target.setStringValue(model);
            }
        }
        
        /**
         * Sets (as xml) the "model" element
         */
        public void xsetModel(org.apache.xmlbeans.XmlString model)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(MODEL$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(MODEL$2);
                }
                target.set(model);
            }
        }
        
        /**
         * Gets the "tag" element
         */
        public java.lang.String getTag()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TAG$4, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "tag" element
         */
        public org.apache.xmlbeans.XmlString xgetTag()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TAG$4, 0);
                return target;
            }
        }
        
        /**
         * True if has "tag" element
         */
        public boolean isSetTag()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TAG$4) != 0;
            }
        }
        
        /**
         * Sets the "tag" element
         */
        public void setTag(java.lang.String tag)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TAG$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TAG$4);
                }
                target.setStringValue(tag);
            }
        }
        
        /**
         * Sets (as xml) the "tag" element
         */
        public void xsetTag(org.apache.xmlbeans.XmlString tag)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TAG$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TAG$4);
                }
                target.set(tag);
            }
        }
        
        /**
         * Unsets the "tag" element
         */
        public void unsetTag()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TAG$4, 0);
            }
        }
        
        /**
         * Gets the "version" element
         */
        public java.lang.String getVersion()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(VERSION$6, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "version" element
         */
        public org.apache.xmlbeans.XmlString xgetVersion()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(VERSION$6, 0);
                return target;
            }
        }
        
        /**
         * Sets the "version" element
         */
        public void setVersion(java.lang.String version)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(VERSION$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(VERSION$6);
                }
                target.setStringValue(version);
            }
        }
        
        /**
         * Sets (as xml) the "version" element
         */
        public void xsetVersion(org.apache.xmlbeans.XmlString version)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(VERSION$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(VERSION$6);
                }
                target.set(version);
            }
        }
        
        /**
         * Gets the "company" element
         */
        public java.lang.String getCompany()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COMPANY$8, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "company" element
         */
        public org.apache.xmlbeans.XmlString xgetCompany()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COMPANY$8, 0);
                return target;
            }
        }
        
        /**
         * Sets the "company" element
         */
        public void setCompany(java.lang.String company)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COMPANY$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(COMPANY$8);
                }
                target.setStringValue(company);
            }
        }
        
        /**
         * Sets (as xml) the "company" element
         */
        public void xsetCompany(org.apache.xmlbeans.XmlString company)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COMPANY$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(COMPANY$8);
                }
                target.set(company);
            }
        }
        
        /**
         * Gets the "default" element
         */
        public boolean getDefault()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DEFAULT$10, 0);
                if (target == null)
                {
                    return false;
                }
                return target.getBooleanValue();
            }
        }
        
        /**
         * Gets (as xml) the "default" element
         */
        public org.apache.xmlbeans.XmlBoolean xgetDefault()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(DEFAULT$10, 0);
                return target;
            }
        }
        
        /**
         * Sets the "default" element
         */
        public void setDefault(boolean xdefault)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DEFAULT$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DEFAULT$10);
                }
                target.setBooleanValue(xdefault);
            }
        }
        
        /**
         * Sets (as xml) the "default" element
         */
        public void xsetDefault(org.apache.xmlbeans.XmlBoolean xdefault)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(DEFAULT$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(DEFAULT$10);
                }
                target.set(xdefault);
            }
        }
    }
}
