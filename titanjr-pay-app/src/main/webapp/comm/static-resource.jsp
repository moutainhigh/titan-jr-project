<!-- common css-->
<%@ include file="/comm/path-param.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty CURRENT_THEME  }"><c:set var="CURRENT_THEME" value="Crimson" scope="session"></c:set></c:if>
<link rel="stylesheet" href="<%=cssSaasPath%>/css/fangcang.min.css">
 <link rel="stylesheet" href="<%=cssSaasPath%>/css/jquery-ui-1.9.2.custom.css" >
 <link rel="stylesheet" href="<%=cssSaasPath%>/css/style.css">
<link href="<%=cssSaasPath%>/themes/${CURRENT_THEME}/css/style.css" rel="stylesheet" type="text/css">



