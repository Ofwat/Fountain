/*
 * An XML document type.
 * Localname: linedetails
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.LinedetailsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one linedetails(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class LinedetailsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.LinedetailsDocument
{
    private static final long serialVersionUID = 1L;
    
    public LinedetailsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName LINEDETAILS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "linedetails");
    
    
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
     * An XML linedetails(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class LinedetailsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.LinedetailsDocument.Linedetails
    {
        private static final long serialVersionUID = 1L;
        
        public LinedetailsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName HEADING$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "heading");
        private static final javax.xml.namespace.QName CODE$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "code");
        private static final javax.xml.namespace.QName DESCRIPTION$4 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "description");
        private static final javax.xml.namespace.QName EQUATION$6 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "equation");
        private static final javax.xml.namespace.QName LINENUMBER$8 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "linenumber");
        private static final javax.xml.namespace.QName RULETEXT$10 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "ruletext");
        private static final javax.xml.namespace.QName TYPE$12 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "type");
        private static final javax.xml.namespace.QName COMPANYTYPE$14 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "company-type");
        private static final javax.xml.namespace.QName USECONFIDENCEGRADE$16 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "use-confidence-grade");
        private static final javax.xml.namespace.QName VALIDATIONRULECODE$18 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "validation-rule-code");
        private static final javax.xml.namespace.QName TEXTCODE$20 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "text-code");
        private static final javax.xml.namespace.QName UNIT$22 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "unit");
        private static final javax.xml.namespace.QName DECIMALPLACES$24 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "decimal-places");
        
        
        /**
         * Gets the "heading" element
         */
        public boolean getHeading()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(HEADING$0, 0);
                if (target == null)
                {
                    return false;
                }
                return target.getBooleanValue();
            }
        }
        
        /**
         * Gets (as xml) the "heading" element
         */
        public org.apache.xmlbeans.XmlBoolean xgetHeading()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(HEADING$0, 0);
                return target;
            }
        }
        
        /**
         * True if has "heading" element
         */
        public boolean isSetHeading()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(HEADING$0) != 0;
            }
        }
        
        /**
         * Sets the "heading" element
         */
        public void setHeading(boolean heading)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(HEADING$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(HEADING$0);
                }
                target.setBooleanValue(heading);
            }
        }
        
        /**
         * Sets (as xml) the "heading" element
         */
        public void xsetHeading(org.apache.xmlbeans.XmlBoolean heading)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(HEADING$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(HEADING$0);
                }
                target.set(heading);
            }
        }
        
        /**
         * Unsets the "heading" element
         */
        public void unsetHeading()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(HEADING$0, 0);
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
         * True if has "code" element
         */
        public boolean isSetCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(CODE$2) != 0;
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
         * Unsets the "code" element
         */
        public void unsetCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(CODE$2, 0);
            }
        }
        
        /**
         * Gets the "description" element
         */
        public java.lang.String getDescription()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DESCRIPTION$4, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "description" element
         */
        public org.apache.xmlbeans.XmlString xgetDescription()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DESCRIPTION$4, 0);
                return target;
            }
        }
        
        /**
         * True if has "description" element
         */
        public boolean isSetDescription()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(DESCRIPTION$4) != 0;
            }
        }
        
        /**
         * Sets the "description" element
         */
        public void setDescription(java.lang.String description)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DESCRIPTION$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DESCRIPTION$4);
                }
                target.setStringValue(description);
            }
        }
        
        /**
         * Sets (as xml) the "description" element
         */
        public void xsetDescription(org.apache.xmlbeans.XmlString description)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DESCRIPTION$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(DESCRIPTION$4);
                }
                target.set(description);
            }
        }
        
        /**
         * Unsets the "description" element
         */
        public void unsetDescription()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(DESCRIPTION$4, 0);
            }
        }
        
        /**
         * Gets the "equation" element
         */
        public java.lang.String getEquation()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EQUATION$6, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "equation" element
         */
        public org.apache.xmlbeans.XmlString xgetEquation()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(EQUATION$6, 0);
                return target;
            }
        }
        
        /**
         * True if has "equation" element
         */
        public boolean isSetEquation()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(EQUATION$6) != 0;
            }
        }
        
        /**
         * Sets the "equation" element
         */
        public void setEquation(java.lang.String equation)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EQUATION$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(EQUATION$6);
                }
                target.setStringValue(equation);
            }
        }
        
        /**
         * Sets (as xml) the "equation" element
         */
        public void xsetEquation(org.apache.xmlbeans.XmlString equation)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(EQUATION$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(EQUATION$6);
                }
                target.set(equation);
            }
        }
        
        /**
         * Unsets the "equation" element
         */
        public void unsetEquation()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(EQUATION$6, 0);
            }
        }
        
        /**
         * Gets the "linenumber" element
         */
        public java.lang.String getLinenumber()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(LINENUMBER$8, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "linenumber" element
         */
        public org.apache.xmlbeans.XmlString xgetLinenumber()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(LINENUMBER$8, 0);
                return target;
            }
        }
        
        /**
         * True if has "linenumber" element
         */
        public boolean isSetLinenumber()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(LINENUMBER$8) != 0;
            }
        }
        
        /**
         * Sets the "linenumber" element
         */
        public void setLinenumber(java.lang.String linenumber)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(LINENUMBER$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(LINENUMBER$8);
                }
                target.setStringValue(linenumber);
            }
        }
        
        /**
         * Sets (as xml) the "linenumber" element
         */
        public void xsetLinenumber(org.apache.xmlbeans.XmlString linenumber)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(LINENUMBER$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(LINENUMBER$8);
                }
                target.set(linenumber);
            }
        }
        
        /**
         * Unsets the "linenumber" element
         */
        public void unsetLinenumber()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(LINENUMBER$8, 0);
            }
        }
        
        /**
         * Gets the "ruletext" element
         */
        public java.lang.String getRuletext()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(RULETEXT$10, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "ruletext" element
         */
        public org.apache.xmlbeans.XmlString xgetRuletext()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(RULETEXT$10, 0);
                return target;
            }
        }
        
        /**
         * True if has "ruletext" element
         */
        public boolean isSetRuletext()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(RULETEXT$10) != 0;
            }
        }
        
        /**
         * Sets the "ruletext" element
         */
        public void setRuletext(java.lang.String ruletext)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(RULETEXT$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(RULETEXT$10);
                }
                target.setStringValue(ruletext);
            }
        }
        
        /**
         * Sets (as xml) the "ruletext" element
         */
        public void xsetRuletext(org.apache.xmlbeans.XmlString ruletext)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(RULETEXT$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(RULETEXT$10);
                }
                target.set(ruletext);
            }
        }
        
        /**
         * Unsets the "ruletext" element
         */
        public void unsetRuletext()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(RULETEXT$10, 0);
            }
        }
        
        /**
         * Gets the "type" element
         */
        public java.lang.String getType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TYPE$12, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "type" element
         */
        public org.apache.xmlbeans.XmlString xgetType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TYPE$12, 0);
                return target;
            }
        }
        
        /**
         * True if has "type" element
         */
        public boolean isSetType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TYPE$12) != 0;
            }
        }
        
        /**
         * Sets the "type" element
         */
        public void setType(java.lang.String type)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TYPE$12, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TYPE$12);
                }
                target.setStringValue(type);
            }
        }
        
        /**
         * Sets (as xml) the "type" element
         */
        public void xsetType(org.apache.xmlbeans.XmlString type)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TYPE$12, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TYPE$12);
                }
                target.set(type);
            }
        }
        
        /**
         * Unsets the "type" element
         */
        public void unsetType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TYPE$12, 0);
            }
        }
        
        /**
         * Gets the "company-type" element
         */
        public java.lang.String getCompanyType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COMPANYTYPE$14, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "company-type" element
         */
        public org.apache.xmlbeans.XmlString xgetCompanyType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COMPANYTYPE$14, 0);
                return target;
            }
        }
        
        /**
         * True if has "company-type" element
         */
        public boolean isSetCompanyType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(COMPANYTYPE$14) != 0;
            }
        }
        
        /**
         * Sets the "company-type" element
         */
        public void setCompanyType(java.lang.String companyType)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COMPANYTYPE$14, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(COMPANYTYPE$14);
                }
                target.setStringValue(companyType);
            }
        }
        
        /**
         * Sets (as xml) the "company-type" element
         */
        public void xsetCompanyType(org.apache.xmlbeans.XmlString companyType)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COMPANYTYPE$14, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(COMPANYTYPE$14);
                }
                target.set(companyType);
            }
        }
        
        /**
         * Unsets the "company-type" element
         */
        public void unsetCompanyType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(COMPANYTYPE$14, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USECONFIDENCEGRADE$16, 0);
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
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USECONFIDENCEGRADE$16, 0);
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
                return get_store().count_elements(USECONFIDENCEGRADE$16) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(USECONFIDENCEGRADE$16, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(USECONFIDENCEGRADE$16);
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
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(USECONFIDENCEGRADE$16, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(USECONFIDENCEGRADE$16);
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
                get_store().remove_element(USECONFIDENCEGRADE$16, 0);
            }
        }
        
        /**
         * Gets the "validation-rule-code" element
         */
        public java.lang.String getValidationRuleCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(VALIDATIONRULECODE$18, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "validation-rule-code" element
         */
        public org.apache.xmlbeans.XmlString xgetValidationRuleCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(VALIDATIONRULECODE$18, 0);
                return target;
            }
        }
        
        /**
         * True if has "validation-rule-code" element
         */
        public boolean isSetValidationRuleCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(VALIDATIONRULECODE$18) != 0;
            }
        }
        
        /**
         * Sets the "validation-rule-code" element
         */
        public void setValidationRuleCode(java.lang.String validationRuleCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(VALIDATIONRULECODE$18, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(VALIDATIONRULECODE$18);
                }
                target.setStringValue(validationRuleCode);
            }
        }
        
        /**
         * Sets (as xml) the "validation-rule-code" element
         */
        public void xsetValidationRuleCode(org.apache.xmlbeans.XmlString validationRuleCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(VALIDATIONRULECODE$18, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(VALIDATIONRULECODE$18);
                }
                target.set(validationRuleCode);
            }
        }
        
        /**
         * Unsets the "validation-rule-code" element
         */
        public void unsetValidationRuleCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(VALIDATIONRULECODE$18, 0);
            }
        }
        
        /**
         * Gets the "text-code" element
         */
        public java.lang.String getTextCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TEXTCODE$20, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "text-code" element
         */
        public org.apache.xmlbeans.XmlString xgetTextCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TEXTCODE$20, 0);
                return target;
            }
        }
        
        /**
         * True if has "text-code" element
         */
        public boolean isSetTextCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TEXTCODE$20) != 0;
            }
        }
        
        /**
         * Sets the "text-code" element
         */
        public void setTextCode(java.lang.String textCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TEXTCODE$20, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TEXTCODE$20);
                }
                target.setStringValue(textCode);
            }
        }
        
        /**
         * Sets (as xml) the "text-code" element
         */
        public void xsetTextCode(org.apache.xmlbeans.XmlString textCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TEXTCODE$20, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TEXTCODE$20);
                }
                target.set(textCode);
            }
        }
        
        /**
         * Unsets the "text-code" element
         */
        public void unsetTextCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TEXTCODE$20, 0);
            }
        }
        
        /**
         * Gets the "unit" element
         */
        public java.lang.String getUnit()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(UNIT$22, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "unit" element
         */
        public org.apache.xmlbeans.XmlString xgetUnit()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(UNIT$22, 0);
                return target;
            }
        }
        
        /**
         * True if has "unit" element
         */
        public boolean isSetUnit()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(UNIT$22) != 0;
            }
        }
        
        /**
         * Sets the "unit" element
         */
        public void setUnit(java.lang.String unit)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(UNIT$22, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(UNIT$22);
                }
                target.setStringValue(unit);
            }
        }
        
        /**
         * Sets (as xml) the "unit" element
         */
        public void xsetUnit(org.apache.xmlbeans.XmlString unit)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(UNIT$22, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(UNIT$22);
                }
                target.set(unit);
            }
        }
        
        /**
         * Unsets the "unit" element
         */
        public void unsetUnit()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(UNIT$22, 0);
            }
        }
        
        /**
         * Gets the "decimal-places" element
         */
        public java.math.BigInteger getDecimalPlaces()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DECIMALPLACES$24, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getBigIntegerValue();
            }
        }
        
        /**
         * Gets (as xml) the "decimal-places" element
         */
        public org.apache.xmlbeans.XmlNonNegativeInteger xgetDecimalPlaces()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlNonNegativeInteger target = null;
                target = (org.apache.xmlbeans.XmlNonNegativeInteger)get_store().find_element_user(DECIMALPLACES$24, 0);
                return target;
            }
        }
        
        /**
         * True if has "decimal-places" element
         */
        public boolean isSetDecimalPlaces()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(DECIMALPLACES$24) != 0;
            }
        }
        
        /**
         * Sets the "decimal-places" element
         */
        public void setDecimalPlaces(java.math.BigInteger decimalPlaces)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DECIMALPLACES$24, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DECIMALPLACES$24);
                }
                target.setBigIntegerValue(decimalPlaces);
            }
        }
        
        /**
         * Sets (as xml) the "decimal-places" element
         */
        public void xsetDecimalPlaces(org.apache.xmlbeans.XmlNonNegativeInteger decimalPlaces)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlNonNegativeInteger target = null;
                target = (org.apache.xmlbeans.XmlNonNegativeInteger)get_store().find_element_user(DECIMALPLACES$24, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlNonNegativeInteger)get_store().add_element_user(DECIMALPLACES$24);
                }
                target.set(decimalPlaces);
            }
        }
        
        /**
         * Unsets the "decimal-places" element
         */
        public void unsetDecimalPlaces()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(DECIMALPLACES$24, 0);
            }
        }
    }
}
