<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>帮组中心-泰坦金融</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
</head>
<body>
   <div class="help_search01">
	   	<input type="text" class="text h_28 w_542 help_inp01" id="search_w" placeholder="请输入问题关键字"> <a target="_blank" href="javascript:;" onclick="s()" class="settingBtn borderColor">搜索</a>	
		</div>
	<div class="help_content">
		<div class="help_content01">
			<h3 class="help_title">热点问题</h3>
			<ul class="clearfix">
				<li><a href="<%=basePath %>/help/detail.action" target="_blank">泰坦码是什么？</a></li>
				<li><a href="金服设置-帮助中心-详情.html" target="_blank">员工登录没有权限怎么办？</a></li>
				<li><a href="金服设置-帮助中心-详情.html" target="_blank">什么是基础费率？什么是执行费率？</a></li>
				<li><a href="金服设置-帮助中心-详情.html" target="_blank">SAAS员工账号和金融员工账号的区别？</a></li>
				<li><a href="金服设置-帮助中心-详情.html" target="_blank">注册时手机或者邮箱无法收到验证码怎么办？</a></li>
				<li><a href="金服设置-帮助中心-详情.html" target="_blank">忘记付款密码怎么办？</a></li>
				<li><a href="金服设置-帮助中心-详情.html" target="_blank">SAAS员工可以使用泰坦金融的功能吗?</a></li>
				<li><a href="金服设置-帮助中心-详情.html" target="_blank">账户申请审核不通过怎么办？</a></li>
				<li><a href="金服设置-帮助中心-详情.html" target="_blank">什么是小额免密支付开关？</a></li>
			</ul>
		</div>

		<div class="help_content02 clearfix">
			<div class="help_common fl">
				<h3 class="help_title">常见问题分类</h3>
				<ul class="clearfix">
					<li>
						<h3 class="help_ico01">泰坦金融概述</h3>
						<p><a href="金服设置-帮助中心-详情.html" target="_blank">泰坦金融是什么？</a></p>
						<p><a href="金服设置-帮助中心-详情.html" target="_blank">泰坦码是什么？</a></p>
						<p><a href="金服设置-帮助中心-详情.html" target="_blank">金融账户的结构是什么样的？</a></p>
						<h4><a href="金服设置-帮助中心-详情.html" target="_blank" class="blue">更多 >></a></h4>
					</li>
					<li>
						<h3 class="help_ico02">注册开通</h3>
						<p><a href="金服设置-帮助中心-详情.html" target="_blank">员工登录没有权限怎么办？</a></p>
						<p><a href="金服设置-帮助中心-详情.html" target="_blank">用户已存在怎么办？</a></p>
						<p><a href="金服设置-帮助中心-详情.html" target="_blank">账户申请审核不通过怎么办？</a></p>
						<h4><a href="金服设置-帮助中心-详情.html" target="_blank" class="blue">更多 >></a></h4>
					</li>
					<li>
						<h3 class="help_ico03">泰坦金融设置</h3>
						<p><a href="金服设置-帮助中心-详情.html" target="_blank">泰坦金融登录用户名是什么，可以用...</a></p>
						<p><a href="金服设置-帮助中心-详情.html" target="_blank">员工离职了，如何停用员工金融系统...</a></p>
						<p><a href="金服设置-帮助中心-详情.html" target="_blank">什么是用户冻结？</a></p>
						<h4><a href="金服设置-帮助中心-详情.html" target="_blank" class="blue">更多 >></a></h4>
					</li>
					<li>
						<h3 class="help_ico04">付款方式与收银台</h3>
						<p><a href="金服设置-帮助中心-详情.html" target="_blank">支付方式分为哪几种？</a></p>
						<p><a href="金服设置-帮助中心-详情.html" target="_blank">支付收银台分哪些，如何展示？</a></p>
						<h4><a href="金服设置-帮助中心-详情.html" target="_blank" class="blue">更多 >></a></h4>
					</li>
					<li>
						<h3 class="help_ico05">充值提现与绑卡</h3>
						<p><a href="金服设置-帮助中心-详情.html" target="_blank">如何对账户进行充值？</a></p>
						<p><a href="金服设置-帮助中心-详情.html" target="_blank">为什么需要绑卡，绑卡有什么要求？</a></p>
						<p><a href="金服设置-帮助中心-详情.html" target="_blank">提现时效，有什么要求？</a></p>
						<h4><a href="金服设置-帮助中心-详情.html" target="_blank" class="blue">更多 >></a></h4>
					</li>
					<li>
						<h3 class="help_ico06">部分名词解释</h3>
						<p><a href="金服设置-帮助中心-详情.html" target="_blank">我的资产</a></p>
						<p><a href="金服设置-帮助中心-详情.html" target="_blank">网关</a></p>
						<p><a href="金服设置-帮助中心-详情.html" target="_blank">回调</a></p>
						<h4><a href="金服设置-帮助中心-详情.html" target="_blank" class="blue">更多 >></a></h4>
					</li>
					<li>
						<h3 class="help_ico07">付款异常及处理方案</h3>
						<p><a href="金服设置-帮助中心-详情.html" target="_blank">网关支付不成功</a></p>
						<p><a href="金服设置-帮助中心-详情.html" target="_blank">网关支付成功，订单最终未付款成功</a></p>
						<p><a href="金服设置-帮助中心-详情.html" target="_blank">联盟供应商付款时，资金未冻结</a></p>
						<h4><a href="金服设置-帮助中心1.html" target="_blank" class="blue">更多 >></a></h4>
					</li>
				</ul>
			</div>
			<div class="help_Prefecture fr">
				<h3 class="help_title">信贷专区</h3>
				<h4>
					<a href="金服设置-帮助中心1.html" target="_blank" class="blue">更多 >></a>
				</h4>
				<ul>
					<li><a href="<%=basePath %>/help/detail.action" target="_blank">泰坦金服</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">金服首页</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">我的资产</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">我的理财</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">泰坦增值宝</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">我的贷款</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">泰坦金服设置</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">帮助中心</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">搜索</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">申请贷款需要满足什么条件？</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">贷款申请需要什么流程？</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">如何申请授信？</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">授信申请需要什么材料？多长时间批复？</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">每次申请贷款之前都要申请授信吗？</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">申请成功后是否可将贷款资金提现？</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">授信额度如何确定？</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">如何提高授信额度？</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">可用授信额度为什么会不断变化？</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">什么情况下可用授信额度显示为0？</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">每笔贷款的最高额度是多少？</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">最长贷款期限是？</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">还款日如何确定？</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">贷款到期后是否可以申请展期？</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">什么情况下贷款会提前到期？</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">贷款提前到期应该怎么办？</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">还款方式有哪些？</a></li>
					<li><a href="金服设置-帮助中心-详情.html" target="_blank">结算款自动扣款具体是如何扣款的？</a></li>
				</ul>
			</div>
		</div>
		
		<div class="help_content03">
			<h3>联系客服</h3>
			<div class="help_contact clearfix">
				<dl class="clearfix help_qq">
					<dt>在线客服</dt>
					<dd>
						<p><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=455605838&site=qq&menu=yes">12345678912</a></p>
						<p><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=455605838&site=qq&menu=yes">12345678912</a></p>
						<p><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=455605838&site=qq&menu=yes">12345678912</a></p>
					</dd>
				</dl>
				<dl class="clearfix help_tel">
					<dt>联系电话</dt>
					<dd>
						<p>0755-12345678</p>
						<p>138 8888 8888</p>
						<p>138 8888 8888</p>
					</dd>
				</dl>
			</div>
		</div>
	</div>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
  </body>
  <script type="text/javascript">
  function s(){
	  var w = $("#search_w").val();
	  var url = "<%=basePath %>/help/search.action?w="+w;
	  window.open(url);
  }
  	
  </script>
</html>
