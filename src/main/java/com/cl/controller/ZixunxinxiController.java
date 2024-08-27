package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;

import com.cl.entity.ZixunxinxiEntity;
import com.cl.entity.view.ZixunxinxiView;

import com.cl.service.ZixunxinxiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 咨询信息
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-29 10:47:27
 */
@RestController
@RequestMapping("/zixunxinxi")
public class ZixunxinxiController {
    @Autowired
    private ZixunxinxiService zixunxinxiService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZixunxinxiEntity zixunxinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xinlijiaoshi")) {
			zixunxinxi.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yonghu")) {
			zixunxinxi.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<ZixunxinxiEntity> ew = new EntityWrapper<ZixunxinxiEntity>();

		PageUtils page = zixunxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zixunxinxi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZixunxinxiEntity zixunxinxi, 
		HttpServletRequest request){
        EntityWrapper<ZixunxinxiEntity> ew = new EntityWrapper<ZixunxinxiEntity>();

		PageUtils page = zixunxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zixunxinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZixunxinxiEntity zixunxinxi){
       	EntityWrapper<ZixunxinxiEntity> ew = new EntityWrapper<ZixunxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zixunxinxi, "zixunxinxi")); 
        return R.ok().put("data", zixunxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZixunxinxiEntity zixunxinxi){
        EntityWrapper< ZixunxinxiEntity> ew = new EntityWrapper< ZixunxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zixunxinxi, "zixunxinxi")); 
		ZixunxinxiView zixunxinxiView =  zixunxinxiService.selectView(ew);
		return R.ok("查询咨询信息成功").put("data", zixunxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZixunxinxiEntity zixunxinxi = zixunxinxiService.selectById(id);
		zixunxinxi = zixunxinxiService.selectView(new EntityWrapper<ZixunxinxiEntity>().eq("id", id));
        return R.ok().put("data", zixunxinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZixunxinxiEntity zixunxinxi = zixunxinxiService.selectById(id);
		zixunxinxi = zixunxinxiService.selectView(new EntityWrapper<ZixunxinxiEntity>().eq("id", id));
        return R.ok().put("data", zixunxinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZixunxinxiEntity zixunxinxi, HttpServletRequest request){
    	zixunxinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(zixunxinxi);
        zixunxinxiService.insert(zixunxinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZixunxinxiEntity zixunxinxi, HttpServletRequest request){
    	zixunxinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(zixunxinxi);
        zixunxinxiService.insert(zixunxinxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZixunxinxiEntity zixunxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zixunxinxi);
        zixunxinxiService.updateById(zixunxinxi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zixunxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	






    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,ZixunxinxiEntity zixunxinxi, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("xinlijiaoshi")) {
            zixunxinxi.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("yonghu")) {
            zixunxinxi.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<ZixunxinxiEntity> ew = new EntityWrapper<ZixunxinxiEntity>();
        int count = zixunxinxiService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zixunxinxi), params), params));
        return R.ok().put("data", count);
    }


}
