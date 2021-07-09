<%@page import="classes.JotterDBFunctions"%>
<%@page import="java.sql.Connection"%>
<%@page import="classes.DBConnector"%>
<%@page import="classes.Jotter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<%@ include file="navBar.jsp" %>

<%
		Integer notes_id=Integer.parseInt(request.getParameter("notes_id"));
		DBConnector dbConnector=new DBConnector();
		Connection connection=dbConnector.makeConnection();
		JotterDBFunctions jotterDBFunctions=new JotterDBFunctions(connection);
		Jotter jotter=jotterDBFunctions.getNoteById(notes_id);
%>

	<div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-4 ">
          <img src="edit_notes.png" alt="Image" class="img-fluid">
        </div>
        <div class="col-md-8 contents">
          <div class="row justify-content-center">
            <div class="col-md-10">
              <div class="mb-4">
              <h1> Edit <strong>Notes</strong></h1>
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
			%>            <form action="JotterEditNote" method="post">
			
			
			
			<input type="hidden" value="<%= notes_id %>" name="notes_id">
			
              <div class="form-group">
                <input type="text" class="form-control" id="title" name="title" placeholder="Title" value="<%=jotter.getTitleString() %>" required>
              </div>
               <div class="form-group">
                <textarea type="text" class="form-control" id="content" name="content" placeholder="Content"><%= jotter.getContentString() %></textarea>
              </div>
               
              <input type="submit" value="Edit" name="edit" class="btn text-white btn-block btn-primary">

            
              
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