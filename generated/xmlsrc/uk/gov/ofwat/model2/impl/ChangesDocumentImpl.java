/*
 * An XML document type.
 * Localname: changes
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.ChangesDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one changes(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class ChangesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.ChangesDocument
{
    private static final long serialVersionUID = 1L;
    
    public ChangesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CHANGES$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "changes");
    
    
    /**
     * Gets the "changes" element
     */
    public uk.gov.ofwat.model2.ChangesDocument.Changes getChanges()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.ChangesDocument.Changes target = null;
            target = (uk.gov.ofwat.model2.ChangesDocument.Changes)get_store().find_element_user(CHANGES$0, 0);
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
        generatedSetterHelperImpl(changes, CHANGES$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
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
            target = (uk.gov.ofwat.model2.ChangesDocument.Changes)get_store().add_element_user(CHANGES$0);
            return target;
        }
    }
    /**
     * An XML changes(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class ChangesImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.ChangesDocument.Changes
    {
        private static final long serialVersionUID = 1L;
        
        public ChangesImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName CHANGE$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "change");
        
        
        /**
         * Gets array of all "change" elements
         */
        public java.lang.String[] getChangeArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(CHANGE$0, targetList);
                java.lang.String[] result = new java.lang.String[targetList.size()];
                for (int i = 0, len = targetList.size() ; i < len ; i++)
                    result[i] = ((org.apache.xmlbeans.SimpleValue)targetList.get(i)).getStringValue();
                return result;
            }
        }
        
        /**
         * Gets ith "change" element
         */
        public java.lang.String getChangeArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CHANGE$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) array of all "change" elements
         */
        public org.apache.xmlbeans.XmlString[] xgetChangeArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(CHANGE$0, targetList);
                org.apache.xmlbeans.XmlString[] result = new org.apache.xmlbeans.XmlString[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets (as xml) ith "change" element
         */
        public org.apache.xmlbeans.XmlString xgetChangeArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CHANGE$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "change" element
         */
        public int sizeOfChangeArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(CHANGE$0);
            }
        }
        
        /**
         * Sets array of all "change" element
         */
        public void setChangeArray(java.lang.String[] changeArray)
        {
            synchronized (monitor())
            {
                check_orphaned();
                arraySetterHelper(changeArray, CHANGE$0);
            }
        }
        
        /**
         * Sets ith "change" element
         */
        public void setChangeArray(int i, java.lang.String change)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CHANGE$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.setStringValue(change);
            }
        }
        
        /**
         * Sets (as xml) array of all "change" element
         */
        public void xsetChangeArray(org.apache.xmlbeans.XmlString[]changeArray)
        {
            synchronized (monitor())
            {
                check_orphaned();
                arraySetterHelper(changeArray, CHANGE$0);
            }
        }
        
        /**
         * Sets (as xml) ith "change" element
         */
        public void xsetChangeArray(int i, org.apache.xmlbeans.XmlString change)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CHANGE$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(change);
            }
        }
        
        /**
         * Inserts the value as the ith "change" element
         */
        public void insertChange(int i, java.lang.String change)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = 
                    (org.apache.xmlbeans.SimpleValue)get_store().insert_element_user(CHANGE$0, i);
                target.setStringValue(change);
            }
        }
        
        /**
         * Appends the value as the last "change" element
         */
        public void addChange(java.lang.String change)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CHANGE$0);
                target.setStringValue(change);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "change" element
         */
        public org.apache.xmlbeans.XmlString insertNewChange(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().insert_element_user(CHANGE$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "change" element
         */
        public org.apache.xmlbeans.XmlString addNewChange()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(CHANGE$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "change" element
         */
        public void removeChange(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(CHANGE$0, i);
            }
        }
    }
}
