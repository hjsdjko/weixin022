package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.XinlijiaoshiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.XinlijiaoshiView;


/**
 * 心理教师
 *
 * @author 
 * @email 
 * @date 2024-03-29 10:47:26
 */
public interface XinlijiaoshiService extends IService<XinlijiaoshiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XinlijiaoshiView> selectListView(Wrapper<XinlijiaoshiEntity> wrapper);
   	
   	XinlijiaoshiView selectView(@Param("ew") Wrapper<XinlijiaoshiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<XinlijiaoshiEntity> wrapper);
   	
   	PageUtils queryPageGroupBy(Map<String, Object> params,Wrapper<XinlijiaoshiEntity> wrapper);

}

