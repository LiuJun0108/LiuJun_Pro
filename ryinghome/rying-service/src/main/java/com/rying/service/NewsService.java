package com.rying.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.rying.dao.INewsDao;
import com.rying.entity.News;
import com.rying.util.DateUtil;

@Service
public class NewsService implements INewsService {
	private Logger log = Logger.getLogger(NewsService.class);
	@Resource
	private INewsDao newsDao;

	@Override
	public List<News> listAll() {
		return this.newsDao.queryAll();
	}

	/**
	 * 最近的count条记录
	 */
	@Override
	public List<News> nearest(int count) {
		String hql = "from News as news order by news.createtime desc";
		log.info("hlq = " + hql);
		return this.newsDao.queryListByHQL(hql, 1, count);
	}

	@Override
	public Long countAll() {
		return this.newsDao.countAll();
	}

	@Override
	public Long countAll(Date start, Date end) {
		StringBuffer hql = new StringBuffer("select count(*) from News as news where 1=1");

		List<Object> values = new ArrayList<Object>();
		if (start != null) {
			hql.append(" and news.createtime >= ?");
			values.add(start);
		}
		if (end != null) {
			hql.append(" and news.createtime <= ?");
			values.add(end);
		}
		log.info("hql = " + hql);

		return this.newsDao.countByHql(hql.toString(), values.toArray());
	}

	@Override
	public List<News> pageList(int pageNo, int pageSize) {
		String hql = "from News as news order by news.createtime desc";
		log.info("hql = " + hql);

		return this.newsDao.queryListByHQL(hql, pageNo, pageSize);
	}

	@Override
	public List<News> pageList(int pageNo, int pageSize, Date start, Date end) {
		StringBuffer hql = new StringBuffer("from News as news where 1=1");

		List<Object> values = new ArrayList<Object>();
		if (start != null) {
			hql.append(" and news.createtime >= ?");
			values.add(start);
		}
		if (end != null) {
			hql.append(" and news.createtime <= ?");
			values.add(end);
		}
		hql.append(" order by news.createtime desc");
		log.info("hql = " + hql);

		return this.newsDao.queryListByHQL(hql.toString(), values.toArray(), pageNo, pageSize);
	}

	@Override
	public News getNewsById(long id) {
		return this.newsDao.getById(id);
	}

	@Override
	public void addNews(News news) {
		Date now = DateUtil.getCurrentDate();
		news.setCreatetime(now);
		news.setUpdatetime(now);

		this.newsDao.save(news);
	}

	@Override
	public void updateNews(News news) {
		News oldNews = this.newsDao.getById(news.getId());

		oldNews.setTitle(news.getTitle());
		oldNews.setContent(news.getContent());
		oldNews.setUpdatetime(new Date());

		this.newsDao.update(oldNews);
	}
 
	@Override
	public void deleteNewsById(long id) {
		News n = new News();
		n.setId(id);
		this.newsDao.delete(n);
	}

}
