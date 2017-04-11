<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/comm/taglib.jsp"%>
<!--弹窗白色底-->
<div class="S_popup clearfix S_popTop " style="width: 840px; ">
  <div class="S_popup_title">
    <ul>
      <li class="P_left"></li>
      <li class="P_centre" style="padding:0 50px;">选择收款方</li>
      <li class="P_right"></li>
    </ul>
  </div>
  <div class="S_popup_content1" style="top:-5px;">
    <div class="Bank_pop">  	
    	
    	<div class="bp_l clearfix ">
    		<div class="l_title ">历史收款账户</div>
    		<div class="l_sel">
	    		<input class="text w_230 fl" type="text" placeholder="公司名称" id="content">
	    		<a class="btn btn_magnify"  href="javascript:void(0)" id="findName">&nbsp;</a>
	    	</div>
	    	<div class="l_c">
	            <!-- 暂无历史收款账户，请新增 -->
	            <div class="dn">暂无历史收款账户，请新增</div>
	            <!-- 有记录 -->
	            
	            <c:forEach items="${orgList}" var="orgItem" varStatus="status">
	            	<div class="l_div">
		    			<div class="b_img"><img src="<%=basePath%>/banks/ico36/${orgItem.bankCode}.png"></div>
			            <p class="vorgNameContent" title="${orgItem.vorgName}">${orgItem.vorgName}</p>
			            <p>${orgItem.bankCard}</p>
			            <div class="b_replace settingBtn J_close"  bankName="${orgItem.bankName}"  bankCode="${orgItem.bankCode}" cardNum="${orgItem.bankCard}" accountName="${orgItem.vorgName}" >选择</div>
		    		</div>
	            </c:forEach>
    		</div>
    	</div>
    	<div class="bp_r clearfix opacity04">
    		<div class="r_title ">新增收款账户</div>
    		<ul>
    			<li>
    			<div class="r_tit">开 户 行 ：</div>
    				<input class="text w_260 fl" id="bankCode"  type="text" placeholder="请选择">
    				 <input type="hidden" id="bankName" >
    			</li>
    			
    			
    			<li id="branch_spec" style="display: none" >
						<span class="r_tit">开户支行：</span>
						<input name="" id="city_code" class="text i_city fl "  placeholder="城市" datatype="*1-20" errormsg="必选项"  type="text">
						<input name="city_name" id="city_name" type="hidden">
						<input type="text" class="text w_148 fl" name="branch_code" id="branch_code" datatype="*1-20" errormsg="必选项" placeholder="请选择支行" >
						<input name="branch_name" id="branch_name" type="hidden">
				</li>
    			
    			
<!--     			<li> -->
<!--     			<div class="r_tit">开户支行 ：</div> -->
<!-- 				<input name="" class="text i_city fl " placeholder="城市" type="text"> -->
<!-- 				<input class="text w_148 fl" type="text" placeholder="请选择支行">    			 -->
<!--     			</li> -->
    			<li>
    			<div class="r_tit">账 户 名 ：</div>
    			<input type="text" class="text w_260 fl" id="name"  placeholder="请输入账户名">
    			</li>
    			<li>
    			<div class="r_tit">银行卡号 ：</div>
    			<input type="text"  class="text w_260 fl" id="accountnumber"  placeholder="请输入账户名">
    			</li>
    			<li class="bp_btn">
    				<a class="btn" id="createVirtualOrg" href="javascript:void(0)">确定</a>
    			</li>
    		</ul>
    	</div>
    </div>
  </div>
</div>
<!--弹窗白色底--> 
<script>

// $.fn.watch = function(callback) {
//     return this.each(function() {
//         //缓存以前的值
//         $.data(this, 'originVal', $(this).val());

//         //event
//         $(this).on('keyup paste', function() {
//             var originVal = $(this, 'originVal');
//             var currentVal = $(this).val();

//             if (originVal !== currentVal) {
//                 $.data(this, 'originVal', $(this).val());
//                 callback(currentVal);
//             }
//         });
//     });
// }

$("#findName").click(function() {
	var value = $('#content').val();
	$('.vorgNameContent').each(function(){
		
		if(value == "" || value == null)
		{
			$(this).parents(".l_div").show();
			return;
		}
		
		var str = $(this).text();
		if(str.indexOf(value) != -1)
		{
			$(this).parents(".l_div").show();
			return;
		}
		
		$(this).parents(".l_div").hide();
		
	});
});


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
        if(d.key =="104"){
        	$("#branch_spec").show();
        	showCityCode();
        }else{
        	$("#branch_spec").hide();
        }
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


function getBankCardData(){
	return {
		bankCode : $("#bankCode").attr("data-id"),
		cardNum : $("#accountnumber").val(),
		accountName :$("#name").val(),
		bankName:$("#bankName").val(),
		bankBranch:$("#branch_code").attr("data-id"),
		bankCityCode:$("#city_code").attr("data-id"),
		bankCityName:$("#city_name").val(),
		userId:"${param.userId}"
	};
}



$(".J_close").click(function(){
	
	top.selectBankCallBack(
			$(this).attr("bankName"),
			$(this).attr("bankCode"),
			$(this).attr("cardNum"),
			$(this).attr("accountName"));
});


//下一步
$('#createVirtualOrg').on('click',function(){
   	//ajax提交办卡信息
   	var bankCardData = getBankCardData();
   	//验证相关的数据
   	var flag = validate_bankCard_data(bankCardData);
   	if(!flag){
   		return ;
   	}
   	top.F.loading.show();
   	$.ajax({
   	    type: 'post',
   		url:'<%=basePath%>/loan_apply/createVirtualOrg.shtml',
   		dataType:'json',
   		data:bankCardData,
   		success:function(data){
   			top.F.loading.hide();
       		if(data.code=="-1"){
       			new top.Tip({msg : data.msg , type: 1 , time:1000}); 
       		}else{
       			top.selectBankCallBack(
						bankCardData.bankName,
						bankCardData.bankCode,
						bankCardData.cardNum,
						bankCardData.accountName);
       		}
       	}
   	});
}); 




function validate_bankCard_data(bankCardData){
	if(typeof bankCardData.bankName =="undifined" || bankCardData.bankName.length<1){
		new top.Tip({msg : '收款银行不能为空！', type: 1 , time:1000}); 
	    return false;
	}
	
	var bankCode = $("#bankCode").attr("data-id");
	if(bankCode=="104"){//中国银行需要验证
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
	}
	
	if(typeof bankCardData.cardNum =="undifined" || bankCardData.cardNum.length<1){
		new top.Tip({msg : '收款账号不能为空！', type: 1 , time:1000}); 
		return false;
	}
	
	var testNumber  = /^[0-9]*$/;
	if(!testNumber.test( bankCardData.cardNum)){
		new top.Tip({msg : '收款账号必须为数字！', type: 1 , time:1000}); 
		return false;
	}
	
	if(typeof bankCardData.accountName =="undifined" || bankCardData.accountName.length<1){
		new top.Tip({msg : '开户名不能为空！', type: 1 , time:1000}); 
		return false;
	}
	
	return true;
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
	
$('.bp_l').hover(function(){
	var _this=$(this);
	if(_this.is('.opacity04')){
		_this.removeClass('opacity04').siblings().addClass('opacity04');
	}
})
$('.bp_r').hover(function(){
	var _this=$(this);
	if(_this.is('.opacity04')){
		_this.removeClass('opacity04').siblings().addClass('opacity04');
	}
})
</script>
