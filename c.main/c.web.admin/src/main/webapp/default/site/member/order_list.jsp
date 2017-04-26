<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class="ui-mobile">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单列表</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="keywords" content="订单列表" />
<meta name="description"
	content="订单列表" />
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
				<div class="pro_tit">
					<ul>
						<li class="cur"><a href="/m/order">订单列表</a></li>
					</ul>
				</div>

				<div class="orderlist" id="tab1">
					<c:forEach var="item" items="${webpage.datas }">
						<div class="order_box">
							<div class="order_txt">
								<span class="fl">订单号：${item.orderSn }(<html:os val="${item.orderStatus }" filed="order"></html:os>)</span>
								 <span class="fr">金额：<em>
										<html:currencyformat price="${item.amount }"></html:currencyformat>
										</em>
								</span>
							</div>
							<a href="/m/od-${item.id}">
							<dl>
								<dd>
									<div class="order_txt">
										<span class="fl">下单时间：
										<html:dateformat pattern="yyyy-MM-dd HH:mm" time="${item.addTime }"></html:dateformat>
										</span>
										<span class="fr">运费：<em>
										<html:currencyformat price="${item.freight }"></html:currencyformat>
										</em>
										</span>
									</div>
									<div class="ipro_time">
										${item.receiptName }-${item.receiptPhone }
									</div>
									<div class="ipro_adress">
										${item.province }-${item.city }-${item.district }
									</div>
									<div class="ipro_adress">
										${item.address }
									</div>
								</dd>
							</dl>
							</a>
						</div>
					</c:forEach>

				</div>

			</div>
			<div class="btmline"></div>
			<footer>
		<ul>
			<li ><a href="/"><span class="bg1"></span>
				<h3>首页</h3></a></li>
			<li><a href="/cart"><span class="bg2"></span>
				<h3>购物车</h3></a></li>
			<li class="cur"><a href="/m/index"><span class="bg3"></span>
				<h3>我的</h3></a></li>
		</ul>
		</footer>
		</div>
	</div>
<script type="text/javascript">
	$(document).ready(function() {
		var pageNo = ${webpage.pageNo};
		var pageCount = ${webpage.pageCount};
		var work=true;
		$(window).scroll(function(){  
			var srollPos = $(window).scrollTop(); 
			if((parseFloat($(document).height())-parseFloat(300)) <= (parseFloat($(window).height()) + parseFloat(srollPos))) {
				if(work){
					data.list();
					work=false;
				}
			}
		});
		var data = {
				list:function(){
					if(pageNo>=pageCount){
						return;
					}
					pageNo ++;
					$.get("ajaxorder/?p="+pageNo,function(data){
						$(".orderlist").append(data);
						work=true;
					});
					
				}
		};

	});
</script>
</body>
</html>