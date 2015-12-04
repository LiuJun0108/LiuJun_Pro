package com.rying.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MenuDatagrid {
	private int id;
	private String name; // 名称
	private String url; // url链接
	private int seq; // 排序
	private String iconCls; // 小图标
	private String type = "0"; // 菜单类型：0=系统菜单，1=功能菜单
	private String description; // 描述
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
	private Date modifytime; // 修改时间
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
	private Date createtime; // 创建时间

	private int pid;
	private List<MenuDatagrid> children = new ArrayList<MenuDatagrid>();

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public int getSeq() {
		return seq;
	}

	public String getIconCls() {
		return iconCls;
	}

	public String getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public List<MenuDatagrid> getChildren() {
		return children;
	}

	public void setChildren(List<MenuDatagrid> children) {
		this.children = children;
	}

}
