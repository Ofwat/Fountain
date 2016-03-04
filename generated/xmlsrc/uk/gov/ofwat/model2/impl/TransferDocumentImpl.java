/*
 * An XML document type.
 * Localname: transfer
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.TransferDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one transfer(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class TransferDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TransferDocument
{
    private static final long serialVersionUID = 1L;
    
    public TransferDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TRANSFER$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "transfer");
    
    
    /**
     * Gets the "transfer" element
     */
    public uk.gov.ofwat.model2.TransferDocument.Transfer getTransfer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.TransferDocument.Transfer target = null;
            target = (uk.gov.ofwat.model2.TransferDocument.Transfer)get_store().find_element_user(TRANSFER$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "transfer" element
     */
    public void setTransfer(uk.gov.ofwat.model2.TransferDocument.Transfer transfer)
    {
        generatedSetterHelperImpl(transfer, TRANSFER$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "transfer" element
     */
    public uk.gov.ofwat.model2.TransferDocument.Transfer addNewTransfer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.TransferDocument.Transfer target = null;
            target = (uk.gov.ofwat.model2.TransferDocument.Transfer)get_store().add_element_user(TRANSFER$0);
            return target;
        }
    }
    /**
     * An XML transfer(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class TransferImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TransferDocument.Transfer
    {
        private static final long serialVersionUID = 1L;
        
        public TransferImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName DESCRIPTION$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "description");
        private static final javax.xml.namespace.QName TRANSFERCONDITION$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "transfer-condition");
        private static final javax.xml.namespace.QName TRANSFERBLOCKS$4 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "transfer-blocks");
        
        
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
         * Gets the "transfer-condition" element
         */
        public uk.gov.ofwat.model2.TransferConditionDocument.TransferCondition getTransferCondition()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TransferConditionDocument.TransferCondition target = null;
                target = (uk.gov.ofwat.model2.TransferConditionDocument.TransferCondition)get_store().find_element_user(TRANSFERCONDITION$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * True if has "transfer-condition" element
         */
        public boolean isSetTransferCondition()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TRANSFERCONDITION$2) != 0;
            }
        }
        
        /**
         * Sets the "transfer-condition" element
         */
        public void setTransferCondition(uk.gov.ofwat.model2.TransferConditionDocument.TransferCondition transferCondition)
        {
            generatedSetterHelperImpl(transferCondition, TRANSFERCONDITION$2, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
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
                target = (uk.gov.ofwat.model2.TransferConditionDocument.TransferCondition)get_store().add_element_user(TRANSFERCONDITION$2);
                return target;
            }
        }
        
        /**
         * Unsets the "transfer-condition" element
         */
        public void unsetTransferCondition()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TRANSFERCONDITION$2, 0);
            }
        }
        
        /**
         * Gets array of all "transfer-blocks" elements
         */
        public uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks[] getTransferBlocksArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(TRANSFERBLOCKS$4, targetList);
                uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks[] result = new uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "transfer-blocks" element
         */
        public uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks getTransferBlocksArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks target = null;
                target = (uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks)get_store().find_element_user(TRANSFERBLOCKS$4, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "transfer-blocks" element
         */
        public int sizeOfTransferBlocksArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TRANSFERBLOCKS$4);
            }
        }
        
        /**
         * Sets array of all "transfer-blocks" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setTransferBlocksArray(uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks[] transferBlocksArray)
        {
            check_orphaned();
            arraySetterHelper(transferBlocksArray, TRANSFERBLOCKS$4);
        }
        
        /**
         * Sets ith "transfer-blocks" element
         */
        public void setTransferBlocksArray(int i, uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks transferBlocks)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks target = null;
                target = (uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks)get_store().find_element_user(TRANSFERBLOCKS$4, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(transferBlocks);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "transfer-blocks" element
         */
        public uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks insertNewTransferBlocks(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks target = null;
                target = (uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks)get_store().insert_element_user(TRANSFERBLOCKS$4, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "transfer-blocks" element
         */
        public uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks addNewTransferBlocks()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks target = null;
                target = (uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks)get_store().add_element_user(TRANSFERBLOCKS$4);
                return target;
            }
        }
        
        /**
         * Removes the ith "transfer-blocks" element
         */
        public void removeTransferBlocks(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TRANSFERBLOCKS$4, i);
            }
        }
    }
}
