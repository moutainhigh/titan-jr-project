<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>注册成功-泰坦金融</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
</head>
  
  <body>
    <div class="TFS" style="width: 1332px">
    <div class="clearfix create">        
        <div class="create_step create_s2">
            <ul>
                <li class="on1">创建账户</li>
                <li class="on1">填写基本信息</li>
                <li class="on p_l42">完成</li>
            </ul>
        </div>
        <div class="clearfix create_c">
            <div class="clearfix basic">                 
                 <div class="fulfill">
                     <i class="tfs_ico"></i>
                     <div class="fl p_t10">
                       <p>恭喜您！泰坦云金融服务申请提交成功！</p>
                       <span>请耐心等待，我们会尽快审核！</span>
                     </div>
                 </div>
            </div>
            <div class="create_c_btn">               
                <a href="<%=basePath %>/home.shtml" class="btn ">完成</a>             
            </div>
        </div>
    </div>
</div>    
  </body>
</html>
