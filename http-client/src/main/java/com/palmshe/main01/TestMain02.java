package com.palmshe.main01;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class TestMain02 {
    
    private static Logger logger= Logger.getLogger(TestMain02.class);

    public static void main(String [] args ){
        int i=  281;
        while (i<282) {
            excutePostRequestest(++i, 341341255);
            //excutePostRequestest0000(1);
        }
    }
    
    private static void excutePostRequestest(int appId, long serialNumber){
        CloseableHttpResponse httpresp= null;
        try {
            CloseableHttpClient client= HttpClients.createDefault();
//            HttpPost postRequest= new HttpPost("http://127.0.0.1:18080/MailUtil/mailAccess/transformRequest");
//            HttpPost postRequest= new HttpPost("http://192.168.86.132:8080/MailUtil/mailAccess/transformRequest");
            HttpPost postRequest= new HttpPost("http://127.0.0.1:18080/MailUtil/wechatAccess/sendWechatMsg");
            RequestConfig configRequest= RequestConfig.custom().setConnectionRequestTimeout(100000).setConnectTimeout(100000)
                    .setSocketTimeout(100000).build();
            List<BasicNameValuePair> params= new ArrayList<BasicNameValuePair>();
            postRequest.setConfig(configRequest);
            params.add(new BasicNameValuePair("content", "监控心跳异常，reason=【 磁盘已写满】"));
            params.add(new BasicNameValuePair("appId", String.valueOf(appId)));
            params.add(new BasicNameValuePair("tousers", "palmshe|huangjl"));
            params.add(new BasicNameValuePair("serialNumber", String.valueOf(serialNumber)));
            //postRequest.setEntity(new UrlEncodedFormEntity(params));
            postRequest.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
            httpresp= client.execute(postRequest);
            HttpEntity entityResponse= httpresp.getEntity();
            logger.info("输出实体为："+EntityUtils.toString(entityResponse));
            EntityUtils.consume(entityResponse);
        } catch (UnsupportedEncodingException e) {
            logger.error("字符编码转换异常", e);
        } catch (ClientProtocolException e) {
            logger.error("请求协议异常", e);
        } catch (IOException e) {
            logger.error("IO异常", e);
        }finally {
            try {
                httpresp.close();
            } catch (IOException e) {
                logger.error("IO异常", e);
            }
        }
    }
    
    private static void excutePostRequestest0000(int appId){
        CloseableHttpResponse httpresp= null;
        try {
            CloseableHttpClient client= HttpClients.createDefault();
//            HttpPost postRequest= new HttpPost("http://127.0.0.1:18080/MailUtil/mailAccess/transformRequest");
//            HttpPost postRequest= new HttpPost("http://192.168.86.132:8080/MailUtil/mailAccess/transformRequest");
           // HttpPost postRequest= new HttpPost("http://127.0.0.1:18080/MailUtil/wechatAccess/sendWechatMsg");
            HttpGet getRequest= new HttpGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wx5722a0c48571e403&corpsecret=zw3mDJ_5cauEZvyotrIgSJvd7vFmSjiqu8RYIz0sjf0hvjROlC_59zKYv7mOQV_L");
            RequestConfig configRequest= RequestConfig.custom().setConnectionRequestTimeout(100000).setConnectTimeout(100000)
                    .setSocketTimeout(100000).build();
//            List<BasicNameValuePair> params= new ArrayList<BasicNameValuePair>();
//            getRequest.setConfig(configRequest);
//            params.add(new BasicNameValuePair("content", "发送内容。。。。。"));
//            params.add(new BasicNameValuePair("tousers", "palmshe"));
//            params.add(new BasicNameValuePair("tousers", "palmshe2"));
//            params.add(new BasicNameValuePair("appId", String.valueOf(appId)));
            //postRequest.setEntity(new UrlEncodedFormEntity(params));
            //getRequest.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
            httpresp= client.execute(getRequest);
            HttpEntity entityResponse= httpresp.getEntity();
            logger.info("输出实体为："+EntityUtils.toString(entityResponse));
            EntityUtils.consume(entityResponse);
        } catch (UnsupportedEncodingException e) {
            logger.error("字符编码转换异常", e);
        } catch (ClientProtocolException e) {
            logger.error("请求协议异常", e);
        } catch (IOException e) {
            logger.error("IO异常", e);
        }finally {
            try {
                httpresp.close();
            } catch (IOException e) {
                logger.error("IO异常", e);
            }
        }
    }
}
