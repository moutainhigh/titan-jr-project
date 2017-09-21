(function(jQuery){  

if(jQuery.browser) return;  

jQuery.browser = {};  
jQuery.browser.mozilla = false;  
jQuery.browser.webkit = false;  
jQuery.browser.opera = false;  
jQuery.browser.msie = false;  

var nAgt = navigator.userAgent;  
jQuery.browser.name = navigator.appName;  
jQuery.browser.fullVersion = ''+parseFloat(navigator.appVersion); 
jQuery.browser.majorVersion = parseInt(navigator.appVersion,10);  
var nameOffset,verOffset,ix;  

// In Opera, the true version is after "Opera" or after "Version"  
if ((verOffset=nAgt.indexOf("Opera"))!=-1) {  
jQuery.browser.opera = true;  
jQuery.browser.name = "Opera";  
jQuery.browser.fullVersion = nAgt.substring(verOffset+6);  
if ((verOffset=nAgt.indexOf("Version"))!=-1)  
jQuery.browser.fullVersion = nAgt.substring(verOffset+8);  
}  
// In MSIE, the true version is after "MSIE" in userAgent  
else if ((verOffset=nAgt.indexOf("MSIE"))!=-1) {  
jQuery.browser.msie = true;  
jQuery.browser.name = "Microsoft Internet Explorer";  
jQuery.browser.fullVersion = nAgt.substring(verOffset+5);  
}  
// In Chrome, the true version is after "Chrome"  
else if ((verOffset=nAgt.indexOf("Chrome"))!=-1) {  
jQuery.browser.webkit = true;  
jQuery.browser.name = "Chrome";  
jQuery.browser.fullVersion = nAgt.substring(verOffset+7);  
}  
// In Safari, the true version is after "Safari" or after "Version"  
else if ((verOffset=nAgt.indexOf("Safari"))!=-1) {  
jQuery.browser.webkit = true;  
jQuery.browser.name = "Safari";  
jQuery.browser.fullVersion = nAgt.substring(verOffset+7);  
if ((verOffset=nAgt.indexOf("Version"))!=-1)  
jQuery.browser.fullVersion = nAgt.substring(verOffset+8);  
}   
// In Firefox, the true version is after "Firefox"  
else if ((verOffset=nAgt.indexOf("Firefox"))!=-1) {  
jQuery.browser.mozilla = true;  
jQuery.browser.name = "Firefox";  
jQuery.browser.fullVersion = nAgt.substring(verOffset+8);  
}  
// In most other browsers, "name/version" is at the end of userAgent  
else if ( (nameOffset=nAgt.lastIndexOf(' ')+1) <  
(verOffset=nAgt.lastIndexOf('/')) )  
{  
jQuery.browser.name = nAgt.substring(nameOffset,verOffset);  
jQuery.browser.fullVersion = nAgt.substring(verOffset+1);  
if (jQuery.browser.name.toLowerCase()==jQuery.browser.name.toUpperCase()) {  
jQuery.browser.name = navigator.appName;  
}  
}  
// trim the fullVersion string at semicolon/space if present  
if ((ix=jQuery.browser.fullVersion.indexOf(";"))!=-1)  
jQuery.browser.fullVersion=jQuery.browser.fullVersion.substring(0,ix);  
if ((ix=jQuery.browser.fullVersion.indexOf(" "))!=-1)  
jQuery.browser.fullVersion=jQuery.browser.fullVersion.substring(0,ix);  

jQuery.browser.majorVersion = parseInt(''+jQuery.browser.fullVersion,10);  
if (isNaN(jQuery.browser.majorVersion)) {  
jQuery.browser.fullVersion = ''+parseFloat(navigator.appVersion);  
jQuery.browser.majorVersion = parseInt(navigator.appVersion,10);  
}  
jQuery.browser.version = jQuery.browser.majorVersion;  
})(jQuery);

var Rnub=function(day){
    if(parseInt(day)<10){
        return "0"+parseInt(day);
    }else{
        return day+"";
    }
}
 function addDate(date,days){ 
   var d=new Date(date); 
   d.setDate(d.getDate()+days); 
   var m=d.getMonth()+1; 
   return d.getFullYear()+'-'+Rnub(m)+'-'+Rnub(d.getDate()); 
 } 

