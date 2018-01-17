<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>泰坦金融-支付路由</title>
    <jsp:include page="/common/static-resource.jsp"></jsp:include>
	<jsp:include page="/common/tfs-static-resource.jsp"></jsp:include>
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
			 ${errorMsg }
		  </div>
		
		<div class="TFSsu_contentfoot">
			<span class="btn btn_exit J_exitKan">关闭</span>
		</div>
	</div>
	
	<jsp:include page="/common/static-js.jsp"></jsp:include>
	<script>
		$('.J_exitKan').on('click',function(){
			window.close();
		});
	</script>
  </body>
</html>
