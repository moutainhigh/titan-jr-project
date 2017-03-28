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
								<li class="r_y1"><div class="rt_title">公司名称</div><input type="text" class="text" id="userName" disabled value="${organ.orgName}" placeholder="请输入公司名称" ><input type="hidden" name="userName" value="${organ.orgName}"/></li>
								<li class="r_y1"><div class="rt_title">卡号</div><input type="text" class="text" id="bankCardCode" name="bankCardCode" placeholder="请输入对公卡卡号"></li>
								<li class="r_y1"><div class="rt_title">开户行</div><input type="text" class="text"  id="bankCardName" name="bankCardName"placeholder="请选择开户银行"><input type="hidden" id="bankCode" name="bankCode" ></li>
								<li class="lb_btn "><a href="javascript:void(0)" onclick="submitForm();" class="">提交绑卡申请</a> <input type="hidden" name="modifyOrBind" id="modifyOrBind" value="${modifyOrBind}"></li>
							</ul>
						</form>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/comm/foot.jsp"></jsp:include>	
		<!-- 查看示例 -->
		<div class="dn" id="example">
		<div class="example">
			<img src="<%=cssWalletPath%>/images/tu01.jpg" alt="">
		</div>	
		</div>
		<script type="text/javascript">
		//验证
		new validform('.r_box');
		
		
		//查看示例
		$('.J_example').on('click',function(){  
		        var d = dialog({
		            title: ' ',
		            fixed: true,
		            padding: '0 0 0px 0',
		            content: $('#example'),
		            skin : 'wallet_pop'      
		        }).showModal()
		        return false;
		}); 
		
		function submitForm(){
			var bankCode = $("#bankCardName").val();
			var accountnumber = $("#bankCardCode").val();
			
			if(typeof bankCode =="undifined" || bankCode.length<1){
				new top.Tip({msg : '开户行不能为空！', type: 1 , time:1000}); 
			    return false;
			}
			
			var regex = /^[0-9]{6,30}$/;
			if(!regex.test(accountnumber)){
				new top.Tip({msg : '卡号格式错误！', type: 1 , time:1000}); 
			    return false;
			}
			$("#bank_card_form").submit();
		}
		
		
		new AutoComplete($('#bankCardName'), {
		    url : '<%=basePath%>/account/getBankInfoList.shtml',
		    source : 'bankInfoDTOList',
		    key : 'bankCode',  //数据源中，做为key的字段
		    val : 'bankName', //数据源中，做为val的字段
		    width : 410,
		    height : 300,
		    autoSelectVal : true,
		    clickEvent : function(d, input){
		        $("#bankCode").val(d.key);
		    }
		});
		
		</script>
	</body>				
</html>
