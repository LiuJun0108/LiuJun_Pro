package com.rying.easyui.model;

/**
 * combobox 模型
 * 
 * @author LiuJun
 *
 */
public class Combobox {
	private String id;
	private String text;

	public Combobox() {
	}

	public Combobox(String id, String text) {
		this.id = id;
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

}
