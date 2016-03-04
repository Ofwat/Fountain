<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@page pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp"%>
<%@ taglib prefix="ofwat" uri="http://www.ofwat.gov.uk" %> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/> 

        <link rel="stylesheet" type="text/css" href="/Fountain/css/reset-fonts-grids.css"/>
   		<link rel="stylesheet" type="text/css" href="/Fountain/css/960grid.css"/>
		<!-- <link rel="stylesheet" type="text/css" href="/Fountain/js/dijit/themes/tundra/tundra.css"/> -->
		<link rel="stylesheet" type="text/css" href="/Fountain/js/dbootstrap/theme/dbootstrap/dbootstrap.css"/>
		<link type="text/css" rel="stylesheet" href="/Fountain/css/layout.css" media="screen"/>
		<!-- <link type="text/css" rel="stylesheet" href="/Fountain/css/bubble.css" media="screen"/> -->
	    <link type="text/css" rel="stylesheet" href="/Fountain/css/adhocReportLayout.css" media="screen"/>
	     <link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen"/>
	     
	     <link type="text/css" rel="stylesheet" href="/Fountain/css/bootstrap.css" media="screen"/>
	     <link rel="icon" type="image/gif" href="/Fountain/images/icon.gif"/>
        <title>Custom Reports: Create A Report</title>
    </head>
    
    <body class="dbootstrap">
