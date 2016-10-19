package com.genius.shiro.pojo;

import java.io.Serializable;

public class RoleSysRelation implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long relationId;

    private Long sysId;

    private Long roleId;

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public Long getSysId() {
        return sysId;
    }

    public void setSysId(Long sysId) {
        this.sysId = sysId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}