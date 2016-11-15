package com.shinerio.concurrent.CyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Horse implements Runnable{
	private static int counter = 0;
	private final int id = counter++;
	private int strides = 0;
	private CyclicBarrier barrier;
	private static Random r= new Random();

	public Horse(CyclicBarrier barrier) {
		this.barrier = barrier;
	}
	
	public synchronized int getStrides(){
		return strides;
	}
	@Override
	public String toString() {
		return "Horse"+id;
	}
	
	public String tracks(){
		StringBuilder s = new StringBuilder();
		for(int i =0;i<getStrides();i++){
			s.append("*");
		}
		s.append(id);
		return s.toString();
	}
	@Override
	public void run() {
		try {
		while(!Thread.interrupted()){
			synchronized (this) {
				strides+=r.nextInt(3);
			}
				barrier.await();
			}
		} catch (InterruptedException e) {
			System.out.println(this+"interrupt");
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
}
