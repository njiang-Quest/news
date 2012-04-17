package com.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.news.dao.NewsDao;
import com.news.entity.News;
import com.news.entity.PageBean;
import com.news.tool.Page;

public class NewsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public NewsServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		PageBean pagebean = null;
		List<News> newslistAll = null;
		List<News> newslist = null;
		
		String pageno = request.getParameter("page");	
		String pagesize = request.getParameter("pagesize");
		String jmppageno = request.getParameter("jmppageno");
		
		if(jmppageno != null){
			
			pageno = jmppageno;
			
			System.out.println(pageno);
		}
		
		if(pageno==null){
			NewsDao newsdao = new NewsDao();
			
			newslistAll = newsdao.news();
			newslist= new ArrayList<News>();
			pagebean = new PageBean();
			
			session.setAttribute("page", pagebean);
			session.setAttribute("newslist", newslist);
			session.setAttribute("newslistAll", newslistAll);
		}else{
			pagebean = (PageBean) session.getAttribute("page");
			newslist = (List<News>) session.getAttribute("newslist");
			newslistAll = (List<News>) session.getAttribute("newslistAll");
		}		
		
		Page page = new Page(pagebean,newslist,newslistAll);
		page.dopage(pagesize, pageno);
		
	
		session.setAttribute("page", page.getPage());
		session.setAttribute("newslist",page.getPagelist());		

		response.sendRedirect("news.jsp");
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
