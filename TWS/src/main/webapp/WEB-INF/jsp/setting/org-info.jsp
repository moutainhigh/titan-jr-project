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
<jsp:include page="./../header.jsp"></jsp:include>
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
				<div class="srl_c clearfix"><div class="w_120 fl">营业执照照片：</div> <div class="con cursor p_t15"><img src="${small_img_10}" width="140" height="100" class="J_magnify"></div></div>
				<div class="srl_c clearfix"><div class="w_120 fl">联系人：</div> 
					<div class="fl message dn"><div class="con"><input type="text" data-is-update="0" id="connect"> <i class="btn J_save">保存</i><i class="btn btn_g J_cancel">取消</i></div></div>
					<div class="fl message "><div class="con fl">${financialOrganDTO.connect }</div> <a href="javascript:void(0)" class="blue undl fl J_alter">修改</a></div>
				</div>
				<div class="srl_c clearfix"><div class="w_120 fl">联系手机：</div> 
					<div class="fl message dn"><div class="con"><input type="text" data-is-update="0" id="mobileTel"> <i class="btn J_save">保存</i><i class="btn btn_g J_cancel">取消</i></div></div>
					<div class="fl message "><div class="con fl">${financialOrganDTO.mobileTel }</div> <a href="javascript:void(0)" class="blue undl fl J_alter">修改</a></div>
				</div>
				</c:if>
				<c:if test="${financialOrganDTO.userType == 2 }">
					<div class="srl_c clearfix"><div class="w_120 fl">姓名：</div> <div class="con">${financialOrganDTO.orgName }</div></div>
					<div class="srl_c clearfix"><div class="w_120 fl">身份证号码：</div> <div class="con">${financialOrganDTO.certificateNumber }</div></div>
					<div class="srl_c clearfix"><div class="w_120 fl">身份证照片：</div> <div class="con cursor p_t15"><img src="${small_img_10}" width="140" height="100" class="J_magnify"></div></div>
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
<jsp:include page="/comm/foot.jsp"></jsp:include> 
<script type="text/javascript">  
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

//我的账户
$('.hr_login').hover(function(){
	$(this).find('.hrl_ul').removeClass('dn');
	$(this).find('.hrl_hover').addClass('l_red');
},function(){
	$(this).find('.hrl_ul').addClass('dn');
	$(this).find('.hrl_hover').removeClass('l_red');
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



//保存
$('.J_save').on('click',function(){
	var _this= $(this),
 		val=_this.parents('.message').find('input').val(),
 		parents=_this.parents('.message');
	parents.addClass('dn');
	parents.siblings().removeClass('dn');
	parents.siblings().find('.con').text(val);
	new window.Tip({ msg : '保存成功',timer:1500,type:2});
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
$.each($('.tit'), function(){
//开关
var _this = $(this), siwtchArray = [], siwtchs = F.UI.find(_this.find('*[uitype=switch]'));
if(siwtchs){
    siwtchs.length ? siwtchArray = siwtchs : siwtchArray.push(siwtchs);
    $.each(siwtchArray, function(k,v){
        v.dom.on('click',function(e){
            var sw = F.UI.find($(this)), isPass = false;
            var msg;
            if(sw.getChecked()){//切换到开启
            	msg = "";
            	window.top.createConfirm({
                	width:320,
                	content: '<div class="f_14 l_h26">您选择开通小额免密支付，1000元以下付款时无需密码。</div>',
                    skin:'comfirm_pop saas_comfirm',
                    ok : function(){
                        isPass = true;
                    },
                    cancel : function(){
                        isPass = false;    
                    },
                    onclose : function(){
                    	if(isPass===true){ 		
                    		$('.tip').html('已开通小额免密支付，1000元以下付款时无需密码');
                        }else{
                        	 $('.tip').html('未开通小额免密支付，付款时需要密码'); 
	                        sw.setChecked(!sw.getChecked()); 	                       
                        }
                    }
                });   
	      
            }else{//切换到关闭
            	msg = "";
            	window.top.createConfirm({
                	width:320,
                	content: '<div class="f_14 l_h26">您选择关闭小额免密支付，所有付款都需要输入支付密码。</div>',
                    skin:'comfirm_pop saas_comfirm',
                    ok : function(){
                        isPass = true;                        
                    },
                    cancel : function(){                   	
                        isPass = false;                     
                    },
                    onclose : function(){
                    	if(isPass===true){ 
                           $('.tip').html('未开通小额免密支付，付款时需要密码'); 
                        }else{
                        	$('.tip').html('已开通小额免密支付，1000元以下付款时无需密码');	
	                        sw.setChecked(!sw.getChecked()); 	                       
                        }                      
                    }
                });   
            }
        });
    });
}
});
//密码输入框
var PasswordStr=new sixDigitPassword("passwordbox");
</script>

</body>
</html>
