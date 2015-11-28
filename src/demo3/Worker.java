package demo3;

import java.util.*;

public class Worker {
	
	private Random random = new Random();
	
	private Object lock1 = new Object();
	private Object lock2 = new Object();

	
	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();
	
	public void stageOne(){
		
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		synchronized (lock1) {
			list1.add(random.nextInt(100));
		}
		
	}
	
	public void stageTwo(){
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		synchronized (lock2) {
			list2.add(random.nextInt(100));
		}
	}
	
	public void process(){
		for (int i = 0; i < 1000; i++) {
			stageOne();
			stageTwo();
		}
	}
	
	public void main(String[] args) {	
		System.out.println("Working...");
		long start = System.currentTimeMillis();
		
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				process();
				
			}	
		});
		
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				process();
				
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Time taken " + (end - start));
		System.out.println("List 1: "+ list1.size() + "; List 2:" + list2.size());
		
	}

}
