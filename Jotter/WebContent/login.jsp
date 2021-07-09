<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SignIn</title>
</head>
<body>
	<%@ include file="navBar.jsp" %>
	<% if(user!=null) response.sendRedirect("home.jsp"); %>
	<div class="content">
    <div class="container">
      <div class="row">
        <div class="col-md-6 order-md-2">
          <img src="undraw_file_sync_ot38.svg" alt="Image" class="img-fluid">
        </div>
        <div class="col-md-6 contents">
          <div class="row justify-content-center">
            <div class="col-md-10">
              <div class="mb-4">
              <h1>Sign In  <strong>Jotter</strong></h1>
              <p class="mb-4">Write notes, share them with others, and access them from your computer.</p>
            </div>
            <% String login_failed=(String)session.getAttribute("login-failed");
			if(login_failed!=null){ %>
				<div class="alert-danger" role="alert">
				<%=login_failed %>
				</div>
				<%
				session.removeAttribute("login-failed");
			}
				%>
            <form action="UserLogin" method="post">
              <div class="form-group first">
                
                <input type="text" class="form-control" id="username" name="username" placeholder="Username" required>

              </div>
              <div class="form-group last mb-4">
                <input type="password" class="form-control" id="password" placeholder="Password" name="password" required>
                
              </div>
              
              <div class="d-flex mb-2 align-items-center">
                <span class="ml-auto"><a href="forgotPassword.jsp" class="forgot-pass">Forgot Password</a></span> 
              </div>

              <input type="submit" value="Log In" class="btn text-white btn-block btn-primary">

             <div class="d-flex justify-content-center">
                <span><a href="registration.jsp" class="forgot-pass">Don't have an account?</a></span> 
              </div>
              
            </form>
            </div>
          </div>
          
        </div>
        
      </div>
    </div>
	</div>
	<%@ include file="footer.html" %>
</body>

</html>