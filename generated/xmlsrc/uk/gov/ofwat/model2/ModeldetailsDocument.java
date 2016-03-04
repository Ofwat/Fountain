/*
 * An XML document type.
 * Localname: modeldetails
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.ModeldetailsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2;


/**
 * A document containing one modeldetails(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public interface ModeldetailsDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ModeldetailsDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("modeldetails244cdoctype");
    
    /**
     * Gets the "modeldetails" element
     */
    uk.gov.ofwat.model2.ModeldetailsDocument.Modeldetails getModeldetails();
    
    /**
     * Sets the "modeldetails" element
     */
    void setModeldetails(uk.gov.ofwat.model2.ModeldetailsDocument.Modeldetails modeldetails);
    
    /**
     * Appends and returns a new empty "modeldetails" element
     */
    uk.gov.ofwat.model2.ModeldetailsDocument.Modeldetails addNewModeldetails();
    
    /**
     * An XML modeldetails(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public interface Modeldetails extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Modeldetails.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("modeldetailsd321elemtype");
        
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
         * Gets the "version" element
         */
        java.lang.String getVersion();
        
        /**
         * Gets (as xml) the "version" element
         */
        org.apache.xmlbeans.XmlString xgetVersion();
        
        /**
         * Sets the "version" element
         */
        void setVersion(java.lang.String version);
        
        /**
         * Sets (as xml) the "version" element
         */
        void xsetVersion(org.apache.xmlbeans.XmlString version);
        
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
         * Gets the "base-year-code" element
         */
        java.lang.String getBaseYearCode();
        
        /**
         * Gets (as xml) the "base-year-code" element
         */
        org.apache.xmlbeans.XmlString xgetBaseYearCode();
        
        /**
         * True if has "base-year-code" element
         */
        boolean isSetBaseYearCode();
        
        /**
         * Sets the "base-year-code" element
         */
        void setBaseYearCode(java.lang.String baseYearCode);
        
        /**
         * Sets (as xml) the "base-year-code" element
         */
        void xsetBaseYearCode(org.apache.xmlbeans.XmlString baseYearCode);
        
        /**
         * Unsets the "base-year-code" element
         */
        void unsetBaseYearCode();
        
        /**
         * Gets the "report-year-code" element
         */
        java.lang.String getReportYearCode();
        
        /**
         * Gets (as xml) the "report-year-code" element
         */
        org.apache.xmlbeans.XmlString xgetReportYearCode();
        
        /**
         * True if has "report-year-code" element
         */
        boolean isSetReportYearCode();
        
        /**
         * Sets the "report-year-code" element
         */
        void setReportYearCode(java.lang.String reportYearCode);
        
        /**
         * Sets (as xml) the "report-year-code" element
         */
        void xsetReportYearCode(org.apache.xmlbeans.XmlString reportYearCode);
        
        /**
         * Unsets the "report-year-code" element
         */
        void unsetReportYearCode();
        
        /**
         * Gets the "allow-data-changes" element
         */
        boolean getAllowDataChanges();
        
        /**
         * Gets (as xml) the "allow-data-changes" element
         */
        org.apache.xmlbeans.XmlBoolean xgetAllowDataChanges();
        
        /**
         * True if has "allow-data-changes" element
         */
        boolean isSetAllowDataChanges();
        
        /**
         * Sets the "allow-data-changes" element
         */
        void setAllowDataChanges(boolean allowDataChanges);
        
        /**
         * Sets (as xml) the "allow-data-changes" element
         */
        void xsetAllowDataChanges(org.apache.xmlbeans.XmlBoolean allowDataChanges);
        
        /**
         * Unsets the "allow-data-changes" element
         */
        void unsetAllowDataChanges();
        
        /**
         * Gets the "model-family-code" element
         */
        java.lang.String getModelFamilyCode();
        
        /**
         * Gets (as xml) the "model-family-code" element
         */
        org.apache.xmlbeans.XmlString xgetModelFamilyCode();
        
        /**
         * True if has "model-family-code" element
         */
        boolean isSetModelFamilyCode();
        
        /**
         * Sets the "model-family-code" element
         */
        void setModelFamilyCode(java.lang.String modelFamilyCode);
        
        /**
         * Sets (as xml) the "model-family-code" element
         */
        void xsetModelFamilyCode(org.apache.xmlbeans.XmlString modelFamilyCode);
        
        /**
         * Unsets the "model-family-code" element
         */
        void unsetModelFamilyCode();
        
        /**
         * Gets the "model-family-parent" element
         */
        boolean getModelFamilyParent();
        
        /**
         * Gets (as xml) the "model-family-parent" element
         */
        org.apache.xmlbeans.XmlBoolean xgetModelFamilyParent();
        
        /**
         * Sets the "model-family-parent" element
         */
        void setModelFamilyParent(boolean modelFamilyParent);
        
        /**
         * Sets (as xml) the "model-family-parent" element
         */
        void xsetModelFamilyParent(org.apache.xmlbeans.XmlBoolean modelFamilyParent);
        
        /**
         * Gets the "display-order" element
         */
        java.math.BigInteger getDisplayOrder();
        
        /**
         * Gets (as xml) the "display-order" element
         */
        org.apache.xmlbeans.XmlInteger xgetDisplayOrder();
        
        /**
         * Sets the "display-order" element
         */
        void setDisplayOrder(java.math.BigInteger displayOrder);
        
        /**
         * Sets (as xml) the "display-order" element
         */
        void xsetDisplayOrder(org.apache.xmlbeans.XmlInteger displayOrder);
        
        /**
         * Gets the "branch-tag" element
         */
        java.lang.String getBranchTag();
        
        /**
         * Gets (as xml) the "branch-tag" element
         */
        org.apache.xmlbeans.XmlString xgetBranchTag();
        
        /**
         * True if has "branch-tag" element
         */
        boolean isSetBranchTag();
        
        /**
         * Sets the "branch-tag" element
         */
        void setBranchTag(java.lang.String branchTag);
        
        /**
         * Sets (as xml) the "branch-tag" element
         */
        void xsetBranchTag(org.apache.xmlbeans.XmlString branchTag);
        
        /**
         * Unsets the "branch-tag" element
         */
        void unsetBranchTag();
        
        /**
         * Gets the "run-code" element
         */
        java.lang.String getRunCode();
        
        /**
         * Gets (as xml) the "run-code" element
         */
        org.apache.xmlbeans.XmlString xgetRunCode();
        
        /**
         * True if has "run-code" element
         */
        boolean isSetRunCode();
        
        /**
         * Sets the "run-code" element
         */
        void setRunCode(java.lang.String runCode);
        
        /**
         * Sets (as xml) the "run-code" element
         */
        void xsetRunCode(org.apache.xmlbeans.XmlString runCode);
        
        /**
         * Unsets the "run-code" element
         */
        void unsetRunCode();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.gov.ofwat.model2.ModeldetailsDocument.Modeldetails newInstance() {
              return (uk.gov.ofwat.model2.ModeldetailsDocument.Modeldetails) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.gov.ofwat.model2.ModeldetailsDocument.Modeldetails newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.gov.ofwat.model2.ModeldetailsDocument.Modeldetails) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.gov.ofwat.model2.ModeldetailsDocument newInstance() {
          return (uk.gov.ofwat.model2.ModeldetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.gov.ofwat.model2.ModeldetailsDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.gov.ofwat.model2.ModeldetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.gov.ofwat.model2.ModeldetailsDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ModeldetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.gov.ofwat.model2.ModeldetailsDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ModeldetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.gov.ofwat.model2.ModeldetailsDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ModeldetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.gov.ofwat.model2.ModeldetailsDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ModeldetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.gov.ofwat.model2.ModeldetailsDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ModeldetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.gov.ofwat.model2.ModeldetailsDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ModeldetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.gov.ofwat.model2.ModeldetailsDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ModeldetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.gov.ofwat.model2.ModeldetailsDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ModeldetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.gov.ofwat.model2.ModeldetailsDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ModeldetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.gov.ofwat.model2.ModeldetailsDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ModeldetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.gov.ofwat.model2.ModeldetailsDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ModeldetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.gov.ofwat.model2.ModeldetailsDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ModeldetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.gov.ofwat.model2.ModeldetailsDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ModeldetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.gov.ofwat.model2.ModeldetailsDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ModeldetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.ModeldetailsDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.ModeldetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.ModeldetailsDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.ModeldetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
