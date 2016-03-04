/*
 * An XML document type.
 * Localname: sectiondetails
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.SectiondetailsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one sectiondetails(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class SectiondetailsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.SectiondetailsDocument
{
    private static final long serialVersionUID = 1L;
    
    public SectiondetailsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SECTIONDETAILS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "sectiondetails");
    
    
    /**
     * Gets the "sectiondetails" element
     */
    public uk.gov.ofwat.model2.SectiondetailsDocument.Sectiondetails getSectiondetails()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.SectiondetailsDocument.Sectiondetails target = null;
            target = (uk.gov.ofwat.model2.SectiondetailsDocument.Sectiondetails)get_store().find_element_user(SECTIONDETAILS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "sectiondetails" element
     */
    public void setSectiondetails(uk.gov.ofwat.model2.SectiondetailsDocument.Sectiondetails sectiondetails)
    {
        generatedSetterHelperImpl(sectiondetails, SECTIONDETAILS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "sectiondetails" element
     */
    public uk.gov.ofwat.model2.SectiondetailsDocument.Sectiondetails addNewSectiondetails()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.SectiondetailsDocument.Sectiondetails target = null;
            target = (uk.gov.ofwat.model2.SectiondetailsDocument.Sectiondetails)get_store().add_element_user(SECTIONDETAILS$0);
            return target;
        }
    }
    /**
     * An XML sectiondetails(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class SectiondetailsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.SectiondetailsDocument.Sectiondetails
    {
        private static final long serialVersionUID = 1L;
        
        public SectiondetailsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName DISPLAY$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "display");
        private static final javax.xml.namespace.QName CODE$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "code");
        private static final javax.xml.namespace.QName GROUPTYPE$4 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "grouptype");
        private static final javax.xml.namespace.QName USELINENUMBER$6 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "use-line-number");
        private static final javax.xml.namespace.QName USEITEMCODE$8 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "use-item-code");
        private static final javax.xml.namespace.QName USEITEMDESCRIPTION$10 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "use-item-description");
        private static final javax.xml.namespace.QName USEUNIT$12 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "use-unit");
        private static final javax.xml.namespace.QName USEYEARCODE$14 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "use-year-code");
        private static final javax.xml.namespace.QName USECONFIDENCEGRADES$16 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "use-confidence-grades");
        private static final javax.xml.namespace.QName ALLOWGROUPSELECTION$18 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "allow-group-selection");
        private static final javax.xml.namespace.QName ALLOWDATACHANGES$20 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "allow-data-changes");
        private static final javax.xml.namespace.QName SECTIONTYPE$22 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "section-type");
        private static final javax.xml.namespace.QName ITEMCODECOLUMN$24 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "item-code-column");
        
        
        /**
         * Gets the "display" element
         */
        public java.lang.String getDisplay()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DISPLAY$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "display" element
         */
        public org.apache.xmlbeans.XmlString xgetDisplay()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DISPLAY$0, 0);
                return target;
            }
        }
        
        /**
         * Sets the "display" element
         */
        public void setDisplay(java.lang.String display)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DISPLAY$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DISPLAY$0);
                }
                target.setStringValue(display);
            }
        }
        
        /**
         * Sets (as xml) the "display" element
         */
        public void xsetDisplay(org.apache.xmlbeans.XmlString display)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DISPLAY$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(DISPLAY$0);
                }
                target.set(display);
            }
        }
        
        /**
         * Gets the "code" element
         */
        public java.lang.String getCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODE$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "code" element
         */
        public org.apache.xmlbeans.XmlString xgetCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CODE$2, 0);
                return target;
            }
        }
        
        /**
         * Sets the "code" element
         */
        public void setCode(java.lang.String code)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODE$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CODE$2);
                }
                target.setStringValue(code);
            }
        }
        
        /**
         * Sets (as xml) the "code" element
         */
        public void xsetCode(org.apache.xmlbeans.XmlString code)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CODE$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(CODE$2);
                }
                target.set(code);
            }
        }
        
        /**
         * Gets the "grouptype" element
         */
        public java.lang.String getGrouptype()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(GROUPTYPE$4, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "grouptype" element
         */
        public org.apache.xmlbeans.XmlString xgetGrouptype()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(GROUPTYPE$4, 0);
                return target;
            }
        }
        
        /**
         * True if has "grouptype" element
         */
        public boolean isSetGrouptype()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(GROUPTYPE$4) != 0;
            }
        }
        
        /**
         * Sets the "grouptype" element
         */
        public void setGrouptype(java.lang.String grouptype)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(GROUPTYPE$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(GROUPTYPE$4);
                }
                target.setStringValue(grouptype);
            }
        }
        
        /**
         * Sets (as xml) the "grouptype" element
         */
        public void xsetGrouptype(org.apache.xmlbeans.XmlString grouptype)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(GROUPTYPE$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(GROUPTYPE$4);
                }
                target.set(grouptype);
            }
        }
        
        /**
         * Unsets the "grouptype" element
         */
        public void unsetGrouptype()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(GROUPTYPE$4, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USELINENUMBER$6, 0);
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
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USELINENUMBER$6, 0);
                return target;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USELINENUMBER$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(USELINENUMBER$6);
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
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USELINENUMBER$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(USELINENUMBER$6);
                }
                target.set(useLineNumber);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USEITEMCODE$8, 0);
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
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USEITEMCODE$8, 0);
                return target;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USEITEMCODE$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(USEITEMCODE$8);
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
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USEITEMCODE$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(USEITEMCODE$8);
                }
                target.set(useItemCode);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USEITEMDESCRIPTION$10, 0);
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
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USEITEMDESCRIPTION$10, 0);
                return target;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USEITEMDESCRIPTION$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(USEITEMDESCRIPTION$10);
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
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USEITEMDESCRIPTION$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(USEITEMDESCRIPTION$10);
                }
                target.set(useItemDescription);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USEUNIT$12, 0);
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
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USEUNIT$12, 0);
                return target;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USEUNIT$12, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(USEUNIT$12);
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
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USEUNIT$12, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(USEUNIT$12);
                }
                target.set(useUnit);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USEYEARCODE$14, 0);
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
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USEYEARCODE$14, 0);
                return target;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USEYEARCODE$14, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(USEYEARCODE$14);
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
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USEYEARCODE$14, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(USEYEARCODE$14);
                }
                target.set(useYearCode);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USECONFIDENCEGRADES$16, 0);
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
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USECONFIDENCEGRADES$16, 0);
                return target;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USECONFIDENCEGRADES$16, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(USECONFIDENCEGRADES$16);
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
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USECONFIDENCEGRADES$16, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(USECONFIDENCEGRADES$16);
                }
                target.set(useConfidenceGrades);
            }
        }
        
        /**
         * Gets the "allow-group-selection" element
         */
        public boolean getAllowGroupSelection()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ALLOWGROUPSELECTION$18, 0);
                if (target == null)
                {
                    return false;
                }
                return target.getBooleanValue();
            }
        }
        
        /**
         * Gets (as xml) the "allow-group-selection" element
         */
        public org.apache.xmlbeans.XmlBoolean xgetAllowGroupSelection()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(ALLOWGROUPSELECTION$18, 0);
                return target;
            }
        }
        
        /**
         * True if has "allow-group-selection" element
         */
        public boolean isSetAllowGroupSelection()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(ALLOWGROUPSELECTION$18) != 0;
            }
        }
        
        /**
         * Sets the "allow-group-selection" element
         */
        public void setAllowGroupSelection(boolean allowGroupSelection)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ALLOWGROUPSELECTION$18, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ALLOWGROUPSELECTION$18);
                }
                target.setBooleanValue(allowGroupSelection);
            }
        }
        
        /**
         * Sets (as xml) the "allow-group-selection" element
         */
        public void xsetAllowGroupSelection(org.apache.xmlbeans.XmlBoolean allowGroupSelection)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(ALLOWGROUPSELECTION$18, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(ALLOWGROUPSELECTION$18);
                }
                target.set(allowGroupSelection);
            }
        }
        
        /**
         * Unsets the "allow-group-selection" element
         */
        public void unsetAllowGroupSelection()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(ALLOWGROUPSELECTION$18, 0);
            }
        }
        
        /**
         * Gets the "allow-data-changes" element
         */
        public boolean getAllowDataChanges()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ALLOWDATACHANGES$20, 0);
                if (target == null)
                {
                    return false;
                }
                return target.getBooleanValue();
            }
        }
        
        /**
         * Gets (as xml) the "allow-data-changes" element
         */
        public org.apache.xmlbeans.XmlBoolean xgetAllowDataChanges()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(ALLOWDATACHANGES$20, 0);
                return target;
            }
        }
        
        /**
         * True if has "allow-data-changes" element
         */
        public boolean isSetAllowDataChanges()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(ALLOWDATACHANGES$20) != 0;
            }
        }
        
        /**
         * Sets the "allow-data-changes" element
         */
        public void setAllowDataChanges(boolean allowDataChanges)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ALLOWDATACHANGES$20, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ALLOWDATACHANGES$20);
                }
                target.setBooleanValue(allowDataChanges);
            }
        }
        
        /**
         * Sets (as xml) the "allow-data-changes" element
         */
        public void xsetAllowDataChanges(org.apache.xmlbeans.XmlBoolean allowDataChanges)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(ALLOWDATACHANGES$20, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(ALLOWDATACHANGES$20);
                }
                target.set(allowDataChanges);
            }
        }
        
        /**
         * Unsets the "allow-data-changes" element
         */
        public void unsetAllowDataChanges()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(ALLOWDATACHANGES$20, 0);
            }
        }
        
        /**
         * Gets the "section-type" element
         */
        public java.lang.String getSectionType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SECTIONTYPE$22, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "section-type" element
         */
        public org.apache.xmlbeans.XmlString xgetSectionType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(SECTIONTYPE$22, 0);
                return target;
            }
        }
        
        /**
         * True if has "section-type" element
         */
        public boolean isSetSectionType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(SECTIONTYPE$22) != 0;
            }
        }
        
        /**
         * Sets the "section-type" element
         */
        public void setSectionType(java.lang.String sectionType)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SECTIONTYPE$22, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(SECTIONTYPE$22);
                }
                target.setStringValue(sectionType);
            }
        }
        
        /**
         * Sets (as xml) the "section-type" element
         */
        public void xsetSectionType(org.apache.xmlbeans.XmlString sectionType)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(SECTIONTYPE$22, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(SECTIONTYPE$22);
                }
                target.set(sectionType);
            }
        }
        
        /**
         * Unsets the "section-type" element
         */
        public void unsetSectionType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(SECTIONTYPE$22, 0);
            }
        }
        
        /**
         * Gets the "item-code-column" element
         */
        public java.math.BigInteger getItemCodeColumn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ITEMCODECOLUMN$24, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getBigIntegerValue();
            }
        }
        
        /**
         * Gets (as xml) the "item-code-column" element
         */
        public org.apache.xmlbeans.XmlNonNegativeInteger xgetItemCodeColumn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlNonNegativeInteger target = null;
                target = (org.apache.xmlbeans.XmlNonNegativeInteger)get_store().find_element_user(ITEMCODECOLUMN$24, 0);
                return target;
            }
        }
        
        /**
         * True if has "item-code-column" element
         */
        public boolean isSetItemCodeColumn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(ITEMCODECOLUMN$24) != 0;
            }
        }
        
        /**
         * Sets the "item-code-column" element
         */
        public void setItemCodeColumn(java.math.BigInteger itemCodeColumn)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ITEMCODECOLUMN$24, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ITEMCODECOLUMN$24);
                }
                target.setBigIntegerValue(itemCodeColumn);
            }
        }
        
        /**
         * Sets (as xml) the "item-code-column" element
         */
        public void xsetItemCodeColumn(org.apache.xmlbeans.XmlNonNegativeInteger itemCodeColumn)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlNonNegativeInteger target = null;
                target = (org.apache.xmlbeans.XmlNonNegativeInteger)get_store().find_element_user(ITEMCODECOLUMN$24, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlNonNegativeInteger)get_store().add_element_user(ITEMCODECOLUMN$24);
                }
                target.set(itemCodeColumn);
            }
        }
        
        /**
         * Unsets the "item-code-column" element
         */
        public void unsetItemCodeColumn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(ITEMCODECOLUMN$24, 0);
            }
        }
    }
}
