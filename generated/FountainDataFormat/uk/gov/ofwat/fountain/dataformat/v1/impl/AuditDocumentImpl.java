/*
 * An XML document type.
 * Localname: audit
 * Namespace: http://www.ofwat.gov.uk/fountain/dataformat/v1
 * Java type: uk.gov.ofwat.fountain.dataformat.v1.AuditDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.fountain.dataformat.v1.impl;
/**
 * A document containing one audit(@http://www.ofwat.gov.uk/fountain/dataformat/v1) element.
 *
 * This is a complex type.
 */
public class AuditDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.fountain.dataformat.v1.AuditDocument
{
    private static final long serialVersionUID = 1L;
    
    public AuditDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName AUDIT$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/fountain/dataformat/v1", "audit");
    
    
    /**
     * Gets the "audit" element
     */
    public uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit getAudit()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit target = null;
            target = (uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit)get_store().find_element_user(AUDIT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "audit" element
     */
    public void setAudit(uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit audit)
    {
        generatedSetterHelperImpl(audit, AUDIT$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "audit" element
     */
    public uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit addNewAudit()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit target = null;
            target = (uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit)get_store().add_element_user(AUDIT$0);
            return target;
        }
    }
    /**
     * An XML audit(@http://www.ofwat.gov.uk/fountain/dataformat/v1).
     *
     * This is a complex type.
     */
    public static class AuditImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit
    {
        private static final long serialVersionUID = 1L;
        
        public AuditImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName COMMENT$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/fountain/dataformat/v1", "comment");
        private static final javax.xml.namespace.QName DATA$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/fountain/dataformat/v1", "data");
        private static final javax.xml.namespace.QName USER$4 = 
            new javax.xml.namespace.QName("", "user");
        private static final javax.xml.namespace.QName TIMESTAMP$6 = 
            new javax.xml.namespace.QName("", "timestamp");
        
        
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
        
        /**
         * Gets the "data" element
         */
        public uk.gov.ofwat.fountain.dataformat.v1.DataDocument.Data getData()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.fountain.dataformat.v1.DataDocument.Data target = null;
                target = (uk.gov.ofwat.fountain.dataformat.v1.DataDocument.Data)get_store().find_element_user(DATA$2, 0);
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
            generatedSetterHelperImpl(data, DATA$2, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
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
                target = (uk.gov.ofwat.fountain.dataformat.v1.DataDocument.Data)get_store().add_element_user(DATA$2);
                return target;
            }
        }
        
        /**
         * Gets the "user" attribute
         */
        public java.lang.String getUser()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(USER$4);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "user" attribute
         */
        public org.apache.xmlbeans.XmlString xgetUser()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(USER$4);
                return target;
            }
        }
        
        /**
         * Sets the "user" attribute
         */
        public void setUser(java.lang.String user)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(USER$4);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(USER$4);
                }
                target.setStringValue(user);
            }
        }
        
        /**
         * Sets (as xml) the "user" attribute
         */
        public void xsetUser(org.apache.xmlbeans.XmlString user)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(USER$4);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(USER$4);
                }
                target.set(user);
            }
        }
        
        /**
         * Gets the "timestamp" attribute
         */
        public long getTimestamp()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TIMESTAMP$6);
                if (target == null)
                {
                    return 0L;
                }
                return target.getLongValue();
            }
        }
        
        /**
         * Gets (as xml) the "timestamp" attribute
         */
        public org.apache.xmlbeans.XmlLong xgetTimestamp()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlLong target = null;
                target = (org.apache.xmlbeans.XmlLong)get_store().find_attribute_user(TIMESTAMP$6);
                return target;
            }
        }
        
        /**
         * Sets the "timestamp" attribute
         */
        public void setTimestamp(long timestamp)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TIMESTAMP$6);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(TIMESTAMP$6);
                }
                target.setLongValue(timestamp);
            }
        }
        
        /**
         * Sets (as xml) the "timestamp" attribute
         */
        public void xsetTimestamp(org.apache.xmlbeans.XmlLong timestamp)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlLong target = null;
                target = (org.apache.xmlbeans.XmlLong)get_store().find_attribute_user(TIMESTAMP$6);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlLong)get_store().add_attribute_user(TIMESTAMP$6);
                }
                target.set(timestamp);
            }
        }
    }
}
