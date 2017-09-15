package com.chocod.summarize.api;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chocod.pagination.ibatis.paginator.domain.PageBounds;
import com.chocod.pagination.ibatis.paginator.domain.PageList;
import com.chocod.summarize.dao.entity.OperateLog;
import com.chocod.summarize.dao.service.OperateLogDaoService;
import com.google.gson.Gson;


@Component
@Path("/operateLog")
@Produces("text/plain")
public class OperateLogRestApi {
	
	@Autowired
	private OperateLogDaoService operateLogDaoService;
	
	private static Logger logger = LoggerFactory.getLogger(OperateLogRestApi.class);
	
	private Gson gson = new Gson();
	/**
	 * 商家延迟发货率定时任务
	 * 1
	 * 100-200
	 */
	@GET
	@Path("/getById/{id}")
	public String getById(@PathParam("id") Long id){
		OperateLog ol = operateLogDaoService.selectById(id);
		return gson.toJson(ol);
	}
	
	@GET
	@Path("/getByOperateType/{operateType}")
	public String getByOperateType(@PathParam("operateType") String operateType){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("operateType", operateType);
		List<OperateLog> ol = operateLogDaoService.queryList(map);
		return gson.toJson(ol);
	}
	
	@GET
	@Path("/getPageByOperateType/{operateType}/{offset}/{limit}")
	public String getPageByOperateType(@PathParam("operateType") String operateType,
			@PathParam("offset") Integer offset,
			@PathParam("limit") Integer limit){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("operateType", operateType);
		PageList<OperateLog> ol = operateLogDaoService.queryForPage(map,new PageBounds(new RowBounds(offset, limit)));
		return gson.toJson(ol);
	}
}
