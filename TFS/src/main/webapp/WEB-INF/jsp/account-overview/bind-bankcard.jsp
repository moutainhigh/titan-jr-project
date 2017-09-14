<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
 <!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
	 	<c:if test="${actionType=='add'}">
		<input type="hidden" id="orgCardId" name="orgCardId" value="${orgCardId }"/>
		<!-- 新增绑卡 弹框-->
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
							<p><input type="text"  class="bank-input accountnumber" maxlength="16" placeholder="输入银行卡号" datatype="*" errormsg="请填写银行卡号" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/><s class="iconfont icon-sc input-empty isShow"></s></p>
						</div>
					</div>
					<div class="content">
						<div class="left"></div>
						<div class="right">
							<button type="button" class="button-btn prohibit" disabled="true">提交</button>
							<button type="submit" class="submit-btn" style="opacity: 0;"></button>
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
							<p><input type="text"  class="credentialsNumber" placeholder="输入身份证号" datatype="*" errormsg="请填写身份证号"/><s class="iconfont icon-sc input-empty isShow"></s></p>
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
							<p><input type="text"  class="bank-input accountnumber" maxlength="16" placeholder="输入银行卡号" datatype="*" errormsg="请填写银行卡号" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/><s class="iconfont icon-sc input-empty isShow"></s></p>
						</div>
					</div>
					<div class="content">
						<div class="left"></div>
						<div class="right">
							<button type="button" class="button-btn prohibit" disabled="true">提交</button>
							<button type="submit" class="submit-btn" style="opacity: 0;"></button>
						</div>
					</div>
				</form>
				<p class="abnormal-bank-prompt">非身份证绑卡验证需要人工复核，请联系金融运营人员协助确认</p>
			</div>
		</div>
		</c:if>
		<!--钱包平台绑卡 修改 -->
		<c:if test="${actionType=='update'}">
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
						<select name="" >
							<option value="">请选择开户银行</option>
						</select>
					</div>
				</div>
				<div class="content">
					<div class="left">支行信息:</div>
					<div class="right">
						<input class="fl" type="text" placeholder="选择城市" style="width: 108px"/>
						<select class="select-fr fl" name="">
							<option value="" style="color: #777;">支行信息</option>
						</select>
					</div>
				</div>
				<form id="J_form3" class="demo_form" action="test.php">
					<div class="content">
						<div class="left">银行卡号:</div>
						<div class="right">
							<p><input type="text"  class="bank-input" maxlength="16" placeholder="输入银行卡号" datatype="*" errormsg="请填写银行卡号" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/><s class="iconfont icon-sc input-empty isShow"></s></p>
						</div>
					</div>
					<div class="content">
						<div class="left"></div>
						<div class="right">
							<button type="button" class="button-btn prohibit" disabled="true">提交</button>
							<button type="submit" class="submit-btn" style="opacity: 0;"></button>
						</div>
					</div>
				</form>
			</div>
		</div>
		</c:if>
	
	<script type="text/javascript">
		var bankAutoComplete = null;
		var cityAutoComplete = null;
		var branchBankAutoComplete = null;
	//开户银行
	bankAutoComplete = new AutoComplete($('#bankCode'), {
	    url : '<%=basePath%>/account/getBankInfoList.shtml?bankType=1',
	    source : 'bankInfoDTOList',
	    key : 'bankCode',  //数据源中，做为key的字段
	    val : 'bankName', //数据源中，做为val的字段
	    width : 240,
	    height : 300,
	    autoSelectVal : true,
	    clickEvent : function(d, input){
	        input.attr('data-id', d.key);
	        $("#bankName").val(d.val);
	       	$("#branch_spec").show();
	       	bk.checkSubmit();
	       	showCityCode();
	    }
	});
	//城市
	cityAutoComplete = new AutoComplete($('#city_code'), {
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
	        $("#city_name").val(arr[arr.length-1]);
	        bk.checkSubmit();
	        showBranch();
	    }
	});
	
	function showCityCode(){
		$("#branch_code").val("");
		$("#branch_code").attr("data-id","");
		$("#city_code").val("");
		$("#city_code").attr("data-id","");
		$("#city_code").show();
		$("#branch_name").val("");
		$("#city_name").val("");
		if(branchBankAutoComplete)
		{
			branchBankAutoComplete.remove();
		}
	}
	
	function showBranch(){
		$("#branch_code").val("");
		$("#branch_code").attr("data-id","");
		$("#branch_code").show();
		$("#branch_name").val("");
		if(branchBankAutoComplete)
		{
			branchBankAutoComplete.remove();
		}
		init_branch();
	}
	
	
	function init_branch(){
		var bankCode = $("#bankCode").attr("data-id");
		var cityCode = $("#city_code").attr("data-id");
		if(bankCode.length>0 && cityCode.length>0){
			//支行
			branchBankAutoComplete = new AutoComplete($('#branch_code'), {
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
			        $("#city_code").attr("data-id",arr[1]);
			        $("#branch_name").val(d.val);
			        bk.checkSubmit();
			    }
			});
		}
	}
	
	function getBankCardData(){
		var t = sessionStorage.getItem("type");
		var fid=bk.getformId();
		if(t=="enterprise"){//企业
			return {
				bankCode : $("#bankCode").attr("data-id"),
				bankName:$("#bankName").val(),
				branchCode:$("#branch_code").attr("data-id"),
				cityCode:$("#city_code").attr("data-id"),
				cityName:$("#city_name").val()
			};
		}else{//个人
			return {
				bankCode : $("#bankCode").attr("data-id"),
				bankName:$("#bankName").val()
			};
		}
	}	
	
	function saveBindCard(){
		var paramData = getBankCardData();
		if($("orgCardId").val().length>0){//新增时，可以修改机构信息
			paramData["accountNumber"] = $("#accountnumber").val();
			paramData["userName"] = $("#orgName").val();
			paramData["userType"] = sessionStorage.getItem("type")=='enterprise'?1:2;
		}
		$.ajax({
    	    type: 'post',
    		url:'<%=basePath%>/account/bankCardBind.shtml',
    		dataType:'json',
    		data:paramData,
    		success:function(result){
        		if(result.code=="-1"){
        			new top.Tip({msg: result.msg, type: 2, timer: 2000});
        		}else{
        			showBindResult();
        		}
        	}
	    });
    }
	</script>
</body>