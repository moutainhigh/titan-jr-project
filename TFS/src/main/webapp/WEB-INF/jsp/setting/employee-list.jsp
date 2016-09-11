<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>员工权限设置-泰坦金融</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	
</head>
  
  <body>
<div id="scroll" >
		<div class="main_sell clearfix user_title">
			<div class="p_r30 p_l10">
				<span>泰坦金融&nbsp;-&nbsp;泰坦金融设置&nbsp;-&nbsp;员工权限设置</span>
			</div>
		</div>
		<div class="TFS_set">
			<div class="TFS_settitle clearfix p_b8">
				<span class="bacth_export_hotel fl J_addnew curpo"> <i class="icon_user"></i>
					新增员工权限
				</span>
				<div class="fr clearfix">
					<input type="text" placeholder="泰坦金融用户名：" id="J_tfs_userLoginName" class="text w_180 c_666" name="jinfuUserLoginName">
					<input type="text" placeholder="姓名：" id="J_tfs_userName" class="text w_140 m_l3 c_666" name="userName">
					<a href="#" class="btn btn_magnify m_l2 J_search">&nbsp;</a>
				</div>
			</div>
		</div>
	</div>
	<div class="scroll_x hide t_85"></div>
	<div class="main_con p_t80 j_page_contain">
		<div class="TFS_set" style="padding:0 16px 0 0; ">
			<table width="100%" border="1" cellspacing="0" class="TFS_settable">
				<colgroup>
				<col width="17%">
				<col width="9%">
				<col width="10%">
				<col width="8%">
				<col width="8%">
				<col width="8%">
				<col width="8%">
				<col width="8%">
				<col width="8%">
				<col width=""></colgroup>
			<tr class="TFS_setth">
				<td rowspan="2">泰坦金融用户名</td>
				<td rowspan="2">姓名</td>
				<td rowspan="2">关联SAAS用户</td>
				<td colspan="6">权限</td>
				<td colspan="6" rowspan="2">操作</td>
			</tr>
			<tr class="TFS_setth">
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
		
	</div>
</div>
<div style="height:50px;"></div>
<div class="main_kkpager j_page_contain">
	<div class="pagination1">
		<div class="pagination_r">
			每页显示酒店数量
			<i >5</i>
			<i>10</i>
			<i >15</i>
			<i>20</i>
		</div>
	</div>
	<div id="kkpager" class="page_turning"></div>
</div>
<jsp:include page="/comm/static-js.jsp"></jsp:include>
<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
<script>
//滚动显示线
    $(window).scroll(function(){
    	var win_top = $(window).scrollTop();
    		if(win_top > 0)
    		{
    			$('.scroll_x').show();
    		}
    		else
    		{
    			$('.scroll_x').hide();
            }
        });

//加载数据表格
var ajaxPage = new AjaxPage({
	url:"<%=basePath%>/setting/employee-table.shtml",
	success:function(html){
		$("#t_user_body").html(html);
	}
});
$(".J_search").on("click",function(){
	search();
});
function search(){
	var queryParams ={"tfsUserLoginName":$("#J_tfs_userLoginName").val(),"userName":$("#J_tfs_userName").val()};
	ajaxPage.load({"queryParams":queryParams});
}
//新增员工
$('.J_addnew').on('click',function(){
    top.F.loading.show();
    $.ajax({
        dataType : 'html',
        context: document.body,
        url : '<%=basePath%>/setting/fc-employee.shtml',        
        success : function(html){
            var d =  window.top.dialog({
                title: ' ',
                padding: '0 0 0px 0',
                content: html,
                skin : 'saas_pop' ,
                button : [ 
                        {
                            value: '关闭',
                            skin : 'btn p_lr30',
                            callback: function () {
                            	ajaxPage.load();
                            },
                            autofocus: true
                        }
					]              
            }).showModal();
        }
    });
}); 

</script>

</body>
</html>
