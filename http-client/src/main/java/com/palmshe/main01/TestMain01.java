package com.palmshe.main01;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * @Description:测试最简单的http请求
 * @author xiong.song
 * @date 2016年7月12日 上午9:22:01 
 */
public class TestMain01 {
    
    private static Logger logger= Logger.getLogger(TestMain01.class);
    
    public static void main(String[] args) {
        for (int i = 11; i <12; i++) {
            //getSimpleDemo();
            //exchangeUri();
            //exchangeResponse();
            //setupHeader();
            //setHttpEntity();
            //closeConnection();
            //excutePostRequest01(5);
            //excutePostRequest02(44);
            //测试乱码
            excutePostRequestest(10112+i, 0, "111111");
        }
    }
    
    /**
     * @Description：简单的请求展示
     */
    private static void getSimpleDemo(){
        try {
            //使用默认配置创建httpclient组件
            CloseableHttpClient client= HttpClients.createDefault();
            //构造一个请求
            HttpGet httpget= new HttpGet("http://172.18.3.22:18080/ConfigManagement/userEmp/index");
            CloseableHttpResponse httpresp= client.execute(httpget);
            StatusLine status= httpresp.getStatusLine();//获取响应的状态信息
            logger.info("响应码是："+ status.getStatusCode());//获取状态码
            HttpEntity entity= httpresp.getEntity();//获取响应实体
            logger.info("响应内容是："+ entity.getContent());//获取响应内容对象
        } catch (ClientProtocolException e) {
            logger.error("请求协议异常", e);
        } catch (IOException e) {
            logger.error("io异常", e);
        }
    }
    
    /**
     * @Description：更改uri
     */
    private static void exchangeUri(){
        int status= -1;
        InputStream contentobj= null;
        CloseableHttpResponse resp= null;
        try {
            CloseableHttpClient client= HttpClients.createDefault();
            URIBuilder uriBuilder= new URIBuilder("http://127.0.0.1:18080/ConfigManagement/userEmp/index");
            URI uri= uriBuilder.build();
            HttpGet httpget=new HttpGet(uri);
            resp= client.execute(httpget);
            status= resp.getStatusLine().getStatusCode();
            logger.info("响应码是："+ status);//获取状态码
            contentobj= resp.getEntity().getContent();
            logger.info("响应内容是："+ contentobj);//获取响应内容对象
            logger.info(uriBuilder.getCharset());
            logger.info(uriBuilder.getHost());
            logger.info(uriBuilder.getPath());
            logger.info(uriBuilder.getPort());
            logger.info(uriBuilder.getScheme());
            uriBuilder.setPath("/ConfigManagement/userEmp/main");//修改uri
            uri= uriBuilder.build();
            httpget= new HttpGet(uri);
            resp= client.execute(httpget);
            status= resp.getStatusLine().getStatusCode();
            logger.info("响应码是："+ status);//获取状态码
            contentobj= resp.getEntity().getContent();
            logger.info("响应内容是："+ contentobj);//获取响应内容对象
            logger.info(uriBuilder.getCharset());
            logger.info(uriBuilder.getHost());
            logger.info(uriBuilder.getPath());
            logger.info(uriBuilder.getPort());
            logger.info(uriBuilder.getScheme());
        } catch (URISyntaxException e) {
            logger.error("uri解析错误", e);
        } catch (ClientProtocolException e) {
            logger.error("请求协议异常", e);
        } catch (IOException e) {
            logger.error("io异常", e);
        }
    }
    
