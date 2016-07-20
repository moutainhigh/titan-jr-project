<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<c:forEach items="${tradePage.itemList}" var="tradeItem">
    <tr>
        <td width=""><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${tradeItem.createtime}" /></td>
        <td width="tdr">
            <c:if test="${tradeItem.remark != null }">
                <i class="flag_ico J_remark" orderId="${tradeItem.userorderid}" onclick="showRemarkInfo(this)"></i>
            </c:if>
        </td>
        <td width="">${tradeItem.tradeType}</td>
        <td width="" >
            <span style="max-width: 253px" title="${tradeItem.goodsname}">
                ${tradeItem.goodsname}
                <c:if test="${tradeItem.goodsdetail != null}">
                    -${tradeItem.goodsdetail}
                </c:if>
            </span>
        </td>
        <td width="" >
            <span style="max-width: 253px">
                ${tradeItem.transTarget}
            </span>
        </td>
        <td class="tdr">
            <c:if test="${tradeItem.tradeType == '付款' or tradeItem.tradeType == '提现'}">
                -
            </c:if>
            <c:if test="${tradeItem.tradeType == '收款' or tradeItem.tradeType == '充值'}">
                +
            </c:if>
            ${tradeItem.tradeamount /100}
        </td>
        <td>
            <c:if test="${tradeItem.isEscrowedPayment == 1 and tradeItem.tradeType == '收款'}">
                <i class="freeze_ico" title="供应商确认订单号即可解冻"/>
            </c:if>
        </td>
        <td class="tdr">${tradeItem.receivedfee /100}</td><!--只展示实收手续费-->
        <td></td>
        <td>
            <c:if test="${tradeItem.statusid == 3 and tradeItem.tradeType !='充值'  or tradeItem.statusid==5}">
                处理中
            </c:if>
            <c:if test="${tradeItem.statusid == 8}">
                已成功
            </c:if>
            <c:if test="${tradeItem.statusid == 6 and tradeItem.tradeType == '收款'}">
                已冻结
            </c:if>
            <c:if test="${tradeItem.statusid == 6 and tradeItem.tradeType != '收款'}">
                已成功
            </c:if>
            <c:if test="${tradeItem.statusid == 9}">
                <i class="MyAssets_red">交易失败</i>
            </c:if>
        </td>
        <td class="">
            <c:if test="${tradeItem.tradeType == '收款'}">
                <a class="J_orderDetails blue decorationUnderline m_r10" onclick="showReceiveDetail(this);" id="${tradeItem.businessordercode}">详情</a>
            </c:if>
            <c:if test="${tradeItem.tradeType == '付款'}">
                <a class="J_orderDetails blue decorationUnderline m_r10" onclick="showPayDetail(this);" id="${tradeItem.businessordercode}">详情</a>
            </c:if>
            <c:if test="${tradeItem.tradeType == '充值'}">
                <a class="J_orderDetails blue decorationUnderline m_r10" onclick="showRechargeDetail(this);" id="${tradeItem.userorderid}">详情</a>
            </c:if>
            <c:if test="${tradeItem.tradeType == '提现'}">
                <a class="J_orderDetails blue decorationUnderline m_r10" onclick="showWithDrawDetail(this);" id="${tradeItem.userorderid}">详情</a>
            </c:if>
            <a class="J_remark blue decorationUnderline" orderId="${tradeItem.userorderid}" onclick="showRemarkInfo(this)">备注</a></td>
    </tr>
