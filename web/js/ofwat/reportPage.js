/*
 *  Copyright (C) 2009 Water Services Regulation Authority (Ofwat)
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

if (!ofwat.rest) {
	alert("missing import: rest.js");
}
if (!ofwat.referenceSelection) {
	alert("missing import: referenceSelection.js");
}

ofwat.reportPage = {
	userTeamArray : {},
	allTeamArray : {},
	reportArray : {}
};
ofwat.reportPage.reportArray

ofwat.reportPage.selectReport = function(uri) {
	location.href = uri;
};

ofwat.reportPage.loadReports = function() {

	var xhrArgs = {
		url : "../../rest-services/report?limit=40",
		handleAs : "json",
		preventCache : true,
		headers : {
			"accept" : "application/json"
		},
		load : function(data) {
			ofwat.reportPage.populateReports(data);
		},
		error : function(error) {
			ofwat.showError("Problem loading reports " + error + ")");
		}
	};
	ofwat
			.showMessage("Loading reports <img border='0' src='../../images/loader.gif'/>");
	var deferred = dojo.xhrGet(xhrArgs);
};

ofwat.reportPage.populateReports = function(reportsArg) {
	ofwat.reportPage.reportArray = reportsArg;
	ofwat.reportPage.loadTeamsForUser();
};

/**
 * Rest call to get teams for a user.
 */
ofwat.reportPage.loadTeamsForUser = function() {
	var xhrArgs = {
		url : "../../rest-services/team/user",
		handleAs : "json",
		headers : {
			"accept" : "application/json"
		},
		load : function(data) {
			ofwat.reportPage.populateTeamsForUser(data);
			ofwat.showMessage("");
		},
		error : function(error) {
			ofwat.showError("loadTeamsForUser", error);
		}
	};
	ofwat
			.showMessage("Loading teams... <img border='0' src='../../images/loader.gif'/>");
	var deferred = dojo.xhrGet(xhrArgs);
};

ofwat.reportPage.populateTeamsForUser = function(data) {
	ofwat.reportPage.userTeamArray = data;
	ofwat.reportPage.loadAllTeams();
};

/**
 * Rest call to get teams for a user.
 */
ofwat.reportPage.loadAllTeams = function() {
	var xhrArgs = {
		url : "../../rest-services/team",
		handleAs : "json",
		headers : {
			"accept" : "application/json"
		},
		load : function(data) {
			ofwat.reportPage.populateAllTeams(data);
			ofwat.showMessage("");
		},
		error : function(error) {
			ofwat.showError("loadAllTeams", error);
		}
	};
	ofwat
			.showMessage("Loading teams... <img border='0' src='../../images/loader.gif'/>");
	var deferred = dojo.xhrGet(xhrArgs);

};

ofwat.reportPage.populateAllTeams = function(data) {
	ofwat.reportPage.allTeamArray = data;
	ofwat.reportPage.showReports();
};

ofwat.reportPage.isUserTeam = function(team) {
	for ( var i = 0; i < ofwat.reportPage.userTeamArray.length; i++) {
		if (ofwat.reportPage.userTeamArray[i].name == team) {
			return true;
		}
	}
	return false;
};

