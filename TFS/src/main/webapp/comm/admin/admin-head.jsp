<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
 <jsp:include page="/comm/admin/admin-static-js.jsp"></jsp:include>
<div class="home_top"></div>
	<div class="nav">
		<ul>
			<li <c:if test="${param.menu=='home'}">class="on"</c:if>><a href="<%=basePath %>/admin/index.shtml">首页</a></li>
			<li <c:if test="${param.menu==''}">class="on"</c:if>><a href="javascript:void(0)" >收付款管理</a></li>
			<li <c:if test="${param.menu==''}">class="on"</c:if>><a href="javascript:void(0)">理财产品 </a></li>
			<li <c:if test="${param.menu=='loan'}">class="on"</c:if>><a href="javascript:void(0)">信贷产品</a></li>
			<li <c:if test="${param.menu=='user'}">class="on"</c:if>>
				<a href="<%=basePath %>/admin/org.shtml">我的用户</a>
				<p>
					<a href="<%=basePath %>/admin/org.shtml">申请审核<i id="head_count">0</i></a>
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
	<script type="text/javascript">
	//刷新账户开通申请待审核数
	$.ajax({
        dataType : 'json',
        type:'post',
        url : '<%=basePath%>/admin/data.shtml',
        success : function(result){
        	var enCount = parseInt(result.enCount);
        	var perCount = parseInt(result.perCount);
        	if($("#head_count")){
        		$("#head_count").html(enCount+perCount);
        	}
        },
        error:function(){
        	new top.Tip({msg : '请求失败，请重试', type: 3 , timer:1500});
        }
    });
	
	
	</script>
	 