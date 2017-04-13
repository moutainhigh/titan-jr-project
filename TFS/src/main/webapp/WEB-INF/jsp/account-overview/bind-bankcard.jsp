<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<div class="S_popup clearfix S_popTop ">
	<div class="S_popup_title">
		<ul>
			<li class="P_left"></li>
			<li class="P_centre" style="padding:0 50px;">绑定提现银行卡</li>
			<li class="P_right"></li>
		</ul>
	</div>
	<div class="S_popup_content1" style="top:0px; padding:0 20px; width: 500px;">
		<div class="password_set">
			<div class="passwordf father tipInfo" style="margin-top:40px;">
				<div class="TFS_bind passwordset_u1">
					<p class="fl mr25"><img src="<%=cssSaasPath%>/images/TFS/kf.png" width="28" height="46" alt=""></p>
					<p class="fl"><strong class="fs16">为了您的账户安全，请先绑定提现银行卡。</strong>
						<span class="c_tfscolor">一经绑定，不可修改！</span>
					</p>
				</div>
				<div class="pas_close">
					<span class="btn p_lr30 J_next bindCard">去绑定提现银行卡</span>
				</div>
			</div>
			<div class=" father input_bankinfo" style="display: none;">
				<ul class="passwordset_u1 clearf">
					<li>
						<span class="reset_pass">收款银行 ：</span>
						<input type="text" id="bankCode" class="text w_250" style="width:310px;" >
                        <input type="hidden" id="bankName" >
					</li>
					<li id="branch_spec" style="display: none" >
						<span class="reset_pass">开户支行：</span>
						<input name="" id="city_code" class="text i_city"  placeholder="城市" datatype="*1-20" errormsg="必选项" style="width: 80px; background-position: 65px 7px ! important;" type="text">
						<input name="city_name" id="city_name" type="hidden">
						<input type="text" class="text" name="branch_code" id="branch_code" datatype="*1-20" errormsg="必选项" placeholder="请选择支行" style="width: 216px;padding-left: 10px;">
						<input name="branch_name" id="branch_name" type="hidden">
					</li>
					<li>
						<span class="reset_pass">收款账号：</span>
						<input type="text" placeholder="" class="text w_250" id="accountnumber" style="width:310px;">
					</li>
					<li>
						<span class="reset_pass">公司名称 ：</span>
						<input type="text" class="text w_250" id="name" style="width:310px;" value="${organ.orgName}" disabled>
					</li>		
				</ul>
				<div class="pas_close">
					<span class="btn p_lr30 J_next to_bindCard">下一步</span>
				</div>
			</div>
		
		   <div class="passwordf" id="bindBankCardFail"  style="display: none; margin:40px 0;">
				 <div class="TFS_bind passwordset_u1" style="padding-bottom:40px;">
					<p class="fl mr25"> <i class="mr_ico"></i></p>
					<p class="fl"><strong class="c_tfscolor f_16">绑定提现卡失败</strong>
						<span class="c_999" id="error_msg"></span>
					</p>
				</div> 
			<!-- 	<div class="TFS_bind passwordset_u1" style="padding-bottom:40px;">
				  <i class="mr_ico"></i>
				  <span class="TFS_mrtips">
				  <strong class="c_tfscolor f_16">对不起,提现卡绑定失败</strong>
				     失败原因：银行卡信息或持卡人姓名不正确不正正宗确。银行卡信息或持卡人。
				   </span>
				</div> -->
				<div class="pas_close" style="margin-bottom:50px;">
					<span class="btn p_lr30 J_finsh_close">关闭</span>
				</div>
			</div>
		    
			<div class="passwordf" id="showValidate"style="display: none; margin:40px 0;">
				<div class="TFS_bind passwordset_u1" style="padding-bottom:40px;">
					<p class="fl mr25"><img src="<%=cssSaasPath%>/images/guidance07.jpg" width="44" height="44" alt=""></p>
					<p class="fl"><strong class="fs16">绑定申请已提交！</strong>
						<span class="c_999">我们将会在24H内审核完成，请耐心等待。</span>
					</p>
				</div>
				<div class="pas_close" style="margin-bottom:50px;">
					<span class="btn p_lr30 J_finsh">完成</span>
				</div>
			</div>
		</div>
	</div>
</div>
<!--弹窗白色底-->

<script>

if('${showBankCardInput}'.length>0){
	$(".tipInfo").hide();
	$(".input_bankinfo").show();
}

