<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!-- 新增员工权限 -->
<div class="S_popup clearfix S_popTop" style=" height: 400px; width: 800px; ">
  <div class="S_popup_title">
    <ul>
      <li class="P_left"></li>
      <li class="P_centre" style="padding:0 50px;">新增员工权限</li>
      <li class="P_right"></li>
    </ul>
  </div>
  <div class="S_popup_content1" style="top:0px;">
    <div class="adjust_c">
      <div class="h_30 p_b10 " >
        <table width="100%">
          <colgroup>
          <col width="150">
          <col width="78">
          <col width="">
          </colgroup>
          <tr>
            <td><input  id="saasUserName" type="text" class="text w_150 c_666 m_r10" placeholder="用户姓名："/></td>
            <td><span class="settingBtn" id="list_search"><i class="settingBtnIcon serchIcon "></i>查询</span></td><td></td>                     
          </tr>
        </table>
      </div>
      <div class="g_Tab bt_96d ">
        <table width="100%" >
          <colgroup>
          <col width="230">
          <col width="100">
          <col width="150">
          <col width="170">
          <col width="">
          </colgroup>
          <thead>
            <tr>
              <th class="tdl"><div class="ml10">登录名</div></th>
              <th class="tdl">用户姓名</th>
              <th class="tdl">手机</th>
              <th class="tdl">角色</th>
              <th class="tdl"><div class="p_l30">操作</div></th>
            </tr>
          </thead>
        </table>
        <div style="height:200px; overflow:auto">
          <table width="100%" id="J_table1_3">
            <colgroup>
            <col width="230">
            <col width="100">
            <col width="150">
            <col width="170">
            <col width="">
            </colgroup>
            <tbody id="show_fc_employee_list_table">
               
            </tbody>
          </table>
        </div>
      </div>
      <div style="padding:2px;" class="but_next"></div>
    </div>

  </div>
</div>
<!--弹窗白色底--> 

<script>
$(function(){
	load();
	$("#list_search").on("click",load);
});

function load(){
	top.F.loading.show();
	var saasUserName = $("#saasUserName").val();
	
	$.ajax({
		type:"post",
		url:"<%=basePath%>/setting/fc-employee-table.shtml",
		data:{"saasUserName":saasUserName},
		dataType:"html",
		success:function(result){
			$("#show_fc_employee_list_table").html(result);
		},
		complete:function(){
			top.F.loading.hide();
		},
		error:function(xhr,status){
			 new top.Tip({msg : '请求失败，请重试', type: 2});
		}
	});
}


 

</script>