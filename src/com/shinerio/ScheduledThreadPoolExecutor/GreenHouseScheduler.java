package com.shinerio.ScheduledThreadPoolExecutor;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GreenHouseScheduler {
	private volatile boolean light = false;
	private volatile boolean water = false;
	private String themostat = "Day";

	public synchronized String getThermostat() {
		return themostat;
	}

	public synchronized void setThermostat(String value) {
		themostat = value;
	}

	ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(10);

	public void schedule(Runnable event, long delay) {
		scheduler.schedule(event, delay, TimeUnit.MILLISECONDS);
	}

	public void repeat(Runnable event, long initialDelay, long period) {
		scheduler.scheduleAtFixedRate(event, initialDelay, period,
				TimeUnit.MILLISECONDS);
	}

	class LightOn implements Runnable {

		@Override
		public synchronized void run() {
			System.out.println("turn on lights!");
			light = true;
		}

	}

	class LightOff implements Runnable {

		@Override
		public void run() {
			System.out.println("Turn off lights");
			light = false;
		}

	}
	
	class  WaterOn implements Runnable{

		@Override
		public void run() {
			System.out.println("Turn greenhouse water on");
			water = true;
		}
		
	}
	
	class WaterOff implements Runnable{
		@Override
		public  void run() {
			System.out.println("Turn greenhouse water off");
			water = false;
		}
	}
	
	class ThermostatNight implements Runnable{

		@Override
		public void run() {
			System.out.println("Thermostat to night setting");
		}
		
	}
	
	class ThermostatDay implements Runnable{

		@Override
		public void run() {
			System.out.println("Thermostat to day setting");
		}
	}
	
	class Bell implements Runnable{

		@Override
		public void run() {
			System.out.println("Belling!");
		}
		
	}
	
	class Terminate implements Runnable{

		@Override
		public void run() {
			System.out.println("Terminationg");
			scheduler.shutdownNow();
		}
		
	}
	
	public static void main(String[] args) {
		GreenHouseScheduler gh = new GreenHouseScheduler();
		gh.schedule(gh.new Terminate(),5000);
		gh.repeat(gh.new ThermostatNight(), 0, 2000);
		gh.repeat(gh.new ThermostatDay(), 0, 1400);
		gh.repeat(gh.new LightOn(), 0, 200);
		gh.repeat(gh.new LightOff(), 0, 400);
		gh.repeat(gh.new WaterOn(), 0, 600);
		gh.repeat(gh.new WaterOff(), 0, 800);
	}
}
