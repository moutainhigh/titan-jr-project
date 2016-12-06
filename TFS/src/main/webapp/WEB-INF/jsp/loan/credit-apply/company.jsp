<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<style>

</style>
    <div class="loanBox">
        <div class="loanStep">
            <span class="cur">1. 填写企业基本信息<i></i></span><span>2. 填写企业补充信息<i></i></span><span>3. 填写担保人信息<i></i></span><span>4. 上传资料<i></i></span><span>5. 提交授信申请</span>
        </div>
        <div class="loanInformation">
            <h3 class="loanInformation_title">基本信息</h3>
            <table cellspacing="0" cellspacing="0" width="100%" class="loanInformation_tab">
                <colgroup>
                    <col width="160">
                    <col width="445">
                    <col width="200">
                    <col>
                </colgroup>
                <tr>
                    <td class="loanInformation_td01">
                        <i class="redNotice">*</i>企业名称：
                    </td>
                    <td class="loanInformation_td02">
                        <input type="text" value="" readonly="readonly" class="loanInformation_inp01"  placeholder="请输入企业名称" datatype="s1-50" errormsg="必填项，并且最长只能50个字符！" field="name">
                    </td>
                    <td class="loanInformation_td01">
                         <i class="redNotice">*</i>企业成立日期：
                    </td>
                    <td class="loanInformation_td02" id="dateSelector" fields="startDate" split="-" >
                    	<div customFun="validateSelects" errormsg="企业成立日期必须选择！"  style="display:inline-block;">
	                       <select item='year'  id="idYear" name="idYear" class="select loanInformation_se01" data="" onblur="$(this).parent().blur();"></select>
	                       <select item='month'  class="select loanInformation_se01" id="idMonth" name="idMonth" data="12" onblur="$(this).parent().blur();"></select>
	                       <select item='day'  id="idDay" name="idDay" class="select loanInformation_se01" data="1" onblur="$(this).parent().blur();"></select>
                       </div>
                    </td>
                </tr>
                 <tr>
                    <td class="loanInformation_td01">
                        <i class="redNotice">*</i>企业规模：
                    </td>
                    <td class="loanInformation_td02">
                        <select class="select loanInformation_se02"  field="orgSize"   customFun="validateSelect" errormsg="请选择企业规模！" >
                            <option>请选择</option>
                            <option value='1'>1 - 50人</option>
                            <option value='2'>50 - 100人</option>
                            <option value='3'>100 - 500人</option>
                            <option value='4'>500 - 1000人</option>
                            <option value='5'>1000人以上</option>
                        </select> 
                    </td>
                    <td class="loanInformation_td01">
                         <i class="redNotice">*</i>营业执照号/社会信用代码：
                    </td>
                    <td class="loanInformation_td02">
                         <input type="text" class="loanInformation_inp01" placeholder="请输入营业执照号/社会信用代码"  field="license" value="" readonly="readonly" datatype="*1-50" errormsg="必填项，并且最长只能50个字符！">
                    </td>
                </tr>
                <tr>
                    <td class="loanInformation_td01">
                        <i class="redNotice">*</i>税务登记号：
                    </td>
                    <td class="loanInformation_td02">
                         <input type="text" class="loanInformation_inp01" placeholder="请输入税务登记号" field="taxRegNo" value=""  datatype="*1-50" errormsg="必填项，并且最长只能50个字符！">
                    </td>
                    <td class="loanInformation_td01">
                         <i class="redNotice">*</i>组织机构代码：
                    </td>
                    <td class="loanInformation_td02">
                         <input type="text" class="loanInformation_inp01" placeholder="请输入组织机构代码"  field="orgCode" value="" datatype="*1-50" errormsg="必填项，并且最长只能50个字符！">
                    </td>
                </tr>
                <tr>
                    <td class="loanInformation_td01">
                        <i class="redNotice">*</i>平台注册账号：
                    </td>
                    <td class="loanInformation_td02">
                        <input type="text" class="loanInformation_inp01" placeholder="请输入平台注册账号" field="regAccount" value="" readonly="readonly" datatype="*1-50" errormsg="必填项，并且最长只能50个字符！">
                    </td>
                    <td class="loanInformation_td01">
                         <i class="redNotice">*</i>平台注册日期：
                    </td>
                     <td class="loanInformation_td02">
                        <input type="text" class="loanInformation_inp01" placeholder="" field="regDate" value="" readonly="readonly">
                    </td>
                </tr>
                
                
                 <tr>
                    <td class="loanInformation_td01">
                        <i class="redNotice">*</i>营业执照生效日期：
                    </td>
                   <td class="loanInformation_td02" id="certificateStartDate" fields="certificateStartDate" split="-" >
                    	<div customFun="validateSelects" errormsg="企业成立日期必须选择！"  style="display:inline-block;">
	                       <select item='year'  id="CertificateStartYear" name="idYear" class="select loanInformation_se01" data="" onblur="$(this).parent().blur();"></select>
	                       <select item='month'  class="select loanInformation_se01" id="CertificateStartMonth" name="idMonth" data="12" onblur="$(this).parent().blur();"></select>
	                       <select item='day'  id="CertificateStartDay" name="idDay" class="select loanInformation_se01" data="1" onblur="$(this).parent().blur();"></select>
                       </div>
                    </td>
                    <td class="loanInformation_td01">
                         <i class="redNotice">*</i>营业执照失效日期：
                    </td>
                     <td class="loanInformation_td02" id="certificateExpireDate" fields="certificateExpireDate" split="-" >
                    	<div customFun="validateSelects" errormsg="企业成立日期必须选择！"  style="display:inline-block;">
	                       <select item='year'  id="CertificateExpireYear" name="idYear" class="select loanInformation_se01" data="" onblur="$(this).parent().blur();"></select>
	                       <select item='month'  class="select loanInformation_se01" id="CertificateExpireMonth" name="idMonth" data="12" onblur="$(this).parent().blur();"></select>
	                       <select item='day'  id="CertificateExpireDay" name="idDay" class="select loanInformation_se01" data="1" onblur="$(this).parent().blur();"></select>
                       </div>
                    </td>
                </tr>
                
                
                  <tr>
                    <td class="loanInformation_td01">
                        <i class="redNotice">*</i>企业类型：
                    </td>
                     <td class="loanInformation_td02">
                        <select class="select loanInformation_se02"  field="companyType"   customFun="validateSelect" errormsg="请选择企业类型！" >
                            <option>请选择</option>
                            <option value='1'>有限责任公司</option>
                            <option value='2'>股份有限公司</option>
                            <option value='3'>内资</option>
                            <option value='4'>国有全资</option>
                            <option value='5'>集资全资</option>
                            <option value='6'>国外投资股份有限公司</option>
                            <option value='99'>其他</option>
                            
                        </select> 
                    </td>
                    <td class="loanInformation_td01">
                         <i class="redNotice">*</i>注册金额：
                    </td>
                     <td class="loanInformation_td02">
                        <input type="text" class="loanInformation_inp01" placeholder="" field="registFinance" value=""  datatype="*1-10" errormsg="必填项，并且最长只能10个字符！">
                    </td>
                </tr>
                
                
                <tr>
                    <td class="loanInformation_td01">
                        <i class="redNotice">*</i>企业注册地址：
                    </td>                  
                    <td class="loanInformation_td02" id="city1" colspan="3" fields="regAddress" split="/" >
                    	<div  customFun="validateSelects" errormsg="请选择企业注册地址!"  style="display:inline-block;">
                    	
	                        <select item="prov" class="prov select loanInformation_se01"   onblur="$(this).parent().blur();"></select> 
	                        <select item="city"  class="city select loanInformation_se01" disabled="disabled"  onblur="$(this).parent().blur();"></select>
	                        <select item="dist" class="dist select loanInformation_se01" disabled="disabled"  onblur="$(this).parent().blur();"></select>
	                        <input item="address" type="text" class="loanInformation_inp03" placeholder="请输入详细地址"   onblur="$(this).parent().blur();">
                       
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="loanInformation_td01">
                        <i class="redNotice">*</i>企业办公地址：
                    </td> 
                    <td class="loanInformation_td02" id="city2" colspan="3"  fields="officeAddress" split="/"  >
                    	<div customFun="validateSelects" errormsg="请选择企业办公地址!" style="display:inline-block;">
	                        <select item="prov" class="prov select loanInformation_se01"  onblur="$(this).parent().blur();"></select> 
	                        <select item="city"  class="city select loanInformation_se01" disabled="disabled"  onblur="$(this).parent().blur();"></select>
	                        <select item="dist" class="dist select loanInformation_se01" disabled="disabled"  onblur="$(this).parent().blur();"></select>
	                        <input item="address" type="text" class="loanInformation_inp03" placeholder="请输入详细地址"  onblur="$(this).parent().blur();">
                        </div>
                    </td>
                </tr>
            </table>
            <h3 class="loanInformation_title">法人信息</h3>
            <table cellspacing="0" cellspacing="0" width="100%" class="loanInformation_tab">
                <colgroup>
                    <col width="160">
                    <col width="445">
                    <col width="200">
                    <col>
                </colgroup>
                <tr>
                    <td class="loanInformation_td01">
                        <i class="redNotice">*</i>法人姓名：
                    </td>
                    <td class="loanInformation_td02">
                        <input type="text" class="loanInformation_inp01" field='legalName' placeholder="请输入法人姓名" value="" datatype="s1-10" errormsg="必填项，并且最长只能10个字符！">
                    </td>
                    <td class="loanInformation_td01">
                         <i class="redNotice">*</i>法人证件号：
                    </td>
                    <td class="loanInformation_td02">
                       <select class="select loanInformation_se01" field='legalceType'>
                           <option value="1" selected="selected">身份证</option>
                           <option value="2">护照</option>
                       </select>
                       <input type="text" class="loanInformation_inp04" placeholder="请输入证件号码" field='legalNo' value="" datatype="*1-50" errormsg="并且最长只能50个字符！">
                    </td>
                </tr>
              </table>  
              <h3 class="loanInformation_title">联系人信息</h3>
            <table cellspacing="0" cellspacing="0" width="100%" class="loanInformation_tab">
                <colgroup>
                    <col width="160">
                    <col width="445">
                    <col width="200">
                    <col>
                </colgroup>
                <tr>
                    <td class="loanInformation_td01">
                        <i class="redNotice">*</i>联系人姓名：
                    </td>
                    <td class="loanInformation_td02">
                        <input  type="text" class="loanInformation_inp01" placeholder="请输入联系人姓名" field='contactName' value=""  datatype="s1-10" errormsg="必填项，并且最长只能10个字符！">
                    </td>
                    <td class="loanInformation_td01">
                         <i class="redNotice">*</i>联系电话：
                    </td>
                    <td class="loanInformation_td02">
                       <input  type="text" class="loanInformation_inp01" placeholder="请输入联系电话" field='contactPhone' value="" datatype="*1-20" errormsg="必填项，并且最长只能20个字符！">
                    </td>
                </tr>
              </table> 
              
        </div>
    </div>
    <div class="TFS_data_button">
        <a class=" btn_exit_long bnt_exit_padding12" href="javascript:void(0)" onclick="navObj.backMain()">关闭</a><a class="btn btnNext" href="javascript:void(0)" onclick="navObj.next()">保存并下一步</a>
    </div>
    <div style="height: 100px"></div>
    
