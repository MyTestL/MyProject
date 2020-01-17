package com.day41.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

@WebFilter("/*")
public class EncodingFilter implements Filter {
	public void destroy() {
		System.out.println("Encoding 销毁...");
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		response.setContentType("text/html;charset=utf-8");
		String method = request.getMethod();
		if ("POST".equals(method)) {
			request.setCharacterEncoding("utf-8");
			chain.doFilter(req, resp);
		} else if ("GET".equals(method)) {
			MyHttpServletRequstWrapper wrapper = new MyHttpServletRequstWrapper(request);
			chain.doFilter(wrapper, response);
		}
	}

	public void init(FilterConfig config) throws ServletException {
		System.out.println("Encoding(定时器) 初始化...");
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Map<String, HttpSession> map = (Map<String, HttpSession>) config.getServletContext().getAttribute("map");//config获取全局对象
				if (map != null) {
					Set<String> set = map.keySet();
					for (String key : set) {
						HttpSession session = map.get(key);
						long end = System.currentTimeMillis()/1000;
						long start = session.getLastAccessedTime()/1000;
						if (end - start > 30) {
							session.invalidate();//销毁...
							map.remove(session.getId());//移除map
							break;
						}
					}
				}
			}
		}, 1000, 1000);
	}

	private class MyHttpServletRequstWrapper extends HttpServletRequestWrapper {
		public MyHttpServletRequstWrapper(HttpServletRequest request) {
			super(request);
		}

		@Override
		public String getParameter(String name) {
			String value = super.getParameter(name);
			if (value != null) {

				try {
					value = new String(value.getBytes("iso-8859-1"), "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			return value;
		}
	}
}
