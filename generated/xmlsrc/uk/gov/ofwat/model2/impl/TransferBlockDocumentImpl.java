/*
 * An XML document type.
 * Localname: transfer-block
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.TransferBlockDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one transfer-block(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class TransferBlockDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TransferBlockDocument
{
    private static final long serialVersionUID = 1L;
    
    public TransferBlockDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TRANSFERBLOCK$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "transfer-block");
    
    
    /**
     * Gets the "transfer-block" element
     */
    public uk.gov.ofwat.model2.TransferBlockDocument.TransferBlock getTransferBlock()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.TransferBlockDocument.TransferBlock target = null;
            target = (uk.gov.ofwat.model2.TransferBlockDocument.TransferBlock)get_store().find_element_user(TRANSFERBLOCK$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "transfer-block" element
     */
    public void setTransferBlock(uk.gov.ofwat.model2.TransferBlockDocument.TransferBlock transferBlock)
    {
        generatedSetterHelperImpl(transferBlock, TRANSFERBLOCK$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "transfer-block" element
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
     * An XML transfer-block(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class TransferBlockImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TransferBlockDocument.TransferBlock
    {
        private static final long serialVersionUID = 1L;
        
        public TransferBlockImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName TRANSFERBLOCKDETAILS$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "transfer-block-details");
        private static final javax.xml.namespace.QName TRANSFERBLOCKITEMS$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "transfer-block-items");
        
        
        /**
         * Gets the "transfer-block-details" element
         */
        public uk.gov.ofwat.model2.TransferBlockDetailsDocument.TransferBlockDetails getTransferBlockDetails()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TransferBlockDetailsDocument.TransferBlockDetails target = null;
                target = (uk.gov.ofwat.model2.TransferBlockDetailsDocument.TransferBlockDetails)get_store().find_element_user(TRANSFERBLOCKDETAILS$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "transfer-block-details" element
         */
        public void setTransferBlockDetails(uk.gov.ofwat.model2.TransferBlockDetailsDocument.TransferBlockDetails transferBlockDetails)
        {
            generatedSetterHelperImpl(transferBlockDetails, TRANSFERBLOCKDETAILS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "transfer-block-details" element
         */
        public uk.gov.ofwat.model2.TransferBlockDetailsDocument.TransferBlockDetails addNewTransferBlockDetails()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TransferBlockDetailsDocument.TransferBlockDetails target = null;
                target = (uk.gov.ofwat.model2.TransferBlockDetailsDocument.TransferBlockDetails)get_store().add_element_user(TRANSFERBLOCKDETAILS$0);
                return target;
            }
        }
        
        /**
         * Gets the "transfer-block-items" element
         */
        public uk.gov.ofwat.model2.TransferBlockItemsDocument.TransferBlockItems getTransferBlockItems()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TransferBlockItemsDocument.TransferBlockItems target = null;
                target = (uk.gov.ofwat.model2.TransferBlockItemsDocument.TransferBlockItems)get_store().find_element_user(TRANSFERBLOCKITEMS$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * True if has "transfer-block-items" element
         */
        public boolean isSetTransferBlockItems()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TRANSFERBLOCKITEMS$2) != 0;
            }
        }
        
        /**
         * Sets the "transfer-block-items" element
         */
        public void setTransferBlockItems(uk.gov.ofwat.model2.TransferBlockItemsDocument.TransferBlockItems transferBlockItems)
        {
            generatedSetterHelperImpl(transferBlockItems, TRANSFERBLOCKITEMS$2, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
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
                target = (uk.gov.ofwat.model2.TransferBlockItemsDocument.TransferBlockItems)get_store().add_element_user(TRANSFERBLOCKITEMS$2);
                return target;
            }
        }
        
        /**
         * Unsets the "transfer-block-items" element
         */
        public void unsetTransferBlockItems()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TRANSFERBLOCKITEMS$2, 0);
            }
        }
    }
}
