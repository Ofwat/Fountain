<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page errorPage="../error.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ofwat" uri="http://www.ofwat.gov.uk" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<jsp:useBean id="results" class="uk.gov.ofwat.fountain.rest.dto.ImportResponseDto" scope="session"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload Table Results</title>
	<link rel="stylesheet" type="text/css" href="/Fountain/css/reset-fonts-grids.css"/>
	<link rel="stylesheet" type="text/css" href="/Fountain/js/dojo/resources/dojo.css"></link>
    <link rel="stylesheet" type="text/css" href="/Fountain/js/dijit/themes/tundra/tundra.css"></link>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/bootstrap.css" media="screen"/>
	<link rel="stylesheet" type="text/css" href="/Fountain/css/tablePages.css" media="screen"></link>
	<link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen"/>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/layout.css"/>
</head>
<body class="tundra">
<div class="bannerContent"></div>
 Table upload file <%=results.getUploadFileName()%>. Upload succeeded.
 <br/>Please note that valid changes are not shown here. 
 <br/>Notices:<br/>
 
	<c:forEach var="notice" items="${results.notices}" varStatus="loopStatus" >
        <c:out value="${notice}"/><br/>
    </c:forEach>

    <hr>
    
    <div id="logFile">
		<a target="_blank" href="/Fountain/rest-services/file/table/log/${results.logFileId}" title="Download log file">
		Download log file.
		</a>
    </div>

    <hr>
	<div class="editButtons">
	<button style="visibility:hidden" id="editButton" onClick="ofwat.editor.startEdit()" dojoType="dijit.form.Button">Edit</button>
	<button id="saveButton" onClick="ofwat.editor.showSaveDialog()" dojoType="dijit.form.Button" >Save Edits</button>
	<button id="cancelEditing" onClick="ofwat.editor.cancelEdits()" dojoType="dijit.form.Button" >Cancel Editing</button>
	</div>

    <div id="saveDialog" class="white_content">
			<div id="editsTableDiv" class="editButtons"></div> 
			Audit Comment <textarea dojoType="dijit.form.Textarea" class="auditComment" id="auditComment"></textarea>
			<div class="okcancel">
			<button onclick="ofwat.editor.saveEdits()" class="btn" dojoType="dijit.form.Button">Commit changes</button>
			<button onclick="ofwat.editor.returnToEdits()" class="btn" dojoType="dijit.form.Button">Return To Edits</button>
			<br/><ofwat:wikiPage helpPage="Saving_Edits" model="true"/>
			</div>
	</div>

    <script type="text/javascript" src="/Fountain/js/json2.js"></script>
	<script type="text/javascript" src="/Fountain/js/ofwat/ofwat.js"></script>
	<script type="text/javascript" src="/Fountain/js/ofwat/rest.js"></script>
	<script type="text/javascript" src="/Fountain/js/ofwat/editor.js"></script>
    <script type="text/javascript" src="/Fountain/js/ofwat/fixedMenu.js"></script>

    <script type="text/javascript" src="/Fountain/js/dojo/dojo.js" djConfig="parseOnLoad: true"></script>
	<script type="text/javascript" src="../../js/jquery/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="../../js/bootstrap.js"></script>
	<script type="text/javascript" src="../../js/moment.js"></script>
    <script type="text/javascript">
	    dojo.require("dijit.layout.TabContainer");
	    dojo.require("dijit.layout.ContentPane");
        dojo.require("dijit.form.Button");
        dojo.require("dijit.form.Textarea");
        dojo.require("dojo.parser");
        dojo.require("dojox.xml.parser");
        dojo.require("dojo.NodeList-traverse");
        dojo.addOnLoad(function() {
            dojo.query("div[rel]").forEach(function(n) {
                var className = dojo.attr(n, "rel");
                // now set it
                dojo.attr(n, "dojoType", className);
            });
            dojo.parser.parse("progtabwrapper");
        });
    </script>

    <script type="text/javascript">
		Handlebars.registerHelper('inRole', ofwat.checkUserInRole); 
		ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);
    </script>

	<jsp:include page="../footer.jsp" />
</body>

</html>