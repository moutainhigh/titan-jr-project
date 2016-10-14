<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/comm/taglib.jsp"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>收银台-泰坦金融</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
</head>
  
  <body>
    	<div class="TFSsuccess_top">
		<div class="TFSsu_topc">
			<h1 class="TFSsu_toptitle"><img src="<%=cssSaasPath %>/images/TFS/tfslogo.png" alt="" width="364" height="27"></h1>
		</div>
	</div>
	<div class="TFSsu_content">
	      <div class="TFSsu_contenttop c_red"> 
			<i class="TFSpay_ico TFSsb"></i>
			<c:choose>
			  <c:when test="${not empty msg}">
			     ${msg}
			  </c:when>
			  <c:otherwise>
			      系统错误
			  </c:otherwise>
			</c:choose>
			 
		  </div>
		<div class="TFSsu_contentfoot">
			<span class="btn btn_exit J_exitKan">关闭</span>
		</div>
	</div>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
	<script>
	$('.J_exitKan').on('click',function(){
		window.close();
	});
		
	</script>
  </body>
</html>
