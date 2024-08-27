package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ZixunxinxiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ZixunxinxiView;


/**
 * 咨询信息
 *
 * @author 
 * @email 
 * @date 2024-03-29 10:47:27
 */
public interface ZixunxinxiService extends IService<ZixunxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZixunxinxiView> selectListView(Wrapper<ZixunxinxiEntity> wrapper);
   	
   	ZixunxinxiView selectView(@Param("ew") Wrapper<ZixunxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ZixunxinxiEntity> wrapper);
   	
   	PageUtils queryPageGroupBy(Map<String, Object> params,Wrapper<ZixunxinxiEntity> wrapper);

}

