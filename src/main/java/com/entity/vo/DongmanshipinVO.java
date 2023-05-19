package com.entity.vo;

import com.entity.DongmanshipinEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 动漫视频
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email 
 * @date 2023-02-14 16:48:50
 */
public class DongmanshipinVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 视频名称
	 */
	
	private String shipinmingcheng;
		
	/**
	 * 视频分类
	 */
	
	private String shipinfenlei;
		
	/**
	 * 视频封面
	 */
	
	private String shipinfengmian;
		
	/**
	 * 视频
	 */
	
	private String shipin;
		
	/**
	 * 发布日期
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date faburiqi;
		
	/**
	 * 视频介绍
	 */
	
	private String shipinjieshao;
		
	/**
	 * 最近点击时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date clicktime;
		
	/**
	 * 点击次数
	 */
	
	private Integer clicknum;
				
	
	/**
	 * 设置：视频名称
	 */
	 
	public void setShipinmingcheng(String shipinmingcheng) {
		this.shipinmingcheng = shipinmingcheng;
	}
	
	/**
	 * 获取：视频名称
	 */
	public String getShipinmingcheng() {
		return shipinmingcheng;
	}
				
	
	/**
	 * 设置：视频分类
	 */
	 
	public void setShipinfenlei(String shipinfenlei) {
		this.shipinfenlei = shipinfenlei;
	}
	
	/**
	 * 获取：视频分类
	 */
	public String getShipinfenlei() {
		return shipinfenlei;
	}
				
	
	/**
	 * 设置：视频封面
	 */
	 
	public void setShipinfengmian(String shipinfengmian) {
		this.shipinfengmian = shipinfengmian;
	}
	
	/**
	 * 获取：视频封面
	 */
	public String getShipinfengmian() {
		return shipinfengmian;
	}
				
	
	/**
	 * 设置：视频
	 */
	 
	public void setShipin(String shipin) {
		this.shipin = shipin;
	}
	
	/**
	 * 获取：视频
	 */
	public String getShipin() {
		return shipin;
	}
				
	
	/**
	 * 设置：发布日期
	 */
	 
	public void setFaburiqi(Date faburiqi) {
		this.faburiqi = faburiqi;
	}
	
	/**
	 * 获取：发布日期
	 */
	public Date getFaburiqi() {
		return faburiqi;
	}
				
	
	/**
	 * 设置：视频介绍
	 */
	 
	public void setShipinjieshao(String shipinjieshao) {
		this.shipinjieshao = shipinjieshao;
	}
	
	/**
	 * 获取：视频介绍
	 */
	public String getShipinjieshao() {
		return shipinjieshao;
	}
				
	
	/**
	 * 设置：最近点击时间
	 */
	 
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
	}
				
	
	/**
	 * 设置：点击次数
	 */
	 
	public void setClicknum(Integer clicknum) {
		this.clicknum = clicknum;
	}
	
	/**
	 * 获取：点击次数
	 */
	public Integer getClicknum() {
		return clicknum;
	}
			
}
