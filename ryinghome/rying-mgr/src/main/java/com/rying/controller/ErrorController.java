package com.rying.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class ErrorController {
	@RequestMapping("/error")
	public String error() {
		return "sys/error";
	}
}
