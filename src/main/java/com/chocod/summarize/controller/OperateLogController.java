package com.chocod.summarize.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.chocod.pagination.ibatis.demo.TestService;
import com.chocod.pagination.ibatis.paginator.domain.PageBounds;
import com.chocod.summarize.dao.entity.OperateLog;
import com.chocod.summarize.dao.service.OperateLogDaoService;

@Controller
@RequestMapping(value="/operateLog")
public class OperateLogController {
	
	@Autowired
	private OperateLogDaoService operateLogDaoService;
	
	@RequestMapping(value="/getOperateLogIndex")
	public ModelAndView getOperateLogIndex(){
		ModelAndView mv = new ModelAndView("operateLogIndex");
		mv.addObject("hello", "world");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("operateType", "FCM推送");
		map.put("status", 2);
//		List<OperateLog> ol = operateLogDaoService.queryList(map);
		List<OperateLog> ol = operateLogDaoService.queryList(map,new PageBounds(2, 4));
		mv.addObject("operateLogs", ol);
		
		TestService ts = new TestService();
		ts.doTest();
		return mv;
	}

}
