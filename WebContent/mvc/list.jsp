<%@page import="java.util.Arrays"%>
<%@page import="kr.co.bit.vo.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>리스트</title>
</head>
<body>
<%
	ArrayList<MemberVO> list = (ArrayList<MemberVO>)request.getAttribute("list");
	StringBuffer sb = new StringBuffer("<table border=\"1\">");
	sb.append("<tr><td>id</td><td>name</td><td>zipcode</td><td>iadd</td><td>langs</td></tr>");
	for(MemberVO vo : list){
		sb.append("<tr>");
		sb.append("<td>"+vo.getId()+"</td>");
		sb.append("<td>"+vo.getName()+"</td>");
		sb.append("<td>"+vo.getZipcode()+"</td>");
		sb.append("<td>"+vo.getAddr1()+" "+vo.getAddr2()+"</td>");
		sb.append("<td>"+Arrays.toString(vo.getLangs())+"</td>");
		sb.append("</tr>");
	}
	sb.append("</table>");
	out.print(sb.toString());
%>
</body>
</html>