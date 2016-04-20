<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<html>
<head>
<jsp:include page="../common/header.jsp"></jsp:include>
<script type="text/javascript">
var applicationId="${applicationId}";

</script>
<link href="<c:url value="/resources/css/dataTables.bootstrap.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/application/manager.js" />"></script>
<script	src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>
<script	src="<c:url value="/resources/js/dataTables.bootstrap.js" />"></script>
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<title>Quản lý ứng dụng</title>

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
  			<li class="active">Quản lý ứng dụng</li>
		</ol>
	</div>
	<div class="row">
	<div class="col-xs-3">
	<div class="list-group">
  	  <a href="#" class="list-group-item disabled">
	    Menu
	  </a>
	  <a href="#" class="list-group-item active">Bảng dữ liệu</a>
	  <a href="/application/file/${applicationId}" class="list-group-item">File</a>
	  <a href="/application/profile/${applicationId}"
						class="list-group-item">Ứng dụng</a>
	</div>
	</div>
	<div class="col-xs-9">
		<div class="col-xs-12">
		<button id="mbackend-create-collection" style="margin-bottom: 10px" class="btn  btn-sm btn-primary">Tạo mới</button>
		</div>
		<div class="col-xs-12">
		<table id="mbackend-list-application" class="table table-striped table-bordered" cellspacing="0" width="100%">
	        <thead>
	            <tr>
	                <th>@ID</th>
	                <th>DocId</th>
	                <th>Bảng dữ liệu</th>
	                <th>Tổng số bản ghi</th>
	                <th>Xóa</th>
	                <th>Chi tiết</th>
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
