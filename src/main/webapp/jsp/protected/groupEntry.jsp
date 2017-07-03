<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/> 
	<title>Fountain: Groups</title>
	<link rel="stylesheet" type="text/css" href="/Fountain/css/reset-fonts-grids.css"/>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/layout.css"/>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/bubble.css" media="screen"/>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/adhocReportLayout.css" media="screen"/>
	<link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen"/>
</head>
<body>
<div class="bannerContent"></div>
   	<div id="response"></div>
	<div id="progtabwrapper">
		<div class="tabContainer" style="width: 100%;" >
			<div class="contentPane" title="Items" >
				Company: <select id='companyStartList'  onchange="ofwat.referenceSelection.loadCompanyGroups();"></select>
				Group: <select id='groupSelect' onchange="ofwat.referenceSelection.loadCompanyGroups();"></select>
				<br/>
				<br/>
				<table class="itemTable">
					<tr>
						<td id="results" rowSpan="2">
							<select class="itemChosenListLeft"  id="groupEntryStartList" size="20" ">
							<!-- Populated from the search results -->
								<option value="0">&nbsp;</option>
							</select>
						</td>
						
						<td>
							<input type="text" id="newGroupName" dojoType="dijit.form.TextBox"></input>
						</td>
					</tr>
					<tr>
						<td>
							
						</td>
						<td >
						 
							<button dojoType="dijit.form.Button" type="button" id="assign" onclick="ofwat.groupEntry.renameGroup(false);">Rename Group</button>
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
	<script type="text/javascript" src="../../js/ofwat/groupEntry.js"></script>
	<script type="text/javascript" src="../../js/moment.js"></script>
	<script type="text/javascript">
        dojo.require("dijit.form.Button");
        dojo.require("dijit.form.TextBox");
    </script>

    <script type="text/javascript" src="../../js/handlebars/handlebars.js"></script>
    <script type="text/javascript">
		Handlebars.registerHelper('inRole', ofwat.checkUserInRole); 
		ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);
    </script>

	<jsp:include page="../footer.jsp" />
</body>
</html>