<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>员工权限信息-泰坦钱包</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
<body style="min-width: 1300px;" class="bg" >
<!-- 头部 -->
<jsp:include page="/comm/header.jsp"></jsp:include>
<div class="h_90"></div>
<!-- 内容 -->
<div class="w_1200 ">
	<div class="overview clearfix m_t20 o_set">	
		<jsp:include page="../slidemenu/jr-setting-menu.jsp">
			<jsp:param value="employee" name="menu"/>
		</jsp:include>
		<div class="s_right clearfix">
			<div class="sr_crumbs">我的账户  >  泰坦钱包设置  >  员工权限信息</div>			
			<div class="sr_staff clearfix">
				<div class="TFS_set1 clearfix">
					<a href="<%=basePath%>/setting/employee-add.shtml" class="sr_staff_add fl J_addnew"> <i class="ico"></i>
						新增员工权限
					</a>
					<div class="fr clearfix">
						<input type="text" placeholder="泰坦金服用户名：" class="text fl " id="J_tfs_userLoginName" name="jinfuUserLoginName"/>
						<input type="text" placeholder="姓名：" id="J_tfs_userName" class="text fl" name="userName"/>
						<span class="btn btn_magnify">&nbsp;</span>
					</div>
				</div>	
				
				<div class="TFS_set" >
						<table width="100%" border="1" cellspacing="0" class="TFS_settable">
							<colgroup>
							<col width="195">
							<col width="120">
							<col width="77">
							<col width="77">
							<col width="77">
							<col width="77">
							<col width="77">
							<col width="77">
							<col width="">
							</colgroup>
						<tr class="TFS_setth">
							<td rowspan="2" class="td_l20">泰坦金服用户名</td>
							<td rowspan="2" class="td_l20">姓名</td>							
							<td colspan="6">权限</td>
							<td colspan="6" rowspan="2">操作</td>
						</tr>
						<tr class="TFS_setth TFS_setth1">
							<td><span title="付款的权限">付款</span></td>
							<td><span title="查看账户资产总额、资产分布、交易记录、增值宝、购买理财产品情况、购买信贷产品情况">查看</span></td>
							<td><span title="给账户充值和给账户余额可以提现的权限">充值&提现</span></td>
							<td><span title="购买理财产品以及理财产品赎回的权限">理财</span></td>
							<td><span title="购买信贷产品以及信贷产品还款的权限">信贷</span></td>
							<td><span title="收付款消息提醒">消息提醒</span></td>
						</tr>
						<tbody id="t_user_body">
			
						</tbody>
					</table>
					<!-- 提示 -->
					<div class="TFSno_data dn"> 
						<i></i>您还未给任何员工添加权限哦！
					</div>
				</div>				
			</div>	

			<div class="main_kkpager" >
				<div id="kkpager" class="page_turning"></div>
			</div>

		</div>
	</div>
</div>
<div class="h_40"></div>
<!-- 版权 -->
<jsp:include page="/comm/foot-line.jsp"></jsp:include>

<script type="text/javascript">  
//内容高度
function scrollCon(){
	var winH=$(window).height();
	$('.s_right').css({'min-height':winH-235});
}
scrollCon();
$(window).on('resize.fixed',function(){
	scrollCon();
})
//加载数据表格
var ajaxPage = new AjaxPage({
	url:"<%=basePath%>/setting/employee-table.shtml",
	success:function(html){
		$("#t_user_body").html(html);
	}
});
$(".btn_magnify").on("click",function(){
	search();
});
function search(){
	var queryParams ={"tfsUserLoginName":$("#J_tfs_userLoginName").val(),"userName":$("#J_tfs_userName").val()};
	ajaxPage.load({"queryParams":queryParams});
}


//冻结
	$('.J_frozen').on('click',function(){
		var _this=$(this);
		if($(this).hasClass('blue')){
		new top.createConfirm({
		    title:'提示',
			padding: '20px 20px 40px',		
	        content : '<div class="f_14 l_h26">你确定要冻结用户“188 8888 8888”？</div>',
			ok : function(){
				_this.removeClass('blue').addClass('orange').text("解冻");
				new Tip({msg : '操作成功！', type: 1 }); 
			},
	        cancel : function(){
	        }
	      }); 
		}else{
			new top.createConfirm({
		    title:'提示',
			padding: '20px 20px 40px',		
	        content : '<div class="f_14 l_h26">你确定要用户“188 8888 8888”解冻吗？</div>',
			ok : function(){
				_this.removeClass('orange').addClass('blue').text("冻结");
				new Tip({msg : '操作成功！', type: 1 }); 
			},
	        cancel : function(){
	        }
	      }); 
		}
	});
 
</script>

</body>
</html>
