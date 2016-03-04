/*
 * An XML document type.
 * Localname: documents
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.DocumentsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one documents(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class DocumentsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.DocumentsDocument
{
    private static final long serialVersionUID = 1L;
    
    public DocumentsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName DOCUMENTS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "documents");
    
    
    /**
     * Gets the "documents" element
     */
    public uk.gov.ofwat.model2.DocumentsDocument.Documents getDocuments()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.DocumentsDocument.Documents target = null;
            target = (uk.gov.ofwat.model2.DocumentsDocument.Documents)get_store().find_element_user(DOCUMENTS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "documents" element
     */
    public void setDocuments(uk.gov.ofwat.model2.DocumentsDocument.Documents documents)
    {
        generatedSetterHelperImpl(documents, DOCUMENTS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "documents" element
     */
    public uk.gov.ofwat.model2.DocumentsDocument.Documents addNewDocuments()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.DocumentsDocument.Documents target = null;
            target = (uk.gov.ofwat.model2.DocumentsDocument.Documents)get_store().add_element_user(DOCUMENTS$0);
            return target;
        }
    }
    /**
     * An XML documents(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class DocumentsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.DocumentsDocument.Documents
    {
        private static final long serialVersionUID = 1L;
        
        public DocumentsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName DOCUMENT$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "document");
        
        
        /**
         * Gets array of all "document" elements
         */
        public uk.gov.ofwat.model2.DocumentDocument.Document[] getDocumentArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(DOCUMENT$0, targetList);
                uk.gov.ofwat.model2.DocumentDocument.Document[] result = new uk.gov.ofwat.model2.DocumentDocument.Document[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "document" element
         */
        public uk.gov.ofwat.model2.DocumentDocument.Document getDocumentArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.DocumentDocument.Document target = null;
                target = (uk.gov.ofwat.model2.DocumentDocument.Document)get_store().find_element_user(DOCUMENT$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "document" element
         */
        public int sizeOfDocumentArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(DOCUMENT$0);
            }
        }
        
        /**
         * Sets array of all "document" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setDocumentArray(uk.gov.ofwat.model2.DocumentDocument.Document[] documentArray)
        {
            check_orphaned();
            arraySetterHelper(documentArray, DOCUMENT$0);
        }
        
        /**
         * Sets ith "document" element
         */
        public void setDocumentArray(int i, uk.gov.ofwat.model2.DocumentDocument.Document document)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.DocumentDocument.Document target = null;
                target = (uk.gov.ofwat.model2.DocumentDocument.Document)get_store().find_element_user(DOCUMENT$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(document);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "document" element
         */
        public uk.gov.ofwat.model2.DocumentDocument.Document insertNewDocument(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.DocumentDocument.Document target = null;
                target = (uk.gov.ofwat.model2.DocumentDocument.Document)get_store().insert_element_user(DOCUMENT$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "document" element
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
         * Removes the ith "document" element
         */
        public void removeDocument(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(DOCUMENT$0, i);
            }
        }
    }
}
