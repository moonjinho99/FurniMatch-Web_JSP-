<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="dbpackage.ConnectDB"%>

<%

String dir = application.getRealPath("/image/");
int max = 10*1024*1024;

MultipartRequest mr = new MultipartRequest(request,dir,max,"UTF-8");

String orgFileName = "image/"+mr.getOriginalFileName("uploaded_file");
String id = mr.getParameter("id");




System.out.println(orgFileName);
System.out.println(id);



ConnectDB con = ConnectDB.getInstance();

String res = con.UpdateProfileImg(orgFileName, id);
out.print(res);
%>


