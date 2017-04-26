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
					<span class="fl"><i style="color: #CFAA51" class="icon iconfont"></i> 确认订单</span> <span class="fr"> <a href="/"><i style="color: #CFAA51"
							class="icon iconfont">&#xe699;</i> </a>
					</span>
				</div>
				<div class="" style="background: #FFFFFF; margin-bottom: 10px;">
					<div style="height: 38px; line-height: 38px; border-top: 1px solid #D4D4D4; border-bottom: 1px solid #D4D4D4; padding-left: 20px; color: #6E6E6E;">
						<i style="color: #CFAA51" class="icon iconfont"></i>&nbsp;配送信息
					</div>
					<div class="receipt">
						<dl>
							<dt>收货人：</dt>
							<dd>
								<input type="text" id="receiptName" name="receiptName" value="${addr.receiptName}" style="padding: 0px 5px 0px 5px; border: 1px solid #B4B4B4;" />
							</dd>
							<dt>手机号：</dt>
							<dd>
								<input type="text" id="receiptPhone" name="receiptPhone" value="${addr.receiptPhone }" style="padding: 0px 5px 0px 5px; border: 1px solid #B4B4B4;" />
							</dd>
							<dt>收货地址：</dt>
							<dd>
								<div style="border: 1px solid #B4B4B4; margin-bottom: 8px;">
									<select id="provinceId" name="provinceId" style="width: 30%; height: 30px; border: 0px solid;">
										<option value="23">四川省</option>
									</select><select id="cityId" name="cityId" style="width: 30%; height: 30px; border: 0px solid;">
										<option value="235">成都市</option>
									</select><select id="districtId" name="districtId" style="width: 40%; height: 30px; border: 0px solid;">
										<option value="2040" <c:if test="${addr.districtId == \"2040\"}">selected</c:if>>锦江区</option>
										<option value="2041" <c:if test="${addr.districtId == \"2041\"}">selected</c:if>>青羊区</option>
										<option value="2042" <c:if test="${addr.districtId == \"2042\"}">selected</c:if>>金牛区</option>
										<option value="2043" <c:if test="${addr.districtId == \"2043\"}">selected</c:if>>武侯区</option>
										<option value="2044" <c:if test="${addr.districtId == \"2044\"}">selected</c:if>>成华区</option>
										<option value="2045" <c:if test="${addr.districtId == \"2045\"}">selected</c:if>>龙泉驿区</option>
										<option value="2046" <c:if test="${addr.districtId == \"2046\"}">selected</c:if>>青白江区</option>
										<option value="2047" <c:if test="${addr.districtId == \"2047\"}">selected</c:if>>新都区</option>
										<option value="2048" <c:if test="${addr.districtId == \"2048\"}">selected</c:if>>温江区</option>
										<option value="2049" <c:if test="${addr.districtId == \"2049\"}">selected</c:if>>金堂县</option>
										<option value="2050" <c:if test="${addr.districtId == \"2050\"}">selected</c:if>>双流县</option>
										<option value="2051" <c:if test="${addr.districtId == \"2051\"}">selected</c:if>>郫县</option>
										<option value="2052" <c:if test="${addr.districtId == \"2052\"}">selected</c:if>>大邑县</option>
										<option value="2053" <c:if test="${addr.districtId == \"2053\"}">selected</c:if>>蒲江县</option>
										<option value="2054" <c:if test="${addr.districtId == \"2054\"}">selected</c:if>>新津县</option>
										<option value="2055" <c:if test="${addr.districtId == \"2055\"}">selected</c:if>>都江堰市</option>
										<option value="2056" <c:if test="${addr.districtId == \"2056\"}">selected</c:if>>彭州市</option>
										<option value="2057" <c:if test="${addr.districtId == \"2057\"}">selected</c:if>>邛崃市</option>
										<option value="2058" <c:if test="${addr.districtId == \"2058\"}">selected</c:if>>崇州市</option>
									</select>
								</div>
								<div style="border: 1px solid #b4b4b4; padding-left: 5px; padding-right: 5px;">
									<input type="text" id="address" name="address" value="${addr.address }" style="width: 100%; border-width: 0px; height: 28px;" />
								</div>
							</dd>
						</dl>
						<div style="height: 38px; line-height: 38px; color: #DD5246;">注：试运行期间，送货范围仅限成都市范围，敬请见谅。</div>
					</div>
				</div>
				<div class="combox" style="margin-bottom: 10px;">
					<div class="orderlist">
						<div style="height: 38px; line-height: 38px; border-top: 1px solid #D4D4D4; border-bottom: 1px solid #D4D4D4; padding-left: 20px; color: #6E6E6E;">
							<i style="color: #CFAA51" class="icon iconfont"></i>&nbsp;商品列表
						</div>
						<c:forEach var="item" items="${map.list }">
							<div class="order_box">
								<dl>
									<dt>
										<img src="/${item.defImg }">
									</dt>
									<dd style="height: 58px;">
										<h3>${item.title }</h3>
									</dd>
									<dd>
										<div class="ipro_price">
											&yen;
											<fmt:formatNumber pattern="#.0" minFractionDigits='0' value="${item.price }"></fmt:formatNumber>
											<span style="color: #B4B4B4; font-size: 12px; float: right; line-height: 20px;">X${item.num }</span>
										</div>
									</dd>
								</dl>
							</div>
							<input type="hidden" id="good_${item.goodsId}" name="good" value="${item.cartId}_${item.goodsId}_${item.num}_${item.price}" />
						</c:forEach>
						<div style="height: 38px; line-height: 38px; border-bottom: 1px solid #D4D4D4; padding-right: 20px; color: #6E6E6E; text-align: right;">
							共${map.count }件商品(${map.otherWeights }kg)&nbsp;&nbsp;小计：<span style="color: #C94E4E;">&yen; <fmt:formatNumber pattern="0.00" minFractionDigits='0'
									value="${map.amountTotal }"></fmt:formatNumber></span> <input type="hidden" id="amountTotal" name="amountTotal" value="${map.amountTotal }" />
						</div>
					</div>
				</div>
				<div class="" style="background: #FFFFFF; margin-bottom: 10px;">
					<div style="height: 38px; line-height: 38px; border-top: 1px solid #D4D4D4; border-bottom: 1px solid #D4D4D4; padding-left: 20px; color: #6E6E6E;">
						<i style="color: #CFAA51" class="icon iconfont"></i>&nbsp;配送方式
						<div style="height: 38px; line-height: 38px; padding-right: 20px; float: right;">
							送货上门：<span style="color: #C94E4E;">&yen; <fmt:formatNumber pattern="0.00" minFractionDigits='0' value="${map.freightTotal }"></fmt:formatNumber></span> <input
								type="hidden" id="freightTotal" name="freightTotal" value="${map.freightTotal }" />
						</div>
					</div>
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
			//确定事件
			$(".clearing_btn").click(function() {
				layer.open({
					type : 2
				});
				var flag = $(this).attr("flag");
				$.ajax({
					type : 'POST',
					url : '../order/save',
					data : $("#orderForm").serialize(),
					success : function(d) {
						if (d.result == 1) {
							layer.open({
								content : '正在发起支付……',
								skin : 'msg',
								time : 2, //2秒后自动关闭
								end : function() {
									layer.closeAll();
									location.href = "../pay";
								}
							});
						} else {
							layer.closeAll();
							layer.open({
								content : '订单提交失败',
								skin : 'msg',
								time : 2
								//2秒后自动关闭
							});
						}
					},
					error : function() {
						layer.closeAll();
						layer.open({
							content : '订单提交失败',
							skin : 'msg',
							time : 2
						//2秒后自动关闭
						});
					}
				});
			});
		});
	</script>

</body>
</html>