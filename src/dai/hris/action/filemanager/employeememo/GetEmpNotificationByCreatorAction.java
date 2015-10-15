package dai.hris.action.filemanager.employeememo;

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
import dai.hris.model.EmployeeNotification;
import dai.hris.service.filemanager.empnotification.EmpNotificationService;
import dai.hris.service.filemanager.empnotification.IEmpNotificationService;

/**
 * Servlet implementation class GetEmployeeMemoAction
 */

//get all of the employee's issued/created memos (employee is giver)
@WebServlet("/GetEmpNotificationByCreatorAction")
public class GetEmpNotificationByCreatorAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IEmpNotificationService empNotificationService = new EmpNotificationService();
	Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEmpNotificationByCreatorAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet GetEmpNotificationByCreatorAction");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost GetEmpNotificationByCreatorAction");
		List<EmployeeNotification> employeeNotificationList = new ArrayList<EmployeeNotification>();
		int empId = Integer.parseInt(request.getParameter("empId"));
		try {
			employeeNotificationList = empNotificationService.getEmployeeNotificationListByCreatedByEmpId(empId);
		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			sqle.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/json");
		String jsonObject = gson.toJson(employeeNotificationList);
		out.print(jsonObject);
		out.close();
	}

}
