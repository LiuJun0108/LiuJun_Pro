package com.rying.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		Object currentUser = session.getAttribute("currentUser");
		if (currentUser != null) {
			session.setAttribute("currentUser", null);
		}
		return "/";
	}

}
