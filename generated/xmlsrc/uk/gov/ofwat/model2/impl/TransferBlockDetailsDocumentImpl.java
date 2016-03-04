/*
 * An XML document type.
 * Localname: transfer-block-details
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.TransferBlockDetailsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one transfer-block-details(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class TransferBlockDetailsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TransferBlockDetailsDocument
{
    private static final long serialVersionUID = 1L;
    
    public TransferBlockDetailsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TRANSFERBLOCKDETAILS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "transfer-block-details");
    
    
    /**
     * Gets the "transfer-block-details" element
     */
    public uk.gov.ofwat.model2.TransferBlockDetailsDocument.TransferBlockDetails getTransferBlockDetails()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.TransferBlockDetailsDocument.TransferBlockDetails target = null;
            target = (uk.gov.ofwat.model2.TransferBlockDetailsDocument.TransferBlockDetails)get_store().find_element_user(TRANSFERBLOCKDETAILS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "transfer-block-details" element
     */
    public void setTransferBlockDetails(uk.gov.ofwat.model2.TransferBlockDetailsDocument.TransferBlockDetails transferBlockDetails)
    {
        generatedSetterHelperImpl(transferBlockDetails, TRANSFERBLOCKDETAILS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "transfer-block-details" element
     */
    public uk.gov.ofwat.model2.TransferBlockDetailsDocument.TransferBlockDetails addNewTransferBlockDetails()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.TransferBlockDetailsDocument.TransferBlockDetails target = null;
            target = (uk.gov.ofwat.model2.TransferBlockDetailsDocument.TransferBlockDetails)get_store().add_element_user(TRANSFERBLOCKDETAILS$0);
            return target;
        }
    }
    /**
     * An XML transfer-block-details(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class TransferBlockDetailsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.TransferBlockDetailsDocument.TransferBlockDetails
    {
        private static final long serialVersionUID = 1L;
        
        public TransferBlockDetailsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName FROMMODELCODE$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "from-model-code");
        private static final javax.xml.namespace.QName FROMVERSIONCODE$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "from-version-code");
        private static final javax.xml.namespace.QName FROMPAGECODE$4 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "from-page-code");
        private static final javax.xml.namespace.QName TOMODELCODE$6 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "to-model-code");
        private static final javax.xml.namespace.QName TOVERSIONCODE$8 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "to-version-code");
        private static final javax.xml.namespace.QName TOPAGECODE$10 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "to-page-code");
        private static final javax.xml.namespace.QName TOMACROCODE$12 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "to-macro-code");
        
        
        /**
         * Gets the "from-model-code" element
         */
        public java.lang.String getFromModelCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FROMMODELCODE$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "from-model-code" element
         */
        public org.apache.xmlbeans.XmlString xgetFromModelCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(FROMMODELCODE$0, 0);
                return target;
            }
        }
        
        /**
         * True if has "from-model-code" element
         */
        public boolean isSetFromModelCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(FROMMODELCODE$0) != 0;
            }
        }
        
        /**
         * Sets the "from-model-code" element
         */
        public void setFromModelCode(java.lang.String fromModelCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FROMMODELCODE$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(FROMMODELCODE$0);
                }
                target.setStringValue(fromModelCode);
            }
        }
        
        /**
         * Sets (as xml) the "from-model-code" element
         */
        public void xsetFromModelCode(org.apache.xmlbeans.XmlString fromModelCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(FROMMODELCODE$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(FROMMODELCODE$0);
                }
                target.set(fromModelCode);
            }
        }
        
        /**
         * Unsets the "from-model-code" element
         */
        public void unsetFromModelCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(FROMMODELCODE$0, 0);
            }
        }
        
        /**
         * Gets the "from-version-code" element
         */
        public java.lang.String getFromVersionCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FROMVERSIONCODE$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "from-version-code" element
         */
        public org.apache.xmlbeans.XmlString xgetFromVersionCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(FROMVERSIONCODE$2, 0);
                return target;
            }
        }
        
        /**
         * True if has "from-version-code" element
         */
        public boolean isSetFromVersionCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(FROMVERSIONCODE$2) != 0;
            }
        }
        
        /**
         * Sets the "from-version-code" element
         */
        public void setFromVersionCode(java.lang.String fromVersionCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FROMVERSIONCODE$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(FROMVERSIONCODE$2);
                }
                target.setStringValue(fromVersionCode);
            }
        }
        
        /**
         * Sets (as xml) the "from-version-code" element
         */
        public void xsetFromVersionCode(org.apache.xmlbeans.XmlString fromVersionCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(FROMVERSIONCODE$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(FROMVERSIONCODE$2);
                }
                target.set(fromVersionCode);
            }
        }
        
        /**
         * Unsets the "from-version-code" element
         */
        public void unsetFromVersionCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(FROMVERSIONCODE$2, 0);
            }
        }
        
        /**
         * Gets the "from-page-code" element
         */
        public java.lang.String getFromPageCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FROMPAGECODE$4, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "from-page-code" element
         */
        public org.apache.xmlbeans.XmlString xgetFromPageCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(FROMPAGECODE$4, 0);
                return target;
            }
        }
        
        /**
         * True if has "from-page-code" element
         */
        public boolean isSetFromPageCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(FROMPAGECODE$4) != 0;
            }
        }
        
        /**
         * Sets the "from-page-code" element
         */
        public void setFromPageCode(java.lang.String fromPageCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FROMPAGECODE$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(FROMPAGECODE$4);
                }
                target.setStringValue(fromPageCode);
            }
        }
        
        /**
         * Sets (as xml) the "from-page-code" element
         */
        public void xsetFromPageCode(org.apache.xmlbeans.XmlString fromPageCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(FROMPAGECODE$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(FROMPAGECODE$4);
                }
                target.set(fromPageCode);
            }
        }
        
        /**
         * Unsets the "from-page-code" element
         */
        public void unsetFromPageCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(FROMPAGECODE$4, 0);
            }
        }
        
        /**
         * Gets the "to-model-code" element
         */
        public java.lang.String getToModelCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TOMODELCODE$6, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "to-model-code" element
         */
        public org.apache.xmlbeans.XmlString xgetToModelCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TOMODELCODE$6, 0);
                return target;
            }
        }
        
        /**
         * True if has "to-model-code" element
         */
        public boolean isSetToModelCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TOMODELCODE$6) != 0;
            }
        }
        
        /**
         * Sets the "to-model-code" element
         */
        public void setToModelCode(java.lang.String toModelCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TOMODELCODE$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TOMODELCODE$6);
                }
                target.setStringValue(toModelCode);
            }
        }
        
        /**
         * Sets (as xml) the "to-model-code" element
         */
        public void xsetToModelCode(org.apache.xmlbeans.XmlString toModelCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TOMODELCODE$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TOMODELCODE$6);
                }
                target.set(toModelCode);
            }
        }
        
        /**
         * Unsets the "to-model-code" element
         */
        public void unsetToModelCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TOMODELCODE$6, 0);
            }
        }
        
        /**
         * Gets the "to-version-code" element
         */
        public java.lang.String getToVersionCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TOVERSIONCODE$8, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "to-version-code" element
         */
        public org.apache.xmlbeans.XmlString xgetToVersionCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TOVERSIONCODE$8, 0);
                return target;
            }
        }
        
        /**
         * True if has "to-version-code" element
         */
        public boolean isSetToVersionCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TOVERSIONCODE$8) != 0;
            }
        }
        
        /**
         * Sets the "to-version-code" element
         */
        public void setToVersionCode(java.lang.String toVersionCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TOVERSIONCODE$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TOVERSIONCODE$8);
                }
                target.setStringValue(toVersionCode);
            }
        }
        
        /**
         * Sets (as xml) the "to-version-code" element
         */
        public void xsetToVersionCode(org.apache.xmlbeans.XmlString toVersionCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TOVERSIONCODE$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TOVERSIONCODE$8);
                }
                target.set(toVersionCode);
            }
        }
        
        /**
         * Unsets the "to-version-code" element
         */
        public void unsetToVersionCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TOVERSIONCODE$8, 0);
            }
        }
        
        /**
         * Gets the "to-page-code" element
         */
        public java.lang.String getToPageCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TOPAGECODE$10, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "to-page-code" element
         */
        public org.apache.xmlbeans.XmlString xgetToPageCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TOPAGECODE$10, 0);
                return target;
            }
        }
        
        /**
         * True if has "to-page-code" element
         */
        public boolean isSetToPageCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TOPAGECODE$10) != 0;
            }
        }
        
        /**
         * Sets the "to-page-code" element
         */
        public void setToPageCode(java.lang.String toPageCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TOPAGECODE$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TOPAGECODE$10);
                }
                target.setStringValue(toPageCode);
            }
        }
        
        /**
         * Sets (as xml) the "to-page-code" element
         */
        public void xsetToPageCode(org.apache.xmlbeans.XmlString toPageCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TOPAGECODE$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TOPAGECODE$10);
                }
                target.set(toPageCode);
            }
        }
        
        /**
         * Unsets the "to-page-code" element
         */
        public void unsetToPageCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TOPAGECODE$10, 0);
            }
        }
        
        /**
         * Gets the "to-macro-code" element
         */
        public java.lang.String getToMacroCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TOMACROCODE$12, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "to-macro-code" element
         */
        public org.apache.xmlbeans.XmlString xgetToMacroCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TOMACROCODE$12, 0);
                return target;
            }
        }
        
        /**
         * True if has "to-macro-code" element
         */
        public boolean isSetToMacroCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TOMACROCODE$12) != 0;
            }
        }
        
        /**
         * Sets the "to-macro-code" element
         */
        public void setToMacroCode(java.lang.String toMacroCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TOMACROCODE$12, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TOMACROCODE$12);
                }
                target.setStringValue(toMacroCode);
            }
        }
        
        /**
         * Sets (as xml) the "to-macro-code" element
         */
        public void xsetToMacroCode(org.apache.xmlbeans.XmlString toMacroCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TOMACROCODE$12, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TOMACROCODE$12);
                }
                target.set(toMacroCode);
            }
        }
        
        /**
         * Unsets the "to-macro-code" element
         */
        public void unsetToMacroCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TOMACROCODE$12, 0);
            }
        }
    }
}
