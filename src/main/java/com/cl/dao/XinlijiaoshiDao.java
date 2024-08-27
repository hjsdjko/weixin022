package com.cl.dao;

import com.cl.entity.XinlijiaoshiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.XinlijiaoshiView;


/**
 * 心理教师
 * 
 * @author 
 * @email 
 * @date 2024-03-29 10:47:26
 */
public interface XinlijiaoshiDao extends BaseMapper<XinlijiaoshiEntity> {
	
	List<XinlijiaoshiView> selectListView(@Param("ew") Wrapper<XinlijiaoshiEntity> wrapper);

	List<XinlijiaoshiView> selectListView(Pagination page,@Param("ew") Wrapper<XinlijiaoshiEntity> wrapper);
	
	XinlijiaoshiView selectView(@Param("ew") Wrapper<XinlijiaoshiEntity> wrapper);
	
	List<XinlijiaoshiView> selectGroupBy(Pagination page,@Param("ew") Wrapper<XinlijiaoshiEntity> wrapper);

}
