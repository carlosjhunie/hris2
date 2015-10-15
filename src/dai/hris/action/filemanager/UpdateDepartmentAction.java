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

import dai.hris.model.Department;
import dai.hris.service.filemanager.department.DepartmentService;
import dai.hris.service.filemanager.department.IDepartmentService;


/**
 * @author rj
 *
 */
@WebServlet("/UpdateDepartmentAction")
public class UpdateDepartmentAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Department department = new Department();
		
		department.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
		department.setDepartmentName((request.getParameter("departmentName")));

		IDepartmentService service = new DepartmentService();
		
		try {
			service.update(department);
			response.getWriter().print("{\"Result\":\"OK\"}");
		}
		catch(Exception e ){
			e.printStackTrace();
			String error = "{\"Result\":\"ERROR\"}";
			 response.getWriter().print(error);
		}

		
		

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
