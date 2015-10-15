package dai.hris.action.filemanager.employeetraining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import dai.hris.model.EducationalBackground;
import dai.hris.model.Employee;
import dai.hris.model.EmployeeOutOfOffice;
import dai.hris.service.filemanager.educationalbackground.EducationalBackgroundService;
import dai.hris.service.filemanager.educationalbackground.IEducationalBackgroundService;
import dai.hris.service.filemanager.employeeoutofoffice.EmployeeOutOfOfficeService;
import dai.hris.service.filemanager.employeeoutofoffice.IEmployeeOutOfOfficeService;

@WebServlet("/SaveEmployeeTrainingAction")
public class SaveEmployeeTrainingAction extends HttpServlet {

	IEmployeeOutOfOfficeService employeeOutOfOfficeService = new EmployeeOutOfOfficeService();
	Gson gson = new Gson();
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		EmployeeOutOfOffice eooo = new EmployeeOutOfOffice();
		
		eooo.setEmpId(Integer.parseInt(request.getParameter("empId")));
		eooo.setDateFrom(request.getParameter("dateFrom"));
		eooo.setDateTo(request.getParameter("dateTo"));
		eooo.setProvider(request.getParameter("provider"));
		eooo.setRemarks(request.getParameter("remarks"));
		eooo.setTitleActivity(request.getParameter("titleActivity"));

		

		try {
			employeeOutOfOfficeService.add(eooo);		
			
			String json = gson.toJson(eooo);
			System.out.println("json: " + json);
			String jsonData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
			response.getWriter().print(jsonData);		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}

	

}
