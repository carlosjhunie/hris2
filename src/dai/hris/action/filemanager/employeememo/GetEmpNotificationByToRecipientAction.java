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

//get all of the employee's received memos (employee is the recipient)
@WebServlet("/GetEmpNotificationByToRecipientAction")
public class GetEmpNotificationByToRecipientAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IEmpNotificationService empNotificationService = new EmpNotificationService();
	Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEmpNotificationByToRecipientAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet GetEmpNotificationByToRecipientAction");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost GetEmpNotificationByToRecipientAction");
		List<EmployeeNotification> empNotificationList = new ArrayList<EmployeeNotification>();
		int toRecipientEmpId = Integer.parseInt(request.getParameter("toRecipientEmpId"));
		try {
			empNotificationList = empNotificationService.getEmployeeNotificationListByToRecipientEmpId(toRecipientEmpId);
		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			sqle.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    String json = gson.toJson(empNotificationList);
	    System.out.println("json: " + json);
 
	    json = "{\"Result\":\"OK\",\"Records\":"+ json + "}";
        response.getWriter().print(json);
        
       
		
	}

}
