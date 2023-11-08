<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="dbpackage.ConnectDB"%>
<%@ page import="javax.servlet.annotation.WebServlet"%>
<%
	request.setCharacterEncoding("UTF-8");
	String find_id = request.getParameter("id");
	
	System.out.println(find_id);
	
	ConnectDB connectDB = ConnectDB.getInstance();
		
	
	String returns = connectDB.findUserInf(find_id);
	System.out.println(returns);
	out.print(returns);
	
%>