<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>泰坦钱包</title>
	<jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
	<script type="text/javascript" src="<%=basePath%>/js/bindingBank.js?v=2017090219"></script>
</head>
<body style="min-width: 1300px;" class="bg" >
<!-- 头部 -->
<jsp:include page="/comm/header.jsp"></jsp:include>
<div class="h_90"></div>
<!-- 内容 -->
<div class="w_1200">
	<div class="overview clearfix">
		<jsp:include page="account_info.jsp"></jsp:include>
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
				<dl class="J_date_list fl m_r15">
					<dd>
						<div class="MyAssets_list_inp01 fl "><i>起始日期：</i></div>
						<input type="text"  id="startDate_1" class="text w_160 text_calendar fl">					
						<label for="" class="S_digit fl">至</label>
						<input type="text" name="" id="endDate_1" class="text text1 w_160 text_calendar fl" >
						<div class="MyAssets_list_inp01 fl "><i>截止日期：</i></div>						
					</dd>
				</dl>
			</div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" id="partner_1" placeholder="交易对方："></div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" id="amount_1" placeholder="订单金额："></div>
			<a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)" onclick="queryTransOrders(1)" >&nbsp;</a>
			<a href="javascript:void(0)" class="MyAssets_Export fr bacth_export_hotel J_export" onclick="exportExcel(1)">导出记录</a>
		</div>		
		<div class="label">
				<table  width="100%" cellspacing="0" border="0">
				<colgroup>	
				    <col width="145">
					<col width="30">
					<col width="80">
					<col width="250">
					<col width="180">
					<col width="100">
					<col width="30">
					<col width="100">
					<col width="30">
					<col width="100">
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
					<col width="145">
					<col width="30">
					<col width="80">
					<col width="250">
					<col width="180">
					<col width="100">
					<col width="30">
					<col width="100">
					<col width="30">
					<col width="100">
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
				<dl class="J_date_list fl m_r15">
					<dd>
						<div class="MyAssets_list_inp01 fl "><i>起始日期：</i></div>
						<input type="text"  id="startDate_2" class="text w_160 text_calendar fl">					
						<label for="" class="S_digit fl">至</label>
						<input type="text" name="" id="endDate_2" class="text text1 w_160 text_calendar fl" >
						<div class="MyAssets_list_inp01 fl "><i>截止日期：</i></div>	
					</dd>
				</dl>
			</div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" id="partner_2" placeholder="交易对方："></div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" id="amount_2" placeholder="订单金额："></div>
			<a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)" onclick="queryTransOrders(2)" >&nbsp;</a>
			<a href="javascript:void(0)" class="MyAssets_Export fr bacth_export_hotel J_export" onclick="exportExcel(2)">导出记录</a>
		</div>		
		<div class="label">
				<table width="100%" cellspacing="0" border="0">
				<colgroup>					
					<col width="145">
					<col width="30">
					<col width="80">
					<col width="250">
					<col width="180">
					<col width="100">
					<col width="30">
					<col width="100">
					<col width="30">
					<col width="100">
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
					<col width="145">
					<col width="30">
					<col width="80">
					<col width="250">
					<col width="180">
					<col width="100">
					<col width="30">
					<col width="100">
					<col width="30">
					<col width="100">
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
						<div class="MyAssets_list_inp01 fl "><i>起始日期：</i></div>
						<input type="text"  id="startDate_3" class="text w_160 text_calendar fl">					
						<label for="" class="S_digit fl">至</label>
						<input type="text" name="" id="endDate_3" class="text text1 w_160 text_calendar fl" >
						<div class="MyAssets_list_inp01 fl "><i>截止日期：</i></div>	
					</dd>
				</dl>
			</div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" id="partner_3" placeholder="交易对方："></div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" id="amount_3" placeholder="订单金额："></div>
			<a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)" onclick="queryTransOrders(3)">&nbsp;</a>
			<a href="javascript:void(0)" class="MyAssets_Export fr bacth_export_hotel J_export" onclick="exportExcel(3)">导出记录</a>
		</div>		
		<div class="label">
				<table width="100%" cellspacing="0" border="0">
				<colgroup>					
					<col width="145">
					<col width="30">
					<col width="80">
					<col width="250">
					<col width="180">
					<col width="100">
					<col width="30">
					<col width="100">
					<col width="30">
					<col width="100">
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
					<col width="145">
					<col width="30">
					<col width="80">
					<col width="250">
					<col width="180">
					<col width="100">
					<col width="30">
					<col width="100">
					<col width="30">
					<col width="100">
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
						<div class="MyAssets_list_inp01 fl "><i>起始日期：</i></div>
						<input type="text"  id="startDate_4" class="text w_160 text_calendar fl">					
						<label for="" class="S_digit fl">至</label>
						<input type="text" name="" id="endDate_4" class="text text1 w_160 text_calendar fl" >
						<div class="MyAssets_list_inp01 fl "><i>截止日期：</i></div>	
					</dd>
				</dl>
			</div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" id="operator_4" placeholder="操作人：" value=""></div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" id="amount_4" placeholder="充值金额："></div>
			<a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)" onclick="queryTransOrders(4)">&nbsp;</a>
			<a href="javascript:void(0)" class="MyAssets_Export fr bacth_export_hotel J_export" onclick="exportExcel(4)">导出记录</a>
		</div>		
		<div  class="label">
				<table width="100%" cellspacing="0" border="0">
				<colgroup>					
					<col width="145">
					<col width="30">
					<col width="80">
					<col width="250">
					<col width="180">
					<col width="100">
					<col width="30">
					<col width="100">
					<col width="30">
					<col width="100">
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
					<col width="145">
					<col width="30">
					<col width="80">
					<col width="250">
					<col width="180">
					<col width="100">
					<col width="30">
					<col width="100">
					<col width="30">
					<col width="100">
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
						<div class="MyAssets_list_inp01 fl "><i>起始日期：</i></div>
						<input type="text"  id="startDate_5" class="text w_160 text_calendar fl">					
						<label for="" class="S_digit fl">至</label>
						<input type="text" name="" id="endDate_5" class="text text1 w_160 text_calendar fl" >
						<div class="MyAssets_list_inp01 fl "><i>截止日期：</i></div>	
					</dd>
				</dl>
			</div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" id="operator_5" placeholder="操作人："></div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" id="amount_5" placeholder="提现金额："></div>
			<a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)" onclick="queryTransOrders(5)">&nbsp;</a>
			<a href="javascript:void(0)" class="MyAssets_Export fr bacth_export_hotel J_export" onclick="exportExcel(5)">导出记录</a>
		</div>		
		<div  class="label">
				<table width="100%" cellspacing="0" border="0">
				<colgroup>					
					<col width="145">
					<col width="30">
					<col width="80">
					<col width="250">
					<col width="180">
					<col width="100">
					<col width="30">
					<col width="100">
					<col width="30">
					<col width="100">
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
					<col width="145">
					<col width="30">
					<col width="80">
					<col width="250">
					<col width="180">
					<col width="100">
					<col width="30">
					<col width="100">
					<col width="30">
					<col width="100">
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
		<div id="kkpager" class="page_turning"></div>
	</div>
	<div class="pagination_r"   style="display:none">
		<i class="on">5</i>
	</div>
