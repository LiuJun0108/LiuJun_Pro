package com.rying.easyui.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * tree模型
 * 
 * @author LiuJun
 *
 */
public class TreeNode implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	public static final String STATE_OPEN = "open";
	public static final String STATE_CLOSED = "closed";

	private String id;
	private String text;// 树节点名称
	private String iconCls;// 前面的小图标样式
	private Boolean checked = false;// 是否勾选状态
	private String state = STATE_OPEN;// 是否展开(open,closed)
	private List<TreeNode> children = new ArrayList<TreeNode>();// 子节点
	private Map<String, Object> attributes = new HashMap<String, Object>(); // 其他参数

	public TreeNode() {
		super();
	}

	public TreeNode(String text) {
		this.text = text;
	}

	public TreeNode(String text, String url) {
		this(text);
		this.attributes.put("url", url);
	}

	public TreeNode(String text, String url, String iconCls) {
		this(text, url);
		this.iconCls = iconCls;
	}

	public TreeNode(String text, String iconCls, List<TreeNode> children) {
		this(text);
		this.iconCls = iconCls;
		this.children = children;
	}

	public TreeNode(String text, String url, String iconCls,
			List<TreeNode> children) {
		this(text, url, iconCls);
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public void addChildren(TreeNode child) {
		this.children.add(child);
	}

}
