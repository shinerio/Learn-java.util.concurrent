package com.shinerio.concurrent.CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TaskPortion implements Runnable{
	private static int counter = 0;
	private final int id = counter++;
	private final CountDownLatch latch;
	
	public TaskPortion(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			doWork();
			latch.countDown();
		} catch (InterruptedException e) {
			System.out.println(this+"work interrupt");
		}
	}
	public void doWork() throws InterruptedException{
		TimeUnit.MILLISECONDS.sleep(1000);
		System.out.println(this+"completed");
	}
	
	public String toString(){
		return String.format("%1$-3d", id);	
	}
}
