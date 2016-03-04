/*
 * An XML document type.
 * Localname: form-cells
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.FormCellsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one form-cells(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class FormCellsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.FormCellsDocument
{
    private static final long serialVersionUID = 1L;
    
    public FormCellsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FORMCELLS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "form-cells");
    
    
    /**
     * Gets the "form-cells" element
     */
    public uk.gov.ofwat.model2.FormCellsDocument.FormCells getFormCells()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.FormCellsDocument.FormCells target = null;
            target = (uk.gov.ofwat.model2.FormCellsDocument.FormCells)get_store().find_element_user(FORMCELLS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "form-cells" element
     */
    public void setFormCells(uk.gov.ofwat.model2.FormCellsDocument.FormCells formCells)
    {
        generatedSetterHelperImpl(formCells, FORMCELLS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "form-cells" element
     */
    public uk.gov.ofwat.model2.FormCellsDocument.FormCells addNewFormCells()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.FormCellsDocument.FormCells target = null;
            target = (uk.gov.ofwat.model2.FormCellsDocument.FormCells)get_store().add_element_user(FORMCELLS$0);
            return target;
        }
    }
    /**
     * An XML form-cells(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class FormCellsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.FormCellsDocument.FormCells
    {
        private static final long serialVersionUID = 1L;
        
        public FormCellsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName FORMCELL$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "form-cell");
        
        
        /**
         * Gets array of all "form-cell" elements
         */
        public uk.gov.ofwat.model2.FormCellDocument.FormCell[] getFormCellArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(FORMCELL$0, targetList);
                uk.gov.ofwat.model2.FormCellDocument.FormCell[] result = new uk.gov.ofwat.model2.FormCellDocument.FormCell[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "form-cell" element
         */
        public uk.gov.ofwat.model2.FormCellDocument.FormCell getFormCellArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.FormCellDocument.FormCell target = null;
                target = (uk.gov.ofwat.model2.FormCellDocument.FormCell)get_store().find_element_user(FORMCELL$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "form-cell" element
         */
        public int sizeOfFormCellArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(FORMCELL$0);
            }
        }
        
        /**
         * Sets array of all "form-cell" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setFormCellArray(uk.gov.ofwat.model2.FormCellDocument.FormCell[] formCellArray)
        {
            check_orphaned();
            arraySetterHelper(formCellArray, FORMCELL$0);
        }
        
        /**
         * Sets ith "form-cell" element
         */
        public void setFormCellArray(int i, uk.gov.ofwat.model2.FormCellDocument.FormCell formCell)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.FormCellDocument.FormCell target = null;
                target = (uk.gov.ofwat.model2.FormCellDocument.FormCell)get_store().find_element_user(FORMCELL$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(formCell);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "form-cell" element
         */
        public uk.gov.ofwat.model2.FormCellDocument.FormCell insertNewFormCell(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.FormCellDocument.FormCell target = null;
                target = (uk.gov.ofwat.model2.FormCellDocument.FormCell)get_store().insert_element_user(FORMCELL$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "form-cell" element
         */
        public uk.gov.ofwat.model2.FormCellDocument.FormCell addNewFormCell()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.FormCellDocument.FormCell target = null;
                target = (uk.gov.ofwat.model2.FormCellDocument.FormCell)get_store().add_element_user(FORMCELL$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "form-cell" element
         */
        public void removeFormCell(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(FORMCELL$0, i);
            }
        }
    }
}
