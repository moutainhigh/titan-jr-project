<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<div class="s_left">
	<div class="sl_tit">泰坦钱包设置</div>
	<div class="sl_c">
			<a href="<%=basePath %>/setting/user-info.shtml" <c:if test="${param.menu=='info'}">class="lion"</c:if>>个人账户资料<i class="ico"></i><em></em></a>
			<a href="<%=basePath %>/setting/org-info.shtml" <c:if test="${param.menu=='org'}">class="lion"</c:if>>机构信息<i class="ico"></i><em></em></a>
			<a href="<%=basePath %>/setting/employee.shtml" <c:if test="${param.menu=='employee'}">class="lion"</c:if>>员工权限信息<i class="ico"></i><em></em></a>
			<a href="<%=basePath %>/setting/fee.shtml" <c:if test="${param.menu=='fee'}">class="lion"</c:if>>收付款费率公示<i class="ico"></i><em></em></a>
			<a href="<%=basePath %>/ex/protocol.shtml" <c:if test="${param.menu=='protocol'}">class="lion"</c:if>>泰坦钱包协议<i class="ico"></i><em></em></a>			
	</div>
</div>

