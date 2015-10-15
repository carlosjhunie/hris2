package dai.hris.action.login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;

import dai.hris.model.ModuleAccess;
import dai.hris.service.login.IModuleAccessService;
import dai.hris.service.login.ModuleAccessService;

/**
 * Servlet implementation class ModuleAccessAction
 */
@WebServlet("/SaveModuleAccessAction")
public class SaveModuleAccessAction extends HttpServlet {

	private static final long serialVersionUID = -1030457224352077812L;
	private IModuleAccessService svc = new ModuleAccessService();
	Gson gson = new Gson();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] fileManagementArray = request.getParameterValues("fileManagementModule");
		String[] employeeArray = request.getParameterValues("employeeModule");
		String[] timeManagementArray = request.getParameterValues("timeManagementModule");
		String[] payrollArray = request.getParameterValues("payrollModule");
		String[] employeesLoanArray = request.getParameterValues("employeesLoanModule");
		String[] payrollReportsArray = request.getParameterValues("payrollReportsModule");
		String empId = request.getParameter("empId");
		String s_moduleAccessId = request.getParameter("moduleAccessId");
		int moduleAccessId = 0;
		if (null != s_moduleAccessId && !"".equalsIgnoreCase(s_moduleAccessId) && !StringUtils.isEmpty(s_moduleAccessId)) {
			moduleAccessId = Integer.parseInt(s_moduleAccessId);
		}
		try {
			ModuleAccess ma = new ModuleAccess();
			ma.setModuleAccessId(moduleAccessId);
			ma.setEmpId(Integer.parseInt(empId));
			
			if(fileManagementArray != null) {
				ma.setFileManagementList(new ArrayList<String>(Arrays.asList(fileManagementArray)));
			}
			
			if(employeeArray != null) {
				ma.setEmployeeList(new ArrayList<String>(Arrays.asList(employeeArray)));				
			}
			
			if(timeManagementArray != null) {
				ma.setTimeManagementList(new ArrayList<String>(Arrays.asList(timeManagementArray)));
			}
			
			if(payrollArray != null) {
				ma.setPayrollList(new ArrayList<String>(Arrays.asList(payrollArray)));
			}
			
			if(employeesLoanArray != null) {
				ma.setEmployeesLoanList(new ArrayList<String>(Arrays.asList(employeesLoanArray)));
			}
			
			if(payrollReportsArray != null) {
				ma.setPayrollReportsList(new ArrayList<String>(Arrays.asList(payrollReportsArray)));
			}
			
			svc.saveOrUpdate(ma);
			
			//HttpSession session = request.getSession(true);
			//session.setAttribute("moduleAccess", ma);
			
			/*String json = gson.toJson(ma);
			json = "{\"Result\":\"OK\",\"Record\":"+ json + "}";
			response.getWriter().print(json);*/
			response.sendRedirect("moduleAccess.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
