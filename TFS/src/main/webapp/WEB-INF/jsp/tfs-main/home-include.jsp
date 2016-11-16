<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!-- 回到顶部 -->
<div class="go_top dn" id="go_top">    
    <a href="javascript:;" class="go"></a>
    <a href="<%=basePath %>/help/index.action" class="go1"></a>
</div>

<!-- banner -->
<div class="main_col">
<div class="mc_search">
	<div class="title">
		<p>泰坦金融</p>
		泰坦金融是为旅游行业量身定制的在线金融服务平台，
		致力于为旅行行业提供安全、快捷、定制化的金融服务。
		泰坦金融将金融服务与旅游行业相结合，探索行业全新的发展模式，提供账户托管、收付款管理以及供应链融资金融服务。<br/>
	</div>
	<c:choose>
    	<c:when test="${ orgBindStatus==0}">
			<!-- 注册 -->
			<div class="enroll ">
				<a href="#" class="btn1 J_admin">注册</a>
				<img src="<%=cssSaasPath%>/images/TFS/home12.jpg">
			</div>
		</c:when>
	 
		<c:when test="${orgCheckResultKey=='FT'}">
          		<!-- 初审中... -->
          		<div class="plan">
                <ul>
                    <li>提交申请</li>
                    <li class="w_90 c_8d"><div>初审</div><p class="Province" >审核中...</p></li>
                    <li class="c_8d">复审</li>
                    <li class="c_8d">审核通过</li>
                </ul>
            </div>
        </c:when>
        <c:when test="${orgCheckResultKey=='FT_INVALID'}">
          		<!-- 初审未通过... -->
          		<div class="plan plan1">
                <ul>
                    <li>提交申请</li>
                    <li class="w_90"><div class="c_f00">初审</div><p class="Province c_f00" title="未通过(${orgCheckResultMsg })">未通过(${orgCheckResultMsg })</p>
                    <c:if test="${userType==1 }">
	                    <!-- 企业用户-修改 -->
	                    <a class="blue underline cursor" href="<%=basePath%>/organ/getEnterpriseInfo.shtml?orgId=${orgId}">修改申请信息</a>
                    </c:if>
                    <c:if test="${userType==2 }">
                    	<!-- 个人用户-修改 -->
                    <a class="blue underline cursor" href="<%=basePath%>/organ/getPersernalInfo.shtml?orgId=${orgId}">修改申请信息</a>
                    </c:if>
                    
                    
                    </li>
                    <li class="c_8d"><div>复审</div></li>
                    <li class="c_8d">审核通过</li>
                </ul>
            </div>
          		
        </c:when>
        <c:when test="${orgCheckResultKey=='REVIEW'}">
         		<!-- 复审中... -->
         		<div class="plan">
               <ul>
                   <li>提交申请</li>
                   <li>初审</li>
                   <li class="w_90 c_8d"><div>复审</div><p class="Province" >审核中...</p></li>
                   <li class="c_8d">审核通过</li>
               </ul>
           </div>
        </c:when>
    </c:choose>
	<!-- 微信 -->
    <c:if test="${ orgBindStatus==1}">
	<div class="WC">
        <img src="<%=cssSaasPath%>/images/TFS/home12.jpg">               
        <p>关注泰坦金融公众号,同步获取账户资产信息</p>
    </div>
	</c:if>

</div>
<div class="mc_img">
<div class="swiper-container">
<div class="swiper-wrapper">
    <div class="swiper-slide"><a href="<%=basePath %>/receipt.shtml#content" class="home01">&nbsp;</a></div>
    <div class="swiper-slide"><a href="<%=basePath %>/pay.shtml#content" class="home02">&nbsp;</a></div>
    <div class="swiper-slide"><a href="<%=basePath %>/loan.shtml#content" class="home03">&nbsp;</a></div>             
</div>
<div class="swiper-pagination"></div>
<div class="swiper-button-next"></div>
<div class="swiper-button-prev"></div>
</div>

</div>
</div>