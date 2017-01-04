<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>泰坦钱包</title>
    <link rel="stylesheet" href="css/fangcang.min.css?v=20161222">
    <link rel="stylesheet" href="http://hres.fangcang.com/css/saas/css/jquery-ui-1.9.2.custom.css" >
    <link rel="stylesheet" href="http://hres.fangcang.com/css/saas/css/style_TFS.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body style="min-width: 1300px;" class="bg" >
<!-- 头部 -->
<div class="header">
	<div class="w_1200">
		<div class="logo">
			<div class="l_img"><img src="images/logo.png"></div>
			<!-- <div class="l_text">
				<i class="ico "></i>欢迎注册
			</div> -->
		</div>
		<div class="head_r">
			<ul>
				<li><a href="首页.html" >首页</a></li>
				<li><a href="解决方案.html">解决方案</a></li>
				<li class="w_240 li_1">
					<!-- 登录前 -->
					<div class="dn">
					<a href="注册.html" class="li_login">免费注册</a>
					<a class="li_btn1" href="登录.html">登录</a>
					</div>
					<!-- 登录后 -->
					<div class="hr_login">
						<div class="hrl_hover">
							<i class="ico"></i>
							我的账户
						</div>
						<div class="hrl_ul dn">
							<a href="资产概览.html">资产概览</a>
							<a href="泰坦钱包设置.html">泰坦钱包设置</a>
							<a href="登录.html">退出</a>								
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
</div>
<div class="h_90"></div>
<!-- 内容 -->
<div class="w_1200">
	<div class="overview clearfix">
		<div class="o_crumbs">
			账户名称/泰坦码：深圳市天下房仓科技有限公司/6666 8888
		</div>
		<div class="MyAssets_chart">
		<div class="MyAssets_code"><img src="images/tu09.jpg"></div>
		<div class="MyAssets_chart_list">
			<div class="MyAssets_chart_list01 fl">
				<h3>我的资产</h3>
				<h4> <i class="">26,000,00</i>
					元
				</h4>
				<table cellpadding="0" cellspacing="0" class="MyAssets_chart_tab01">
					<tr>
						<td width="75" class="MyAssets_chart_td01">
							<canvas id="can1" class="canvasBox" height="60" width="60"></canvas>
							<span></span>
						</td>
						<td>
							<p>
								<span>现金可用余额：6,000,00</span>
								<a href="充值.html" target="_blank" class="blue decorationUnderline ">充值</a>
								<a href="提现.html" target="_blank" class="blue decorationUnderline ">提现</a>
								<a href="javascript:void(0)" target="_blank" class="blue decorationUnderline withdrawBtn">提现</a>
							</p>
							<p>
								<span>
									现金冻结余额：3,000,00 <i class="MyAssets_noticeIco" title="当联盟分销商付款成功后，供应商未确认订单前资金会冻结，确认后即可解冻"></i>
								</span>
								<a href="我的资产-详情.html" class="blue decorationUnderline" target="_blank" >详情</a>
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
		<div>
		<div class="MyAssets_tab" style="">
		<div class="MyAssets_list_Options ">
			<div class="J_Section " id="Options" >
				<dl class="J_date_list fl m_r15">
					<dd>
					<div class="MyAssets_list_inp01 fl ">	
					<i>起始日期：</i> 			
					<input type="text"  class="text w_160 text_calendar fl">
					</div>						
					<label for="" class="S_digit fl">至</label>
					<div class="MyAssets_list_inp01 fl ">	
					<i>截止日期：</i>								
					<input type="text" name="" class="text w_160 text_calendar fl" >
					</div>						
					</dd>
				</dl>
			</div>			
			
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" placeholder="交易双方："></div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" placeholder="订单金额："></div>
			<a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)">&nbsp;</a>
			<a href="javascript:void(0)" class="MyAssets_Export fr bacth_export_hotel J_export">导出记录</a>
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
					<col width="10">
					<col width="80">
					<col width="250">
					<col width="180">
					<col width="100">
					<col width="10">
					<col width="100">
					<col width="10">
					<col width="100">
					<col width="">
				</colgroup>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"><i class="flag_ico J_remark"></i></td>
					<td width="">付款</td>
					<td width="" ><span style="max-width: 240px" title="酒店 - 酒店名称：IT测试酒店4722酒店名称：IT测试酒店4722">酒店 - 酒店名称：IT测试酒店4722酒店名称IT测试酒店4722</span></td>
					<td width="" ><span style="max-width: 170px" title="联盟供应商-会员测试">联盟供应商-会员测试</span></td>
					<td class="tdr">-872.00</td>
					<td></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td><i class="MyAssets_red">交易失败</i></td>
					<td class=""><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a>  <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"><i class="flag_ico J_remark"></i></td>
					<td width="">提现</td>
					<td width="" ><span style="max-width: 240px" title="酒店 - 酒店名称：IT测试酒店4722">酒店 - 酒店名称：IT测试酒店4722</span></td>
					<td width="" ><span style="max-width: 170px" title="联盟供应商-会员测试">联盟供应商-会员测试联盟供应商-会员测试联盟供应商-会员测试</span></td>
					<td class="tdr">-872.00</td>
					<td></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td>交易成功</td>
					<td class=" "><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a>  <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"></td>
					<td width="">充值</td>
					<td width="" ><span style="max-width: 240px" title="酒店 - 酒店名称：IT测试酒店4722">酒店 - 酒店名称：IT测试酒店4722</span></td>
					<td width="" ><span style="max-width: 170px" title="联盟供应商-会员测试">联盟供应商-会员测试联盟供应商-会员测试联盟供应商-会员测试</span></td>
					<td class="tdr">+2,888.00</td>
					<td><i class="freeze_ico" title="供应商确认订单号即可解冻"></i></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td>已冻结</td>
					<td class=" "><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a>  <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"></td>
					<td width="">收款</td>
					<td width="" ><span style="max-width: 240px" title="酒店 - 酒店名称：IT测试酒店4722">酒店 - 酒店名称：IT测试酒店4722</span></td>
					<td width="" ><span style="max-width: 170px" title="联盟供应商-会员测试">联盟供应商-会员测试联盟供应商-会员测试联盟供应商-会员测试</span></td>
					<td class="tdr">+2,888.00</td>
					<td><i class="freeze_ico" title="供应商确认订单号即可解冻"></i></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td>已冻结</td>
					<td class=" "><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a> <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"></td>
					<td width="">充值</td>
					<td width="" ><span style="max-width: 240px" title="酒店 - 酒店名称：IT测试酒店4722">酒店 - 酒店名称：IT测试酒店4722</span></td>
					<td width="" ><span style="max-width: 170px" title="联盟供应商-会员测试">联盟供应商-会员测试联盟供应商-会员测试联盟供应商-会员测试</span></td>
					<td class="tdr">+2,888.00</td>
					<td><i class="freeze_ico" title="供应商确认订单号即可解冻"></i></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td>已冻结</td>
					<td class=" "><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a>  <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"></td>
					<td width="">收款</td>
					<td width="" ><span style="max-width: 240px" title="酒店 - 酒店名称：IT测试酒店4722">酒店 - 酒店名称：IT测试酒店4722</span></td>
					<td width="" ><span style="max-width: 170px" title="联盟供应商-会员测试">联盟供应商-会员测试联盟供应商-会员测试联盟供应商-会员测试</span></td>
					<td class="tdr">+2,888.00</td>
					<td><i class="freeze_ico" title="供应商确认订单号即可解冻"></i></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td>已冻结</td>
					<td class=" "><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a> <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
			</table>
		</div>
		</div>			

		<div class="MyAssets_tab" style="display: none;">
		<div class="MyAssets_list_Options ">

			<div class="J_Section " id="Options1" >
				<dl class="J_date_list fl m_r15">
					<dd>
					<div class="MyAssets_list_inp01 fl ">	
					<i>起始日期：</i> 			
					<input type="text"  class="text w_160 text_calendar fl">
					</div>						
					<label for="" class="S_digit fl">至</label>
					<div class="MyAssets_list_inp01 fl ">	
					<i>截止日期：</i>								
					<input type="text" name="" class="text w_160 text_calendar fl" >
					</div>						
					</dd>
				</dl>
			</div>	
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" placeholder="交易对方："></div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" placeholder="订单金额："></div>
			<a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)">&nbsp;</a>
			<a href="javascript:void(0)" class="MyAssets_Export fr bacth_export_hotel J_export">导出记录</a>
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
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"><i class="flag_ico J_remark"></i></td>
					<td width="">付款</td>
					<td width="" ><span style="max-width: 230px" title="酒店 - 酒店名称：IT测试酒店4722酒店名称：IT测试酒店4722">酒店 - 酒店名称：IT测试酒店4722酒店名称IT测试酒店4722</span></td>
					<td width="" ><span style="max-width: 170px" title="收款方：联盟供应商-会员测试">收款方：联盟供应商-会员测试</span></td>
					<td class="tdr">-872.00</td>
					<td></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td><i class="MyAssets_red">交易失败</i></td>
					<td class=""><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a>  <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"><i class="flag_ico J_remark"></i></td>
					<td width="">付款</td>
					<td width="" ><span style="max-width: 230px" title="酒店 - 酒店名称：IT测试酒店4722酒店名称：IT测试酒店4722">酒店 - 酒店名称：IT测试酒店4722酒店名称IT测试酒店4722</span></td>
					<td width="" ><span style="max-width: 170px" title="收款方：联盟供应商-会员测试">收款方：联盟供应商-会员测试</span></td>
					<td class="tdr">-872.00</td>
					<td></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td><i class="MyAssets_red">交易失败</i></td>
					<td class=""><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a>  <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"><i class="flag_ico J_remark"></i></td>
					<td width="">付款</td>
					<td width="" ><span style="max-width: 230px" title="酒店 - 酒店名称：IT测试酒店4722酒店名称：IT测试酒店4722">酒店 - 酒店名称：IT测试酒店4722酒店名称IT测试酒店4722</span></td>
					<td width="" ><span style="max-width: 170px" title="收款方：联盟供应商-会员测试">收款方：联盟供应商-会员测试</span></td>
					<td class="tdr">-872.00</td>
					<td></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td><i class="MyAssets_red">交易失败</i></td>
					<td class=""><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a>  <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"><i class="flag_ico J_remark"></i></td>
					<td width="">付款</td>
					<td width="" ><span style="max-width: 230px" title="酒店 - 酒店名称：IT测试酒店4722酒店名称：IT测试酒店4722">酒店 - 酒店名称：IT测试酒店4722酒店名称IT测试酒店4722</span></td>
					<td width="" ><span style="max-width: 170px" title="收款方：联盟供应商-会员测试">收款方：联盟供应商-会员测试</span></td>
					<td class="tdr">-872.00</td>
					<td></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td><i class="MyAssets_red">交易失败</i></td>
					<td class=""><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a>  <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"><i class="flag_ico J_remark"></i></td>
					<td width="">付款</td>
					<td width="" ><span style="max-width: 230px" title="酒店 - 酒店名称：IT测试酒店4722酒店名称：IT测试酒店4722">酒店 - 酒店名称：IT测试酒店4722酒店名称IT测试酒店4722</span></td>
					<td width="" ><span style="max-width: 170px" title="收款方：联盟供应商-会员测试">收款方：联盟供应商-会员测试</span></td>
					<td class="tdr">-872.00</td>
					<td></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td><i class="MyAssets_red">交易失败</i></td>
					<td class=""><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a>  <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"><i class="flag_ico J_remark"></i></td>
					<td width="">付款</td>
					<td width="" ><span style="max-width: 230px" title="酒店 - 酒店名称：IT测试酒店4722酒店名称：IT测试酒店4722">酒店 - 酒店名称：IT测试酒店4722酒店名称IT测试酒店4722</span></td>
					<td width="" ><span style="max-width: 170px" title="收款方：联盟供应商-会员测试">收款方：联盟供应商-会员测试</span></td>
					<td class="tdr">-872.00</td>
					<td></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td><i class="MyAssets_red">交易失败</i></td>
					<td class=""><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a>  <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
			</table>
		</div>
		</div>

		<div class="MyAssets_tab" style="display: none;">
		<div class="MyAssets_list_Options ">

			<div class="J_Section " id="Options2" >
				<dl class="J_date_list fl m_r15">
					<dd>
					<div class="MyAssets_list_inp01 fl ">	
					<i>起始日期：</i> 			
					<input type="text"  class="text w_160 text_calendar fl">
					</div>						
					<label for="" class="S_digit fl">至</label>
					<div class="MyAssets_list_inp01 fl ">	
					<i>截止日期：</i>								
					<input type="text" name="" class="text w_160 text_calendar fl" >
					</div>						
					</dd>
				</dl>
			</div>	
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" placeholder="交易对方："></div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" placeholder="订单金额："></div>
			<a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)">&nbsp;</a>
			<a href="javascript:void(0)" class="MyAssets_Export fr bacth_export_hotel J_export">导出记录</a>
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
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"><i class="flag_ico J_remark"></i></td>
					<td width="">收款</td>
					<td width="" ><span style="max-width: 240px" title="酒店 - 酒店名称：IT测试酒店4722酒店名称：IT测试酒店4722">酒店 - 酒店名称：IT测试酒店4722酒店名称IT测试酒店4722</span></td>
					<td width="" ><span style="max-width: 170px" title="付款方：联盟供应商-会员测试">付款方：联盟供应商-会员测试</span></td>
					<td class="tdr">-872.00</td>
					<td></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td><i class="MyAssets_red">交易失败</i></td>
					<td class=""><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a> <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"><i class="flag_ico J_remark"></i></td>
					<td width="">收款</td>
					<td width="" ><span style="max-width: 240px" title="酒店 - 酒店名称：IT测试酒店4722">酒店 - 酒店名称：IT测试酒店4722</span></td>
					<td width="" ><span style="max-width: 170px" title="付款方：联盟供应商-会员测试联盟供应商">付款方：联盟供应商-会员测试联盟供应商-会员测试联盟供应商-会员测试</span></td>
					<td class="tdr">-872.00</td>
					<td></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td>交易成功</td>
					<td class=" "><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a> <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"></td>
					<td width="">收款</td>
					<td width="" ><span style="max-width: 240px" title="酒店 - 酒店名称：IT测试酒店4722">酒店 - 酒店名称：IT测试酒店4722</span></td>
					<td width="" ><span style="max-width: 170px" title="付款方：联盟供应商-会员测试">付款方：联盟供应商-会员测试</span></td>
					<td class="tdr">+2,888.00</td>
					<td><i class="freeze_ico" title="供应商确认订单号即可解冻"></i></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td>已冻结</td>
					<td class=" "><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a> <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"></td>
					<td width="">收款</td>
					<td width="" ><span style="max-width: 240px" title="酒店 - 酒店名称：IT测试酒店4722">酒店 - 酒店名称：IT测试酒店4722</span></td>
					<td width="" ><span style="max-width: 170px" title="付款方：联盟供应商-会员测试">付款方：联盟供应商-会员测试</span></td>
					<td class="tdr">+2,888.00</td>
					<td><i class="freeze_ico" title="供应商确认订单号即可解冻"></i></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td>已冻结</td>
					<td class=" "><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a> <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"></td>
					<td width="">收款</td>
					<td width="" ><span style="max-width: 240px" title="酒店 - 酒店名称：IT测试酒店4722">酒店 - 酒店名称：IT测试酒店4722</span></td>
					<td width="" ><span style="max-width: 170px" title="付款方：联盟供应商-会员测试">付款方：联盟供应商-会员测试</span></td>
					<td class="tdr">+2,888.00</td>
					<td><i class="freeze_ico" title="供应商确认订单号即可解冻"></i></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td>已冻结</td>
					<td class=" "><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a> <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"></td>
					<td width="">收款</td>
					<td width="" ><span style="max-width: 240px" title="酒店 - 酒店名称：IT测试酒店4722">酒店 - 酒店名称：IT测试酒店4722</span></td>
					<td width="" ><span style="max-width: 170px" title="付款方：联盟供应商-会员测试">付款方：联盟供应商-会员测试</span></td>
					<td class="tdr">+2,888.00</td>
					<td><i class="freeze_ico" title="供应商确认订单号即可解冻"></i></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td>已冻结</td>
					<td class=" "><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a>  <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
			</table>
		</div>
		</div>		

		<div class="MyAssets_tab" style="display: none;">
		<div class="MyAssets_list_Options ">

			<div class="J_Section " id="Options3" >
				<dl class="J_date_list fl m_r15">
					<dd>
					<div class="MyAssets_list_inp01 fl ">	
					<i>起始日期：</i> 			
					<input type="text"  class="text w_160 text_calendar fl">
					</div>						
					<label for="" class="S_digit fl">至</label>
					<div class="MyAssets_list_inp01 fl ">	
					<i>截止日期：</i>								
					<input type="text" name="" class="text w_160 text_calendar fl" >
					</div>						
					</dd>
				</dl>
			</div>	
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" placeholder="付款方式："></div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" placeholder="操作人："></div>
			<a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)">&nbsp;</a>
			<a href="javascript:void(0)" class="MyAssets_Export fr bacth_export_hotel J_export">导出记录</a>
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
					<td class="tdl">付款方式</td>
					<td class="tdl">操作人</td>
					<td class="tdr">充值金额</td>
					<td class="tdl"></td>
					<td class="tdr">手续费</td>
					<td class="tdl"></td>
					<td class="tdl">交易结果</td>					
					<td class="tdl">操作</td>		
				</tr>
				</tbody>
			</table>
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
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"><i class="flag_ico J_remark"></i></td>
					<td width="">充值</td>
					<td width="" ><span style="max-width: 240px" >工商银行（**** 6666）</span></td>
					<td width="" ><span style="max-width: 170px">张三李四</span></td>
					<td class="tdr">+872.00</td>
					<td></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td><i class="MyAssets_red">交易失败</i></td>
					<td class=""><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a>  <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"><i class="flag_ico J_remark"></i></td>
					<td width="">充值</td>
					<td width="" ><span style="max-width: 240px" >工商银行（**** 6666）</span></td>
					<td width="" ><span style="max-width: 170px">张三李四</span></td>
					<td class="tdr">+872.00</td>
					<td></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td><i class="MyAssets_red">交易失败</i></td>
					<td class=""><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a>  <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"><i class="flag_ico J_remark"></i></td>
					<td width="">充值</td>
					<td width="" ><span style="max-width: 240px" >工商银行（**** 6666）</span></td>
					<td width="" ><span style="max-width: 170px">张三李四</span></td>
					<td class="tdr">+872.00</td>
					<td></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td><i class="MyAssets_red">交易失败</i></td>
					<td class=""><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a>  <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"><i class="flag_ico J_remark"></i></td>
					<td width="">充值</td>
					<td width="" ><span style="max-width: 240px" >工商银行（**** 6666）</span></td>
					<td width="" ><span style="max-width: 170px">张三李四</span></td>
					<td class="tdr">+872.00</td>
					<td></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td><i class="MyAssets_red">交易失败</i></td>
					<td class=""><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a>  <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"><i class="flag_ico J_remark"></i></td>
					<td width="">充值</td>
					<td width="" ><span style="max-width: 240px" >工商银行（**** 6666）</span></td>
					<td width="" ><span style="max-width: 170px">张三李四</span></td>
					<td class="tdr">+872.00</td>
					<td></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td><i class="MyAssets_red">交易失败</i></td>
					<td class=""><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a>  <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"><i class="flag_ico J_remark"></i></td>
					<td width="">充值</td>
					<td width="" ><span style="max-width: 240px" >工商银行（**** 6666）</span></td>
					<td width="" ><span style="max-width: 170px">张三李四</span></td>
					<td class="tdr">+872.00</td>
					<td></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td><i class="MyAssets_red">交易失败</i></td>
					<td class=""><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a>  <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
			</table>
		</div>
		</div>

		<div class="MyAssets_tab" style="display: none;">
		<div class="MyAssets_list_Options ">

			<div class="J_Section " id="Options4" >
				<dl class="J_date_list fl m_r15">
					<dd>
					<div class="MyAssets_list_inp01 fl ">	
					<i>起始日期：</i> 			
					<input type="text"  class="text w_160 text_calendar fl">
					</div>						
					<label for="" class="S_digit fl">至</label>
					<div class="MyAssets_list_inp01 fl ">	
					<i>截止日期：</i>								
					<input type="text" name="" class="text w_160 text_calendar fl" >
					</div>						
					</dd>
				</dl>
			</div>	
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" placeholder="提现信息："></div>
			<div class="MyAssets_list_inp01 fl ml10">				
				<input type="text" value="" placeholder="操作人："></div>
			<a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)">&nbsp;</a>
			<a href="javascript:void(0)" class="MyAssets_Export fr bacth_export_hotel J_export">导出记录</a>
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
					<td class="tdl">提现信息</td>
					<td class="tdl">操作人</td>
					<td class="tdr">提现金额</td>
					<td class="tdl"></td>
					<td class="tdr">手续费</td>
					<td class="tdl"></td>
					<td class="tdl">交易结果</td>					
					<td class="tdl">操作</td>		
				</tr>
			</tbody>
			</table>
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
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"><i class="flag_ico J_remark"></i></td>
					<td width="">提现</td>
					<td width="" ><span style="max-width: 240px" >工商银行（**** 6666）</span></td>
					<td width="" ><span style="max-width: 170px">张三李四</span></td>
					<td class="tdr">-872.00</td>
					<td></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td><i class="MyAssets_red">交易失败</i></td>
					<td class=""><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a>  <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"><i class="flag_ico J_remark"></i></td>
					<td width="">提现</td>
					<td width="" ><span style="max-width: 240px" >工商银行（**** 6666）</span></td>
					<td width="" ><span style="max-width: 170px">张三李四</span></td>
					<td class="tdr">-872.00</td>
					<td></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td><i class="MyAssets_red">交易失败</i></td>
					<td class=""><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a>  <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"><i class="flag_ico J_remark"></i></td>
					<td width="">提现</td>
					<td width="" ><span style="max-width: 240px" >工商银行（**** 6666）</span></td>
					<td width="" ><span style="max-width: 170px">张三李四</span></td>
					<td class="tdr">-872.00</td>
					<td></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td><i class="MyAssets_red">交易失败</i></td>
					<td class=""><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a>  <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"><i class="flag_ico J_remark"></i></td>
					<td width="">提现</td>
					<td width="" ><span style="max-width: 240px" >工商银行（**** 6666）</span></td>
					<td width="" ><span style="max-width: 170px">张三李四</span></td>
					<td class="tdr">-872.00</td>
					<td></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td><i class="MyAssets_red">交易失败</i></td>
					<td class=""><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a>  <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"><i class="flag_ico J_remark"></i></td>
					<td width="">提现</td>
					<td width="" ><span style="max-width: 240px" >工商银行（**** 6666）</span></td>
					<td width="" ><span style="max-width: 170px">张三李四</span></td>
					<td class="tdr">-872.00</td>
					<td></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td><i class="MyAssets_red">交易失败</i></td>
					<td class=""><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a>  <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
				<tr>				
					<td width="">2016.01.13   15:06:52</td>
					<td width="tdr"><i class="flag_ico J_remark"></i></td>
					<td width="">提现</td>
					<td width="" ><span style="max-width: 240px" >工商银行（**** 6666）</span></td>
					<td width="" ><span style="max-width: 170px">张三李四</span></td>
					<td class="tdr">-872.00</td>
					<td></td>
					<td class="tdr">2.00</td>
					<td></td>
					<td><i class="MyAssets_red">交易失败</i></td>
					<td class=""><a class="J_orderDetails blue decorationUnderline m_r10" href="我的资产-详情1.html" target="_blank">详情</a>  <a class="J_remark blue decorationUnderline">备注</a></td>
				</tr>
			</table>
		</div>
		</div>
	
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


