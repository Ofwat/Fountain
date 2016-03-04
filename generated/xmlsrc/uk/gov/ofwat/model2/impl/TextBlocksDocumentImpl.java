/*
 * An XML document type.
 * Localname: text-blocks
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.TextBlocksDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one text-blocks(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class TextBlocksDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TextBlocksDocument
{
    private static final long serialVersionUID = 1L;
    
    public TextBlocksDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TEXTBLOCKS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "text-blocks");
    
    
    /**
     * Gets the "text-blocks" element
     */
    public uk.gov.ofwat.model2.TextBlocksDocument.TextBlocks getTextBlocks()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.TextBlocksDocument.TextBlocks target = null;
            target = (uk.gov.ofwat.model2.TextBlocksDocument.TextBlocks)get_store().find_element_user(TEXTBLOCKS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "text-blocks" element
     */
    public void setTextBlocks(uk.gov.ofwat.model2.TextBlocksDocument.TextBlocks textBlocks)
    {
        generatedSetterHelperImpl(textBlocks, TEXTBLOCKS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "text-blocks" element
     */
    public uk.gov.ofwat.model2.TextBlocksDocument.TextBlocks addNewTextBlocks()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.TextBlocksDocument.TextBlocks target = null;
            target = (uk.gov.ofwat.model2.TextBlocksDocument.TextBlocks)get_store().add_element_user(TEXTBLOCKS$0);
            return target;
        }
    }
    /**
     * An XML text-blocks(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class TextBlocksImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TextBlocksDocument.TextBlocks
    {
        private static final long serialVersionUID = 1L;
        
        public TextBlocksImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName TEXTBLOCK$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "text-block");
        
        
        /**
         * Gets array of all "text-block" elements
         */
        public uk.gov.ofwat.model2.TextBlockDocument.TextBlock[] getTextBlockArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(TEXTBLOCK$0, targetList);
                uk.gov.ofwat.model2.TextBlockDocument.TextBlock[] result = new uk.gov.ofwat.model2.TextBlockDocument.TextBlock[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "text-block" element
         */
        public uk.gov.ofwat.model2.TextBlockDocument.TextBlock getTextBlockArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TextBlockDocument.TextBlock target = null;
                target = (uk.gov.ofwat.model2.TextBlockDocument.TextBlock)get_store().find_element_user(TEXTBLOCK$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "text-block" element
         */
        public int sizeOfTextBlockArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TEXTBLOCK$0);
            }
        }
        
        /**
         * Sets array of all "text-block" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setTextBlockArray(uk.gov.ofwat.model2.TextBlockDocument.TextBlock[] textBlockArray)
        {
            check_orphaned();
            arraySetterHelper(textBlockArray, TEXTBLOCK$0);
        }
        
        /**
         * Sets ith "text-block" element
         */
        public void setTextBlockArray(int i, uk.gov.ofwat.model2.TextBlockDocument.TextBlock textBlock)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TextBlockDocument.TextBlock target = null;
                target = (uk.gov.ofwat.model2.TextBlockDocument.TextBlock)get_store().find_element_user(TEXTBLOCK$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(textBlock);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "text-block" element
         */
        public uk.gov.ofwat.model2.TextBlockDocument.TextBlock insertNewTextBlock(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TextBlockDocument.TextBlock target = null;
                target = (uk.gov.ofwat.model2.TextBlockDocument.TextBlock)get_store().insert_element_user(TEXTBLOCK$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "text-block" element
         */
        public uk.gov.ofwat.model2.TextBlockDocument.TextBlock addNewTextBlock()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TextBlockDocument.TextBlock target = null;
                target = (uk.gov.ofwat.model2.TextBlockDocument.TextBlock)get_store().add_element_user(TEXTBLOCK$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "text-block" element
         */
        public void removeTextBlock(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TEXTBLOCK$0, i);
            }
        }
    }
}
