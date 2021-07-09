package classes;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class JotterEditNote
 */
@WebServlet("/JotterEditNote")
public class JotterEditNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JotterEditNote() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noteid=Integer.parseInt(request.getParameter("notes_id"));
		String titleString=request.getParameter("title");
		String contentString=request.getParameter("content");
		
		Jotter jotter=new Jotter();
		jotter.setTitleString(titleString);
		jotter.setContentString(contentString);
		jotter.setJotter_id(noteid);
		

		DBConnector dbConnector=new DBConnector();
		Connection connection=dbConnector.makeConnection();
		JotterDBFunctions jotterDBFunctions=new JotterDBFunctions(connection);
		boolean flag=jotterDBFunctions.updateNote(jotter);
		HttpSession httpSession=request.getSession();
		
		if(flag) {
			httpSession.setAttribute("update-msg","Successfully Updated");
		}
		else {
			httpSession.setAttribute("update-failed-msg","Something went Wrong");
		}
		
		response.sendRedirect("showNotes.jsp");
		
	}

}
