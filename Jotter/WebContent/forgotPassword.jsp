<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% User user1=(User)session.getAttribute("current-user"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgot Password</title>
</head>
<body>
<%@ include file="navBar.jsp" %>
<% if(user!=null) response.sendRedirect("home.jsp"); %>

<% String success_msg=(String)session.getAttribute("success-msg");
			if(success_msg!=null){ %>
				<div class="alert-success" role="alert">
					<%=success_msg %>	
				</div>
				<% response.sendRedirect("forgotPassword.jsp"); %>
			<%
			
			session.removeAttribute("success-msg");
			
			}
			%>
			
			
	<% String failed_msg=(String)session.getAttribute("failed-msg");
			if(failed_msg!=null){%>
				<div class="alert-danger" role="alert">
					<%=failed_msg %>,<a href="registration.jsp"> Register from here</a>
				</div>
			<%
			session.removeAttribute("failed-msg");
			}
			%>
			
			

<form action="ForgotPassword" method="post" >
	<input type="email" name="email" id="email" placeholder="Enter your email id" required><br>
	<input type="submit" value="Submit"> 
</form>
</body>
</html>