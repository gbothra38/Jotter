<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="classes.JotterDBFunctions"%>
<%@page import="java.sql.Connection"%>
<%@page import="classes.DBConnector"%>
<%@page import="classes.Jotter"%>
<%@ page import="java.io.IOException" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Note</title>
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
<%
		Integer notes_id=Integer.parseInt(request.getParameter("notes_id"));
		DBConnector dbConnector=new DBConnector();
		Connection connection=dbConnector.makeConnection();
		JotterDBFunctions jotterDBFunctions=new JotterDBFunctions(connection);
		Jotter jotter=jotterDBFunctions.getNoteById(notes_id);
		String blobString=InputStreamToString(jotter.getFileBlob());
%>


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
              <h1> <strong><%= jotter.getTitleString() %></strong></h1>
            </div>
            <div class="form-group" style:"margin-right:10px">
                <h10><%= jotter.getpDate() %></h10>
            </div>
            <div class="form-group">
                <h2><%= jotter.getContentString() %></h2>
            </div>
            <% if(blobString!=null){ %>
            <div class="form-group">
                <h2><%= blobString %></h2>
            </div><% } %>
            </div>
          </div>
          
        </div>
        
      </div>
    </div>
	</div>


<%@ include file="footer.html" %>

</body>
</html>