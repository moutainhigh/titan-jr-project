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
     <div class="TFS_Audit01">
        授信申请审核中！
    </div>
    <div class="My_loanNum_step">
        <h3 class="My_loanNum_step_h302"><span class="cur">申请授信流程:</span><span class="bd1">1. 填写企业基本信息</span><span class="bd1">2. 填写企业补充信息</span><span class="bd1">3. 填写担保人信息</span><span class="bd1">4. 上传资料</span><span>授信申请审核中...</span></h3>
        <p class="TFS_Audit01_p">申请提交时间：<fmt:formatDate value="${creditOrder.reqTime}" pattern="yyyy-MM-dd HH:mm:ss" /> <br/>
        请耐心等待，自申请之日起，5个工作日内可获取申请结果</p>
    </div>
<jsp:include page="/comm/static-js.jsp"></jsp:include>
<script type="text/javascript">

</script>

</body>
</html>