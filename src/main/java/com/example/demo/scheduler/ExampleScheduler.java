package com.example.demo.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExampleScheduler {

	@Scheduled(cron = "#{@schedulerCronExample1}")
	public void schedule1() {
		System.out.println("동작중 스케쥴1");
	}
}
