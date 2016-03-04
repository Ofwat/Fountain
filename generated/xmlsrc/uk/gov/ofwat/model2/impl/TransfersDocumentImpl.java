/*
 * An XML document type.
 * Localname: transfers
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.TransfersDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one transfers(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class TransfersDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TransfersDocument
{
    private static final long serialVersionUID = 1L;
    
    public TransfersDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TRANSFERS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "transfers");
    
    
    /**
     * Gets the "transfers" element
     */
    public uk.gov.ofwat.model2.TransfersDocument.Transfers getTransfers()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.TransfersDocument.Transfers target = null;
            target = (uk.gov.ofwat.model2.TransfersDocument.Transfers)get_store().find_element_user(TRANSFERS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "transfers" element
     */
    public void setTransfers(uk.gov.ofwat.model2.TransfersDocument.Transfers transfers)
    {
        generatedSetterHelperImpl(transfers, TRANSFERS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "transfers" element
     */
    public uk.gov.ofwat.model2.TransfersDocument.Transfers addNewTransfers()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.TransfersDocument.Transfers target = null;
            target = (uk.gov.ofwat.model2.TransfersDocument.Transfers)get_store().add_element_user(TRANSFERS$0);
            return target;
        }
    }
    /**
     * An XML transfers(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class TransfersImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TransfersDocument.Transfers
    {
        private static final long serialVersionUID = 1L;
        
        public TransfersImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName TRANSFER$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "transfer");
        
        
        /**
         * Gets array of all "transfer" elements
         */
        public uk.gov.ofwat.model2.TransferDocument.Transfer[] getTransferArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(TRANSFER$0, targetList);
                uk.gov.ofwat.model2.TransferDocument.Transfer[] result = new uk.gov.ofwat.model2.TransferDocument.Transfer[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "transfer" element
         */
        public uk.gov.ofwat.model2.TransferDocument.Transfer getTransferArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TransferDocument.Transfer target = null;
                target = (uk.gov.ofwat.model2.TransferDocument.Transfer)get_store().find_element_user(TRANSFER$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "transfer" element
         */
        public int sizeOfTransferArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TRANSFER$0);
            }
        }
        
        /**
         * Sets array of all "transfer" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setTransferArray(uk.gov.ofwat.model2.TransferDocument.Transfer[] transferArray)
        {
            check_orphaned();
            arraySetterHelper(transferArray, TRANSFER$0);
        }
        
        /**
         * Sets ith "transfer" element
         */
        public void setTransferArray(int i, uk.gov.ofwat.model2.TransferDocument.Transfer transfer)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TransferDocument.Transfer target = null;
                target = (uk.gov.ofwat.model2.TransferDocument.Transfer)get_store().find_element_user(TRANSFER$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(transfer);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "transfer" element
         */
        public uk.gov.ofwat.model2.TransferDocument.Transfer insertNewTransfer(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TransferDocument.Transfer target = null;
                target = (uk.gov.ofwat.model2.TransferDocument.Transfer)get_store().insert_element_user(TRANSFER$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "transfer" element
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
         * Removes the ith "transfer" element
         */
        public void removeTransfer(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TRANSFER$0, i);
            }
        }
    }
}
