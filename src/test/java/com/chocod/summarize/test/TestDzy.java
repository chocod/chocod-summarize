package com.chocod.summarize.test;

import java.util.HashMap;
import java.util.List;

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
public class TestDzy {
	
	@Autowired
	private OperateLogDaoService operateLogDaoService;
	
	private Gson gson = new Gson();
	
//	@Test
	public void testSystemEnv(){
		
		String zkAdr = SystemEnv.getProperty("zk.register.address");
		
		System.out.println(zkAdr);
		
		System.out.println("isBetaEnv:"+SystemEnv.isBetaEnv());
		System.out.println("isLocalEnv:"+SystemEnv.isLocalEnv());
		System.out.println("isOnlineEnv:"+SystemEnv.isOnlineEnv());
	}
	
	
	@Test
	public void testOperateLogDaoService(){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("operateType", "FCM推送");
		map.put("status", 2);
		List<OperateLog> ol = operateLogDaoService.queryList(map);
		System.out.println(gson.toJson(ol));
	}
}
