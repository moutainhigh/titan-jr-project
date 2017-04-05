<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>授信申请-泰坦金融</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>

</head>
<body class="bgf2f2f2">
    <div class="My_loanNum ">
        <div class='My_loanNum_bg ${not empty lastUpdateDate ? "hidden" : ""}'>
        	<c:if test="${maxLoanAmount != null && lastUpdateDate == null}">
           		 <a href="javascript:void(0)" class="blue underline J_My_loanBtn">看看我能贷多少钱？</a>
            </c:if>	
        </div>
        <div class='My_loanNum_bg1  ${empty lastUpdateDate ? "hidden" : ""}'>
            <img src="<%=cssSaasPath%>/images/TFS/loan_img04.jpg">
            <p>评估时间 : <fmt:formatDate value="${lastUpdateDate}" pattern="yyyy-MM-dd" /></p>
            <h3>最高额度：<i><fmt:formatNumber value="${maxLoanAmount}"  pattern="#,##0.00#" /></i>元</h3>
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
        <c:if test="${userType == 1}">
        	<a href="<%=basePath%>/loan/credit/applyCredit.shtml" class="bacth_export_hotel My_loanNum_step_btn01">马上申请授信<em>></em></a>
        </c:if>
    </div>
<jsp:include page="/comm/static-js.jsp"></jsp:include>
<script type="text/javascript">
    $(function(){
    	
        $(".J_My_loanBtn").click(function(){
        	F.loading.show();
        	$.ajax({
				type:"get",
				url: '<%=basePath%>/loan/credit/updateLoanAmountDate.shtml',
				dataType:"json",
				success:function(result){
					if(result.code==1){
						$('.My_loanNum_bg').hide();
				       $(".My_loanNum_bg1").show();
					}
				},
				error:function(){
				},
				complete: function(){
					 F.loading.hide();
				}
			 });
        });
    })
</script>
</body>
</html>