<!-- 	<div class="main_kkpager">
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
	</div> -->
</div>
</div>
<div id="bindcard-wrap" style="display:none;">
	<div class="veil"></div>
	<!--模态框-->
	<div class="modal-box">
		 
	</div>
</div>
<form action="<%=basePath%>/account/toBindCardStepOne.shtml" method="post" id="toBindCard" target="_blank">
  <input type="hidden" name="modifyOrBind" id="modifyOrBind">
</form>

<div class="h_40"></div>
<!-- 版权 -->
<jsp:include page="/comm/foot-line.jsp"></jsp:include>

<script type="text/javascript">  
//我的账户
        
        var page1,page2,page3,page4,page5;
		//总记录数
		var total1,total2,total3,total4,total5;
		//每页数量
		 var size1 = 5;
        $(function(){
			tabChange($(".MyAssets_list_tab span"), $(".MyAssets_tab"), "on");
			initRequest();
			/* initPageSizeChangeEvent(); */
			initAutoSelectPartner();
			validate_BankCard_Satatus();
		});
        
        
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
						tabChangeRequest(page2, total2, size1, tabIdx);
						break;
					case 3:
						tabChangeRequest(page3, total3, size1, tabIdx);
						break;
					case 4:
						tabChangeRequest(page4, total4, size1, tabIdx);
						break;
					case 5:
						tabChangeRequest(page5, total5, size1, tabIdx);
						break;
				}
				$(tabpannel).eq(index).show().siblings().hide();
			});
		}
      //初始化页面请求
		function initRequest() {
			$.ajax({
				type:'post',
				dataType : 'html',
				showLoading:true,
				context: document.body,
				data:{currentPage:1,pageSize:size1,tradeTypeId:"0"},
				url : '<%=basePath%>/account/query-org-page.shtml',
				success : function(html){
					$("#id_1").empty();
					$("#id_1").html(html);
					var total1 = $("#id_1 #tradePageTotal").val();
					var page1 = $("#id_1 #tradePageCurrent").val();
					pageGo(page1,total1,size1,1);
				}
			});
		}
		 
		//添加日期锻
		$('.J_Section').each(function(){
			//添加日期锻
			new DateSection('#' + $(this).attr('id'), {minDate : '2016-01-01',maxDate : ''});
			});
		
		
		var partner_dataSource=null;
		//初始化联想查询框
		function initAutoSelectPartner() {
			$.ajax({
        		dataType : 'json',
        		showLoading:true,
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
		function validate_BankCard_Satatus(){
			$.ajax({
				dataType:"json",
				url:"<%=basePath%>/account/checkBindResult.shtml",
				success: function (result) {
					if(result.code=="1"){
						$("#btn_withdraw").attr({"data-result":result.data.orgBankcardStatus});
		        	  	if(result.data.orgBankcardStatus=="0"){//绑定失败
		        			bc.bindResultView();
		        		}else if(result.data.orgBankcardStatus=="10"){//未关联机构
		        			$(".binding-prompt").show();
		        		}else if(result.data.orgBankcardStatus=="20"){//无绑定记录
		        			$(".binding-prompt").show();
		        		}
		        	}else{
		        		 new top.Tip({msg: result.msg, type: 2, timer: 2000});
		        	}
				}
			});
		}
        
        //提现
        $('.withdrawBtn').on('click',function(){
        	var bind = $("#btn_withdraw").attr("data-result");
       	  	if(bind=="0"){//绑定失败
       			bc.bindResultView();
       		}else if(bind=="1"){//对私或者对公已绑定成功
       			account_withdraw();
       		}else if(bind=="2"){//审核中
       			bc.bindResultView();
       		}else if(bind=="10"){//未关联机构
       			bc.bind_card();
       		}else if(bind=="20"){//无绑定记录
       			bc.updateBind();
       		}else{
       			new top.Tip({msg: "系统繁忙，请稍后再试", type: 2, timer: 2000});
       		}
		     
        });
         
        //充值
        $('.rechargeBtn').on('click',function(){
        	window.open('<%=basePath%>/account/goto_cashierDesk.shtml?payType=7&succUrl='+encodeURIComponent(js_base_path+'/account/overview-main.shtml'));
            return false;
        });
        function account_withdraw(){
        	window.open('<%=basePath%>/account/goto_cashierDesk.shtml?payType=8&succUrl='+encodeURIComponent(js_base_path+'/account/overview-main.shtml'));
          }
          
        
    	window.onload = function () {
			loadAccountBalance();
		};
		
		$('.J_Section').each(function(){
			//添加日期锻
			new DateSection('#' + $(this).attr('id'), {minDate : '',maxDate : ''});
		});
	       
    	var errorIndex = 1;
		function loadAccountBalance()
		{
			$.ajax({
        		dataType : 'json',	
        		showLoading:true,
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
		
		
		//按条件查询
		function queryTransOrders(index) {
			var tradeType = "" + (index - 1);
			var data = getQueryOrderData(index);
			var size = $(".pagination_r .on").text();
			$.ajax({
				type:'post',
				dataType: 'html',
				showLoading:true,
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
					pageGo(page, total, size,index);
				}
			});
		}
		
		//页签切换请求
		function tabChangeRequest(page, total, size, index) {
			if (!page) {
				page = 1;
			}
			//resetPageSizeView(size);
			var data = getQueryOrderData(index);
			$.ajax({
				type:'post',
				dataType: 'html',
				showLoading:true,
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
					
				}
			});
		}

		//点击分页页码时调用
		function pageNoChangeRequest(page, size, index) {
			var data = getQueryOrderData(index);
			$.ajax({
				type:"post",
				dataType : 'html',
				showLoading:true,
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
				}
			});
		}

		//切换每页数量时调用
		function pageSizeChangeRequest(page, size, index) {
			var data = getQueryOrderData(index);
			$.ajax({
				type:'post',
				dataType : 'html',
				showLoading:true,
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
		
	F.UI.scan();
	
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

	
</script>
</body>
</html>
