package dai.hris.action.filemanager.payrollinfo;

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
import dai.hris.service.filemanager.educationalbackground.EducationalBackgroundService;
import dai.hris.service.filemanager.educationalbackground.IEducationalBackgroundService;

@WebServlet("/SavePayrollInfoAction")
public class SavePayrollInfoAction extends HttpServlet {

	IEducationalBackgroundService educationalBacgroundService = new EducationalBackgroundService();
	Gson gson = new Gson();
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		EducationalBackground eb = new EducationalBackground();
		
		eb.setEmpId(Integer.parseInt(request.getParameter("empId")));
		eb.setCourse(request.getParameter("course"));
		eb.setRemarks(request.getParameter("remarks"));
		eb.setSchool(request.getParameter("school"));
		eb.setYearAttended(request.getParameter("yearAttended"));
		eb.setYearGraduated(request.getParameter("yearGraduated"));

		

		try {
			educationalBacgroundService.add(eb);		
			
			String json = gson.toJson(eb);
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
