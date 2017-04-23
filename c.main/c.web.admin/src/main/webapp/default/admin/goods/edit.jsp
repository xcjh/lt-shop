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
<link href="${ctx }/s/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
<title>商品添加</title>
</head>
<body>
<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>商品管理<span
			class="c-gray en">&gt;</span>商品编辑<a class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
<div class="page-container">
	<form action="" method="post" class="form form-horizontal" id="postform">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>产品标题：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${goods.title }" placeholder="" id="title" name="title"/>
				<input type="hidden" name="id" value="${goods.id }" />
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>商品状态：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="radio" name="status" value="0" <c:if test="${goods.status==0 }">checked="checked"</c:if> />新品
				<input type="radio" name="status" value="1" <c:if test="${goods.status==1 }">checked="checked"</c:if>  />上架
				<input type="radio" name="status" value="1" <c:if test="${goods.status==2 }">checked="checked"</c:if>  />下架
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">计量单位：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${goods.unit }" placeholder="" id="unit" name="unit"/>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">产品重量：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" name="weight" id="weight" placeholder="" value="${goods.weight }" class="input-text" style="width:90%">
				kg</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">运费：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" name="freight" id="freight" placeholder="" value="${goods.freight }" class="input-text" style="width:90%">
				元</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">市场价格：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" name="priceMarket" id="priceMarket" placeholder="" value="${goods.priceMarket }" class="input-text" style="width:90%">
				元</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">成本价格：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" name="priceCost" id="priceCost" placeholder="" value="${goods.priceCost }" class="input-text" style="width:90%">
				元</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">销售价格：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" name="price" id="price" placeholder="" value="${goods.price }" class="input-text" style="width:90%">
				元</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">图片上传：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<div id="imgurls"></div>
				<div class="uploader-list-container">
					<div class="queueList">
						<div id="dndArea" class="placeholder">
							<div id="filePicker-2"></div>
							<p>或将照片拖到这里，单次最多可选300张</p>
						</div>
					</div>
					<div class="statusBar" style="display:none;">
						<div class="progress"> <span class="text">0%</span> <span class="percentage"></span> </div>
						<div class="info"></div>
						<div class="btns">
							<div id="filePicker2"></div>
							<div class="uploadBtn">开始上传</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">详细内容：</label>
			<div class="formControls col-xs-8 col-sm-9"> 
				<script id="note" name="note"  type="text/plain" style="width:100%;height:400px;">${goods.note}</script> 
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
<script type="text/javascript" src="${ctx}/s/lib/webuploader/0.1.5/webuploader.min.js"></script>
<script type="text/javascript" src="${ctx}/s/js/lt.upload.js?t="+new Date().getTime()></script> 
<script type="text/javascript">
$(function(){	
	//编辑器
	var ue = UE.getEditor('note');
	//表单验证
	$("#postform").validate({
		rules:{
			title:{
				required:true
			},
			unit:{
				required:true
			},
			weight:{
				required:true,
				isFloatGteZero:true
			},
			freight:{
				required:true,
				isFloatGteZero:true
			},
			priceMarket:{
				required:true,
				isFloatGteZero:true
			},
			priceCost:{
				required:true,
				isFloatGteZero:true
			},
			price:{
				required:true,
				isFloatGteZero:true
			},
			note:{
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
		            			  location.href='l';
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