package com.dy.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExampleTask {
	
	@Scheduled(cron="0 */1 * * * ?")
	public void taskTest(){
		System.out.println("I am running --------------------------------------------------------------");
	}
}
