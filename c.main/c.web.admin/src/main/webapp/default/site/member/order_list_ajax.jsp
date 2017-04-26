<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:forEach var="item" items="${webpage.datas }">
	<div class="order_box">
		<div class="order_txt">
			<span class="fl">订单号：${item.orderSn }(<html:os val="${item.orderStatus }" filed="order"></html:os>)</span>
			 <span class="fr">金额：<em>
					<html:currencyformat price="${item.amount }"></html:currencyformat>
					</em>
			</span>
		</div>
		<a href="/m/od-${item.id}">
		<dl>
			<dd>
				<div class="order_txt">
					<span class="fl">下单时间：
					<html:dateformat pattern="yyyy-MM-dd HH:mm" time="${item.addTime }"></html:dateformat>
					</span>
					<span class="fr">运费：<em>
					<html:currencyformat price="${item.freight }"></html:currencyformat>
					</em>
					</span>
				</div>
				<div class="ipro_time">
					${item.receiptName }-${item.receiptPhone }
				</div>
				<div class="ipro_adress">
					${item.province }-${item.city }-${item.district }
				</div>
				<div class="ipro_adress">
					${item.address }
				</div>
			</dd>
		</dl>
		</a>
	</div>
</c:forEach>
