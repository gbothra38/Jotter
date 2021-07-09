<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="classes.User" %>
<!DOCTYPE html>
<html>
<head>



<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="style.css"/>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Jotter</title>
</head>
<body>
<%User user=(User)session.getAttribute("current-user");%>
<%User currUser=(User)session.getAttribute("current-user");%>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <%if(currUser==null){ %>
  <a class="navbar-brand ml-4" href="index.jsp"><h1>Jotter</h1></a>
  <%}else{ %>
  	<a class="navbar-brand ml-4" href="home.jsp"><h1>Jotter</h1></a>
  <%} %>
	<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
  <div class="collapse navbar-collapse justify-content-center" id="navbarsExample08">
    <ul class="navbar-nav">
      
      	<%if(currUser==null){
      %>
      		
      		<li class="nav-item active">
        		<a class="nav-link" href="login.jsp"><h3>Login</h3> </a>
      		</li>
     
      <%}else{%>
      	<li class="nav-item active">
        		<a class="nav-link" href="addNote.jsp">Add Notes </a>
      	</li>
      	<li class="nav-item active">
        		<a class="nav-link" href="showNotes.jsp">Show Notes </a>
      	</li>
      	<li class="nav-item active">
        		<a class="nav-link" href="receivedNotes.jsp">Received Notes</a>
      	</li>

     	
      	<li class="nav-item dropdown active">
      	
        <a class="nav-link dropdown-toggle"  id="dropdown08" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" href="#"><%=currUser.getUsernameString().toUpperCase() %></a>
        <div class="dropdown-menu" aria-labelledby="dropdown08">
          <a class="dropdown-item" data-toggle="modal" data-target="#user_update" href="#">Edit Profile</a>
          <!--  <a class="dropdown-item" data-toggle="modal" data-target="#user_deactivate" href="UserDelete?user_id=<%= currUser.getUser_id()%>">Deactivate Account</a>-->
          
           <a class="dropdown-item" data-toggle="modal" data-target="#user_deactivate" href="#" >Deactivate Account</a>
          <a class="dropdown-item" href="UserLogout">LogOut</a>
        </div>
      </li>	
      	<% }
      %>
    </ul>  
  </div>
</nav>


<% if(currUser!=null){ %>

<!-- Deactivate Account -->
<div class="modal fade" id="user_deactivate" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Deactivate Acoount</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Are you sure?
      </div>
      <div class="modal-footer">
      
        <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
        <a href="UserDelete?user_id=<%= currUser.getUser_id() %>" class="btn btn-primary" >Yes</a>
      </div>
    </div>
  </div>
</div>

<!-- Edit Profile -->
<div class="modal fade" id="user_update" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Update Your Profile</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      
      <div class="modal-body">
        <form action="EditUser" method="post">
              <div class="form-group">
                <input type="text" class="form-control" id="username" name="username" placeholder="Username" value="<%= currUser.getUsernameString() %>"required>
              </div>
               <div class="form-group">
                <input type="email" class="form-control" id="email" name="email" placeholder="Email" value="<%= currUser.getEmailString() %>" required>
              </div>
                <div class="form-group">
                <input type="password" class="form-control" id="password"  pattern="\S{6,}$" name="password" placeholder="Password" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must have at least 6 characters' : ''); if(this.checkValidity()) form.confirm_password.pattern = this.value;" value="<%= currUser.getPassworString() %>>" required>
              </div>
              <div class="form-group">
                <input type="password" class="form-control" id="confirm_password" name="confirm_password" placeholder="Re-enter Password" pattern="^\S{6,}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Please enter the same Password as above' : '');" required>
              </div>
                            

              <input type="submit" value="Update" class="btn text-white btn-block btn-primary">

             
              
            </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
       
      </div>
    </div>
  </div>
</div>

<%} %>

</body>

</html>
