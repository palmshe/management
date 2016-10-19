package com.palmshe.main02;

import java.util.Date;

import org.apache.log4j.Logger;

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
    private final static String QUEUE_NAME = "producer_name_customers";

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
            //声明一个队列
            //声明一个队列 -- 在RabbitMQ中，队列声明是幂等性的
            //（一个幂等操作的特点是其任意多次执行所产生的影响均与一次执行的影响相同），也就是说，如果不存在，就创建，如果存在，不会对已经存在的队列产生任何影响
           //第二个参数为true,表示消息会持久化
            chan.queueDeclare(QUEUE_NAME, true, false, false, null);
            for (int i = 0; i < 7; i++) {
                String msg= "Message Queue 0"+i;
                //MessageProperties.PERSISTENT_TEXT_PLAIN使服务端数据持久化
                chan.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes("utf-8"));
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
