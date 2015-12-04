package com.rying.service;

import java.util.Date;
import java.util.List;

import com.rying.entity.News;

public interface INewsService {
	public List<News> listAll();

	/**
	 * 最近的count条记录
	 * 
	 * @param count
	 * @return
	 */
	public List<News> nearest(int count);

	/**
	 * 数据库总数，用于分页
	 * 
	 * @return
	 */
	Long countAll();

	/**
	 * 数据库总数，用于分页
	 * 
	 * @param start
	 *            添加开始时间
	 * @param end
	 *            添加结束时间
	 * @return
	 */
	Long countAll(Date start, Date end);

	/**
	 * 分页数据
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<News> pageList(int pageNo, int pageSize);

	/**
	 * 分页数据
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param start
	 * @param end
	 * @return
	 */
	List<News> pageList(int pageNo, int pageSize, Date start, Date end);

	/**
	 * 以ID取数据
	 * 
	 * @param id
	 * @return
	 */
	News getNewsById(long id);

	void addNews(News news);

	void updateNews(News news);

	void deleteNewsById(long id);
}
