<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/> 
	<title>Fountain: Item roles</title>
	<link rel="stylesheet" type="text/css" href="/Fountain/css/reset-fonts-grids.css"/>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/layout.css"/>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/bootstrap.css" media="screen"/>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/adhocReportLayout.css" media="screen"/>
	<link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen"/>
</head>
<body>
<div class="bannerContent"></div>

   	<div id="response"></div>
	<div id="progtabwrapper">
		<div class="tabContainer" style="width: 100%;" >
			<div class="contentPane" title="Items" >
				Table Suite: <select id='modelSelect' onchange="ofwat.referenceSelection.loadPages();"></select>
				Table: <select id='pageSelect'  onchange="ofwat.referenceSelection.loadPageItems();"></select>
				<br/>
				<br/>
				<input type="text" name="search" class="dojoTextBox" id="itemSearchTerm" />
				<button class="dojoButton" id="search" onclick="ofwat.referenceSelection.itemSearch(false);">Quick Search</button>
				<button class="dojoButton" id="searchDef"	onclick="ofwat.referenceSelection.itemSearch(true);">Search Definitions</button>
				<div id="searchCriteria"></div>
				<table class="itemTable">
					<tr>
						<td id="results" rowSpan="2">
							<select class="itemChosenListLeft"  id="itemStartList" size="20" onchange="ofwat.itemRoles.showTeam();">
							<!-- Populated from the search results -->
								<option value="0">&nbsp;</option>
							</select>
						</td>
						
						<td>
							<select id='teamSelect'"></select><br/>	
						</td>
					</tr>
					<tr>
						<td>
							<div id="currentTeamName"></div>
						</td>
						<td >
							<button class="dojoButton" id="assign" onclick="ofwat.itemRoles.assignTeam(false);">Assign Team</button>
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
	<script type="text/javascript" src="../../js/ofwat/referenceSelection.js"></script>
	<script type="text/javascript" src="../../js/ofwat/itemRoles.js"></script>
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