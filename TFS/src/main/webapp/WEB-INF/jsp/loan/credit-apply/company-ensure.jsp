<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
    <div class="loanBox">
        <div class="loanStep">
            <span class="cur">1. 填写企业基本信息<i></i></span><span class="cur">2. 填写企业补充信息<i></i></span><span class="cur">3. 填写担保人信息<i></i></span><span>4. 上传资料<i></i></span><span>5. 提交授信申请</span>
        </div>
        <div class="loanInformation">
            <h3 class="loanInformation_title">担保人类型</h3>
            <table cellspacing="0" cellspacing="0" width="100%" class="loanInformation_tab">
                <colgroup>
                    <col width="160">
                    <col>
                </colgroup>
                <tr>
                    <td class="loanInformation_td01">
                        <i class="redNotice">*</i>担保人类型：
                    </td>
                    <td class="loanInformation_td02">
                        <label class="f_ui-radio-c3 mr70"><input field="assureType" name="assureType" type="radio" checked="" value="1"><i></i> &nbsp;自然人担保</label>
                        <label class="f_ui-radio-c3"><input name="assureType" type="radio" value="2" /><i></i> &nbsp;企业担保</label>
                    </td>                   
                </tr>                   
            </table>
            
            <div class="dbrInfirmation" fieldObj="loanPersonEnsure">
                <h3 class="loanInformation_title">担保人基本信息</h3>
                <table cellspacing="0" cellspacing="0" width="100%" class="loanInformation_tab">
                    <colgroup>
                        <col width="160">
                        <col width="370">
                        <col width="148">
                        <col width="200">
                        <col width="148">
                        <col>
                    </colgroup>              
                    <tr>
                        <td class="loanInformation_td01">
                            <i class="redNotice">*</i>姓名：
                        </td>
                        <td class="loanInformation_td02">
                            <input type="text" class="loanInformation_inp01" placeholder="请输入担保人姓名" field='personName'>
                        </td>
                        <td class="loanInformation_td01">
                             <i class="redNotice">*</i>身份证号：
                        </td>
                        <td class="loanInformation_td03">
                           <input type="text" class="loanInformation_inp01" placeholder="请输入担保人身份证号" field='nationalIdentityNumber'>
                        </td>
                         <td class="loanInformation_td01">
                             <i class="redNotice">*</i>手机号码：
                        </td>
                        <td class="loanInformation_td03">
                           <input type="text" class="loanInformation_inp01" placeholder="请输入担保人手机号码" field='mobilenNmber'>
                        </td>
                    </tr>  
                    <tr>
                        <td class="loanInformation_td01">
                            <i class="redNotice">*</i>婚姻状况：
                        </td>
                        <td class="loanInformation_td02">
                            <select class="select loanInformation_se02" field='marriageStatus'>
                                <option>请选择</option>
                                <option value="1">已婚</option>
                                <option value="2">未婚</option>
                            </select>
                        </td>
                        <td class="loanInformation_td01">
                             <i class="redNotice">*</i>有无子女：
                        </td>
                        <td class="loanInformation_td03">
                           <select class="select loanInformation_se02" field='haveChildren'>
                                <option>请选择</option>
                                <option value="1">有子女</option>
                                <option value="2">无子女</option>
                            </select>
                        </td>
                         <td class="loanInformation_td01">
                             <i class="redNotice">*</i>籍贯：
                        </td>
                        <td class="loanInformation_td03" id="city_1Ensure">
                           <select class="select loanInformation_se02 prov" field='nativePlace'>
                                <option>请选择</option>
                            </select>
                            <select class="city hidden"></select>
                        </td>                         
                    </tr>   
                    <tr>
                        <td class="loanInformation_td01">
                            <i class="redNotice">*</i>现居住地址：
                        </td>                  
                        <td class="loanInformation_td02" id="city1Ensure" colspan="5" fields='currentLiveaAdress' split="/">
                            <select class="prov select loanInformation_se01" item="1"></select> 
                            <select class="city select loanInformation_se01" disabled="disabled" item="2"></select>
                            <select class="dist select loanInformation_se01" disabled="disabled" item="3"></select>
                            <input type="text" class="loanInformation_inp03" placeholder="请输入详细地址" item="4">
                        </td>
                    </tr>  
                    <tr>
                        <td class="loanInformation_td01">
                            <i class="redNotice">*</i>毕业学校：
                        </td>
                        <td class="loanInformation_td02">
                            <input type="text" class="loanInformation_inp01" placeholder="请输入担保人毕业学校"  field='graduateSchool'>
                        </td>
                        <td class="loanInformation_td01">
                             <i class="redNotice">*</i>最高学历：
                        </td>
                        <td class="loanInformation_td03">
                           <select class="select loanInformation_se02"  field='highestEducation'>
                                <option>请选择</option>
                                <option value="1">小学</option>
                                <option value="2">初中</option>
                                <option value="3">高中</option>
                                <option value="4">中专</option>
                                <option value="5">大专</option>
                                <option value="6">本科</option>
                                <option value="7">硕士</option>
                                <option value="8">博士</option>                            
                            </select>
                        </td>
                         <td class="loanInformation_td01">
                             <i class="redNotice">*</i>工作年限：
                        </td>
                        <td class="loanInformation_td03">
                           <select class="select loanInformation_se02" field='yearsWorking'>
                                <option>请选择</option>
                                <option value="1">1 - 3年</option>
                                <option value="2">3 - 5年</option>
                                <option value="3">5 - 10年</option>
                                <option value="4">10年以上</option>
                            </select>
                        </td>                         
                    </tr>
                    <tr>
                        <td class="loanInformation_td01">
                            <i class="redNotice">*</i>工作单位：
                        </td>
                        <td class="loanInformation_td02">
                            <input type="text" class="loanInformation_inp01" placeholder="请输入担保人工作单位" field='workCompany'>
                        </td>
                        <td class="loanInformation_td01">
                             <i class="redNotice">*</i>现任职务：
                        </td>
                        <td class="loanInformation_td03">
                           <input type="text" class="loanInformation_inp01" placeholder="请输入担保人现任职务" field='occupation'>
                        </td>
                         <td class="loanInformation_td01">
                             <i class="redNotice">*</i>工作电话：
                        </td>
                        <td class="loanInformation_td03">
                           <input type="text" class="loanInformation_inp01" placeholder="请输入担保人工作电话" field='worktelePhone'>
                        </td>
                    </tr>  
                    <tr>
                        <td class="loanInformation_td01">
                            <i class="redNotice">*</i> 办公地址：
                        </td>                  
                        <td class="loanInformation_td02" id="city2Ensure" colspan="3"  fields='officeAddress' split="/">
                            <select class="prov select loanInformation_se01" item="1"></select> 
                            <select class="city select loanInformation_se01" disabled="disabled" item="2"></select>
                            <select class="dist select loanInformation_se01" disabled="disabled" item="3"></select>
                            <input type="text" class="loanInformation_inp01 loanInformation_inp07" placeholder="请输入详细地址" item="4">
                        </td>
                        <td class="loanInformation_td01">
                             <i class="redNotice">*</i>所处行业：
                        </td>
                        <td class="loanInformation_td03" >
                           <input type="text" class="loanInformation_inp01" placeholder="请输入担保人所处行业" field='industry'>
                        </td>
                    </tr>             
                </table>
                 <h3 class="loanInformation_title">资产情况</h3>
                <table cellspacing="0" cellspacing="0" width="100%" class="loanInformation_tab">
                    <colgroup>
                        <col width="160">
                        <col width="370">
                        <col width="148">
                        <col>
                    </colgroup>              
                    <tr>
                        <td class="loanInformation_td01">
                            <i class="redNotice">*</i>车产情况：
                        </td>
                        <td class="loanInformation_td02" colspan="5" style="padding: 0px;border-width: 1px 1px 0px 0px;">
                            <table cellspacing="0" cellspacing="0" width="100%" class="loanInformation_tab02">
                                <colgroup>
                                    <col width="369">
                                    <col width="148">
                                    <col width="200">
                                    <col width="148">
                                    <col>
                                </colgroup>  
                                <tr>
                                    <td class="loanInformation_td02">
                                        <select class="select loanInformation_se02" style="width: 319px;" id="carStatus" field="carPropertyType">
                                            <option>请选择</option>
                                            <option value="1">无车</option>
                                            <option value="2">有车无车贷</option>
                                            <option value="3">有车有车贷</option>
                                        </select>
                                    </td>
                                     <td class="loanInformation_td01 loanInformation_car">
                                        <i class="redNotice">*</i>购车年份：
                                    </td>
                                    <td class="loanInformation_td02 loanInformation_car" fields="carPurchaseDate" split="-">
                                       <span id="dateSelectorEnsure">
                                            <select id="idYearEnsure" name="idYearEnsure" class="select loanInformation_se01" data="" item="1"></select>
                                           <select class="select loanInformation_se01" id="idMonthEnsure" name="idMonthEnsure" data="12" item="2"></select>
                                        </span>
                                    </td>    
                                    <td class="loanInformation_td01 loanInformation_car">
                                        <i class="redNotice">*</i>汽车品牌：
                                    </td>
                                    <td class="loanInformation_td02 loanInformation_car">
                                        <input type="text" class="loanInformation_inp01" placeholder="请输入担保人汽车品牌" field="carBrand">
                                    </td>    
                                </tr>
                            </table>
                            
                        </td>    
                       

                    </tr>  
                    <tr>
                        <td class="loanInformation_td01">
                            <i class="redNotice">*</i>房产情况：
                        </td>
                        <td class="loanInformation_td02">
                            <select class="select loanInformation_se02" style="width: 319px;" field="housePropertyType">
                                <option>请选择</option>
                                <option value="1">无房</option>
                                <option value="2">有房无房贷</option>
                                <option value="3">有房有房贷</option>
                            </select>
                        </td>    
                        <td class="loanInformation_td01">
                            其他资产：
                        </td>
                        <td class="loanInformation_td02">
                            <input type="text" class="loanInformation_inp01" placeholder="" field="otherProperty">
                        </td>                  
                    </tr>  
                    <tr>
                        <td class="loanInformation_td01">
                            补充说明：
                        </td>                  
                        <td class="loanInformation_td02" colspan="3">
                           <input type="text" class="loanInformation_inp01" placeholder=""  field="propertyRemark">
                        </td>
                    </tr> 
                  </table>  
                    
                <h3 class="loanInformation_title">联系人信息</h3>
                <table cellspacing="0" cellspacing="0" width="100%" class="loanInformation_tab">
                    <colgroup>
                        <col width="160">
                        <col width="280">
                        <col width="160">
                        <col width="280">
                        <col width="160">
                        <col>
                  </colgroup>                                 
                   <tr>
                        <td class="loanInformation_td01">
                            <i class="redNotice">*</i>第一联系人姓名：
                        </td>
                        <td class="loanInformation_td02">
                             <input type="text" class="loanInformation_inp01" placeholder="请输入第一联系人姓名" field="firstContactName">
                        </td>    
                        <td class="loanInformation_td01">
                            <i class="redNotice">*</i>第一联系人电话：
                        </td>
                        <td class="loanInformation_td02">
                            <input type="text" class="loanInformation_inp01" placeholder="请输入第一联系人电话" field="firstContactPhone">
                        </td>  
                        <td class="loanInformation_td01">
                            <i class="redNotice">*</i>与担保人关系：
                        </td>
                        <td class="loanInformation_td03">
                            <select class="select loanInformation_se02" field="relationToguarantee1">
                                <option>请选择</option>
                                <option value="1">父母</option>
                                <option value="2">子女</option>
                                <option value="3">配偶</option>
                                <option value="4">朋友</option>
                                <option value="5">同事</option>
                                <option value="6">其他</option>
                            </select>
                        </td>                  
                    </tr>  
                    <tr>
                        <td class="loanInformation_td01">
                            <i class="redNotice">*</i>第二联系人姓名：
                        </td>
                        <td class="loanInformation_td02">
                             <input type="text" class="loanInformation_inp01" placeholder="请输入第二联系人姓名" field="secondContactName">
                        </td>    
                        <td class="loanInformation_td01">
                            <i class="redNotice">*</i>第二联系人电话：
                        </td>
                        <td class="loanInformation_td02">
                            <input type="text" class="loanInformation_inp01" placeholder="请输入第二联系人电话" field="secondContactPhone">
                        </td>  
                        <td class="loanInformation_td01">
                            <i class="redNotice">*</i>与担保人关系：
                        </td>
                        <td class="loanInformation_td03">
                            <select class="select loanInformation_se02" field="relationToguarantee2">
                                <option>请选择</option>
                                 <option value="1">父母</option>
                                <option value="2">子女</option>
                                <option value="3">配偶</option>
                                <option value="4">朋友</option>
                                <option value="5">同事</option>
                                <option value="6">其他</option>
                            </select>
                        </td>                  
                    </tr>  
                  </table>    
            </div>
            
            <div class="dbrInfirmation" fieldObj="companyEnsure">
                <h3 class="loanInformation_title">企业信息</h3>
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
                            <input type="text" class="loanInformation_inp01" placeholder="请输入企业名称" field="companyName">
                        </td>
                        <td class="loanInformation_td01">
                             <i class="redNotice">*</i>企业成立日期：
                        </td>
                        <td class="loanInformation_td02" id="dateSelector1Ensure" fields="foundDate" split="-">
                           <select id="idYear1Ensure" name="idYear1Ensure" class="select loanInformation_se01" data="" item="1"></select>
                           <select class="select loanInformation_se01" id="idMonth1Ensure" name="idMonth1Ensure" data="12" item="2"></select>
                           <select id="idDay1Ensure" name="idDay1Ensure" class="select loanInformation_se01" data="1" item="3"></select>
                        </td>                    
                    </tr>  
                    <tr>
                        <td class="loanInformation_td01">
                            <i class="redNotice">*</i>企业规模：
                        </td>
                        <td class="loanInformation_td03">
                            <select class="select loanInformation_se02" field="enterpriseScale">
                                <option>请选择</option>
                                <option value="1">1 - 50人</option>
                                <option value="2">50 - 100人</option>
                                <option value="3">100 - 500人</option>
                                <option value="4">500 - 1000人</option>
                                <option value="5">1000人以上</option>
                            </select>
                        </td>
                        <td class="loanInformation_td01">
                             <i class="redNotice">*</i>营业执照号/社会信用代码：
                        </td>
                        <td class="loanInformation_td02">
                           <input type="text" class="loanInformation_inp01" placeholder="请输入营业执照号/社会信用代码" field="businessLicense">
                        </td>                    
                    </tr> 
                    <tr>
                        <td class="loanInformation_td01">
                            <i class="redNotice">*</i>税务登记号：
                        </td>
                        <td class="loanInformation_td03">
                           <input type="text" class="loanInformation_inp01" placeholder="请输入税务登记号" field="taxRegisterCode">
                        </td>
                        <td class="loanInformation_td01">
                             <i class="redNotice">*</i>组织机构代码：
                        </td>
                        <td class="loanInformation_td02">
                           <input type="text" class="loanInformation_inp01" placeholder="请输入组织机构代码" field="orgCodeCertificate">
                        </td>                    
                    </tr> 
                    <tr>
                        <td class="loanInformation_td01">
                            <i class="redNotice">*</i>平台注册账号：
                        </td>
                        <td class="loanInformation_td03">
                           <input type="text" class="loanInformation_inp01" placeholder="请输入平台注册账号" field="registerAccount">
                        </td>
                        <td class="loanInformation_td01">
                             <i class="redNotice">*</i>平台注册日期：
                        </td>
                         <td class="loanInformation_td02" id="dateSelector2Ensure" fields="registerDate" split="-">
                           <select id="idYear2Ensure" name="idYear2Ensure" class="select loanInformation_se01" data="" item="1"></select>
                           <select class="select loanInformation_se01" id="idMonth2Ensure" name="idMonth2Ensure" data="12" item="2"></select>
                           <select id="idDay2Ensure" name="idDay2Ensure" class="select loanInformation_se01" data="1" item="3"></select>
                        </td>                      
                    </tr> 
                    <tr>
                        <td class="loanInformation_td01">
                            <i class="redNotice">*</i>企业注册地址：
                        </td>                  
                        <td class="loanInformation_td02" id="city3Ensure" colspan="3" fields="officeAddress" split="/">
                            <select class="prov select loanInformation_se01" item="1"></select> 
                            <select class="city select loanInformation_se01" disabled="disabled" item="2"></select>
                            <select class="dist select loanInformation_se01" disabled="disabled" item="3"></select>
                            <input type="text" class="loanInformation_inp03" placeholder="请输入详细地址" item="4">
                        </td>
                    </tr>
                    <tr>
                        <td class="loanInformation_td01">
                            <i class="redNotice">*</i>企业办公地址：
                        </td>                  
                        <td class="loanInformation_td02" id="city4Ensure" colspan="3" fields="regAddress" split="/">
                            <select class="prov select loanInformation_se01" item="1"></select> 
                            <select class="city select loanInformation_se01" disabled="disabled" item="2"></select>
                            <select class="dist select loanInformation_se01" disabled="disabled" item="3"></select>
                            <input type="text" class="loanInformation_inp03" placeholder="请输入详细地址" item="4">
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
                        <input type="text" class="loanInformation_inp01" placeholder="请输入法人姓名"  field="legalPersonName">
                    </td>
                    <td class="loanInformation_td01">
                         <i class="redNotice">*</i>法人证件号：
                    </td>
                    <td class="loanInformation_td02">
                       <select class="select loanInformation_se01 " field="legalPersonCertificateType">
                           <option value="1">身份证</option>
                           <option value="2">护照</option>
                       </select>
                       <input type="text" class="loanInformation_inp04" placeholder="请输入证件号码" field="legalPersonCertificateNumber">
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
                        <input type="text" class="loanInformation_inp01" placeholder="请输入联系人姓名"  field="contactName">
                    </td>
                    <td class="loanInformation_td01">
                         <i class="redNotice">*</i>联系电话：
                    </td>
                    <td class="loanInformation_td02">
                       <input type="text" class="loanInformation_inp01" placeholder="请输入联系电话"  field="contactPhone">
                    </td>
                </tr>
              </table>  
            </div>

        </div>
    </div>
    <div class="TFS_data_button">
        <a class=" btn_exit_long bnt_exit_padding12" href="javascript:void(0)" onclick="navObj.prev()">上一步</a>
        <a class="btn btnNext" href="javascript:void(0)" onclick="navObj.next()">保存并下一步</a>
    </div>
    <div style="height: 100px"></div>
