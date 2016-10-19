package com.genius.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.genius.shiro.bean.UserInfoQry;
import com.genius.shiro.pojo.UserInfo;
import com.genius.shiro.service.UserInfoService;

public class UserInfoRealm extends AuthorizingRealm{

    @Autowired
    private UserInfoService userInfoService;
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username= (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo= new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(this.userInfoService.getRoleByUsername(username));
        authorizationInfo.addStringPermissions(this.userInfoService.getPermissionByUsername(username, 0));
        authorizationInfo.addStringPermissions(this.userInfoService.getPermissionByUsername(username, 1));
        authorizationInfo.addStringPermissions(this.userInfoService.getPermissionByUsername(username, 2));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username= (String)token.getPrincipal();
        String password= String.valueOf((char[])token.getCredentials());
        UserInfoQry qrytoken= new UserInfoQry();
        qrytoken.setUserName(username);
        qrytoken.setUserPassword(password);
        UserInfo  userInfo= this.userInfoService.findUserByToken(qrytoken);
        if (userInfo== null ) {
            throw new UnknownAccountException();
        }
        
        if (Boolean.TRUE.equals(userInfo.getLocked())) {
            throw new LockedAccountException();
        }
        
        SimpleAuthenticationInfo authenticationInfo=
                new SimpleAuthenticationInfo(username, password, this.getName());
        
        return authenticationInfo;
    }

}
