<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dbpackage.ConnectDB"%>
<%@ page import="javax.servlet.annotation.WebServlet"%>

<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String type = request.getParameter("type"); 
	String name = request.getParameter("name");
	String phonenum = request.getParameter("phonenum");
	
	System.out.println(id);
	System.out.println(pwd);
	System.out.println(type);
	ConnectDB connectDB = ConnectDB.getInstance();
		
	 if(type.equals("login")) {
		String returns = connectDB.logindb(id, pwd);
		System.out.println(returns);
		out.print(returns);
	} else if(type.equals("join")) {
		String returns = connectDB.joindb(id, pwd,name,phonenum);
		out.print(returns);
	} else if(type.equals("checkid"))
	{
		String returns = connectDB.checkId(id);
		out.print(returns);
	}
%>
