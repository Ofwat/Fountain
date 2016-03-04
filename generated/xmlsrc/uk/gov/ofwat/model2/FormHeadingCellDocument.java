/*
 * An XML document type.
 * Localname: form-heading-cell
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.FormHeadingCellDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2;


/**
 * A document containing one form-heading-cell(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public interface FormHeadingCellDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(FormHeadingCellDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("formheadingcelleb81doctype");
    
    /**
     * Gets the "form-heading-cell" element
     */
    uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell getFormHeadingCell();
    
    /**
     * Sets the "form-heading-cell" element
     */
    void setFormHeadingCell(uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell formHeadingCell);
    
    /**
     * Appends and returns a new empty "form-heading-cell" element
     */
    uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell addNewFormHeadingCell();
    
    /**
     * An XML form-heading-cell(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public interface FormHeadingCell extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(FormHeadingCell.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("formheadingcell3d1felemtype");
        
        /**
         * Gets the "text" element
         */
        java.lang.String getText();
        
        /**
         * Gets (as xml) the "text" element
         */
        org.apache.xmlbeans.XmlString xgetText();
        
        /**
         * True if has "text" element
         */
        boolean isSetText();
        
        /**
         * Sets the "text" element
         */
        void setText(java.lang.String text);
        
        /**
         * Sets (as xml) the "text" element
         */
        void xsetText(org.apache.xmlbeans.XmlString text);
        
        /**
         * Unsets the "text" element
         */
        void unsetText();
        
        /**
         * Gets the "use-line-number" element
         */
        boolean getUseLineNumber();
        
        /**
         * Gets (as xml) the "use-line-number" element
         */
        org.apache.xmlbeans.XmlBoolean xgetUseLineNumber();
        
        /**
         * True if has "use-line-number" element
         */
        boolean isSetUseLineNumber();
        
        /**
         * Sets the "use-line-number" element
         */
        void setUseLineNumber(boolean useLineNumber);
        
        /**
         * Sets (as xml) the "use-line-number" element
         */
        void xsetUseLineNumber(org.apache.xmlbeans.XmlBoolean useLineNumber);
        
        /**
         * Unsets the "use-line-number" element
         */
        void unsetUseLineNumber();
        
        /**
         * Gets the "use-item-code" element
         */
        boolean getUseItemCode();
        
        /**
         * Gets (as xml) the "use-item-code" element
         */
        org.apache.xmlbeans.XmlBoolean xgetUseItemCode();
        
        /**
         * True if has "use-item-code" element
         */
        boolean isSetUseItemCode();
        
        /**
         * Sets the "use-item-code" element
         */
        void setUseItemCode(boolean useItemCode);
        
        /**
         * Sets (as xml) the "use-item-code" element
         */
        void xsetUseItemCode(org.apache.xmlbeans.XmlBoolean useItemCode);
        
        /**
         * Unsets the "use-item-code" element
         */
        void unsetUseItemCode();
        
        /**
         * Gets the "use-item-description" element
         */
        boolean getUseItemDescription();
        
        /**
         * Gets (as xml) the "use-item-description" element
         */
        org.apache.xmlbeans.XmlBoolean xgetUseItemDescription();
        
        /**
         * True if has "use-item-description" element
         */
        boolean isSetUseItemDescription();
        
        /**
         * Sets the "use-item-description" element
         */
        void setUseItemDescription(boolean useItemDescription);
        
        /**
         * Sets (as xml) the "use-item-description" element
         */
        void xsetUseItemDescription(org.apache.xmlbeans.XmlBoolean useItemDescription);
        
        /**
         * Unsets the "use-item-description" element
         */
        void unsetUseItemDescription();
        
        /**
         * Gets the "use-unit" element
         */
        boolean getUseUnit();
        
        /**
         * Gets (as xml) the "use-unit" element
         */
        org.apache.xmlbeans.XmlBoolean xgetUseUnit();
        
        /**
         * True if has "use-unit" element
         */
        boolean isSetUseUnit();
        
        /**
         * Sets the "use-unit" element
         */
        void setUseUnit(boolean useUnit);
        
        /**
         * Sets (as xml) the "use-unit" element
         */
        void xsetUseUnit(org.apache.xmlbeans.XmlBoolean useUnit);
        
        /**
         * Unsets the "use-unit" element
         */
        void unsetUseUnit();
        
        /**
         * Gets the "use-year-code" element
         */
        boolean getUseYearCode();
        
        /**
         * Gets (as xml) the "use-year-code" element
         */
        org.apache.xmlbeans.XmlBoolean xgetUseYearCode();
        
        /**
         * True if has "use-year-code" element
         */
        boolean isSetUseYearCode();
        
        /**
         * Sets the "use-year-code" element
         */
        void setUseYearCode(boolean useYearCode);
        
        /**
         * Sets (as xml) the "use-year-code" element
         */
        void xsetUseYearCode(org.apache.xmlbeans.XmlBoolean useYearCode);
        
        /**
         * Unsets the "use-year-code" element
         */
        void unsetUseYearCode();
        
        /**
         * Gets the "use-confidence-grades" element
         */
        boolean getUseConfidenceGrades();
        
        /**
         * Gets (as xml) the "use-confidence-grades" element
         */
        org.apache.xmlbeans.XmlBoolean xgetUseConfidenceGrades();
        
        /**
         * True if has "use-confidence-grades" element
         */
        boolean isSetUseConfidenceGrades();
        
        /**
         * Sets the "use-confidence-grades" element
         */
        void setUseConfidenceGrades(boolean useConfidenceGrades);
        
        /**
         * Sets (as xml) the "use-confidence-grades" element
         */
        void xsetUseConfidenceGrades(org.apache.xmlbeans.XmlBoolean useConfidenceGrades);
        
        /**
         * Unsets the "use-confidence-grades" element
         */
        void unsetUseConfidenceGrades();
        
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
         * Gets the "item-code" element
         */
        java.lang.String getItemCode();
        
        /**
         * Gets (as xml) the "item-code" element
         */
        org.apache.xmlbeans.XmlString xgetItemCode();
        
        /**
         * True if has "item-code" element
         */
        boolean isSetItemCode();
        
        /**
         * Sets the "item-code" element
         */
        void setItemCode(java.lang.String itemCode);
        
        /**
         * Sets (as xml) the "item-code" element
         */
        void xsetItemCode(org.apache.xmlbeans.XmlString itemCode);
        
        /**
         * Unsets the "item-code" element
         */
        void unsetItemCode();
        
        /**
         * Gets the "year-code" element
         */
        java.lang.String getYearCode();
        
        /**
         * Gets (as xml) the "year-code" element
         */
        org.apache.xmlbeans.XmlString xgetYearCode();
        
        /**
         * True if has "year-code" element
         */
        boolean isSetYearCode();
        
        /**
         * Sets the "year-code" element
         */
        void setYearCode(java.lang.String yearCode);
        
        /**
         * Sets (as xml) the "year-code" element
         */
        void xsetYearCode(org.apache.xmlbeans.XmlString yearCode);
        
        /**
         * Unsets the "year-code" element
         */
        void unsetYearCode();
        
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
         * Gets the "cell-code" element
         */
        java.lang.String getCellCode();
        
        /**
         * Gets (as xml) the "cell-code" element
         */
        org.apache.xmlbeans.XmlString xgetCellCode();
        
        /**
         * True if has "cell-code" element
         */
        boolean isSetCellCode();
        
        /**
         * Sets the "cell-code" element
         */
        void setCellCode(java.lang.String cellCode);
        
        /**
         * Sets (as xml) the "cell-code" element
         */
        void xsetCellCode(org.apache.xmlbeans.XmlString cellCode);
        
        /**
         * Unsets the "cell-code" element
         */
        void unsetCellCode();
        
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
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell newInstance() {
              return (uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.gov.ofwat.model2.FormHeadingCellDocument newInstance() {
          return (uk.gov.ofwat.model2.FormHeadingCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.gov.ofwat.model2.FormHeadingCellDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.gov.ofwat.model2.FormHeadingCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.gov.ofwat.model2.FormHeadingCellDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.FormHeadingCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.gov.ofwat.model2.FormHeadingCellDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.FormHeadingCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.gov.ofwat.model2.FormHeadingCellDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormHeadingCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.gov.ofwat.model2.FormHeadingCellDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormHeadingCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.gov.ofwat.model2.FormHeadingCellDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormHeadingCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.gov.ofwat.model2.FormHeadingCellDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormHeadingCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.gov.ofwat.model2.FormHeadingCellDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormHeadingCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.gov.ofwat.model2.FormHeadingCellDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormHeadingCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.gov.ofwat.model2.FormHeadingCellDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormHeadingCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.gov.ofwat.model2.FormHeadingCellDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.FormHeadingCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.gov.ofwat.model2.FormHeadingCellDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.FormHeadingCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.gov.ofwat.model2.FormHeadingCellDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.FormHeadingCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.gov.ofwat.model2.FormHeadingCellDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.FormHeadingCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.gov.ofwat.model2.FormHeadingCellDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.FormHeadingCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.FormHeadingCellDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.FormHeadingCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.FormHeadingCellDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.FormHeadingCellDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
