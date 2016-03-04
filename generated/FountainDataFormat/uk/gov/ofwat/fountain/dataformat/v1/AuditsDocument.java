/*
 * An XML document type.
 * Localname: audits
 * Namespace: http://www.ofwat.gov.uk/fountain/dataformat/v1
 * Java type: uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.fountain.dataformat.v1;


/**
 * A document containing one audits(@http://www.ofwat.gov.uk/fountain/dataformat/v1) element.
 *
 * This is a complex type.
 */
public interface AuditsDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(AuditsDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s1E19C368053534F99A2219B5E0EEC0C6").resolveHandle("auditsbb52doctype");
    
    /**
     * Gets the "audits" element
     */
    uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument.Audits getAudits();
    
    /**
     * Sets the "audits" element
     */
    void setAudits(uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument.Audits audits);
    
    /**
     * Appends and returns a new empty "audits" element
     */
    uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument.Audits addNewAudits();
    
    /**
     * An XML audits(@http://www.ofwat.gov.uk/fountain/dataformat/v1).
     *
     * This is a complex type.
     */
    public interface Audits extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Audits.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s1E19C368053534F99A2219B5E0EEC0C6").resolveHandle("audits05e6elemtype");
        
        /**
         * Gets array of all "audit" elements
         */
        uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit[] getAuditArray();
        
        /**
         * Gets ith "audit" element
         */
        uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit getAuditArray(int i);
        
        /**
         * Returns number of "audit" element
         */
        int sizeOfAuditArray();
        
        /**
         * Sets array of all "audit" element
         */
        void setAuditArray(uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit[] auditArray);
        
        /**
         * Sets ith "audit" element
         */
        void setAuditArray(int i, uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit audit);
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "audit" element
         */
        uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit insertNewAudit(int i);
        
        /**
         * Appends and returns a new empty value (as xml) as the last "audit" element
         */
        uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit addNewAudit();
        
        /**
         * Removes the ith "audit" element
         */
        void removeAudit(int i);
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument.Audits newInstance() {
              return (uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument.Audits) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument.Audits newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument.Audits) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument newInstance() {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