<script type="text/javascript">

	
    function companyEnsureRender(dataObj ,  contextPath){
        $(".dbrInfirmation").eq($('input[name="assureType"]:checked').val()-1).show();
        $("input[name='assureType']").change(function(){
            $(".dbrInfirmation").hide().eq($('input[name="assureType"]:checked').val()-1).show();
        })
        
        $("#carStatus").change(function(){
            console.log($(this).find("option:selected").html())
            if($(this).find("option:selected").html()=="有车无车贷"||$(this).find("option:selected").html()=="有车有车贷"){
                $(this).parent().removeAttr("colspan");
                $(this).parents("tr").find(".loanInformation_car").show();
            }else{
                $(this).parent().attr("colspan","5");
                $(this).parents("tr").find(".loanInformation_car").hide();
            }
        })

        $("#city_1Ensure").citySelect({
        	url : contextPath+'/js/city.min.js',
            prov:"请选择",
            nodata:"none"
        }); 
        $("#city1Ensure").citySelect({
        	url : contextPath+'/js/city.min.js',
            prov:"请选择", 
            city:"请选择",
            dist:"请选择",
            nodata:"none"
        }); 
        $("#city2Ensure").citySelect({
        	url : contextPath+'/js/city.min.js',
            prov:"请选择", 
            city:"请选择",
            dist:"请选择",
            nodata:"none"
        }); 
         $("#city3Ensure").citySelect({
        	 url : contextPath+'/js/city.min.js',
            prov:"请选择", 
            city:"请选择",
            dist:"请选择",
            nodata:"none"
        }); 
        $("#city4Ensure").citySelect({
        	url : contextPath+'/js/city.min.js',
            prov:"请选择", 
            city:"请选择",
            dist:"请选择",
            nodata:"none"
        }); 
         var myDate = new Date();
        $("#dateSelectorEnsure").DateSelector({
                ctlYearId: 'idYearEnsure',
                ctlMonthId: 'idMonthEnsure',
                ctlDayId: '',
                defYear: myDate.getFullYear(),
                defMonth: (myDate.getMonth()+1),
                defDay: myDate.getDate(),
                minYear: 1800,
                maxYear: 2100
        });
        $("#dateSelector1Ensure").DateSelector({
                ctlYearId: 'idYear1Ensure',
                ctlMonthId: 'idMonth1Ensure',
                ctlDayId: 'idDay1Ensure',
                defYear: myDate.getFullYear(),
                defMonth: (myDate.getMonth()+1),
                defDay: myDate.getDate(),
                minYear: 1800,
                maxYear: 2100
        });
        $("#dateSelector2Ensure").DateSelector({
                ctlYearId: 'idYear2Ensure',
                ctlMonthId: 'idMonth2Ensure',
                ctlDayId: 'idDay2Ensure',
                defYear: myDate.getFullYear(),
                defMonth: (myDate.getMonth()+1),
                defDay: myDate.getDate(),
                minYear: 1800,
                maxYear: 2100
        });
    }
</script>