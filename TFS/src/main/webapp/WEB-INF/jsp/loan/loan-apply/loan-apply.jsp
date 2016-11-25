<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
         <div class="main_return fl"><a href="javascript:history.go(-1)"><i></i>返回上一页</a></div>
        <div class="history fl">我的贷款 >  申请包房贷款</div>
    </div>   
</div>
<div class="Refund clearfix">
	<div class="R_title"><i></i>申请包房贷款</div>
	<div class="R_c clearfix">		
		<!-- <div class="RC_t">
      <ul>
        <li>贷款编号：D13245678913264</li>        
      </ul>
    </div> -->

		<div class="RC_c clearfix">
			<ul>
				<li class="clearfix"><div class="tit">贷款金额</div>
					<i class="c_666 f_20"><input name="amount" id="amount" class="text w_250  c_666 " placeholder="" type="text" value=""></i> 
					<i class="c_666 ">元</i> <i class="c_999 m_l20">贷款最长期限为90天，日费率0.5‰，到期日前随借随还</i>
				</li>
				<li class="clearfix J_Section"><div class="tit">酒店信息</div>
          <div class="w_290"><i class="c_f00">*</i>酒店名称：<input name="hotelName" id="hotelName" class="text w_184 c_666 " placeholder="" type="text" value=""></div>
          <div class="w_360"><i class="c_f00">*</i>包房时段：
           <input type="text" class="text w_120 text_calendar" name="beginDate" id="dataS" readonly="readonly" placeholder="请选择日期">
           至
           <input type="text" class="text w_120 text_calendar" name="endDate" id="dataE"  readonly="readonly" placeholder="请选择日期">
          </div>
          <div class="w_400"><i class="c_f00">*</i>包房数量：<input name="roomNights" id="roomNights" class="text w_120 c_666" placeholder="" type="text" value="">
          间夜
          </div>
				</li>
        <li class="clearfix"><div class="tit">收款信息</div>
          <div class="w_290"><i class="c_f00">*</i>账户名：<input name="accountName" id="accountName" class="text w_184 c_666 m_l10" placeholder="" type="text" value=""></div>
          <div class="w_360"><i class="c_f00">*</i>银行卡号：<input name="account" id="account" class="text w_265 c_666" placeholder="" type="text" value=""></div>
          <div class="w_400"><i class="c_f00">*</i>开户行：
              <select class="select b_fff m_l10 w_150" name="bank" id="bank">
                <option value="0">开户行</option>
                <option value="1">开户行</option>
                <option value="2">开户行</option>
              </select>
          </div>
           <input type="hidden" class="text w_250  c_666 " name="contactNames" id="contactNames" >
        </li>
        <li class="clearfix p_l83"><div class="tit">包房合同</div>
        <div class="p_l25">请至少上传一张包房合同附件，附件格式支持PDF、JPG、JPEG、PNG、ZIP、RAR，大小不超过5M</div>
        <dl>
          <dd class="RC_c_dd">             
            <div class="TFSaddImg hidden"></div>
            <input type="file">
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
            <div class="TFSimgOn TFSimgOnBig">
                <i class="J_delete_upload loanInformation_upload_btn">删除</i>
                <div class="dd_img"><img src="../images/TFS/LH01.jpg">
                </div>
                <div class="dd_text" title="附件附件">附件附件.jpg</div>
            </div>
          </dd>
          <dd class="RC_c_dd">         
            <div class="TFSaddImg"></div>
            <input type="file">
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
                <div class="dd_img"><img src="../images/TFS/help_logo.jpg">          
                </div>          
                <div class="dd_text" title="附件附件">附件附件.jpg</div>                
            </div>
          </dd> 
          <dd class="RC_c_dd">         
            <div class="TFSaddImg"></div>
            <input type="file">
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
                <div class="dd_img"><img src="../images/TFS/LH01.jpg">          
                </div>          
                <div class="dd_text" title="附件附件">附件附件.jpg</div>                
            </div>
          </dd> 
          <dd class="RC_c_dd">         
            <div class="TFSaddImg hidden"></div>
            <input type="file">
            <div class="TFSuploading TFSupload_ZIP hidden">
                 <p class="TFSuploading1">
                     <span></span>
                 </p>    
                 <p class="TFSuploading2">
                     已上传<i>0</i>%
                 </p>                                
            </div>
            <div class="TFSuploaderror ">
                <i class="J_re_upload loanInformation_upload_btn">重新上传</i>
                <p>上传失败</p>
            </div>
            <div class="TFSimgOn hidden">
                <i class="J_delete_upload loanInformation_upload_btn">删除</i>
                <div class="dd_img"><img src="../images/TFS/LH01.jpg">          
                </div>          
                <div class="dd_text" title="附件附件">附件附件.jpg</div>                
            </div>
          </dd> 
        </dl>       
        </li>
			</ul>
		</div>
	</div>
