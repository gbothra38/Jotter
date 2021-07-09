package classes;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;



/**
 * Servlet implementation class UserNotes
 */
@WebServlet("/UserNotes")
@MultipartConfig(maxFileSize = 16177215) 
public class UserNotes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserNotes() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int user_id=Integer.parseInt(request.getParameter("user_id"));
		String titleString=request.getParameter("title");
		String contentString=request.getParameter("content");
		InputStream inputStream = null; // input stream of the upload file
        
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("file");
        if (filePart != null) {
            // prints out some information for debugging
//            System.out.println(filePart.getName());
//            System.out.println(filePart.getSize());
//            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
            System.out.println(inputStream);
        }
                		
		Jotter jotter=new Jotter(user_id,titleString, contentString, inputStream );
        
        
		DBConnector dbConnector=new DBConnector();
		Connection connection=dbConnector.makeConnection();
		JotterDBFunctions jotterDBFunctions=new JotterDBFunctions(connection);
		boolean flag=jotterDBFunctions.addNotes(jotter);
		HttpSession httpSession=request.getSession();
		
		if(flag) {
			httpSession.setAttribute("success-msg","Successfully Added");
		}
		else {
			httpSession.setAttribute("failed-msg","Something went Wrong");
		}
		
		response.sendRedirect("addNote.jsp");
	}   
}