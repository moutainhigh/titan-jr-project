//验证用户名格式
var email_reg=/^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
var phone_reg=/^13[0-9]{9}$|^14[0-9]{9}$|^15[0-9]{9}$|^18[0-9]{9}$|^17[0-9]{9}$/;
var payPwd_reg=/^[0-9]{6}$/;
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
//重写jquery ajax
(function($){  
    var _ajax=$.ajax;
    $.ajax=function(opt){
        //定义默认的error和success方法  
        var fn = {
        	beforeSend:function(XMLHttpRequest){},
        	success:function(data, textStatus){},
            error:function(XMLHttpRequest, textStatus, errorThrown){},  
            complete:function(XMLHttpRequest, textStatus){}
        };
        var defaultOption = {
        	showLoading:false,//默认不显示load圈
        	invalidCode:""    //不需要执行的代码,格式如:,403,603,
        };
        opt = $.extend({},defaultOption,opt);
        
        if(opt.beforeSend){  
            fn.beforeSend=opt.beforeSend;
        }
        if(opt.success){  
            fn.success=opt.success;  
        }
        if(opt.error){  
            fn.error=opt.error;
        } 
        if(opt.complete){  
            fn.complete=opt.complete;
        }
        //扩展增强处理  
        var _opt = $.extend(opt,{
        	beforeSend:function(XHR){
        		//显示loading圈
        		if(opt.showLoading){
        			F.loading.show();
        		}
        		fn.beforeSend(XHR);
            },
            success:function(data, textStatus){  
            	fn.success(data, textStatus);  
            },
            error:function(XHR, TS, errorThrown){
            	//登录判断
            	if(XHR.status&&XHR.status==603&&opt.invalidCode.indexOf(",603,")==-1){//没有登录
            		var returnUrl = encodeURIComponent(window.location.href);
            		location.href=js_base_path+"/ex/login.shtml?returnUrl="+returnUrl;
					return;
				}
            	//权限判断
            	if(XHR.status&&XHR.status==403&&opt.invalidCode.indexOf(",403,")==-1){
        			new top.Tip({msg : '没有权限访问，请联系管理员', type: 2 , timer:2000});
        			return ;
        		}
            	fn.error(XHR, TS, errorThrown);  
            },
            complete:function(XHR, TS){  
            	fn.complete(XHR, TS);
            	if(opt.showLoading){
            		F.loading.hide();
        		}
            }  
        });  
        return _ajax(_opt);  
    };  
})(jQuery); 
//ajax分页
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
/**
 * TWS发送验证码
 * @author luoqinglong 
 * @date 2017-03-28
 */
function SendCode(option){
	this.defaults={
		send_btn:null,//发送验证码的按钮
		receive_input:null,//接收短信的输入框
		msgType:null,//短信业务类型
		fui_form:null,//F.UI的表单
		verifyType:'phone',//接收账号的类型,phone:手机号，email:邮箱地址，all:手机号和邮箱，
		sendingFlag : false,
		second:60,
		interval:null
	};
	this._option = $.extend({},this.defaults,option);
	this.init();
}
/**
 * 倒计时
 */
SendCode.prototype.timeOut = function(){
	_this = this;
	this._option.interval = setInterval(function () {   
        if(_this._option.second>0){
        	_this._option.send_btn.html("重新发送(" + _this._option.second + ")"); 
        	_this._option.second--;
        }else{
        	_this.clearSend();
        }
   }, 1000);
};
/**
 * 重置验证码发送资源
 */
SendCode.prototype.clearSend = function(){
	this._option.send_btn.removeClass("r_huise").html("重新获取验证码");
    clearInterval(this._option.interval);
    this._option.second=60;
    this._option.sendingFlag = false;
    
};
/**
 * 验证码发送初始化
 */
SendCode.prototype.init = function(){
	_this = this;
	_this._option.send_btn.on('click',function(){
		if(_this._option.sendingFlag){
			return;
		}
	    var raObj = _this._option.receive_input;
	    var receiveAddress = raObj.val()||raObj.html();
	    if($.trim(receiveAddress).length==0){
	    	_this._option.fui_form._setErrorStyle(raObj,'必填项');
			return;	
		}
	    if(_this._option.verifyType=='phone'){
	    	if(!phone_reg.test(receiveAddress)){
	    		_this._option.fui_form._setErrorStyle(raObj,'格式不正确');
				return ;
			}
	    }else if(_this._option.verifyType=='email'){
	    	if(!email_reg.test(receiveAddress)){
	    		_this._option.fui_form._setErrorStyle(raObj,'格式不正确');
				return ;
			}
	    }else if(_this._option.verifyType=='all'){
	    	if((!phone_reg.test(receiveAddress))&&(!email_reg.test(receiveAddress))){
	    		_this._option.fui_form._setErrorStyle(raObj,'格式不正确');
				return ;
			}
	    }
	  //校验 msgType
		if(_this._option.msgType==null){
			new top.Tip({msg : "===代码错误，没有指定发送消息类型", type: 2, timer:2500});
			return;
		}
		if(!_this._option.send_btn.hasClass("r_huise")){  
			_this._option.sendingFlag = true;
			_this._option.send_btn.addClass('r_huise');
			_this.timeOut();
	    }
		
		$.ajax({
			type:'post',
			url : js_base_path+'/ex/sendCode.shtml',
			data:{"receiveAddress":receiveAddress,"msgType":_this._option.msgType},
			dataType : 'json',
			success : function(result){
				if(result.code==1){
				    new top.Tip({msg : '验证码已成功发送,请注意查收！', type: 1, timer:2000});
				}else{
					new top.Tip({msg : result.msg, type: 2, timer:2500});
					_this.clearSend();
				}
			},
			error:function(){
				_this.clearSend();
			}
		});
	});
};
var TWS = TWS||{};
TWS.initSendCode = function(option){
	new SendCode(option);
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
/**
 * 检查密码的复杂度
 * @param pass
 * @returns 密码强度1-4
 */
function checkComplicacy(pass){
	var modes = 0;
	if(pass.length<6){
		return 1;
	}
	if(/\d/.test(pass)){
		modes++;
	}
	if(/[a-z]/.test(pass)){
		modes++;
	}
	if(/[A-Z]/.test(pass)){
		modes++;
	}
	if(/\W/.test(pass)){
		modes++;
	}
	return modes;
}
