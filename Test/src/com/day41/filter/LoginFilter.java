package com.day41.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
	public void destroy() {
		System.out.println("Login 销毁...");
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		/*HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String type = request.getParameter("type");
		String username = (String) request.getSession().getAttribute("username");
		if (username != null) {
			chain.doFilter(request, response);
		} else {
			String uri = request.getRequestURI();
			if (uri.endsWith("login.jsp") || uri.endsWith("LoginServlet") && "login".equals(type)) {
				chain.doFilter(request, response);
			} else {
				response.sendRedirect("login.jsp");
			}
		}*/
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig config) throws ServletException {
		System.out.println("Login 初始化...");
	}

}
