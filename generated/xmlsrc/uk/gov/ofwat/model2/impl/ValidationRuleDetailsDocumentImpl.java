/*
 * An XML document type.
 * Localname: validation-rule-details
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.ValidationRuleDetailsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one validation-rule-details(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class ValidationRuleDetailsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.ValidationRuleDetailsDocument
{
    private static final long serialVersionUID = 1L;
    
    public ValidationRuleDetailsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName VALIDATIONRULEDETAILS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "validation-rule-details");
    
    
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
     * An XML validation-rule-details(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class ValidationRuleDetailsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.ValidationRuleDetailsDocument.ValidationRuleDetails
    {
        private static final long serialVersionUID = 1L;
        
        public ValidationRuleDetailsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName CODE$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "code");
        private static final javax.xml.namespace.QName DESCRIPTION$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "description");
        private static final javax.xml.namespace.QName FORMULA$4 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "formula");
        
        
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
         * Gets the "formula" element
         */
        public java.lang.String getFormula()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FORMULA$4, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "formula" element
         */
        public org.apache.xmlbeans.XmlString xgetFormula()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(FORMULA$4, 0);
                return target;
            }
        }
        
        /**
         * Sets the "formula" element
         */
        public void setFormula(java.lang.String formula)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FORMULA$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(FORMULA$4);
                }
                target.setStringValue(formula);
            }
        }
        
        /**
         * Sets (as xml) the "formula" element
         */
        public void xsetFormula(org.apache.xmlbeans.XmlString formula)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(FORMULA$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(FORMULA$4);
                }
                target.set(formula);
            }
        }
    }
}
