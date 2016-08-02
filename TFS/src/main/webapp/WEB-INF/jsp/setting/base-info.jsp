<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>基本信息-泰坦金融</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
</head>
<body>
<input type="hidden" id="org_usertype" value="${ financialOrganDTO.userType}"/>

<div id="scroll" >
		<div class="main_sell clearfix user_title">
			<div class="p_r30 p_l10">
				<span>泰坦金融&nbsp;-&nbsp;泰坦金融设置&nbsp;-&nbsp;基础信息</span>
			</div>
		</div>
		<div class="TFS_set">
			
		</div>
	</div>
	<div class="scroll_x hide t_56"></div>
	<div class="main_con p_t56">
		<div class="TFS_set" style="padding:0 16px 0 0; ">
			<div class="TFS_basic">
				<h2>登录信息：</h2>
				<div class="TFS_basictop">
					<ul>
						<li><span class="TFS_basname">泰坦金融管理员用户名：</span>${adminUser.userloginname }</li>
						<li class="fl"><span class="TFS_basname">泰坦金融管理员登录密码：</span> ······ <span class="J_modify undl curpo blue">修改</span></li>
						<li class="fl"><span class="TFS_basname">泰坦码：</span><strong class="c_f00">${financialOrganDTO.titanCode }</strong></li>
					</ul>
				</div>
				<c:if test="${financialOrganDTO.userType ==1 }">
				<!-- 企业信息 -->
				<h2>公司信息：</h2>
				<div class="TFS_basicbottom en_info_wrap">
					<ul>
						<li class="fl"><span class="TFS_basname">公司名称：</span>${financialOrganDTO.orgName }</li>
						<li class="fl"><span class="TFS_basname fl">联系人：</span><div class="info_c"><h3>${financialOrganDTO.connect }</h3><i class="J_mody blue curpo undl">修改</i></div><input type="text" style="display:none;" data-is-update="0" id="connect" class="text w_180"></li>
						<li class="fl"><span class="TFS_basname">营业执照：</span>${financialOrganDTO.buslince }</li>
						<li class="fl"><span class="TFS_basname fl">联系手机：</span><div class="info_c"><h3>${financialOrganDTO.mobileTel }</h3><i class="J_mody blue curpo undl">修改</i></div><input type="text" style="display:none;" data-is-update="0" id="mobileTel" class="text w_180" ></li>
						<li class="fl"><span class="TFS_basname fl">上传工商执照注册照片：</span>
						<p class="fl"><img width="130" height="90" class="cursor J_magnify" src="${small_img_10 }"></p></li>
					</ul>
				</div>
				</c:if>
				<c:if test="${financialOrganDTO.userType == 2 }">
				<!-- 个人信息 -->
				<h2 >个人信息：</h2>
				<div class="TFS_basicbottom" >
					<ul>
						<li><span class="TFS_basname">姓名：</span>${financialOrganDTO.orgName }</li>
						<li><span class="TFS_basname">身份证号：</span>${financialOrganDTO.certificateNumber }</li>
						<li><span class="TFS_basname fl">本人持身份证正面照：</span>
						<p class="fl"><img width="130" height="90" class="cursor J_magnify" src="${small_img_10 }"></p></li>
					</ul>
				</div>
				</c:if>
			</div>
			<c:if test="${financialOrganDTO.userType == 1 }">
				<div class="TFS_basicbutton" style="display:none;">
					<span class="btn p_lr30 b_submit">保存</span>
					<span class="btn btn_exit b_cancel">取消</span>
				</div>
			</c:if>
		</div>
	</div>
	<div id="info_enter_big_img">
		
	</div>
	<div id="info_per_big_img">
		
	</div>
<jsp:include page="/comm/static-js.jsp"></jsp:include>
<script> 
F.UI.scan();
//new validform('.pour_c');
var big_img_url = '${big_img_50 }';
//放大图
$('.J_magnify').on('click',function(){
	var _html = "";
		if(big_img_url){
			if($("#org_usertype").val()==1){
				_html = "<div class=\"clearfix agreement\" id=\"big_img_w\" style=\"width:825px;\">"
					+"<img src="+big_img_url+"  width=\"825\" id=\"big_img\" class=\"cursor \"></div>";
			}else{
				_html = "<div class=\"clearfix agreement\" id=\"big_img_w\" style=\"width:525px;\">"
					+"<img src="+big_img_url+"  width=\"525\" id=\"big_img\" class=\"cursor \"></div>";
			}
		}
        var d =  window.top.dialog({
            title: ' ',
            padding: '0',
            content: _html,
            skin : 'saas_pop saas_hfe',                  
            button :false,
            close : function(){                        
            }
        }).showModal();
    });
//点击修改
$(".J_mody").on('click',function(){
	var parentW = $(this).parents(".info_c");
	parentW.hide();
	parentW.next("input").show().attr({"data-is-update":"1"});
	$(".TFS_basicbutton").show();
});
//修改密码
$('.J_modify').on('click',function(){
    top.F.loading.show();
    $.ajax({
        dataType : 'html',
        context: document.body,
        url : '<%=basePath%>/setting/login-pwd.shtml',           
        success : function(html){
            top.F.loading.hide();
             de =  window.top.dialog({
                title: ' ',
                padding: '0 0 0px 0',
                content: html,
                skin : 'saas_pop' ,
            }).showModal();
        },
        complete:function(){
			top.F.loading.hide();
		},
		error:function(xhr,status){
      	 	if(xhr.status&&xhr.status==403){
   				new top.Tip({msg : '没有权限访问，请联系管理员', type: 3 , timer:2000});
   				return ;
   			}
			 new top.Tip({msg : '请求失败，请重试', type: 2});
		}
    });
}); 
//保存
$(".b_submit").on('click',function(){
	saveInfo();
});
//取消
$(".b_cancel").on('click',function(){
	$(".TFS_basicbutton").hide();
	var parentW = $(".info_c");
	parentW.show();
	parentW.next("input").hide();
});
//保存联系人信息
function saveInfo(){
	var connectObj = $(".en_info_wrap #connect");
	var mobileObj = $(".en_info_wrap #mobileTel");
	
	var connect = connectObj.val();
	var mobile = mobileObj.val();
	if(connectObj.attr("data-is-update")=="1"&&$.trim(connect).length==0){
		new top.Tip({msg : '联系人不能为空', type: 2});
		return ;
	}
	if(mobileObj.attr("data-is-update")=="1"&&$.trim(mobile).length==0){
		new top.Tip({msg : '手机号码不能为空', type: 2});
		return ;
	}
	if((!phone_reg.test(mobile))&&mobile.length>0){
		new top.Tip({msg : '联系手机格式错误', type: 2});
		return ;
	}
	
	$.ajax({
        dataType : 'json',
        context: document.body,
        url : '<%=basePath%>/setting/set-enterprise-info.shtml', 
        data:{"connect":connect,"mobile":mobile},
        type:'post',
        success : function(result){
        	if(result.code==1){
				 location.href="<%=basePath%>/setting/base-info.shtml";
			}else{
				new top.Tip({msg : result.msg, type: 3, timer:2000});
			}
        },
        complete:function(){
			top.F.loading.hide();
		},
		error:function(xhr,status){
			 //错误方法增强处理     
       	 	if(xhr.status&&xhr.status==403){
    			new top.Tip({msg : '没有权限访问，请联系管理员', type: 3 , time:2000});
    			return ;
    		}
			 new top.Tip({msg : '请求失败，请重试', type: 2});
		}
    });
	
}
</script>  

</body>
</html>
