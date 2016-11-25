<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/comm/path-param.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>SAAS后台管理</title>
<jsp:include page="/comm/static-resource.jsp"></jsp:include>
<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
<body>
	<div id="scroll">
		<div class="main_top clearfix headline bg_bom">
			<div class="main_return fl">
				<a href="javascript:history.go(-1)"><i></i>返回上一页</a>
			</div>
			<div class="history fl">我的贷款 > 申请包房贷款</div>
		</div>
	</div>
	<div class="Refund clearfix">
		<div class="R_title">
			<i></i>申请包房贷款
		</div>
		<div class="R_c clearfix">
			<!-- <div class="RC_t">
      <ul>
        <li>贷款编号：D13245678913264</li>        
      </ul>
    </div> -->

			<div class="RC_c clearfix">
				<ul>
					<input name="loanApplyOrderNo" id="loanApplyOrderNo" class="text w_250  c_666 " type="hidden" value=""/>
					<li class="clearfix"><div class="tit">贷款金额</div> <i
						class="c_666 f_20"><input name="amount" id="amount"
							class="text w_250  c_666 " placeholder="" type="text" value=""></i>
						<i class="c_666 ">元</i> <i class="c_999 m_l20">贷款最长期限为90天，日费率0.5‰，到期日前随借随还</i>
					</li>
					<li class="clearfix J_Section"><div class="tit">酒店信息</div>
						<div class="w_290">
							<i class="c_f00">*</i>酒店名称：<input name="hotelName" id="hotelName"
								class="text w_184 c_666 " placeholder="" type="text" value="">
						</div>
						<div class="w_360">
							<i class="c_f00">*</i>包房时段： <input type="text"
								class="text w_120 text_calendar" name="beginDate" id="dataS"
								readonly="readonly" placeholder="请选择日期"> 至 <input
								type="text" class="text w_120 text_calendar" name="endDate"
								id="dataE" readonly="readonly" placeholder="请选择日期">
						</div>
						<div class="w_400">
							<i class="c_f00">*</i>包房数量：<input name="roomNights"
								id="roomNights" class="text w_120 c_666" placeholder=""
								type="text" value=""> 间夜
						</div></li>
					<li class="clearfix"><div class="tit">收款信息</div>
						<div class="w_290">
							<i class="c_f00">*</i>账户名：<input name="accountName"
								id="accountName" class="text w_184 c_666 m_l10" placeholder=""
								type="text" value="">
						</div>
						<div class="w_360">
							<i class="c_f00">*</i>银行卡号：<input name="account" id="account"
								class="text w_265 c_666" placeholder="" type="text" value="">
						</div>
						<div class="w_400">
							<i class="c_f00">*</i>开户行： <select
								class="select b_fff m_l10 w_150" name="bank" id="bank">
								<option value="0">开户行</option>
								<option value="1">开户行</option>
								<option value="2">开户行</option>
							</select>
						</div> <input type="hidden" class="text w_250  c_666 "
						name="contactNames" id="contactNames"></li>
					<li class="clearfix p_l83"><div class="tit">包房合同</div>
						<div class="p_l25">请至少上传一张包房合同附件，附件格式支持PDF、JPG、JPEG、PNG、ZIP、RAR，大小不超过5M</div>
						<dl>
							<dd class="RC_c_dd">
								<div class="TFSaddImg"></div>
								<input type="file" name="compartment_contract" id="compartment_contract1">
								<div class="TFSuploading TFSupload_ZIP hidden">
									<p class="TFSuploading1">
										<span></span>
									</p>
									<p class="TFSuploading2">
										已上传<i>0</i>%
									</p>
								</div>
								<div class="TFSuploaderror hidden">
									<i class="J_re_upload loanInformation_upload_btn">重新上传</i>
									<p>上传失败</p>
								</div>
								<div class="TFSimgOn hidden">
									<i class="J_delete_upload loanInformation_upload_btn">删除</i>
									<div class="dd_img">
										<img src="../images/TFS/LH01.jpg">
									</div>
									<div class="dd_text" title="附件附件">附件附件.jpg</div>
								</div>
								<div>
								  <span id="compartment_contract1_error" style="color:red;"></span>
								</div>
							</dd>
							<dd class="RC_c_dd">
								<div class="TFSaddImg"></div>
								<input type="file" name="compartment_contract" id="compartment_contract2">
								<div class="TFSuploading TFSupload_ZIP hidden">
									<p class="TFSuploading1">
										<span></span>
									</p>
									<p class="TFSuploading2">
										已上传<i>0</i>%
									</p>
								</div>
								<div class="TFSuploaderror hidden">
									<i class="J_re_upload loanInformation_upload_btn">重新上传</i>
									<p>上传失败</p>
								</div>
								<div class="TFSimgOn hidden">
									<i class="J_delete_upload loanInformation_upload_btn">删除</i>
									<div class="dd_img">
										<img src="../images/TFS/help_logo.jpg">
									</div>
									<div class="dd_text" title="附件附件">附件附件.jpg</div>
								</div>
								<div>
								  <span id="compartment_contract2_error" style="color:red;"></span>
								</div>
							</dd>
							<dd class="RC_c_dd">
								<div class="TFSaddImg"></div>
								<input type="file" name="compartment_contract" id="compartment_contract3">
								<div class="TFSuploading TFSupload_ZIP hidden">
									<p class="TFSuploading1">
										<span></span>
									</p>
									<p class="TFSuploading2">
										已上传<i>0</i>%
									</p>
								</div>
								<div class="TFSuploaderror hidden">
									<i class="J_re_upload loanInformation_upload_btn">重新上传</i>
									<p>上传失败</p>
								</div>
								<div class="TFSimgOn hidden">
									<i class="J_delete_upload loanInformation_upload_btn">删除</i>
									<div class="dd_img">
										<img src="../images/TFS/LH01.jpg">
									</div>
									<div class="dd_text" title="附件附件">附件附件.jpg</div>
								</div>
								<div>
								  <span id="compartment_contract3_error" style="color:red;"></span>
								</div>
							</dd>
							<dd class="RC_c_dd">
								<div class="TFSaddImg"></div>
								<input type="file" name="compartment_contract" id="compartment_contract4">
								<div class="TFSuploading TFSupload_ZIP hidden">
									<p class="TFSuploading1">
										<span></span>
									</p>
									<p class="TFSuploading2">
										已上传<i>0</i>%
									</p>
								</div>
								<div class="TFSuploaderror hidden">
									<i class="J_re_upload loanInformation_upload_btn">重新上传</i>
									<p>上传失败</p>
								</div>
								<div class="TFSimgOn hidden">
									<i class="J_delete_upload loanInformation_upload_btn">删除</i>
									<div class="dd_img">
										<img src="../images/TFS/LH01.jpg">
									</div>
									<div class="dd_text" title="附件附件">附件附件.jpg</div>
								</div>
								<div>
								  <span id="compartment_contract4_error" style="color:red;"></span>
								</div>
							</dd>
						</dl></li>
				</ul>
			</div>
		</div>
	</div>



	<div style="height: 60px"></div>
	<div class="TFS_data_button">
		<a class="btn btnNext" href="泰坦金融-我的贷款首页-包房贷款申请已提交.html">提交申请</a> <a
			class=" btn_exit_long bnt_exit_padding12"
			href="javascript:history.go(-1)">取消</a>
	</div>

	<jsp:include page="/comm/static-js.jsp"></jsp:include>
	<script type="text/javascript" src="<%=basePath %>/js/ajaxfileupload.js"></script>

	<script type="text/javascript">   
	var static_path="http://image.fangcang.com/upload/images/titanjr/loan_apply/${JR_USERID}/";
        $.ajax({
        	 type: "post",
             url: "<%=basePath%>/loan/loanApplyOrderNo.action",
             dataType: "json",
             success: function(data){
            	 if(data.code==1){
            		 $("#loanApplyOrderNo").val(data.data);
            		 static_path = static_path+$("#loanApplyOrderNo").val()+"/";
            	 }
            	}
            }); 
  
		var dateSelect = new datePickerAdd($("#dataS"), $("#dataE"), function(
				dateText, inst) {
		}, function(dateText, inst) { //结束日期回调            
		}, "", "", "", true);

  
		//放大tupian
		function bigImgShow() {
			var _index = 0;
			var leftBtn, rightBtn, ulDiv;
			$(".TFSimgOnBig")
					.find("img")
					.live(
							'click',
							function() {
								var _div = '<div tabindex="0" style=" position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; overflow: hidden; -webkit-user-select: none; z-index: 1024; background-color: rgba(0, 0, 0,0.7);" class="bigImgShow_box_bg"><span class="left_btn"></span><span class="right_btn"></span><div class="bigImgShow_box"><span class="close_btn"></span><ul>';
								_index = $(this).parents("dd").index();
								for (var i = 0; i < $(".TFSimgOnBig").find(
										"img").length; i++) {
									var img = $(".TFSimgOnBig").eq(i).find(
											"img").attr("src");
									if (i == _index) {
										_div += "<li class='cur MissionRoom_annexList_li'><img src='"+img+"'></li>"
									} else {
										_div += "<li class='MissionRoom_annexList_li'><img src='"+img+"'></li>"
									}
								}
								;
								_div += "</ul></div></div>";
								window.top.$("body").append(_div);
								leftBtn = window.parent.$(".left_btn");
								rightBtn = window.parent.$(".right_btn");
								var closeBtn = window.parent.$(".close_btn");
								var divId = window.parent
										.$(".bigImgShow_box_bg");
								ulDiv = window.parent.$(".bigImgShow_box")
										.find("ul");
								if (_index == 0) {
									leftBtn.hide();
								}
								;
								if (_index == $(".TFSimgOnBig").length - 1) {
									rightBtn.hide();
								}
								;
								leftBtn.on('click', function() {
									_index--;
									if (_index == 0) {
										leftBtn.hide();
									}
									;
									rightBtn.show();
									showImg();
								});
								rightBtn
										.on(
												'click',
												function() {
													_index++;
													if (_index == $(".TFSimgOnBig").length - 1) {
														rightBtn.hide();
													}
													;
													leftBtn.show();
													showImg();
												});
								closeBtn.live('click', function() {
									divId.remove();
								});
							});

			function showImg() {
				ulDiv.find(".MissionRoom_annexList_li").hide().eq(_index)
						.show();
			}
		};
		new bigImgShow();

		$(".TFSaddImg").on('click', function() {
			$(this).next("input").click();
		});
		/* $(".RC_c_dd input").change(function() {
			$(this).prev().addClass("hidden");
			$(this).parent().find(".TFSuploading").removeClass("hidden");
			loading($(this).parent().find(".TFSuploading"));
		}) */

		//删除
		$(".J_delete_upload").live('click', function() {
			$(this).parent().addClass("hidden").removeClass("TFSimgOnBig");
			$(this).parent().parent().find(".TFSaddImg").removeClass("hidden");
		});

		//重新上传
		$(".J_re_upload").on('click', function() {
			$(this).parent().parent().find('input').click();
		});
	

		  function loading(obj , callBack){
	           var l1=obj.find("span");
	           var l2=obj.find("i");
	           l2.html(0);
	           l1.css("width",0+"%");
	           var i=0;
	           var loadingJ=setInterval(function(){
	                l1.css("width",i+"%");
	                l2.html(i);
	                if(i==90){
	                    clearInterval(loadingJ);
	                }
	                i++;},50);
	           return loadingJ;
	       }
	       //OK 装B完成
	       function loadingOver(obj , callBack)
	       {
	    	   var l1=obj.find("span");
	           var l2=obj.find("i");
	           
	           var i=parseInt(l2.text());
	           
	           var loadingN=setInterval(function(){
	                 l1.css("width",i+"%");
	                 l2.html(i);
	                 if(i == 100){
	                     clearInterval(loadingN);
	                 }
	                 i++;},20);
	           
	           setTimeout(function(){
	        	  
	             obj.addClass("hidden");  
	             obj.parent().find(".TFSimgOn").removeClass("hidden").addClass("TFSimgOnBig");
	             l2.html(0);
	             
	             callBack();
	           },3000);
	       }
	       
		
		$(".RC_c_dd input").live("change",function(){
			var ids =  $(this).attr('id');
        	//开始装B走进度条了哦 
	        var loadingJ =  loading($(this).parent().find(".TFSuploading"));
	        
	        $(this).prev('.TFSaddImg').addClass("hidden");
	        $(this).parent().find(".TFSuploaderror").addClass("hidden");
       	 	$(this).parent().find(".TFSuploading").removeClass("hidden");
       	 	
       	    $.ajaxFileUpload({
   	        	url: '<%=basePath%>/loan/upload.shtml',
   	            secureuri: false, 
   	            fileElementId: $(this).attr('id'), 
   	            data:{loanApplyOrderNo:$("#loanApplyOrderNo").val(),fileName:ids},
   	            dataType: 'json', 
   	            success: function (result, status){
   	            	if(result.code==1)
   	            	{
	   	            	 if(loadingJ)
	   	            	 {
	   	            	  	clearInterval(loadingJ);
	   	            	 }
	   	            	 //假装让进度条走到100
	   	            	loadingOver($('#' + ids).parent().find(".TFSuploading") , function(){
	   	            		uploadSucess(ids , result.data);
	   	            	});
   					}
   	            	else
   	            	{alert("#"+ids+"_error");
   	            		$("#"+ids+"_error").text(result.msg);
   						uploadError(ids);
   					}
   	            },
   	            error: function (data, status, e){
   	            	uploadError(ids);
   	            },
   	        });
          });
		
		
		function uploadError(ids)
		{
			$('#'+ids).prev('.TFSaddImg').addClass("hidden");
			$('#'+ids).parent().find(".TFSuploading").addClass("hidden");
	    	$('#'+ids).parent().find(".TFSuploaderror").removeClass("hidden");
		}
		
		//这是一个附件上传之后的错误回调函数哦亲
		function handleError(s)
	    {
			uploadError(s.fileElementId);
	    }
		
		function uploadSucess(ids , fileName)
		{
		
			var index1=fileName.lastIndexOf(".");
			var index2=fileName.length;
			var postf=fileName.substring(index1+1,index2);//后缀名
			var imgList ="/png/jpg/jpeg/";
			
			$('#'+ids).parent().find('.TFSimgOnBig').find('img').unbind("click");
			$('#'+ids).parent().find('.TFSimgOn').removeClass('TFSimgOnBig');
			
			//如果是图片则支持其预览
			if(imgList.indexOf(postf.toLowerCase()) !=-1)
			{
				$('#'+ids).parent().find('.TFSimgOn').addClass('TFSimgOnBig');
				$('#'+ids).parent().find('.TFSimgOn').find('img').attr("src",static_path+fileName);
				bigImgShow();
			}else
			{
				$('#'+ids).parent().find('.TFSimgOn').find('img').attr("src",'<%=cssSaasPath%>/images/TFS/help_logo.jpg');
			
				$('#'+ids).parent().find('.TFSimgOn').find("img").click(function(){
					 window.open(static_path+fileName);
				 });
			}
			
		}
		
	</script>
</body>
</html>
