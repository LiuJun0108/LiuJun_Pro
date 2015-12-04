package com.rying.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;

import com.rying.entity.News;
import com.rying.util.DateUtil;

public class NewsServiceTest extends BaseTest {
	@Resource
	private INewsService newsService;

	@Test
	public void testListAll() {
		List<News> list = this.newsService.listAll();
		System.out.println(list.size());
		Assert.assertTrue(list != null);
	}

	@Test
	public void testCountAll() {
		long count = this.newsService.countAll();
		Assert.assertTrue(count > 0);
	}

	@Test
	public void testCountAll2() throws ParseException {
		long count = this.newsService.countAll(DateUtil.parse("2015-11-23", "yyyy-MM-dd"),
				DateUtil.parse("2015-11-25", "yyyy-MM-dd"));
		Assert.assertTrue(count > 0);
	}

	@Test
	public void testPageList() {
		List<News> pageList = this.newsService.pageList(1, 10);
		Assert.assertTrue(pageList.size() > 0);
	}

	@Test
	public void testPageList2() throws ParseException {
		List<News> pageList = this.newsService.pageList(1, 10, DateUtil.parse("2015-11-23", "yyyy-MM-dd"),
				DateUtil.parse("2015-11-25", "yyyy-MM-dd"));
		Assert.assertTrue(pageList.size() > 0);
	}

	@Test
	@Transactional
	public void testAddNews() {
		News news = new News();
		news.setTitle("c");
		news.setContent("c");
		news.setCreatetime(new Date());
		news.setUpdatetime(new Date());

		this.newsService.addNews(news);
	}

	@Test
	@Transactional
	public void testUpdateNews() {
		News news = new News();
		news.setTitle("c");
		news.setContent("c");
		news.setCreatetime(new Date());
		news.setUpdatetime(new Date());
		this.newsService.addNews(news);

		news.setContent("ccc");
		this.newsService.updateNews(news);
	}

	@Test
	public void testDeleteNews() {
		News news = new News();
		news.setTitle("c");
		news.setContent("c");
		news.setCreatetime(new Date());
		news.setUpdatetime(new Date());
		this.newsService.addNews(news);

		this.newsService.deleteNewsById(news.getId());
	}
}
