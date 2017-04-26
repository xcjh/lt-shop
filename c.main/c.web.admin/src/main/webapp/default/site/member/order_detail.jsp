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
<style type="text/css">
.layui-m-layerbtn span {
    -moz-box-flex: 1;
    cursor: pointer;
    display: block;
    font-size: 14px;
    padding-top:15px;
}
</style>
</head>
<body class="bg01">
	<div class="container">
		<div class="box_main">

			<div class="order_detailtips">
				<span class="fl"><a href="/m/order"><i style="color: #CFAA51"
					class="icon iconfont">&#xe60e;</i></a> 订单编号：${detail.order.orderSn }</span> <span
					class="error fr"> <html:os
						val="${detail.order.orderStatus }" filed="order"></html:os>
				</span>
			</div>
			<div class="combox">
				<div class="orderlist">
					<c:forEach var="item" items="${detail.list }">
						<div class="order_box">
							<dl>
								<dt>
									<a href="/d/${item.goodsId }"><img src="/${item.defImg }"></a>
								</dt>
								<dd>
									<h3>
										<a href="/d/${item.goodsId }">${item.title }</a>
									</h3>
									<div class="ipro_price">
										购买价： &yen;
										<fmt:formatNumber pattern="#.0" minFractionDigits='0'
											value="${item.buyPrice }"></fmt:formatNumber>
									</div>
									<div class="ipro_adress">数量：${item.num}</div>
								</dd>
							</dl>
						</div>
					</c:forEach>

				</div>


			</div>

			<div class="order_detailbox">
				<h3>
					费用信息(
					<html:dateformat pattern="yyyy-MM-dd HH:mm"
						time="${detail.order.addTime }"></html:dateformat>
					)
				</h3>
				<div class="clear"></div>
				<div class="order_detailboxc">
					<p>
						商品总价：
						<html:currencyformat price="${detail.order.amount }"
							sub="${detail.order.freight }"></html:currencyformat>
					</p>
					<p>
						订单运费：
						<html:currencyformat price="${detail.order.freight }"></html:currencyformat>
					</p>
					<p>
						<span class="fl" style="color: #999">&nbsp;</span><span
							class="fr error">合计： <html:currencyformat
								price="${detail.order.amount }"></html:currencyformat>
						</span>
					</p>
				</div>

			</div>
			<div class="order_detailbox">
				<h3>收货信息</h3>
				<div class="clear"></div>
				<div class="order_detailboxc">
					<div class="ipro_time">${detail.order.receiptName }-${detail.order.receiptPhone }
					</div>
					<div class="ipro_adress">${detail.order.province }-${detail.order.city }-${detail.order.district }
					</div>
					<div class="ipro_adress">${detail.order.address }</div>
				</div>

			</div>
		</div>
		<div class="btmline"></div>
		<c:if test="${detail.order.orderStatus==1 }">
			<div class="order_footer">
				<a class="order_footerbtn bg1" href="/pay/wx-${detail.order.id }">去支付</a>
				<a class="order_footerbtn bg2" href="javascript:void(0);">取消订单</a>
			</div>
		</c:if>
	</div>
	</div>
	<script type="text/javascript">
	$(".bg2").click(function(){
	 layer.open({
		    content: '确定要取消订单吗？'
		    ,btn: ['确定', '取消']
		    ,yes: function(index){
		    	$.ajax({
					type : 'POST',
					url : '/m/odc-${detail.order.id }',
					success : function(d) {
						if (d.result == 1) {
							layer.open({
								content : '取消订单成功',
								skin : 'msg',
								time : 1 //2秒后自动关闭
								,
								end : function() {
									location.reload(true);
								}
							});
							
						} 
						else {
							layer.open({
								content : '取消订单失败',
								skin : 'msg',
								time : 2
							//2秒后自动关闭
							});
						}
					},
					error : function() {
						layer.open({
							content : '取消订单发送错误',
							skin : 'msg',
							time : 2
						//2秒后自动关闭
						});
					}
				});
		    }
		  });

 	});
	</script>

</body>
</html>