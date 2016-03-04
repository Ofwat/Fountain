/*
 * An XML document type.
 * Localname: document
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.DocumentDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2;


/**
 * A document containing one document(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public interface DocumentDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(DocumentDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("document616adoctype");
    
    /**
     * Gets the "document" element
     */
    uk.gov.ofwat.model2.DocumentDocument.Document getDocument();
    
    /**
     * Sets the "document" element
     */
    void setDocument(uk.gov.ofwat.model2.DocumentDocument.Document document);
    
    /**
     * Appends and returns a new empty "document" element
     */
    uk.gov.ofwat.model2.DocumentDocument.Document addNewDocument();
    
    /**
     * An XML document(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public interface Document extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Document.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("document0e61elemtype");
        
        /**
         * Gets the "reporter" element
         */
        java.lang.String getReporter();
        
        /**
         * Gets (as xml) the "reporter" element
         */
        org.apache.xmlbeans.XmlString xgetReporter();
        
        /**
         * Sets the "reporter" element
         */
        void setReporter(java.lang.String reporter);
        
        /**
         * Sets (as xml) the "reporter" element
         */
        void xsetReporter(org.apache.xmlbeans.XmlString reporter);
        
        /**
         * Gets the "auditor" element
         */
        java.lang.String getAuditor();
        
        /**
         * Gets (as xml) the "auditor" element
         */
        org.apache.xmlbeans.XmlString xgetAuditor();
        
        /**
         * Sets the "auditor" element
         */
        void setAuditor(java.lang.String auditor);
        
        /**
         * Sets (as xml) the "auditor" element
         */
        void xsetAuditor(org.apache.xmlbeans.XmlString auditor);
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.gov.ofwat.model2.DocumentDocument.Document newInstance() {
              return (uk.gov.ofwat.model2.DocumentDocument.Document) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.gov.ofwat.model2.DocumentDocument.Document newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.gov.ofwat.model2.DocumentDocument.Document) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.gov.ofwat.model2.DocumentDocument newInstance() {
          return (uk.gov.ofwat.model2.DocumentDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.gov.ofwat.model2.DocumentDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.gov.ofwat.model2.DocumentDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.gov.ofwat.model2.DocumentDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.DocumentDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.gov.ofwat.model2.DocumentDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.DocumentDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.gov.ofwat.model2.DocumentDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.DocumentDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.gov.ofwat.model2.DocumentDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.DocumentDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.gov.ofwat.model2.DocumentDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.DocumentDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.gov.ofwat.model2.DocumentDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.DocumentDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.gov.ofwat.model2.DocumentDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.DocumentDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.gov.ofwat.model2.DocumentDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.DocumentDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.gov.ofwat.model2.DocumentDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.DocumentDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.gov.ofwat.model2.DocumentDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.DocumentDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.gov.ofwat.model2.DocumentDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.DocumentDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.gov.ofwat.model2.DocumentDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.DocumentDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.gov.ofwat.model2.DocumentDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.DocumentDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.gov.ofwat.model2.DocumentDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.DocumentDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.DocumentDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.DocumentDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.DocumentDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.DocumentDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
