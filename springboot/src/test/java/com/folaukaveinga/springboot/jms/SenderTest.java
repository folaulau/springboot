package com.folaukaveinga.springboot.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SenderTest {

	@Autowired
	private SenderService senderService;
	
	@Test
	public void testSendMail() {
		this.senderService.sendMail(new Mail("to","from","msg","sub"));
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException, msg: "+e.getLocalizedMessage());
		}
	}
	

}
