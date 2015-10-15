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
import dai.hris.model.TimeEntry;
import dai.hris.service.timeentry.ITimeEntryService;
import dai.hris.service.timeentry.TimeEntryService;


/**
 * @author rj
 *
 */
@WebServlet("/GetTimeEntryPerSupervisorAction")
public class GetTimeEntryPerSupervisorAction extends HttpServlet {
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
		
		String clockDate = request.getParameter("clockDate");
		
		ITimeEntryService service = new TimeEntryService();
		
		try {
	
			List<TimeEntry> timeEntryList = service.getAllTimeEntryBySuperVisor(Integer.parseInt(superVisorId));
			List<CalendarObject> list = new ArrayList<CalendarObject>();
			Iterator<TimeEntry> i = timeEntryList.iterator(); 
			
			while(i.hasNext()){
				TimeEntry timeEntry = i.next();
				CalendarObject calendarObject = new CalendarObject();
				
				calendarObject.setId(Integer.toString(timeEntry.getEmpId()));
				calendarObject.setTitle(timeEntry.getEmpName());
				calendarObject.setStart(StringUtils.isEmpty(timeEntry.getTimeIn())? "" : timeEntry.getTimeIn().substring(0, 10));			
				calendarObject.setUrl("GetTimeEntryByDateAndSuperVisorAction" + (StringUtils.isEmpty(timeEntry.getTimeIn()) ? "" : "?clockDate=" + timeEntry.getTimeIn().substring(0, 10)));
				
				if(StringUtils.isEmpty(timeEntry.getTimeIn()) || StringUtils.isEmpty(timeEntry.getTimeOut())){
					calendarObject.setColor("red");
					calendarObject.setTextColor("white");
				} else {
					calendarObject.setColor("green");
					calendarObject.setTextColor("white");
				}
				
				list.add(calendarObject);
				
			}		
			
			
			String json = gson.toJson(list);
		    System.out.println("json: " + json);
	 
		    
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
