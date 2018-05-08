package com;

import redis.clients.jedis.Jedis;

import javax.jms.JMSException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws JMSException, InterruptedException {

        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //设置 redis 字符串数据
        //jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));

        String url = "tcp://localhost:61616";

//        String user = "wangjianfang";
//        String password = "huangwenfei";
//        String query = "MyQueue";
//        new Thread(new MessageReceiver(query,url,user,password), "Name-Receiver").start();
//        new Thread(new MessageSender(query,url,user,password), "Name-Sender").start();

//        ActivemqQueueProducer producer = new ActivemqQueueProducer();
//        ActivemqQueueConsumer consumer = new ActivemqQueueConsumer("1");
////        ActivemqQueueConsumerAsyn consumer1 = new ActivemqQueueConsumerAsyn("2");
//
//        producer.initialize(url);
//
//        Thread.sleep(500);
//
//        for (int i = 0; i < 5; i++) {
//            producer.sendMessage("111---Hello, world!" + i);
//        }
//        producer.submit();//如果开启事务需使用
//        //producer.close();
//
//        System.out.println("consumer1开始监听");
//        consumer.recive(url);
//        consumer.close();
//        System.out.println("consumer1接收完毕！");

//        for (int i = 0; i < 10; i++) {
//            producer.sendMessage("222---Hello, world!" + i);
//        }
//        System.out.println("consumer2开始监听");
//        consumer1.recive();
//        System.out.println("consumer2接收完毕！");
    }
}
