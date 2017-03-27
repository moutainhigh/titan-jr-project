<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	if(request.getCookies() != null)
	{
		for(int i=0;i<request.getCookies().length;i++)
		{
			if("JSESSIONID".equals(request.getCookies()[i].getName()))
			{
				Cookie killMyCookie = new Cookie("JSESSIONID", request.getCookies()[i].getValue());
				killMyCookie.setHttpOnly(true);
				killMyCookie.setPath("/");
				response.addCookie(killMyCookie);
				response.getHeaderNames();
			}
		}
	}
%>
<!-- 输入付款密码 -->
<div class="S_popup clearfix">	
	<div class="S_popup_content" style="width: 400px; padding: 0 15px;position: relative;">
		<div class="password_set">
			<div class="press_pass"></div>
			<ul class="passwordset_u1" style="padding:50px 0 50px 20px; margin-bottom:0px;">
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
			</ul>
		</div>
		<div style="color:#f00;position: absolute;top: 95px;left: 110px;display:none;" class="org-info-pwd-error">请输入6位付款密码</div>
	</div>
</div>

<script type="text/javascript">
var PasswordStr2=new sixDigitPassword("passwordbox");
</script>