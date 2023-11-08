<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="dbpackage.ConnectDB"%>
<%@ page import="javax.servlet.annotation.WebServlet"%>
<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String type = request.getParameter("type");
	
	
	System.out.println(id);
	
	ConnectDB connectDB = ConnectDB.getInstance();
		
	 if(type.equals("findname")) {
		String returns = connectDB.findNamedb(id);
		System.out.println(returns);
		out.print(returns);
	} 
%>