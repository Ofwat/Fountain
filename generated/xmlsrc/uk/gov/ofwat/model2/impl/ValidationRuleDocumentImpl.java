/*
 * An XML document type.
 * Localname: validation-rule
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.ValidationRuleDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one validation-rule(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class ValidationRuleDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.ValidationRuleDocument
{
    private static final long serialVersionUID = 1L;
    
    public ValidationRuleDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName VALIDATIONRULE$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "validation-rule");
    
    
    /**
     * Gets the "validation-rule" element
     */
    public uk.gov.ofwat.model2.ValidationRuleDocument.ValidationRule getValidationRule()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.ValidationRuleDocument.ValidationRule target = null;
            target = (uk.gov.ofwat.model2.ValidationRuleDocument.ValidationRule)get_store().find_element_user(VALIDATIONRULE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "validation-rule" element
     */
    public void setValidationRule(uk.gov.ofwat.model2.ValidationRuleDocument.ValidationRule validationRule)
    {
        generatedSetterHelperImpl(validationRule, VALIDATIONRULE$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "validation-rule" element
     */
    public uk.gov.ofwat.model2.ValidationRuleDocument.ValidationRule addNewValidationRule()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.ValidationRuleDocument.ValidationRule target = null;
            target = (uk.gov.ofwat.model2.ValidationRuleDocument.ValidationRule)get_store().add_element_user(VALIDATIONRULE$0);
            return target;
        }
    }
    /**
     * An XML validation-rule(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class ValidationRuleImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.ValidationRuleDocument.ValidationRule
    {
        private static final long serialVersionUID = 1L;
        
        public ValidationRuleImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName VALIDATIONRULEDETAILS$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "validation-rule-details");
        private static final javax.xml.namespace.QName VALIDATIONRULEITEMS$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "validation-rule-items");
        
        
        /**
         * Gets the "validation-rule-details" element
         */
        public uk.gov.ofwat.model2.ValidationRuleDetailsDocument.ValidationRuleDetails getValidationRuleDetails()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.ValidationRuleDetailsDocument.ValidationRuleDetails target = null;
                target = (uk.gov.ofwat.model2.ValidationRuleDetailsDocument.ValidationRuleDetails)get_store().find_element_user(VALIDATIONRULEDETAILS$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "validation-rule-details" element
         */
        public void setValidationRuleDetails(uk.gov.ofwat.model2.ValidationRuleDetailsDocument.ValidationRuleDetails validationRuleDetails)
        {
            generatedSetterHelperImpl(validationRuleDetails, VALIDATIONRULEDETAILS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "validation-rule-details" element
         */
        public uk.gov.ofwat.model2.ValidationRuleDetailsDocument.ValidationRuleDetails addNewValidationRuleDetails()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.ValidationRuleDetailsDocument.ValidationRuleDetails target = null;
                target = (uk.gov.ofwat.model2.ValidationRuleDetailsDocument.ValidationRuleDetails)get_store().add_element_user(VALIDATIONRULEDETAILS$0);
                return target;
            }
        }
        
        /**
         * Gets the "validation-rule-items" element
         */
        public uk.gov.ofwat.model2.ValidationRuleItemsDocument.ValidationRuleItems getValidationRuleItems()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.ValidationRuleItemsDocument.ValidationRuleItems target = null;
                target = (uk.gov.ofwat.model2.ValidationRuleItemsDocument.ValidationRuleItems)get_store().find_element_user(VALIDATIONRULEITEMS$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "validation-rule-items" element
         */
        public void setValidationRuleItems(uk.gov.ofwat.model2.ValidationRuleItemsDocument.ValidationRuleItems validationRuleItems)
        {
            generatedSetterHelperImpl(validationRuleItems, VALIDATIONRULEITEMS$2, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "validation-rule-items" element
         */
        public uk.gov.ofwat.model2.ValidationRuleItemsDocument.ValidationRuleItems addNewValidationRuleItems()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.ValidationRuleItemsDocument.ValidationRuleItems target = null;
                target = (uk.gov.ofwat.model2.ValidationRuleItemsDocument.ValidationRuleItems)get_store().add_element_user(VALIDATIONRULEITEMS$2);
                return target;
            }
        }
    }
}
