moment.lang('vi-vn');
function IsJsonString(str) {
	
    try {
        JSON.parse(str);
    } catch (e) {
        return false;
    }
    return true;
}
function getFileIcon(type,id){
	var sIcon="";
	var iconPath = "/resources/img/AssetIcons/";
	var sContent = "content";
	var serverUrl=BaasBox.endPoint+"/file/";
	if (window.location.protocol == "https:"){
		serverUrl=serverUrl.replace("http:","https:");
	}
	switch (type){
	case "image/png":
	case "image/jpeg":
	case "image/gif":
	case "image/tiff":
	case "image/jpg":
		sIcon = BaasBox.endPoint+"/file/"+id+"?X-BB-SESSION="+BaasBox.getCurrentUser().token+"&X-BAASBOX-APPCODE="+BaasBox.appcode+"&resize=<=40px"
		sContent="image";
		break;
	case "application/zip":
		sIcon = iconPath + "zip.png";
		break;
	case "image/eps":
		sIcon = iconPath + "eps.png";
		break;
	case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
		sIcon = iconPath + "word.png";
		break;
	case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
		sIcon = iconPath + "excel.png";
		break;
	case "application/vnd.openxmlformats-officedocument.presentationml.presentation":
		sIcon = iconPath + "ppt.png";
		break;
	case "application/pdf":
		sIcon = iconPath+ "pdf.png";
		break;
	default :
		sIcon = iconPath + "file.png";
		break;
	}
	if (sIcon=="") return "";
	var ret = "<img style='width:40px; height:45px' title='"+type+"' alt='"+type+"' src='"+ sIcon +"'/><br />"
	ret += "<a href='"+(sContent=="content"?"view-source:":"")+serverUrl+(sContent=="content"?"content/":"") + id+"?X-BB-SESSION="+BaasBox.getCurrentUser().token+"&X-BAASBOX-APPCODE="+BaasBox.appcode+"' title='"+(sContent=="content"?"It only works with Chrome and Firefox":"")+"' target='_blank'><span title='xem file' class='fui-folder'></span></a> ";
	ret += "<a href='"+BaasBox.endPoint+"/file/details/"+id+"?X-BB-SESSION="+BaasBox.getCurrentUser().token+"&X-BAASBOX-APPCODE="+BaasBox.appcode+"' target='_blank' ><span title='chi tiết' class='fui-list-bulleted'></span></a>"
	return ret;
}
function bytesToSize(bytes, precision) {
	if (bytes){
		var sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];

		if (bytes == 0) return 'n/a';
		if (bytes < 1024) {
			return Number(bytes) + " " + sizes[0];
		}
		var posttxt = 0;
		do {
			posttxt++;
			bytes = bytes / 1024;
		} while( bytes >= 1024 );
		bytes=Math.round(bytes * 10) / 10
		return  bytes.toFixed(precision) + " " + sizes[posttxt];

	} else return "n/a";
}
var mbackend = {
	firstName : "mbackend",
	postFormSendJson : function(c) {
		return this.firstName;
	}
};

$(document).ready(function() {

	$(".form-json input[type=submit]").click(function(event) {
		event.preventDefault();
		formData = JSON.parse(JSON.stringify($(this).serializeArray()));

	});
	// config baasbox
	BaasBox.setEndPoint("http://baasbox.mbackend.com"); // the address of your BaasBox
												// server
	BaasBox.appcode = "6wNsswyF4zQLMI8YU8Ht"; // the application code of your server

	$('.dropdown-toggle').dropdown();

	if (BaasBox.getCurrentUser() != undefined) {

		// update user name
		$("#mbackend-user-login").text(BaasBox.getCurrentUser().username);
	}
	
	$("#mbackend-logout").click(function(){
		BaasBox.logout()
		  .done(function (res) {
		    console.log(res);
		    location.href="/user/logout";
		  })
		  .fail(function (error) {
		    console.log("error ", error);
		  })
	});


});