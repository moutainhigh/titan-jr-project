<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<c:forEach items="${helpTypePage.itemList }" var="helpType">
	<li><a href="${helpType.helpType }">${helpType.name }</a></li>
</c:forEach>
<li style="display:none;"><input type="hidden" name="totalPage" class="totalPage" value="${orgCheckDTOPage.totalPage}"/>
			<input type="hidden" name="pageSize" class="pageSize" value="${orgCheckDTOPage.pageSize}"/>
			<input type="hidden" name="currentPage" class="currentPage" value="${orgCheckDTOPage.currentPage}"/>
			<input type="hidden" name="totalCount" class="totalCount" value="${orgCheckDTOPage.totalCount}"/></li>