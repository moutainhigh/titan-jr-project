<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

    <div class="loanBox">
        <div class="loanStep">
            <span class="cur">1. 填写企业基本信息<i></i></span><span class="cur">2. 填写企业补充信息<i></i></span><span>3. 填写担保人信息<i></i></span><span>4. 上传资料<i></i></span><span>5. 提交授信申请</span>
        </div>
        <div class="loanInformation">
            <h3 class="loanInformation_title">股东信息</h3>
            <div class="loanInformation_tabBox" id="controllDatas">
                <table  cellspacing="0" cellspacing="0" width="100%" class="loanInformation_tab">
                    <colgroup>
                        <col width="160">
                        <col width="370">
                        <col width="148">
                        <col width="200">
                        <col width="148">
                        <col>
                    </colgroup>
                    <tr fieldObj='controllDatas' >
                        <td class="loanInformation_td01"  >
                            <i class="redNotice">*</i>股东名称：
                        </td>
                        <td class="loanInformation_td02">
                            <input type="text" class="loanInformation_inp01" placeholder="请输入股东名称" field='shareholderName' datatype="s1-10" errormsg="必填项，并且最长只能10个字符！">
                        </td>
                        <td class="loanInformation_td01">
                             <i class="redNotice">*</i>出资金额：
                        </td>
                        <td class="loanInformation_td03">
                           <input type="text" class="loanInformation_inp02" placeholder=""  field='contributionAmount' datatype="/^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/" errormsg="必填项，并且最多只能保留2位小数！"> 万元
                        </td>
                        <td class="loanInformation_td01">
                             <i class="redNotice">*</i>出资金额：
                        </td>
                        <td class="loanInformation_td03">
                           <input type="text" class="loanInformation_inp02" placeholder="" field='equityRatio' datatype="/^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/" errormsg="必填项，并且最多只能保留2位小数！"> %
                           <span class="J_deleteBtnTFS blue">删除</span>
                        </td>
                    </tr>                            
                </table>
            </div>
            <a href="javascript:void(0)" class="blue J_addBtnTFS" id="addControllDatas">添加股东信息</a>
            
            <h3 class="loanInformation_title">主营业务信息</h3>
            <div class="loanInformation_tabBox" id="mainBusinessDatas">
            <table  cellspacing="0" cellspacing="0" width="100%" class="loanInformation_tab">
                <colgroup>
                    <col width="160">
                    <col width="370">
                    <col width="148">
                    <col width="200">
                    <col width="148">
                    <col>
                </colgroup>              
                <tr fieldObj="mainBusinessDatas">
                    <td class="loanInformation_td01">
                        <i class="redNotice">*</i>主要产品/服务：
                    </td>
                    <td class="loanInformation_td02">
                        <input type="text" class="loanInformation_inp01" placeholder="请输入主要产品/服务" field="mainProductsOrService" datatype="*1-50" errormsg="必填项，并且最长只能50个字符！">
                    </td>
                    <td class="loanInformation_td01">
                         <i class="redNotice">*</i>年销售规模：
                    </td>
                    <td class="loanInformation_td03">
                       <input type="text" class="loanInformation_inp02" placeholder="" field="mainAnnualSale" datatype="/^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/" errormsg="必填项，并且最多只能保留2位小数！"> 万元
                    </td>
                    <td class="loanInformation_td01">
                         <i class="redNotice">*</i>占总销售额比例：
                    </td>
                    <td class="loanInformation_td03">
                       <input type="text" class="loanInformation_inp02" placeholder="" field="mainSaleProportion" datatype="/^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/" errormsg="必填项，并且最多只能保留2位小数！"> %
                       <span class="J_deleteBtnTFS blue">删除</span>
                    </td>
                </tr>            
            </table>
            </div>
            <a href="javascript:void(0)" class="blue J_addBtnTFS" id="addMainBusinessDatas">添加主营业务</a>
            <h3 class="loanInformation_title">合作企业信息</h3>
            <div class="loanInformation_tabBox" id="cooperationCompanyInfos">
            
            <table cellspacing="0" cellspacing="0" width="100%" class="loanInformation_tab loanInformation_tab1" >
                <colgroup>
                    <col width="160">
                    <col width="370">
                    <col width="148">
                    <col width="200">
                    <col width="148">
                    <col>
                </colgroup>
                <tbody fieldObj="cooperationCompanyInfos">        
                <tr>
                    <td class="loanInformation_td01">
                        <i class="redNotice">*</i>合作企业名称：
                    </td>
                    <td class="loanInformation_td02">
                        <input type="text" class="loanInformation_inp01" placeholder="请输入合作企业名称" field='cooperationName' datatype="*1-50" errormsg="必填项，并且最长只能50个字符！">
                    </td>
                    <td class="loanInformation_td01">
                         <i class="redNotice">*</i>年销售规模：
                    </td>
                    <td class="loanInformation_td03">
                       <input type="text" class="loanInformation_inp02" placeholder="" field='yearAnnualSale' datatype="/^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/" errormsg="必填项，并且最多只能保留2位小数！"> 万元
                    </td>
                    <td class="loanInformation_td01">
                         <i class="redNotice">*</i>占总销售额比例：
                    </td>
                    <td class="loanInformation_td03">
                       <input type="text" class="loanInformation_inp02" placeholder="" field='saleProportion' datatype="/^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/" errormsg="必填项，并且最多只能保留2位小数！"> %
                    </td>
                </tr>     
                <tr>
                    <td class="loanInformation_td01">
                        <i class="redNotice">*</i>结算方式：
                    </td>
                    <td class="loanInformation_td02">
                        <input type="text" class="loanInformation_inp01" placeholder="请输入结算方式" field='settlement' datatype="s1-10" errormsg="必填项，并且最长只能10个字符！">
                    </td>
                    <td class="loanInformation_td01">
                         <i class="redNotice">*</i>年销售规模：
                    </td>
                    <td class="loanInformation_td03">
                       <input type="text" class="loanInformation_inp02" placeholder="" field='cooperationYears' datatype="s1-10" errormsg="必填项，并且最长只能10个字符！"> 年&nbsp;&nbsp;&nbsp;
                    </td>
                    <td class="loanInformation_td01">
                         <i class="redNotice">*</i>合作关系：
                    </td>
                    <td class="loanInformation_td03">
                       <select class="select loanInformation_se02" field='cooperation'  customFun="validateSelect" errormsg="请选择合作关系!" style="width: 143px;">
                            <option>请选择</option>
                            <option value="1">供应商</option>
                            <option value="2">分销商</option>
                        </select>
                       <span class="J_deleteBtnTFS1 J_deleteBtnTFS blue">删除</span>
                    </td>
                </tr> 
                </tbody>    
            </table>
            </div>
            <a href="javascript:void(0)" class="blue J_addBtnTFS" id="addCooperationCompanyInfos">添加合作企业</a>
            <h3 class="loanInformation_title">经营场所信息</h3>
            <table cellspacing="0" cellspacing="0" width="100%" class="loanInformation_tab" fieldObj='companyLease'>
                <colgroup>
                    <col width="160">
                    <col width="500">
                    <col width="148">
                    <col>
                </colgroup>              
                <tr>
                    <td class="loanInformation_td01">
                        <i class="redNotice">*</i>经营场所：
                    </td>
                    <td class="loanInformation_td02" style="border-right: 0px;">
                      <label class="f_ui-radio-c3 mr70"><input field="leaseType" name="leaseType" type="radio" value="1"><i></i> &nbsp;自有产权</label>
                      <label class="f_ui-radio-c3"><input name="leaseType" type="radio" checked="" value="2" /><i></i> &nbsp;租赁房屋</label>                    
                    </td>     
                    <td style="border-width:1px 0px 0px;"></td>      
                    <td style="border-left: 0px;"></td>           
                </tr>    
                <tr class="dnTypeTms">
                    <td class="loanInformation_td01">
                        <i class="redNotice">*</i>租赁期限：
                    </td>
                    <td class="loanInformation_td02">
                        <span id="dateSelectorAppend" fields='beginLeaseDate' split='-'>
                           <div customFun="validateSelects" errormsg="请选择租赁开始日期"  style="display:inline-block;">
	                           <select id="idYearAppend" name="idYearAppend" class="select loanInformation_se01" data="" item="year" onblur="$(this).parent().blur();"></select>
	                           <select class="select loanInformation_se01" id="idMonthAppend" name="idMonthAppend" data="12" item="month" onblur="$(this).parent().blur();"></select>
                           </div>
                        </span>
                         <span class="loanInformation_td02_ico">——</span>
                         <span id="dateSelector1Append"  fields='endLeaseDate' split='-'>
                         	<div customFun="validateSelects" errormsg="请选择租赁结束日期"  style="display:inline-block;">
	                            <select id="idYear1Append" name="idYear1Append" class="select loanInformation_se01" data="" item='year' onblur="$(this).parent().blur();"></select>
	                            <select class="select loanInformation_se01" id="idMonth1Append" name="idMonth1Append" data="12" item='month' onblur="$(this).parent().blur();"></select>
                            </div>
                        </span>
                    </td>   
                    <td class="loanInformation_td01">
                        <i class="redNotice">*</i>建筑面积：
                    </td>
                    <td class="loanInformation_td03" colspan="3">
                        <input type="text" class="loanInformation_inp02 loanInformation_inp05" placeholder="" field='housingArea' customFun="validateTextValue" customArgs="/^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/" errormsg="必填项，并且最多只能保留2位小数！"> 平方米
                    </td>                 
                </tr>
                <tr class="dnTypeTms">
                    <td class="loanInformation_td01">
                        <i class="redNotice">*</i>租金：
                    </td>
                    <td class="loanInformation_td03">
                       <input type="text" class="loanInformation_inp02 loanInformation_inp06" placeholder="" field='rental' customFun="validateTextValue" customArgs="/^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/" errormsg="必填项，并且最多只能保留2位小数！"> 万/年
                    </td>   
                    <td class="loanInformation_td01">
                        <i class="redNotice">*</i>支付方式：
                    </td>
                    <td class="loanInformation_td03" colspan="3">
                        <input type="text" style="width: 348px;" class="loanInformation_inp01" placeholder="请输入支付方式"  field='paymentMethod'  customFun="validateTextValue" errormsg="必填项，并且最长只能10个字符！"> 
                    </td>                 
                </tr>
                <tr class="dnTypeTms">
                    <td class="loanInformation_td01">
                        <i class="redNotice">*</i>租赁地址：
                    </td>                  
                    <td class="loanInformation_td02" id="cityAppend" colspan="3" fields='leaseAddress' split="/">
                    	<div customFun="validateSelects" errormsg="请选择租赁地址!"  style="display:inline-block;">
	                        <select class="prov select loanInformation_se01" item='1' onblur="$(this).parent().blur();"></select> 
	                        <select class="city select loanInformation_se01" disabled="disabled" item='2' onblur="$(this).parent().blur();"></select>
	                        <select class="dist select loanInformation_se01" disabled="disabled" item='3' onblur="$(this).parent().blur();"></select>
	                        <input type="text" class="loanInformation_inp03" placeholder="请输入详细地址" item='4' onblur="$(this).parent().blur();">
                        </div>
                    </td>
                </tr>  
                 <tr class="dnTypeTms">
                    <td class="loanInformation_td01"> 备注
                    </td>                  
                    <td class="loanInformation_td02" colspan="3">
                       <input type="text" class="loanInformation_inp01" placeholder="请输入备注内容" field='remark' customFun="validateTextValue" errormsg="必填项，并且最长只能100个字符！">
                    </td>
                </tr>         
            </table>
        </div>
    </div>
    <div class="TFS_data_button">
        <a class=" btn_exit_long bnt_exit_padding12"  href="javascript:void(0)" onclick="navObj.prev()">上一步</a>
        <a class="btn btnNext"  href="javascript:void(0)" onclick="navObj.next()">保存并下一步</a>
    </div>
    <div style="height: 100px"></div>
