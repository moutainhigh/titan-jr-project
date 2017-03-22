package com.fangcang.titanjr.web.util;


import java.io.Serializable;

import com.fangcang.titanjr.web.user.UserWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fangcang.security.domain.User;
import com.fangcang.util.MyBeanUtil;

/**
 * Created by zhaoshan on 2016/5/16.
 */
public class TranslateUtil implements Serializable {
    private static final Log log = LogFactory.getLog(TranslateUtil.class);

    /**
     * 把登录权限用户User对象转换成UserWrapper
     *
     * @param user
     * @return UserWrapper
     */
    public static UserWrapper translateUser(User user) {
        if (null == user) {
            return null;
        }
        UserWrapper userWrapper = new UserWrapper();
        MyBeanUtil.copyProperties(userWrapper, user);
        userWrapper.setRoles(user.getRoles());

        return userWrapper;
    }
}
