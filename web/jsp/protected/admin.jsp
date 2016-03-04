<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@page pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp"%>
<%@page import="uk.gov.ofwat.fountain.domain.Model,uk.gov.ofwat.fountain.api.ModelService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ofwat" uri="http://www.ofwat.gov.uk" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="uk.gov.ofwat.fountain.domain.ModelType"%>
<%@page import="java.util.List"%>
<%@page import="uk.gov.ofwat.fountain.api.ModelServiceImpl"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/> 
	<title>Fountain: Admin</title>
	<!-- CSS Reset sheet, minified version, written by yahoo -->
	<link rel="stylesheet" type="text/css" href="/Fountain/css/reset-fonts-grids.css"/>
	<!-- 960.gs grid sheet -->
	<link rel="stylesheet" type="text/css" href="/Fountain/css/960grid.css"/>
	
	<link type="text/css" rel="stylesheet" href="/Fountain/css/layout.css" media="screen"/>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/bootstrap.css" media="screen"/>
	<link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen"/>
	<link rel="icon" type="image/gif" href="/Fountain/images/icon.gif"/>
</head>
<body>
<div class="bannerContent"></div>
<p></p>

<div id="response"></div>
<div id="progtabwrapper">
		<div class="tabContainer" style="width: 100%;" >
			<div class="contentPane" title="Locks" >
				<br/>
				<table class="itemTable">
					<tr>
						<td id="results" rowspan="2">
						Users with current locks<br/>
							<select class="chosenList"  id="userStartList" size="20" ">
							<!-- Populated from the search results -->
								<option value="0">&nbsp;</option>
							</select>
						</td>					
					</tr>
					<tr>
						
						<td >
							<button class="dojoButton" id="deleteLock" onclick="ofwat.admin.deleteLocks(false);">Delete Locks</button>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>

	
	<script type="text/javascript" src="../../js/json2.js"></script>
	<script type="text/javascript" src="../../js/ofwat/ofwat.js"></script>
	<script type="text/javascript" src="../../js/ofwat/rest.js"></script>
	<script type="text/javascript" src="../../js/dojo/dojo.js" ></script>
	<script type="text/javascript" src="../../js/ofwat/listUtils.js"></script> 
	<script type="text/javascript" src="../../js/ofwat/admin.js"></script>
	<script type="text/javascript" src="../../js/jquery/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="../../js/bootstrap.js"></script>	
	<script type="text/javascript" src="../../js/moment.js"></script>

    <script type="text/javascript" src="../../js/handlebars/handlebars.js"></script>
    <script type="text/javascript">
		Handlebars.registerHelper('inRole', ofwat.checkUserInRole); 
		ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);
    </script>

	<jsp:include page="../footer.jsp" />
</body>
</html>