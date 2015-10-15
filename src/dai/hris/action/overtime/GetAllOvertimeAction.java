package dai.hris.action.overtime;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.EmployeeOutOfOffice;
import dai.hris.model.EmployeeOvertime;
import dai.hris.service.filemanager.employeeoutofoffice.IEmployeeOutOfOfficeService;
import dai.hris.service.filemanager.employeeoutofoffice.EmployeeOutOfOfficeService;
import dai.hris.service.overtime.EmployeeOvertimeService;
import dai.hris.service.overtime.IEmployeeOvertimeService;

@WebServlet("/GetAllOvertimeAction")
public class GetAllOvertimeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int empId = Integer.parseInt(request.getParameter("empId"));
		IEmployeeOvertimeService employeeOvertimeService = new EmployeeOvertimeService();
		ArrayList<EmployeeOvertime> list = null;
		try {
			list = employeeOvertimeService.getEmployeeOvertimeByEmpId(empId);
		}
		catch(Exception e ){
			e.printStackTrace();
		}	

	    String json = gson.toJson(list);
 
	    json = "{\"Result\":\"OK\",\"Records\":"+ json + "}";
        response.getWriter().print(json);

	}
	

}
