<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dbpackage.ConnectDB"%>
<%@ page import="javax.servlet.annotation.WebServlet"%>
<%

	request.setCharacterEncoding("UTF-8");
	
	String sell_code = request.getParameter("sell_code");
	String id = request.getParameter("id");
	
	System.out.println(sell_code);
	System.out.println(id);

	ConnectDB con = new ConnectDB();
	
	con.DeleteSellProduct(id,sell_code);
	
%>