ofwat.reportPage.showReports = function() {
	var teamReportGrid = [];
	var teamReportIndex = [];
	var universalTeam = "Universal";
	var iplTeam = "IPL";

	// add teams in correct order
	// Note on use of number of teams. There will always be a Fountain.Users
	// team (if not the team rest
	// service would give an error). So if there is only one team it must be
	// Fountain.Users/Universal.
	if (ofwat.reportPage.userTeamArray.length > 1) {
		// add IPL first if user is in it
		if (ofwat.reportPage.isUserTeam(iplTeam)) {
			teamReportGrid[iplTeam] = [];
			teamReportIndex[iplTeam] = 0;
		}
		// add user teams next
		for ( var i = 0; i < ofwat.reportPage.userTeamArray.length; i++) {
			var team = ofwat.reportPage.userTeamArray[i].name;
			if (universalTeam != team) {
				teamReportGrid[team] = [];
				teamReportIndex[team] = 0;
			}
		}
		// add other teams
		for ( var i = 0; i < ofwat.reportPage.allTeamArray.length; i++) {
			var team = ofwat.reportPage.allTeamArray[i].name;
			if (!ofwat.reportPage.isUserTeam(team)) {
				teamReportGrid[team] = [];
				teamReportIndex[team] = 0;
			}
		}
		// add universal team last
		teamReportGrid[universalTeam] = [];
		teamReportIndex[universalTeam] = 0;
	} else {
		// user is only in universal team
		// add universal team first
		teamReportGrid[universalTeam] = [];
		teamReportIndex[universalTeam] = 0;
		// add other teams
		for ( var i = 0; i < ofwat.reportPage.allTeamArray.length; i++) {
			var team = ofwat.reportPage.allTeamArray[i].name;
			if (!ofwat.reportPage.isUserTeam(team)) {
				teamReportGrid[team] = [];
				teamReportIndex[team] = 0;
			}
		}
	}

	// add reports
	for ( var i = 0; i < ofwat.reportPage.reportArray.length; i++) {
		var report = ofwat.reportPage.reportArray[i];
		if (ofwat.reportPage.isMyReport(report) || report.publicReport) {
			teamReportGrid[report.team][teamReportIndex[report.team]] = report;
			teamReportIndex[report.team]++;
		}
	}

	// start to make the table here - with the main heading as ths
	var table = "<table id='tblReports' class='table table-striped table-bordered table-condensed'>";
	table += "<tr><th>Report Name<i class='icon-filter'></i></th><th>Last Modified Date<i class='icon-filter'></i></th><th></th></tr>";
	var i = 1;
	for (key in teamReportGrid) {
		var teamReports = teamReportGrid[key];
		table = ofwat.reportPage.displayReports(teamReports, table, i);
		i++;
	}
	;

	table += "</table>";
	var n = dojo.byId('reports');
	n.innerHTML = table;

	dojo.byId("response").innerHTML = "";

	$('#tblReports').filterable({
		editableOptions : {
			placement : "right"
		},
		postFilterCallback : function(query) {
			if (query == '') {
				ofwat.reportPage.hideReports();
			}
		}
	});

	// modify buttons style
	$.fn.editableform.buttons = '<button type="submit" class="btn btn-primary editable-submit">Apply Filter <i class="icon-ok icon-white"></i></button>'
	// '<button type="button" class="btn editable-cancel"><i
	// class="icon-remove"></i></button>';

};

ofwat.reportPage.isMyReport = function(report) {
	for ( var i = 0; i < ofwat.reportPage.userTeamArray.length; i++) {
		if (ofwat.reportPage.userTeamArray[i].name == report.team) {
			return true;
		}
	}
	return false;
};

