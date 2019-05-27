package com.example.SensorDataGenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SensorDataGeneratorApplicationTests {

	@Autowired
	private MessageBuilder messageBuilder;

	@Autowired
	private Sender sender;

	@Test
	public void messageBuildTest() {
		MessageBuilder  messageBuilder = new MessageBuilder();
		System.out.println(messageBuilder.build());
	}

	@Test
	public void SenderTest() throws InterruptedException {

		while(true)
		{
			String message = this.messageBuilder.build();
			System.out.println(message);
			this.sender.Send(message);
			Thread.sleep(2000);
		}

	}

}
