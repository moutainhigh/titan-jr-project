<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>帮助中心-泰坦金融</title>
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
</head>
  
  <body>
   <div class="help_box">
		<h3 class="help_box_title">
			<img src="<%=cssSaasPath%>/images/TFS/help_logo.jpg">帮助中心
		</h3>
		<div class="help_box_search">
			<input type="text" class="text h_28 w_542 help_inp01" placeholder="请输入问题关键字" value="${w }" id="s_word"> <a href="javascript:void(0);" onclick="sch()" class="help_searchBtn">搜索</a>	
		</div>
		<div class="help_box_content help_search_content">
			<c:forEach items="${helpPage.itemList }" var="help">
				<dl>
					<dt><a href="<%=basePath %>/help/detail.action?hid=${help.helpId }">${tfs:redKeyword(help.helpTitle,w)}</a></dt>
					<dd>${tfs:redKeyword(help.helpContent,w)}</dd>
				</dl>
			</c:forEach>
			<c:if test="${fn:length(helpPage.itemList) == 0}">
			<div style="font-size:14px;">没有查询到与关键词<span style="color:#f00;margin:5px;">${w }</span>匹配的结果，请尝试其他关键词</div>
			</c:if>
			<div style="padding:10px 20px 0px 0; overflow:hidden;">
				<div id="kkpager" class="fr"></div>
			</div>
		</div>
		
	</div>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
	<script type="text/javascript">
	function sch(){
		 var w = $("#s_word").val();
		 window.location.href = "<%=basePath %>/help/search.action?w="+w;
	}
	
	//加载数据表格
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
					return this.hrefFormer + this.hrefLatter + "?pn="+n+"&w="+'${w}';
				}
		    });
	   }
	});
	</script>
  </body>
</html>