<script type="text/javascript">

	var companyForm = null;
	
	function afterDoneCompany()
	{
		//初始化
	 companyForm = new validform('#company', {
			msgTooltip : true
		});
	}
	
	function vlidateCompany()
	{
		if(companyForm == null)
		{
			return true;
		}
		return companyForm.validate();
	}
	
	function companyRender(dataObj, contextPath) {

		$("#city1").citySelect({
			url : contextPath + '/js/city.min.js',
			prov : "请选择",
			city : "请选择",
			dist : "请选择",
			nodata : "none"
		});
		$("#city2").citySelect({
			url : contextPath + '/js/city.min.js',
			prov : "请选择",
			city : "请选择",
			dist : "请选择",
			nodata : "none"
		});
		var myDate = new Date();
		$("#dateSelector").DateSelector({
			ctlYearId : 'idYear',
			ctlMonthId : 'idMonth',
			ctlDayId : 'idDay',
			defYear : myDate.getFullYear(),
			defMonth : (myDate.getMonth() + 1),
			defDay : myDate.getDate(),
			minYear : 1800,
			maxYear : 2100
		});
		$("#dateSelector1").DateSelector({
			ctlYearId : 'idYear1',
			ctlMonthId : 'idMonth1',
			ctlDayId : 'idDay1',
			defYear : myDate.getFullYear(),
			defMonth : (myDate.getMonth() + 1),
			defDay : myDate.getDate(),
			minYear : 1800,
			maxYear : 2100
		});
		
		
		$("#certificateStartDate").DateSelector({
			ctlYearId : 'CertificateStartYear',
			ctlMonthId : 'CertificateStartMonth',
			ctlDayId : 'CertificateStartDay',
			defYear : myDate.getFullYear(),
			defMonth : (myDate.getMonth() + 1),
			defDay : myDate.getDate(),
			minYear : 1800,
			maxYear : 2100
		});
		$("#certificateExpireDate").DateSelector({
			ctlYearId : 'CertificateExpireYear',
			ctlMonthId : 'CertificateExpireMonth',
			ctlDayId : 'CertificateExpireDay',
			defYear : myDate.getFullYear(),
			defMonth : (myDate.getMonth() + 1),
			defDay : myDate.getDate(),
			minYear : 1800,
			maxYear : 2100
		});
	}
</script>
