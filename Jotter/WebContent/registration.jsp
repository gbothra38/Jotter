<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SignUp</title>

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
            <% String success_msg=(String)session.getAttribute("success-msg");
			if(success_msg!=null){%>
				<div class="alert-success" role="alert">
					<%=success_msg %>, <a href="login.jsp">Login from here</a>
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
            <form action="UserRegistration" method="post">
              <div class="form-group">
                <input type="text" class="form-control" id="username" name="username" placeholder="Username" required>
              </div>
               <div class="form-group">
                <input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
              </div>
                <div class="form-group">
                <input type="password" class="form-control" id="password"  pattern="\S{6,}$" name="password" placeholder="Password" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must have at least 6 characters' : ''); if(this.checkValidity()) form.confirm_password.pattern = this.value;" required>
              </div>
              <div class="form-group">
                <input type="password" class="form-control" id="confirm_password" name="confirm_password" placeholder="Re-enter Password" pattern="^\S{6,}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Please enter the same Password as above' : '');" required>
              </div>
                            

              <input type="submit" value="Register" class="btn text-white btn-block btn-primary">

             <div class="d-flex justify-content-center">
                <span><a href="login.jsp" class="forgot-pass">Have an account? Login Here</a></span> 
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