ofwat.reportPage.displayReports = function(reports, txt, teamCount) {

	var currentTeam = "";
	var countSinceTeam = 0

	for ( var i = 0; i < reports.length; i++) {
		var currentReport = reports[i];
		var currentReportName = ofwat.escapeHTML(currentReport.name);
		var newTeam = false;
		countSinceTeam++;
		if (currentReport.team != currentTeam) {
			if (i != 0) {
				txt += "</tbody>";
			}
			currentTeam = currentReport.team;
			txt += "<tr><th colspan=3>" + currentTeam + "</th></tr>";
			var teamId = 'team_' + teamCount;
			txt += "<tbody id='" + teamId + "'>";
			countSinceTeam = 0;
			newTeam = true;
		}

		// Show the report link and actions.
		if (countSinceTeam == 7) {
			txt += "<tr class='moreReports'><td colspan='2' onclick='ofwat.reportPage.showHiddenReports(&#39;"
					+ teamId + "&#39;)'>...<td></tr>";
		}
		if (countSinceTeam < 6) {
			txt += "<tr><td>";
		} else if (countSinceTeam >= 6) {
			txt += "<tr style='display:none'><td>";
		}
		txt += "<a href='javascript:ofwat.reportPage.selectReport(\"reportDisplay.page?reportId="
				+ currentReport.id + "\")'/>" + currentReportName + "</a>";
		txt += "</td>";
		txt += "<td>" + currentReport.lastModified + "</td>";
		txt = txt + "<td>";
		txt += "<a href='javascript:ofwat.reportPage.selectReport(\"reportDisplay.page?reportId="
				+ currentReport.id
				+ "\")' title='View "
				+ currentReportName
				+ "'/><img border='0' src='../../images/table.png' alt='View report'/></a>&nbsp;";
		txt += "<a href='javascript:ofwat.reportPage.populateReport(\"/Fountain/rest-services/report/\", \""
				+ currentReport.id
				+ "\")' title='Download "
				+ currentReportName
				+ " as spreadsheet'/><img border='0' src='../../images/page_excel.png' alt='Export report to Excel'/></a>&nbsp;";

		if (currentReport.redesign) {
			txt = txt
					+ "<a name='editReport' title='Redesign "
					+ currentReportName
					+ "' href='adhocReport.jsp?reportId="
					+ currentReport.id
					+ "' alt='Edit "
					+ currentReportName
					+ "'><img border='0' src='../../images/pencil.png'/></a>&nbsp;";
		}
		// Delete and publish activities only available for your own team's
		// reports
		if (ofwat.reportPage.isMyReport(currentReport)) {
			// publish/unpublish is not relevant for the general fountain users
			// group.
			if (currentReport.team != "Universal") {
				if (!currentReport.publicReport) {
					// it's not public so allow it to be published
					txt = txt
							+ "<a name='publishReport' title='Publish "
							+ currentReportName
							+ "' href='javascript:ofwat.reportPage.publishReport(\""
							+ currentReport.id
							+ "\", \""
							+ currentReportName
							+ "\")' alt='Publish "
							+ currentReportName
							+ "'><img border='0' src='../../images/world_go.png'/></a>&nbsp;";
				} else {
					// show the unpublish button
					txt = txt
							+ "<a name='hideReport' title='Make "
							+ currentReportName
							+ " no longer public' href='javascript:ofwat.reportPage.hideReport(\""
							+ currentReport.id
							+ "\", \""
							+ currentReportName
							+ "\")' alt='Hide "
							+ currentReportName
							+ "'><img border='0' src='../../images/arrow_undo.png'/></a>&nbsp;";
				}
			}
			txt = txt
					+ "<a name='deleteReport' title='Delete "
					+ currentReportName
					+ "' href='javascript:ofwat.reportPage.deleteReport(\""
					+ currentReport.link.uri
					+ "\",\""
					+ currentReportName
					+ "\")' alt='Delete "
					+ currentReportName
					+ "'><img border='0' src='../../images/bin_closed.png'/></a>&nbsp;";
		}
		txt = txt + "</td></tr>";

		if (i == reports.length - 1) {
			txt += "</tbody>";
		}
	}
	return txt;
};

ofwat.reportPage.deleteReport = function(uri, reportName) {

	if (window.confirm("Are you sure you want to delete " + reportName
			+ " report?")) {
		var xhrArgs = {
			url : uri,
			handleAs : "json",
			preventCache : true,
			load : function(data) {
				ofwat.showMessage("Successfully deleted " + reportName);
				location.reload();
			},
			error : function(error, ioargs) {
				var message = "";
				switch (ioargs.xhr.status) {
				case 401:
					message = "You are not authorised to delete this report";
					break;
				case 404:
					message = "The report was not found";
					break;
				case 500:
					message = "The server reported an error. " + error;
					break;
				case 407:
					message = "You need to authenticate with a proxy.";
					break;
				default:
					message = "Unknown error.";
				}
				ofwat.showError(message);
			}
		};
		ofwat.showMessage("Deleting report " + reportName
				+ "<img border='0' src='../../images/loader.gif'/>");

		// Call the asynchronous xhrDelete
		var deferred = dojo.xhrDelete(xhrArgs);
	}
};

ofwat.reportPage.publishReport = function(reportId, reportName) {
	var xhrArgs = {
		url : "../../rest-services/report/" + reportId + "/publish",
		handleAs : "json",
		preventCache : true,
		headers : {
			"Content-Type" : "application/json"
		},
		load : function() {
			ofwat.showMessage("Published " + reportName);
			ofwat.reportPage.loadReports();
		},
		error : function(error, ioargs) {
			var message = "";
			switch (ioargs.xhr.status) {
			case 401:
				message = "You are not authorised to publish this report";
				break;
			case 404:
				message = "The report was not found";
				break;
			case 500:
				message = "The server reported an error. " + error;
				break;
			case 407:
				message = "You need to authenticate with a proxy.";
				break;
			default:
				message = "Unknown error.";
			}
			ofwat.showError(message);
		}
	};
	ofwat.showMessage("Publishing report " + reportName
			+ " <img border='0' src='../../images/loader.gif'/>");
	dojo.xhrPost(xhrArgs);
};

