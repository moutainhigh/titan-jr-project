<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!--绑定SAAS-->
<!--弹窗白色底-->
<div class="S_popup clearfix S_popTop" style="width:700px;">
  <div class="S_popup_title">
    <ul>
      <li class="P_left"></li>
      <li class="P_centre" style="padding: 0 100px;">绑定SAAS</li>
      <li class="P_right"></li>
    </ul>
  </div>
  
<div class="S_popup_content adjust_c">
  <div class="binding ">
  <div class="b_l"><p>${merchantName}(${merchantCode})</p></div>
    <div class="b_z"><i class="tfs_ico"></i></div>
    <div class="b_r">
      <ul>
        <li><div class="b_r_t">泰坦金融用户名：</div><input type="text" id="id_username" class="text w_230 " name="" ></li>
        <li><div class="b_r_t">泰坦金融登录密码：</div><input type="password" id="id_password" class="text w_230 " name="" ></li>
      </ul>
    </div>
  </div>
  <div class="but_next" style="padding:2px;"></div>
  </div>
  
</div>