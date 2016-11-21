<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>SAAS后台管理</title>
	<jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
	<style>html,body{ overflow: hidden;}</style>
</head>
<body>
	<!--新版提示-引导-->
	<!-- <div class="guidance_mask"></div>
	<div class="guidance">
		<ul>
			<li class="g_img01">
				<div class="g_cont">
					<h2>新版SAAS正式上线啦!</h2>
					<div class="g_cont1">
						<h3>全新库存</h3>
						<p>支持一键将自签酒店录入到系统</p>
						<div class="gbtn m_t20">我知道了</div>
					</div>
				</div>
			</li>
			<li class="g_img02" style="display:none;">
				<div class="g_cont">
					<h3>价格房态</h3>
					<p>点击可以录价格、房价、条款</p>
					<h3 class="m_t20">渠道配额</h3>
					<p>设置卖到各渠道不同的房间数量</p>
					<div class="gbtn m_t20">我知道了</div>
				</div>
			</li>
			<li class="g_img03" style="display:none;">
				<div class="g_cont">
					<h3>日历框可用鼠标框选多个日期</h3>
				</div>
				<div class="gbtn">我知道了</div>
			</li>
			<li class="g_img04" style="display:none;">
				<div class="g_cont">
					<h3>房态共享</h3>
					<p>与其它价格计划共享房态与房间数</p>
					<div class="gbtn m_t20">我知道了</div>
				</div>
			</li>
			<li class="g_img05" style="display:none;">
				<div class="g_cont">
					<h3>操作日志</h3>
					<p>
						在日历表里“详细内容”里
						<br>查看某一天的操作日志</p>
					<div class="gbtn m_t20"></div>
				</div>
			</li>
		</ul>
		<div class="guidance_circle" >
			<ul>
				<li class="hover"></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
		</div>
	</div> -->

	<div class="header_shrink "></div>
	<div class="wrap">
		<div class="header">
			<div class="h_logo fl">
				<div class="h_logo_img">
					<img src="${CURRENT_LogoUrl}" alt=""></div>
			</div>
			<ul class="h_nav fl">
				<li class="nav_home">
					<a href="iframe-新签入库.html"> <i></i>
						<span>首页</span>
					</a>
					<div class="nav_sub" >
						<a href="首页.html" class="s1" data-parent="false" >
							<span></span>
							交易平台
						</a>
						<a href="iframe-我要采购.html" class="s2" data-parent="false">
							<span></span> 							 						
							我要采购
						</a>
						<a href="采购旅游产品.html" class="s3" data-parent="false">						
							<span></span>
							采购旅游产品
						</a>
						<a href="http://bbs.fangcang.com" target="_blank" class="s4 BBS">
							<span></span>
							论坛
						</a>
					</div>
				</li>
				<li class="nav_hotel">
					<a href="#"> <i></i>
						<span>酒店</span>
					</a>
					<div class="nav_sub" >
						<!--   <a href="iframe-供-产品.html" class="s4" data-parent="false">
						<span></span>
						采购联盟商家产品
					</a>
					-->
					<a href="iframe-供-产品.html" class="s1"  data-parent="false" target="_blank" >
						<span></span>
						录入·库存
					</a>
					<a href="#" class="s2" data-url="slidemenu/jiudian_shoumai.html">
						<span></span>
						售卖
					</a>
					<a href="#" class="s3" data-url="slidemenu/jiudian_dingdan.html">
						<span></span>
						<i></i>
						订单
					</a>
				</div>
			</li>

			<li class="nav_travel">
				<a href="#">
					<i></i>
					<span>旅游</span>
				</a>
				<div class="nav_sub" >
					<a href="#" class="s1"  data-url="slidemenu/lvyou_qyrk.html">
						<span></span>
						签约入库
					</a>
					<a href="#" class="s2" data-url="slidemenu/lvyou_shoumai.html">
						<span></span>
						售卖
					</a>
					<a href="#" class="s3" data-url="slidemenu/lvyou_dingdan.html">
						<span></span>
						<i></i>
						订单
					</a>
				</div>
			</li>
			<li class="nav_partner">
				<a href="#">
					<i></i>
					<span>合作伙伴</span>
				</a>
				<div class="nav_sub">
					<a href="iframe - 酒店列表.html" class="s1" data-parent="false">
						<span></span>
						供应商
					</a>
					<a href="#" class="s2" data-url="slidemenu/lvyou_fxs.html" >
						<span></span>
						分销商
					</a>
					<a href="iframe-供-产品.html" class="s3" data-parent="false">
						<span></span>
						其他联系人
					</a>
				</div>
			</li>
			<li class="nav_finance">
				<a href="#">
					<i></i>
					<span>财务</span>
				</a>
				<div class="nav_sub" >
					<a href="#" class="s1" data-url="slidemenu/caiwu_fwgd.html">
						<span></span>
						财务工单
					</a>
					<a href="#" class="s2" data-url="slidemenu/caiwu_yfk.html">
						<span></span>
						预付款
					</a>
					<a href="#" class="s3" data-url="slidemenu/caiwu_dzjs.html">
						<span></span>
						对账结算
					</a>
					<a href="#" class="s4" data-url="slidemenu/caiwu_mfyj.html">
						<span></span>
						面付佣金
					</a>
					<a href="#" class="s5" data-url="slidemenu/caiwu_jymx.html">
						<span></span>
						交易明细
					</a>
					<a href="#" class="s6" data-url="slidemenu/caiwu_bbtj.html">
						<span></span>
						财务统计
					</a>
					<a href="#" class="s7" data-url="slidemenu/caiwu_hlgl.html">
						<span></span>
						汇率管理
					</a>
				</div>
			</li>
			<li class="nav_set">
				<a href="#">
					<i></i>
					<span>系统设置</span>
				</a>
				<div class="nav_sub" >
					<a href="#" class="s1" data-url="slidemenu/xitong_grxx.html">
						<span></span>
						个人信息
					</a>
					<a href="#" class="s2" data-url="slidemenu/xitong_sjgl.html">
						<span></span>
						商家管理
					</a>
					<a href="#" class="s3" data-url="slidemenu/xitong_rygl.html">
						<span></span>
						人员管理
					</a>
					<a href="#" class="s4" data-url="slidemenu/xitong_gxsz.html">
						<span></span>
						个性设置
					</a>
					<a href="#" class="s5" data-url="slidemenu/xitong_jdgl.html">
						<span></span>
						酒店管理
					</a>
					<a href="#" class="s6" data-url="slidemenu/xitong_sjzx.html">
						<span></span>
						数据中心
					</a>
					<a href="#" class="s7" data-url="slidemenu/xitong_spjx.html">
						<span></span>
						视频教学
					</a>
				</div>
			</li>

			<li class="nav_platform">
				<a href="#">
					<i></i>
					<span>平台管理</span>
				</a>
				<div class="nav_sub">
					<a href="#" class="s1" data-url="slidemenu/pingtai_sjgl.html">
						<span></span>
						商家管理
					</a>
					<a href="#" class="s2" data-url="slidemenu/pingtai_jdgl.html">
						<span></span>
						酒店管理
					</a>
					<a href="#" class="s3" data-url="slidemenu/pingtai_lygl.html">
						<span></span>
						旅游管理
					</a>
					<a href="#" class="s4" data-url="slidemenu/pingtai_qdgl.html">
						<span></span>
						渠道管理
					</a>
					<a href="#" class="s6" data-url="slidemenu/pingtai_sjzx.html">
						<span></span>
						数据中心
					</a>
				</div>
			</li>

			<li class="nav_FinancialService">
				<a href="<%=basePath%>/home.shtml">
					<i></i>
					<span>泰坦金融</span>
				</a>
				<div class="nav_sub">
					<a href="<%=basePath%>/home.shtml" class="s1" data-parent="false">
						<span></span>
						金融首页
					</a>
					<a href="<%=basePath%>/account/overview-main.shtml" class="s2" data-parent="false">
						<span></span>
						账户概览
					</a>
					<a href="" class="s3" data-parent="false">
						<span></span>
						泰坦增值宝
					</a>
					<a href="" class="s4" data-parent="false">
						<span></span>
						我的理财
					</a>
					<a href="" class="s6" data-parent="false">
						<span></span>
						我的贷款
					</a>
					<a href="" class="s7" data-url="<%=basePath %>/setting/slidemenu.shtml">
						<span></span>
						泰坦金融设置
					</a>
					<a href="<%=basePath%>/common/clickToPay.shtml" class="s8" data-parent="false">
						<span></span>
						付款页面
					</a>
					<a href="<%=basePath%>/help/index.action" class="s8" data-parent="false">
						<span></span>
						帮助中心
					</a>
				</div>
			</li>

		</ul>
		<div class="h_tools fr">
			<p>
				<span class="welcome" title="欢迎您，赖际文">欢迎您，赖际文</span>
				<i class="wire_ico"></i>
				<a href="#" class="J_copy">版本信息</a>
				<i class="wire_ico"></i>
				<a href="javascript:;" class="sign_out">
					<i class="ico"></i>
					退出
				</a>
			</p>
			<p class="link">
				<a href="#" class="mail">
					<i></i>
					<span>3</span>
				</a>
				<i class="wire_ico"></i>
				<a href="#" class="note">
					<i></i>
				</a>
				<i class="wire_ico"></i>
				<a href="#" class="school">学习中心</a>
				<i class="wire_ico"></i>
				<a href="#" class="old_version">使用旧版</a>
			</p>
		</div>

		<!-- 二级导航背景 -->
		<div class="h_sub_bg"></div>
		<!-- 二级导航背景结束 -->
	</div>
	<div class="content clearfix">
		<!-- 左侧栏开始 -->
		<div class="side_area">
			<div class="menu_container"></div>
		</div>
		<!-- 左侧栏结束 -->

		<!-- 右侧主体内容开始 -->
		<div class="main_area" id="main_area"></div>
		<!-- 右侧主体内容结束 -->
	</div>
