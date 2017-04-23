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
<link href="${ctx}/s/lib/lightbox2/2.8.1/css/lightbox.css" rel="stylesheet" type="text/css" >
<title>商品相册管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商品[${goods.title }]相册管理 <span class="c-gray en">&gt;</span> 图片展示 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">
	<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> 
	</span> 
	<span class="r">共有数据：<strong>${size}</strong> 条</span> </div>
	<div class="portfolio-content">
		<ul class="cl portfolio-area">
			<c:forEach var="item" items="${ablums }">
			<li class="item">
				<div class="portfoliobox">
					<input class="checkbox" name="imgs" type="checkbox" value="${item }">
					<div class="picbox"><a href="/${item }" data-lightbox="gallery" data-title=""><img src="/${item }"></a></div>
				</div>
			</li>
			</c:forEach>
		</ul>
	</div>
</div>
<jsp:include page="../inc_footer.jsp"></jsp:include>
<jsp:include page="../inc_validation.jsp"></jsp:include>
<jsp:include page="../inc_ueditor.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/s/lib/lightbox2/2.8.1/js/lightbox-plus-jquery.min.js"></script> 
<script type="text/javascript">
$(function(){
	$.Huihover(".portfolio-area li");
});
function datadel(){
	var ids = "";
	$('.portfoliobox input:checkbox[name=imgs]:checked').each(function(){
		ids += ","+$(this).val();
	});
	if(ids==""){
		layer.alert("请选择要删除的图片");
		return false;
	}
	layer.confirm("您确定要执行删除操作吗？",function(index){
		layer.close(index);
		$.ajax({
	         type:"post",
	         cache:false,
	         dataType:"json",
	         data:"imgs="+ids,
	         url:"dgi-${goods.id}",
	         success:function(data, textStatus){
	            if(data.result==1){
	            	layer.msg('批量删除成功',{icon:1,time:1000},function(){
	            		location.replace(location.href);
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
	});
}
</script>
</body>
</html>