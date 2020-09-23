package com.qihui.sun.service.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class TestJmsQueueConsumer {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test_queue");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener((message)-> {
                TextMessage message1 = (TextMessage) message;
                try {
                    System.out.println(message1.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
        });
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        consumer.close();
        session.close();
        connection.close();

    }
}
