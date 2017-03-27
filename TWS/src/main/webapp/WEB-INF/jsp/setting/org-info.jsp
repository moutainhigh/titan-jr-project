<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>机构信息-泰坦钱包</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
<body style="min-width: 1300px;" class="bg" >
<input type="hidden" id="org_usertype" value="${ financialOrganDTO.userType}"/>
<!-- 头部 -->
<jsp:include page="/comm/header.jsp"></jsp:include>
<div class="h_90"></div>
<!-- 内容 -->
<div class="w_1200 ">
	<div class="overview clearfix m_t20 o_set">	
		<jsp:include page="../slidemenu/jr-setting-menu.jsp">
			<jsp:param value="org" name="menu"/>
		</jsp:include>
		<div class="s_right clearfix">
			<div class="sr_crumbs">我的账户  >  泰坦钱包设置  >  机构信息</div>
			<div class="sr_list">
				<div class="img ico"></div>
				<div class="tit">泰坦码  <span class="c_f00">${financialOrganDTO.titanCode }</span></div>
				
			</div>
			<div class="sr_list">
				<div class="img ico"></div>
				<div class="tit">机构信息</div>
				<c:if test="${financialOrganDTO.userType ==1 }">
				<!-- 企业信息 -->
				<div class="srl_c clearfix"><div class="w_120 fl">公司名称：</div> <div class="con">${financialOrganDTO.orgName }</div></div>
				<div class="srl_c clearfix"><div class="w_120 fl">营业执照号：</div> <div class="con">${financialOrganDTO.buslince }</div></div>
				<div class="srl_c clearfix"><div class="w_120 fl">营业执照照片：</div> <div class="con cursor p_t15" style="width:140px;"><img src="${small_img_10}" width="140" height="100" class="J_magnify"></div></div>
				<div class="srl_c clearfix"><div class="w_120 fl">联系人：</div> 
					<div class="fl message dn"><div class="con"><input type="text"  id="connect"> <i class="btn J_save" data-field="connect">保存</i><i class="btn btn_g J_cancel">取消</i></div></div>
					<div class="fl message "><div class="con fl">${financialOrganDTO.connect }</div> <a href="javascript:void(0)" class="blue undl fl J_alter">修改</a></div>
				</div>
				<div class="srl_c clearfix"><div class="w_120 fl">联系手机：</div> 
					<div class="fl message dn"><div class="con"><input type="text"  id="mobileTel"> <i class="btn J_save" data-field="mobileTel">保存</i><i class="btn btn_g J_cancel">取消</i></div></div>
					<div class="fl message "><div class="con fl">${financialOrganDTO.mobileTel }</div> <a href="javascript:void(0)" class="blue undl fl J_alter">修改</a></div>
				</div>
				</c:if>
				<c:if test="${financialOrganDTO.userType == 2 }">
					<div class="srl_c clearfix"><div class="w_120 fl">姓名：</div> <div class="con">${financialOrganDTO.orgName }</div></div>
					<div class="srl_c clearfix"><div class="w_120 fl">身份证号码：</div> <div class="con">${financialOrganDTO.certificateNumber }</div></div>
					<div class="srl_c clearfix"><div class="w_120 fl">身份证照片：</div> <div class="con cursor p_t15" style="width:140px;"><img src="${small_img_10}" width="140" height="100" class="J_magnify"></div></div>
				</c:if>
			</div>
			<div class="sr_list">
				<div class="img ico"></div>
				<div class="tit">			
				<div class=" fl">小额免密支付开关</div>
				<div class="titright fl">
					<span uitype="switch" <c:if test="${allownopwdpay==1}">checked</c:if> class="tit_text" >
					</span>
					<c:if test="${allownopwdpay==0}"><span >未开通小额免密支付，付款时需要密码</span></c:if>
					<c:if test="${allownopwdpay==1}"><span class="tip">已开通小额免密支付，1000元以下付款时无需密码</span></c:if>
				</div>
				</div>
			</div>
			<div class="h_90"></div>
		</div>
	</div>
