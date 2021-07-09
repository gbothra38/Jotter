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
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		User user=new User();
		user.setUsernameString(username);
		user.setPassworString(password);
		
		DBConnector connector=new DBConnector();
		Connection connection=connector.makeConnection();
		
		UserDBFunctions userDBFunctions=new UserDBFunctions(connection);
		
		User currentUser=userDBFunctions.getUser(user);
		HttpSession httpSession=request.getSession();
		if(currentUser!=null) {
			response.sendRedirect("home.jsp");
			httpSession.setAttribute("current-user", currentUser);
		}
		else {
			
			httpSession.setAttribute("login-failed", "Please enter valid username and password");
			response.sendRedirect("login.jsp");
		}
	}

}
