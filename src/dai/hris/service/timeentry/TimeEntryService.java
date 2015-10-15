package dai.hris.service.timeentry;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import dai.hris.action.filemanager.util.EmployeeUtil;
import dai.hris.dao.filemanager.EmployeeDAO;
import dai.hris.dao.filemanager.EmployeeTimeEntryDAO;
import dai.hris.dao.timeentry.TimeEntryDAO;
import dai.hris.model.CheckInCheckOut;
import dai.hris.model.DisplayKioskTimeEntry;
import dai.hris.model.Employee;
import dai.hris.model.EmployeeSchedule;
import dai.hris.model.EmployeeTimeEntry;
import dai.hris.model.Resolution;
import dai.hris.model.ShiftingSchedule;
import dai.hris.model.TimeEntry;
import dai.hris.model.TimeEntryDispute;
import dao.hris.dao.biometric.CheckInCheckOutDAO;


public class TimeEntryService implements ITimeEntryService {
	static Logger logger = Logger.getLogger(TimeEntryService.class.getClass());
	
	public TimeEntryService(){
		
	}		
	
	public List<Employee> getEmployeeListPerSuperVisor(int supervisorId) throws SQLException, Exception{
		EmployeeDAO dao = new EmployeeDAO();
		
		List<Employee> empList = dao.getEmployeeListBySupervisorId(supervisorId);
		
		dao.closeConnection();
		
		return empList;
	}
	
//	public void resolveTimeEntryUsingScheduledTime(Resolution resolution) throws SQLException, Exception {
//		TimeEntryDAO dao = new TimeEntryDAO();
//		
//		dao.resolveTimeEntryUsingScheduledTime(resolution);
//		
//		dao.closeConnection();
//	}
	
	public void resolveTimeEntryUsingAssignedTime(Resolution resolution) throws SQLException, Exception {
		TimeEntryDAO dao = new TimeEntryDAO();
		
		dao.resolveTimeEntryUsingAssignedTime(resolution);
		
		dao.closeConnection();
	}
	
	public void disputeTimeEntry(Resolution resolution) throws SQLException, Exception {
		TimeEntryDAO dao = new TimeEntryDAO();
		
		dao.disputeTimeEntry(resolution);
		
		dao.closeConnection();
	}
	
	public void disputeTimeEntryBySupervisor(Resolution resolution) throws SQLException, Exception {
		TimeEntryDAO dao = new TimeEntryDAO();
		
		dao.disputeTimeEntryBySupervisor(resolution);
		
		dao.closeConnection();
	}
	
	public void updateTimeEntryDispute(String newStatus, int timeEntryDisputeId) throws SQLException, Exception {
		TimeEntryDAO dao = new TimeEntryDAO();
		
		dao.updateTimeEntryDispute(newStatus, timeEntryDisputeId);
		
		dao.closeConnection();
	}
	
	public void saveEmployeeSchedule(EmployeeSchedule empSched) throws SQLException, Exception {
		TimeEntryDAO dao = new TimeEntryDAO();
		
		if(dao.checkIfEmployeeHasSchedule(empSched)){
			dao.updateEmployeeSchedule(empSched);
		} else {
			dao.insertEmployeeSchedule(empSched);
		}
		
		dao.closeConnection();
		
	}
	
	//TODO
	public void addEmployeeSchedule(EmployeeSchedule empSched) throws SQLException, Exception {
		TimeEntryDAO dao = new TimeEntryDAO();
		
		dao.insertEmployeeSchedule(empSched);
		
		dao.closeConnection();
	}
	
	//TODO
	public void editEmployeeSchedule(EmployeeSchedule empSched) throws SQLException, Exception {
		TimeEntryDAO dao = new TimeEntryDAO();
		
		dao.editEmployeeSchedule(empSched);
		
		dao.closeConnection();
	}
	
	public List<EmployeeSchedule> getEmployeeScheduleForTheMonth(int supervisorId) throws SQLException, Exception {
		TimeEntryDAO dao = new TimeEntryDAO();
		List<EmployeeSchedule> empSchedList  = dao.getEmployeeScheduleForTheMonth(supervisorId);
		dao.closeConnection();
		
		return empSchedList;
	}
	
