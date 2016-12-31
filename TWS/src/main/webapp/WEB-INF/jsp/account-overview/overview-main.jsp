<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>

<body style="min-width: 1300px;" class="bg" >
<!-- 头部 -->
<jsp:include page="../header.jsp"></jsp:include>
<div class="h_90"></div>
<!-- 内容 -->
<div class="w_1200">
	<div class="overview clearfix">
		<div class="o_crumbs">
			账户名称/泰坦码：${organ.orgName}/${organ.titanCode}
		</div>
		<div class="MyAssets_chart">
		<div class="MyAssets_code"><img src="<%=cssWalletPath%>/images/tu09.jpg"></div>
		<div class="MyAssets_chart_list">
			<div class="MyAssets_chart_list01 fl">
				<h3>我的资产</h3>
				<h4> <i class="MyAssets_greenNotice"  id="amountSpan">加载中...</i>
					元
				</h4>
				<table cellpadding="0" cellspacing="0" class="MyAssets_chart_tab01">
					<tr>
						<td width="75" class="MyAssets_chart_td01">
							<canvas id="can1" class="canvasBox" height="60" width="60"></canvas>
							<span>100<i>%</i></span>
						</td>
						<td>
							<p>
								<span>现金可用余额：<i id="balanceusableSpan">加载中...</i></span>
								<a href="javascript:void(0)" class="blue decorationUnderline rechargeBtn">充值</a>
								<a href="javascript:void(0)" class="blue decorationUnderline withdrawBtn">提现</a>
							</p>
							<p>
								<span>
									现金冻结余额：<i id="balancefrozonSpan">加载中...</i>
									<i class="MyAssets_noticeIco" title="交易平台在线收款默认为担保支付，在订单离店日后1天款项自动解冻"></i>
								</span>
								<a href="<%=basePath%>/account/freeze-detail-page.shtml" class="blue decorationUnderline">详情</a>
							</p>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="clear"></div>
		</div>
		<div class="MyAssets_list">
		<h3 class="MyAssets_list_tab">
			<span class="on">
				交易记录
				<i></i>
				<em></em>
			</span>
			<span>
				付款记录
				<i></i>
				<em></em>
			</span>
			<span>
				收款记录
				<i></i>
				<em></em>
			</span>
			<span>
				充值记录
				<i></i>
				<em></em>
			</span>
			<span>
				提现记录
				<i></i>
				<em></em>
			</span>			
		</h3>
		<jsp:include page="trade_record.jsp"/>
		<jsp:include page="pay_record.jsp"/>
		<jsp:include page="recieve_record.jsp"/>
		<jsp:include page="recharge_record.jsp"/>
		<jsp:include page="withdraw_record.jsp"/>
	
		</div>
	</div>
	
	<div class="main_kkpager">		
		<div id="kkpager" class="page_turning"></div>
	</div>

	</div>
</div>
<div class="h_40"></div>
<!-- 版权 -->
<div class="footer1">
	<div class="f_bd">
		<div class="fl">
			Copyright © 2012-2016, fangcang.com. All Rights Reserved 泰坦云 版权所有 粤ICP备13046150号	
		</div>
		<div class="fl f_bd_r">
		<script charset="utf-8" type="text/javascript" src="http://szcert.ebs.org.cn/govicon.js?id=78ccac39-a97a-452c-9f81-162cd840cff6&amp;width=130&amp;height=50&amp;type=2" id="ebsgovicon"></script>
		</div>
	</div>	
</div>

