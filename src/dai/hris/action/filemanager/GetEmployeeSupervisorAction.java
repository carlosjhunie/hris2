/**
 * 
 */
package dai.hris.action.filemanager;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.Employee;
import dai.hris.model.GenericDisplayOptionModel;
import dai.hris.service.filemanager.employee.EmployeeService;
import dai.hris.service.filemanager.employee.IEmployeeService;




@WebServlet("/GetEmployeeSupervisorAction")
public class GetEmployeeSupervisorAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IEmployeeService employeeService = new EmployeeService();	
	
	Gson gson = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		//ArrayList<Employee> list = new ArrayList<Employee>();
		HashMap<String, Object> map = null;
		int empId = Integer.parseInt(request.getParameter("empId"));
		
		
		try {			

			System.out.println("the empId here in doGet GetEmployeeAction is " +empId);			
			map = employeeService.getEmployeeSupervisors(empId);			
			
		} catch (SQLException sqe1) {
			//TODO add proper logging
			System.err.println(sqe1.getMessage());
			sqe1.printStackTrace();
		} catch (Exception e) {
			//TODO add proper logging
			System.err.println(e.getMessage());
			e.printStackTrace();			
		}
		
		
		String json = gson.toJson(map);		
		 
	    response.getWriter().print(json);	
		
		
	}

}
