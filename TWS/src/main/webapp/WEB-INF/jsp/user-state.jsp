<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>用户状态-泰坦金融</title>
     <link rel="stylesheet" href="<%=cssWalletPath%>/css/fangcang.min.css?v=20161222">
	<link rel="stylesheet" href="<%=cssWalletPath%>/css/style.css">
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
<body style="min-width: 1300px;" class="bg" >
<jsp:include page="./org-reg/org-head.jsp"></jsp:include>

<div class="register r_three">
	<!-- 待审核 -->
	<c:if test="${code=='ORG_FT' }">
		<div class="r_box">				
			<div class="r_t_text">
				<div class="rt_img audit"><i class="ico"></i></div>
				<div class="rt_c h_70">
					<p>申请审核中</p>			
				</div>				
				<div class="rt_step">
					<ul>
						<li class="li1">提交申请</li>
						<li class="li2">初审中...</li>
						<li>复审</li>
						<li class="last">审核通过</li>
					</ul>
				</div>
					
			</div>
			<div class="r_t_hint">				
				<a href="<%=basePath%>/main/main.shtml">返回首页 ></a>
			</div>	
		</div>
	</c:if>
	<!-- 初审不通过 -->
	<c:if test="${code=='ORG_FT_INVALID' }">
		<div class="r_box">				
			<div class="r_t_text">
				<div class="rt_img auditOn"><i class="ico"></i></div>
				<div class="rt_c h_70">
					<p>初审不通过</p>			
				</div>				
				<div class="rt_step">
					<ul>
						<li class="li1">提交申请</li>
						<li class="li3">初审不通过</li>
						<li>复审</li>
						<li class="last">审核通过</li>
					</ul>
				</div>
				<div class="rt_on">
					不通过原因：${msg}
				</div>	
			</div>
			<div class="r_t_hint">				
				<a href="<%=basePath%>/main/main.shtml">返回首页 ></a>
			</div>	
		</div>
	</c:if>
	<!-- 复审中 -->
	<c:if test="${code=='ORG_REVIEW' }">
		<div class="r_box">				
			<div class="r_t_text">
				<div class="rt_img audit"><i class="ico"></i></div>
				<div class="rt_c h_70">
					<p>申请复审中</p>			
				</div>				
				<div class="rt_step">
					<ul>
						<li class="li1">提交申请</li>
						<li class="li1">初审通过</li>
						<li class="li2">复审中...</li>
						<li class="last">审核通过</li>
					</ul>
				</div>
			</div>
			<div class="r_t_hint">				
				<a href="<%=basePath%>/main/main.shtml">返回首页 ></a>
			</div>	
		</div>
	</c:if>
	<!-- 复审不通过 -->
	<c:if test="${code=='ORG_REVIEW_INVALID' }">
		<div class="r_box">				
			<div class="r_t_text">
				<div class="rt_img auditOn"><i class="ico"></i></div>
				<div class="rt_c h_70">
					<p>复审不通过</p>			
				</div>				
				<div class="rt_step">
					<ul>
						<li class="li1">提交申请</li>
						<li class="li1">初审通过</li>
						<li class="li3">复审不通过</li>
						<li class="last">审核通过</li>
					</ul>
				</div>
				<div class="rt_on">
					不通过原因：${msg}
				</div>
			</div>
			<div class="r_t_hint">				
				<a href="<%=basePath%>/main/main.shtml">返回首页 ></a>
			</div>	
		</div>
	</c:if>
	
	<!-- 机构冻结 -->
	
	<!-- 机构被注销 -->
	
	
	<!-- 用户冻结 -->
	
	
	<!-- 用户被注销 -->
	
	
</div>

<jsp:include page="/comm/foot.jsp"></jsp:include>
  </body>
</html>
