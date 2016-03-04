/*
 * An XML document type.
 * Localname: heading
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.HeadingDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one heading(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class HeadingDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.HeadingDocument
{
    private static final long serialVersionUID = 1L;
    
    public HeadingDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName HEADING$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "heading");
    
    
    /**
     * Gets the "heading" element
     */
    public uk.gov.ofwat.model2.HeadingDocument.Heading getHeading()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.HeadingDocument.Heading target = null;
            target = (uk.gov.ofwat.model2.HeadingDocument.Heading)get_store().find_element_user(HEADING$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "heading" element
     */
    public void setHeading(uk.gov.ofwat.model2.HeadingDocument.Heading heading)
    {
        generatedSetterHelperImpl(heading, HEADING$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "heading" element
     */
    public uk.gov.ofwat.model2.HeadingDocument.Heading addNewHeading()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.HeadingDocument.Heading target = null;
            target = (uk.gov.ofwat.model2.HeadingDocument.Heading)get_store().add_element_user(HEADING$0);
            return target;
        }
    }
    /**
     * An XML heading(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class HeadingImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.HeadingDocument.Heading
    {
        private static final long serialVersionUID = 1L;
        
        public HeadingImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName CODE$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "code");
        private static final javax.xml.namespace.QName DESCRIPTION$2 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "description");
        private static final javax.xml.namespace.QName PARENT$4 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "parent");
        private static final javax.xml.namespace.QName NOTES$6 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "notes");
        private static final javax.xml.namespace.QName ANNOTATION$8 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "annotation");
        
        
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
         * Gets the "description" element
         */
        public java.lang.String getDescription()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DESCRIPTION$2, 0);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DESCRIPTION$2, 0);
                return target;
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
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DESCRIPTION$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DESCRIPTION$2);
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
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DESCRIPTION$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(DESCRIPTION$2);
                }
                target.set(description);
            }
        }
        
        /**
         * Gets the "parent" element
         */
        public java.lang.String getParent()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PARENT$4, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "parent" element
         */
        public org.apache.xmlbeans.XmlString xgetParent()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PARENT$4, 0);
                return target;
            }
        }
        
        /**
         * True if has "parent" element
         */
        public boolean isSetParent()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(PARENT$4) != 0;
            }
        }
        
        /**
         * Sets the "parent" element
         */
        public void setParent(java.lang.String parent)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PARENT$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PARENT$4);
                }
                target.setStringValue(parent);
            }
        }
        
        /**
         * Sets (as xml) the "parent" element
         */
        public void xsetParent(org.apache.xmlbeans.XmlString parent)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PARENT$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PARENT$4);
                }
                target.set(parent);
            }
        }
        
        /**
         * Unsets the "parent" element
         */
        public void unsetParent()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(PARENT$4, 0);
            }
        }
        
        /**
         * Gets the "notes" element
         */
        public java.lang.String getNotes()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NOTES$6, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "notes" element
         */
        public org.apache.xmlbeans.XmlString xgetNotes()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NOTES$6, 0);
                return target;
            }
        }
        
        /**
         * True if has "notes" element
         */
        public boolean isSetNotes()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(NOTES$6) != 0;
            }
        }
        
        /**
         * Sets the "notes" element
         */
        public void setNotes(java.lang.String notes)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NOTES$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(NOTES$6);
                }
                target.setStringValue(notes);
            }
        }
        
        /**
         * Sets (as xml) the "notes" element
         */
        public void xsetNotes(org.apache.xmlbeans.XmlString notes)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NOTES$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(NOTES$6);
                }
                target.set(notes);
            }
        }
        
        /**
         * Unsets the "notes" element
         */
        public void unsetNotes()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(NOTES$6, 0);
            }
        }
        
        /**
         * Gets the "annotation" element
         */
        public java.lang.String getAnnotation()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ANNOTATION$8, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "annotation" element
         */
        public org.apache.xmlbeans.XmlString xgetAnnotation()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ANNOTATION$8, 0);
                return target;
            }
        }
        
        /**
         * True if has "annotation" element
         */
        public boolean isSetAnnotation()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(ANNOTATION$8) != 0;
            }
        }
        
        /**
         * Sets the "annotation" element
         */
        public void setAnnotation(java.lang.String annotation)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ANNOTATION$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ANNOTATION$8);
                }
                target.setStringValue(annotation);
            }
        }
        
        /**
         * Sets (as xml) the "annotation" element
         */
        public void xsetAnnotation(org.apache.xmlbeans.XmlString annotation)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ANNOTATION$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(ANNOTATION$8);
                }
                target.set(annotation);
            }
        }
        
        /**
         * Unsets the "annotation" element
         */
        public void unsetAnnotation()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(ANNOTATION$8, 0);
            }
        }
    }
}
