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
<body>
<div class="TFS">
    <div class="clearfix m_b10 m_t15">
        <div class="banner fl">
            <div class="TFS_banner" >
                <div class="bd">
                    <ul>
                        <li ><img src="<%=cssSaasPath%>/images/TFS/banner01.jpg"></li>
                        <li ><img src="<%=cssSaasPath%>/images/TFS/banner02.jpg"></li>
                    </ul>
                </div>
                <div class="hd">
                    <ul>
                    </ul>
                </div>
            </div>
        </div>
        <div class="dredge fr">
            <div class="title"><p>TITAN</p> Financial Service</div>
            <div class="c">泰坦金融是为旅游行业量身定制的在线金融服务平台，致力于为旅行行业提供安全、高收益、定制化的金融服务。泰坦金融将金融服务与旅游行业相结合，探索行业全新的发展模式。</div>
            <c:choose>
            	<c:when test="${ orgBindStatus==0}">
            		<div class="btn1">
		                <a class="tfs_ico b J_admin" href="javascript:;"></a>
		                <!-- 
		                <div class="t J_t">已有泰坦金融账号，点击绑定SAAS</div> -->
		            </div>
            	</c:when>
            	<c:when test="${orgCheckResultKey=='FT'}">
            		<!-- 初审中... -->
            		<div class="plan">
		                <ul>
		                    <li>提交申请</li>
		                    <li class="w_90 c_8d"><div>初审</div><p class="Province" >审核中...</p></li>
		                    <li class="c_8d">复审</li>
		                    <li class="c_8d">审核通过</li>
		                </ul>
		            </div>
            	</c:when>
            	<c:when test="${orgCheckResultKey=='FT_INVALID'}">
            		<!-- 初审未通过... -->
            		<div class="plan plan1">
		                <ul>
		                    <li>提交申请</li>
		                    <li class="w_90"><div class="c_f00">初审</div><p class="Province c_f00" title="未通过(${orgCheckResultMsg })">未通过(${orgCheckResultMsg })</p>
		                    <c:if test="${userType==1 }">
			                    <!-- 企业用户-修改 -->
			                    <a class="blue underline cursor" href="<%=basePath%>/organ/getEnterpriseInfo.shtml?orgId=${orgId}">修改申请信息</a>
		                    </c:if>
		                    <c:if test="${userType==2 }">
		                    	<!-- 个人用户-修改 -->
		                    <a class="blue underline cursor" href="<%=basePath%>/organ/getPersernalInfo.shtml?orgId=${orgId}">修改申请信息</a>
		                    </c:if>
		                    
		                    
		                    </li>
		                    <li class="c_8d"><div>复审</div></li>
		                    <li class="c_8d">审核通过</li>
		                </ul>
		            </div>
            		
            	</c:when>
            	<c:when test="${orgCheckResultKey=='REVIEW'}">
            		<!-- 复审中... -->
            		<div class="plan">
		                <ul>
		                    <li>提交申请</li>
		                    <li>初审</li>
		                    <li class="w_90 c_8d"><div>复审</div><p class="Province" >审核中...</p></li>
		                    <li class="c_8d">审核通过</li>
		                </ul>
		            </div>
            	</c:when>
            </c:choose>
            <div class="WC">
                <img src="<%=cssSaasPath%>/images/TFS/tu01.png">
                <p>关注泰坦金融公众号,同步获取账户资产信息</p>
            </div>
        </div>
    </div>
    <div class="product">
        <ul>
            <li><div class="tfs_ico img1"></div><p>在线收付款</p>在线收付款费率公示</li>
            <li><div class="tfs_ico img2"></div><p>信贷产品</p>包房专项贷款 &nbsp; 运营贷</li>
            <li><div class="tfs_ico img3"></div><p>理财产品</p>泰坦增值宝 &nbsp; 理财产品</li>
        </ul>
    </div>
    <div class="p_payment">
        <div class="p_pl">
            <h2>在线收付款</h2>
            <p>泰坦金融，为您提供专业的在线收付款服务！</p>
            <span class="bacth_export_hotel">在线收付款</span>
        </div>
        <div class="p_pr">
            <div class="img1">                
                <ul>
                    <li><img src="<%=cssSaasPath%>/images/TFS/tu03.jpg"><p>方便快捷</p></li>
                    <li><img src="<%=cssSaasPath%>/images/TFS/tu04.jpg"><p>安全可靠</p></li>
                    <li><img src="<%=cssSaasPath%>/images/TFS/tu05.jpg"><p>即时到账</p></li>
                    <li><img src="<%=cssSaasPath%>/images/TFS/tu06.jpg"><p>费率低</p></li>
                </ul>
            </div>
        </div>
    </div>    
    <div class="p_credit">
        <div class="p_pl">
            <h2>信贷产品</h2>
            <p>泰坦金融让您的运营数据变成真金白银！</p>  
            <span class="btn ">包房专项贷款</span>  <span class="btn">运营贷</span>
        </div>
        <div class="p_pr">
            <div class="img1">                
                <ul>
                    <li><img src="<%=cssSaasPath%>/images/TFS/tu07.jpg"><p>创新融资模式</p></li>
                    <li><img src="<%=cssSaasPath%>/images/TFS/tu08.jpg"><p>盘活企业库存</p></li>
                    <li><img src="<%=cssSaasPath%>/images/TFS/tu09.jpg"><p>可质押品类</p></li>
                    <li><img src="<%=cssSaasPath%>/images/TFS/tu10.jpg"><p>押品替换可售</p></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="p_licai">
        <div class="p_pl">
            <h2>理财产品</h2>
            <p>泰坦金融，为您提供便捷、安全、稳定的理财服务！</p>  
            <span class="bacth_export_hotel J_p1">泰坦增值宝</span>  <span class="bacth_export_hotel J_p2">理财产品</span>          
        </div>
        <div class="p_pr">
            <div class="img1">                
                <img src="<%=cssSaasPath%>/images/TFS/tu01.jpg">
            </div>
            <div class="img2 dn">
                <img src="<%=cssSaasPath%>/images/TFS/tu02.jpg">
            </div>
        </div>
    </div>
