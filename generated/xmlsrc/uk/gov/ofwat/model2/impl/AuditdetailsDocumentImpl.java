/*
 * An XML document type.
 * Localname: auditdetails
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.AuditdetailsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one auditdetails(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class AuditdetailsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.AuditdetailsDocument
{
    private static final long serialVersionUID = 1L;
    
    public AuditdetailsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName AUDITDETAILS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "auditdetails");
    
    
    /**
     * Gets the "auditdetails" element
     */
    public uk.gov.ofwat.model2.AuditdetailsDocument.Auditdetails getAuditdetails()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.AuditdetailsDocument.Auditdetails target = null;
            target = (uk.gov.ofwat.model2.AuditdetailsDocument.Auditdetails)get_store().find_element_user(AUDITDETAILS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "auditdetails" element
     */
    public void setAuditdetails(uk.gov.ofwat.model2.AuditdetailsDocument.Auditdetails auditdetails)
    {
        generatedSetterHelperImpl(auditdetails, AUDITDETAILS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "auditdetails" element
     */
    public uk.gov.ofwat.model2.AuditdetailsDocument.Auditdetails addNewAuditdetails()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.AuditdetailsDocument.Auditdetails target = null;
            target = (uk.gov.ofwat.model2.AuditdetailsDocument.Auditdetails)get_store().add_element_user(AUDITDETAILS$0);
            return target;
        }
    }
    /**
     * An XML auditdetails(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class AuditdetailsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.AuditdetailsDocument.Auditdetails
    {
        private static final long serialVersionUID = 1L;
        
        public AuditdetailsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName USERNAME$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "username");
        private static final javax.xml.namespace.QName TIMESTAMP$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "timestamp");
        private static final javax.xml.namespace.QName REASON$4 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "reason");
        
        
        /**
         * Gets the "username" element
         */
        public java.lang.String getUsername()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USERNAME$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "username" element
         */
        public org.apache.xmlbeans.XmlString xgetUsername()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(USERNAME$0, 0);
                return target;
            }
        }
        
        /**
         * Sets the "username" element
         */
        public void setUsername(java.lang.String username)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USERNAME$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(USERNAME$0);
                }
                target.setStringValue(username);
            }
        }
        
        /**
         * Sets (as xml) the "username" element
         */
        public void xsetUsername(org.apache.xmlbeans.XmlString username)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(USERNAME$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(USERNAME$0);
                }
                target.set(username);
            }
        }
        
        /**
         * Gets the "timestamp" element
         */
        public java.lang.String getTimestamp()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIMESTAMP$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "timestamp" element
         */
        public org.apache.xmlbeans.XmlString xgetTimestamp()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TIMESTAMP$2, 0);
                return target;
            }
        }
        
        /**
         * Sets the "timestamp" element
         */
        public void setTimestamp(java.lang.String timestamp)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIMESTAMP$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TIMESTAMP$2);
                }
                target.setStringValue(timestamp);
            }
        }
        
        /**
         * Sets (as xml) the "timestamp" element
         */
        public void xsetTimestamp(org.apache.xmlbeans.XmlString timestamp)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TIMESTAMP$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TIMESTAMP$2);
                }
                target.set(timestamp);
            }
        }
        
        /**
         * Gets the "reason" element
         */
        public java.lang.String getReason()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(REASON$4, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "reason" element
         */
        public org.apache.xmlbeans.XmlString xgetReason()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(REASON$4, 0);
                return target;
            }
        }
        
        /**
         * Sets the "reason" element
         */
        public void setReason(java.lang.String reason)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(REASON$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(REASON$4);
                }
                target.setStringValue(reason);
            }
        }
        
        /**
         * Sets (as xml) the "reason" element
         */
        public void xsetReason(org.apache.xmlbeans.XmlString reason)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(REASON$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(REASON$4);
                }
                target.set(reason);
            }
        }
    }
}
