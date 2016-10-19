package com.genius.shiro.dao;

import java.util.Set;

import com.genius.shiro.bean.UserInfoQry;
import com.genius.shiro.pojo.UserInfo;

/**
 * @Description:授权用户dao
 * @author xiong.song
 * @date 2016年6月27日 下午4:21:45 
 */
public interface IUserInfoDao {
    
    /**
     * @Description：通过认证获取用户
     * @param token
     * @return 
     */
    UserInfo findUserByToken(UserInfoQry token);
    
    /**
     * @Description：通过用户名获取角色
     * @param userName
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