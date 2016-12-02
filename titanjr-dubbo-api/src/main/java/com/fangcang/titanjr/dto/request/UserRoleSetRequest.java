package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

import java.util.List;
import java.util.Map;

/**
 * 用户角色设置请求
 * Created by zhaoshan on 2016/4/26.
 */
public class UserRoleSetRequest extends BaseRequestDTO{
    /**
     * map的key是用户id，value是角色id的列表
     */
    private Map<Long, List<Long>> userRoleIdMap;
    /**
     * 需要删除的用户角色
     */
    private Map<Long, List<Long>> deleteUserRoleIdMap;
    
    public Map<Long, List<Long>> getUserRoleIdMap() {
        return userRoleIdMap;
    }

    public void setUserRoleIdMap(Map<Long, List<Long>> userRoleIdMap) {
        this.userRoleIdMap = userRoleIdMap;
    }

	public Map<Long, List<Long>> getDeleteUserRoleIdMap() {
		return deleteUserRoleIdMap;
	}

	public void setDeleteUserRoleIdMap(Map<Long, List<Long>> deleteUserRoleIdMap) {
		this.deleteUserRoleIdMap = deleteUserRoleIdMap;
	}
    
}
