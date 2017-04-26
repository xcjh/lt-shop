<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:forEach var="item" items="${webpage.datas }">
	<a href="d/${item.id }">
		<dl>
			<dt>
				<img src="${item.def_img }">
			</dt>
			<dd>
				<h3>${item.title }</h3>
				<div class="ipro_price">
					商城价：
					&yen;<fmt:formatNumber pattern="#.0" minFractionDigits='0'
						value="${item.price }"></fmt:formatNumber>
				</div>
				<div class="ipro_price line_m">
					市场价：
					&yen;<fmt:formatNumber pattern="#.0" minFractionDigits='0'
						value="${item.price_market }"></fmt:formatNumber>
				</div>
				<div class="ipro_price">
					快递费：
					&yen;<fmt:formatNumber pattern="#.0" minFractionDigits='0'
						value="${item.freight }"></fmt:formatNumber>
				</div>
			</dd>
		</dl>
	</a>
</c:forEach>
