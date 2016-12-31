<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
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
		<input type="text" value="" id="partner_1" placeholder="交易对方：">
	</div>
	<div class="MyAssets_list_inp01 fl ml10">				
		<input type="text" value="" id="amount_1" placeholder="订单金额：">
	</div>
	<a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)" onclick="queryTransOrders(1)" >&nbsp;</a>
	<a href="javascript:void(0)" class="MyAssets_Export fr bacth_export_hotel J_export" onclick="exportExcel(1)">
	<img src="<%=cssSaasPath%>/images/TFS/tfs_c01.png" ></a>
</div>	