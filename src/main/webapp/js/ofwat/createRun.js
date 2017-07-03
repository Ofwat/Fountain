ofwat.createRun = {
};


ofwat.createRun.getLoadingMessage = function (){
	return "Creating Run..."
};

ofwat.createRun.onLoad = function() {
	var fountainBaseURl = "/Fountain/rest-services";
	
	//Load run templates..
	$.ajaxSetup({async:false});
	var runTemplatesUrl = fountainBaseURl + "/runs/templates";
	$.get(runTemplatesUrl, function(data){
			//console.log(data);
			$.each(data.runTemplates, function(index, val){
				var defaultSelected = false;
			    var nowSelected = false;
				$('#selectRuntemplate').append( new Option(val.name,val.id,defaultSelected, nowSelected) );
				$('#selectRuntemplate').prop('disabled', false);
			})
		}
	);
	
	//Load run company templates..
	$.ajaxSetup({async:false});
	var runCompanyTemplatesUrl = fountainBaseURl + "/runs/companyTemplate";
	$.get(runCompanyTemplatesUrl, function(data){
			//console.log(data);
			$.each(data.runCompanyTemplates, function(index, val){
				var defaultSelected = false;
			    var nowSelected = false;
				$('#selectRunCompanytemplate').append( new Option(val.name,val.id,defaultSelected, nowSelected) );
				$('#selectRunCompanytemplate').prop('disabled', false);
			})
		}
	);
	
	ofwat.createRun.LoadAgenda();
	ofwat.createRun.LoadDataSourcesForSelectedAgenda();
	ofwat.createRun.LoadTagsForSelectedRun();
   

	$("#btnCancel").click(function(){
		window.location.href = "/Fountain/jsp/protected/runs/listRuns.jsp";
	});
	
	
	$("#createRun").submit(function(event){
		event.preventDefault();
		var $createRunForm = $("#createRun");
		//perform validation and submit. 
		var runName = $("#runName").val();
		var runDescription = $("#txtDescription").val();
		var runTemplateId = $("#selectRuntemplate").val();
		var runCompanyTemplateId = $("#selectRunCompanytemplate").val();
		var agendaId = $("#selectAgenda").val();
		var dataSourceId = $("#selectDatasource").val();
		var baseTagId = $("#selectTag").val();
		var defaultRun = $("#selectDefault").val();
		
		//Show the ajax spinner!
		//render all the div with a spinner?
		var xhr = $.ajax({
			async: true,
			url:"/Fountain/rest-services/runs/" + runName + "?agendaId=" + agendaId + "&sourceRunId=" + dataSourceId + "&sourceTagId=" + baseTagId + "&runTemplateId=" + runTemplateId + "&runCompanyTemplateId=" + runCompanyTemplateId + "&runDescription=" + encodeURIComponent(runDescription) + "&defaultRun=" + defaultRun, 
			success:function(data){
				//navigate to run list add to flash!
				//alert("Created run.");
				window.location.href = "/Fountain/jsp/protected/runs/listRuns.jsp";
				//alert("completed");
			},
			type:"POST"
		});
		xhr.fail(function(data){
			//Call pnotify here!
			$.pnotify({
				 title: 'Could not create run',
				 text: 'Could not create Run, make sure you have entered a name and selected a Run Template. ' + data.responseText,
				 type: 'error',
				 opacity: .8,
				 history: false
			});			
			//Kill any modal dialogues that are open. 
			$("#modalRunCreate").modal('hide');
		});
		
		//Show a message to say run is being created then re-direct to the runlist!
		$("#btnModalOk").click(function(){
			//redirect to the run list page.
			window.location.href = "/Fountain/jsp/protected/runs/listRuns.jsp";
		});
		$("#modalRunCreate").modal({backdrop:'static'});
	});

};	 

ofwat.createRun.LoadAgenda = function() {
	var fountainBaseURl = "/Fountain/rest-services";
	var agendaUrl = fountainBaseURl + "/agenda";
	var selectAgenda = $('#selectAgenda'); 
	$.get(agendaUrl, function(data){
		selectAgenda.empty();
		var defaultSelected = false;
		var nowSelected = false;
		$.each(data, function(index, val){
			selectAgenda.append( new Option(val.name,val.id,defaultSelected, nowSelected) );
		});
		selectAgenda.prop('disabled', false);
	});			
	selectAgenda.change(ofwat.createRun.LoadDataSourcesForSelectedAgenda());
};

ofwat.createRun.LoadDataSourcesForSelectedAgenda = function() {
	var fountainBaseURl = "/Fountain/rest-services";
	var selectedAgendaId = $('#selectAgenda').val();
	var dataSourcesUrl = fountainBaseURl + "/runs" + "?agendaId=" + selectedAgendaId + "&standardRole=true&defaultRole=true"
	var selectDatasource = $('#selectDatasource')
	$.get(dataSourcesUrl, function(data){
		selectDatasource.empty();
		var defaultSelected = false;
		var nowSelected = false;
		$.each(data, function(index, val){
			selectDatasource.append( new Option(val.name, val.id, defaultSelected, nowSelected) );
		});
		selectDatasource.prop('disabled', false);
	});			
	selectDatasource.change(ofwat.createRun.LoadTagsForSelectedRun());
};

ofwat.createRun.LoadTagsForSelectedRun = function() {
	var fountainBaseURl = "/Fountain/rest-services";
	var selectedDataSourceId = $('#selectDatasource').val();
	var tagsUrl = fountainBaseURl + "/tags/run" + "?runId=" +selectedDataSourceId;
	var selectTag = $('#selectTag')
	$.get(tagsUrl, function(data){
		selectTag.empty();
		var defaultSelected = true;
	    var nowSelected = true;
	    selectTag.append( new Option("<Latest>", "0", defaultSelected, nowSelected));
		$.each(data, function(index, val){
			defaultSelected = false;
		    nowSelected = false;
		    selectTag.append( new Option(val.displayName, val.id, defaultSelected, nowSelected) );
		});
		selectTag.prop('disabled', false);
	});
};


