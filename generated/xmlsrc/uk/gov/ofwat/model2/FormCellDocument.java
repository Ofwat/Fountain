/*
 * An XML document type.
 * Localname: form-cell
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.FormCellDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2;


/**
 * A document containing one form-cell(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public interface FormCellDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(FormCellDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("formcellcdccdoctype");
    
    /**
     * Gets the "form-cell" element
     */
    uk.gov.ofwat.model2.FormCellDocument.FormCell getFormCell();
    
    /**
     * Sets the "form-cell" element
     */
    void setFormCell(uk.gov.ofwat.model2.FormCellDocument.FormCell formCell);
    
    /**
     * Appends and returns a new empty "form-cell" element
     */
    uk.gov.ofwat.model2.FormCellDocument.FormCell addNewFormCell();
    
    /**
     * An XML form-cell(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public interface FormCell extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(FormCell.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("formcell7635elemtype");
        
        /**
         * Gets the "cell-code" element
         */
        java.lang.String getCellCode();
        
        /**
         * Gets (as xml) the "cell-code" element
         */
        org.apache.xmlbeans.XmlString xgetCellCode();
        
        /**
         * Sets the "cell-code" element
         */
        void setCellCode(java.lang.String cellCode);
        
        /**
         * Sets (as xml) the "cell-code" element
         */
        void xsetCellCode(org.apache.xmlbeans.XmlString cellCode);
        
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
         * Gets the "input-confidence-grade" element
         */
        boolean getInputConfidenceGrade();
        
        /**
         * Gets (as xml) the "input-confidence-grade" element
         */
        org.apache.xmlbeans.XmlBoolean xgetInputConfidenceGrade();
        
        /**
         * True if has "input-confidence-grade" element
         */
        boolean isSetInputConfidenceGrade();
        
        /**
         * Sets the "input-confidence-grade" element
         */
        void setInputConfidenceGrade(boolean inputConfidenceGrade);
        
        /**
         * Sets (as xml) the "input-confidence-grade" element
         */
        void xsetInputConfidenceGrade(org.apache.xmlbeans.XmlBoolean inputConfidenceGrade);
        
        /**
         * Unsets the "input-confidence-grade" element
         */
        void unsetInputConfidenceGrade();
        
        /**
         * Gets the "confidence-grade-input-code" element
         */
        java.lang.String getConfidenceGradeInputCode();
        
        /**
         * Gets (as xml) the "confidence-grade-input-code" element
         */
        org.apache.xmlbeans.XmlString xgetConfidenceGradeInputCode();
        
        /**
         * True if has "confidence-grade-input-code" element
         */
        boolean isSetConfidenceGradeInputCode();
        
        /**
         * Sets the "confidence-grade-input-code" element
         */
        void setConfidenceGradeInputCode(java.lang.String confidenceGradeInputCode);
        
        /**
         * Sets (as xml) the "confidence-grade-input-code" element
         */
        void xsetConfidenceGradeInputCode(org.apache.xmlbeans.XmlString confidenceGradeInputCode);
        
        /**
         * Unsets the "confidence-grade-input-code" element
         */
        void unsetConfidenceGradeInputCode();
        
        /**
         * Gets the "row-heading-source" element
         */
        boolean getRowHeadingSource();
        
        /**
         * Gets (as xml) the "row-heading-source" element
         */
        org.apache.xmlbeans.XmlBoolean xgetRowHeadingSource();
        
        /**
         * True if has "row-heading-source" element
         */
        boolean isSetRowHeadingSource();
        
        /**
         * Sets the "row-heading-source" element
         */
        void setRowHeadingSource(boolean rowHeadingSource);
        
        /**
         * Sets (as xml) the "row-heading-source" element
         */
        void xsetRowHeadingSource(org.apache.xmlbeans.XmlBoolean rowHeadingSource);
        
        /**
         * Unsets the "row-heading-source" element
         */
        void unsetRowHeadingSource();
        
        /**
         * Gets the "column-heading-source" element
         */
        boolean getColumnHeadingSource();
        
        /**
         * Gets (as xml) the "column-heading-source" element
         */
        org.apache.xmlbeans.XmlBoolean xgetColumnHeadingSource();
        
        /**
         * True if has "column-heading-source" element
         */
        boolean isSetColumnHeadingSource();
        
        /**
         * Sets the "column-heading-source" element
         */
        void setColumnHeadingSource(boolean columnHeadingSource);
        
        /**
         * Sets (as xml) the "column-heading-source" element
         */
        void xsetColumnHeadingSource(org.apache.xmlbeans.XmlBoolean columnHeadingSource);
        
        /**
         * Unsets the "column-heading-source" element
         */
        void unsetColumnHeadingSource();
        
        /**
         * Gets the "group-description-code" element
         */
        java.lang.String getGroupDescriptionCode();
        
        /**
         * Gets (as xml) the "group-description-code" element
         */
        org.apache.xmlbeans.XmlString xgetGroupDescriptionCode();
        
        /**
         * True if has "group-description-code" element
         */
        boolean isSetGroupDescriptionCode();
        
        /**
         * Sets the "group-description-code" element
         */
        void setGroupDescriptionCode(java.lang.String groupDescriptionCode);
        
        /**
         * Sets (as xml) the "group-description-code" element
         */
        void xsetGroupDescriptionCode(org.apache.xmlbeans.XmlString groupDescriptionCode);
        
        /**
         * Unsets the "group-description-code" element
         */
        void unsetGroupDescriptionCode();
        
        /**
         * Gets the "row" element
         */
        java.lang.String getRow();
        
        /**
         * Gets (as xml) the "row" element
         */
        org.apache.xmlbeans.XmlString xgetRow();
        
        /**
         * Sets the "row" element
         */
        void setRow(java.lang.String row);
        
        /**
         * Sets (as xml) the "row" element
         */
        void xsetRow(org.apache.xmlbeans.XmlString row);
        
        /**
         * Gets the "column" element
         */
        java.lang.String getColumn();
        
        /**
         * Gets (as xml) the "column" element
         */
        org.apache.xmlbeans.XmlString xgetColumn();
        
        /**
         * Sets the "column" element
         */
        void setColumn(java.lang.String column);
        
        /**
         * Sets (as xml) the "column" element
         */
        void xsetColumn(org.apache.xmlbeans.XmlString column);
        
        /**
         * Gets the "row-span" element
         */
        java.lang.String getRowSpan();
        
        /**
         * Gets (as xml) the "row-span" element
         */
        org.apache.xmlbeans.XmlString xgetRowSpan();
        
        /**
         * True if has "row-span" element
         */
        boolean isSetRowSpan();
        
        /**
         * Sets the "row-span" element
         */
        void setRowSpan(java.lang.String rowSpan);
        
        /**
         * Sets (as xml) the "row-span" element
         */
        void xsetRowSpan(org.apache.xmlbeans.XmlString rowSpan);
        
        /**
         * Unsets the "row-span" element
         */
        void unsetRowSpan();
        
        /**
         * Gets the "column-span" element
         */
        java.lang.String getColumnSpan();
        
        /**
         * Gets (as xml) the "column-span" element
         */
        org.apache.xmlbeans.XmlString xgetColumnSpan();
        
        /**
         * True if has "column-span" element
         */
        boolean isSetColumnSpan();
        
        /**
         * Sets the "column-span" element
         */
        void setColumnSpan(java.lang.String columnSpan);
        
        /**
         * Sets (as xml) the "column-span" element
         */
        void xsetColumnSpan(org.apache.xmlbeans.XmlString columnSpan);
        
        /**
         * Unsets the "column-span" element
         */
        void unsetColumnSpan();
        
        /**
         * Gets the "width" element
         */
        java.lang.String getWidth();
        
        /**
         * Gets (as xml) the "width" element
         */
        org.apache.xmlbeans.XmlString xgetWidth();
        
        /**
         * True if has "width" element
         */
        boolean isSetWidth();
        
        /**
         * Sets the "width" element
         */
        void setWidth(java.lang.String width);
        
        /**
         * Sets (as xml) the "width" element
         */
        void xsetWidth(org.apache.xmlbeans.XmlString width);
        
        /**
         * Unsets the "width" element
         */
        void unsetWidth();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.gov.ofwat.model2.FormCellDocument.FormCell newInstance() {
              return (uk.gov.ofwat.model2.FormCellDocument.FormCell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.gov.ofwat.model2.FormCellDocument.FormCell newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.gov.ofwat.model2.FormCellDocument.FormCell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.gov.ofwat.model2.FormCellDocument newInstance() {
          return (uk.gov.ofwat.model2.FormCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.gov.ofwat.model2.FormCellDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.gov.ofwat.model2.FormCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.gov.ofwat.model2.FormCellDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.FormCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.gov.ofwat.model2.FormCellDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.FormCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.gov.ofwat.model2.FormCellDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.gov.ofwat.model2.FormCellDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.gov.ofwat.model2.FormCellDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.gov.ofwat.model2.FormCellDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.gov.ofwat.model2.FormCellDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.gov.ofwat.model2.FormCellDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.gov.ofwat.model2.FormCellDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.gov.ofwat.model2.FormCellDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.gov.ofwat.model2.FormCellDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.FormCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.gov.ofwat.model2.FormCellDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.FormCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.gov.ofwat.model2.FormCellDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.FormCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.gov.ofwat.model2.FormCellDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.FormCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.FormCellDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.FormCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.FormCellDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.FormCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
