<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/path-param.jsp"%>
<div class="header">
	<div class="w_1200">
		<div class="logo">
			<div class="l_img"><img src="<%=cssWalletPath%>/images/logo.png"></div>
			<!-- <div class="l_text">
				<i class="ico "></i>欢迎注册
			</div> -->
		</div>
		<div class="head_r">
			<ul>
				<li><a href="<%=basePath %>/main/main.shtml">首页</a></li>
				<li><a href="<%=basePath %>/main/solution.shtml" class="Aon">解决方案</a></li>
				<li class="w_240 li_1">
					<!-- 登录前 -->
					<div class="dn">
					<a href="注册.html" class="li_login">免费注册</a>
					<a class="li_btn1" href="<%=basePath %>/login.shtml">登录</a>
					</div>
					<!-- 登录后 -->
					<div class="hr_login">
						<div class="hrl_hover">
							<i class="ico"></i>
							我的账户
						</div>
						<div class="hrl_ul dn">
							<a href="<%=basePath %>/account/overview-main.shtml">资产概览</a>
							<a href="泰坦钱包设置.html">泰坦钱包设置</a>
							<a href="<%=basePath %>/login.shtml">退出</a>								
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
</div>