function datePickerAdd(dateStart, dateEnd, sCallback, eCallback, maxDate, minDate,extras,minDate_end,maxDate_limit,showButton){
    var _minDate=minDate ? minDate : GetDateStr(new Date());
    var extras = $.extend({ 
        startDefault : '',
        endDefault :  ''
    }, ( extras || {} ) );
    showButton=showButton?showButton:false;
    dateStart.datepicker({
        // defaultDate: "+1w",
        _getFormatConfig: true,
       // maxDate:GetDateStr(730),
        changeMonth : true,
        changeYear : true,
        minDate: minDate ? minDate : GetDateStr(new Date()),
        maxDate : maxDate ,
        defaultDate : extras.startDefault,
        showButtonPanel: showButton,          
        onClose: function( selectedDate ) {      
            if(selectedDate){
                if(minDate_end){
                     dateEnd.datepicker( "option", "minDate", addDate(selectedDate,1));
                 }else{
                     dateEnd.datepicker( "option", "minDate", selectedDate);
                 } 
                 if(maxDate_limit){
                     dateEnd.datepicker( "option", "maxDate", addDate(selectedDate,90));
                 }            
            }else{
                dateEnd.datepicker( "option", "minDate", new Date());
            }
          //  dateEnd.datepicker('show');
        },
        onSelect:function(dateText, inst){                        
            if(dateEnd.val()!=""){
                 sCallback && sCallback(dateText,inst,DateDiff(dateText,dateEnd.val())); 
            }else{
                 sCallback && sCallback(dateText,inst,""); 
            }       
        }
    });
     $(".ui-datepicker-close").on("click", function (){  
        datepicker_CurrentInput.value = "";  
    });
   
    if(dateStart.val()!=""&&minDate_end==true){
        _minDate=addDate(dateStart.val(),1);
    }
    dateEnd.datepicker({
        //defaultDate: "+1w",
        _getFormatConfig: true,
       // maxDate:GetDateStr(730),
        minDate :  _minDate,
        maxDate : maxDate,
        changeMonth : true,
        changeYear : true,
        defaultDate : extras.endDefault,
        showButtonPanel: showButton, 
        onClose: function( selectedDate ) {
            if(selectedDate){
                if(minDate_end){
                     dateStart.datepicker( "option", "maxDate", addDate(selectedDate,-1));
                 }else{
                     dateStart.datepicker( "option", "maxDate", selectedDate);
                 }    
                 if(maxDate_limit){
                     dateStart.datepicker( "option", "minDate", addDate(selectedDate,-90));
                 }        
            }else{
                dateStart.datepicker( "option", "maxDate", maxDate ? maxDate :'');
            }
        },
        onSelect:function(dateText,inst){          
            if(eCallback){
                eCallback(dateText,inst,DateDiff(dateStart.val(),dateText));
            }else{
                sCallback && sCallback(dateText,inst,DateDiff(dateStart.val(),dateText));
            }
        }
    }); 
    
}

function DateDiff(sDate1,  sDate2){  
   var  aDate,  oDate1,  oDate2,  iDays  
   aDate  =  sDate1.split("-")  
   oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    
   aDate  =  sDate2.split("-")  
   oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])  
   iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)   
   return  iDays  
}    

/**
* 添加日期段
* @selector 外层容器
* @args 配置项
*/
function DateSection(selector, args){
    this.args = $.extend({ 
        delBtn : '.J_date_list',
        addBtn : '.J_add_date_item',
        maxDate : '',
        minDate : ''
    }, ( args || {} ) );
    this.dom = selector instanceof $ ? selector : $( selector );
    this._init();
} 