</div>
<div class="h_40"></div>
<!-- 版权 -->
<jsp:include page="/comm/foot-line.jsp"></jsp:include>
<!-- 图片放大 -->
<div class="clearfix dn" id="magnify" style="max-width:1000px;">
<img src="${big_img_50}"  style="max-width: 1000px; max-height: 600px;display: block;margin: 0 auto;">  
</div>
<script type="text/javascript">
var isadmin = '${isJrAdmin}';
//内容高度
function scrollCon(){
	var winH=$(window).height();
	var headH = $('.header').height();
	var footerH=$('.footer1').height();
	$('.s_right').css({'min-height':winH-headH-footerH});
}
scrollCon();
$(window).on('resize.fixed',function(){
	scrollCon();
})

//修改
$('.J_alter').on('click',function(){
 	var _this= $(this),
 		text=_this.parents('.message').find('.con').text(),
 		parents=_this.parents('.message');
 	parents.addClass('dn');
 	parents.siblings().removeClass('dn')
 	parents.siblings().find('input').val('').focus().val(text);
})
//检查是否设置了密码
function checkIsSetPayPassword(){
	var issetFlag = false;
  	 $.ajax({
      	 type: "post",
      	 async:false,
         url: "<%=basePath%>/account/checkIsSetPayPassword.action",
         dataType: "json",
         success: function(data){
          	 if(data.result=="success"){//没有设置密码
          		issetFlag = false;
          	 }else{//设置了密码
          		issetFlag = true;
          	 }
          }
       }); 
  	 return issetFlag;
}
//开关确认框
function showConfirm(){
	 window.top.createConfirm({
     	width:310,
         content:'<div style=\'line-height:24px;\'>'+msg+'</div>',
         ok : function(){
             var swi = toState?1:0;
             var pay="";
             if(toState){
            	 pay = PasswordStr2.returnStr();
             }
             $.ajax({
             		type:"post",
             		url:"<%=basePath%>/setting/set-swicth.shtml",
             		data:{"allownopwdpay":swi,"payPassword":pay},
             		dataType:"json",
             		success:function(result){
             			if(result.code==1){
                 			if(toState){
                 				$(".nopassword").addClass('tip').html('已开通小额免密支付，1000元以下付款时无需密码');
                        	 } else{
                        	   	$(".nopassword").removeClass('tip').html('未开通小额免密支付，付款时需要付款密码');
                        	 }
                 			 sw.setChecked(toState);
             			}else{
             				new top.Tip({msg : result.msg, type: 2});
             			}
             		},
             		complete:function(){
             			top.F.loading.hide();
             		},
             		error:function(xhr,status){
             			if(xhr.status&&xhr.status==403){
                			new top.Tip({msg : '没有权限访问，请联系管理员', type: 3 , timer:2000});
                			return ;
                		}
             			 new top.Tip({msg : '请求失败，请重试', type: 3});
             		}
             	}); 
             
         }
	});
}

//保存
$('.J_save').on('click',function(){
	var _this= $(this);
 	
	//////////////////
	var field = $(this).attr("data-field");
	var connectObj = $("#connect");
	var mobileObj = $("#mobileTel");
	
	var connect = connectObj.val();
	var mobile = mobileObj.val();
	var data={}
	if(field=='connect'){
		if($.trim(connect).length==0){
			new top.Tip({msg : '联系人不能为空', type: 2});
			return ;
		}
		data = {"connect":connect};
	}else{
		if($.trim(mobile).length==0){
			new top.Tip({msg : '联系手机不能为空', type: 2});
			return ;
		}
		if((!phone_reg.test(mobile))&&mobile.length>0){
			new top.Tip({msg : '联系手机格式错误', type: 2});
			return ;
		}
		data = {"mobile":mobile};
	}
	
	$.ajax({
        dataType : 'json',
        context: document.body,
        url : '<%=basePath%>/setting/set-enterprise-info.shtml', 
        data:data,
        type:'post',
        success : function(result){
        	if(result.code==1){
				 location.href="<%=basePath%>/setting/org-info.shtml";
				 val=_this.parents('.message').find('input').val(),
			 	parents=_this.parents('.message');
				parents.addClass('dn');
				parents.siblings().removeClass('dn');
				parents.siblings().find('.con').text(val);
			}else{
				new top.Tip({msg : result.msg, type: 2, timer:2000});
			}
        },
        complete:function(){
			top.F.loading.hide();
		},
		error:function(xhr,status){
			 //错误方法增强处理     
       	 	if(xhr.status&&xhr.status==403){
    			new top.Tip({msg : '没有权限访问，请联系管理员', type: 2 , time:2000});
    			return ;
    		}
			 new top.Tip({msg : '请求失败，请重试', type: 2});
		}
    });
	
	
})
//取消
$('.J_cancel').on('click',function(){
	var _this= $(this),
 		text=_this.parents('.message').find('.con').text(),
 		parents=_this.parents('.message');
	parents.addClass('dn');
	parents.siblings().removeClass('dn');
})

