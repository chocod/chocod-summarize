package com.chocod.summarize.zookeeper;

import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;

public class ZooKeeperOperator extends AbstractZooKeeper {  
    
  private static Log log = LogFactory.getLog(ZooKeeperOperator.class.getName());  

  /** 
   *  
   *<b>function:</b>创建持久态的znode,比支持多层创建.比如在创建/parent/child的情况下,无/parent.无法通过 
   *@author cuiran 
   *@createDate 2013-01-16 15:08:38 
   *@param path 
   *@param data 
   *@throws KeeperException 
   *@throws InterruptedException 
   */  
  public void create(String path,byte[] data)throws KeeperException, InterruptedException{  
      /** 
       * 此处采用的是CreateMode是PERSISTENT  表示The znode will not be automatically deleted upon client's disconnect. 
       * EPHEMERAL 表示The znode will be deleted upon the client's disconnect. 
       */   
      this.zooKeeper.create(path, data, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);  
  }  
  /** 
   *  
   *<b>function:</b>获取节点信息 
   *@author cuiran 
   *@createDate 2013-01-16 15:17:22 
   *@param path 
   *@throws KeeperException 
   *@throws InterruptedException 
   */  
  public void getChild(String path) throws KeeperException, InterruptedException{     
      try{  
          List<String> list=this.zooKeeper.getChildren(path, false);  
          if(list.isEmpty()){  
              log.debug(path+"中没有节点");  
          }else{  
              log.debug(path+"中存在节点");  
              for(String child:list){  
                  log.debug("节点为："+child);  
              }  
          }  
      }catch (KeeperException.NoNodeException e) {  
          // TODO: handle exception  
           throw e;     

      }  
  }  
    
  public byte[] getData(String path) throws KeeperException, InterruptedException {     
      return  this.zooKeeper.getData(path, false,null);     
  }    
    
   public static void main(String[] args) {  
       
	   try {     
              ZooKeeperOperator zkoperator = new ZooKeeperOperator();     
              zkoperator.connect("192.168.209.129");  
              ZooKeeper zk = zkoperator.getZk();
              byte[] bt = zk.getData("/test", new WatcherImpl(), null);
              if(bt != null){
  				for(int i=0;i<bt.length;i++){
  					System.out.print((char)bt[i]);
  				}
  			}	
              while(true){
            	  
              }
//              zk.close();
              
          } catch (Exception e) {     
              e.printStackTrace();     
          }     
        
	   /*
	   ZkClient zkClient = new ZkClient("192.168.209.129:2181");
	   String path = "/testZkClient";
	   String data = "hello world!";
	   String newdata = "我是中文";
	   boolean s = zkClient.exists(path);
	   
	   if(!s){
		   zkClient.create(path, data, CreateMode.PERSISTENT);
	   }else{
		   zkClient.writeData(path, newdata);
	   }
	   String readRs = zkClient.readData(path);
	   System.out.println(s);
	   System.out.println(readRs);
	   zkClient.subscribeDataChanges(path, listener);
      */
  }  
}  