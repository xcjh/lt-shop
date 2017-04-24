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
	<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>订单管理<span
		class="c-gray en">&gt;</span>订单列表<a class="btn btn-success radius r"
		style="line-height: 1.6em; margin-top: 3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="page-container">
	<div class="text-c">
	<form action="l" method="get">
		关键字:
		<input type="text" name="w" id="w" placeholder="请输收货人姓名、订单号" value="${params.w }" style="width:250px" class="input-text">
		<button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 查找</button>
		</form>
	</div>
	
	<div class="cl pd-5 bg-1 bk-gray mt-10">
		<span class="l">
		
		</span> 
		<span class="r">共有数据：<strong>${ pager.total}</strong> 条</span>
	</div>
	<table class="table table-border table-bordered table-bg table-hover table-sort">
		<thead>
			<tr class="text-c">
				<th width="20%">订单编号</th>
				<th width="10%">订单状态</th>
				<th width="10%">支付状态</th>
				<th width="10%">发货状态</th>
				<th width="8%">收货人</th>
				<th width="8%">收货人手机</th>
				<th width="14%">下单时间</th>
				<th >操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.datas }" var="item">
			
			<tr class="text-c">
				<td>${item.orderSn }</td>
				<td>
					<html:os val="${item.orderStatus }" filed="order"></html:os>
				</td>
				<td>
					<html:os val="${item.payStatus }" filed="pay"></html:os>
				</td>
				<td>
					<html:os val="${item.deliverStatus }" filed="deliver"></html:os>
				</td>
				<td>${item.receiptName } </td>
				<td>${item.receiptPhone } </td>
				<td>
					<html:dateformat time="${item.addTime }"></html:dateformat>
				</td>
				<td>
					
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
		var uid = $(this).attr("uid");
		var status = $(this).attr("status");
		var flag = "正常";
		if(status==0)flag="禁用";
		var title = $(this).prop("title");
		var url ="m/"+uid+"/"+status;
		var that = this;
		layer.confirm("您确定要执行["+title+"]操作吗？",function(index){
			$.ajax({
				type: 'POST',
				url: url,
				dataType: 'json',
				success: function(data){
					if(data.result==1){
						$(".ms[uid="+uid+"]").each(function(){
							if($(this).attr("status")==status){
								$(this).hide();
							}
							else{
								$(this).show();
							}
						});
						$(that).parent().parent().find(".mspanel").html(flag);
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
</script>
</body>
</html>