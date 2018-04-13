<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%

	String cmd = request.getParameter("cmd");
	System.out.println("cmd = "+cmd);
	if(cmd.equals("id")){
		cmd = "true";	
	} else {
		cmd = "123456";
	}
	String json = "{\"user\":\"admin\",\"message\":\"success\"}";
	out.print(json);
%>