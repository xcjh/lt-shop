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
<title>订单管理</title>
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
				<th width="10%">订单编号</th>
				<th width="10%">订单金额</th>
				<th width="8%">运费</th>
				<th width="8%">订单状态</th>
				<th width="8%">支付状态</th>
				<th width="8%">发货状态</th>
				<th width="12%">省/市/区</th>
				<th width="8%">收货人</th>
				<th width="8%">收货人手机</th>
				<th width="14%">下单时间</th>
				<th >操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.datas }" var="item">
			
			<tr class="text-c">
				<td>
					<a href="d-${item.id }">${item.orderSn }</a> 
				</td>
				<td><html:currencyformat price="${item.amount }"></html:currencyformat> </td>
				<td><html:currencyformat price="${item.freight }"></html:currencyformat> </td>
				<td>
					<html:os val="${item.orderStatus }" filed="order"></html:os>
				</td>
				<td>
					<html:os val="${item.payStatus }" filed="pay"></html:os>
				</td>
				<td>
					<html:os val="${item.deliverStatus }" filed="deliver"></html:os>
				</td>
				<td>${item.province }/${item.city }/${item.district }</td>
				<td>${item.receiptName } </td>
				<td>${item.receiptPhone } </td>
				<td>
					<html:dateformat time="${item.addTime }"></html:dateformat>
				</td>
				<td>
					<a title="支付"  href="javascript:;" oid="${item.id }" status="2" cs="1" class="ms ml-5" 
					<c:if test="${item.orderStatus!=1 }">style="display:none"</c:if>
					>
						<i class="Hui-iconfont">&#xe63a;</i>
					</a>
					<a title="发货"  href="javascript:;" oid="${item.id }" status="3" cs="2" class="ms ml-5" 
					<c:if test="${item.orderStatus!=2 }">style="display:none"</c:if>
					>
						<i class="Hui-iconfont">&#xe634;</i>
					</a>
					<a title="收货"  href="javascript:;" oid="${item.id }" status="4" cs="3"  class="ms ml-5" 
					<c:if test="${item.orderStatus!=3 }">style="display:none"</c:if>
					>
						<i class="Hui-iconfont">&#xe669;</i>
					</a>
					<a title="取消"  href="javascript:;" oid="${item.id }" status="0"  class="ms ml-5" 
					<c:if test="${item.orderStatus gt 3 }">style="display:none"</c:if>
					>
						<i class="Hui-iconfont">&#xe6e2;</i>
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
		var oid = $(this).attr("oid");
		var status = $(this).attr("status");
		var title = $(this).prop("title");
		var url ="m/"+oid+"/"+status;
		var that = this;
		layer.confirm("您确定要执行["+title+"]操作吗？",function(index){
			$.ajax({
				type: 'POST',
				url: url,
				dataType: 'json',
				success: function(data){
					if(data.result==1){
						$(that).hide();
						$(that).nextAll("a[cs="+status+"]").show();
						if(status==0){
							$(".ms").hide();
							$(that).parent().parent().find("td").eq(1).html("已取消");
						}
						if(status==2){
							$(that).parent().parent().find("td").eq(1).html("已支付");
							$(that).parent().parent().find("td").eq(2).html("已付款");
						}
						if(status==3){
							$(that).parent().parent().find("td").eq(1).html("已发货");
							$(that).parent().parent().find("td").eq(3).html("已发货");
						}
						if(status==4){
							$(".ms").hide();
							$(that).parent().parent().find("td").eq(1).html("已收货");
						}
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