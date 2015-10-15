/**
 * 
 */
package dai.hris.action.timeentry;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;

import dai.hris.model.CalendarObject;
import dai.hris.model.Employee;
import dai.hris.model.EmployeeSchedule;
import dai.hris.model.TimeEntry;
import dai.hris.service.timeentry.ITimeEntryService;
import dai.hris.service.timeentry.TimeEntryService;


/**
 * @author rj
 *
 */
@WebServlet("/GetEmployeeScheduleAction")
public class GetEmployeeScheduleAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String superVisorId = "";
		if (null != request.getSession().getAttribute("employeeLoggedIn")) {
			superVisorId = String.valueOf(((Employee)request.getSession().getAttribute("employeeLoggedIn")).getEmpId());
		} else {
			superVisorId = request.getParameter("superVisorId");
		}
		
		String scheduleDate = request.getParameter("scheduleDate");
		
		ITimeEntryService service = new TimeEntryService();
		
		try {
	
			List<EmployeeSchedule> empSchedList = service.getEmployeeScheduleCalendarSpecificDate(Integer.parseInt(superVisorId), scheduleDate);
//			List<CalendarObject> list = new ArrayList<CalendarObject>();
//			Iterator<EmployeeSchedule> i = empSchedList.iterator(); 
//			
//			while(i.hasNext()){
//				EmployeeSchedule empSched = i.next();
//				CalendarObject calendarObject = new CalendarObject();
//				
//				calendarObject.setId(Integer.toString(empSched.getEmpId()));
//				calendarObject.setTitle(empSched.getEmpName() + " " + empSched.getEmpShift());
//				calendarObject.setStart(empSched.getScheduleDate());			
//				//calendarObject.setUrl("javascript:addDeleteSchedule(" + empSched.getShiftingScheduleId() + ", " + empSched.getEmpId() + ");");
//				
//				if(empSched.getShiftingScheduleId() == 2000){
//					calendarObject.setColor("#9900FF");
//	        	} else if(empSched.getShiftingScheduleId() == 2001){
//	        		calendarObject.setColor("#666699");
//	        	} else {
//	        		if(empSched.getUpdatedBy() > 0){
//						calendarObject.setColor("blue");
//					} else {
//						calendarObject.setColor("green");
//					}
//	        	}
//				
//				
//				list.add(calendarObject);
//				
//			}		
			
			
//			String json = gson.toJson(list);
//		    System.out.println("json: " + json);
			
			String json = gson.toJson(empSchedList);
		    System.out.println("json: " + json);
	 
		    json = "{\"Result\":\"OK\",\"Records\":"+ json + "}";
	        response.getWriter().print(json);
	 
		    
	        
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		
	}

}
