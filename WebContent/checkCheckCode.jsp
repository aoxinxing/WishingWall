<%@ page contentType="text/html; charset=UTF-8" language="java"  %>
<%
String inCheckCode=request.getParameter("inCheckCode");
	if(session.getAttribute("randCheckCode").equals(inCheckCode)){
		out.println("1");
	}else{
		out.println("0");
	}
%>