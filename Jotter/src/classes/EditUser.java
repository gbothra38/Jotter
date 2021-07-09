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
 * Servlet implementation class EditUser
 */
@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession=request.getSession();
		User userFromSession=(User) httpSession.getAttribute("current-user");
		
		String username=request.getParameter("username");
		String email=request.getParameter("email"); 
		String password=request.getParameter("password");
		int userid= userFromSession.getUser_id();
		
		User user=new User();
		user.setUser_id(userid);
		user.setUsernameString(username);
		user.setEmailString(email);
		user.setPassworString(password);
		DBConnector dbConnector=new DBConnector();
		Connection connection=dbConnector.makeConnection();
		UserDBFunctions userDBFunctions=new UserDBFunctions(connection);
		boolean flag=userDBFunctions.editUser(user);
		
		
		if(flag) {
			httpSession.setAttribute("current-user", user);
			httpSession.setAttribute("success-msg","Update Successful");
		}
		else {
			httpSession.setAttribute("failed-msg","Something went wrong");
		}
		
		response.sendRedirect("home.jsp");
		
	}
}
