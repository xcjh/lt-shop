<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class="ui-mobile">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户协议</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="keywords" content="用户协议" />
<meta name="description" content="用户协议" />
<link type="text/css" rel="stylesheet" href="${ctx }/f/css/skin.css" />
<link type="text/css" rel="stylesheet" href="${ctx }/f/css/style.css" />
<link type="text/css" rel="stylesheet"
	href="${ctx }/f/css/fonts/iconfont.css" />
<script type="text/javascript" src="${ctx }/f/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx }/f/js/WapCircleImg.js"></script>
<script type="text/javascript" src="${ctx }/f/js/common.js"></script>
</head>
<body class="bg01">
	<div class="container">
		<div class="box_main">
			<div class="xieyi">
				${config.xieyi}
			</div>
		</div>
		<div class="btmline"></div>
		<footer>
		<ul>
			<li><a href="/"><span class="bg1"></span>
					<h3>首页</h3></a></li>
			<li><a href="/cart"><span class="bg2"></span>
					<h3>购物车</h3></a></li>
			<li class="cur"><a href="/m/index"><span class="bg3"></span>
					<h3>我的</h3></a></li>
		</ul>
		</footer>
	</div>
	</div>

</body>
</html>