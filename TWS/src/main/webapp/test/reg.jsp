<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>测试接口页面</title>
  </head>
  
  <body>
   <a href="javascript:;" onclick="regThird()">第三方注册链接</a>
   <br/>
   <a href="javascript:;" onclick="proxyLogin()">管理员代理登录</a>
   <script type="text/javascript">
   //第三方注册
   function regThird(){
		var url="http://local.luoqinglong.com:8020/TWS/ex/organ/showOrgUser.shtml?channel=MGKD6EP5&encrypt_type=RSA&info=WefpbsnMr7NQqeoE73zf8hT9nDiPbR1fkAKaZP4M6H3Zq9AiTSTgzrTZrxh0TO0ycx78zSV0Yo6a7E4IvXrdF52K0pTDEfysiS1rATCKHb8QuuieKoDJOobpwlVWvZeD3glwI1TS0CYhllK7YBqp7hcJ6UyejMwdakAx9lkuxZg-4yuJPRw*j7Wqx09BxGrbEBETiYnjyx1tb6MMoGsSpIYQd8ZOe9tDlULab8tSCpTf5qQYu8klbuCMnDZFGcsTRy66OoNqRStrxqtALB5KWhoAWziqsgAC3wZWd1*sbgjFUD*BSQALdxW9684ns13CB*Zqf83dTKOHPCao7ZN1Bw==&sign=6A344455AC578E71B48B603EC23AF687";
   		location.href=url;
   }
   //代理登录
   function proxyLogin(){
	   var url="http://local.luoqinglong.com:8020/TWS/ex/proxyLogin.shtml?channel=MGKD6EP5&encrypt_type=RSA&info=GqTkJnYkCRIq3m9*DOSPlcp5JssnV*kJfLCiMlUsnHplSuYP1ghVF6I9BbL5cFlxqzmbvqlk5Y9TmEWkmEgbKK8gAE3AMXRFhbY0qPidbC2lEmbXBw8abUfeCBapLsSjDw3QGNSlWvlKU2nJQSaOZs*JP-ouiepNXiF6Y45mVTk=&jumpurl=http%3A%2F%2Flocal.luoqinglong.com%3A8020%2FTWS%2Faccount%2Foverview-main.shtml&reqtime=1487559602507&sign=D6129FD466E8A5F626714B4B3412B76D";
	   location.href=url;
   }
   
   </script>
  </body>
</html>
