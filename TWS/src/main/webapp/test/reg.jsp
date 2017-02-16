<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>第三方测试接口页面</title>
  </head>
  
  <body>
   <a href="javascript:;" onclick="regThird()">第三方注册链接</a>
   <script type="text/javascript">
   
   function regThird(){
		var url="http://local.fangcang.com:8020/TWS/ex/organ/showOrgUser.shtml?channel=MGKD6EP5&encrypt_type=RSA&info=CUxNZhU1lrFgzBZsN6iVqN0xePpF*V6hRR-lqqU7sTSpVJymXvNU2*9fv0*iAZwIL5R5fJ5-42Nz6WFhdA9RHvvTF8IUDQfxRaOSgzsf4xYJ1AgRoJzm5psR4yy4SE-ECvUqKoOYhVtAG7mDAV*uDPMy6niacpkhPQ-4aH8otZx6lOXEADjwTjgdlSOQd33M4wg1FM-Gxm*K8JJZ6XJ8FPRwx6vIPpLvTmcjjhm-T04BUf4q-SAuLlGP6uWfvt0a5Nzcs86bPszVhiWlS2VQvjkt89CCGuIfNhtdldlDrSXU2DFMC6ILLvNO8fsFCiRiusjYXMmgOFVzM5-Z*OS1BQ==&sign=E40DCF31206AF5F3C51094638790D69D";
   		location.href=url;
   }
   
   </script>
  </body>
</html>
