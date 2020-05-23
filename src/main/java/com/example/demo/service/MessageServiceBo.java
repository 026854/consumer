package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.example.demo.sender.MessageSending;

@Service
public class MessageServiceBo {
	
	@Autowired
	MessageSending sending;

	public void receive(String in, int receiver) throws InterruptedException {
		StopWatch watch = new StopWatch();
		watch.start();
		System.out.println("instance " + receiver + " [x] Received '" + in + "'");
		doWork(in);
		watch.stop();
		System.out.println("instance " + receiver + " [x] Done in " + watch.getTotalTimeSeconds() + "s");
		sending.sendingMessage("tut.direct", "result", "result!!!!");
	}
	
	public void resultreceive(String in, int receiver) throws InterruptedException {
		StopWatch watch = new StopWatch();
		watch.start();
		System.out.println("instance " + receiver + " [x] Received '" + in + "'");
		doWork(in);
		watch.stop();
		System.out.println("instance " + receiver + " [x] Done in " + watch.getTotalTimeSeconds() + "s");
	}

	private void doWork(String in) throws InterruptedException {
		for (char ch : in.toCharArray()) {
			if (ch == '.') {
				Thread.sleep(1000);
			}
		}
	}

}
