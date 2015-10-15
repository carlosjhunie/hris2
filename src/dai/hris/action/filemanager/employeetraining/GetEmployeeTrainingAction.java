package dai.hris.action.filemanager.employeetraining;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import dai.hris.model.EducationalBackground;
import dai.hris.model.EmployeeOutOfOffice;
import dai.hris.service.filemanager.employeeoutofoffice.EmployeeOutOfOfficeService;
import dai.hris.service.filemanager.employeeoutofoffice.IEmployeeOutOfOfficeService;


@WebServlet("/GetEmployeeTrainingAction")
public class GetEmployeeTrainingAction extends HttpServlet {

	//training is OOO
	IEmployeeOutOfOfficeService trainingOOO = new EmployeeOutOfOfficeService();
	Gson gson = new Gson();

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet GetEmployeeTrainingAction");
		doPost(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost GetEmployeeTrainingAction");
		List<EmployeeOutOfOffice> employeeOutOfOfficeList = new ArrayList<EmployeeOutOfOffice>();
		int empId = Integer.parseInt(request.getParameter("empId"));
		try {
			employeeOutOfOfficeList = trainingOOO.getEmployeeOutOfOfficeListByEmpId(empId);
		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			sqle.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    String json = gson.toJson(employeeOutOfOfficeList);
	    System.out.println("json: " + json);
 
	    json = "{\"Result\":\"OK\",\"Records\":"+ json + "}";
        response.getWriter().print(json);
        
        
	    
		
	}

	

}
