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
				<% response.sendRedirect("login.jsp"); %>
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

			

<form action="EnterOTP" method="post" >
	<input type="email" name="email" id="email" placeholder="Enter email" required><br>
	<input type="text" name="otp" id="otp" placeholder="Enter OTP" required><br>
	<input type="password" name="password" id="password" pattern="\S{6,}$" placeholder="Enter new password"  onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must have at least 6 characters' : ''); if(this.checkValidity()) form.re_password.pattern = this.value;" required><br>
	<input type="password" name="re_password" id="re_password" placeholder="Re-enter password" pattern="^\S{6,}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Please enter the same Password as above'" required><br>
	<input type="submit" value="Submit"> 
</form>
</body>
</html>