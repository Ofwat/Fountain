/*
 * An XML document type.
 * Localname: texts
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.TextsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one texts(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class TextsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TextsDocument
{
    private static final long serialVersionUID = 1L;
    
    public TextsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TEXTS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "texts");
    
    
    /**
     * Gets the "texts" element
     */
    public uk.gov.ofwat.model2.TextsDocument.Texts getTexts()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.TextsDocument.Texts target = null;
            target = (uk.gov.ofwat.model2.TextsDocument.Texts)get_store().find_element_user(TEXTS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "texts" element
     */
    public void setTexts(uk.gov.ofwat.model2.TextsDocument.Texts texts)
    {
        generatedSetterHelperImpl(texts, TEXTS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "texts" element
     */
    public uk.gov.ofwat.model2.TextsDocument.Texts addNewTexts()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.TextsDocument.Texts target = null;
            target = (uk.gov.ofwat.model2.TextsDocument.Texts)get_store().add_element_user(TEXTS$0);
            return target;
        }
    }
    /**
     * An XML texts(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class TextsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TextsDocument.Texts
    {
        private static final long serialVersionUID = 1L;
        
        public TextsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName TEXT$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "text");
        
        
        /**
         * Gets array of all "text" elements
         */
        public uk.gov.ofwat.model2.TextDocument.Text[] getTextArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(TEXT$0, targetList);
                uk.gov.ofwat.model2.TextDocument.Text[] result = new uk.gov.ofwat.model2.TextDocument.Text[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "text" element
         */
        public uk.gov.ofwat.model2.TextDocument.Text getTextArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TextDocument.Text target = null;
                target = (uk.gov.ofwat.model2.TextDocument.Text)get_store().find_element_user(TEXT$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "text" element
         */
        public int sizeOfTextArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TEXT$0);
            }
        }
        
        /**
         * Sets array of all "text" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setTextArray(uk.gov.ofwat.model2.TextDocument.Text[] textArray)
        {
            check_orphaned();
            arraySetterHelper(textArray, TEXT$0);
        }
        
        /**
         * Sets ith "text" element
         */
        public void setTextArray(int i, uk.gov.ofwat.model2.TextDocument.Text text)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TextDocument.Text target = null;
                target = (uk.gov.ofwat.model2.TextDocument.Text)get_store().find_element_user(TEXT$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(text);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "text" element
         */
        public uk.gov.ofwat.model2.TextDocument.Text insertNewText(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TextDocument.Text target = null;
                target = (uk.gov.ofwat.model2.TextDocument.Text)get_store().insert_element_user(TEXT$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "text" element
         */
        public uk.gov.ofwat.model2.TextDocument.Text addNewText()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TextDocument.Text target = null;
                target = (uk.gov.ofwat.model2.TextDocument.Text)get_store().add_element_user(TEXT$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "text" element
         */
        public void removeText(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TEXT$0, i);
            }
        }
    }
}
