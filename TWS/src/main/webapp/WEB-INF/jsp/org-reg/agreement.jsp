<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<div class="sra_c ser_content">
<jsp:include page="/comm/content.jsp"></jsp:include>
</div>
<script type="text/javascript">
$(".ser_content").mCustomScrollbar({  		                  
   	scrollButtons:{  
            enable:true //是否使用上下滚动按钮  
       },      
    autoHideScrollbar: false, //是否自动隐藏滚动条  
    scrollInertia :0,//滚动延迟  
    horizontalScroll : false,//水平滚动条  
    callbacks:{  
    }  
});
</script>
