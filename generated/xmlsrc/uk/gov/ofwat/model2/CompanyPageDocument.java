/*
 * An XML document type.
 * Localname: company-page
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.CompanyPageDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2;


/**
 * A document containing one company-page(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public interface CompanyPageDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CompanyPageDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("companypage00a6doctype");
    
    /**
     * Gets the "company-page" element
     */
    uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage getCompanyPage();
    
    /**
     * Sets the "company-page" element
     */
    void setCompanyPage(uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage companyPage);
    
    /**
     * Appends and returns a new empty "company-page" element
     */
    uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage addNewCompanyPage();
    
    /**
     * An XML company-page(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public interface CompanyPage extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CompanyPage.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("companypage53e1elemtype");
        
        /**
         * Gets the "company-code" element
         */
        java.lang.String getCompanyCode();
        
        /**
         * Gets (as xml) the "company-code" element
         */
        org.apache.xmlbeans.XmlString xgetCompanyCode();
        
        /**
         * Sets the "company-code" element
         */
        void setCompanyCode(java.lang.String companyCode);
        
        /**
         * Sets (as xml) the "company-code" element
         */
        void xsetCompanyCode(org.apache.xmlbeans.XmlString companyCode);
        
        /**
         * Gets the "page-code" element
         */
        java.lang.String getPageCode();
        
        /**
         * Gets (as xml) the "page-code" element
         */
        org.apache.xmlbeans.XmlString xgetPageCode();
        
        /**
         * Sets the "page-code" element
         */
        void setPageCode(java.lang.String pageCode);
        
        /**
         * Sets (as xml) the "page-code" element
         */
        void xsetPageCode(org.apache.xmlbeans.XmlString pageCode);
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage newInstance() {
              return (uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.gov.ofwat.model2.CompanyPageDocument newInstance() {
          return (uk.gov.ofwat.model2.CompanyPageDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.gov.ofwat.model2.CompanyPageDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.gov.ofwat.model2.CompanyPageDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.gov.ofwat.model2.CompanyPageDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.CompanyPageDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.gov.ofwat.model2.CompanyPageDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.CompanyPageDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.gov.ofwat.model2.CompanyPageDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.CompanyPageDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.gov.ofwat.model2.CompanyPageDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.CompanyPageDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.gov.ofwat.model2.CompanyPageDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.CompanyPageDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.gov.ofwat.model2.CompanyPageDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.CompanyPageDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.gov.ofwat.model2.CompanyPageDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.CompanyPageDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.gov.ofwat.model2.CompanyPageDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.CompanyPageDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.gov.ofwat.model2.CompanyPageDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.CompanyPageDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.gov.ofwat.model2.CompanyPageDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.CompanyPageDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.gov.ofwat.model2.CompanyPageDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.CompanyPageDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.gov.ofwat.model2.CompanyPageDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.CompanyPageDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.gov.ofwat.model2.CompanyPageDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.CompanyPageDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.gov.ofwat.model2.CompanyPageDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.CompanyPageDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.CompanyPageDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.CompanyPageDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.CompanyPageDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.CompanyPageDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
