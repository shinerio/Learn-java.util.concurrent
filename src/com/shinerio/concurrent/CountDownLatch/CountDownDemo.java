package com.shinerio.concurrent.CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownDemo {

	public static void main(String[] args) {
		final  int SIZE = 10;
		ExecutorService exec = Executors.newCachedThreadPool();
		CountDownLatch latch = new CountDownLatch(SIZE);
		for (int i = 0; i < 3; i++) {
			exec.execute(new WaitingTask(latch));
		}
		for (int i = 0; i < SIZE; i++) {
			exec.execute(new TaskPortion(latch));
		}
		System.out.println("Lauched all tasks");
		exec.shutdown();
	}
}
