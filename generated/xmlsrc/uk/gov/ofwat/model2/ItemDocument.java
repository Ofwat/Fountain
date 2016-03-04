/*
 * An XML document type.
 * Localname: item
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.ItemDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2;


/**
 * A document containing one item(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public interface ItemDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ItemDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("item43f2doctype");
    
    /**
     * Gets the "item" element
     */
    uk.gov.ofwat.model2.ItemDocument.Item getItem();
    
    /**
     * Sets the "item" element
     */
    void setItem(uk.gov.ofwat.model2.ItemDocument.Item item);
    
    /**
     * Appends and returns a new empty "item" element
     */
    uk.gov.ofwat.model2.ItemDocument.Item addNewItem();
    
    /**
     * An XML item(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public interface Item extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Item.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("item8061elemtype");
        
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
         * Gets the "pricebase-code" element
         */
        java.lang.String getPricebaseCode();
        
        /**
         * Gets (as xml) the "pricebase-code" element
         */
        org.apache.xmlbeans.XmlString xgetPricebaseCode();
        
        /**
         * True if has "pricebase-code" element
         */
        boolean isSetPricebaseCode();
        
        /**
         * Sets the "pricebase-code" element
         */
        void setPricebaseCode(java.lang.String pricebaseCode);
        
        /**
         * Sets (as xml) the "pricebase-code" element
         */
        void xsetPricebaseCode(org.apache.xmlbeans.XmlString pricebaseCode);
        
        /**
         * Unsets the "pricebase-code" element
         */
        void unsetPricebaseCode();
        
        /**
         * Gets the "purpose-code" element
         */
        java.lang.String getPurposeCode();
        
        /**
         * Gets (as xml) the "purpose-code" element
         */
        org.apache.xmlbeans.XmlString xgetPurposeCode();
        
        /**
         * True if has "purpose-code" element
         */
        boolean isSetPurposeCode();
        
        /**
         * Sets the "purpose-code" element
         */
        void setPurposeCode(java.lang.String purposeCode);
        
        /**
         * Sets (as xml) the "purpose-code" element
         */
        void xsetPurposeCode(org.apache.xmlbeans.XmlString purposeCode);
        
        /**
         * Unsets the "purpose-code" element
         */
        void unsetPurposeCode();
        
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
            public static uk.gov.ofwat.model2.ItemDocument.Item newInstance() {
              return (uk.gov.ofwat.model2.ItemDocument.Item) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.gov.ofwat.model2.ItemDocument.Item newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.gov.ofwat.model2.ItemDocument.Item) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.gov.ofwat.model2.ItemDocument newInstance() {
          return (uk.gov.ofwat.model2.ItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.gov.ofwat.model2.ItemDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.gov.ofwat.model2.ItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.gov.ofwat.model2.ItemDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.gov.ofwat.model2.ItemDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.gov.ofwat.model2.ItemDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.gov.ofwat.model2.ItemDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.gov.ofwat.model2.ItemDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.gov.ofwat.model2.ItemDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.gov.ofwat.model2.ItemDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.gov.ofwat.model2.ItemDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.gov.ofwat.model2.ItemDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.gov.ofwat.model2.ItemDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.gov.ofwat.model2.ItemDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.gov.ofwat.model2.ItemDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.gov.ofwat.model2.ItemDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.gov.ofwat.model2.ItemDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.ItemDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.ItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.ItemDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.ItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