ofwat.reportPage.hideReport = function(reportId, reportName) {
	var xhrArgs = {
		url : "../../rest-services/report/" + reportId + "/unpublish",
		handleAs : "json",
		preventCache : true,
		headers : {
			"Content-Type" : "application/json"
		},
		load : function() {
			ofwat.showMessage("Hidden " + reportName + " from other teams");
			ofwat.reportPage.loadReports();
		},
		error : function(error, ioargs) {
			var message = "";
			switch (ioargs.xhr.status) {
			case 401:
				message = "You are not authorised to hide this report";
				break;
			case 404:
				message = "The report was not found";
				break;
			case 500:
				message = "The server reported an error. " + error;
				break;
			case 407:
				message = "You need to authenticate with a proxy.";
				break;
			default:
				message = "Unknown error.";
			}
			ofwat.showError(message);
		}
	};
	ofwat
			.showMessage("Hiding report "
					+ reportName
					+ " from other teams <img border='0' src='../../images/loader.gif'/>");
	dojo.xhrPost(xhrArgs);
};

ofwat.reportPage.populateReport = function(url, id) {
	url = url + id;
	reportId = id;
	reportUrl = url;
	ofwat.reportCompanyRunSelection.getReportDetails();
	ofwat.reportPage.selectCompanyDialog(reportId);
	var companyType = null;
	var company = null;
	var run = null;
	var dataTag = null;
	var populate = true;

	if (!reportHasCompany) {
		var companyTypeDiv = dojo.byId('companyTypeDiv');
		dojo.removeClass(companyTypeDiv, "invisible");
		var companyDiv = dojo.byId('companyDiv');
		dojo.removeClass(companyDiv, "invisible");

		var companyType = null;
		var company = null;
		if (null == companyType || null == company) {
			ofwat.reportPage.setupDiv(reportId);
			populate = false;
		}
	} else {
		var companyTypeDiv = dojo.byId('companyTypeDiv');
		dojo.addClass(companyTypeDiv, "invisible");
		var companyDiv = dojo.byId('companyDiv');
		dojo.addClass(companyDiv, "invisible");
	}

	if (!reportHasRun) {
		var runDiv = dojo.byId('runDiv');
		dojo.removeClass(runDiv, "invisible");
		var dataTagDiv = dojo.byId('dataTagDiv');
		dojo.removeClass(dataTagDiv, "invisible");

		var run = null;
		var dataTag = null;
		if (null == run || null == dataTag) {
			if (populate) {
				ofwat.reportPage.setupDiv(reportId);
				populate = false;
			}
		}
	} else {
		var runDiv = dojo.byId('runDiv');
		dojo.addClass(runDiv, "invisible");
		var dataTagDiv = dojo.byId('dataTagDiv');
		dojo.addClass(dataTagDiv, "invisible");
	}

	if (reportHasCompany && reportHasRun) {
		ofwat.reportCompanyRunSelection.excelExportReport(url + "/excel");
	}

};

ofwat.reportPage.setupDiv = function(reportId) {
	var selectCompanyDiv = dojo.byId("selectCompanyDiv");

	var companyType = null;
	if (null != companyType) {
		dojo.byId('companyType').value = companyType;
	}
	var company = null;
	if (null != company) {
		dojo.byId('company').value = company;
		ofwat.reportCompanyRunSelection.loadDataTags(reportId);
	}

	var run = null;
	if (null != run) {
		dojo.byId('run').value = run;
		ofwat.reportCompanyRunSelection.loadDataTags(reportId);
	}
	var dataTag = null;
	if (null != dataTag) {
		dojo.byId('dataTag').value = dataTag;
	}

	dojo.removeClass(selectCompanyDiv, "invisible");
	dojo.byId('selectCompanyDiv').style.display = 'block';
	dojo.byId('fade').style.display = 'block';
	dojo.byId('goButton').focus();
};

