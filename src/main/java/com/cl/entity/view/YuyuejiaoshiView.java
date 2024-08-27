package com.cl.entity.view;

import com.cl.entity.YuyuejiaoshiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.cl.utils.EncryptUtil;
 

/**
 * 预约教师
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-03-29 10:47:26
 */
@TableName("yuyuejiaoshi")
public class YuyuejiaoshiView  extends YuyuejiaoshiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public YuyuejiaoshiView(){
	}
 
 	public YuyuejiaoshiView(YuyuejiaoshiEntity yuyuejiaoshiEntity){
 	try {
			BeanUtils.copyProperties(this, yuyuejiaoshiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
