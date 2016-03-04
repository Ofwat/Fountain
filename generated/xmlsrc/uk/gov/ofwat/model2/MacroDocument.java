/*
 * An XML document type.
 * Localname: macro
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.MacroDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2;


/**
 * A document containing one macro(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public interface MacroDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(MacroDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("macroaf2bdoctype");
    
    /**
     * Gets the "macro" element
     */
    uk.gov.ofwat.model2.MacroDocument.Macro getMacro();
    
    /**
     * Sets the "macro" element
     */
    void setMacro(uk.gov.ofwat.model2.MacroDocument.Macro macro);
    
    /**
     * Appends and returns a new empty "macro" element
     */
    uk.gov.ofwat.model2.MacroDocument.Macro addNewMacro();
    
    /**
     * An XML macro(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public interface Macro extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Macro.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("macroedf3elemtype");
        
        /**
         * Gets the "name" element
         */
        java.lang.String getName();
        
        /**
         * Gets (as xml) the "name" element
         */
        org.apache.xmlbeans.XmlString xgetName();
        
        /**
         * Sets the "name" element
         */
        void setName(java.lang.String name);
        
        /**
         * Sets (as xml) the "name" element
         */
        void xsetName(org.apache.xmlbeans.XmlString name);
        
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
         * Gets the "block" element
         */
        java.lang.String getBlock();
        
        /**
         * Gets (as xml) the "block" element
         */
        org.apache.xmlbeans.XmlString xgetBlock();
        
        /**
         * Sets the "block" element
         */
        void setBlock(java.lang.String block);
        
        /**
         * Sets (as xml) the "block" element
         */
        void xsetBlock(org.apache.xmlbeans.XmlString block);
        
        /**
         * Gets the "conditional-item-code" element
         */
        java.lang.String getConditionalItemCode();
        
        /**
         * Gets (as xml) the "conditional-item-code" element
         */
        org.apache.xmlbeans.XmlString xgetConditionalItemCode();
        
        /**
         * True if has "conditional-item-code" element
         */
        boolean isSetConditionalItemCode();
        
        /**
         * Sets the "conditional-item-code" element
         */
        void setConditionalItemCode(java.lang.String conditionalItemCode);
        
        /**
         * Sets (as xml) the "conditional-item-code" element
         */
        void xsetConditionalItemCode(org.apache.xmlbeans.XmlString conditionalItemCode);
        
        /**
         * Unsets the "conditional-item-code" element
         */
        void unsetConditionalItemCode();
        
        /**
         * Gets the "page-code" element
         */
        java.lang.String getPageCode();
        
        /**
         * Gets (as xml) the "page-code" element
         */
        org.apache.xmlbeans.XmlString xgetPageCode();
        
        /**
         * True if has "page-code" element
         */
        boolean isSetPageCode();
        
        /**
         * Sets the "page-code" element
         */
        void setPageCode(java.lang.String pageCode);
        
        /**
         * Sets (as xml) the "page-code" element
         */
        void xsetPageCode(org.apache.xmlbeans.XmlString pageCode);
        
        /**
         * Unsets the "page-code" element
         */
        void unsetPageCode();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.gov.ofwat.model2.MacroDocument.Macro newInstance() {
              return (uk.gov.ofwat.model2.MacroDocument.Macro) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.gov.ofwat.model2.MacroDocument.Macro newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.gov.ofwat.model2.MacroDocument.Macro) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.gov.ofwat.model2.MacroDocument newInstance() {
          return (uk.gov.ofwat.model2.MacroDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.gov.ofwat.model2.MacroDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.gov.ofwat.model2.MacroDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.gov.ofwat.model2.MacroDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.MacroDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.gov.ofwat.model2.MacroDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.MacroDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.gov.ofwat.model2.MacroDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.MacroDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.gov.ofwat.model2.MacroDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.MacroDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.gov.ofwat.model2.MacroDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.MacroDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.gov.ofwat.model2.MacroDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.MacroDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.gov.ofwat.model2.MacroDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.MacroDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.gov.ofwat.model2.MacroDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.MacroDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.gov.ofwat.model2.MacroDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.MacroDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.gov.ofwat.model2.MacroDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.MacroDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.gov.ofwat.model2.MacroDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.MacroDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.gov.ofwat.model2.MacroDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.MacroDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.gov.ofwat.model2.MacroDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.MacroDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.gov.ofwat.model2.MacroDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.MacroDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.MacroDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.MacroDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.MacroDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.MacroDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
