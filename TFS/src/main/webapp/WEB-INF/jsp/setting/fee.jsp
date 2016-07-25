<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>金融设置-收付款费率公示</title>
	<jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
<body>
<div id="scroll" >
	<div class="main_sell clearfix user_title">
		<div class="p_r30 p_l10">
			<span>泰坦金融&nbsp;-&nbsp;泰坦金融设置&nbsp;-&nbsp;收付款费率公示</span>
		</div>
	</div>
</div>
<div class="scroll_x hide t_56"></div>
<div class="main_con p_t56">
	<div class="TFS_set" style="padding:0 16px 0 0; width: 670px;">
		<table width="100%" border="1" cellspacing="0" class="TFS_pubtable">
			<colgroup>
				<col width="23%">
				<col width="23%">
				<col width="">
			</colgroup>
			<tr class="TFS_setth">
				<td>付款方式</td>
				<td>基础费率</td>
				<td>执行费率（至2016.10.31）</td>
			</tr>
			<tr class="TFS_settd">
				<td><strong>PC端B2C网银</strong></td>
				<td><strong>0.30%</strong></td>
				<td><strong class="c_f60_1">免费</strong></td>
			</tr>
			<tr class="TFS_settd">
				<td><strong>PC端B2B网银</strong></td>
				<td><strong>10元/笔</strong></td>
				<td><strong class="c_f60_1">免费</strong></td>
			</tr>
			<tr class="TFS_settd">
				<td><strong>PC端信用卡</strong></td>
				<td><strong>0.30%</strong></td>
				<td><strong class="c_f60_1">免费</strong></td>
			</tr>
			<tr class="TFS_settd">
				<td><strong>提现</strong></td>
				<td><strong>5元/笔</strong></td>
				<td><strong class="c_f60_1">免费</strong></td>
			</tr>
		</table>
	</div>
</div>

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
</script>
</body>
</html>