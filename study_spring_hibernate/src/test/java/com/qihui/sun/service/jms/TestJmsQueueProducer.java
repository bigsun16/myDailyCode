package com.qihui.sun.service.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TestJmsQueueProducer {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test_queue");
        MessageProducer producer = session.createProducer(queue);
        TextMessage textMessage = session.createTextMessage("test jms hello world!");
        producer.send(textMessage);

        producer.close();
        session.close();
        connection.close();
    }
}
