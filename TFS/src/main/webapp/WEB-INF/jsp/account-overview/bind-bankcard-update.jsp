<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
 <!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
		<!-- 绑卡 修改 -->
		<input type="hidden" id="actionType" name="actionType" value="${actionType }"/>
		<form id="J_form_update" class="demo_form" action="">
		<div class="binding-bank-modal platform-binding-bank-modal">
			<div class="title"><p>绑定提现银行卡</p><div class="title-icon"><i class="iconfont icon-sc close"></i></div></div>
			<div class="content">
				<div class="left">账户类型:</div>
				<c:if test="${orgSub.usertype==1}">
					<div class="right content-icon">企业</div>
				</c:if>
				<c:if test="${orgSub.usertype==2}">
					<div class="right content-icon">个人</div>
				</c:if>
			</div>
			<div class="bank-type">
				<div class="content">
					<div class="left">开户名:</div>
					<div class="right">${orgSub.orgname}</div>
				</div>
				<div class="content">
					<div class="left">开户银行:</div>
					<div class="right">
						<p> <input type="hidden" class="bankName" ><input type="text"  class="bankCode" placeholder="请选择开户银行" datatype="*" errormsg="请选择开户银行" /><s class="iconfont icon-sc input-empty isShow"></s></p>
					</div>
				</div>
				<!-- 对公  -->
				<c:if test="${orgSub.usertype==1}">
					<div class="content">
						<div class="left">支行信息:</div>
						<div class="right">
							<input class="fl city_code" type="text" placeholder="选择城市" datatype="*1-20" errormsg="请选择城市" style="width: 108px"/>
							<input name="branch_name" type="hidden" class="branch_name"><input class="select-fr fl branch_code" type="text"  name="branch_code"  datatype="*1-20" errormsg="请选择支行" placeholder="请选择支行" placeholder="支行信息"/>
							<input name="city_name"  type="hidden" class="city_name">
						</div>
					</div>
				</c:if>
					<div class="content">
						<div class="left">银行卡号:</div>
						<div class="right">
							<p><input type="text"  class="bank-input accountnumber" maxlength="16" placeholder="输入银行卡号" datatype="*" errormsg="请填写银行卡号" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/><s class="iconfont icon-sc input-empty isShow"></s></p>
						</div>
					</div>
					<div class="content">
						<div class="left"></div>
						<div class="right">
							<button type="button" class="button-btn prohibit" disabled="true" onclick="saveBindCard();">提交</button>
						</div>
					</div>
				
			</div>
		</div>
		</form>
	<script type="text/javascript">
		var bankAutoComplete = null;
		var cityAutoComplete = null;
		var branchBankAutoComplete = null;
		var usertype='${orgSub.usertype}'; 
	//开户银行
	bankAutoComplete = new AutoComplete($('.bankCode'), {
	    url : '<%=basePath%>/account/getBankInfoList.shtml?bankType=1',
	    source : 'bankInfoDTOList',
	    key : 'bankCode',  //数据源中，做为key的字段
	    val : 'bankName', //数据源中，做为val的字段
	    width : 240,
	    height : 300,
	    autoSelectVal : true,
	    clickEvent : function(d, input){
	        input.attr('data-id', d.key);
	        $(".bankName").val(d.val);
	       	bc.checkSubmit();
	       	if($('.city_code')){
	       		showCityCode();
	       	}
	    }
	});
	
	if($('.city_code')){//企业
	   initCityAutoComplete();
	}
	
	
	function initCityAutoComplete(){
		//城市
		cityAutoComplete = new AutoComplete($('.city_code'), {
		    url : '<%=basePath%>/account/getCitys.shtml',
		    source : 'cityInfoDTOList',
		    key : 'cityCode',  //数据源中，做为key的字段
		    val : 'cityName', //数据源中，做为val的字段
		    width : 240,
		    height : 300,
		    autoSelectVal : true,
		    clickEvent : function(d, input){
		        input.attr('data-id', d.key);
		        input.attr('value',d.val.substring(d.val.indexOf("-")+1));
		        var arr = d.val.split("-");
		        $(".city_name").val(arr[arr.length-1]);
		        bc.checkSubmit();
		        showBranch();
		    }
		});
	}
	
	
	function showCityCode(){
		$(".branch_code").val("");
		$(".branch_code").attr("data-id","");
		$(".city_code").val("");
		$(".city_code").attr("data-id","");
		$(".city_code").show();
		$(".branch_name").val("");
		$(".city_name").val("");
		if(branchBankAutoComplete)
		{
			branchBankAutoComplete.remove();
		}
	}
	
	function showBranch(){
		$(".branch_code").val("");
		$(".branch_code").attr("data-id","");
		$(".branch_code").show();
		$(".branch_name").val("");
		if(branchBankAutoComplete)
		{
			branchBankAutoComplete.remove();
		}
		init_branch();
	}
	
	
	function init_branch(){
		var bankCode = $(".bankCode").attr("data-id");
		var cityCode = $(".city_code").attr("data-id");
		if(bankCode.length>0 && cityCode.length>0){
			//支行
			branchBankAutoComplete = new AutoComplete($('.branch_code'), {
			    url : '<%=basePath%>/account/getBankInfoList.shtml?bankCity='+cityCode+'&parentCode='+bankCode,
			    source : 'bankInfoDTOList',
			    key : 'cityKey',  //数据源中，做为key的字段
			    val : 'bankName', //数据源中，做为val的字段
			    width : 240,
			    height : 300,
			    autoSelectVal : true,
			    clickEvent : function(d, input){
			        var arr = d.key.split(",");
			        input.attr('data-id', arr[0]);
			        $(".city_code").attr("data-id",arr[1]);
			        $(".branch_name").val(d.val);
			        bc.checkSubmit();
			    }
			});
		}
	}
	
	function getBankCardData(){
		var p={
				userType:usertype,
				bankCode : $(".bankCode").attr("data-id"),
				bankName:$(".bankName").val(),
				accountNumber:$(".accountnumber").val()
				};
				
		if(usertype=="1"){//企业
			p["branchCode"]=$(".branch_code").attr("data-id");
			p["cityCode"]=$(".city_code").attr("data-id");
			p["cityName"]=$(".city_name").val();
		}
		return p;
	}	
	
	function saveBindCard(){
		var paramData = getBankCardData();
		$.ajax({
    	    type: 'post',
    		url:'<%=basePath%>/account/bankCardBind.shtml',
    		dataType:'json',
    		data:paramData,
    		success:function(result){
        		if(result.code=="-1"){
        			new top.Tip({msg: result.msg, type: 2, timer: 2000});
        		}else{
        			bc.bindResultView();
        		}
        	}
	    });
    }
    bc.initUpdateBindCardPanel();
	</script>
</body>