	public List<EmployeeSchedule> getEmployeeScheduleCalendar(int supervisorId) throws SQLException, Exception {
		TimeEntryDAO dao = new TimeEntryDAO();
		List<EmployeeSchedule> empSchedList  = dao.getEmployeeScheduleCalendar(supervisorId);
		dao.closeConnection();
		
		return empSchedList;
	}
	
	public List<EmployeeSchedule> getEmployeeScheduleCalendarSpecificDate(int supervisorId, String scheduleDate) throws SQLException, Exception {
		TimeEntryDAO dao = new TimeEntryDAO();
		List<EmployeeSchedule> empSchedList  = dao.getEmployeeScheduleCalendarSpecificDate(supervisorId, scheduleDate);
		dao.closeConnection();
		
		return empSchedList;
	}
	
	private CheckInCheckOut getLatestTimeEntryBiometric(String sn) throws SQLException, Exception{
		
		CheckInCheckOutDAO checkInCheckOutDAO = new CheckInCheckOutDAO();
		CheckInCheckOut checkInCheckOut = checkInCheckOutDAO.getCheckInCheckOutLatestBySN(sn);
		return checkInCheckOut;
	
	} 
	
	public DisplayKioskTimeEntry displayKioskTimeEntry(String sn, DisplayKioskTimeEntry displayKioskTimeEntry) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
		// grace period for timein and timeout in HOURS
		int gracePeriod = 2;
		
		CheckInCheckOutDAO checkInCheckOutDAO = new CheckInCheckOutDAO();
		
		//get timeentry in biometric
		CheckInCheckOut checkInCheckOut = checkInCheckOutDAO.getCheckInCheckOutLatestBySN(sn);
		String empNo =  checkInCheckOut.getBADGENUMBER();
		if(empNo == null ) {
			throw new Exception("NOLATESTBIOENTRY");
		}
		//set readflag of biometric to Y
		checkInCheckOutDAO.updateReadFlagToY(checkInCheckOut.getCheckInCheckOutID());
		checkInCheckOutDAO.closeConnection();

		
		//get empId from employee
		EmployeeDAO employeeDAO = new EmployeeDAO();
		Employee employee = employeeDAO.getEmployee(empNo);
		
		
		if(employee == null) {
			throw new Exception("NOMATCHEMP");
		}
		
		displayKioskTimeEntry.setFirstname(employee.getFirstname());
		displayKioskTimeEntry.setLastname(employee.getLastname());
		displayKioskTimeEntry.setEmpNo(empNo);
		displayKioskTimeEntry.setPicLoc(employee.getPicLoc());
		
		int empId = employee.getEmpId();
		
		
		//get shift timein and timeout
		SimpleDateFormat sdf_yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf_yyyy_MM_dd_HH_mm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		
		TimeEntryDAO timeEntryDAO = new TimeEntryDAO();
		ShiftingSchedule shiftingSchedule = timeEntryDAO.getShiftingScheduleByEmpIdAndShiftTimeIn(empId, checkInCheckOut.getCHECKTIME());
		if(shiftingSchedule == null) {
			
			// no shift for today;  try to get the shift of yesterday instead
			shiftingSchedule = getShiftScheduleYesterday(checkInCheckOut,
					empId, timeEntryDAO);

			if(shiftingSchedule  == null) {
				throw new Exception("NOSHIFT");
			}
		}
		
		Calendar shiftTimeOutCalendar = Calendar.getInstance();
		Calendar shiftTimeInCalendar = Calendar.getInstance();
		
		formatShiftTimeInAndShiftTimeOut(gracePeriod, sdf_yyyy_MM_dd,
				sdf_yyyy_MM_dd_HH_mm, shiftingSchedule, shiftTimeOutCalendar,
				shiftTimeInCalendar);
		
		//compare with shifting for timeentry in BIOMETRICS
		Date biometricsCheckinChekout= checkInCheckOut.getCHECKTIME();
		TimeEntry timeEntry = null;
		
		//check if there is in shift; if not used the shift date of yesterday
		if (biometricsCheckinChekout.before(shiftTimeOutCalendar.getTime()) && biometricsCheckinChekout.after(shiftTimeInCalendar.getTime())) {
			//do no thing
		}
		else {
			// use the shift of yesterday
			shiftingSchedule = getShiftScheduleYesterday(checkInCheckOut,
					empId, timeEntryDAO);
			if(shiftingSchedule  == null) {
				throw new Exception("NOTINSHIFT");
			}
			formatShiftTimeInAndShiftTimeOut(gracePeriod, sdf_yyyy_MM_dd,
					sdf_yyyy_MM_dd_HH_mm, shiftingSchedule, shiftTimeOutCalendar,
					shiftTimeInCalendar);
			

			
		}
		
