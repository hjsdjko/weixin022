package com.cl.dao;

import com.cl.entity.YuyuejiaoshiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YuyuejiaoshiView;


/**
 * 预约教师
 * 
 * @author 
 * @email 
 * @date 2024-03-29 10:47:26
 */
public interface YuyuejiaoshiDao extends BaseMapper<YuyuejiaoshiEntity> {
	
	List<YuyuejiaoshiView> selectListView(@Param("ew") Wrapper<YuyuejiaoshiEntity> wrapper);

	List<YuyuejiaoshiView> selectListView(Pagination page,@Param("ew") Wrapper<YuyuejiaoshiEntity> wrapper);
	
	YuyuejiaoshiView selectView(@Param("ew") Wrapper<YuyuejiaoshiEntity> wrapper);
	
	List<YuyuejiaoshiView> selectGroupBy(Pagination page,@Param("ew") Wrapper<YuyuejiaoshiEntity> wrapper);

}
