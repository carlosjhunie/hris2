package dai.hris.action.filemanager.employeepayrollinfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import dai.hris.model.EducationalBackground;
import dai.hris.model.EmployeePayrollInfo;
import dai.hris.service.filemanager.employeepayrollinfo.EmployeePayrollInfoService;
import dai.hris.service.filemanager.employeepayrollinfo.IEmployeePayrollInfoService;

/**
 * Servlet implementation class SaveEmployeePayrollInfoaction
 */
@WebServlet("/SaveEmployeePayrollInfoAction")
public class SaveEmployeePayrollInfoAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IEmployeePayrollInfoService  employeePayrollInfoService = new EmployeePayrollInfoService();
	Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveEmployeePayrollInfoAction() {
        super();
        // TODO Auto-generated constructor stub
    }

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
        EmployeePayrollInfo employeePayrollInfo = mapper.readValue(json, EmployeePayrollInfo.class);

		try {		
			
			int empPayrollInfoId = 0;
			
			if(employeePayrollInfoService.isEmployeePayrollInfoExist(employeePayrollInfo.getEmpId())){
				empPayrollInfoId = employeePayrollInfoService.update(employeePayrollInfo);
			} else {
				empPayrollInfoId = employeePayrollInfoService.add(employeePayrollInfo);		
			}
			
			 
			String json2 = gson.toJson(empPayrollInfoId);
			System.out.println("json2 is: " + json2);
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
	}

}
