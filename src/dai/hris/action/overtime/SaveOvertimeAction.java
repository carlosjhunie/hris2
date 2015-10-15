/**
 * 
 */
package dai.hris.action.overtime;

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
import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;

import dai.hris.action.filemanager.util.EmployeeUtil;
import dai.hris.model.EmployeeOutOfOffice;
import dai.hris.model.EmployeeOvertime;
import dai.hris.model.Leave;
import dai.hris.service.filemanager.employeeoutofoffice.EmployeeOutOfOfficeService;
import dai.hris.service.filemanager.employeeoutofoffice.IEmployeeOutOfOfficeService;
import dai.hris.service.filemanager.leave.ILeaveService;
import dai.hris.service.filemanager.leave.LeaveService;
import dai.hris.service.overtime.EmployeeOvertimeService;
import dai.hris.service.overtime.IEmployeeOvertimeService;


@WebServlet("/SaveOvertimeAction")
public class SaveOvertimeAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IEmployeeOvertimeService employeeOvertimeService = new EmployeeOvertimeService();
	Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		System.out.println("doPost SaveOutOfOfficeAction");

		EmployeeOvertime employeeOvertime= new EmployeeOvertime();
		HashMap<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
		  String name = (String) names.nextElement();
		  map.put(name, request.getParameterValues(name));
		}
		try {
			 ConvertUtilsBean convertUtilsBean = BeanUtilsBean.getInstance().getConvertUtils();
			    convertUtilsBean.register(false, true, -1);
			    
			BeanUtils.populate(employeeOvertime, map);
			
			//now since we have populated already, we need to set the values again for the fields that are not straightly mapped from request parameters
			employeeOvertime.setCreatedBy(employeeOvertime.getEmpId());
			employeeOvertime.setCreatedDate(EmployeeUtil.getCurrentSystemDateSqlFormat());
			
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			int empOTId = employeeOvertimeService.add(employeeOvertime);
			//TODO We need to set the leaveId generated to the Leave object, before returning model object as json data
			employeeOvertime.setEmpOvertimeId(empOTId);
			String json = gson.toJson(employeeOvertime);
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
