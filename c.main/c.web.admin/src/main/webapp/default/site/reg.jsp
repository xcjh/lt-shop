<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class="ui-mobile">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="keywords" content="注册" />
<meta name="description"
	content="注册" />
<link type="text/css" rel="stylesheet" href="${ctx }/f/css/skin.css" />
<link type="text/css" rel="stylesheet" href="${ctx }/f/css/style.css" />
<link type="text/css" rel="stylesheet" href="${ctx }/f/css/fonts/iconfont.css" />
<script type="text/javascript" src="${ctx }/f/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx }/f/js/WapCircleImg.js"></script>
<script type="text/javascript" src="${ctx }/f/js/common.js"></script>
</head>
<body class="loginbg">
<div class="container">
	<div class="login">
		<div class="login_top">为保证您的账户安全，请您使用手机号注册。</div>
		<div class="login_con">
			<ul>
				<li class="borline"><i><img width="16" src="${ctx }/f/images/login_icon1.png"></i><input id="mobile" name="mobile"	type="text" placeholder="请输入您的手机号" /></li>
				<li class="borline"><i><img width="16" src="${ctx }/f/images/login_icon2.png"></i><input id="upwd" name="upwd"	type="password" placeholder="请设置您的登录密码" /></li>
				<li><a class="login_btn" >注册</a></li>
				<li id="errorpanel" class="" style="display:none;color:red;">手机号不正确</li>
			</ul>
		</div>
		<div class="login_footer" style="display: none">
			登录即表示您已同意<a href="">《用户协议》</a>所有内容
		</div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	//注册
	$(".login_btn").click(function(){
		var mobile = $("#mobile").val();
		var upwd = $("#upwd").val();
		var pattern = /^1[34578]\d{9}$/;  
		if (!pattern.test(mobile)){
			$("#errorpanel").html("手机号不正确").show();
			return false;
		}
		if(upwd==null || upwd=="" || upwd.length<6 || upwd.length>16){
			$("#errorpanel").html("请输入6-16位的密码").show();
			return false;
		}
		$("#errorpanel").hide();
    	$.ajax({
			type : 'POST',
			url : 'reg/save',
			data:'uname='+mobile+'&upwd='+upwd,
			success : function(d) {
				if (d.result == 1) {
					if(d.message!=null&&d.message!=""){
						location.href=d.message;
					}else{
						location.href="/";
					}
				} 
				else if (d.result == 2){
					$("#errorpanel").html("该手机号已被占用").show();
				}
				else {
					$("#errorpanel").html("注册失败").show();
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