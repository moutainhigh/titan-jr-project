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
	<div class="S_popup_title">
		<ul>
			<li class="P_left"></li>
			<li class="P_centre" style="padding:0 30px;">请输入付款密码</li>
			<li class="P_right"></li>
		</ul>
	</div>
	<div class="S_popup_content" style="width: 400px; padding: 0 15px;">
		<div class="password_set">
			<div class="press_pass"></div>
			<ul class="passwordset_u1" style="padding:20px 0 20px 20px; margin-bottom:0px;">
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
	</div>
</div>

<script type="text/javascript">
var PasswordStr2=new sixDigitPassword("passwordbox");
</script>