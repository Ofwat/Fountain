/*
 * An XML document type.
 * Localname: forms
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.FormsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one forms(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class FormsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.FormsDocument
{
    private static final long serialVersionUID = 1L;
    
    public FormsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FORMS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "forms");
    
    
    /**
     * Gets the "forms" element
     */
    public uk.gov.ofwat.model2.FormsDocument.Forms getForms()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.FormsDocument.Forms target = null;
            target = (uk.gov.ofwat.model2.FormsDocument.Forms)get_store().find_element_user(FORMS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "forms" element
     */
    public void setForms(uk.gov.ofwat.model2.FormsDocument.Forms forms)
    {
        generatedSetterHelperImpl(forms, FORMS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "forms" element
     */
    public uk.gov.ofwat.model2.FormsDocument.Forms addNewForms()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.FormsDocument.Forms target = null;
            target = (uk.gov.ofwat.model2.FormsDocument.Forms)get_store().add_element_user(FORMS$0);
            return target;
        }
    }
    /**
     * An XML forms(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class FormsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.FormsDocument.Forms
    {
        private static final long serialVersionUID = 1L;
        
        public FormsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName FORM$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "form");
        
        
        /**
         * Gets array of all "form" elements
         */
        public uk.gov.ofwat.model2.FormDocument.Form[] getFormArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(FORM$0, targetList);
                uk.gov.ofwat.model2.FormDocument.Form[] result = new uk.gov.ofwat.model2.FormDocument.Form[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "form" element
         */
        public uk.gov.ofwat.model2.FormDocument.Form getFormArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.FormDocument.Form target = null;
                target = (uk.gov.ofwat.model2.FormDocument.Form)get_store().find_element_user(FORM$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "form" element
         */
        public int sizeOfFormArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(FORM$0);
            }
        }
        
        /**
         * Sets array of all "form" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setFormArray(uk.gov.ofwat.model2.FormDocument.Form[] formArray)
        {
            check_orphaned();
            arraySetterHelper(formArray, FORM$0);
        }
        
        /**
         * Sets ith "form" element
         */
        public void setFormArray(int i, uk.gov.ofwat.model2.FormDocument.Form form)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.FormDocument.Form target = null;
                target = (uk.gov.ofwat.model2.FormDocument.Form)get_store().find_element_user(FORM$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(form);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "form" element
         */
        public uk.gov.ofwat.model2.FormDocument.Form insertNewForm(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.FormDocument.Form target = null;
                target = (uk.gov.ofwat.model2.FormDocument.Form)get_store().insert_element_user(FORM$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "form" element
         */
        public uk.gov.ofwat.model2.FormDocument.Form addNewForm()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.FormDocument.Form target = null;
                target = (uk.gov.ofwat.model2.FormDocument.Form)get_store().add_element_user(FORM$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "form" element
         */
        public void removeForm(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(FORM$0, i);
            }
        }
    }
}
