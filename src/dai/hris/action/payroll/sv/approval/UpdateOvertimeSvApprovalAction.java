/**
 * 
 */
package dai.hris.action.payroll.sv.approval;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import com.google.gson.Gson;

import dai.hris.model.Employee;
import dai.hris.model.EmployeeOvertime;
import dai.hris.service.filemanager.employee.EmployeeService;
import dai.hris.service.filemanager.employee.IEmployeeService;
import dai.hris.service.overtime.EmployeeOvertimeService;
import dai.hris.service.overtime.IEmployeeOvertimeService;


@WebServlet("/UpdateOvertimeSvApprovalAction")
public class UpdateOvertimeSvApprovalAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IEmployeeOvertimeService overtimeService = new EmployeeOvertimeService();
	IEmployeeService employeeService = new EmployeeService();
	Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		System.out.println("doPost UpdateOvertimeSvApprovalAction");
		int superVisorId = Integer.parseInt(request.getParameter("superVisorId"));
		Employee employee = null;
		EmployeeOvertime overtime = new EmployeeOvertime();
		HashMap<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
		  String name = (String) names.nextElement();
		  map.put(name, request.getParameterValues(name));
		}
		try {
			    
			BeanUtils.populate(overtime, map);

			
		} catch (IllegalAccessException e1) {

			e1.printStackTrace();
		} catch (InvocationTargetException e1) {

			e1.printStackTrace();
		}
		
		
		try {
			employee = employeeService.getEmployee(overtime.getEmpId());
			/**
			 * 0 - FOR APPROVAL
			 * 1 - NOT APPROVED
			 * 2 - APPROVED
			 */
			if (overtime.getStatus() == 2) {  
				if (employee.getSuperVisor1Id() == superVisorId) {
					overtime.setApprovedBy(superVisorId);
				}else if ((employee.getSuperVisor2Id() == superVisorId)) {
					overtime.setSecondaryApprover(superVisorId);
				}
			} else {
				overtime.setApprovedBy(0);
				overtime.setSecondaryApprover(0);
			}
			overtimeService.update(overtime);			
			
			String json = gson.toJson(overtime);
			String jsonData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
			response.getWriter().print(jsonData);			
			
		} catch (SQLException e) {
			e.printStackTrace();
			String error = "{\"Result\":\"ERROR\"}";
			 response.getWriter().print(error);
		} catch (Exception e) {
			e.printStackTrace();
			String error = "{\"Result\":\"ERROR\"}";
			 response.getWriter().print(error);
		}

	}
	



}
