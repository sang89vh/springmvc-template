<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<html>
<head>
<jsp:include page="../common/header.jsp"></jsp:include>
<link href="<c:url value="/resources/css/user/signup.css" />"
	rel="stylesheet">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<title>Tạo mới tài khoản miễn phí</title>
</head>
<body>
	<div class="container">
		<form id="form-signup" class="form-signup" method="post" action="../user/signup">
			<h2 class="form-signup-heading text-center">TẠO TÀI KHOẢN
				MIỄN PHÍ</h2>
			<label for="inputEmail" class="sr-only">Địa chỉ email</label> 
			<input name="email"
				type="email" id="inputEmail" class="form-control"
				placeholder="Địa chỉ email" required autofocus> 
				
			<label
				for="inputPassword" class="sr-only">Mật khẩu</label> 
				
			<input name="password"
				type="password" id="inputPassword" class="form-control"
				placeholder="Mật khẩu" required>	
				
			<label
				for="inputApplication" class="sr-only">Tên ứng dụng đầu tiên</label>
			<input name="application"
				type="text" id="inputApplication" class="form-control"
				placeholder="Tên ứng dụng đầu tiên" required autofocus> 
				
			

			<input class="btn btn-lg btn-primary btn-block" type="submit" value="Tạo tài khoản"/>
				
			<button class="btn btn-lg btn-warning btn-block" type="submit">Đã có tài khoản</button>
		</form>

	</div>
	<!-- /container -->



</body>
</html>
