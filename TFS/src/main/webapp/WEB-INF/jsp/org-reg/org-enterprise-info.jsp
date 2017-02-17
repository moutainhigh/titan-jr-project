<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>企业基本信息-泰坦金融</title>
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
	        	<input type="hidden" name="imageType" id="imageType" value="1"/>
	        	<input type="hidden" name="userType" id="userType" value="1"/>
	        	
	            <div class="clearfix basic">
	                 <div class="basic_c">
	                   <div class="b_c_t"><i class="tfs_ico"></i>公司信息</div>
	                   <div class="b_c_c clearfix">
	                    <ul>
	                    <li><p><i class="c_f00">*</i>公司名称：</p><input type="text" class="text w_420 " name="orgName" value="${org.orgName }" placeholder="请与营业执照上的公司名称保持一致" datatype="*" errormsg="公司名称不能为空"></li>
	                        <li><p><i class="c_f00">*</i>营业执照号：</p><input type="text" class="text w_420 " name="buslince" placeholder="三证合一请填写统一社会信用代码" id="buslince" value="${org.buslince }" datatype="*" errormsg="营业执照号不能为空" afterPassed="checkOrgRegNum"></li>
	                        <li>
	                        <div class="fl w_290">
		                        <p class="fl"><i class="c_f00">*</i>上传营业执照照片：</p>
		                        <span class="fl span_btn w_50" id="img_upload">选择附件<input type="file" class="to_lead" name="img_file" id="img_file" onchange="ajaxFileUpload()"></span>
		                        <span class="fl span_btn w_50" style="display:none" id="uploading">上传中...</span>  
	                         	<i class="c_f00" id="upload_error" style="display:none">上传失败！</i>  
	                        </div>
	                        <div class="fl t_a_c">
	                        <c:if test="${not empty small_img_10}">
	                        	<img src="${small_img_10}" width="130" height="90" data-src-v="${small_img_10}" id="img_small" class="cursor J_magnify">
	                        </c:if>
	                        <c:if test="${empty small_img_10}">
	                        	<img src="<%=cssSaasPath%>/images/TFS/tu12.jpg" width="130"  id="img_small" class="cursor J_magnify">
	                        </c:if>  
	                        <p></p>
	                        </div>                      
	                        </li>
	                    </ul>
	                   </div>
	                 </div>
	                 <div class="basic_c">
	                     <div class="b_c_t"><span class="tfs_ico"></span>联系人信息</div>
	                     <div class="b_c_c brn">
	                     <ul>
	                        <li><p><i class="c_f00">*</i>联系人：</p><input type="text" class="text w_420 " name="connect" value="${org.connect }" datatype="*" errormsg="联系人不能为空"></li>
	                        <li><p><i class="c_f00">*</i>联系手机：</p><input type="text" class="text w_420 " name="mobiletel" value="${org.mobileTel }" id="mobiletel" require="true" datatype="m" errormsg="手机号码格式不正确"></li>
	                        <li><p><i class="c_f00">*</i>短信验证码：</p><input type="text" class="text w_420 " name="smsRegCode" id="smsRegCode" datatype="*" errormsg="短信验证码不能为空">
	                        <span class="verify">获取验证码</span></li>
	                     </ul>
	                     </div>
	                 </div>
	            </div>
	            <div class="create_c_btn">
	             <c:if test="${empty org.orgId or org.orgId==0  }">
	                <a href="<%=basePath %>/organ/showOrgUser.shtml?userType=1&eUserLoginName=${regUserLoginInfo.userLoginName}&ePassword=${regUserLoginInfo.password}&ePasswordConfirm=${regUserLoginInfo.passwordConfirm}&eRegCode=${regUserLoginInfo.regCode}" class="btn btnh">上一步</a>
	               </c:if>
	                <a href="javasript:void(0);" class="btn" onclick="regOrg()">下一步</a>           
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
	var smsRegCodeEle = $("#smsRegCode");
	var userLoginName = $("#mobiletel").val();
	var regCode = smsRegCodeEle.val();
	
	$.ajax({
		type:'post',
		url: '<%=basePath%>/organ/checkRegCode.shtml',		
		data:{"userLoginName":userLoginName,"regCode":regCode},
		dataType:"json",
		success:function(result){
			if(result.code==1){
				if($("#orgId").val().length>0){
					$("#info_form").attr({"action":"<%=basePath%>/organ/updateOrg.shtml"});
				}else{
					$("#info_form").attr({"action":"<%=basePath%>/organ/regOrg.shtml"});
				}
				 $("#reg_btn").click();
			}else{
				new top.createConfirm({
			        padding:'30px 20px 65px',
			        width:'330px',
			        title:'提示',
			        content : '<div class="f_14 t_a_c">'+result.msg+'</div>',      
			        button:false
			     }); 
			}
			
		},
		error:function(){
			alert("系统错误，请重试!");
		}
	});
	
}


//放大图
$('.J_magnify').on('click',function(){
	if(big_img_url){
		var _html = "<div class=\"clearfix agreement\" id=\"big_img_w\" style=\"width:825px;\">"
			+"<img src="+big_img_url+"  width=\"825\" id=\"big_img\" class=\"cursor \"></div>";
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
		data:{"orgId":$("#orgId").val(),"userType":$("#userType").val(),"buslince":value},
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
$('#buslince').on('change',function(){
	vform.setErrormsg($("#buslince"),'营业执照号不能为空');
});
//获取验证码
function timeOut(_this){
    var i=60;
    var interval=setInterval(function () {                
         if(i>0){
             _this.html("重新发送(" + i + ")"); 
             i--;
         }else{
            _this.removeClass("huise").html("获取验证码");
            clearInterval(interval);
         };              
    }, 1000);
};

$('.verify').on('click',function(){
	var phoneEle = $("#mobiletel");
	var receiveAddress = phoneEle.val();
	if(!phone_reg.test(receiveAddress)){
		phoneEle.focus();
		vform.setErrormsg(phoneEle,'手机号码格式不正确');
		return ;
	}
	_this= $(this);
	$.ajax({
		async : false,
		data:{"receiveAddress":receiveAddress},
		url : '<%=basePath%>/organ/sendRegCode.shtml',
		dataType : 'json',
		success : function(result){
			if(result.code==1){
				if(!_this.hasClass("huise")){
			        new top.Tip({msg : '验证码已成功发送,请注意查收！', type: 1, timer:2000});    
			        _this.addClass('huise');
			        timeOut(_this);
			    } 
			}else{
				new top.Tip({msg : result.msg, type: 1, timer:2500});
			}
		},
		error : function(){
			new top.Tip({msg : '网络错误，请重试', type: 1, timer:2000});
		}
	});
      
});


</script>

</body>
</html>
