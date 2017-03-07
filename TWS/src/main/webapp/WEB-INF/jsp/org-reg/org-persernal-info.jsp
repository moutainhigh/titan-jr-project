<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>个人基本信息-泰坦钱包</title>
   	<jsp:include page="/comm/tws-static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
	<script type="text/javascript" src="<%=basePath %>/js/ajaxfileupload.js"></script>
</head>
  
 <body style="min-width: 1300px;" class="bg" >
<jsp:include page="org-head.jsp"></jsp:include>

<div class="register r_two">
	<div class="r_box ">
		<form action="" id="info_form" method="post">
		<!-- 新增时使用 -->
       	<input type="hidden" name="userLoginName" value="${regUserLoginInfo.userLoginName}"/>
       	<input type="hidden" name="password" value="${regUserLoginInfo.password}"/>
       	<input type="hidden" name="passwordConfirm" value="${regUserLoginInfo.passwordConfirm}"/>
       	<input type="hidden" name="regCode" value="${regUserLoginInfo.regCode}"/>
       	<input type="hidden" name="channel" value="${regUserLoginInfo.channel}"/>
        <input type="hidden" name="info" value="${regUserLoginInfo.info}"/>
        <input type="hidden" name="encrypt_type" value="${regUserLoginInfo.encrypt_type}"/>
        <input type="hidden" name="sign" value="${regUserLoginInfo.sign}"/>
       	<!-- 修改时使用 -->
       	<input type="hidden" name="imgIds" id="imgIds" />
       	<input type="hidden" name="orgId" id="orgId" value="${org.orgId }"/>
       	<input type="hidden" name="userType" id="userType" value="2"/>
       	<input type="hidden" name="imageType" id="imageType" value="2"/>
		<div class="r_c ">
			<div class="r_text">
				<ul>
					<li class="r_y1"><div class="rt_title">姓名</div><input type="text" class="text" placeholder="请输入您的真实姓名" name="orgName" id="orgName" value="${org.orgName }" datatype="*" errormsg="必填项" ></li>
					<li class="r_y1"><div class="rt_title">身份证号</div><input type="text" class="text" placeholder="请输入您的身份证号码" name="certificatenumber" id="certificatenumber" value="${org.certificateNumber }" datatype="*" errormsg="必填项" afterPassed="checkOrgRegNum"></li>
					<!-- 初始状态 -->
					<li class="r_uploading">
						<div class="fl r_u_l">上传本人持身份证正面照：<a class="J_example " href="javascript:;">查看示例</a></div>
						<div class="fl r_u_r">
						<div class="TFSaddImg"></div>
			            <input type="file" name="img_file" id="img_file" onchange="ajaxFileUpload()"/>
			            <div class="TFSuploading hidden">
			                 <p class="TFSuploading1">
			                     <span></span>
			                 </p>    
			                 <p class="TFSuploading2">
			                     已上传<i>0</i>%
			                 </p>                                
			            </div>
			            <div class="TFSuploaderror hidden">
			                <div class="J_re_upload loanInformation_upload_btn">重新上传</div>
			                <p>上传失败</p>
			            </div>
			            <div class="TFSimgOn hidden">
			                <div class="J_delete_upload loanInformation_upload_btn">删除</div>
			                <div class="dd_img">
			                <c:if test="${not empty small_img_10}">
	                        	<img src="${small_img_10}" id="pre_view" width="180" height="120" data-src-v="${small_img_10}" />
	                        </c:if>
	                        <c:if test="${empty small_img_10}">
	                        	<img src="<%=cssWalletPath%>/images/tu12.jpg" id="pre_view" width="180" height="120" data-src-v=""/>          
	                        </c:if>
			                </div>          
			                <div class="dd_text" title="身份证">身份证</div>                
			            </div>
						</div>
					</li>
					
					<li class="lb_btn "><a href="javascript:;" class="" onclick="regOrg()">提交申请</a></li>
				</ul>

			</div>
			
		</div>
		<input type="submit" id="reg_btn" style="display:none;"/>
		</form>
	</div>
</div>

<jsp:include page="/comm/foot.jsp"></jsp:include>
<!-- 查看示例 -->
<div class="dn" id="example">
<div class="example" style="max-width: 1000px;">
	<img src="<%=cssWalletPath%>/images/tu01.jpg" alt="" style="max-height: 600px;max-width: 1000px;">
</div>	
</div>

<!-- 放大 -->
<div class="dn" id="TFSimgOnBig">
<div style="max-width: 1000px;">
<c:if test="${not empty big_img_50}">
	<img src="${big_img_50}" id="bigImg_50" alt="" style="max-height: 600px;max-width: 1000px;"/>
