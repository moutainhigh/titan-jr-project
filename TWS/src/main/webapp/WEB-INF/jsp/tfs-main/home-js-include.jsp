<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/path-param.jsp"%>
<script type="text/javascript">
var isAdmin = '${isAdmin}';

//广告 
var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
        slidesPerView: 1,
        paginationClickable: true,
        spaceBetween: 0,
        loop: true,
        autoplay: 10000,
        autoplayDisableOnInteraction: false
    });


//回到顶部
	$(function(){
	$(window).on('scroll',function(){
		var st = $(document).scrollTop();
		if( st>100 ){			
			$('#go_top').fadeIn(function(){
				$(this).removeClass('dn');
			});
		}else{
			$('#go_top').fadeOut(function(){
				$(this).addClass('dn');
			});
		}	
	});
	$('#go_top .go').on('click',function(){
		$('html,body').animate({'scrollTop':0},500);
	});
});
 
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