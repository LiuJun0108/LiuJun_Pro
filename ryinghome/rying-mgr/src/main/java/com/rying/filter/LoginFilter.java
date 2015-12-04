package com.rying.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.rying.util.StrValidator;

public class LoginFilter implements Filter {
	private Logger log = Logger.getLogger(LoginFilter.class);
	/** 要检查的 session 的名称 */
	private String sessionKey;
	/** 需要排除（不拦截）的URL的正则表达式 */
	private Pattern excepUrlPattern;
	/** 检查不通过时，转发的URL */
	private String forwardUrl;

	private String basepath;

	@Override
	public void destroy() {
		System.out.println("LoginFilter.destroy()");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		// 如果请求的路径与forwardUrl相同，或请求的路径是排除的URL时，则直接放行
		String servletPath = request.getServletPath();
		if (servletPath.equals(forwardUrl) || excepUrlPattern.matcher(servletPath).matches()) {
			chain.doFilter(request, response);
			return;
		}

		Object currentUser = request.getSession().getAttribute(sessionKey);
		if (currentUser == null) {
			if (basepath == null) {
				basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ request.getContextPath() + "/";
			}
			response.sendRedirect(basepath + forwardUrl);
			log.info("未登陆或登陆超时");
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.sessionKey = config.getInitParameter("sessionKey");
		this.forwardUrl = config.getInitParameter("forwardUrl");

		String excepUrlRegex = config.getInitParameter("excepUrlRegex");
		if (StrValidator.isNotNullAndEmpty(excepUrlRegex)) {
			this.excepUrlPattern = Pattern.compile(excepUrlRegex);
		}

	}

}
