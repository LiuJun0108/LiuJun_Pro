package com.rying.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rying.easyui.model.Datagrid;
import com.rying.easyui.model.JsonMsg;
import com.rying.entity.News;
import com.rying.service.INewsService;
import com.rying.util.DateUtil;

@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {
	@Resource
	private INewsService newsService;

	@RequestMapping("/news")
	public String news() {
		return "news/news";
	}

	@RequestMapping("/datagrid")
	@ResponseBody
	public Datagrid<News> datagrid(int page, int rows, String start, String end) {
		Date s = null;
		Date e = null;
		try {
			if (start != null && !start.trim().isEmpty()) {
				s = DateUtil.parse(start, "yyyy-MM-dd");
			}
			if (end != null && !end.trim().isEmpty()) {
				e = DateUtil.parse(end, "yyyy-MM-dd");
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Long count = this.newsService.countAll(s, e);
		List<News> pageList = this.newsService.pageList(page, rows, s, e);
		return new Datagrid<News>(count.intValue(), pageList);
	}

	@RequestMapping(value = "/addNews", method = RequestMethod.GET)
	public String addNews() {
		return "news/addNews";
	}

	@RequestMapping(value = "/addNews", method = RequestMethod.POST)
	public String addNews(News news) {
		System.out.println("title = " + news.getTitle());
		this.newsService.addNews(news);
		return "redirect:news";

	}

	@RequestMapping(value = "/updateNews", method = RequestMethod.GET)
	public ModelAndView updateNews(long id) {
		News news = this.newsService.getNewsById(id);

		ModelAndView mv = new ModelAndView("news/addNews");
		mv.addObject("news", news);
		return mv;
	}

	@RequestMapping(value = "/updateNews", method = RequestMethod.POST)
	public String updateNews(News news) {
		this.newsService.updateNews(news);
		return "redirect:news";
	}

	@RequestMapping("/destroyNews")
	@ResponseBody
	public JsonMsg destroyNews(long id) {
		this.newsService.deleteNewsById(id);
		return new JsonMsg(true, "删除成功");
	}

	@RequestMapping("/showNews")
	public ModelAndView showNews(long id) {
		News news = this.newsService.getNewsById(id);

		ModelAndView mv = new ModelAndView("news/show");
		mv.addObject("news", news);
		return mv;
	}

	@RequestMapping("/selectPage")
	public String selectPage() {
		return "news/select_page";
	}
}