</c:forEach>
<input type="hidden" id="tradePageTotal" value="${tradePage.totalCount}">
<input type="hidden" id="tradePageCurrent" value="${tradePage.currentPage}">
<script type="text/javascript" >
    //收款订单详情
    function showReceiveDetail(obj) {
        var code = $(obj).attr('id');
        $.ajax({
            dataType : 'html',
            context: document.body,
            data:{businessordercode:code},
            url : '<%=basePath%>/account/order-receive-detail.shtml',
            success : function(html){
                var d = window.top.dialog({
                    title: ' ',
                    padding: '0 0 0px 0 ',
                    content: html,
                    skin : 'saas_pop',
                    button : [
                        {
                            value: '关闭',
                            skin : 'btn btn_grey btn_exit',
                            callback: function () {
                                top.removeIframeDialog();
                            }
                        }
                    ]
                }).showModal();
            }
        });
    }

    function showPayDetail(obj) {
        var code = $(obj).attr('id');
        $.ajax({
            dataType : 'html',
            context: document.body,
            data:{businessordercode:code},
            url : '<%=basePath%>/account/order-pay-detail.shtml',
            success : function(html){
                var d = window.top.dialog({
                    title: ' ',
                    padding: '0 0 0px 0 ',
                    content: html,
                    skin : 'saas_pop',
                    button : [
                        {
                            value: '关闭',
                            skin : 'btn btn_grey btn_exit',
                            callback: function () {
                                top.removeIframeDialog();
                            }
                        }
                    ]
                }).showModal();
                window.parent.$(".J_rechargeBtn").on('click',function(){
                    d.remove();//隐藏订单详情弹框
                    window.top.createIframeDialog({
                        url : 'TFS/充值.html',
                    });
                    return false;
                });
            }
        });
    }

    function showRechargeDetail(obj) {
        var code = $(obj).attr('id');
        $.ajax({
            dataType : 'html',
            context: document.body,
            data:{userOrderId:code},
            url : '<%=basePath%>/account/order-recharge-detail.shtml',
            success : function(html){
                var d = window.top.dialog({
                    title: ' ',
                    padding: '0 0 0px 0 ',
                    content: html,
                    skin : 'saas_pop',
                    button : [
                        {
                            value: '关闭',
                            skin : 'btn btn_grey btn_exit',
                            callback: function () {
                                top.removeIframeDialog();
                            }
                        }
                    ]
                }).showModal();
                window.parent.$(".J_rechargeBtn").on('click',function(){
                    d.remove();//隐藏订单详情弹框
                    window.top.createIframeDialog({
                        url : 'TFS/充值.html',
                    });
                    return false;
                });
            }
        });
    }
    function showWithDrawDetail(obj) {
        var code = $(obj).attr('id');
        $.ajax({
            dataType : 'html',
            context: document.body,
            data:{userOrderId:code},
            url : '<%=basePath%>/account/order-withdraw-detail.shtml',
            success : function(html){
                var d = window.top.dialog({
                    title: ' ',
                    padding: '0 0 0px 0 ',
                    content: html,
                    skin : 'saas_pop',
                    button : [
                        {
                            value: '关闭',
                            skin : 'btn btn_grey btn_exit',
                            callback: function () {
                                top.removeIframeDialog();
                            }
                        }
                    ]
                }).showModal();
                window.parent.$(".J_rechargeBtn").on('click',function(){
                    d.remove();//隐藏订单详情弹框
                    window.top.createIframeDialog({
                        url : 'TFS/充值.html',
                    });
                    return false;
                });
            }
        });
    }

    //备注
    function showRemarkInfo(obj) {
        var code = $(obj).attr("orderId");
        $.ajax({
            dataType: 'html',
            context: document.body,
            data: {userOrderId: code},
            url: '<%=basePath%>/account/order-remark.shtml',
            success: function (html) {
                var d = window.top.dialog({
                    title: ' ',
                    padding: '0 0 0px 0 ',
                    content: html,
                    skin: 'saas_pop',
                    button: [
                        {
                            value: '保存',
                            skin: 'btn btn_save',
                            callback: function () {
                                //保存备注信息
                                var flag = false;
                                $.ajax({
                                    async:false,
                                    type:'post',
                                    data:{"remark":top.$("#id_remark_text").val(),userOrderId: code},
                                    url : '<%=basePath%>/account/updateOrderRemark.shtml',
                                    dataType : 'json',
                                    success : function(result){
                                        if(result.code==1){
                                            alert("备注信息更新成功");
                                        }else{
                                            alert("备注信息更新失败：" + result.msg);
                                        }
                                    }
                                });
                            },
                            autofocus: true
                        },
                        {
                            value: '取消',
                            skin: 'btn btn_grey btn_exit',
                            callback: function () {
                                //直接关闭
                            }
                        }
                    ]
                }).showModal();
            }
        });
    }

</script>