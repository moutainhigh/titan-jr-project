<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/comm/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
    <jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
<body>
<div id="scroll">
    <div class="main_top clearfix headline">
        <div class="main_return fl"><a href="<%=basePath%>/account/overview-main.shtml"><i></i>返回上一页</a></div>
        <div class="history fl">您所在位置：我的资产 &gt; 冻结金额详情</div>
    </div>
    <div class="main_con p_b8" style="padding-left:14px;">
        <div class="MyAssets_top_tip" style="display: none"><i class="ico_frozen fl"></i><span
                class="fl" >交易平台在线收款默认为担保支付，在订单离店日后1天款项自动解冻</span>
            <i class="tourism_ico curpo chacha fl J_delete"></i>
        </div>
        <div class="MyAssets_list_Options" style="padding-left:0px;">
            <div class="J_Section">
                <dl class="J_date_list ">
                    <dd>
                        <label for="date_bach_start2"></label>
                        <input type="text" id="date_bach_start" name="date_bach_start"
                               class="text w_200 text_calendar fl">
                        <label for="date_bach_end2" class="S_digit">至</label>
                        <input type="text" id="date_bach_end" name="date_bach_end" class="text w_200 text_calendar fl">
                    </dd>
                </dl>
            </div>
            <div class="MyAssets_w_border fl ml10">
                <input type="text" value="" id="userTradeNo" placeholder="交易单号："></div>
            <div class="MyAssets_w_border fl ml10">
                <input type="text" value="" id="partner" placeholder="交易对方："></div>
            <a class="btn btn_magnify m_l2 fl ml10 MyAssets_Search" href="javascript:void(0)" onclick="queryFreezeOrders()">&nbsp;</a>
            <a href="javascript:void(0)" class="MyAssets_Export fr bacth_export_hotel J_export">
                <img src="<%=cssSaasPath%>/images/TFS/tfs_c01.png"></a>
        </div>
        <div class="label" style="margin-right:17px;">
            <table border="0" cellspacing="0" width="100%">
                <colgroup>
                    <col width="20">
                    <col width="23%">
                    <col width="20%">
                    <col width="25%">
                    <col width="15%">
                    <col width="10%">
                    <col width="">
                </colgroup>
                <tr>
                    <td></td>
                    <td class="tdl">交易时间</td>
                    <td class="tdl">交易单号</td>
                    <td class="tdl">交易内容</td>
                    <td class="tdl">付款方</td>
                    <td class="tdr">交易金额</td>
                    <td></td>
                </tr>
            </table>
        </div>

    </div>
</div>
<div class="scroll_x hide t_140"></div>
<div class="main_con p_t175" style="padding-left:14px;">

    <div class="no_data" style="display:none;">
        <p>没找到您想要的酒店</p>
        您可以换个条件再搜索！
    </div>
    <!-- 请输入查询条件后查询 -->
    <div class="report-search" style="display: none"></div>
    <div class="MyAssets_frozen p_t15">
        <table border="0" cellspacing="0" width="100%">
            <colgroup>
                <col width="20">
                <col width="23%">
                <col width="20%">
                <col width="25%">
                <col width="15%">
                <col width="10%">
                <col width="">
            </colgroup>
            <tbody id="id_1"></tbody>
        </table>
    </div>
</div>


<div style="height:50px;"></div>
<div class="main_kkpager">
    <div class="pagination1">
        <div class="pagination_r">
            每页显示酒店数量
            <i class="on">5</i>
            <i>10</i>
            <i>15</i>
            <i>20</i>
        </div>
    </div>
    <div id="kkpager" class="page_turning"></div>
</div>

<script>
    // window.open
    // ('dollare.html','newwindow','height=768,width=1366,top=0,left=0,toolbar=no,menubar=no,scrollbars=no,  resizable=no,location=no, status=no');
    //滚动显示线
    $(window).scroll(function () {
        var win_top = $(window).scrollTop();
        if (win_top > 0) {
            $('.scroll_x').show();
        }
        else {
            $('.scroll_x').hide();
        }
    });

    $(".pagination_r i").click(function () {
        $(".pagination_r i").eq($(this).index()).addClass("on").siblings().removeClass("on");
    });

    F.UI.scan();
    //添加日期锻

    $(function () {
        initRequest(); 
        initQueryDate();
        initAutoSelectPartner()
    });

    //添加日期锻
    function initQueryDate() {
        var current = new Date();
        current.setMonth(current.getMonth() - 3);//查询三个月的数据
        var dateStr = current.getFullYear() + "-" + (current.getMonth() + 1) + "-" + current.getDate();
        new DateSection('.J_Section',{minDate:dateStr,maxDate:''});
    }

    //初始化页面请求
    function initRequest() {
        var size = $(".pagination_r .on").text();
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

    function queryFreezeOrders() {
        var size = $(".pagination_r .on").text();
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

    function validate_partner(partnerName){
		for(var i = 0 ; i <partner_dataSource.organDTOList.length;i++)
		{
			
			if(partnerName == partner_dataSource.organDTOList[i].orgName){
				
				return true;
			}
		}
		return false;
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

    //点击删除提示
    $(".J_delete").on('click', function (event) {
        $(".MyAssets_top_tip").hide();
        $(".p_t175").css("padding-top", "130px");
        
        $.cookie('freezeTip' , 'hide' , { expires: 30 });
        
    });

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

    $(".pagination_r i").on('click',function(){
        $(".pagination_r i").eq($(this).index()).addClass("on").siblings().removeClass("on");
        var size = $(this).text();
        pageSizeChangeRequest(1, size);
    });

    //切换每页数量时调用
    function pageSizeChangeRequest(page, size) {
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
            data:{
                currentPage:page,
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
                pageGo(page,total,size);
                F.loading.hide();
            }
        });
    }
    
   
	(function() {
		var status = $.cookie('freezeTip');
		if (status && status == 'hide') {
			$(".MyAssets_top_tip").hide();
		} else {
			$(".MyAssets_top_tip").show();
		}
	}());
</script>
</body>
</html>