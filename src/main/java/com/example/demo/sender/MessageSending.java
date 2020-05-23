package com.example.demo.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageSending {

	@Autowired
	  private RabbitTemplate template;
	  
	  
	  public void sendingMessage(String topic,String key, String message) {
		  template.convertAndSend(topic, key,message);
	  }
}
