package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.YuyuejiaoshiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YuyuejiaoshiView;


/**
 * 预约教师
 *
 * @author 
 * @email 
 * @date 2024-03-29 10:47:26
 */
public interface YuyuejiaoshiService extends IService<YuyuejiaoshiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YuyuejiaoshiView> selectListView(Wrapper<YuyuejiaoshiEntity> wrapper);
   	
   	YuyuejiaoshiView selectView(@Param("ew") Wrapper<YuyuejiaoshiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YuyuejiaoshiEntity> wrapper);
   	
   	PageUtils queryPageGroupBy(Map<String, Object> params,Wrapper<YuyuejiaoshiEntity> wrapper);

}

