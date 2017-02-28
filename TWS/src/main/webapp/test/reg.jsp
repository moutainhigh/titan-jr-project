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
		var url="http://local.luoqinglong.com:8020/TWS/ex/organ/showOrgUser.shtml?channel=MGKD6EP5&encrypt_type=RSA&info=Rtmn8aUyR3w4L0VpAXkOyeLtVjuTS2yui7ZrLr569v*P1Ftw8jf7tIWic*LsETfpolhbbZcpz0Y967PvqPIPdXVNWYgOJKhx-Q8PtWJlnDo6CxYca0EUUZOSArYJB8R*LlKe2nc2JeWcj1oyT9EfX5qQueAappQeiQCBKCTXIew5T5q7DumKpziqXHcaymgRlNxrdFZJZNga4FQUXyDx2lOMAQxOtPjeySV8B8reXMTeNdBEjxPYA9jlXfm6enLzUd63q87PQkiFdX7J552eVn93TeG5Fu6ZSuMZ*PSfAQtyRvb0Uo6YgWzhkCv6XjIVV7T3HbDzJGKDcOXvgmbi3A==&sign=890FEE5BCDDBF267820FF6C4EAED06AA";
   		location.href=url;
   }
   //代理登录
   function proxyLogin(){
	   var url="http://local.luoqinglong.com:8020/TWS/ex/proxyLogin.shtml?channel=MGKD6EP5&encrypt_type=RSA&info=IOL9Nj77O*sHtWHVvKSOj4Ad9GQRr3cMBCBbgbJ8JklrW4X3H1pxVwtCRf2*HLVk1PJxqmZT-b1vkukR37nwddtr3-jH2aj8oypCosB3QePxKoFLlXv80Sey7UTzQS0Ecj63Dijc6j5i0lvBpGEldfNPvsriN5evIJ8mOJtwWaE=&jumpurl=http%3A%2F%2Flocal.luoqinglong.com%3A8020%2FTWS%2Faccount%2Foverview-main.shtml&reqtime=1488253576144&sign=91C341C88A56D0E243588215200073BD";
	   location.href=url;
   }
   
   </script>
  </body>
</html>
