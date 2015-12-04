package com.rying.model;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {

	private int pageNo = 1; // 当前页
	private int pageSize = 5; // 每 页显示数
	private int pageCount = 0; // 共多少页
	private List<T> list = new ArrayList<T>(); // 每页显示数据

	public Page() {
	}

	public Page(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public Page(int pageNo, int pageSize, int count, List<T> list) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.pageCount = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		this.list = list;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

}
