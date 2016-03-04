/*
 * An XML document type.
 * Localname: form-heading-cell
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.FormHeadingCellDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one form-heading-cell(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class FormHeadingCellDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.FormHeadingCellDocument
{
    private static final long serialVersionUID = 1L;
    
    public FormHeadingCellDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FORMHEADINGCELL$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "form-heading-cell");
    
    
    /**
     * Gets the "form-heading-cell" element
     */
    public uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell getFormHeadingCell()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell target = null;
            target = (uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell)get_store().find_element_user(FORMHEADINGCELL$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "form-heading-cell" element
     */
    public void setFormHeadingCell(uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell formHeadingCell)
    {
        generatedSetterHelperImpl(formHeadingCell, FORMHEADINGCELL$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "form-heading-cell" element
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
     * An XML form-heading-cell(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class FormHeadingCellImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell
    {
        private static final long serialVersionUID = 1L;
        
        public FormHeadingCellImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName TEXT$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "text");
        private static final javax.xml.namespace.QName USELINENUMBER$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "use-line-number");
        private static final javax.xml.namespace.QName USEITEMCODE$4 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "use-item-code");
        private static final javax.xml.namespace.QName USEITEMDESCRIPTION$6 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "use-item-description");
        private static final javax.xml.namespace.QName USEUNIT$8 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "use-unit");
        private static final javax.xml.namespace.QName USEYEARCODE$10 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "use-year-code");
        private static final javax.xml.namespace.QName USECONFIDENCEGRADES$12 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "use-confidence-grades");
        private static final javax.xml.namespace.QName ROW$14 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "row");
        private static final javax.xml.namespace.QName COLUMN$16 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "column");
        private static final javax.xml.namespace.QName ROWSPAN$18 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "row-span");
        private static final javax.xml.namespace.QName COLUMNSPAN$20 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "column-span");
        private static final javax.xml.namespace.QName ITEMCODE$22 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "item-code");
        private static final javax.xml.namespace.QName YEARCODE$24 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "year-code");
        private static final javax.xml.namespace.QName WIDTH$26 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "width");
        private static final javax.xml.namespace.QName CELLCODE$28 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "cell-code");
        private static final javax.xml.namespace.QName GROUPDESCRIPTIONCODE$30 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "group-description-code");
        
        
        /**
         * Gets the "text" element
         */
        public java.lang.String getText()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TEXT$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "text" element
         */
        public org.apache.xmlbeans.XmlString xgetText()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TEXT$0, 0);
                return target;
            }
        }
        
        /**
         * True if has "text" element
         */
        public boolean isSetText()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TEXT$0) != 0;
            }
        }
        
        /**
         * Sets the "text" element
         */
        public void setText(java.lang.String text)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TEXT$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TEXT$0);
                }
                target.setStringValue(text);
            }
        }
        
        /**
         * Sets (as xml) the "text" element
         */
        public void xsetText(org.apache.xmlbeans.XmlString text)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TEXT$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TEXT$0);
                }
                target.set(text);
            }
        }
        
        /**
         * Unsets the "text" element
         */
        public void unsetText()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TEXT$0, 0);
            }
        }
        
        /**
         * Gets the "use-line-number" element
         */
        public boolean getUseLineNumber()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USELINENUMBER$2, 0);
                if (target == null)
                {
                    return false;
                }
                return target.getBooleanValue();
            }
        }
        
        /**
         * Gets (as xml) the "use-line-number" element
         */
        public org.apache.xmlbeans.XmlBoolean xgetUseLineNumber()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USELINENUMBER$2, 0);
                return target;
            }
        }
        
        /**
         * True if has "use-line-number" element
         */
        public boolean isSetUseLineNumber()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(USELINENUMBER$2) != 0;
            }
        }
        
        /**
         * Sets the "use-line-number" element
         */
        public void setUseLineNumber(boolean useLineNumber)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USELINENUMBER$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(USELINENUMBER$2);
                }
                target.setBooleanValue(useLineNumber);
            }
        }
        
        /**
         * Sets (as xml) the "use-line-number" element
         */
        public void xsetUseLineNumber(org.apache.xmlbeans.XmlBoolean useLineNumber)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USELINENUMBER$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(USELINENUMBER$2);
                }
                target.set(useLineNumber);
            }
        }
        
        /**
         * Unsets the "use-line-number" element
         */
        public void unsetUseLineNumber()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(USELINENUMBER$2, 0);
            }
        }
        
        /**
         * Gets the "use-item-code" element
         */
        public boolean getUseItemCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USEITEMCODE$4, 0);
                if (target == null)
                {
                    return false;
                }
                return target.getBooleanValue();
            }
        }
        
        /**
         * Gets (as xml) the "use-item-code" element
         */
        public org.apache.xmlbeans.XmlBoolean xgetUseItemCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USEITEMCODE$4, 0);
                return target;
            }
        }
        
        /**
         * True if has "use-item-code" element
         */
        public boolean isSetUseItemCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(USEITEMCODE$4) != 0;
            }
        }
        
        /**
         * Sets the "use-item-code" element
         */
        public void setUseItemCode(boolean useItemCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USEITEMCODE$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(USEITEMCODE$4);
                }
                target.setBooleanValue(useItemCode);
            }
        }
        
        /**
         * Sets (as xml) the "use-item-code" element
         */
        public void xsetUseItemCode(org.apache.xmlbeans.XmlBoolean useItemCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USEITEMCODE$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(USEITEMCODE$4);
                }
                target.set(useItemCode);
            }
        }
        
        /**
         * Unsets the "use-item-code" element
         */
        public void unsetUseItemCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(USEITEMCODE$4, 0);
            }
        }
        
        /**
         * Gets the "use-item-description" element
         */
        public boolean getUseItemDescription()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USEITEMDESCRIPTION$6, 0);
                if (target == null)
                {
                    return false;
                }
                return target.getBooleanValue();
            }
        }
        
        /**
         * Gets (as xml) the "use-item-description" element
         */
        public org.apache.xmlbeans.XmlBoolean xgetUseItemDescription()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USEITEMDESCRIPTION$6, 0);
                return target;
            }
        }
        
        /**
         * True if has "use-item-description" element
         */
        public boolean isSetUseItemDescription()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(USEITEMDESCRIPTION$6) != 0;
            }
        }
        
        /**
         * Sets the "use-item-description" element
         */
        public void setUseItemDescription(boolean useItemDescription)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USEITEMDESCRIPTION$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(USEITEMDESCRIPTION$6);
                }
                target.setBooleanValue(useItemDescription);
            }
        }
        
        /**
         * Sets (as xml) the "use-item-description" element
         */
        public void xsetUseItemDescription(org.apache.xmlbeans.XmlBoolean useItemDescription)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USEITEMDESCRIPTION$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(USEITEMDESCRIPTION$6);
                }
                target.set(useItemDescription);
            }
        }
        
        /**
         * Unsets the "use-item-description" element
         */
        public void unsetUseItemDescription()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(USEITEMDESCRIPTION$6, 0);
            }
        }
        
        /**
         * Gets the "use-unit" element
         */
        public boolean getUseUnit()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USEUNIT$8, 0);
                if (target == null)
                {
                    return false;
                }
                return target.getBooleanValue();
            }
        }
        
        /**
         * Gets (as xml) the "use-unit" element
         */
        public org.apache.xmlbeans.XmlBoolean xgetUseUnit()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USEUNIT$8, 0);
                return target;
            }
        }
        
        /**
         * True if has "use-unit" element
         */
        public boolean isSetUseUnit()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(USEUNIT$8) != 0;
            }
        }
        
        /**
         * Sets the "use-unit" element
         */
        public void setUseUnit(boolean useUnit)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USEUNIT$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(USEUNIT$8);
                }
                target.setBooleanValue(useUnit);
            }
        }
        
        /**
         * Sets (as xml) the "use-unit" element
         */
        public void xsetUseUnit(org.apache.xmlbeans.XmlBoolean useUnit)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USEUNIT$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(USEUNIT$8);
                }
                target.set(useUnit);
            }
        }
        
        /**
         * Unsets the "use-unit" element
         */
        public void unsetUseUnit()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(USEUNIT$8, 0);
            }
        }
        
        /**
         * Gets the "use-year-code" element
         */
        public boolean getUseYearCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USEYEARCODE$10, 0);
                if (target == null)
                {
                    return false;
                }
                return target.getBooleanValue();
            }
        }
        
        /**
         * Gets (as xml) the "use-year-code" element
         */
        public org.apache.xmlbeans.XmlBoolean xgetUseYearCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USEYEARCODE$10, 0);
                return target;
            }
        }
        
        /**
         * True if has "use-year-code" element
         */
        public boolean isSetUseYearCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(USEYEARCODE$10) != 0;
            }
        }
        
        /**
         * Sets the "use-year-code" element
         */
        public void setUseYearCode(boolean useYearCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USEYEARCODE$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(USEYEARCODE$10);
                }
                target.setBooleanValue(useYearCode);
            }
        }
        
        /**
         * Sets (as xml) the "use-year-code" element
         */
        public void xsetUseYearCode(org.apache.xmlbeans.XmlBoolean useYearCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USEYEARCODE$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(USEYEARCODE$10);
                }
                target.set(useYearCode);
            }
        }
        
        /**
         * Unsets the "use-year-code" element
         */
        public void unsetUseYearCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(USEYEARCODE$10, 0);
            }
        }
        
        /**
         * Gets the "use-confidence-grades" element
         */
        public boolean getUseConfidenceGrades()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USECONFIDENCEGRADES$12, 0);
                if (target == null)
                {
                    return false;
                }
                return target.getBooleanValue();
            }
        }
        
        /**
         * Gets (as xml) the "use-confidence-grades" element
         */
        public org.apache.xmlbeans.XmlBoolean xgetUseConfidenceGrades()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USECONFIDENCEGRADES$12, 0);
                return target;
            }
        }
        
        /**
         * True if has "use-confidence-grades" element
         */
        public boolean isSetUseConfidenceGrades()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(USECONFIDENCEGRADES$12) != 0;
            }
        }
        
        /**
         * Sets the "use-confidence-grades" element
         */
        public void setUseConfidenceGrades(boolean useConfidenceGrades)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USECONFIDENCEGRADES$12, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(USECONFIDENCEGRADES$12);
                }
                target.setBooleanValue(useConfidenceGrades);
            }
        }
        
        /**
         * Sets (as xml) the "use-confidence-grades" element
         */
        public void xsetUseConfidenceGrades(org.apache.xmlbeans.XmlBoolean useConfidenceGrades)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USECONFIDENCEGRADES$12, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(USECONFIDENCEGRADES$12);
                }
                target.set(useConfidenceGrades);
            }
        }
        
        /**
         * Unsets the "use-confidence-grades" element
         */
        public void unsetUseConfidenceGrades()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(USECONFIDENCEGRADES$12, 0);
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
         * Gets the "item-code" element
         */
        public java.lang.String getItemCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ITEMCODE$22, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "item-code" element
         */
        public org.apache.xmlbeans.XmlString xgetItemCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ITEMCODE$22, 0);
                return target;
            }
        }
        
        /**
         * True if has "item-code" element
         */
        public boolean isSetItemCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(ITEMCODE$22) != 0;
            }
        }
        
        /**
         * Sets the "item-code" element
         */
        public void setItemCode(java.lang.String itemCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ITEMCODE$22, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ITEMCODE$22);
                }
                target.setStringValue(itemCode);
            }
        }
        
        /**
         * Sets (as xml) the "item-code" element
         */
        public void xsetItemCode(org.apache.xmlbeans.XmlString itemCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ITEMCODE$22, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(ITEMCODE$22);
                }
                target.set(itemCode);
            }
        }
        
        /**
         * Unsets the "item-code" element
         */
        public void unsetItemCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(ITEMCODE$22, 0);
            }
        }
        
        /**
         * Gets the "year-code" element
         */
        public java.lang.String getYearCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(YEARCODE$24, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "year-code" element
         */
        public org.apache.xmlbeans.XmlString xgetYearCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(YEARCODE$24, 0);
                return target;
            }
        }
        
        /**
         * True if has "year-code" element
         */
        public boolean isSetYearCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(YEARCODE$24) != 0;
            }
        }
        
        /**
         * Sets the "year-code" element
         */
        public void setYearCode(java.lang.String yearCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(YEARCODE$24, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(YEARCODE$24);
                }
                target.setStringValue(yearCode);
            }
        }
        
        /**
         * Sets (as xml) the "year-code" element
         */
        public void xsetYearCode(org.apache.xmlbeans.XmlString yearCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(YEARCODE$24, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(YEARCODE$24);
                }
                target.set(yearCode);
            }
        }
        
        /**
         * Unsets the "year-code" element
         */
        public void unsetYearCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(YEARCODE$24, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(WIDTH$26, 0);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(WIDTH$26, 0);
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
                return get_store().count_elements(WIDTH$26) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(WIDTH$26, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(WIDTH$26);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(WIDTH$26, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(WIDTH$26);
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
                get_store().remove_element(WIDTH$26, 0);
            }
        }
        
        /**
         * Gets the "cell-code" element
         */
        public java.lang.String getCellCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CELLCODE$28, 0);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CELLCODE$28, 0);
                return target;
            }
        }
        
        /**
         * True if has "cell-code" element
         */
        public boolean isSetCellCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(CELLCODE$28) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CELLCODE$28, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CELLCODE$28);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CELLCODE$28, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(CELLCODE$28);
                }
                target.set(cellCode);
            }
        }
        
        /**
         * Unsets the "cell-code" element
         */
        public void unsetCellCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(CELLCODE$28, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(GROUPDESCRIPTIONCODE$30, 0);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(GROUPDESCRIPTIONCODE$30, 0);
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
                return get_store().count_elements(GROUPDESCRIPTIONCODE$30) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(GROUPDESCRIPTIONCODE$30, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(GROUPDESCRIPTIONCODE$30);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(GROUPDESCRIPTIONCODE$30, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(GROUPDESCRIPTIONCODE$30);
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
                get_store().remove_element(GROUPDESCRIPTIONCODE$30, 0);
            }
        }
    }
}