</div>

<!-- 订单提示 -->
<div class="mail_hint" id="mail" style="display:none">

	<div class="left">
		<h2>酒店订单</h2>
		<ul>
			<li>
				<a href="#">
					紧急待确认客户
					<i>(10221)</i>
				</a>
			</li>
			<li>
				<a href="#">
					待确认客户
					<i>(101)</i>
				</a>
			</li>
			<li>
				待处理供货单
				<i>(21)</i>
			</li>
			<li>
				取消修改申请
				<i>(131)</i>
			</li>
			<li>
				收款异常订单
				<i>(1)</i>
			</li>
			<li>
				付款异常订单
				<i>(1)</i>
			</li>
		</ul>
	</div>
	<div class="right">
		<h2>旅游订单</h2>
		<ul>
			<li>
				紧急待确认客户
				<i>(10221)</i>
			</li>
			<li>
				待确认客户
				<i>(101)</i>
			</li>
			<li>
				待处理申请
				<i>(21)</i>
			</li>
			<li>
				待确认供货单
				<i>(131)</i>
			</li>
			<li>
				分销商收款异常
				<i>(1)</i>
			</li>
			<li>
				供应商收款异常
				<i>(1)</i>
			</li>
		</ul>
	</div>

</div>
<!-- 便签 -->
<div class="note_hint" id="note" style="display:none">
	<div class="n_h_t">便签条</div>
	<div class="n_h_c">
		<textarea name="" cols="" rows=""></textarea>
	</div>
