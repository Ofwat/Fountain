ofwat.updates = {};

ofwat.viewRelease = {};

ofwat.viewRelease.getLatestRelease = function(callback){
	$.ajax({
		type: "GET",
		url: "/Fountain/rest-services/release/latest",
		//processData: false,
		success: function(release){
			console.log(release);
			callback(release);
		},
		//dataType: "json",
		fail:function(error){
			//We aren't going to log to the console as it will fail in IE without modernizr
		}
	})	
};


ofwat.viewRelease.onLoad = function(releaseId) {
  	Handlebars.registerHelper('foreach', ofwat.forEachHelper);
	Handlebars.registerHelper('checked', ofwat.checkedHelper);
	Handlebars.registerHelper('date', ofwat.dateHelper);
	var url = releaseId;
	ofwat.loadTemplate(
		dojo.byId("editReleaseData"),
		ofwat.template.viewRelease, {
			url : url,
		}
	);
};


ofwat.viewRelease.getFormData = function(form){
	var formData = {};
	var updates = [];
	var summary = [];
	$(form).find(':input').each(function(){
		console.log($(this));
		formData[$(this).attr("id")] = this;
	});
	return formData;
}

ofwat.viewRelease.addupdate = function(){
	//Get the data from the form.
	var formData = ofwat.viewRelease.getFormData($("#addUpdateFrm"));
	var title = formData.updtTitle.value;
	var externalLink = formData.updtLink.value;
	var sortOrder = formData.updtSortOrder.value;
	var description = tinyMCE.activeEditor.getContent();
	var releaseId = $("#releaseId").val();
	
	//Send to the server
	
	var data = {
		"title":title,
		"description":description,
		"externalLink":externalLink,
		"sortOrder": sortOrder
	}
	
	$.ajax({
		type: "POST",
		url: "/Fountain/rest-services/release/" + releaseId + "/update",
		data: data,
		//processData: false,
		success: function(update){
			//insert update
			//<td>{{id}}</td><td>{{description}}</td><td>{{title}}</td><td><a href="{{externalLink}}">{{externalLink}}</a></td><td><a class="btn updateButton" id="deleteUpdate" data-updateid="{{id}}" href="#">Delete</a></td>
			$("#tblUpdates tr:last").after("<tr id='update_" + update.id + "'><td>" + update.sortOrder + "</td><td>" + update.id + "</td><td>" + update.description + "</td><td>" + update.title + "</td><td>" + update.externalLink + "</td><td><a class='btn updateButton' id='deleteUpdate_" + update.id + "' data-updateid='" + update.id + "' href='#'>Delete</a></td></tr>");
			$("#deleteUpdate_" + update.id).click(function(){ofwat.viewRelease.deleteUpdate($(this).data("updateid"))});
			$.pnotify({
				 title: 'Added Update',
				 text: 'Added update',
				 type: 'success',
				 opacity: .8,
				 history: false
			});
		},
		//dataType: "json",
		fail:function(error){
			$.pnotify({
				 title: 'Could not add update.',
				 text: 'Could not add update: ' + error.responseText + '',
				 type: 'error',
				 opacity: .8,
				 history: false
			});
		}
	})
}


ofwat.viewRelease.deleteUpdate = function(updateId){
	//Get the data from the form.
	//Send to the server
	$.ajax({
		type: "DELETE",
		url: "/Fountain/rest-services/release/update/" + updateId,
		success: function(release){
			$("#update_" + release.id).remove();
			$.pnotify({
				 title: 'Deleted Update',
				 text: 'Deleted Update',
				 type: 'success',
				 opacity: .8,
				 history: false
			});
		},
		fail:function(error){
			$.pnotify({
				 title: 'Could not delete update.',
				 text: 'Could not delete update: ' + error.responseText + '',
				 type: 'error',
				 opacity: .8,
				 history: false
			});			
		}
	})
}

ofwat.viewRelease.updateRelease = function(){
	var formData = ofwat.viewRelease.getFormData($("#releaseFrm"));
	var name = formData.txtName.value;
	var version = formData.txtVersion.value;
	var releaseDate = formData.txtDate.value;
	var published = $(formData.chkPublished).is(':checked');
	var releaseId = $("#releaseId").val();
	//Send to the server
	
	var data = {
		name:name,
		version:version,
		releaseDate:releaseDate,
		published:published
	}
	
	$.ajax({
		type: "POST",
		url: "/Fountain/rest-services/release/" + releaseId,
		data: data,
		//processData: false,
		success: function(xhr){
			$.pnotify({
				 title: 'Updated Release.',
				 text: 'Updated Release',
				 type: 'success',
				 opacity: .8,
				 history: false
			});
		},
		//dataType: "json",
		fail:function(error){
			$.pnotify({
				 title: 'Could not update release.',
				 text: 'Could not update release: ' + error.responseText + '',
				 type: 'error',
				 opacity: .8,
				 history: false
			});
		}
	})
}

ofwat.updates.deleteRelease = function(){
	//Get the data from the form.
	
	//Send to the server
	
	//Show the response	
	
	//Redirect to show releases page. 
}

ofwat.updates.createRelease = function(){
	//Get the data from the form.
	
	//Send to the server
	
	//Show the response
	
	//Render the view/edit release page. 
}