    /**
     * @Description：更改响应
     */
    private static void exchangeResponse(){
        try {
            //使用默认配置创建httpclient组件
            CloseableHttpClient client= HttpClients.createDefault();
            //构造一个请求
            HttpGet httpget= new HttpGet("http://172.18.3.22:18080/ConfigManagement/userEmp/index");
            CloseableHttpResponse httpresp= client.execute(httpget);
            HttpResponse rep= new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_NO_CONTENT, "No Datas.");
            logger.info(rep.getStatusLine().toString());
        } catch (ClientProtocolException e) {
            logger.error("请求协议异常", e);
        } catch (IOException e) {
            logger.error("io异常", e);
        }
    }
    
    /**
     * @Description：设置响应首部
     */
    private static void setupHeader(){
        try {
            //使用默认配置创建httpclient组件
            CloseableHttpClient client= HttpClients.createDefault();
            //构造一个请求
            HttpGet httpget= new HttpGet("http://172.18.3.22:18080/ConfigManagement/userEmp/index");
            CloseableHttpResponse httpresp= client.execute(httpget);
            HttpResponse rep= new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_NO_CONTENT, "No Datas.");
            rep.addHeader("Set-Cookie", "c1=a;path=/;domain=172.18.3.33");
            rep.addHeader("Set-Cookie", "c2=b;path=/main;domain=172.18.3.34");
            HeaderIterator it= rep.headerIterator("Set-Cookie");
            while (it.hasNext()) {
                logger.info(it.next());
            }
        } catch (ClientProtocolException e) {
            logger.error("请求协议异常", e);
        } catch (IOException e) {
            logger.error("io异常", e);
        }
    }
    
    /**
     * @Description：设置内容实体
     */
    private static void setHttpEntity(){
        //utf-8汉字占用三个字节
        StringEntity entity= new StringEntity("important message 重要消息", ContentType.create("text/plain", "UTF-8"));
        try {
            logger.info(EntityUtils.toByteArray(entity));
            logger.info(EntityUtils.toByteArray(entity).length);
            logger.info(EntityUtils.toString(entity));
            logger.info(entity.getContentType());
            logger.info(entity.getContentLength());
        } catch (IOException e) {
            logger.error("未能转换成字节", e);
        }
    }
    
    /**
     * @Description：释放连接资源
     */
    private static void closeConnection(){
        CloseableHttpResponse httpresp= null;
        try {
            //使用默认配置创建httpclient组件
            CloseableHttpClient client= HttpClients.createDefault();
            //构造一个请求
            HttpGet httpget= new HttpGet("http://172.18.3.22:18080/ConfigManagement/userEmp/index");
            httpresp= client.execute(httpget);
            HttpEntity entity= httpresp.getEntity();
            if (entity!=null) {
                logger.info("文本长度："+entity.getContentLength());
            }
        } catch (ClientProtocolException e) {
            logger.error("请求协议异常", e);
        } catch (IOException e) {
            logger.error("io异常", e);
        }finally {
            try {
                httpresp.close();//仅仅只关闭响应连接
            } catch (IOException e) {
                logger.error("流关闭失败",e);
            }
        }
    }
    
    /**
     * @Description： 执行请求响应
     */
    private static void excuteRquestResponse(){
        CloseableHttpResponse httpresp= null;
        try {
            //使用默认配置创建httpclient组件
            CloseableHttpClient client= HttpClients.createDefault();
            //构造一个请求
            HttpGet httpget= new HttpGet("http://172.18.3.22:18080/ConfigManagement/userEmp/index");
            httpresp= client.execute(httpget);
            HttpEntity entity= httpresp.getEntity();
            if (entity!=null) {
                logger.info("文本长度："+entity.getContentLength());
            }
        } catch (ClientProtocolException e) {
            logger.error("请求协议异常", e);
        } catch (IOException e) {
            logger.error("io异常", e);
        }finally {
            try {
                httpresp.close();//仅仅只关闭响应连接
            } catch (IOException e) {
                logger.error("流关闭失败",e);
            }
        }
    }
    
    /**
     * @Description：测试post请求
     */
    private static void excutePostRequest(int appId){
        CloseableHttpResponse httpresp= null;
        try {
            CloseableHttpClient client= HttpClients.createDefault();
            HttpPost postRequest= new HttpPost("http://localhost:18080/MailUtil/mailAccess/transformRequest");
            RequestConfig configRequest= RequestConfig.custom().setConnectionRequestTimeout(100000).setConnectTimeout(100000)
                    .setSocketTimeout(100000).build();
            List<BasicNameValuePair> params= new ArrayList<BasicNameValuePair>();
            postRequest.setConfig(configRequest);
            String jsonstring= "{\"params\":{\"appId\":"+appId+",\"appName\":\"应用名称\",\"businessId\":888,\"businessName\":\"正在爬虫\",\"mailLevel\":1,\"sendWeights\":0,\"mailTitle\":\"邮件标题\",\"mailMessage\":\"这是邮件内容\",\"to\":{\"xiong.song@genius.com.cn\":\"姓名2\",\"linping.lv@genius.com.cn\":\"姓名3\"},\"cc\":{\"palmshe@foxmail.com\":\"姓名2\",\"to1@foxmail.com\":\"姓名1\"}}}";
            StringEntity entityRequest= new StringEntity(jsonstring, "UTF-8");
            entityRequest.setContentEncoding("UTF-8");
            entityRequest.setContentType("application/json");
            postRequest.setEntity(entityRequest);
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
    
    private static void excutePostRequest01(int appId){
        CloseableHttpResponse httpresp= null;
        try {
            CloseableHttpClient client= HttpClients.createDefault();
            HttpPost postRequest= new HttpPost("http://localhost:18080/MailUtil/mailAccess/transformRequest");
            RequestConfig configRequest= RequestConfig.custom().setConnectionRequestTimeout(100000).setConnectTimeout(100000)
                    .setSocketTimeout(100000).build();
            List<BasicNameValuePair> params= new ArrayList<BasicNameValuePair>();
            postRequest.setConfig(configRequest);
            String jsonstring= "{\"params\":{\"appId\":"+appId+",\"appName\":\"应用名称\",\"businessId\":888,\"businessName\":\"正在爬虫\",\"mailLevel\":1,\"sendWeights\":0,\"mailTitle\":\"邮件标题\",\"mailMessage\":\"这是邮件内容\",\"to\":{\"xiong.song@genius.com.cn\":\"姓名2\",\"linping.lv@genius.com.cn\":\"姓名3\"},\"cc\":{\"palmshe@foxmail.com\":\"姓名2\",\"to1@foxmail.com\":\"姓名1\"}}}";
            params.add(new BasicNameValuePair("paramsSource", jsonstring));
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
    //{"params":{"appId":4,"appName":"系统名称","businessId":888,"businessName":"业务名称","mailLevel":1,"sendWeights":2,"mailTitle":"邮件标题","mailMessage":"内容","to":{"xiong.song@genius.com.cn":"名称","linping.lv@genius.com.cn":"名称"},"cc":{"palmshe@foxmail.com":"名称","to1@foxmail.com":"名称"}}}
    private static void excutePostRequest02(int appId){
        CloseableHttpResponse httpresp= null;
        try {
            CloseableHttpClient client= HttpClients.createDefault();
            HttpPost postRequest= new HttpPost("http://192.168.86.132:8080/MailUtil/mailAccess/transformRequest");
            RequestConfig configRequest= RequestConfig.custom().setConnectionRequestTimeout(100000).setConnectTimeout(100000)
                    .setSocketTimeout(100000).build();
            List<BasicNameValuePair> params= new ArrayList<BasicNameValuePair>();
            postRequest.setConfig(configRequest);
            String jsonstring= "{\"params\":{\"appId\":"+appId+",\"appName\":\"应用名称\",\"mailLevel\":1,\"sendWeights\":2,\"mailTitle\":\"邮件标题\",\"mailMessage\":\"这是邮件内容\",\"to\":\"175102714@qq.com\",\"cc\":\"palmshe@foxmail.com\"}}";
            String serialNumber= "789456124";
            params.add(new BasicNameValuePair("paramsSource", jsonstring));
            params.add(new BasicNameValuePair("serialNumber", serialNumber));
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
    
    
    private static void excutePostRequestest(int appId, int sendweight, String serialNum){
        CloseableHttpResponse httpresp= null;
        try {
            CloseableHttpClient client= HttpClients.createDefault();
            HttpPost postRequest= new HttpPost("http://127.0.0.1:18080/MailUtil/mailAccess/transformRequest");
//            HttpPost postRequest= new HttpPost("http://192.168.86.132:8080/MailUtil/mailAccess/transformRequest");
//            HttpPost postRequest= new HttpPost("http://172.18.3.22:18080/MailUtil/mailAccess/transformRequest");
            RequestConfig configRequest= RequestConfig.custom().setConnectionRequestTimeout(100000).setConnectTimeout(100000)
                    .setSocketTimeout(100000).build();
            List<BasicNameValuePair> params= new ArrayList<BasicNameValuePair>();
            postRequest.setConfig(configRequest);
            String jsonstring= "{\"params\":{\"appId\":"+appId+",\"appName\":\"应用名称\",\"mailLevel\":1,\"sendWeights\":"+sendweight+",\"mailTitle\":\"邮件标题\",\"mailMessage\":\"程序名称 \t版本号 \tIP地址 \t线程ID \t最后心跳时间\nCommDataPrepare \t1.0.0325b \t172.18.5.43 \t0:10 \t2016-09-22 10:40:56\nFtpTransit \t1.0.1020a \t172.18.5.43 \tThread-1 \t2016-09-22 10:41:02\n\",\"to\":\"palmshe@foxmail.com|huangjia.lin@genius.com.cn\"}}";
            params.add(new BasicNameValuePair("paramsSource", jsonstring));
            params.add(new BasicNameValuePair("serialNumber", serialNum));
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
}
