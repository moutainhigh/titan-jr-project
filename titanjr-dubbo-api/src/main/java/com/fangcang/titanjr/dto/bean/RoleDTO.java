package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

/**
 * 角色实体
 * Created by zhaoshan on 2016/5/12.
 */
public class RoleDTO  implements Serializable {

    private Long roleId;
    private String roleCode;
    private Long fcRoleId;
    private String roleName;
    private String roleRemark;
    private Integer isActive;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Long getFcRoleId() {
        return fcRoleId;
    }

    public void setFcRoleId(Long fcRoleId) {
        this.fcRoleId = fcRoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleRemark() {
        return roleRemark;
    }

    public void setRoleRemark(String roleRemark) {
        this.roleRemark = roleRemark;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
}
