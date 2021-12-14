<%@page import="classes.JotterDBFunctions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="classes.Jotter" %> 
<%@ page import="classes.DBConnector"%>
<%@ page import="classes.User" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.io.InputStream" %>

<% User user1=(User)session.getAttribute("current-user"); %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">


</style>
<meta charset="ISO-8859-1">
<title>Show Notes</title>
</head>
<body>
<%!
public String InputStreamToString(InputStream inputStream) throws IOException {
	   
    ByteArrayOutputStream result = new ByteArrayOutputStream();
    byte[] buffer = new byte[1024];
    for (int length; (length = inputStream.read(buffer)) != -1; ) {
        result.write(buffer, 0, length);
    }
    // StandardCharsets.UTF_8.name() > JDK 7
    return result.toString("UTF-8");
}
%>
<%@ include file="navBar.jsp" %>
	<% if(user1==null) response.sendRedirect("login.jsp"); %>
	
	
	<%
		if(user1!=null){
			DBConnector dbconnector=new DBConnector();
			JotterDBFunctions jotterDBFunctions=new JotterDBFunctions(dbconnector.makeConnection());
			List<Jotter> listNotes=null;
			%>
			<div class="container" style=" margin-top:20px; margin-bottom:20px;">
				<div class="row">
				<div class="col-4">
					
				</div>
				<div class="col-4">
					<nav class="navbar-nav navbar-light bg-light ml-auto mr-auto">
  				<form class="form-inline" method="get">
    				<input class="form-control mr-sm-2" id="search" name="search" type="search" placeholder="Search Here" aria-label="Search">
    			<input class="btn btn-outline-success my-2 my-sm-0" type="submit" value="Search"/>
  				</form>
			</nav>
				</div>
				<div class="col-4">
					
				</div>
				</div>
			</div>
			
			
			
			<% 
			String queryString=request.getParameter("search");
			if(queryString!=null){
				
				listNotes=jotterDBFunctions.searchList(user1.getUser_id(),queryString);
			}
			else{
				listNotes=jotterDBFunctions.getNotes(user1.getUser_id());	
			}
			System.out.println("User found"+ listNotes.size());
			%>
			
			<div class="container-fluid">
				<div class="container-fluid">
					<div class="row">
						<% for(Jotter jotter:listNotes) {%>
						  <div class="col-sm-6">
						    <a href="singleNote.jsp?notes_id=<%= jotter.getJotter_id() %>"><div class="card" style="height: 18rem; margin-bottom:10px">
						      <div class="card-body">
						        <h1 class="card-title"><%=jotter.getTitleString() %></h1>
						        <h6 class="card-text"><%= jotter.getpDate() %></h6>
						        
						        <% String blobString=InputStreamToString(jotter.getFileBlob());%>
						        
						        <div class="overflow-auto p-3 mb-3 mb-md-3 bg-light" style="max-width: 100%; max-height: 100px;">
						        	<h5>Content:<p class="card-text"><%= jotter.getContentString() %></p>
						        <% if(blobString!=null){ System.out.println("Start");%>FileContent:
						        <p class="card-text"><%= blobString %></h5></p></div>
						        
						        	<% } %>
						        <a href="editNote.jsp?notes_id=<%= jotter.getJotter_id() %>"><div class="btn btn-primary" >Edit Note</div></a>
								<a href="JotterDeleteNote?notes_id=<%= jotter.getJotter_id() %>"> <div class="btn btn-primary" > Delete Note</div> </a>
								<a href="shareNote.jsp?notes_id=<%=jotter.getJotter_id() %>"><div class="btn btn-primary">    Share   </div>    </a>
						      </div>
						    </div></a>
						  </div>
						  <%} %>
					</div>
				</div>
			</div>
			<%} %>
			
			


			
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


</body>
</html>