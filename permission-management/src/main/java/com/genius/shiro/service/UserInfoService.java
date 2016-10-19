package com.genius.shiro.service;

import java.util.Set;

import com.genius.shiro.bean.UserInfoQry;
import com.genius.shiro.pojo.UserInfo;

public interface UserInfoService {

    /**
     * @Description：通过认证获取用户
     * @param token
     * @return
     */
    UserInfo findUserByToken(UserInfoQry token);
    
    /**
     * @Description：通过用户名获取角色
     * @param userId
     * @param locked
     * @return
     */
    Set<String> getRoleByUsername(String userName);
    
    /**
     * @Description：通过用户名获取权限
     * @param userName
     * @param level
     * @return
     * @throws Throwable
     */
    Set<String> getPermissionByUsername(String userName, int level);
}
