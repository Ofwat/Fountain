/*
 * An XML document type.
 * Localname: line
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.LineDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one line(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class LineDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.LineDocument
{
    private static final long serialVersionUID = 1L;
    
    public LineDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName LINE$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "line");
    
    
    /**
     * Gets the "line" element
     */
    public uk.gov.ofwat.model2.LineDocument.Line getLine()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.LineDocument.Line target = null;
            target = (uk.gov.ofwat.model2.LineDocument.Line)get_store().find_element_user(LINE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "line" element
     */
    public void setLine(uk.gov.ofwat.model2.LineDocument.Line line)
    {
        generatedSetterHelperImpl(line, LINE$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "line" element
     */
    public uk.gov.ofwat.model2.LineDocument.Line addNewLine()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.LineDocument.Line target = null;
            target = (uk.gov.ofwat.model2.LineDocument.Line)get_store().add_element_user(LINE$0);
            return target;
        }
    }
    /**
     * An XML line(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class LineImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.LineDocument.Line
    {
        private static final long serialVersionUID = 1L;
        
        public LineImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName LINEDETAILS$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "linedetails");
        private static final javax.xml.namespace.QName DOCUMENTS$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "documents");
        private static final javax.xml.namespace.QName CELLRANGE$4 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "cellrange");
        private static final javax.xml.namespace.QName CELLS$6 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "cells");
        
        
        /**
         * Gets the "linedetails" element
         */
        public uk.gov.ofwat.model2.LinedetailsDocument.Linedetails getLinedetails()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.LinedetailsDocument.Linedetails target = null;
                target = (uk.gov.ofwat.model2.LinedetailsDocument.Linedetails)get_store().find_element_user(LINEDETAILS$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "linedetails" element
         */
        public void setLinedetails(uk.gov.ofwat.model2.LinedetailsDocument.Linedetails linedetails)
        {
            generatedSetterHelperImpl(linedetails, LINEDETAILS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "linedetails" element
         */
        public uk.gov.ofwat.model2.LinedetailsDocument.Linedetails addNewLinedetails()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.LinedetailsDocument.Linedetails target = null;
                target = (uk.gov.ofwat.model2.LinedetailsDocument.Linedetails)get_store().add_element_user(LINEDETAILS$0);
                return target;
            }
        }
        
        /**
         * Gets the "documents" element
         */
        public uk.gov.ofwat.model2.DocumentsDocument.Documents getDocuments()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.DocumentsDocument.Documents target = null;
                target = (uk.gov.ofwat.model2.DocumentsDocument.Documents)get_store().find_element_user(DOCUMENTS$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * True if has "documents" element
         */
        public boolean isSetDocuments()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(DOCUMENTS$2) != 0;
            }
        }
        
        /**
         * Sets the "documents" element
         */
        public void setDocuments(uk.gov.ofwat.model2.DocumentsDocument.Documents documents)
        {
            generatedSetterHelperImpl(documents, DOCUMENTS$2, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
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
                target = (uk.gov.ofwat.model2.DocumentsDocument.Documents)get_store().add_element_user(DOCUMENTS$2);
                return target;
            }
        }
        
        /**
         * Unsets the "documents" element
         */
        public void unsetDocuments()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(DOCUMENTS$2, 0);
            }
        }
        
        /**
         * Gets the "cellrange" element
         */
        public uk.gov.ofwat.model2.CellrangeDocument.Cellrange getCellrange()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.CellrangeDocument.Cellrange target = null;
                target = (uk.gov.ofwat.model2.CellrangeDocument.Cellrange)get_store().find_element_user(CELLRANGE$4, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * True if has "cellrange" element
         */
        public boolean isSetCellrange()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(CELLRANGE$4) != 0;
            }
        }
        
        /**
         * Sets the "cellrange" element
         */
        public void setCellrange(uk.gov.ofwat.model2.CellrangeDocument.Cellrange cellrange)
        {
            generatedSetterHelperImpl(cellrange, CELLRANGE$4, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "cellrange" element
         */
        public uk.gov.ofwat.model2.CellrangeDocument.Cellrange addNewCellrange()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.CellrangeDocument.Cellrange target = null;
                target = (uk.gov.ofwat.model2.CellrangeDocument.Cellrange)get_store().add_element_user(CELLRANGE$4);
                return target;
            }
        }
        
        /**
         * Unsets the "cellrange" element
         */
        public void unsetCellrange()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(CELLRANGE$4, 0);
            }
        }
        
        /**
         * Gets the "cells" element
         */
        public uk.gov.ofwat.model2.CellsDocument.Cells getCells()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.CellsDocument.Cells target = null;
                target = (uk.gov.ofwat.model2.CellsDocument.Cells)get_store().find_element_user(CELLS$6, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * True if has "cells" element
         */
        public boolean isSetCells()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(CELLS$6) != 0;
            }
        }
        
        /**
         * Sets the "cells" element
         */
        public void setCells(uk.gov.ofwat.model2.CellsDocument.Cells cells)
        {
            generatedSetterHelperImpl(cells, CELLS$6, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
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
                target = (uk.gov.ofwat.model2.CellsDocument.Cells)get_store().add_element_user(CELLS$6);
                return target;
            }
        }
        
        /**
         * Unsets the "cells" element
         */
        public void unsetCells()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(CELLS$6, 0);
            }
        }
    }
}
