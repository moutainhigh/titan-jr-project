<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>个人基本信息-泰坦金融</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
</head>
  
  <body class="backdrop">
<div class="TFS" style="width: 1332px">
    <div class="clearfix create">        
        <div class="create_step create_s1">
            <ul>
                <li class="on">创建账户</li>
                <li class="on1">填写基本信息</li>
                <li class="p_l42">完成</li>
            </ul>
        </div>
        <div class="clearfix create_c">
	        <form action="" id="info_form" method="post">
	        	<!-- 新增时使用 -->
	        	<input type="hidden" name="userLoginName" value="${regUserLoginInfo.userLoginName}"/>
	        	<input type="hidden" name="password" value="${regUserLoginInfo.password}"/>
	        	<input type="hidden" name="passwordConfirm" value="${regUserLoginInfo.passwordConfirm}"/>
	        	<input type="hidden" name="regCode" value="${regUserLoginInfo.regCode}"/>
	        	<!-- 修改时使用 -->
	        	<input type="hidden" name="imgIds" id="imgIds" />
	        	<input type="hidden" name="orgId" id="orgId" value="${org.orgId }"/>
	        	<input type="hidden" name="userType" id="userType" value="2"/>
	        	<input type="hidden" name="imageType" id="imageType" value="2"/>
	        	
	            <div class="clearfix basic">
	                 <div class="basic_c personage">
	                   <div class="b_c_t"><i class="tfs_ico"></i>填写个人信息</div>
	                   <div class="b_c_c clearfix brn">
	                    <ul>
	                    <li><p><i class="c_f00">*</i>姓名：</p><input type="text" class="text w_500 " name="orgName" id="orgName" value="${org.orgName }" placeholder="请与证件上的姓名保持一致"  datatype="*" errormsg="姓名不能为空"></li>
	                        <li><p><i class="c_f00">*</i>身份证号：</p><input type="text" class="text w_500 " name="certificatenumber" id="certificatenumber" value="${org.certificateNumber }" datatype="*" errormsg="身份证号不能为空" afterPassed="checkOrgRegNum"></li>
	                        <li>
	                        <div class="fl w_370">
	                        	<p class="fl"><i class="c_f00">*</i>上传本人持身份证正面照：</p>
	                         	<span class="fl span_btn w_50" id="img_upload">选择附件<input type="file" class="to_lead" name="img_file" id="img_file" onchange="ajaxFileUpload()"></span>
		                        <span class="fl span_btn w_50" style="display:none" id="uploading">上传中...</span>  
	                         	<i class="c_f00" id="upload_error" style="display:none">上传失败！</i>
	                        </div>
	                        <div class="fl t_a_c">
	                         <c:if test="${not empty small_img_10}">
	                        	<img src="${small_img_10}" width="130" data-src-v="${small_img_10}" height="90" id="img_small" class="cursor J_magnify">
	                        </c:if>
	                        <c:if test="${empty small_img_10}">
	                        	<img src="<%=cssSaasPath%>/images/TFS/tu13.jpg" width="130" id="img_small" class="cursor J_magnify">
	                        </c:if>
	                        
	                        </div>                      
	                        </li>
	                    </ul>
	                   </div>
	                 </div>
	            </div>
	            <div class="create_c_btn">
	                <a href="<%=basePath %>/organ/showOrgUser.shtml" id="prevBtn" class="btn btnh">上一步</a>
	                <a href="javascript:void(0);" class="btn" onclick="regOrg()">下一步</a>           
	            </div>
	            <input type="submit" id="reg_btn" style="display:none;"/>
            </form>
        </div>
    </div>
</div>    

<jsp:include page="/comm/static-js.jsp"></jsp:include>
<script type="text/javascript" src="<%=basePath %>/js/ajaxfileupload.js"></script>
<script type="text/javascript">
var phone_reg=/^13[0-9]{9}$|^14[0-9]{9}$|^15[0-9]{9}$|^18[0-9]{9}$|^17[0-9]{9}$/;
var big_img_url="${big_img_50}";
var vform =new validform('#info_form',{
	msgAlign: 'bottom'
});

