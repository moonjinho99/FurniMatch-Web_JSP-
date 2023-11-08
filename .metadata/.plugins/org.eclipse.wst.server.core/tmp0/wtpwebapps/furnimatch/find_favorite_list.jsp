<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="dbpackage.ConnectDB"%>
<%@ page import="javax.servlet.annotation.WebServlet"%>
<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String code = request.getParameter("favorite_code");
	String type = request.getParameter("type");
	
	
	System.out.println("--관심목록 전송 아이디--"+id);
	
	ConnectDB connectDB = ConnectDB.getInstance();
		
	 if(type.equals("findname")) {
		String returns = connectDB.findFavoriteCode(id, code);
		System.out.println(returns);
		out.print(returns);
	} else if(type.equals("findfavorite"))
	{
		String returns = connectDB.findFavorite(id);
		System.out.println("관심목록 값 : "+returns);
		out.print(returns);
	}
%>