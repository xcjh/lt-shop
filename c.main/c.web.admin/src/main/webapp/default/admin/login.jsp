<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <jsp:include page="inc_header.jsp"></jsp:include>
    <link href="${ctx}/s/css/H-ui.login.css" rel="stylesheet" type="text/css"/>
    <title>管理系统</title>
    <meta name="keywords" content="keywords">
    <meta name="description" content="description">
</head>
<body>
<div class="header"><span>管理系统</span></div>
<div class="loginWraper">
    <div id="loginform" class="loginBox">
        <form class="form form-horizontal"  id="form-login" action="" method="post">
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                <div class="formControls col-xs-8">
                    <input id="uname" name="uname"  value="${uname }" type="text" placeholder="请输入2-16位的帐号" class="input-text size-L">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-xs-8">
                    <input id="upwd" name="upwd" value="${upwd }" type="password" placeholder="请输入6-16位的密码" class="input-text size-L">
                </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <label for="online">
                        <input type="checkbox" name="online" id="online" <c:if test="${online==1 }">checked</c:if> value="1">
                      记录帐号密码</label>
                </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3" style="padding-left: 50px;">
                    <input name="" type="submit" class="btn btn-success radius size-L"
                           value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
                </div>
            </div>
        </form>
    </div>
</div>
<div class="footer">CopyRight © 2014 管理平台 All Rights Reserved 版权所有 北京后台管理有限公司</div>
<jsp:include page="inc_footer.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/s/js/jquery.validation/1.14.0/jquery.validate.min.js"></script> 
<script type="text/javascript" src="${ctx}/s/js/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="${ctx}/s/js/jquery.validation/1.14.0/messages_zh.min.js"></script>
<script type="text/javascript">
$(function(){	
	$("#form-login").validate({
		rules:{
			uname:{
				required:true,
				minlength:2,
				maxlength:16
			},
			upwd:{
				required:true,
				minlength:6,
				maxlength:16
			},
			
			
		},
		onkeyup:false,
		focusCleanup:false,
		success:"valid",
		submitHandler:function(form){
			var layerobj ;
			var para =$(form).serialize();//组织参数
		    var url ="loginp";
		    $.ajax({
		          type:"post",
		          cache:false,
		          dataType:"json",
		          data:para,
		          url:url,
		          beforeSend:function(XMLHttpRequest){
		             layerobj = layer.load()
		          },
		          complete:function(XMLHttpRequest, textStatus){
		        	  layer.close(layerobj);
		          },
		          success:function(data, textStatus){
		             if(data.result==1){
		            	 location.href="main";
		             }else{
		            	 layer.open({
		            		  type: 1,
		            		  area: ['200px', '120px'],
		            		  shadeClose: true, //点击遮罩关闭
		            		  content: '\<\div style="padding:20px;">'+data.message+'\<\/div>'
		            		  });
		             }
			      }
		       });
			
		}
	});
});
</script> 
</body>
</html>