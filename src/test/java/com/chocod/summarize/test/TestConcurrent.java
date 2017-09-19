package com.chocod.summarize.test;

import java.util.concurrent.CountDownLatch;

/**
 * synchronized ()  括号中的是锁对象。
 * @author dingzhiyong
 *
 */
public class TestConcurrent {
	private String flag[] = { "true" };  

	class NotifyThread extends Thread {  
	    public NotifyThread(String name) {  
	        super(name);  
	    }  
	
	    public void run() {  
	        try {  
	            sleep(3000);  
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        }  
	        synchronized (flag) {  
	            flag[0] = "false";  
	            flag.notifyAll(); 
	            flag.notify();
	        }  
	    }  
	};  
	
	class WaitThread extends Thread {  
	    public WaitThread(String name) {  
	        super(name);  
	    }  
	
	    public void run() {  
	        synchronized (flag) {  
	            while (flag[0] != "false") {  
	                System.out.println(getName() + " begin waiting!");  
	                long waitTime = System.currentTimeMillis();  
	                try {  
	                    flag.wait();  
	
	                } catch (InterruptedException e) {  
	                    e.printStackTrace();  
	                }  
	                waitTime = System.currentTimeMillis() - waitTime;  
	                System.out.println(getName() + " wait time :" + waitTime);  
	            }  
	            System.out.println(getName() + " end waiting!");  
	        }  
	    }  
	}  
	
		public static void main(String[] args) throws InterruptedException {  
	    System.out.println("Main Thread Run!");  
	    TestConcurrent test = new TestConcurrent();  
	    NotifyThread notifyThread = test.new NotifyThread("notify01");  
	    WaitThread waitThread01 = test.new WaitThread("waiter01");  
	    WaitThread waitThread02 = test.new WaitThread("waiter02");  
	    WaitThread waitThread03 = test.new WaitThread("waiter03");  
	    
	    waitThread01.start();  
	    waitThread02.start();  
	    waitThread03.start(); 
	    notifyThread.start();  
}  }
