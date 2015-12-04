package com.rying.controller;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
	protected String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		return basePath;
	}
}
