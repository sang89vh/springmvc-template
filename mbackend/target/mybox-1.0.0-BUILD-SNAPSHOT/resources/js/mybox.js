var mybox = {
	firstName:"mybox",		
	postFormSendJson : function(c) {
		return this.firstName ;
	}
};

$(document).ready(function() {

	$(".form-json input[type=submit]").click(function(event) {
		event.preventDefault();
		formData = JSON.parse(JSON.stringify($(this).serializeArray()));
		
	});
});