/*
 * An XML document type.
 * Localname: transfer-block-item
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.TransferBlockItemDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2;


/**
 * A document containing one transfer-block-item(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public interface TransferBlockItemDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(TransferBlockItemDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("transferblockiteme2a2doctype");
    
    /**
     * Gets the "transfer-block-item" element
     */
    uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem getTransferBlockItem();
    
    /**
     * Sets the "transfer-block-item" element
     */
    void setTransferBlockItem(uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem transferBlockItem);
    
    /**
     * Appends and returns a new empty "transfer-block-item" element
     */
    uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem addNewTransferBlockItem();
    
    /**
     * An XML transfer-block-item(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public interface TransferBlockItem extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(TransferBlockItem.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("transferblockitem0161elemtype");
        
        /**
         * Gets the "item-code" element
         */
        java.lang.String getItemCode();
        
        /**
         * Gets (as xml) the "item-code" element
         */
        org.apache.xmlbeans.XmlString xgetItemCode();
        
        /**
         * Sets the "item-code" element
         */
        void setItemCode(java.lang.String itemCode);
        
        /**
         * Sets (as xml) the "item-code" element
         */
        void xsetItemCode(org.apache.xmlbeans.XmlString itemCode);
        
        /**
         * Gets the "company-type" element
         */
        java.lang.String getCompanyType();
        
        /**
         * Gets (as xml) the "company-type" element
         */
        org.apache.xmlbeans.XmlString xgetCompanyType();
        
        /**
         * True if has "company-type" element
         */
        boolean isSetCompanyType();
        
        /**
         * Sets the "company-type" element
         */
        void setCompanyType(java.lang.String companyType);
        
        /**
         * Sets (as xml) the "company-type" element
         */
        void xsetCompanyType(org.apache.xmlbeans.XmlString companyType);
        
        /**
         * Unsets the "company-type" element
         */
        void unsetCompanyType();
        
        /**
         * Gets array of all "year-code" elements
         */
        java.lang.String[] getYearCodeArray();
        
        /**
         * Gets ith "year-code" element
         */
        java.lang.String getYearCodeArray(int i);
        
        /**
         * Gets (as xml) array of all "year-code" elements
         */
        org.apache.xmlbeans.XmlString[] xgetYearCodeArray();
        
        /**
         * Gets (as xml) ith "year-code" element
         */
        org.apache.xmlbeans.XmlString xgetYearCodeArray(int i);
        
        /**
         * Returns number of "year-code" element
         */
        int sizeOfYearCodeArray();
        
        /**
         * Sets array of all "year-code" element
         */
        void setYearCodeArray(java.lang.String[] yearCodeArray);
        
        /**
         * Sets ith "year-code" element
         */
        void setYearCodeArray(int i, java.lang.String yearCode);
        
        /**
         * Sets (as xml) array of all "year-code" element
         */
        void xsetYearCodeArray(org.apache.xmlbeans.XmlString[] yearCodeArray);
        
        /**
         * Sets (as xml) ith "year-code" element
         */
        void xsetYearCodeArray(int i, org.apache.xmlbeans.XmlString yearCode);
        
        /**
         * Inserts the value as the ith "year-code" element
         */
        void insertYearCode(int i, java.lang.String yearCode);
        
        /**
         * Appends the value as the last "year-code" element
         */
        void addYearCode(java.lang.String yearCode);
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "year-code" element
         */
        org.apache.xmlbeans.XmlString insertNewYearCode(int i);
        
        /**
         * Appends and returns a new empty value (as xml) as the last "year-code" element
         */
        org.apache.xmlbeans.XmlString addNewYearCode();
        
        /**
         * Removes the ith "year-code" element
         */
        void removeYearCode(int i);
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem newInstance() {
              return (uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.gov.ofwat.model2.TransferBlockItemDocument.TransferBlockItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.gov.ofwat.model2.TransferBlockItemDocument newInstance() {
          return (uk.gov.ofwat.model2.TransferBlockItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.gov.ofwat.model2.TransferBlockItemDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.gov.ofwat.model2.TransferBlockItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.gov.ofwat.model2.TransferBlockItemDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.TransferBlockItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.gov.ofwat.model2.TransferBlockItemDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.TransferBlockItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.gov.ofwat.model2.TransferBlockItemDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TransferBlockItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.gov.ofwat.model2.TransferBlockItemDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TransferBlockItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.gov.ofwat.model2.TransferBlockItemDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TransferBlockItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.gov.ofwat.model2.TransferBlockItemDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TransferBlockItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.gov.ofwat.model2.TransferBlockItemDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TransferBlockItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.gov.ofwat.model2.TransferBlockItemDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TransferBlockItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.gov.ofwat.model2.TransferBlockItemDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TransferBlockItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.gov.ofwat.model2.TransferBlockItemDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TransferBlockItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.gov.ofwat.model2.TransferBlockItemDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.TransferBlockItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.gov.ofwat.model2.TransferBlockItemDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.TransferBlockItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.gov.ofwat.model2.TransferBlockItemDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.TransferBlockItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.gov.ofwat.model2.TransferBlockItemDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.TransferBlockItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.TransferBlockItemDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.TransferBlockItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.TransferBlockItemDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.TransferBlockItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