ofwat.reportPage.selectCompanyDialog = function(reportId) {

	var selectCompanyDiv = dojo.create("div");
	selectCompanyDiv.id = "selectCompanyDiv";
	dojo.addClass(selectCompanyDiv, "invisible");
	dojo.addClass(selectCompanyDiv, "white_content");
	
	selectCompanyDiv.innerHTML = "" +
	"<div id='companyTypeDiv'>" +
	"<p>Please select from the following to run your report:</p>" +
	"<p class='frontPageSelector'>" +
	"	<label for='companyType'>Company Type</label>" + 
	"	<select id='companyType' onchange='ofwat.reportCompanyRunSelection.filterCompanies();'>" +
	"	<option value='0'>place holder</option>" +
	"</select>" +
	"</p>" +
	"</div>" +
	"<div id='companyDiv'>" +
	"<p class='frontPageSelector'>" +
	"	<label for='company'>Company</label>" + 
	"	<select id='company' onchange='ofwat.reportCompanyRunSelection.loadDataTags(" + reportId + ");'>" +
	"		<option value='0'>place holder</option>" +
	"	</select>" +
	"</p>" +
	"</div>" +
	"<div id='agendaDiv'>" +
	"<p class='frontPageSelector'>" +
	"	<label for='agenda'>Data Stream</label>" + 
	"	<select id='agenda' onchange='ofwat.reportCompanyRunSelection.updateRuns();ofwat.reportCompanyRunSelection.loadDataTags(" + reportId + ");'>" +
	"	<option value='0'>place holder</option>" +
	"</select>" +
	"</p>" +
	"</div>" +
	"<div id='runDiv'>" +
	"<p class='frontPageSelector'>" +
	"	<label for='run'>Run</label>" + 
	"	<select id='run' onchange='ofwat.reportCompanyRunSelection.loadDataTags(" + reportId + ");'>" +
	"	<option value='0'>place holder</option>" +
	"</select>" +
	"</p>" +
	"</div>" +
	"<div id='dataTagDiv'>" +
	"<p class='frontPageSelector'>" +
	"	<label for='dataTag'>Checkpoint</label>" + 
	"	<select id='dataTag'>" +
	"		<option value='0'>place holder</option>" +
	"	</select>" +
	"</p>" +
	"</div>" +
	"<p class='right'>" +
	"	<button class='goButton btn' id='goButton' onclick='ofwat.reportCompanyRunSelection.excelExportReport(reportUrl + \"/excel\");'>Go</button>" +
	"</p>";


	var allContentDiv = dojo.byId("allContent");
	dojo.place(selectCompanyDiv, allContentDiv, "last");
	ofwat.reportCompanyRunSelection.loadCompanyTypes().then(ofwat.reportCompanyRunSelection.loadCompanies).then(ofwat.reportCompanyRunSelection.loadAgenda).then(ofwat.reportCompanyRunSelection.loadDataTags(reportId)); 
}

ofwat.reportPage.showHiddenReports = function(teamId) {
	// if no team then show all reports!
	if (teamId != null) {
		var $teamTbody = $("#" + teamId);
		// show all the hidden TRs!
		$teamTbody.children("tr").css("display", "table-row");
		// Except the ... row with id "moreReports" this should be hidden
		$teamTbody.children(".moreReports").css("display", "none");
	} else {
		$("#tblReports").children("tbody").each(function(i) {
			$(this).children("tr").css("display", "table-row");
			// Except the ... row with id "moreReports"
			$(this).children(".moreReports").css("display", "none");
		})
	}
}

ofwat.reportPage.hideReports = function(teamId) {
	// if no team then hide all but first 6 for each team!
	if (teamId != null) {
		var $teamTbody = $("#" + teamId);
		var length = $teamTbody.children("tr").length;
		$teamTbody.children("tr").slice(6, length).css("display", "none");
		$teamTbody.children(".moreReports").css("display", "table-row");
	} else {
		$("#tblReports").children("tbody").each(function(i) {
			var length = $(this).children("tr").length;
			$(this).children("tr").slice(6, length).css("display", "none");
			$(this).children(".moreReports").css("display", "table-row");
		})
	}
}

// ofwat.reportPage.loadReports();