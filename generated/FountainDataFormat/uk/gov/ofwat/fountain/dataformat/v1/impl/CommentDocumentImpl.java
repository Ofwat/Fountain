/*
 * An XML document type.
 * Localname: comment
 * Namespace: http://www.ofwat.gov.uk/fountain/dataformat/v1
 * Java type: uk.gov.ofwat.fountain.dataformat.v1.CommentDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.fountain.dataformat.v1.impl;
/**
 * A document containing one comment(@http://www.ofwat.gov.uk/fountain/dataformat/v1) element.
 *
 * This is a complex type.
 */
public class CommentDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.fountain.dataformat.v1.CommentDocument
{
    private static final long serialVersionUID = 1L;
    
    public CommentDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName COMMENT$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/fountain/dataformat/v1", "comment");
    
    
    /**
     * Gets the "comment" element
     */
    public java.lang.String getComment()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COMMENT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "comment" element
     */
    public org.apache.xmlbeans.XmlString xgetComment()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COMMENT$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "comment" element
     */
    public void setComment(java.lang.String comment)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COMMENT$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(COMMENT$0);
            }
            target.setStringValue(comment);
        }
    }
    
    /**
     * Sets (as xml) the "comment" element
     */
    public void xsetComment(org.apache.xmlbeans.XmlString comment)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COMMENT$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(COMMENT$0);
            }
            target.set(comment);
        }
    }
}
