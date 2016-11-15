package com.shinerio.concurrent.DelayQueue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DelayQueueDemo {
	public static void main(String[] args) {
		Random r= new Random();
		ExecutorService exec = Executors.newCachedThreadPool();
		DelayQueue<DelayedTask> delayQueue = new DelayQueue<DelayedTask>();
		for(int i = 0;i<5;i++){
			delayQueue.put(new DelayedTask(r.nextInt(5000)));
		}
		delayQueue.put(new DelayedTask.EndSentinel(5000, exec));
		exec.execute(new DelayedTaskConsumer(delayQueue));
	}
}
