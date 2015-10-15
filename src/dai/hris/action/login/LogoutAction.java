package dai.hris.action.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import dai.hris.model.Employee;
import dai.hris.service.login.ILoginService;
import dai.hris.service.login.LoginService;



/**
 * Servlet implementation class for Servlet: LogoutAction
 *
 */
@WebServlet("/LogoutAction")
 public class LogoutAction extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	 private static final long serialVersionUID = -6185891323760506163L;
	 static Logger logger = Logger.getLogger(LogoutAction.class.getClass());
	 /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public LogoutAction() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		
			
		session.invalidate();
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
		if (dispatcher != null) {
			response.setContentType("text/html");
			dispatcher.include(request, response);
		}
		
		
		
	}   	  	    
}