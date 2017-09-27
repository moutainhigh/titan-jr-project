<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
 <!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
		<!-- 新增绑卡 -->
		<div class="binding-bank-modal register-binding-bank-modal ">
			<div class="title"><p>绑定提现银行卡</p><div class="title-icon"><i class="iconfont icon-sc close"></i></div></div>
			<div class="content">
				<div class="left">账户类型:</div>
				<div class="right content-icon">
					<span data-type="enterprise"><i class="iconfont icon-check selected"></i>企业</span><span data-type="personal"><i class="iconfont icon-check1"></i>个人</span>
				</div>
			</div>
			<!-- 企业 -->
			<div class="bank-type enterprise-modal">
				<form id="J_form_enterprise" class="demo_form" action="">
					<div class="content">
						<div class="left">开户名:</div>
						<div class="right">
							<p><input type="text" class="orgName"  placeholder="输入企业银行开户名" datatype="*" errormsg="请填写开户名" /><s class="iconfont icon-sc input-empty isShow"></s></p>
						</div>
					</div>
					<div class="content">
						<div class="left">统一社会信用代码:</div>
						<div class="right">
							<p><input type="text"  class="credentialsNumber" placeholder="输入统一社会信用代码/营业执照编号" datatype="*" errormsg="请填写信用代码" /><s class="iconfont icon-sc input-empty isShow"></s></p>
						</div>
					</div>
					<div class="content">
						<div class="left">开户银行:</div>
						<div class="right">
							<p> <input type="hidden" class="bankName" ><input type="text"  class="bankCode" placeholder="请选择开户银行" datatype="*" errormsg="请选择开户银行" /><s class="iconfont icon-sc input-empty isShow"></s></p>
						</div>
					</div>
					<div class="content">
						<div class="left">支行信息:</div>
						<div class="right">
							<input class="fl city_code" type="text" placeholder="选择城市" datatype="*1-20" errormsg="请选择城市" style="width: 108px"/>
							<input name="branch_name" type="hidden" class="branch_name"><input class="select-fr fl branch_code" type="text"  name="branch_code"  datatype="*1-20" errormsg="请选择支行" placeholder="请选择支行" placeholder="支行信息"/>
							<input name="city_name"  type="hidden" class="city_name">
						</div>
					</div>
					<div class="content">
						<div class="left">银行卡号:</div>
						<div class="right">
							<p><input type="text"  class="bank-input accountnumber" maxlength="25" placeholder="输入银行卡号" datatype="*" errormsg="请填写银行卡号" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/><s class="iconfont icon-sc input-empty isShow"></s></p>
						</div>
					</div>
					<div class="content">
						<div class="left"></div>
						<div class="right">
							<button type="button" class="button-btn prohibit" disabled="true" onclick="saveBindCard();">提交</button>
						</div>
					</div>
				</form>
			</div>
			<!-- 个人-->
			<div class="bank-type personal-modal isShow">
				<form id="J_form_personal" class="demo_form" action="">
					<div class="content">
						<div class="left">姓名:</div>
						<div class="right">
							<p><input type="text"  class="orgName"  placeholder="输入姓名" datatype="*" errormsg="请填写姓名" /><s class="iconfont icon-sc input-empty isShow"></s></p>
						</div>
					</div>
					<div class="content">
						<div class="left">身份证号:</div>
						<div class="right">
							<p><input type="text"  class="credentialsNumber" placeholder="输入身份证号" datatype="*" afterPassed="cardIdValidate" errormsg="请填写身份证号"/><s class="iconfont icon-sc input-empty isShow"></s></p>
						</div>
					</div>
					<div class="content">
						<div class="left">开户银行:</div>
						<div class="right">
							<p> <input type="hidden" class="bankName" ><input type="text"  class="bankCode" placeholder="请选择开户银行" datatype="*" errormsg="请选择开户银行" /><s class="iconfont icon-sc input-empty isShow"></s></p>
						</div>
					</div>
					<div class="content">
						<div class="left">银行卡号:</div>
						<div class="right">
							<p><input type="text"  class="bank-input accountnumber" maxlength="25" placeholder="输入银行卡号" datatype="*" errormsg="请填写银行卡号" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/><s class="iconfont icon-sc input-empty isShow"></s></p>
						</div>
					</div>
					<div class="content">
						<div class="left"></div>
						<div class="right">
							<button type="button" class="button-btn prohibit" disabled="true" onclick="saveBindCard();">去提现</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	
	<script type="text/javascript">
		var enterpriseBankAutoComplete = null;
		var personalBankAutoComplete = null;
		var cityAutoComplete = null;
		var branchBankAutoComplete = null;
		var usertype='${orgSub.usertype}'; 
	//企业开户银行
	enterpriseBankAutoComplete = new AutoComplete($('#J_form_enterprise .bankCode'), {
	    url : '<%=basePath%>/account/getBankInfoList.shtml?bankType=1',
	    source : 'bankInfoDTOList',
	    key : 'bankCode',  //数据源中，做为key的字段
	    val : 'bankName', //数据源中，做为val的字段
	    width : 240,
	    height : 300,
	    autoSelectVal : true,
	    clickEvent : function(d, input){
	        input.attr('data-id', d.key);
	        $("#J_form_enterprise .bankName").val(d.val);
	       	bc.checkSubmit();
	       	if($('#J_form_enterprise .city_code').length>0){
	       		showCityCode();
	       	}
	    }
	});
	//个人开户行
	personalBankAutoComplete = new AutoComplete($('#J_form_personal .bankCode'), {
	    url : '<%=basePath%>/account/getBankInfoList.shtml?bankType=1',
	    source : 'bankInfoDTOList',
	    key : 'bankCode',  //数据源中，做为key的字段
	    val : 'bankName', //数据源中，做为val的字段
	    width : 240,
	    height : 300,
	    autoSelectVal : true,
	    clickEvent : function(d, input){
	        input.attr('data-id', d.key);
	        $("#J_form_personal .bankName").val(d.val);
	       	bc.checkSubmit();
	    }
	});
	if($('#J_form_enterprise .city_code').length>0){
	   initCityAutoComplete();
	}
	//支行
	function initCityAutoComplete(){
		//城市
		cityAutoComplete = new AutoComplete($('#J_form_enterprise .city_code'), {
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
		        $("#J_form_enterprise .city_name").val(arr[arr.length-1]);
		        bc.checkSubmit();
		        showBranch();
		    }
		});
	}
	
	
	function showCityCode(){
		$("#J_form_enterprise .branch_code").val("");
		$("#J_form_enterprise .branch_code").attr("data-id","");
		$("#J_form_enterprise .city_code").val("");
		$("#J_form_enterprise .city_code").attr("data-id","");
		$("#J_form_enterprise .city_code").show();
		$("#J_form_enterprise .branch_name").val("");
		$("#J_form_enterprise .city_name").val("");
		if(branchBankAutoComplete)
		{
			branchBankAutoComplete.remove();
		}
	}
	
	function showBranch(){
		$("#J_form_enterprise .branch_code").val("");
		$("#J_form_enterprise .branch_code").attr("data-id","");
		$("#J_form_enterprise .branch_code").show();
		$("#J_form_enterprise .branch_name").val("");
		if(branchBankAutoComplete)
		{
			branchBankAutoComplete.remove();
		}
		init_branch();
	}
	
	
	function init_branch(){
		var bankCode = $("#J_form_enterprise .bankCode").attr("data-id");
		var cityCode = $("#J_form_enterprise .city_code").attr("data-id");
		if(bankCode.length>0 && cityCode.length>0){
			//支行
			branchBankAutoComplete = new AutoComplete($('#J_form_enterprise .branch_code'), {
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
			        $("#J_form_enterprise .city_code").attr("data-id",arr[1]);
			        $("#J_form_enterprise .branch_name").val(d.val);
			        bc.checkSubmit();
			    }
			});
		}
	}
	//个人和企业注册数据
	function getBankCardData(){
		var t = sessionStorage.getItem("type");
		var fid=bc.getformId();
		var p={
				userType:sessionStorage.getItem("type")=='enterprise'?1:2,
				userName:$("#"+fid+" .orgName").val(),
				credentialsNumber:$("#"+fid+" .credentialsNumber").val(),
				bankCode : $("#"+fid+" .bankCode").attr("data-id"),
				bankName:$("#"+fid+" .bankName").val(),
				accountNumber:$("#"+fid+" .accountnumber").val()
				};
		if(t=="enterprise"){//企业
			p["branchCode"]=$("#"+fid+" .branch_code").attr("data-id");
			p["cityCode"]=$("#"+fid+" .city_code").attr("data-id");
			p["cityName"]=$("#"+fid+" .city_name").val();
			 
		}
		return p;
	}	
	
	function saveBindCard(){
		F.loading.show();
		var paramData = getBankCardData();
		$.ajax({
    	    type: 'post',
    		url:'<%=basePath%>/account/bankCardBind.shtml',
    		dataType:'json',
    		data:paramData,
    		success:function(result){
        		if(result.code=="-1"){
        			var fid=bc.getformId();
        			var t = sessionStorage.getItem("type");
        			var formv ;
        			if(t=="enterprise"){//企业
        				formv = bc.personalValid;
        			}else{
        				formv = bc.enterpriseValid;
        			}
        			formv._setErrorStyle($("#"+fid+" .accountnumber"),result.msg);
        		}else{
        			if(paramData.userType=='2'){//个人表示绑卡成功
        				bc.close();
        				window.location.reload();
        			}else{
        				bc.bindResultView();
        			}
        		}
        	},
        	complete:function()
			{
				F.loading.hide();
			}
	    });
    }
    //校验身份证号码
    function cardIdValidate(value, inputDom){
    	var reg = /^\d{15}$|^\d{18}$/g;
    	bc.checkSubmit();
    	if(!reg.test(value)){
    		$(inputDom).parent().find("span").append("<p>非身份证绑卡验证需要人工复核，请联系金融运营人员协助确认</p>");
    		return true;
    	}
    	return true;
    }
    
    bc.initBindCardPanel();
	</script>
</body>