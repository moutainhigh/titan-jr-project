<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
    <title>泰坦钱包</title>
   <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
	<script type="text/javascript" src="<%=basePath %>/js/ajaxfileupload.js"></script>
</head>
<body style="min-width: 1300px;" class="bg" >
<div class="header">
	<div class="w_1200">
		<div class="logo">
			<div class="l_img"><img src="<%=cssWalletPath %>/images/logo.png"></div>
			<div class="l_text">
				<i class="ico"></i>欢迎注册
			</div>
		</div>
		<div class="head_r">
			<ul>
				<!-- <li class="lion">首页</li>
				<li>解决方案</li> -->
				<li class="w_240 li_1">
					已注册，现在就
					<a class="li_btn" href="<%=basePath%>/ex/login.shtml">登录</a>
				</li>
			</ul>
		</div>
	</div>
</div>

<div class="register r_two r_h680">
	<div class="r_box ">
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
		<div class="r_c ">
			<div class="r_text">
				<ul>
					<li class="r_y1"><div class="rt_title">公司名称</div><input type="text" class="text" name="orgName" value="${org.orgName }" datatype="*" errormsg="必填项" placeholder="请与营业执照上的公司名称保持一致" ></li>
					<li class="r_y1"><div class="rt_title">营业执照号</div><input type="text" class="text" name="buslince" id="buslince" value="${org.buslince }" datatype="*" errormsg="必填项" afterPassed="checkOrgRegNum" placeholder="必填项"></li>
					<li class="r_y1"><div class="rt_title">联系人</div><input type="text" class="text" name="connect" value="${org.connect }" datatype="*" errormsg="必填项" placeholder="请输入联系人" ></li>
					<li class="r_y1"><div class="rt_title">联系手机</div><input type="text" class="text" name="mobiletel" value="${org.mobileTel }" id="mobiletel" require="true" datatype="m" errormsg="格式不正确" placeholder="请输入联系手机"></li>
					<li class="r_yzm m_b30"><div class="rt_title">验证码</div>
					<input type="text" class="text" name="smsRegCode" id="smsRegCode" require="true" datatype="/\w{4,}/" errormsg="长度太短" ><div class="r_verify">获取验证码</div>
					</li>
					<li class="r_uploading">
						<div class="fl r_u_l">上传营业执照照片：</div>
						<div class="fl r_u_r">
							<input type="file" name="img_file" id="img_file" onchange="ajaxFileUpload()">
						</div>
					</li>
					<li class="lb_btn "><a href="javasript:void(0);" onclick="regOrg()" class="">提交申请</a></li>
				</ul>
				<c:if test="${not empty small_img_10}">
	                        	<img src="${small_img_10}" width="130" height="90" data-src-v="${small_img_10}" id="img_small" class="cursor J_magnify">
	                        </c:if>
	                        <c:if test="${empty small_img_10}">
	                        	<img src="<%=cssWalletPath%>/images/TFS/tu12.jpg" width="130"  id="img_small" class="cursor J_magnify">
	                        </c:if>  
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
	<img src="images/tu01.jpg" alt="">
</div>	
</div>

<script type="text/javascript">
//验证
var big_img_url="${big_img_50}";
var vform =new validform('#info_form');
function ajaxFileUpload() {
    $.ajaxFileUpload({
        	url: '<%=basePath%>/ex/organ/upload.shtml',
            secureuri: false, 
            fileElementId: 'img_file', 
            dataType: 'json', 
            data:{"imageType":$("#imageType").val()},
            success: function (result, status){
            	//$("#uploading").hide();
            	//$("#img_upload").show();
            	if(result.code==1){
            		$("#img_small").attr({"src":result.data.imgPreview_10});
            		$("#img_small").attr({"data-src-v":result.data.imgPreview_10});
            		big_img_url = result.data.imgPreview_50;
            		$("#imgIds").val(result.data.imgIds);
            	}else{
            		alert("上传失败");
            		$("#upload_error").html(result.msg).show();
            	}
            },
            error: function (data, status, e){
            	////$("#uploading").hide();
            	//$("#img_upload").show();
            	//$("#upload_error").html("证件照上传失败").show();
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
		url: '<%=basePath%>/ex/checkRegCode.shtml',		
		data:{"userLoginName":userLoginName,"regCode":regCode},
		dataType:"json",
		success:function(result){
			if(result.code==1){
				if($("#orgId").val().length>0){
					$("#info_form").attr({"action":"<%=basePath%>/organ/updateOrg.shtml"});
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
				new top.Tip({msg : result.msg, type: 1, timer:2500});
			}
		},
		error : function(){
			new top.Tip({msg : '网络错误，请重试', type: 1, timer:2000});
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
 

</script>
</body>
</html>