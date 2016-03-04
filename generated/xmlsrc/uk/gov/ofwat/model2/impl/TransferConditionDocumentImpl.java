/*
 * An XML document type.
 * Localname: transfer-condition
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.TransferConditionDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one transfer-condition(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class TransferConditionDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TransferConditionDocument
{
    private static final long serialVersionUID = 1L;
    
    public TransferConditionDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TRANSFERCONDITION$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "transfer-condition");
    
    
    /**
     * Gets the "transfer-condition" element
     */
    public uk.gov.ofwat.model2.TransferConditionDocument.TransferCondition getTransferCondition()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.TransferConditionDocument.TransferCondition target = null;
            target = (uk.gov.ofwat.model2.TransferConditionDocument.TransferCondition)get_store().find_element_user(TRANSFERCONDITION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "transfer-condition" element
     */
    public void setTransferCondition(uk.gov.ofwat.model2.TransferConditionDocument.TransferCondition transferCondition)
    {
        generatedSetterHelperImpl(transferCondition, TRANSFERCONDITION$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "transfer-condition" element
     */
    public uk.gov.ofwat.model2.TransferConditionDocument.TransferCondition addNewTransferCondition()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.TransferConditionDocument.TransferCondition target = null;
            target = (uk.gov.ofwat.model2.TransferConditionDocument.TransferCondition)get_store().add_element_user(TRANSFERCONDITION$0);
            return target;
        }
    }
    /**
     * An XML transfer-condition(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class TransferConditionImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TransferConditionDocument.TransferCondition
    {
        private static final long serialVersionUID = 1L;
        
        public TransferConditionImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName ITEMCODE$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "item-code");
        private static final javax.xml.namespace.QName YEARCODE$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "year-code");
        private static final javax.xml.namespace.QName VALUE$4 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "value");
        private static final javax.xml.namespace.QName FAILUREMESSAGE$6 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "failure-message");
        
        
        /**
         * Gets the "item-code" element
         */
        public java.lang.String getItemCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ITEMCODE$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "item-code" element
         */
        public org.apache.xmlbeans.XmlString xgetItemCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ITEMCODE$0, 0);
                return target;
            }
        }
        
        /**
         * Sets the "item-code" element
         */
        public void setItemCode(java.lang.String itemCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ITEMCODE$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ITEMCODE$0);
                }
                target.setStringValue(itemCode);
            }
        }
        
        /**
         * Sets (as xml) the "item-code" element
         */
        public void xsetItemCode(org.apache.xmlbeans.XmlString itemCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ITEMCODE$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(ITEMCODE$0);
                }
                target.set(itemCode);
            }
        }
        
        /**
         * Gets the "year-code" element
         */
        public java.lang.String getYearCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(YEARCODE$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "year-code" element
         */
        public org.apache.xmlbeans.XmlString xgetYearCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(YEARCODE$2, 0);
                return target;
            }
        }
        
        /**
         * Sets the "year-code" element
         */
        public void setYearCode(java.lang.String yearCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(YEARCODE$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(YEARCODE$2);
                }
                target.setStringValue(yearCode);
            }
        }
        
        /**
         * Sets (as xml) the "year-code" element
         */
        public void xsetYearCode(org.apache.xmlbeans.XmlString yearCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(YEARCODE$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(YEARCODE$2);
                }
                target.set(yearCode);
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
         * Gets the "failure-message" element
         */
        public java.lang.String getFailureMessage()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FAILUREMESSAGE$6, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "failure-message" element
         */
        public org.apache.xmlbeans.XmlString xgetFailureMessage()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(FAILUREMESSAGE$6, 0);
                return target;
            }
        }
        
        /**
         * Sets the "failure-message" element
         */
        public void setFailureMessage(java.lang.String failureMessage)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FAILUREMESSAGE$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(FAILUREMESSAGE$6);
                }
                target.setStringValue(failureMessage);
            }
        }
        
        /**
         * Sets (as xml) the "failure-message" element
         */
        public void xsetFailureMessage(org.apache.xmlbeans.XmlString failureMessage)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(FAILUREMESSAGE$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(FAILUREMESSAGE$6);
                }
                target.set(failureMessage);
            }
        }
    }
}
