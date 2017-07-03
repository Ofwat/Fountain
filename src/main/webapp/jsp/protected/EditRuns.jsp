<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@page pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp"%>
<%@page import="uk.gov.ofwat.fountain.domain.Model, uk.gov.ofwat.fountain.api.ModelService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ofwat" uri="http://www.ofwat.gov.uk" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/> 
	<title>Fountain: Admin</title>
	<!-- CSS Reset sheet, minified version, written by yahoo -->
	<link rel="stylesheet" type="text/css" href="/Fountain/css/reset-fonts-grids.css"/>
	<!-- 960.gs grid sheet -->
	<link rel="stylesheet" type="text/css" href="/Fountain/css/960grid.css"/>
	
	<link type="text/css" rel="stylesheet" href="/Fountain/css/layout.css" media="screen"/>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/bubble.css" media="screen"/>
	<link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen"/>
</head>
<body>
<div class="bannerContent"></div>
<p></p>

<div>
<button class="dojoButton" id="createRun" onclick="">Create a new Run</button>
<div id="reports" class="grid_12">
<table class="reportType">
<tbody>
<tr><th>Run Id</th><th>Run Date</th><th>Edit</th></tr>
<tr>
   <td class="reportListOdd"><a href='javascript:ofwat.reportPage.selectReport("reportDisplay.page?reportId=1613")'>Run A</a></td>
   <td class="reportListOdd">10 Jul 2012 13:05:52</td>
   <td class="reportListOdd">
   		<a name="editReport" title="Redesign leakage time series" href="adhocReport.jsp?reportId=1152" alt="Edit leakage time series"><img border="0" src="../../images/pencil.png"></a>
   		<a name="deleteReport" title="Delete leakage time series" href='javascript:ofwat.reportPage.deleteReport("http://localhost:8080/Fountain/rest-services/report/1152","leakage time series")' alt="Delete leakage time series"><img border="0" src="../../images/bin_closed.png"></a>
   </td>
</tr>
<tr>
   <td class="reportListEven"><a href='javascript:ofwat.reportPage.selectReport("reportDisplay.page?reportId=1569")'>Run B</a></td>
   <td class="reportListEven">05 Jul 2012 14:51:53</td>
   <td class="reportListEven">
		<a name="editReport" title="Redesign leakage time series" href="adhocReport.jsp?reportId=1152" alt="Edit leakage time series"><img border="0" src="../../images/pencil.png"></a>
   		<a name="deleteReport" title="Delete leakage time series" href='javascript:ofwat.reportPage.deleteReport("http://localhost:8080/Fountain/rest-services/report/1152","leakage time series")' alt="Delete leakage time series"><img border="0" src="../../images/bin_closed.png"></a>
   </td>
</tr>
<tr>
   <td class="reportListOdd"><a href='javascript:ofwat.reportPage.selectReport("reportDisplay.page?reportId=1563")'>Run C</a></td>
   <td class="reportListOdd">05 Jul 2012 12:44:19</td>
   <td class="reportListOdd">
		<a name="editReport" title="Redesign leakage time series" href="adhocReport.jsp?reportId=1152" alt="Edit leakage time series"><img border="0" src="../../images/pencil.png"></a>
   		<a name="deleteReport" title="Delete leakage time series" href='javascript:ofwat.reportPage.deleteReport("http://localhost:8080/Fountain/rest-services/report/1152","leakage time series")' alt="Delete leakage time series"><img border="0" src="../../images/bin_closed.png"></a>
   </td>
</tr>
<tr>
   <td class="reportListEven"><a href='javascript:ofwat.reportPage.selectReport("reportDisplay.page?reportId=1555")'>Run D</a></td>
   <td class="reportListEven">05 Jul 2012 12:00:41</td>
   <td class="reportListEven">
   		<a name="editReport" title="Redesign leakage time series" href="adhocReport.jsp?reportId=1152" alt="Edit leakage time series"><img border="0" src="../../images/pencil.png"></a>
   		<a name="deleteReport" title="Delete leakage time series" href='javascript:ofwat.reportPage.deleteReport("http://localhost:8080/Fountain/rest-services/report/1152","leakage time series")' alt="Delete leakage time series"><img border="0" src="../../images/bin_closed.png"></a>   
   </td>
</tr>
</tbody></table></div>	
</div>
	<script type="text/javascript" src="../../js/json2.js"></script>
	<script type="text/javascript" src="../../js/ofwat/ofwat.js"></script>
	<script type="text/javascript" src="../../js/ofwat/rest.js"></script>
	<script type="text/javascript" src="../../js/dojo/dojo.js" ></script>
	<script type="text/javascript" src="../../js/ofwat/listUtils.js"></script> 
	<script type="text/javascript" src="../../js/ofwat/admin.js"></script>
	<script type="text/javascript" src="../../js/moment.js"></script>
    <script type="text/javascript" src="../../js/handlebars/handlebars.js"></script>
    <script type="text/javascript">
		Handlebars.registerHelper('inRole', ofwat.checkUserInRole); 
		ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);
    </script>

	<jsp:include page="../footer.jsp" />
</body>
</html>