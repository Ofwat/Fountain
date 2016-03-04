/*
 * An XML document type.
 * Localname: validation-rule-item
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.ValidationRuleItemDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2;


/**
 * A document containing one validation-rule-item(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public interface ValidationRuleItemDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ValidationRuleItemDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("validationruleitemaa75doctype");
    
    /**
     * Gets the "validation-rule-item" element
     */
    uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem getValidationRuleItem();
    
    /**
     * Sets the "validation-rule-item" element
     */
    void setValidationRuleItem(uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem validationRuleItem);
    
    /**
     * Appends and returns a new empty "validation-rule-item" element
     */
    uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem addNewValidationRuleItem();
    
    /**
     * An XML validation-rule-item(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public interface ValidationRuleItem extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ValidationRuleItem.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("validationruleitem8101elemtype");
        
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
         * Gets the "evaluate" element
         */
        java.lang.String getEvaluate();
        
        /**
         * Gets (as xml) the "evaluate" element
         */
        org.apache.xmlbeans.XmlString xgetEvaluate();
        
        /**
         * True if has "evaluate" element
         */
        boolean isSetEvaluate();
        
        /**
         * Sets the "evaluate" element
         */
        void setEvaluate(java.lang.String evaluate);
        
        /**
         * Sets (as xml) the "evaluate" element
         */
        void xsetEvaluate(org.apache.xmlbeans.XmlString evaluate);
        
        /**
         * Unsets the "evaluate" element
         */
        void unsetEvaluate();
        
        /**
         * Gets the "value" element
         */
        java.lang.String getValue();
        
        /**
         * Gets (as xml) the "value" element
         */
        org.apache.xmlbeans.XmlString xgetValue();
        
        /**
         * True if has "value" element
         */
        boolean isSetValue();
        
        /**
         * Sets the "value" element
         */
        void setValue(java.lang.String value);
        
        /**
         * Sets (as xml) the "value" element
         */
        void xsetValue(org.apache.xmlbeans.XmlString value);
        
        /**
         * Unsets the "value" element
         */
        void unsetValue();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem newInstance() {
              return (uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.gov.ofwat.model2.ValidationRuleItemDocument.ValidationRuleItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.gov.ofwat.model2.ValidationRuleItemDocument newInstance() {
          return (uk.gov.ofwat.model2.ValidationRuleItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.gov.ofwat.model2.ValidationRuleItemDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.gov.ofwat.model2.ValidationRuleItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.gov.ofwat.model2.ValidationRuleItemDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ValidationRuleItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.gov.ofwat.model2.ValidationRuleItemDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ValidationRuleItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.gov.ofwat.model2.ValidationRuleItemDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ValidationRuleItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.gov.ofwat.model2.ValidationRuleItemDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ValidationRuleItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.gov.ofwat.model2.ValidationRuleItemDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ValidationRuleItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.gov.ofwat.model2.ValidationRuleItemDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ValidationRuleItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.gov.ofwat.model2.ValidationRuleItemDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ValidationRuleItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.gov.ofwat.model2.ValidationRuleItemDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ValidationRuleItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.gov.ofwat.model2.ValidationRuleItemDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ValidationRuleItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.gov.ofwat.model2.ValidationRuleItemDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ValidationRuleItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.gov.ofwat.model2.ValidationRuleItemDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ValidationRuleItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.gov.ofwat.model2.ValidationRuleItemDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ValidationRuleItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.gov.ofwat.model2.ValidationRuleItemDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ValidationRuleItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.gov.ofwat.model2.ValidationRuleItemDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ValidationRuleItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.ValidationRuleItemDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.ValidationRuleItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.ValidationRuleItemDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.ValidationRuleItemDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
