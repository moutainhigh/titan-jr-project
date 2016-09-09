<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/comm/taglib.jsp"%>
<%@ page isELIgnored="false" %>
<div class="S_popup clearfix">
	<div class="S_popup_title">
		<ul>
			<li class="P_left"></li>
			<li class="P_centre" style="padding:0 100px;">选择收款账户</li>
			<li class="P_right"></li>
		</ul>
	</div>
	<div class="S_popup_content" style="width: 500px; padding: 0 15px;">
		<div class="goldtixian">
			<ul>
				<c:forEach items="${accountHistoryResponse.accountHistoryDTOList}" var="accHistory">
					<li>
<!-- 						<img class="fl" src="<%=cssSaasPath%>/images/TFS/card.png" alt=""> -->
							<span class="cardinf fl">
								${accHistory.orgname}<br>${accHistory.titancode }
							</span>
						<a class="btn btn_add p_lr30 fr J_close">选择</a>
					</li>
				</c:forEach>
			</ul>
			<div class="gold_add curpo J_chose"><span class="goldadd_ico">使用新的收款账户</span></div>
			<div class="gold_bottom"><span class="blue p_l18 curpo J_gold">清空账户历史记录</span></div>
		</div>
	</div>
</div>

<script>
//清空历史
$(".J_gold").on('click', function() {
	//数据库删除记录
	 $.ajax({
    	 type: "post",
         data: {payerUserid:'${accountHistoryResponse.accountHistoryDTOList[0].payeruserid}',
        	 inAccountCode:'${accountHistoryResponse.accountHistoryDTOList[0].inaccountcode}',
        	 outAccountCode:'${accountHistoryResponse.accountHistoryDTOList[0].outaccountcode}'},
         dataType: "json",
         url: "<%=basePath%>/account/deleteAccountHistory.action",
         success: function(data){
        	 if(data.code=="-1"){
        		 alert("清除账户历史失败");
        	 }
          }
         });
	
	$(".goldtixian").children('ul').slideUp(1000);
});
//选择

</script>