package com.news.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtil {
	
	//  1 静态块,加载数据库驱动
	static{
		
		try {
			
			//Oracle jdbc连接
//			Class.forName("oracle.driver.jdbc.OracleDriver");
			
			//ＳＱＬ　Ｓｅｒｖｅｒ　ｊｄｂｃ　连接
//			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			
			//MySql jdbc
//			Class.forName("org.gjt.mm.mysql.Driver");
			
			//odbc桥连接
//			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			//ＳＱＬ　Ｓｅｒｖｅｒ　jtds ｊｄｂｃ　连接
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 *  2 获取数据库连接对象
	 * @return
	 */
	public static Connection getConnection(){
		
		Connection conn = null;
		try {
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:bbs","scott","tiger");
//			conn = DriverManager.getConnection("jdbc:microsoft:sqlserver://localhost:1433;databaseName=BBS","sa","123");
//			conn = DriverManager.getConnection("jdbc:mysql:localhost:3306/bbs","root","root");		
//			conn = DriverManager.getConnection("jdbc:odbc:bbs","sa","123");
			
			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433;databaseName=BBS","sa","123");
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}

	
	/**
	 * 关闭连接
	 * @param conn
	 * @param state
	 * @param rs
	 */
	public static void freeConnection(Connection conn,Statement state,ResultSet rs){
		
		if(rs != null ){
			try {
				rs.close();			
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				if(state != null)
					try {
						state.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				try {
					if(conn != null && !conn.isClosed())
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] args){
		System.out.println(ConnectionUtil.getConnection());
	}
}

//select * from user 效率最低
//select id,name,pwd from user 效率居中
//SELECT id,name,pwd FROM user 效率最高
