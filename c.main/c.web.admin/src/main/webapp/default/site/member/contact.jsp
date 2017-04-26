<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class="ui-mobile">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="keywords" content="首页" />
<meta name="description"
	content="首页" />
<link type="text/css" rel="stylesheet" href="${ctx }/f/css/skin.css" />
<link type="text/css" rel="stylesheet" href="${ctx }/f/css/style.css" />
<link type="text/css" rel="stylesheet" href="${ctx }/f/css/fonts/iconfont.css" />
<script type="text/javascript" src="${ctx }/f/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx }/f/js/WapCircleImg.js"></script>
<script type="text/javascript" src="${ctx }/f/js/common.js"></script>
</head>
<body class="bg01">
	 <div class="container">
            <div class="box_main">
                <div class="user_top bg2">
				    <dl>
					    <dt><img src="${user.imghead }"></dt>
					   <dd>${user.uname }</dd>
					</dl>
				</div>
				<div class="user_list contactlist">
				    <ul>
					   <li><i style="color:#00BB9C" class="icon iconfont">&#xe665;</i> 邮箱：${config.email}</li>
					   <li><i style="color:#10CD6E" class="icon iconfont">&#xe60a;</i> 电话：${config.phone}</li>
					   <li><i style="color:#56ABE4" class="icon iconfont">&#xe680;</i> 地址：${config.address}</li>
					   <li><i style="color:#EB4F38" class="icon iconfont">&#xe661;</i> 微博：${config.weibo}</li>
					   <li><i style="color:#CFAA51" class="icon iconfont">&#xe695;</i> 微信：${config.weixin}</li>
					</ul>
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