/*
 * An XML document type.
 * Localname: modeldetails
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.ModeldetailsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one modeldetails(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class ModeldetailsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.ModeldetailsDocument
{
    private static final long serialVersionUID = 1L;
    
    public ModeldetailsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName MODELDETAILS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "modeldetails");
    
    
    /**
     * Gets the "modeldetails" element
     */
    public uk.gov.ofwat.model2.ModeldetailsDocument.Modeldetails getModeldetails()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.ModeldetailsDocument.Modeldetails target = null;
            target = (uk.gov.ofwat.model2.ModeldetailsDocument.Modeldetails)get_store().find_element_user(MODELDETAILS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "modeldetails" element
     */
    public void setModeldetails(uk.gov.ofwat.model2.ModeldetailsDocument.Modeldetails modeldetails)
    {
        generatedSetterHelperImpl(modeldetails, MODELDETAILS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "modeldetails" element
     */
    public uk.gov.ofwat.model2.ModeldetailsDocument.Modeldetails addNewModeldetails()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.ModeldetailsDocument.Modeldetails target = null;
            target = (uk.gov.ofwat.model2.ModeldetailsDocument.Modeldetails)get_store().add_element_user(MODELDETAILS$0);
            return target;
        }
    }
    /**
     * An XML modeldetails(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class ModeldetailsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.ModeldetailsDocument.Modeldetails
    {
        private static final long serialVersionUID = 1L;
        
        public ModeldetailsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName CODE$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "code");
        private static final javax.xml.namespace.QName NAME$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "name");
        private static final javax.xml.namespace.QName VERSION$4 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "version");
        private static final javax.xml.namespace.QName TYPE$6 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "type");
        private static final javax.xml.namespace.QName TEXTCODE$8 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "text-code");
        private static final javax.xml.namespace.QName BASEYEARCODE$10 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "base-year-code");
        private static final javax.xml.namespace.QName REPORTYEARCODE$12 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "report-year-code");
        private static final javax.xml.namespace.QName ALLOWDATACHANGES$14 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "allow-data-changes");
        private static final javax.xml.namespace.QName MODELFAMILYCODE$16 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "model-family-code");
        private static final javax.xml.namespace.QName MODELFAMILYPARENT$18 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "model-family-parent");
        private static final javax.xml.namespace.QName DISPLAYORDER$20 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "display-order");
        private static final javax.xml.namespace.QName BRANCHTAG$22 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "branch-tag");
        private static final javax.xml.namespace.QName RUNCODE$24 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "run-code");
        
        
        /**
         * Gets the "code" element
         */
        public java.lang.String getCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODE$0, 0);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CODE$0, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CODE$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CODE$0);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CODE$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(CODE$0);
                }
                target.set(code);
            }
        }
        
        /**
         * Gets the "name" element
         */
        public java.lang.String getName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NAME$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "name" element
         */
        public org.apache.xmlbeans.XmlString xgetName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NAME$2, 0);
                return target;
            }
        }
        
        /**
         * Sets the "name" element
         */
        public void setName(java.lang.String name)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NAME$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(NAME$2);
                }
                target.setStringValue(name);
            }
        }
        
        /**
         * Sets (as xml) the "name" element
         */
        public void xsetName(org.apache.xmlbeans.XmlString name)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NAME$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(NAME$2);
                }
                target.set(name);
            }
        }
        
        /**
         * Gets the "version" element
         */
        public java.lang.String getVersion()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(VERSION$4, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "version" element
         */
        public org.apache.xmlbeans.XmlString xgetVersion()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(VERSION$4, 0);
                return target;
            }
        }
        
        /**
         * Sets the "version" element
         */
        public void setVersion(java.lang.String version)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(VERSION$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(VERSION$4);
                }
                target.setStringValue(version);
            }
        }
        
        /**
         * Sets (as xml) the "version" element
         */
        public void xsetVersion(org.apache.xmlbeans.XmlString version)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(VERSION$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(VERSION$4);
                }
                target.set(version);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TYPE$6, 0);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TYPE$6, 0);
                return target;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TYPE$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TYPE$6);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TYPE$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TYPE$6);
                }
                target.set(type);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TEXTCODE$8, 0);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TEXTCODE$8, 0);
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
                return get_store().count_elements(TEXTCODE$8) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TEXTCODE$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TEXTCODE$8);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TEXTCODE$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TEXTCODE$8);
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
                get_store().remove_element(TEXTCODE$8, 0);
            }
        }
        
        /**
         * Gets the "base-year-code" element
         */
        public java.lang.String getBaseYearCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BASEYEARCODE$10, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "base-year-code" element
         */
        public org.apache.xmlbeans.XmlString xgetBaseYearCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(BASEYEARCODE$10, 0);
                return target;
            }
        }
        
        /**
         * True if has "base-year-code" element
         */
        public boolean isSetBaseYearCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(BASEYEARCODE$10) != 0;
            }
        }
        
        /**
         * Sets the "base-year-code" element
         */
        public void setBaseYearCode(java.lang.String baseYearCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BASEYEARCODE$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(BASEYEARCODE$10);
                }
                target.setStringValue(baseYearCode);
            }
        }
        
        /**
         * Sets (as xml) the "base-year-code" element
         */
        public void xsetBaseYearCode(org.apache.xmlbeans.XmlString baseYearCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(BASEYEARCODE$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(BASEYEARCODE$10);
                }
                target.set(baseYearCode);
            }
        }
        
        /**
         * Unsets the "base-year-code" element
         */
        public void unsetBaseYearCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(BASEYEARCODE$10, 0);
            }
        }
        
        /**
         * Gets the "report-year-code" element
         */
        public java.lang.String getReportYearCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(REPORTYEARCODE$12, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "report-year-code" element
         */
        public org.apache.xmlbeans.XmlString xgetReportYearCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(REPORTYEARCODE$12, 0);
                return target;
            }
        }
        
        /**
         * True if has "report-year-code" element
         */
        public boolean isSetReportYearCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(REPORTYEARCODE$12) != 0;
            }
        }
        
        /**
         * Sets the "report-year-code" element
         */
        public void setReportYearCode(java.lang.String reportYearCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(REPORTYEARCODE$12, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(REPORTYEARCODE$12);
                }
                target.setStringValue(reportYearCode);
            }
        }
        
        /**
         * Sets (as xml) the "report-year-code" element
         */
        public void xsetReportYearCode(org.apache.xmlbeans.XmlString reportYearCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(REPORTYEARCODE$12, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(REPORTYEARCODE$12);
                }
                target.set(reportYearCode);
            }
        }
        
        /**
         * Unsets the "report-year-code" element
         */
        public void unsetReportYearCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(REPORTYEARCODE$12, 0);
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ALLOWDATACHANGES$14, 0);
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
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(ALLOWDATACHANGES$14, 0);
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
                return get_store().count_elements(ALLOWDATACHANGES$14) != 0;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ALLOWDATACHANGES$14, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ALLOWDATACHANGES$14);
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
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(ALLOWDATACHANGES$14, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(ALLOWDATACHANGES$14);
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
                get_store().remove_element(ALLOWDATACHANGES$14, 0);
            }
        }
        
        /**
         * Gets the "model-family-code" element
         */
        public java.lang.String getModelFamilyCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MODELFAMILYCODE$16, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "model-family-code" element
         */
        public org.apache.xmlbeans.XmlString xgetModelFamilyCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(MODELFAMILYCODE$16, 0);
                return target;
            }
        }
        
        /**
         * True if has "model-family-code" element
         */
        public boolean isSetModelFamilyCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(MODELFAMILYCODE$16) != 0;
            }
        }
        
        /**
         * Sets the "model-family-code" element
         */
        public void setModelFamilyCode(java.lang.String modelFamilyCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MODELFAMILYCODE$16, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(MODELFAMILYCODE$16);
                }
                target.setStringValue(modelFamilyCode);
            }
        }
        
        /**
         * Sets (as xml) the "model-family-code" element
         */
        public void xsetModelFamilyCode(org.apache.xmlbeans.XmlString modelFamilyCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(MODELFAMILYCODE$16, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(MODELFAMILYCODE$16);
                }
                target.set(modelFamilyCode);
            }
        }
        
        /**
         * Unsets the "model-family-code" element
         */
        public void unsetModelFamilyCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(MODELFAMILYCODE$16, 0);
            }
        }
        
        /**
         * Gets the "model-family-parent" element
         */
        public boolean getModelFamilyParent()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MODELFAMILYPARENT$18, 0);
                if (target == null)
                {
                    return false;
                }
                return target.getBooleanValue();
            }
        }
        
        /**
         * Gets (as xml) the "model-family-parent" element
         */
        public org.apache.xmlbeans.XmlBoolean xgetModelFamilyParent()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(MODELFAMILYPARENT$18, 0);
                return target;
            }
        }
        
        /**
         * Sets the "model-family-parent" element
         */
        public void setModelFamilyParent(boolean modelFamilyParent)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MODELFAMILYPARENT$18, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(MODELFAMILYPARENT$18);
                }
                target.setBooleanValue(modelFamilyParent);
            }
        }
        
        /**
         * Sets (as xml) the "model-family-parent" element
         */
        public void xsetModelFamilyParent(org.apache.xmlbeans.XmlBoolean modelFamilyParent)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(MODELFAMILYPARENT$18, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(MODELFAMILYPARENT$18);
                }
                target.set(modelFamilyParent);
            }
        }
        
        /**
         * Gets the "display-order" element
         */
        public java.math.BigInteger getDisplayOrder()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DISPLAYORDER$20, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getBigIntegerValue();
            }
        }
        
        /**
         * Gets (as xml) the "display-order" element
         */
        public org.apache.xmlbeans.XmlInteger xgetDisplayOrder()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlInteger target = null;
                target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(DISPLAYORDER$20, 0);
                return target;
            }
        }
        
        /**
         * Sets the "display-order" element
         */
        public void setDisplayOrder(java.math.BigInteger displayOrder)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DISPLAYORDER$20, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DISPLAYORDER$20);
                }
                target.setBigIntegerValue(displayOrder);
            }
        }
        
        /**
         * Sets (as xml) the "display-order" element
         */
        public void xsetDisplayOrder(org.apache.xmlbeans.XmlInteger displayOrder)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlInteger target = null;
                target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(DISPLAYORDER$20, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlInteger)get_store().add_element_user(DISPLAYORDER$20);
                }
                target.set(displayOrder);
            }
        }
        
        /**
         * Gets the "branch-tag" element
         */
        public java.lang.String getBranchTag()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BRANCHTAG$22, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "branch-tag" element
         */
        public org.apache.xmlbeans.XmlString xgetBranchTag()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(BRANCHTAG$22, 0);
                return target;
            }
        }
        
        /**
         * True if has "branch-tag" element
         */
        public boolean isSetBranchTag()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(BRANCHTAG$22) != 0;
            }
        }
        
        /**
         * Sets the "branch-tag" element
         */
        public void setBranchTag(java.lang.String branchTag)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BRANCHTAG$22, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(BRANCHTAG$22);
                }
                target.setStringValue(branchTag);
            }
        }
        
        /**
         * Sets (as xml) the "branch-tag" element
         */
        public void xsetBranchTag(org.apache.xmlbeans.XmlString branchTag)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(BRANCHTAG$22, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(BRANCHTAG$22);
                }
                target.set(branchTag);
            }
        }
        
        /**
         * Unsets the "branch-tag" element
         */
        public void unsetBranchTag()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(BRANCHTAG$22, 0);
            }
        }
        
        /**
         * Gets the "run-code" element
         */
        public java.lang.String getRunCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(RUNCODE$24, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "run-code" element
         */
        public org.apache.xmlbeans.XmlString xgetRunCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(RUNCODE$24, 0);
                return target;
            }
        }
        
        /**
         * True if has "run-code" element
         */
        public boolean isSetRunCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(RUNCODE$24) != 0;
            }
        }
        
        /**
         * Sets the "run-code" element
         */
        public void setRunCode(java.lang.String runCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(RUNCODE$24, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(RUNCODE$24);
                }
                target.setStringValue(runCode);
            }
        }
        
        /**
         * Sets (as xml) the "run-code" element
         */
        public void xsetRunCode(org.apache.xmlbeans.XmlString runCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(RUNCODE$24, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(RUNCODE$24);
                }
                target.set(runCode);
            }
        }
        
        /**
         * Unsets the "run-code" element
         */
        public void unsetRunCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(RUNCODE$24, 0);
            }
        }
    }
}
