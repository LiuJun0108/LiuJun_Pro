package com.rying.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthorityInterceptor extends HandlerInterceptorAdapter {
	private String excepUrl = "login";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			String requestURI = request.getRequestURI();
			String contextPath = request.getContextPath();
			if (requestURI.contains(excepUrl))
				return true;

			String method = requestURI.substring(contextPath.length() + 1);
			@SuppressWarnings("unchecked")
			List<String> auths = (List<String>) request.getSession().getAttribute("auths");
			if (auths == null)
				throw new RuntimeException();

			if (hasAuthority(auths, method)) {
				return true;
			}

			response.addHeader("sessionstatus", "noAuthority");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private boolean hasAuthority(List<String> auths, String method) {
		try {
			method = method.substring(0, method.lastIndexOf("."));
		} catch (Exception e) {
		}
		for (String auth : auths) {
			try {
				auth = auth.substring(0, auth.lastIndexOf("."));
			} catch (Exception e) {
			}
			if (auth.equalsIgnoreCase(method)) {
				return true;
			}
		}

		return false;
	}

}
