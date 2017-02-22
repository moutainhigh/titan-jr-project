<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <title>泰坦钱包</title>
	    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
		<jsp:include page="/comm/static-js.jsp"></jsp:include>
	</head>
	<body style="min-width: 1300px;" class="bg" >
		<div class="header">
			<div class="w_1200">
				<div class="logo">
					<div class="l_img"><img src="<%=cssWalletPath%>/images/logo.png"></div>
					<div class="l_text">
						<i class="ico"></i>绑定提现银行卡
					</div>
				</div>
			</div>
		</div>
		<div class="register r_three">
			<div class="r_box">	
			    <c:choose>
			      <c:when test="${msg ==null}">
				      <div class="r_t_text">
							<div class="rt_img"><i class="ico"></i></div>
							<div class="rt_c">
								<p>您的绑卡申请已经成功提交</p>
								我们将会在24小时内审核完成，请耐心等待。
							</div>
							<div class="rt_skip">
								3 秒后自动跳转至账户概览页面
							</div>
						</div>
						<div class="r_t_hint">				
							<a href="<%=basePath%>/account/overview-main.shtml">立即跳转 ></a>
						</div>	
			      </c:when>
			      <c:otherwise>
				      <div class="r_t_text">
							<div class="rt_img"><i class="ico"></i></div>
							<div class="rt_c">
								<p style="color: red">${msg}!</p>
							</div>
						</div>
			      </c:otherwise>
			    </c:choose>
			</div>
		</div>
		<div class="h_40"></div>
		<!-- 版权 -->
		<jsp:include page="/comm/foot.jsp"></jsp:include>
		<!-- 查看示例 -->
		<div class="dn" id="example">
		<div class="example">
			<img src="<%=cssWalletPath%>/images/tu01.jpg" alt="">
		</div>	
		</div>
		<script type="text/javascript">
			 //跳转
			function timeOut(_this){
			    var i=2;
			    var interval=setInterval(function () {                
			         if(i>0){
			             _this.html( i + " 秒后自动跳转至账户概览页面"); 
			             i--;
			         }else{
			            window.location.href="<%=basePath%>/account/overview-main.shtml";
			            clearInterval(interval);
			         }; 
			    }, 1000);
			};
			if('${msg}'==null){
				timeOut($('.rt_skip'));
			}
			
		</script>
	</body>
</html>
