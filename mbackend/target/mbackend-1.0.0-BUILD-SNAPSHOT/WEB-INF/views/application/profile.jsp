<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<html>
<head>
<jsp:include page="../common/header.jsp"></jsp:include>
<script type="text/javascript">
	var applicationId = "${applicationId}";
</script>
<script src="<c:url value="/resources/js/application/profile.js" />"></script>
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<title>Quản lý thông tin ứng dụng</title>

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
					<a href="#" class="list-group-item disabled"> Menu </a> <a
						href="/application/manager/${applicationId}"
						class="list-group-item">Bảng dữ liệu</a> <a
						href="/application/file/${applicationId}"
						class="list-group-item ">File</a> <a
						href="/application/profile/${applicationId}"
						class="list-group-item active">Ứng dụng</a>
				</div>
			</div>
			<div class="col-xs-9">
				<table class="table">
					<caption><h5>Thông tin ứng dụng.</h5></caption>

					<tbody>
						<tr>
							<td><b>Tên ứng dụng</b></td>
							<td><span id="mbackend-application-name"></span></td>
							<td><span class="fui-new" id="mbackend-edit-application"></span></td>
						</tr>
						<tr>
							<td><b>Application Id</b></td>
							<td>${applicationId}</td>
							<td></td>
						</tr>
						<tr>
							<td><b>Thông tin cấu hình BaasBox</b></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td><b>EndPoint</b></td>
							<td>http://baasbox.mbackend.com/</td>
							<td></td>
						</tr>
						<tr>
							<td><b>Appcode</b></td>
							<td>6wNsswyF4zQLMI8YU8Ht</td>
							<td></td>
						</tr>
						
					</tbody>
				</table>
			</div>
		</div>

	</div>
	<!-- /container -->



</body>
<footer>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</footer>
</html>
