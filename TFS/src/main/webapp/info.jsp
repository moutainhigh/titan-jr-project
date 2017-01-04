<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>金融首页</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
	
</head>
  
  <body class="backdrop">
 
	        	<input type="hidden" name="imageType" id="imageType" value="1"/>
	        	<p class="fl"><i class="c_f00">*</i><input type="button" value="上传" onclick="ajaxFileUpload()"/>上传营业执照照片：</p>
	             <span class="fl">选择附件<input type="file" class="to_lead" name="img_file" id="img_file"></span>
	            

<jsp:include page="/comm/static-js.jsp"></jsp:include>
<script type="text/javascript" src="<%=basePath %>/js/ajaxfileupload.js"></script>
<script type="text/javascript">

function ajaxFileUpload() {
    
    $.ajaxFileUpload({
        	url: '<%=basePath%>/organ/upload.shtml',  //用于文件上传的服务器端请求地址
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: 'img_file', //文件上传域的ID
            dataType: 'json', //返回值类型 一般设置为json
            data:{"name":'luoqinglong'},
            success: function (data, status)  //服务器成功响应处理函数
            {
                
            },
            error: function (data, status, e)//服务器响应失败处理函数
            {
                alert(e);
            }
        }
    );
}

  

</script>

</body>
</html>
