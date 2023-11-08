<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="dbpackage.ConnectDB"%>
<%@ page import="javax.servlet.annotation.WebServlet"%>
<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	
	System.out.println(id);
	
	ConnectDB connectDB = ConnectDB.getInstance();
		
	String res = connectDB.productInfProfileImg(id);
	System.out.println(res);
	out.print(res);
%>