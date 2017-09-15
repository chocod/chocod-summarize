package com.chocod.summarize.test;

import org.junit.Before;
import org.junit.Test;

import com.chocod.summarize.config.SystemEnv;


/**
 * 打包完成接口事务
 * 	1. 绑定物流箱
 * 		波次单维度
 * 	2. 释放载具
 * 		波次单维度
 * 	3. 释放道口
 * 		波次单维度
 * 	4. 通知订单缺货信息 
 * 		订单维度
 * 	5. 回调拣货商品明细
 * 		波次单维度
 * @author liuhongwei
 */
public class PackCompleteApiTransactionTest {
	
	
	
	@Before
	public void init(){
		
		SystemEnv.addProperty("fncache.zkaddress", "10.202.249.175:2181;10.202.249.176:2181;10.202.249.177:2181");
		
		SystemEnv.addProperty("fncache.appname.freshbackend", "fresh_backend_beta");
		
		SystemEnv.addProperty("fncache.pool.init", "2");
		
		SystemEnv.addProperty("fncache.pool.min", "2");
		
		SystemEnv.addProperty("fncache.pool.max", "4");
	}
	
	
	@Test
	public void testData(){
		
		
	}
	

}
