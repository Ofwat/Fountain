/*
 * An XML document type.
 * Localname: data
 * Namespace: http://www.ofwat.gov.uk/fountain/dataformat/v1
 * Java type: uk.gov.ofwat.fountain.dataformat.v1.DataDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.fountain.dataformat.v1;


/**
 * A document containing one data(@http://www.ofwat.gov.uk/fountain/dataformat/v1) element.
 *
 * This is a complex type.
 */
public interface DataDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(DataDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s1E19C368053534F99A2219B5E0EEC0C6").resolveHandle("data7720doctype");
    
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
     * An XML data(@http://www.ofwat.gov.uk/fountain/dataformat/v1).
     *
     * This is a complex type.
     */
    public interface Data extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Data.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s1E19C368053534F99A2219B5E0EEC0C6").resolveHandle("datab2e6elemtype");
        
        /**
         * Gets array of all "value" elements
         */
        uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value[] getValueArray();
        
        /**
         * Gets ith "value" element
         */
        uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value getValueArray(int i);
        
        /**
         * Returns number of "value" element
         */
        int sizeOfValueArray();
        
        /**
         * Sets array of all "value" element
         */
        void setValueArray(uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value[] valueArray);
        
        /**
         * Sets ith "value" element
         */
        void setValueArray(int i, uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value value);
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "value" element
         */
        uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value insertNewValue(int i);
        
        /**
         * Appends and returns a new empty value (as xml) as the last "value" element
         */
        uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value addNewValue();
        
        /**
         * Removes the ith "value" element
         */
        void removeValue(int i);
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.gov.ofwat.fountain.dataformat.v1.DataDocument.Data newInstance() {
              return (uk.gov.ofwat.fountain.dataformat.v1.DataDocument.Data) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.gov.ofwat.fountain.dataformat.v1.DataDocument.Data newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.gov.ofwat.fountain.dataformat.v1.DataDocument.Data) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.gov.ofwat.fountain.dataformat.v1.DataDocument newInstance() {
          return (uk.gov.ofwat.fountain.dataformat.v1.DataDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.DataDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.gov.ofwat.fountain.dataformat.v1.DataDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.gov.ofwat.fountain.dataformat.v1.DataDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.fountain.dataformat.v1.DataDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.DataDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.fountain.dataformat.v1.DataDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.gov.ofwat.fountain.dataformat.v1.DataDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.DataDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.DataDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.DataDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.DataDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.DataDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.DataDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.DataDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.DataDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.DataDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.DataDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.DataDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.DataDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.DataDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.DataDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.fountain.dataformat.v1.DataDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.DataDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.fountain.dataformat.v1.DataDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.DataDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.fountain.dataformat.v1.DataDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.DataDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.fountain.dataformat.v1.DataDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.gov.ofwat.fountain.dataformat.v1.DataDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.fountain.dataformat.v1.DataDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.fountain.dataformat.v1.DataDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.fountain.dataformat.v1.DataDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.fountain.dataformat.v1.DataDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.fountain.dataformat.v1.DataDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
