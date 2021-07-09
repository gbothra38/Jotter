package classes;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/TransactionDeleteNote")
public class TransactionDeleteNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public TransactionDeleteNote() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer noteid=Integer.parseInt(request.getParameter("notes_id"));
		Integer receiverid=Integer.parseInt(request.getParameter("user_id"));
		DBConnector dbConnector=new DBConnector();
		Connection connection=dbConnector.makeConnection();
		TransactionDBFunctions transactionDBFunctions=new TransactionDBFunctions(connection);
		boolean flag=transactionDBFunctions.deleteNote(noteid,receiverid);
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
		response.sendRedirect("receivedNotes.jsp");
	}


}
