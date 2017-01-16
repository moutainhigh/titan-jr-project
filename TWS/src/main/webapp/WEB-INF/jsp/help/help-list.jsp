<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>问题列表-帮助中心-泰坦金融</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
<style type="text/css">
		body {background: #f1f1f1;}
		.ui-popup-backdrop{ opacity: 0.4!important; }
		.f_ui-pager .pageBtnWrap {float: left;}
		.f_ui-pager .infoTextAndGoPageBtnWrap {padding-top: 0px;}
		.f_ui-pager span.curr { background: #ffe1e0;color: #ff6561;border: 1px solid #ff6561;}
		.f_ui-pager a:hover{background:#d24745; color:#fff;border: 1px solid #c93d3a;}
		.f_ui-pager-btn-go-input.focus{border: 1px solid #ff6561;}
		.f_ui-pager-btn-go { background-color: #d24745;}
	</style>
<body>
<input type="hidden" id="s_ht" value="${ selectHelpType.helpType}">
	<div class="help_box">
		<h3 class="help_box_title">
			<img src="<%=cssSaasPath%>/images/TFS/help_logo.jpg"/>帮助中心
		</h3>
		<div class="help_box_search">
			<input type="text" id="s_word" class="text h_28 w_542 help_inp01" placeholder="请输入问题关键字"> <a href="javascript:;" onclick="sch()" class="help_searchBtn">搜索</a>	
		</div>
		<div class="help_box_content clearfix">
			<div class="help_box_list fl">
				<h3 class="help_box_list_title">常见问题分类</h3>
				<ul>
					<c:forEach items="${helpTypeList }" var="helpType">
						<li <c:if test="${helpType.helpType == selectHelpType.helpType}">class="cur"</c:if>>
							<a href="<%=basePath %>/help/help-list.action?ht=${helpType.helpType }">${helpType.name }</a>
						</li>
					</c:forEach> 
				</ul>
			</div>
			<div class="help_box_main fr">
				<h3 class="help_box_main_title">泰坦金融 > 帮助中心 > ${selectHelpType.name }</h3>
				<div class="help_box_main_content">
					<ul class="help_box_main_list" id="r_help_type_list">
						 <c:forEach items="${helpPage.itemList }" var="help">
							<li><a target="_blank" href="<%=basePath %>/help/detail.action?hid=${help.helpId }">${help.helpTitle }</a></li>
						</c:forEach>
					</ul>
					<div style="padding:10px 20px 10px 0; overflow:hidden;">
						<div id="kkpager" class="fr"></div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
	<script type="text/javascript">
	function sch(){
		 var w = $("#s_word").val();
		 window.location.href = "<%=basePath %>/help/search.action?w="+w;
	}
	   $(function(){
		   var c='${helpPage.totalPage}';
		   if(c>1){
			   new Pager({
			        pno : '${helpPage.currentPage}',
			        total : '${helpPage.totalPage}',
			        totalRecords : '${helpPage.totalCount}',
			        isShowTotalRecords :true,
			        isGoPage : true,
			        mode : 'link',
			        getLink : function(n){
						return this.hrefFormer + this.hrefLatter + "?pn="+n+"&ht="+'${selectHelpType.helpType}';
					}
			    });
		   }
		});
	</script>
  </body>
</html>
