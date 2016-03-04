/*
 * An XML document type.
 * Localname: items
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.ItemsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one items(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class ItemsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.ItemsDocument
{
    private static final long serialVersionUID = 1L;
    
    public ItemsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ITEMS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "items");
    
    
    /**
     * Gets the "items" element
     */
    public uk.gov.ofwat.model2.ItemsDocument.Items getItems()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.ItemsDocument.Items target = null;
            target = (uk.gov.ofwat.model2.ItemsDocument.Items)get_store().find_element_user(ITEMS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "items" element
     */
    public void setItems(uk.gov.ofwat.model2.ItemsDocument.Items items)
    {
        generatedSetterHelperImpl(items, ITEMS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "items" element
     */
    public uk.gov.ofwat.model2.ItemsDocument.Items addNewItems()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.ItemsDocument.Items target = null;
            target = (uk.gov.ofwat.model2.ItemsDocument.Items)get_store().add_element_user(ITEMS$0);
            return target;
        }
    }
    /**
     * An XML items(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class ItemsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.ItemsDocument.Items
    {
        private static final long serialVersionUID = 1L;
        
        public ItemsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName ITEM$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "item");
        
        
        /**
         * Gets array of all "item" elements
         */
        public uk.gov.ofwat.model2.ItemDocument.Item[] getItemArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(ITEM$0, targetList);
                uk.gov.ofwat.model2.ItemDocument.Item[] result = new uk.gov.ofwat.model2.ItemDocument.Item[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "item" element
         */
        public uk.gov.ofwat.model2.ItemDocument.Item getItemArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.ItemDocument.Item target = null;
                target = (uk.gov.ofwat.model2.ItemDocument.Item)get_store().find_element_user(ITEM$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "item" element
         */
        public int sizeOfItemArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(ITEM$0);
            }
        }
        
        /**
         * Sets array of all "item" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setItemArray(uk.gov.ofwat.model2.ItemDocument.Item[] itemArray)
        {
            check_orphaned();
            arraySetterHelper(itemArray, ITEM$0);
        }
        
        /**
         * Sets ith "item" element
         */
        public void setItemArray(int i, uk.gov.ofwat.model2.ItemDocument.Item item)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.ItemDocument.Item target = null;
                target = (uk.gov.ofwat.model2.ItemDocument.Item)get_store().find_element_user(ITEM$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(item);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "item" element
         */
        public uk.gov.ofwat.model2.ItemDocument.Item insertNewItem(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.ItemDocument.Item target = null;
                target = (uk.gov.ofwat.model2.ItemDocument.Item)get_store().insert_element_user(ITEM$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "item" element
         */
        public uk.gov.ofwat.model2.ItemDocument.Item addNewItem()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.ItemDocument.Item target = null;
                target = (uk.gov.ofwat.model2.ItemDocument.Item)get_store().add_element_user(ITEM$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "item" element
         */
        public void removeItem(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(ITEM$0, i);
            }
        }
    }
}
