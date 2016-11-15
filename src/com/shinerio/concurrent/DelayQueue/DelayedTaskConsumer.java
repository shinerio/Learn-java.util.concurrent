package com.shinerio.concurrent.DelayQueue;

import java.util.concurrent.DelayQueue;

public class DelayedTaskConsumer implements Runnable {
	private DelayQueue<DelayedTask> queue;
	
	public DelayedTaskConsumer(DelayQueue<DelayedTask> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				queue.take().run();
			}
		}catch(InterruptedException e){
			System.out.println("consumer interrupt");
		}
		System.out.println("consumer finished run");
	}
}
