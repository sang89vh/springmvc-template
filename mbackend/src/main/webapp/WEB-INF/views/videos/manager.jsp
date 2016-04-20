<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<html>
<head>
<jsp:include page="../common/header.jsp"></jsp:include>
<script src="<c:url value="/resources/js/videos/manager.js" />"></script>
<link href="<c:url value="/resources/css/dataTables.bootstrap.css" />" rel="stylesheet">
<script	src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>
<script	src="<c:url value="/resources/js/dataTables.bootstrap.js" />"></script>
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
		<div class="col-xs-12">
		<button id="mbackend-create-document" style="margin-bottom: 10px" class="btn  btn-sm btn-primary">Tạo mới</button>
		</div>
		<div class="col-xs-12">
		<table id="mbackend-list-collection" class="table table-striped table-bordered" cellspacing="0" width="100%">
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
</footer>
</html>
