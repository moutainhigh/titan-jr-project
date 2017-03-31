<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <title>泰坦钱包</title>
	    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
		<jsp:include page="/comm/static-js.jsp"></jsp:include>
	</head>
	<body style="min-width: 1300px;" class="bg" >
		<jsp:include page="/comm/head-title.jsp">
			<jsp:param value="绑定提现银行卡" name="title"/>
		</jsp:include>
		<div class="register r_two">
			<div class="r_box h_335_p_t45">
				<div class="r_c ">
					<div class="r_text">
						<form id="bank_card_form" method="post" action="<%=basePath%>/account/bankCardBind.shtml">
							<ul>
								<li class="r_y1"><div class="rt_title">收款银行</div><input type="text" class="text"  id="auto_bankCardName" datatype="/\w*/" errormsg="请选择银行"  placeholder="请选择收款银行" /><input type="hidden" id="bankCode" name="bankCode" /><input type="hidden" id="bankCardName" name="bankCardName" /></li>
								<li class="r_y2" id="branch_spec" data-show="0" style="display: none" ><div class="rt_title">开户支行</div>
									<input type="text" id="auto_city_code" class="text text1 ico" placeholder="城市">
									<input name="cityCode" id="cityCode" type="hidden"/><input name="cityName" id="cityName" type="hidden"/>
									<input type="text" id="auto_branch_code" class="text text2" datatype="/\w*/"  errormsg="请选择支行" placeholder="请选择支行">
									<input name="branchCode" id="branchCode" type="hidden"/>
									</li>
								<li class="r_y1"><div class="rt_title">收款账号</div><input type="text" class="text" id="bankCardCode" name="bankCardCode" datatype="/\w*/"   errormsg="不能为空" placeholder="请输入收款账号"></li>
								<li class="r_y1"><div class="rt_title">公司名称</div><input type="text" class="text" id="userName" disabled value="${organ.orgName}" placeholder="请输入公司名称" ><input type="hidden" name="userName" value="${organ.orgName}"/></li>
								<li class="lb_btn "><a href="javascript:void(0)" onclick="submitForm()" class="">提交绑卡申请</a> <input type="hidden" name="modifyOrBind" id="modifyOrBind" value="${modifyOrBind}"></li>
							</ul>
						</form>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/comm/foot.jsp"></jsp:include>	
		<script type="text/javascript">
		//验证
		var bank_card_form = new validform('#bank_card_form');
		function submitForm(){
			var checksucc = true;
			checksucc = checkBank()&&checksucc;
			checksucc = checkBranch()&&checksucc;
			
			if($("#bankCardCode").val().length==0){
				bank_card_form._setErrorStyle($("#bankCardCode"),"不能为空");
				checksucc = false && checksucc;
			}
			
			if(checksucc){
				$("#bank_card_form").submit();
			}
			
		}
		function checkBank(){
			if($("#bankCardName").val().length==0||$("#auto_bankCardName").val().length==0){
				bank_card_form._setErrorStyle($("#auto_bankCardName"),"请选择银行");
				return false ;
			}
			return true;
		}
		function checkBranch(){
			if($("#branch_spec").attr("data-show")==1){
				if($("#auto_city_code").val().length==0||$("#auto_branch_code").val().length==0){
					bank_card_form._setErrorStyle($("#auto_branch_code"),"请选择支行");
					return false;
				}
			}
			return true;
		}
		var childBankListObj;
		new AutoComplete($('#auto_bankCardName'), {
		    url : '<%=basePath%>/account/getBankInfoList.shtml?bankType=1',
		    source : 'bankInfoDTOList',
		    key : 'bankCode',  //数据源中，做为key的字段
		    val : 'bankName', //数据源中，做为val的字段
		    width : 240,
		    height : 300,
		    autoSelectVal : true,
		    clickEvent : function(d, input){
		        $("#bankCode").val(d.key);
		        $("#bankCardName").val(d.val);
		        if(d.key =="104"){
		        	$("#branch_spec").show().attr({"data-show":"1"});
		        	showCityCode();
		        }else{
		        	$("#branch_spec").hide().attr({"data-show":"0"});
		        }
		        clearCity();
		    }
		});

		 new AutoComplete($('#auto_city_code'), {
		    url : '<%=basePath%>/account/getCitys.shtml',
		    source : 'cityInfoDTOList',
		    key : 'cityCode',  //数据源中，做为key的字段
		    val : 'cityName', //数据源中，做为val的字段
		    width : 240,
		    height : 300,
		    autoSelectVal : true,
		    clickEvent : function(d, input){
		        var arr = d.val.split("-");
		        $("#cityCode").val(d.key);
		        $("#cityName").val(arr[arr.length-1]);
		        input.val(arr[arr.length-1]);
		        showBranch();
		        clearBranch();
		    }
		});
		
		function showCityCode(){
			if(childBankListObj)
			{
				childBankListObj.remove();
			}
		}

		function showBranch(){
		 
			if(childBankListObj)
			{
				childBankListObj.remove();
			}
			init_branch();
		}

		function init_branch(){
			var bankCode = $("#bankCode").val();
			var cityCode = $("#cityCode").val();
			if(bankCode !=null && cityCode !=null){
				childBankListObj = new AutoComplete($('#auto_branch_code'), {
				    url : '<%=basePath%>/account/getBankInfoList.shtml?bankCity='+cityCode+'&parentCode='+bankCode,
				    source : 'bankInfoDTOList',
				    key : 'cityKey',  //数据源中，做为key的字段
				    val : 'bankName', //数据源中，做为val的字段
				    width : 240,
				    height : 300,
				    autoSelectVal : true,
				    clickEvent : function(d, input){
				    	 var arr = d.key.split(",");
				        $("#branchCode").val(arr[0]);
				        if($('#auto_branch_code').val().length==0){
				    		bank_card_form._setErrorStyle($("#auto_branch_code"),"请选择支行");
				    	}else{
				    		bank_card_form._setCorrectStyle($("#auto_branch_code"),"");
				    	}
				    }
				});
				
			}
		}
		function clearBranch(){
			$("#auto_branch_code").val("");
			$("#branchCode").val("");
		}
		
		function clearCity(){
			$("#auto_city_code").val("");
			$("#cityCode").val("");
			$("#cityName").val("");
			clearBranch();
		}
		</script>
	</body>				
</html>
