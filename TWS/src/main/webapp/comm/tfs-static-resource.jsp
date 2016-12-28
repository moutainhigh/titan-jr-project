<!-- common css-->
<%@ include file="/comm/path-param.jsp"%>
 
 <link rel="stylesheet" href="<%=cssSaasPath%>/css/style_TFS.css" data-export="true">
 <script type="text/javascript" src="<%=basePath%>/rsa/Barrett.js"></script>
 <script type="text/javascript" src="<%=basePath%>/rsa/BigInt.js"></script>
 <script type="text/javascript" src="<%=basePath%>/rsa/RSA.js"></script>
 <script type="text/javascript" src="<%=basePath%>/rsa/RsaDataUtil.js"></script>

 <script type="text/javascript" src="<%=basePath%>/js/tfs-common.js"></script>
 <script type="text/javascript" src="<%=basePath%>/js/tfs-util.js"></script>
 
 <!-- 钱包的js -->
<script src="<%=cssWalletPath%>/js/jquery-3.1.1.min.js"></script>
<script src="<%=cssWalletPath%>/js/fangcang.min.js"></script>
<script src="<%=cssWalletPath%>/js/common.js"></script>
<script src="<%=cssWalletPath%>/js/AD.js"></script>
 

 
<input id="module" type="hidden" value="${applicationScope.module}">
<input id="empoent" type="hidden" value="${applicationScope.empoent}">


