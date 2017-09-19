package com.chocod.summarize.test;
/**
 * 实验：线程中断
 * @author dingzhiyong
 *
 */
public class Thread3 extends Thread{  
    public void run(){  
        while(true){  
            if(Thread.interrupted()){  
                System.out.println("Someone interrupted me.");
//  代码块1
                return;
                
            }  
            else{  
                System.out.println("Going...");  
            }  
            long now = System.currentTimeMillis(); 
            
//	代码块2            
//            while(System.currentTimeMillis()-now<1000){  
//                // 为了避免Thread.sleep()而需要捕获InterruptedException而带来的理解上的困惑,  
//                // 此处用这种方法空转1秒              	
//            } 
            
//  代码块3            
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("InterruptedException..."); 
			}
        }  
    }  
      
    //当仅注释代码块3时，Thread.interrupted()会被检测到一次，且仅为1次。
    //
    
    
    
    public static void main(String[] args) throws InterruptedException {  
        Thread3 t = new Thread3();  
        t.start();
        Thread.sleep(3000);  
        t.interrupt(); 
        
    }  
} 
