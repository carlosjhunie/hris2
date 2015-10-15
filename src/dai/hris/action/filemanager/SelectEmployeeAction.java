/**
 * 
 */
package dai.hris.action.filemanager;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.SearchEmployee;
import dai.hris.service.filemanager.employee.EmployeeService;
import dai.hris.service.filemanager.employee.IEmployeeService;


@WebServlet("/SelectEmployeeAction")
public class SelectEmployeeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IEmployeeService employeeService = new EmployeeService();	
	
	Gson gson = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		System.out.println("SelectEmployeeAction doGet");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SelectEmployeeAction doPost");
		List<SearchEmployee> list = null;
		String oSearchText = request.getParameter("oSearchText");		
		
		
		try {			

			list = employeeService.selectEmployee(oSearchText);	
			
		} catch (SQLException sqe1) {
			//TODO add proper logging
			System.err.println(sqe1.getMessage());
			sqe1.printStackTrace();
		} catch (Exception e) {
			//TODO add proper logging
			System.err.println(e.getMessage());
			e.printStackTrace();			
		}
		
			
		
		String json = gson.toJson(list);		
		 
		//json = "{\"Employee\":"+json+"}";
	    response.getWriter().print(json);	
		
		
	}

}
