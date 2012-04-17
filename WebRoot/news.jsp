<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'news.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<style type="text/css">
		a{text-decoration: none}
		a:visited{color:black}
		a:hover{color:green;font-family: 黑体}
		
		table{border-bottom: 1px;border-right: 0;border-left: 0px;
		border-top: 1px;border-color:red}
	</style>
	
	 <script type="text/javascript">
  
  
  		function hidden(){
  			document.getElementById("add").style.display="block";
  			document.getElementById("body").style.display="none";
  		
  		}
  	 	
  		function mycheck(){

  			var t = document.myform.title2.value;
  			var c = document.myform.context2.value;
  			
  			if(t.length==0){
  				document.getElementById("s1").innerText="内容不能为空";
  				return false;
  			}
  			if(c.length==0){
  				document.getElementById("s2").innerText="内容不能为空";
  				return false;
  			}
  		}
  		
  		function checksearch(){
  			var s = document.searchform.search.value;
  			if(s.length==0)
  			{
  				return false;
  			}
  		}
  		
  		function back(){
  			document.getElementById("add").style.display="none";
  			document.getElementById("body").style.display="block";
  		
  		}
  		
  </script>
  <script type="text/javascript">
  	
  		
		var xmlhttpresponse = false;
		
		//创建XMLHttpRequest对象
		function createxmlhttpresponse(){
			if(window.XMLHttpRequest){
				//非ie浏览器
				xmlhttpresponse = new XMLHttpRequest();
			}else if(window.ActiveXObject){
				//ie浏览器
				try{
					xmlhttpresponse = new window.ActiveXObject("Msxml2.XMLHTTP");
					
				}catch(e){
				
					xmlhttpresponse = new window.ActiveXObject("Microsoft.XMLHTTP");
					
				}
			}
		}

		//响应函数
		function processRequest(){
			if(xmlhttpresponse.readyState==4){			
				if(xmlhttpresponse.status==200){
					window.location.reload();
				}else{
					alert("请求有异常");
				}
			}
		
		}
		
		function checkpage(){
			createxmlhttpresponse();
			var pagesize = document.getElementById("pagesize").value;
			var flag = true;
			if(pagesize.length > 0){
				for(var i = 0;i < pagesize.length;i++){
					var c = pagesize.charAt(i);
					if(!(c >=0 && c<= 9)){					
						flag = false;
						break;
					}
				}
				if(flag){
					xmlhttpresponse.open("GET","NewsServlet?pagesize="+pagesize,true);
					xmlhttpresponse.onreadystatechange=processRequest;
					xmlhttpresponse.send(null);
				}
			}
		}
		
		function pageno(pages){
		
			createxmlhttpresponse();
			var jmppageno = document.getElementById("jmppageno").value;
			var flag = true;
			
			if(jmppageno.length > 0){
				for(var i = 0;i < jmppageno.length;i++){
					var c = jmppageno.charAt(i);
					if(!(c >=0 && c<= 9)){
						flag = false;
						break;
					}
				}
				
				if(pages < jmppageno){flag = false;}
				
				if(flag){
					xmlhttpresponse.open("GET","NewsServlet?jmppageno="+jmppageno,true);
					xmlhttpresponse.onreadystatechange=processRequest;
					xmlhttpresponse.send(null);
				}
			}
		}
  </script>
  
  </head>
  
  <body topmargin="50">
  <div id = body>	

	<div >
	  	<form name="searchform" action = "SearchServlet" method="post" onsubmit="return checksearch()">
	  	<table align = center width="50%">
	  		<tr><td align = right>
	  			<input type="text" name="search" style="width:300px">
	  			<input type="submit" value="搜 索">
	  		</td></tr>
	  		
	  	</table>
	  	</form>
	  </div>
	  
	  <div id="news">
	  	<table align = center rules="rows" width = "50%"  cellspacing="0"  >
	  	<tr bgcolor="gray"><td colspan=2  align = center height=50><font face="黑体" size="5">新闻列表</font></td></tr>
	  
	  	<c:forEach items="${newslist}" var="news">
	  		<tr  height=30>	  			
  				<td width=70%>&nbsp;&nbsp;&nbsp;&nbsp;<a href="NewsInfoServlet?id=${news.id }">${news.title }</a></td>
  				<td><a href="NewsInfoServlet?id=${news.id }">${news.time }</a></td>			
	  		</tr>  		
	  	</c:forEach>
	  	
	  	<tr bgcolor="gray">
	  	<td>&nbsp;</td>
	  	<td  height=40><a href="javascript:hidden()"><font face="黑体" size=4>发布新闻</font></a></td>
	  	</tr>
	  	
	  	</table>
	  	<table align = center width="50%">
	  		<tr>
	  			<td align= right>
	  			每页显示<input id="pagesize" type="text" style="width: 30px" onblur="checkpage()">&nbsp;&nbsp;
	  			当前页数:【${page.currentpage }/${page.pages }】
	  			跳到第<input id="jmppageno" type="text" style="width: 30px" onblur="pageno(${page.pages })">页&nbsp;
	  			 <a href="NewsServlet?page=1">首页</a>&nbsp;
	  			 <a href="NewsServlet?page=${page.currentpage-1 }">上一页</a>&nbsp;
	  			 <a href="NewsServlet?page=${page.currentpage+1 }">下一页</a>&nbsp;
	  			 <a href="NewsServlet?page=${page.pages }">尾页</a></td>
	  		</tr>	  		
	  	</table>
	  	<p/>
	  	<table align = center width="50%">
	  		<tr><td align = right colspan=2> <a href="NewsServlet?pagesize=0">显示所有</a></td></tr>	  		
	  	</table>
	  </div>
  </div>
  
  <div id = add style="display:none">
  	<form name="myform" action="AddNewsServlet" method= "post" onsubmit="return mycheck()">
  		<table align = center width=60%>
  			<tr><td colspan=2><font face="黑体" size=6>发布新闻</font></td>
  				<td><a href="javascript:back()">返回列表</a></td>
  			</tr>
  			<tr><td colspan=3><hr></td></tr>
  			<tr><td>&nbsp;</td></tr>
  			<tr><td>&nbsp;</td></tr>
  			<tr>
  				<td>标题:</td>
  				<td > <input type=text name="title2" style="width: 430px" ></td><td valign="top"><br></td>
  				<td><span id="s1"></span></td>
  		    </tr>
  		    <tr><td valign="top"><br></td><td valign="top"><br></td><td valign="top"><br></td></tr>
  		    <tr>
  				<td>内容:</td>
  				<td ><textarea name="context2" rows=10 cols=60></textarea></td><td valign="top"><br></td>
  				<td><span id="s2"></span></td>
  			</tr>
  			<tr><td>&nbsp;</td></tr>
  			<tr><td>&nbsp;</td></tr>
  			<tr>
  				<td>&nbsp;</td>
  				<td ><input type = "submit" value="发布">
  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  				<input type = "reset" value="清空"></td>
  			</tr>
  		</table>
  	
  	</form>
  
 
  </div>
  </body>
</html>
