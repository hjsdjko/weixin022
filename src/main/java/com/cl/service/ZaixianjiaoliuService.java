package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ZaixianjiaoliuEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ZaixianjiaoliuView;


/**
 * 在线交流
 *
 * @author 
 * @email 
 * @date 2024-03-29 10:47:27
 */
public interface ZaixianjiaoliuService extends IService<ZaixianjiaoliuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZaixianjiaoliuView> selectListView(Wrapper<ZaixianjiaoliuEntity> wrapper);
   	
   	ZaixianjiaoliuView selectView(@Param("ew") Wrapper<ZaixianjiaoliuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ZaixianjiaoliuEntity> wrapper);
   	
   	PageUtils queryPageGroupBy(Map<String, Object> params,Wrapper<ZaixianjiaoliuEntity> wrapper);

}

