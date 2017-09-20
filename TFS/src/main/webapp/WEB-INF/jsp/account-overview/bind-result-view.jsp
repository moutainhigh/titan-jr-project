<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
	<!--绑定提现卡审核 弹框-->
		<div id="bindcard-st" class="examine-modal"  >
			<div class="title"><p>绑定提现卡审核</p><div class="title-icon"><i class="iconfont icon-sc close"></i></div></div>
			<div class="content">
				<div class="left"></div>
				<div class="right">信息已提交，目前进度为：</div>
			</div>
			<div class="content" style="overflow:visible;">
				<div class="line"></div>
				<div class="left"></div>
				<div class="right">
					<div class="left-icon fl">
						<div class="circular-icon"></div>
					</div>
					<div class="right-info fl">提交审核</div>
				</div>
			</div>
			<div class="content" style="overflow:visible;">
				<div class="left"></div>
				<div class="right">
					<div class="left-icon fl">
						<div class="circular-icon"></div>
					</div>
					<div class="right-info fl">审核中</div>
				</div>
			</div>
			<div class="content" style="overflow:visible;">
				<div class="left"></div>
				<div class="right">
					<div class="left-icon fl">
						<div class="circular-icon" style="background-color: #c9c9c9;"></div>
					</div>
					<div class="right-info fl"><span style="color: #ccc;">完成</span><div class="review-prompt prompt-tip">${orgBankcardMsg}</div></div>
				</div>
			</div>
			<!-- 审核失败，则修改 -->
			<c:if test="${orgBankcardStatus=='0'}">
				<div class="content">
					<div class="left"></div>
					<div class="right">
						<button type="button" class="know-btn" onclick="bc.updateBind();">我知道了</button>
					</div>
				</div>
			</c:if>
			
			<c:if test="${orgBankcardStatus!='0'}">
				<div class="content">
					<div class="left"></div>
					<div class="right">
						<button type="button" class="know-btn" onclick="bc.close();">我知道了</button>
					</div>
				</div>
			</c:if>
			
			 
		</div>
</body>
</html> 