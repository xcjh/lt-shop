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
<script type="text/javascript" src="${ctx }/f/js/lt.inputnum.js"></script>
<script type="text/javascript" src="${ctx}/f/js/layer_mobile/layer.js"></script>
</head>
<body class="bg01">
	<div class="container">
		<div class="box_main">
			<div class="banner_box">
				<div id="Cimgf0d5c2216b8cbscroller_imglist" class="roll_img_mb_01">
					<div class="img_box" style="mix-height: 200px">
						<ul>
							<c:forEach var="item" items="${albumsArr }">
								<li><img src="/${item }" width="100%"/></li>
							</c:forEach>
						</ul>
					</div>
					<div class="nav_box" style="display: none">
						<ul id="li_on_name">
							<li class="li_on"></li>
							<li></li>
							<li></li>
						</ul>
					</div>
				</div>
				<script type="text/javascript">
                      WapCircleImg_01("Cimgf0d5c2216b8cbscroller_imglist", "${albumsArrSize }", true);
                    </script>
			</div>
			<div class="detai_info">
				<h3>${goods.title }</h3>
				<div class="detai_info2">
					<span class="fl"> 商城价：</span><b>&yen;<fmt:formatNumber pattern="#.0" minFractionDigits='0' value="${goods.price }"></fmt:formatNumber>/${goods.unit }
					</b>
					<span class="fr line_m">市场价：&yen;<fmt:formatNumber
								pattern="#.0" minFractionDigits='0'
								value="${goods.priceMarket }"></fmt:formatNumber></span>
				</div>
				<div class="detai_info2">
					<span class="fl"> 快递费：</span><b>&yen;<fmt:formatNumber
								pattern="#.0" minFractionDigits='0'
								value="${goods.freight }"></fmt:formatNumber>
					</b>
					<span class="fr">重量：${goods.weight }kg</span>
				</div>
			</div>
			<div class="detai_box">
				<div class="detai_boxtit">
					<h3>商品详情</h3>
				</div>
				<div class="detai_boxc">
					<div class="course_txt">
						${goods.note }
					</div>
				</div>
			</div>


		</div>
		<div class="btmline"></div>
		<div class="order_footer">
			<a class="order_footerbtn bg2" flag="2"  href="javascript:void(0)">立即购买</a> <a
				class="order_footerbtn bg1" flag="1" href="javascript:void(0)">加入购物车</a>

		</div>
	</div>
	</div>

	<div class="overflowy"></div>
	<div class="baoming_tc">
		<div class="baoming_tctit">
			<h3>请选择数量</h3>
			<i class="icon iconfont"></i>
		</div>
		<div class="baoming_tc_con" >
			 
		</div>
		<a class="baoming_btn">确 定</a>
	</div>
	</div>
<div class="float_div">
	<a href="/cart" class="error">
		<i  class="icon iconfont">&#xe65c;</i>
	</a>
</div>

<script type="text/javascript">
var islogin ="${islogin}";
$(function(){
	$(".baoming_tc_con").inputnum({defPrice:'<fmt:formatNumber pattern="#.0" minFractionDigits='0' value="${goods.price }"></fmt:formatNumber>'});
	//展开数量选择
	$(".order_footerbtn").mousedown(function(){
		$(".baoming_btn").attr("flag",$(this).attr("flag"));
		if(islogin==1){
			$(".baoming_tc").slideDown();
			$(".overflowy").show();
		}else{
			location.href="/login?furl=/d/${goods.id}"
		}	
	});
	//关闭数量选择
	$(".baoming_tc .baoming_tctit i").mousedown(function(){
		$(".baoming_tc").slideUp();
		$(".overflowy").hide();
	});
	//确定事件
	$(".baoming_btn").click(function(){
		layer.open({type: 2});
		var flag = $(this).attr("flag");
		$.ajax({
			type : 'POST',
			url : '../cart/save',
			data:'gid=${goods.id}&num='+$("input[name=num]").val(),
			success : function(d) {
				if (d.result == 1) {
					$(".baoming_tc").slideUp();
					$(".overflowy").hide();
					if(flag==1){
						layer.closeAll();
						layer.open({
						    content: '加入购物车成功'
						    ,skin: 'msg'
						    ,time: 2 //2秒后自动关闭
						  });
					}
					else{
						location.href="/cart";
					}
				} 
				else if (d.result == 2){
					layer.open({
					    content: '找不到要商品'
					    ,skin: 'msg'
					    ,time: 2 //2秒后自动关闭
					  });
				}else if (d.result == 3){
					layer.open({
					    content: '请登录后再进行操作'
					    ,skin: 'msg'
					    ,time: 1 //2秒后自动关闭
					    ,end:function(){
					    	location.href="/login?furl=/d/${goods.id}";
					    }
					  });
				}
				else {
					layer.open({
					    content: '加入购物车失败'
					    ,skin: 'msg'
					    ,time: 2 //2秒后自动关闭
					  });
				}
			},
			error : function() {
			}
		});
	});

});
            
				
</script>
</body>
</html>