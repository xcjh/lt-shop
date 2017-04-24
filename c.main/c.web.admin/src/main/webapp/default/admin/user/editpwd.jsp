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
<title>密码修改</title>
</head>
<body>

<div class="page-container">
		<form action="" method="post" id="postform" class="form form-horizontal"
			id="form-user-add">
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2"> <span
					class="c-red">*</span> 登录帐号：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					${user.uname }
					<input type="hidden" name="id" value="${user.id }" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2"> <span
					class="c-red">*</span> 登录密码：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="password" class="input-text" value="" placeholder=""
						id="upwd" name="upwd">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2"> <span
					class="c-red">*</span> 确认密码：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="password" class="input-text" value="" placeholder=""
						id="upwd2" name="upwd2">
				</div>
			</div>
			
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<input class="btn btn-primary radius" type="submit"
						value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				</div>
			</div>
		</form>
</div>
<jsp:include page="../inc_footer.jsp"></jsp:include>
<jsp:include page="../inc_validation.jsp"></jsp:include>
	<script type="text/javascript">
		//表单验证
		$(function(){	
			$("#postform").validate({
				rules:{
					upwd:{
						required:true
					},
					upwd2:{
						required:true,
						equalTo:"#upwd"
					}
					
					
				},
				messages:{
					upwd2:{
						equalTo: "两次密码不一致"
					}
				},
				onkeyup:false,
				focusCleanup:false,
				success:"valid",
				submitHandler:function(form){
					var layerobj ;
					var para =$(form).serialize();//组织参数
				    var url ="editpwds";
				    $.ajax({
				          type:"post",
				          cache:false,
				          dataType:"json",
				          data:para,
				          url:url,
				          beforeSend:function(XMLHttpRequest){
				             layerobj = layer.load();
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
				            		  content: '\<\div style="padding:20px;">修改成功\<\/div>',
				            		  cancel:function(){
				            			  layer.closeAll();
				            			  layer.load();
				            			  parent.location.href='../logout';
				            			  
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