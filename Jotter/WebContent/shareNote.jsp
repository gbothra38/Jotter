<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Share Note</title>
</head>
<body>
<%@ include file="navBar.jsp" %>
<% String notes_idString=request.getParameter("notes_id");
int notes_id;
	if(notes_idString==null){
		notes_id=51;
	}else{
		notes_id=Integer.parseInt(request.getParameter("notes_id"));}%>
	

	<div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-4 ">
          <img src="undraw_file_sync_ot38.svg" alt="Image" class="img-fluid">
        </div>
        <div class="col-md-8 contents">
          <div class="row justify-content-center">
            <div class="col-md-10">
              <div class="mb-4">
              <h1> Share  <strong>Notes</strong></h1>
              <p class="mb-4">Write notes, share them with others, and access them from your computer.</p>
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
			%>         
			
			<% if(user==null){
					response.sendRedirect("index.jsp");
				}
				else{
					  %>
						   
			<form action="JotterShareNote" method="post">
					<input type="hidden" name="notes_id" value="<%= notes_id%>">
					<input type="hidden" value="<%= user.getUser_id() %>" name="user_id">				
					<div class="form-group">
                		<input type="email" class="form-control" id="email" name="email" placeholder="Enter Email">
              		</div>
              		 <input type="submit" value="Share" class="btn text-white btn-block btn-primary">
				<%} %>
              
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