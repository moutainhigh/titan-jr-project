<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/comm/taglib.jsp" %>
<c:forEach items="${tradePage.itemList}" var="tradeItem">
    <tr>
        <td></td>
        <td class="tdl"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${tradeItem.createtime}"/></td>

        <td class="tdl">
            <c:if test="${tradeItem.tradeType == '收款'}">
                <span class="blue undl curpo J_orderDetails" onclick="showReceiveDetail(this);"
                  id="${tradeItem.businessordercode}">
                    ${tradeItem.userorderid}-${tradeItem.businessordercode}
                </span>
            </c:if>

            <c:if test="${tradeItem.tradeType == '付款'}">
                <span class="blue undl curpo J_orderDetails" onclick="showPayDetail(this);"
                  id="${tradeItem.businessordercode}">
                    ${tradeItem.userorderid}-${tradeItem.businessordercode}
                </span>
            </c:if>

            <c:if test="${tradeItem.tradeType == '充值'}">
                <span class="blue undl curpo J_orderDetails" onclick="showRechargeDetail(this);"
                  id="${tradeItem.userorderid}">
                    ${tradeItem.userorderid}-${tradeItem.businessordercode}
                </span>
            </c:if>

            <c:if test="${tradeItem.tradeType == '提现'}">
                <span class="blue undl curpo J_orderDetails" onclick="showWithDrawDetail(this);"
                  id="${tradeItem.userorderid}">
                    ${tradeItem.userorderid}-${tradeItem.businessordercode}
                </span>
            </c:if>
        </td>

        <td class="tdl">
            <span class="Province" style="max-width:300px;" title="${tradeItem.goodsname}">
                ${tradeItem.goodsname}
                <c:if test="${tradeItem.goodsdetail != null}">
                    -${tradeItem.goodsdetail}
                </c:if>
            </span>
        </td>

        <td class="tdl">
            <span class="Province" style="max-width:185px;" title="${tradeItem.transTarget}">
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
            ${tradeItem.amount /100}
        </td>

        <td class="tdl"><span class="ico_forzen"></span></td>
    </tr>
</c:forEach>

<input type="hidden" id="tradePageTotal" value="${tradePage.totalCount}">
<input type="hidden" id="tradePageCurrent" value="${tradePage.currentPage}">

<script type="text/javascript">
    //收款订单详情
    function showReceiveDetail(obj) {
        var code = $(obj).attr('id');
        $.ajax({
            dataType: 'html',
            context: document.body,
            data: {businessordercode: code},
            url: '<%=basePath%>/account/order-receive-detail.shtml',
            success: function (html) {
                var d = window.top.dialog({
                    title: ' ',
                    padding: '0 0 0px 0 ',
                    content: html,
                    skin: 'saas_pop',
                    button: [
                        {
                            value: '关闭',
                            skin: 'btn btn_grey btn_exit',
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
            dataType: 'html',
            context: document.body,
            data: {businessordercode: code},
            url: '<%=basePath%>/account/order-pay-detail.shtml',
            success: function (html) {
                var d = window.top.dialog({
                    title: ' ',
                    padding: '0 0 0px 0 ',
                    content: html,
                    skin: 'saas_pop',
                    button: [
                        {
                            value: '关闭',
                            skin: 'btn btn_grey btn_exit',
                            callback: function () {
                                top.removeIframeDialog();
                            }
                        }
                    ]
                }).showModal();
                window.parent.$(".J_rechargeBtn").on('click', function () {
                    d.remove();//隐藏订单详情弹框
                    window.top.createIframeDialog({
                        url: 'TFS/充值.html',
                    });
                    return false;
                });
            }
        });
    }

    function showRechargeDetail(obj) {
        var code = $(obj).attr('id');
        $.ajax({
            dataType: 'html',
            context: document.body,
            data: {userOrderId: code},
            url: '<%=basePath%>/account/order-recharge-detail.shtml',
            success: function (html) {
                var d = window.top.dialog({
                    title: ' ',
                    padding: '0 0 0px 0 ',
                    content: html,
                    skin: 'saas_pop',
                    button: [
                        {
                            value: '关闭',
                            skin: 'btn btn_grey btn_exit',
                            callback: function () {
                                top.removeIframeDialog();
                            }
                        }
                    ]
                }).showModal();
                window.parent.$(".J_rechargeBtn").on('click', function () {
                    d.remove();//隐藏订单详情弹框
                    window.top.createIframeDialog({
                        url: 'TFS/充值.html',
                    });
                    return false;
                });
            }
        });
    }

    function showWithDrawDetail(obj) {
        var code = $(obj).attr('id');
        $.ajax({
            dataType: 'html',
            context: document.body,
            data: {userOrderId: code},
            url: '<%=basePath%>/account/order-withdraw-detail.shtml',
            success: function (html) {
                var d = window.top.dialog({
                    title: ' ',
                    padding: '0 0 0px 0 ',
                    content: html,
                    skin: 'saas_pop',
                    button: [
                        {
                            value: '关闭',
                            skin: 'btn btn_grey btn_exit',
                            callback: function () {
                                top.removeIframeDialog();
                            }
                        }
                    ]
                }).showModal();
                window.parent.$(".J_rechargeBtn").on('click', function () {
                    d.remove();//隐藏订单详情弹框
                    window.top.createIframeDialog({
                        url: 'TFS/充值.html',
                    });
                    return false;
                });
            }
        });
    }

</script>