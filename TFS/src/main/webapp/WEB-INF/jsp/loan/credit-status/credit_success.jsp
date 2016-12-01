<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>SAAS后台管理</title>
	 <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
<body>

<div class="LoanHome">
	<ul>
		<li>
			<div class="LH_t"><p>可用授信额度（元）</p>1,000,000</div>
			<div class="LH_c p_t20">
				<div class="xi"></div>
				<dl>
					<dd><p>授信额度：</p><p>4,000,000  元</p></dd>
					<dd><p>有效期至：</p><p>2018-12-31</p></dd>
				</dl>
			</div>
		</li>
		<li>
			<div class="LH_t"><p>待还本金（元）</p>1,000,000</div>
			<div class="LH_c ">
				<div class="xi"></div>
				<dl>
					<dd><p>包房贷待还本金：</p><p>4,000,000  元</p><p><a href="泰坦金融-我的贷款首页-申请包房贷款.html" class="blue ">申请包房贷</a></p></dd>
					<dd><p>运营贷待还本金：</p><p>4,000,000  元</p></dd>
				</dl>
			</div>
		</li>
		<li>
			<div class="LH_c xi p_t20">
				<div class="xi"></div>
				<dl>
					<dd><p>七日内待还笔数</p><p class="f_20">2</p></dd>
					<dd><p>参考七日内待还本息(元)</p><p class="f_20">1,000,000</p></dd>
				</dl>
			</div>
			<div class="LH_c p_t20">
				<div class="xi"></div>
				<dl>
					<dd><p>已逾期笔数</p><p class="f_20">2</p></dd>
					<dd><p>参考已逾期待还本息(元)</p><p class="f_20">1,000,000</p></dd>
				</dl>
			</div>
		</li>
	</ul>
	<div class="clear"></div>
