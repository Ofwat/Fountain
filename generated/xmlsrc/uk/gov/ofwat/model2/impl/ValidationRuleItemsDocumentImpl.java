/*
 * An XML document type.
 * Localname: validation-rule-items
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.ValidationRuleItemsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one validation-rule-items(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class ValidationRuleItemsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.ValidationRuleItemsDocument
{
    private static final long serialVersionUID = 1L;
    
    public ValidationRuleItemsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName VALIDATIONRULEITEMS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "validation-rule-items");
    
    
    /**
     * Gets the "validation-rule-items" element
     */
    public uk.gov.ofwat.model2.ValidationRuleItemsDocument.ValidationRuleItems getValidationRuleItems()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.ValidationRuleItemsDocument.ValidationRuleItems target = null;
            target = (uk.gov.ofwat.model2.ValidationRuleItemsDocument.ValidationRuleItems)get_store().find_element_user(VALIDATIONRULEITEMS$0, 0);
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
        generatedSetterHelperImpl(validationRuleItems, VALIDATIONRULEITEMS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
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
            target = (uk.gov.ofwat.model2.ValidationRuleItemsDocument.ValidationRuleItems)get_store().add_element_user(VALIDATIONRULEITEMS$0);
            return target;
        }
    }
    /**
     * An XML validation-rule-items(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class ValidationRuleItemsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.ValidationRuleItemsDocument.ValidationRuleItems
    {
        private static final long serialVersionUID = 1L;
        
        public ValidationRuleItemsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName VALIDATIONRULEITEM$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "validation-rule-item");
        
        
        /**
         * Gets array of all "validation-rule-item" elements
         */
        public uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem[] getValidationRuleItemArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(VALIDATIONRULEITEM$0, targetList);
                uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem[] result = new uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "validation-rule-item" element
         */
        public uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem getValidationRuleItemArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem target = null;
                target = (uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem)get_store().find_element_user(VALIDATIONRULEITEM$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "validation-rule-item" element
         */
        public int sizeOfValidationRuleItemArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(VALIDATIONRULEITEM$0);
            }
        }
        
        /**
         * Sets array of all "validation-rule-item" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setValidationRuleItemArray(uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem[] validationRuleItemArray)
        {
            check_orphaned();
            arraySetterHelper(validationRuleItemArray, VALIDATIONRULEITEM$0);
        }
        
        /**
         * Sets ith "validation-rule-item" element
         */
        public void setValidationRuleItemArray(int i, uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem validationRuleItem)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem target = null;
                target = (uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem)get_store().find_element_user(VALIDATIONRULEITEM$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(validationRuleItem);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "validation-rule-item" element
         */
        public uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem insertNewValidationRuleItem(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem target = null;
                target = (uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem)get_store().insert_element_user(VALIDATIONRULEITEM$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "validation-rule-item" element
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
         * Removes the ith "validation-rule-item" element
         */
        public void removeValidationRuleItem(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(VALIDATIONRULEITEM$0, i);
            }
        }
    }
}
