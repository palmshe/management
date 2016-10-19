package com.palmshe.main04;

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
public class OtherCustomerClass {
    
    private static Logger logger = Logger.getLogger(CustomerClass.class);
    private final static String EXCHANGE_NAME = "logs";
    private final static String CUSTOMER= "002-Customer";
    
    public static void main(String[] args) {
        //创建连接工厂
        ConnectionFactory factory= new ConnectionFactory();
        //设置Rabbitmq地址
        factory.setHost("127.0.0.1");
        //设置端口
        //factory.setPort(5672);
        String queueName=null;
        try {
            //创建新的连接
            Connection conn=factory.newConnection();
            //创建一个频道
             final Channel chan= conn.createChannel();
             //声明交换机
             chan.exchangeDeclare(EXCHANGE_NAME, "fanout");
            //声明一个队列，变成绑定一个随机队列名称
             queueName= chan.queueDeclare().getQueue();
             chan.queueBind(queueName, EXCHANGE_NAME, "");
            //消费者等待消息
            logger.info("Waiting for messages. To exit press CTRL+C");  
            //设置每次获取消息个数，实现了一定的负载均衡的效果
            //chan.basicQos(1);
            //消费队列
            //DefaultConsumer类实现了Consumer接口，通过传入一个频道，告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery 
            Consumer consumer= new DefaultConsumer(chan){

                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
                        byte[] body) throws IOException {
                    String message= new String(body, "utf-8");
                    try {
                        doWork(message);
                    } finally {
                        //注意分发时，不能去确认处理完毕，否则，通过queueBind()绑定的频道关闭
                        //消息处理完，true为确认
                        //chan.basicAck(envelope.getDeliveryTag(), true);
                    }
                }
                
            };
            //消息消费完，true为确认
            chan.basicConsume(queueName, true, consumer);  
        } catch (Exception e) {
            logger.error("消息消费异常", e);
        } finally {
            
        } 
    }

    /**
     * @Description：处理
     * @param message
     */
    protected static void doWork(String message) {
        logger.info(CUSTOMER+"获取一个消息，时间："+new Date().toString()+"，"+message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
