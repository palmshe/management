package com.palmshe.main03;

import java.util.Date;

import org.apache.log4j.Logger;

import com.rabbitmq.client.AMQP.Exchange.Declare;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * @Description:生产者
 * @author xiong.song
 * @date 2016年7月8日 下午2:28:26
 */
public class ProducerClass {

    private static Logger logger = Logger.getLogger(ProducerClass.class);
    private final static String EXCHANGE_NAME = "logs";//交换器的名称

    public static void main(String[] args) {
        //创建连接工厂
        ConnectionFactory factory= new ConnectionFactory();
        //设置Rabbitmq地址
        factory.setHost("127.0.0.1");
        //设置端口
        //factory.setPort(15672);
        Connection conn=null;
        Channel chan= null;
        try {
            //创建新的连接
             conn=factory.newConnection();
            //创建一个频道
             chan=conn.createChannel();
            //定义交换器的名称和类型,交换器的规则有：直连direct，主题topic，headers标题，fanout分发
             chan.exchangeDeclare(EXCHANGE_NAME,"fanout");
            //不需要创建队列
            //定义一个消息
            String message= "通过交换器分发过来的消息。。。。。";
            //发送消息到队列中
            //当发送到匿名交换器时（第一个参数），就需要路由线索（第二个参数）
            //chan.basicPublish("", QUEUE_NAME, null, message.getBytes("utf-8"));
            chan.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("utf-8"));
            logger.info("已发布一个消息，时间："+new Date().toString()+"，"+message);
            
            for (int i = 0; i < 7; i++) {
                String msg= "Message Queue 0"+i;
                chan.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes("utf-8"));
                logger.info("已发布一个消息，时间："+new Date().toString()+"，"+msg);
            }
        } catch (Exception e) {
            logger.error("消息发布异常", e);
        } finally {
            try {
                chan.close();
                conn.close();
            } catch (Exception e) {
                logger.error("发布连接异常", e);
            }
            
        } 
    }

}
