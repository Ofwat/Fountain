/*
 * An XML document type.
 * Localname: linedetails
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.LinedetailsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2;


/**
 * A document containing one linedetails(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public interface LinedetailsDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(LinedetailsDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("linedetails8429doctype");
    
    /**
     * Gets the "linedetails" element
     */
    uk.gov.ofwat.model2.LinedetailsDocument.Linedetails getLinedetails();
    
    /**
     * Sets the "linedetails" element
     */
    void setLinedetails(uk.gov.ofwat.model2.LinedetailsDocument.Linedetails linedetails);
    
    /**
     * Appends and returns a new empty "linedetails" element
     */
    uk.gov.ofwat.model2.LinedetailsDocument.Linedetails addNewLinedetails();
    
    /**
     * An XML linedetails(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public interface Linedetails extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Linedetails.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("linedetails4a2felemtype");
        
        /**
         * Gets the "heading" element
         */
        boolean getHeading();
        
        /**
         * Gets (as xml) the "heading" element
         */
        org.apache.xmlbeans.XmlBoolean xgetHeading();
        
        /**
         * True if has "heading" element
         */
        boolean isSetHeading();
        
        /**
         * Sets the "heading" element
         */
        void setHeading(boolean heading);
        
        /**
         * Sets (as xml) the "heading" element
         */
        void xsetHeading(org.apache.xmlbeans.XmlBoolean heading);
        
        /**
         * Unsets the "heading" element
         */
        void unsetHeading();
        
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
         * Gets the "description" element
         */
        java.lang.String getDescription();
        
        /**
         * Gets (as xml) the "description" element
         */
        org.apache.xmlbeans.XmlString xgetDescription();
        
        /**
         * True if has "description" element
         */
        boolean isSetDescription();
        
        /**
         * Sets the "description" element
         */
        void setDescription(java.lang.String description);
        
        /**
         * Sets (as xml) the "description" element
         */
        void xsetDescription(org.apache.xmlbeans.XmlString description);
        
        /**
         * Unsets the "description" element
         */
        void unsetDescription();
        
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
         * Gets the "linenumber" element
         */
        java.lang.String getLinenumber();
        
        /**
         * Gets (as xml) the "linenumber" element
         */
        org.apache.xmlbeans.XmlString xgetLinenumber();
        
        /**
         * True if has "linenumber" element
         */
        boolean isSetLinenumber();
        
        /**
         * Sets the "linenumber" element
         */
        void setLinenumber(java.lang.String linenumber);
        
        /**
         * Sets (as xml) the "linenumber" element
         */
        void xsetLinenumber(org.apache.xmlbeans.XmlString linenumber);
        
        /**
         * Unsets the "linenumber" element
         */
        void unsetLinenumber();
        
        /**
         * Gets the "ruletext" element
         */
        java.lang.String getRuletext();
        
        /**
         * Gets (as xml) the "ruletext" element
         */
        org.apache.xmlbeans.XmlString xgetRuletext();
        
        /**
         * True if has "ruletext" element
         */
        boolean isSetRuletext();
        
        /**
         * Sets the "ruletext" element
         */
        void setRuletext(java.lang.String ruletext);
        
        /**
         * Sets (as xml) the "ruletext" element
         */
        void xsetRuletext(org.apache.xmlbeans.XmlString ruletext);
        
        /**
         * Unsets the "ruletext" element
         */
        void unsetRuletext();
        
        /**
         * Gets the "type" element
         */
        java.lang.String getType();
        
        /**
         * Gets (as xml) the "type" element
         */
        org.apache.xmlbeans.XmlString xgetType();
        
        /**
         * True if has "type" element
         */
        boolean isSetType();
        
        /**
         * Sets the "type" element
         */
        void setType(java.lang.String type);
        
        /**
         * Sets (as xml) the "type" element
         */
        void xsetType(org.apache.xmlbeans.XmlString type);
        
        /**
         * Unsets the "type" element
         */
        void unsetType();
        
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
         * Gets the "use-confidence-grade" element
         */
        boolean getUseConfidenceGrade();
        
        /**
         * Gets (as xml) the "use-confidence-grade" element
         */
        org.apache.xmlbeans.XmlBoolean xgetUseConfidenceGrade();
        
        /**
         * True if has "use-confidence-grade" element
         */
        boolean isSetUseConfidenceGrade();
        
        /**
         * Sets the "use-confidence-grade" element
         */
        void setUseConfidenceGrade(boolean useConfidenceGrade);
        
        /**
         * Sets (as xml) the "use-confidence-grade" element
         */
        void xsetUseConfidenceGrade(org.apache.xmlbeans.XmlBoolean useConfidenceGrade);
        
        /**
         * Unsets the "use-confidence-grade" element
         */
        void unsetUseConfidenceGrade();
        
        /**
         * Gets the "validation-rule-code" element
         */
        java.lang.String getValidationRuleCode();
        
        /**
         * Gets (as xml) the "validation-rule-code" element
         */
        org.apache.xmlbeans.XmlString xgetValidationRuleCode();
        
        /**
         * True if has "validation-rule-code" element
         */
        boolean isSetValidationRuleCode();
        
        /**
         * Sets the "validation-rule-code" element
         */
        void setValidationRuleCode(java.lang.String validationRuleCode);
        
        /**
         * Sets (as xml) the "validation-rule-code" element
         */
        void xsetValidationRuleCode(org.apache.xmlbeans.XmlString validationRuleCode);
        
        /**
         * Unsets the "validation-rule-code" element
         */
        void unsetValidationRuleCode();
        
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
         * Gets the "unit" element
         */
        java.lang.String getUnit();
        
        /**
         * Gets (as xml) the "unit" element
         */
        org.apache.xmlbeans.XmlString xgetUnit();
        
        /**
         * True if has "unit" element
         */
        boolean isSetUnit();
        
        /**
         * Sets the "unit" element
         */
        void setUnit(java.lang.String unit);
        
        /**
         * Sets (as xml) the "unit" element
         */
        void xsetUnit(org.apache.xmlbeans.XmlString unit);
        
        /**
         * Unsets the "unit" element
         */
        void unsetUnit();
        
        /**
         * Gets the "decimal-places" element
         */
        java.math.BigInteger getDecimalPlaces();
        
        /**
         * Gets (as xml) the "decimal-places" element
         */
        org.apache.xmlbeans.XmlNonNegativeInteger xgetDecimalPlaces();
        
        /**
         * True if has "decimal-places" element
         */
        boolean isSetDecimalPlaces();
        
        /**
         * Sets the "decimal-places" element
         */
        void setDecimalPlaces(java.math.BigInteger decimalPlaces);
        
        /**
         * Sets (as xml) the "decimal-places" element
         */
        void xsetDecimalPlaces(org.apache.xmlbeans.XmlNonNegativeInteger decimalPlaces);
        
        /**
         * Unsets the "decimal-places" element
         */
        void unsetDecimalPlaces();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.gov.ofwat.model2.LinedetailsDocument.Linedetails newInstance() {
              return (uk.gov.ofwat.model2.LinedetailsDocument.Linedetails) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.gov.ofwat.model2.LinedetailsDocument.Linedetails newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.gov.ofwat.model2.LinedetailsDocument.Linedetails) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.gov.ofwat.model2.LinedetailsDocument newInstance() {
          return (uk.gov.ofwat.model2.LinedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.gov.ofwat.model2.LinedetailsDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.gov.ofwat.model2.LinedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.gov.ofwat.model2.LinedetailsDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.LinedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.gov.ofwat.model2.LinedetailsDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.LinedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.gov.ofwat.model2.LinedetailsDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.LinedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.gov.ofwat.model2.LinedetailsDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.LinedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.gov.ofwat.model2.LinedetailsDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.LinedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.gov.ofwat.model2.LinedetailsDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.LinedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.gov.ofwat.model2.LinedetailsDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.LinedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.gov.ofwat.model2.LinedetailsDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.LinedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.gov.ofwat.model2.LinedetailsDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.LinedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.gov.ofwat.model2.LinedetailsDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.LinedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.gov.ofwat.model2.LinedetailsDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.LinedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.gov.ofwat.model2.LinedetailsDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.LinedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.gov.ofwat.model2.LinedetailsDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.LinedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.gov.ofwat.model2.LinedetailsDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.LinedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.LinedetailsDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.LinedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.LinedetailsDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.LinedetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
