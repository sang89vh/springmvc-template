function vimeoLoadingThumb(id){    
    var url = "http://vimeo.com/api/v2/video/" + id + ".json?callback=showThumb";
      
    var id_img = "#vimeo-" + id;
    var script = document.createElement( 'script' );
    script.type = 'text/javascript';
    script.src = url;

    $("#vimeo-banner").before(script);
}

function showThumb(data){
    var id_img = "#vimeo-" + data[0].id;
    $("#vimeo-banner").attr('src',data[0].thumbnail_medium);
    banner = data[0].thumbnail_medium;
	$("#banner").val(data[0].thumbnail_medium);
	type="VIMEO";
}

$(document).ready(function(){
	$("#btn-cancel").click(function() {

		BootstrapDialog.confirm('Hi,are you sure cancel it?', function(result) {
			if (result) {
				location.href = "/videos/detail/"+VIDEOID;
			}
		});
	});
	$("#btn-save").click(function() {
		var btn = this;
		var type="YOUTUBE";;

		var ytbid = "";
		var vimeoid = "";
		var vimeoidytbid = "";
		var message = "";
		var url = $("#url").val();
		if (url === null || url === "") {
			message = message + "<br/> The url is empty ";
		} else {
			 
			if(url.indexOf("youtube") > -1){
				ytbid = url.replace("https://www.youtube.com/watch?v=", "");
				vimeoid="";
				vimeoidytbid=ytbid;
			}else{
				vimeoid = url.replace("https://vimeo.com/", "");
				ytbid="";
				vimeoidytbid=vimeoid;
			}
			
			
			
			
			 
		}

		var banner = $("#banner").val();
		if (ytbid !== "" && (banner === null || banner === "")) {
			banner = "http://img.youtube.com/vi/" + ytbid + "/hqdefault.jpg";
			$("#banner").val(banner);
			type="YOUTUBE";
			$("#vimeo-banner").attr('src',banner);
		}
		
		if (vimeoid !== "" && (banner === null || banner === "")) {
			
			
			vimeoLoadingThumb(vimeoid);
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
			
			$("#videoid").val(videoid);
		}
		if (videoid === null || videoid === "") {
			message = message + "<br/> The videoid is empty ";
		}
		var title = $("#title").val();
		if (title === null || title === "") {
			message = message + "<br/> The title is empty ";
		}
		
		var order = $("#order").val();
		if (order === null || order === "") {
			message = message + "<br/> The order is empty ";
		}
		var privacy = $("#privacy").val();

		

		if (message !== "") {
			$("#alert-message").html("");
			$("#alert-message").html(message);
		} else {
			$(btn).button("loading");
			var post = {
				"VIDEOID" : videoid,
				"TITLE" : title,
				"TYPE":type,
				"KEYWORD" : keyword,
				"URL" : vimeoidytbid,
				"applicationId" : "041507c2-6096-474b-a685-f13ff9455a44",
				"BANNER" : banner,
				"PRIVACY" : privacy,
				"ORDER" : order
			}
			
			BaasBox.save(post, "VIMEOS").done(function(res) {
				console.log("res ", res);
				$(btn).button("reset");
				location.href = "/videos/detail/"+VIDEOID;
			}).fail(function(error) {
				console.log("error ", error);
				BootstrapDialog.alert(error.responseText);
				$(btn).button("reset");
			})

		}

	});
});