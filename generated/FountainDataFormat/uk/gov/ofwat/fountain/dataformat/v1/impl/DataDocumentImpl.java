/*
 * An XML document type.
 * Localname: data
 * Namespace: http://www.ofwat.gov.uk/fountain/dataformat/v1
 * Java type: uk.gov.ofwat.fountain.dataformat.v1.DataDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.fountain.dataformat.v1.impl;
/**
 * A document containing one data(@http://www.ofwat.gov.uk/fountain/dataformat/v1) element.
 *
 * This is a complex type.
 */
public class DataDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.fountain.dataformat.v1.DataDocument
{
    private static final long serialVersionUID = 1L;
    
    public DataDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName DATA$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/fountain/dataformat/v1", "data");
    
    
    /**
     * Gets the "data" element
     */
    public uk.gov.ofwat.fountain.dataformat.v1.DataDocument.Data getData()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.fountain.dataformat.v1.DataDocument.Data target = null;
            target = (uk.gov.ofwat.fountain.dataformat.v1.DataDocument.Data)get_store().find_element_user(DATA$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "data" element
     */
    public void setData(uk.gov.ofwat.fountain.dataformat.v1.DataDocument.Data data)
    {
        generatedSetterHelperImpl(data, DATA$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "data" element
     */
    public uk.gov.ofwat.fountain.dataformat.v1.DataDocument.Data addNewData()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.fountain.dataformat.v1.DataDocument.Data target = null;
            target = (uk.gov.ofwat.fountain.dataformat.v1.DataDocument.Data)get_store().add_element_user(DATA$0);
            return target;
        }
    }
    /**
     * An XML data(@http://www.ofwat.gov.uk/fountain/dataformat/v1).
     *
     * This is a complex type.
     */
    public static class DataImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.fountain.dataformat.v1.DataDocument.Data
    {
        private static final long serialVersionUID = 1L;
        
        public DataImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName VALUE$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/fountain/dataformat/v1", "value");
        
        
        /**
         * Gets array of all "value" elements
         */
        public uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value[] getValueArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(VALUE$0, targetList);
                uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value[] result = new uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "value" element
         */
        public uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value getValueArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value target = null;
                target = (uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value)get_store().find_element_user(VALUE$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "value" element
         */
        public int sizeOfValueArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(VALUE$0);
            }
        }
        
        /**
         * Sets array of all "value" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setValueArray(uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value[] valueArray)
        {
            check_orphaned();
            arraySetterHelper(valueArray, VALUE$0);
        }
        
        /**
         * Sets ith "value" element
         */
        public void setValueArray(int i, uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value value)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value target = null;
                target = (uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value)get_store().find_element_user(VALUE$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(value);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "value" element
         */
        public uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value insertNewValue(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value target = null;
                target = (uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value)get_store().insert_element_user(VALUE$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "value" element
         */
        public uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value addNewValue()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value target = null;
                target = (uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value)get_store().add_element_user(VALUE$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "value" element
         */
        public void removeValue(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(VALUE$0, i);
            }
        }
    }
}
