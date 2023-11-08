<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dbpackage.ConnectDB"%>
<%@ page import="javax.servlet.annotation.WebServlet"%>
<%

	request.setCharacterEncoding("UTF-8");
	
	String favorite_code = request.getParameter("favorite_code");
	String id = request.getParameter("id");
	String type = request.getParameter("type");
	String res="";

	ConnectDB con = new ConnectDB();
	
	if(type.equals("insert"))
	{
		res = con.insertFavorite(favorite_code,id);
	}
	else if(type.equals("delete"))
	{
		res = con.deleteFavorite(favorite_code);
	}
	
	out.print(res);
	
%>