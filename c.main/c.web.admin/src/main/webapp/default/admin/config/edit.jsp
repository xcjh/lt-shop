<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<jsp:include page="../inc_header.jsp"></jsp:include>
<title>编辑编辑</title>
</head>
<body>
<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>配置管理<span
			class="c-gray en">&gt;</span>配置编辑<a class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
<div class="page-container">
	<form action="" method="post" class="form form-horizontal" id="postform">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>邮箱：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${config.email }" placeholder="" id="email" name="email"/>
				<input type="hidden" name="id" value="${config.id }" />
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">电话：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${config.phone }" placeholder="" id="phone" name="phone"/>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">地址：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${config.address }" placeholder="" id="address" name="address"/>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">微博：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${config.weibo }" placeholder="" id="weibo" name="weibo"/>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">微信：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${config.weixin }" placeholder="" id="weixin" name="weixin"/>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">详细内容：</label>
			<div class="formControls col-xs-8 col-sm-9"> 
				<script id="xieyi" name="xieyi"  type="text/plain" style="width:100%;height:400px;">${config.xieyi}</script> 
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button  class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 提交</button>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">
	 ctx =' ${ctx}',uid=${uid};
</script>
<jsp:include page="../inc_footer.jsp"></jsp:include>
<jsp:include page="../inc_validation.jsp"></jsp:include>
<jsp:include page="../inc_ueditor.jsp"></jsp:include>
<script type="text/javascript">
$(function(){	
	//编辑器
	var ue = UE.getEditor('xieyi');
	//表单验证
	$("#postform").validate({
		rules:{
			email:{
				required:true
			},
			phone:{
				required:true
			},
			address:{
				required:true
			},
			weibo:{
				required:true
			},
			weixin:{
				required:true
			},
			xieyi:{
				required:true
			}
			
			
		},
		onkeyup:false,
		focusCleanup:false,
		success:"valid",
		submitHandler:function(form){
			var layerobj ;
			var para =$(form).serialize();//组织参数
		    var url ="m";
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
		            	 layer.open({
		            		  type: 1,
		            		  area: ['200px', '120px'],
		            		  shadeClose: true, //点击遮罩关闭
		            		  content: '\<\div style="padding:20px;">编辑成功\<\/div>',
		            		  cancel:function(){
		            			  location.replace(location.href);
		            		  }
		            		  });
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