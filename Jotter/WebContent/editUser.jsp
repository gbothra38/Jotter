<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SignUp</title>
<script>
	function validate(){
		var email=document.getElementById("email").value;
		var password=document.getElementById("password").value;
		var confirm_password=document.getElementById("confirm_password").value;
		var pattern = new RegExp(/^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d][A-Za-z\d!@#$%^&*()_+]{7,19}$/);

	        

		
		if(email==""){
			document.getElementById("email_error").innerHTML="Please enter Email";
			return false;	
		}
		else{
			document.getElementById("email_error").innerHTML="";
		}
		
		if(password==""){
			document.getElementById("password_error").innerHTML="Please enter Password";
			return false;	
		}
		else{
			document.getElementById("password_error").innerHTML="";
		}
		
		if(password.length<7){
			document.getElementById("password_error").innerHTML="Password should be of minimum 7 characters";
			return false;
		}
		
        if (pattern.test(password)==false){
			document.getElementById("password_error").innerHTML="Password should contain uppercase, lower case letter, number and special character";
			return false;
		}
        
        if(confirm_password==""){
			document.getElementById("confirm_password_error").innerHTML="Please enter Confirm Password";
			return false;	
		}
		else{
			document.getElementById("confirm_password_error").innerHTML="";
		}
        
        if(confirm_password!=password){
			document.getElementById("confirm_password_error").innerHTML="Password doesn't match";
			return false;	
		}
		else{
			document.getElementById("email_error").innerHTML="";
		}
		
		
		return true;
	}
</script>
</head>
<body>
	<%@ include file="navBar.jsp" %>
	<% if(user==null) response.sendRedirect("registration.jsp"); %>
	<div align="center">
		
		
		
		<h1>SignUp Page</h1>
		<form action="EditUser" method="POST">
		<input type="hidden"  value="<%= user.getUser_id() %>" name="user_id">
			Username:<input type="text" id="username" name="username" value="<%= user.getUsernameString() %>" required>
			<span style="color:red">*</span>
			Email:
			<span style="color:red">*</span> 
			<input type="email" id="email" name="email" value="<%= user.getEmailString() %>" required></input>
			
			Password: 
			<input id="password" name="password" type="password" pattern="\S{6,}$" value="<%= user.getPassworString() %>" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must have at least 6 characters' : ''); if(this.checkValidity()) form.confirm_password.pattern = this.value;" disabled>
  
			Confirm Password:
			 <span style="color:red" id="confirm_password_error">*</span>
			 <input id="confirm_password" name="password_two" type="password" pattern="^\S{6,}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Please enter the same Password as above' : '');" placeholder="Verify Password" required>
			 
			 Phone Number:
			<!-- <spam style="color:red" id="password_error">*</spam> --> 
			<input type="text" id="phone_number" name="phone_number" value="<%= user.getPhone_number() %>" required></input><br>
			
			<input type="submit" name="register" value="Submit"></input>
		</form>
	</div>
	
</body>
</html>