//默认是手机
var regUserType = 1;
//检测注册的用户名是邮箱还是手机
if(!phone_reg.test('${regUserLoginInfo.userLoginName}')){
	regUserType = 2;
}

var herf = $('#prevBtn').attr('href');
if(regUserType == 1)
{
 	 $('#prevBtn').attr('href',herf+"?userType=2&regUserType="+regUserType+"&pUserLoginName=${regUserLoginInfo.userLoginName}&pPassword="+
 		 "${regUserLoginInfo.password}&pPasswordConfirm=${regUserLoginInfo.passwordConfirm}&pRegCode=${regUserLoginInfo.regCode}");
}
else if(regUserType == 2)
{
	 $('#prevBtn').attr('href',herf+"?userType=2&regUserType="+regUserType+"&mUserLoginName=${regUserLoginInfo.userLoginName}&mPassword="+
			 "${regUserLoginInfo.password}&mPasswordConfirm=${regUserLoginInfo.passwordConfirm}&mRegCode=${regUserLoginInfo.regCode}");
}


function ajaxFileUpload() {
	$("#uploading").show();
	$("#img_upload").hide();
	$("#upload_error").hide();
    $.ajaxFileUpload({
        	url: '<%=basePath%>/organ/upload.shtml',
            secureuri: false, 
            fileElementId: 'img_file', 
            dataType: 'json', 
            data:{"imageType":$("#imageType").val()},
            success: function (result, status){
            	$("#uploading").hide();
            	$("#img_upload").show();
            	if(result.code==1){
            		$("#img_small").attr({"src":result.data.imgPreview_10});
            		$("#img_small").attr({"data-src-v":result.data.imgPreview_10});
            		big_img_url = result.data.imgPreview_50;
            		$("#imgIds").val(result.data.imgIds);
            	}else{
            		$("#upload_error").html(result.msg).show();
            	}
            },
            error: function (data, status, e){
            	$("#uploading").hide();
            	$("#img_upload").show();
            	$("#upload_error").html("证件照上传失败").show();
            }
        }
    );
}

//注册
function regOrg(){
	if(!vform.validate()){
		return;
	}
	var imageV = $("#img_small").attr("data-src-v");
	if(typeof(imageV)=='undefined'){
		$("#upload_error").html("请上传证件照").show();
		return;
	}
	if($("#orgId").val().length>0){
		$("#info_form").attr({"action":"<%=basePath%>/organ/updateOrg.shtml"});
	}else{
		$("#info_form").attr({"action":"<%=basePath%>/organ/regOrg.shtml"});
	}
	$("#reg_btn").click();
}

//放大图
$('.J_magnify').on('click',function(){
	if(big_img_url){
		var _html = "<div class=\"clearfix agreement\" id=\"big_img_w\" style=\"width:525px;\">"
			+"<img src="+big_img_url+"  width=\"525\" id=\"big_img\" class=\"cursor \"></div>";
		      var d =  window.top.dialog({
		          title: ' ',
		          padding: '0',
		          content: _html,
		          skin : 'saas_pop saas_hfe',                  
		          button :false,
		          close : function(){                        
		          }
		      }).showModal();
	}
	
});

//检查注册编码
function checkOrgRegNum(value, inputDom){
	var flag = false;
	$.ajax({
		async:false,
		type:'post',
		data:{"orgId":$("#orgId").val(),"userType":$("#userType").val(),"certificateNumber":value},
		url : '<%=basePath%>/organ/checkOrgRegNum.shtml',
		dataType : 'json',
		success : function(result){
			if(result.code==1){
				flag = true;
			}else{
				flag = false;
				//修改错误提示信息
				vform.setErrormsg(inputDom,result.msg);
			}
		},
		error:function(){
			flag = true;
			new top.Tip({msg : '系统错误，请重试', type: 1, timer:2000}); 
		}
	});
	return  flag;
}
//还原提示语
$('#certificatenumber').on('change',function(){
	vform.setErrormsg('#certificatenumber','身份证号不能为空');
});

</script>

</body>
</html>
