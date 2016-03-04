/*
 * An XML document type.
 * Localname: model
 * Namespace: http://www.ofwat.gov.uk/model2
 * Java type: uk.gov.ofwat.model2.ModelDocument
 *
 * Automatically generated - do not modify.
 */
package uk.gov.ofwat.model2;


/**
 * A document containing one model(@http://www.ofwat.gov.uk/model2) element.
 *
 * This is a complex type.
 */
public interface ModelDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ModelDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("modela42edoctype");
    
    /**
     * Gets the "model" element
     */
    uk.gov.ofwat.model2.ModelDocument.Model getModel();
    
    /**
     * Sets the "model" element
     */
    void setModel(uk.gov.ofwat.model2.ModelDocument.Model model);
    
    /**
     * Appends and returns a new empty "model" element
     */
    uk.gov.ofwat.model2.ModelDocument.Model addNewModel();
    
    /**
     * An XML model(@http://www.ofwat.gov.uk/model2).
     *
     * This is a complex type.
     */
    public interface Model extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Model.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s99C7CDD6A5ED0574521CDCF62037D352").resolveHandle("model24f9elemtype");
        
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
         * Gets the "audits" element
         */
        uk.gov.ofwat.model2.AuditsDocument.Audits getAudits();
        
        /**
         * True if has "audits" element
         */
        boolean isSetAudits();
        
        /**
         * Sets the "audits" element
         */
        void setAudits(uk.gov.ofwat.model2.AuditsDocument.Audits audits);
        
        /**
         * Appends and returns a new empty "audits" element
         */
        uk.gov.ofwat.model2.AuditsDocument.Audits addNewAudits();
        
        /**
         * Unsets the "audits" element
         */
        void unsetAudits();
        
        /**
         * Gets the "items" element
         */
        uk.gov.ofwat.model2.ItemsDocument.Items getItems();
        
        /**
         * Sets the "items" element
         */
        void setItems(uk.gov.ofwat.model2.ItemsDocument.Items items);
        
        /**
         * Appends and returns a new empty "items" element
         */
        uk.gov.ofwat.model2.ItemsDocument.Items addNewItems();
        
        /**
         * Gets the "years" element
         */
        uk.gov.ofwat.model2.YearsDocument.Years getYears();
        
        /**
         * Sets the "years" element
         */
        void setYears(uk.gov.ofwat.model2.YearsDocument.Years years);
        
        /**
         * Appends and returns a new empty "years" element
         */
        uk.gov.ofwat.model2.YearsDocument.Years addNewYears();
        
        /**
         * Gets the "inputs" element
         */
        uk.gov.ofwat.model2.InputsDocument.Inputs getInputs();
        
        /**
         * Sets the "inputs" element
         */
        void setInputs(uk.gov.ofwat.model2.InputsDocument.Inputs inputs);
        
        /**
         * Appends and returns a new empty "inputs" element
         */
        uk.gov.ofwat.model2.InputsDocument.Inputs addNewInputs();
        
        /**
         * Gets the "headings" element
         */
        uk.gov.ofwat.model2.HeadingsDocument.Headings getHeadings();
        
        /**
         * True if has "headings" element
         */
        boolean isSetHeadings();
        
        /**
         * Sets the "headings" element
         */
        void setHeadings(uk.gov.ofwat.model2.HeadingsDocument.Headings headings);
        
        /**
         * Appends and returns a new empty "headings" element
         */
        uk.gov.ofwat.model2.HeadingsDocument.Headings addNewHeadings();
        
        /**
         * Unsets the "headings" element
         */
        void unsetHeadings();
        
        /**
         * Gets the "validation-rules" element
         */
        uk.gov.ofwat.model2.ValidationRulesDocument.ValidationRules getValidationRules();
        
        /**
         * True if has "validation-rules" element
         */
        boolean isSetValidationRules();
        
        /**
         * Sets the "validation-rules" element
         */
        void setValidationRules(uk.gov.ofwat.model2.ValidationRulesDocument.ValidationRules validationRules);
        
        /**
         * Appends and returns a new empty "validation-rules" element
         */
        uk.gov.ofwat.model2.ValidationRulesDocument.ValidationRules addNewValidationRules();
        
        /**
         * Unsets the "validation-rules" element
         */
        void unsetValidationRules();
        
        /**
         * Gets the "company-pages" element
         */
        uk.gov.ofwat.model2.CompanyPagesDocument.CompanyPages getCompanyPages();
        
        /**
         * True if has "company-pages" element
         */
        boolean isSetCompanyPages();
        
        /**
         * Sets the "company-pages" element
         */
        void setCompanyPages(uk.gov.ofwat.model2.CompanyPagesDocument.CompanyPages companyPages);
        
        /**
         * Appends and returns a new empty "company-pages" element
         */
        uk.gov.ofwat.model2.CompanyPagesDocument.CompanyPages addNewCompanyPages();
        
        /**
         * Unsets the "company-pages" element
         */
        void unsetCompanyPages();
        
        /**
         * Gets the "documents" element
         */
        uk.gov.ofwat.model2.DocumentsDocument.Documents getDocuments();
        
        /**
         * True if has "documents" element
         */
        boolean isSetDocuments();
        
        /**
         * Sets the "documents" element
         */
        void setDocuments(uk.gov.ofwat.model2.DocumentsDocument.Documents documents);
        
        /**
         * Appends and returns a new empty "documents" element
         */
        uk.gov.ofwat.model2.DocumentsDocument.Documents addNewDocuments();
        
        /**
         * Unsets the "documents" element
         */
        void unsetDocuments();
        
        /**
         * Gets the "pages" element
         */
        uk.gov.ofwat.model2.PagesDocument.Pages getPages();
        
        /**
         * Sets the "pages" element
         */
        void setPages(uk.gov.ofwat.model2.PagesDocument.Pages pages);
        
        /**
         * Appends and returns a new empty "pages" element
         */
        uk.gov.ofwat.model2.PagesDocument.Pages addNewPages();
        
        /**
         * Gets the "texts" element
         */
        uk.gov.ofwat.model2.TextsDocument.Texts getTexts();
        
        /**
         * True if has "texts" element
         */
        boolean isSetTexts();
        
        /**
         * Sets the "texts" element
         */
        void setTexts(uk.gov.ofwat.model2.TextsDocument.Texts texts);
        
        /**
         * Appends and returns a new empty "texts" element
         */
        uk.gov.ofwat.model2.TextsDocument.Texts addNewTexts();
        
        /**
         * Unsets the "texts" element
         */
        void unsetTexts();
        
        /**
         * Gets the "macros" element
         */
        uk.gov.ofwat.model2.MacrosDocument.Macros getMacros();
        
        /**
         * True if has "macros" element
         */
        boolean isSetMacros();
        
        /**
         * Sets the "macros" element
         */
        void setMacros(uk.gov.ofwat.model2.MacrosDocument.Macros macros);
        
        /**
         * Appends and returns a new empty "macros" element
         */
        uk.gov.ofwat.model2.MacrosDocument.Macros addNewMacros();
        
        /**
         * Unsets the "macros" element
         */
        void unsetMacros();
        
        /**
         * Gets the "transfers" element
         */
        uk.gov.ofwat.model2.TransfersDocument.Transfers getTransfers();
        
        /**
         * True if has "transfers" element
         */
        boolean isSetTransfers();
        
        /**
         * Sets the "transfers" element
         */
        void setTransfers(uk.gov.ofwat.model2.TransfersDocument.Transfers transfers);
        
        /**
         * Appends and returns a new empty "transfers" element
         */
        uk.gov.ofwat.model2.TransfersDocument.Transfers addNewTransfers();
        
        /**
         * Unsets the "transfers" element
         */
        void unsetTransfers();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.gov.ofwat.model2.ModelDocument.Model newInstance() {
              return (uk.gov.ofwat.model2.ModelDocument.Model) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.gov.ofwat.model2.ModelDocument.Model newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.gov.ofwat.model2.ModelDocument.Model) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.gov.ofwat.model2.ModelDocument newInstance() {
          return (uk.gov.ofwat.model2.ModelDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.gov.ofwat.model2.ModelDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.gov.ofwat.model2.ModelDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.gov.ofwat.model2.ModelDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ModelDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.gov.ofwat.model2.ModelDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ModelDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.gov.ofwat.model2.ModelDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ModelDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.gov.ofwat.model2.ModelDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ModelDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.gov.ofwat.model2.ModelDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ModelDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.gov.ofwat.model2.ModelDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ModelDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.gov.ofwat.model2.ModelDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ModelDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.gov.ofwat.model2.ModelDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ModelDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.gov.ofwat.model2.ModelDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ModelDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.gov.ofwat.model2.ModelDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.gov.ofwat.model2.ModelDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.gov.ofwat.model2.ModelDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ModelDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.gov.ofwat.model2.ModelDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ModelDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.gov.ofwat.model2.ModelDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ModelDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.gov.ofwat.model2.ModelDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.gov.ofwat.model2.ModelDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.ModelDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.ModelDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.gov.ofwat.model2.ModelDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.gov.ofwat.model2.ModelDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