</div>    

<jsp:include page="/comm/static-js.jsp"></jsp:include>
<script type="text/javascript" src="<%=cssSaasPath%>/js/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript">
var isAdmin = '${isAdmin}';
$(function(){
    jQuery(".TFS_banner").slide({
         titCell:".hd ul", 
         mainCell:".bd ul", 
         effect:"fold", 
         autoPlay:true, 
         autoPage:true,
         trigger:"click",
         interTime:5000
    });    
});

//理财产品 
$('.J_p1').hover(function(){
    var jp1 = $(this).parent().siblings().find('.img1');
    var jp2 = $(this).parent().siblings().find('.img2');
    jp1.show();
    jp2.hide();
})    
$('.J_p2').hover(function(){
    var jp1 = $(this).parent().siblings().find('.img1');
    var jp2 = $(this).parent().siblings().find('.img2');
    jp1.hide(); 
    jp2.show(); 
})
//只有商家管理员才能开通泰坦金融哦 
$('.J_admin').on('click',function(){
	if(isAdmin==1){
		location = "<%=basePath%>"+"/organ/showOrgUser.shtml";
	}else{
		new top.createConfirm({
	        padding:'30px 20px 65px',
	        width:'330px',
	        title:'提示',
	        content : '<div class="f_14 t_a_c">只有商家管理员才能开通泰坦金融哦~</div>',      
	        button:false
	      }); 
	}
        
});
//已有泰坦金融账号，点击绑定SAAS
$('.J_t').on('click',function(){
	if(isAdmin==1){
		showBindSaasPage();
	}else{
		new top.createConfirm({
	        padding:'30px 20px 65px',
	        width:'330px',
	        title:'提示',
	        content : '<div class="f_14 t_a_c">只有商家管理员才能绑定SAAS哦~</div>',      
	        button:false
	      }); 
	}
});

function showBindSaasPage(){
	$.ajax({
        dataType : 'html',
        context: document.body,
        url : "<%=basePath%>"+"/organ/toBindSaas.shtml",
        success : function(html){
            var d =  window.top.dialog({
                title: ' ',
                padding: '0 0 20px 0',
                content: html,
                skin : 'saas_pop',
                button : [ 
                    {
                        value: '确认',
                        skin : 'btn p_lr30',
                        callback: function () {
                            confirmBindOrgInfo();
                        },
                        autofocus: true
                    },
                    {
                        value: '取消',
                        skin : 'btn btn_grey btn_exit',
                        callback: function () {
                        	
                        }
                    }
                ],
                close : function(){                        
                }
            }).showModal();
        }
    });
	
}
function confirmBindOrgInfo() {

    if (!top.$("#id_username").val()){
        alert("绑定时用户名不能为空");
        return;
    }
    if (!top.$("#id_password").val()){
        alert("绑定时密码不能为空");
        return;
    }

    var params = {
        username: top.$("#id_username").val(),
        password: top.$("#id_password").val()
    }
    $.ajax(
            {
                url: "<%=basePath%>" + "/organ/bindSaas.shtml",
                dataType: "json",
                data: params,
                success: function (result) {
                    var resultStr = '<div class="f_14 l_h30">当前SaaS商家与泰坦金融绑定成功 <br>请重新登录SaaS系统确认绑定状态</div>';
                    if (result.code != 1){
                        resultStr = '<div class="f_14 l_h30">绑定失败，失败原因： <br>' + result.msg + '</div>';
                    }
                    window.top.createConfirm({
                        padding: '30px 20px 50px',
                        width: '340px',
                        title: '提示',
                        //<a href="https：//www.xxxxxx.com"  target="_blank" class="blue underline cursor">https：//www.xxxxxx.com</a>
                        content: resultStr,
                        okValue: '确定',
                        skin: 'saas_confirm_singlebtn ',
                        ok: function () {

                        },
                        cancel: false
                    });
                }
            }
    );
}
</script>
</body>
</html>
