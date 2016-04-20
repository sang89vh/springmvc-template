$(document).ready(function(){
	
		$(document).on("keypress", ".div-login-container",
				function(event) {

					if (event.which == 13) {
						$("#btn-signup-submit").click();
					}
		});

	$("#btn-signup-submit").click(function() {
		var btn=$(this);
        btn.button('loading');
		var url=this.form.action;
		var email = $("input[name=email]").val();
		var password = $("input[name=password]").val();
		var application = $("input[name=application]").val();
		if(email.trim()!=""&&password.trim()!=""&&application.trim()!=""){
		var jsonPostData = {
			"email" : email,
			"password" : password,
			"application" : application
		}

		
		$.ajax({
			  url: url,
			  type: "POST",
			  contentType: "application/json; charset=utf-8",
			  dataType: "json",
			  data:  JSON.stringify(jsonPostData)
			}).done(function(singupOutVO) {
				console.log(singupOutVO.data.user.status)
				//baasbox login
				if("ACTIVE"==singupOutVO.data.user.status){
				BaasBox.login(email, password)
			    .done(function (user) {
			        console.log("Logged in ", user);
			        //location.href="../application/my-application";
			        var post = new Object();
                	post.applicationName = application;
                	post.username = BaasBox.getCurrentUser().username;     
                	BaasBox.save(post, "MBACKEND_APPLICATION")
                	  .done(function(res) {
                	    console.log("res ", res);
//                	    btn.button('reset');
                	    location.href="../application/my-application";
                	  })
                	  .fail(function(error) {
                	    console.log("error ", error);
                	    btn.button('reset');
                	    BootstrapDialog.alert(error.message);
                	  })
			    })
			    .fail(function (err) {
			    	btn.button('reset');
			        console.log("error ", err);
			        BootstrapDialog.alert(error.message);
			    })
			    .error(function (err) {
			    	btn.button('reset');
			        console.log("error ", err);
			        BootstrapDialog.alert(error.message);
			    })
				}
			 
			});
		}else{
			BootstrapDialog.alert("Hãy nhập địa chỉ Email, Mật khẩu. Tên ứng dụng để tiếp tục đăng ký!");
			btn.button('reset');
		}
	});
	
});