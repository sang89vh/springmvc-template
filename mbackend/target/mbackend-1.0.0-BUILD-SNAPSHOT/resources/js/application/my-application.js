function changeEventForButton(){
	//event click create new application
	$(".mbackend-create-application").click(function(){
	BootstrapDialog.show({
        message: 'Tên ứng dụng: <input type="text" class="form-control" placeholder="Tên ứng dụng" required autofocus />',       
        buttons: [{
            label: 'Hủy',
            action: function(dialogRef) {
                dialogRef.close();
            }
        },{
            label: 'Lưu',
            hotkey: 13,
            cssClass: 'btn-primary',
            action: function(dialogRef) {
            	var applicationName = dialogRef.getModalBody().find('input').val();
                if($.trim(applicationName.toLowerCase()) == '') {
                    alert('Tên ứng dụng của bạn!');
                    return false;
                }else{
                	console.log(applicationName);
                	var post = new Object();
                	post.applicationName = applicationName;
                	post.username = BaasBox.getCurrentUser().username;     
                	BaasBox.save(post, "MBACKEND_APPLICATION")
                	  .done(function(res) {
                	    console.log("res ", res);
                	    var applicationId=res.id;
                	    location.href="../application/manager/"+applicationId;
                	  })
                	  .fail(function(error) {
                	    console.log("error ", error);
                	  })
                	 dialogRef.close();
                	 return true;
                }
                
            }
        }]
    });
	});
	
	
	$(".mbackend-manager-application").click(function(){
		var applicationId=$(this).attr("applicationId");
		location.href="../application/manager/"+applicationId;
	})
}
$(document).ready(function(){
		BaasBox.loadCollection("MBACKEND_APPLICATION")
		  .done(function(res) {
		    
		    var i=1;
		    
		    res.forEach(function(e){
		    	 
		    	    //console.log(e.applicationName);
		    	    $("#mbackend-appname-" + i).text(e.applicationName);
		    	    var button=$("#mbackend-app-btn-" + i);
		    	    var createDate=$("#mbackend-app-create-" + i);
		    	    button.removeClass("btn-default");
		    	    button.removeClass("mbackend-create-application");
		    	    button.addClass("btn-primary");
		    	    button.addClass("mbackend-manager-application");
		    	    button.text("Quản lý ứng dụng");
		    	    button.attr("applicationId",e.id);
		    	    console.log(moment(new Date(e._creation_date)).fromNow());
		    	    createDate.text(moment(new Date(e._creation_date)).fromNow());
				    i++;
				    return i!=8;
		    	
		    })
		    changeEventForButton();
		   
		  })
		  .fail(function(error) {
		    console.log("error ", error);
		  })
		
		  
	
	
});