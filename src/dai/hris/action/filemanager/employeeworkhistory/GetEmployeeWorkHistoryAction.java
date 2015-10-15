package dai.hris.action.filemanager.employeeworkhistory;

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
import dai.hris.model.EmployeeWorkHistory;
import dai.hris.service.filemanager.employeeworkhistory.EmployeeWorkHistoryService;
import dai.hris.service.filemanager.employeeworkhistory.IEmployeeWorkHistoryService;

/**
 * Servlet implementation class GetEmployeeWorkHistoryService
 */
@WebServlet("/GetEmployeeWorkHistoryAction")
public class GetEmployeeWorkHistoryAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    IEmployeeWorkHistoryService employeeWorkHistoryService = new EmployeeWorkHistoryService();
    Gson gson = new Gson();

    public GetEmployeeWorkHistoryAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet GetEmployeeWorkHistoryAction");
		doPost(request, response);
	}

	//
	//In the server side, we must handle these query string arguments for paging:
	//	jtStartIndex: Start index of records for current page.
	//	jtPageSize: Count of maximum expected records.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet GetEmployeeWorkHistoryAction");
		//int jtStartIndex= Integer.valueOf(request.getParameter("jtStartIndex"));
		///int jtPageSize =  Integer.valueOf(request.getParameter("jtPageSize"));
		//System.out.println("old jpagesize: " + jtPageSize);
		List<EmployeeWorkHistory> employeeWorkHistoryList = new ArrayList<EmployeeWorkHistory>();
		int empId = Integer.parseInt(request.getParameter("empId"));
		try {
			employeeWorkHistoryList = employeeWorkHistoryService.getEmployeeWorkHistoryListByEmpId(empId);
		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			sqle.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		int computedEndIndex = 0;
		int mainListSize = employeeWorkHistoryList.size();

		if (jtStartIndex + jtPageSize == mainListSize) {
			computedEndIndex = mainListSize;
		} 
		if (jtStartIndex == jtPageSize) {
			computedEndIndex = mainListSize;
		} 
		System.out.println(jtStartIndex);
		System.out.println(computedEndIndex);
		List<EmployeeWorkHistory> subL = new ArrayList<EmployeeWorkHistory>(employeeWorkHistoryList.subList(jtStartIndex, computedEndIndex));
		String json = gson.toJson(subL);
	    System.out.println("json: " + json); 
	    json = "{\"TotalRecordCount\":" + employeeWorkHistoryList.size() + ",\"Result\":\"OK\",\"Records\":"+ json + "}";
	    */
		
		String json = gson.toJson(employeeWorkHistoryList);
	    System.out.println("json: " + json);
 
	    json = "{\"Result\":\"OK\",\"Records\":"+ json + "}";
        response.getWriter().print(json);
        
        
		
	    
		
	}

}
