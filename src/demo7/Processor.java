package demo7;

import java.util.Scanner;

public class Processor {
	
	public void produce() throws InterruptedException{
		synchronized (this) {
			System.out.println("Producer thread running...");
			
			// Passes lock to other thread.
			wait();
			System.out.println("Resumed.");
		}
	}
	
	public void consume() throws InterruptedException{
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(100);
		
		synchronized (this) {
			System.out.println("Lock Aquired- waiting for keyboard input.");
			scanner.nextLine();
			System.out.println("Return key pressed.");
			notify();
			
			// Proves lock is not passed back by calling notify().
			// Have to wait until code in consume() method is finishd.
			Thread.sleep(5000);
		}
	}

}
