<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!--弹窗白色底-->
<div class="S_popup clearfix S_popTop w911 checkOrg">
<input type="hidden" id="orgId" value="${financialOrganDTO.orgId}"/>
	<div class="examine">
		<div class="examine_left fl">
			<div class="examinele_con">
				<img src="${orgImgUrl }" width="600" height="420" alt="">
				<ul>
					<li class="fs16">姓名：<span class="orange">${orgSubDTO.orgName }</span></li>
					<li class="fs16">身份证号：<span class="orange">${orgSubDTO.certificateNumber }</span></li>
					<li class="fs14">用户名：<span>${tfsLoginUsername }</span></li>
					<li class="fs14">申请时间：<span><fmt:formatDate value="${financialOrganDTO.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span></li>
				</ul>
			</div>
		</div>
		<!-- 审核  -->
		<c:if test="${opt=='check' }">
			<div class="examine_right fr">
				<div class="examine_righttop">
					<h3>审核结果</h3>
					<p class="ptb9"><span class="w80"><i class="c_fc2020">*</i>审核人：</span>${operatorName }</p>
					<p class="ptb9"><span class="w80"><i class="c_fc2020">*</i>审核结果：</span>
						<select id="checkStatus" class="select w135 J_nothroght">
							<option value="">请选择</option>
							<option value="1">通过</option>
							<option value="0">不通过</option>
						</select>
					</p>
					<p class="ptb9 showhide"><span class="w80"><i class="c_fc2020">*</i>未通过原因：</span>
						<textarea id="checkReason" class="textarea"></textarea>
					</p>	
				</div>	
				<div class="examine_bobut">
					<span class="J_save cursor w85 btn_red fs16">保存</span>
					<span class="J_close cursor w85 btn_grey fs16">取消</span>
				</div>
			</div>
		</c:if>
		<!-- 显示审核结果 -->
		<c:if test="${opt=='view' }">
			<div class="examine_right fr">
				<div class="examine_righttop">
					<h3>审核结果</h3>
					<p class="ptb9"><span class="w80"><i class="c_fc2020">*</i>审核人：</span>${financialOrganDTO.checkStatus.checkUser}</p>
					<p class="ptb9"><span class="w80"><i class="c_fc2020">*</i>审核结果：</span>
					<c:if test="${financialOrganDTO.checkStatus.checkResultKey=='FT'}">待审核</c:if>
					<c:if test="${financialOrganDTO.checkStatus.checkResultKey=='FT_INVALID'}">初审未通过</c:if>
					<c:if test="${financialOrganDTO.checkStatus.checkResultKey=='REVIEW'}">复审中</c:if>
					<c:if test="${financialOrganDTO.checkStatus.checkResultKey=='REVIEW_INVALID'}">复审未通过</c:if>
					<c:if test="${financialOrganDTO.checkStatus.checkResultKey=='PASS'}">已通过</c:if>
					</p>
					<c:if test="${not empty financialOrganDTO.checkStatus.checkResultMsg and financialOrganDTO.checkStatus.checkResultKey !='PASS'}">
					<p class="ptb9"><span class="w80"><i class="c_fc2020">*</i>未通过原因：</span><textarea name="" class="textarea disable" disabled>${financialOrganDTO.checkStatus.checkResultMsg }</textarea></p>
					</c:if>
				</div>
				<div class="examine_bobut">	
					<span class="J_close cursor w85 btn_grey fs16">关闭</span>
				</div>	
			</div>
		</c:if>
	</div>
</div>
<!--弹窗白色底-->
<script>
//选择未通过出现文本域
	$(".J_nothroght").on('change',function(){
		var _this=$(this);
		if(_this.find("option:selected").text()=='不通过'){
			_this.parent("p").next().show();
		}else{
			_this.parent("p").next().hide();
		}
	});
</script>