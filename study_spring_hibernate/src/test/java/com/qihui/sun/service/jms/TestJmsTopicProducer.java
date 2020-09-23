package com.qihui.sun.service.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TestJmsTopicProducer {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test_topic");
        MessageProducer producer = session.createProducer(topic);
        System.out.println("I am sending message...");
        TextMessage textMessage = session.createTextMessage("hello jms topic!!!");
        producer.send(textMessage);
        producer.close();
        session.close();
        connection.close();
    }
}
