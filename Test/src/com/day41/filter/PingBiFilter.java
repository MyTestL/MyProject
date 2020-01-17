package com.day41.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class PingBiFilter implements Filter {
	public void destroy() {
		System.out.println("屏蔽销毁...");
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		MyHttpServletRequestWrapper wrapper = new MyHttpServletRequestWrapper(request);
		chain.doFilter(wrapper, response);
	}

	public void init(FilterConfig config) throws ServletException {
		System.out.println("屏蔽启动...");
	}

	private class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {
		public MyHttpServletRequestWrapper(HttpServletRequest request) {
			super(request);
		}

		@Override
		public String getParameter(String name) {
			String value = super.getParameter(name);
			if (value != null) {
				value = value.replace("暴力", "**");
				value = value.replace("黄色", "**");
				value = value.replace(">", "&gt;");
				value = value.replace("<", "&lt;");
			}
			return value;
		}
	}
}
