package com.genius.shiro.pojo;

import java.io.Serializable;
import java.util.Date;

public class PrivilegeLog implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long logId;

    private Long userId;

    private Date ceateTime;

    private String privilegeUrlTotal;

    private String sysTableName;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCeateTime() {
        return ceateTime;
    }

    public void setCeateTime(Date ceateTime) {
        this.ceateTime = ceateTime;
    }

    public String getPrivilegeUrlTotal() {
        return privilegeUrlTotal;
    }

    public void setPrivilegeUrlTotal(String privilegeUrlTotal) {
        this.privilegeUrlTotal = privilegeUrlTotal == null ? null : privilegeUrlTotal.trim();
    }

    public String getSysTableName() {
        return sysTableName;
    }

    public void setSysTableName(String sysTableName) {
        this.sysTableName = sysTableName == null ? null : sysTableName.trim();
    }
}