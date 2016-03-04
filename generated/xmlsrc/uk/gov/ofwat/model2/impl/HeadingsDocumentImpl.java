/*
 * An XML document type.
 * Localname: headings
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.HeadingsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one headings(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class HeadingsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.HeadingsDocument
{
    private static final long serialVersionUID = 1L;
    
    public HeadingsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName HEADINGS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "headings");
    
    
    /**
     * Gets the "headings" element
     */
    public uk.gov.ofwat.model2.HeadingsDocument.Headings getHeadings()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.HeadingsDocument.Headings target = null;
            target = (uk.gov.ofwat.model2.HeadingsDocument.Headings)get_store().find_element_user(HEADINGS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "headings" element
     */
    public void setHeadings(uk.gov.ofwat.model2.HeadingsDocument.Headings headings)
    {
        generatedSetterHelperImpl(headings, HEADINGS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "headings" element
     */
    public uk.gov.ofwat.model2.HeadingsDocument.Headings addNewHeadings()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.HeadingsDocument.Headings target = null;
            target = (uk.gov.ofwat.model2.HeadingsDocument.Headings)get_store().add_element_user(HEADINGS$0);
            return target;
        }
    }
    /**
     * An XML headings(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class HeadingsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.HeadingsDocument.Headings
    {
        private static final long serialVersionUID = 1L;
        
        public HeadingsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName HEADING$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "heading");
        
        
        /**
         * Gets array of all "heading" elements
         */
        public uk.gov.ofwat.model2.HeadingDocument.Heading[] getHeadingArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(HEADING$0, targetList);
                uk.gov.ofwat.model2.HeadingDocument.Heading[] result = new uk.gov.ofwat.model2.HeadingDocument.Heading[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "heading" element
         */
        public uk.gov.ofwat.model2.HeadingDocument.Heading getHeadingArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.HeadingDocument.Heading target = null;
                target = (uk.gov.ofwat.model2.HeadingDocument.Heading)get_store().find_element_user(HEADING$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "heading" element
         */
        public int sizeOfHeadingArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(HEADING$0);
            }
        }
        
        /**
         * Sets array of all "heading" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setHeadingArray(uk.gov.ofwat.model2.HeadingDocument.Heading[] headingArray)
        {
            check_orphaned();
            arraySetterHelper(headingArray, HEADING$0);
        }
        
        /**
         * Sets ith "heading" element
         */
        public void setHeadingArray(int i, uk.gov.ofwat.model2.HeadingDocument.Heading heading)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.HeadingDocument.Heading target = null;
                target = (uk.gov.ofwat.model2.HeadingDocument.Heading)get_store().find_element_user(HEADING$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(heading);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "heading" element
         */
        public uk.gov.ofwat.model2.HeadingDocument.Heading insertNewHeading(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.HeadingDocument.Heading target = null;
                target = (uk.gov.ofwat.model2.HeadingDocument.Heading)get_store().insert_element_user(HEADING$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "heading" element
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
         * Removes the ith "heading" element
         */
        public void removeHeading(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(HEADING$0, i);
            }
        }
    }
}
