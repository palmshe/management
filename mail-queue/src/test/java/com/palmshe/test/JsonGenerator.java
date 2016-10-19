package com.palmshe.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.palmshe.mail.bean.MailParams;
import com.palmshe.mail.bean.ParamsSource;

public class JsonGenerator {
    
    private static Gson gson= new Gson();
    private static Logger logger= Logger.getLogger(JsonGenerator.class);

    public static void main(String[] args) {
        //generateJson();
        //generateEntity();
        //generateParamsSource();
        generateParmasEntity();
        //testDate();
        System.exit(0);
    }
    
    private static void generateJson(){
        MailParams params= new MailParams();
        params.setAppId(100);
        params.setAppName("应用名称");
        params.setBusinessId(888);
        params.setBusinessName("正在爬虫");
        params.setMailLevel(1);
        params.setMailMessage("这是邮件内容");
        params.setMailTitle("邮件标题");
        params.setSendWeights(2);
        Map<String, String> to= new HashMap<String, String>();
        to.put("to1@foxmail.com", "姓名1");
        to.put("to2@foxmail.com", "姓名2");
        to.put("to3@foxmail.com", "姓名3");
        Map<String, String> cc= new HashMap<String, String>();
        cc.put("to1@foxmail.com", "姓名1");
        cc.put("to2@foxmail.com", "姓名2");
        Map<String, String> bcc= new HashMap<String, String>();
        bcc.put("to1@foxmail.com", "姓名1");
        params.setTo(to);
        params.setCc(cc);
        params.setBcc(bcc);
        logger.info(gson.toJson(params));
    }
    
    private static void generateEntity(){
        String jsonstring= "{\"appId\":100,\"businessName\":\"正在爬虫\",\"mailLevel\":1,\"sendWeights\":2,\"mailTitle\":\"邮件标题\",\"mailMessage\":\"这是邮件内容\",\"to\":{\"to2@foxmail.com\":\"姓名2\",\"to3@foxmail.com\":\"姓名3\",\"to1@foxmail.com\":\"姓名1\"},\"cc\":{\"to2@foxmail.com\":\"姓名2\",\"to1@foxmail.com\":\"姓名1\"},\"bcc\":{\"to1@foxmail.com\":\"姓名1\"}}";
        MailParams params= gson.fromJson(jsonstring, MailParams.class);
        logger.info(params.getBusinessName()+"\r\n");
        logger.info(params.getCc()+"\r\n");
        logger.info(params.getMailMessage()+"\r\n");
    }
    
    private static void generateParamsSource(){
        MailParams params= new MailParams();
        params.setAppId(100);
        params.setAppName("应用名称");
        params.setBusinessId(888);
        params.setBusinessName("正在爬虫");
        params.setMailLevel(1);
        params.setMailMessage("这是邮件内容");
        params.setMailTitle("邮件标题");
        params.setSendWeights(2);
        Map<String, String> to= new HashMap<String, String>();
        to.put("to1@foxmail.com", "姓名1");
        to.put("to2@foxmail.com", "姓名2");
        to.put("to3@foxmail.com", "姓名3");
        Map<String, String> cc= new HashMap<String, String>();
        cc.put("to1@foxmail.com", "姓名1");
        cc.put("to2@foxmail.com", "姓名2");
        Map<String, String> bcc= new HashMap<String, String>();
        bcc.put("to1@foxmail.com", "姓名1");
        params.setTo(to);
        params.setCc(cc);
        params.setBcc(bcc);
        //放入请求类中
        ParamsSource source= new ParamsSource();
        source.setParams(params);
        logger.info(gson.toJson(source));
    }
    
    private static void generateParmasEntity(){
        String jsonstring= "{\"params\":{\"appId\":100,\"appName\":\"应用名称\",\"businessId\":888,\"businessName\":\"正在爬虫\",\"mailLevel\":1,\"sendWeights\":2,\"mailTitle\":\"邮件标题\",\"mailMessage\":\"这是邮件内容\",\"to\":{\"to2@foxmail.com\":\"姓名2\",\"to3@foxmail.com\":\"姓名3\",\"to1@foxmail.com\":\"姓名1\"},\"cc\":{\"to2@foxmail.com\":\"姓名2\",\"to1@foxmail.com\":\"姓名1\"},\"bcc\":{\"to1@foxmail.com\":\"姓名1\"}}}";
        ParamsSource source= gson.fromJson(jsonstring, ParamsSource.class);
        logger.info(source.getParams().getTo()+"\r\n");
        logger.info(source.getParams().getMailTitle()+"\r\n");
    }
    
    private static void testDate(){
        Date date= new Date();
        long predate= date.getTime();
        logger.info("predate:"+predate);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            logger.info("线程恢复异常", e);
        }
        date= new Date();
        long sufdate= date.getTime();
        logger.info("sufdate:"+sufdate);
        logger.info("时间经过："+ (sufdate-predate));
    }
    
}




