/*
 * An XML document type.
 * Localname: macros
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.MacrosDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one macros(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class MacrosDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.MacrosDocument
{
    private static final long serialVersionUID = 1L;
    
    public MacrosDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName MACROS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "macros");
    
    
    /**
     * Gets the "macros" element
     */
    public uk.gov.ofwat.model2.MacrosDocument.Macros getMacros()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.MacrosDocument.Macros target = null;
            target = (uk.gov.ofwat.model2.MacrosDocument.Macros)get_store().find_element_user(MACROS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "macros" element
     */
    public void setMacros(uk.gov.ofwat.model2.MacrosDocument.Macros macros)
    {
        generatedSetterHelperImpl(macros, MACROS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "macros" element
     */
    public uk.gov.ofwat.model2.MacrosDocument.Macros addNewMacros()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.MacrosDocument.Macros target = null;
            target = (uk.gov.ofwat.model2.MacrosDocument.Macros)get_store().add_element_user(MACROS$0);
            return target;
        }
    }
    /**
     * An XML macros(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class MacrosImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.MacrosDocument.Macros
    {
        private static final long serialVersionUID = 1L;
        
        public MacrosImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName MACRO$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "macro");
        
        
        /**
         * Gets array of all "macro" elements
         */
        public uk.gov.ofwat.model2.MacroDocument.Macro[] getMacroArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(MACRO$0, targetList);
                uk.gov.ofwat.model2.MacroDocument.Macro[] result = new uk.gov.ofwat.model2.MacroDocument.Macro[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "macro" element
         */
        public uk.gov.ofwat.model2.MacroDocument.Macro getMacroArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.MacroDocument.Macro target = null;
                target = (uk.gov.ofwat.model2.MacroDocument.Macro)get_store().find_element_user(MACRO$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "macro" element
         */
        public int sizeOfMacroArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(MACRO$0);
            }
        }
        
        /**
         * Sets array of all "macro" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setMacroArray(uk.gov.ofwat.model2.MacroDocument.Macro[] macroArray)
        {
            check_orphaned();
            arraySetterHelper(macroArray, MACRO$0);
        }
        
        /**
         * Sets ith "macro" element
         */
        public void setMacroArray(int i, uk.gov.ofwat.model2.MacroDocument.Macro macro)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.MacroDocument.Macro target = null;
                target = (uk.gov.ofwat.model2.MacroDocument.Macro)get_store().find_element_user(MACRO$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(macro);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "macro" element
         */
        public uk.gov.ofwat.model2.MacroDocument.Macro insertNewMacro(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.MacroDocument.Macro target = null;
                target = (uk.gov.ofwat.model2.MacroDocument.Macro)get_store().insert_element_user(MACRO$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "macro" element
         */
        public uk.gov.ofwat.model2.MacroDocument.Macro addNewMacro()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.MacroDocument.Macro target = null;
                target = (uk.gov.ofwat.model2.MacroDocument.Macro)get_store().add_element_user(MACRO$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "macro" element
         */
        public void removeMacro(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(MACRO$0, i);
            }
        }
    }
}
