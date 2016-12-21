var titan_expo = "10001";
var titanPayObj = {};

function initTitanPayObj() {
    var config = {
        intervalTime: 3000
    };
    var titan_rsakey = null;
    titanPayObj.initTitanPay = function(configObj) {
        if (!configObj || !configObj.module || !configObj.address) {
            return "Param_Error";
        } else {
            if (!configObj.empoent) {
                configObj.empoent = titan_expo;
            }
        } if (configObj) {
            config = configObj;
        }
        this.createPayForm(config.address);
        return "Success";
    };
    titanPayObj.createPayForm = function(address) {
        var elscript = document.createElement("script");
        elscript.src = config.address + "/titanjr-pay-app/js/rsa/RSA.js";
        elscript.setAttribute("type", "text/javascript");
        document.body.appendChild(elscript);
        var form = document.createElement("form");
        form.action = config.address + 'trade/titanPay.action';
        form.target = '_blank';
        form.id = 'titan_pay_form';
        form.method = 'post';
        var orderInfo = document.createElement("input");
        orderInfo.type = 'hidden';
        orderInfo.name = 'orderInfo';
        orderInfo.id = 'titan_orderInfo';
        var businessInfo = document.createElement("input");
        businessInfo.type = 'hidden';
        businessInfo.name = 'businessInfo';
        businessInfo.id = 'titan_businessInfo';
        form.appendChild(orderInfo);
        form.appendChild(businessInfo);
        document.body.appendChild(form);
    };
    titanPayObj.checkOrder = function(orderInfo) {
        return true;
    };
    titanPayObj.titanEncrypted = function(str) {
        if (titan_rsakey == null) {
            titanJrPayRsaObj.setMaxDigits(129);
            titan_rsakey = new titanJrPayRsaObj.RSAKeyPair(config.empoent, "", config.module);
        }
        return titanJrPayRsaObj.encryptedString(titan_rsakey, encodeURIComponent(str ,"gbk"));
    
    };
    titanPayObj.getTitanPayUrl = function(orderInfo, businessInfo) {
        if (this.checkOrder(orderInfo)) {
            var orderInfo = this.titanEncrypted(JSON.stringify(orderInfo));
            var businessInfo = JSON.stringify(businessInfo);
            var url = config.address + '/titanjr-pay-app/trade/titanPay.action';
            url += '?orderInfo=' + encodeURIComponent(orderInfo) + "&businessInfo=" + encodeURIComponent(businessInfo);
            return url;
        }
        return null;
    };
    titanPayObj.titanPay = function(orderInfo, businessInfo) {
        if (this.checkOrder(orderInfo)) {
            document.getElementById('titan_orderInfo').value = this.titanEncrypted(JSON.stringify(orderInfo));
            document.getElementById('titan_businessInfo').value = JSON.stringify(businessInfo);
            document.getElementById('titan_pay_form').submit();
        }
    };
};
initTitanPayObj();