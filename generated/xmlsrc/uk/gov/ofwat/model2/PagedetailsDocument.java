/*
 * An XML document type.
 * Localname: pagedetails
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.PagedetailsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2;


/**
 * A document containing one pagedetails(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public interface PagedetailsDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(PagedetailsDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("pagedetails6a84doctype");
    
    /**
     * Gets the "pagedetails" element
     */
    uk.gov.ofwat.model2.PagedetailsDocument.Pagedetails getPagedetails();
    
    /**
     * Sets the "pagedetails" element
     */
    void setPagedetails(uk.gov.ofwat.model2.PagedetailsDocument.Pagedetails pagedetails);
    
    /**
     * Appends and returns a new empty "pagedetails" element
     */
    uk.gov.ofwat.model2.PagedetailsDocument.Pagedetails addNewPagedetails();
    
    /**
     * An XML pagedetails(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public interface Pagedetails extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Pagedetails.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("pagedetails5ba5elemtype");
        
        /**
         * Gets the "code" element
         */
        java.lang.String getCode();
        
        /**
         * Gets (as xml) the "code" element
         */
        org.apache.xmlbeans.XmlString xgetCode();
        
        /**
         * Sets the "code" element
         */
        void setCode(java.lang.String code);
        
        /**
         * Sets (as xml) the "code" element
         */
        void xsetCode(org.apache.xmlbeans.XmlString code);
        
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
         * Gets the "text" element
         */
        java.lang.String getText();
        
        /**
         * Gets (as xml) the "text" element
         */
        org.apache.xmlbeans.XmlString xgetText();
        
        /**
         * True if has "text" element
         */
        boolean isSetText();
        
        /**
         * Sets the "text" element
         */
        void setText(java.lang.String text);
        
        /**
         * Sets (as xml) the "text" element
         */
        void xsetText(org.apache.xmlbeans.XmlString text);
        
        /**
         * Unsets the "text" element
         */
        void unsetText();
        
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
         * Gets the "heading" element
         */
        java.lang.String getHeading();
        
        /**
         * Gets (as xml) the "heading" element
         */
        org.apache.xmlbeans.XmlString xgetHeading();
        
        /**
         * True if has "heading" element
         */
        boolean isSetHeading();
        
        /**
         * Sets the "heading" element
         */
        void setHeading(java.lang.String heading);
        
        /**
         * Sets (as xml) the "heading" element
         */
        void xsetHeading(org.apache.xmlbeans.XmlString heading);
        
        /**
         * Unsets the "heading" element
         */
        void unsetHeading();
        
        /**
         * Gets the "commercial-in-confidence" element
         */
        boolean getCommercialInConfidence();
        
        /**
         * Gets (as xml) the "commercial-in-confidence" element
         */
        org.apache.xmlbeans.XmlBoolean xgetCommercialInConfidence();
        
        /**
         * True if has "commercial-in-confidence" element
         */
        boolean isSetCommercialInConfidence();
        
        /**
         * Sets the "commercial-in-confidence" element
         */
        void setCommercialInConfidence(boolean commercialInConfidence);
        
        /**
         * Sets (as xml) the "commercial-in-confidence" element
         */
        void xsetCommercialInConfidence(org.apache.xmlbeans.XmlBoolean commercialInConfidence);
        
        /**
         * Unsets the "commercial-in-confidence" element
         */
        void unsetCommercialInConfidence();
        
        /**
         * Gets the "hidden" element
         */
        boolean getHidden();
        
        /**
         * Gets (as xml) the "hidden" element
         */
        org.apache.xmlbeans.XmlBoolean xgetHidden();
        
        /**
         * True if has "hidden" element
         */
        boolean isSetHidden();
        
        /**
         * Sets the "hidden" element
         */
        void setHidden(boolean hidden);
        
        /**
         * Sets (as xml) the "hidden" element
         */
        void xsetHidden(org.apache.xmlbeans.XmlBoolean hidden);
        
        /**
         * Unsets the "hidden" element
         */
        void unsetHidden();
        
        /**
         * Gets the "text-code" element
         */
        java.lang.String getTextCode();
        
        /**
         * Gets (as xml) the "text-code" element
         */
        org.apache.xmlbeans.XmlString xgetTextCode();
        
        /**
         * True if has "text-code" element
         */
        boolean isSetTextCode();
        
        /**
         * Sets the "text-code" element
         */
        void setTextCode(java.lang.String textCode);
        
        /**
         * Sets (as xml) the "text-code" element
         */
        void xsetTextCode(org.apache.xmlbeans.XmlString textCode);
        
        /**
         * Unsets the "text-code" element
         */
        void unsetTextCode();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.gov.ofwat.model2.PagedetailsDocument.Pagedetails newInstance() {
              return (uk.gov.ofwat.model2.PagedetailsDocument.Pagedetails) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.gov.ofwat.model2.PagedetailsDocument.Pagedetails newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.gov.ofwat.model2.PagedetailsDocument.Pagedetails) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.gov.ofwat.model2.PagedetailsDocument newInstance() {
          return (uk.gov.ofwat.model2.PagedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.gov.ofwat.model2.PagedetailsDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.gov.ofwat.model2.PagedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.gov.ofwat.model2.PagedetailsDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.PagedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.gov.ofwat.model2.PagedetailsDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.PagedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.gov.ofwat.model2.PagedetailsDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.PagedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.gov.ofwat.model2.PagedetailsDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.PagedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.gov.ofwat.model2.PagedetailsDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.PagedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.gov.ofwat.model2.PagedetailsDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.PagedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.gov.ofwat.model2.PagedetailsDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.PagedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.gov.ofwat.model2.PagedetailsDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.PagedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.gov.ofwat.model2.PagedetailsDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.PagedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.gov.ofwat.model2.PagedetailsDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.PagedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.gov.ofwat.model2.PagedetailsDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.PagedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.gov.ofwat.model2.PagedetailsDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.PagedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.gov.ofwat.model2.PagedetailsDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.PagedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.gov.ofwat.model2.PagedetailsDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.PagedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.PagedetailsDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.PagedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.PagedetailsDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.PagedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
