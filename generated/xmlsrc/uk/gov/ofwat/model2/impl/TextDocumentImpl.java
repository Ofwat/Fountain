/*
 * An XML document type.
 * Localname: text
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.TextDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one text(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class TextDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TextDocument
{
    private static final long serialVersionUID = 1L;
    
    public TextDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TEXT$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "text");
    
    
    /**
     * Gets the "text" element
     */
    public uk.gov.ofwat.model2.TextDocument.Text getText()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.TextDocument.Text target = null;
            target = (uk.gov.ofwat.model2.TextDocument.Text)get_store().find_element_user(TEXT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "text" element
     */
    public void setText(uk.gov.ofwat.model2.TextDocument.Text text)
    {
        generatedSetterHelperImpl(text, TEXT$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "text" element
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
     * An XML text(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class TextImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TextDocument.Text
    {
        private static final long serialVersionUID = 1L;
        
        public TextImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName CODE$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "code");
        private static final javax.xml.namespace.QName TEXTBLOCKS$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "text-blocks");
        
        
        /**
         * Gets the "code" element
         */
        public java.lang.String getCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODE$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "code" element
         */
        public org.apache.xmlbeans.XmlString xgetCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CODE$0, 0);
                return target;
            }
        }
        
        /**
         * Sets the "code" element
         */
        public void setCode(java.lang.String code)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODE$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CODE$0);
                }
                target.setStringValue(code);
            }
        }
        
        /**
         * Sets (as xml) the "code" element
         */
        public void xsetCode(org.apache.xmlbeans.XmlString code)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CODE$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(CODE$0);
                }
                target.set(code);
            }
        }
        
        /**
         * Gets the "text-blocks" element
         */
        public uk.gov.ofwat.model2.TextBlocksDocument.TextBlocks getTextBlocks()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.TextBlocksDocument.TextBlocks target = null;
                target = (uk.gov.ofwat.model2.TextBlocksDocument.TextBlocks)get_store().find_element_user(TEXTBLOCKS$2, 0);
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
            generatedSetterHelperImpl(textBlocks, TEXTBLOCKS$2, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
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
                target = (uk.gov.ofwat.model2.TextBlocksDocument.TextBlocks)get_store().add_element_user(TEXTBLOCKS$2);
                return target;
            }
        }
    }
}
