/*
 * An XML document type.
 * Localname: form-headings-left
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.FormHeadingsLeftDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2;


/**
 * A document containing one form-headings-left(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public interface FormHeadingsLeftDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(FormHeadingsLeftDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("formheadingsleftd98bdoctype");
    
    /**
     * Gets the "form-headings-left" element
     */
    uk.gov.ofwat.model2.FormHeadingsLeftDocument.FormHeadingsLeft getFormHeadingsLeft();
    
    /**
     * Sets the "form-headings-left" element
     */
    void setFormHeadingsLeft(uk.gov.ofwat.model2.FormHeadingsLeftDocument.FormHeadingsLeft formHeadingsLeft);
    
    /**
     * Appends and returns a new empty "form-headings-left" element
     */
    uk.gov.ofwat.model2.FormHeadingsLeftDocument.FormHeadingsLeft addNewFormHeadingsLeft();
    
    /**
     * An XML form-headings-left(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public interface FormHeadingsLeft extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(FormHeadingsLeft.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("formheadingsleftd981elemtype");
        
        /**
         * Gets array of all "form-heading-cell" elements
         */
        uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell[] getFormHeadingCellArray();
        
        /**
         * Gets ith "form-heading-cell" element
         */
        uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell getFormHeadingCellArray(int i);
        
        /**
         * Returns number of "form-heading-cell" element
         */
        int sizeOfFormHeadingCellArray();
        
        /**
         * Sets array of all "form-heading-cell" element
         */
        void setFormHeadingCellArray(uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell[] formHeadingCellArray);
        
        /**
         * Sets ith "form-heading-cell" element
         */
        void setFormHeadingCellArray(int i, uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell formHeadingCell);
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "form-heading-cell" element
         */
        uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell insertNewFormHeadingCell(int i);
        
        /**
         * Appends and returns a new empty value (as xml) as the last "form-heading-cell" element
         */
        uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell addNewFormHeadingCell();
        
        /**
         * Removes the ith "form-heading-cell" element
         */
        void removeFormHeadingCell(int i);
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.gov.ofwat.model2.FormHeadingsLeftDocument.FormHeadingsLeft newInstance() {
              return (uk.gov.ofwat.model2.FormHeadingsLeftDocument.FormHeadingsLeft) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.gov.ofwat.model2.FormHeadingsLeftDocument.FormHeadingsLeft newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.gov.ofwat.model2.FormHeadingsLeftDocument.FormHeadingsLeft) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.gov.ofwat.model2.FormHeadingsLeftDocument newInstance() {
          return (uk.gov.ofwat.model2.FormHeadingsLeftDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.gov.ofwat.model2.FormHeadingsLeftDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.gov.ofwat.model2.FormHeadingsLeftDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.gov.ofwat.model2.FormHeadingsLeftDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.FormHeadingsLeftDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.gov.ofwat.model2.FormHeadingsLeftDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.FormHeadingsLeftDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.gov.ofwat.model2.FormHeadingsLeftDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormHeadingsLeftDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.gov.ofwat.model2.FormHeadingsLeftDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormHeadingsLeftDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.gov.ofwat.model2.FormHeadingsLeftDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormHeadingsLeftDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.gov.ofwat.model2.FormHeadingsLeftDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormHeadingsLeftDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.gov.ofwat.model2.FormHeadingsLeftDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormHeadingsLeftDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.gov.ofwat.model2.FormHeadingsLeftDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormHeadingsLeftDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.gov.ofwat.model2.FormHeadingsLeftDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormHeadingsLeftDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.gov.ofwat.model2.FormHeadingsLeftDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormHeadingsLeftDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.gov.ofwat.model2.FormHeadingsLeftDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.FormHeadingsLeftDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.gov.ofwat.model2.FormHeadingsLeftDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.FormHeadingsLeftDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.gov.ofwat.model2.FormHeadingsLeftDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.FormHeadingsLeftDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.gov.ofwat.model2.FormHeadingsLeftDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.FormHeadingsLeftDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.FormHeadingsLeftDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.FormHeadingsLeftDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.FormHeadingsLeftDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.FormHeadingsLeftDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
