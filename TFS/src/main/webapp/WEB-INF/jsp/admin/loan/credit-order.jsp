<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>授信申请审核-泰坦金融管理平台</title>
	 <jsp:include page="/comm/admin/admin-static-resource.jsp"></jsp:include>
</head>
  <body>
  <jsp:include page="/comm/admin/admin-head.jsp">
  	<jsp:param value="loan" name="menu"/>
  </jsp:include>
  <div class="my_acount">
		<div class="mya_title">
			<strong>授信审核</strong>
			<p>
				<a class="on" href="javascript:void(0)">授信审核<i>8</i></a>			
			</p>	
		</div>		
		<div class="acount">
			<div class="table_top">
				<div class="fr">
					<input type="text" class="textinput w200 fl" placeholder="用户名：">
					<input type="text" class="textinput w200 fl" placeholder="公司名称：">
					<div class="selecttit fl">
						<div class="selecttop text w200 J_seltype cursor">审核状态：<span class="J_chosed" data-value="">未通过</span><i class="ico"></i></div>
						<div class="selectcon">
							<p data-value="1">全部</p>
							<p data-value="2">待审核</p>
							<p data-value="3">未通过</p>
							<p data-value="4">已通过</p>
						</div>
					</div>
					<input type="button" class="search fl" value="搜索">
				</div>
			</div>	
			<div class="clear"></div>
			<div class="acount_table">
				<table border="0" cellspacing="0" width="100%" >
	                <colgroup>
	                    <col width="20%">
	                    <col width="21%">
	                    <col width="15%">
	                    <col width="13%">
	                    <col width="12%">                    
	                    <col width="11%">		                  
	                    <col width="">
	                </colgroup>
	                <tr>
	                    <th class="tdl" ><span class="pl12">申请时间</span></th>
	                    <th class="tdl" >公司名称</th>
	                    <th class="tdl" >联系人</th>
	                    <th class="tdl" >手机号码</th>
	                    <th class="tdl" >审核人</th>
	                    <th class="tdl" >审核状态</th>	                   
	                    <th class="tdl" >操作</th>
	                </tr>  
	            </table>
	            <div class="table_content">
					<table border="0" cellspacing="0" width="100%">
						<colgroup>
		                    <col width="20%">
		                    <col width="21%">
		                    <col width="15%">
		                    <col width="13%">
		                    <col width="12%">                    
		                    <col width="11%">		                  
		                    <col width="">
		                </colgroup>
		                <tbody id="t_body">
						
						</tbody>
		                
		            </table>
	            </div>
			</div>
			<div style="padding:20px 31px 0 0; overflow:hidden;">
				<div id="kkpager" class="fr"></div>
			</div>
		</div>
	</div>
    <jsp:include page="/comm/admin/admin-static-js.jsp"></jsp:include>
    <script type="text/javascript">
  //加载数据表格
    var ajaxPage = new AjaxPage({
    	pageSize:15,
    	url:"<%=basePath%>/admin/orgTable.shtml",
    	success:function(html){
    		$("#t_body").html(html);
    		freshCreditCount();
    	}
    });
  
  //授信申请未审核数量
  function freshCreditOrderCount(){
	  
	  
	  
  }
    function search(){
    	var resultKey = $("#resultKey").attr("data-value");
    	var queryParams ={"userType":$("#userType").val(),"userLoginName":$("#userLoginName").val(),"orgName":$("#orgName").val(),"resultKey":resultKey};
    	ajaxPage.load({"queryParams":queryParams});
    }
    </script>
    
  </body>
</html>
