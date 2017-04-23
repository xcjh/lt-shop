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
<title>商品管理</title>
</head>
<body>
<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>商品管理<span
		class="c-gray en">&gt;</span>商品列表<a class="btn btn-success radius r"
		style="line-height: 1.6em; margin-top: 3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="page-container">
	<div class="text-c">
	<form action="l" method="get">
		关键字:
		<input type="text" name="w" id="w" placeholder="请输商品名称" value="${params.w }" style="width:250px" class="input-text">
		<button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 查找</button>
		</form>
	</div>
	
	<div class="cl pd-5 bg-1 bk-gray mt-10">
		<span class="l">
		<a href="a"  class="btn btn-primary radius">
			<i class="Hui-iconfont">&#xe600;</i>添加商品
		</a>
		</span> 
		<span class="r">共有数据：<strong>${ pager.total}</strong> 条</span>
	</div>
	<table class="table table-border table-bordered table-bg table-hover table-sort">
		<thead>
			<tr class="text-c">
				<th width="20%">商品名称</th>
				<th width="10%">状态</th>
				<th width="8%">市场价</th>
				<th width="9%">销售价</th>
				<th width="14%">添加时间</th>
				<th >操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.datas }" var="item">
			
			<tr class="text-c">
				<td>${item.title }</td>
				<td>
					<c:if test="${item.status==0 }">新品</c:if>
					<c:if test="${item.status==1 }">上架</c:if>
					<c:if test="${item.status==2 }">下架</c:if>
					<c:if test="${item.status==3 }">售罄</c:if>
				</td>
				<td><html:currencyformat price="${item.priceMarket }"></html:currencyformat> </td>
				<td><html:currencyformat price="${item.price }"></html:currencyformat></td>
				<td>
					<html:dateformat time="${item.addTime }"></html:dateformat>
				</td>
				<td>
					<a title="编辑" href="e-${item.id}"  class="ml-5" style="text-decoration:none">
						<i class="Hui-iconfont">&#xe6df;</i>
					</a>
					<a title="相册管理" href="javascript:;" onClick="picture_edit('相册编辑','sa-${item.id}','10001')" class="ml-5" style="text-decoration:none">
						<i class="Hui-iconfont">&#xe613;</i>
					</a>
					<a title="上架"  href="javascript:;" gid="${item.id}" status="1"  class="ms ml-5"
					<c:if test="${item.status==1 }">style="display:none"</c:if>
					>
						<i class="Hui-iconfont">&#xe6dc;</i>
					</a>
					<a title="下架"  href="javascript:;" gid="${item.id}" status="2"    class="ms ml-5" 
					<c:if test="${item.status==2 }">style="display:none"</c:if>
					>
						<i class="Hui-iconfont">&#xe6de;</i>
					</a>
				</td>
			</tr>
			</c:forEach>
			
		</tbody>
	</table>
	${pager.showhtml}
</div>
<jsp:include page="../inc_footer.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		$(".ms").click(function(){
			var gid = $(this).attr("gid");
			var status = $(this).attr("status");
			var title = $(this).prop("title");
			var url ="m/"+gid+"/"+status;
			layer.confirm("您确定要执行["+title+"]操作吗？",function(index){
				$.ajax({
					type: 'POST',
					url: url,
					dataType: 'json',
					success: function(data){
						if(data.result==1){
							$(".ms[gid="+gid+"]").each(function(){
								if($(this).attr("status")==status){
									$(this).hide();
								}
								else{
									$(this).show();
								}
							});
							layer.msg(title+'成功!',{icon:1,time:1000});
						}else{
							layer.msg('操作失败!',{icon:2,time:1000});
						}
						
					},
					error:function(data) {
						console.log(data.msg);
					},
				});		
			});
		});
	});
	/*图片-编辑*/
	function picture_edit(title,url,id){
		var index = layer.open({
			type: 2,
			title: title,
			content: url
		});
		layer.full(index);
	}
</script>
</body>
</html>