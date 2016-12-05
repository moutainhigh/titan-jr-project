<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>授信申请审核-泰坦金融管理平台</title>
	 <jsp:include page="/comm/admin/admin-static-resource.jsp"></jsp:include>
</head>
  <body>
  <jsp:include page="/comm/admin/admin-head.jsp">
  	<jsp:param value="loan" name="menu"/>
  </jsp:include>
  <div class="my_acount">
		<div class="mya_title">
			<strong>授信审核</strong>
			<p>
				<a class="on" href="javascript:void(0)">授信审核<i id="i_to_check_count">0</i></a>			
			</p>	
		</div>	
		<div class="crumbs">
			<a href="javascript:history.go(-1)" ><i></i>返回上一步</a>
			<span>信贷  > 授信审核 > ${getCreditInfoResponse.creditCompany.name }</span>
		</div>	
		<div class="credit">
			<div class="c_left">
				<div class="cl_title">
					<ul>
						<li class="lion"><i></i>企业基本信息</li>
						<li><i></i>企业补充信息</li>
						<li><i></i>担保人信息</li>
						<li><i></i>上传资料</li>
					</ul>
				</div>
				<div class="cl_c">
					<div class="clc_t">
						<i></i>基本信息
					</div>
					<div class="clc_c">
						<table border="0" cellspacing="0" width="100%">
							<colgroup>
								<col width="120">
								<col width="">
								<col width="190">
								<col width="216">
							</colgroup>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>企业名称：</td>
								<td>${getCreditInfoResponse.creditCompany.name }</td>
								<td class="bg_f2"><i class="c_f00">*</i>企业成立日期：</td>
								<td>${getCreditInfoResponse.creditCompany.startDate }</td>
							</tr>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>企业规模：</td>
								<td>${getCreditInfoResponse.creditCompany.orgSize }</td>
								<td class="bg_f2"><i class="c_f00">*</i>营业执照号/社会信用代码：</td>
								<td>${getCreditInfoResponse.creditCompany.orgSize }</td>
							</tr>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>税务登记号：</td>
								<td>${getCreditInfoResponse.creditCompany.taxRegNo }</td>
								<td class="bg_f2"><i class="c_f00">*</i>组织机构代码：</td>
								<td>${getCreditInfoResponse.creditCompany.orgCode }</td>
							</tr>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>平台注册账号：</td>
								<td>${getCreditInfoResponse.creditCompany.regAccount }</td>
								<td class="bg_f2"><i class="c_f00">*</i>平台注册日期：</td>
								<td>${getCreditInfoResponse.creditCompany.regDate }</td>
							</tr>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>企业注册地址：</td>
								<td colspan="3">${getCreditInfoResponse.creditCompany.regAddress }</td>
							</tr>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>企业办公地址：</td>
								<td colspan="3">${getCreditInfoResponse.creditCompany.officeAddress }</td>
							</tr>
						</table>
					</div>
					<div class="clc_t">
						<i></i>法人信息
					</div>
					<div class="clc_c">
						<table border="0" cellspacing="0" width="100%">
							<colgroup>
								<col width="120">
								<col width="">
								<col width="190">
								<col width="216">
							</colgroup>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>法人姓名：</td>
								<td>${getCreditInfoResponse.creditCompany.legalName }</td>
								<td class="bg_f2"><i class="c_f00">*</i>法人证件号：</td>
								<td><c:if test="${getCreditInfoResponse.creditCompany.legalNo ==1}">身份证</c:if> <c:if test="${getCreditInfoResponse.creditCompany.legalNo ==2}">护照</c:if>	${getCreditInfoResponse.creditCompany.legalNo }</td>
							</tr>							
						</table>
					</div>
					<div class="clc_t">
						<i></i>联系人信息
					</div>
					<div class="clc_c">
						<table border="0" cellspacing="0" width="100%">
							<colgroup>
								<col width="120">
								<col width="">
								<col width="190">
								<col width="216">
							</colgroup>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>联系人姓名：</td>
								<td>${getCreditInfoResponse.creditCompany.contactName }</td>
								<td class="bg_f2"><i class="c_f00">*</i>联系电话：</td>
								<td>${getCreditInfoResponse.creditCompany.contactPhone }</td>
							</tr>							
						</table>
					</div>
				</div>
				<div class="cl_c dn">
					<div class="clc_t">
						<i></i>股东信息
					</div>
					<div class="clc_c">
						<c:forEach items="${getCreditInfoResponse.companyAppendInfo.controllDatas}" var="loanControllDataBean">
							<table border="0" cellspacing="0" width="100%">
								<colgroup>
									<col width="125">
									<col width="">
									<col width="110">
									<col width="100">
									<col width="132">
									<col width="100">
								</colgroup>
								<tr>
									<td class="bg_f2"><i class="c_f00">*</i>股东名称：</td>
									<td>${loanControllDataBean.shareholderName }</td>
									<td class="bg_f2"><i class="c_f00">*</i>出资金额：</td>
									<td>${loanControllDataBean.contributionAmount }</td>
									<td class="bg_f2"><i class="c_f00">*</i>股权比例：</td>
									<td>${loanControllDataBean.equityRatio}</td>
								</tr>		
							</table>
						</c:forEach>
					</div>
					<div class="clc_t">
						<i></i>主营业务信息
					</div>
					<div class="clc_c">
						<c:forEach items="${getCreditInfoResponse.companyAppendInfo.mainBusinessDatas}" var="loanMainBusinessDataBean">
							<table border="0" cellspacing="0" width="100%">
								<colgroup>
									<col width="125">
									<col width="">
									<col width="110">
									<col width="100">
									<col width="132">
									<col width="100">
								</colgroup>
								<tr>
									<td class="bg_f2"><i class="c_f00">*</i>主要产品/服务：</td>
									<td>${loanMainBusinessDataBean.mainProductsOrService }</td>
									<td class="bg_f2"><i class="c_f00">*</i>年销售规模：</td>
									<td>${loanMainBusinessDataBean.mainAnnualSale }</td>
									<td class="bg_f2"><i class="c_f00">*</i>占总销售额比例：</td>
									<td>${loanMainBusinessDataBean.mainSaleProportion }</td>
								</tr>	
							</table>
						</c:forEach>
					</div>
					<div class="clc_t">
						<i></i>合作企业信息
					</div>
					<div class="clc_c">
						<c:forEach items="${getCreditInfoResponse.companyAppendInfo.cooperationCompanyInfos}" var="loanCooperationCompanyBean">
							<table border="0" cellspacing="0" width="100%">
								<colgroup>
									<col width="125">
									<col width="">
									<col width="110">
									<col width="100">
									<col width="132">
									<col width="100">
								</colgroup>
								<tr>
									<td class="bg_f2"><i class="c_f00">*</i>合作企业名称：</td>
									<td>${loanCooperationCompanyBean.cooperationName }</td>
									<td class="bg_f2"><i class="c_f00">*</i>年交易额：</td>
									<td>${loanCooperationCompanyBean.yearAnnualSale }</td>
									<td class="bg_f2"><i class="c_f00">*</i>占总销售额比例：</td>
									<td>${loanCooperationCompanyBean.saleProportion }</td>
								</tr>	
								<tr>
									<td class="bg_f2"><i class="c_f00">*</i>结算方式：</td>
									<td>${loanCooperationCompanyBean.settlement }</td>
									<td class="bg_f2"><i class="c_f00">*</i>合作年限：</td>
									<td>${loanCooperationCompanyBean.cooperationYears }</td>
									<td class="bg_f2"><i class="c_f00">*</i>合作关系：</td>
									<td>${loanCooperationCompanyBean.cooperation }</td>
								</tr>	
											
							</table>
						</c:forEach>		
					</div>
					<div class="clc_t">
						<i></i>经营场所信息
					</div>
					<div class="clc_c">
						<table border="0" cellspacing="0" width="100%">
							<colgroup>
								<col width="125">
								<col width="">
								<col width="130">
								<col width="150">								
							</colgroup>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>经营场所：</td>
								<td colspan="3">
									<label class="f_ui-radio-c3 m_r15"><input name="r1" type="radio"   <c:if test="${getCreditInfoResponse.companyAppendInfo.companyLease.leaseType==1 }">checked="checked"</c:if>><i></i> &nbsp;自有产权 </label>
									<label class="f_ui-radio-c3"><input name="r1" type="radio"   <c:if test="${getCreditInfoResponse.companyAppendInfo.companyLease.leaseType==2 }">checked="checked"</c:if>/><i></i> &nbsp;租赁房屋</label>
								</td>
							</tr>	
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>租赁期限：</td>
								<td>${getCreditInfoResponse.companyAppendInfo.companyLease.beginLeaseDate} - ${getCreditInfoResponse.companyAppendInfo.companyLease.endLeaseDate} </td>
								<td class="bg_f2"><i class="c_f00">*</i>建筑面积：</td>
								<td>${getCreditInfoResponse.companyAppendInfo.companyLease.housingArea}</td>								
							</tr>	
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>租金：</td>
								<td>${getCreditInfoResponse.companyAppendInfo.companyLease.rental}</td>
								<td class="bg_f2"><i class="c_f00">*</i>支付方式：</td>
								<td>${getCreditInfoResponse.companyAppendInfo.companyLease.paymentMethod}</td>								
							</tr>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>租赁地址：</td>
								<td colspan="3">${getCreditInfoResponse.companyAppendInfo.companyLease.leaseAddress}</td>															
							</tr>	
							<tr>
								<td class="bg_f2">备注</td>
								<td colspan="3">${getCreditInfoResponse.companyAppendInfo.companyLease.remark}</td>															
							</tr>					
						</table>
					</div>
				</div>
				<div class="cl_c dn">
					<div class="clc_t">
						<i></i>担保人类型
					</div>
					<div class="clc_c clc_surety">
						<table border="0" cellspacing="0" width="100%">
							<colgroup>
								<col width="105">
								<col width="">															
							</colgroup>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>担保人类型：</td>
								<td >
									<label class="f_ui-radio-c3 m_r15"><input name="r2" type="radio" <c:if test="${getCreditInfoResponse.creditOrder.assureType==1}">checked="checked"</c:if> value="1"/><i></i> &nbsp;自然人担保 </label>
									<label class="f_ui-radio-c3"><input name="r2" type="radio"  <c:if test="${getCreditInfoResponse.creditOrder.assureType==2}">checked="checked"</c:if> value="2"/><i></i> &nbsp;企业担保</label>
								</td>
							</tr>
						</table>
					</div>
					<c:if test="${getCreditInfoResponse.creditOrder.assureType==1}">
					<div class="surety">
					<div class="clc_t">
						<i></i>担保人基本信息
					</div>
					<div class="clc_c">
						<table border="0" cellspacing="0" width="100%">
							<colgroup>
								<col width="105">
								<col width="">
								<col width="95">
								<col width="170">
								<col width="95">
								<col width="120">
							</colgroup>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>姓名：</td>
								<td>${getCreditInfoResponse.loanPersonEnsure.personName}</td>
								<td class="bg_f2"><i class="c_f00">*</i>身份证号：</td>
								<td>${getCreditInfoResponse.loanPersonEnsure.nationalIdentityNumber}</td>
								<td class="bg_f2"><i class="c_f00">*</i>手机号码：</td>
								<td>${getCreditInfoResponse.loanPersonEnsure.mobilenNmber}</td>
							</tr>	
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>婚姻状况：</td>
								<td>${getCreditInfoResponse.loanPersonEnsure.marriageStatus}</td>
								<td class="bg_f2"><i class="c_f00">*</i>有无子女：</td>
								<td>${getCreditInfoResponse.loanPersonEnsure.haveChildren}</td>
								<td class="bg_f2"><i class="c_f00">*</i>籍贯：</td>
								<td>${getCreditInfoResponse.loanPersonEnsure.nativePlace}</td>
							</tr>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>现居住地址：</td>
								<td colspan="5">${getCreditInfoResponse.loanPersonEnsure.currentLiveaAdress}</td>
							</tr>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>毕业学校：</td>
								<td>${getCreditInfoResponse.loanPersonEnsure.graduateSchool}</td>
								<td class="bg_f2"><i class="c_f00">*</i>最高学历：</td>
								<td>${getCreditInfoResponse.loanPersonEnsure.highestEducation}</td>
								<td class="bg_f2"><i class="c_f00">*</i>工作年限：</td>
								<td>${getCreditInfoResponse.loanPersonEnsure.yearsWorking}</td>
							</tr>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>工作单位：</td>
								<td>${getCreditInfoResponse.loanPersonEnsure.workCompany}</td>
								<td class="bg_f2"><i class="c_f00">*</i>现任职务：</td>
								<td>${getCreditInfoResponse.loanPersonEnsure.occupation}</td>
								<td class="bg_f2"><i class="c_f00">*</i>工作电话：</td>
								<td>${getCreditInfoResponse.loanPersonEnsure.worktelePhone}</td>
							</tr>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>办公地址：</td>
								<td colspan="3">${getCreditInfoResponse.loanPersonEnsure.officeAddress}</td>
								<td class="bg_f2"><i class="c_f00">*</i>所处行业：</td>
								<td>${getCreditInfoResponse.loanPersonEnsure.industry}</td>
							</tr>
						</table>
					</div>
					<div class="clc_t">
						<i></i>资产情况
					</div>
					<div class="clc_c">
						<table border="0" cellspacing="0" width="100%">
							<colgroup>
								<col width="105">
								<col width="">
								<col width="95">
								<col width="170">
								<col width="95">
								<col width="120">
							</colgroup>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>车产情况：</td>
								<td><c:if test="${getCreditInfoResponse.loanPersonEnsure.carPropertyType==1}">无车</c:if><c:if test="${getCreditInfoResponse.loanPersonEnsure.carPropertyType==2}">有车无贷</c:if><c:if test="${getCreditInfoResponse.loanPersonEnsure.carPropertyType==3}">有车有贷</c:if></td>
								<td class="bg_f2"><i class="c_f00">*</i>购车年份：</td>
								<td>${getCreditInfoResponse.loanPersonEnsure.carPurchaseDate}</td>
								<td class="bg_f2"><i class="c_f00">*</i>汽车品牌：</td>
								<td>${getCreditInfoResponse.loanPersonEnsure.carBrand}</td>
							</tr>	
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>房产情况：</td>
								<td><c:if test="${getCreditInfoResponse.loanPersonEnsure.housePropertyType==1}">无房</c:if><c:if test="${getCreditInfoResponse.loanPersonEnsure.housePropertyType==2}">有房有贷</c:if><c:if test="${getCreditInfoResponse.loanPersonEnsure.housePropertyType==3}">有房无贷</c:if></td>
								<td class="bg_f2"><i class="c_f00">*</i>其他资产：</td>
								<td colspan="3">${getCreditInfoResponse.loanPersonEnsure.otherProperty}</td>
							</tr>
							<tr>
								<td class="bg_f2">补充说明：</td>
								<td colspan="5">${getCreditInfoResponse.loanPersonEnsure.propertyRemark}</td>
							</tr>
						</table>
					</div>	
					<div class="clc_t">
						<i></i>联系人信息
					</div>
					<div class="clc_c">
						<table border="0" cellspacing="0" width="100%">
							<colgroup>
								<col width="132">
								<col width="">
								<col width="132">
								<col width="170">
								<col width="132">
								<col width="120">
							</colgroup>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>第一联系人姓名：</td>
								<td>${getCreditInfoResponse.loanPersonEnsure.firstContactName}</td>
								<td class="bg_f2"><i class="c_f00">*</i>第一联系人电话：</td>
								<td>${getCreditInfoResponse.loanPersonEnsure.firstContactPhone}</td>
								<td class="bg_f2"><i class="c_f00">*</i>与担保人关系：</td>
								<td>
									<c:if test="${getCreditInfoResponse.loanPersonEnsure.relationToguarantee1==1}">父母</c:if>
									<c:if test="${getCreditInfoResponse.loanPersonEnsure.relationToguarantee1==2}">子女</c:if>
									<c:if test="${getCreditInfoResponse.loanPersonEnsure.relationToguarantee1==3}">配偶</c:if>
									<c:if test="${getCreditInfoResponse.loanPersonEnsure.relationToguarantee1==4}">朋友</c:if>
									<c:if test="${getCreditInfoResponse.loanPersonEnsure.relationToguarantee1==5}">同事</c:if>
									<c:if test="${getCreditInfoResponse.loanPersonEnsure.relationToguarantee1==6}">其他</c:if>
								</td>
							</tr>	
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>第二联系人姓名：</td>
								<td>${getCreditInfoResponse.loanPersonEnsure.secondContactName}</td>
								<td class="bg_f2"><i class="c_f00">*</i>第二联系人电话：</td>
								<td>${getCreditInfoResponse.loanPersonEnsure.secondContactPhone}</td>
								<td class="bg_f2"><i class="c_f00">*</i>与担保人关系：</td>
								<td>
									<c:if test="${getCreditInfoResponse.loanPersonEnsure.relationToguarantee2==1}">父母</c:if>
									<c:if test="${getCreditInfoResponse.loanPersonEnsure.relationToguarantee2==2}">子女</c:if>
									<c:if test="${getCreditInfoResponse.loanPersonEnsure.relationToguarantee2==3}">配偶</c:if>
									<c:if test="${getCreditInfoResponse.loanPersonEnsure.relationToguarantee2==4}">朋友</c:if>
									<c:if test="${getCreditInfoResponse.loanPersonEnsure.relationToguarantee2==5}">同事</c:if>
									<c:if test="${getCreditInfoResponse.loanPersonEnsure.relationToguarantee2==6}">其他</c:if>
								</td>
							</tr>
						</table>
					</div>
					</div>	
					</c:if>
					<c:if test="${getCreditInfoResponse.creditOrder.assureType==2}">
					<div class="surety1">
					<div class="clc_t">
						<i></i>基本信息
					</div>
					<div class="clc_c">
						<table border="0" cellspacing="0" width="100%">
							<colgroup>
								<col width="120">
								<col width="">
								<col width="190">
								<col width="215">								
							</colgroup>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>企业名称：</td>
								<td>${getCreditInfoResponse.companyEnsure.companyName}</td>
								<td class="bg_f2"><i class="c_f00">*</i>企业成立日期：</td>
								<td>${getCreditInfoResponse.companyEnsure.foundDate}</td>								
							</tr>	
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>企业规模：</td>
								<td>
									<c:if test="${getCreditInfoResponse.companyEnsure.enterpriseScale==1}">1-50</c:if>
									<c:if test="${getCreditInfoResponse.companyEnsure.enterpriseScale==2}">50-100</c:if>
									<c:if test="${getCreditInfoResponse.companyEnsure.enterpriseScale==3}">100-500</c:if>
									<c:if test="${getCreditInfoResponse.companyEnsure.enterpriseScale==4}">500-1000</c:if>
									<c:if test="${getCreditInfoResponse.companyEnsure.enterpriseScale==5}">1000以上</c:if>
								</td>
								<td class="bg_f2"><i class="c_f00">*</i>营业执照号/社会信用代码：</td>
								<td>${getCreditInfoResponse.companyEnsure.businessLicense}</td>								
							</tr>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>税务登记号：</td>
								<td>${getCreditInfoResponse.companyEnsure.taxRegisterCode}</td>
								<td class="bg_f2"><i class="c_f00">*</i>组织机构代码：</td>
								<td>${getCreditInfoResponse.companyEnsure.orgCodeCertificate}</td>								
							</tr>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>平台注册账号：</td>
								<td>${getCreditInfoResponse.companyEnsure.registerAccount}</td>
								<td class="bg_f2"><i class="c_f00">*</i>平台注册日期：</td>
								<td>${getCreditInfoResponse.companyEnsure.registerDate}</td>								
							</tr>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>企业注册地址：</td>
								<td colspan="5">${getCreditInfoResponse.companyEnsure.regAddress}</td>
							</tr>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>企业办公地址：</td>
								<td colspan="5">${getCreditInfoResponse.companyEnsure.officeAddress}</td>
							</tr>
						</table>
					</div>
					<div class="clc_t">
						<i></i>法人信息
					</div>
					<div class="clc_c">
						<table border="0" cellspacing="0" width="100%">
							<colgroup>
								<col width="120">
								<col width="">
								<col width="190">
								<col width="215">								
							</colgroup>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>法人姓名：</td>
								<td>${getCreditInfoResponse.companyEnsure.legalPersonName}</td>
								<td class="bg_f2"><i class="c_f00">*</i>法人证件号：</td>
								<td>${getCreditInfoResponse.companyEnsure.legalPersonCertificateNumber}</td>								
							</tr>								
						</table>
					</div>	
					<div class="clc_t">
						<i></i>联系人信息
					</div>
					<div class="clc_c">
						<table border="0" cellspacing="0" width="100%">
							<colgroup>
								<col width="120">
								<col width="">
								<col width="190">
								<col width="215">								
							</colgroup>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>联系人姓名：</td>
								<td>${getCreditInfoResponse.companyEnsure.contactName}</td>
								<td class="bg_f2"><i class="c_f00">*</i>联系电话：</td>
								<td>${getCreditInfoResponse.companyEnsure.contactPhone}</td>								
							</tr>								
						</table>
					</div>
					</div>
					</c:if>
				</div>
				<div class="cl_c dn">
					<div class="clc_t">
						<i></i>企业证件资料
					</div>
					<div class="clc_c">
						<ul>
							 <c:choose>
								<c:when test="${fn:endsWith(getCreditInfoResponse.creditCompany.licenseUrl, '.pdf') or fn:endsWith(getCreditInfoResponse.creditCompany.licenseUrl, '.zip') or fn:endsWith(getCreditInfoResponse.creditCompany.licenseUrl, '.rar')}">
									<li><p><i class="c_f00">*</i>企业营业执照</p>
										<div class="download">
											<i <c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.licenseUrl, '.pdf')}">class='PDF'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.licenseUrl, '.zip')}">class='ZIP'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.licenseUrl, '.rar')}">class='RAR'</c:if>></i>
											<span title=""></span>
											<a href="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.creditCompany.licenseUrl}">下载</a>
										</div>
									</li>
								</c:when>
								<c:otherwise>
									<!-- 直接显示图片资源 -->
									<li><p><i class="c_f00">*</i>企业营业执照</p>
									<img src="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.creditCompany.licenseUrl}" class="J_see"></li>
								</c:otherwise>
							</c:choose>
							
							<c:choose>
								<c:when test="${fn:endsWith(getCreditInfoResponse.creditCompany.taxRegUrl, '.pdf') or fn:endsWith(getCreditInfoResponse.creditCompany.taxRegUrl, '.zip') or fn:endsWith(getCreditInfoResponse.creditCompany.taxRegUrl, '.rar')}">
									<li><p><i class="c_f00">*</i>税务登记证</p>
										<div class="download">
											<i <c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.taxRegUrl, '.pdf')}">class='PDF'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.taxRegUrl, '.zip')}">class='ZIP'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.taxRegUrl, '.rar')}">class='RAR'</c:if>></i>
											<span title=""></span>
											<a href="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.creditCompany.taxRegUrl}">下载</a>
										</div>
									</li>
								</c:when>
								<c:otherwise>
									<!-- 直接显示图片资源 -->
									<li><p><i class="c_f00">*</i>税务登记证</p>
									<img src="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.creditCompany.taxRegUrl}" class="J_see"></li>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${fn:endsWith(getCreditInfoResponse.creditCompany.orgCodeUrl, '.pdf') or fn:endsWith(getCreditInfoResponse.creditCompany.orgCodeUrl, '.zip') or fn:endsWith(getCreditInfoResponse.creditCompany.orgCodeUrl, '.rar')}">
									<li><p><i class="c_f00">*</i>组织机构代码证</p>
										<div class="download">
											<i <c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.orgCodeUrl, '.pdf')}">class='PDF'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.orgCodeUrl, '.zip')}">class='ZIP'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.orgCodeUrl, '.rar')}">class='RAR'</c:if>></i>
											<span title=""></span>
											<a href="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.creditCompany.orgCodeUrl}">下载</a>
										</div>
									</li>
								</c:when>
								<c:otherwise>
									<!-- 直接显示图片资源 -->
									<li><p><i class="c_f00">*</i>组织机构代码证</p>
									<img src="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.creditCompany.orgCodeUrl}" class="J_see"></li>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${fn:endsWith(getCreditInfoResponse.creditCompany.accountUrl, '.pdf') or fn:endsWith(getCreditInfoResponse.creditCompany.accountUrl, '.zip') or fn:endsWith(getCreditInfoResponse.creditCompany.accountUrl, '.rar')}">
									<li><p><i class="c_f00">*</i>开户许可证</p>
										<div class="download">
											<i <c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.accountUrl, '.pdf')}">class='PDF'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.accountUrl, '.zip')}">class='ZIP'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.accountUrl, '.rar')}">class='RAR'</c:if>></i>
											<span title=""></span>
											<a href="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.creditCompany.accountUrl}">下载</a>
										</div>
									</li>
								</c:when>
								<c:otherwise>
									<!-- 直接显示图片资源 -->
									<li><p><i class="c_f00">*</i>开户许可证</p>
									<img src="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.creditCompany.accountUrl}" class="J_see"></li>
								</c:otherwise>
							</c:choose>
							
							<c:choose>
								<c:when test="${fn:endsWith(getCreditInfoResponse.creditCompany.legalNoUrl, '.pdf') or fn:endsWith(getCreditInfoResponse.creditCompany.legalNoUrl, '.zip') or fn:endsWith(getCreditInfoResponse.creditCompany.legalNoUrl, '.rar')}">
									<li><p><i class="c_f00">*</i>法人身份证</p>
										<div class="download">
											<i <c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.legalNoUrl, '.pdf')}">class='PDF'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.legalNoUrl, '.zip')}">class='ZIP'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.legalNoUrl, '.rar')}">class='RAR'</c:if>></i>
											<span title=""></span>
											<a href="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.creditCompany.legalNoUrl}">下载</a>
										</div>
									</li>
								</c:when>
								<c:otherwise>
									<!-- 直接显示图片资源 -->
									<li><p><i class="c_f00">*</i>法人身份证</p>
									<img src="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.creditCompany.legalNoUrl}" class="J_see"></li>
								</c:otherwise>
							</c:choose>
							
							<c:choose>
								<c:when test="${fn:endsWith(getCreditInfoResponse.creditCompany.creditUrl, '.pdf') or fn:endsWith(getCreditInfoResponse.creditCompany.creditUrl, '.zip') or fn:endsWith(getCreditInfoResponse.creditCompany.creditUrl, '.rar')}">
									<li><p><i class="c_f00">*</i>企业信用报告</p>
										<div class="download">
											<i <c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.creditUrl, '.pdf')}">class='PDF'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.creditUrl, '.zip')}">class='ZIP'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.creditUrl, '.rar')}">class='RAR'</c:if>></i>
											<span title=""></span>
											<a href="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.creditCompany.creditUrl}">下载</a>
										</div>
									</li>
								</c:when>
								<c:otherwise>
									<!-- 直接显示图片资源 -->
									<li><p><i class="c_f00">*</i>企业信用报告</p>
									<img src="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.creditCompany.creditUrl}" class="J_see"></li>
								</c:otherwise>
							</c:choose>
							
							<c:choose>
								<c:when test="${fn:endsWith(getCreditInfoResponse.creditCompany.waterUrl, '.pdf') or fn:endsWith(getCreditInfoResponse.creditCompany.waterUrl, '.zip') or fn:endsWith(getCreditInfoResponse.creditCompany.waterUrl, '.rar')}">
									<li><p><i class="c_f00">*</i>企业银行流水</p>
										<div class="download">
											<i <c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.waterUrl, '.pdf')}">class='PDF'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.waterUrl, '.zip')}">class='ZIP'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.waterUrl, '.rar')}">class='RAR'</c:if>></i>
											<span title=""></span>
											<a href="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.creditCompany.waterUrl}">下载</a>
										</div>
									</li>
								</c:when>
								<c:otherwise>
									<!-- 直接显示图片资源 -->
									<li><p><i class="c_f00">*</i>企业银行流水</p>
									<img src="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.creditCompany.waterUrl}" class="J_see"></li>
								</c:otherwise>
							</c:choose>
							
							<c:choose>
								<c:when test="${fn:endsWith(getCreditInfoResponse.creditCompany.officeNoUrl, '.pdf') or fn:endsWith(getCreditInfoResponse.creditCompany.officeNoUrl, '.zip') or fn:endsWith(getCreditInfoResponse.creditCompany.officeNoUrl, '.rar')}">
									<li><p><i class="c_f00">*</i>经营场所证明</p>
										<div class="download">
											<i <c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.officeNoUrl, '.pdf')}">class='PDF'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.officeNoUrl, '.zip')}">class='ZIP'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.officeNoUrl, '.rar')}">class='RAR'</c:if>></i>
											<span title=""></span>
											<a href="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.creditCompany.officeNoUrl}">下载</a>
										</div>
									</li>
								</c:when>
								<c:otherwise>
									<!-- 直接显示图片资源 -->
									<li><p><i class="c_f00">*</i>经营场所证明</p>
									<img src="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.creditCompany.officeNoUrl}" class="J_see"></li>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${fn:endsWith(getCreditInfoResponse.creditCompany.officeUrl, '.pdf') or fn:endsWith(getCreditInfoResponse.creditCompany.officeUrl, '.zip') or fn:endsWith(getCreditInfoResponse.creditCompany.officeUrl, '.rar')}">
									<li><p><i class="c_f00">*</i>经营场所照片</p>
										<div class="download">
											<i <c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.officeUrl, '.pdf')}">class='PDF'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.officeUrl, '.zip')}">class='ZIP'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.creditCompany.officeUrl, '.rar')}">class='RAR'</c:if>></i>
											<span title=""></span>
											<a href="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.creditCompany.officeUrl}">下载</a>
										</div>
									</li>
								</c:when>
								<c:otherwise>
									<!-- 直接显示图片资源 -->
									<li><p><i class="c_f00">*</i>经营场所照片</p>
									<img src="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.creditCompany.officeUrl}" class="J_see"></li>
								</c:otherwise>
							</c:choose>
							
						</ul>
						<div class="clear" ></div>
					</div>	
					<div class="clc_t">
						<i></i>担保人证件资料
					</div>
					<c:if test="${getCreditInfoResponse.companyEnsure!=null}">
						<!-- 企业担保 -->
						<div class="clc_c">
							<ul>
								<c:choose>
									<c:when test="${fn:endsWith(getCreditInfoResponse.companyEnsure.businessLicenseUrl, '.pdf') or fn:endsWith(getCreditInfoResponse.companyEnsure.businessLicenseUrl, '.zip') or fn:endsWith(getCreditInfoResponse.companyEnsure.businessLicenseUrl, '.rar')}">
										<li><p><i class="c_f00">*</i>企业营业执照</p>
											<div class="download">
												<i <c:if test="${fn:endsWith(getCreditInfoResponse.companyEnsure.businessLicenseUrl, '.pdf')}">class='PDF'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.companyEnsure.businessLicenseUrl, '.zip')}">class='ZIP'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.companyEnsure.businessLicenseUrl, '.rar')}">class='RAR'</c:if>></i>
												<span title=""></span>
												<a href="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.companyEnsure.businessLicenseUrl}">下载</a>
											</div>
										</li>
									</c:when>
									<c:otherwise>
										<!-- 直接显示图片资源 -->
										<li><p><i class="c_f00">*</i>企业营业执照</p>
										<img src="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.companyEnsure.businessLicenseUrl}" class="J_see"></li>
									</c:otherwise>
								</c:choose>
							
								<c:choose>
									<c:when test="${fn:endsWith(getCreditInfoResponse.companyEnsure.taxRegisterCodeUrl, '.pdf') or fn:endsWith(getCreditInfoResponse.companyEnsure.taxRegisterCodeUrl, '.zip') or fn:endsWith(getCreditInfoResponse.companyEnsure.taxRegisterCodeUrl, '.rar')}">
										<li><p><i class="c_f00">*</i>税务登记证</p>
											<div class="download">
												<i <c:if test="${fn:endsWith(getCreditInfoResponse.companyEnsure.taxRegisterCodeUrl, '.pdf')}">class='PDF'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.companyEnsure.taxRegisterCodeUrl, '.zip')}">class='ZIP'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.companyEnsure.taxRegisterCodeUrl, '.rar')}">class='RAR'</c:if>></i>
												<span title=""></span>
												<a href="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.companyEnsure.taxRegisterCodeUrl}">下载</a>
											</div>
										</li>
									</c:when>
									<c:otherwise>
										<!-- 直接显示图片资源 -->
										<li><p><i class="c_f00">*</i>税务登记证</p>
										<img src="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.companyEnsure.taxRegisterCodeUrl}" class="J_see"></li>
									</c:otherwise>
								</c:choose>
								
								<c:choose>
									<c:when test="${fn:endsWith(getCreditInfoResponse.companyEnsure.orgCodeCertificateUrl, '.pdf') or fn:endsWith(getCreditInfoResponse.companyEnsure.orgCodeCertificateUrl, '.zip') or fn:endsWith(getCreditInfoResponse.companyEnsure.orgCodeCertificateUrl, '.rar')}">
										<li><p><i class="c_f00">*</i>组织机构代码证</p>
											<div class="download">
												<i <c:if test="${fn:endsWith(getCreditInfoResponse.companyEnsure.orgCodeCertificateUrl, '.pdf')}">class='PDF'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.companyEnsure.orgCodeCertificateUrl, '.zip')}">class='ZIP'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.companyEnsure.orgCodeCertificateUrl, '.rar')}">class='RAR'</c:if>></i>
												<span title=""></span>
												<a href="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.companyEnsure.orgCodeCertificateUrl}">下载</a>
											</div>
										</li>
									</c:when>
									<c:otherwise>
										<!-- 直接显示图片资源 -->
										<li><p><i class="c_f00">*</i>组织机构代码证</p>
										<img src="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.companyEnsure.orgCodeCertificateUrl}" class="J_see"></li>
									</c:otherwise>
								</c:choose>
								
								<c:choose>
									<c:when test="${fn:endsWith(getCreditInfoResponse.companyEnsure.licenseUrl, '.pdf') or fn:endsWith(getCreditInfoResponse.companyEnsure.licenseUrl, '.zip') or fn:endsWith(getCreditInfoResponse.companyEnsure.licenseUrl, '.rar')}">
										<li><p><i class="c_f00">*</i>开户许可证</p>
											<div class="download">
												<i <c:if test="${fn:endsWith(getCreditInfoResponse.companyEnsure.licenseUrl, '.pdf')}">class='PDF'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.companyEnsure.licenseUrl, '.zip')}">class='ZIP'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.companyEnsure.licenseUrl, '.rar')}">class='RAR'</c:if>></i>
												<span title=""></span>
												<a href="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.companyEnsure.licenseUrl}">下载</a>
											</div>
										</li>
									</c:when>
									<c:otherwise>
										<!-- 直接显示图片资源 -->
										<li><p><i class="c_f00">*</i>开户许可证</p>
										<img src="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.companyEnsure.licenseUrl}" class="J_see"></li>
									</c:otherwise>
								</c:choose>
								
								<c:choose>
									<c:when test="${fn:endsWith(getCreditInfoResponse.companyEnsure.legalPersonUrl, '.pdf') or fn:endsWith(getCreditInfoResponse.companyEnsure.legalPersonUrl, '.zip') or fn:endsWith(getCreditInfoResponse.companyEnsure.legalPersonUrl, '.rar')}">
										<li><p><i class="c_f00">*</i>法人身份证</p>
											<div class="download">
												<i <c:if test="${fn:endsWith(getCreditInfoResponse.companyEnsure.legalPersonUrl, '.pdf')}">class='PDF'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.companyEnsure.legalPersonUrl, '.zip')}">class='ZIP'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.companyEnsure.legalPersonUrl, '.rar')}">class='RAR'</c:if>></i>
												<span title=""></span>
												<a href="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.companyEnsure.legalPersonUrl}">下载</a>
											</div>
										</li>
									</c:when>
									<c:otherwise>
										<!-- 直接显示图片资源 -->
										<li><p><i class="c_f00">*</i>法人身份证</p>
										<img src="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.companyEnsure.legalPersonUrl}" class="J_see"></li>
									</c:otherwise>
								</c:choose>
								
							</ul>
							<div class="clear" ></div>
						</div>
					</c:if>
					<c:if test="${getCreditInfoResponse.loanPersonEnsure!=null}">
						<!-- 个人担保 -->
						<div class="clc_c">
							<ul>
								<c:choose>
									<c:when test="${fn:endsWith(getCreditInfoResponse.loanPersonEnsure.idCardUrl, '.pdf') or fn:endsWith(getCreditInfoResponse.loanPersonEnsure.idCardUrl, '.zip') or fn:endsWith(getCreditInfoResponse.loanPersonEnsure.idCardUrl, '.rar')}">
										<li><p><i class="c_f00">*</i>担保人身份证</p>
											<div class="download">
												<i <c:if test="${fn:endsWith(getCreditInfoResponse.loanPersonEnsure.idCardUrl, '.pdf')}">class='PDF'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.loanPersonEnsure.idCardUrl, '.zip')}">class='ZIP'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.loanPersonEnsure.idCardUrl, '.rar')}">class='RAR'</c:if>></i>
												<span title=""></span>
												<a href="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.loanPersonEnsure.idCardUrl}">下载</a>
											</div>
										</li>
									</c:when>
									<c:otherwise>
										<!-- 直接显示图片资源 -->
										<li><p><i class="c_f00">*</i>担保人身份证</p>
										<img src="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.loanPersonEnsure.idCardUrl}" class="J_see"></li>
									</c:otherwise>
								</c:choose>
							
								<c:choose>
									<c:when test="${fn:endsWith(getCreditInfoResponse.loanPersonEnsure.registeredUrl, '.pdf') or fn:endsWith(getCreditInfoResponse.loanPersonEnsure.registeredUrl, '.zip') or fn:endsWith(getCreditInfoResponse.loanPersonEnsure.registeredUrl, '.rar')}">
										<li><p><i class="c_f00">*</i>担保人户口本</p>
											<div class="download">
												<i <c:if test="${fn:endsWith(getCreditInfoResponse.loanPersonEnsure.registeredUrl, '.pdf')}">class='PDF'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.loanPersonEnsure.registeredUrl, '.zip')}">class='ZIP'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.loanPersonEnsure.registeredUrl, '.rar')}">class='RAR'</c:if>></i>
												<span title=""></span>
												<a href="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.loanPersonEnsure.registeredUrl}">下载</a>
											</div>
										</li>
									</c:when>
									<c:otherwise>
										<!-- 直接显示图片资源 -->
										<li><p><i class="c_f00">*</i>担保人户口本</p>
										<img src="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.loanPersonEnsure.registeredUrl}" class="J_see"></li>
									</c:otherwise>
								</c:choose>
							
								<c:choose>
									<c:when test="${fn:endsWith(getCreditInfoResponse.loanPersonEnsure.spouseRegisteredUrl, '.pdf') or fn:endsWith(getCreditInfoResponse.loanPersonEnsure.spouseRegisteredUrl, '.zip') or fn:endsWith(getCreditInfoResponse.loanPersonEnsure.spouseRegisteredUrl, '.rar')}">
										<li><p><i class="c_f00">*</i>配偶户口本</p>
											<div class="download">
												<i <c:if test="${fn:endsWith(getCreditInfoResponse.loanPersonEnsure.spouseRegisteredUrl, '.pdf')}">class='PDF'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.loanPersonEnsure.spouseRegisteredUrl, '.zip')}">class='ZIP'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.loanPersonEnsure.spouseRegisteredUrl, '.rar')}">class='RAR'</c:if>></i>
												<span title=""></span>
												<a href="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.loanPersonEnsure.spouseRegisteredUrl}">下载</a>
											</div>
										</li>
									</c:when>
									<c:otherwise>
										<!-- 直接显示图片资源 -->
										<li><p><i class="c_f00">*</i>配偶户口本</p>
										<img src="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.loanPersonEnsure.spouseRegisteredUrl}" class="J_see"></li>
									</c:otherwise>
								</c:choose>
								
								<c:choose>
									<c:when test="${fn:endsWith(getCreditInfoResponse.loanPersonEnsure.spouseIdCardUrl, '.pdf') or fn:endsWith(getCreditInfoResponse.loanPersonEnsure.spouseIdCardUrl, '.zip') or fn:endsWith(getCreditInfoResponse.loanPersonEnsure.spouseIdCardUrl, '.rar')}">
										<li><p><i class="c_f00">*</i>配偶身份证</p>
											<div class="download">
												<i <c:if test="${fn:endsWith(getCreditInfoResponse.loanPersonEnsure.spouseIdCardUrl, '.pdf')}">class='PDF'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.loanPersonEnsure.spouseIdCardUrl, '.zip')}">class='ZIP'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.loanPersonEnsure.spouseIdCardUrl, '.rar')}">class='RAR'</c:if>></i>
												<span title=""></span>
												<a href="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.loanPersonEnsure.spouseIdCardUrl}">下载</a>
											</div>
										</li>
									</c:when>
									<c:otherwise>
										<!-- 直接显示图片资源 -->
										<li><p><i class="c_f00">*</i>配偶身份证</p>
										<img src="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.loanPersonEnsure.spouseIdCardUrl}" class="J_see"></li>
									</c:otherwise>
								</c:choose>
								
								<c:choose>
									<c:when test="${fn:endsWith(getCreditInfoResponse.loanPersonEnsure.marriageUrl, '.pdf') or fn:endsWith(getCreditInfoResponse.loanPersonEnsure.marriageUrl, '.zip') or fn:endsWith(getCreditInfoResponse.loanPersonEnsure.marriageUrl, '.rar')}">
										<li><p><i class="c_f00">*</i>结婚证</p>
											<div class="download">
												<i <c:if test="${fn:endsWith(getCreditInfoResponse.loanPersonEnsure.marriageUrl, '.pdf')}">class='PDF'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.loanPersonEnsure.marriageUrl, '.zip')}">class='ZIP'</c:if><c:if test="${fn:endsWith(getCreditInfoResponse.loanPersonEnsure.marriageUrl, '.rar')}">class='RAR'</c:if>></i>
												<span title=""></span>
												<a href="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.loanPersonEnsure.marriageUrl}">下载</a>
											</div>
										</li>
									</c:when>
									<c:otherwise>
										<!-- 直接显示图片资源 -->
										<li><p><i class="c_f00">*</i>结婚证</p>
										<img src="http://image.fangcang.com/upload/images/titanjr/credit_apply/${getCreditInfoResponse.creditOrder.orgCode}/${getCreditInfoResponse.loanPersonEnsure.marriageUrl}" class="J_see"></li>
									</c:otherwise>
								</c:choose>
								
							</ul>
							<div class="clear" ></div>
						</div>
					</c:if>
				</div>
			</div>
			<div class="c_right">
				<div class="examine_righttop">
				<h3>审核结果</h3>
				<p class="ptb9"><span class="w80"><i class="c_fc2020">*</i>审核人：</span>王峰</p>
				<p class="ptb9"><span class="w80"><i class="c_fc2020">*</i>审核结果：</span>
					<select name="auditResult" id="auditResult" class="w100 select w135 J_nothroght">
						<option value="">请选择</option>
						<option value="3">通过</option>
						<option value="4">不通过</option>
					</select>
				</p>
				<p class="ptb9 showhide"><span class="w80"><i class="c_fc2020">*</i>未通过原因：</span>
					<textarea name="content" id="au_content" class="textarea " placeholder="请输入未通过原因。"></textarea>
				</p>	
				</div>	
				<div class="examine_bobut">
					<span class=" cursor w85 btn_red fs16 " id="btn_save">保存</span>				
				</div>
			</div>
		</div>		
	</div>
  <input type="hidden" value="${getCreditInfoResponse.orderNo }" id="credit_orderNo"/>
  <jsp:include page="/comm/admin/admin-static-js.jsp"></jsp:include>
    <script type="text/javascript">
  //选择未通过出现文本域
	$(".J_nothroght").on('change',function(){
		var _this=$(this);
		if(_this.find("option:selected").text()=='不通过'){
			_this.parent("p").next().show();
		}else{
			_this.parent("p").next().hide();
		}
	});
  	//保存
  	$("#btn_save").on("click",function(){
  		//TODO 校验
  		$.ajax({
  	        type:"post",
  	        data:{'orderNo':$("#credit_orderNo").val(),'auditResult':$("#auditResult").val(),'content':$("#au_content").val()},
  	        url : '<%=basePath%>/admin/check-credit-order.shtml',  
  	      	dataType : 'json',
  	      	beforeSend : function(){
  	      		top.F.loading.show();
  	      	},
  	        success : function(result){
  	           if(result.code==1){
  	        	   location.href="<%=basePath %>/admin/credit-order.shtml";
  	        	   flag = true;
  	           }else{
  	        	   new top.Tip({msg : result.msg, type: 3 , timer:2000});
  	           }
  	        },
  	        error:function(){
  	        	new top.Tip({msg : '请求失败，请重试', type: 3 , timer:2000});
  	        },
  	        complete:function(){
  	        	top.F.loading.hide();
  	        }
  			
  		});
  	});

	$('.cl_title li').on('click',function(){
		var _this = $(this),
			_index = _this.index();
		_this.addClass('lion').siblings().removeClass('lion');
		$('.cl_c').eq(_index).show().siblings('.cl_c').hide();		
		scrollCon();
	})
	//内容高度
	function scrollCon(){
		var c_left = $('.c_left').height();
		$('.c_right').height(c_left-20);
	}
	scrollCon();

	$(".clc_surety input[type=radio]").on('change',function(){
		
	});

	//查看
	$('.J_see').on('click',function(){
    	var img_url = $(this).attr("src");
        var _html = "<div class=\"S_popup clearfix S_popTop\">"
    				+"<div style=\"padding: 20px;\">"
    				+"<img src=\""+img_url+"\" width=\"700\" height=\"500\">"
    				+"</div>"
    				+"</div>";
        var d =  window.top.dialog({
              title: '图片预览',
              padding: '0 0 0px 0',
              width: 740,
              content: _html,
              skin : 'saas_pop'  
         }).showModal();
	}); 
    //刷新待审核数
	$("#i_to_check_count").html($("#creditOrderCount").val());
    
   </script>
  
   <style>
	.f_ui-radio-c3 i{background-position: -48px 2px;}
	.f_ui-radio-c3 input[type="radio"]:checked + i {background-position: -68px 2px;}
	</style>
  </body>
</html>
