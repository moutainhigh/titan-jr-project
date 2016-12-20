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
			<div class="LH_t"><p>可用授信额度（元）</p> <fmt:formatNumber value="${creditOrder.actualAmount /100}"  pattern="#,##0.00#" /></div>
			<div class="LH_c p_t20">
				<div class="xi"></div>
				<dl>
					<dd><p>授信额度：</p><p>  <fmt:formatNumber value="${creditOrder.amount /100}"  pattern="#,##0.00#" />  元</p></dd>
					<dd><p>有效期至：</p><p><fmt:formatDate value="${creditOrder.expireTime}" pattern="yyyy-MM-dd" /></p></dd>
				</dl>
			</div>
		</li>
		<li>
			<div class="LH_t"><p>待还本金（元）</p><span id="loanAmount" amount="#,##0.00#"></span></div>
			<div class="LH_c ">
				<div class="xi"></div>
				<dl>
					<dd><p>包房贷待还本金：</p><p  id="ROOM_PACK" amount="#,##0.00#">0.00 元</p><p><a href="<%=basePath %>/loan_apply/main.shtml" class="blue ">申请包房贷</a></p></dd>
					<dd><p>运营贷待还本金：</p><p  id="OPERACTION" amount="#,##0.00#">0.00 元</p></dd>
				</dl>
			</div>
		</li>
		<li>	
			<div class="LH_c xi p_t20">
				<div class="xi"></div>
				<dl>
					<dd><p>七日内待还笔数</p><p class="f_20"  id="sevenDaysNum"></p></dd>
					<dd><p>参考七日内待还本息(元)</p><p class="f_20"  id="sevenDaysAmount" amount="#,##0.00#"></p></dd>
				</dl>
			</div>
			<div class="LH_c p_t20">
				<div class="xi"></div>
				<dl>
					<dd><p>已逾期笔数</p><p class="f_20" id="expiryNum"></p></dd>
					<dd><p>参考已逾期待还本息(元)</p><p class="f_20" id="expiryAmount" amount="#,##0.00#"></p></dd>
				</dl>
			</div>
		</li>
	</ul>
	<div class="clear"></div>
