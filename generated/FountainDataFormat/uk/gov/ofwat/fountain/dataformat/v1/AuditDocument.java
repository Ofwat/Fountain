/*
 * An XML document type.
 * Localname: audit
 * Namespace: http://www.ofwat.gov.uk/fountain/dataformat/v1
 * Java type: uk.gov.ofwat.fountain.dataformat.v1.AuditDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.fountain.dataformat.v1;


/**
 * A document containing one audit(@http://www.ofwat.gov.uk/fountain/dataformat/v1) element.
 *
 * This is a complex type.
 */
public interface AuditDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(AuditDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s1E19C368053534F99A2219B5E0EEC0C6").resolveHandle("audit45e1doctype");
    
    /**
     * Gets the "audit" element
     */
    uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit getAudit();
    
    /**
     * Sets the "audit" element
     */
    void setAudit(uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit audit);
    
    /**
     * Appends and returns a new empty "audit" element
     */
    uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit addNewAudit();
    
    /**
     * An XML audit(@http://www.ofwat.gov.uk/fountain/dataformat/v1).
     *
     * This is a complex type.
     */
    public interface Audit extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Audit.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s1E19C368053534F99A2219B5E0EEC0C6").resolveHandle("auditcfbaelemtype");
        
        /**
         * Gets the "comment" element
         */
        java.lang.String getComment();
        
        /**
         * Gets (as xml) the "comment" element
         */
        org.apache.xmlbeans.XmlString xgetComment();
        
        /**
         * Sets the "comment" element
         */
        void setComment(java.lang.String comment);
        
        /**
         * Sets (as xml) the "comment" element
         */
        void xsetComment(org.apache.xmlbeans.XmlString comment);
        
        /**
         * Gets the "data" element
         */
        uk.gov.ofwat.fountain.dataformat.v1.DataDocument.Data getData();
        
        /**
         * Sets the "data" element
         */
        void setData(uk.gov.ofwat.fountain.dataformat.v1.DataDocument.Data data);
        
        /**
         * Appends and returns a new empty "data" element
         */
        uk.gov.ofwat.fountain.dataformat.v1.DataDocument.Data addNewData();
        
        /**
         * Gets the "user" attribute
         */
        java.lang.String getUser();
        
        /**
         * Gets (as xml) the "user" attribute
         */
        org.apache.xmlbeans.XmlString xgetUser();
        
        /**
         * Sets the "user" attribute
         */
        void setUser(java.lang.String user);
        
        /**
         * Sets (as xml) the "user" attribute
         */
        void xsetUser(org.apache.xmlbeans.XmlString user);
        
        /**
         * Gets the "timestamp" attribute
         */
        long getTimestamp();
        
        /**
         * Gets (as xml) the "timestamp" attribute
         */
        org.apache.xmlbeans.XmlLong xgetTimestamp();
        
        /**
         * Sets the "timestamp" attribute
         */
        void setTimestamp(long timestamp);
        
        /**
         * Sets (as xml) the "timestamp" attribute
         */
        void xsetTimestamp(org.apache.xmlbeans.XmlLong timestamp);
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit newInstance() {
              return (uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditDocument newInstance() {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
