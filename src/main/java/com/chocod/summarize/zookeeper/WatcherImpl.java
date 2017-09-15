package com.chocod.summarize.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;

public class WatcherImpl implements Watcher {

	@Override
	public void process(WatchedEvent event) {
		System.out.println("path:"+event.getPath());
		// TODO Auto-generated method stub
		if(event.getType() == EventType.NodeCreated){
            System.out.println("创建节点");
        }
        if(event.getType() == EventType.NodeDataChanged){
            System.out.println("节点改变");
        }
        if(event.getType() == EventType.NodeChildrenChanged){
            System.out.println("子节点节点改变");
        }
        if(event.getType() == EventType.NodeDeleted){
            System.out.println("节点删除");
        }
        
        try {
			ZooKeeperOperator.getZk().exists(event.getPath(), this);
		} catch (KeeperException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

}
