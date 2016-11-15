package com.shinerio.concurrent.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class WaitingTask implements Runnable{
	private static int counter = 0;
	private final int id = counter++;
	private final CountDownLatch latch;
	
	public WaitingTask(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			latch.await();
			System.out.println("Latch barrier passed for"+this);
		} catch (InterruptedException e) {
			System.out.println(this+"interrput");
		}
	}
	
	@Override
	public String toString() {
		return String.format("waitingTask %1$-3d", id);
	}
}
