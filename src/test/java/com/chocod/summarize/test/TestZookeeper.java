package com.chocod.summarize.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chocod.summarize.config.SystemEnv;
import com.chocod.summarize.dao.entity.OperateLog;
import com.chocod.summarize.dao.service.OperateLogDaoService;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/applicationContext.xml"})
public class TestZookeeper implements Watcher {
	
	
	private Gson gson = new Gson();
	
	private ZooKeeper zk;
	
	protected CountDownLatch countDownLatch=new CountDownLatch(1);
	
	@Test
	public void testZkConnection() throws Exception{
		try {
			zk = new ZooKeeper("192.168.209.129:2181", 5000, this);			
			System.out.println("before await");
			countDownLatch.await();
			System.out.println("after await");
			byte[] bt = zk.getData("/test", false, null);
			if(bt != null){
				for(int i=0;i<bt.length;i++){
					System.out.println((char)bt[i]);
				}
			}		
//			zk.exists(path, true)
			zk.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void process(WatchedEvent event) {
		System.out.println("found event");
		// TODO Auto-generated method stub
		if(event.getState()==KeeperState.SyncConnected){  
			System.out.println("found event:is KeeperState.SyncConnected");
            countDownLatch.countDown();  
        } 		
	}
	
	public static void main(String[] args){
		System.out.println("begin...");
	}
	
}
