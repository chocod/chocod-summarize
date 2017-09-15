package com.chocod.summarize.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
		return mv;
	}

}
