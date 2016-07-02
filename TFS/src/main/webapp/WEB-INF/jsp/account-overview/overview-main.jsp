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
				<h4> <i class="MyAssets_greenNotice">
					<fmt:formatNumber value="${accountBalance.amount/100 }"  pattern="#,##0.00#" /></i>
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
								<span>现金可用余额：<fmt:formatNumber value="${accountBalance.balanceusable/100 }" pattern="#,##0.00#" /></span>
								<a href="javascript:void(0)" class="blue decorationUnderline rechargeBtn">充值</a>
								<a href="javascript:void(0)" class="blue decorationUnderline withdrawBtn">提现</a>
							</p>
							<p>
								<span>
									现金冻结余额：<fmt:formatNumber value="${accountBalance.balancefrozon/100 }" pattern="#,#00.00#" />
									<i class="MyAssets_noticeIco" title="当联盟分销商付款成功后，供应商未确认订单前资金会冻结，确认后即可解冻"></i>
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
		});

        //充值
        $('.rechargeBtn').on('click',function(){
                window.top.createIframeDialog({
                    url : 'TFS/充值.html',
                });
                return false;
        });

        //提现
        $('.withdrawBtn').on('click',function(){
                window.top.createIframeDialog({
                    url : '<%=basePath%>/account/account-withdraw.shtml',
					close:function () {
						alert("===");
					}
                });
                return false;
        });

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

		//初始化联想查询框
		function initAutoSelectPartner() {
			new AutoComplete($('#partner_1'), {
				url : '<%=basePath%>/account/getOrgList.shtml',
				source : 'organDTOList',
				key : 'userId',  //数据源中，做为key的字段
				val : 'orgName', //数据源中，做为val的字段
				width : 240,
				height : 300,
				autoSelect : false,
				clickEvent : function(d, input){
					input.attr('data-id', d.key);
				}
			});

			new AutoComplete($('#partner_2'), {
				url : '<%=basePath%>/account/getOrgList.shtml',
				source : 'organDTOList',
				key : 'userId',  //数据源中，做为key的字段
				val : 'orgName', //数据源中，做为val的字段
				width : 240,
				height : 300,
				autoSelect : false,
				clickEvent : function(d, input){
					input.attr('data-id', d.key);
				}
			});

			new AutoComplete($('#partner_3'), {
				url : '<%=basePath%>/account/getOrgList.shtml',
				source : 'organDTOList',
				key : 'userId',  //数据源中，做为key的字段
				val : 'orgName', //数据源中，做为val的字段
				width : 240,
				height : 300,
				autoSelect : false,
				clickEvent : function(d, input){
					input.attr('data-id', d.key);
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
					endTimeStr: data.startTimeStr,
					orderAmount: data.orderAmount,
					orderOperator:data.orderOperator	
				},
				url : '<%=basePath%>/account/query-org-page.shtml',
				success : function(html){
					$("#id_" + index).empty();
					$("#id_" + index).html(html);
					var total4 = $("#id_" + index +" #tradePageTotal").val();
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
					endTimeStr: data.startTimeStr,
					orderAmount: data.orderAmount,
					orderOperator:data.orderOperator	
				},
				url : '<%=basePath%>/account/query-org-page.shtml',
				success : function(html){
					$("#id_" + index).empty();
					$("#id_" + index).html(html);
					var total = $("#id_" + index + " #tradePageTotal").val();
					pageGo(page,total,size,index);
					F.loading.hide();
				}
			});
		}

		function getQueryOrderData(index){
			var startDate = $("#startDate_" + index ).attr('data-prev');
			var endDate = $("#endDate_" + index ).attr('data-prev');
			var amount = $("#amount_" + index ).val();
			var partner = "";
			if (index == 1 || index == 2 || index == 3){
				var partnerName = $("#partner_" + index ).val();
				if(partnerName.length>0){
					partner = $("#partner_" + index ).attr('data-id');
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
			alert("<%=basePath%>/account/exportExcel.shtml" + paraList)
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
			var canvas1 = new scale({id: "can1", numb: 100});
		}

        //充值
        $('.rechargeBtn').on('click',function(){
                window.top.createIframeDialog({
                	  url : '<%=basePath%>/trade/showCashierDesk.action?paySource=5',
                });
                return false;
        });
      

</script>
</body>
</html>
