<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>付款密码-泰坦金融</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
</head>
  
  <body>
  <div class="S_popup clearfix S_popTop ">
  <div class="S_popup_title">
    <ul>
      <li class="P_left"></li>
      <li class="P_centre" style="padding:0 50px;">修改付款密码</li>
      <li class="P_right"></li>
    </ul>
  </div>
  <div class="S_popup_content1" style="top:0px; padding:0 20px; width: 500px;">
  	<div class="password_set">
			<ul class="passwordset_u1 clearf">
				<li>
					<span class="pass_name">原密码：</span>
					<div class="sixDigitPassword" id="passwordbox5"> 
						<i><b></b></i>  
						<i><b></b></i> 
						<i><b></b></i>
						<i><b></b></i>
						<i><b></b></i>
						<i><b></b></i>
						<span></span>
					</div>
				</li>
				<li>
					<span class="pass_name">新密码：</span>
					<div class="sixDigitPassword" id="passwordbox6"> 
						<i><b></b></i>  
						<i><b></b></i> 
						<i><b></b></i>
						<i><b></b></i>
						<i><b></b></i>
						<i><b></b></i>
						<span></span>
					</div>
				</li>
				<li>
					<span class="pass_name">确认密码：</span>
					<div class="sixDigitPassword" id="passwordbox7"> 
						<i><b></b></i>  
						<i><b></b></i> 
						<i><b></b></i>
						<i><b></b></i>
						<i><b></b></i>
						<i><b></b></i>
						<span></span>
					</div>
				</li>
			</ul>
		</div> 	
  </div>
</div>
<!--弹窗白色底--> 
<script>
//密码输入框
var PasswordStr3=null;
var PasswordStr4=null;
var PasswordStr5=null;
$("document").ready(function (){
	PasswordStr3=new sixDigitPassword("passwordbox5");
	PasswordStr4=new sixDigitPassword("passwordbox6");
	PasswordStr5=new sixDigitPassword("passwordbox7");
});

</script>
</body>
</html>
