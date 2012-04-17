<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'newsinfo.jsp' starting page</title>
    
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
	</style>
	<script type="text/javascript">
	
		function back(){
			history.back();
		}
	</script>
  </head>
  
  <body>
   <table align=center width=60%>
   <tr><td> <font face="黑体" size="6">${newsinfo.title }</font></td></tr>
   <tr><td align = right><font color="gray" >日期:${newsinfo.time }</font></td></tr>
    <tr><td> <hr></td></tr>
   <tr><td> <font  size="5">&nbsp;&nbsp;&nbsp;&nbsp;${newsinfo.context }</font></td></tr>
   <tr height = 50><td>&nbsp;</td></tr>
   <tr><td align = right><A href="javascript:back()" style="text-decoration: none">返回</A></td></tr>
    </table>
  </body>
</html>
