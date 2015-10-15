/**
 * 
 */
package dai.hris.action.filemanager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


import dai.hris.model.EmployeeType;
import dai.hris.service.filemanager.employeetype.EmployeeTypeService;
import dai.hris.service.filemanager.employeetype.IEmployeeTypeService;


/**
 * @author rj
 *
 */
@WebServlet("/AddEmployeeTypeAction")
public class AddEmployeeTypeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		EmployeeType empType = new EmployeeType();
		empType.setEmployeeTypeName((request.getParameter("employeeTypeName")));
		
		IEmployeeTypeService service = new EmployeeTypeService();
	

		try {
			service.add(empType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String json = gson.toJson(empType);
		System.out.println("json: " + json);
		String jsonData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
		response.getWriter().print(jsonData);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}

}
