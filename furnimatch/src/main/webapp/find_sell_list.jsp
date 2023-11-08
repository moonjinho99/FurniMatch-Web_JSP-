<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="dbpackage.ConnectDB"%>
<%@ page import="javax.servlet.annotation.WebServlet"%>
<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	
	ConnectDB connectDB = ConnectDB.getInstance();
		
	 String res = connectDB.findSellItems(id);
	 System.out.println("판매 리스트 값 : "+res);
	 out.print(res);
%>