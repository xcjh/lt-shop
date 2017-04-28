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
</head>
<body class="bg01">
	<div class="container">
		<div class="box_main">

			<div class="order_detailtips">
				<span class="fl"><i style="color: #CFAA51" class="icon iconfont">&#xe65c;</i> 您的购物车清单</span> <span class="fr"> <a href="/"><i
						style="color: #CFAA51" class="icon iconfont">&#xe699;</i> </a>
				</span>
			</div>
			<div class="combox">
				<div class="orderlist">
					<c:forEach var="item" items="${map.list }">
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
										单价： &yen;
										<fmt:formatNumber pattern="#.0" minFractionDigits='0' value="${item.price }"></fmt:formatNumber>
									</div>
									<div class="ipro_price numitem" num="${item.num }" gid="${item.goodsId }"
										price="<fmt:formatNumber pattern="#.0" minFractionDigits='0' value="${item.price }"></fmt:formatNumber>"></div>
								</dd>
							</dl>
						</div>
					</c:forEach>
				</div>


			</div>
		</div>
		<div class="btmline"></div>
		<c:if test="${map.count gt 0 }">
		<div class="order_footer">
			<a class="order_fee"> <span class="fl" style="color: #999; padding-left: 10px; padding-top: 10px;">运费：&yen; <fmt:formatNumber pattern="0.00"
						minFractionDigits='0' value="${map.freightTotal }"></fmt:formatNumber></span> <span class="fr error" style="padding-right: 10px; padding-top: 10px;">合计：&yen;
					<fmt:formatNumber pattern="0.00" minFractionDigits='0' value="${map.amount }"></fmt:formatNumber>
			</span>
			</a> <a class="order_footerbtn bg2 clearing_btn" href="javascript:void(0);">去结算</a>
		</div>
		</c:if>
	</div>
	</div>


	<script type="text/javascript">
		function change(objPanel) {
			//layer.open({type: 2});
			var gid = $(objPanel).attr("gid");
			var num = $(objPanel).find("input[name=num]").val();
			$.ajax({
				type : 'POST',
				url : '../cart/modify',
				data : 'gid=' + gid + '&num=' + num,
				success : function(d) {
					if (d.result == 1) {
						layer.closeAll();
						$(".order_fee .fl").html(
								"运费：&yen;" + d.data.freightTotal);
						$(".order_fee .fr").html("合计：&yen;" + d.data.amount);
						layer.open({
							content : '修改数量成功',
							skin : 'msg',
							time : 2
						//2秒后自动关闭
						});
					} else if (d.result == 2) {
						layer.open({
							content : d.message,
							skin : 'msg',
							time : 2
						//2秒后自动关闭
						});
					} else if (d.result == 3) {
						layer.open({
							content : '请登录后再进行操作',
							skin : 'msg',
							time : 1 //2秒后自动关闭
							,
							end : function() {
								location.href = "/login?furl=/d/" + gid;
							}
						});
					} else {
						layer.open({
							content : '修改数量失败',
							skin : 'msg',
							time : 2
						//2秒后自动关闭
						});
					}
				},
				error : function() {
				}
			});
		}
		$(function() {
			$(".numitem").each(function() {
				$(this).inputnum({
					defPrice : $(this).attr("price"),
					defNum : $(this).attr("num"),
					changeCallback : change
				});
			});
			//确定事件
			$(".clearing_btn").click(function() {
				if ($(".numitem").size() == 0) {
					layer.open({
						content : '您还没有添加商品',
						skin : 'msg',
						time : 2, //2秒后自动关闭
					});
					return;
				}
				layer.open({
					type : 2
				});
				layer.open({
					content : '加载中……',
					skin : 'msg',
					time : 1, //2秒后自动关闭
					end : function() {
						layer.closeAll();
						location.href = "../order/confirm";
					}
				});
			});
		});
	</script>
</body>
</html>