<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/fangcang.min.js"></script>
<script src="js/jquery-ui-1.9.2.custom.js"></script>
<script src="js/common.js"></script>
<script type="text/javascript">  
//我的账户
$('.hr_login').hover(function(){
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
	        }*/
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
        });
		
        //默认出现弹框	
		new top.createConfirm({
	    title:'提示',
		padding: '20px 20px 40px',
		width:400,
		cancelValue : '下次再说',
        okValue : '修改提现卡信息',	
        skin : 'comfirm_pop',	
        content : '<div class="l_h26" style="padding-left: 30px;"><i class="mr_ico"></i><span class="TFS_mrtips"><strong class="c_tfscolor f_16">对不起,提现卡绑定失败</strong>失败原因：银行卡信息或持卡人姓名不正确不正正宗确。银行卡信息或持卡人。</span></div>',
		ok : function(){	
			window.open("提现绑定失败.html");
			$('.withdrawBtn').text('提现卡审核中···').removeClass('blue decorationUnderline withdrawBtn').addClass('c_999').attr('href','javascript:void(0)').removeAttr('target','_blank"');
        },
        cancel : function(){
          	$(".withdrawBtn").text('提现卡绑定失败').removeClass('blue withdrawBtn').addClass('MyAssets_red');
        }
        });

		$('.withdrawBtn').on('click',function(){
			window.open("提现绑定失败.html");
			$(this).text('提现卡审核中···').removeClass('blue decorationUnderline withdrawBtn').addClass('c_999').attr('href','javascript:void(0)').removeAttr('target','_blank')
		})
</script>

</body>
</html>
