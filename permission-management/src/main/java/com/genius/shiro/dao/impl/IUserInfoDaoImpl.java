package com.genius.shiro.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.genius.shiro.bean.UserInfoQry;
import com.genius.shiro.dao.IUserInfoDao;
import com.genius.shiro.pojo.UserInfo;
import com.genius.shiro.utils.BaseDao;

@Repository
public class IUserInfoDaoImpl extends BaseDao implements IUserInfoDao {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public UserInfo findUserByToken(UserInfoQry token) {
      String sql= " "
                  + " select user_id, locked from user_info where user_name=  ? and user_password= ? ";
      List<UserInfo> result= this.jdbcTemplate.query(sql, new BeanPropertyRowMapper(UserInfo.class), new Object[]{token.getUserName(), token.getUserPassword()});
      if (result.isEmpty()) {
          return null;
      }
      return result.get(0);
    }

    @Override
    public Set<String> getRoleByUsername(String userName) {
        String sql= " "
                + " select temp.role_name from "
                + " (SELECT r.role_name, u.user_name, u.locked, r.available "
                + " FROM "
                + " user_info u "
                + " left JOIN user_role_relation urr ON  u.user_id = urr.user_id "
                + " LEFT JOIN role_info r on r.role_id= urr.role_id "
                + " HAVING u.user_name=? and u.locked=0 and r.available=0) temp ";
        List<String> result= this.jdbcTemplate.queryForList(sql, String.class, new Object[]{userName});
        return new HashSet<String>(result);
    }

    @Override
    public Set<String> getPermissionByUsername(String userName, int level) {
        String sql="";
        if (level== 0) {
                sql =  " select CONCAT(temp.sp_url,\":\", temp.mp_url,\":\", temp.dpe_url) from "
                    +  " (SELECT "
                    +  "    u.user_id, "
                    +  "    u.user_name, "
                    +  "    u.locked, "
                    +  "    r.role_id, "
                    +  "    r.role_name, "
                    +  "    r.available, "
                    +  "    sp.sys_id, "
                    +  "    sp.sys_name, "
                    +  "    sp.available as sp_available, "
                    +  "    sp.privilege_url as sp_url, "
                    +  "    mp.menu_id, "
                    +  "    mp.menu_name, "
                    +  "    mp.available as mp_available, "
                    +  "    mp.privilege_url as mp_url, "
                    +  "    dpe.do_id, "
                    +  "    dpe.do_name, "
                    +  "    dpe.available as dpe_available, " 
                    +  "    dpe.privilege_url as dpe_url "
                    +  " FROM "
                    +  "    user_info u "
                    +  " LEFT JOIN user_role_relation urr ON urr.user_id = u.user_id "
                    +  " LEFT JOIN role_info r ON urr.role_id = r.role_id "
                    +  " LEFT JOIN role_sys_relation rsr ON rsr.role_id = r.role_id "
                    +  " LEFT JOIN sys_privilege sp ON rsr.sys_id = sp.sys_id  "
                    +  " LEFT JOIN menu_privilege mp ON sp.sys_id = mp.sys_id and mp.role_id= r.role_id "
                    +  " LEFT JOIN do_privilege dpe ON dpe.menu_id = mp.menu_id and dpe.role_id= r.role_id "
                    +  " HAVING "
                    +  " u.locked = 0 and u.user_name= ? and r.available=0 and sp.available=0 and mp.available= 0 and dpe.available=0 "
                    +  " ) temp ";
        }else if (level== 1) {
            sql =  " select CONCAT(temp.sp_url,\":\", temp.mp_url) from "
                    +  " (SELECT "
                    +  "    u.user_id, "
                    +  "    u.user_name, "
                    +  "    u.locked, "
                    +  "    r.role_id, "
                    +  "    r.role_name, "
                    +  "    r.available, "
                    +  "    sp.sys_id, "
                    +  "    sp.sys_name, "
                    +  "    sp.available as sp_available, "
                    +  "    sp.privilege_url as sp_url, "
                    +  "    mp.menu_id, "
                    +  "    mp.menu_name, "
                    +  "    mp.available as mp_available, "
                    +  "    mp.privilege_url as mp_url "
                    +  " FROM "
                    +  "    user_info u "
                    +  " LEFT JOIN user_role_relation urr ON urr.user_id = u.user_id "
                    +  " LEFT JOIN role_info r ON urr.role_id = r.role_id "
                    +  " LEFT JOIN role_sys_relation rsr ON rsr.role_id = r.role_id "
                    +  " LEFT JOIN sys_privilege sp ON rsr.sys_id = sp.sys_id  "
                    +  " LEFT JOIN menu_privilege mp ON sp.sys_id = mp.sys_id and mp.role_id= r.role_id "
                    +  " HAVING "
                    +  " u.locked = 0 and u.user_name= ? and r.available=0 and sp.available=0 and mp.available= 0 "
                    +  " ) temp ";
        }else if (level== 2) {
            sql =  " select CONCAT(temp.sp_url) from "
                    +  " (SELECT "
                    +  "    u.user_id, "
                    +  "    u.user_name, "
                    +  "    u.locked, "
                    +  "    r.role_id, "
                    +  "    r.role_name, "
                    +  "    r.available, "
                    +  "    sp.sys_id, "
                    +  "    sp.sys_name, "
                    +  "    sp.available as sp_available, "
                    +  "    sp.privilege_url as sp_url "
                    +  " FROM "
                    +  "    user_info u "
                    +  " LEFT JOIN user_role_relation urr ON urr.user_id = u.user_id "
                    +  " LEFT JOIN role_info r ON urr.role_id = r.role_id "
                    +  " LEFT JOIN role_sys_relation rsr ON rsr.role_id = r.role_id "
                    +  " LEFT JOIN sys_privilege sp ON rsr.sys_id = sp.sys_id  "
                    +  " HAVING "
                    +  " u.locked = 0 and u.user_name= ? and r.available=0 and sp.available=0 "
                    +  " ) temp ";
        }else {
            
        }
        List<String> result= this.jdbcTemplate.queryForList(sql, String.class, new Object[]{userName});
        return new HashSet<String>(result);
    }

}
