ofwat.createAgenda = {
};


ofwat.createAgenda.getLoadingMessage = function (){
	return "Creating Data Stream ..."
};

ofwat.createAgenda.onLoad = function() {
	
	$("#btnCancel").click(function(){
		window.location.href = "/Fountain/jsp/protected/runs/listAgenda.jsp";
	});
	
	$("#createAgenda").submit(function(event){
		event.preventDefault();
		var $createAgendaForm = $("#createAgenda");
		//perform validation and submit. 
		var agendaName = $("#agendaName").val();
		var agendaCode = $("#agendaCode").val();
		var agendaDescription = $("#agendaDescription").val();
		
		//Show the ajax spinner!
		//render all the div with a spinner?
		var xhr = $.ajax({
			async: true,
			url:"/Fountain/rest-services/agenda/" + agendaName + "?agendaCode=" + agendaCode + "&agendaDescription=" + agendaDescription, 
			success:function(data){
				//navigate to run list add to flash!
				$.pnotify({
					 title: 'Could not create Data Stream',
					 text: 'Could not create Data Stream, make sure you have entered a name and code. ' + data.responseText,
					 type: 'error',
					 opacity: .8,
					 history: false
				});			
				window.location.href = "/Fountain/jsp/protected/runs/listRuns.jsp";
			},
			type:"POST"
		});
		xhr.fail(function(data){
			//Call pnotify here!
			$.pnotify({
				 title: 'Could not create Data Stream',
				 text: 'Could not create Data Stream, make sure you have entered a name and code. ' + data.responseText,
				 type: 'success',
				 opacity: .8,
				 history: false
			});			
			//Kill any modal dialogues that are open. 
			$("#modalAgendaCreate").modal('hide');
		});
		
		//Show a message to say run is being created then re-direct to the runlist!
		$("#btnModalOk").click(function(){
			//redirect to the run list page.
			window.location.href = "/Fountain/jsp/protected/runs/listRuns.jsp";
		});
		$("#modalAgendaCreate").modal({backdrop:'static'});
	});

};	 



