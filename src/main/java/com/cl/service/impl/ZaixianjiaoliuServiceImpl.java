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


import com.cl.dao.ZaixianjiaoliuDao;
import com.cl.entity.ZaixianjiaoliuEntity;
import com.cl.service.ZaixianjiaoliuService;
import com.cl.entity.view.ZaixianjiaoliuView;

@Service("zaixianjiaoliuService")
public class ZaixianjiaoliuServiceImpl extends ServiceImpl<ZaixianjiaoliuDao, ZaixianjiaoliuEntity> implements ZaixianjiaoliuService {
	
	@Override
	public PageUtils queryPageGroupBy(Map<String, Object> params, Wrapper<ZaixianjiaoliuEntity> wrapper) {
		Page<ZaixianjiaoliuView> page =new Query<ZaixianjiaoliuView>(params).getPage();
        page.setRecords(baseMapper.selectGroupBy(page,wrapper));
    	PageUtils pageUtil = new PageUtils(page);
    	return pageUtil;
	}
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZaixianjiaoliuEntity> page = this.selectPage(
                new Query<ZaixianjiaoliuEntity>(params).getPage(),
                new EntityWrapper<ZaixianjiaoliuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ZaixianjiaoliuEntity> wrapper) {
		  Page<ZaixianjiaoliuView> page =new Query<ZaixianjiaoliuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<ZaixianjiaoliuView> selectListView(Wrapper<ZaixianjiaoliuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ZaixianjiaoliuView selectView(Wrapper<ZaixianjiaoliuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