<div class="bannerContent"></div>
    	<div id="response"></div>

		<div id="progtabwrapper" style="">
			<div id="tabContainer" class="tabContainer" style="width: 100%;" >
			
				<div id="itemTab" class="contentPane" title="Items">
					Items from: <select id='modelSelect' onchange="ofwat.referenceSelection.loadPages();"></select>
					Table: <select id='pageSelect'  onchange="ofwat.referenceSelection.loadPageItems();"></select>
					<br/>
					<br/>
					<input type="text" name="search" class="dojoTextBox" id="itemSearchTerm" />
					<button dojoType="dijit.form.Button" id="search" onclick="ofwat.referenceSelection.itemSearch(false);">Quick Search</button>
					<button dojoType="dijit.form.Button" id="searchDef"	onclick="ofwat.referenceSelection.itemSearch(true);">Search Definitions</button>
					<div id="searchCriteria"></div>
					<table class="itemTable">
						<tr>
							<td id="results">
								<select class="itemChosenListLeft" multiple="multiple" id="itemStartList" size="20">
								<!-- Populated from the search results -->
									<option value="0">&nbsp;</option>
								</select>
							</td>
							<td class="centre">
								<button dojoType="dijit.form.Button" id="addItem" style="success" onclick="ofwat.listUtils.move('itemStartList', 'itemChosenList', true);">Add</button>
								<br />
								<button dojoType="dijit.form.Button" id="removeItem" style="btn-warning" onclick="ofwat.listUtils.remove('itemChosenList');">Remove</button>
							</td>
							<td><select class="itemChosenListRight" multiple="multiple" id="itemChosenList" size="20">
								<!-- Populated from the selected search results -->
								</select>
							</td>
							<td><img src="../../images/move_up.gif"	alt="move up" onclick="javascript:ofwat.listUtils.ListItemMove(-1, 'itemChosenList');" /><br />
								<img src="../../images/move_down.gif" alt="move down" onclick="javascript:ofwat.listUtils.ListItemMove(1, 'itemChosenList');" /></td>
						</tr>
					</table>
				</div>
						
				<div id="yearTab" class="contentPane" title="Years">
					<div id="intervalDiv">	
					
						Interval Type: <select id='intervalTypes' onchange="ofwat.referenceSelection.populateIntervals();"></select>
						<br/>
						<br/>
						<table class="intervalTable">
							<tr>
								<td id="intervalResults"><select class="chosenList" multiple="multiple" id="intervalStartList" size="20">
									<!-- Populated from the search results -->
										<option value="0">&nbsp;</option>
									</select>
								</td>
								<td class="centre">
									<button dojoType="dijit.form.Button" id="addInterval" onclick="ofwat.listUtils.move('intervalStartList', 'intervalChosenList', true);">Add</button>
									<br/>
									<button dojoType="dijit.form.Button" id="removeInterval" onclick="ofwat.listUtils.remove( 'intervalChosenList');">Remove</button>
								</td>
								<td><select class="chosenList" multiple="multiple" id="intervalChosenList" size="20">
									<!-- Populated from the selected search results -->
									</select>
								</td>
								<td><img src="../../images/move_up.gif"	alt="move up" onclick="javascript:ofwat.listUtils.ListItemMove(-1, 'intervalChosenList');" /><br />
									<img src="../../images/move_down.gif" alt="move down" onclick="javascript:ofwat.listUtils.ListItemMove(1, 'intervalChosenList');" /></td>
							</tr>
						</table>
					</div>
				</div>
				
				<div id="companyTab" class="contentPane" title="Companies">
					<div id="companyDiv">
						<h4>Select company here or leave blank to select at runtime.</h4>
						Company Type: <select id='companyTypes' onchange="ofwat.referenceSelection.filterCompanies();"></select>
						<br/>
						<br/>
						<table class="companyTable">
							<tr>
								<td id="companyResults">
									<select class="chosenList" multiple="multiple" id="companyStartList" size="20">
									<!-- Populated from the search results -->
										<option value="0">&nbsp;</option>
									</select>
								</td>
								<td class="centre">
									<button dojoType="dijit.form.Button" id="addCompany" onclick="ofwat.listUtils.move('companyStartList', 'companyChosenList', true);">Add</button>
									<br/>
									<button dojoType="dijit.form.Button" id="removeCompany" onclick="ofwat.listUtils.remove( 'companyChosenList');">Remove</button>
								</td>
								<td><select class="chosenList" multiple="multiple" id="companyChosenList" size="20">
									<!-- Populated from the selected search results -->
									</select>
								</td>
								<td><img src="../../images/move_up.gif"	alt="move up" onclick="javascript:ofwat.listUtils.ListItemMove(-1, 'companyChosenList');" /><br />
									<img src="../../images/move_down.gif" alt="move down" onclick="javascript:ofwat.listUtils.ListItemMove(1, 'companyChosenList');"/>
								</td>
							</tr>
						</table>					
					</div>
				</div>
				
                <div id="RunTab" class="contentPane" title="Runs">
                    <div id="runDiv">
                    	<h4>Select run here or leave blank to select at runtime.</h4>
                        Data Stream: <select id='agendaList' onchange="ofwat.referenceSelection.updateRuns();"></select>
                        Run: <select id='runList' onchange="ofwat.referenceSelection.updateTags();"></select>
                        <br/>
                        <br/>
                        <table class="tagTable">
                            <tr>
                                <td id="tagResults">
                                    <select class="chosenList" multiple="multiple" id="tagStartList" size="20">
                                        <!-- Populated from the search results -->
                                        <option value="0">&nbsp;</option>
                                    </select>
                                </td>
                                <td class="centre">
                                    <button dojoType="dijit.form.Button" id="addRunTag" onclick="ofwat.listUtils.move('tagStartList', 'tagChosenList', true);">Add</button>
                                    <br/>
                                    <button dojoType="dijit.form.Button" id="removeRunTag" onclick="ofwat.listUtils.remove( 'tagChosenList');">Remove</button>
                                </td>
                                <td><select class="chosenList" multiple="multiple" id="tagChosenList" size="20">
                                    <!-- Populated from the selected search results -->
                                </select>
                                </td>
                                <td><img src="../../images/move_up.gif"	alt="move up" onclick="javascript:ofwat.listUtils.ListItemMove(-1, 'tagChosenList');" /><br />
                                    <img src="../../images/move_down.gif" alt="move down" onclick="javascript:ofwat.listUtils.ListItemMove(1, 'tagChosenList');"/>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>

				<div id="layoutTab" class="contentPane" title="Layout">
					<table class="layoutTable">
						<tr>
							<td id="layout">
									Rows<br/>
									<select class="chosenList" multiple="multiple" id="layoutLeftList" size="20">
									<!-- Row headings -->
										<option value="1">Company</option>
										<option value="0">Item</option>
									</select>
								</td>
								<td><img src="../../images/move_up.gif"	alt="move up" onclick="javascript:ofwat.listUtils.ListItemMove(-1, 'layoutLeftList');" /><br />
									<img src="../../images/move_down.gif" alt="move down" onclick="javascript:ofwat.listUtils.ListItemMove(1, 'layoutLeftList');"/></td>
								<td class="centre">
									<button dojoType="dijit.form.Button" id="addLayout" onclick="ofwat.listUtils.move('layoutLeftList', 'layoutTopList', false);">&gt;</button>
									<br/>
									<button dojoType="dijit.form.Button" id="removeLayout" onclick="ofwat.listUtils.move('layoutTopList', 'layoutLeftList', false);;">&lt;</button>
								</td>
								<td>
									Columns<br/>
									<select class="chosenList" multiple="multiple" id="layoutTopList" size="20">
									<!-- Populated from the selected search results -->
										<option value="0">Year</option>
										<option value="1">Run</option>
									</select>
								</td>
								<td><img src="../../images/move_up.gif"	alt="move up" onclick="javascript:ofwat.listUtils.ListItemMove(-1, 'layoutTopList');" /><br />
									<img src="../../images/move_down.gif" alt="move down" onclick="javascript:ofwat.listUtils.ListItemMove(1, 'layoutTopList');"/>
								</td>
							
								<td>
									<button dojoType="dijit.form.Button" id="genPreview" onclick="ofwat.adhocReport.generatePreview();">Show Preview</button>
									<br/>
									<div id="preview">&nbsp;</div>
								</td>
						</tr>		
					</table>
				</div>

				<div id="displayTab" class="contentPane" title="Display Options" onShow="ofwat.adhocReport.showAvailableGroups();">
					<table class="displayTable">
						<tr>
							<td id="display" class="well" style="">
									<b>Item Display Options</b><br/>
									(choose at least 1)<br/><br/>
									<input id="desccb" name="desccb" dojoType="dijit.form.CheckBox" checked/>
        							<label for="desccb">
            							Description
        							</label><br/>
        							   <input id="unitcb" name="unitcb" dojoType="dijit.form.CheckBox" checked/>
        							<label for="unitcb">
            							Unit
        							</label><br/>
        							   <input id="boncb" name="boncb" dojoType="dijit.form.CheckBox" checked/>
        							<label for="boncb">
            							Boncode
        							</label><br/>
        								   <input id="modelcb" name="modelcb" dojoType="dijit.form.CheckBox" checked/>
        							<label for="modelcb">
            							Table Suite 
        							</label><br/>
        							
        							<input id="cgcb" name="cgcb" dojoType="dijit.form.CheckBox"/>
        							<label for="cgcb">
            							Show CGs
        							</label><br/>
								</td>
								<td class="well" style="background-color: #FFFFFF">
									<b>Company Display Options</b><br/>
									(choose at least 1)<br/><br/>
        							<input id="namecb" name="namecb" dojoType="dijit.form.CheckBox"/>
        							<label for="namecb">
            							Full Name
        							</label><br/>
        							<input id="acronymcb" name="acronymcb" dojoType="dijit.form.CheckBox" checked/>
        							<label for="acronymcb">
            							Acronym
        							</label><br/>
								</td>
								<td class="well" style="">
									<b>Run Display Options</b><br/>
									<br/><br/>
                                    <input id="agendaNamecb" name="agendaNamecb" dojoType="dijit.form.CheckBox" checked/>
                                    <label for="agendaNamecb">
                                        Data Stream Name
                                    </label><br/>
                                    <input id="agendaCodecb" name="agendaCodecb" dojoType="dijit.form.CheckBox"/>
                                    <label for="agendaCodecb">
                                        Data Stream Code
                                    </label><br/>
                                    <input id="runNamecb" name="runNamecb" dojoType="dijit.form.CheckBox" checked/>
                                    <label for="runNamecb">
                                        Run Name
                                    </label><br/>
                                    <input id="runDescriptioncb" name="runDescription" dojoType="dijit.form.CheckBox"/>
                                    <label for="runDescriptioncb">
                                        Run Description
                                    </label><br/>
                                    <input id="tagNamecb" name="tagName" dojoType="dijit.form.CheckBox" checked/>
                                    <label for="tagNamecb">
                                        Checkpoint Name
                                    </label><br/>
								</td>
								<td class="well" style="background-color: #FFFFFF">
									<b>Group Display Options<b>
									<br/><br/>
									<select id="availableGroups">
									</select>
								</td>
						</tr>		
					</table>
				</div>
		</div>
