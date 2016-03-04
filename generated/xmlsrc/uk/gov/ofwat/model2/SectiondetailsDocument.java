/*
 * An XML document type.
 * Localname: sectiondetails
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.SectiondetailsDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2;


/**
 * A document containing one sectiondetails(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public interface SectiondetailsDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(SectiondetailsDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("sectiondetails1d48doctype");
    
    /**
     * Gets the "sectiondetails" element
     */
    uk.gov.ofwat.model2.SectiondetailsDocument.Sectiondetails getSectiondetails();
    
    /**
     * Sets the "sectiondetails" element
     */
    void setSectiondetails(uk.gov.ofwat.model2.SectiondetailsDocument.Sectiondetails sectiondetails);
    
    /**
     * Appends and returns a new empty "sectiondetails" element
     */
    uk.gov.ofwat.model2.SectiondetailsDocument.Sectiondetails addNewSectiondetails();
    
    /**
     * An XML sectiondetails(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public interface Sectiondetails extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Sectiondetails.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("sectiondetails9da1elemtype");
        
        /**
         * Gets the "display" element
         */
        java.lang.String getDisplay();
        
        /**
         * Gets (as xml) the "display" element
         */
        org.apache.xmlbeans.XmlString xgetDisplay();
        
        /**
         * Sets the "display" element
         */
        void setDisplay(java.lang.String display);
        
        /**
         * Sets (as xml) the "display" element
         */
        void xsetDisplay(org.apache.xmlbeans.XmlString display);
        
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
         * Gets the "grouptype" element
         */
        java.lang.String getGrouptype();
        
        /**
         * Gets (as xml) the "grouptype" element
         */
        org.apache.xmlbeans.XmlString xgetGrouptype();
        
        /**
         * True if has "grouptype" element
         */
        boolean isSetGrouptype();
        
        /**
         * Sets the "grouptype" element
         */
        void setGrouptype(java.lang.String grouptype);
        
        /**
         * Sets (as xml) the "grouptype" element
         */
        void xsetGrouptype(org.apache.xmlbeans.XmlString grouptype);
        
        /**
         * Unsets the "grouptype" element
         */
        void unsetGrouptype();
        
        /**
         * Gets the "use-line-number" element
         */
        boolean getUseLineNumber();
        
        /**
         * Gets (as xml) the "use-line-number" element
         */
        org.apache.xmlbeans.XmlBoolean xgetUseLineNumber();
        
        /**
         * Sets the "use-line-number" element
         */
        void setUseLineNumber(boolean useLineNumber);
        
        /**
         * Sets (as xml) the "use-line-number" element
         */
        void xsetUseLineNumber(org.apache.xmlbeans.XmlBoolean useLineNumber);
        
        /**
         * Gets the "use-item-code" element
         */
        boolean getUseItemCode();
        
        /**
         * Gets (as xml) the "use-item-code" element
         */
        org.apache.xmlbeans.XmlBoolean xgetUseItemCode();
        
        /**
         * Sets the "use-item-code" element
         */
        void setUseItemCode(boolean useItemCode);
        
        /**
         * Sets (as xml) the "use-item-code" element
         */
        void xsetUseItemCode(org.apache.xmlbeans.XmlBoolean useItemCode);
        
        /**
         * Gets the "use-item-description" element
         */
        boolean getUseItemDescription();
        
        /**
         * Gets (as xml) the "use-item-description" element
         */
        org.apache.xmlbeans.XmlBoolean xgetUseItemDescription();
        
        /**
         * Sets the "use-item-description" element
         */
        void setUseItemDescription(boolean useItemDescription);
        
        /**
         * Sets (as xml) the "use-item-description" element
         */
        void xsetUseItemDescription(org.apache.xmlbeans.XmlBoolean useItemDescription);
        
        /**
         * Gets the "use-unit" element
         */
        boolean getUseUnit();
        
        /**
         * Gets (as xml) the "use-unit" element
         */
        org.apache.xmlbeans.XmlBoolean xgetUseUnit();
        
        /**
         * Sets the "use-unit" element
         */
        void setUseUnit(boolean useUnit);
        
        /**
         * Sets (as xml) the "use-unit" element
         */
        void xsetUseUnit(org.apache.xmlbeans.XmlBoolean useUnit);
        
        /**
         * Gets the "use-year-code" element
         */
        boolean getUseYearCode();
        
        /**
         * Gets (as xml) the "use-year-code" element
         */
        org.apache.xmlbeans.XmlBoolean xgetUseYearCode();
        
        /**
         * Sets the "use-year-code" element
         */
        void setUseYearCode(boolean useYearCode);
        
        /**
         * Sets (as xml) the "use-year-code" element
         */
        void xsetUseYearCode(org.apache.xmlbeans.XmlBoolean useYearCode);
        
        /**
         * Gets the "use-confidence-grades" element
         */
        boolean getUseConfidenceGrades();
        
        /**
         * Gets (as xml) the "use-confidence-grades" element
         */
        org.apache.xmlbeans.XmlBoolean xgetUseConfidenceGrades();
        
        /**
         * Sets the "use-confidence-grades" element
         */
        void setUseConfidenceGrades(boolean useConfidenceGrades);
        
        /**
         * Sets (as xml) the "use-confidence-grades" element
         */
        void xsetUseConfidenceGrades(org.apache.xmlbeans.XmlBoolean useConfidenceGrades);
        
        /**
         * Gets the "allow-group-selection" element
         */
        boolean getAllowGroupSelection();
        
        /**
         * Gets (as xml) the "allow-group-selection" element
         */
        org.apache.xmlbeans.XmlBoolean xgetAllowGroupSelection();
        
        /**
         * True if has "allow-group-selection" element
         */
        boolean isSetAllowGroupSelection();
        
        /**
         * Sets the "allow-group-selection" element
         */
        void setAllowGroupSelection(boolean allowGroupSelection);
        
        /**
         * Sets (as xml) the "allow-group-selection" element
         */
        void xsetAllowGroupSelection(org.apache.xmlbeans.XmlBoolean allowGroupSelection);
        
        /**
         * Unsets the "allow-group-selection" element
         */
        void unsetAllowGroupSelection();
        
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
         * Gets the "section-type" element
         */
        java.lang.String getSectionType();
        
        /**
         * Gets (as xml) the "section-type" element
         */
        org.apache.xmlbeans.XmlString xgetSectionType();
        
        /**
         * True if has "section-type" element
         */
        boolean isSetSectionType();
        
        /**
         * Sets the "section-type" element
         */
        void setSectionType(java.lang.String sectionType);
        
        /**
         * Sets (as xml) the "section-type" element
         */
        void xsetSectionType(org.apache.xmlbeans.XmlString sectionType);
        
        /**
         * Unsets the "section-type" element
         */
        void unsetSectionType();
        
        /**
         * Gets the "item-code-column" element
         */
        java.math.BigInteger getItemCodeColumn();
        
        /**
         * Gets (as xml) the "item-code-column" element
         */
        org.apache.xmlbeans.XmlNonNegativeInteger xgetItemCodeColumn();
        
        /**
         * True if has "item-code-column" element
         */
        boolean isSetItemCodeColumn();
        
        /**
         * Sets the "item-code-column" element
         */
        void setItemCodeColumn(java.math.BigInteger itemCodeColumn);
        
        /**
         * Sets (as xml) the "item-code-column" element
         */
        void xsetItemCodeColumn(org.apache.xmlbeans.XmlNonNegativeInteger itemCodeColumn);
        
        /**
         * Unsets the "item-code-column" element
         */
        void unsetItemCodeColumn();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.gov.ofwat.model2.SectiondetailsDocument.Sectiondetails newInstance() {
              return (uk.gov.ofwat.model2.SectiondetailsDocument.Sectiondetails) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.gov.ofwat.model2.SectiondetailsDocument.Sectiondetails newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.gov.ofwat.model2.SectiondetailsDocument.Sectiondetails) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.gov.ofwat.model2.SectiondetailsDocument newInstance() {
          return (uk.gov.ofwat.model2.SectiondetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.gov.ofwat.model2.SectiondetailsDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.gov.ofwat.model2.SectiondetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.gov.ofwat.model2.SectiondetailsDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.SectiondetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.gov.ofwat.model2.SectiondetailsDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.SectiondetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.gov.ofwat.model2.SectiondetailsDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.SectiondetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.gov.ofwat.model2.SectiondetailsDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.SectiondetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.gov.ofwat.model2.SectiondetailsDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.SectiondetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.gov.ofwat.model2.SectiondetailsDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.SectiondetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.gov.ofwat.model2.SectiondetailsDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.SectiondetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.gov.ofwat.model2.SectiondetailsDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.SectiondetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.gov.ofwat.model2.SectiondetailsDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.SectiondetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.gov.ofwat.model2.SectiondetailsDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.SectiondetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.gov.ofwat.model2.SectiondetailsDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.SectiondetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.gov.ofwat.model2.SectiondetailsDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.SectiondetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.gov.ofwat.model2.SectiondetailsDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.SectiondetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.gov.ofwat.model2.SectiondetailsDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.SectiondetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.SectiondetailsDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.SectiondetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.SectiondetailsDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.SectiondetailsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
