package com.genius.shiro.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.shiro.bean.UserInfoQry;
import com.genius.shiro.dao.IUserInfoDao;
import com.genius.shiro.pojo.UserInfo;
import com.genius.shiro.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private IUserInfoDao iUserInfoDao;
    
    @Override
    public UserInfo findUserByToken(UserInfoQry token) {
        return this.iUserInfoDao.findUserByToken(token);
    }

    @Override
    public Set<String> getRoleByUsername(String userName) {
        return this.iUserInfoDao.getRoleByUsername(userName);
    }

    @Override
    public Set<String> getPermissionByUsername(String userName, int level) {
        return this.iUserInfoDao.getPermissionByUsername(userName, level);
    }

}
