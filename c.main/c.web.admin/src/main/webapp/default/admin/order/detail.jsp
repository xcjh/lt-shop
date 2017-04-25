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
<title>订单详情</title>
</head>
<body>
<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>订单管理<span
			class="c-gray en">&gt;</span>订单详情<a class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
<div class="page-container">
		<table class="table table-border table-bordered radius  table-striped">
		<tbody>
			<tr>
				<th class="text-r" width="120">订单编号：</th>
				<td>${detail.order.orderSn }</td>
				<th class="text-r"  width="120">订单状态：</th>
				<td><html:os val="${detail.order.orderStatus }" filed="order"></html:os></td>
			</tr>
			<tr>
				<th class="text-r" width="120">支付状态：</th>
				<td><html:os val="${detail.order.payStatus }" filed="pay"></html:os></td>
				<th class="text-r"  width="120">收货状态：</th>
				<td><html:os val="${detail.order.deliverStatus }" filed="deliver"></html:os></td>
			</tr>
			<tr>
				<th class="text-r" width="120">订单金额：</th>
				<td><html:currencyformat price="${detail.order.amount }"></html:currencyformat></td>
				<th class="text-r"  width="120">运费：</th>
				<td><html:currencyformat price="${detail.order.freight }"></html:currencyformat></td>
			</tr>
			<tr>
				<th class="text-r" width="120">收货人：</th>
				<td>${detail.order.receiptName }</td>
				<th class="text-r"  width="120">收货人电话：</th>
				<td>${detail.order.receiptPhone }</td>
			</tr>
			<tr>
				<th class="text-r" width="120">收货省份：</th>
				<td>${detail.order.province }</td>
				<th class="text-r"  width="120">收货城市：</th>
				<td>${detail.order.city }</td>
			</tr>
			<tr>
				<th class="text-r" width="120">收货地区：</th>
				<td>${detail.order.district }</td>
				<th class="text-r"  width="120">详细地址：</th>
				<td>${detail.order.address }</td>
			</tr>
			<tr>
				<th class="text-r"  width="120">退款金额：</th>
				<td><html:currencyformat price="${detail.order.amountRefund }"></html:currencyformat> </td>
				<th class="text-r" width="120">下单时间：</th>
				<td><html:dateformat time="${detail.order.addTime }"></html:dateformat> </td>
			</tr>
		</tbody>
	</table>
	<table class="table table-border table-bordered radius  table-striped">
		<tbody>
		<tr>
			<td width="150">缩略图</td>
			<th>商品名称</th>
			<th>购买单价</th>
			<th>购买数量</th>
		</tr>
		<c:forEach var="item" items="${detail.list }">
			<tr>
				<td><a href="/d/${item.goodsId }" target="_blank"><img src="/${item.defImg }" alt="${item.title }" class="radius" style="width:80px;height:80px;"></a> </td>
				<td><a href="/d/${item.goodsId }" target="_blank">${item.title }</a></td>
				<td><html:currencyformat price="${item.buyPrice }"></html:currencyformat> </td>
				<td>${item.num }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
</div>

<jsp:include page="../inc_footer.jsp"></jsp:include>
<script type="text/javascript">
</script>
</body>
</html>