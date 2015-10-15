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

@WebServlet("/UpdatePayrollInfoAction")
public class UpdatePayrollInfoAction extends HttpServlet {

	//IEducationalBackgroundService educationalBacgroundService = new EducationalBackgroundService();
	//Gson gson = new Gson();

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
/*
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
		System.out.println("here is the json output: ");
		System.out.println("=========================");
		System.out.println(json);
		System.out.println("=========================");
 
        // 2. initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();
 
        // 3. Convert received JSON to Article
        EducationalBackground educationalBackground = mapper.readValue(json, EducationalBackground.class);

		try {
			int status = educationalBacgroundService.update(educationalBackground);		
			String json2 = gson.toJson(status);
			//System.out.println("json: " + json);
			//String jsonData = "{\"Result\":\"OK\",\"Record\":" + gson.toJson(empId) + "}";
			response.getWriter().print(json2);
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
		*/
	}

	

}
