/*
 * An XML document type.
 * Localname: transfer-block-item
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.TransferBlockItemDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one transfer-block-item(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class TransferBlockItemDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TransferBlockItemDocument
{
    private static final long serialVersionUID = 1L;
    
    public TransferBlockItemDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TRANSFERBLOCKITEM$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "transfer-block-item");
    
    
    /**
     * Gets the "transfer-block-item" element
     */
    public uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem getTransferBlockItem()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem target = null;
            target = (uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem)get_store().find_element_user(TRANSFERBLOCKITEM$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "transfer-block-item" element
     */
    public void setTransferBlockItem(uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem transferBlockItem)
    {
        generatedSetterHelperImpl(transferBlockItem, TRANSFERBLOCKITEM$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "transfer-block-item" element
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
     * An XML transfer-block-item(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class TransferBlockItemImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem
    {
        private static final long serialVersionUID = 1L;
        
        public TransferBlockItemImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName ITEMCODE$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "item-code");
        private static final javax.xml.namespace.QName COMPANYTYPE$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "company-type");
        private static final javax.xml.namespace.QName YEARCODE$4 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "year-code");
        
        
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
         * Gets the "company-type" element
         */
        public java.lang.String getCompanyType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COMPANYTYPE$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "company-type" element
         */
        public org.apache.xmlbeans.XmlString xgetCompanyType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COMPANYTYPE$2, 0);
                return target;
            }
        }
        
        /**
         * True if has "company-type" element
         */
        public boolean isSetCompanyType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(COMPANYTYPE$2) != 0;
            }
        }
        
        /**
         * Sets the "company-type" element
         */
        public void setCompanyType(java.lang.String companyType)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COMPANYTYPE$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(COMPANYTYPE$2);
                }
                target.setStringValue(companyType);
            }
        }
        
        /**
         * Sets (as xml) the "company-type" element
         */
        public void xsetCompanyType(org.apache.xmlbeans.XmlString companyType)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COMPANYTYPE$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(COMPANYTYPE$2);
                }
                target.set(companyType);
            }
        }
        
        /**
         * Unsets the "company-type" element
         */
        public void unsetCompanyType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(COMPANYTYPE$2, 0);
            }
        }
        
        /**
         * Gets array of all "year-code" elements
         */
        public java.lang.String[] getYearCodeArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(YEARCODE$4, targetList);
                java.lang.String[] result = new java.lang.String[targetList.size()];
                for (int i = 0, len = targetList.size() ; i < len ; i++)
                    result[i] = ((org.apache.xmlbeans.SimpleValue)targetList.get(i)).getStringValue();
                return result;
            }
        }
        
        /**
         * Gets ith "year-code" element
         */
        public java.lang.String getYearCodeArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(YEARCODE$4, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) array of all "year-code" elements
         */
        public org.apache.xmlbeans.XmlString[] xgetYearCodeArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(YEARCODE$4, targetList);
                org.apache.xmlbeans.XmlString[] result = new org.apache.xmlbeans.XmlString[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets (as xml) ith "year-code" element
         */
        public org.apache.xmlbeans.XmlString xgetYearCodeArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(YEARCODE$4, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "year-code" element
         */
        public int sizeOfYearCodeArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(YEARCODE$4);
            }
        }
        
        /**
         * Sets array of all "year-code" element
         */
        public void setYearCodeArray(java.lang.String[] yearCodeArray)
        {
            synchronized (monitor())
            {
                check_orphaned();
                arraySetterHelper(yearCodeArray, YEARCODE$4);
            }
        }
        
        /**
         * Sets ith "year-code" element
         */
        public void setYearCodeArray(int i, java.lang.String yearCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(YEARCODE$4, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.setStringValue(yearCode);
            }
        }
        
        /**
         * Sets (as xml) array of all "year-code" element
         */
        public void xsetYearCodeArray(org.apache.xmlbeans.XmlString[]yearCodeArray)
        {
            synchronized (monitor())
            {
                check_orphaned();
                arraySetterHelper(yearCodeArray, YEARCODE$4);
            }
        }
        
        /**
         * Sets (as xml) ith "year-code" element
         */
        public void xsetYearCodeArray(int i, org.apache.xmlbeans.XmlString yearCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(YEARCODE$4, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(yearCode);
            }
        }
        
        /**
         * Inserts the value as the ith "year-code" element
         */
        public void insertYearCode(int i, java.lang.String yearCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = 
                    (org.apache.xmlbeans.SimpleValue)get_store().insert_element_user(YEARCODE$4, i);
                target.setStringValue(yearCode);
            }
        }
        
        /**
         * Appends the value as the last "year-code" element
         */
        public void addYearCode(java.lang.String yearCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(YEARCODE$4);
                target.setStringValue(yearCode);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "year-code" element
         */
        public org.apache.xmlbeans.XmlString insertNewYearCode(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().insert_element_user(YEARCODE$4, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "year-code" element
         */
        public org.apache.xmlbeans.XmlString addNewYearCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(YEARCODE$4);
                return target;
            }
        }
        
        /**
         * Removes the ith "year-code" element
         */
        public void removeYearCode(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(YEARCODE$4, i);
            }
        }
    }
}