</div>
<jsp:include page="/comm/static-js.jsp"></jsp:include>
<script type="text/javascript" src="<%=cssSaasPath%>/js/frame.js?v=20151010"></script>

<!-- 登录成功声音 -->
<audio id="audio_tip_4">
	<source type="audio/mpeg" src="http://hres.fangcang.com/css/saas/images/sound/tip_login_success.mp3"></source>
<source type="audio/wav" src="http://hres.fangcang.com/css/saas/images/sound/tip_login_success.ogg"></source>
</audio>

<script>
//checkbox、radio、switch初始化
F.UI.scan('.old_new_cut_hint');
//新旧版切换
//$('.J_experience').on('click',function(){
//  $('.old_new_cut_mask,.old_new_cut,.old_new_cut_top').hide();
//})
//新版提示-引导
(function(){
    var menu = $('.guidance .guidance_circle li');
    var cont = $('.guidance>ul li');    
    var index = 0;
    menu.on('click',function(){
        index = menu.index(this);
        $(this).addClass('hover').siblings().removeClass('hover');
        cont.eq(index).fadeIn().siblings().hide();
    }); 
    cont.find('.gbtn').on('click',function(){
        index++;
        menu.eq(index).addClass('hover').siblings().removeClass('hover');
        cont.eq(index).fadeIn().siblings().hide();
        if(index>=menu.length){ $('.guidance_mask,.guidance').fadeOut();}
    });
})();



$(function(){
    //登录成功
    SAAS.sound.play(4);
});
$('.J_copy').on('click',function(){
    $.ajax({
        dataType : 'html',
        context: document.body,
        url : '版权信息.html',
        success : function(html){

            var d =  dialog({
                title: ' ',
                padding: '0 0 30px 0',
                content: html,
                skin : 'saas_pop',
               
            }).showModal()
        }
    })
});

//订单提示
toolTip('.mail',{
    width : 312,
    triggerType : 'click',
    skin : 'saas_tip mail_pop',
    content : $('#mail')
});

//便签
toolTip('.note',{
    width : 190,
    padding : '10px',
    skin : 'saas_tip mail_pop ',
    triggerType : 'click',
    content : $('#note')
}); 

//切换旧版
$('.old_version').on('click',function(){
    window.top.createConfirm({
    content : '<p class="f_16">你确定要切换回使用旧版系统吗？</div>',
    okValue:'确定',
    padding : '30px 0px 50px 50px',
    ok : function(){        
    },
    cancel : function(){
    }
});
});

//欢迎使用新版提示
$('.new_edition').on('click',function(){
    window.top.createConfirm({
    content : '<p class="f_16">欢迎使用新版SAAS酒店系统。</br>请尽情体验！</div>',
    okValue:'立即体验',
    padding : '15px 0px 30px 50px',
    skin : 'saas_confirm_singlebtn ',
    ok : function(){        
    },
    cancel : false
});
});

$('.sign_out').on('click',function(){
    location.href="<%=basePath%>/j_acegi_logout?wait=y";
});


</script>
</body>
</html>