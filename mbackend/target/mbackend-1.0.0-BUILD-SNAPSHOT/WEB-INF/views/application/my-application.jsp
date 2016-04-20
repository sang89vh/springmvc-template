<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<html>
<head>
<jsp:include page="../common/header.jsp"></jsp:include>
<script src="<c:url value="/resources/js/application/my-application.js" />"></script>
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<title>Danh sách ứng dụng</title>
</head>
<body>
	<div id="mbackend-main-nav">
	<jsp:include page="../common/main-menu.jsp"></jsp:include>
    </div>
    <div class="container">
    <div class="row demo-tiles">
        
        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12 mbackend-application-col">
          <div class="tile tile-hot">
            <img src="../resources/img/icons/svg/ribbon.svg" alt="ribbon" class="tile-hot-ribbon">
            <img src="../resources/img/icons/svg/chat.svg" alt="Chat" class="tile-image">
            <h3 class="tile-title" id="mbackend-appname-1">Free for Share</h3>
            <p id="mbackend-app-create-1">Bắt đầu với ứng dụng đầu tiên ngay bây giờ</p>
            <button id="mbackend-app-btn-1"  class="btn btn-primary btn-large btn-block mbackend-manager-application" >Quản lý ứng dụng</button>
          </div>

        </div>
        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12 mbackend-application-col">
          <div class="tile">
            <img src="../resources/img/icons/svg/compas.svg" alt="Compas" class="tile-image big-illustration">
            <h3 class="tile-title" id="mbackend-appname-2">Ứng dụng thứ hai</h3>
            <p id="mbackend-app-create-2">100% Miễn phí</p>
            <button id="mbackend-app-btn-2"  class="btn btn-default btn-large btn-block mbackend-create-application" >Thêm ứng dụng</button>
          </div>
        </div>

        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12 mbackend-application-col">
          <div class="tile">
            <img src="../resources/img/icons/svg/loop.svg" alt="Infinity-Loop" class="tile-image">
            <h3 class="tile-title" id="mbackend-appname-3">Ứng dụng thứ ba</h3>
            <p id="mbackend-app-create-3">100% Miễn phí</p>
            <button id="mbackend-app-btn-3"  class="btn btn-default btn-large btn-block mbackend-create-application" >Thêm ứng dụng</button>
          </div>
        </div>

        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12 mbackend-application-col">
          <div class="tile">
            <img src="../resources/img/icons/svg/pencils.svg" alt="Pensils" class="tile-image">
            <h3 class="tile-title" id="mbackend-appname-4">Ứng dụng thứ tư</h3>
            <p id="mbackend-app-create-4">100% Miễn phí</p>
            <button id="mbackend-app-btn-4" class="btn btn-default btn-large btn-block mbackend-create-application" >Thêm ứng dụng</button>
          </div>
        </div>

        
      </div>
      <div class="row demo-tiles">
        
        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12 mbackend-application-col">
          <div class="tile tile-hot">
            <img src="../resources/img/icons/svg/map.svg" alt="Chat" class="tile-image">
             <h3 class="tile-title" id="mbackend-appname-5">Ứng dụng thứ năm</h3>
            <p id="mbackend-app-create-5">100% Miễn phí</p>
            <button id="mbackend-app-btn-5" class="btn btn-default btn-large btn-block mbackend-create-application" >Thêm ứng dụng</button>
          </div>

        </div>
        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12 mbackend-application-col">
          <div class="tile">
            <img src="../resources/img/icons/svg/gift-box.svg" alt="Compas" class="tile-image big-illustration">
            <h3 class="tile-title" id="mbackend-appname-6">Ứng dụng thứ sáu</h3>
            <p id="mbackend-app-create-6">100% Miễn phí</p>
            <button id="mbackend-app-btn-6" class="btn btn-default btn-large btn-block mbackend-create-application" >Thêm ứng dụng</button>
          </div>
        </div>

        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12 mbackend-application-col">
          <div class="tile">
            <img src="../resources/img/icons/svg/paper-bag.svg" alt="Infinity-Loop" class="tile-image">
            <h3 class="tile-title" id="mbackend-appname-7">Ứng dụng thứ bẩy</h3>
            <p id="mbackend-app-create-7">100% Miễn phí</p>
            <button id="mbackend-app-btn-7" class="btn btn-default btn-large btn-block mbackend-create-application" >Thêm ứng dụng</button>
          </div>
        </div>

        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12 mbackend-application-col">
          <div class="tile">
            <img src="../resources/img/icons/svg/mail.svg" alt="Pensils" class="tile-image">
            <h3 class="tile-title" id="mbackend-appname-8">Ứng dụng thứ tám</h3>
            <p id="mbackend-app-create-8">100% Miễn phí</p>
            <button id="mbackend-app-btn-8" class="btn btn-default btn-large btn-block mbackend-create-application" >Thêm ứng dụng</button>
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
