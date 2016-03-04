/*
 * An XML document type.
 * Localname: transfer-block-items
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.TransferBlockItemsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one transfer-block-items(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class TransferBlockItemsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TransferBlockItemsDocument
{
    private static final long serialVersionUID = 1L;
    
    public TransferBlockItemsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TRANSFERBLOCKITEMS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "transfer-block-items");
    
    
    /**
     * Gets the "transfer-block-items" element
     */
    public uk.gov.ofwat.model2.TransferBlockItemsDocument.TransferBlockItems getTransferBlockItems()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.TransferBlockItemsDocument.TransferBlockItems target = null;
            target = (uk.gov.ofwat.model2.TransferBlockItemsDocument.TransferBlockItems)get_store().find_element_user(TRANSFERBLOCKITEMS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "transfer-block-items" element
     */
    public void setTransferBlockItems(uk.gov.ofwat.model2.TransferBlockItemsDocument.TransferBlockItems transferBlockItems)
    {
        generatedSetterHelperImpl(transferBlockItems, TRANSFERBLOCKITEMS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "transfer-block-items" element
     */
    public uk.gov.ofwat.model2.TransferBlockItemsDocument.TransferBlockItems addNewTransferBlockItems()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.TransferBlockItemsDocument.TransferBlockItems target = null;
            target = (uk.gov.ofwat.model2.TransferBlockItemsDocument.TransferBlockItems)get_store().add_element_user(TRANSFERBLOCKITEMS$0);
            return target;
        }
    }
    /**
     * An XML transfer-block-items(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class TransferBlockItemsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TransferBlockItemsDocument.TransferBlockItems
    {
        private static final long serialVersionUID = 1L;
        
        public TransferBlockItemsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName TRANSFERBLOCKITEM$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "transfer-block-item");
        
        
        /**
         * Gets array of all "transfer-block-item" elements
         */
        public uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem[] getTransferBlockItemArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(TRANSFERBLOCKITEM$0, targetList);
                uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem[] result = new uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "transfer-block-item" element
         */
        public uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem getTransferBlockItemArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem target = null;
                target = (uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem)get_store().find_element_user(TRANSFERBLOCKITEM$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "transfer-block-item" element
         */
        public int sizeOfTransferBlockItemArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TRANSFERBLOCKITEM$0);
            }
        }
        
        /**
         * Sets array of all "transfer-block-item" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setTransferBlockItemArray(uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem[] transferBlockItemArray)
        {
            check_orphaned();
            arraySetterHelper(transferBlockItemArray, TRANSFERBLOCKITEM$0);
        }
        
        /**
         * Sets ith "transfer-block-item" element
         */
        public void setTransferBlockItemArray(int i, uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem transferBlockItem)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem target = null;
                target = (uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem)get_store().find_element_user(TRANSFERBLOCKITEM$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(transferBlockItem);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "transfer-block-item" element
         */
        public uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem insertNewTransferBlockItem(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem target = null;
                target = (uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem)get_store().insert_element_user(TRANSFERBLOCKITEM$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "transfer-block-item" element
         */
        public uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem addNewTransferBlockItem()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem target = null;
                target = (uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem)get_store().add_element_user(TRANSFERBLOCKITEM$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "transfer-block-item" element
         */
        public void removeTransferBlockItem(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TRANSFERBLOCKITEM$0, i);
            }
        }
    }
}
