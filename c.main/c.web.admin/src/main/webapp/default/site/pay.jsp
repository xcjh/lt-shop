<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class="ui-mobile">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="keywords" content="首页" />
<meta name="description" content="首页" />
<link type="text/css" rel="stylesheet" href="${ctx }/f/css/skin.css" />
<link type="text/css" rel="stylesheet" href="${ctx }/f/css/style.css" />
<link type="text/css" rel="stylesheet" href="${ctx }/f/css/fonts/iconfont.css" />
<script type="text/javascript" src="${ctx }/f/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx }/f/js/WapCircleImg.js"></script>
<script type="text/javascript" src="${ctx }/f/js/common.js"></script>
<script type="text/javascript" src="${ctx }/f/js/lt.inputnum.js"></script>
<script type="text/javascript" src="${ctx}/f/js/layer_mobile/layer.js"></script>
<style>
.receipt {
	border-bottom: 1px solid #D4D4D4;
	border-left: 20px solid #FFFFFF;
	border-right: 20px solid #FFFFFF;
	color: #6E6E6E;
}

.receipt  dl {
	
}

.receipt dl dt {
	height: 26px;
	line-height: 26px;
}

.receipt dl dd {
	width: 100%;
}

.receipt dl dd input {
	height: 26px;
	outline: none;
}
</style>
</head>
<body class="bg01">
	<form id="orderForm" name="orderForm" action="" method="">
		<div class="container">
			<div class="box_main">
				<div class="order_detailtips">
					<span class="fl"><i style="color: #CFAA51" class="icon iconfont"></i> 支付信息</span> <span class="fr"> <a href="/"><i style="color: #CFAA51"
							class="icon iconfont">&#xe699;</i> </a>
					</span>
				</div>
			</div>
			<div class="btmline"></div>
			<div class="order_footer">
				<a class="order_fee"> <span class="fl" style="color: #999; padding-left: 10px; padding-top: 3px; float: right;"> <span class="fr error"
						style="padding-right: 10px; padding-top: 10px;">合计：&yen; <fmt:formatNumber pattern="0.00" minFractionDigits='0' value="${map.amount }"></fmt:formatNumber>
					</span></a> <a class="order_footerbtn bg2 clearing_btn" href="javascript:void(0);">提交订单</a>
			</div>
		</div>
		</div>
	</form>
	<script type="text/javascript">
		$(function() {
			
		});
	</script>

</body>
</html>