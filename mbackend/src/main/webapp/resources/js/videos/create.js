

$(document).ready(function() {

	$("#btn-cancel").click(function() {

		BootstrapDialog.confirm('Hi,are you sure cancel it?', function(result) {
			if (result) {
				location.href = "/videos/manager";
			}
		});
	});

	$("#btn-save").click(function() {
		var btn = this;

		var ytbid = "";
		var message = "";
		var link = $("#link").val();
		if (link === null || link === "") {
			message = message + "<br/> The Link is empty ";
		} else {
			ytbid = link.replace("https://www.youtube.com/watch?v=", "");
		}

		var banner = $("#banner").val();
		if (ytbid !== "" && (banner === null || banner === "")) {
			banner = "http://img.youtube.com/vi/" + ytbid + "/hqdefault.jpg";
			
			
			
		}
		if (banner === null || banner === "") {
			message = message + "<br/> The banner is empty ";
		}

		var keyword = $("#keyword").val();
		if (keyword === null || keyword === "") {
			message = message + "<br/> The keyword is empty ";
		}

		var videoid = $("#videoid").val();
		if (ytbid !== "" && (videoid === null || videoid === "")) {
			videoid = ytbid;
			$("#videoid").val(videoid);
		}
		if (videoid === null || videoid === "") {
			message = message + "<br/> The videoid is empty ";
		}
		var title = $("#title").val();
		if (title === null || title === "") {
			message = message + "<br/> The title is empty ";
		}
		var intro = $("#intro").val();
		if (intro === null || intro === "") {
			message = message + "<br/> The intro is empty ";
		}
		var privacy = $("#privacy").val();

		var category = $("#category").val();

		if (message !== "") {
			$("#alert-message").html("");
			$("#alert-message").html(message);
		} else {
			$(btn).button("loading");
			var post = {
				"VIDEOID" : videoid,
				"TITLE" : title,
				"KEYWORD" : keyword,
				"YOUTUBE" : ytbid,
				"CONTENT" : intro,
				"applicationId" : "041507c2-6096-474b-a685-f13ff9455a44",
				"BANNER" : banner,
				"CATEGORIES" : category,
				"PRIVACY" : privacy
				
			}

			BaasBox.save(post, "MOVIES").done(function(res) {
				console.log("res ", res);
				$(btn).button("reset");
				location.href = "/videos/manager";
			}).fail(function(error) {
				console.log("error ", error);
			})

		}

	});
});