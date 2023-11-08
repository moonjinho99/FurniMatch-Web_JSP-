<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="dbpackage.ConnectDB"%>

<%

String dir = application.getRealPath("/image/");
int max = 10*1024*1024;

MultipartRequest mr = new MultipartRequest(request,dir,max,"UTF-8");

String orgFileName1 = "image/"+mr.getOriginalFileName("uploaded_file1");
String orgFileName2 = "image/"+mr.getOriginalFileName("uploaded_file2");
String orgFileName3 = "image/"+mr.getOriginalFileName("uploaded_file3");
String orgFileName4 = "image/"+mr.getOriginalFileName("uploaded_file4");

String sellerName = mr.getParameter("sellerName");
String title = mr.getParameter("title");
String address = mr.getParameter("address");
String price = mr.getParameter("price");
String width = mr.getParameter("width");
String length = mr.getParameter("length");
String height = mr.getParameter("height");
String content = mr.getParameter("content");
String category = mr.getParameter("category");
String sellerId = mr.getParameter("sellerId");


String code=title.substring(0,1)+address.substring(0,1)+price.substring(0,1);
System.out.println(code);

System.out.println(orgFileName1);
System.out.println(orgFileName2);
System.out.println(orgFileName3);
System.out.println(orgFileName4);


ConnectDB con = ConnectDB.getInstance();

String insertResult = con.insertProduct(orgFileName1,orgFileName2,orgFileName3,orgFileName4,sellerName,title,address,price,width,length,height,content,category,sellerId);

if(insertResult.equals("ok"))
{
	out.print("ok");
	con.insertSellProduct(sellerId, code);
}
	
else
	out.print("no");
%>


