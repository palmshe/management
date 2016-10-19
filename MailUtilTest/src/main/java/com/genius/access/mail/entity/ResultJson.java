package com.genius.access.mail.entity;

/**
 * @Description:接口返回
 * @author xiong.song
 * @date 2016年7月13日 下午2:10:32 
 */
public class ResultJson {
    private int code;//返回代号
    private String msg;//返回信息
    private String counterKey;//键值，appId-businessId
    
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getCounterKey() {
        return counterKey;
    }
    public void setCounterKey(String counterKey) {
        this.counterKey = counterKey;
    }
}