</div>	
	
	
	<div class="MyAssets_list m_t35 LoanHome_list" id="ID1">
		<h3 class="MyAssets_list_tab">
			<span class="on" key="loan-payment">
				<A name="zkback">待还款</A>
				<i></i>
			</span>
			<span key="loan-audit">
				贷款申请审核中
				<i></i>
			</span>
			<span key="loan-over">
				已还清
				<i></i>
			</span>
			<span key="loan-all">
				全部贷款
				<i></i>
			</span>					
		</h3>
		
	
		
		<div class="MyAssets_tab" style="">
		
			<div class="MyAssets_list_Options"  key="loan-payment">
				<input type="hidden" field="currPage" value="1" id="loan-payment-Page"/>
				<div class="J_Section " id="loan-payment">
					<dl class="J_date_list fl m_r15">
						<dd>
							<label for="" class="fl">还款到期日：</label> <input type="text"
								class="text w_160 text_calendar fl"  field="beginActualRepaymentDate"> <label for=""
								class="S_digit">至</label> <input type="text"  field="endActualRepaymentDate"
								class="text w_160 text_calendar fl">
						</dd>
					</dl>
				</div>	
				<div class="J_Section" id="loan-payment1" >
					<dl class="J_date_list ">
						<dd>
							<label for="" class="fl">放款时间：</label> <input type="text"
								class="text w_160 text_calendar fl"  field="beginRelMoneyTime"> <label for=""
								class="S_digit">至</label> <input type="text"  field="endRelMoneyTime"
								class="text w_160 text_calendar fl">
						</dd>
					</dl>
		
				</div>
				<div class="MyAssets_list_inp01 fl ml10">
					<select class="select w_120" field="productType">
						<option value="">贷款类型</option>
						<option value="10001">包房贷</option>
						<option value="10002">运营贷</option>
					</select>
				</div>
				<a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search"
					href="javascript:void(0)" onclick="loadLoanInfiList('loan-payment')">&nbsp;</a>
			</div>
			
			
				

			
		
			<div class="MyAssets_list_Options"  style="display:none" key="loan-audit">
			<input type="hidden" field="currPage" value="1" id="loan-audit-Page"/>
				<div class="J_Section " id="loan-auditDate">
					<dl class="J_date_list fl">
						<dd>
							<label for="" class="fl">贷款申请时间：</label> <input type="text"
								class="text w_160 text_calendar fl" filed='beginCreateTime'> <label for=""
								class="S_digit">至</label> <input type="text"  filed='endCreateTime'
								class="text w_160 text_calendar fl">
						</dd>
					</dl>
		
				</div>
				<div class="MyAssets_list_inp01 fl ml10">
					<select class="select w_120" field="productType">
						<option value="">贷款类型</option>
						<option value="10001">包房贷</option>
						<option value="10002">运营贷</option>
					</select>
				</div>
				<a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search"
					href="javascript:void(0)"  onclick="loadLoanInfiList('loan-audit')">&nbsp;</a>
			</div>

			<div class="MyAssets_list_Options "  style="display:none"  key="loan-over">
			<input type="hidden" field="currPage" value="1" id="loan-over-Page"/>
				<div class="J_Section"  id="loan-overDate">
					<dl class="J_date_list fl m_r15">
						<dd>
							<label for="" class="fl">还款时间：</label>
							<input type="text"  class="text w_160 text_calendar fl" field="beginLastRepaymentDate">
							<label for="" class="S_digit">至</label>
							<input type="text" name="" class="text w_160 text_calendar fl" field="endLastRepaymentDate" >
						</dd>
					</dl>
					</div>
				<div  class="J_Section "  id="loan-overDate1">
					<dl class="J_date_list  ">
						<dd>
							<label for="" class="fl">放款时间：</label>
							<input type="text"  class="text w_160 text_calendar fl" field="beginRelMoneyTime">
							<label for="" class="S_digit">至</label>
							<input type="text" name="" class="text w_160 text_calendar fl"   field="endRelMoneyTime">
						</dd>
					</dl>
				</div>
				<div class="MyAssets_list_inp01 fl ml10">
	             	<select class="select w_120" field="productType">
						<option value="">贷款类型</option>
						<option value="10001">包房贷</option>
						<option value="10002">运营贷</option>
					</select>
	            </div>
				<a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)" onclick="loadLoanInfiList('loan-over')">&nbsp;</a>	
			</div>		
			<div class="MyAssets_list_Options " key="loan-all" style="display:none">
				<input type="hidden" field="currPage" value="1"  id="loan-all-Page"/>
				<div class="J_Section " id="loan-all" >
					<dl class="J_date_list fl m_r15">
						<dd>
							<label for="" class="fl">贷款申请时间：</label>
							<input type="text"  class="text w_160 text_calendar fl" field='beginCreateTime'>
							<label for="" class="S_digit">至</label>
							<input type="text" name="" class="text w_160 text_calendar fl" field='endCreateTime'>
						</dd>
					</dl>
					</div>
					<div  class="J_Section " id="loan-all1" >
						<dl class="J_date_list ">
							<dd>
								<label for="" class="fl">放款时间：</label>
								<input type="text"  class="text w_160 text_calendar fl" field='beginRelMoneyTime'>
								<label for="" class="S_digit">至</label>
								<input type="text" name="" class="text w_160 text_calendar fl" field='endRelMoneyTime'>
							</dd>
						</dl>
					
				</div>
				<div class="MyAssets_list_inp01 fl ml10">
	               <select class="select w_120" field="productType">
						<option value="">贷款类型</option>
						<option value="10001">包房贷</option>
						<option value="10002">运营贷</option>
					</select>   
	            </div>
	            <div class="MyAssets_list_inp01 fl ml10">
	                <select class="select w_120" field="loanStatus">
	                <option value="">贷款状态</option>
	                <option value="1">审核中</option>
	                <option value="6">已放款</option>
	                <option value="7">未通过</option>
	              </select>         
	            </div>
	
	            <a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)" onclick="loadLoanInfiList('loan-all')">&nbsp;</a>	
				<a href="javascript:void(0)" onclick="exportExcel('loan-all');" class="MyAssets_Export fr bacth_export_hotel J_export" target="_blank">
					<img src="<%=cssSaasPath %>/images/TFS/tfs_c01.png"></a>			
					
			</div>
			
			
		<div id="loanInfoList">
		
		</div>
	</div>
		
		
		
		<div class="clear"></div>
	</div>
	<div class="LoanHome_AD" style="display:none;">
		<ul>
			<li><img src="../images/TFS/loan02.jpg"></li>
			<li><img src="../images/TFS/loan01.jpg"></li>
		</ul>
		<div class="clear"></div>
	</div>

	<jsp:include page="/comm/static-js.jsp"></jsp:include>
	<script type="text/javascript"> 
	
	window.onload = function()
	{
		var reqUrl  = window.location.href;
		
		if(reqUrl.indexOf("#zkback") != -1)
		{
			setTimeout(function(){
				window.location.href=window.location.href;
			} , 500);
			
		}
	}
	
	function exportExcel(pageKey)
	{
		var reqParam = "";
		$('.MyAssets_list_Options[key='+pageKey+']').find('[field]').each(function(){
			
			if($(this).val() != null && $(this).val() != '')
			{
				reqParam += '&'+$(this).attr('field')+'='+$(this).val();
			}
		});
		window.open('<%=basePath%>/loan/exportExcel.shtml'+"?DateTime="+new Date().getTime()+"&pageKey=" + pageKey+reqParam);
		
	}
	var currPageKey = "";
	function loadLoanInfiList(pageKey, callBack)
	{
		var reqParam = "";
		
		$('.MyAssets_list_Options[key='+pageKey+']').find('[field]').each(function(){
			
			if($(this).val() != null && $(this).val() != '')
			{
				reqParam += '&'+$(this).attr('field')+'='+$(this).val();
			}
		});
		
		top.F.loading.show();
		  $.ajax({
		    context: document.body,
			url :  '<%=basePath%>/loan/getLoanInfoList.shtml'+"?DateTime="+new Date().getTime()+"&pageKey=" + pageKey+reqParam,
			dataType : 'html',
			success : function(html) {
				
				if(callBack)
				{
					callBack();
				}
				$('#loanInfoList').html(html);
				
				currPageKey = pageKey;
				
				if(pageObj)
				{
					pageObj.selectPage($('#'+currPageKey+"-Page").val());
				}
				setTimeout(function(){top.F.loading.hide();},500);
				
			},
			error : function() {
				new Tip({
					msg : '加载列表失败！',
					type : 3
				});
				top.F.loading.hide();
			}
		 });
	}
	
	function pageClick(n)
	{
		$('#'+currPageKey+"-Page").val(n);
		loadLoanInfiList(currPageKey);
	}
	
	
	$(function(){
		
		  $.ajax({
			type : 'get',
			url :  '<%=basePath%>/loan/loanStatInfo.shtml'+"?DateTime="+new Date().getTime(),
			dataType : 'json',
			success : function(obj) {
					for ( var name in obj) {
						if (name == 'productAmount') {
							if(obj[name])
							{
								for(var productName in obj[name])
								{
									$('#' + productName).text(formatCurrency(obj[name][productName]/100)+" 元");
								}
							}
						} else {
							if($('#' + name).attr("amount"))
							{
								$('#' + name).text(formatCurrency(obj[name]/100));
							}else{
							
								$('#' + name).text(obj[name]);
							}
						}
					}
				},
				error : function() {
					new Tip({
						msg : '数据保存失败！',
						type : 3
					});
				}
			});
		  loadLoanInfiList('loan-payment');
		});

		//tab切换样式
		$(".MyAssets_list_tab span").on(
				'click',
				function() {
					var _this = $(this);
					var pageKey =  $(this).attr("key");
					_index = _this.index();
				
					 loadLoanInfiList(pageKey  , function(){
						 
						 _this.addClass('on').siblings().removeClass('on');
							$('.MyAssets_tab').eq(_index).show().siblings('.MyAssets_tab').hide();
							$('.MyAssets_list_Options').eq(_index).show().siblings('.MyAssets_list_Options').hide();
							
				 })
		});
	
		$('.J_Section').each(function(){
			//添加日期锻
			new DateSection('#' + $(this).attr('id'), {
				minDate : '2010-01-01',
				maxDate : ''
			});
		});
		
	
	</script>
</body>
</html>
