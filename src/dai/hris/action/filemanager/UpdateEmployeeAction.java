/**
 * 
 */
package dai.hris.action.filemanager;

import java.sql.SQLException;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import dai.hris.model.Employee;
import dai.hris.service.filemanager.employee.EmployeeService;
import dai.hris.service.filemanager.employee.IEmployeeService;



@WebServlet("/UpdateEmployeeAction")
public class UpdateEmployeeAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IEmployeeService employeeService = new EmployeeService();	
	Gson gson = new Gson();	

	/**
	 * when accessing URL, this is called
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**called on post
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		System.out.println("doPost UpdateEmployeeAction");
		Employee employeeLoggedIn = (Employee) request.getSession().getAttribute("employeeLoggedIn");

		
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
        
		System.out.println("here is the json output: ");
		System.out.println("=========================");
		System.out.println(json);
		System.out.println("=========================");
 
        // 2. initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();
 
        // 3. Convert received JSON to Article
        Employee employee = mapper.readValue(json, Employee.class);
        String responseJson = "";
		try {
			if (StringUtils.isEmpty((employee.getUsername()))) {
				throw new Exception("Username should not be EMPTY!");
				//removed update password in update employee				
			}
			
			
								 //the empId of existing user that owns the username
//			List<Integer> empListWithEmployeeModuleAccess = employeeService.getAllEmpIdsWithEmployeeModuleAccess();  //all of these can update
//			
//			if (empIdExisting >0 && CollectionUtils.isNotEmpty(empListWithEmployeeModuleAccess)) {				
//				empListWithEmployeeModuleAccess.add(new Integer(empIdExisting));						//existing empId and users that have access to emp module can update it
//			}
//			
//			if (!empListWithEmployeeModuleAccess.contains(employee.getEmpId()) && 
//					!(employeeLoggedIn.getEmpId()> 0 && empListWithEmployeeModuleAccess.contains(employeeLoggedIn.getEmpId()))) {
//				throw new Exception("The update failed. Please check your username.");
//				//removed update password in update employee				
//			}
			
			int empId = 0;
			Employee oldEmpRecord = employeeService.getEmployee(employee.getEmpId());
			
			if(oldEmpRecord.getUsername().equals(employee.getUsername())){
				empId = employeeService.updateExcludingPassword(employee);		
			} else {				
				if(employeeService.checkExistingUserName(employee.getUsername()) == 0) {
					empId = employeeService.updateExcludingPassword(employee);
				}
			}

				
			
			responseJson = gson.toJson(empId);			
			//response.getWriter().print(responseJson);
			
			
		} catch (SQLException e) {			
			e.printStackTrace();
			response.getWriter().print(e.getMessage());
		} catch (Exception e) {		
			e.printStackTrace();
			response.getWriter().print(e.getMessage());
		}finally {
			response.getWriter().print(responseJson);
		}

	}
	



}
