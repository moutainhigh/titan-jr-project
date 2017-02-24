<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/config/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="robots" content="noindex,nofollow" />
<link href="inc/style.css" rel="stylesheet" type="text/css" />
<title>页面错误信息</title>
<style type="text/css">
<!--
body {
background-image: url(images/064.jpg);
background-color: #f6f3e0;
}
-->
</style>
</head>

<body>
<div class="zhong fs20" style="margin:15px 0;">页面错误信息</div>
<div style="padding:0 30px;">
  ${errMessage}
</div>
</body>
</html>