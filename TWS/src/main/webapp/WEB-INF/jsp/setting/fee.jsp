<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>个人账户资料-泰坦金融</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
  
  <body style="min-width: 1300px;" class="bg" >
<!-- 头部 -->
<jsp:include page="./../header.jsp"></jsp:include>
<div class="h_90"></div>
<!-- 内容 -->
<div class="w_1200 ">
	<div class="overview clearfix m_t20 o_set">	
		<jsp:include page="../slidemenu/jr-setting-menu.jsp">
			<jsp:param value="fee" name="menu"/>
		</jsp:include>
		<div class="s_right clearfix">
			<div class="sr_crumbs">我的账户  >  泰坦钱包设置  > 收付款费率公示</div>			
			<div class="sr_agreement clearfix">
				<table border="0" cellspacing="0" >
					<colgroup>
						<col width="130">
						<col width="130">
						<col width="250">
					</colgroup>
					<tr>
						<th>付款方式</th>
						<th>基础费率</th>
						<th>执行费率（至2017.03.31）</th>
					</tr>
					<c:forEach items="${rateInfoList}" var="rateInfo">
						<tr class="TFS_settd">
							<td>${rateInfo.description }</td>
							<td>${rateInfo.standrate }
								<c:if test="${rateInfo.ratetype==1}">%</c:if>
								<c:if test="${rateInfo.ratetype==2}">元/笔</c:if>
							</td>
							<td><span>
								<c:if test="${rateInfo.executionrate == 0.0}">免费</c:if>
								<c:if test="${rateInfo.executionrate != 0.0}">${rateInfo.executionrate}
									<c:if test="${rateInfo.ratetype==1}">%</c:if>
									<c:if test="${rateInfo.ratetype==2}">元/笔</c:if>
								</c:if>
							</span></td>
						</tr>
					</c:forEach>
				</table>
			</div>	
			<div class="h_90"></div>		
		</div>
	</div>
</div>
<div class="h_40"></div>
<!-- 版权 -->
<jsp:include page="/comm/foot.jsp"></jsp:include>
<script type="text/javascript">  
//内容高度
function scrollCon(){
	var winH=$(window).height();
	$('.s_right').css({'min-height':winH-235});
}
scrollCon();
$(window).on('resize.fixed',function(){
	scrollCon();
});

</script>

</body>
</html>

