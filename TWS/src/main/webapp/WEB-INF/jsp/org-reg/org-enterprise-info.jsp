<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
    <title>泰坦钱包</title>
   	<jsp:include page="/comm/tws-static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
	<script type="text/javascript" src="<%=basePath %>/js/ajaxfileupload.js"></script>
</head>
<body style="min-width: 1300px;" class="bg" >
<jsp:include page="org-head.jsp"></jsp:include>

<div class="register r_two r_h680">
	<div class="r_box ">
		<form action="" id="info_form" method="post" onsubmit="return checkOrgReg();">
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
        	<input type="hidden" name="imageType" id="imageType" value="1"/>
        	<input type="hidden" name="userType" id="userType" value="1"/>
		<div class="r_c ">
			<div class="r_text">
				<ul>
					<li class="r_y1"><div class="rt_title">公司名称</div><input type="text" class="text" name="orgName" value="${orgSubDTO.orgName }" datatype="*" errormsg="必填项" placeholder="请与营业执照上的公司名称保持一致" ></li>
					<li class="r_y1"><div class="rt_title">营业执照号</div><input type="text" class="text" name="buslince" id="buslince" value="${orgSubDTO.buslince }" datatype="*" errormsg="必填项" afterPassed="checkOrgRegNum" placeholder="必填项"></li>
					<li class="r_y1"><div class="rt_title">联系人</div><input type="text" class="text" name="connect" value="${org.connect }" datatype="*" errormsg="必填项" placeholder="请输入联系人" ></li>
					<li class="r_y1"><div class="rt_title">联系手机</div><input type="text" class="text" name="mobiletel" value="${org.mobileTel }" id="mobiletel" require="true" datatype="m" errormsg="格式不正确" placeholder="请输入联系手机"></li>
					<li class="r_yzm m_b30"><div class="rt_title">验证码</div>
					<input type="text" class="text" name="smsRegCode" id="smsRegCode" require="true" datatype="/\w{4,}/" errormsg="长度太短" ><div class="r_verify">获取验证码</div>
					</li>
					<li class="r_uploading">
						<div class="fl r_u_l">上传营业执照照片：</div>
						<div class="fl r_u_r">
							<div class="TFSaddImg "></div>
							<input type="file" name="img_file" id="img_file" onchange="ajaxFileUpload()">
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
				                <p id="upload_error">上传失败</p>
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
				                <div class="dd_text" title="营业执照">营业执照</div>                
				            </div>
						</div>
					</li>
					<li class="lb_btn "><a href="javascript:void(0);" onclick="regOrg()" class="">提交申请</a></li>
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
<div class="example">
	<img src="<%=cssWalletPath%>/images/tu01.jpg" alt="">
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
var big_img_url="${big_img_50}";
var vform =new validform('#info_form');
function ajaxFileUpload() {
	$(".r_u_r input").prev().addClass("hidden");
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
            			$(".TFSuploading").addClass("hidden");
            			$("#pre_view").attr({"src":result.data.imgPreview_10});
            			$("#pre_view").attr({"data-src-v":result.data.imgPreview_10});
            			$("#bigImg_50").attr({"src":result.data.imgPreview_50});
            			$(".TFSimgOn").removeClass("hidden").addClass("TFSimgOnBig");
            		   },100);
            	}else{
            		$(".TFSuploading").addClass("hidden");
            		$(".TFSuploaderror").removeClass("hidden");
            		$("#upload_error").html(result.msg);
            	}
            },
            error: function (data, status, e){
            	$(".TFSuploading").addClass("hidden");
            	$(".TFSuploaderror").removeClass("hidden");
            }
        }
    );
}

//注册
function regOrg(){
	if(checkOrgReg()==false){
		return ;
	}
	var smsRegCodeEle = $("#smsRegCode");
	var userLoginName = $("#mobiletel").val();
	var regCode = smsRegCodeEle.val();
	
	$.ajax({
		type:'post',
		url: '<%=basePath%>/ex/checkCode.shtml',		
		data:{"userLoginName":userLoginName,"code":regCode},
		dataType:"json",
		success:function(result){
			if(result.code==1){
				if($("#orgId").val().length>0){
					$("#info_form").attr({"action":"<%=basePath%>/reg/organ/updateOrg.shtml"});
				}else{
					$("#info_form").attr({"action":"<%=basePath%>/ex/organ/regOrg.shtml"});
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
function checkOrgReg(){
	if(!vform.validate()){
		return false;
	}
	var imageV = $("#pre_view").attr("data-src-v");
	if(typeof(imageV)=='undefined'||imageV.length==0){
		new Tip({msg:"请上传证件照",type:2,timer:2000});
		return false;
	}
	return true;
}
//检查注册编码
function checkOrgRegNum(value, inputDom){
	var flag = false;
	$.ajax({
		async:false,
		type:'post',
		data:{"orgId":$("#orgId").val(),"userType":$("#userType").val(),"buslince":value},
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
$('#buslince').on('change',function(){
	vform.setErrormsg($("#buslince"),'必填项');
});
 
//获取验证码
var sendingFlag = false;
function timeOut(_this){
    var i=60;
    var interval=setInterval(function () {                
         if(i>0){
             _this.html("重新发送(" + i + ")"); 
             i--;
         }else{
            _this.removeClass("r_huise").html("重新获取验证码");
            clearInterval(interval);
            sendingFlag = false;
         };              
    }, 1000);
};

$('.r_verify').on('click',function(){
	if(sendingFlag){
		return;
	}
	var phoneEle = $("#mobiletel");
	var receiveAddress = phoneEle.val();
	if(!phone_reg.test(receiveAddress)){
		phoneEle.focus();
		vform.setErrormsg(phoneEle,'格式不正确');
		return ;
	}
	_this= $(this);
	if(!$(this).hasClass("r_huise")){
		sendingFlag = true;
    	$(this).text("重新发送(60)");
        $(this).addClass('r_huise');
        timeOut($(this));
    } 
	$.ajax({
		//async : false,
		data:{"receiveAddress":receiveAddress,"msgType":1},
		url : '<%=basePath%>/ex/sendCode.shtml',
		dataType : 'json',
		success : function(result){
			if(result.code==1){
		        new top.Tip({msg : '验证码已成功发送,请注意查收！', type: 1, timer:2000});    
			}else{
				new top.Tip({msg : result.msg, type: 2, timer:2500});
			}
		},
		error : function(){
			new top.Tip({msg : '网络错误，请重试', type: 2, timer:2000});
		}
	});
      
});

/////tws
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
    $("#pre_view").attr({"data-src-v":""});
    $("#pre_view").attr({"src":""});
    
});
</script>
</body>
</html>