package com.qihui.sun.service.jms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JmsTest {

	@Autowired
	private JmsQueueProducer jmsQueueProducer;

	@Autowired
	private JmsTopicProducer jmsTopicProducer;

	@Test
	public void testJmsQueueProducer(){
		jmsQueueProducer.sendMessage("hello jms!!!");
	}

	@Test
	public void testJmsTopicProducer(){
		jmsTopicProducer.sendMessage("你好!");
	}
}
