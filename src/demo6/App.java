package demo6;

import java.util.*;
import java.util.concurrent.*;

public class App {
	
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}
	
	private static void producer() throws InterruptedException{
		Random random = new Random();
		
		while(true){
			queue.put(random.nextInt(100));
		}
	}
	
	private static void consumer() throws InterruptedException{
		Random random = new Random();
		
		while(true){
			
			if (random.nextInt(1) == 0) {
				Integer value = queue.take();
				
				System.out.println("Taken value: "+ value +";Queue size is:  "+ queue.size());
			}
			
		}
	}
}