DateSection.prototype = {
    _init : function(){
        var self = this;
        if(!this.dom.data('isbind')){
            this.dom.data('isbind',true);
            this._initDefaultInput();
            this.bindDelDate();
            this.bindAddDate();
            this.dom.data('getResult',this.getResult);
        }
    },
    _initDefaultInput : function(){
        var self = this;
        var input = this.dom.find('.J_date_list dd').eq(0).find('input.text');
        if(input.length){
            var sd =  input.eq(0).attr('readonly',true);
            var ed =  input.eq(1).attr('readonly',true);
            datePickerAdd(sd, ed, function(d, inst){
                self.checkRepeat(d, inst);
            },false, self.args.maxDate, self.args.minDate);
        }
    },
    bindDelDate : function(){
        var dom = this.dom;
        var delBtn =  dom.find(this.args.delBtn);
        delBtn.on('click','.S_digit_delete',function(){
            if(dom.find('.J_date_list dd').length<=1){new Tip({msg : '最少保留1段!' , type: 3})
            }else{
                $(this).parent().remove();
            }
            return false;
        });
    },
    bindAddDate : function(){
        var self = this;
        var dom  = this.dom;
        var addBtn =  dom.find(this.args.addBtn);
        var listDom= dom.find('.J_date_list');
        addBtn.on('click',function(){
            var inputs = listDom.find('input.text');
            var isPass = true;
            $.each(inputs, function(k,v){
                if($(this).val()==''){
                    new top.Tip({ msg : '亲，请检查输入！', type: 3});
                    isPass = false;
                    return false;
                }
            });

            if(!isPass){ return false; }

            dom.find('.J_add_date_copy').hide();   

            if(listDom.find('dd').length>=5){
                new top.Tip({msg : '亲，只能增加5条哦~', type: 2})
                return false;
            }
            var item = $('<dd class="J_date">'+
                '<input type="text" class="text w_100 text_calendar fl" readonly />'+
                '<label class="S_digit">至</label>'+
                '<input type="text" class="text w_100 text_calendar fl" readonly />'+
                '<i class="S_digit_delete"></i>'+
            '</dd>').appendTo( listDom); 

            var sd = item.find('input.text').eq(0);
            var ed = item.find('input.text').eq(1);

            datePickerAdd(sd, ed, function(d, inst){
                self.checkRepeat(d, inst);
            }, false, self.args.maxDate, self.args.minDate);
        });
    },
    checkRepeat : function(d, inst){
        var flag  = true;
        var dInt  = new Date(d)*1,
            input = $(inst.input), //当前的input
            parent= input.parent(),
            curSd = parent.find('input.text').eq(0),
            curEd = parent.find('input.text').eq(1);
     
        $.each(this.dom.find('.J_date_list dd').not(parent), function(k,v){
            var sd = $(this).find('input.text').eq(0);
            var ed = $(this).find('input.text').eq(1);
            if(sd.val()!='' && sd.val()!=''){
                var sdInt = new Date(sd.val())*1;
                var edInt = new Date(ed.val())*1;
                if(dInt>=sdInt && dInt<=edInt){
                    new Tip({ msg : '日期段重复，请检查输入！', type: 3});
                    input.datepicker('setDate', input.attr('data-prev'));
                    input.blur();
                    flag = false;
                    return false;
                }else{
                    var curSdInt = new Date(curSd.val())*1;
                    var curEdInt = new Date(curEd.val())*1;
                    if(curSdInt<sdInt && curEdInt>edInt){
                        new Tip({ msg : '日期段重复，请检查输入！', type: 3});
                        curSd.datepicker('setDate', curSd.attr('data-prev'));
                        curEd.datepicker('setDate', curSd.attr('data-prev'));
                        input.blur();
                        flag = false;
                        return false;
                    }
                }
            }
        });
  
        //记录当前选中的日期，以便还原
        flag && input.attr('data-prev', d);
        return flag;
    },

    /**
    * 返回选择日期段结果
    * @dom { String } selector ，可缺省 
    */
    getResult : function(dom){
        var result = [];
        var dom = dom ? dom : this.dom;
        $.each(dom.find('.J_date_list dd'), function(k, v){
            var sd = $(v).find('input.text').eq(0);
            var ed = $(v).find('input.text').eq(1);
            result.push({
                sd : sd.val(),
                ed : ed.val()
            });
        });
        return result;
    },
    hide : function(){
        var input = this.dom.find('.J_date_list dd').eq(0).find('input.text');
        if(input.length){
            var sd =  input.eq(0).attr('readonly',true);
            var ed =  input.eq(1).attr('readonly',true);
            sd.datepicker('hide');
            ed.datepicker('hide');
        }
    }
}

