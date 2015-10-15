package dai.hris.action.filemanager.employeeoutofoffice;

import java.sql.Date;
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
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;

import dai.hris.action.filemanager.util.EmployeeUtil;
import dai.hris.model.Employee;
import dai.hris.model.EmployeeOutOfOffice;
import dai.hris.model.Leave;
import dai.hris.service.filemanager.employee.EmployeeService;
import dai.hris.service.filemanager.employee.IEmployeeService;
import dai.hris.service.filemanager.employeeoutofoffice.EmployeeOutOfOfficeService;
import dai.hris.service.filemanager.employeeoutofoffice.IEmployeeOutOfOfficeService;
import dai.hris.service.filemanager.leave.ILeaveService;
import dai.hris.service.filemanager.leave.LeaveService;


@WebServlet("/UpdateOutOfOfficeSvApprovalAction")
public class UpdateOutOfOfficeSvApprovalAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IEmployeeOutOfOfficeService outofOfficeService = new EmployeeOutOfOfficeService();
	IEmployeeService employeeService = new EmployeeService();
	Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		System.out.println("doPost UpdateOutOfOfficeSvApprovalAction");
		int superVisorId = Integer.parseInt(request.getParameter("superVisorId"));
		Employee employee = null;
		EmployeeOutOfOffice employeeOOO = new EmployeeOutOfOffice();
		HashMap<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
		  String name = (String) names.nextElement();
		  map.put(name, request.getParameterValues(name));
		}
		try {
			    
			BeanUtils.populate(employeeOOO, map);
			
		} catch (IllegalAccessException e1) {

			e1.printStackTrace();
		} catch (InvocationTargetException e1) {

			e1.printStackTrace();
		}
		
		
		try {
			employee = employeeService.getEmployee(employeeOOO.getEmpId());
			/**
			 * 0 - FOR APPROVAL
			 * 1 - NOT APPROVED
			 * 2 - APPROVED
			 */
			if (employeeOOO.getStatus() == 2) {  
				if (employee.getSuperVisor1Id() == superVisorId) {
					employeeOOO.setApprovedBy(superVisorId);
				}else if ((employee.getSuperVisor2Id() == superVisorId)) {
					employeeOOO.setSecondaryApprover(superVisorId);
				}
			} else {
				employeeOOO.setApprovedBy(0);
				employeeOOO.setSecondaryApprover(0);
			}

			System.out.println("empOOOId: " + employeeOOO.getEmpOOOId());
			System.out.println("visor 1: " + employee.getSuperVisor1Id());
			System.out.println("visor 2: " + superVisorId);
			System.out.println("emp: " + employeeOOO.getApprovedBy());
			System.out.println("status: " + employeeOOO.getStatus());
			outofOfficeService.approveOOO(employeeOOO);			
			
			String json = gson.toJson(employeeOOO);
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
