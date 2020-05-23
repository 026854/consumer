package com.example.demo.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.service.MessageServiceBo;

public class Receiver {

	private Logger log = LoggerFactory.getLogger(Receiver.class);
	   
	@Autowired
	private MessageServiceBo serviceBO; 
	
	@RabbitListener(queues = "coffee.queue",concurrency = "3")
    public void receive1(String in) throws InterruptedException {
    	log.info("consume message {} on {} ", in, Thread.currentThread().getName());
    	try {
    		serviceBO.receive(in, 1);
            //Thread.sleep(ThreadLocalRandom.current().nextLong(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void receive2(String in) throws InterruptedException {
    	serviceBO.receive(in, 2);
    }
    @RabbitListener(queues = "jieun.queue")
    public void jieunreceive(String in) throws InterruptedException {
    	serviceBO.receive(in, 3);
    }
    
    @RabbitListener(queues = "result.queue")
    public void resultreceive(String in) throws InterruptedException {
    	serviceBO.resultreceive(in, 3);
    }
}
