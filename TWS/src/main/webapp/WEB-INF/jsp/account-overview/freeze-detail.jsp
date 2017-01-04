<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/comm/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
    <jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
    <jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
<body style="min-width: 1300px;" class="bg" >
<!-- 头部 -->
<div class="header">
	<div class="w_1200">
		<div class="logo">
			<div class="l_img"><img src="<%=cssWalletPath%>/images/logo.png"></div>
			<div class="l_text">
				<i class="ico "></i>冻结金额详情
			</div>
		</div>
	</div>
</div>
<div class="h_90"></div>
<!-- 内容 -->
<div class="w_1200 ">
	<div class="overview clearfix m_t20 ">
		<div class="MyAssets_top_tip"><i class="ico_frozen fl ico"></i><span class="fl">当联盟分销商付款成功后，供应商未确认订单前资金会冻结，确认后即可解冻</span>
			<i class="tourism_ico fr J_delete ">×</i>	
		</div>
		<div class="o_freeze">
			<div class="main_con p_b8" >
				<div class="MyAssets_list_Options" style="padding-left:0px;">
					<div class="J_Section" id="Options">
						<dl class="J_date_list fl m_r15">
							<dd>
								<div class="MyAssets_list_inp01 fl "><i>起始日期：</i></div>
								<input type="text"  id="date_bach_start" name="date_bach_start" class="text w_160 text_calendar fl">					
								<label for="" class="S_digit fl">至</label>
								<input type="text" id="date_bach_end" name="date_bach_end" class="text text1 w_160 text_calendar fl" >
								<div class="MyAssets_list_inp01 fl "><i>截止日期：</i></div>	
							</dd>
						</dl>
					</div>
					<div class="MyAssets_list_inp01 fl ml10">
						<input type="text" value="" id="userTradeNo" placeholder="交易单号："></div>
					<div class="MyAssets_list_inp01 fl ml10">
						<input type="text" value="" id="partner" placeholder="付款方："></div>
					<a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)" onclick="queryFreezeOrders()">&nbsp;</a>
					<a href="javascript:void(0)" class="MyAssets_Export fr bacth_export_hotel J_export">导出记录</a>
				</div>
				<div class="label" style="margin-right:17px;">
					<table border="0" cellspacing="0" width="100%" >
					<colgroup>
						<col width="10">
						<col width="200">
						<col width="200">
						<col width="340">
						<col width="210">
						<col width="110">
						<col width="">
					</colgroup>
					<tr>
						<td></td>
						<td class="tdl" >交易时间</td>
						<td class="tdl" >交易单号</td>
						<td class="tdl" >交易内容</td>
						<td class="tdl" >付款方</td>
						<td class="tdr" >交易金额</td>
						<td></td>
					</tr>
				</table>
			</div>
		</div>
		
		<div class="main_con " >
			<div class="MyAssets_frozen ">
				<table border="0" cellspacing="0" width="100%">
					<colgroup>
						<col width="10">
						<col width="200">
						<col width="200">
						<col width="340">
						<col width="210">
						<col width="110">
						<col width="">
					</colgroup>
					<tbody id="id_1"></tbody>
				</table>
			</div>
		</div>

		<div class="main_kkpager">		
			<div id="kkpager" class="page_turning"></div>
		</div>
	</div>
	</div>
</div>
<div class="h_40"></div>
<!-- 版权 -->
<div class="footer1">
	<div class="f_bd">
		<div class="fl">
			Copyright © 2012-2016, fangcang.com. All Rights Reserved 泰坦云 版权所有 粤ICP备13046150号	
		</div>
		<div class="fl f_bd_r">
		<script charset="utf-8" type="text/javascript" src="http://szcert.ebs.org.cn/govicon.js?id=78ccac39-a97a-452c-9f81-162cd840cff6&amp;width=130&amp;height=50&amp;type=2" id="ebsgovicon"></script>
		</div>
	</div>	
</div>
<script type="text/javascript"> 
$(function () {
    initRequest(); 
    initAutoSelectPartner()
});

function initRequest() {
    var size =10;
    F.loading.show();
    $.ajax({
        dataType : 'html',
        context: document.body,
        data:{
            currentPage:1,
            pageSize:size,
            tradeTypeId:"2",
            isEscrowedPayment:"0"
        },
        url : '<%=basePath%>/account/query-freeze-list.shtml',
        success : function(html){
            $("#id_1").empty();
            $("#id_1").html(html);
            var total = $("#id_1 #tradePageTotal").val();
            var page = $("#id_1 #tradePageCurrent").val();
            pageGo(page,total,size);
            F.loading.hide();
        }
    });
}


var partner_dataSource=null;
//初始化联想查询框
function initAutoSelectPartner() {
	$.ajax({
		dataType : 'json',		      
        url : '<%=basePath%>/account/getOrgList.shtml?date=' + new Date().getTime(),
        success:function(data){
        	
        	if(data && data.organDTOList)
        	{
        		partner_dataSource = data;
        		var dataArr = new Array();
        		for(i = 0 ; i <data.organDTOList.length;i++)
        		{
        			dataArr[dataArr.length] = {key:data.organDTOList[i].userId , val:data.organDTOList[i].orgName};
        		}
        		
        		new AutoComplete($('#partner'), {
        			data : dataArr,
					key : 'userId',  //数据源中，做为key的字段
					val : 'orgName', //数据源中，做为val的字段
					width : 240,
					height : 300,
					autoSelectVal : true,
					clickEvent : function(d, input){
						input.attr('data-id', d.key);
					}
				});
        	}
        	
    		
        }
	});
}

 //点击删除提示
