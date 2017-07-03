ofwat.tagRun = {
        allRunsArray: {},
        allAgendaArray: {}
};


ofwat.tagRun.getLoadingMessage = function (){
	return "Creating Checkpoint..."
};

ofwat.tagRun.onLoad = function() {
	
    ofwat.tagRun.loadAgenda();

	$("#btnCancel").click(function(){
		window.location.href = "/Fountain/jsp/protected/runs/listRuns.jsp";
	});
	
	
	$("#tagRun").submit(function(event){
		event.preventDefault();
		var $tagRunForm = $("#tagRun");
		//perform validation and submit. 
		var tagName = $("#tagName").val();
		var tagNote = $("#txtNote").val();
		var dataSourceId = $("#selectDatasource").val();
		
		//Show the ajax spinner!
		//render all the div with a spinner?
		var xhr = $.ajax({
			async: true,
			url:"/Fountain/rest-services/tags/data/free/" + tagName + "?runId=" + dataSourceId, 
			success:function(data){
				//navigate to run list add to flash!
				//alert("Created run.");
				window.location.href = "/Fountain/jsp/protected/runs/listRuns.jsp";
				//alert("completed");
			},
			type:"PUT"
		});
		xhr.fail(function(data){
			//Call pnotify here!
			$.pnotify({
				 title: 'Could not create checkpoint',
				 text: 'Could not create checkpoint, make sure you have entered a name and selected data source.',
				 type: 'error',
				 opacity: .8,
				 history: false
			});					
		});
		
		//Show a message to say run is being created then re-direct to the selectDatasource!
		$("#btnModalOk").click(function(){
			//redirect to the run list page.
			window.location.href = "/Fountain/jsp/protected/runs/listRuns.jsp";
		});
		$("#modalRunCreate").modal({backdrop:'static'});
	});
};	 


ofwat.tagRun.loadAgenda = function() {
//    alert("Loading Agenda");
    var xhrArgs = {
        url: "/Fountain/rest-services/agenda",
        handleAs: "json",
        preventCache: true,
        sync: true,
        headers: {
            "accept": "application/json"
        },
        load: function(data){
            ofwat.tagRun.populateAgenda(data);
            ofwat.showMessage("");
        },
        error: function(error) {
            ofwat.showError("loadAgenda errored", error);
        }
    };
    ofwat.showMessage("Loading agenda... <img border='0' src='../../images/loader.gif'/>");
    var deferred = dojo.xhrGet(xhrArgs);
    return deferred;
};

ofwat.tagRun.populateAgenda = function(data) {
//    alert("populating Agenda");
    var agendaArray = data;
    var elAgendaSelector = dojo.byId("selectAgenda");
    dojo.empty("selectAgenda");
    for (i=0;i<agendaArray.length;i++)
    {
        var agenda = agendaArray[i];
        dojo.place("<option value='"+agenda.id+ "'> " + ofwat.escapeHTML(agenda.name) + "</option>", elAgendaSelector);
        ofwat.tagRun.allAgendaArray[agenda.id] = agenda;
    }
    var selectedAgendaId = elAgendaSelector.options[elAgendaSelector.selectedIndex].value;
    ofwat.tagRun.updateRuns();
};

ofwat.tagRun.loadRuns = function(agendaId) {
//    alert("Loading Runs");
    var xhrArgs = {
		url: "/Fountain/rest-services/runs?agendaId=" + agendaId,
        handleAs: "json",
        preventCache: true,
        sync: true,
        headers: {
            "accept": "application/json"
        },
        load: function(data){
            ofwat.tagRun.populateRuns(data);
            ofwat.showMessage("");
        },
        error: function(error) {
            ofwat.showError("loadRuns errored", error);
        }
    };
    ofwat.showMessage("Loading company types... <img border='0' src='../../images/loader.gif'/>");
    var deferred = dojo.xhrGet(xhrArgs);
    return deferred;
};

ofwat.tagRun.populateRuns = function(data) {
//    alert("populating Runs");
    var runsArray = data;
    var elRunSelector = dojo.byId("selectDatasource");
    dojo.empty("selectDatasource");
    for (i=0;i<runsArray.length;i++)
    {
        var run = runsArray[i];
        if (run.runRole != "PROXY") {
	        dojo.place("<option value='"+run.id+ "'> " + ofwat.escapeHTML(run.name) + "</option>", elRunSelector);
	        ofwat.tagRun.allRunsArray[run.id] = run;
	    }
    }
    var selectedRunId = elRunSelector.options[elRunSelector.selectedIndex].value;
};

ofwat.tagRun.updateRuns = function(){
    var elSelector = dojo.byId("selectAgenda");
    var selectedAgendaId = elSelector.options[elSelector.selectedIndex].value;
    ofwat.tagRun.loadRuns(selectedAgendaId);
};

