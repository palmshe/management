package com.palmshe.main01;

import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * @Description:消费者
 * @author xiong.song
 * @date 2016年7月8日 下午2:55:37 
 */
public class CustomerClass {
    
    private static Logger logger = Logger.getLogger(CustomerClass.class);
    private final static String QUEUE_NAME = "producer_name";
    
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
            chan.queueDeclare(QUEUE_NAME, false, false, false, null);
            //消费者等待消息
            logger.info("Waiting for messages. To exit press CTRL+C");  
            //消费队列
            //DefaultConsumer类实现了Consumer接口，通过传入一个频道，告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery 
            Consumer consumer= new DefaultConsumer(chan){

                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
                        byte[] body) throws IOException {
                    String message= new String(body, "utf-8");
                    logger.info("获取一个消息，时间："+new Date().toString()+"，"+message);
                }
                
            };
            //回复队列应答 ，RabbitMQ中的消息确认机制
            chan.basicConsume(QUEUE_NAME, true, consumer);  
        } catch (Exception e) {
            logger.error("消息消费异常", e);
        } finally {
            
        } 
    }

}
