package com.yixl.user.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * fastjson 测试实体类
 * @author yixl
 *
 */
public class User2 {
	private int id;
	private String name;
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	// 不出现
	@JSONField(serialize = false)
	private String remark;// 备注信息
	
	public User2() {
		super();
	}

	public User2(int id, String name, Date createTime, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.createTime = createTime;
		this.remark = remark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
