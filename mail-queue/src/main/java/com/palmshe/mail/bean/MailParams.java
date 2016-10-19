package com.palmshe.mail.bean;

import java.io.Serializable;
import java.util.Map;

public class MailParams implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private int appId;//应用id
    private String appName;//引用名称
    private int businessId;//业务id
    private String businessName;//相关业务描述
    private int mailLevel;//邮件级别，0：普通，1：紧急
    private int sendWeights;//发送权重，0：低，1：中，2：高
    private String mailTitle;//邮件标题
    private String mailMessage;//邮件内容
    private Map<String, String> to;//收件人
    private Map<String, String> cc;//抄送
    private Map<String, String> bcc;//秘送
   
    public String getAppName() {
        return appName;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }
    public int getBusinessId() {
        return businessId;
    }
    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }
    public int getAppId() {
        return appId;
    }
    public void setAppId(int appId) {
        this.appId = appId;
    }
    public String getBusinessName() {
        return businessName;
    }
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
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
    public Map<String, String> getTo() {
        return to;
    }
    public void setTo(Map<String, String> to) {
        this.to = to;
    }
    public Map<String, String> getCc() {
        return cc;
    }
    public void setCc(Map<String, String> cc) {
        this.cc = cc;
    }
    public Map<String, String> getBcc() {
        return bcc;
    }
    public void setBcc(Map<String, String> bcc) {
        this.bcc = bcc;
    }
}
