/*
 * An XML document type.
 * Localname: transfer-blocks
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.TransferBlocksDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one transfer-blocks(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class TransferBlocksDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TransferBlocksDocument
{
    private static final long serialVersionUID = 1L;
    
    public TransferBlocksDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TRANSFERBLOCKS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "transfer-blocks");
    
    
    /**
     * Gets the "transfer-blocks" element
     */
    public uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks getTransferBlocks()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks target = null;
            target = (uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks)get_store().find_element_user(TRANSFERBLOCKS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "transfer-blocks" element
     */
    public void setTransferBlocks(uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks transferBlocks)
    {
        generatedSetterHelperImpl(transferBlocks, TRANSFERBLOCKS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "transfer-blocks" element
     */
    public uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks addNewTransferBlocks()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks target = null;
            target = (uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks)get_store().add_element_user(TRANSFERBLOCKS$0);
            return target;
        }
    }
    /**
     * An XML transfer-blocks(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class TransferBlocksImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TransferBlocksDocument.TransferBlocks
    {
        private static final long serialVersionUID = 1L;
        
        public TransferBlocksImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName TRANSFERBLOCK$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "transfer-block");
        
        
        /**
         * Gets array of all "transfer-block" elements
         */
        public uk.gov.ofwat.model2.TransferBlockDocument.TransferBlock[] getTransferBlockArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(TRANSFERBLOCK$0, targetList);
                uk.gov.ofwat.model2.TransferBlockDocument.TransferBlock[] result = new uk.gov.ofwat.model2.TransferBlockDocument.TransferBlock[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "transfer-block" element
         */
        public uk.gov.ofwat.model2.TransferBlockDocument.TransferBlock getTransferBlockArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TransferBlockDocument.TransferBlock target = null;
                target = (uk.gov.ofwat.model2.TransferBlockDocument.TransferBlock)get_store().find_element_user(TRANSFERBLOCK$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "transfer-block" element
         */
        public int sizeOfTransferBlockArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TRANSFERBLOCK$0);
            }
        }
        
        /**
         * Sets array of all "transfer-block" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setTransferBlockArray(uk.gov.ofwat.model2.TransferBlockDocument.TransferBlock[] transferBlockArray)
        {
            check_orphaned();
            arraySetterHelper(transferBlockArray, TRANSFERBLOCK$0);
        }
        
        /**
         * Sets ith "transfer-block" element
         */
        public void setTransferBlockArray(int i, uk.gov.ofwat.model2.TransferBlockDocument.TransferBlock transferBlock)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TransferBlockDocument.TransferBlock target = null;
                target = (uk.gov.ofwat.model2.TransferBlockDocument.TransferBlock)get_store().find_element_user(TRANSFERBLOCK$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(transferBlock);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "transfer-block" element
         */
        public uk.gov.ofwat.model2.TransferBlockDocument.TransferBlock insertNewTransferBlock(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TransferBlockDocument.TransferBlock target = null;
                target = (uk.gov.ofwat.model2.TransferBlockDocument.TransferBlock)get_store().insert_element_user(TRANSFERBLOCK$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "transfer-block" element
         */
        public uk.gov.ofwat.model2.TransferBlockDocument.TransferBlock addNewTransferBlock()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TransferBlockDocument.TransferBlock target = null;
                target = (uk.gov.ofwat.model2.TransferBlockDocument.TransferBlock)get_store().add_element_user(TRANSFERBLOCK$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "transfer-block" element
         */
        public void removeTransferBlock(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TRANSFERBLOCK$0, i);
            }
        }
    }
}
