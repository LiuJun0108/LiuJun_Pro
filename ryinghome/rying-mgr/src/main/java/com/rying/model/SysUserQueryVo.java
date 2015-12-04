package com.rying.model;

public class SysUserQueryVo {
	private String username;
	private String name;
	private String phone;
	private String email;
	private String startModifyTime;
	private String endModifyTime;
	private String startCreateTime;
	private String endCreateTime;

	public SysUserQueryVo(String username, String name, String phone, String email, String startModifyTime, String endModifyTime, String startCreateTime, String endCreateTime) {
		this.username = username;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.startModifyTime = startModifyTime;
		this.endModifyTime = endModifyTime;
		this.startCreateTime = startCreateTime;
		this.endCreateTime = endCreateTime;
	}

	public String getUsername() {
		return username;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getStartModifyTime() {
		return startModifyTime;
	}

	public String getEndModifyTime() {
		return endModifyTime;
	}

	public String getStartCreateTime() {
		return startCreateTime;
	}

	public String getEndCreateTime() {
		return endCreateTime;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setStartModifyTime(String startModifyTime) {
		this.startModifyTime = startModifyTime;
	}

	public void setEndModifyTime(String endModifyTime) {
		this.endModifyTime = endModifyTime;
	}

	public void setStartCreateTime(String startCreateTime) {
		this.startCreateTime = startCreateTime;
	}

	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

}