		//check if biometrics is in shift 
		if(biometricsCheckinChekout.before(shiftTimeOutCalendar.getTime()) && biometricsCheckinChekout.after(shiftTimeInCalendar.getTime())) {
		
			//check if it has time in
			//timeEntry = timeEntryDAO.getTimeEntryByShiftTimeInDateAndEmpId(empId, shiftTimeInCalendar.getTime());
			String shiftTimeInStr =  sdf_yyyy_MM_dd_HH_mm.format(shiftTimeInCalendar.getTime());
			String shiftTimeOutStr =  sdf_yyyy_MM_dd_HH_mm.format(shiftTimeOutCalendar.getTime());
			
			
			timeEntry = timeEntryDAO.getTimeEntryByShiftTimeInDateAndEmpId(empId, shiftTimeInStr, shiftTimeOutStr);
			
			timeEntry.setEmpId(empId);
			timeEntry.setShiftId(shiftingSchedule.getShiftingScheduleId());
			timeEntry.setDeviceNo(checkInCheckOut.getSn());
			timeEntry.setVerifyCode(checkInCheckOut.getVerifyCode());

			if(timeEntry.getTimeInTS() == null) {
				// insert as timeIN
				timeEntry.setTimeInTS(checkInCheckOut.getCHECKTIME());				
				timeEntryDAO.insertTimeIn(timeEntry);
			}
			else{				
				//update as timeOut
				timeEntry.setTimeOutTS(checkInCheckOut.getCHECKTIME());			
				timeEntryDAO.updateTimeOut(timeEntry);
			}
			
		}
		else {
			//no longer in shift +- grace period
			timeEntryDAO.closeConnection();
			throw new Exception("NOTINSHIFT");
		}
					
		timeEntryDAO.closeConnection();	
		if(timeEntry != null ) {
			if(timeEntry.getTimeInTS() != null) {
				displayKioskTimeEntry.setTimeIn(sdf_yyyy_MM_dd_HH_mm.format(timeEntry.getTimeInTS()));
			}
			if(timeEntry.getTimeOutTS() != null) {
				displayKioskTimeEntry.setTimeOut(sdf_yyyy_MM_dd_HH_mm.format(timeEntry.getTimeOutTS()));
			}
			
		}
		                                  