<script type="text/javascript">  
//我的账户
/* $('.hr_login').hover(function(){
	$(this).find('.hrl_ul').removeClass('dn');
	$(this).find('.hrl_hover').addClass('l_red');
},function(){
	$(this).find('.hrl_ul').addClass('dn');
	$(this).find('.hrl_hover').removeClass('l_red');
})

	//init
	$(function(){
	var page1,page2,page3,page4,page5;
	var tabIdx=1;	
	function tabChange(tabbtn, tabpannel, tabclass) {
	    var $div_li = tabbtn;
	    $div_li.click(function() {
	        $(this).addClass(tabclass).siblings().removeClass(tabclass);
	        var index = $div_li.index(this);
	        tabIdx=index+1;
	        switch(tabIdx){
        		case 1:        		
        		if(page1){
        			pageGo(page1);
        		}else{
        			pageGo();
        		}
        		break;
        		case 2:
        		if(page2){
        			pageGo(page2);
        		}else{
        			pageGo();
        		}
        		break;
        		case 3:
        		if(page3){
        			pageGo(page3);
        		}else{
        			pageGo();
        		}
        		break;
        		case 4:
        		if(page4){
        			pageGo(page4);
        		}else{
        			pageGo();
        		}
        		break;
        		case 5:
        		if(page5){
        			pageGo(page5);
        		}else{
        			pageGo();
        		}
        		break;
        	}
	        $(tabpannel).eq(index).show().siblings().hide();
	    });
	}
	tabChange($(".MyAssets_list_tab span"), $(".MyAssets_tab"), "on");
	function pageGo(pageIdx){
		pageIdx=pageIdx || 1;	
		var totalPage = 20;
	    var totalRecords = 390;
	    var pageNo = 1;
	    //生成分页
	    //有些参数是可选的，比如lang，若不传有默认值
	    new Pager({
	        pno : pageIdx,
	        //总页码
	        total : totalPage,
	        //总数据条数
	        totalRecords : totalRecords,
	        isShowTotalRecords :true,
			isGoPage : true,
	        mode : 'click',//默认值是link，可选link或者click
			
	        click : function(n){
	        	F.loading.show();

	            setTimeout(function(){
	                //隐藏loding
	                F.loading.hide();

	            },500);
	            //手动选中按钮
	            this.selectPage(n);
	        	if(tabIdx){
	        		switch(tabIdx){
	        			case 1:
		        		page1=n;
		        		break;
		        		case 2:
		        		page2=n;
		        		break;
		        		case 3:
		        		page3=n;
		        		break;
		        		case 4:
		        		page4=n;
		        		break;
		        		case 5:
		        		page5=n;
		        		break;
		        	}
	        	}      	
	            // do something
	            //显示loading
	           
	            return false;
	        }
        /*
	        ,lang               : {
	            firstPageText           : '首页',
	            firstPageTipText        : '首页',
	            lastPageText            : '尾页',
	            lastPageTipText         : '尾页',
	            prePageText             : '上一页',
	            prePageTipText          : '上一页',
	            nextPageText            : '下一页',
	            nextPageTipText         : '下一页',
	            totalPageBeforeText     : '共',
	            totalPageAfterText      : '页',
	            currPageBeforeText      : '当前第',
	            currPageAfterText       : '页',
	            totalInfoSplitStr       : '/',
	            totalRecordsBeforeText  : '共',
	            totalRecordsAfterText   : '条数据',
	            gopageBeforeText        : '&nbsp;转到',
	            gopageButtonOkText      : '确定',
	            gopageAfterText         : '页',
	            buttonTipBeforeText     : '第',
	            buttonTipAfterText      : '页'
	        }
	    });	
	}
    pageGo();
    var pageList=$(".main_kkpager").html();
  
	});
	
    //进度图
        function scale(arg){            
            var opts={
                bgcolor:"#fcfbf7",
                _width:"60",
                _height:"60",
                id:"",
                numb:"60"
            };      
            for(var i in arg){
                for(var a in opts){
                    if(i==a){                   
                        opts[a]=arg[i];                     
                    }
                }
            };      
            if(opts.id==""){
                return false;
            }   

            var canvas=document.getElementById(opts.id); 
            var context=canvas.getContext("2d");   
            this.init(canvas,opts,context);                 
        };
        scale.prototype={
            init:function(canvas,opts,context){
                this.bgDraw(opts,context);
                this.numb(canvas,opts);
            },
            numb:function(canvas,opts){
                var i=0;
                var num=canvas.parentNode.getElementsByTagName("span")[0];                    
                var interval = setInterval(function () {
                    num.innerHTML=i+"<i>%</i>";                             
                     if(i<opts.numb){
                         i++;
                     }else{
                        clearInterval(interval);
                     };                               
                }, 10);

            }
            ,bgDraw:function(opts,context){
                context.beginPath();
                context.arc(30, 30, 30, 0, Math.PI * 2, true);
                context.closePath();
                context.fillStyle="rgb(252,251,247)";
                context.fill();
                var g1 = context.createLinearGradient(0, 0, 0, 300);
                g1.addColorStop(0, 'rgb(203,204,205)');   
                g1.addColorStop(0.5, 'rgb(229,229,230)');
                g1.addColorStop(1, 'rgb(203,204,205)'); 
                var g2 = context.createLinearGradient(0,0, 0,140);
                g2.addColorStop(0, 'rgb(255,113,43)');   
                g2.addColorStop(0.5, 'rgb(255,113,43)');
                g2.addColorStop(1, 'rgb(255,113,43)'); 

                context.beginPath();             
                context.arc(30,30,27.5,0,360,false);        
                context.lineWidth=5;
                context.strokeStyle=g1;                
                context.stroke();//画空心圆
                context.closePath();
                var i=0;
                var interval = setInterval(function () {
                     //每次转换平铺类型清空                   
                     if(i<opts.numb){
                         i++;
                     }else{
                        clearInterval(interval);
                     };
                     var rote=Math.PI*2*i*0.01;
                     context.save();
                     context.beginPath();
                     context.arc(30,30,27.5,-0.5*Math.PI/2,-0.5*Math.PI/2+rote,false);
                     context.lineWidth=4;
                     context.strokeStyle=g2;
                     context.stroke();//画空心圆
                     context.closePath();                    
                }, 10);
            }           
        }; 
        F.UI.scan();   
        window.onload=function(){
             var canvas1=new scale({id:"can1",numb:50}); 
        }          
       	
		$('.J_Section').each(function(){
		//添加日期锻
		new DateSection('#' + $(this).attr('id'), {minDate : '',maxDate : ''});
		});
                
        //导出提示
        $(".J_export").on('click', function() {
        	top.F.loading.show();        
            setTimeout(function(){
				top.F.loading.hide();
				new top.Tip({msg : '导出成功!', type: 1 , timer:1500});    
			},1500); 
        });    

        //备注
        $(".J_remark").on('click',function(){
        	$.ajax({
		        dataType : 'html',		      
		        context: document.body,
		        url : '备注.html',
		        success : function(html){
		        	 var d = window.top.dialog({
				        title: '备注',
				        padding: '0 0 0px 0 ',
				        content: html,
				        skin : 'overview_pop',
				        button : [ 
                        {
                            value: '保存',
                            skin : 'btn btn_save',
                            callback: function () {
                               return false;
                            },
                            autofocus: true
                        },
                        {
                            value: '取消',
                            skin : 'btn btn_g',
                            callback: function () {
                               alert('c');
                            }
                        }
                    ]     
				    }).showModal();		        	 
		        }
		    });
        }); */
        
        
        
        
        $(function(){
			tabChange($(".MyAssets_list_tab span"), $(".MyAssets_tab"), "on");
			initRequest();
			initPageSizeChangeEvent();
			initQueryDate();
			initAutoSelectPartner();
			validate_BankCard_Satatus();
		});
        
        
      //初始化页面请求
		function initRequest() {
			var size1 = $(".pagination_r .on").text();
			F.loading.show();
			$.ajax({
				dataType : 'html',
				context: document.body,
				data:{currentPage:1,pageSize:size1,tradeTypeId:"0"},
				url : '<%=basePath%>/account/query-org-page.shtml',
				success : function(html){
					$("#id_1").empty();
					$("#id_1").html(html);
					var total1 = $("#id_1 #tradePageTotal").val();
					var page1 = $("#id_1 #tradePageCurrent").val();
					pageGo(page1,total1,size1,1);
				},
				complete:function()
				{
					F.loading.hide();
				}
			});
		}
        
		
        function validate_BankCard_Satatus(){
			$.ajax({
				dataType:"json",
				url:"<%=basePath%>/account/validate_person_Enterprise.shtml",
				success: function (data) {
					if(data.msg=="5"){
						//修改银行卡
						bind_card_fail();
					}else if(data.msg=="6"){//审核中该如何解决
						$(".withdrawBtn").text('提现卡审核中···').removeClass('blue decorationUnderline').css("color","#999");
					}
				}
			});
			
		}
        
        //充值
        $('.rechargeBtn').on('click',function(){
                window.top.createIframeDialog({
                	  url : '<%=basePath%>/account/goto_cashierDesk.shtml?payType=7',
                });
                return false;
        });
        
        
    	window.onload = function () {
			loadAccountBalance();
		};
    	var errorIndex = 1;
		function loadAccountBalance()
		{
			$.ajax({
        		dataType : 'json',		      
		        url : '<%=basePath%>/account/query-account-balance.shtml?date=' + new Date().getTime() ,
		        success:function(data){
		        	if(data)
		        	{
		        		$('#amountSpan').text(formatCurrency(data.amount/100));
		        		$('#balanceusableSpan').text(formatCurrency(data.balanceusable/100));
		        		$('#balancefrozonSpan').text(formatCurrency(data.balancefrozon/100));
		        	}
		        	var canvas1 = new scale({id: "can1", numb: 100});
		        },
		        error: function()
		        {
		        	if(++errorIndex <= 3)
		        	{
		        		loadAccountBalance();	
		        	}
		        }
        	});
		}

</script>
</body>
</html>