<script type="text/javascript">
	
	var companyAppendValidate = null;
	
	
	
	function afterDoneCompanyAppend()
	{	
		//初始化
		companyAppendValidate = new validform('#companyAppend', {
			msgTooltip : true
		});
	
	}

	function vlidateCompanyAppend()
	{
		if(companyAppendValidate == null)
		{
			return true;
		}
		
		return companyAppendValidate.validate();
	}
	
   function companyAppendRender(dataObj ,  contextPath){
	   
	   var appendHtmlObj ={"controllDatas" :$('#controllDatas').html() , "mainBusinessDatas" : $('#mainBusinessDatas').html() , "addCooperationCompanyInfos": $('#addCooperationCompanyInfos').html() };k
	   
        if($('input[name="leaseType"]:checked').val()=="2"){
            $(".dnTypeTms").show();
        }else{
            $(".dnTypeTms").hide();
        }
        $("input[name='leaseType']").change(function(){
            if($('input[name="leaseType"]:checked').val()=="2"){
                $(".dnTypeTms").show();
            }else{
                $(".dnTypeTms").hide();
            }
        })
         $(".loanInformation_tabBox").each(function(){
            if($(this).find(".loanInformation_tab").length==1){
                $(this).find(".J_deleteBtnTFS").hide();
            }
        });
        $(".J_addBtnTFS").live('click',function(){
    //        var _tr=$('<table cellspacing="0" width="100%" class="loanInformation_tab" ></table>');
            var _thisPre=$(this).prev(".loanInformation_tabBox");
            
            _thisPre.append( appendHtmlObj[ _thisPre.attr('id')]);
            
        //    var _trn=_thisPre.find("table").eq(0).html();
           // _tr.html(_trn);
          //  
            _thisPre.find(".J_deleteBtnTFS").show();
            afterDoneCompanyAppend();
        });
      
        $(".J_deleteBtnTFS").live('click',function(){            
            if($(this).parents(".loanInformation_tabBox").find(".loanInformation_tab").length==2){
                $(this).parents(".loanInformation_tabBox").find(".J_deleteBtnTFS").hide();
            }
            $(this).parents(".loanInformation_tab").remove();
            afterDoneCompanyAppend();
        });
	
        
        $("#cityAppend").citySelect({
        	url : contextPath+'/js/city.min.js',
            prov:"请选择", 
            city:"请选择",
            dist:"请选择",
            nodata:"none"
        }); 
        
        if(dataObj)
        {
        	var dataObj = $.parseJSON(dataObj);
        	var controllDatas= dataObj['controllDatas'];
        	if(controllDatas)
        	{
	        	for(var i=1;i<controllDatas.length;i++)
	        	{
					$("#addControllDatas").click();
	        	}
        	}
        	
        	var mainBusinessDatas= dataObj['mainBusinessDatas'];
        	if(mainBusinessDatas)
        	{
	        	for(var i=1;i<mainBusinessDatas.length;i++)
	        	{
					$("#addMainBusinessDatas").click();
	        	}
        	}
        	
        	var cooperationCompanyInfos= dataObj['cooperationCompanyInfos'];
        	
        	if(cooperationCompanyInfos)
        	{
	        	for(var i=1;i<cooperationCompanyInfos.length;i++)
	        	{
					$("#addCooperationCompanyInfos").click();
	        	}
        	}
        }
         

         var myDate = new Date();
        $("#dateSelectorAppend").DateSelector({
                ctlYearId: 'idYearAppend',
                ctlMonthId: 'idMonthAppend',
                ctlDayId: '',
                defYear: myDate.getFullYear(),
                defMonth: (myDate.getMonth()+1),
                defDay: myDate.getDate(),
                minYear: 1800,
                maxYear: 2100
        });
        $("#dateSelector1Append").DateSelector({
                ctlYearId: 'idYear1Append',
                ctlMonthId: 'idMonth1Append',
                ctlDayId: '',
                defYear: myDate.getFullYear(),
                defMonth: (myDate.getMonth()+1),
                defDay: myDate.getDate(),
                minYear: 1800,
                maxYear: 2100
        });
    }
</script>