package dai.hris.action.timeentry;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dai.hris.model.Resolution;
import dai.hris.service.login.ILoginService;
import dai.hris.service.login.LoginService;
import dai.hris.service.timeentry.ITimeEntryService;
import dai.hris.service.timeentry.TimeEntryService;




/**
 * Servlet implementation class for Servlet: LoginAction
 *
 */
@WebServlet("/TimeEntryResolutionAction")
 public class TimeEntryResolutionAction extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	 private static final long serialVersionUID = -6185891323760506163L;	 
	 /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public TimeEntryResolutionAction() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doPost(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);   				
		
		//String resolutionCategory = request.getParameter("resolutionCategory");
		String clockDate = request.getParameter("clockDate");		
		String empId = request.getParameter("empId");		
		//String resolutionType = request.getParameter("resolutionType");	
		String shiftScheduleId = request.getParameter("shiftScheduleId");
		//String timeEntryAssigned = request.getParameter("timeEntryAssigned");	
		String timeInHrs = request.getParameter("timeInHrs");
		String timeOutHrs = request.getParameter("timeOutHrs");
		String resolutionRemarks = request.getParameter("resolutionRemarks");	
		String resolvedBy = request.getParameter("resolvedBy");	
		String timeEntryId = request.getParameter("timeEntryId");	
		
		
			
        ITimeEntryService timeEntryService = new TimeEntryService();
        
        Resolution resolution = new Resolution();
        if(!timeEntryId.isEmpty() && timeEntryId.length() > 0){
        	resolution.setTimeEntryId(Integer.parseInt(timeEntryId));
        } else {
        	resolution.setTimeEntryId(0);
        }
        
        resolution.setClockDate(clockDate);
        
        if(!empId.isEmpty() && empId.length() > 0){
        	resolution.setEmpId(Integer.parseInt(empId));
        }
        
        //resolution.setResolutionCategory(resolutionCategory);
        resolution.setResolutionRemarks(resolutionRemarks);
        //resolution.setResolutionType(resolutionType);
        
        resolution.setTimeInHrs(timeInHrs);
        resolution.setTimeOutHrs(timeOutHrs);
        
        if(!resolvedBy.isEmpty() && resolvedBy.length() > 0){
        	resolution.setResolvedBy(Integer.parseInt(resolvedBy));
        }
        
        if(!shiftScheduleId.isEmpty() && shiftScheduleId.length() > 0){
        	resolution.setShiftScheduleId(Integer.parseInt(shiftScheduleId));
        }
        
        //resolution.setTimeEntryAssigned(timeEntryAssigned);
        
        
        
		try {
			
			timeEntryService.disputeTimeEntryBySupervisor(resolution);
			
//			if (resolutionType.equals("S")){
//				timeEntryService.resolveTimeEntryUsingScheduledTime(resolution);
//			} else if (resolutionType.equals("L")){
//				timeEntryService.resolveTimeEntryUsingAssignedTime(resolution);
//			}			
        	
			RequestDispatcher dispatcher = null;
			dispatcher = getServletContext().getRequestDispatcher("/GetTimeEntryByDateAndSuperVisorAction?clockDate="+clockDate);			    

			if (dispatcher != null) {
	        	response.setContentType("text/html");
	            dispatcher.include(request, response);
	        }
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}   	  	    
}