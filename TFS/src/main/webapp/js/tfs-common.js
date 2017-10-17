//验证用户名格式
var email_reg=/^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
var phone_reg=/^13[0-9]{9}$|^14[0-9]{9}$|^15[0-9]{9}$|^18[0-9]{9}$|^17[0-9]{9}$/;
//个人提现
var personBankData={
		content:[
		         {'key':'102','val':'中国工商银行'},
		         {'key':'104','val':'中国银行'},
		         {'key':'103','val':'中国农业银行'},
		         {'key':'105','val':'中国建设银行'},
		         {'key':'301','val':'交通银行'},
		         {'key':'302','val':'中信银行'},
		         {'key':'303','val':'中国光大银行'},
		         {'key':'304','val':'华夏银行'},
		         {'key':'305','val':'中国民生银行'},
		         {'key':'308','val':'招商银行'},
		         {'key':'309','val':'兴业银行'},
		         {'key':'306','val':'广发银行'},
		         {'key':'307','val':'平安银行'},
		         {'key':'310','val':'上海浦东发展银行'},
		         {'key':'403','val':'中国邮政储蓄银行'}
		         ]
};
function AjaxPage(option){
	this.defaults = {
			"pageSize":15,
			"pageNo":1,
			"url":"",
			success:function(){},//ajax请求成功的回调函数
			error:function(){},
			"pageWrapClass":"",//包含table和kkpage的外层dom 的class
			"queryParams":{}//请求参数
		};
	this.option = $.extend({},this.defaults,option);
	this.page = null;
	this.load();
	this.bindSizeClick();
}
/**
 * 绑定pageSize点击事件
 */
AjaxPage.prototype.getSelectorPrefix=function(){
	var _pageClass = this.option.pageWrapClass;
	var selectorPrifix = "";
	if(_pageClass.length>0){
		selectorPrifix = "."+_pageClass+" ";
	}
	return selectorPrifix;
};
/**
 * 绑定pageSize点击事件
 */
AjaxPage.prototype.bindSizeClick=function(){
	var selectorPrefix = this.getSelectorPrefix();
	var _ajaxPage = this;
	$(selectorPrefix+".pagination_r i").click(function(){
		$(selectorPrefix+".pagination_r i").eq($(this).index()).addClass("on").siblings().removeClass("on");
		var pageSize = $(selectorPrefix+".pagination_r .on").text();
		_ajaxPage.load({"pageSize":pageSize});
		
	});
};

AjaxPage.prototype.setSize=function(){
	if(arguments.length>0&&(typeof arguments[0] == 'number')){
		this.option.pageSize = arguments[0];
	}
};
/***
 * 加载表格数据
 *
 * @param option
 */
AjaxPage.prototype.load=function(){
	var _ajaxPage = this;
	var _option;
	this.option.pageNo=1;
	
	if(arguments.length>0){
		if(typeof arguments[0] == 'string'){
			this.option.url = arguments[0];
		}else{
			_option= arguments[0];
		}
		_ajaxPage.option = $.extend({},_ajaxPage.option,_option);
	}
	var _queryParams = $.extend(_ajaxPage.option.queryParams,{pageSize:_ajaxPage.option.pageSize,pageNo:_ajaxPage.option.pageNo});
	_ajaxPage.option.queryParams = _queryParams;
	if(_ajaxPage.option.url.length==0){
		return;
	}
	
	$.ajax({
		dataType : 'html',
		type:'post',
		data:_ajaxPage.option.queryParams,
		url :_ajaxPage.option.url,
		beforeSend:function(){
			F.loading.show();
		},
		success : function(html){
			if (_ajaxPage.option.success){
				_ajaxPage.option.success.call(_ajaxPage, html);
			}
			var selectorPrefix = _ajaxPage.getSelectorPrefix();
			var retPageSize = $(selectorPrefix+".pageSize").val();
			if(retPageSize&&retPageSize.length>0){
				$(selectorPrefix+".pagination_r i").each(function(i){
					if($(this).text()==retPageSize){
						$(this).addClass("on");
					}else{
						$(this).removeClass("on");
					}
				});
			}
			_ajaxPage.page = new Pager({
				pno : $(selectorPrefix+".currentPage").val(),
		        isShowTotalRecords :true,
				isGoPage : true,
		        mode : 'click',//默认值是link，可选link或者click
				total : $(selectorPrefix+".totalPage").val(),
				totalRecords : $(selectorPrefix+".totalCount").val(),
				click:function(pageNo){
					this.selectPage(pageNo);
					_ajaxPage.load({"pageNo":pageNo});
				}
			});
		},
		error:function(xhr){
			if (_ajaxPage.option.error){
				_ajaxPage.option.error.call(_ajaxPage, xhr);
			}
			
		},
		complete:function(){
			F.loading.hide();
		}
	});
};
//刷新待审核机构数
function freshCheckCount(){
	var enCount = parseInt($("#enCount").val());
	var perCount = parseInt($("#perCount").val());
	$("#i_encount").html(enCount);
	$("#i_percount").html(perCount);
	if($("#head_count")){
		$("#head_count").html(enCount+perCount);
	}
	
}
//把回车字符替换为br
function replaceEnterKey(src){
	var string = src;  
	try{  
	    string=string.replace(/\r\n/g,"<BR/>");  
	    string=string.replace(/\n/g,"<BR/>");  
	}catch(e) {  
	    alert(e.message);  
	}  
	return string;  
}
//把br换为/r/n
function backEnterKey(src){
	return src.replace(/\<BR\/\>/gi,"\r\n");  
}
//心跳,每5分钟请求一次
function beat(){
	window.setInterval(function(){
		$.ajax({
			url:js_base_path+"/beat.action",
			type:"post",
			dataType:"json"
		});
	}, 5*60*1000);
}
var tfs_common_valid = {
		
		validAmount :function(src){
			var decimals_reg = /^[1-9]{1}\d{0,7}(\.\d{1,2})?$/;
			var number_reg = /^[0]{1}(\.\d{1,2})?$/;
			return decimals_reg.test(src)||number_reg.test(src);
		},
		
		isBlank :function(src){
			if(src === undefined || $.trim(src).length<1){
				return true;
			}
			return false;
		},
		
		idBankCard :function(src){
			var neg = /^[0-9]\d*$/;
   	        if(src.length>30||!neg.test(src)){
   	        	return true;
   	        }
			return false;
		},
		isTitanCode: function(src){
			var neg = /^[0-9]{8}$/;
   	        if(neg.test(src)){
   	        	return true;
   	        }
			return false;
		}
}

