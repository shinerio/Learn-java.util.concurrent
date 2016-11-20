package com.shinerio.concurrent.PriorityBlockingQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class PriorityTask implements Runnable,Comparable<PriorityTask>{
	private final int priority;
	private static int counter = 0;
	private final int id = counter++;
	protected static List<PriorityTask> list= new ArrayList<PriorityTask>(); 
	public PriorityTask(int priority) {
		this.priority = priority;
		list.add(this);
	}

	@Override
	public int compareTo(PriorityTask o) {
		return o.priority>this.priority?1:(o.priority==this.priority?0:-1);
	}
	@Override
	public String toString() {
		return "priority"+String.format("[%1$-3d]", priority)+"Task:"+id;
	}
	@Override
	public void run() {
		try {
			System.out.println(this+"run over!");
			TimeUnit.MILLISECONDS.sleep(200);
		} catch (InterruptedException e) {
			System.out.println(this+"is interrupted");
		}
	}
	public static class EndSentinel extends PriorityTask{
		private ExecutorService exec;

		public EndSentinel(ExecutorService exec) {
			super(-1);
			this.exec = exec;
		}
		public void run(){
			System.out.println("the list order list");
			for (PriorityTask priorityTask : PriorityTask.list) {
				System.out.println(priorityTask);
			}
			System.out.println(this+"calling shutDownNow()");
			exec.shutdownNow();
		}
	}
}