DateSection.getResult = function(selector){
    var dom  = selector instanceof $ ? selector : $( selector );
    return dom.data('getResult')(dom);
}



//placeholder效果（可兼容ie6）
var JplaceHolder = {
    _check : function(){
        return 'placeholder' in document.createElement('input');
    },
     init : function(){
        if(!this._check()){
            this.fix();
        }
    },
    fix : function(){
        $('input[placeholder]').each(function(index, element) {
            var self = $(this), txt = self.attr('placeholder');
            if(self.hasClass("J_region")) return;
            self.wrap($('<span></span>').css({position:'relative','display':'inline-block'}));
            var pos = self.position(), h = self.outerHeight(true), paddingleft = self.css('padding-left');
            var holder = $('<span></span>').text(txt).css({position:'absolute', left:pos.left, top:pos.top, height:h, lienHeight:h, paddingLeft:paddingleft,fontSize:'12px',textIndent:'10px', color:'#999'}).appendTo(self.parent());
            self.focusin(function(){
                holder.hide();
            }).focusout(function(){
                if(!self.val()){
                    holder.show();  
                }
            });
            holder.click(function(){
                holder.hide();
                self.focus();
            });
        }); 
    }
};

$(function(){
    JplaceHolder.init();    
});
  

//清除输入框的初始值
function inputClear(aclass){var arr = [];aclass.on('focus',function(){if($(this).val()!=''&&$(this).val()==arr[aclass.index(this)]){$(this).val('');}});aclass.each(function(index1, element) {arr.push($(this).val());aclass.on('blur',function(){if($(this).val()==''){$(this).val(arr[aclass.index(this)]);}});});}



/*
* 创建confirm对话框
* @args {json} 参数列表
*/
function createConfirm(args){

    //skin : 'oneBtns' 一个按钮的样式
    var button =  [ 
        {
            value: args.okValue ||'确定',
            callback: function () {
               args.ok && args.ok();
            },
            autofocus: true
        },
        {
            value:  args.cancelValue || '取消',
            callback: function () {
                args.cancel && args.cancel();
            }
        }
    ]

    if(args.button === false){ button = false}
    if(args.cancel === false){ button = [button[0]] }
    var d = dialog({
        title: args.title || '提示',
        padding: args.padding || '30px 20px 50px 20px',
        width :  args.width || 300,
        content:  args.content || '',
        skin : args.skin ? args.skin + ' saas_comfirm' : 'saas_comfirm',
        button : button,
        onclose : args.onclose || null
    }).showModal();
    
    
}



var iframeDialog = false;
function createIframeDialog(args){

    var args = $.extend({ 
        url : '',  //iframe url地址
        zIndex : 1000,
        loadComplete : null, //加载完成回调方法
        close:function(y){}
    },( args || {} ) );

    var w = $(window).width();
    var h =  $(window).height();

    iframeDialog && iframeDialog.remove();

    //iframe外框
    iframeDialog = $('<div class="iframe_wrap"><div class="other_popup_close"></div></div>').css({
        height : h,
        zIndex : args.zIndex
    }).appendTo('body').focus();

    //加载动画
    //F.loading.show();

    //iframe
    var iframe = $('<iframe id="pop_iframe" name="pop_iframe" src="' +  args.url + '" ></iframe>')
        .css({
            width : '100%',
            height :h,
            border:'none',
            overflow: 'hidden'
        }).appendTo(iframeDialog)

        //iframe加载完成
        .on('load',function(){

           // F.loading.hide();

            //加载完成回调函数
            args.loadComplete && args.loadComplete();
            //子页面document
            var doc = $(window.frames["pop_iframe"].document) || window.frames["pop_iframe"].contentWindow.document;

            //显示子页面滚动条
            if($.browser.msie && $.browser.version<8){
                doc.find('html').css({
                    height : h
                });
            }else{
                doc.find('body').css({
                    height : h
                });
            }
            iframe.unbind();
        });

    //关闭按钮
    iframeDialog.find('.other_popup_close').on('click',function(){
        args.close && args.close();
        console.log(11)
        removeIframeDialog();
        return false;
    });
}

