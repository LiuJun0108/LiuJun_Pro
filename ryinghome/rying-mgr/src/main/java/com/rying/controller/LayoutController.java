package com.rying.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rying.entity.SysUser;
import com.rying.service.IAdminService;

@Controller
@RequestMapping("/layout")
public class LayoutController {
	@Resource
	private IAdminService adminService;

	@RequestMapping("/top")
	public String top() {
		return "layout/top";
	}

	@RequestMapping("/west")
	public String west(HttpServletRequest request, ModelMap model) {
		SysUser currentUser = (SysUser) request.getSession().getAttribute("currentUser");
		if (currentUser != null) {
			boolean isAdmin = this.adminService.isAdmin(currentUser.getUsername());
			model.addAttribute("isAdmin", isAdmin);
		}
		return "layout/west";
	}

	@RequestMapping("/center")
	public String center() {
		return "layout/center";
	}

	@RequestMapping("/welcome")
	public String welcome() {
		return "layout/welcome";
	}

}