</div>	
	
	

<div style="height: 60px"></div>
<div class="TFS_data_button">	
	<a class="btn btnNext" href="泰坦金融-我的贷款首页-包房贷款申请已提交.html">提交申请</a>
    <a class=" btn_exit_long bnt_exit_padding12" href="javascript:history.go(-1)">取消</a>
</div>

<jsp:include page="/comm/static-js.jsp"></jsp:include>

<script type="text/javascript">   
  //日历
  var dateSelect=new datePickerAdd($("#dataS"),$("#dataE"),function(dateText,inst){ 
         },function(dateText,inst){ //结束日期回调            
      },"","","",true);


  $(function(){
      function bigImgShow(){              
           var _index=0; 
           var leftBtn,rightBtn,ulDiv;
            $(".TFSimgOnBig").find("img").live('click',function(){
            var _div='<div tabindex="0" style=" position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; overflow: hidden; -webkit-user-select: none; z-index: 1024; background-color: rgba(0, 0, 0,0.7);" class="bigImgShow_box_bg"><span class="left_btn"></span><span class="right_btn"></span><div class="bigImgShow_box"><span class="close_btn"></span><ul>';     
            _index=$(this).parents("dd").index();
            for(var i=0;i< $(".TFSimgOnBig").find("img").length;i++){
                var img=$(".TFSimgOnBig").eq(i).find("img").attr("src");
                 if(i==_index){
                   _div+="<li class='cur MissionRoom_annexList_li'><img src='"+img+"'></li>" 
                }else{
                   _div+="<li class='MissionRoom_annexList_li'><img src='"+img+"'></li>"  
                }                              
            };
            _div+="</ul></div></div>";
            window.top.$("body").append(_div);
            leftBtn=window.parent.$(".left_btn");
            rightBtn=window.parent.$(".right_btn");
            var closeBtn=window.parent.$(".close_btn");
            var divId=window.parent.$(".bigImgShow_box_bg");
            ulDiv=window.parent.$(".bigImgShow_box").find("ul");
            if(_index==0){
                leftBtn.hide();
            };
            if(_index==$(".TFSimgOnBig").length-1){
                rightBtn.hide();
            };        
            leftBtn.on('click',function(){
               _index--;
               if(_index==0){
                 leftBtn.hide();
               };
               rightBtn.show();
               showImg();
           }); 
           rightBtn.on('click',function(){                 
               _index++;
               if(_index==$(".TFSimgOnBig").length-1){
                 rightBtn.hide();
               };
               leftBtn.show();
               showImg();
           });  
           closeBtn.live('click',function(){                 
              divId.remove();
           });          
        });
           
           function showImg(){
            ulDiv.find(".MissionRoom_annexList_li").hide().eq(_index).show(); 
           } 
         };   
         new bigImgShow(); 
        $(".TFSaddImg").on('click',function(){
            $(this).next("input").click();           
        });
        $(".RC_c_dd input").change(function(){
            $(this).prev().addClass("hidden");
            $(this).parent().find(".TFSuploading").removeClass("hidden");
            loading($(this).parent().find(".TFSuploading"));
        })
    
       //删除
       $(".J_delete_upload").live('click',function(){
            $(this).parent().addClass("hidden").removeClass("TFSimgOnBig");
            $(this).parent().parent().find(".TFSaddImg").removeClass("hidden");
       });

        //重新上传
        $(".J_re_upload").on('click',function(){
            $(this).parent().parent().find('input').click();           
        });
        $(".RC_c_dd input").change(function(){
            $(this).parent().addClass("hidden");
            $(this).parent().parent().find(".TFSuploading").removeClass("hidden");
            loading($(this).parent().parent().find(".TFSuploading"));
        });


       function loading(obj){
           var l1=obj.find("span");
           var l2=obj.find("i");
           var i=0;
           var loadingJ=setInterval(function(){
                l1.css("width",i+"%");
                l2.html(i);
                if(i==90){
                    clearInterval(loadingJ);
                }
                i++;},20); 
           setTimeout(function(){
             var loadingN=setInterval(function(){
                l1.css("width",i+"%");
                l2.html(i);
                if(i==100){
                    clearInterval(loadingN);
                }
                i++;},20);
                             
           },2500);
           setTimeout(function(){
             obj.addClass("hidden");  
             obj.parent().find(".TFSimgOn").removeClass("hidden").addClass("TFSimgOnBig");
           },3000);
       }
    })
</script>
</body>
</html>
