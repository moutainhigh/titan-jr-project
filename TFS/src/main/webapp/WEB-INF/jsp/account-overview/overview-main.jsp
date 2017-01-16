<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
<body>
	<h3 class="MyAssets_top">账户名称/泰坦码：${organ.orgName}/${organ.titanCode }</h3>
	<div class="MyAssets_chart">
		<div class="MyAssets_code">
			<img src="<%=cssSaasPath%>/images/TFS/MyAssets_code.jpg"></div>
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
			
			<div class="MyAssets_chart_list01 fr" id="loanAccountZone" style="display: none;">
				<h3>我的负债</h3>
				<h4>
					<i class="MyAssets_redNotice"><aa id="loanAmount">0.00</aa></i>
					元
				</h4>
				<table cellpadding="0" cellspacing="0" class="MyAssets_chart_tab01">
					<tr>
						<td width="75" class="MyAssets_chart_td01">
							<canvas id="can4" class="canvasBox"  height="60" width="60"></canvas>
							<span></span>
						</td>
						<td>
							<div class="fl" >
							<p style="line-height: 24px;">
								<span>运营贷总欠款：<a id="OPERACTION">0.00</a></span>															
							</p>
							<p class="c_999 " style="line-height: 24px;">
								<span class="f_12">
								<i class="fl">参考待还本息：<a id="OPERACTION_SUM">0.00</a></i> <i class="MyAssets_noticeIco m_t3 fl" title="参考待还本息为定时更新贷款订单应还本息之和，实际还款本息以还款时显示应还本息为准。"></i>
								</span>
							</p>	
							</div>
							<a href="<%=basePath %>/loan/credit/checkCreditStatus.shtml#zkback" class="blue decorationUnderline fl m_t15">还款</a>
						</td>
					</tr>
					<tr>
						<td width="75" class="MyAssets_chart_td01">
							<canvas id="can5" class="canvasBox"  height="60" width="60"></canvas>
							<span></span>
						</td>
						<td>
							<div class="fl" >
							<p style="line-height: 24px;">
								<span>包房专项贷款：<a id="ROOM_PACK">0.00</a></span>
							</p>
							<p class="c_999 " style="line-height: 24px;">
								<span class="f_12">
								<i class="fl">参考待还本息：<a id="ROOM_PACK_SUM">0.00</a></i> <i class="MyAssets_noticeIco m_t3 fl" title="参考待还本息为定时更新贷款订单应还本息之和，实际还款本息以还款时显示应还本息为准。"></i>
								</span>								
							</p>
							</div>	
							<a href="<%=basePath %>/loan/credit/checkCreditStatus.shtml#zkback" class="blue decorationUnderline fl m_t15">还款</a>
						
						</td>
					</tr>
				</table>
			</div>
			
		</div>
		<div class="clear"></div>
	</div>

	<div class="MyAssets_list">
		<h3 class="MyAssets_list_tab">
			<span class="on" id="1">
				交易记录
				<i></i>
			</span>
			<span id="2">
				付款记录
				<i></i>
			</span>
			<span id="3">
				收款记录
				<i></i>
			</span>
			<span id="4">
				充值记录
				<i></i>
			</span>
			<span id="5">
				提现记录
				<i></i>
			</span>			
		</h3>
		<div>
		<div class="MyAssets_tab" style="">
		<div class="MyAssets_list_Options ">

			<div class="J_Section" id="date_select_1">
				<dl class="J_date_list ">
					<dd>
						<label for=""></label>
						<input type="text" id="startDate_1"  class="text w_200 text_calendar fl">
						<label for="" class="S_digit">至</label>
						<input type="text" id="endDate_1" name="" class="text w_200 text_calendar fl" >
					</dd>
				</dl>
			</div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" id="partner_1" placeholder="交易对方："></div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" id="amount_1" placeholder="订单金额："></div>
			<a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)" onclick="queryTransOrders(1)" >&nbsp;</a>
			<a href="javascript:void(0)" class="MyAssets_Export fr bacth_export_hotel J_export" onclick="exportExcel(1)">
				<img src="<%=cssSaasPath%>/images/TFS/tfs_c01.png" ></a>
		</div>		
		<div style="margin-right:17px;margin-left: 17px; margin-bottom: 10px;" class="label">
				<table width="100%" cellspacing="0" border="0">
				<colgroup>					
					<col width="10%">
					<col width="40">
					<col width="9%">
					<col width="20%">
					<col width="20%">
					<col width="8%">
					<col width="40">
					<col width="8%">
					<col width="40">
					<col width="8%">
					<col width="">
				</colgroup>
				<tbody>
				<tr>					
					<td class="tdl">交易时间</td>
					<td width="tdr"></td>
					<td class="tdl">交易类型</td>
					<td class="tdl">交易内容</td>
					<td class="tdl">交易对方</td>
					<td class="tdr">订单金额</td>
					<td class="tdl"></td>
					<td class="tdr">手续费</td>
					<td class="tdl"></td>
					<td class="tdl">交易结果</td>					
					<td class="tdl">操作</td>		
				</tr>
			</tbody></table>
		</div>
		<div class="MyAssets_listContent">
			<table cellpadding="0" cellspacing="0" width="100%" class="MyAssets_listTable">
				<colgroup>					
					<col width="10%">
					<col width="40">
					<col width="9%">
					<col width="20%">
					<col width="20%">
					<col width="8%">
					<col width="40">
					<col width="8%">
					<col width="40">
					<col width="8%">
					<col width="">
				</colgroup>
				<tbody id="id_1">
				</tbody>
			</table>
		</div>
		</div>			

		<div class="MyAssets_tab" style="display: none;">
		<div class="MyAssets_list_Options ">

			<div class="J_Section" id="date_select_2">
				<dl class="J_date_list ">
					<dd>
						<label for=""></label>
						<input type="text" id="startDate_2" class="text w_200 text_calendar fl">
						<label for="" class="S_digit">至</label>
						<input type="text" id="endDate_2" name="" class="text w_200 text_calendar fl" >
					</dd>
				</dl>
			</div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" id="partner_2" placeholder="交易对方："></div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" id="amount_2" placeholder="订单金额："></div>
			<a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)" onclick="queryTransOrders(2)" >&nbsp;</a>
			<a href="javascript:void(0)" class="MyAssets_Export fr bacth_export_hotel J_export" onclick="exportExcel(2)">
				<img src="<%=cssSaasPath%>/images/TFS/tfs_c01.png"></a>
		</div>		
		<div style="margin-right:17px;margin-left: 17px; margin-bottom: 10px;" class="label">
				<table width="100%" cellspacing="0" border="0">
				<colgroup>					
					<col width="10%">
					<col width="40">
					<col width="9%">
					<col width="20%">
					<col width="20%">
					<col width="8%">
					<col width="40">
					<col width="8%">
					<col width="40">
					<col width="8%">
					<col width="">
				</colgroup>
				<tbody>
				<tr>					
					<td class="tdl">交易时间</td>
					<td width="tdr"></td>
					<td class="tdl">交易类型</td>
					<td class="tdl">交易内容</td>
					<td class="tdl">收款方</td>
					<td class="tdr">订单金额</td>
					<td class="tdl"></td>
					<td class="tdr">手续费</td>
					<td class="tdl"></td>
					<td class="tdl">交易结果</td>					
					<td class="tdl">操作</td>		
				</tr>
			</tbody></table>
		</div>
		<div class="MyAssets_listContent">
			<table cellpadding="0" cellspacing="0" width="100%" class="MyAssets_listTable">
				<colgroup>					
					<col width="10%">
					<col width="40">
					<col width="9%">
					<col width="20%">
					<col width="20%">
					<col width="8%">
					<col width="40">
					<col width="8%">
					<col width="40">
					<col width="8%">
					<col width="">
				</colgroup>
				<tbody id="id_2"></tbody>

			</table>
		</div>
		</div>

		<div class="MyAssets_tab" style="display: none;">
		<div class="MyAssets_list_Options ">

			<div class="J_Section" id="date_select_3">
				<dl class="J_date_list ">
					<dd>
						<label for=""></label>
						<input type="text" id="startDate_3" class="text w_200 text_calendar fl">
						<label for="" class="S_digit">至</label>
						<input type="text" id="endDate_3"  name="" class="text w_200 text_calendar fl" >
					</dd>
				</dl>
			</div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" id="partner_3" placeholder="交易对方："></div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" id="amount_3" placeholder="订单金额："></div>
			<a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)" onclick="queryTransOrders(3)">&nbsp;</a>
			<a href="javascript:void(0)" class="MyAssets_Export fr bacth_export_hotel J_export" onclick="exportExcel(3)">
				<img src="<%=cssSaasPath%>/images/TFS/tfs_c01.png"></a>
		</div>		
		<div style="margin-right:17px;margin-left: 17px; margin-bottom: 10px;" class="label">
				<table width="100%" cellspacing="0" border="0">
				<colgroup>					
					<col width="10%">
					<col width="40">
					<col width="9%">
					<col width="20%">
					<col width="20%">
					<col width="8%">
					<col width="40">
					<col width="8%">
					<col width="40">
					<col width="8%">
					<col width="">
				</colgroup>
				<tbody>
				<tr>					
					<td class="tdl">交易时间</td>
					<td width="tdr"></td>
					<td class="tdl">交易类型</td>
					<td class="tdl">交易内容</td>
					<td class="tdl">付款方</td>
					<td class="tdr">订单金额</td>
					<td class="tdl"></td>
					<td class="tdr">手续费</td>
					<td class="tdl"></td>
					<td class="tdl">交易结果</td>					
					<td class="tdl">操作</td>		
				</tr>
			</tbody></table>
		</div>
		<div class="MyAssets_listContent">
			<table cellpadding="0" cellspacing="0" width="100%" class="MyAssets_listTable">
				<colgroup>					
					<col width="10%">
					<col width="40">
					<col width="9%">
					<col width="20%">
					<col width="20%">
					<col width="8%">
					<col width="40">
					<col width="8%">
					<col width="40">
					<col width="8%">
					<col width="">
				</colgroup>
				<tbody id="id_3"></tbody>
			</table>
		</div>
		</div>		

		<div class="MyAssets_tab" style="display: none;">
		<div class="MyAssets_list_Options ">

			<div class="J_Section" id="date_select_4">
				<dl class="J_date_list ">
					<dd>
						<label for=""></label>
						<input type="text" id="startDate_4"  class="text w_200 text_calendar fl">
						<label for="" class="S_digit">至</label>
						<input type="text" id="endDate_4"  name="" class="text w_200 text_calendar fl" >
					</dd>
				</dl>
			</div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" id="operator_4" placeholder="操作人：" value=""></div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" id="amount_4" placeholder="充值金额："></div>
			<a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)" onclick="queryTransOrders(4)">&nbsp;</a>
			<a href="javascript:void(0)" class="MyAssets_Export fr bacth_export_hotel J_export" onclick="exportExcel(4)">
				<img src="<%=cssSaasPath%>/images/TFS/tfs_c01.png"></a>
		</div>		
		<div style="margin-right:17px;margin-left: 17px; margin-bottom: 10px;" class="label">
				<table width="100%" cellspacing="0" border="0">
				<colgroup>					
					<col width="10%">
					<col width="40">
					<col width="9%">
					<col width="20%">
					<col width="20%">
					<col width="8%">
					<col width="40">
					<col width="8%">
					<col width="40">
					<col width="8%">
					<col width="">
				</colgroup>
				<tbody>
				<tr>					
					<td class="tdl">交易时间</td>
					<td width="tdr"></td>
					<td class="tdl">交易类型</td>
					<td class="tdl">交易内容</td>
					<td class="tdl">收款账户</td>
					<td class="tdr">充值金额</td>
					<td class="tdl"></td>
					<td class="tdr">手续费</td>
					<td class="tdl"></td>
					<td class="tdl">交易结果</td>					
					<td class="tdl">操作</td>		
				</tr>
			</tbody></table>
		</div>
		<div class="MyAssets_listContent">
			<table cellpadding="0" cellspacing="0" width="100%" class="MyAssets_listTable">
				<colgroup>					
					<col width="10%">
					<col width="40">
					<col width="9%">
					<col width="20%">
					<col width="20%">
					<col width="8%">
					<col width="40">
					<col width="8%">
					<col width="40">
					<col width="8%">
					<col width="">
				</colgroup>
				<tbody id="id_4"></tbody>
			</table>
		</div>
		</div>

		<div class="MyAssets_tab" style="display: none;">
		<div class="MyAssets_list_Options ">

			<div class="J_Section" id="date_select_5">
				<dl class="J_date_list ">
					<dd>
						<label for=""></label>
						<input type="text" id="startDate_5" class="text w_200 text_calendar fl">
						<label for="" class="S_digit">至</label>
						<input type="text" id="endDate_5" name="" class="text w_200 text_calendar fl" >
					</dd>
				</dl>
			</div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" id="operator_5" placeholder="操作人："></div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" id="amount_5" placeholder="提现金额："></div>
			<a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)" onclick="queryTransOrders(5)">&nbsp;</a>
			<a href="javascript:void(0)" class="MyAssets_Export fr bacth_export_hotel J_export" onclick="exportExcel(5)" >
				<img src="<%=cssSaasPath%>/images/TFS/tfs_c01.png"></a>
		</div>		
		<div style="margin-right:17px;margin-left: 17px; margin-bottom: 10px;" class="label">
				<table width="100%" cellspacing="0" border="0">
				<colgroup>					
					<col width="10%">
					<col width="40">
					<col width="9%">
					<col width="20%">
					<col width="20%">
					<col width="8%">
					<col width="40">
					<col width="8%">
					<col width="40">
					<col width="8%">
					<col width="">
				</colgroup>
				<tbody>
				<tr>					
					<td class="tdl">交易时间</td>
					<td width="tdr"></td>
					<td class="tdl">交易类型</td>
					<td class="tdl">交易内容</td>
					<td class="tdl">付款账户</td>
					<td class="tdr">提现金额</td>
					<td class="tdl"></td>
					<td class="tdr">手续费</td>
					<td class="tdl"></td>
					<td class="tdl">交易结果</td>					
					<td class="tdl">操作</td>		
				</tr>
			</tbody></table>
		</div>
		<div class="MyAssets_listContent">
			<table cellpadding="0" cellspacing="0" width="100%" class="MyAssets_listTable">
				<colgroup>					
					<col width="10%">
					<col width="40">
					<col width="9%">
					<col width="20%">
					<col width="20%">
					<col width="8%">
					<col width="40">
					<col width="8%">
					<col width="40">
					<col width="8%">
					<col width="">
				</colgroup>
				<tbody id="id_5"></tbody>
			</table>
		</div>
		</div>
	
		</div>

	</div>
	<div style="height:50px;"></div>
	<div class="main_kkpager">
		<div class="pagination1">
			<div class="pagination_r">
				每页显示酒店数量
				<i class="on">5</i>
				<i>10</i>
				<i>15</i>
				<i>20</i>
			</div>
		</div>
		<div id="kkpager" class="page_turning"></div>
	</div>

	<script type="text/javascript">
		//当前页码
		var page1,page2,page3,page4,page5;
		//总记录数
		var total1,total2,total3,total4,total5;
		//每页数量
		var size1 = 5,size2 = 5,size3 = 5,size4 = 5,size5 = 5;

		$(function(){
			tabChange($(".MyAssets_list_tab span"), $(".MyAssets_tab"), "on");
			initRequest();
			initPageSizeChangeEvent();
			initQueryDate();
			initAutoSelectPartner();
			validate_BankCard_Satatus();
		});

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

        $('.withdrawBtn').on('click',function(){
        	$.ajax({
        		dataType : 'json',		      
		        url : '<%=basePath%>/account/validate_person_Enterprise.shtml',
		        success:function(data){
		        	if(data.result=="success"){
		        		if(data.msg=="3"){//对公且未绑定
		        			bind_card_public($(this));
		        		}else if(data.msg=="2"|| data.msg=="4"){//对私或者对公已绑定成功
		        			account_withdraw();
		        		}else if(data.msg=="5"){//对公，且绑定失败
		        			 bind_card_fail();
		        		}else if(data.msg=="6"){
		        			bank_card_binding();
		        		}else{
		        			 new top.Tip({msg: "系统错误", type: 1, time: 1000});
		        		}
		        	}else{
		        		 new top.Tip({msg: "系统错误", type: 1, time: 1000});
		        	}
		        }
        	});
        	
        	
        });

        function account_withdraw(){
      	  window.top.createIframeDialog({
                url : '<%=basePath%>/account/goto_cashierDesk.shtml?payType=8',
					close:function () {
						/* ALERT("==="); */
					}
            });
        }
        
        function bind_card_fail(){
        	new top.createConfirm({
			    title:'提示',
				padding: '20px 20px 40px',
				width:400,
				cancelValue : '下次再说',
		        okValue : '修改提现卡信息',		
		        content : '<div class="l_h26" style="padding-left: 30px;"><i class="mr_ico"></i><span class="TFS_mrtips"><strong class="c_tfscolor f_16">对不起,提现卡绑定失败</strong>失败原因：银行卡信息或持卡人姓名不正确不正正宗确。银行卡信息或持卡人。</span></div>',
				ok : function(){	
					$.ajax({
				        dataType : 'html',		      
				        context: document.body,
				        data:{
				        	orgName:'${organ.orgName}'
				        },
				        url : '<%=basePath%>/account/update_account-withdraw_info.shtml',
				        success : function(html){
				        	var d = window.top.dialog({
						        title: ' ',
						        padding: '0 0 0px 0 ',
						        content: html,
						        skin : 'saas_pop',
						    }).showModal();		
							//点击关闭
							window.parent.$(".J_finsh").on('click', function() {
								d.remove();
									$(".withdrawBtn").text('提现卡审核中···').removeClass('blue decorationUnderline').css("color","#999");
								});  
							window.parent.$(".J_finsh_close").on('click', function() {
								d.remove();
									$(".withdrawBtn").text('提现卡审核失败···').removeClass('blue decorationUnderline').css("color","#999");
								});  
						}

				    });
		        },
		        cancel : function(){
		          	$(".withdrawBtn").text('提现卡绑定失败').removeClass('blue').addClass('MyAssets_red');
		        }
		      });
        
        }
        
        function bank_card_binding(){
        	$.ajax({
        		dataType : 'json',		      
		        url : '<%=basePath%>/account/checkBindAccountWithDrawCard.shtml',
		        success : function(data){
		        	if(data.result=="success"){
		        		if(data.msg=="2"|| data.msg=="4"){//对私或者对公已绑定成功
		        			account_withdraw();
		        		}else if(data.msg=="5"){//对公，且绑定失败
		        			 bind_card_fail();
		        		}else if(data.msg=="6"){
		        			new top.createConfirm({
		                		title:'提示',
		        				padding: '20px 20px 40px',
		        				width:400,
		        				okValue:'关闭',
		        				skin:'saas_confirm_singlebtn',
		        		        content : '<div class="l_h26" style="padding-left: 30px;"><i class="mr_ico"></i><span class="TFS_mrtips"><strong class="c_tfscolor f_16">对不起,提现卡绑定审核中</strong>请您稍后查看,我们会在24小时之内审核您的提现卡。</span></div>',
		        		        cancel : false
		        		      });
		                	window.top.$(".ui-dialog-close").hide();
		        		}else{
		        			 new top.Tip({msg: "系统错误", type: 1, time: 1000});
		        		}
		        	}
		        },
		        error:function(data){
		        	console.log(data);
		        	alert("失败");
		        }
        	})
        }        
        
        
        function bind_card_public(this_Object){
        	var _this=this_Object;
        	$.ajax({
		        dataType : 'html',		      
		        context: document.body,
		        url : '<%=basePath%>/account/toBindAccountWithDrawCard.shtml',
		        success : function(html){
		        	var d = window.top.dialog({
				        title: ' ',
				        padding: '0 0 0px 0 ',
				        content: html,
				        skin : 'saas_pop',
				    }).showModal();		
					//点击关闭
					window.parent.$(".J_finsh").on('click', function() {
						d.remove();
						_this.text('提现卡审核中···').removeClass('blue decorationUnderline').css("color","#999");
					}); 
					window.parent.$(".J_finsh_close").on('click', function() {
						d.remove();
							 $(".withdrawBtn").text('提现卡审核失败···').removeClass('blue decorationUnderline').css("color","#999"); 
						});  
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
        
        
        //导出提示
//        $(".J_export").on('click', function() {
//        	top.F.loading.show();
//            setTimeout(function(){
//				top.F.loading.hide();
//				new top.Tip({msg : '导出成功!', type: 1 , time:1500});
//			},1500);
//        });

		//按条件查询
		function queryTransOrders(index) {
			var tradeType = "" + (index - 1);
			var data = getQueryOrderData(index);
			var size = $(".pagination_r .on").text();
			F.loading.show();
			$.ajax({
				dataType: 'html',
				context: document.body,
				data: {
					currentPage: 1,
					pageSize: size,
					tradeTypeId: tradeType,
					admissionName: data.admissionName,
					startTimeStr: data.startTimeStr,
					endTimeStr: data.endTimeStr,
					orderAmount: data.orderAmount,
					orderOperator:data.orderOperator,
				},
				url: '<%=basePath%>/account/query-org-page.shtml',
				success: function (html) {
					$("#id_" + index).empty();
					$("#id_" + index).html(html);
					var total = $("#id_" + index + " #tradePageTotal").val();
					var page = $("#id_" + index + " #tradePageCurrent").val();
					pageGo(page, total, size,1);
					F.loading.hide();
				}
			});
		}

		//添加日期锻
		function initQueryDate() {
			var current = new Date();
			current.setMonth(current.getMonth() - 3);//查询三个月的数据
			var dateStr = current.getFullYear() + "-" + (current.getMonth() + 1) + "-" + current.getDate();
			new DateSection('#date_select_1',{minDate:dateStr,maxDate:''});
			new DateSection('#date_select_2',{minDate:dateStr,maxDate:''});
			new DateSection('#date_select_3',{minDate:dateStr,maxDate:''});
			new DateSection('#date_select_4',{minDate:dateStr,maxDate:''});
			new DateSection('#date_select_5',{minDate:dateStr,maxDate:''});
		}

		var partner_dataSource=null;
		//初始化联想查询框
		function initAutoSelectPartner() {
			$.ajax({
        		dataType : 'json',		      
		        url : '<%=basePath%>/account/getOrgList.shtml?date=' + new Date().getTime(),
		        success:function(data){
		        	
		        	if(data && data.organDTOList)
		        	{
		        		partner_dataSource = data;
		        		var dataArr = new Array();
		        		for(i = 0 ; i <data.organDTOList.length;i++)
		        		{
		        			dataArr[dataArr.length] = {key:data.organDTOList[i].userId , val:data.organDTOList[i].orgName};
		        		}
		        		
		        		new AutoComplete($('#partner_1'), {
		        			data : dataArr,
							key : 'userId',  //数据源中，做为key的字段
							val : 'orgName', //数据源中，做为val的字段
							width : 240,
							height : 300,
							autoSelectVal : true,
							clickEvent : function(d, input){
								input.attr('data-id', d.key);
							}
						});

						new AutoComplete($('#partner_2'), {
							data : dataArr,
							key : 'userId',  //数据源中，做为key的字段
							val : 'orgName', //数据源中，做为val的字段
							width : 240,
							height : 300,
							autoSelectVal : true,
							clickEvent : function(d, input){
								input.attr('data-id', d.key);
							}
						});

						new AutoComplete($('#partner_3'), {
							data : dataArr,
							key : 'userId',  //数据源中，做为key的字段
							val : 'orgName', //数据源中，做为val的字段
							width : 240,
							height : 300,
							autoSelectVal : true,
							clickEvent : function(d, input){
								input.attr('data-id', d.key);
							}
						});
		        	}
		        	
		    		
		        }
        	});
		}

		//页签切换请求
		function tabChangeRequest(page, total, size, index) {
			if (!page) {
				page = 1;
			}
			resetPageSizeView(size);
			var data = getQueryOrderData(index);
			F.loading.show();
			$.ajax({
				dataType: 'html',
				context: document.body,
				data: {
					currentPage: page,
					pageSize: size,
					tradeTypeId: index - 1,
					admissionName: data.admissionName,
					startTimeStr: data.startTimeStr,
					endTimeStr: data.endTimeStr,
					orderAmount: data.orderAmount,
					orderOperator:data.orderOperator,
				},
				url: '<%=basePath%>/account/query-org-page.shtml',
				success: function (html) {
					$("#id_" + index).empty();
					$("#id_" + index).html(html);
					var total = $("#id_" + index + " #tradePageTotal").val();
					pageGo(page, total, size,index);
					
				},
				complete:function()
				{
					F.loading.hide();
				}
			});
		}

		//点击分页页码时调用
		function pageNoChangeRequest(page, size, index) {
			F.loading.show();
			var data = getQueryOrderData(index);
			$.ajax({
				dataType : 'html',
				context: document.body,
				data:{
					currentPage:page,
					pageSize:size,
					tradeTypeId:index-1,
					admissionName: data.admissionName,
					startTimeStr: data.startTimeStr,
					endTimeStr: data.endTimeStr,
					orderAmount: data.orderAmount,
					orderOperator:data.orderOperator	
				},
				url : '<%=basePath%>/account/query-org-page.shtml',
				success : function(html){
					$("#id_" + index).empty();
					$("#id_" + index).html(html);
					var total4 = $("#id_" + index +" #tradePageTotal").val();
				},
				complete:function()
				{
					F.loading.hide();
				}
			});
		}

		//切换每页数量时调用
		function pageSizeChangeRequest(page, size, index) {
			F.loading.show();
			var data = getQueryOrderData(index);
			$.ajax({
				dataType : 'html',
				context: document.body,
				data:{
					currentPage:page,
					pageSize:size,
					tradeTypeId:index-1,
					admissionName: data.admissionName,
					startTimeStr: data.startTimeStr,
					endTimeStr: data.endTimeStr,
					orderAmount: data.orderAmount,
					orderOperator:data.orderOperator	
				},
				url : '<%=basePath%>/account/query-org-page.shtml',
				success : function(html){
					$("#id_" + index).empty();
					$("#id_" + index).html(html);
					var total = $("#id_" + index + " #tradePageTotal").val();
					pageGo(page,total,size,index);
					
				},
				complete:function()
				{
					F.loading.hide();
				}
			});
		}

		function validate_partner(partnerName){
			for(var i = 0 ; i <partner_dataSource.organDTOList.length;i++)
    		{
    			if(partnerName == partner_dataSource.organDTOList[i].orgName){
    				return true;
    			}
    		}
			return false;
		}
		
		function getQueryOrderData(index){
			var startDate = $("#startDate_" + index ).attr('data-prev');
			var endDate = $("#endDate_" + index ).attr('data-prev');
			var amount = $("#amount_" + index ).val();
			var partner = "";
			if (index == 1 || index == 2 || index == 3){
				var partnerName = $("#partner_" + index ).val();
				if(partnerName.length>0){
					if(validate_partner(partnerName)==true){
						partner = $("#partner_" + index ).attr('data-id');
					}else{//随便输入的账户，
						partner="lksdjlsk12";
					}
				}
			}
			var operator = "";
			if (index == 4 || index == 5){
				operator = $("#operator_" + index).val();
			}
			return {
				admissionName: partner,
				startTimeStr: startDate,
				endTimeStr: endDate,
				orderAmount: amount,
				orderOperator:operator
			};
			
		}
		
		//修改样式
		function resetPageSizeView(size){
			$(".pagination_r i").each(function () {
				if ($(this).text() == size) {
					$(this).addClass("on");
				} else {
					$(this).removeClass("on");
				}
			});
		}

		//分页内页面跳转方法
		function pageGo(pageIdx, totals, size,tabIdx){
			pageIdx = pageIdx || 1;
			var totalPage = 0;
			if (totals % size == 0){ //取模若为0标示可以除尽
				totalPage = totals / size;
			} else {
				totalPage = totals / size + 1;
			}
			var totalRecords = totals;
			var pageNo = pageIdx;
			//生成分页
			//有些参数是可选的，比如lang，若不传有默认值
			new Pager({
				pno : pageNo,
				//总页码
				total : totalPage,
				//总数据条数
				totalRecords : totalRecords,
				isShowTotalRecords :true,
				isGoPage : true,
				mode : 'click',//默认值是link，可选link或者click
				click : function(n){ //手动选中按钮
				this.selectPage(n);
				tabIdx = parseInt(tabIdx);
					if(tabIdx){
						switch(tabIdx){
							case 1:
								page1=n;
								pageNoChangeRequest(page1, size, tabIdx)
								break;
							case 2:
								page2=n;
								pageNoChangeRequest(page2, size, tabIdx)
								break;
							case 3:
								page3=n;
								pageNoChangeRequest(page3, size, tabIdx)
								break;
							case 4:
								page4=n;
								pageNoChangeRequest(page4, size, tabIdx)
								break;
							case 5:
								page5=n;
								pageNoChangeRequest(page5, size, tabIdx)
								break;
						}
					}
					return false;
				}
			});
		}

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

		//初始化每页数量事件
		function initPageSizeChangeEvent() {
			var pageList=$(".main_kkpager").html();
			$(".pagination_r i").on('click',function(){
				$(".pagination_r i").eq($(this).index()).addClass("on").siblings().removeClass("on");
				var index = $(".MyAssets_list_tab .on").attr("id");
				switch(index){
					case "1":
						size1=$(this).text();
						pageSizeChangeRequest(page1, size1, index);
						break;
					case "2":
						size2=$(this).text();
						pageSizeChangeRequest(page2, size2, index);
						break;
					case "3":
						size3=$(this).text();
						pageSizeChangeRequest(page3, size3, index);
						break;
					case "4":
						size4=$(this).text();
						pageSizeChangeRequest(page4, size4, index);
						break;
					case "5":
						size5=$(this).text();
						pageSizeChangeRequest(page5, size5, index);
						break;
				}
			});
		}

		//页签切换时调用
		function tabChange(tabbtn, tabpannel, tabclass) {
			var $div_li = tabbtn;
			$div_li.click(function() {
				$(this).addClass(tabclass).siblings().removeClass(tabclass);
				var index = $div_li.index(this);
				tabIdx = index+1;
				switch(tabIdx){
					case 1:
						tabChangeRequest(page1, total1, size1, tabIdx);
						break;
					case 2:
						tabChangeRequest(page2, total2, size2, tabIdx);
						break;
					case 3:
						tabChangeRequest(page3, total3, size3, tabIdx);
						break;
					case 4:
						tabChangeRequest(page4, total4, size4, tabIdx);
						break;
					case 5:
						tabChangeRequest(page5, total5, size5, tabIdx);
						break;
				}
				$(tabpannel).eq(index).show().siblings().hide();
			});
		}

		function exportExcel(index) {
			var startDate = $("#startDate_" + index).attr('data-prev');
			var endDate = $("#endDate_" + index).attr('data-prev');
			var amount = $("#amount_" + index).val();
			var partner = "";
			if (index == 1 || index == 2 || index == 3) {
				partner = $("#partner_" + index).attr('data-id');
			}
			var operator = "";
			if (index == 4 || index == 5) {
				operator = $("operator_" + index).val();
			}
			var paraList ="?tradeTypeId=" + (index - 1);
			if (partner){
				paraList = paraList + "&admissionName=" + partner;
			}
			if (startDate){
				paraList = paraList + "&startTimeStr=" + startDate;
			}
			if (endDate){
				paraList = paraList + "&endTimeStr=" + endDate;
			}
			if (amount){
				paraList = paraList + "&orderAmount=" + amount;
			}
			if (operator){
				paraList = paraList + "&orderOperator=" + operator;
			}
			location.href = "<%=basePath%>/account/exportExcel.shtml" + paraList;
		}


		//进度图
		function scale(arg) {
			var opts = {
				bgcolor: "#fcfbf7",
				_width: "60",
				_height: "60",
				id: "",
				numb: "60"
			};
			for (var i in arg) {
				for (var a in opts) {
					if (i == a) {
						opts[a] = arg[i];
					}
				}
			}
			;
			if (opts.id == "") {
				return false;
			}

			var canvas = document.getElementById(opts.id);
			var context = canvas.getContext("2d");
			this.init(canvas, opts, context);
		}
		scale.prototype = {
			init: function (canvas, opts, context) {
				this.bgDraw(opts, context);
				this.numb(canvas, opts);
			},
			numb: function (canvas, opts) {
				var i = 0;
				var num = canvas.parentNode.getElementsByTagName("span")[0];
				var interval = setInterval(function () {
					num.innerHTML = i + "<i>%</i>";
					if (i < opts.numb) {
						i++;
					} else {
						clearInterval(interval);
					}
					;
				}, 10);

			},
			bgDraw: function (opts, context) {
				context.beginPath();
				context.arc(30, 30, 30, 0, Math.PI * 2, true);
				context.closePath();
				context.fillStyle = "rgb(252,251,247)";
				context.fill();
				var g1 = context.createLinearGradient(0, 0, 0, 300);
				g1.addColorStop(0, 'rgb(203,204,205)');
				g1.addColorStop(0.5, 'rgb(229,229,230)');
				g1.addColorStop(1, 'rgb(203,204,205)');
				var g2 = context.createLinearGradient(0, 0, 0, 140);
				g2.addColorStop(0, 'rgb(252,236,182)');
				g2.addColorStop(0.5, 'rgb(250,201,55)');
				g2.addColorStop(1, 'rgb(252,236,182)');

				context.beginPath();
				context.arc(30, 30, 27.5, 0, 360, false);
				context.lineWidth = 5;
				context.strokeStyle = g1;
				context.stroke();//画空心圆
				context.closePath();
				var i = 0;
				var interval = setInterval(function () {
					//每次转换平铺类型清空
					if (i < opts.numb) {
						i++;
					} else {
						clearInterval(interval);
					}
					;
					var rote = Math.PI * 2 * i * 0.01;
					context.save();
					context.beginPath();
					context.arc(30, 30, 27.5, -0.5 * Math.PI / 2, -0.5 * Math.PI / 2 + rote, false);
					context.lineWidth = 4;
					context.strokeStyle = g2;
					context.stroke();//画空心圆
					context.closePath();
				}, 10);
			}
		};

		F.UI.scan();

		window.onload = function () {
			loadAccountBalance();
		}

        //充值
        $('.rechargeBtn').on('click',function(){
                window.top.createIframeDialog({
                	  url : '<%=basePath%>/account/goto_cashierDesk.shtml?payType=7',
                });
                return false;
        });
        
      	
		//异步加载账户余额信息
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
		
		loadLoanAccountInfo();
		
		function loadLoanAccountInfo()
		{
			  $.ajax({
					type : 'get',
					url :  '<%=basePath%>/loan/loanStatInfo.shtml'+"?DateTime="+new Date().getTime(),
					dataType : 'json',
					success : function(obj) {
							
						   if(obj['code'] && obj['msg'])
						   {
							   return;
						   }
						   
						   
						   $('#loanAccountZone').show();
						   
						   
						   var roomPackNum = 0;
						   var operationNum = 0;
						   
						   if(obj['productAmount'])
						   {
							   if(obj['productAmount']['ROOM_PACK'])
							   {
								   roomPackNum = Math.round((obj['productAmount']['ROOM_PACK'] / obj['loanAmount'] ) * 100);
								   $('#ROOM_PACK').text(formatCurrency(obj['productAmount']['ROOM_PACK']/100)  );
							   }
							   
							   if(obj['productAmount']['OPERACTION'])
							   {
								   operationNum = Math.round((obj['productAmount']['OPERACTION'] / obj['loanAmount'] ) * 100);
								   $('#OPERACTION').text(formatCurrency(obj['productAmount']['OPERACTION']/100));
							   }
						   }
						   
						   if(obj['productActualAmount'])
						   {
							   if(obj['productActualAmount']['ROOM_PACK'])
							   {
								   
								   $('#ROOM_PACK_SUM').text(formatCurrency(obj['productActualAmount']['ROOM_PACK']/100)  );
							   }
							   
							   if(obj['productActualAmount']['OPERACTION'])
							   {
								   $('#OPERACTION_SUM').text(formatCurrency(obj['productActualAmount']['OPERACTION']/100));
							   }
						   }
						   
						   
						   if(obj['loanAmount'])
							{
							   $('#loanAmount').text(formatCurrency(obj['loanAmount']/100) );
							}
						   
						   
						   new scale({id: "can5", numb: roomPackNum });
						   new scale({id: "can4", numb: operationNum});
						   
							

						},
						error:function(xhr,status){
		         			if(xhr.status&&xhr.status==403){
		            			//new top.Tip({msg : '没有权限访问贷款数据，请联系管理员', type: 3 , timer:2000});
		            			return ;
		            		}
		         			 new top.Tip({msg : '请求失败，请重试', type: 3, timer:2000});
		         		}
					});
		}
</script>
</body>
</html>
