/*
 * An XML document type.
 * Localname: inputs
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.InputsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one inputs(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class InputsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.InputsDocument
{
    private static final long serialVersionUID = 1L;
    
    public InputsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName INPUTS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "inputs");
    
    
    /**
     * Gets the "inputs" element
     */
    public uk.gov.ofwat.model2.InputsDocument.Inputs getInputs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.InputsDocument.Inputs target = null;
            target = (uk.gov.ofwat.model2.InputsDocument.Inputs)get_store().find_element_user(INPUTS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "inputs" element
     */
    public void setInputs(uk.gov.ofwat.model2.InputsDocument.Inputs inputs)
    {
        generatedSetterHelperImpl(inputs, INPUTS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "inputs" element
     */
    public uk.gov.ofwat.model2.InputsDocument.Inputs addNewInputs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.InputsDocument.Inputs target = null;
            target = (uk.gov.ofwat.model2.InputsDocument.Inputs)get_store().add_element_user(INPUTS$0);
            return target;
        }
    }
    /**
     * An XML inputs(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class InputsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.InputsDocument.Inputs
    {
        private static final long serialVersionUID = 1L;
        
        public InputsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName INPUT$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "input");
        
        
        /**
         * Gets array of all "input" elements
         */
        public uk.gov.ofwat.model2.InputDocument.Input[] getInputArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(INPUT$0, targetList);
                uk.gov.ofwat.model2.InputDocument.Input[] result = new uk.gov.ofwat.model2.InputDocument.Input[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "input" element
         */
        public uk.gov.ofwat.model2.InputDocument.Input getInputArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.InputDocument.Input target = null;
                target = (uk.gov.ofwat.model2.InputDocument.Input)get_store().find_element_user(INPUT$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "input" element
         */
        public int sizeOfInputArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(INPUT$0);
            }
        }
        
        /**
         * Sets array of all "input" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setInputArray(uk.gov.ofwat.model2.InputDocument.Input[] inputArray)
        {
            check_orphaned();
            arraySetterHelper(inputArray, INPUT$0);
        }
        
        /**
         * Sets ith "input" element
         */
        public void setInputArray(int i, uk.gov.ofwat.model2.InputDocument.Input input)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.InputDocument.Input target = null;
                target = (uk.gov.ofwat.model2.InputDocument.Input)get_store().find_element_user(INPUT$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(input);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "input" element
         */
        public uk.gov.ofwat.model2.InputDocument.Input insertNewInput(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.InputDocument.Input target = null;
                target = (uk.gov.ofwat.model2.InputDocument.Input)get_store().insert_element_user(INPUT$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "input" element
         */
        public uk.gov.ofwat.model2.InputDocument.Input addNewInput()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.InputDocument.Input target = null;
                target = (uk.gov.ofwat.model2.InputDocument.Input)get_store().add_element_user(INPUT$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "input" element
         */
        public void removeInput(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(INPUT$0, i);
            }
        }
    }
}
