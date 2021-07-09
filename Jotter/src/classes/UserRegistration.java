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
 * Servlet implementation class UserRegistration
 */
@WebServlet("/UserRegistration")
public class UserRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistration() {
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
		//PrintWriter out=response.getWriter();
		String username=request.getParameter("username");
		String email=request.getParameter("email");  
		String password=request.getParameter("password");
		
		User user=new User(username,email,password);
		
		DBConnector dbConnector=new DBConnector();
		Connection connection=dbConnector.makeConnection();
		UserDBFunctions userDBFunctions=new UserDBFunctions(connection);
		boolean flag=userDBFunctions.addUser(user);
		HttpSession httpSession=request.getSession();
		
		if(flag) {
			httpSession.setAttribute("success-msg","Registration Successful");
		}
		else {
			httpSession.setAttribute("failed-msg","Something went wrong");
		}
		
		response.sendRedirect("registration.jsp");
		
	}

}