//图片放大
$('.J_magnify').on('click',function(){ 
        var d = dialog({
            title: ' ',
            fixed: true,
            padding: '0 0 0px 0',
            content: $('#magnify'),
            skin : 'wallet_pop'      
        }).showModal()
        return false;
});  

F.UI.scan();
//展开、收缩表格
var msg,sw,payDialog,toState;
    $.each($('.tit'), function(){
        var _this = $(this), siwtchArray = [], siwtchs = F.UI.find(_this.find('*[uitype=switch]'));
        if(siwtchs){
            siwtchs.length ? siwtchArray = siwtchs : siwtchArray.push(siwtchs);
            $.each(siwtchArray, function(k,v){
                v.dom.on('click',function(e){
                	sw = F.UI.find($(this));
                	toState = sw.getChecked();
                	//还原状态
                	sw.setChecked(!toState);
                	///业务代码写在下面
                	if(isadmin!='1'){
                		new top.Tip({msg : '只有管理员才能操作该功能', type: 2,timer:3000});
                		return ;
                	}
                    if(toState){//切换到开启
                    	msg = "您选择开通小额免密支付，1000元以下付款时无需密码。";
                    }else{//切换到关闭
                    	msg = "您选择关闭小额免密支付，所有付款都需要输入付款密码。";
                    }
                	if(toState){//切换到开启
                		//是否设置了付款密码
                    	if(!checkIsSetPayPassword()){
                    		new top.Tip({msg : '请先设置付款密码', type: 2,timer:3000});
                    		return ;
                    	}else{
                    		
                    	}
                    	showPayPassword();
                	}else{
                		showConfirm();
                	}
				});
            });
        }
    });
  //显示输入付款密码框
    function showPayPassword(){
       	$.ajax({
               dataType: 'html',
               context: document.body,
               url : '<%=basePath%>/account/showPayPassword.shtml',
               success : function(html){
            	   payDialog =  dialog({
                       title: '请输入付款密码',
                       padding: '0 0 20px 0 ',
                       content: html,
                       skin : 'saas_pop',
                       button : [
                           {
                               value: '确定',
                               skin : 'btn btn_grey ',
                               callback: function () {
                               	if(PasswordStr2.returnStr().length==6){
                               		if(to_check_payPassword()){
                               			return true;
                               		}
                               		$(".ui-dialog-content").html(html);
                               		$(".org-info-pwd-error").html("付款密码错误,请重新输入").show();
                               		setTimeout(function(){
                               			$('#passwordbox').click();
                               		},500);
                               		return false;
                               	}else{
                               		$(".org-info-pwd-error").html("请输入6位付款密码").show();
                               		setTimeout(function(){
                               			$('#passwordbox').click();
                               		},500);
                               		return false;
                               	}
                               }
                           }
                       ]
                   }).showModal().focus();
               }
           });
       }
  //检查密码是否正确
    function to_check_payPassword(){
    	var flag=false;
    	 $.ajax({
    		async:false, 
            type: "post",
            dataType: 'json',
            url: '<%=basePath%>/setting/check_payPassword.shtml',
            data: {
           	 payPassword:PasswordStr2.returnStr()
            },
            success: function (data) {
           	 if(data.code=="1"){
           		showConfirm();
           		flag = true;
           	 }else{
           		flag = false;
           	 }
            }
    	 });
    	 return flag;
    }
  
  //保存电话和联系人
    $(".b_submit").on('click',function(){
    	
    });
</script>

</body>
</html>
