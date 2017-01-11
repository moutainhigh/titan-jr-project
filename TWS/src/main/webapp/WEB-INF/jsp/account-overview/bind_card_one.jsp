<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		
		<div class="register r_three rt_binding">
			<div class="r_box ">
				<div class="r_t_text">
					<div class="rt_img "><i class="ico"></i></div>
					<div class="rt_c">
						<p>为了您的账户安全，请先绑定提现银行卡</p>
						一经绑定，不可修改！
					</div>
				</div>
				<div class="r_c ">
					<div class="r_text">
						<ul>					
							<li class="lb_btn "><a href="<%=basePath%>/account/toBindCardStepTwo.shtml?modifyOrBind=${modifyOrBind}" class="">去绑定提现银行卡</a></li>
						</ul>
					</div>			
				</div>
			</div>
		</div>
		
		<div class="footer">
			Copyright © 2012-2016, fangcang.com. All Rights Reserved 泰坦云 版权所有 粤ICP备13046150号	
		</div>
		<div class="szcert">
			<script charset="utf-8" type="text/javascript" src="http://szcert.ebs.org.cn/govicon.js?id=78ccac39-a97a-452c-9f81-162cd840cff6&amp;width=130&amp;height=50&amp;type=2" id="ebsgovicon"></script> 
		</div>	
		<!-- 查看示例 -->
		<div class="dn" id="example">
		<div class="example">
			<img src="<%=cssWalletPath%>/images/tu01.jpg" alt="">
		</div>	
		</div>
	<script type="text/javascript">
	//验证
	new validform('.r_box');
	//查看示例
	$('.J_example').on('click',function(){  
	        var d = dialog({
	            title: ' ',
	            fixed: true,
	            padding: '0 0 0px 0',
	            content: $('#example'),
	            skin : 'wallet_pop'      
	        }).showModal()
	        return false;
	}); 
	
	</script>
	</body>
</html>
