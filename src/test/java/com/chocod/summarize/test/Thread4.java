package com.chocod.summarize.test;

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
            return;
        }  
    }  
      
    public static void main(String[] args){  
        Thread4 t4 = new Thread4(Thread.currentThread());  
        t4.start();  
        try {  
        	System.out.println("behand join");
            t4.join();
            System.out.println("after join");
        } catch (InterruptedException e) {  
            System.out.println("Parent thread will die...");  
        }  
    }  
}  
