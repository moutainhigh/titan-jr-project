<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>

<c:if test="${empty creditOrder}">
	<jsp:include page="./credit-status/credit-new.jsp"></jsp:include>
</c:if>

<c:if test="${creditOrder.status == 1}">
	<jsp:include page="./credit-status/credit-new.jsp"></jsp:include>
</c:if>

<c:if test="${creditOrder.status == 2 or creditOrder.status == 3 or creditOrder.status == 10 or creditOrder.status == 11}">
	<jsp:include page="./credit-status/credit-audit.jsp"></jsp:include>
</c:if>

<c:if test="${creditOrder.status == 4}">
	<jsp:include page="./credit-status/credit-update.jsp"></jsp:include>
</c:if> 

<c:if test="${creditOrder.status == 5}">
	<jsp:include page="./credit-status/credit_success.jsp"></jsp:include>
</c:if> 
