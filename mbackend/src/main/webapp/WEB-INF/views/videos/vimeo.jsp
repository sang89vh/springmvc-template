<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript">
	var VIDEOID = "${VIDEOID}";
	var ID = "${ID}";
</script>
<jsp:include page="../common/header.jsp"></jsp:include>
<script src="<c:url value="/resources/js/videos/vimeo.js" />"></script>
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
				<div>
					<div class="form-group">
						<input type="text" class="form-control" id="videoid" value="${ID}"
							placeholder="Video ID">
					</div>
					<div class="form-group">
						<input type="url" class="form-control" id="url" placeholder="Link">
					</div>

					<div class="form-group">
						<input type="text" class="form-control" id="title"
							placeholder="Title">
					</div>
					
				</div>
				<div class="form-group">
						<img id="vimeo-banner" src="" alt="This is the example" />
					</div>
			</div>



			<div class="col-xs-6">
				<div class="form-group">
					<input type="text" class="form-control" id="banner"
						placeholder="Banner">
				</div>


			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<input type="text" class="form-control" id="keyword"
						placeholder="Keyword">
				</div>
				<div class="form-group">
					<div class="dropdown">
						<select id="privacy">
							<option value="PUBLIC">Public</option>
							<option value="UNLISTED">Unlisted</option>
							<option value="PRIVATE">Private</option>
						</select>

					</div>
				</div>
				<div class="form-group">
					<input type="number" min="1" value="1" class="form-control"
						id="order" placeholder="Order">
				</div>
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
