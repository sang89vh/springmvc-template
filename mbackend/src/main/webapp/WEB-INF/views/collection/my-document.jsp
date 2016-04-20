<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript">
	var documentId = '${documentId}';
	var collectionId = '${collectionId}';
	var collectionName = '${collectionName}';
</script>

<link href="<c:url value="/resources/css/collection/my-document.css" />"
	rel="stylesheet">
<jsp:include page="../common/header.jsp"></jsp:include>

<script
	src="<c:url value="/resources/js/collection/my-document.js" />"></script>
<script src="<c:url value="/resources/js/jquery-json-editor.js" />"></script>
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<title>Quản lý bảng dữ liệu</title>
</head>
<body>
	<div id="mbackend-main-nav">
		<jsp:include page="../common/main-menu.jsp"></jsp:include>
	</div>
	<div class="container">
	<div class="mbackend-breadcrumb">
		<ol class="breadcrumb">
  			<li><a href="">MBackend</a></li>
  			<li><a href="/application/manager/${applicationId}">Bảng dữ liệu</a></li>
  			<li><a href="/collection/my-collection/${documentId}/${collectionId}/${collectionName}">Quản lý bảng dữ liệu</a></li>
  			<li class="active">Cập nhật dữ liệu</li>
		</ol>
	</div>
	<div class="col-xs-12">

		<ul class="nav nav-tabs">
			<li role="presentation" class="active" id="mbackend-edit-form"><a href="#">Form</a></li>
			<li role="presentation" id="mbackend-edit-json"><a href="#">JSon</a></li>
		</ul>
	</div>
	<div class="col-xs-12" id="mbackend-container-form">
	   <div id="mbackend-json-form"></div>
	   <div class="col-xs-12">
	   <div class="col-xs-2">
          <a href="/collection/my-collection/${documentId}/${collectionId}/${collectionName}" class="btn btn-block btn-sm btn-warning">Hủy bỏ</a>
        </div>
        <div class="col-xs-2">
          <a id="mbackend-btn-save" class="btn btn-block btn-sm btn-primary">Lưu</a>
        </div>
	</div>
	</div>
	<div class="col-xs-12 hidden" id="mbackend-container-json">
	   <textarea rows="10" cols="10" id="mbackend-textarea-json" ></textarea>
	   <div class="col-xs-12">
	   <div class="col-xs-2">
          <a href="/collection/my-collection/${documentId}/${collectionId}/${collectionName}" class="btn btn-block btn-sm btn-warning">Hủy bỏ</a>
        </div>
        <div class="col-xs-2">
          <a id="mbackend-btn-json-save" class="btn btn-block btn-sm btn-primary">Lưu</a>
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
