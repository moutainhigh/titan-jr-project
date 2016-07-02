package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.TitanRoleDTO;

import java.util.List;

/**
 * Created by zhaoshan on 2016/5/11.
 */
public class TitanRoleResponse extends BaseResponseDTO {

    private List<TitanRoleDTO> titanRoleDTOList;

    public List<TitanRoleDTO> getTitanRoleDTOList() {
        return titanRoleDTOList;
    }

    public void setTitanRoleDTOList(List<TitanRoleDTO> titanRoleDTOList) {
        this.titanRoleDTOList = titanRoleDTOList;
    }
}
