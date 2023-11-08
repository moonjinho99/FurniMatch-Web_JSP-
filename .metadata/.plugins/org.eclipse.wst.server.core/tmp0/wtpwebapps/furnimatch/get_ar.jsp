<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="dbpackage.ConnectDB"%>
<%@ page import="javax.servlet.annotation.WebServlet"%>
<%
	request.setCharacterEncoding("UTF-8");
	String code = request.getParameter("code");
	
	System.out.println("ar코드값"+code);
	
	ConnectDB connectDB = ConnectDB.getInstance();
		
	
	String returns = connectDB.getAR(code);
	System.out.println(returns);
	
	out.print(returns);
	
%>