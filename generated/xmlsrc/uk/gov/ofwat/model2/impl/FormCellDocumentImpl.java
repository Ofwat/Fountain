/*
 * An XML document type.
 * Localname: form-cell
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.FormCellDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one form-cell(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class FormCellDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.FormCellDocument
{
    private static final long serialVersionUID = 1L;
    
    public FormCellDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FORMCELL$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "form-cell");
    
    
    /**
     * Gets the "form-cell" element
     */
    public uk.gov.ofwat.model2.FormCellDocument.FormCell getFormCell()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.FormCellDocument.FormCell target = null;
            target = (uk.gov.ofwat.model2.FormCellDocument.FormCell)get_store().find_element_user(FORMCELL$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "form-cell" element
     */
    public void setFormCell(uk.gov.ofwat.model2.FormCellDocument.FormCell formCell)
    {
        generatedSetterHelperImpl(formCell, FORMCELL$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "form-cell" element
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
     * An XML form-cell(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class FormCellImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.FormCellDocument.FormCell
    {
        private static final long serialVersionUID = 1L;
        
        public FormCellImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName CELLCODE$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "cell-code");
        private static final javax.xml.namespace.QName USECONFIDENCEGRADE$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "use-confidence-grade");
        private static final javax.xml.namespace.QName INPUTCONFIDENCEGRADE$4 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "input-confidence-grade");
        private static final javax.xml.namespace.QName CONFIDENCEGRADEINPUTCODE$6 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "confidence-grade-input-code");
        private static final javax.xml.namespace.QName ROWHEADINGSOURCE$8 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "row-heading-source");
        private static final javax.xml.namespace.QName COLUMNHEADINGSOURCE$10 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "column-heading-source");
        private static final javax.xml.namespace.QName GROUPDESCRIPTIONCODE$12 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "group-description-code");
        private static final javax.xml.namespace.QName ROW$14 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "row");
        private static final javax.xml.namespace.QName COLUMN$16 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "column");
        private static final javax.xml.namespace.QName ROWSPAN$18 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "row-span");
        private static final javax.xml.namespace.QName COLUMNSPAN$20 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "column-span");
        private static final javax.xml.namespace.QName WIDTH$22 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "width");
        
        
        /**
         * Gets the "cell-code" element
         */
        public java.lang.String getCellCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CELLCODE$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "cell-code" element
         */
        public org.apache.xmlbeans.XmlString xgetCellCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CELLCODE$0, 0);
                return target;
            }
        }
        
        /**
         * Sets the "cell-code" element
         */
        public void setCellCode(java.lang.String cellCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CELLCODE$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CELLCODE$0);
                }
                target.setStringValue(cellCode);
            }
        }
        
        /**
         * Sets (as xml) the "cell-code" element
         */
        public void xsetCellCode(org.apache.xmlbeans.XmlString cellCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CELLCODE$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(CELLCODE$0);
                }
                target.set(cellCode);
            }
        }
        
        /**
         * Gets the "use-confidence-grade" element
         */
        public boolean getUseConfidenceGrade()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USECONFIDENCEGRADE$2, 0);
                if (target == null)
                {
                    return false;
                }
                return target.getBooleanValue();
            }
        }
        
        /**
         * Gets (as xml) the "use-confidence-grade" element
         */
        public org.apache.xmlbeans.XmlBoolean xgetUseConfidenceGrade()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USECONFIDENCEGRADE$2, 0);
                return target;
            }
        }
        
        /**
         * True if has "use-confidence-grade" element
         */
        public boolean isSetUseConfidenceGrade()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(USECONFIDENCEGRADE$2) != 0;
            }
        }
        
        /**
         * Sets the "use-confidence-grade" element
         */
        public void setUseConfidenceGrade(boolean useConfidenceGrade)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USECONFIDENCEGRADE$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(USECONFIDENCEGRADE$2);
                }
                target.setBooleanValue(useConfidenceGrade);
            }
        }
        
        /**
         * Sets (as xml) the "use-confidence-grade" element
         */
        public void xsetUseConfidenceGrade(org.apache.xmlbeans.XmlBoolean useConfidenceGrade)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USECONFIDENCEGRADE$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(USECONFIDENCEGRADE$2);
                }
                target.set(useConfidenceGrade);
            }
        }
        
        /**
         * Unsets the "use-confidence-grade" element
         */
        public void unsetUseConfidenceGrade()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(USECONFIDENCEGRADE$2, 0);
            }
        }
        
        /**
         * Gets the "input-confidence-grade" element
         */
        public boolean getInputConfidenceGrade()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(INPUTCONFIDENCEGRADE$4, 0);
                if (target == null)
                {
                    return false;
                }
                return target.getBooleanValue();
            }
        }
        
        /**
         * Gets (as xml) the "input-confidence-grade" element
         */
        public org.apache.xmlbeans.XmlBoolean xgetInputConfidenceGrade()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(INPUTCONFIDENCEGRADE$4, 0);
                return target;
            }
        }
        
        /**
         * True if has "input-confidence-grade" element
         */
        public boolean isSetInputConfidenceGrade()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(INPUTCONFIDENCEGRADE$4) != 0;
            }
        }
        
        /**
         * Sets the "input-confidence-grade" element
         */
        public void setInputConfidenceGrade(boolean inputConfidenceGrade)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(INPUTCONFIDENCEGRADE$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(INPUTCONFIDENCEGRADE$4);
                }
                target.setBooleanValue(inputConfidenceGrade);
            }
        }
        
        /**
         * Sets (as xml) the "input-confidence-grade" element
         */
        public void xsetInputConfidenceGrade(org.apache.xmlbeans.XmlBoolean inputConfidenceGrade)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(INPUTCONFIDENCEGRADE$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(INPUTCONFIDENCEGRADE$4);
                }
                target.set(inputConfidenceGrade);
            }
        }
        
        /**
         * Unsets the "input-confidence-grade" element
         */
        public void unsetInputConfidenceGrade()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(INPUTCONFIDENCEGRADE$4, 0);
            }
        }
        
        /**
         * Gets the "confidence-grade-input-code" element
         */
        public java.lang.String getConfidenceGradeInputCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CONFIDENCEGRADEINPUTCODE$6, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "confidence-grade-input-code" element
         */
        public org.apache.xmlbeans.XmlString xgetConfidenceGradeInputCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CONFIDENCEGRADEINPUTCODE$6, 0);
                return target;
            }
        }
        
        /**
         * True if has "confidence-grade-input-code" element
         */
        public boolean isSetConfidenceGradeInputCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(CONFIDENCEGRADEINPUTCODE$6) != 0;
            }
        }
        
        /**
         * Sets the "confidence-grade-input-code" element
         */
        public void setConfidenceGradeInputCode(java.lang.String confidenceGradeInputCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CONFIDENCEGRADEINPUTCODE$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CONFIDENCEGRADEINPUTCODE$6);
                }
                target.setStringValue(confidenceGradeInputCode);
            }
        }
        
        /**
         * Sets (as xml) the "confidence-grade-input-code" element
         */
        public void xsetConfidenceGradeInputCode(org.apache.xmlbeans.XmlString confidenceGradeInputCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CONFIDENCEGRADEINPUTCODE$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(CONFIDENCEGRADEINPUTCODE$6);
                }
                target.set(confidenceGradeInputCode);
            }
        }
        
        /**
         * Unsets the "confidence-grade-input-code" element
         */
        public void unsetConfidenceGradeInputCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(CONFIDENCEGRADEINPUTCODE$6, 0);
            }
        }
        
        /**
         * Gets the "row-heading-source" element
         */
        public boolean getRowHeadingSource()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ROWHEADINGSOURCE$8, 0);
                if (target == null)
                {
                    return false;
                }
                return target.getBooleanValue();
            }
        }
        
        /**
         * Gets (as xml) the "row-heading-source" element
         */
        public org.apache.xmlbeans.XmlBoolean xgetRowHeadingSource()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(ROWHEADINGSOURCE$8, 0);
                return target;
            }
        }
        
        /**
         * True if has "row-heading-source" element
         */
        public boolean isSetRowHeadingSource()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(ROWHEADINGSOURCE$8) != 0;
            }
        }
        
        /**
         * Sets the "row-heading-source" element
         */
        public void setRowHeadingSource(boolean rowHeadingSource)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ROWHEADINGSOURCE$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ROWHEADINGSOURCE$8);
                }
                target.setBooleanValue(rowHeadingSource);
            }
        }
        
        /**
         * Sets (as xml) the "row-heading-source" element
         */
        public void xsetRowHeadingSource(org.apache.xmlbeans.XmlBoolean rowHeadingSource)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(ROWHEADINGSOURCE$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(ROWHEADINGSOURCE$8);
                }
                target.set(rowHeadingSource);
            }
        }
        
        /**
         * Unsets the "row-heading-source" element
         */
        public void unsetRowHeadingSource()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(ROWHEADINGSOURCE$8, 0);
            }
        }
        
        /**
         * Gets the "column-heading-source" element
         */
        public boolean getColumnHeadingSource()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COLUMNHEADINGSOURCE$10, 0);
                if (target == null)
                {
                    return false;
                }
                return target.getBooleanValue();
            }
        }
        
        /**
         * Gets (as xml) the "column-heading-source" element
         */
        public org.apache.xmlbeans.XmlBoolean xgetColumnHeadingSource()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(COLUMNHEADINGSOURCE$10, 0);
                return target;
            }
        }
        
        /**
         * True if has "column-heading-source" element
         */
        public boolean isSetColumnHeadingSource()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(COLUMNHEADINGSOURCE$10) != 0;
            }
        }
        
        /**
         * Sets the "column-heading-source" element
         */
        public void setColumnHeadingSource(boolean columnHeadingSource)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COLUMNHEADINGSOURCE$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(COLUMNHEADINGSOURCE$10);
                }
                target.setBooleanValue(columnHeadingSource);
            }
        }
        
        /**
         * Sets (as xml) the "column-heading-source" element
         */
        public void xsetColumnHeadingSource(org.apache.xmlbeans.XmlBoolean columnHeadingSource)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(COLUMNHEADINGSOURCE$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(COLUMNHEADINGSOURCE$10);
                }
                target.set(columnHeadingSource);
            }
        }
        
        /**
         * Unsets the "column-heading-source" element
         */
        public void unsetColumnHeadingSource()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(COLUMNHEADINGSOURCE$10, 0);
            }
        }
        
        /**
         * Gets the "group-description-code" element
         */
        public java.lang.String getGroupDescriptionCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(GROUPDESCRIPTIONCODE$12, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "group-description-code" element
         */
        public org.apache.xmlbeans.XmlString xgetGroupDescriptionCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(GROUPDESCRIPTIONCODE$12, 0);
                return target;
            }
        }
        
        /**
         * True if has "group-description-code" element
         */
        public boolean isSetGroupDescriptionCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(GROUPDESCRIPTIONCODE$12) != 0;
            }
        }
        
        /**
         * Sets the "group-description-code" element
         */
        public void setGroupDescriptionCode(java.lang.String groupDescriptionCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(GROUPDESCRIPTIONCODE$12, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(GROUPDESCRIPTIONCODE$12);
                }
                target.setStringValue(groupDescriptionCode);
            }
        }
        
        /**
         * Sets (as xml) the "group-description-code" element
         */
        public void xsetGroupDescriptionCode(org.apache.xmlbeans.XmlString groupDescriptionCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(GROUPDESCRIPTIONCODE$12, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(GROUPDESCRIPTIONCODE$12);
                }
                target.set(groupDescriptionCode);
            }
        }
        
        /**
         * Unsets the "group-description-code" element
         */
        public void unsetGroupDescriptionCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(GROUPDESCRIPTIONCODE$12, 0);
            }
        }
        
        /**
         * Gets the "row" element
         */
        public java.lang.String getRow()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ROW$14, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "row" element
         */
        public org.apache.xmlbeans.XmlString xgetRow()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ROW$14, 0);
                return target;
            }
        }
        
        /**
         * Sets the "row" element
         */
        public void setRow(java.lang.String row)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ROW$14, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ROW$14);
                }
                target.setStringValue(row);
            }
        }
        
        /**
         * Sets (as xml) the "row" element
         */
        public void xsetRow(org.apache.xmlbeans.XmlString row)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ROW$14, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(ROW$14);
                }
                target.set(row);
            }
        }
        
        /**
         * Gets the "column" element
         */
        public java.lang.String getColumn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COLUMN$16, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "column" element
         */
        public org.apache.xmlbeans.XmlString xgetColumn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COLUMN$16, 0);
                return target;
            }
        }
        
        /**
         * Sets the "column" element
         */
        public void setColumn(java.lang.String column)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COLUMN$16, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(COLUMN$16);
                }
                target.setStringValue(column);
            }
        }
        
        /**
         * Sets (as xml) the "column" element
         */
        public void xsetColumn(org.apache.xmlbeans.XmlString column)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COLUMN$16, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(COLUMN$16);
                }
                target.set(column);
            }
        }
        
        /**
         * Gets the "row-span" element
         */
        public java.lang.String getRowSpan()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ROWSPAN$18, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "row-span" element
         */
        public org.apache.xmlbeans.XmlString xgetRowSpan()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ROWSPAN$18, 0);
                return target;
            }
        }
        
        /**
         * True if has "row-span" element
         */
        public boolean isSetRowSpan()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(ROWSPAN$18) != 0;
            }
        }
        
        /**
         * Sets the "row-span" element
         */
        public void setRowSpan(java.lang.String rowSpan)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ROWSPAN$18, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ROWSPAN$18);
                }
                target.setStringValue(rowSpan);
            }
        }
        
        /**
         * Sets (as xml) the "row-span" element
         */
        public void xsetRowSpan(org.apache.xmlbeans.XmlString rowSpan)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ROWSPAN$18, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(ROWSPAN$18);
                }
                target.set(rowSpan);
            }
        }
        
        /**
         * Unsets the "row-span" element
         */
        public void unsetRowSpan()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(ROWSPAN$18, 0);
            }
        }
        
        /**
         * Gets the "column-span" element
         */
        public java.lang.String getColumnSpan()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COLUMNSPAN$20, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "column-span" element
         */
        public org.apache.xmlbeans.XmlString xgetColumnSpan()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COLUMNSPAN$20, 0);
                return target;
            }
        }
        
        /**
         * True if has "column-span" element
         */
        public boolean isSetColumnSpan()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(COLUMNSPAN$20) != 0;
            }
        }
        
        /**
         * Sets the "column-span" element
         */
        public void setColumnSpan(java.lang.String columnSpan)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COLUMNSPAN$20, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(COLUMNSPAN$20);
                }
                target.setStringValue(columnSpan);
            }
        }
        
        /**
         * Sets (as xml) the "column-span" element
         */
        public void xsetColumnSpan(org.apache.xmlbeans.XmlString columnSpan)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COLUMNSPAN$20, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(COLUMNSPAN$20);
                }
                target.set(columnSpan);
            }
        }
        
        /**
         * Unsets the "column-span" element
         */
        public void unsetColumnSpan()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(COLUMNSPAN$20, 0);
            }
        }
        
        /**
         * Gets the "width" element
         */
        public java.lang.String getWidth()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(WIDTH$22, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "width" element
         */
        public org.apache.xmlbeans.XmlString xgetWidth()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(WIDTH$22, 0);
                return target;
            }
        }
        
        /**
         * True if has "width" element
         */
        public boolean isSetWidth()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(WIDTH$22) != 0;
            }
        }
        
        /**
         * Sets the "width" element
         */
        public void setWidth(java.lang.String width)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(WIDTH$22, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(WIDTH$22);
                }
                target.setStringValue(width);
            }
        }
        
        /**
         * Sets (as xml) the "width" element
         */
        public void xsetWidth(org.apache.xmlbeans.XmlString width)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(WIDTH$22, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(WIDTH$22);
                }
                target.set(width);
            }
        }
        
        /**
         * Unsets the "width" element
         */
        public void unsetWidth()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(WIDTH$22, 0);
            }
        }
    }
}
