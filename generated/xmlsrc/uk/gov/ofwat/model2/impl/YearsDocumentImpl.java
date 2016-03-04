/*
 * An XML document type.
 * Localname: years
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.YearsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2.impl;
/**
 * A document containing one years(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public class YearsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.YearsDocument
{
    private static final long serialVersionUID = 1L;
    
    public YearsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName YEARS$0 = 
        new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "years");
    
    
    /**
     * Gets the "years" element
     */
    public uk.gov.ofwat.model2.YearsDocument.Years getYears()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.YearsDocument.Years target = null;
            target = (uk.gov.ofwat.model2.YearsDocument.Years)get_store().find_element_user(YEARS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "years" element
     */
    public void setYears(uk.gov.ofwat.model2.YearsDocument.Years years)
    {
        generatedSetterHelperImpl(years, YEARS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "years" element
     */
    public uk.gov.ofwat.model2.YearsDocument.Years addNewYears()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.gov.ofwat.model2.YearsDocument.Years target = null;
            target = (uk.gov.ofwat.model2.YearsDocument.Years)get_store().add_element_user(YEARS$0);
            return target;
        }
    }
    /**
     * An XML years(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public static class YearsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.gov.ofwat.model2.YearsDocument.Years
    {
        private static final long serialVersionUID = 1L;
        
        public YearsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName YEAR$0 = 
            new javax.xml.namespace.QName("http://www.ofwat.gov.uk/model2", "year");
        
        
        /**
         * Gets array of all "year" elements
         */
        public uk.gov.ofwat.model2.YearDocument.Year[] getYearArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(YEAR$0, targetList);
                uk.gov.ofwat.model2.YearDocument.Year[] result = new uk.gov.ofwat.model2.YearDocument.Year[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "year" element
         */
        public uk.gov.ofwat.model2.YearDocument.Year getYearArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.YearDocument.Year target = null;
                target = (uk.gov.ofwat.model2.YearDocument.Year)get_store().find_element_user(YEAR$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "year" element
         */
        public int sizeOfYearArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(YEAR$0);
            }
        }
        
        /**
         * Sets array of all "year" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setYearArray(uk.gov.ofwat.model2.YearDocument.Year[] yearArray)
        {
            check_orphaned();
            arraySetterHelper(yearArray, YEAR$0);
        }
        
        /**
         * Sets ith "year" element
         */
        public void setYearArray(int i, uk.gov.ofwat.model2.YearDocument.Year year)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.YearDocument.Year target = null;
                target = (uk.gov.ofwat.model2.YearDocument.Year)get_store().find_element_user(YEAR$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(year);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "year" element
         */
        public uk.gov.ofwat.model2.YearDocument.Year insertNewYear(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.YearDocument.Year target = null;
                target = (uk.gov.ofwat.model2.YearDocument.Year)get_store().insert_element_user(YEAR$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "year" element
         */
        public uk.gov.ofwat.model2.YearDocument.Year addNewYear()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.gov.ofwat.model2.YearDocument.Year target = null;
                target = (uk.gov.ofwat.model2.YearDocument.Year)get_store().add_element_user(YEAR$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "year" element
         */
        public void removeYear(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(YEAR$0, i);
            }
        }
    }
}
