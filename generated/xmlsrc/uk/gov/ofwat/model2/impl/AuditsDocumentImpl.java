/*
 * An XML document type.
 * Localname: audits
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.AuditsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one audits(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class AuditsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.AuditsDocument
{
    private static final long serialVersionUID = 1L;
    
    public AuditsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName AUDITS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "audits");
    
    
    /**
     * Gets the "audits" element
     */
    public uk.gov.ofwat.model2.AuditsDocument.Audits getAudits()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.AuditsDocument.Audits target = null;
            target = (uk.gov.ofwat.model2.AuditsDocument.Audits)get_store().find_element_user(AUDITS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "audits" element
     */
    public void setAudits(uk.gov.ofwat.model2.AuditsDocument.Audits audits)
    {
        generatedSetterHelperImpl(audits, AUDITS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "audits" element
     */
    public uk.gov.ofwat.model2.AuditsDocument.Audits addNewAudits()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.AuditsDocument.Audits target = null;
            target = (uk.gov.ofwat.model2.AuditsDocument.Audits)get_store().add_element_user(AUDITS$0);
            return target;
        }
    }
    /**
     * An XML audits(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class AuditsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.AuditsDocument.Audits
    {
        private static final long serialVersionUID = 1L;
        
        public AuditsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName AUDIT$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "audit");
        
        
        /**
         * Gets array of all "audit" elements
         */
        public uk.gov.ofwat.model2.AuditDocument.Audit[] getAuditArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(AUDIT$0, targetList);
                uk.gov.ofwat.model2.AuditDocument.Audit[] result = new uk.gov.ofwat.model2.AuditDocument.Audit[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "audit" element
         */
        public uk.gov.ofwat.model2.AuditDocument.Audit getAuditArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.AuditDocument.Audit target = null;
                target = (uk.gov.ofwat.model2.AuditDocument.Audit)get_store().find_element_user(AUDIT$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "audit" element
         */
        public int sizeOfAuditArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(AUDIT$0);
            }
        }
        
        /**
         * Sets array of all "audit" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setAuditArray(uk.gov.ofwat.model2.AuditDocument.Audit[] auditArray)
        {
            check_orphaned();
            arraySetterHelper(auditArray, AUDIT$0);
        }
        
        /**
         * Sets ith "audit" element
         */
        public void setAuditArray(int i, uk.gov.ofwat.model2.AuditDocument.Audit audit)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.AuditDocument.Audit target = null;
                target = (uk.gov.ofwat.model2.AuditDocument.Audit)get_store().find_element_user(AUDIT$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(audit);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "audit" element
         */
        public uk.gov.ofwat.model2.AuditDocument.Audit insertNewAudit(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.AuditDocument.Audit target = null;
                target = (uk.gov.ofwat.model2.AuditDocument.Audit)get_store().insert_element_user(AUDIT$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "audit" element
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
         * Removes the ith "audit" element
         */
        public void removeAudit(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(AUDIT$0, i);
            }
        }
    }
}
