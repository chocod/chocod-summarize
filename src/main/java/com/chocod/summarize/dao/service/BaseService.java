package com.chocod.summarize.dao.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.chocod.pagination.ibatis.paginator.domain.Order;
import com.chocod.pagination.ibatis.paginator.domain.PageBounds;
import com.chocod.pagination.ibatis.paginator.domain.PageList;
import com.chocod.pagination.ibatis.paginator.domain.Paginator;
import com.chocod.summarize.dao.mapper.BaseMapper;


/**
 * Service基础类
 * 
 * @author chensheng
 *
 * @param <T>
 *            Model对象
 * @param <M>
 *            Mapper对象
 */
@SuppressWarnings("rawtypes")
public abstract class BaseService<T, M extends BaseMapper<T>> {

	protected M mapper;

	/**
	 * 根据ID查询对象
	 * 
	 * @param id
	 *            数据ID值
	 * 
	 */
	public T selectById(Long id) {
		return (T) mapper.selectByPrimaryKey(id);
	}

	/**
	 * 新增 (如果为空,插入默认值)
	 * 
	 * @param record
	 *            Model对象
	 * 
	 * @return 影响行数
	 */
	public int insert(T record) {
		return mapper.insert(record);
	}
	

	/**
	 * 查询记录数
	 * 
	 * @param queryMap
	 *            查询条件
	 */
	public int count(Map queryMap) {
		return mapper.count(queryMap);
	}

	/**
	 * 根据条件分页查询列表及分页信息
	 * 
	 * @param queryMap
	 *            查询条件
	 * 
	 * @param PageBounds
	 *            分页参数
	 */
	@SuppressWarnings("unchecked")
	public PageList<T> queryForPage(Map queryMap, PageBounds page) {
		page.setContainsTotalCount(true);
		PageList<T> list = (PageList) mapper.select(queryMap, page);
		return list;
	}
	
	
	/**
	 * 根据条件分页查询列表及分页信息(分页查询优化)
	 */
	public PageList<T> queryForPage(Map queryMap, int page,int limit) {
		List<T> list = this.queryList(queryMap, new PageBounds(page,limit));
		int count = this.count(queryMap);
		return new PageList<T>(list,new Paginator(page,limit,count));
	}

	/**
	 * 根据条件分页查询列表
	 * 
	 * @param queryMap
	 *            查询条件
	 * 
	 * @param PageBounds
	 *            分页参数
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryList(Map queryMap, PageBounds page) {
		page.setContainsTotalCount(false);
		List<T> list = mapper.select(queryMap, page);
		return list;
	}

	/**
	 * 根据条件分页查询列表
	 * 
	 * @param offset
	 *            从多少条开始
	 * @param limit
	 *            查询多少条数据
	 * @param Order
	 *            排序字符串 如：id.asc 或者id.asc,name.desc
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryList(Map queryMap, int offset, int limit, String order) {
		PageBounds pageBound = new PageBounds(new RowBounds(offset, limit));
		pageBound.setOrders(Order.formString(order));
		List<T> list = mapper.select(queryMap, pageBound);
		return list;
	}

	/**
	 * 查询前N条数据
	 * 
	 * @param queryMap
	 *            查询条件
	 * @param limit
	 *            查询记录数
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryTop(Map queryMap, int limit) {
		List<T> list = mapper.select(queryMap, new PageBounds(limit));
		return list;
	}

	/**
	 * 查询前N条数据
	 * 
	 * @param queryMap
	 *            查询条件
	 * @param limit
	 *            查询记录数
	 * @param Order
	 *            排序字符串 如：id.asc 或者id.asc,name.desc
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryTop(Map queryMap, int limit, String order) {
		PageBounds pageBounds = new PageBounds(limit);
		pageBounds.setOrders(Order.formString(order));
		List<T> list = mapper.select(queryMap, pageBounds);
		return list;
	}

	/**
	 * 根据条件查询所有数据
	 * 
	 * @param queryMap
	 *            查询条件
	 */
	public List<T> queryList(Map queryMap) {
		List<T> list = mapper.select(queryMap);
		return list;
	}
	
	
	public abstract void setMapper(M mapper);
	
}
