/*
 * An XML document type.
 * Localname: cells
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.CellsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one cells(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class CellsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.CellsDocument
{
    private static final long serialVersionUID = 1L;
    
    public CellsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CELLS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "cells");
    
    
    /**
     * Gets the "cells" element
     */
    public uk.gov.ofwat.model2.CellsDocument.Cells getCells()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.CellsDocument.Cells target = null;
            target = (uk.gov.ofwat.model2.CellsDocument.Cells)get_store().find_element_user(CELLS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "cells" element
     */
    public void setCells(uk.gov.ofwat.model2.CellsDocument.Cells cells)
    {
        generatedSetterHelperImpl(cells, CELLS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "cells" element
     */
    public uk.gov.ofwat.model2.CellsDocument.Cells addNewCells()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.CellsDocument.Cells target = null;
            target = (uk.gov.ofwat.model2.CellsDocument.Cells)get_store().add_element_user(CELLS$0);
            return target;
        }
    }
    /**
     * An XML cells(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class CellsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.CellsDocument.Cells
    {
        private static final long serialVersionUID = 1L;
        
        public CellsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName CELL$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "cell");
        
        
        /**
         * Gets array of all "cell" elements
         */
        public uk.gov.ofwat.model2.CellDocument.Cell[] getCellArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(CELL$0, targetList);
                uk.gov.ofwat.model2.CellDocument.Cell[] result = new uk.gov.ofwat.model2.CellDocument.Cell[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "cell" element
         */
        public uk.gov.ofwat.model2.CellDocument.Cell getCellArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.CellDocument.Cell target = null;
                target = (uk.gov.ofwat.model2.CellDocument.Cell)get_store().find_element_user(CELL$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "cell" element
         */
        public int sizeOfCellArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(CELL$0);
            }
        }
        
        /**
         * Sets array of all "cell" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setCellArray(uk.gov.ofwat.model2.CellDocument.Cell[] cellArray)
        {
            check_orphaned();
            arraySetterHelper(cellArray, CELL$0);
        }
        
        /**
         * Sets ith "cell" element
         */
        public void setCellArray(int i, uk.gov.ofwat.model2.CellDocument.Cell cell)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.CellDocument.Cell target = null;
                target = (uk.gov.ofwat.model2.CellDocument.Cell)get_store().find_element_user(CELL$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(cell);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "cell" element
         */
        public uk.gov.ofwat.model2.CellDocument.Cell insertNewCell(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.CellDocument.Cell target = null;
                target = (uk.gov.ofwat.model2.CellDocument.Cell)get_store().insert_element_user(CELL$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "cell" element
         */
        public uk.gov.ofwat.model2.CellDocument.Cell addNewCell()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.CellDocument.Cell target = null;
                target = (uk.gov.ofwat.model2.CellDocument.Cell)get_store().add_element_user(CELL$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "cell" element
         */
        public void removeCell(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(CELL$0, i);
            }
        }
    }
}
