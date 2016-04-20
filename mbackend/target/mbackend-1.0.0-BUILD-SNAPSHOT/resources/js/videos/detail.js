var myPage = 0;
var jTable;
var jTableDataSource;
var collectionName = 'VIMEOS';
var ID = "";
function deleteDocument(docid, jRow) {

	BaasBox.deleteObject(docid, collectionName).done(function(res) {
		console.log("res ", res);
		jTable.row(jRow).remove().draw();
	}).fail(function(error) {
		console.log("error ", error);
	})

}
function bindingEventDeleteFile() {
	$(".mbackend-document-delete").click(
			function() {
				var docId = $(this).attr("docid");
				var TITLE = $(this).attr("TITLE");
				var jRow = $(this).parents('tr');
				BootstrapDialog.confirm("Bạn có chắc chắn muốn xóa video: "
						+ TITLE, function(confirm) {

					console.log(confirm);
					if (confirm === true) {
						deleteDocument(docId, jRow);
					}

				});
			});
}

function loadDetail() {

	BaasBox
			.loadCollectionWithParams(collectionName, {
				"orderBy" : "ORDER",
				where : "VIDEOID= ? ",
				params : ID,
				page : myPage,
				recordsPerPage : BaasBox.pagelength,
				orderBy : "_creation_date desc"
			})
			.done(
					function(res) {
						console.log("res ", res);
						jTableDataSource = res;
						jTable = $('#mbackend-list-collection')
								.DataTable(
										{
											"info" : false,
											"lengthChange" : false,
											"columnDefs" : [
													{
														"targets" : [ 0 ],
														"render" : function(
																data, type, row) {

															console.log(data);
															var html = '';
															if (row.TYPE === "YOUTUBE") {
																html = '<iframe width="200" height="113" src="https://www.youtube.com/embed/'
																		+ data
																		+ '" frameborder="0" allowfullscreen></iframe>';
																;
															} else {

																html = html
																		+ '<div class="row row-videos">';

																html = html
																		+ '<div class="col-lg-4 col-md-12 col-sm-12 col-xs-12">';
																html = html
																		+ ' <iframe src="https://player.vimeo.com/video/'
																		+ data
																		+ '?color=b9ab97&title=0&byline=0&portrait=0" width="200" height="133" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>';
																html = html
																		+ '</div>';

																html = html
																		+ '<div class="col-lg-8 col-md-12 col-sm-12 col-xs-12" style="padding: 0px!important;">';
																// html=html+row.TITLE;
																html = html
																		+ '</div>';
																html = html
																		+ '</div>';

															}
															return html;
														}
													},

													{
														"targets" : [ 2 ],
														"render" : function(
																data, type, row) {
															return ' <button docid="'
																	+ data
																	+ '"  TITLE="'
																	+ row.TITLE
																	+ '" class="btn btn-xs btn-danger fui-cross-circle mbackend-document-delete">&nbsp;Xóa</button>';
														}
													},
													{
														"targets" : [ 3 ],
														"render" : function(
																data, type, row) {
															return ' <a href="/videos/vimeo-detail/'
																	+ data
																	+ '"   class="btn btn-xs btn-primary fui-new">&nbsp;Chi tiết</a>';
														}
													}

											],
											data : jTableDataSource,
											"initComplete" : function() {
												bindingEventDeleteFile();
											},
											columns : [ {
												data : 'URL'
											}, {
												data : 'TITLE'
											}, {
												data : 'id'
											}, {
												data : 'id'
											} ]
										});
					}).fail(function(error) {
				console.log("error ", error);
			})
}

// Define some variables used to remember state.
var playlistId = "";
var orderPlaylist = 1;
var nextPageToken = null;
function getPlaylistItems() {

	var requestOptions = {
		playlistId : playlistId,
		part : 'snippet, id',
		maxResults : 50

	};

	if (nextPageToken && nextPageToken != null) {
		requestOptions.pageToken = nextPageToken;
	}

	console.log(requestOptions);

	var request = gapi.client.youtube.playlistItems.list(requestOptions);

	request.execute(function(response) {
		nextPageToken = response.result.nextPageToken;

		var playlistItems = response.result.items;
		if (playlistItems) {
			$.each(playlistItems, function(index, item) {
				displayResult(item.snippet, item.id);
			});

			if (nextPageToken && nextPageToken != null) {
				setTimeout(copyPlaylist, 10000) //wait ten seconds before continuing
				
			}else{
				BootstrapDialog.alert('Finished');
			}
		} else {
			BootstrapDialog.alert('Sorry you have no uploaded videos');
		}

		// Retrieve the next page of videos in the playlist.

		function displayResult(videoSnippet, videoId) {
			var title = videoSnippet.title;

			var thumbnail = videoSnippet.thumbnails.high.url;

//			$('#alert-message')
//					.append('<p><img src="' + thumbnail + '" /></p>');
			$('#alert-message').append(orderPlaylist+': <p>' + title + '</p>');
			// $('#alert-message').append('<p>' + videoId + '</p>');

			var post = {
				"VIDEOID" : $("#videoid").val(),
				"TITLE" : title,
				"TYPE" : "YOUTUBE",
				"KEYWORD" : title.toLocaleLowerCase(),
				"URL" : videoSnippet.resourceId.videoId,
				"applicationId" : "041507c2-6096-474b-a685-f13ff9455a44",
				"BANNER" : thumbnail,
				"PRIVACY" : "PUBLIC",
				"ORDER" : ++orderPlaylist
			}

			//console.log(post);
			BaasBox.save(post, "VIMEOS").done(function(res) {
				console.log("res ", res);

			}).fail(function(error) {
				console.log("error ", error);
				// BootstrapDialog.alert(error.responseText);

			})

		}

	});
}

