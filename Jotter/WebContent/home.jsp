<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>
<%@ include file="navBar.jsp" %>
<% if(user==null)	response.sendRedirect("login.jsp"); %>
<% String success_msg=(String)session.getAttribute("success-msg");
			if(success_msg!=null){%>
				<div class="alert-success" role="alert">
					<%=success_msg %>
				</div>
			<%
			session.removeAttribute("success-msg");
			}
			%>
			
			
		<% String failed_msg=(String)session.getAttribute("failed-msg");
			if(failed_msg!=null){%>
				<div class="alert-danger" role="alert">
					<%=failed_msg %>
				</div>
			<%
			session.removeAttribute("failed-msg");
			}
			%>
			<% response.sendRedirect("addNote.jsp"); %>
</body>
</html>