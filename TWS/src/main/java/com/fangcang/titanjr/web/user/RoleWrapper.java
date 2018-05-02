package com.fangcang.titanjr.web.user;

import java.util.Collection;
import java.util.Iterator;

import com.fangcang.util.StringUtil;

public class RoleWrapper {
	
	/**
	 * 角色
	 */
//	private List<RoleDTO> roleList;
	
	private String roleListIdStr;
	
	private String roleListNameStr;
	
	private boolean isPlatformManager=false;
	
	private boolean isManager=false;
	
	private boolean isReservationist=false;
	
	private boolean isTreasurer=false;
	
	private boolean isProducter=false;
	
	private boolean isOperater=false;
	
//	public void setRoleList(List<RoleDTO> roleList) {
//		this.roleList=roleList;
//		for(int i=0;i<roleList.size();i++){
//			if(roleList.get(i).getId()==322){
//				isPlatformManager=true;
//			}else if(roleList.get(i).getId()==323){
//				isManager=true;
//			}else if(roleList.get(i).getId()==324){
//				isReservationist=true;
//			}else if(roleList.get(i).getId()==325){
//				isTreasurer=true;
//			}else if(roleList.get(i).getId()==326){
//				isProducter=true;
//			}else if(roleList.get(i).getId()==327){
//				isOperater=true;
//			}
//		}
//	}
	
	public void setRoleList(Collection<String> roleList) {
		Iterator iterator=roleList.iterator();
		while(iterator.hasNext()){
			String role=(String)iterator.next();
			if("322".equals(role)){
		        isPlatformManager=true;
			}else if("323".equals(role)){
				isManager=true;
			}else if("324".equals(role)){
				isReservationist=true;
			}else if("325".equals(role)){
				isTreasurer=true;
			}else if("326".equals(role)){
				isProducter=true;
			}else if("327".equals(role)){
				isOperater=true;
			}
		}
	}
	
//	public List<RoleDTO> getRoleList() {
//		return roleList;
//	}

	public String getRoleListIdStr() {
		if(isManager){
			roleListIdStr="323";
		}
		
		if(isReservationist && !StringUtil.isValidString(roleListIdStr)){
			roleListIdStr="324";
		}else if(isReservationist){
			roleListIdStr += ",324";
		}
		
		if(isTreasurer && !StringUtil.isValidString(roleListIdStr)){
			roleListIdStr="325";
		}else if(isTreasurer){
			roleListIdStr += ",325";
		}
		
		if(isProducter && !StringUtil.isValidString(roleListIdStr)){
			roleListIdStr="326";
		}else if(isProducter){
			roleListIdStr += ",326";
		}
		
		if(isOperater && !StringUtil.isValidString(roleListIdStr)){
			roleListIdStr="327";
		}else if(isOperater){
			roleListIdStr += ",327";
		}
		return roleListIdStr;
	}

	public String getRoleListNameStr(){
		if(isPlatformManager){
			roleListNameStr="平台管理员";
		}
		
		if(isManager && !StringUtil.isValidString(roleListNameStr)){
			roleListNameStr="管理员";
		}else if(isManager){
			roleListNameStr +=",管理员";
		}
		
		if(isReservationist && !StringUtil.isValidString(roleListNameStr)){
			roleListNameStr="预订员";
		}else if(isReservationist){
			roleListNameStr += ",预订员";
		}
		
		if(isTreasurer && !StringUtil.isValidString(roleListNameStr)){
			roleListNameStr="财务员";
		}else if(isTreasurer){
			roleListNameStr += ",财务员";
		}
		
		if(isProducter && !StringUtil.isValidString(roleListNameStr)){
			roleListNameStr="产品员";
		}else if(isProducter){
			roleListNameStr += ",产品员";
		}
		
		if(isOperater && !StringUtil.isValidString(roleListNameStr)){
			roleListNameStr="运营员";
		}else if(isOperater){
			roleListNameStr += ",运营员";
		}
		return roleListNameStr;
	}

	public boolean getIsPlatformManager() {
		return isPlatformManager;
	}

	public boolean getIsManager() {
		return isManager;
	}

	public boolean getIsReservationist() {
		return isReservationist;
	}

	public boolean getIsTreasurer() {
		return isTreasurer;
	}

	public boolean getIsProducter() {
		return isProducter;
	}

	public boolean getIsOperater() {
		return isOperater;
	}
}
