package dai.hris.action.shiftingSchedule;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;







import dai.hris.model.Employee;
import dai.hris.model.EmployeeSchedule;
import dai.hris.model.ShiftingSchedule;
import dai.hris.service.timeentry.ITimeEntryService;
import dai.hris.service.timeentry.TimeEntryService;
import dai.hris.services.shiftingSchedule.IShiftingScheduleService;
import dai.hris.services.shiftingSchedule.ShiftingScheduleService;

@WebServlet("/CheckEmployeeScheduleAction")
public class CheckEmployeeScheduleAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	
		EmployeeSchedule empSched = new EmployeeSchedule();


		ITimeEntryService service = new TimeEntryService();
		
		String scheduleDate = request.getParameter("scheduleDate");
		
		String returnValue = "";
		
		String supervisorId = "";
		if (null != request.getSession().getAttribute("employeeLoggedIn")) {
			supervisorId = String.valueOf(((Employee)request.getSession().getAttribute("employeeLoggedIn")).getEmpId());
		} else {
			supervisorId = request.getParameter("empIdLoggedIn");
		}

		try {
			empSched.setScheduleDate(scheduleDate);
			empSched.setSuperVisorId(Integer.parseInt(supervisorId));
			
			if(service.checkIfCalendarHasSchedule(empSched)){
				returnValue = "YES";
			} else {
				returnValue = "NO";
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}

		String json = gson.toJson(returnValue);
		System.out.println("json: " + json);
		String jsonData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
		response.getWriter().print(jsonData);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}

}
