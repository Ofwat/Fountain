/*
 * An XML document type.
 * Localname: lines
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.LinesDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one lines(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class LinesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.LinesDocument
{
    private static final long serialVersionUID = 1L;
    
    public LinesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName LINES$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "lines");
    
    
    /**
     * Gets the "lines" element
     */
    public uk.gov.ofwat.model2.LinesDocument.Lines getLines()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.LinesDocument.Lines target = null;
            target = (uk.gov.ofwat.model2.LinesDocument.Lines)get_store().find_element_user(LINES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "lines" element
     */
    public void setLines(uk.gov.ofwat.model2.LinesDocument.Lines lines)
    {
        generatedSetterHelperImpl(lines, LINES$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "lines" element
     */
    public uk.gov.ofwat.model2.LinesDocument.Lines addNewLines()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.LinesDocument.Lines target = null;
            target = (uk.gov.ofwat.model2.LinesDocument.Lines)get_store().add_element_user(LINES$0);
            return target;
        }
    }
    /**
     * An XML lines(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class LinesImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.LinesDocument.Lines
    {
        private static final long serialVersionUID = 1L;
        
        public LinesImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName LINE$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "line");
        
        
        /**
         * Gets array of all "line" elements
         */
        public uk.gov.ofwat.model2.LineDocument.Line[] getLineArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(LINE$0, targetList);
                uk.gov.ofwat.model2.LineDocument.Line[] result = new uk.gov.ofwat.model2.LineDocument.Line[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "line" element
         */
        public uk.gov.ofwat.model2.LineDocument.Line getLineArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.LineDocument.Line target = null;
                target = (uk.gov.ofwat.model2.LineDocument.Line)get_store().find_element_user(LINE$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "line" element
         */
        public int sizeOfLineArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(LINE$0);
            }
        }
        
        /**
         * Sets array of all "line" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setLineArray(uk.gov.ofwat.model2.LineDocument.Line[] lineArray)
        {
            check_orphaned();
            arraySetterHelper(lineArray, LINE$0);
        }
        
        /**
         * Sets ith "line" element
         */
        public void setLineArray(int i, uk.gov.ofwat.model2.LineDocument.Line line)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.LineDocument.Line target = null;
                target = (uk.gov.ofwat.model2.LineDocument.Line)get_store().find_element_user(LINE$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(line);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "line" element
         */
        public uk.gov.ofwat.model2.LineDocument.Line insertNewLine(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.LineDocument.Line target = null;
                target = (uk.gov.ofwat.model2.LineDocument.Line)get_store().insert_element_user(LINE$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "line" element
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
         * Removes the ith "line" element
         */
        public void removeLine(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(LINE$0, i);
            }
        }
    }
}
