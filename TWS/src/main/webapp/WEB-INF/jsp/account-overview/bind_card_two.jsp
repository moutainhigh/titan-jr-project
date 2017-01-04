<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <title>泰坦钱包</title>
	    <jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
	    <link rel="stylesheet" href="<%=cssWalletPath%>/css/fangcang.min.css?v=20161222">
		<link rel="stylesheet" href="<%=cssWalletPath%>/css/style.css">
	</head>
	<body style="min-width: 1300px;" class="bg" >
		<div class="header">
			<div class="w_1200">
				<div class="logo">
					<div class="l_img"><img src="<%=cssWalletPath%>/images/logo.png"></div>
					<div class="l_text">
						<i class="ico"></i>绑定提现银行卡
					</div>
				</div>
			</div>
		</div>
		<div class="register r_two">
			<div class="r_box h_335_p_t45">
				<div class="r_c ">
					<div class="r_text">
						<form id="bank_card_form" method="post" action="<%=basePath%>/account/bankCardBind.shtml">
							<ul>
								<li class="r_y1"><div class="rt_title">公司名称</div><input type="text" class="text"  id="name" value="${organ.orgName}" disabled placeholder="请输入公司名称" ></li>
								<li class="r_y1"><div class="rt_title">卡号</div><input type="text" class="text" id="accountnumber" placeholder="请输入对公卡卡号"></li>
								<li class="r_y1"><div class="rt_title">开户行</div><input type="text" class="text" id="bankCode" placeholder="请选择开户银行"><input type="hidden" id="bankName" ></li>
								<li class="lb_btn "><a href="javascript:void(0)" onclick="submitForm();" class="">提交绑卡申请</a></li>
							</ul>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="footer">
			Copyright © 2012-2016, fangcang.com. All Rights Reserved 泰坦云 版权所有 粤ICP备13046150号	
		</div>
		<div class="szcert">
			<script charset="utf-8" type="text/javascript" src="http://szcert.ebs.org.cn/govicon.js?id=78ccac39-a97a-452c-9f81-162cd840cff6&amp;width=130&amp;height=50&amp;type=2" id="ebsgovicon"></script> 
		</div>	
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
			var bankCode = $("#bankCode").attr("data-id");
			var accountnumber = $("#accountnumber").val();
			
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
		
		
		new AutoComplete($('#bankCode'), {
		    url : '<%=basePath%>/account/getBankInfoList.shtml',
		    source : 'bankInfoDTOList',
		    key : 'bankCode',  //数据源中，做为key的字段
		    val : 'bankName', //数据源中，做为val的字段
		    width : 240,
		    height : 300,
		    autoSelectVal : true,
		    clickEvent : function(d, input){
		        input.attr('data-id', d.key);
		        $("#bankName").val(d.val);
		    }
		});
		
		</script>
	</body>				
</html>
