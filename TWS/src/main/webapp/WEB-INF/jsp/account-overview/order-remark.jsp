<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<script type="text/javascript" src="<%=basePath%>/js/tfs-common.js"></script>
<!-- <div class="S_popup clearfix">
	<div class="S_popup_title">
		<ul>
			<li class="P_left"></li>
			<li class="P_centre" style="padding:0 100px;">备注</li>
			<li class="P_right"></li>
		</ul>
	</div>
	<div class="S_popup_content" style="width: 570px; padding: 10px;">
		<div class="hostel_hint1">     
       <div class=" p_t10">
         <div class="S_topTitle p_r15">备注内容：</div>
         <textarea cols="" rows="" id="id_remark_text" class="text textarea_1 w437_h180"></textarea> 
         </div>
         </div>
	</div>
</div> -->
<div class="clearfix" style="width: 530px; padding:15px 25px 10px;">
     <div class="remark_l fl">备注内容：</div>
     <div class="remark_r fl">
     	<textarea cols="" rows="" id="id_remark_text" ></textarea>       
	 </div>
</div>


<script type="text/javascript">
	var c = backEnterKey("${transOrder.remark}");
	$("#id_remark_text").html(c);
</script>