<!-- End of wrapper for the content panels --></div>

		<span id="okCancel" class="right">
			<button dojoType="dijit.form.Button" onclick="ofwat.adhocReport.validateSelection()">Save</button>
			<button dojoType="dijit.form.Button" onclick="ofwat.referenceSelection.clearOptions()">Clear Options</button>
		</span>
		
		<div id="saveDialog" class="white_content">
			<div id='response'></div>
			<div style="float:left">
				<p>
				<label for="reportName">Report Name</label>
				<input type="text" name="report" class="dojoTextBox" id="reportName" />
				</p>
				<div id="publicReportDiv" style="padding-bottom: 20px;">
					<div>
						<label for="publicReport" class="checkbox inline">
							<input type="checkbox" name="publicReport" class="dojoCheckBox" id="publicReport"/>
							 Public Report <i class="icon-large icon-eye-open"></i>
						</label>
					</div>
					<div>
						<label for="readOnly" class="checkbox inline">
							<input type="checkbox" name="publicReport" class="dojoCheckBox" id="readOnly"/>
							 Read Only <i class="icon-large icon-lock"></i>			
						</label>
					</div>
					<span class="clear"></span>
				</div>
				<div id="teamDiv">
				<p>
				<label for="teamSelect">Team:</label>
				<select id='teamSelect'></select>
				</p>
				</div>
				<div class="okcancel">	
					<!-- if it's your report, then save and save as, otherwise just save as (if a new report, then save)  -->
					<span class="hiddenButton" id="saveAsButtonSpan"><button title="Save A Copy" onclick="ofwat.adhocReport.saveReport()" dojoType="dijit.form.Button">Save As</button></span>
					<span class="displayButton" id="saveButtonSpan"><button title="Save Report" id="saveButton" onclick="ofwat.adhocReport.saveReport()" dojoType="dijit.form.Button">Save</button></span>
					<button title="Return To Edits" onclick="ofwat.adhocReport.cancelSave()" dojoType="dijit.form.Button">Cancel</button>
				</div>
			</div>
			<div id="readOnlyMessageAdmin" class="hidden" style="float:left;width: 50%;">
				<div class="alert alert-block">
				  <h4>Read-Only report <i class="icon-ban-circle icon-large"></i></h4>
				  	This report has been marked as read only.<p/>
				  	</p>
				  	If you wish to modify this report you will be modifying the structure of a report that has been marked read only by <span class="reportOwner"></span>.
				</div>
			</div>
			<div class="clear"></div>
			
			<div id="readOnlyMessage" class="hidden" style="float:left;width: 50%;">
				<div class="alert alert-block">
				  <h4>Read-Only report <i class="icon-ban-circle icon-large"></i></h4>
				  	This report is marked as Read-Only. You can't update it's structure, you can only save a new report.<p/>
				  	</p>
				  	If you wish to modify this report please contact a Fountain administrator or the report owner <span class="reportOwner"></span> and ask them to uncheck the read only box when saving the report.
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<div id="fade" class="dark_overlay"></div>

	<script type="text/javascript">
		/*var dojoConfig = {
			'packages': dojo.config.packages.push(
				 {
					 location: '../../js/dbootstrap',
				     name: 'dbootstrap'
				 }
			)
		}*/
	</script>
	
	<script type="text/javascript" src="/Fountain/js/dojo/dojo.js.uncompressed.js"></script>
	<script type="text/javascript" src="../../js/ofwat/ofwat.js"></script>
	<script type="text/javascript" >helpPage="Report Design";ofwat.wikiPage();</script>
	
	<script type="text/javascript" src="../../js/ofwat/adhocReportDecorator.js"></script>
	<script type="text/javascript" src="../../js/json2.js"></script>
	<script type="text/javascript" src="../../js/ofwat/rest.js"></script>
	<script type="text/javascript" src="../../js/ofwat/listUtils.js"></script>
	<script type="text/javascript" src="../../js/ofwat/referenceSelection.js"></script>
	
    <script type="text/javascript" src="../../js/jquery/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="../../js/bootstrap.js"></script>	

    <script type="text/javascript" src="../../js/handlebars/handlebars.js"></script>
    <script type="text/javascript">
		Handlebars.registerHelper('inRole', ofwat.checkUserInRole); 
		ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);
		helpPage="Report Design";
		ofwat.wikiPage();
    </script>

  	<jsp:include page="../footer.jsp" />

	<script type="text/javascript" src="../../js/ofwat/reportPreview.js"></script>
	<script type="text/javascript" src="../../js/ofwat/adhocReport.js"></script>
	<script type="text/javascript" src="../../js/moment.js"></script>
<script>		// set up dojo
 	//dojo.require("dbootstrap");
	dojo.require("dijit.layout.TabContainer");
	dojo.require("dijit.layout.ContentPane");
	dojo.require("dojo.parser");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.CheckBox");
	//$(document).ready(function(){ 
		//alert("ready!")
		//ofwat.showLoading($('#progtabwrapper'));
	//});
	dojo.addOnLoad(function() {
		//$(document).ready(function(){ 
		//alert("ready");
		dojo.query("div[rel]").forEach(function(n) {
	        var className = dojo.attr(n, "rel");
	        // now set it
	        dojo.attr(n, "dojoType", className);
	    });
	   dojo.parser.parse();
	   // When all dojo aspects are loaded, call the page initialisation funtion
	   dojo.addOnLoad(ofwat.adhocReport.initialise(ofwat.showLoading, ofwat.hideLoading));
	//});
	});
	</script>
 
</body>
    
   
</html>