$(".bindCard").on('click',function(){
	 $(this).parents(".father").hide();
	 $(this).parents(".father").next().show();
})

//下一步
$('.to_bindCard').on('click',function(){
    // $(".passwordf").hide();
    // $(".passwordf_next").show(); 
   
    if($(this).text()=="下一步"){
    	//ajax提交办卡信息
    	var bankCardData = getBankCardData();
    	//验证相关的数据
    	var flag = validate_bankCard_data(bankCardData);
    	if(!flag){
    		return ;
    	}
    	 $(this).parents(".father").hide();
    	$.ajax({
    	    type: 'post',
    		url:'<%=basePath%>/account/bankCardBind.shtml',
    		dataType:'json',
    		data:{
    			bankCardName:bankCardData.bankHeadName,
    			bankCardCode:bankCardData.accountnumber,
    			userName:bankCardData.name,
    			bankCode:bankCardData.bankCode,
    			branchCode:bankCardData.branchCode,
    			cityCode:bankCardData.cityCode,
    			cityName:bankCardData.cityName,
    			modifyOrBind:${modifyOrBind}
    		},
    		success:function(data){
        		if(data.code=="-1"){
        			$("#error_msg").text("失败原因是："+data.msg);
        			$("#showValidate").hide();
        			$("#bindBankCardFail").show();
        		}else{
        			$("#bindBankCardFail").hide();
        			$("#showValidate").show();
        		}
        		  $(this).parents(".father").next().show();
        	}
    	});
    }
}); 

function validate_bankCard_data(bankCardData){
	if(typeof bankCardData.bankHeadName =="undifined" || bankCardData.bankHeadName.length<1){
		new top.Tip({msg : '收款银行不能为空！', type: 1 , time:1000}); 
	    return false;
	}
	
	var bankCode = $("#bankCode").attr("data-id");
// 	if(bankCode=="104"){//中国银行需要验证
		var cityCode = $("#city_code").attr("data-id");
		if(typeof cityCode =="undifined" || cityCode.length<1){
			new top.Tip({msg : '开户城市不能为空！', type: 1 , time:1000}); 
			return false;
		}
		var branchCode = $("#branch_code").attr("data-id");
		if(typeof branchCode =="undifined" || branchCode.length<1){
			new top.Tip({msg : '开户支行不能为空！', type: 1 , time:1000}); 
			return false;
		}
// 	}
	
	if(typeof bankCardData.accountnumber =="undifined" || bankCardData.accountnumber.length<1){
		new top.Tip({msg : '收款账号不能为空！', type: 1 , time:1000}); 
		return false;
	}
	
	var testNumber  = /^[0-9]*$/;
	if(!testNumber.test( bankCardData.accountnumber)){
		new top.Tip({msg : '收款账号必须为数字！', type: 1 , time:1000}); 
		return false;
	}
	
	if(typeof bankCardData.name =="undifined" || bankCardData.name.length<1){
		new top.Tip({msg : '开户名不能为空！', type: 1 , time:1000}); 
		return false;
	}
	
	return true;
}
var backListObj = null;
var cityListObj = null;
var childBackListObj = null;

backListObj = new AutoComplete($('#bankCode'), {
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
//         if(d.key =="104"){
        	$("#branch_spec").show();
        	showCityCode();
//         }else{
//         	$("#branch_spec").hide();
//         }
    }
});

cityListObj = new AutoComplete($('#city_code'), {
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
	if(childBackListObj)
	{
		childBackListObj.remove();
	}
}

function showBranch(){
	$("#branch_code").val("");
	$("#branch_code").attr("data-id","");
	$("#branch_code").show();
	$("#branch_name").val("");
	if(childBackListObj)
	{
		childBackListObj.remove();
	}
	init_branch();
}


function init_branch(){
	var bankCode = $("#bankCode").attr("data-id");
	var cityCode = $("#city_code").attr("data-id");
	if(bankCode !=null && cityCode !=null){
		childBackListObj = new AutoComplete($('#branch_code'), {
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
		    }
		});
		
	}
}


function getBankCardData(){
	return {
		bankCode : $("#bankCode").attr("data-id"),
		accountnumber : $("#accountnumber").val(),
		name :$("#name").val(),
		bankHeadName:$("#bankName").val(),
		branchCode:$("#branch_code").attr("data-id"),
		cityCode:$("#city_code").attr("data-id"),
		cityName:$("#city_name").val()
	};
}
</script>