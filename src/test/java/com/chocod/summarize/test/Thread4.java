package com.chocod.summarize.test;

import java.lang.Thread.UncaughtExceptionHandler;


/**
 * 实验：线程join，合并
 * @author dingzhiyong
 * 如果在B线程中调用了A线程的join方法，则直到A线程执行完毕，才会继续执行B线程。
 *
 */
public class Thread4 extends Thread {  
    private Thread parent;  
    public Thread4(Thread parent){  
        this.parent = parent;  
    }  
      
    public void run() {  
        while (true) {
            System.out.println("sub thread is running...");  
            long now = System.currentTimeMillis();  
            while (System.currentTimeMillis() - now < 2000) {  
                // 为了避免Thread.sleep()而需要捕获InterruptedException而带来的理解上的困惑,  
                // 此处用这种方法空转2秒  
            }  
//            parent.interrupt();
            
            if(true){
            	throw new RuntimeException("12312312");
            }
            return;
        }  
    }  
      
    public static void main(String[] args){  
        Thread4 t4 = new Thread4(Thread.currentThread()); 
        t4.setName("t4 thread");
        t4.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				// TODO Auto-generated method stub
				System.out.println("发生异常");
				System.out.println("当前线程："+Thread.currentThread().getName());
				System.out.println("入参线程："+t.getName());
				System.out.println(t==Thread.currentThread());
				e.printStackTrace();
			}
		});
        t4.start(); 
//        System.out.println("立即打印失败《");
//        printStackTrace(t4.getStackTrace());
//        System.out.println("立即打印失败》");
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {			
		}
//        System.out.println("稍后打印失败《");
//        printStackTrace(t4.getStackTrace());
//        System.out.println("稍后打印失败》");
        
        System.out.println("当前线程："+Thread.currentThread().getName());
        try {  
        	System.out.println("behand join");
            t4.join();
            
            System.out.println("after join");
        } catch (InterruptedException e) {  
            System.out.println("Parent thread will die...");  
        }  
    }  
    
    private static void printStackTrace(StackTraceElement[] stackArray){
    	StringBuffer sb = new StringBuffer();         
        for (int i = 0; i < stackArray.length; i++) {  
            StackTraceElement element = stackArray[i];  
            sb.append(element.toString() + "\n");  
        }  
        System.out.println(sb);
    }
}  
