<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/path-param.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>金服首页</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>

</head>
<body class="bgf2f2f2">
    <div class="My_loanNum">
        <div class="My_loanNum_bg">
            <a href="javascript:void(0)" class="blue underline J_My_loanBtn">看看我能贷多少钱？</a>
        </div>
        <div class="My_loanNum_bg1 hidden">
            <img src="<%=cssSaasPath%>/images/TFS/loan_img04.jpg">
            <p>评估时间:2016.11.11</p>
            <h3>最高额度：<i>21,000,000</i>元</h3>
        </div>
    </div>
    <div class="My_loanNum_step">
        <h3 class="My_loanNum_step_h301"><span class="cur">申请授信流程:</span><span class="bd1">1. 填写企业基本信息</span><span class="bd1">2. 填写企业补充信息</span><span class="bd1">3. 填写担保人信息</span><span class="bd1">4. 上传资料</span><span>5. 提交授信申请</span></h3>
        <h4 class="mt10">申请贷款前请先申请授信</h4>
        <h4>信贷申请条件</h4>
        <p>公司成立超过一年；<br/>
        月均交易额超过100万，在房仓SAAS平台连续6个月有交易记录；<br/>
        公司法人或实际控制人为华籍；</p>
        <h4>自申请之日起，5个工作日内可获取申请结果</h4>
        <a href="<%=basePath%>/loan/credit/applyCredit.shtml" class="bacth_export_hotel My_loanNum_step_btn01">马上申请授信<em>></em></a>
    </div>
<jsp:include page="/comm/static-js.jsp"></jsp:include>
<script type="text/javascript">
    $(function(){
        $(".J_My_loanBtn").click(function(){
            $(this).parent().hide();
            $(".My_loanNum_bg1").show();
        });
    })
</script>

</body>
</html>