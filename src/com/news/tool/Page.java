package com.news.tool;

import java.util.List;

import com.news.dao.NewsDao;
import com.news.entity.News;
import com.news.entity.PageBean;

public class Page {
	
	private PageBean page;
	private List pagelist;
	private List listall;
	
	public Page(PageBean page,List pagelist, List listall){
		this.page = page;
		this.pagelist = pagelist;
		this.listall = listall;
	}
	
	public void dopage(String pagesize,String pageno){
		
		if(pageno==null){
			//第一次访问，在dao获取所有记录						
			if(pagesize != null){
				if(pagesize.equals("0"))
					page.setPagesize(listall.size());
				else{
					int size = Integer.parseInt(pagesize);
					
					if(size >= listall.size())
						page.setPagesize(listall.size());
					else
						page.setPagesize(size);	
				}
			}
					
			page.setCurrentpage(1);
			page.setRows(listall.size());
			int pages = page.getRows()%page.getPagesize()==0?page.getRows()/page.getPagesize():page.getRows()/page.getPagesize()+1;
			page.setPages(pages);
			
			for(int i=0;i<page.getPagesize();i++)
				pagelist.add(listall.get(i));		

		}else{
			
			//设置每页显示条数
			if(pagesize != null){
				int size = Integer.parseInt(pagesize);
				
				if(size >= page.getRows())
					page.setPagesize(page.getRows());
				else
					page.setPagesize(size);	
				
			}
		
			//如果已经是首页 或尾页 则直接跳转
			int p = Integer.parseInt(pageno);			
			if(p!=0 && !(p>=(page.getPages()+1)) ){
			
				page.setCurrentpage(Integer.parseInt(pageno));
				

				//移除上一次页面内容	
				int size = pagelist.size();
				for(int i = 0;i<size;i++){
					pagelist.remove(0);//每次都是0 是因为每次把第一个删除后，后面的就会往前替代
				}
				
				//普通页
				if(page.getCurrentpage() < page.getPages()){
					for(int i = 0;i<page.getPagesize();i++){
						pagelist.add(listall.get(i+(page.getCurrentpage()-1)*page.getPagesize()));
					}			
				}else{//最后一页
					int last = listall.size() % page.getPagesize();
					if(last != 0){//最后的一页没有pagesize条记录
						 for(int i = 0;i<last;i++){
							 pagelist.add(listall.get(i+(page.getCurrentpage()-1)*page.getPagesize()));	
						 }				
					}else{//最后的一页没有刚好有pagesize条记录
						for(int i = 0;i<page.getPagesize();i++){
							pagelist.add(listall.get(i+(page.getCurrentpage()-1)*page.getPagesize()));
						}
					}
		
				}
			}
			
		}	
	}

	public PageBean getPage() {
		return page;
	}

	public void setPage(PageBean page) {
		this.page = page;
	}

	public List getPagelist() {
		return pagelist;
	}

	public void setPagelist(List pagelist) {
		this.pagelist = pagelist;
	}

	public List getListall() {
		return listall;
	}

	public void setListall(List listall) {
		this.listall = listall;
	}



}
