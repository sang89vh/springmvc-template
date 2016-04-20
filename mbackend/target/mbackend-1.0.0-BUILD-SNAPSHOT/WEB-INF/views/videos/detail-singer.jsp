<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<html>
<head>
<jsp:include page="../common/header.jsp"></jsp:include>

<script type="text/javascript">
	var SINGERID = "${SINGERID}";
</script>
<script src="<c:url value="/resources/js/videos/detail_singer.js" />"></script>
<link href="<c:url value="/resources/css/dataTables.bootstrap.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="/resources/js/dataTables.bootstrap.js" />"></script>
<script src="auth.js"></script>
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<title>Mobile backend as services</title>

</head>
<body>
	<div id="mbackend-main-nav">
		<jsp:include page="../common/main-menu.jsp"></jsp:include>
	</div>
	<div class="container">
		<div class="mbackend-breadcrumb">
			<ol class="breadcrumb">
				<li><a href="">MBackend</a></li>
				<li><a href="/videos/manager-singer">Videos</a></li>
				<li class="active">Detail</li>
			</ol>
		</div>
		<div class="row">
			<div class="row">
				<div class="col-xs-12">
					<div class="form-group">
						<input id="playlist-url" type="url" class="form-control" placeholder="Youtube playlist URL"
							aria-label="Youtube channel">
						<button id="btn-copy-playlist" type="button" style="margin-top: 10px;margin-bottom: 10px"
								class="btn  btn-sm btn-primary">Copy PlayList</button>
						
					</div>
					
				</div>
				<div class="col-xs-12">
					<div class="alert alert-warning" role="alert">
						<div id="alert-message"></div>
					</div>

				</div>
				<div class="col-xs-6 ">
					<!-- 16:9 aspect ratio -->
					<div class="embed-responsive embed-responsive-16by9">
						<iframe id="videoshow" class="embed-responsive-item"></iframe>
					</div>
					<div class="form-group">
						<textarea required="required" class="form-control" rows="3"
							id="intro" placeholder="Intro"></textarea>
					</div>


				</div>
				<div class="col-xs-6 ">
					<div class="form-group  form-group-sm">
						<label>Video ID</label> <input required="required" type="text"
							class="form-control" id="videoid" placeholder="Video ID">
					</div>
					<div class="form-group  form-group-sm">
						<label>Title</label> <input required="required" type="text"
							class="form-control" id="title" placeholder="Title">
					</div>
					<div class="form-group  form-group-sm">
						<label>Youtube</label><input required="required" type="url"
							class="form-control" id="link" placeholder="Link youtube">
					</div>
					<div class="form-group  form-group-sm">
						<label>Banner</label><input required="required" type="text"
							class="form-control" id="banner" placeholder="Banner">
					</div>

					<div class="form-group  form-group-sm">
						<label>keyword</label><input required="required" type="text"
							class="form-control" id="keyword" placeholder="keyword">
					</div>

				</div>
			</div>

			<div class="row">
				<div class="col-xs-6"></div>
				<div class="col-xs-6">
					<div>


						<button type="button" class="btn btn-primary" id="btn-save">Save</button>
						<button type="button" class="btn btn-warning" id="btn-cancel">Cancel</button>
					</div>
				</div>

			</div>
			<hr />
			<div class="row">

				<div class="col-xs-12">
					<div class="col-xs-12">
						<button id="mbackend-create-document" style="margin-bottom: 10px"
							class="btn  btn-sm btn-primary">Tạo mới</button>
					</div>
					<div class="col-xs-12">
						<table id="mbackend-list-collection"
							class="table table-striped table-bordered" cellspacing="0"
							width="100%">
							<thead>
								<tr>

									<th>Link</th>
									<th>Title</th>
									<th>Delete</th>
									<th>Detail</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>




		</div>
		<!-- /container -->
</body>
<footer>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	 <script src="https://apis.google.com/js/client.js?onload=init"></script>
</footer>
</html>
