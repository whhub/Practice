package com.zhhe.lianying_project;

import com.zhhe.lianying_project.rabbitmq.Producer;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.zhhe.lianying_project.rabbitmq.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTests {

	@Autowired
	private Producer sender;

	@Test
	public void hello() throws Exception{
		sender.send("{\"deviceNum\":\"2019040901548220283D\",\"date\":\"2019-05-15 11:57:00:115\",\"data\":{\"process\":{},\"custom\":{},\"state\":{}}}");
	}

}
