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

import com.cl.entity.ZaixianjiaoliuEntity;
import com.cl.entity.view.ZaixianjiaoliuView;

import com.cl.service.ZaixianjiaoliuService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 在线交流
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-29 10:47:27
 */
@RestController
@RequestMapping("/zaixianjiaoliu")
public class ZaixianjiaoliuController {
    @Autowired
    private ZaixianjiaoliuService zaixianjiaoliuService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZaixianjiaoliuEntity zaixianjiaoliu,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xinlijiaoshi")) {
			zaixianjiaoliu.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yonghu")) {
			zaixianjiaoliu.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<ZaixianjiaoliuEntity> ew = new EntityWrapper<ZaixianjiaoliuEntity>();

		PageUtils page = zaixianjiaoliuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zaixianjiaoliu), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZaixianjiaoliuEntity zaixianjiaoliu, 
		HttpServletRequest request){
        EntityWrapper<ZaixianjiaoliuEntity> ew = new EntityWrapper<ZaixianjiaoliuEntity>();

		PageUtils page = zaixianjiaoliuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zaixianjiaoliu), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZaixianjiaoliuEntity zaixianjiaoliu){
       	EntityWrapper<ZaixianjiaoliuEntity> ew = new EntityWrapper<ZaixianjiaoliuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zaixianjiaoliu, "zaixianjiaoliu")); 
        return R.ok().put("data", zaixianjiaoliuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZaixianjiaoliuEntity zaixianjiaoliu){
        EntityWrapper< ZaixianjiaoliuEntity> ew = new EntityWrapper< ZaixianjiaoliuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zaixianjiaoliu, "zaixianjiaoliu")); 
		ZaixianjiaoliuView zaixianjiaoliuView =  zaixianjiaoliuService.selectView(ew);
		return R.ok("查询在线交流成功").put("data", zaixianjiaoliuView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZaixianjiaoliuEntity zaixianjiaoliu = zaixianjiaoliuService.selectById(id);
		zaixianjiaoliu = zaixianjiaoliuService.selectView(new EntityWrapper<ZaixianjiaoliuEntity>().eq("id", id));
        return R.ok().put("data", zaixianjiaoliu);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZaixianjiaoliuEntity zaixianjiaoliu = zaixianjiaoliuService.selectById(id);
		zaixianjiaoliu = zaixianjiaoliuService.selectView(new EntityWrapper<ZaixianjiaoliuEntity>().eq("id", id));
        return R.ok().put("data", zaixianjiaoliu);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZaixianjiaoliuEntity zaixianjiaoliu, HttpServletRequest request){
    	zaixianjiaoliu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(zaixianjiaoliu);
        zaixianjiaoliuService.insert(zaixianjiaoliu);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZaixianjiaoliuEntity zaixianjiaoliu, HttpServletRequest request){
    	zaixianjiaoliu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(zaixianjiaoliu);
        zaixianjiaoliuService.insert(zaixianjiaoliu);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZaixianjiaoliuEntity zaixianjiaoliu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zaixianjiaoliu);
        zaixianjiaoliuService.updateById(zaixianjiaoliu);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zaixianjiaoliuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
