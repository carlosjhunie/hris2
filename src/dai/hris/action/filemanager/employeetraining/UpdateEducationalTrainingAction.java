package dai.hris.action.filemanager.employeetraining;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;

import dai.hris.model.EmployeeOutOfOffice;
import dai.hris.service.filemanager.employeeoutofoffice.EmployeeOutOfOfficeService;
import dai.hris.service.filemanager.employeeoutofoffice.IEmployeeOutOfOfficeService;

@WebServlet("/UpdateEducationalTrainingAction")
public class UpdateEducationalTrainingAction extends HttpServlet {

	IEmployeeOutOfOfficeService employeeOutOfOfficeService = new EmployeeOutOfOfficeService();
	Gson gson = new Gson();

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost UpdateEducationalTrainingAction");
		EmployeeOutOfOffice eOOO = new EmployeeOutOfOffice();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
		  String name = (String) names.nextElement();
		  map.put(name, request.getParameterValues(name));
		}
		try {
			    
			BeanUtils.populate(eOOO, map);
	
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		try {
			int eOOOId = employeeOutOfOfficeService.update(eOOO);
			eOOO.setEmpOOOId(eOOOId);
			String json = gson.toJson(eOOO);
			String jsonData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
			System.out.println(jsonData);
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
