package com.csy.o2o.entity;

import java.util.Date;

public class Area {

	//����
	private Integer areaid;
	//����
	private String areaname;
	//����
	private Integer level;
	//����ʱ��
	private Date createTime;
	//����ʱ��
	private Date updateTime;
	
	public Integer getAreaid() {
		return areaid;
	}
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
	public String getAreaname() {
		return areaname;
	}
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "Area [areaid=" + areaid + ", areaname=" + areaname + ", level=" + level + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
	
}
