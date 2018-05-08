package com;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Date;

/**
 * Created by wjf on 2018/5/5.
 */
public class MessageSender implements Runnable {

    private String url;
    private String user;
    private String password;
    private final String QUEUE;

    public MessageSender(String queue, String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.QUEUE = queue;
    }

    @Override
    public void run() {
        // ConnectionFactory--连接工厂，JMS用它创建连接
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
        Session session = null;
        Destination sendQueue;
        Connection connection = null;

        int messageCount = 0;
        try {
            // 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            System.out.println(Thread.currentThread().getName() + " start");

            while (true) {
                // 获取操作连接
                session = connection.createSession(true, Session.SESSION_TRANSACTED);
                sendQueue = session.createQueue(QUEUE);
                // MessageProducer：消息发送者
                MessageProducer sender = session.createProducer(sendQueue);
                TextMessage outMessage = session.createTextMessage();
                outMessage.setText(new Date() + "现在发送是第" + messageCount + "条消息");
                sender.send(outMessage);
                session.commit();
                sender.close();

                if ((++messageCount) == 10) {
                    // 发够十条消息退出
                    break;
                }
                Thread.sleep(1000);
            }
            if (session != null)
                session.close();
            if (connection != null)
                connection.close();
            System.out.println(Thread.currentThread().getName() + " close");
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
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
