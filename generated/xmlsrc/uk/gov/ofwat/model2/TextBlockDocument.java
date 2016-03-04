/*
 * An XML document type.
 * Localname: text-block
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.TextBlockDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2;


/**
 * A document containing one text-block(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public interface TextBlockDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(TextBlockDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("textblock5298doctype");
    
    /**
     * Gets the "text-block" element
     */
    uk.gov.ofwat.model2.TextBlockDocument.TextBlock getTextBlock();
    
    /**
     * Sets the "text-block" element
     */
    void setTextBlock(uk.gov.ofwat.model2.TextBlockDocument.TextBlock textBlock);
    
    /**
     * Appends and returns a new empty "text-block" element
     */
    uk.gov.ofwat.model2.TextBlockDocument.TextBlock addNewTextBlock();
    
    /**
     * An XML text-block(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public interface TextBlock extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(TextBlock.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("textblock5ba1elemtype");
        
        /**
         * Gets the "description" element
         */
        java.lang.String getDescription();
        
        /**
         * Gets (as xml) the "description" element
         */
        org.apache.xmlbeans.XmlString xgetDescription();
        
        /**
         * Sets the "description" element
         */
        void setDescription(java.lang.String description);
        
        /**
         * Sets (as xml) the "description" element
         */
        void xsetDescription(org.apache.xmlbeans.XmlString description);
        
        /**
         * Gets the "version-number" element
         */
        java.lang.String getVersionNumber();
        
        /**
         * Gets (as xml) the "version-number" element
         */
        org.apache.xmlbeans.XmlString xgetVersionNumber();
        
        /**
         * Sets the "version-number" element
         */
        void setVersionNumber(java.lang.String versionNumber);
        
        /**
         * Sets (as xml) the "version-number" element
         */
        void xsetVersionNumber(org.apache.xmlbeans.XmlString versionNumber);
        
        /**
         * Gets the "text-format-code" element
         */
        java.lang.String getTextFormatCode();
        
        /**
         * Gets (as xml) the "text-format-code" element
         */
        org.apache.xmlbeans.XmlString xgetTextFormatCode();
        
        /**
         * Sets the "text-format-code" element
         */
        void setTextFormatCode(java.lang.String textFormatCode);
        
        /**
         * Sets (as xml) the "text-format-code" element
         */
        void xsetTextFormatCode(org.apache.xmlbeans.XmlString textFormatCode);
        
        /**
         * Gets the "text-type-code" element
         */
        java.lang.String getTextTypeCode();
        
        /**
         * Gets (as xml) the "text-type-code" element
         */
        org.apache.xmlbeans.XmlString xgetTextTypeCode();
        
        /**
         * Sets the "text-type-code" element
         */
        void setTextTypeCode(java.lang.String textTypeCode);
        
        /**
         * Sets (as xml) the "text-type-code" element
         */
        void xsetTextTypeCode(org.apache.xmlbeans.XmlString textTypeCode);
        
        /**
         * Gets the "retired" element
         */
        boolean getRetired();
        
        /**
         * Gets (as xml) the "retired" element
         */
        org.apache.xmlbeans.XmlBoolean xgetRetired();
        
        /**
         * True if has "retired" element
         */
        boolean isSetRetired();
        
        /**
         * Sets the "retired" element
         */
        void setRetired(boolean retired);
        
        /**
         * Sets (as xml) the "retired" element
         */
        void xsetRetired(org.apache.xmlbeans.XmlBoolean retired);
        
        /**
         * Unsets the "retired" element
         */
        void unsetRetired();
        
        /**
         * Gets the "data" element
         */
        java.lang.String getData();
        
        /**
         * Gets (as xml) the "data" element
         */
        org.apache.xmlbeans.XmlString xgetData();
        
        /**
         * Sets the "data" element
         */
        void setData(java.lang.String data);
        
        /**
         * Sets (as xml) the "data" element
         */
        void xsetData(org.apache.xmlbeans.XmlString data);
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.gov.ofwat.model2.TextBlockDocument.TextBlock newInstance() {
              return (uk.gov.ofwat.model2.TextBlockDocument.TextBlock) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.gov.ofwat.model2.TextBlockDocument.TextBlock newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.gov.ofwat.model2.TextBlockDocument.TextBlock) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.gov.ofwat.model2.TextBlockDocument newInstance() {
          return (uk.gov.ofwat.model2.TextBlockDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.gov.ofwat.model2.TextBlockDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.gov.ofwat.model2.TextBlockDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.gov.ofwat.model2.TextBlockDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.TextBlockDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.gov.ofwat.model2.TextBlockDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.TextBlockDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.gov.ofwat.model2.TextBlockDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TextBlockDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.gov.ofwat.model2.TextBlockDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TextBlockDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.gov.ofwat.model2.TextBlockDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TextBlockDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.gov.ofwat.model2.TextBlockDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TextBlockDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.gov.ofwat.model2.TextBlockDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TextBlockDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.gov.ofwat.model2.TextBlockDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TextBlockDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.gov.ofwat.model2.TextBlockDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TextBlockDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.gov.ofwat.model2.TextBlockDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.TextBlockDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.gov.ofwat.model2.TextBlockDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.TextBlockDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.gov.ofwat.model2.TextBlockDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.TextBlockDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.gov.ofwat.model2.TextBlockDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.TextBlockDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.gov.ofwat.model2.TextBlockDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.TextBlockDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.TextBlockDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.TextBlockDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.TextBlockDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.TextBlockDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
