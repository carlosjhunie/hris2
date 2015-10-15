/**
 * 
 */
package dai.hris.action.filemanager;

import java.sql.SQLException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.service.filemanager.employee.EmployeeService;
import dai.hris.service.filemanager.employee.IEmployeeService;



/**
 * @author rj
 * revised by daniel padilla
 * 
 * TODO: add validation for fields in jsp
 * 			populate supervisor ids properly
 *
 */
@WebServlet("/UpdatePasswordAction")
public class UpdatePasswordAction extends HttpServlet {	
	private static final long serialVersionUID = 1L;
	IEmployeeService employeeService = new EmployeeService();	
	Gson gson = new Gson();

	

	/**
	 * when accessing URL, this is called
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String json = "";
		String empId = request.getParameter("empId");
		String oldPassword = request.getParameter("oldPassword");		
		
		String result = "";
		try {
			if(employeeService.verifyOldPassword(empId, oldPassword)){
				result = "success";
				json = gson.toJson(result);				
			    response.getWriter().print(json);
			} else {
				result = "failed";
				json = gson.toJson(result);					
			    response.getWriter().print(json);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}		
		
		

	} 

	/**called on post
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {	
		
		
		String json = "";
		String empId = request.getParameter("empId");
		String newPassword = request.getParameter("newPassword1");		

		try {
			employeeService.updatePassword(empId, newPassword);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}		
		
		json = gson.toJson("success");
		
		 
		 json = "{\"Employee\":"+json+"}";
	     response.getWriter().print(json);



		

	}
	



}
