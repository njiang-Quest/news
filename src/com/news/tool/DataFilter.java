package com.news.tool;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

/**
 * 中文过滤器
 * @author Administrator
 *
 */
public class DataFilter implements Filter {

	 protected String encoding = null;

	 protected FilterConfig filterConfig = null;

	 protected boolean ignore = true;


	public void destroy() {
		 this.encoding = null;
		  this.filterConfig = null;

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		 if (ignore || (request.getCharacterEncoding() == null)) {
			   String encoding = this.selectEncoding(request);
			   if (encoding != null){
			    request.setCharacterEncoding(encoding);
			   }

			  }
			  chain.doFilter(request, response);

			 }


	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println(">>>>>>>>>>>中文过滤器初始化开始>>>>>>>>>>>>>");
		
		  this.filterConfig = filterConfig;
		  this.encoding = filterConfig.getInitParameter("encoding");
		  String value = filterConfig.getInitParameter("ignore");
		  if (value == null) {
		   this.ignore = true;
		  } else if (value.equalsIgnoreCase("true")) {
		   this.ignore = true;
		  } else {
		   this.ignore = false;
		  }
		  
		  System.out.println(">>>>>>>>>>>中文过滤器初始化结束>>>>>>>>>>>>>");

	}
	 protected String selectEncoding(ServletRequest request) {
		  return (this.encoding);
		 }

	

}
