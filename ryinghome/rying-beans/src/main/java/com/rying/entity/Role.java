package com.rying.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "t_role")
@JsonIgnoreProperties(value = { "sysUsers", "resources" })
public class Role {
	@Id
	@GeneratedValue
	private int id;
	@Column(length = 10)
	private String name; // 角色名称
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date modifytime;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createtime; // 修改时间

	@ManyToMany(fetch=FetchType.EAGER, mappedBy = "roles")
	private Set<SysUser> sysUsers;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t_role_resources")
	private Set<Resources> resources;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public Set<SysUser> getSysUsers() {
		return sysUsers;
	}

	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Set<Resources> getResources() {
		return resources;
	}

	public void setResources(Set<Resources> resources) {
		this.resources = resources;
	}

}
