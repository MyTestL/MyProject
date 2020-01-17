package com.day42.servlet;

import com.day42.entity.User;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		request.setCharacterEncoding("utf-8");
		if ("register".equals(type)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			Map<String, Object> map = new HashMap<>();
			User user = new User();
			try {
				List<FileItem> list = upload.parseRequest(request);
				for (FileItem item : list) {
					if (!item.isFormField()) {
						if (!item.getName().endsWith(".jpg")) {
							request.setAttribute("msg", "图片格式错误");
							request.getRequestDispatcher("register.jsp").forward(request, response);
						} else {
							String path = getServletContext().getRealPath("upload");
							File file = new File(path);
							if (!file.exists()) {
								file.mkdirs();
							}
							InputStream is = item.getInputStream();
							String filename = UUID.randomUUID().toString() + item.getName();
							File allFile = new File(path, filename);
							IOUtils.copy(is, new FileOutputStream(allFile));
						}
					} else {
						dealData(map, item);
					}
				}
				BeanUtils.populate(user, map);
			} catch (FileUploadException | IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	private void dealData(Map<String, Object> map, FileItem item) throws UnsupportedEncodingException {
		Object value = map.get(item.getFieldName());
		if (value == null) {
			map.put(item.getFieldName(), item.getString("utf-8"));
		} else {
			map.put(item.getFieldName(), value + "," + item.getString("utf-8"));
		}
	}
}
