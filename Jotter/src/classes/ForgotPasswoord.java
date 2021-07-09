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
 * Servlet implementation class ForgotPasswoord
 */
@WebServlet("/ForgotPassword")
public class ForgotPasswoord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ForgotPasswoord() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailString=request.getParameter("email");
		User user=null;
		DBConnector dbConnector=new DBConnector();
		Connection connection=dbConnector.makeConnection();
		UserDBFunctions userDBFunctions=new UserDBFunctions(connection);
		user=userDBFunctions.getUserByEmail(emailString);
		HttpSession httpSession= request.getSession();
		if(user!=null) {
			MailFunction.sendMailForPassword(emailString, user.getUser_id());
			httpSession.setAttribute("success-msg", "Email has been sent to your mail");
		}
		
		else {
			httpSession.setAttribute("failed-msg", "Invalid Email Id");
		}
		response.sendRedirect("forgotPassword.jsp");
	}

}
