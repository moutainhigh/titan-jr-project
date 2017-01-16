<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>金融首页</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
    <link rel="stylesheet" href="<%=cssSaasPath%>/css/set_ad.css">
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
<body class="bg_f5" style="min-width: 1349px;" >
<jsp:include page="home-include.jsp"></jsp:include>
<!-- 内容 -->
<div class="con_1">
	<div class="w_1200" >
		<ul>
			<li>
				<i class="img1"></i>
				<p>在线收付款</p>
				在线收付款费率公示
			</li>
			<li>
				<i class="img2"></i>
				<p>信贷产品</p>
				包房专项贷款 运营贷
			</li>
			<li>
				<i class="img3"></i>
				<p>理财产品</p>
				泰坦增值宝 理财产品
			</li>
		</ul>
	</div>
</div>

<div class="TFS con_2">
	<div class="p_payment c2_bot">
        <div class="p_pl">
            <h2>在线收付款</h2>
            <p>泰坦金融，为您提供专业的在线收付款服务！</p>
            <a href="<%=basePath %>/receipt.shtml#content" class="bacth_export_hotel" >在线收款介绍</a>
            <a href="<%=basePath %>/pay.shtml#content" class="bacth_export_hotel" >在线付款介绍</a>
        </div>
        <div class="p_pr">
            <div class="img1">                
                <ul>                    
                    <li><img src="<%=cssSaasPath%>/images/TFS/home08.jpg"><p>方便快捷</p></li>
                    <li><img src="<%=cssSaasPath%>/images/TFS/home09.jpg"><p>安全可靠</p></li>
                    <li><img src="<%=cssSaasPath%>/images/TFS/home10.jpg"><p>即时到账</p></li>
                    <li class="p_r0"><img src="<%=cssSaasPath%>/images/TFS/home11.jpg"><p>费率低</p></li>
                </ul>
            </div>
        </div>
    </div>    
    <div class="p_credit c2_bot">
        <div class="p_pl">
            <h2>信贷产品</h2>
            <p>泰坦金融让您的运营数据变成真金白银！</p>  
            <a href="<%=basePath %>/loan.shtml#content" class="btn" >在线贷款介绍</a>
           
        </div>
        <div class="p_pr">
            <div class="img1">                
                <ul>
                    <li><img src="<%=cssSaasPath%>/images/TFS/home04.jpg"><p>创新融资模式</p></li>
                    <li><img src="<%=cssSaasPath%>/images/TFS/home05.jpg"><p>盘活企业库存</p></li>
                    <li><img src="<%=cssSaasPath%>/images/TFS/home06.jpg"><p>可质押品类</p></li>
                    <li class="p_r0"><img src="<%=cssSaasPath%>/images/TFS/home07.jpg"><p>押品替换可售</p></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="p_licai">
        <div class="p_pl">
            <h2>理财产品</h2>
            <p>泰坦金融，为您提供便捷、安全、稳定的理财服务！</p>  
        </div>
        <div class="p_pr">
            <div class="img1">                
                <img src="<%=cssSaasPath%>/images/TFS/tu01.jpg">
            </div>
            <div class="img2 dn">
                <img src="<%=cssSaasPath%>/images/TFS/tu02.jpg">
            </div>
        </div>
    </div>
</div>

<!-- 版权 -->
<div class="footer">
	Copyright(c) 2013-2016, fangcang.com. All Rights Reserved. 天下房仓版权所有 ICP证：粤13046150 号
</div>
<jsp:include page="/comm/static-js.jsp"></jsp:include>
<script type="text/javascript" src="<%=cssSaasPath%>/js/set_ad.js"></script>
<jsp:include page="home-js-include.jsp"></jsp:include>
</body>
</html>