</c:if>
<c:if test="${ empty big_img_50}">
	<img src="<%=cssWalletPath%>/images/tu12.jpg" id="bigImg_50" alt="" style="max-height: 600px;max-width: 1000px;"/>
</c:if>
</div>	
</div>
<c:if test="${not empty small_img_10}">
<script type="text/javascript">
$(".TFSaddImg").addClass("hidden");
$(".TFSuploading").addClass("hidden");
$(".TFSimgOn").removeClass("hidden").addClass("TFSimgOnBig");
</script>
</c:if>
<script type="text/javascript">

//验证
var vform = new validform('#info_form');
var big_img_url="${big_img_50}";
//tws
function ajaxFileUpload() {
	$(".TFSaddImg").addClass("hidden");
    $(".r_u_r input").parent().find(".TFSuploading").removeClass("hidden");
    $(".r_u_r input").parent().find('.TFSuploaderror').addClass('hidden');
    loading($(".r_u_r input").parent().find(".TFSuploading"));
    $.ajaxFileUpload({
        	url: '<%=basePath%>/ex/organ/upload.shtml',
            secureuri: false, 
            fileElementId: 'img_file', 
            dataType: 'json', 
            data:{"imageType":$("#imageType").val()},
            success: function (result, status){
            	if(result.code==1){
            		big_img_url = result.data.imgPreview_50;
            		$("#imgIds").val(result.data.imgIds);
            		var objTFSuploading = $(".TFSuploading");
            		objTFSuploading.find("span").css("width","100%");
            		objTFSuploading.find("i").html(100);
            		 setTimeout(function(){
            			
            			$("#pre_view").attr({"src":result.data.imgPreview_10});
            			$("#pre_view").attr({"data-src-v":result.data.imgPreview_10});
            			$("#bigImg_50").attr({"src":result.data.imgPreview_50});
            			$(".TFSuploading").addClass("hidden");
            			$(".TFSimgOn").removeClass("hidden").addClass("TFSimgOnBig");
            		   },100);
            		
            	}else{
            		$(".TFSuploading").addClass("hidden");
            		$(".TFSuploaderror").removeClass("hidden");
            	}
            	
            },
            error: function (data, status, e){
            	$(".TFSuploading").addClass("hidden");
            	$(".TFSuploaderror").removeClass("hidden");
            }
        }
    );
}
var submitingFlag =  false;
//注册
function regOrg(){
	if(!vform.validate()){
		return;
	}
	var imageV = $("#pre_view").attr("data-src-v");
	if($.trim(imageV).length==0){
		new Tip({msg:"请上传证件照",type:2,timer:2000});
		return;
	}
	if($("#orgId").val().length>0){
		$("#info_form").attr({"action":"<%=basePath%>/reg/organ/updateOrg.shtml"});
	}else{
		$("#info_form").attr({"action":"<%=basePath%>/ex/organ/regOrg.shtml"});
	}
	//把按钮置灰
	if(!submitingFlag){
		$("#reg_btn").click();
		submitingFlag = true;
	}
	
}


//检查注册编码
function checkOrgRegNum(value, inputDom){
	var flag = false;
	$.ajax({
		async:false,
		type:'post',
		data:{"orgId":$("#orgId").val(),"userType":$("#userType").val(),"certificateNumber":value},
		url : '<%=basePath%>/ex/organ/checkOrgRegNum.shtml',
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



//查看示例
$('.J_example').on('click',function(){  
        var d = dialog({
            title: ' ',
            fixed: true,
            padding: '0 0 0px 0',
            content: $('#example'),
            skin : 'wallet_pop'      
        }).showModal()
        return false;
}); 
 
//上传
$(".TFSaddImg").on('click',function(){
    $(this).next("input").click();           
});
 

//重新上传
$(".J_re_upload").on('click',function(){
    $(this).parent().parent().find('input').click();             
});
function loading(obj){
	   var l1=obj.find("span");
	   var l2=obj.find("i");
	   var i=0;
	   var loadingJ=setInterval(function(){
	        l1.css("width",i+"%");
	        l2.html(i);
	        if(i==90){
	            clearInterval(loadingJ);
	        }
	        i++;
	},20); 
	}

//放大
$('body,html').on('click','.TFSimgOnBig',function(){  
    var d = dialog({
        title: ' ',
        fixed: true,
        padding: '0 0 0px 0',
        content: $('#TFSimgOnBig'),
        skin : 'wallet_pop'      
    }).showModal()
    return false;
});
	//删除
	$(".J_delete_upload").on('click',function(event){
		event.stopPropagation();
	    $(this).parent().addClass("hidden").removeClass("TFSimgOnBig");
	    $(this).parent().parent().find(".TFSaddImg").removeClass("hidden");
	});

</script>
</body>
</html>
