package com.qihui.sun.service.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class JmsMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("I get the message: " + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
