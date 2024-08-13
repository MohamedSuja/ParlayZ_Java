package com.parlayZ.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCorsFilter.class);

	public SimpleCorsFilter() {
		// default constructor
	}
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		response.setHeader("Access-Control-Allow-Origin", ((HttpServletRequest) req).getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", " POST");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Expose-Headers",
				"x-requested-with,content-type, authorization, X-Authorization, Authorization, Origin, Content-Disposition");
		response.setHeader("Access-Control-Allow-Headers",
				"x-requested-with,content-type, authorization, X-Authorization, Authorization, Origin");
		String remoteAddr = request.getHeader("X-FORWARDED-FOR");
		LOGGER.info("***************** HEADER INFO ************");
		LOGGER.info(request.getRemoteAddr());
		LOGGER.info(request.getRemoteHost());
		LOGGER.info(request.getRequestURI());
		LOGGER.info(remoteAddr);
		LOGGER.info("***************** HEADER INFO ************");
		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, res);
		}
	}
	@Override
	public void init(FilterConfig filterConfig) {
		// void init
	}

	@Override
	public void destroy() {
		// void destroy
	}
}