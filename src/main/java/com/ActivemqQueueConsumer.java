package com;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by wjf on 2018/5/5.
 */
public class ActivemqQueueConsumer {
    private String name = "";

    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageConsumer consumer = null;

    ActivemqQueueConsumer(String name){
        this.name = name;
    }

    public void initialize(String url) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("wangjianfang");
        consumer = session.createConsumer(destination);
        connection.start();
    }

    public void recive(String url) {
        try {
            initialize(url);
            System.out.println("Consumer(" + name + "):->Begin listening...");
            int count = 0;
            while (count < 10) {
                Message message = consumer.receive(); //主动接收消息(同步)
                System.out.println("consumer recive:" + ((TextMessage) message).getText());
                count++;
                //System.out.println(count);
            }
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void submit() throws JMSException {
        session.commit();
    }

    // 关闭连接
    public void close() throws JMSException {
        System.out.println("Consumer:->Closing connection");
        if (consumer != null)
            consumer.close();
        if (session != null)
            session.close();
        if (connection != null)
            connection.close();
    }

}
