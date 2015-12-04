package com.rying.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "t_menu")
@JsonIgnoreProperties(value = { "children", "resources" })
public class Menu {
	private static final String TYPE_MENU = "1";

	@Id
	@GeneratedValue
	private int id;
	private String name; // 名称
	private String type = TYPE_MENU;
	private int sys; // 1-->系统菜单，0-->功能菜单
	private String url; // url链接
	private int seq; // 排序
	@Column(length = 50)
	private String iconCls; // 小图标
	private String description; // 描述

	private Date modifytime; // 修改时间
	private Date createtime; // 创建时间

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Menu menu;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "menu")
	@OrderBy("seq")
	private Set<Menu> children = new HashSet<Menu>();
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "upperMenu")
	private Set<Resources> resources = new HashSet<Resources>();

	public Set<Resources> getResources() {
		return resources;
	}

	public void setResources(Set<Resources> resources) {
		this.resources = resources;
	}

	public Set<Menu> getChildren() {
		return children;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public String getDescription() {
		return description;
	}

	public String getIconCls() {
		return iconCls;
	}

	public int getId() {
		return id;
	}

	public Menu getMenu() {
		return menu;
	}

	public Date getModifytime() {
		return modifytime;
	}

	@Column(length = 20)
	public String getName() {
		return name;
	}

	@Column(columnDefinition = "INT default 0", length = 2)
	public int getSeq() {
		return seq;
	}

	@Column(length = 50)
	public String getUrl() {
		return url;
	}

	public void setChildren(Set<Menu> children) {
		this.children = children;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(length = 1)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", type=" + type + ", url=" + url + ", seq=" + seq + ", iconCls="
				+ iconCls + ", description=" + description + ", modifytime=" + modifytime + ", createtime=" + createtime
				+ ", menu=" + menu + ", children=" + children + ", resources=" + resources + "]";
	}

	public int getSys() {
		return sys;
	}

	/**
	 * 1-->系统菜单，0-->功能菜单
	 * @param sys
	 */
	public void setSys(int sys) {
		this.sys = sys;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Menu) {
			Menu menu = (Menu) obj;
			if (this.getId() == menu.getId())
				return true;
			else
				return false;
		} else {
			throw new RuntimeException();
		}
	}

	public void addChild(Menu child) {
		if (this.children == null) {
			this.children = new HashSet<Menu>();
		}
		this.children.add(child);
	}
}
