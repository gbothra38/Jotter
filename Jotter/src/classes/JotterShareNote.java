package classes;

import java.io.IOException;
import java.sql.Connection;

import javax.mail.Flags.Flag;
import javax.mail.search.AndTerm;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class JotterShareNote
 */
@WebServlet("/JotterShareNote")
public class JotterShareNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JotterShareNote() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		int notes_id=Integer.parseInt(request.getParameter("notes_id"));
		int sender_id=Integer.parseInt(request.getParameter("user_id"));
		System.out.println("notes_id:"+notes_id+"sender_id:"+sender_id);
		String receiverEmailString=request.getParameter("email");
		DBConnector dbConnector=new DBConnector();
		Connection connection=dbConnector.makeConnection();
		UserDBFunctions userDBFunctions=new UserDBFunctions(connection);
		User receiverUser=userDBFunctions.getUserByEmail(receiverEmailString);
		HttpSession httpSession=request.getSession();
		if(receiverUser!=null) {
			int receiver_id=receiverUser.getUser_id();
			if(receiver_id==sender_id) {
				httpSession.setAttribute("failed-msg", "Please dont enter your own email address");
				response.sendRedirect("shareNote.jsp");
				return;
			}
			TransactionDBFunctions transactionDBFunctions=new TransactionDBFunctions(connection);
			boolean flag=transactionDBFunctions.addTransaction(sender_id,receiver_id,notes_id);
			
			if(flag==true) {
				httpSession.setAttribute("success-msg", "Successfully Sent.");
			}
			else {
				httpSession.setAttribute("failed-msg", "Something went wrong.");
			}
		}
		else {
			httpSession.setAttribute("failed-msg", "Please enter registered email address");
		}
		
		response.sendRedirect("shareNote.jsp");
		
	}

}
