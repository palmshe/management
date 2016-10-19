package com.genius.shiro.pojo;

import java.io.Serializable;

public class UserRoleRelation implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long relationId;

    private Long userId;

    private Long roleId;

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}