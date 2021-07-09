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
 * Servlet implementation class JotterDeleteNote
 */
@WebServlet("/JotterDeleteNote")
public class JotterDeleteNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JotterDeleteNote() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer noteid=Integer.parseInt(request.getParameter("notes_id"));
		
		DBConnector dbConnector=new DBConnector();
		Connection connection=dbConnector.makeConnection();
		JotterDBFunctions jotterDBFunctions=new JotterDBFunctions(connection);
		boolean flag=jotterDBFunctions.deleteNote(noteid);
		HttpSession httpSession=request.getSession();
		HttpSession session=null;
		if(flag) {
			session=request.getSession();
			session.setAttribute("delete-msg","Successfully Deleted");
			
		}
		else {
			session=request.getSession();
			httpSession.setAttribute("delete-failed-msg","Something went Wrong");
		}
		response.sendRedirect("showNotes.jsp");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

}