</div>	
	
	
	<div class="MyAssets_list m_t35 LoanHome_list" id="ID1">
		<h3 class="MyAssets_list_tab">
			<span class="on">
				待还款
				<i></i>
			</span>
			<span>
				贷款申请审核中
				<i></i>
			</span>
			<span>
				已还清
				<i></i>
			</span>
			<span>
				全部贷款
				<i></i>
			</span>					
		</h3>
		<div>
		<div class="MyAssets_tab" style="">
		<div class="MyAssets_list_Options ">
			<div class="J_Section " >
				<dl class="J_date_list fl m_r15">
					<dd>
						<label for="" class="fl">还款到期日：</label>
						<input type="text"  class="text w_160 text_calendar fl">
						<label for="" class="S_digit">至</label>
						<input type="text" name="" class="text w_160 text_calendar fl" >
					</dd>
				</dl>
				<dl class="J_date_list ">
					<dd>
						<label for="" class="fl">贷款时间：</label>
						<input type="text"  class="text w_160 text_calendar fl">
						<label for="" class="S_digit">至</label>
						<input type="text" name="" class="text w_160 text_calendar fl" >
					</dd>
				</dl>
				
			</div>
			<div class="MyAssets_list_inp01 fl ml10">
                <select class="select w_120">
                <option value="贷款类型">贷款类型</option>
                <option value="包房贷">包房贷</option>
                <option value="运营贷">运营贷</option>
              </select>
            </div>
			<a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)">&nbsp;</a>			
		</div>		
		<div style="margin-right:17px;margin-left: 17px; " class="label">
				<table width="100%" cellspacing="0" border="0">
				<colgroup>					
					<col width="50">					
					<col width="9%">
					<col width="9%">
					<col width="11%">
					<col width="7%">
					<col width="10%">
					<col width="15%">
					<col width="15%">
					<col width="11%">			
					<col width="">
				</colgroup>
				<tbody>
				<tr>					
					<td class="tdl">序号</td>
					<td width="tdl">还款到期日</td>
					<td class="tdr">待还本金(元)</td>
					<td class="tdr">参考待还本息(元)</td>
					<td></td>
					<td class="tdl">贷款类型</td>
					<td class="tdl">还款方式</td>
					<td class="tdl">放款时间</td>
					<td class="tdl">贷款状态</td>									
					<td class="tdl">操作</td>		
				</tr>
			</tbody></table>
		</div>
		<div class="MyAssets_listContent">
			<div style="display:none; margin:150px auto; width:250px;" class="no_data1">
			    <p><i class="c_999">暂无待还款项</i></p>			  
		    </div>
			<table cellpadding="0" cellspacing="0" width="100%" class="MyAssets_listTable">
				<colgroup>					
					<col width="50">					
					<col width="9%">
					<col width="9%">
					<col width="11%">
					<col width="7%">
					<col width="10%">
					<col width="15%">
					<col width="15%">
					<col width="11%">			
					<col width="">
				</colgroup>
				<tr>				
					<td width="">1</td>
					<td width="">2016-07-30</td>
					<td class="tdr">872.00</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 180px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td class="">2016-07-30  12:25:23</td>
					<td><span class="MyAssets_red">已逾期</span></td>					
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-详情.html">详情</a>  <a class="blue decorationUnderline" href="泰坦金融-我的贷款首页-还款.html">还款</a></td>
				</tr>
				<tr>				
					<td width="">2</td>
					<td width="">2016-07-30</td>
					<td class="tdr">872.00</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 180px" title="到期日前随借随还">到期日前随借随还</span></td>
					<td class="">2016-07-30  12:25:23</td>
					<td><span class="MyAssets_red">已逾期</span></td>					
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-详情-未通过.html">详情</a>  <a class="blue decorationUnderline">还款</a></td>
				</tr>
				<tr>				
					<td width="">3</td>
					<td width="">2016-07-30</td>
					<td class="tdr">872.00</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 180px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td class="">2016-07-30  12:25:23</td>
					<td><span class="MyAssets_red">已逾期</span></td>					
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-已还清.html">详情</a>  <a class="blue decorationUnderline">还款</a></td>
				</tr>
				<tr>				
					<td width="">4</td>
					<td width="">2016-07-30</td>
					<td class="tdr">872.00</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 180px" title="到期日前随借随还">到期日前随借随还</span></td>
					<td class="">2016-07-30  12:25:23</td>
					<td><span class="MyAssets_red">已逾期</span></td>					
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-详情.html">详情</a>  <a class="blue decorationUnderline">还款</a></td>
				</tr>	
				<tr>				
					<td width="">5</td>
					<td width="">2016-07-30</td>
					<td class="tdr">872.00</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 180px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td class="">2016-07-30  12:25:23</td>
					<td>已放款</td>					
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-已还清.html">详情</a>  <a class="blue decorationUnderline">还款</a></td>
				</tr>
				<tr>				
					<td width="">6</td>
					<td width="">2016-07-30</td>
					<td class="tdr">872.00</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 180px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td class="">2016-07-30  12:25:23</td>
					<td>已放款</td>					
					<td class=""><a class="blue decorationUnderline m_r10"  href="泰坦金融-我的贷款首页-已还清.html">详情</a>  <a class="blue decorationUnderline">还款</a></td>
				</tr>
				<tr>				
					<td width="">7</td>
					<td width="">2016-07-30</td>
					<td class="tdr">872.00</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 180px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td class="">2016-07-30  12:25:23</td>
					<td>已放款</td>					
					<td class=""><a class="blue decorationUnderline m_r10">详情</a>  <a class="blue decorationUnderline">还款</a></td>
				</tr>
				<tr>				
					<td width="">8</td>
					<td width="">2016-07-30</td>
					<td class="tdr">872.00</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 180px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td class="">2016-07-30  12:25:23</td>
					<td>已放款</td>					
					<td class=""><a class="blue decorationUnderline m_r10">详情</a>  <a class="blue decorationUnderline">还款</a></td>
				</tr>	
				<tr>				
					<td width="">9</td>
					<td width="">2016-07-30</td>
					<td class="tdr">872.00</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 180px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td class="">2016-07-30  12:25:23</td>
					<td>已放款</td>					
					<td class=""><a class="blue decorationUnderline m_r10">详情</a>  <a class="blue decorationUnderline">还款</a></td>
				</tr>
				<tr>				
					<td width="">10</td>
					<td width="">2016-07-30</td>
					<td class="tdr">872.00</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 180px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td class="">2016-07-30  12:25:23</td>
					<td>已放款</td>					
					<td class=""><a class="blue decorationUnderline m_r10">详情</a>  <a class="blue decorationUnderline">还款</a></td>
				</tr>
				<tr>				
					<td width="">11</td>
					<td width="">2016-07-30</td>
					<td class="tdr">872.00</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 180px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td class="">2016-07-30  12:25:23</td>
					<td>已放款</td>					
					<td class=""><a class="blue decorationUnderline m_r10">详情</a>  <a class="blue decorationUnderline">还款</a></td>
				</tr>
				<tr>				
					<td width="">12</td>
					<td width="">2016-07-30</td>
					<td class="tdr">872.00</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 180px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td class="">2016-07-30  12:25:23</td>
					<td>已放款</td>					
					<td class=""><a class="blue decorationUnderline m_r10">详情</a>  <a class="blue decorationUnderline">还款</a></td>
				</tr>
				<tr>				
					<td width="">13</td>
					<td width="">2016-07-30</td>
					<td class="tdr">872.00</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 180px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td class="">2016-07-30  12:25:23</td>
					<td>已放款</td>					
					<td class=""><a class="blue decorationUnderline m_r10">详情</a>  <a class="blue decorationUnderline">还款</a></td>
				</tr>
				<tr>				
					<td width="">14</td>
					<td width="">2016-07-30</td>
					<td class="tdr">872.00</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 180px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td class="">2016-07-30  12:25:23</td>
					<td>已放款</td>					
					<td class=""><a class="blue decorationUnderline m_r10">详情</a>  <a class="blue decorationUnderline">还款</a></td>
				</tr>
				<tr>				
					<td width="">15</td>
					<td width="">2016-07-30</td>
					<td class="tdr">872.00</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 180px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td class="">2016-07-30  12:25:23</td>
					<td>已放款</td>					
					<td class=""><a class="blue decorationUnderline m_r10">详情</a>  <a class="blue decorationUnderline">还款</a></td>
				</tr>		
			</table>
		</div>
		</div>

		<div class="MyAssets_tab" style="display: none;">
		<div class="MyAssets_list_Options ">

			<div class="J_Section " >
				<dl class="J_date_list fl">
					<dd>
						<label for="" class="fl">贷款申请时间：</label>
						<input type="text"  class="text w_160 text_calendar fl">
						<label for="" class="S_digit">至</label>
						<input type="text" name="" class="text w_160 text_calendar fl" >
					</dd>
				</dl>			
				
			</div>
			<div class="MyAssets_list_inp01 fl ml10">
                <select class="select w_120">
                <option value="贷款类型">贷款类型</option>
                <option value="包房贷">包房贷</option>
                <option value="运营贷">运营贷</option>
              </select>
            </div>
			<a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)">&nbsp;</a>	
			
		</div>		
		<div style="margin-right:17px;margin-left: 17px; " class="label">
				<table width="100%" cellspacing="0" border="0">
				<colgroup>					
					<col width="50">					
					<col width="12%">
					<col width="11%">
					<col width="10%">
					<col width="13%">
					<col width="19%">
					<col width="15%">			
					<col width="">
				</colgroup>
				<tbody>
				<tr>					
					<td class="tdl">序号</td>
					<td width="tdl">贷款申请时间</td>
					<td class="tdr">贷款额度(元)</td>
					<td></td>
					<td class="tdl">贷款类型</td>
					<td class="tdl">还款方式</td>
					<td class="tdl">贷款状态</td>												
					<td class="tdl">操作</td>		
				</tr>
			</tbody></table>
		</div>
		<div class="MyAssets_listContent">
			<table cellpadding="0" cellspacing="0" width="100%" class="MyAssets_listTable">
				<colgroup>					
					<col width="50">					
					<col width="12%">
					<col width="11%">
					<col width="10%">
					<col width="13%">
					<col width="19%">
					<col width="15%">			
					<col width="">
				</colgroup>
				<tr>				
					<td width="">1</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 230px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td><span class="MyAssets_red">审核中</span></td>					
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-详情-审核中.html">详情</a>  <a class="blue decorationUnderline J_revocation">撤销申请</a></td>
				</tr>
				<tr>				
					<td width="">2</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 230px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td><span class="MyAssets_red">审核中</span></td>					
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-详情-审核中.html">详情</a>  <a class="blue decorationUnderline J_revocation">撤销申请</a></td>
				</tr>
				<tr>				
					<td width="">3</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 230px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td><span class="MyAssets_red">审核中</span></td>					
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-详情-审核中.html">详情</a>  <a class="blue decorationUnderline J_revocation">撤销申请</a></td>
				</tr>
				<tr>				
					<td width="">4</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 230px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td><span class="MyAssets_red">审核中</span></td>					
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-详情-审核中.html">详情</a>  <a class="blue decorationUnderline J_revocation">撤销申请</a></td>
				</tr>
				<tr>				
					<td width="">5</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 230px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td><span class="MyAssets_red">审核中</span></td>					
					<td class=""><a class="blue decorationUnderline m_r10">详情</a>  <a class="blue decorationUnderline J_revocation">撤销申请</a></td>
				</tr>
				<tr>				
					<td width="">6</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 230px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td><span class="MyAssets_red">审核中</span></td>					
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-详情-审核中.html">详情</a>  <a class="blue decorationUnderline J_revocation">撤销申请</a></td>
				</tr>
				<tr>				
					<td width="">7</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 230px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td><span class="MyAssets_red">审核中</span></td>					
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-详情-审核中.html">详情</a>  <a class="blue decorationUnderline J_revocation">撤销申请</a></td>
				</tr>
				<tr>				
					<td width="">8</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 230px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td><span class="MyAssets_red">审核中</span></td>					
					<td class=""><a class="blue decorationUnderline m_r10">详情</a>  <a class="blue decorationUnderline J_revocation">撤销申请</a></td>
				</tr>
				<tr>				
					<td width="">9</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 230px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td><span class="MyAssets_red">审核中</span></td>					
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-详情-审核中.html">详情</a>  <a class="blue decorationUnderline J_revocation">撤销申请</a></td>
				</tr>
				<tr>				
					<td width="">10</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 230px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td><span class="MyAssets_red">审核中</span></td>					
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-详情-审核中.html">详情</a>  <a class="blue decorationUnderline J_revocation">撤销申请</a></td>
				</tr>
				<tr>				
					<td width="">11</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 230px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td><span class="MyAssets_red">审核中</span></td>					
					<td class=""><a class="blue decorationUnderline m_r10">详情</a>  <a class="blue decorationUnderline J_revocation">撤销申请</a></td>
				</tr>
			</table>
		</div>
		</div>		

		<div class="MyAssets_tab" style="display: none;">
		<div class="MyAssets_list_Options ">

			<div class="J_Section " >
				<dl class="J_date_list fl m_r15">
					<dd>
						<label for="" class="fl">还款时间：</label>
						<input type="text"  class="text w_160 text_calendar fl">
						<label for="" class="S_digit">至</label>
						<input type="text" name="" class="text w_160 text_calendar fl" >
					</dd>
				</dl>
				<dl class="J_date_list ">
					<dd>
						<label for="" class="fl">放款时间：</label>
						<input type="text"  class="text w_160 text_calendar fl">
						<label for="" class="S_digit">至</label>
						<input type="text" name="" class="text w_160 text_calendar fl" >
					</dd>
				</dl>
				
			</div>
			<div class="MyAssets_list_inp01 fl ml10">
                <select class="select w_120">
                <option value="贷款类型">贷款类型</option>
                <option value="包房贷">包房贷</option>
                <option value="运营贷">运营贷</option>
              </select>
            </div>
			<a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)">&nbsp;</a>	
			
		</div>		
		<div style="margin-right:17px;margin-left: 17px; " class="label">
				<table width="100%" cellspacing="0" border="0">
				<colgroup>					
					<col width="50">					
					<col width="13%">
					<col width="10%">
				
					<col width="13%">
					<col width="9%">
					<col width="11%">
					<col width="15%">
					<col width="15%">			
					<col width="">
				</colgroup>
				<tbody>
				<tr>					
					<td class="tdl">序号</td>
					<td width="tdl">还款时间</td>
					<td class="tdr">贷款金额(元)</td>
				
					<td class="tdr">本息合计(元)</td>
					<td></td>
					<td class="tdl">贷款类型</td>
					<td class="tdl">还款方式</td>	
					<td class="tdl">放款时间</td>
					<td class="tdl">操作</td>		
				</tr>
			</tbody></table>
		</div>
		<div class="MyAssets_listContent">
			<table cellpadding="0" cellspacing="0" width="100%" class="MyAssets_listTable">
				<colgroup>					
					<col width="50">					
					<col width="13%">
					<col width="10%">
				
					<col width="13%">
					<col width="9%">
					<col width="11%">
					<col width="15%">
					<col width="15%">			
					<col width="">
				</colgroup>
				<tr>				
					<td width="">1</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 166px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td width="">2016-07-30  15:25:36</td>					
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-已还清.html">详情</a> </td>
				</tr>
				<tr>				
					<td width="">2</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 166px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td width="">2016-07-30  15:25:36</td>					
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-已还清.html">详情</a> </td>
				</tr>
				<tr>				
					<td width="">3</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 166px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td width="">2016-07-30  15:25:36</td>					
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-已还清.html">详情</a> </td>
				</tr>
				<tr>				
					<td width="">4</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 166px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td width="">2016-07-30  15:25:36</td>					
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-已还清.html">详情</a> </td>
				</tr>
				<tr>				
					<td width="">5</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
				
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 166px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td width="">2016-07-30  15:25:36</td>					
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-已还清.html">详情</a> </td>
				</tr>
				<tr>				
					<td width="">6</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
				
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 166px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td width="">2016-07-30  15:25:36</td>					
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-已还清.html">详情</a> </td>
				</tr>
				<tr>				
					<td width="">7</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
				
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 166px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td width="">2016-07-30  15:25:36</td>					
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-已还清.html">详情</a> </td>
				</tr>
				<tr>				
					<td width="">8</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
				
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 166px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td width="">2016-07-30  15:25:36</td>					
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-已还清.html">详情</a> </td>
				</tr>
				<tr>				
					<td width="">9</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
				
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 166px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td width="">2016-07-30  15:25:36</td>					
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-已还清.html">详情</a> </td>
				</tr>
				<tr>				
					<td width="">10</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
				
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 166px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td width="">2016-07-30  15:25:36</td>					
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-已还清.html">详情</a> </td>
				</tr>
			</table>
		</div>
		</div>

		<div class="MyAssets_tab" style="display: none;">
		<div class="MyAssets_list_Options ">

			<div class="J_Section " >
				<dl class="J_date_list fl m_r15">
					<dd>
						<label for="" class="fl">贷款申请时间：</label>
						<input type="text"  class="text w_160 text_calendar fl">
						<label for="" class="S_digit">至</label>
						<input type="text" name="" class="text w_160 text_calendar fl" >
					</dd>
				</dl>
				<dl class="J_date_list ">
					<dd>
						<label for="" class="fl">放款时间：</label>
						<input type="text"  class="text w_160 text_calendar fl">
						<label for="" class="S_digit">至</label>
						<input type="text" name="" class="text w_160 text_calendar fl" >
					</dd>
				</dl>
				
			</div>
			<div class="MyAssets_list_inp01 fl ml10">
                <select class="select w_120">
                <option value="贷款类型">贷款类型</option>
                <option value="包房贷">包房贷</option>
                <option value="运营贷">运营贷</option>
              </select>          
            </div>
            <div class="MyAssets_list_inp01 fl ml10">
                <select class="select w_120">
                <option value="贷款状态">贷款状态</option>
                <option value="审核中">审核中</option>
                <option value="已放款">已放款</option>
                <option value="未通过">未通过</option>
              </select>         
            </div>

            <a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)">&nbsp;</a>	
			<a href="javascript:void(0)" class="MyAssets_Export fr bacth_export_hotel J_export">
				<img src="../images/TFS/tfs_c01.png"></a>			

		</div>		
		<div style="margin-right:17px;margin-left: 17px; " class="label">
				<table width="100%" cellspacing="0" border="0">
				<colgroup>					
					<col width="50">					
					<col width="11%">
					<col width="9%">
					
					<col width="9%">
					
					<col width="9%">
					
					<col width="9%">
					<col width="5%">
					<col width="8%">
					<col width="14%">
					<col width="8%">			
					<col width="">
				</colgroup>
				<tbody>
				<tr>					
					<td class="tdl">序号</td>
					<td width="tdl">贷款申请时间</td>
					<td class="tdr">贷款额度(元)</td>
					
					<td class="tdr">已还本金(元)</td>
					
					<td class="tdr">已付利息(元)</td>
					
					<td class="tdr">剩余本金(元)</td>	
					<td></td>
					<td class="tdl">贷款类型</td>
					<td class="tdl">还款方式</td>
					<td class="tdl">贷款状态</td>
					<td class="tdl">操作</td>
				</tr>
			</tbody></table>
		</div>
		<div class="MyAssets_listContent">
			<table cellpadding="0" cellspacing="0" width="100%" class="MyAssets_listTable">
				<colgroup>					
					<col width="50">					
					<col width="11%">
					<col width="9%">
					
					<col width="9%">
					
					<col width="9%">
					
					<col width="9%">
					<col width="5%">
					<col width="8%">
					<col width="14%">
					<col width="8%">			
					<col width="">
				</colgroup>
				<tr>				
					<td width="">1</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 142px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td><span class="MyAssets_red">未通过</span></td>				
					<td class=""><a class="blue decorationUnderline m_r10 " href="泰坦金融-我的贷款首页-详情-未通过.html">详情</a> </td>
				</tr>
				<tr>				
					<td width="">2</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 142px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td>已放款</td>
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-详情-审核中.html">详情</a> <a class="blue decorationUnderline J_revocation">撤销申请</a> </td>
				</tr>
				<tr>				
					<td width="">3</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 142px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td>已放款</td>
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-详情-审核中.html">详情</a> <a class="blue decorationUnderline J_revocation">撤销申请</a> </td>
				</tr>		
				<tr>				
					<td width="">4</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 142px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td>已放款</td>
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-详情-审核中.html">详情</a> <a class="blue decorationUnderline J_revocation">撤销申请</a> </td>
				</tr>	
				<tr>				
					<td width="">5</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 142px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td>已放款</td>
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-详情-审核中.html">详情</a> <a class="blue decorationUnderline J_revocation">撤销申请</a> </td>
				</tr>	
				<tr>				
					<td width="">6</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 142px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td>已放款</td>
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-详情-审核中.html">详情</a> <a class="blue decorationUnderline J_revocation">撤销申请</a> </td>
				</tr>	
				<tr>				
					<td width="">7</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 142px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td>已放款</td>
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-详情-审核中.html">详情</a> <a class="blue decorationUnderline J_revocation">撤销申请</a> </td>
				</tr>	
				<tr>				
					<td width="">8</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 142px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td>已放款</td>
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-详情-审核中.html">详情</a> <a class="blue decorationUnderline J_revocation">撤销申请</a> </td>
				</tr>	
				<tr>				
					<td width="">9</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 142px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td>已放款</td>
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-详情-审核中.html">详情</a> <a class="blue decorationUnderline J_revocation">撤销申请</a> </td>
				</tr>	
				<tr>				
					<td width="">10</td>
					<td width="">2016-07-30  15:25:36</td>
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					
					<td class="tdr">872.00</td>
					<td></td>
					<td width="" >包房贷</td>
					<td width="" ><span style="max-width: 142px" title="按月付息，到期还本">按月付息，到期还本</span></td>
					<td>已放款</td>
					<td class=""><a class="blue decorationUnderline m_r10" href="泰坦金融-我的贷款首页-详情-审核中.html">详情</a> <a class="blue decorationUnderline J_revocation">撤销申请</a> </td>
				</tr>	

			</table>
		</div>
		</div>	
		</div>
		<div id="kkpager" class="page_turning"></div>
		<div class="clear"></div>
	</div>
	<div class="LoanHome_AD">
		<ul>
			<li><img src="../images/TFS/loan02.jpg"></li>
			<li><img src="../images/TFS/loan01.jpg"></li>
		</ul>
		<div class="clear"></div>
	</div>

	<jsp:include page="/comm/static-js.jsp"></jsp:include>
	<script type="text/javascript">  
	
	$(function(){
	    var totalPage = 20;
	    var totalRecords = 390;
	    var pageNo = 1;
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
			
	        click : function(n){
	            // do something
	            //显示loading
	            top.F.loading.show();
	            setTimeout(function(){
	                //隐藏loding
	                top.F.loading.hide();
	            },500);
	            //手动选中按钮
	            this.selectPage(n);
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
	});
	/*
	$(function(){
	    console.log(88)
	    top.F.loading.show();
	});*/

 	//tab切换样式
    $(".MyAssets_list_tab span").on('click',function(){
    	var _this = $(this),
    		_index=_this.index();
    	_this.addClass('on').siblings().removeClass('on');
    	$('.MyAssets_tab').eq(_index).show().siblings('.MyAssets_tab').hide();              
    });	

    
    //添加日期锻
	new DateSection('.J_Section',{minDate:'2006-05-05',maxDate:''});
          
	//撤销申请
	$('.J_revocation').on('click',function(){
		window.top.createConfirm({
		title:'提示',
		content : '<div style="font-size:15px;line-height:30px;">确定要撤销贷款申请吗？</div>',
		okValue:'确定',
		cancelValue:'取消',
		ok : function(){
			top.F.loading.show();
		    setTimeout(function(){
		         top.F.loading.hide();
		       	 new window.top.Tip({ msg : '贷款申请已撤销'});
		    },1000)	
		},
		cancel : function(){
			
		}
		});
	})	
  
		
</script>
</body>
</html>