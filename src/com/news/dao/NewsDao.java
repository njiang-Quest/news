package com.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.news.entity.News;

public class NewsDao {
	
	public List<News> news(){
		
		Connection conn = null;
		Statement state = null;
		ResultSet rs = null;
		List<News> newslist = new ArrayList<News>();
		conn = ConnectionUtil.getConnection();
		
		String sql = SQLStatement.GETALL;
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			
			while(rs.next()){
				News news = new News();
				news.setId(rs.getString(1));
				news.setTitle(rs.getString(2));
				news.setTime(rs.getDate(3));
				news.setContext(rs.getString(4));
				
				newslist.add(news);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionUtil.freeConnection(conn, state, rs);
		}
		return newslist;
		
	}
	
	public boolean addNews(News news){
			
		Connection conn = null;
		PreparedStatement ps = null;
		
		conn = ConnectionUtil.getConnection();
		String sql = SQLStatement.ADDNEWS;
		int rows = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(3, getMaxId()+1);
			ps.setString(1, news.getTitle());
			ps.setString(2, news.getContext());
			rows = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionUtil.freeConnection(conn, ps, null);
		}
		return rows>0?true:false;
	}
	
	
	public int getMaxId(){
		
		Connection conn = null;
		Statement state = null;
		ResultSet rs = null;
		conn = ConnectionUtil.getConnection();
		String sql = SQLStatement.MAXID;
		
		int maxid = 0;
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			if(rs.next())
				maxid = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionUtil.freeConnection(conn, state, null);
		}
		
		return maxid;
	}
	
	
	public List<News> search(String searchmessage){
		
		Connection conn = null;
		Statement state = null;
		ResultSet rs = null;
		
		conn = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM NEWS WHERE TITLE LIKE '%"+searchmessage+"%'";
		List<News> newslist = new ArrayList<News>();
		try {
			state = conn.createStatement();
			
			rs = state.executeQuery(sql);
			
			while(rs.next()){
				News news = new News();
				news.setId(rs.getString(1));
				news.setTitle(rs.getString(2));
				news.setTime(rs.getDate(3));
				news.setContext(rs.getString(4));
				
				newslist.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionUtil.freeConnection(conn, state, rs);
		}
		
		return newslist;
	}

}
