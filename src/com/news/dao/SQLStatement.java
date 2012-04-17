package com.news.dao;

public interface SQLStatement {
	
	String ADDNEWS = "INSERT INTO NEWS(T_TIME,TITLE,CONTEXT,ID) VALUES(getdate(),?,?,?)";

	String MAXID = "SELECT MAX(ID) FROM NEWS";
	
	String  GETALL = "SELECT ID,TITLE,T_TIME,CONTEXT FROM NEWS";

}
