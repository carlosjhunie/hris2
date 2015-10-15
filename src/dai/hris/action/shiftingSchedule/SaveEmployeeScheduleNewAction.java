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

import javax.servlet.RequestDispatcher;
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
import dai.hris.model.Employee;
import dai.hris.model.EmployeeSchedule;
import dai.hris.model.TimeEntry;
import dai.hris.service.payroll.impl.CaseRatePaymentService;
import dai.hris.service.timeentry.ITimeEntryService;
import dai.hris.service.timeentry.TimeEntryService;

@WebServlet("/SaveEmployeeScheduleNewAction")
public class SaveEmployeeScheduleNewAction extends HttpServlet {
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
		
		try {
			ITimeEntryService service = new TimeEntryService();
			
			String saveMethod = request.getParameter("saveMethod");
			String empId = request.getParameter("empId");
			String empScheduleId = request.getParameter("empScheduleId");
			String scheduleDate = request.getParameter("scheduleDate");
			String shiftingScheduleId = request.getParameter("shiftingScheduleId");
			
			String supervisorId = "";
			if (null != request.getSession().getAttribute("employeeLoggedIn")) {
				supervisorId = String.valueOf(((Employee)request.getSession().getAttribute("employeeLoggedIn")).getEmpId());
			} else {
				supervisorId = request.getParameter("empIdLoggedIn");
			}
			
			if("ADD".equals(saveMethod)){
				EmployeeSchedule empSched = new EmployeeSchedule();
				empSched.setEmpId(Integer.parseInt(empId));
				empSched.setShiftingScheduleId(Integer.parseInt(shiftingScheduleId));
				empSched.setScheduleDate(scheduleDate);
				empSched.setSuperVisorId(Integer.parseInt(supervisorId));
				
				service.addEmployeeSchedule(empSched);		
			} else if("EDIT".equals(saveMethod)){
				EmployeeSchedule empSched = new EmployeeSchedule();
				//empSched.setEmpId(Integer.parseInt(empId));
				empSched.setShiftingScheduleId(Integer.parseInt(shiftingScheduleId));
				empSched.setScheduleDate(scheduleDate);
				empSched.setSuperVisorId(Integer.parseInt(supervisorId));
				empSched.setEmpScheduleId(Integer.parseInt(empScheduleId));
				
				service.editEmployeeSchedule(empSched);		
			}
			
			
			
			RequestDispatcher dispatcher = null;
			dispatcher = getServletContext().getRequestDispatcher("/employeeScheduleDetails.jsp");			    
	
			if (dispatcher != null) {
	        	response.setContentType("text/html");
	            dispatcher.include(request, response);
	        }
			
			
		} catch (SQLException e) {
			System.out.println("SaveEmployeeScheduleAction: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	
}
