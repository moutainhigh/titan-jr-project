package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.UserInfoDTO;

import java.util.List;

/**
 * Created by zhaoshan on 2016/4/25.
 */
public class UserInfoResponse extends BaseResponseDTO{

	private List<UserInfoDTO> userInfoDTOList;

    public List<UserInfoDTO> getUserInfoDTOList() {
        return userInfoDTOList;
    }

    public void setUserInfoDTOList(List<UserInfoDTO> userInfoDTOList) {
        this.userInfoDTOList = userInfoDTOList;
    }

}
