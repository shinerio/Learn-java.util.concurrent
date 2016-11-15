package com.shinerio.concurrent.DelayQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class DelayedTask implements Runnable,Delayed{
	private static int counter = 0;
	private final int id = counter++;
	private final int delta;
	private final long trigger;
	protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();
	
	public DelayedTask(int delta) {
		this.delta = delta;
		trigger = System.nanoTime()+TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
		sequence.add(this);
	}

			@Override
	public int compareTo(Delayed o) {
		DelayedTask other = (DelayedTask) o;
		return other.trigger>this.trigger?-1:(other.trigger==this.trigger?0:1);
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(trigger-System.nanoTime(),TimeUnit.NANOSECONDS );
	}

	@Override
	public void run() {
		System.out.println(this);
	}
	public String toString(){
		return String.format("[%1$-4d]", delta)+"Task"+id;
	}
	public String summary(){
		return "("+id+":"+delta+")";
	}
	public static class EndSentinel extends DelayedTask{
		private ExecutorService exec;

		public EndSentinel(int delta, ExecutorService exec) {
			super(delta);
			this.exec = exec;
		}
		public void run(){
			for (DelayedTask delayedTask : DelayedTask.sequence) {
				System.out.println(delayedTask.summary());
			}
			System.out.println(this+"calling shutdown()");
			exec.shutdownNow();
		}
		
	}
}
