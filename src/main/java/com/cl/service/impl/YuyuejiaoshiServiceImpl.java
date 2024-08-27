package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.YuyuejiaoshiDao;
import com.cl.entity.YuyuejiaoshiEntity;
import com.cl.service.YuyuejiaoshiService;
import com.cl.entity.view.YuyuejiaoshiView;

@Service("yuyuejiaoshiService")
public class YuyuejiaoshiServiceImpl extends ServiceImpl<YuyuejiaoshiDao, YuyuejiaoshiEntity> implements YuyuejiaoshiService {
	
	@Override
	public PageUtils queryPageGroupBy(Map<String, Object> params, Wrapper<YuyuejiaoshiEntity> wrapper) {
		Page<YuyuejiaoshiView> page =new Query<YuyuejiaoshiView>(params).getPage();
        page.setRecords(baseMapper.selectGroupBy(page,wrapper));
    	PageUtils pageUtil = new PageUtils(page);
    	return pageUtil;
	}
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YuyuejiaoshiEntity> page = this.selectPage(
                new Query<YuyuejiaoshiEntity>(params).getPage(),
                new EntityWrapper<YuyuejiaoshiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YuyuejiaoshiEntity> wrapper) {
		  Page<YuyuejiaoshiView> page =new Query<YuyuejiaoshiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<YuyuejiaoshiView> selectListView(Wrapper<YuyuejiaoshiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YuyuejiaoshiView selectView(Wrapper<YuyuejiaoshiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