/*
* 关闭iframe弹窗
*/
function removeIframeDialog(callback){
    callback && callback();
    iframeDialog && iframeDialog.unbind();
    iframeDialog && iframeDialog.remove();
    iframeDialog = false;
}


//菜单
$('.TTM .TTM_menu_list li').hover(function(){
    var _this=$(this);
        _this.addClass('on').siblings().removeClass('on').end().find('.li_hover').stop().delay(100).slideDown(0).end().siblings().find('.li_hover').stop().delay(100).slideUp(0);
},function(){       
    var _this=$(this);
        _this.removeClass('on').find('.li_hover').stop().delay(100).slideUp(0);    
        $('.TTM .TTM_menu_list li').eq(1).addClass('on');
})

$(function(){
    $(window).on('scroll',function(){
        var st = $(document).scrollTop();
        if( st>100 ){           
            $('#go_top').fadeIn(function(){
                $(this).removeClass('dn');
            });
        }else{
            $('#go_top').fadeOut(function(){
                $(this).addClass('dn');
            });
        }   
    });
    $('#go_top .go').on('click',function(){
        $('html,body').animate({'scrollTop':0},500);
    });
   // console.log($(document.body).height())
  /*  var bottomTop=$(".footer").offset().top+30;
    if($(document.body).height()>window.innerHeight){
            $(".footer").removeClass("footer_fixed");
        }else{
            if(bottomTop<window.innerHeight){
                $(".footer").addClass("footer_fixed");
            }; 
        }
         window.addEventListener("resize", function () {  
           console.log(123456)
        }, false);  
    $(window).resize(function(){
        console.log(213123)
        var bottomTop=$(".footer").offset().top+30;
        if($(document.body).height()>window.innerHeight){
            $(".footer").removeClass("footer_fixed");
        }else{
            if(bottomTop<window.innerHeight){
                $(".footer").addClass("footer_fixed");
            }; 
        }       
    });*/
});



/*
* 带箭头提示框框
* @dom  {selector} 提示信息依附的dom选择器
* @args {json} 参数列表
*/
function toolTip(dom, args){
    var dom =  dom instanceof $ ? dom : $(dom);
    var opts = $.extend({
        triggerType : 'click',
        width : 300,
        padding : '15px',
        align : 'bottom',
        quickClose : true,
        content : '',
        skin : 'saas_tip'
    }, args);
    
    var d = false;
    dom.unbind(opts.triggerType);
    dom[opts.triggerType](function(){
        d && d.show();
        d = dialog({
            skin : opts.skin,
            padding : opts.padding,
            width :  opts.width,
            align: opts.align,
            quickClose: opts.quickClose,
            content: args.content
        });
        d.show(this);
    })
}

  F.loading_line = (function($, F, undefined){
    var dom;
    var setInt;
    var show  = function(){
        dom= $('<div class="load_line1"><span></span></div>');
        $("body").append(dom);
        var i=0;
        function loadingshow(){
            if(i>=99){
                return;
             }else if(i>=90){
                i+=0.03;
                $(".load_line1 span").animate({"width": i+"%"},10);
             }else if(i>=60){
                i+=0.2;
                $(".load_line1 span").animate({"width": i+"%"},10);
            }else{
                i+=1;
                $(".load_line1 span").animate({"width": i+"%"},10);
            }     
        }
        setInt=setInterval(loadingshow,10)     
    }

    var remove = function(){
        clearInterval(setInt);
        $(".load_line1 span").stop(true,true);
        $(".load_line1 span").animate({"width":"100%"},200);
        setTimeout(function(){
            $(".load_line1").remove();
        },300)
    }
    return {
        show : show,
        remove : remove
    }
})(jQuery, F);


