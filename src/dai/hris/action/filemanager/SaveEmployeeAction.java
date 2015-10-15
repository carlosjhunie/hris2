/**
 * 
 */
package dai.hris.action.filemanager;

import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import dai.hris.model.Employee;
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
@WebServlet("/SaveEmployeeAction")
public class SaveEmployeeAction extends HttpServlet {

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
		System.out.println("doPost SaveEmployeeAction");

		
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

		try {
			int empId = 0;
			if(employeeService.checkExistingUserName(employee.getUsername()) == 0) {
				empId = employeeService.add(employee);
			}						
			
			String json2 = gson.toJson(empId);
			
			response.getWriter().print(json2);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}

	}
	



}
