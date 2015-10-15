package dai.hris.action.login;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dai.hris.model.Employee;
import dai.hris.model.ModuleAccess;
import dai.hris.service.login.ILoginService;
import dai.hris.service.login.IModuleAccessService;
import dai.hris.service.login.LoginService;
import dai.hris.service.login.ModuleAccessService;




/**
 * Servlet implementation class for Servlet: LoginAction
 *
 */
@WebServlet("/LoginAction")
 public class LoginAction extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	 private static final long serialVersionUID = -6185891323760506163L;	 
	 private IModuleAccessService svc = new ModuleAccessService();
	 
	 /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public LoginAction() {
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
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");			
		
		ILoginService ls = new LoginService();	
        
		try {
			
				if(ls.checkCredentials(username, password)){	
					Employee emp = ls.getEmployee(username);
					//ls.updateLoginActivity(emp.getEmpId(), emp.getDepartmentId(), "LOGIN");
					session.setAttribute("employeeLoggedIn", emp);					
		        	
					ModuleAccess ma = svc.getModuleAccessByEmpId(emp.getEmpId());
					session.setAttribute("moduleAccess", ma);
					
					RequestDispatcher dispatcher = null;
					dispatcher = getServletContext().getRequestDispatcher("/employeeDashBoard.jsp");			    

					if (dispatcher != null) {
			        	response.setContentType("text/html");
			            dispatcher.include(request, response);
			        }											
				} else {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp?isExist=0");
					if (dispatcher != null) {
						response.setContentType("text/html");
						dispatcher.include(request, response);
					}
				}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}   	  	    
}