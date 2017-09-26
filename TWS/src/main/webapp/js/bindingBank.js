var bc={};
bc.isDisabled=function(modal){
	var inputc=0,notNullc=0;
    $(modal).find("input").each(function(i,v){
    	inputc++;
        if($(v).val() != ""){
        	notNullc++;
        }
    });
    if(inputc==notNullc){//全部已填
    	$(modal).find(".button-btn").removeClass("prohibit").removeAttr("disabled");
    }else{
    	$(modal).find(".button-btn").addClass("prohibit").prop("disabled","true");
    }
}
//模态是否显示
bc.veilIsShow=function(isShow){
    if(isShow == "show"){
        // 显示弹框和遮罩层
        $(".veil").show();
        $(".modal-box").show();
        $("body").css("overflow","hidden");
    }else{
        // 显示弹框和遮罩层
        $(".veil").hide();
        $(".modal-box").hide();
        $("body").css("overflow","visible");
    }
}
// 提交按钮是否可点击
bc.checkSubmit=function(){
	bc.isDisabled(".enterprise-modal");
    bc.isDisabled(".personal-modal");
}
//当前选中的form
bc.getformId=function(){
	var t = sessionStorage.getItem("type");
	if(t=="enterprise"){
		return "J_form_enterprise";
	}else{
		return "J_form_personal";
	}
}
//审核结果
bc.bindResultView=function(){
	$.ajax({
		type:'post',
		dataType : 'html',	
	    url : js_base_path+'/account/bindResultView.shtml',
	    success : function(html){
			$("#bindcard-wrap").show();
			$(".modal-box").html(html);
			$(".examine-modal").css({"left":"50%"});
			$(".close").on("click",function(){
				bc.close();
		    });
	    },
	    error:function(data){
	    	new top.Tip({msg : '请求失败，请重试', type: 3});
	    }
	})
}

//关闭
bc.close=function(){
	 $("#bindcard-wrap").hide();
}
//绑卡页面
bc.bind_card=function(){
	$.ajax({
		type:'post',
        dataType : 'html',
        url : js_base_path+'/account/toBindAccountWithDrawCard.shtml',
        success : function(html){
        	$(".modal-box").html(html);
		    $("#bindcard-wrap").show();
		},
        error:function(xhr,status){
 			if(xhr.status&&xhr.status==403){
    			new top.Tip({msg : '没有权限访问，请联系管理员', type: 3 , timer:2000});
    			return ;
    		}
 			new top.Tip({msg : '请求失败，请重试', type: 3});
 		}
    });
}
//修改
bc.updateBind=function(){
	$.ajax({
		type:'post',
      dataType : 'html',
      url : js_base_path+'/account/toBindAccountWithDrawCard.shtml',
      success : function(html){
      		$(".modal-box").html(html);
		    $("#bindcard-wrap").show();
		},
      error:function(xhr,status){
			if(xhr.status&&xhr.status==403){
  			new top.Tip({msg : '没有权限访问，请联系管理员', type: 3 , timer:2000});
  			return ;
  		}
			new top.Tip({msg : '请求失败，请重试', type: 3});
		}
  });
}

bc.personalValid;
bc.enterpriseValid;
bc.updateCardValid;
//新增绑卡页面初始化
bc.initBindCardPanel=function(){
    // 表单验证
	bc.personalValid =  new validform('#J_form_personal',{
        noMust:true
    });
	bc.enterpriseValid = new validform('#J_form_enterprise',{
        noMust:true
    });
	sessionStorage.setItem("type","enterprise");
 // 账户类型选择
    $(".content-icon").on("click","span",function(){
        $(".content-icon").find(".iconfont").removeClass("icon-check selected").addClass("icon-check1");
        $(this).find(".iconfont").removeClass("icon-check1").addClass("icon-check selected");
        var type = $(this).attr("data-type");
        sessionStorage.setItem("type",type);
        typeContent();
    });
    // 弹框关闭按钮
    $(".close").on("click",function(){
    	bc.close();
    });
 // 根据类型显示内容
    function typeContent(){
        if(sessionStorage.getItem("type") == "enterprise"){
            $(".personal-modal").hide();
            $(".enterprise-modal").show();
        }else{
            $(".personal-modal").show();
            $(".enterprise-modal").hide();
        }
    }
 // input的清空按钮
    $(".content").on("keyup","input",function(){
        bc.isDisabled(".enterprise-modal");
        bc.isDisabled(".personal-modal");
    });
}
bc.initUpdateBindCardPanel=function(){
	bc.updateCardValid = new validform('#J_form_update',{
	    noMust:true
	 });
	 $(".close").on("click",function(){
	    bc.close();
	 });
	// 提交按钮是否可用
    $(".content").on("keyup","input",function(){
    	bc.isDisabled(".platform-binding-bank-modal");
    });
}
