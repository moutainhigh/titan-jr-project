package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by zhaoshan on 2016/5/9.
 */
public class LoginPasswordModifyRequest extends BaseRequestDTO {

    @NotNull
    private Integer tfsUserid;

    @NotEmpty
    private String originalPassword;

    @NotEmpty
    private String newPassword;

    public Integer getTfsUserid() {
        return tfsUserid;
    }

    public void setTfsUserid(Integer tfsUserid) {
        this.tfsUserid = tfsUserid;
    }

    public String getOriginalPassword() {
        return originalPassword;
    }

    public void setOriginalPassword(String originalPassword) {
        this.originalPassword = originalPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
