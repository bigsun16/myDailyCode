package com.qihui.sun.service.jms;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

public class JmsQueueProducer {
    private JmsTemplate jmsTemplate;
    private Destination destination;

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
    
    public void sendMessage(final String message){
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                System.out.println("I am producer ,I am sending message:" + message);
                return session.createTextMessage(message);
            }
        });
    }
}
