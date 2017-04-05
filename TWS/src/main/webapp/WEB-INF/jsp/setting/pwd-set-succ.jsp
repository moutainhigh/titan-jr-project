<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
    <title>密码修改成功-泰坦钱包</title>
   <jsp:include page="/comm/tws-static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
<body style="min-width: 1300px;" class="bg" >
<!-- 登录密码  -->
<c:if test="${pageType==1 }">
	<jsp:include page="/comm/head-title.jsp">
	<jsp:param value="修改登录密码" name="title"/>
	</jsp:include>
</c:if>
<!-- 付款密码  -->
<c:if test="${pageType==2 }">
	<jsp:include page="/comm/head-title.jsp">
	<jsp:param value="修改付款密码" name="title"/>
	</jsp:include>
</c:if>

<div class="register r_three">
	<div class="r_box">				
			<div class="r_t_text">
				<div class="rt_img"><i class="ico"></i></div>
				<div class="rt_c h_50">
					<c:if test="${pageType==1 }"><p>您的登录密码已经修改成功</p></c:if>
					<c:if test="${pageType==2 }"><p>您的付款密码已经修改成功</p></c:if>
				</div>
				<div class="rt_skip">
					3 秒后将自动跳转
				</div>
			</div>
			<div class="r_t_hint">				
				<a href="<%=basePath%>/setting/user-info.shtml">立即跳转 ></a>
			</div>	
	</div>
</div>
<jsp:include page="/comm/foot.jsp"></jsp:include>
<script type="text/javascript">
 //跳转
function timeOut(_this){
    var i=2;
    var interval=setInterval(function () {                
         if(i>0){
             _this.html( i + " 秒后自动跳转"); 
             i--;
         }else{
            window.location.href="<%=basePath%>/setting/user-info.shtml";
            clearInterval(interval);
         }; 
    }, 1000);
};
timeOut($('.rt_skip'));


</script>
</body>
</html>