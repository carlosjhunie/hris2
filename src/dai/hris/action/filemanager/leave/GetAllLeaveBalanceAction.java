package dai.hris.action.filemanager.leave;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dai.hris.action.filemanager.util.EmployeeUtil;
import dai.hris.model.Employee;
import dai.hris.model.Leave;
import dai.hris.model.LeaveBalance;
import dai.hris.service.filemanager.leave.ILeaveService;
import dai.hris.service.filemanager.leave.LeaveService;

@WebServlet("/GetAllLeaveBalanceAction")
public class GetAllLeaveBalanceAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//int empId = Integer.parseInt(request.getParameter("empId"));
		//get the employeeLoggedIn from session instead
		List<LeaveBalance> leaveBalanceList = new ArrayList<LeaveBalance>();
		HttpSession session = request.getSession(true);  
		Employee employee = (Employee) session.getAttribute("employeeLoggedIn");		
		ILeaveService service = new LeaveService();
		double leaveCount = 0d;
		Map<String, Object> allLeavesCountMap = new TreeMap<String, Object>();
		
		 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		 
		try {
			System.out.println(employee.getEmpId());
			System.out.println(EmployeeUtil.getCurrentSystemDateSqlFormat());
			leaveCount = service.getAllEarnedLeavesCountByEmpId(employee.getEmpId(), EmployeeUtil.getCurrentSystemDateSqlFormat(), 1.25);
			Date employmentStartDate = new Date(formatter.parse(employee.getEmploymentDate()).getTime());
			System.out.println(employmentStartDate);
			//use 3 = Approved status
			allLeavesCountMap = service.getAllLeavesCountByEmpId(employee.getEmpId(), employmentStartDate, EmployeeUtil.getCurrentSystemDateSqlFormat(), 3);
			allLeavesCountMap.put("All Earned Leaves", leaveCount);
			
			
			LeaveBalance leaveBalance = null;
			for (Map.Entry<String, Object> entry : allLeavesCountMap.entrySet()) {
				leaveBalance = new LeaveBalance();
				leaveBalance.setCategory(entry.getKey());
				leaveBalance.setCount(entry.getValue());
				leaveBalanceList.add(leaveBalance);
			}
			
			
		}
		catch(Exception e ){
			e.printStackTrace();
		}	

	    String json = gson.toJson(leaveBalanceList);
 
	    json = "{\"Result\":\"OK\",\"Records\":"+ json + "}";
	    System.out.println(json);
        response.getWriter().print(json);

	}
	

}
