package com;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by wjf on 2018/5/5.
 */
public class ActivemqQueueProducer {

    private Session session;
    private  MessageProducer producer ;
    private Connection connection;

    //初始化
    public  void initialize(String url) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        connection = connectionFactory.createConnection();
        session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("wangjianfang");
        producer = session.createProducer(destination);
        connection.start();
    }

    public void sendMessage(String Message) {
        try {
            TextMessage textMessage = session.createTextMessage(Message);
            producer.send(textMessage);
            System.out.println("Sending message:" +textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void submit() {
        try {
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void close() throws JMSException {
        System.out.println("Producer:->Closing connection");
        if (producer != null)
            producer.close();
        if (session != null)
            session.close();
        if (connection != null)
            connection.close();
    }
}
