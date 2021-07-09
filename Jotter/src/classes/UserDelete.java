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
 * Servlet implementation class UserDelete
 */
@WebServlet("/UserDelete")
public class UserDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer userid=Integer.parseInt(request.getParameter("user_id"));
		
		DBConnector dbConnector=new DBConnector();
		Connection connection=dbConnector.makeConnection();
		UserDBFunctions userDBFunctions=new UserDBFunctions(connection);
		boolean flag=userDBFunctions.deleteUser(userid);
		HttpSession httpSession=request.getSession();
		HttpSession session=null;
		if(flag) {
			session=request.getSession();
			session.setAttribute("delete-msg","Successfully Deleted");
			httpSession.removeAttribute("current-user");
			response.sendRedirect("registration.jsp");
		}
		else {
			session=request.getSession();
			httpSession.setAttribute("delete-failed-msg","Something went Wrong");
			response.sendRedirect("home.jsp");
		}
		
	}

	

}
