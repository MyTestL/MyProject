package com.day41.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

@WebListener()
public class TimerListener implements ServletContextListener,
		                                      HttpSessionListener, HttpSessionAttributeListener {

	// Public constructor is required by servlet spec
	public TimerListener() {
	}

	// -------------------------------------------------------
	// ServletContextListener implementation
	// -------------------------------------------------------
	public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
	}

	public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
	}

	// -------------------------------------------------------
	// HttpSessionListener implementation
	// -------------------------------------------------------
	public void sessionCreated(HttpSessionEvent se) {//创建
		/* Session is created. */
		System.out.println("Session创建时触发...---id:" + se.getSession().getId());
		HttpSession session = se.getSession();//用这个获取Session对象
		Map<String, HttpSession> map = (Map<String, HttpSession>) session.getServletContext().getAttribute("map");//session获取全局对象
		if (map == null) {
			map = new HashMap<>();
		}
		map.put(session.getId(), session);
		session.getServletContext().setAttribute("map", map);

		ServletContext context = se.getSession().getServletContext();
		Integer count = (Integer) context.getAttribute("count");
		if (count == null) {
			context.setAttribute("count", 1);
		} else {
			context.setAttribute("count", count + 1);
		}
		System.out.println("增加了访问次数:" + context.getAttribute("count"));
	}

	public void sessionDestroyed(HttpSessionEvent se) {//销毁
		/* Session is destroyed. */
		System.out.println("Session销毁时触发...---id:" + se.getSession().getId());
		ServletContext context = se.getSession().getServletContext();
		Integer count = (Integer) context.getAttribute("count");
		context.setAttribute("count", count - 1);
		System.out.println("减少了访问次数:" + context.getAttribute("count"));
	}

	// -------------------------------------------------------
	// HttpSessionAttributeListener implementation
	// -------------------------------------------------------

	public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
	}

	public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
	}

	public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attribute
         is replaced in a session.
      */
	}
}
