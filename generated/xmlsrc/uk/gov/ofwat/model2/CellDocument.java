/*
 * An XML document type.
 * Localname: cell
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.CellDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2;


/**
 * A document containing one cell(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public interface CellDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CellDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("cell2bc3doctype");
    
    /**
     * Gets the "cell" element
     */
    uk.gov.ofwat.model2.CellDocument.Cell getCell();
    
    /**
     * Sets the "cell" element
     */
    void setCell(uk.gov.ofwat.model2.CellDocument.Cell cell);
    
    /**
     * Appends and returns a new empty "cell" element
     */
    uk.gov.ofwat.model2.CellDocument.Cell addNewCell();
    
    /**
     * An XML cell(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public interface Cell extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Cell.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("cell4341elemtype");
        
        /**
         * Gets the "code" element
         */
        java.lang.String getCode();
        
        /**
         * Gets (as xml) the "code" element
         */
        org.apache.xmlbeans.XmlString xgetCode();
        
        /**
         * True if has "code" element
         */
        boolean isSetCode();
        
        /**
         * Sets the "code" element
         */
        void setCode(java.lang.String code);
        
        /**
         * Sets (as xml) the "code" element
         */
        void xsetCode(org.apache.xmlbeans.XmlString code);
        
        /**
         * Unsets the "code" element
         */
        void unsetCode();
        
        /**
         * Gets the "year" element
         */
        java.lang.String getYear();
        
        /**
         * Gets (as xml) the "year" element
         */
        org.apache.xmlbeans.XmlString xgetYear();
        
        /**
         * Sets the "year" element
         */
        void setYear(java.lang.String year);
        
        /**
         * Sets (as xml) the "year" element
         */
        void xsetYear(org.apache.xmlbeans.XmlString year);
        
        /**
         * Gets the "equation" element
         */
        java.lang.String getEquation();
        
        /**
         * Gets (as xml) the "equation" element
         */
        org.apache.xmlbeans.XmlString xgetEquation();
        
        /**
         * True if has "equation" element
         */
        boolean isSetEquation();
        
        /**
         * Sets the "equation" element
         */
        void setEquation(java.lang.String equation);
        
        /**
         * Sets (as xml) the "equation" element
         */
        void xsetEquation(org.apache.xmlbeans.XmlString equation);
        
        /**
         * Unsets the "equation" element
         */
        void unsetEquation();
        
        /**
         * Gets the "type" element
         */
        java.lang.String getType();
        
        /**
         * Gets (as xml) the "type" element
         */
        org.apache.xmlbeans.XmlString xgetType();
        
        /**
         * Sets the "type" element
         */
        void setType(java.lang.String type);
        
        /**
         * Sets (as xml) the "type" element
         */
        void xsetType(org.apache.xmlbeans.XmlString type);
        
        /**
         * Gets the "cgtype" element
         */
        java.lang.String getCgtype();
        
        /**
         * Gets (as xml) the "cgtype" element
         */
        org.apache.xmlbeans.XmlString xgetCgtype();
        
        /**
         * True if has "cgtype" element
         */
        boolean isSetCgtype();
        
        /**
         * Sets the "cgtype" element
         */
        void setCgtype(java.lang.String cgtype);
        
        /**
         * Sets (as xml) the "cgtype" element
         */
        void xsetCgtype(org.apache.xmlbeans.XmlString cgtype);
        
        /**
         * Unsets the "cgtype" element
         */
        void unsetCgtype();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.gov.ofwat.model2.CellDocument.Cell newInstance() {
              return (uk.gov.ofwat.model2.CellDocument.Cell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.gov.ofwat.model2.CellDocument.Cell newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.gov.ofwat.model2.CellDocument.Cell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.gov.ofwat.model2.CellDocument newInstance() {
          return (uk.gov.ofwat.model2.CellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.gov.ofwat.model2.CellDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.gov.ofwat.model2.CellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.gov.ofwat.model2.CellDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.CellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.gov.ofwat.model2.CellDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.CellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.gov.ofwat.model2.CellDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.CellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.gov.ofwat.model2.CellDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.CellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.gov.ofwat.model2.CellDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.CellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.gov.ofwat.model2.CellDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.CellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.gov.ofwat.model2.CellDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.CellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.gov.ofwat.model2.CellDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.CellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.gov.ofwat.model2.CellDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.CellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.gov.ofwat.model2.CellDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.CellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.gov.ofwat.model2.CellDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.CellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.gov.ofwat.model2.CellDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.CellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.gov.ofwat.model2.CellDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.CellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.gov.ofwat.model2.CellDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.CellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.CellDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.CellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.CellDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.CellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
