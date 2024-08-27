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

import com.cl.entity.YuyuejiaoshiEntity;
import com.cl.entity.view.YuyuejiaoshiView;

import com.cl.service.YuyuejiaoshiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 预约教师
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-29 10:47:26
 */
@RestController
@RequestMapping("/yuyuejiaoshi")
public class YuyuejiaoshiController {
    @Autowired
    private YuyuejiaoshiService yuyuejiaoshiService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YuyuejiaoshiEntity yuyuejiaoshi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xinlijiaoshi")) {
			yuyuejiaoshi.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yonghu")) {
			yuyuejiaoshi.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<YuyuejiaoshiEntity> ew = new EntityWrapper<YuyuejiaoshiEntity>();

		PageUtils page = yuyuejiaoshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuyuejiaoshi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YuyuejiaoshiEntity yuyuejiaoshi, 
		HttpServletRequest request){
        EntityWrapper<YuyuejiaoshiEntity> ew = new EntityWrapper<YuyuejiaoshiEntity>();

		PageUtils page = yuyuejiaoshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuyuejiaoshi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YuyuejiaoshiEntity yuyuejiaoshi){
       	EntityWrapper<YuyuejiaoshiEntity> ew = new EntityWrapper<YuyuejiaoshiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yuyuejiaoshi, "yuyuejiaoshi")); 
        return R.ok().put("data", yuyuejiaoshiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YuyuejiaoshiEntity yuyuejiaoshi){
        EntityWrapper< YuyuejiaoshiEntity> ew = new EntityWrapper< YuyuejiaoshiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yuyuejiaoshi, "yuyuejiaoshi")); 
		YuyuejiaoshiView yuyuejiaoshiView =  yuyuejiaoshiService.selectView(ew);
		return R.ok("查询预约教师成功").put("data", yuyuejiaoshiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YuyuejiaoshiEntity yuyuejiaoshi = yuyuejiaoshiService.selectById(id);
		yuyuejiaoshi = yuyuejiaoshiService.selectView(new EntityWrapper<YuyuejiaoshiEntity>().eq("id", id));
        return R.ok().put("data", yuyuejiaoshi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YuyuejiaoshiEntity yuyuejiaoshi = yuyuejiaoshiService.selectById(id);
		yuyuejiaoshi = yuyuejiaoshiService.selectView(new EntityWrapper<YuyuejiaoshiEntity>().eq("id", id));
        return R.ok().put("data", yuyuejiaoshi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody YuyuejiaoshiEntity yuyuejiaoshi, HttpServletRequest request){
    	yuyuejiaoshi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(yuyuejiaoshi);
        yuyuejiaoshiService.insert(yuyuejiaoshi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody YuyuejiaoshiEntity yuyuejiaoshi, HttpServletRequest request){
    	yuyuejiaoshi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(yuyuejiaoshi);
        yuyuejiaoshiService.insert(yuyuejiaoshi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody YuyuejiaoshiEntity yuyuejiaoshi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yuyuejiaoshi);
        yuyuejiaoshiService.updateById(yuyuejiaoshi);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<YuyuejiaoshiEntity> list = new ArrayList<YuyuejiaoshiEntity>();
        for(Long id : ids) {
            YuyuejiaoshiEntity yuyuejiaoshi = yuyuejiaoshiService.selectById(id);
            yuyuejiaoshi.setSfsh(sfsh);
            yuyuejiaoshi.setShhf(shhf);
            list.add(yuyuejiaoshi);
        }
        yuyuejiaoshiService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        yuyuejiaoshiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
