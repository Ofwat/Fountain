/*
 * An XML document type.
 * Localname: document
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.DocumentDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one document(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class DocumentDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.DocumentDocument
{
    private static final long serialVersionUID = 1L;
    
    public DocumentDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName DOCUMENT$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "document");
    
    
    /**
     * Gets the "document" element
     */
    public uk.gov.ofwat.model2.DocumentDocument.Document getDocument()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.DocumentDocument.Document target = null;
            target = (uk.gov.ofwat.model2.DocumentDocument.Document)get_store().find_element_user(DOCUMENT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "document" element
     */
    public void setDocument(uk.gov.ofwat.model2.DocumentDocument.Document document)
    {
        generatedSetterHelperImpl(document, DOCUMENT$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "document" element
     */
    public uk.gov.ofwat.model2.DocumentDocument.Document addNewDocument()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.DocumentDocument.Document target = null;
            target = (uk.gov.ofwat.model2.DocumentDocument.Document)get_store().add_element_user(DOCUMENT$0);
            return target;
        }
    }
    /**
     * An XML document(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class DocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.DocumentDocument.Document
    {
        private static final long serialVersionUID = 1L;
        
        public DocumentImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName REPORTER$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "reporter");
        private static final javax.xml.namespace.QName AUDITOR$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "auditor");
        
        
        /**
         * Gets the "reporter" element
         */
        public java.lang.String getReporter()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(REPORTER$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "reporter" element
         */
        public org.apache.xmlbeans.XmlString xgetReporter()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(REPORTER$0, 0);
                return target;
            }
        }
        
        /**
         * Sets the "reporter" element
         */
        public void setReporter(java.lang.String reporter)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(REPORTER$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(REPORTER$0);
                }
                target.setStringValue(reporter);
            }
        }
        
        /**
         * Sets (as xml) the "reporter" element
         */
        public void xsetReporter(org.apache.xmlbeans.XmlString reporter)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(REPORTER$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(REPORTER$0);
                }
                target.set(reporter);
            }
        }
        
        /**
         * Gets the "auditor" element
         */
        public java.lang.String getAuditor()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(AUDITOR$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "auditor" element
         */
        public org.apache.xmlbeans.XmlString xgetAuditor()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(AUDITOR$2, 0);
                return target;
            }
        }
        
        /**
         * Sets the "auditor" element
         */
        public void setAuditor(java.lang.String auditor)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(AUDITOR$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(AUDITOR$2);
                }
                target.setStringValue(auditor);
            }
        }
        
        /**
         * Sets (as xml) the "auditor" element
         */
        public void xsetAuditor(org.apache.xmlbeans.XmlString auditor)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(AUDITOR$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(AUDITOR$2);
                }
                target.set(auditor);
            }
        }
    }
}
