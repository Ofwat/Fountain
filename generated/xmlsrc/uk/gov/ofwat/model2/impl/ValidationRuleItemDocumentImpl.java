/*
 * An XML document type.
 * Localname: validation-rule-item
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.ValidationRuleItemDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one validation-rule-item(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class ValidationRuleItemDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.ValidationRuleItemDocument
{
    private static final long serialVersionUID = 1L;
    
    public ValidationRuleItemDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName VALIDATIONRULEITEM$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "validation-rule-item");
    
    
    /**
     * Gets the "validation-rule-item" element
     */
    public uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem getValidationRuleItem()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem target = null;
            target = (uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem)get_store().find_element_user(VALIDATIONRULEITEM$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "validation-rule-item" element
     */
    public void setValidationRuleItem(uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem validationRuleItem)
    {
        generatedSetterHelperImpl(validationRuleItem, VALIDATIONRULEITEM$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "validation-rule-item" element
     */
    public uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem addNewValidationRuleItem()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem target = null;
            target = (uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem)get_store().add_element_user(VALIDATIONRULEITEM$0);
            return target;
        }
    }
    /**
     * An XML validation-rule-item(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class ValidationRuleItemImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem
    {
        private static final long serialVersionUID = 1L;
        
        public ValidationRuleItemImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName TYPE$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "type");
        private static final javax.xml.namespace.QName EVALUATE$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "evaluate");
        private static final javax.xml.namespace.QName VALUE$4 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "value");
        
        
        /**
         * Gets the "type" element
         */
        public java.lang.String getType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TYPE$0, 0);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TYPE$0, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TYPE$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TYPE$0);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TYPE$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TYPE$0);
                }
                target.set(type);
            }
        }
        
        /**
         * Gets the "evaluate" element
         */
        public java.lang.String getEvaluate()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EVALUATE$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "evaluate" element
         */
        public org.apache.xmlbeans.XmlString xgetEvaluate()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(EVALUATE$2, 0);
                return target;
            }
        }
        
        /**
         * True if has "evaluate" element
         */
        public boolean isSetEvaluate()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(EVALUATE$2) != 0;
            }
        }
        
        /**
         * Sets the "evaluate" element
         */
        public void setEvaluate(java.lang.String evaluate)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EVALUATE$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(EVALUATE$2);
                }
                target.setStringValue(evaluate);
            }
        }
        
        /**
         * Sets (as xml) the "evaluate" element
         */
        public void xsetEvaluate(org.apache.xmlbeans.XmlString evaluate)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(EVALUATE$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(EVALUATE$2);
                }
                target.set(evaluate);
            }
        }
        
        /**
         * Unsets the "evaluate" element
         */
        public void unsetEvaluate()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(EVALUATE$2, 0);
            }
        }
        
        /**
         * Gets the "value" element
         */
        public java.lang.String getValue()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(VALUE$4, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "value" element
         */
        public org.apache.xmlbeans.XmlString xgetValue()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(VALUE$4, 0);
                return target;
            }
        }
        
        /**
         * True if has "value" element
         */
        public boolean isSetValue()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(VALUE$4) != 0;
            }
        }
        
        /**
         * Sets the "value" element
         */
        public void setValue(java.lang.String value)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(VALUE$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(VALUE$4);
                }
                target.setStringValue(value);
            }
        }
        
        /**
         * Sets (as xml) the "value" element
         */
        public void xsetValue(org.apache.xmlbeans.XmlString value)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(VALUE$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(VALUE$4);
                }
                target.set(value);
            }
        }
        
        /**
         * Unsets the "value" element
         */
        public void unsetValue()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(VALUE$4, 0);
            }
        }
    }
}
