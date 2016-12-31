<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<div class="MyAssets_tab" style="">
	<jsp:include page="trade_header.jsp"/>
	<!-- 表头 -->
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
			</tbody>
		</table>
	</div>
	
	<!-- 内容 -->
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
			<tbody id="id_2">
			</tbody>
		</table>
	</div>
</div>