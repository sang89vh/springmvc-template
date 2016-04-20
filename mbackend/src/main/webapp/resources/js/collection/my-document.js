
var firstForm = null;
var jsonForm=null;

$(document).ready(function(){
	BaasBox.loadObject(collectionName, documentId)
	  .done(function(res) {
		  var data=res['data'];
		  delete  data._creation_date;
		  delete  data['@class'];
		  delete  data['@rid'];
		  delete  data._author;
		  delete  data['@version'];
		  delete  data.id;
	    console.log("res ", data);
	     jsonForm={data:data};
	    
	    firstForm = $("#mbackend-json-form").empty().jsonEdit(
	    		jsonForm
	        );
	    var myJsonData=JSON.stringify(firstForm.getData(), null, 4);
		   $("#mbackend-textarea-json").val(myJsonData);
	  })
	  .fail(function(error) {
	    console.log("error ", error);
	  })
	  
	  
	  $("#mbackend-btn-save").click(function(){
		  
		 
		  var post=firstForm.getData();
		  post.id=documentId;
		  
		  BaasBox.save(post, collectionName)
		  .done(function(res) {
		    console.log("res ", res);
		    location.href="/collection/my-collection/"+documentId+"/"+collectionId+"/"+collectionName;
		  })
		  .fail(function(error) {
		    console.log("error ", error);
		  })
	  })
	  $("#mbackend-btn-json-save").click(function(){
		  
		  if($("#mbackend-textarea-json").val()!=""){
			  myJsonData=  JSON.parse($("#mbackend-textarea-json").val());
			  jsonForm={data:myJsonData};
			  firstForm = $("#mbackend-json-form").empty().jsonEdit(
					  jsonForm
			  );
		  }
		  var post=firstForm.getData();
		  post.id=documentId;
		  
		  BaasBox.save(post, collectionName)
		  .done(function(res) {
			  console.log("res ", res);
			  location.href="/collection/my-collection/"+documentId+"/"+collectionId+"/"+collectionName;
		  })
		  .fail(function(error) {
			  console.log("error ", error);
		  })
	  })
	  $("#mbackend-edit-form").click(function(){
		
		   myJsonData=  JSON.parse($("#mbackend-textarea-json").val());
		   jsonForm={data:myJsonData};
		   firstForm = $("#mbackend-json-form").empty().jsonEdit(
				   jsonForm
		        );
		   
		  
			$("#mbackend-edit-form").removeClass("active");
		  $("#mbackend-edit-json").addClass("active");
		  
		  $("#mbackend-container-form").removeClass("hidden");
		  $("#mbackend-container-json").addClass("hidden");
		  
		  
	  })
	  
	   $("#mbackend-edit-json").click(function(){
		   var myJsonData=JSON.stringify(firstForm.getData(), null, 4);
		   $("#mbackend-textarea-json").val(myJsonData);
		   
			  
		$("#mbackend-edit-json").removeClass("active");
		  $("#mbackend-edit-form").addClass("active");
		  
		  $("#mbackend-container-json").removeClass("hidden");
		  $("#mbackend-container-form").addClass("hidden");
		  
		  
	  })
})
