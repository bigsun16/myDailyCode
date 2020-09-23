package com.qihui.sun.service.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class TestJmsTopicConsumer {
    public static void main(String[] args) throws JMSException, IOException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test_topic");
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener((message)-> {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("I get the message: "+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
        });
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }
}
