package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

import java.util.List;

/**
 *
 * Created by zhaoshan on 2016/5/11.
 */
public class TitanRoleQueryRequest extends BaseRequestDTO{

    private List<String> roleCodes;
    private Long fcRoleId;
    private List<String>roleids;

    public List<String> getRoleCodes() {
        return roleCodes;
    }

    public void setRoleCodes(List<String> roleCodes) {
        this.roleCodes = roleCodes;
    }

    public Long getFcRoleId() {
        return fcRoleId;
    }

    public void setFcRoleId(Long fcRoleId) {
        this.fcRoleId = fcRoleId;
    }

	public List<String> getRoleids() {
		return roleids;
	}

	public void setRoleids(List<String> roleids) {
		this.roleids = roleids;
	}
}