$('.J_delete').on('click', function (event) {
    $('.MyAssets_top_tip').hide();
    $('.p_t175').css('padding-top', '130px');
    
    $.cookie('freezeTip' , 'hide' , { expires: 30 });
    
});

//导出提示
//导出提示
 $(".J_export").on('click',function() {
     var startDate = $("#date_bach_start").attr('data-prev');
     var endDate = $("#date_bach_end").attr('data-prev');
     var tradeNo = $("#userTradeNo").val();
     var partnerName = $("#partner").val();
     var partner = null;
     if($.trim(partnerName).length>0){
     	if(validate_partner(partnerName)==true){
	partner = $("#partner").attr('data-id');
}else{//随便输入的账户，
	partner="lksdjlsk12";
}
     }
     var paraList ="?tradeTypeId=" + (2);
     if (partner){
         paraList = paraList + "&admissionName=" + partner;
     }
     if (startDate){
         paraList = paraList + "&startTimeStr=" + startDate;
     }
     if (endDate){
         paraList = paraList + "&endTimeStr=" + endDate;
     }
     if (tradeNo){
         paraList = paraList + "&userOrderId=" + tradeNo;
     }
     paraList = paraList + "&isEscrowedPayment=0";
     location.href = "<%=basePath%>/account/exportExcel.shtml" + paraList;
});
 

//添加日期锻
//添加日期锻
$('.J_Section').each(function(){
	//添加日期锻
	new DateSection('#' + $(this).attr('id'), {minDate : '2016-01-01',maxDate : ''});
});


//分页内页面跳转方法
function pageGo(pageIdx, totals, size){
    pageIdx = pageIdx || 1;
    var totalPage = 0;
    if (totals % size == 0){ //取模若为0标示可以除尽
        totalPage = totals / size;
    } else {
        totalPage = totals / size + 1;
    }
    var totalRecords = totals;
    var pageNo = pageIdx;
    //生成分页
    //有些参数是可选的，比如lang，若不传有默认值
    new Pager({
        pno : pageNo,
        //总页码
        total : totalPage,
        //总数据条数
        totalRecords : totalRecords,
        isShowTotalRecords :true,
        isGoPage : true,
        mode : 'click',//默认值是link，可选link或者click
        click : function(n){
            this.selectPage(n);
            pageNoChangeRequest(n,size);
            return false;
        }
    });
}

//点击分页页码时调用
function pageNoChangeRequest(page, size) {
    var startDate = $("#date_bach_start").attr('data-prev');
    var endDate = $("#date_bach_end").attr('data-prev');
    var tradeNo = $("#userTradeNo").val();
    var partnerName = $("#partner").val();
    var partner = null;
    if($.trim(partnerName).length>0){
    	if(validate_partner(partnerName)==true){
			partner = $("#partner").attr('data-id');
		}else{//随便输入的账户，
			partner="lksdjlsk12";
		}
    }
    F.loading.show();
    $.ajax({
        dataType : 'html',
        context: document.body,
        data:{currentPage:page,
            pageSize:size,
            tradeTypeId:"2",
            isEscrowedPayment:"0",
            admissionName: partner,
            startTimeStr: startDate,
            endTimeStr: endDate,
            userOrderId:tradeNo,
        },
        url : '<%=basePath%>/account/query-freeze-list.shtml',
        success : function(html){
            $("#id_1").empty();
            $("#id_1").html(html);
            F.loading.hide();
        }
    });
}

function validate_partner(partnerName){
	for(var i = 0 ; i <partner_dataSource.organDTOList.length;i++)
	{
		
		if(partnerName == partner_dataSource.organDTOList[i].orgName){
			
			return true;
		}
	}
	return false;
}

(function() {
	var status = $.cookie('freezeTip');
	if (status && status == 'hide') {
		$(".MyAssets_top_tip").hide();
	} else {
		$(".MyAssets_top_tip").show();
	}
}());

function queryFreezeOrders() {
    var size = 10;
    var startDate = $("#date_bach_start").attr('data-prev');
    var endDate = $("#date_bach_end").attr('data-prev');
    var tradeNo = $("#userTradeNo").val();
    var partnerName = $("#partner").val();
    var partner = null;
    if($.trim(partnerName).length>0){
    	if(validate_partner(partnerName)==true){
			partner = $("#partner").attr('data-id');
		}else{//随便输入的账户，
			partner="lksdjlsk12";
		}
    }
    
    F.loading.show();
    $.ajax({
        dataType : 'html',
        context: document.body,
        data:{currentPage:1,
            pageSize:size,
            tradeTypeId:"2",
            isEscrowedPayment:"0",
            admissionName: partner,
            startTimeStr: startDate,
            endTimeStr: endDate,
            userOrderId:tradeNo,
        },
        url : '<%=basePath%>/account/query-freeze-list.shtml',
        success : function(html){
            $("#id_1").empty();
            $("#id_1").html(html);
            var total = $("#id_1 #tradePageTotal").val();
            var page = $("#id_1 #tradePageCurrent").val();
            pageGo(page,total,size);
            F.loading.hide();
        }
    });
}

</script>
</body>
</html>