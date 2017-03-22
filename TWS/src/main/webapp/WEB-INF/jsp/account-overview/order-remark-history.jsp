<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<script type="text/javascript" src="<%=basePath%>/js/tfs-common.js"></script>
 
	<div class="S_popup_content" style="width: 570px; padding: 10px;">
		<div class="hostel_hint1">     
       <div class=" p_t10">
         <div class="S_topTitle p_r15" style="width:100px;">备注内容：</div>
         <div style="float:left;width:380px;" id="r_content"></div>
         
         </div>
         </div>
		
	</div>
<script type="text/javascript">
		var c = replaceEnterKey("${transOrder.remark}");
		$("#r_content").html(c);
</script>

