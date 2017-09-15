package com.chocod.summarize.dao.mapper;

import java.util.List;
import java.util.Map;

import com.chocod.pagination.ibatis.paginator.domain.PageBounds;



/**
 * BaseMapper 基础接口
 *
 */
public interface BaseMapper<T> {

	int deleteByPrimaryKey(Long id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
	
    List<T> select(Map params);
    
	List<T> select(Map params,PageBounds pb);
	
	int count(Map params);
}
