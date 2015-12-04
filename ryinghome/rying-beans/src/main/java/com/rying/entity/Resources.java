package com.rying.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "t_resources")
@JsonIgnoreProperties(value = {"roles"})
public class Resources {
	private static final String TYPE_RESOURCES = "2";

	@Id
	@GeneratedValue
	private int id;
	private String name;// 资源名称
	private String type = TYPE_RESOURCES;
	private String action; // action
	private int seq; // 排序
	private String iconCls; // 小图标
	private String description; // 描述
	private Date modifytime; // 修改时间
	private Date createtime; // 创建时间

	@ManyToOne
	@JoinColumn(name = "upperMenu_id")
	private Menu upperMenu;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "resources")
	private Set<Role> roles;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAction() {
		return action;
	}

	public Set<Role> getRoles() {
		return roles;
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

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Menu getUpperMenu() {
		return upperMenu;
	}

	public void setUpperMenu(Menu upperMenu) {
		this.upperMenu = upperMenu;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
