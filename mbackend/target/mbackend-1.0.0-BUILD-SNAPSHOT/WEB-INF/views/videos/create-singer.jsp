<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<html>
<head>
<jsp:include page="../common/header.jsp"></jsp:include>
<script src="<c:url value="/resources/js/videos/create_singer.js" />"></script>
<link href="<c:url value="/resources/css/dataTables.bootstrap.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="/resources/js/dataTables.bootstrap.js" />"></script>
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
				<li><a href="/application/my-application">Ứng dụng</a></li>
				<li class="active">Like Videos</li>
			</ol>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="alert alert-warning" role="alert">
					<div id="alert-message"></div>
				</div>

			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<input required="required" type="url" class="form-control"
						id="link" placeholder="Link youtube">
				</div>
				<div class="form-group">
					<input required="required" type="text" class="form-control"
						id="banner" placeholder="Banner">
				</div>

				<div class="form-group">
					<input required="required" type="text" class="form-control"
						id="keyword" placeholder="keyword">
				</div>



			</div>
			<div class="col-xs-6">
				<div>
					<div class="form-group">
						<input required="required" type="text" class="form-control"
							id="videoid" placeholder="Video ID">
					</div>
					<div class="form-group">
						<input required="required" type="text" class="form-control"
							id="title" placeholder="Title">
					</div>

				</div>
			</div>
			<div class="col-xs-6"></div>
			<div class="col-xs-6">
				<button type="button" class="btn btn-primary" id="btn-save">Save</button>
				<button type="button" class="btn btn-warning" id="btn-cancel">Cancel</button>

			</div>
		</div>

	</div>
	<!-- /container -->
</body>
<footer>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</footer>
</html>
