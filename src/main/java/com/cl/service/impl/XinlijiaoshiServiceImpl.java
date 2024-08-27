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


import com.cl.dao.XinlijiaoshiDao;
import com.cl.entity.XinlijiaoshiEntity;
import com.cl.service.XinlijiaoshiService;
import com.cl.entity.view.XinlijiaoshiView;

@Service("xinlijiaoshiService")
public class XinlijiaoshiServiceImpl extends ServiceImpl<XinlijiaoshiDao, XinlijiaoshiEntity> implements XinlijiaoshiService {
	
	@Override
	public PageUtils queryPageGroupBy(Map<String, Object> params, Wrapper<XinlijiaoshiEntity> wrapper) {
		Page<XinlijiaoshiView> page =new Query<XinlijiaoshiView>(params).getPage();
        page.setRecords(baseMapper.selectGroupBy(page,wrapper));
    	PageUtils pageUtil = new PageUtils(page);
    	return pageUtil;
	}
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XinlijiaoshiEntity> page = this.selectPage(
                new Query<XinlijiaoshiEntity>(params).getPage(),
                new EntityWrapper<XinlijiaoshiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<XinlijiaoshiEntity> wrapper) {
		  Page<XinlijiaoshiView> page =new Query<XinlijiaoshiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<XinlijiaoshiView> selectListView(Wrapper<XinlijiaoshiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public XinlijiaoshiView selectView(Wrapper<XinlijiaoshiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
