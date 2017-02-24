package com.fangcang.titanjr.dto.bean;

import java.util.Date;

/**
 * Created by zhaoshan on 2016/5/11.
 */
public class TitanRoleDTO {

    private Long roleid;
    private String rolecode;
    private Long fcroleid;
    private String rolename;
    private String roleremark;
    private String creator;
    private Date createtime;

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public String getRolecode() {
        return rolecode;
    }

    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }

    public Long getFcroleid() {
        return fcroleid;
    }

    public void setFcroleid(Long fcroleid) {
        this.fcroleid = fcroleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRoleremark() {
        return roleremark;
    }

    public void setRoleremark(String roleremark) {
        this.roleremark = roleremark;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
