$(document).ready(function() {

	$(".form-signup input[type=submit]").click(function(event) {
		event.preventDefault();
		var url=this.form.action;
		var email = $("input[name=email]").val();
		var password = $("input[name=password]").val();
		var application = $("input[name=application]").val();
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
			 
			});
	});
});