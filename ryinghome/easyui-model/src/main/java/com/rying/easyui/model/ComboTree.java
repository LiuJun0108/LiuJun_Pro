package com.rying.easyui.model;

import java.util.List;

public class ComboTree {
	public static final String STATE_OPEN = "open";
	public static final String STATE_CLOSED = "closed";

	private String id;// 节点ID，对加载远程数据很重要。
	private String text;// 显示节点文本。
	private String state = "open";// 节点状态，'open' 或
	// 'closed'，默认;'open'。如果为'closed'的时候，将不自动展开该节点。
	private boolean checked;// 表示该节点是否被选中。
	private String iconCls;
	private Object attributes;// 被添加到节点的自定义属性。
	private List<ComboTree> children; // 一个节点数组声明了若干节点。

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public String getState() {
		return state;
	}

	public boolean isChecked() {
		return checked;
	}

	public Object getAttributes() {
		return attributes;
	}

	public List<ComboTree> getChildren() {
		return children;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public void setChildren(List<ComboTree> children) {
		this.children = children;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

}
