<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/path-param.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>授信申请-泰坦金融</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
</head>
<body class="bgf2f2f2">
     <div class="TFS_Audit01">
        <span class="TFS_Audit03">授信申请审核未通过！</span>
    </div>
    <div class="My_loanNum_step">
        <h3 class="My_loanNum_step_h301"><span class="cur">申请授信流程:</span><span class="bd1">1. 填写企业基本信息</span><span class="bd1">2. 填写企业补充信息</span><span class="bd1">3. 填写担保人信息</span><span class="bd1">4. 上传资料</span><span>5. 提交授信申请</span></h3>
        <p class="TFS_Audit03_p">未通过原因：</p>
        <div class="TFS_Audit03_te">${creditOpinion.content}</div>
        <a href="<%=basePath%>/loan/credit/applyCredit.shtml" class="bacth_export_hotel My_loanNum_step_btn01" style="margin-top: 30px;">修改申请资料<em>&gt;</em></a>
    </div>
<jsp:include page="/comm/static-js.jsp"></jsp:include>
<script type="text/javascript">

</script>

</body>
</html>