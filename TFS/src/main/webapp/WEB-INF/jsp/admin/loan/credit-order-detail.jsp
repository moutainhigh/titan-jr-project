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
								<td>${getCreditInfoResponse.creditCompany.taxregno }</td>
								<td class="bg_f2"><i class="c_f00">*</i>组织机构代码：</td>
								<td>${getCreditInfoResponse.creditCompany.orgcode }</td>
							</tr>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>平台注册账号：</td>
								<td>${getCreditInfoResponse.creditCompany.regaccount }</td>
								<td class="bg_f2"><i class="c_f00">*</i>平台注册日期：</td>
								<td>${getCreditInfoResponse.creditCompany.regdate }</td>
							</tr>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>企业注册地址：</td>
								<td colspan="3">${getCreditInfoResponse.creditCompany.regaddress }</td>
							</tr>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>企业办公地址：</td>
								<td colspan="3">${getCreditInfoResponse.creditCompany.officeaddress }</td>
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
								<td>${getCreditInfoResponse.creditCompany.legalname }</td>
								<td class="bg_f2"><i class="c_f00">*</i>法人证件号：</td>
								<td><c:if test="${getCreditInfoResponse.creditCompany.legalno ==1}">身份证</c:if> <c:if test="${getCreditInfoResponse.creditCompany.legalno ==2}">护照</c:if>	${getCreditInfoResponse.creditCompany.legalno }</td>
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
								<td>${getCreditInfoResponse.creditCompany.contactname }</td>
								<td class="bg_f2"><i class="c_f00">*</i>联系电话：</td>
								<td>${getCreditInfoResponse.creditCompany.contactphone }</td>
							</tr>							
						</table>
					</div>
				</div>
				<div class="cl_c dn">
					<div class="clc_t">
						<i></i>股东信息
					</div>
					<div class="clc_c">
						<table border="0" cellspacing="0" width="100%">
							<colgroup>
								<col width="125">
								<col width="">
								<col width="110">
								<col width="100">
								<col width="132">
								<col width="100">
							</colgroup>
							<c:forEach items="${getCreditInfoResponse.creditCompany.controllDatas}" var="loanControllDataBean">
								<tr>
									<td class="bg_f2"><i class="c_f00">*</i>股东名称：</td>
									<td>${loanControllDataBean.shareholderName }</td>
									<td class="bg_f2"><i class="c_f00">*</i>出资金额：</td>
									<td>${loanControllDataBean.contributionAmount }</td>
									<td class="bg_f2"><i class="c_f00">*</i>股权比例：</td>
									<td>${loanControllDataBean.equityRatio}</td>
								</tr>		
							</c:forEach>					
						</table>
					</div>
					<div class="clc_t">
						<i></i>主营业务信息
					</div>
					<div class="clc_c">
						<table border="0" cellspacing="0" width="100%">
							<colgroup>
								<col width="125">
								<col width="">
								<col width="110">
								<col width="100">
								<col width="132">
								<col width="100">
							</colgroup>
							<c:forEach items="${getCreditInfoResponse.creditCompany.mainBusinessDatas}" var="loanMainBusinessDataBean">
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>主要产品/服务：</td>
								<td>${loanMainBusinessDataBean.mainProductsOrService }</td>
								<td class="bg_f2"><i class="c_f00">*</i>年销售规模：</td>
								<td>${loanMainBusinessDataBean.mainAnnualSale }</td>
								<td class="bg_f2"><i class="c_f00">*</i>占总销售额比例：</td>
								<td>${loanMainBusinessDataBean.mainSaleProportion }</td>
							</tr>	
							</c:forEach>						
						</table>
					</div>
					<div class="clc_t">
						<i></i>合作企业信息
					</div>
					<div class="clc_c">
						<table border="0" cellspacing="0" width="100%">
							<colgroup>
								<col width="125">
								<col width="">
								<col width="110">
								<col width="100">
								<col width="132">
								<col width="100">
							</colgroup>
							<c:forEach items="${getCreditInfoResponse.creditCompany.cooperationCompanyInfos}" var="loanCooperationCompanyBean">
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
							</c:forEach>					
						</table>
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
									<label class="f_ui-radio-c3 m_r15"><input name="r1" type="radio" checked=""><i></i> &nbsp;自有产权 </label>
									<label class="f_ui-radio-c3"><input name="r1" type="radio" /><i></i> &nbsp;租赁房屋</label>
								</td>
							</tr>	
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>租赁期限：</td>
								<td>${getCreditInfoResponse.creditCompany.companyLease.beginLeaseDate} - ${getCreditInfoResponse.creditCompany.companyLease.endLeaseDate} </td>
								<td class="bg_f2"><i class="c_f00">*</i>建筑面积：</td>
								<td>${getCreditInfoResponse.creditCompany.companyLease.housingArea}</td>								
							</tr>	
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>租金：</td>
								<td>${getCreditInfoResponse.creditCompany.companyLease.rental}</td>
								<td class="bg_f2"><i class="c_f00">*</i>支付方式：</td>
								<td>${getCreditInfoResponse.creditCompany.companyLease.paymentMethod}</td>								
							</tr>
							<tr>
								<td class="bg_f2"><i class="c_f00">*</i>租赁地址：</td>
								<td colspan="3">${getCreditInfoResponse.creditCompany.companyLease.leaseAddress}</td>															
							</tr>	
							<tr>
								<td class="bg_f2">备注</td>
								<td colspan="3">${getCreditInfoResponse.creditCompany.companyLease.remark}</td>															
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
									<label class="f_ui-radio-c3 m_r15"><input name="r2" type="radio" checked="" value="1"><i></i> &nbsp;自然人担保 </label>
									<label class="f_ui-radio-c3"><input name="r2" type="radio" value="2"/><i></i> &nbsp;企业担保</label>
								</td>
							</tr>
						</table>
					</div>
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

					<div class="surety1 dn">
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
								<td>某某某</td>
								<td class="bg_f2"><i class="c_f00">*</i>联系电话：</td>
								<td>132 1456 7894</td>								
							</tr>								
						</table>
					</div>
					</div>

				</div>
				<div class="cl_c dn">
					<div class="clc_t">
						<i></i>企业证件资料
					</div>
					<div class="clc_c">
						<ul>
							<li><p><i class="c_f00">*</i>企业营业执照</p>
							<img src="images/zhizhao.jpg" class="J_see"></li>
							<li><p><i class="c_f00">*</i>税务登记证</p>
							<img src="images/zhizhao.jpg" class="J_see"></li>
							<li><p><i class="c_f00">*</i>组织机构代码证</p>
							<img src="images/zhizhao.jpg" class="J_see"></li>
							<li><p><i class="c_f00">*</i>开户许可证</p>
							<img src="images/zhizhao.jpg" class="J_see"></li>
							<li><p><i class="c_f00">*</i>法人身份证</p>
							<img src="images/zhizhao.jpg" class="J_see"></li>
							<li><p><i class="c_f00">*</i>企业信用报告</p>
							<img src="images/zhizhao.jpg" class="J_see"></li>
							<li><p><i class="c_f00">*</i>企业银行流水</p>
							<img src="images/zhizhao.jpg" class="J_see"></li>
							<li><p><i class="c_f00">*</i>经营场所证明</p>
							<div class="download">
									<i class="PDF"></i>
									<span title="企业银行流企业银行流">企业银行流企业银行流</span>
									<a href="javascript:void(0)">下载</a>
								</div></li>
							<li><p><i class="c_f00">*</i>经营场所照片</p>
							<div class="download">
									<i class="RAR"></i>
									<span title="企业银行流">企业银行流</span>
									<a href="javascript:void(0)">下载</a>
								</div></li>
							<li><p><i class="c_f00">*</i>其他附件</p>
								<div class="download">
									<i class="ZIP"></i>
									<span title="企业银行流">企业银行流</span>
									<a href="javascript:void(0)">下载</a>
								</div>
							</li>
						</ul>
						<div class="clear" ></div>
					</div>	
					<div class="clc_t">
						<i></i>担保人证件资料
					</div>
					<div class="clc_c">
						<ul>
							<li><p><i class="c_f00">*</i>企业营业执照</p>
							<img src="images/zhizhao.jpg" class="J_see"></li>
							<li><p><i class="c_f00">*</i>税务登记证</p>
							<img src="images/zhizhao.jpg" class="J_see"></li>
							<li><p><i class="c_f00">*</i>组织机构代码证</p>
							<img src="images/zhizhao.jpg" class="J_see"></li>
							<li><p><i class="c_f00">*</i>开户许可证</p>
							<img src="images/zhizhao.jpg" class="J_see"></li>
							<li><p><i class="c_f00">*</i>法人身份证</p>
							<img src="images/zhizhao.jpg" class="J_see"></li>							
						</ul>
						<div class="clear" ></div>
					</div>
				</div>
			</div>
			<div class="c_right">
				<div class="examine_righttop">
				<h3>审核结果</h3>
				<p class="ptb9"><span class="w80"><i class="c_fc2020">*</i>审核人：</span>王峰</p>
				<p class="ptb9"><span class="w80"><i class="c_fc2020">*</i>审核结果：</span>
					<select name="" class="w100 select w135 J_nothroght">
						<option value="">请选择</option>
						<option value="">通过</option>
						<option value="">不通过</option>
					</select>
				</p>
				<p class="ptb9 showhide"><span class="w80"><i class="c_fc2020">*</i>未通过原因：</span>
					<textarea name="" class="textarea " placeholder="请输入未通过原因。"></textarea>
				</p>	
				</div>	
				<div class="examine_bobut">
					<span class=" cursor w85 btn_red fs16">保存</span>				
				</div>
			</div>
		</div>		
	</div>
  
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
		var value = $(this).val();			
		if(value==1 ){
			$('.surety').show();
			$('.surety1').hide();
		}else{
			$('.surety').hide();
			$('.surety1').show();
		}
		scrollCon();
	})

	//查看
	$('.J_see').on('click',function(){
	    $.ajax({
	        dataType : 'html',
	        context: document.body,
	        url : '信贷-授信审核-查看.html',            
	        success : function(html){
	           	var d =  window.top.dialog({
	                title: '企业营业执照',
	                padding: '0 0 0px 0',
	                width: 740,
	                content: html,
	                skin : 'saas_pop'  
	            }).showModal();
	           
	        }
	    });
	}); 
    
	$("#i_to_check_count").html($("#creditOrderCount").val());
    
   </script>
  
   <style>
	.f_ui-radio-c3 i{background-position: -48px 2px;}
	.f_ui-radio-c3 input[type="radio"]:checked + i {background-position: -68px 2px;}
	</style>
  </body>
</html>
