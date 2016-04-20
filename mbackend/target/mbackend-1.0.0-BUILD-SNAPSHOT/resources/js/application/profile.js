var applicationName="";
$(document).ready(function(){
	BaasBox.loadObject("MBACKEND_APPLICATION", applicationId)
	  .done(function(res) {
	    applicationName=res.data.applicationName;
		$("#mbackend-application-name").text(applicationName);
	  })
	  .fail(function(error) {
	    console.log("error ", error);
	  })
	  
	  $("#mbackend-edit-application").click(function(){
		  BootstrapDialog.show({
			    title:'Cập nhật thông tin ứng dụng',
	            message: 'Tên ứng dụng: <input type="text" class="form-control" value="'+applicationName+'">',
	            onhide: function(dialogRef){
	                var myApplication = $.trim(dialogRef.getModalBody().find('input').val());
	                
	                if(myApplication === '') {
	                    alert('Lỗi! Điền tên ứng dụng của bạn!');
	                    return false;
	                }
	                if(myApplication!=applicationName){
	                	
	                applicationName=myApplication;
	                
	                BaasBox.updateField(applicationId, "MBACKEND_APPLICATION", "applicationName", myApplication)
	                .done(function(res) {
	                  $("#mbackend-application-name").text(applicationName);
	                  console.log("res ", res);
	                })
	                .fail(function(error) {
	                  console.log("error ", error);
	                })
	                }
	            },
	            buttons: [{	            	
	                label: 'Lưu',
	                cssClass: 'btn btn-primary', 
	                action: function(dialogRef) {
	                    dialogRef.close();
	                }
	            }]
	        });
	  })
	
})