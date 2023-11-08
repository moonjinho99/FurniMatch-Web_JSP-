<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dbpackage.ConnectDB"%>
<%@ page import="javax.servlet.annotation.WebServlet"%>
<%

	request.setCharacterEncoding("UTF-8");
	
	String id = request.getParameter("id");
	String username = request.getParameter("username");
	String phonenum = request.getParameter("phonenum");
	String password = request.getParameter("password");
	String type = request.getParameter("type");
	String res="";

	ConnectDB con = new ConnectDB();
	
	if(type.equals("username"))
	{
		res = con.updateUser(username, id, type);
	}
	else if(type.equals("phonenum"))
	{
		res = con.updateUser(phonenum,id,type);
	}
	else if(type.equals("password"))
	{
		res = con.updateUser(password,id,type);
	}
	
	out.print(res);
	
%>