package dai.hris.action.filemanager.employeepayrollinfo;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.EmployeePayrollInfo;
import dai.hris.service.filemanager.employeepayrollinfo.EmployeePayrollInfoService;
import dai.hris.service.filemanager.employeepayrollinfo.IEmployeePayrollInfoService;

/**
 * Servlet implementation class GetEmployeePayrollInfoAction
 */
@WebServlet("/GetEmployeePayrollInfoAction")
public class GetEmployeePayrollInfoAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IEmployeePayrollInfoService employeePayrollInfoService = new EmployeePayrollInfoService();
	Gson gson = new Gson();


    public GetEmployeePayrollInfoAction() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost GetEmployeePayrollInfoAction");
		EmployeePayrollInfo employeePayrollInfo = new EmployeePayrollInfo();
		int empId = Integer.parseInt(request.getParameter("empId"));
		try {
			employeePayrollInfo = employeePayrollInfoService.getEmployeePayrollInfo(empId);
		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			sqle.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/json");
		String jsonObject = gson.toJson(employeePayrollInfo);
		
		System.out.println("jsonObject: " + jsonObject);

        
        jsonObject = "{\"EmployeePayrollInfo\":"+jsonObject+"}";
	    response.getWriter().print(jsonObject);
	}

}
