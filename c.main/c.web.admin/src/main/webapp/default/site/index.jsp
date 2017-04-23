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

			<div class="combox">
				<div class="iprolist">
					<c:forEach var="item" items="${webpage.datas }">
						<a href="d/${item.id }">
							<dl>
								<dt>
									<img src="${item.def_img }">
								</dt>
								<dd>
									<h3>${item.title }</h3>
									<div class="ipro_price">
										商城价：
										&yen;<fmt:formatNumber pattern="#.0" minFractionDigits='0'
											value="${item.price }"></fmt:formatNumber>
									</div>
									<div class="ipro_price line_m">
										市场价：
										&yen;<fmt:formatNumber pattern="#.0" minFractionDigits='0'
											value="${item.price_market }"></fmt:formatNumber>
									</div>
									<div class="ipro_price">
										快递费：
										&yen;<fmt:formatNumber pattern="#.0" minFractionDigits='0'
											value="${item.freight }"></fmt:formatNumber>
									</div>
								</dd>
							</dl>
						</a>
					</c:forEach>

				</div>


			</div>





		</div>
		<div class="btmline"></div>
		<footer>
		<ul>
			<li class="cur"><a href="/"><span class="bg1"></span>
				<h3>首页</h3></a></li>
			<li><a href="/cart"><span class="bg2"></span>
				<h3>购物车</h3></a></li>
			<li><a href="/m/index"><span class="bg3"></span>
				<h3>我的</h3></a></li>
		</ul>
		</footer>
	</div>
	</div>

</body>
</html>