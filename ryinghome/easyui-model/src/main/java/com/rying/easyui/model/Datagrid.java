package com.rying.easyui.model;

import java.util.List;

/**
 * 带分页的表格模型
 * 
 * @author LiuJun
 *
 */
public class Datagrid<T> {
	private int total;// 总记录数Long类型
	private List<T> rows;// 每行记录

	public Datagrid() {

	}

	public Datagrid(int total, List<T> rows) {
		this.total = total;
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
