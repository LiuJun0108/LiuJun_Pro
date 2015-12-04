package com.rying.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rying.entity.InvestorRelations;
import com.rying.entity.News;
import com.rying.service.IInvestorRelationsService;
import com.rying.service.INewsService;
import com.rying.util.DateUtil;

@Controller
public class HomeController {
	@Resource
	private INewsService newsService;
	@Resource
	private IInvestorRelationsService investorRelationsService;
	
	@RequestMapping("/home")
	public String home() {
		return "jsp/home";
	}

	@RequestMapping("/about")
	public String about() {
		return "jsp/about";
	}

	@RequestMapping("/culture")
	public String culture() {
		return "jsp/culture";
	}

	@RequestMapping("/qua")
	public String qua() {
		return "jsp/qua";
	}

	@RequestMapping("/stru")
	public String stru() {
		return "jsp/stru";
	}

	@RequestMapping("/proc")
	public String proc() {
		return "jsp/proc";
	}

	@RequestMapping("/about_news")
	public ModelAndView about_news(@RequestParam(defaultValue = "1") int pageNo,
			@RequestParam(defaultValue = "20") int pageSize) {
		Long count = this.newsService.countAll();
		Long pageCount = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		if (pageNo > pageCount) {
			pageNo = 1;
		}
		List<News> list = this.newsService.pageList(pageNo, pageSize);

		ModelAndView mv = new ModelAndView("jsp/about_news");
		mv.addObject("newsList", list);
		mv.addObject("pageCount", pageCount);
		mv.addObject("pageNo", pageNo);
		return mv;
	}

	@RequestMapping("/news_detail")
	public ModelAndView news_detail(long id) {
		News news = this.newsService.getNewsById(id);

		ModelAndView mv = new ModelAndView("jsp/news_detail");
		mv.addObject("news", news);
		return mv;
	}

	@RequestMapping("/feel")
	public String feel() {
		return "jsp/feel/feel";
	}

	@RequestMapping("/act")
	public String act() {
		return "jsp/feel/act";
	}

	@RequestMapping("/join")
	public String join() {
		return "jsp/join/join";
	}

	@RequestMapping("/letter")
	public String letter() {
		return "jsp/join/letter";
	}

	@RequestMapping("/post")
	public String post() {
		return "jsp/join/post";
	}

	@RequestMapping("/process")
	public String process() {
		return "jsp/join/process";
	}

	@RequestMapping("/rela")
	public ModelAndView rela() {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		try {
			String year = sdf.format(new Date());
			List<InvestorRelations> yearList = this.investorRelationsService.queryList(sdf.parse(year));
			Map<String, Object> yearMap = new HashMap<String, Object>();
			yearMap.put("year", year);
			yearMap.put("isEmpty", yearList.isEmpty());
			yearMap.put("list", yearList);

			int nextyear = Integer.valueOf(year) + 1;
			List<InvestorRelations> nextyearList = this.investorRelationsService.queryList(sdf.parse(nextyear+""));
			Map<String, Object> nextyearMap = new HashMap<String, Object>();
			nextyearMap.put("year", nextyear+"");
			nextyearMap.put("isEmpty", nextyearList.isEmpty());
			nextyearMap.put("list", nextyearList);

			int theyearafternext = nextyear + 1;
			List<InvestorRelations> theyearafternextList = this.investorRelationsService.queryList(sdf.parse(theyearafternext+""));
			
			Map<String, Object> theyearafternextMap = new HashMap<String, Object>();
			theyearafternextMap.put("year", theyearafternext + "");
			theyearafternextMap.put("isEmpty", theyearafternextList.isEmpty());
			theyearafternextMap.put("list", theyearafternextList);

			List<Map<String, Object>> mapList = new ArrayList<>();
			mapList.add(yearMap);
			mapList.add(nextyearMap);
			mapList.add(theyearafternextMap);

			ModelAndView mv = new ModelAndView("jsp/rela/rela");
			mv.addObject("mapList", mapList);

			return mv;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/contact")
	public String contact() {
		return "jsp/contact/contact";
	}

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		System.out.println(sdf.format(new Date()));
	}

}
