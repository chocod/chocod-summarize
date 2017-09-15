package com.chocod.summarize.dao.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chocod.pagination.ibatis.paginator.domain.PageBounds;
import com.chocod.pagination.ibatis.paginator.domain.PageList;
import com.chocod.summarize.dao.entity.OperateLog;
import com.chocod.summarize.dao.mapper.OperateLogMapper;

/**
 * 订单服务service
 * 
 * @author CHH
 * @date 2017-2-21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OperateLogDaoService extends BaseService<OperateLog, OperateLogMapper>{

	private final static Logger logger = LoggerFactory.getLogger(OperateLogDaoService.class);
	
	@Autowired
	@Override
	public void setMapper(OperateLogMapper mapper) {
		this.mapper = mapper;
	}

	
	public List<OperateLog> selectByMap(Map map) {
		return this.mapper.select(map);
	}

	
	public PageList<OperateLog> selectByMap(Map map,PageBounds pb) {
		return (PageList<OperateLog>)this.mapper.select(map,pb);
	}
	
	

}
