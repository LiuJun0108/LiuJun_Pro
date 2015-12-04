package com.rying.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 资源的树形表格
 * 
 * @author Administrator
 * 
 */
public class ResourcesTreeGrid {
	public static final String TYPE_MENU = "1";
	public static final String TYPE_FUNC = "2";
	private int id;
	private String name;// 资源名称
	private String type;
	private String action; // action
	private int seq; // 排序
	private String iconCls; // 小图标
	private String description; // 描述
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date modifytime; // 修改时间
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createtime; // 创建时间
	
	private int pId;
	private List<ResourcesTreeGrid> children = new ArrayList<ResourcesTreeGrid>();

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAction() {
		return action;
	}

	public int getSeq() {
		return seq;
	}

	public String getIconCls() {
		return iconCls;
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

	public List<ResourcesTreeGrid> getChildren() {
		return children;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public void setChildren(List<ResourcesTreeGrid> children) {
		this.children = children;
	}

}