function init() {
	gapi.client.setApiKey('AIzaSyBlOnR6rx1xvrtjD4nNRcPRwe8tp5SlFSo');

}

function copyPlaylist() {

	gapi.client.load('youtube', 'v3').then(getPlaylistItems);
}

$(document)
		.ready(
				function() {

					$("#btn-copy-playlist")
							.click(
									function() {

										$("#btn-copy-playlist").button(
												"loading");

										var playlistURL = $("#playlist-url")
												.val();

										if (playlistURL != null
												&& playlistURL != "") {
											playlistId = playlistURL
													.replace(
															"https://www.youtube.com/playlist?list=",
															"");

											if (playlistId === null
													|| playlistId === ""
													|| playlistId === playlistURL) {

												BootstrapDialog
														.alert("Playlist URL is invalid!");
												$("#playlist-url").val("");

											} else {
												copyPlaylist();
											}

										} else {

											BootstrapDialog
													.alert("Playlist URL is invalid!");
											$("#playlist-url").val("");
										}

									});

					BaasBox.loadObject("MOVIES", VIDEOID).done(
							function(res) {
								console.log("res ", res['data']);
								var video = res['data'];

								$("#videoshow").attr(
										"src",
										"https://www.youtube.com/embed/"
												+ video.YOUTUBE);

								$("#videoid").val(video.VIDEOID);
								ID = video.VIDEOID;
								$("#title").val(video.TITLE);
								$("#keyword").val(video.KEYWORD);
								$("#link").val(video.YOUTUBE);
								$("#intro").val(video.CONTENT);
								// "applicationId" :
								// "041507c2-6096-474b-a685-f13ff9455a44",
								$("#banner").val(video.BANNER);
								$("#category").val(video.CATEGORIES);
								$("#privacy").val(video.PRIVACY);

								loadDetail();

							}).fail(function(error) {
						console.log("error ", error);
					})

					$("#btn-save")
							.click(
									function() {
										var btn = this;

										var ytbid = "";
										var message = "";
										var link = $("#link").val();
										if (link === null || link === "") {
											message = message
													+ "<br/> The Link is empty ";
										} else {
											ytbid = link
													.replace(
															"https://www.youtube.com/watch?v=",
															"");
										}

										var banner = $("#banner").val();
										if (ytbid !== ""
												&& (banner === null || banner === "")) {
											banner = "http://img.youtube.com/vi/"
													+ ytbid + "/hqdefault.jpg";
											$("#banner").val(banner);
										}
										if (banner === null || banner === "") {
											message = message
													+ "<br/> The banner is empty ";
										}

										var keyword = $("#keyword").val();
										if (keyword === null || keyword === "") {
											message = message
													+ "<br/> The keyword is empty ";
										}

										var videoid = $("#videoid").val();
										if (ytbid !== ""
												&& (videoid === null || videoid === "")) {
											videoid = ytbid;
											$("#videoid").val(videoid);
										}
										if (videoid === null || videoid === "") {
											message = message
													+ "<br/> The videoid is empty ";
										}
										var title = $("#title").val();
										if (title === null || title === "") {
											message = message
													+ "<br/> The title is empty ";
										}
										var intro = $("#intro").val();
										if (intro === null || intro === "") {
											message = message
													+ "<br/> The intro is empty ";
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
											ID = videoid;
											post.id = VIDEOID;
											BaasBox
													.save(post, "MOVIES")
													.done(
															function(res) {
																console.log(
																		"res ",
																		res);
																$(btn)
																		.button(
																				"reset");
																$(
																		"#alert-message")
																		.html(
																				"Save success");

															})
													.fail(
															function(error) {
																console
																		.log(
																				"error ",
																				error);
															})

										}

									});

					$("#btn-cancel")
							.click(
									function() {

										BootstrapDialog
												.confirm(
														'Hi,are you sure cancel it?',
														function(result) {
															if (result) {
																location.href = "/videos/manager";
															}
														});
									});

					$("#mbackend-create-document").click(function() {

						location.href = "../../videos/" + VIDEOID + "/" + ID;

					})

				})
