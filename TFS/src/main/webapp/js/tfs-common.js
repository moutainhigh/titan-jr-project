//验证用户名格式
var email_reg=/^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
var phone_reg=/^13[0-9]{9}$|^14[0-9]{9}$|^15[0-9]{9}$|^18[0-9]{9}$|^17[0-9]{9}$/;
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
	$("#i_encount").html($("#enCount").val());
	$("#i_percount").html($("#perCount").val());
}
/***
//重写ajax
(function($){     
    //备份jquery的ajax方法     
    var _ajax=$.ajax;     
         
    //重写jquery的ajax方法     
    $.ajax=function(opt){     
        //备份opt中error和success方法     
        var fn = {     
            error:function(XMLHttpRequest, textStatus, errorThrown){},     
            success:function(data, textStatus){}     
         } ;    
         if(opt.error){     
             fn.error=opt.error;     
         }     
         if(opt.success){     
             fn.success=opt.success;     
         }     
              
         //扩展增强处理     
         var _opt = $.extend(opt,{     
             error:function(XMLHttpRequest, textStatus, errorThrown){     
                 //错误方法增强处理     
            	 if(XMLHttpRequest.status&&XMLHttpRequest.status==403){
         			new top.Tip({msg : '没有权限访问，请联系管理员', type: 3 , time:2000});
         			return ;
         		}
         		if(XMLHttpRequest.status&&XMLHttpRequest.status==604){
         			new top.Tip({msg : '请先开通金融账号', type: 3 , time:2000});
         			return ;
         		}
                 fn.error(XMLHttpRequest, textStatus, errorThrown);
                 new top.Tip({msg : '系统错误，请重试!', type: 3 , time:1500}); 
             },     
             success:function(data, textStatus){
                 fn.success(data, textStatus);     
             }     
         });     
         _ajax(_opt);     
     };     
 })(jQuery);    
***/