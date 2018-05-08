package com;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by wjf on 2018/5/5.
 */
public class MessageReceiver implements Runnable {
    private String url;
    private String user;
    private String password;
    private final String QUEUE;

    public MessageReceiver(String queue, String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.QUEUE = queue;
    }

    @Override
    public void run() {
        //ConnectionFactory--连接工厂，JMS用它创建连接
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
        // Session： 一个发送或接收消息的线程
        Session session = null;
        // Destination ：消息的目的地;消息发送给谁.
        Destination receiveQueue;
        try {
            // Connection ：JMS 客户端到JMS
            Connection connection = connectionFactory.createConnection();
            session = connection.createSession(true, Session.SESSION_TRANSACTED);
            receiveQueue = session.createQueue(QUEUE);
            MessageConsumer consumer = session.createConsumer(receiveQueue);

            connection.start();
            System.out.println(Thread.currentThread().getName() + " start");

            while (true) {
                Message message = consumer.receive();
                if (message instanceof TextMessage) {
                    TextMessage receiveMessage = (TextMessage) message;
                    System.out.println("我是Receiver,收到消息如下: \r\n"
                            + receiveMessage.getText());
                } else {
                    session.commit();
                    break;
                }

            }
            if (consumer != null)
                consumer.close();
            if (session != null)
                session.close();
            if (connection != null)
                connection.close();
            System.out.println(Thread.currentThread().getName() + " close");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}