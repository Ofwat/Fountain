/*
 * An XML document type.
 * Localname: transfer-block-details
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.TransferBlockDetailsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2;


/**
 * A document containing one transfer-block-details(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public interface TransferBlockDetailsDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(TransferBlockDetailsDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("transferblockdetails9305doctype");
    
    /**
     * Gets the "transfer-block-details" element
     */
    uk.gov.ofwat.model2.TransferBlockDetailsDocument.TransferBlockDetails getTransferBlockDetails();
    
    /**
     * Sets the "transfer-block-details" element
     */
    void setTransferBlockDetails(uk.gov.ofwat.model2.TransferBlockDetailsDocument.TransferBlockDetails transferBlockDetails);
    
    /**
     * Appends and returns a new empty "transfer-block-details" element
     */
    uk.gov.ofwat.model2.TransferBlockDetailsDocument.TransferBlockDetails addNewTransferBlockDetails();
    
    /**
     * An XML transfer-block-details(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public interface TransferBlockDetails extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(TransferBlockDetails.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("transferblockdetailse441elemtype");
        
        /**
         * Gets the "from-model-code" element
         */
        java.lang.String getFromModelCode();
        
        /**
         * Gets (as xml) the "from-model-code" element
         */
        org.apache.xmlbeans.XmlString xgetFromModelCode();
        
        /**
         * True if has "from-model-code" element
         */
        boolean isSetFromModelCode();
        
        /**
         * Sets the "from-model-code" element
         */
        void setFromModelCode(java.lang.String fromModelCode);
        
        /**
         * Sets (as xml) the "from-model-code" element
         */
        void xsetFromModelCode(org.apache.xmlbeans.XmlString fromModelCode);
        
        /**
         * Unsets the "from-model-code" element
         */
        void unsetFromModelCode();
        
        /**
         * Gets the "from-version-code" element
         */
        java.lang.String getFromVersionCode();
        
        /**
         * Gets (as xml) the "from-version-code" element
         */
        org.apache.xmlbeans.XmlString xgetFromVersionCode();
        
        /**
         * True if has "from-version-code" element
         */
        boolean isSetFromVersionCode();
        
        /**
         * Sets the "from-version-code" element
         */
        void setFromVersionCode(java.lang.String fromVersionCode);
        
        /**
         * Sets (as xml) the "from-version-code" element
         */
        void xsetFromVersionCode(org.apache.xmlbeans.XmlString fromVersionCode);
        
        /**
         * Unsets the "from-version-code" element
         */
        void unsetFromVersionCode();
        
        /**
         * Gets the "from-page-code" element
         */
        java.lang.String getFromPageCode();
        
        /**
         * Gets (as xml) the "from-page-code" element
         */
        org.apache.xmlbeans.XmlString xgetFromPageCode();
        
        /**
         * True if has "from-page-code" element
         */
        boolean isSetFromPageCode();
        
        /**
         * Sets the "from-page-code" element
         */
        void setFromPageCode(java.lang.String fromPageCode);
        
        /**
         * Sets (as xml) the "from-page-code" element
         */
        void xsetFromPageCode(org.apache.xmlbeans.XmlString fromPageCode);
        
        /**
         * Unsets the "from-page-code" element
         */
        void unsetFromPageCode();
        
        /**
         * Gets the "to-model-code" element
         */
        java.lang.String getToModelCode();
        
        /**
         * Gets (as xml) the "to-model-code" element
         */
        org.apache.xmlbeans.XmlString xgetToModelCode();
        
        /**
         * True if has "to-model-code" element
         */
        boolean isSetToModelCode();
        
        /**
         * Sets the "to-model-code" element
         */
        void setToModelCode(java.lang.String toModelCode);
        
        /**
         * Sets (as xml) the "to-model-code" element
         */
        void xsetToModelCode(org.apache.xmlbeans.XmlString toModelCode);
        
        /**
         * Unsets the "to-model-code" element
         */
        void unsetToModelCode();
        
        /**
         * Gets the "to-version-code" element
         */
        java.lang.String getToVersionCode();
        
        /**
         * Gets (as xml) the "to-version-code" element
         */
        org.apache.xmlbeans.XmlString xgetToVersionCode();
        
        /**
         * True if has "to-version-code" element
         */
        boolean isSetToVersionCode();
        
        /**
         * Sets the "to-version-code" element
         */
        void setToVersionCode(java.lang.String toVersionCode);
        
        /**
         * Sets (as xml) the "to-version-code" element
         */
        void xsetToVersionCode(org.apache.xmlbeans.XmlString toVersionCode);
        
        /**
         * Unsets the "to-version-code" element
         */
        void unsetToVersionCode();
        
        /**
         * Gets the "to-page-code" element
         */
        java.lang.String getToPageCode();
        
        /**
         * Gets (as xml) the "to-page-code" element
         */
        org.apache.xmlbeans.XmlString xgetToPageCode();
        
        /**
         * True if has "to-page-code" element
         */
        boolean isSetToPageCode();
        
        /**
         * Sets the "to-page-code" element
         */
        void setToPageCode(java.lang.String toPageCode);
        
        /**
         * Sets (as xml) the "to-page-code" element
         */
        void xsetToPageCode(org.apache.xmlbeans.XmlString toPageCode);
        
        /**
         * Unsets the "to-page-code" element
         */
        void unsetToPageCode();
        
        /**
         * Gets the "to-macro-code" element
         */
        java.lang.String getToMacroCode();
        
        /**
         * Gets (as xml) the "to-macro-code" element
         */
        org.apache.xmlbeans.XmlString xgetToMacroCode();
        
        /**
         * True if has "to-macro-code" element
         */
        boolean isSetToMacroCode();
        
        /**
         * Sets the "to-macro-code" element
         */
        void setToMacroCode(java.lang.String toMacroCode);
        
        /**
         * Sets (as xml) the "to-macro-code" element
         */
        void xsetToMacroCode(org.apache.xmlbeans.XmlString toMacroCode);
        
        /**
         * Unsets the "to-macro-code" element
         */
        void unsetToMacroCode();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.gov.ofwat.model2.TransferBlockDetailsDocument.TransferBlockDetails newInstance() {
              return (uk.gov.ofwat.model2.TransferBlockDetailsDocument.TransferBlockDetails) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.gov.ofwat.model2.TransferBlockDetailsDocument.TransferBlockDetails newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.gov.ofwat.model2.TransferBlockDetailsDocument.TransferBlockDetails) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.gov.ofwat.model2.TransferBlockDetailsDocument newInstance() {
          return (uk.gov.ofwat.model2.TransferBlockDetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.gov.ofwat.model2.TransferBlockDetailsDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.gov.ofwat.model2.TransferBlockDetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.gov.ofwat.model2.TransferBlockDetailsDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.TransferBlockDetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.gov.ofwat.model2.TransferBlockDetailsDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.TransferBlockDetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.gov.ofwat.model2.TransferBlockDetailsDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TransferBlockDetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.gov.ofwat.model2.TransferBlockDetailsDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TransferBlockDetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.gov.ofwat.model2.TransferBlockDetailsDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TransferBlockDetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.gov.ofwat.model2.TransferBlockDetailsDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TransferBlockDetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.gov.ofwat.model2.TransferBlockDetailsDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TransferBlockDetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.gov.ofwat.model2.TransferBlockDetailsDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TransferBlockDetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.gov.ofwat.model2.TransferBlockDetailsDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TransferBlockDetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.gov.ofwat.model2.TransferBlockDetailsDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TransferBlockDetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.gov.ofwat.model2.TransferBlockDetailsDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.TransferBlockDetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.gov.ofwat.model2.TransferBlockDetailsDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.TransferBlockDetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.gov.ofwat.model2.TransferBlockDetailsDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.TransferBlockDetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.gov.ofwat.model2.TransferBlockDetailsDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.TransferBlockDetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.TransferBlockDetailsDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.TransferBlockDetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.TransferBlockDetailsDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.TransferBlockDetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
