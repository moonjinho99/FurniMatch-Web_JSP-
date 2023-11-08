<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="dbpackage.ConnectDB"%>

<%
	request.setCharacterEncoding("UTF-8");

	ConnectDB con = ConnectDB.getInstance();

	String res = con.outputARList();

	System.out.println("메인화면 AR 리스트값 : "+res);
	
	out.print(res);

%>