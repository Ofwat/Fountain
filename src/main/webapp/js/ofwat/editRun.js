ofwat.editRun = {
};


ofwat.editRun.onLoad = function(runId) {
  	Handlebars.registerHelper('foreach', ofwat.forEachHelper);
	Handlebars.registerHelper('checked', ofwat.checkedHelper);
	//Populate the modal div with correct string for the run
	var modelConfirmText = "";
	var modelUndoTxt = "";
//	var runId = <%=request.getParameter("runId")%>;
	var url = "/" + runId + "/models";
	//Call back from the templating function - this call is done when the data is recieved but before the 
	//template is rendered!
	var dataCallback = function(data){
		var _this = this;
		//$(".modal-body").append("<p>Do you really wish to complete run " + data.name + "?</p>");
		modelConfirmText = "<p>Do you really wish to complete run " + data.name + "?</p>";
		modelUndoText = "<p>Do you really wish to undo run " + data.name + "?</p>";
	}
	//Add the callback to the templating call in Ofwat.js
	ofwat.template.editRun.dataCallback = dataCallback;
	//load the template [assing the node and additional params. 
	Handlebars.registerHelper('checked', ofwat.checkedHelper);
	Handlebars.registerHelper('disabled', ofwat.disabledHelper);
	ofwat.loadTemplate(
		dojo.byId("editRunData"),
		ofwat.template.editRun, {
			url : url,
		}
	);
	//After synchronous templating above. 			
	$("#myModalUndo .modal-body").append(modelUndoText);
	$("#myModalComplete .modal-body").append(modelConfirmText);			
	// $("#btnCompleteRun").removeAttr("disabled");
	// $("#btnUndoRun").removeAttr("disabled");

	$("#btnCompleteRun").click(function(){
		//Show a dialog asking whether they really want to complete the run...
		$('#myModalComplete').modal();
	});					

	$("#btnUndoRun").click(function(){
		//Show a dialog asking whether they really want to complete the run...
		$('#myModalUndo').modal();
	});			
	
	$("#btnUndoRunConfirm").click(function(){
		var xhr = $.ajax({
			type:"PUT",
			url:"/Fountain/rest-services/runs/" +  runId + "?completed=false",
			success: function(data){
				//TODO should really show the undo button here!
				window.location.href = "/Fountain/jsp/protected/runs/listRuns.jsp";
			}
		})
		xhr.error(function(error){
			$('#myModalUndo').modal('hide');
			$.pnotify({
				 title: 'Could not undo run',
				 text: 'Could not undo run: ' + error.statusText + '. ' + error.responseText,
				 type: 'error',
				 opacity: .8,
				 history: false
			});
			
		})						
	});				
	
	$("#btnCompleteRunConfirm").click(function(){
		//TODO Decide what we do here!
		var xhr = $.ajax({
			type:"PUT",
			url:"/Fountain/rest-services/runs/" +  runId + "?completed=true",
			success: function(data){
				window.location.href = "/Fountain/jsp/protected/runs/listRuns.jsp";
			}
		})
		xhr.error(function(error){
			//Kill the modal!
			$('#myModalComplete').modal('hide');					
			$.pnotify({
				 title: 'Could not complete run',
				 text: 'Could not complete run: ' + error.statusText + '. ' + error.responseText,
				 type: 'error',
				 opacity: .8,
				 history: false
			});		

		})
	});
	
	//Attach the handler to all the complete buttons.
	var tagButtons = $(".completeCompanyModel");
	$.each(tagButtons, function(id, val){
		var _this = this;
		_this.val = val;
		$(val).click(function(){
			var $dataNode = $(val.parentNode.parentNode);
			var modelName = $dataNode.data("modelname");
			var companyName = $dataNode.data("companyname");
			var runId = $dataNode.data("runid");
			var runName = $dataNode.data("runname");
			var companyId = $dataNode.data("companyid");
			var modelId = $dataNode.data("modelid");
			var tagOnCompletion = $dataNode.find(".tagOnCompletion").is(':checked'); 
			//Change the row to a spinner! i.e replace row with one that is colspan
			//Create a new row
			var $newRow = $("<tr><td colspan='4' style='text-align: center;'><img src='/Fountain/images/ajax-loader.gif'></img></td></tr>");
			//hide the existing row.
			$dataNode.hide();
			//add the new row
			$newRow.insertBefore($dataNode);

			_this.$dataNode = $dataNode;
			_this.$newRow = $newRow;
			//Create a tag.
			var xhr = $.ajax({
				type:"PUT",
				url:"/Fountain/rest-services/tags/data/" + encodeURIComponent(modelName + ". " + runName + ". Tag") + "?runId=" + runId + "&modelId=" + modelId + "&companyId=" + companyId + "&tagOnCompletion=" + tagOnCompletion, 
				success: function(data){
					$.pnotify({
						title: 'Added Checkpoint',
					    text: 'Completed ' + modelName + ' on run "' + $('#txtName').val() +'" for company ' + companyName + '.',
					    type: 'success',
					    opacity: .8,
					    history: false
					});	

					//Get the row!
					var $val = _this.val;
					//Get the parent row!
					var parentTd = $val.parentElement;
					_this.$dataNode.find(".completeCompanyModel").addClass('hide');
					$undoButton = $(parentTd).find(".undoCompanyModel");
					$undoButton.removeClass("hide");
					//Add the details to the row. 
					var $completedTd = $(parentTd).parent().find(".completed");
					var $completedByTd = $(parentTd).parent().find(".completedBy");
					var $completedDateTd = $(parentTd).parent().find(".completedDate");
					var $tagOnCompletionTd = $dataNode.find(".tagOnCompletionTd"); 
					$completedTd.empty();
					$completedByTd.text(data.user);
					$completedDateTd.text(data.completedDate);
					$tagOnCompletionTd.empty();
					var disabled = ofwat.disabledHelper(data.completed);
					var checked = ofwat.checkedHelper(data.tagged);
					$tagOnCompletionTd.append('<input type="checkbox" class="tagOnCompletion" ' + disabled + ' ' + checked + '/>');
					
					_this.$newRow.remove();
					_this.$dataNode.show();

				}
			})
			xhr.error(function(error){
				_this.$newRow.remove();
				_this.$dataNode.show();

				$.pnotify({
					 title: 'Could not create checkpoint',
					 text: 'Could not complete model: ' + error.statusText + '',
					 type: 'error',
					 opacity: .8,
					 history: false
				});							

			})
		});
	})
	
	
	var undoButtons = $(".undoCompanyModel");
	$.each(undoButtons, function(id, val){
		var _this = this;
		_this.val = val;
		$(val).click(function(){
			var $dataNode = $(val.parentNode.parentNode);
			var modelName = $dataNode.data("modelname");
			var companyName = $dataNode.data("companyname");
			var runId = $dataNode.data("runid");
			var companyId = $dataNode.data("companyid");
			var modelId = $dataNode.data("modelid");
			//make a call to create the tag. 
			
			//Change the row to a spinner! i.e replace row with one that is colspan
			//Create a new row
			var $newRow = $("<tr><td colspan='4' style='text-align: center;'><img src='/Fountain/images/ajax-loader.gif'></img></td></tr>");
			//hide the existing row.
			$dataNode.hide();
			//add the new row
			$newRow.insertBefore($dataNode);

			_this.$dataNode = $dataNode;
			_this.$newRow = $newRow;
			
			var xhr = $.ajax({
				type:"DELETE",
				url:"/Fountain/rest-services/tags/data/" + encodeURIComponent(modelName + " " + companyName + " Tag") + "?runId=" + runId + "&modelId=" + modelId + "&companyId=" + companyId, 
				success: function(data){
					$.pnotify({
						title: 'Removed Checkpoint',
					    text: 'Successfully undone checkpoint.',
					    type: 'success',
					    opacity: .8,
					    history: false
					});	
					//Get the row!
					var $val = _this.val;
					var parentTd = $val.parentElement;
					_this.$dataNode.find(".undoCompanyModel").addClass('hide');
					$completeButton = $(parentTd).find(".completeCompanyModel");
					$completeButton.removeClass("hide");
					
					var $completedTd = $(parentTd).parent().find(".completed");
					var $completedByTd =$(parentTd).parent().find(".completedBy");
					var $completedDateTd =$(parentTd).parent().find(".completedDate");
					var $tagOnCompletionTd = $dataNode.find(".tagOnCompletionTd"); 
					$completedTd.empty();
					$completedByTd.empty();
					$completedDateTd.empty();							
					$tagOnCompletionTd.empty();
					$tagOnCompletionTd.append('<input type="checkbox" class="tagOnCompletion" />');

					_this.$newRow.remove();
					_this.$dataNode.show();
				}
			})
			xhr.error(function(error){
				_this.$newRow.remove();
				_this.$dataNode.show();
				
				$.pnotify({
					 title: 'Could not delete checkpoint',
					 text: 'Could not delete the checkpoint: ' + error.responseText + '',
					 type: 'error',
					 opacity: .8,
					 history: false
				});							

			})
		});
	})			

	
	var $companyLists = $(".innerModelCompanies");
	$.each($companyLists, function(index, value) {
		$value = $(value);
		$value.on('shown', function() {
			var $div = $(value);
			//need to get parent/parent/prev sib/1st td, 1st button 
			var b = $("#demo_" + $div.data("id")).parent().parent()
					.prev().first()
			b = b.find(":button")[0]
			b.innerHTML = "-";

		});
		$value.on('hide', function() {
			var $div = $(value);
			//need to get parent/parent/prev sib/1st td, 1st button 
			var b = $("#demo_" + $div.data("id")).parent().parent()
					.prev().first()
			b = b.find(":button")[0]
			b.innerHTML = "+";
		})
	});

};
