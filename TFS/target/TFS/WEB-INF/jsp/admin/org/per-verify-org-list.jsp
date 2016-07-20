<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>个人用户-申请审核-泰坦金融管理平台</title>
	 <jsp:include page="/comm/admin/admin-static-resource.jsp"></jsp:include>
</head>
  <body>
  <jsp:include page="/comm/admin/admin-head.jsp">
  	<jsp:param value="user" name="menu"/>
  </jsp:include>
  <div class="my_acount">
		<div class="mya_title">
			<strong>申请审核</strong>
			<p>
				<a  href="<%=basePath %>/admin/org.shtml">企业用户<i id="i_encount">0</i></a>
				<a class="on" href="<%=basePath %>/admin/org.shtml?userType=2">个人用户<i id="i_percount">0</i></a>
			</p>	
		</div>
		<div class="acount">
			<div class="table_top">
				<div class="fr">
			    	<input type="hidden" id="userType" value="2"/>
			    	<input type="text" class="textinput w200 fl" name="userLoginName" id="userLoginName" placeholder="用户名：">
			    	<input type="text" class="textinput w200 fl" name="orgName" id="orgName" placeholder="联系人：">
			    	<div class="selecttit fl">
						<div class="selecttop text w200 J_seltype cursor">审核状态：<span class="J_chosed" id=resultKey data-value="">全部</span><i class="ico"></i></div>
						<div class="selectcon">
							<p data-value="">全部</p>
							<p data-value="FT">待审核</p>
							<p data-value="FT_INVALID">初审未通过</p>
							<p data-value="REVIEW">复审中</p>
							<p data-value="REVIEW_INVALID">复审未通过</p>
							<p data-value="PASS">已通过</p>
						</div>
					</div>
					<input type="button" class="search fl" value="搜索" onclick="search()"/>
    		</div>
    	</div>	
		<div class="clear"></div>
		<div class="acount_table">
		    <table border="0" cellspacing="0" width="100%">
						<colgroup>
							<col width="25%">
		                    <col width="20%">
		                    <col width="20%">                    
		                    <col width="15%">
		                    <col width="15%">
		                    <col width="">
		                 </colgroup>
					<tr>
		                 <th class="tdl" ><span class="pl12">用户名</span></th>
	                    <th class="tdl" >联系人</th>
	                    <th class="tdl" >身份证号</th>
	                    <th class="tdl" >审核人</th>
	                    <th class="tdl" >审核状态</th>
	                    <th class="tdl" >操作</th>
		             </tr>
			 </table>
    		<div class="table_content">
				<table border="0" cellspacing="0" width="100%">
					<colgroup>
			                <col width="25%">
		                    <col width="20%">
		                    <col width="20%">                    
		                    <col width="15%">
		                    <col width="15%">
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
    	queryParams:getParam(),
    	pageSize:15,
    	url:"<%=basePath%>/admin/orgTable.shtml",
    	success:function(html){
    		$("#t_body").html(html);
    		freshCheckCount();
    	}
    });
    function search(){
    	ajaxPage.load({"queryParams":getParam()});
    }
    function getParam(){
    	var resultKey = $("#resultKey").attr("data-value");
    	var queryParams = {"userType":$("#userType").val(),"userLoginName":$("#userLoginName").val(),"orgName":$("#orgName").val(),"resultKey":resultKey};
    	return queryParams;
    }
   
    </script>
    
  </body>
</html>
