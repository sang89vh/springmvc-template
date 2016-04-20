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
		if(email.trim()!=""&&password.trim()!=""){
					BaasBox.login(email, password)
				    .done(function (user) {
				    	/*btn.button('reset');*/
				        console.log("Logged in ", user);
				        $.ajax({
							  url: "/user/store-client-user",
							  type: "POST",
							  contentType: "application/json; charset=utf-8",
							  dataType: "json",
							  data:  JSON.stringify(user)
							}).done(function(status) {							
								
								location.href="../application/my-application";
								
							}).error(function(error) {	
								
								BootstrapDialog.alert(error.message);
								console.log("error ", err);
						        btn.button('reset');
								
							});
				        
				       
				    })
				    .fail(function (err) {
				        console.log("error ", err);
				        BootstrapDialog.alert(err.responseJSON.message);
				        btn.button('reset');
				    })
					
			}else{
				BootstrapDialog.alert("Hãy nhập địa chỉ Email, Mật khẩu để tiếp tục đăng nhập!");
				btn.button('reset');
			}
				
		});
	
	
});