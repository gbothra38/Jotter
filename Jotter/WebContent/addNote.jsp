<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="classes.User" %>
 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Note</title>
</head>
<body style="background-color:white;">
<%@ include file="navBar.jsp" %>

	<div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-4 ">
          <img src="add_notes_2.jpg" alt="Image" class="img-fluid">
        </div>
        <div class="col-md-8 contents">
          <div class="row justify-content-center">
            <div class="col-md-10">
              <div class="mb-4">
              <h1> Add  <strong>Notes</strong></h1>
              <p class="mb-4"><h4>Write notes, share them with others, and access them from your computer.</h4></p>
            </div>
<% String success_msg=(String)session.getAttribute("success-msg");
			if(success_msg!=null){%>
				<div class="alert-success" role="alert">
					<%=success_msg %>
				</div>
			<%
			session.removeAttribute("success-msg");
			}
			String failed_msg=(String)session.getAttribute("failed-msg");
			if(failed_msg!=null){%>
				<div class="alert-danger" role="alert">
					<%=failed_msg %>
				</div>
			<%
			session.removeAttribute("failed-msg");
			}
			%>            <form action="UserNotes" method="post" enctype="multipart/form-data">
			
			<% if(user==null){
			response.sendRedirect("login.jsp");
			}
			else{%>
			
			
			<input type="hidden" value="<%= user.getUser_id() %>" name="user_id">
			<%} %>
              <div class="form-group">
                <input type="text" class="form-control" id="title" name="title" placeholder="Title" required>
              </div>
               <div class="form-group">
                <textarea type="text" class="form-control" id="content" name="content" rows="20" cols="100" placeholder="Content"></textarea>
              </div>
                <div class="form-group">
                <input type="file" class="form-control-file"" id="file" name="file" size="50">
              </div>
              
               
              <input type="submit" value="Add" class="btn text-white btn-block btn-primary">

            
              
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