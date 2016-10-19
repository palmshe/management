package com.genius.shiro.pojo;

import java.io.Serializable;

public class DoPrivilege implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long doId;

    private String doName;

    private Boolean available;

    private String privilegeUrl;

    private String iconUrl;

    private Long menuId;

    public Long getDoId() {
        return doId;
    }

    public void setDoId(Long doId) {
        this.doId = doId;
    }

    public String getDoName() {
        return doName;
    }

    public void setDoName(String doName) {
        this.doName = doName == null ? null : doName.trim();
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getPrivilegeUrl() {
        return privilegeUrl;
    }

    public void setPrivilegeUrl(String privilegeUrl) {
        this.privilegeUrl = privilegeUrl == null ? null : privilegeUrl.trim();
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl == null ? null : iconUrl.trim();
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}