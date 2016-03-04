/*
 * An XML document type.
 * Localname: form-headings-top
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.FormHeadingsTopDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one form-headings-top(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class FormHeadingsTopDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.FormHeadingsTopDocument
{
    private static final long serialVersionUID = 1L;
    
    public FormHeadingsTopDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FORMHEADINGSTOP$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "form-headings-top");
    
    
    /**
     * Gets the "form-headings-top" element
     */
    public uk.gov.ofwat.model2.FormHeadingsTopDocument.FormHeadingsTop getFormHeadingsTop()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.FormHeadingsTopDocument.FormHeadingsTop target = null;
            target = (uk.gov.ofwat.model2.FormHeadingsTopDocument.FormHeadingsTop)get_store().find_element_user(FORMHEADINGSTOP$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "form-headings-top" element
     */
    public void setFormHeadingsTop(uk.gov.ofwat.model2.FormHeadingsTopDocument.FormHeadingsTop formHeadingsTop)
    {
        generatedSetterHelperImpl(formHeadingsTop, FORMHEADINGSTOP$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "form-headings-top" element
     */
    public uk.gov.ofwat.model2.FormHeadingsTopDocument.FormHeadingsTop addNewFormHeadingsTop()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.FormHeadingsTopDocument.FormHeadingsTop target = null;
            target = (uk.gov.ofwat.model2.FormHeadingsTopDocument.FormHeadingsTop)get_store().add_element_user(FORMHEADINGSTOP$0);
            return target;
        }
    }
    /**
     * An XML form-headings-top(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class FormHeadingsTopImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.FormHeadingsTopDocument.FormHeadingsTop
    {
        private static final long serialVersionUID = 1L;
        
        public FormHeadingsTopImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName FORMHEADINGCELL$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "form-heading-cell");
        
        
        /**
         * Gets array of all "form-heading-cell" elements
         */
        public uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell[] getFormHeadingCellArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(FORMHEADINGCELL$0, targetList);
                uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell[] result = new uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "form-heading-cell" element
         */
        public uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell getFormHeadingCellArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell target = null;
                target = (uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell)get_store().find_element_user(FORMHEADINGCELL$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "form-heading-cell" element
         */
        public int sizeOfFormHeadingCellArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(FORMHEADINGCELL$0);
            }
        }
        
        /**
         * Sets array of all "form-heading-cell" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setFormHeadingCellArray(uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell[] formHeadingCellArray)
        {
            check_orphaned();
            arraySetterHelper(formHeadingCellArray, FORMHEADINGCELL$0);
        }
        
        /**
         * Sets ith "form-heading-cell" element
         */
        public void setFormHeadingCellArray(int i, uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell formHeadingCell)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell target = null;
                target = (uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell)get_store().find_element_user(FORMHEADINGCELL$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(formHeadingCell);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "form-heading-cell" element
         */
        public uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell insertNewFormHeadingCell(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell target = null;
                target = (uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell)get_store().insert_element_user(FORMHEADINGCELL$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "form-heading-cell" element
         */
        public uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell addNewFormHeadingCell()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell target = null;
                target = (uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell)get_store().add_element_user(FORMHEADINGCELL$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "form-heading-cell" element
         */
        public void removeFormHeadingCell(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(FORMHEADINGCELL$0, i);
            }
        }
    }
}
