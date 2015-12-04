package com.rying.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rying.entity.News;
import com.rying.service.INewsService;

@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {
	@Resource
	private INewsService newsService;

	@RequestMapping("/news")
	public ModelAndView news(long id) {
		News news = this.newsService.getNewsById(id);

		ModelAndView mv = new ModelAndView("jsp/news_detail");
		mv.addObject("news", news);
		return mv;
	}

	@RequestMapping("/nearest")
	@ResponseBody
	public List<News> nearest(int count) {
		return this.newsService.nearest(count);
	}

}