		return displayKioskTimeEntry;
	}

	private ShiftingSchedule getShiftScheduleYesterday(
			CheckInCheckOut checkInCheckOut, int empId,
			TimeEntryDAO timeEntryDAO) throws SQLException, Exception {
		ShiftingSchedule shiftingSchedule;
		Calendar checkinCheckoutCalendar = Calendar.getInstance();
		checkinCheckoutCalendar.setTime(checkInCheckOut.getCHECKTIME());
		checkinCheckoutCalendar.add(Calendar.DAY_OF_MONTH, -1);
		shiftingSchedule = timeEntryDAO.getShiftingScheduleByEmpIdAndShiftTimeIn(empId,checkinCheckoutCalendar.getTime());
		return shiftingSchedule;
	}

	private void formatShiftTimeInAndShiftTimeOut(int gracePeriod,
			SimpleDateFormat sdf_yyyy_MM_dd,
			SimpleDateFormat sdf_yyyy_MM_dd_HH_mm,
			ShiftingSchedule shiftingSchedule, Calendar shiftTimeOutCalendar,
			Calendar shiftTimeInCalendar) throws ParseException {
		String shiftTimeInStr = shiftingSchedule.getTimeIn();
		String shiftTimeOutStr = shiftingSchedule.getTimeOut();
		
		shiftTimeInStr = sdf_yyyy_MM_dd.format(shiftingSchedule.getScheduleDate()) + " " + shiftTimeInStr;
		shiftTimeOutStr = sdf_yyyy_MM_dd.format(shiftingSchedule.getScheduleDate()) + " " + shiftTimeOutStr;
		
		Date shiftTimeInDate = sdf_yyyy_MM_dd_HH_mm.parse(shiftTimeInStr);
		
		shiftTimeInCalendar.setTime(shiftTimeInDate);

		Date shiftTimeOutDate = sdf_yyyy_MM_dd_HH_mm.parse(shiftTimeOutStr);
		
		shiftTimeOutCalendar.setTime(shiftTimeOutDate);

		shiftTimeInCalendar.add(Calendar.HOUR, -gracePeriod);
		shiftTimeOutCalendar.add(Calendar.HOUR, gracePeriod);
		
		
		
		//check if shift is overlapping to another day
		if(shiftTimeOutCalendar.before(shiftTimeInCalendar)) {
			//add one day
			shiftTimeOutCalendar.add(Calendar.DATE, 1);	
		}
	}	
	
	public  List<TimeEntry> getAllTimeEntryBySuperVisor(int superVisorId) throws SQLException, Exception {
		TimeEntryDAO dao = new TimeEntryDAO();
		List<TimeEntry> listFinal  = new ArrayList<TimeEntry>();
		
		List<Employee> empList = dao.getEmployeeListBySupervisorId(superVisorId);
		Map<Integer, List<TimeEntry>> timeEntryMap = dao.getAllTimeEntryBySuperVisorMap(superVisorId);
		Map<Integer, List<EmployeeSchedule>> employeeScheduleMap = dao.getEmployeeScheduleForTheMonthMap(superVisorId);
		
		
		Iterator<Employee> empIterator = empList.iterator();
		
		while(empIterator.hasNext()){
			Employee employee = empIterator.next();
			
			List<TimeEntry> timeEntryList = null;
			List<EmployeeSchedule> empSchedList = null;		
			
			
			if(employeeScheduleMap.containsKey(employee.getEmpId())){
				empSchedList = employeeScheduleMap.get(employee.getEmpId());
				
				Iterator<EmployeeSchedule> empSchedIterator = empSchedList.iterator();
								while(empSchedIterator.hasNext()){
					EmployeeSchedule empSchedule = empSchedIterator.next();
					
					if(timeEntryMap.containsKey(employee.getEmpId())){
						timeEntryList = timeEntryMap.get(employee.getEmpId());
						
						Iterator<TimeEntry> timeEntryIterator = timeEntryList.iterator();
						
						boolean noEntryFound = true;
						
						Map<String, TimeEntry> map2 = new HashMap<String, TimeEntry>();
						
						while(timeEntryIterator.hasNext()){
							TimeEntry timeEntry = timeEntryIterator.next();
							
							map2.put(StringUtils.isEmpty(timeEntry.getTimeIn()) ? "" : timeEntry.getTimeIn().substring(0, 10), timeEntry);
							
							if(empSchedule.getScheduleDate().equals(StringUtils.isEmpty(timeEntry.getTimeIn()) ? "" : timeEntry.getTimeIn().substring(0, 10))  && empSchedule.getShiftingScheduleId() == timeEntry.getShiftScheduleId()){
								timeEntry.setScheduleDate(timeEntry.getTimeIn().substring(0, 10));
								timeEntry.setShiftScheduleId(empSchedule.getShiftingScheduleId());
								listFinal.add(timeEntry);
								noEntryFound = false;
								break;
							}
						}
						
						if(noEntryFound){
							TimeEntry timeEntry = new TimeEntry();
							timeEntry.setEmpId(employee.getEmpId());
					    	timeEntry.setEmpName(employee.getLastname() + ", " + employee.getFirstname());
					    	timeEntry.setShiftScheduleDesc(empSchedule.getEmpShift());
					    	timeEntry.setScheduleDate(empSchedule.getScheduleDate());
					    	timeEntry.setShiftScheduleId(empSchedule.getShiftingScheduleId());
					    	//timeEntry.setShiftType(map2.get(empSchedule.getScheduleDate()).getShiftType());	    
					    	//timeEntry.setTimeIn(rs.getString("timeIn"));
					    	//timeEntry.setTimeOut(rs.getString("timeOut"));
							
							listFinal.add(timeEntry);
						}
						
						
						
					} else {
						TimeEntry timeEntry = new TimeEntry();
						timeEntry.setEmpId(employee.getEmpId());
				    	timeEntry.setEmpName(employee.getLastname() + ", " + employee.getFirstname());
				    	timeEntry.setShiftScheduleDesc(empSchedule.getEmpShift());
				    	timeEntry.setScheduleDate(empSchedule.getScheduleDate());
				    	timeEntry.setShiftScheduleId(empSchedule.getShiftingScheduleId());
				    	//timeEntry.setShiftType(map2.get(empSchedule.getScheduleDate()).getShiftType());	    
				    	//timeEntry.setTimeIn(rs.getString("timeIn"));
				    	//timeEntry.setTimeOut(rs.getString("timeOut"));
						
						listFinal.add(timeEntry);
					}				
					
				}
			}
		}
				
		
		
		dao.closeConnection();
		
		return listFinal;		
	}
	
	public  List<TimeEntry> getAllTimeEntryByEmpId(int empId) throws SQLException, Exception {
		TimeEntryDAO dao = new TimeEntryDAO();
		EmployeeDAO empDAO = new EmployeeDAO();
		List<TimeEntry> listFinal  = new ArrayList<TimeEntry>();
		
		Employee employee = empDAO.getEmployee(empId);
		Map<Integer, List<TimeEntry>> timeEntryMap = dao.getAllTimeEntryByEmpIdMap(empId);
		Map<Integer, List<EmployeeSchedule>> employeeScheduleMap = dao.getEmployeeScheduleForTheMonthByEmpIdMap(empId);
		
		
		
			
			
			List<TimeEntry> timeEntryList = null;
			List<EmployeeSchedule> empSchedList = null;		
			
			
			if(employeeScheduleMap.containsKey(employee.getEmpId())){
				empSchedList = employeeScheduleMap.get(employee.getEmpId());
				
				Iterator<EmployeeSchedule> empSchedIterator = empSchedList.iterator();
								while(empSchedIterator.hasNext()){
					EmployeeSchedule empSchedule = empSchedIterator.next();
					
					if(timeEntryMap.containsKey(employee.getEmpId())){
						timeEntryList = timeEntryMap.get(employee.getEmpId());
						
						Iterator<TimeEntry> timeEntryIterator = timeEntryList.iterator();
						
						boolean noEntryFound = true;
						
						Map<String, TimeEntry> map2 = new HashMap<String, TimeEntry>();
						
						while(timeEntryIterator.hasNext()){
							TimeEntry timeEntry = timeEntryIterator.next();
							
							map2.put(StringUtils.isEmpty(timeEntry.getTimeIn()) ? "" : timeEntry.getTimeIn().substring(0, 10), timeEntry);
							
							if(empSchedule.getScheduleDate().equals(StringUtils.isEmpty(timeEntry.getTimeIn()) ? "" : timeEntry.getTimeIn().substring(0, 10))  && empSchedule.getShiftingScheduleId() == timeEntry.getShiftScheduleId()){
								timeEntry.setScheduleDate(timeEntry.getTimeIn().substring(0, 10));
								timeEntry.setShiftScheduleId(empSchedule.getShiftingScheduleId());
								listFinal.add(timeEntry);
								noEntryFound = false;
								break;
							}
						}
						
						if(noEntryFound){
							TimeEntry timeEntry = new TimeEntry();
							timeEntry.setEmpId(employee.getEmpId());
					    	timeEntry.setEmpName(employee.getLastname() + ", " + employee.getFirstname());
					    	timeEntry.setShiftScheduleDesc(empSchedule.getEmpShift());
					    	timeEntry.setScheduleDate(empSchedule.getScheduleDate());
					    	timeEntry.setShiftScheduleId(empSchedule.getShiftingScheduleId());
					    	//timeEntry.setShiftType(map2.get(empSchedule.getScheduleDate()).getShiftType());	    
					    	//timeEntry.setTimeIn(rs.getString("timeIn"));
					    	//timeEntry.setTimeOut(rs.getString("timeOut"));
							
							listFinal.add(timeEntry);
						}
						
						
						
					} else {
						TimeEntry timeEntry = new TimeEntry();
						timeEntry.setEmpId(employee.getEmpId());
				    	timeEntry.setEmpName(employee.getLastname() + ", " + employee.getFirstname());
				    	timeEntry.setShiftScheduleDesc(empSchedule.getEmpShift());
				    	timeEntry.setScheduleDate(empSchedule.getScheduleDate());
				    	timeEntry.setShiftScheduleId(empSchedule.getShiftingScheduleId());
				    	//timeEntry.setShiftType(map2.get(empSchedule.getScheduleDate()).getShiftType());	    
				    	//timeEntry.setTimeIn(rs.getString("timeIn"));
				    	//timeEntry.setTimeOut(rs.getString("timeOut"));
						
						listFinal.add(timeEntry);
					}				
					
				}
			}
		
				
		
		empDAO.closeConnection();
		dao.closeConnection();
		
		return listFinal;		
	}
	
	
	
	public  List<TimeEntryDispute> getAllTimeEntryDisputeByEmpId(int empId) throws SQLException, Exception {
		TimeEntryDAO dao = new TimeEntryDAO();
		
		List<TimeEntryDispute> listFinal  = dao.getAllTimeEntryDisputeByEmpId(empId);		
		
		
		dao.closeConnection();
		
		return listFinal;
	}
	
	public  List<TimeEntryDispute> getAllTimeEntryDisputeBySupervisorId(int supervisorId) throws SQLException, Exception {
		TimeEntryDAO dao = new TimeEntryDAO();
		
		List<TimeEntryDispute> listFinal  = dao.getAllTimeEntryDisputeBySupervisorId(supervisorId);		
		
		
		dao.closeConnection();
		
		return listFinal;
	}
	
	public  List<TimeEntryDispute> getAllTimeEntryDisputeHR() throws SQLException, Exception {
		TimeEntryDAO dao = new TimeEntryDAO();
		
		List<TimeEntryDispute> listFinal  = dao.getAllTimeEntryDisputeHR();		
		
		
		dao.closeConnection();
		
		return listFinal;
	}
	
	public  List<TimeEntryDispute> getAllTimeEntryDisputeBySupervisorIdAndClockDate(int supervisorId, String clockDate) throws SQLException, Exception {
		TimeEntryDAO dao = new TimeEntryDAO();
		
		List<TimeEntryDispute> listFinal  = dao.getAllTimeEntryDisputeBySupervisorIdAndClockDate(supervisorId, clockDate);		
		
		
		dao.closeConnection();
		
		return listFinal;
	}
	
	public List<TimeEntry> getTimeEntryByDateAndSuperVisorAction(int superVisorId, String clockDate) throws SQLException, Exception {
		TimeEntryDAO dao = new TimeEntryDAO();
		List<TimeEntry> listFinal  = new ArrayList<TimeEntry>();
		
		List<Employee> empList = dao.getEmployeeListBySupervisorId(superVisorId);
		Map<Integer, List<TimeEntry>> timeEntryMap = dao.getAllTimeEntryBySuperVisorAndClockDateMap(superVisorId, clockDate);
		Map<Integer, List<EmployeeSchedule>> employeeScheduleMap = dao.getEmployeeScheduleBySuperVisorAndClockDateMap(superVisorId, clockDate);
		
		
		Iterator<Employee> empIterator = empList.iterator();
		
		while(empIterator.hasNext()){
			Employee employee = empIterator.next();
			
			List<TimeEntry> timeEntryList = null;
			List<EmployeeSchedule> empSchedList = null;		
			
			
			if(employeeScheduleMap.containsKey(employee.getEmpId())){
				empSchedList = employeeScheduleMap.get(employee.getEmpId());
				
				Iterator<EmployeeSchedule> empSchedIterator = empSchedList.iterator();
				
				while(empSchedIterator.hasNext()){
					EmployeeSchedule empSchedule = empSchedIterator.next();
					
					//TODO
					
					if(empSchedule.getShiftingScheduleId() == 2000){
						
						TimeEntry timeEntry = new TimeEntry();
						timeEntry.setEmpId(employee.getEmpId());
				    	timeEntry.setEmpName(employee.getLastname() + ", " + employee.getFirstname());
				    	timeEntry.setShiftScheduleDesc("Paid - Rest Day");
				    	timeEntry.setScheduleDate(empSchedule.getScheduleDate());
				    	timeEntry.setShiftScheduleId(empSchedule.getShiftingScheduleId());
				    		    
				    	timeEntry.setTimeIn("Paid - Rest Day");
				    	timeEntry.setTimeOut("Paid - Rest Day");
						
						listFinal.add(timeEntry);
						
		        	} else if(empSchedule.getShiftingScheduleId() == 2001){
		        		
		        		TimeEntry timeEntry = new TimeEntry();
						timeEntry.setEmpId(employee.getEmpId());
				    	timeEntry.setEmpName(employee.getLastname() + ", " + employee.getFirstname());
				    	timeEntry.setShiftScheduleDesc("Unpaid - Rest Day");
				    	timeEntry.setScheduleDate(empSchedule.getScheduleDate());
				    	timeEntry.setShiftScheduleId(empSchedule.getShiftingScheduleId());
				    		    
				    	timeEntry.setTimeIn("Unpaid - Rest Day");
				    	timeEntry.setTimeOut("Unpaid - Rest Day");
				    	
				    	listFinal.add(timeEntry);
		        	} else {
					
						if(timeEntryMap.containsKey(employee.getEmpId())){
							timeEntryList = timeEntryMap.get(employee.getEmpId());
							
							Iterator<TimeEntry> timeEntryIterator = timeEntryList.iterator();
							
							boolean noEntryFound = true;
							
							Map<String, TimeEntry> map2 = new HashMap<String, TimeEntry>();
							
							while(timeEntryIterator.hasNext()){
								TimeEntry timeEntry = timeEntryIterator.next();
								
								map2.put(StringUtils.isEmpty(timeEntry.getTimeIn()) ? "" : timeEntry.getTimeIn().substring(0, 10), timeEntry);
								
								if(empSchedule.getScheduleDate().equals(StringUtils.isEmpty(timeEntry.getTimeIn()) ? "" : timeEntry.getTimeIn().substring(0, 10)) && empSchedule.getShiftingScheduleId() == timeEntry.getShiftScheduleId() ){
									timeEntry.setScheduleDate(timeEntry.getTimeIn().substring(0, 10));
									timeEntry.setShiftScheduleId(empSchedule.getShiftingScheduleId());
									timeEntry.setShiftScheduleDesc(empSchedule.getEmpShift());
									listFinal.add(timeEntry);
									noEntryFound = false;
									break;
								}
							}
							
							if(noEntryFound){
								TimeEntry timeEntry = new TimeEntry();
								timeEntry.setEmpId(employee.getEmpId());
						    	timeEntry.setEmpName(employee.getLastname() + ", " + employee.getFirstname());
						    	timeEntry.setShiftScheduleDesc(empSchedule.getEmpShift());
						    	timeEntry.setScheduleDate(empSchedule.getScheduleDate());
						    	timeEntry.setShiftScheduleId(empSchedule.getShiftingScheduleId());
						    	//timeEntry.setShiftType(map2.get(empSchedule.getScheduleDate()).getShiftType());	    
						    	//timeEntry.setTimeIn(rs.getString("timeIn"));
						    	//timeEntry.setTimeOut(rs.getString("timeOut"));
								
								listFinal.add(timeEntry);
							}
							
							
							
						} else {
							TimeEntry timeEntry = new TimeEntry();
							timeEntry.setEmpId(employee.getEmpId());
					    	timeEntry.setEmpName(employee.getLastname() + ", " + employee.getFirstname());
					    	timeEntry.setShiftScheduleDesc(empSchedule.getEmpShift());
					    	timeEntry.setScheduleDate(empSchedule.getScheduleDate());
					    	timeEntry.setShiftScheduleId(empSchedule.getShiftingScheduleId());
					    	//timeEntry.setShiftType(map2.get(empSchedule.getScheduleDate()).getShiftType());	    
					    	//timeEntry.setTimeIn(rs.getString("timeIn"));
					    	//timeEntry.setTimeOut(rs.getString("timeOut"));
							
							listFinal.add(timeEntry);
						}	
		        	}
					
				}
			}
		}
				
		
		
		dao.closeConnection();
		
		return listFinal;
	}
	
	public void deleteEmployeeSchedule(int empScheduleId) throws SQLException, Exception {
		TimeEntryDAO dao = new TimeEntryDAO();
		dao.deleteEmployeeSchedule(empScheduleId);
		dao.closeConnection();		
	}
	
	public boolean checkIfCalendarHasSchedule(EmployeeSchedule empSched) throws SQLException, Exception {
		TimeEntryDAO dao = new TimeEntryDAO();
		boolean returnValue = dao.checkIfCalendarHasSchedule(empSched);
		dao.closeConnection();	
		return returnValue;
	}
}

