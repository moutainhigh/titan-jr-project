<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<div class="home_top"></div>
	<div class="nav">
		<ul>
			<li <c:if test="${param.menu=='home'}">class="on"</c:if>><a href="javascript:void(0)">首页</a></li>
			<li <c:if test="${param.menu==''}">class="on"</c:if>><a href="javascript:void(0)" >收付款管理</a></li>
			<li <c:if test="${param.menu==''}">class="on"</c:if>><a href="javascript:void(0)">理财产品 </a></li>
			<li <c:if test="${param.menu==''}">class="on"</c:if>><a href="javascript:void(0)">信贷产品</a></li>
			<li <c:if test="${param.menu=='user'}">class="on"</c:if>>
				<a href="<%=basePath %>/admin/org.shtml">我的用户</a>
				<p>
					<a href="<%=basePath %>/admin/org.shtml">申请审核<i>0</i></a>
					<a href="javascript:void(0)">用户管理</a>
				</p>
			</li>
			<li <c:if test="${param.menu=='plat'}">class="on"</c:if>>
				<a href="javascript:void(0)">平台管理</a>
				<p>
					<a href="javascript:void(0)">平台运营员</a>
					<a href="javascript:void(0)">发布公告</a>
				</p>
			</li>
		</ul>
	</div>
	
	 