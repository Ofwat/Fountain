/*
 * An XML document type.
 * Localname: validation-rules
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.ValidationRulesDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one validation-rules(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class ValidationRulesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.ValidationRulesDocument
{
    private static final long serialVersionUID = 1L;
    
    public ValidationRulesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName VALIDATIONRULES$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "validation-rules");
    
    
    /**
     * Gets the "validation-rules" element
     */
    public uk.gov.ofwat.model2.ValidationRulesDocument.ValidationRules getValidationRules()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.ValidationRulesDocument.ValidationRules target = null;
            target = (uk.gov.ofwat.model2.ValidationRulesDocument.ValidationRules)get_store().find_element_user(VALIDATIONRULES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "validation-rules" element
     */
    public void setValidationRules(uk.gov.ofwat.model2.ValidationRulesDocument.ValidationRules validationRules)
    {
        generatedSetterHelperImpl(validationRules, VALIDATIONRULES$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "validation-rules" element
     */
    public uk.gov.ofwat.model2.ValidationRulesDocument.ValidationRules addNewValidationRules()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.ValidationRulesDocument.ValidationRules target = null;
            target = (uk.gov.ofwat.model2.ValidationRulesDocument.ValidationRules)get_store().add_element_user(VALIDATIONRULES$0);
            return target;
        }
    }
    /**
     * An XML validation-rules(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class ValidationRulesImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.ValidationRulesDocument.ValidationRules
    {
        private static final long serialVersionUID = 1L;
        
        public ValidationRulesImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName VALIDATIONRULE$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "validation-rule");
        
        
        /**
         * Gets array of all "validation-rule" elements
         */
        public uk.gov.ofwat.model2.ValidationRuleDocument.ValidationRule[] getValidationRuleArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(VALIDATIONRULE$0, targetList);
                uk.gov.ofwat.model2.ValidationRuleDocument.ValidationRule[] result = new uk.gov.ofwat.model2.ValidationRuleDocument.ValidationRule[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "validation-rule" element
         */
        public uk.gov.ofwat.model2.ValidationRuleDocument.ValidationRule getValidationRuleArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.ValidationRuleDocument.ValidationRule target = null;
                target = (uk.gov.ofwat.model2.ValidationRuleDocument.ValidationRule)get_store().find_element_user(VALIDATIONRULE$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "validation-rule" element
         */
        public int sizeOfValidationRuleArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(VALIDATIONRULE$0);
            }
        }
        
        /**
         * Sets array of all "validation-rule" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setValidationRuleArray(uk.gov.ofwat.model2.ValidationRuleDocument.ValidationRule[] validationRuleArray)
        {
            check_orphaned();
            arraySetterHelper(validationRuleArray, VALIDATIONRULE$0);
        }
        
        /**
         * Sets ith "validation-rule" element
         */
        public void setValidationRuleArray(int i, uk.gov.ofwat.model2.ValidationRuleDocument.ValidationRule validationRule)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.ValidationRuleDocument.ValidationRule target = null;
                target = (uk.gov.ofwat.model2.ValidationRuleDocument.ValidationRule)get_store().find_element_user(VALIDATIONRULE$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(validationRule);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "validation-rule" element
         */
        public uk.gov.ofwat.model2.ValidationRuleDocument.ValidationRule insertNewValidationRule(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.ValidationRuleDocument.ValidationRule target = null;
                target = (uk.gov.ofwat.model2.ValidationRuleDocument.ValidationRule)get_store().insert_element_user(VALIDATIONRULE$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "validation-rule" element
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
         * Removes the ith "validation-rule" element
         */
        public void removeValidationRule(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(VALIDATIONRULE$0, i);
            }
        }
    }
}
