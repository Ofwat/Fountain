/*
 * An XML document type.
 * Localname: audit
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.AuditDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one audit(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class AuditDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.AuditDocument
{
    private static final long serialVersionUID = 1L;
    
    public AuditDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName AUDIT$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "audit");
    
    
    /**
     * Gets the "audit" element
     */
    public uk.gov.ofwat.model2.AuditDocument.Audit getAudit()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.AuditDocument.Audit target = null;
            target = (uk.gov.ofwat.model2.AuditDocument.Audit)get_store().find_element_user(AUDIT$0, 0);
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
    public void setAudit(uk.gov.ofwat.model2.AuditDocument.Audit audit)
    {
        generatedSetterHelperImpl(audit, AUDIT$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "audit" element
     */
    public uk.gov.ofwat.model2.AuditDocument.Audit addNewAudit()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.AuditDocument.Audit target = null;
            target = (uk.gov.ofwat.model2.AuditDocument.Audit)get_store().add_element_user(AUDIT$0);
            return target;
        }
    }
    /**
     * An XML audit(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class AuditImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.AuditDocument.Audit
    {
        private static final long serialVersionUID = 1L;
        
        public AuditImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName AUDITDETAILS$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "auditdetails");
        private static final javax.xml.namespace.QName CHANGES$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "changes");
        
        
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
         * Gets the "changes" element
         */
        public uk.gov.ofwat.model2.ChangesDocument.Changes getChanges()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.ChangesDocument.Changes target = null;
                target = (uk.gov.ofwat.model2.ChangesDocument.Changes)get_store().find_element_user(CHANGES$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "changes" element
         */
        public void setChanges(uk.gov.ofwat.model2.ChangesDocument.Changes changes)
        {
            generatedSetterHelperImpl(changes, CHANGES$2, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "changes" element
         */
        public uk.gov.ofwat.model2.ChangesDocument.Changes addNewChanges()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.ChangesDocument.Changes target = null;
                target = (uk.gov.ofwat.model2.ChangesDocument.Changes)get_store().add_element_user(CHANGES$2);
                return target;
            }
        }
    }
}
