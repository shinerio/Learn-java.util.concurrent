package com.shinerio.concurrent.PriorityBlockingQueue;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class PriorityTaskConsumer implements Runnable {
	private PriorityBlockingQueue<PriorityTask> q;
	private static Random r = new Random();
	public PriorityTaskConsumer(PriorityBlockingQueue<PriorityTask> queue) {
		this.q = queue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				q.take().run();
				TimeUnit.MILLISECONDS.sleep(200);
			}
		} catch (InterruptedException e) {
			System.out.println(this + "is interrupted");
		}
		System.out.println(this + "exit normal");
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		PriorityBlockingQueue<PriorityTask> queue = new PriorityBlockingQueue<PriorityTask>();
		for (int i = 0; i < 20; i++) {
			queue.add(new PriorityTask(r.nextInt(10)));
		}
		queue.add(new PriorityTask(10));
		queue.add(new PriorityTask.EndSentinel(exec));
		exec.execute(new PriorityTaskConsumer(queue));
	}
}
