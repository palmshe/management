package com.genius.access.mail.entity;

import java.io.Serializable;

public class MailParams implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private int appId;//应用id
    private String appName;//引用名称
    private int mailLevel;//邮件级别，0：普通，1：紧急
    private int sendWeights;//发送权重，0：低，1：中，2：高
    private String mailTitle;//邮件标题
    private String mailMessage;//邮件内容
    private String to ;
    private String bcc;
    private String cc;
    
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getBcc() {
        return bcc;
    }
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }
    public String getCc() {
        return cc;
    }
    public void setCc(String cc) {
        this.cc = cc;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String getAppName() {
        return appName;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }
    public int getAppId() {
        return appId;
    }
    public void setAppId(int appId) {
        this.appId = appId;
    }
    public int getMailLevel() {
        return mailLevel;
    }
    public void setMailLevel(int mailLevel) {
        this.mailLevel = mailLevel;
    }
    public int getSendWeights() {
        return sendWeights;
    }
    public void setSendWeights(int sendWeights) {
        this.sendWeights = sendWeights;
    }
    public String getMailTitle() {
        return mailTitle;
    }
    public void setMailTitle(String mailTitle) {
        this.mailTitle = mailTitle;
    }
    public String getMailMessage() {
        return mailMessage;
    }
    public void setMailMessage(String mailMessage) {
        this.mailMessage = mailMessage;
    }
}
