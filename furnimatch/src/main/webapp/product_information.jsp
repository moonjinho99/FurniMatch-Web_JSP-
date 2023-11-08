<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="dbpackage.ConnectDB"%>

<%
	request.setCharacterEncoding("UTF-8");
	String code = request.getParameter("code");
	
	System.out.println("물품코드 : "+code);
	
	ConnectDB connectDB = ConnectDB.getInstance();
	
	String res = connectDB.productInf(code);
	
	System.out.println(res);
	
	out.print(res);
%>

