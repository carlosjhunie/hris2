package dai.hris.action.shiftingSchedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import dai.hris.model.CalendarObject;
import dai.hris.model.CaseRatePayment;
import dai.hris.model.EmployeeSchedule;
import dai.hris.model.TimeEntry;
import dai.hris.service.payroll.impl.CaseRatePaymentService;
import dai.hris.service.timeentry.ITimeEntryService;
import dai.hris.service.timeentry.TimeEntryService;

@WebServlet("/SaveEmployeeScheduleAction")
public class SaveEmployeeScheduleAction extends HttpServlet {
	private static final long serialVersionUID = 5398739944589430743L;

	private CaseRatePaymentService service = new CaseRatePaymentService();
	Gson gson = new Gson();

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
		
		ITimeEntryService service = new TimeEntryService();
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json_param = "";
        if(br != null){
            json_param = br.readLine();
        }
       
        System.out.println("json_param: " + json_param);
        
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> param = mapper.readValue(json_param, Map.class);
        ArrayList<String> empList = null;
        String empId = null;
        if(param.get("empId") instanceof String){
        	empList = new ArrayList<String>();
        	empId = (String)param.get("empId");
        	empList.add(empId);
        } else {
        	empList = (ArrayList<String>)param.get("empId");
        }
        
        
		
		try {
			int shiftingScheduleId = Integer.parseInt(param.get("shiftingScheduleId").toString());
			int supervisorId = Integer.parseInt(param.get("empIdLoggedIn").toString());
			String scheduleDate = param.get("scheduleDate").toString();
			Iterator<String> empIterator = empList.iterator();
			
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			while(empIterator.hasNext()){
				String empIdToSave = empIterator.next();
				EmployeeSchedule empSched = new EmployeeSchedule();
				empSched.setEmpId(Integer.parseInt(empIdToSave));
				empSched.setShiftingScheduleId(shiftingScheduleId);
				empSched.setScheduleDate(scheduleDate);
				empSched.setSuperVisorId(supervisorId);
				
				service.saveEmployeeSchedule(empSched);				
			}
			
			List<EmployeeSchedule> empSchedList = service.getEmployeeScheduleForTheMonth(supervisorId);
			List<CalendarObject> list = new ArrayList<CalendarObject>();
			Iterator<EmployeeSchedule> i = empSchedList.iterator(); 
			
			while(i.hasNext()){
				EmployeeSchedule empSched = i.next();
				CalendarObject calendarObject = new CalendarObject();
				
				calendarObject.setId(Integer.toString(empSched.getEmpId()));
				calendarObject.setTitle(empSched.getEmpName() + empSched.getEmpShift());
				calendarObject.setStart(empSched.getScheduleDate());			
				//calendarObject.setUrl("clockEntry.jsp" + (StringUtils.isEmpty(timeEntry.getTimeIn()) ? "" : "?clockDate=" + timeEntry.getTimeIn().substring(0, 10)));
				
				if(empSched.getUpdatedBy() > 0){
					calendarObject.setColor("blue");
				} else {
					calendarObject.setColor("green");
				}
				
				
				
				list.add(calendarObject);
				
			}		
			
			
			String json = gson.toJson(list);
		    System.out.println("CalendarObject json: " + json);
	 
		    
	        response.getWriter().print(json);
			
			
		} catch (SQLException e) {
			System.out.println("SaveEmployeeScheduleAction: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	
}
