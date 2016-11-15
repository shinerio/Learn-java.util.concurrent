package com.shinerio.concurrent.CyclicBarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HoreseRace {
	static final int FINISH_LINE = 10;
	private List<Horse> horses = new ArrayList<Horse>();
	private ExecutorService exec = Executors.newCachedThreadPool();
	private CyclicBarrier barrier;
	
	public HoreseRace(int nHorses,final int pause){
		barrier = new CyclicBarrier(nHorses,new Runnable() {
			
			@Override
			public void run() {
				StringBuilder s = new StringBuilder();
				for(int i=0;i<FINISH_LINE;i++){
					s.append("=");
				}
				System.out.println(s);
				for (Horse horse : horses) {
					System.out.println(horse.getStrides());
				}
				for (Horse horse : horses) {
					if(horse.getStrides()>=FINISH_LINE){
						System.out.println(horse+"win!");
						exec.shutdownNow();
						break;
					}
				}
			}
		});
		
		for(int i=0;i<nHorses;i++){
			Horse horse = new Horse(barrier);
			horses.add(horse);
			exec.execute(horse);
		}
	}
	public static void main(String[] args) {
		int nHorses = 5;
		int pause = 200;
		new HoreseRace(nHorses, pause);
	}
}
