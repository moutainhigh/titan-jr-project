<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="S_popup clearfix">
	<div class="S_popup_title">
		<ul>
			<li class="P_left"></li>
			<li class="P_centre" style="padding:0 100px;">设置付款密码</li>
			<li class="P_right"></li>
		</ul>
	</div>
	<div class="S_popup_content" style="width: 500px; padding: 0 15px;">
		<div class="password_set">
			<div class="password_tip">您尚未设置付款密码，请先设置您的付款密码。</div>
			<ul class="passwordset_u1 clearf" style="margin-bottom:0px;">
				<li>
					<span class="pass_name">密码：</span>
					<div class="sixDigitPassword" id="passwordbox"> 
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
					<div class="sixDigitPassword" id="passwordbox1"> 
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
<script type="text/javascript">
var PasswordStr=new sixDigitPassword("passwordbox");
var PasswordStr1=new sixDigitPassword("passwordbox1");
</script>