/**
 * 
 */
package dai.hris.action.filemanager;


import java.io.IOException;
import java.sql.SQLException;

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



/**
 * @author danielpadilla
 *
 */
@WebServlet("/GetEmployeeAction")
public class GetEmployeeAction extends HttpServlet {
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
		Employee employee = new Employee();
		int empId = Integer.parseInt(request.getParameter("empId"));
		String displayOption = request.getParameter("displayOption");
		
		
		try {			

			System.out.println("the empId here in doGet GetEmployeeAction is " +empId);			
			employee = employeeService.getEmployee(empId);			
			
		} catch (SQLException sqe1) {
			//TODO add proper logging
			System.err.println(sqe1.getMessage());
			sqe1.printStackTrace();
		} catch (Exception e) {
			//TODO add proper logging
			System.err.println(e.getMessage());
			e.printStackTrace();			
		}
		
		if ("true".equalsIgnoreCase(displayOption)) {	
			//if need to be displayed in jTable,use generic model
		    	GenericDisplayOptionModel genericDisplayOptionModel = new GenericDisplayOptionModel();
		    	genericDisplayOptionModel.setValue(employee.getEmpId());
		    	genericDisplayOptionModel.setDisplayText(employee.getEmpNo() + " " + employee.getFirstname() + " "+employee.getLastname());
			
			String json = gson.toJson(genericDisplayOptionModel);
		    System.out.println("1json: " + json);
	 
		    json = "{\"Result\":\"OK\",\"Options\":["+ json + "]}";   ///note the change from Records to Option, added bracket
	        response.getWriter().print(json);
		
		} else {	
		
		
		String json = gson.toJson(employee);		
		 
		json = "{\"Employee\":"+json+"}";
	    response.getWriter().print(json);	
		}
		
	}

}
