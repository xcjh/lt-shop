<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class="ui-mobile">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>微信支付</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="Keywords" content="订单页" />
<meta name="Description" content="订单页" />
<link type="text/css" rel="stylesheet" href="/css/skin.css" />
<link type="text/css" rel="stylesheet" href="/css/style.css" />
<link type="text/css" rel="stylesheet" href="/css/fonts/iconfont.css" />
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<style type="text/css">
.loading {
	background: #ECF5FF url("../admin/images/esayui/loading.gif") no-repeat
		scroll 5px 50%;
	border: 1px solid #CFAA51;
	font-size: 12px;
	height: 27px;
	left: 60%;
	line-height: 25px;
	margin: -50px 0 0 -100px;
	padding-left: 25px;
	padding-right: 10px;
	position: absolute;
	text-align: left;
	top: 50%;
	z-index: 999;
}
</style>

</head>
<body>
	<div style="height: 100%; width: 100%; position: fixed; left: 0pt; top: 0pt; z-index: 8;">
		<a><div id="backBtn" style='position: absolute; left: 40%; top: 40%; z-index: 9992; cursor: pointer;' url="/m/od-${order.id }">点击返回</a>
	</div>
	</a>
	</div>
	<script type="text/javascript">
		$(function() {
			$("#backBtn").click(function() {
				$(".jqmOverlay").show();
				setTimeout(function() {
					location.href = $("#backBtn").attr("url");

				}, 5000);
			});
		});

		function onBridgeReady() {
			WeixinJSBridge.invoke('getBrandWCPayRequest', {
				"appId" : "${map.appid}", //公众号名称，由商户传入     
				"timeStamp" : "${map.timeStamp}",//时间戳，自1970年以来的秒数     
				"nonceStr" : "${map.nonceStr}", //随机串     
				"package" : "prepay_id=${map.prepay_id}",
				"signType" : "MD5", //微信签名方式：     
				"paySign" : "${map.mysign}" //微信签名 
			}, function(res) {
				if (res.err_msg == "get_brand_wcpay_request：ok") {
				} // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
			});
		}
		if (typeof WeixinJSBridge == "undefined") {
			if (document.addEventListener) {
				document.addEventListener('WeixinJSBridgeReady', onBridgeReady,
						false);
			} else if (document.attachEvent) {
				document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
				document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
			}
		} else {
			onBridgeReady();
		}
		onBridgeReady();
	</script>
	<div
		style="display: none; height: 100%; width: 100%; position: fixed; background: url(../images/float_topbg.png) repeat; left: 0pt; top: 0pt; z-index: 98; opacity: 0.4;"
		class="jqmOverlay">
		<div id="loading" class="loading" style='z-index: 300'>加载中...</div>
	</div>

</body>
</html>