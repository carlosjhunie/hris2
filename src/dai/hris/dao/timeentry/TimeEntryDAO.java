package dai.hris.dao.timeentry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dai.hris.action.filemanager.util.EmployeeUtil;
import dai.hris.dao.DBConstants;
import dai.hris.model.Employee;
import dai.hris.model.EmployeeSchedule;
import dai.hris.model.EmployeeTimeEntry;
import dai.hris.model.Resolution;
import dai.hris.model.ShiftingSchedule;
import dai.hris.model.TimeEntry;
import dai.hris.model.TimeEntryDispute;

public class TimeEntryDAO {
	
	Connection conn = null;
	EmployeeUtil util = new EmployeeUtil();
	
	public TimeEntryDAO() {
		try {
    		
    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("TimeEntryDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("TimeEntryDAO :" + e.getMessage());
		}
	}
	
	public void deleteEmployeeSchedule(int empScheduleId) throws SQLException, Exception {
		String sql = "DELETE FROM  empSchedule WHERE empScheduleId = ?";
  		PreparedStatement ps  = conn.prepareStatement(sql);
  		int index = 1;
  		
  		ps.setInt(index++, empScheduleId);  		
        
        ps.executeUpdate();       
         
        conn.commit();
         
        ps.close();
	}
	
	public void insertEmployeeSchedule(EmployeeSchedule empSched) throws SQLException, Exception {
		String sql = "INSERT INTO empSchedule (shiftingScheduleId, empId, scheduleDate, createdBy, creationDate) VALUES (?,?,?,?,SYSDATETIME())";
  		PreparedStatement ps  = conn.prepareStatement(sql);
  		int index = 1;
  		
        ps.setInt(index++, empSched.getShiftingScheduleId());
        ps.setInt(index++, empSched.getEmpId());
        ps.setString(index++, empSched.getScheduleDate());
        ps.setInt(index++, empSched.getSuperVisorId());
        
        ps.executeUpdate(); 
        
        conn.commit();
        
        ps.close();
        sql = null;
        
	}
	
	public void updateEmployeeSchedule(EmployeeSchedule empSched) throws SQLException, Exception {
		String sql = "UPDATE  empSchedule set shiftingScheduleId = ?, updatedBy = ?, updateDate = SYSDATETIME() where empId =? AND scheduleDate = ?";
  		PreparedStatement ps  = conn.prepareStatement(sql);
  		int index = 1;
  		
  		ps.setInt(index++, empSched.getShiftingScheduleId());
  		ps.setInt(index++, empSched.getSuperVisorId());
        ps.setInt(index++, empSched.getEmpId());
        ps.setString(index++, empSched.getScheduleDate());		
        
        ps.executeUpdate();       
         
        conn.commit();
         
        ps.close();
         
	}
	
	public void editEmployeeSchedule(EmployeeSchedule empSched) throws SQLException, Exception {
		String sql = "UPDATE  empSchedule set scheduleDate = ?, shiftingScheduleId = ?, updatedBy = ?, updateDate = SYSDATETIME() where empScheduleId =?";
  		PreparedStatement ps  = conn.prepareStatement(sql);
  		int index = 1;
  		
  		ps.setString(index++, empSched.getScheduleDate());
  		ps.setInt(index++, empSched.getShiftingScheduleId());
  		ps.setInt(index++, empSched.getSuperVisorId());
        ps.setInt(index++, empSched.getEmpScheduleId());
        
        
        ps.executeUpdate();       
         
        conn.commit();
         
        ps.close();
         
	}
	
	public boolean checkIfEmployeeHasSchedule(EmployeeSchedule empSched) throws SQLException, Exception {
		String sql = "SELECT * FROM empSchedule WHERE empId = ? AND scheduleDate = ?";
  		PreparedStatement ps  = conn.prepareStatement(sql);
  		int index = 1;
  		

        ps.setInt(index++, empSched.getEmpId());
        ps.setString(index++, empSched.getScheduleDate());
       	
        
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
        	
        	ps.close();
        	
        	return true;
        }        
        
        ps.close();
        return false;
         
	}
	
	public boolean checkIfCalendarHasSchedule(EmployeeSchedule empSched) throws SQLException, Exception {
		String sql = "SELECT * FROM empSchedule WHERE createdBy = ? AND scheduleDate = ?";
  		PreparedStatement ps  = conn.prepareStatement(sql);
  		int index = 1;
  		

        ps.setInt(index++, empSched.getSuperVisorId());
        ps.setString(index++, empSched.getScheduleDate());
       	
        
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
        	
        	ps.close();
        	
        	return true;
        }        
        
        ps.close();
        return false;
         
	}
	
	public List<Employee> getEmployeeListBySupervisorId(int superVisorId) throws SQLException, Exception {
		String sql = "SELECT * FROM employee WHERE superVisor1Id = ?";
  		PreparedStatement ps  = conn.prepareStatement(sql);
  		int index = 1;
  		

        ps.setInt(index++, superVisorId);       
       	
        
        ResultSet rs = ps.executeQuery();
        
        List<Employee> empList = new ArrayList<Employee>();
        
        while(rs.next()){ 
        	Employee emp = new Employee();
			emp.setEmpId(rs.getInt("empId"));
			emp.setEmpNo(rs.getString("empNo")); // required field
			emp.setLastname(rs.getString("lastname"));
			emp.setFirstname(rs.getString("firstname"));
			emp.setMiddlename(rs.getString("middlename"));
			emp.setDateOfBirth(rs.getString("dateOfBirth"));
			emp.setGender(rs.getString("gender"));
			emp.setCivilStatus(rs.getString("civilStatus"));
			emp.setNationality(rs.getString("nationality"));
			emp.setStreet(rs.getString("street"));
			emp.setCityId(rs.getInt("cityId"));
			emp.setEmail(rs.getString("email"));
			emp.setMobileNo(rs.getString("mobileNo"));
			emp.setTelNo(rs.getString("telNo"));
			emp.setBirthPlace(rs.getString("birthPlace"));
			emp.setProvinceId(rs.getInt("provinceId"));
			emp.setZipCode(rs.getString("zipCode"));
			emp.setJobTitleId(rs.getInt("jobTitleId"));
			emp.setDepartmentId(rs.getInt("departmentId"));
			emp.setDivisionId(rs.getInt("divisionId"));
			emp.setEmployeeTypeId(rs.getInt("employeeTypeId"));
			emp.setEmpStatus(rs.getString("empStatus"));
			emp.setEmploymentDate(rs.getString("employmentDate"));
			emp.setEndOfContract(rs.getString("endOfContract"));
			emp.setSss(rs.getString("sss"));
			emp.setGsis(rs.getString("gsis"));
			emp.setHdmf(rs.getString("hdmf"));
			emp.setTin(rs.getString("tin"));
			emp.setPhic(rs.getString("phic"));
			emp.setTaxstatus(rs.getString("taxstatus"));
			emp.setPicLoc(rs.getString("picLoc"));
			emp.setSuperVisor1Id(rs.getInt("superVisor1Id"));
			emp.setSuperVisor2Id(rs.getInt("superVisor2Id"));
			emp.setSuperVisor3Id(rs.getInt("superVisor3Id"));
			emp.setCountryId(rs.getInt("countryId"));

        	empList.add(emp);       	
        }        
        
        ps.close();
        return empList;
         
	}
	
	
 	public List<EmployeeSchedule> getEmployeeScheduleForTheMonth(int supervisorId) throws SQLException, Exception {
 		StringBuffer sql = new StringBuffer();
 		
 		sql.append("SELECT es.empId, es.empScheduleId, es.scheduleDate, es.shiftingScheduleId, e.lastname, e.firstname, ss.description, es.updatedBy ");
 		sql.append("FROM empSchedule es, employee e, shiftingSchedule ss ");
 		sql.append("WHERE es.empId = e.empId AND es.shiftingScheduleId = ss.shiftingScheduleId ");
 		sql.append("AND MONTH(es.scheduleDate) = MONTH(SYSDATETIME()) ");
 		sql.append("AND e.superVisor1Id = ");
 		sql.append(supervisorId);
 		sql.append(" ORDER BY es.scheduleDate, e.lastname");
 				
  		PreparedStatement ps  = conn.prepareStatement(sql.toString());
  		
  		        
        ResultSet rs = ps.executeQuery();
        
        List<EmployeeSchedule> empSchedList = new ArrayList<EmployeeSchedule>();
        
        while(rs.next()){
        	EmployeeSchedule empSched = new EmployeeSchedule();
        	empSched.setEmpId(rs.getInt("empId"));
        	empSched.setEmpScheduleId(rs.getInt("empScheduleId"));
        	empSched.setScheduleDate(rs.getString("scheduleDate"));
        	empSched.setShiftingScheduleId(rs.getInt("shiftingScheduleId"));
        	empSched.setEmpName(rs.getString("lastname") + ", " + rs.getString("firstname"));
        	empSched.setEmpShift(rs.getString("description"));
        	empSched.setUpdatedBy(rs.getInt("updatedBy"));
        	
        	empSchedList.add(empSched);
        	
        }
        
        StringBuffer sql2 = new StringBuffer();
 		
 		sql2.append("SELECT es.empId, es.empScheduleId, es.scheduleDate, es.shiftingScheduleId, e.lastname, e.firstname, es.updatedBy ");
 		sql2.append("FROM empSchedule es, employee e ");
 		sql2.append("WHERE es.empId = e.empId AND es.shiftingScheduleId in (2000, 2001) ");
 		sql2.append("AND MONTH(es.scheduleDate) = MONTH(SYSDATETIME()) ");
 		sql2.append("AND e.superVisor1Id = ");
 		sql2.append(supervisorId);
 		sql2.append(" ORDER BY es.scheduleDate, e.lastname");
 				
  		PreparedStatement ps2  = conn.prepareStatement(sql2.toString());
  		
  		        
        ResultSet rs2 = ps2.executeQuery();
        
        
        
        while(rs2.next()){
        	EmployeeSchedule empSched = new EmployeeSchedule();
        	empSched.setEmpId(rs2.getInt("empId"));
        	empSched.setEmpScheduleId(rs2.getInt("empScheduleId"));
        	empSched.setScheduleDate(rs2.getString("scheduleDate"));
        	empSched.setShiftingScheduleId(rs2.getInt("shiftingScheduleId"));
        	empSched.setEmpName(rs2.getString("lastname") + ", " + rs2.getString("firstname"));
        	
        	if(empSched.getShiftingScheduleId() == 2000){
        		empSched.setEmpShift("Paid - Rest Day");
        	} else if(empSched.getShiftingScheduleId() == 2001){
        		empSched.setEmpShift("Unpaid - Rest Day");
        	}
        	
        	empSched.setUpdatedBy(rs2.getInt("updatedBy"));
        	
        	empSchedList.add(empSched);
        	
        }
        
        ps.close();
        return empSchedList;
         
	}
 	
 	public List<EmployeeSchedule> getEmployeeScheduleCalendar(int supervisorId) throws SQLException, Exception {
 		StringBuffer sql = new StringBuffer();
 		
 		sql.append("SELECT es.empId, es.empScheduleId, es.scheduleDate, es.shiftingScheduleId, e.lastname, e.firstname, ss.description, es.updatedBy ");
 		sql.append("FROM empSchedule es, employee e, shiftingSchedule ss ");
 		sql.append("WHERE es.empId = e.empId AND es.shiftingScheduleId = ss.shiftingScheduleId ");
 		//sql.append("AND MONTH(es.scheduleDate) = MONTH(SYSDATETIME()) ");
 		sql.append("AND e.superVisor1Id = ");
 		sql.append(supervisorId);
 		sql.append(" ORDER BY es.scheduleDate, e.lastname");
 				
  		PreparedStatement ps  = conn.prepareStatement(sql.toString());
  		
  		        
        ResultSet rs = ps.executeQuery();
        
        List<EmployeeSchedule> empSchedList = new ArrayList<EmployeeSchedule>();
        
        while(rs.next()){
        	EmployeeSchedule empSched = new EmployeeSchedule();
        	empSched.setEmpId(rs.getInt("empId"));
        	empSched.setEmpScheduleId(rs.getInt("empScheduleId"));
        	empSched.setScheduleDate(rs.getString("scheduleDate"));
        	empSched.setShiftingScheduleId(rs.getInt("shiftingScheduleId"));
        	empSched.setEmpName(rs.getString("lastname") + ", " + rs.getString("firstname"));
        	empSched.setEmpShift(rs.getString("description"));
        	empSched.setUpdatedBy(rs.getInt("updatedBy"));
        	
        	empSchedList.add(empSched);
        	
        }
        
        //For Rest Day and 
        
        StringBuffer sql2 = new StringBuffer();
 		
 		sql2.append("SELECT es.empId, es.empScheduleId, es.scheduleDate, es.shiftingScheduleId, e.lastname, e.firstname, es.updatedBy ");
 		sql2.append("FROM empSchedule es, employee e ");
 		sql2.append("WHERE es.empId = e.empId AND es.shiftingScheduleId in (2000, 2001) ");
 		//sql2.append("AND MONTH(es.scheduleDate) = MONTH(SYSDATETIME()) ");
 		sql2.append("AND e.superVisor1Id = ");
 		sql2.append(supervisorId);
 		sql2.append(" ORDER BY es.scheduleDate, e.lastname");
 				
  		PreparedStatement ps2  = conn.prepareStatement(sql2.toString());
  		
  		        
        ResultSet rs2 = ps2.executeQuery();
        
        
        
        while(rs2.next()){
        	EmployeeSchedule empSched = new EmployeeSchedule();
        	empSched.setEmpId(rs2.getInt("empId"));
        	empSched.setEmpScheduleId(rs2.getInt("empScheduleId"));
        	empSched.setScheduleDate(rs2.getString("scheduleDate"));
        	empSched.setShiftingScheduleId(rs2.getInt("shiftingScheduleId"));
        	empSched.setEmpName(rs2.getString("lastname") + ", " + rs2.getString("firstname"));
        	
        	if(empSched.getShiftingScheduleId() == 2000){
        		empSched.setEmpShift("Paid - Rest Day");
        	} else if(empSched.getShiftingScheduleId() == 2001){
        		empSched.setEmpShift("Unpaid - Rest Day");
        	}
        	
        	empSched.setUpdatedBy(rs2.getInt("updatedBy"));
        	
        	empSchedList.add(empSched);
        	
        }
        
        /*
        
        //For Leave
        StringBuffer sql3 = new StringBuffer();
 		
        sql3.append("SELECT l.empId, l.leaveId, l.dateFrom, l.dateTo, e.lastname, e.firstname ");
        sql3.append("FROM leave l, employee e ");
 		sql3.append("WHERE l.empId = e.empId "); 		
 		sql3.append("AND e.superVisor1Id = ");
 		sql3.append(supervisorId);
 		sql3.append(" ORDER BY l.dateFrom, e.lastname");
 				
  		PreparedStatement ps3  = conn.prepareStatement(sql3.toString());
  		
  		        
        ResultSet rs3 = ps3.executeQuery();
        
        
        
        while(rs3.next()){
        	EmployeeSchedule empSched = new EmployeeSchedule();
        	empSched.setEmpId(rs3.getInt("empId"));        	
        	empSched.setScheduleDate(rs3.getString("dateFrom"));
        	empSched.setToDate(rs3.getString("dateTo"));        	
        	empSched.setEmpName(rs3.getString("lastname") + ", " + rs3.getString("firstname"));        	
        	empSched.setEmpShift("On Leave");        	      	
        	empSchedList.add(empSched);        	
        }
        
        //For Out of Office
        StringBuffer sql4 = new StringBuffer();
 		
        sql4.append("SELECT l.empId, l.empOOOId, l.dateFrom, l.dateTo, e.lastname, e.firstname ");
        sql4.append("FROM empOutOfOffice l, employee e ");
        sql4.append("WHERE l.empId = e.empId "); 		
        sql4.append("AND e.superVisor1Id = ");
        sql4.append(supervisorId);
        sql4.append(" ORDER BY l.dateFrom, e.lastname");
 				
  		PreparedStatement ps4  = conn.prepareStatement(sql4.toString());
  		
  		        
        ResultSet rs4 = ps4.executeQuery();
        
        
        
        while(rs4.next()){
        	EmployeeSchedule empSched = new EmployeeSchedule();
        	empSched.setEmpId(rs4.getInt("empId"));        	
        	empSched.setScheduleDate(rs4.getString("dateFrom"));
        	empSched.setToDate(rs4.getString("dateTo"));        	
        	empSched.setEmpName(rs4.getString("lastname") + ", " + rs4.getString("firstname"));        	
        	empSched.setEmpShift("On Training and Seminar");        	      	
        	empSchedList.add(empSched);        	
        }
        
        
        
        ps3.close();
        ps4.close();
        
        */
        ps.close();
        ps2.close();
        return empSchedList;
         
	}
 	
 	public List<EmployeeSchedule> getEmployeeScheduleCalendarSpecificDate(int supervisorId, String scheduleDate) throws SQLException, Exception {
 		StringBuffer sql = new StringBuffer();
 		
 		sql.append("SELECT es.empId, es.empScheduleId, es.scheduleDate, es.shiftingScheduleId, e.lastname, e.firstname, ss.description, es.updatedBy ");
 		sql.append("FROM empSchedule es, employee e, shiftingSchedule ss ");
 		sql.append("WHERE es.empId = e.empId AND es.shiftingScheduleId = ss.shiftingScheduleId ");
 		//sql.append("AND MONTH(es.scheduleDate) = MONTH(SYSDATETIME()) ");
 		sql.append(" AND convert(varchar(26),es.scheduleDate,23) = '");
    	sql.append(scheduleDate);
 		sql.append("' AND e.superVisor1Id = ");
 		sql.append(supervisorId);
 		sql.append(" ORDER BY es.scheduleDate, e.lastname");
 				
  		PreparedStatement ps  = conn.prepareStatement(sql.toString());
  		
  		        
        ResultSet rs = ps.executeQuery();
        
        List<EmployeeSchedule> empSchedList = new ArrayList<EmployeeSchedule>();
        
        while(rs.next()){
        	EmployeeSchedule empSched = new EmployeeSchedule();
        	empSched.setEmpId(rs.getInt("empId"));
        	empSched.setEmpScheduleId(rs.getInt("empScheduleId"));
        	empSched.setScheduleDate(rs.getString("scheduleDate"));
        	empSched.setShiftingScheduleId(rs.getInt("shiftingScheduleId"));
        	empSched.setEmpName(rs.getString("lastname") + ", " + rs.getString("firstname"));
        	empSched.setEmpShift(rs.getString("description"));
        	empSched.setUpdatedBy(rs.getInt("updatedBy"));
        	
        	empSchedList.add(empSched);
        	
        }
        
        StringBuffer sql2 = new StringBuffer();
 		
 		sql2.append("SELECT es.empId, es.empScheduleId, es.scheduleDate, es.shiftingScheduleId, e.lastname, e.firstname, es.updatedBy ");
 		sql2.append("FROM empSchedule es, employee e ");
 		sql2.append("WHERE es.empId = e.empId AND es.shiftingScheduleId in (2000, 2001) ");
 		//sql2.append("AND MONTH(es.scheduleDate) = MONTH(SYSDATETIME()) ");
 		sql2.append(" AND convert(varchar(26),es.scheduleDate,23) = '");
    	sql2.append(scheduleDate);
 		sql2.append("' AND e.superVisor1Id = ");
 		sql2.append(supervisorId);
 		sql2.append(" ORDER BY es.scheduleDate, e.lastname");
 				
  		PreparedStatement ps2  = conn.prepareStatement(sql2.toString());
  		
  		        
        ResultSet rs2 = ps2.executeQuery();
        
        
        
        while(rs2.next()){
        	EmployeeSchedule empSched = new EmployeeSchedule();
        	empSched.setEmpId(rs2.getInt("empId"));
        	empSched.setEmpScheduleId(rs2.getInt("empScheduleId"));
        	empSched.setScheduleDate(rs2.getString("scheduleDate"));
        	empSched.setShiftingScheduleId(rs2.getInt("shiftingScheduleId"));
        	empSched.setEmpName(rs2.getString("lastname") + ", " + rs2.getString("firstname"));
        	
        	if(empSched.getShiftingScheduleId() == 2000){
        		empSched.setEmpShift("Paid - Rest Day");
        	} else if(empSched.getShiftingScheduleId() == 2001){
        		empSched.setEmpShift("Unpaid - Rest Day");
        	}
        	
        	empSched.setUpdatedBy(rs2.getInt("updatedBy"));
        	
        	empSchedList.add(empSched);
        	
        }
        
        ps.close();
        return empSchedList;
         
	}
 	
 	public Map<Integer, List<EmployeeSchedule>> getEmployeeScheduleForTheMonthMap(int supervisorId) throws SQLException, Exception {
 		StringBuffer sql = new StringBuffer();
 		
 		sql.append("SELECT es.empId, es.empScheduleId, es.scheduleDate, es.shiftingScheduleId, e.lastname, ss.description, es.updatedBy ");
 		sql.append("FROM empSchedule es, employee e, shiftingSchedule ss ");
 		sql.append("WHERE es.empId = e.empId AND es.shiftingScheduleId = ss.shiftingScheduleId ");
 		//sql.append("AND MONTH(es.scheduleDate) = MONTH(SYSDATETIME()) ");
 		sql.append("AND e.superVisor1Id = ");
 		sql.append(supervisorId);
 		sql.append(" ORDER BY es.scheduleDate");
 				
  		PreparedStatement ps  = conn.prepareStatement(sql.toString());
  		
  		        
        ResultSet rs = ps.executeQuery();
        
        //List<EmployeeSchedule> empSchedList = new ArrayList<EmployeeSchedule>();
        Map<Integer, List<EmployeeSchedule>> map = new HashMap<Integer, List<EmployeeSchedule>>();
        
        while(rs.next()){
        	EmployeeSchedule empSched = new EmployeeSchedule();
        	empSched.setEmpId(rs.getInt("empId"));
        	empSched.setEmpScheduleId(rs.getInt("empScheduleId"));
        	empSched.setScheduleDate(rs.getString("scheduleDate"));
        	empSched.setShiftingScheduleId(rs.getInt("shiftingScheduleId"));
        	empSched.setEmpName(rs.getString("lastname"));
        	empSched.setEmpShift(rs.getString("description"));
        	empSched.setUpdatedBy(rs.getInt("updatedBy"));
        	
        	
        	
        	if(map.containsKey(rs.getInt("empId"))){
	    		 map.get(rs.getInt("empId")).add(empSched);
	    	 } else {
	    		 List<EmployeeSchedule> list = new ArrayList<EmployeeSchedule>();
	    		 list.add(empSched);
	    		 map.put(rs.getInt("empId"), list);
	    	 }
        	
        }
        
        StringBuffer sql2 = new StringBuffer();
 		
 		sql2.append("SELECT es.empId, es.empScheduleId, es.scheduleDate, es.shiftingScheduleId, e.lastname, es.updatedBy ");
 		sql2.append("FROM empSchedule es, employee e ");
 		sql2.append("WHERE es.empId = e.empId AND es.shiftingScheduleId in (2000, 2001) ");
 		//sql2.append("AND MONTH(es.scheduleDate) = MONTH(SYSDATETIME()) ");
 		sql2.append("AND e.superVisor1Id = ");
 		sql2.append(supervisorId);
 		sql2.append(" ORDER BY es.scheduleDate");
 				
  		PreparedStatement ps2  = conn.prepareStatement(sql2.toString());
  		
  		        
        ResultSet rs2 = ps2.executeQuery();
        
        
        
        while(rs2.next()){
        	EmployeeSchedule empSched = new EmployeeSchedule();
        	empSched.setEmpId(rs2.getInt("empId"));
        	empSched.setEmpScheduleId(rs2.getInt("empScheduleId"));
        	empSched.setScheduleDate(rs2.getString("scheduleDate"));
        	empSched.setShiftingScheduleId(rs2.getInt("shiftingScheduleId"));
        	empSched.setEmpName(rs2.getString("lastname"));
        	
        	if(empSched.getShiftingScheduleId() == 2000){
        		empSched.setEmpShift("Paid - Rest Day");
        	} else if(empSched.getShiftingScheduleId() == 2001){
        		empSched.setEmpShift("Unpaid - Rest Day");
        	}
        	
        	empSched.setUpdatedBy(rs2.getInt("updatedBy"));
        	if(map.containsKey(rs2.getInt("empId"))){
        	
	    		 map.get(rs2.getInt("empId")).add(empSched);
	    	 } else {
	    		 List<EmployeeSchedule> list = new ArrayList<EmployeeSchedule>();
	    		 list.add(empSched);
	    		 map.put(rs2.getInt("empId"), list);
	    	 }
        	
        }
        
        ps.close();
        return map;
         
	}
 	
 	public Map<Integer, List<EmployeeSchedule>> getEmployeeScheduleForTheMonthByEmpIdMap(int empId) throws SQLException, Exception {
 		StringBuffer sql = new StringBuffer();
 		
 		sql.append("SELECT es.empId, es.empScheduleId, es.scheduleDate, es.shiftingScheduleId, e.lastname, ss.description, es.updatedBy ");
 		sql.append("FROM empSchedule es, employee e, shiftingSchedule ss ");
 		sql.append("WHERE es.empId = e.empId AND es.shiftingScheduleId = ss.shiftingScheduleId ");
 		sql.append("AND MONTH(es.scheduleDate) = MONTH(SYSDATETIME()) ");
 		sql.append("AND e.empId = ");
 		sql.append(empId);
 		sql.append(" ORDER BY es.scheduleDate");
 				
  		PreparedStatement ps  = conn.prepareStatement(sql.toString());
  		
  		        
        ResultSet rs = ps.executeQuery();
        
        //List<EmployeeSchedule> empSchedList = new ArrayList<EmployeeSchedule>();
        Map<Integer, List<EmployeeSchedule>> map = new HashMap<Integer, List<EmployeeSchedule>>();
        
        while(rs.next()){
        	EmployeeSchedule empSched = new EmployeeSchedule();
        	empSched.setEmpId(rs.getInt("empId"));
        	empSched.setEmpScheduleId(rs.getInt("empScheduleId"));
        	empSched.setScheduleDate(rs.getString("scheduleDate"));
        	empSched.setShiftingScheduleId(rs.getInt("shiftingScheduleId"));
        	empSched.setEmpName(rs.getString("lastname"));
        	empSched.setEmpShift(rs.getString("description"));
        	empSched.setUpdatedBy(rs.getInt("updatedBy"));
        	
        	
        	
        	if(map.containsKey(rs.getInt("empId"))){
	    		 map.get(rs.getInt("empId")).add(empSched);
	    	 } else {
	    		 List<EmployeeSchedule> list = new ArrayList<EmployeeSchedule>();
	    		 list.add(empSched);
	    		 map.put(rs.getInt("empId"), list);
	    	 }
        	
        }
        
        StringBuffer sql2 = new StringBuffer();
 		
 		sql2.append("SELECT es.empId, es.empScheduleId, es.scheduleDate, es.shiftingScheduleId, e.lastname, es.updatedBy ");
 		sql2.append("FROM empSchedule es, employee e ");
 		sql2.append("WHERE es.empId = e.empId AND es.shiftingScheduleId in (2000, 2001) ");
 		//sql2.append("AND MONTH(es.scheduleDate) = MONTH(SYSDATETIME()) ");
 		sql2.append("AND e.empId = ");
 		sql2.append(empId);
 		sql2.append(" ORDER BY es.scheduleDate");
 				
  		PreparedStatement ps2  = conn.prepareStatement(sql2.toString());
  		
  		        
        ResultSet rs2 = ps2.executeQuery();
        
        
        
        while(rs2.next()){
        	EmployeeSchedule empSched = new EmployeeSchedule();
        	empSched.setEmpId(rs2.getInt("empId"));
        	empSched.setEmpScheduleId(rs2.getInt("empScheduleId"));
        	empSched.setScheduleDate(rs2.getString("scheduleDate"));
        	empSched.setShiftingScheduleId(rs2.getInt("shiftingScheduleId"));
        	empSched.setEmpName(rs2.getString("lastname"));
        	
        	if(empSched.getShiftingScheduleId() == 2000){
        		empSched.setEmpShift("Paid - Rest Day");
        	} else if(empSched.getShiftingScheduleId() == 2001){
        		empSched.setEmpShift("Unpaid - Rest Day");
        	}
        	
        	empSched.setUpdatedBy(rs2.getInt("updatedBy"));
        	if(map.containsKey(rs2.getInt("empId"))){
        	
	    		 map.get(rs2.getInt("empId")).add(empSched);
	    	 } else {
	    		 List<EmployeeSchedule> list = new ArrayList<EmployeeSchedule>();
	    		 list.add(empSched);
	    		 map.put(rs2.getInt("empId"), list);
	    	 }
        	
        }
        
        ps.close();
        return map;
         
	}
 	
 	public Map<Integer, List<EmployeeSchedule>> getEmployeeScheduleBySuperVisorAndClockDateMap(int supervisorId, String clockDate) throws SQLException, Exception {
 		StringBuffer sql = new StringBuffer();
 		
 		sql.append("SELECT es.empId, es.empScheduleId, es.scheduleDate, es.shiftingScheduleId, e.lastname, ss.description, es.updatedBy ");
 		sql.append("FROM empSchedule es, employee e, shiftingSchedule ss ");
 		sql.append("WHERE es.empId = e.empId AND es.shiftingScheduleId = ss.shiftingScheduleId ");
 		sql.append("AND es.scheduleDate = '");
 		sql.append(clockDate);
 		sql.append("' AND e.superVisor1Id = ");
 		sql.append(supervisorId);
 		 				
  		PreparedStatement ps  = conn.prepareStatement(sql.toString());
  		
  		        
        ResultSet rs = ps.executeQuery();
        
        //List<EmployeeSchedule> empSchedList = new ArrayList<EmployeeSchedule>();
        Map<Integer, List<EmployeeSchedule>> map = new HashMap<Integer, List<EmployeeSchedule>>();
        
        while(rs.next()){
        	EmployeeSchedule empSched = new EmployeeSchedule();
        	empSched.setEmpId(rs.getInt("empId"));
        	empSched.setEmpScheduleId(rs.getInt("empScheduleId"));
        	empSched.setScheduleDate(rs.getString("scheduleDate"));
        	empSched.setShiftingScheduleId(rs.getInt("shiftingScheduleId"));
        	empSched.setEmpName(rs.getString("lastname"));
        	empSched.setEmpShift(rs.getString("description"));
        	empSched.setUpdatedBy(rs.getInt("updatedBy"));
        	
        	
        	
        	if(map.containsKey(rs.getInt("empId"))){
	    		 map.get(rs.getInt("empId")).add(empSched);
	    	 } else {
	    		 List<EmployeeSchedule> list = new ArrayList<EmployeeSchedule>();
	    		 list.add(empSched);
	    		 map.put(rs.getInt("empId"), list);
	    	 }
        	
        }
        
        StringBuffer sql2 = new StringBuffer();
 		
 		sql2.append("SELECT es.empId, es.empScheduleId, es.scheduleDate, es.shiftingScheduleId, e.lastname, es.updatedBy ");
 		sql2.append("FROM empSchedule es, employee e ");
 		sql2.append("WHERE es.empId = e.empId AND es.shiftingScheduleId in (2000, 2001) ");
 		sql2.append("AND es.scheduleDate = '");
 		sql2.append(clockDate);
 		sql2.append("' AND e.superVisor1Id = ");
 		sql2.append(supervisorId);
 		
 				
  		PreparedStatement ps2  = conn.prepareStatement(sql2.toString());
  		
  		        
        ResultSet rs2 = ps2.executeQuery();
        
        
        
        while(rs2.next()){
        	EmployeeSchedule empSched = new EmployeeSchedule();
        	empSched.setEmpId(rs2.getInt("empId"));
        	empSched.setEmpScheduleId(rs2.getInt("empScheduleId"));
        	empSched.setScheduleDate(rs2.getString("scheduleDate"));
        	empSched.setShiftingScheduleId(rs2.getInt("shiftingScheduleId"));
        	empSched.setEmpName(rs2.getString("lastname"));
        	
        	if(empSched.getShiftingScheduleId() == 2000){
        		empSched.setEmpShift("Paid - Rest Day");
        	} else if(empSched.getShiftingScheduleId() == 2001){
        		empSched.setEmpShift("Unpaid - Rest Day");
        	}
        	
        	empSched.setUpdatedBy(rs2.getInt("updatedBy"));
        	if(map.containsKey(rs2.getInt("empId"))){
        	
	    		 map.get(rs2.getInt("empId")).add(empSched);
	    	 } else {
	    		 List<EmployeeSchedule> list = new ArrayList<EmployeeSchedule>();
	    		 list.add(empSched);
	    		 map.put(rs2.getInt("empId"), list);
	    	 }
        	
        }
        
        ps.close();
        return map;
         
	}
	
	
	
	public void insertTimeIn(TimeEntry timeEntry) throws SQLException, Exception {
		String sql = "INSERT INTO empTimeEntry (shiftId, timeIn, empId, inputMethodTimeIn,  deviceNoTimeIn) VALUES (?,?,?,?,?)";
  		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
  		int index = 1;
  		
        ps.setInt(index++, timeEntry.getShiftId());
        ps.setTimestamp(index++, timeEntry.getTimeInTS());
        ps.setInt(index++, timeEntry.getEmpId());
        ps.setInt(index++, timeEntry.getVerifyCode());
        ps.setString(index++, timeEntry.getDeviceNo());
        
        
        ps.executeUpdate();
          
        ResultSet keyResultSet = ps.getGeneratedKeys();
         int generatedAutoIncrementId = 0;
         if (keyResultSet.next()) {
          	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
          	timeEntry.setTimeEntryId(generatedAutoIncrementId);
          	conn.commit();
          }
        ps.close();
        keyResultSet.close();
	}
	
	public void updateTimeOut(TimeEntry timeEntry) throws SQLException, Exception {
		String sql = "UPDATE  empTimeEntry set timeOut = ?, inputMethodTimeOut = ?, deviceNoTimeOut = ? where timeEntryId =?";
  		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
  		int index = 1;  
  		
  	    ps.setTimestamp(index++, timeEntry.getTimeOutTS());
  	    ps.setInt(index++, timeEntry.getVerifyCode());
  	    ps.setString(index++, timeEntry.getDeviceNo());
        ps.setInt(index++, timeEntry.getTimeEntryId());
        
        ps.executeUpdate();
          
        conn.commit();
        ps.close();
        
	}

	
	
	

	
	
    public  List<TimeEntry> getAllTimeEntryBySuperVisor(int superVisorId) throws SQLException, Exception {
    	StringBuffer sql = new StringBuffer();
    	
    	sql.append("SELECT ee.shiftId, ee.timeEntryId, e.empId, (e.lastname + ',' + e.firstname) as name, ee.timeIn, ee.timeOut, ss.description, ss.shiftType ");
    	sql.append("FROM empTimeEntry ee, employee e, shiftingSchedule ss ");
    	sql.append("WHERE ee.empId = e.empId AND ee.shiftId = ss.shiftingScheduleId AND e.superVisor1Id = ");
    	sql.append(superVisorId);
    	sql.append(" ORDER BY ee.timeIn");
    	
    	
		PreparedStatement ps = conn.prepareStatement(sql.toString());

	    ResultSet rs = ps.executeQuery();
	    List<TimeEntry> list = new ArrayList<TimeEntry>();
	    
	    while (rs.next()) {
	    	 TimeEntry model = new TimeEntry();
	    	 model.setTimeEntryId(rs.getInt("timeEntryId"));
	    	 model.setEmpId(rs.getInt("empId"));
	    	 model.setEmpName(rs.getString("name"));
	    	 model.setShiftScheduleDesc(rs.getString("description"));
	    	 model.setShiftType(rs.getString("shiftType"));	    
	    	 model.setTimeIn(rs.getString("timeIn"));
	    	 model.setTimeOut(rs.getString("timeOut"));
	    	 model.setShiftScheduleId(rs.getInt("shiftId"));
	    	 model.setShiftId(rs.getInt("shiftId"));
	    	 
	    	 list.add(model);

	    }	    
	    ps.close();
	    rs.close();      
	    return list;     

	}
    
    public  Map<Integer, List<TimeEntry>> getAllTimeEntryBySuperVisorMap(int superVisorId) throws SQLException, Exception {
    	StringBuffer sql = new StringBuffer();
    	
    	sql.append("SELECT ee.shiftId, ee.timeEntryId, e.empId, (e.lastname + ',' + e.firstname) as name, ee.timeIn, ee.timeOut, ss.description, ss.shiftType ");
    	sql.append("FROM empTimeEntry ee, employee e, shiftingSchedule ss ");
    	sql.append("WHERE ee.empId = e.empId AND ee.shiftId = ss.shiftingScheduleId AND e.superVisor1Id = ");
    	sql.append(superVisorId);
    	sql.append(" ORDER BY ee.timeIn");
    	
    	
		PreparedStatement ps = conn.prepareStatement(sql.toString());

	    ResultSet rs = ps.executeQuery();
	    
	    
	    Map<Integer, List<TimeEntry>> map = new HashMap<Integer, List<TimeEntry>>();	    
	    
	    while (rs.next()) {
	    	 TimeEntry timeEntry = new TimeEntry();
	    	 timeEntry.setTimeEntryId(rs.getInt("timeEntryId"));
	    	 timeEntry.setEmpId(rs.getInt("empId"));
	    	 timeEntry.setEmpName(rs.getString("name"));
	    	 timeEntry.setShiftScheduleDesc(rs.getString("description"));
	    	 timeEntry.setShiftType(rs.getString("shiftType"));	    
	    	 timeEntry.setTimeIn(rs.getString("timeIn"));
	    	 timeEntry.setTimeOut(rs.getString("timeOut"));
	    	 timeEntry.setShiftScheduleId(rs.getInt("shiftId"));
	    	 timeEntry.setShiftId(rs.getInt("shiftId"));
	    	 
	    	 if(map.containsKey(rs.getInt("empId"))){
	    		 map.get(rs.getInt("empId")).add(timeEntry);
	    	 } else {
	    		 List<TimeEntry> list = new ArrayList<TimeEntry>();
	    		 list.add(timeEntry);
	    		 map.put(rs.getInt("empId"), list);
	    	 }    	 

	    }	    
	    ps.close();
	    rs.close();      
	    return map;     

	}
    
    public  Map<Integer, List<TimeEntry>> getAllTimeEntryByEmpIdMap(int empId) throws SQLException, Exception {
    	StringBuffer sql = new StringBuffer();
    	
    	sql.append("SELECT ee.shiftId, ee.timeEntryId, e.empId, (e.lastname + ',' + e.firstname) as name, ee.timeIn, ee.timeOut, ss.description, ss.shiftType ");
    	sql.append("FROM empTimeEntry ee, employee e, shiftingSchedule ss ");
    	sql.append("WHERE ee.empId = e.empId AND ee.shiftId = ss.shiftingScheduleId AND e.empId = ");
    	sql.append(empId);
    	sql.append(" ORDER BY ee.timeIn");
    	
    	
		PreparedStatement ps = conn.prepareStatement(sql.toString());

	    ResultSet rs = ps.executeQuery();
	    
	    
	    Map<Integer, List<TimeEntry>> map = new HashMap<Integer, List<TimeEntry>>();	    
	    
	    while (rs.next()) {
	    	 TimeEntry timeEntry = new TimeEntry();
	    	 timeEntry.setTimeEntryId(rs.getInt("timeEntryId"));
	    	 timeEntry.setEmpId(rs.getInt("empId"));
	    	 timeEntry.setEmpName(rs.getString("name"));
	    	 timeEntry.setShiftScheduleDesc(rs.getString("description"));
	    	 timeEntry.setShiftType(rs.getString("shiftType"));	    
	    	 timeEntry.setTimeIn(rs.getString("timeIn"));
	    	 timeEntry.setTimeOut(rs.getString("timeOut"));
	    	 timeEntry.setShiftScheduleId(rs.getInt("shiftId"));
	    	 timeEntry.setShiftId(rs.getInt("shiftId"));
	    	 
	    	 if(map.containsKey(rs.getInt("empId"))){
	    		 map.get(rs.getInt("empId")).add(timeEntry);
	    	 } else {
	    		 List<TimeEntry> list = new ArrayList<TimeEntry>();
	    		 list.add(timeEntry);
	    		 map.put(rs.getInt("empId"), list);
	    	 }    	 

	    }	    
	    ps.close();
	    rs.close();      
	    return map;     

	}
    
    public  Map<Integer, List<TimeEntry>> getAllTimeEntryBySuperVisorAndClockDateMap(int superVisorId, String clockDate) throws SQLException, Exception {
    	StringBuffer sql = new StringBuffer();
    	
    	sql.append("SELECT ee.shiftId, ee.timeEntryId, e.empId, (e.lastname + ',' + e.firstname) as name, ee.timeIn, ee.timeOut, ss.description, ss.shiftType ");
    	sql.append("FROM empTimeEntry ee, employee e, shiftingSchedule ss ");
    	sql.append("WHERE ee.empId = e.empId AND ee.shiftId = ss.shiftingScheduleId AND e.superVisor1Id = ");
    	sql.append(superVisorId);
    	sql.append(" AND convert(varchar(26),ee.timeIn,23) = '");
    	sql.append(clockDate);
    	sql.append("'");
    	
    	
		PreparedStatement ps = conn.prepareStatement(sql.toString());

	    ResultSet rs = ps.executeQuery();
	    
	    
	    Map<Integer, List<TimeEntry>> map = new HashMap<Integer, List<TimeEntry>>();	    
	    
	    while (rs.next()) {
	    	 TimeEntry timeEntry = new TimeEntry();
	    	 
	    	 timeEntry.setTimeEntryId(rs.getInt("timeEntryId"));
	    	 timeEntry.setEmpId(rs.getInt("empId"));
	    	 timeEntry.setEmpName(rs.getString("name"));
	    	 timeEntry.setShiftScheduleDesc(rs.getString("description"));
	    	 timeEntry.setShiftType(rs.getString("shiftType"));	    
	    	 timeEntry.setTimeIn(rs.getString("timeIn"));
	    	 timeEntry.setTimeOut(rs.getString("timeOut"));
	    	 timeEntry.setShiftScheduleId(rs.getInt("shiftId"));
	    	 timeEntry.setShiftId(rs.getInt("shiftId"));
	    	 
	    	 if(rs.getString("timeIn") != null && rs.getString("timeIn").length() > 0){
	    		 String timeInHrsText = rs.getString("timeIn").substring(11, 16);
	    		 
	    		 timeEntry.setTimeInHrsText(timeInHrsText);
	    	 } else {
	    		 timeEntry.setTimeInHrsText("00:00");
	    	 }
	    	 
	    	 if(rs.getString("timeOut") != null && rs.getString("timeOut").length() > 0){
	    		 String timeOutHrsText = rs.getString("timeIn").substring(11, 16);
	    		 
	    		 timeEntry.setTimeOutHrsText(timeOutHrsText);	    		 
	    	 } else {
	    		 timeEntry.setTimeOutHrsText("00:00");	  
	    	 }
	    	 
	    	 
	    	 
	    	 if(map.containsKey(rs.getInt("empId"))){
	    		 map.get(rs.getInt("empId")).add(timeEntry);
	    	 } else {
	    		 List<TimeEntry> list = new ArrayList<TimeEntry>();
	    		 list.add(timeEntry);
	    		 map.put(rs.getInt("empId"), list);
	    	 }    	 

	    }	    
	    ps.close();
	    rs.close();      
	    return map;     

	}
    
    public  ArrayList<TimeEntry> getAllTimeEntryByMonthAndSuperVisor(int superVisorId) throws SQLException, Exception {
    	StringBuffer sql = new StringBuffer();
    	
    	sql.append("SELECT ee.shiftId, ee.timeEntryId, e.empId, (e.lastname + ',' + e.firstname) as name, ee.timeIn, ee.timeOut, ss.description, ss.shiftType ");
    	sql.append("FROM empTimeEntry ee, employee e, shiftingSchedule ss ");
//    	sql.append("WHERE ee.empId = e.empId AND ee.shiftId = ss.shiftingScheduleId AND MONTH(ee.timeIn) = MONTH(SYSDATETIME()) AND e.superVisor1Id = ");
    	sql.append("WHERE ee.empId = e.empId AND ee.shiftId = ss.shiftingScheduleId AND e.superVisor1Id = ");
    	sql.append(superVisorId);
    	
    	
		PreparedStatement ps = conn.prepareStatement(sql.toString());

	    ResultSet rs = ps.executeQuery();
	    ArrayList<TimeEntry> list = new ArrayList<TimeEntry>();
	    
	    while (rs.next()) {
	    	 TimeEntry model = new TimeEntry();
	    	 model.setTimeEntryId(rs.getInt("timeEntryId"));
	    	 model.setEmpId(rs.getInt("empId"));
	    	 model.setEmpName(rs.getString("name"));
	    	 model.setShiftScheduleDesc(rs.getString("description"));
	    	 model.setShiftType(rs.getString("shiftType"));	    
	    	 model.setTimeIn(rs.getString("timeIn"));
	    	 model.setTimeOut(rs.getString("timeOut"));
	    	 model.setShiftScheduleId(rs.getInt("shiftId"));
	    	 model.setShiftId(rs.getInt("shiftId"));
	    	
	    	 list.add(model);

	    }	    
	    ps.close();
	    rs.close();      
	    return list;     

	}


    public boolean isEmployeePresentInTheHoliday(int empId, String holidayDate) throws SQLException, Exception {    	
    	
    	StringBuffer sql = new StringBuffer();
    	
    	sql.append("SELECT * FROM empTimeEntry WHERE empId = ");
    	sql.append(empId);
    	sql.append(" AND convert(varchar(26),timeIn,23) = '");
    	sql.append(holidayDate);
    	sql.append("'");
    	
    	
    	
		PreparedStatement ps = conn.prepareStatement(sql.toString());

	    ResultSet rs = ps.executeQuery();
	    
	    
	    if (rs.next()) {
	    	ps.close();
		    rs.close();
	    	return true;
	    }	    
	    ps.close();
	    rs.close();      
	    return false;     

	}
    
    public  ShiftingSchedule getShiftingScheduleByEmpIdAndShiftTimeIn(int empId, Date shiftTimeIn ) throws SQLException, Exception {
    	
		String sql = "SELECT es.scheduleDate, es.shiftingScheduleId, sc.shiftType, sc.timeIn, sc.timeOut, sc.description FROM  shiftingSchedule sc, empSchedule es WHERE es.shiftingScheduleId = sc.shiftingScheduleId  AND es.empId = ?   AND convert(date, es.scheduleDate, 101)  = ?";		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1, empId);
		
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/YYYY");
		String empLoginDate = df.format(shiftTimeIn);
		ps.setString(2, empLoginDate);
		
		

	    ResultSet rs = ps.executeQuery();
	   
	    ShiftingSchedule model = null;
	    
	    if (rs.next()) {
	    	model = new ShiftingSchedule();
	    	 model.setShiftingScheduleId(rs.getInt("shiftingScheduleId"));
	    	 model.setShiftType(rs.getString("shiftType"));	    
	    	 model.setTimeIn(util.sqlTimeToString(rs.getTime("timeIn")));
	    	 model.setTimeOut(util.sqlTimeToString(rs.getTime("timeOut")));
	    	 model.setDescription(rs.getString("description"));	    
	    	 model.setTimeInTimeStamp(rs.getTimestamp("timeIn"));
	    	 model.settImeOutTimestamp(rs.getTimestamp("timeOut"));
	    	 model.setScheduleDate(rs.getDate("scheduleDate"));
	    }	    
	    ps.close();
	    rs.close();      
	    return model;     

	}
    
    public  void add(ShiftingSchedule shiftingSchedule) throws SQLException, Exception {
  		String sql = "INSERT INTO shiftingSchedule (shiftType,timeIn,timeOut, description) VALUES (?,?,?,?)";
  		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
  		int index = 1;
  		
        ps.setString(index++, shiftingSchedule.getShiftType());
        ps.setTime(index++, util.convertToSqlTime(shiftingSchedule.getTimeIn()));
        ps.setTime(index++, util.convertToSqlTime(shiftingSchedule.getTimeOut()));
        ps.setString(index++, shiftingSchedule.getDescription());

        ps.executeUpdate();
          
        ResultSet keyResultSet = ps.getGeneratedKeys();
         int generatedAutoIncrementId = 0;
         if (keyResultSet.next()) {
          	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
          	shiftingSchedule.setShiftingScheduleId(generatedAutoIncrementId);
          	conn.commit();
          }
        ps.close();
          keyResultSet.close();

  	}
    
    public  void update(ShiftingSchedule shiftingSchedule) throws SQLException, Exception {
    	
    	
  		String sql = "UPDATE  shiftingSchedule set shiftType = ? ,timeIn = ?,timeOut = ? , description = ? where shiftingScheduleId =?";
  		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
  		int index = 1;
  		
        ps.setString(index++, shiftingSchedule.getShiftType());
        ps.setTime(index++, util.convertToSqlTime(shiftingSchedule.getTimeIn()));
        ps.setTime(index++, util.convertToSqlTime(shiftingSchedule.getTimeOut()));
        ps.setString(index++, shiftingSchedule.getDescription());
        ps.setInt(index++, shiftingSchedule.getShiftingScheduleId());
        
        ps.executeUpdate();
          
        ResultSet keyResultSet = ps.getGeneratedKeys();
         int generatedAutoIncrementId = 0;
         if (keyResultSet.next()) {
          	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
          	shiftingSchedule.setShiftingScheduleId(generatedAutoIncrementId);
          	conn.commit();
          }
        ps.close();
          keyResultSet.close();

  	}
    
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}
    
	public TimeEntry getTimeEntryByShiftTimeInDateAndEmpId(int empId, String shiftTimeInStr, String shiftTimeOutStr) throws SQLException, Exception {	
		
		String sql = "select * from empTimeEntry where empId = " + empId + " and timein " + " between '" + shiftTimeInStr + "' and  '" + shiftTimeOutStr +"'";
		PreparedStatement ps = conn.prepareStatement(sql);				

		ResultSet rs = ps.executeQuery();
		TimeEntry timeEntry = new TimeEntry();
		if (rs.next()) {			
			timeEntry.setTimeEntryId(rs.getInt("timeEntryId"));
			timeEntry.setTimeInTS(rs.getTimestamp("timeIn"));
			timeEntry.setTimeOutTS(rs.getTimestamp("timeOut"));				
		}

		ps.close();
		rs.close();
		return timeEntry;

	}
	
//	public void resolveTimeEntryUsingScheduledTime(Resolution resolution) throws SQLException, Exception {
//		if("S".equals(resolution.getResolutionType())){
//			//get schedule time in and out from schedule table based on date and empId
//			String sqlSelect = "SELECT timeIn, timeOut FROM shiftingSchedule WHERE shiftingScheduleId = " + resolution.getShiftScheduleId();
//			PreparedStatement psSelect = conn.prepareStatement(sqlSelect);				
//
//			ResultSet rsSelect = psSelect.executeQuery();
//			
//			if (rsSelect.next()) {	
//				if("timeIn".equals(resolution.getResolutionCategory())){
//					resolution.setTimeEntryAssigned(resolution.getClockDate() + " " + rsSelect.getString("timeIn").substring(0, 8));
//				} else {
//					resolution.setTimeEntryAssigned(resolution.getClockDate() + " " + rsSelect.getString("timeOut").substring(0, 8));
//				}
//							
//			}
//			
//			
//			//timeIn
//			if("timeIn".equals(resolution.getResolutionCategory())){
//				StringBuffer sql = new StringBuffer();
//				
//				sql.append("INSERT INTO empTimeEntry (shiftId, timeIn, empId, inputMethodTimeIn, resolutionRemarks, resolvedBy) VALUES (");
//				sql.append(resolution.getShiftScheduleId());
//				sql.append(",'");
//				sql.append(resolution.getTimeEntryAssigned());
//				sql.append("',");
//				sql.append(resolution.getEmpId());
//				sql.append(",9999,'");
//				sql.append(resolution.getResolutionRemarks());
//				sql.append("',");
//				sql.append(resolution.getResolvedBy());
//				sql.append(")");
//				
//				System.out.print("INSERT SQL: " + sql.toString());
//		  		PreparedStatement ps  = conn.prepareStatement(sql.toString());
//		  		
//		        
//		        ps.executeUpdate();		          
//		        
//		        conn.commit();
//		        
//		        ps.close();
//		        
//			} else {
//				
//				//Error need to get timeEntryId from UI if TimeOut
//				StringBuffer sql = new StringBuffer();
//				
//				sql.append("UPDATE  empTimeEntry set timeOut = '");
//				sql.append(resolution.getTimeEntryAssigned());
//				sql.append("', inputMethodTimeOut = 9999, resolutionRemarks = '");
//				sql.append(resolution.getResolutionRemarks());
//				sql.append("', resolvedBy = ");
//				sql.append(resolution.getResolvedBy());
//				sql.append(" WHERE timeEntryId = ");
//				sql.append(resolution.getTimeEntryId());
//				
//				
//				System.out.print("UPDATE SQL: " + sql.toString());
//		  		PreparedStatement ps  = conn.prepareStatement(sql.toString());
//		  		
//		        
//		        ps.executeUpdate();		          
//		       
//		        conn.commit();
//		         
//		        ps.close();
//		         
//			}
//		}
//		
//		
//	}
	
	public  List<TimeEntryDispute> getAllTimeEntryDisputeHR() throws SQLException, Exception {
		StringBuffer sql = new StringBuffer();
    	
    	sql.append("SELECT ee.timeEntryDisputeId, ee.approvalStatus, ee.shiftId, ee.timeEntryId, e.empId, (e.lastname + ',' + e.firstname) as name, ee.timeIn, ee.timeOut, ss.description, ss.shiftType ");
    	sql.append("FROM empTimeEntryDispute ee, employee e, shiftingSchedule ss ");
    	sql.append("WHERE ee.empId = e.empId AND ee.shiftId = ss.shiftingScheduleId AND ee.approvalStatus <> 'HA' ");    	
    	sql.append(" ORDER BY ee.timeIn");
    	
    	
		PreparedStatement ps = conn.prepareStatement(sql.toString());

	    ResultSet rs = ps.executeQuery();
	    List<TimeEntryDispute> list = new ArrayList<TimeEntryDispute>();
	    
	    while (rs.next()) {
	    	TimeEntryDispute model = new TimeEntryDispute();
	    	 model.setTimeEntryId(rs.getInt("timeEntryId"));
	    	 model.setEmpId(rs.getInt("empId"));
	    	 model.setEmpName(rs.getString("name"));
	    	 model.setShiftScheduleDesc(rs.getString("description"));
	    	 model.setShiftType(rs.getString("shiftType"));	    
	    	 model.setTimeIn(rs.getString("timeIn"));
	    	 model.setTimeOut(rs.getString("timeOut"));
	    	 model.setShiftScheduleId(rs.getInt("shiftId"));
	    	 model.setShiftId(rs.getInt("shiftId"));
	    	 model.setScheduleDate(model.getTimeIn().substring(0, 10));
	    	 
	    	 model.setTimeEntryDisputeId(rs.getInt("timeEntryDisputeId"));
	    	 model.setApprovalStatus(rs.getString("approvalStatus"));
	    	 
	    	 list.add(model);

	    }	    
	    ps.close();
	    rs.close();      
	    return list;
	}
	
	
	public  List<TimeEntryDispute> getAllTimeEntryDisputeBySupervisorId(int supervisorId) throws SQLException, Exception {
		StringBuffer sql = new StringBuffer();
    	
    	sql.append("SELECT ee.timeEntryDisputeId, ee.approvalStatus, ee.shiftId, ee.timeEntryId, e.empId, (e.lastname + ',' + e.firstname) as name, ee.timeIn, ee.timeOut, ss.description, ss.shiftType ");
    	sql.append("FROM empTimeEntryDispute ee, employee e, shiftingSchedule ss ");
    	sql.append("WHERE ee.empId = e.empId AND ee.shiftId = ss.shiftingScheduleId AND ee.resolvedBy = ");
    	sql.append(supervisorId);    	
    	sql.append(" ORDER BY ee.timeIn");
    	
    	
		PreparedStatement ps = conn.prepareStatement(sql.toString());

	    ResultSet rs = ps.executeQuery();
	    List<TimeEntryDispute> list = new ArrayList<TimeEntryDispute>();
	    
	    while (rs.next()) {
	    	TimeEntryDispute model = new TimeEntryDispute();
	    	 model.setTimeEntryId(rs.getInt("timeEntryId"));
	    	 model.setEmpId(rs.getInt("empId"));
	    	 model.setEmpName(rs.getString("name"));
	    	 model.setShiftScheduleDesc(rs.getString("description"));
	    	 model.setShiftType(rs.getString("shiftType"));	    
	    	 model.setTimeIn(rs.getString("timeIn"));
	    	 model.setTimeOut(rs.getString("timeOut"));
	    	 model.setShiftScheduleId(rs.getInt("shiftId"));
	    	 model.setShiftId(rs.getInt("shiftId"));
	    	 model.setScheduleDate(model.getTimeIn().substring(0, 10));
	    	 
	    	 model.setTimeEntryDisputeId(rs.getInt("timeEntryDisputeId"));
	    	 model.setApprovalStatus(rs.getString("approvalStatus"));
	    	 
	    	 list.add(model);

	    }	    
	    ps.close();
	    rs.close();      
	    return list;
	}
	
	public  List<TimeEntryDispute> getAllTimeEntryDisputeBySupervisorIdAndClockDate(int supervisorId, String clockDate) throws SQLException, Exception {
		StringBuffer sql = new StringBuffer();
    	
    	sql.append("SELECT ee.timeEntryDisputeId, ee.approvalStatus, ee.shiftId, ee.timeEntryId, e.empId, (e.lastname + ',' + e.firstname) as name, ee.timeIn, ee.timeOut, ss.description, ss.shiftType ");
    	sql.append("FROM empTimeEntryDispute ee, employee e, shiftingSchedule ss ");
    	sql.append("WHERE ee.empId = e.empId AND ee.shiftId = ss.shiftingScheduleId AND ee.resolvedBy = ");
    	sql.append(supervisorId);
    	sql.append(" AND convert(varchar(26),ee.timeIn,23) = '");
    	sql.append(clockDate);
    	sql.append("' ORDER BY ee.timeIn");
    	
    	
		PreparedStatement ps = conn.prepareStatement(sql.toString());

	    ResultSet rs = ps.executeQuery();
	    List<TimeEntryDispute> list = new ArrayList<TimeEntryDispute>();
	    
	    while (rs.next()) {
	    	TimeEntryDispute model = new TimeEntryDispute();
	    	 model.setTimeEntryId(rs.getInt("timeEntryId"));
	    	 model.setEmpId(rs.getInt("empId"));
	    	 model.setEmpName(rs.getString("name"));
	    	 model.setShiftScheduleDesc(rs.getString("description"));
	    	 model.setShiftType(rs.getString("shiftType"));	    
	    	 model.setTimeIn(rs.getString("timeIn"));
	    	 model.setTimeOut(rs.getString("timeOut"));
	    	 model.setShiftScheduleId(rs.getInt("shiftId"));
	    	 model.setShiftId(rs.getInt("shiftId"));
	    	 model.setScheduleDate(model.getTimeIn().substring(0, 10));
	    	 
	    	 model.setTimeEntryDisputeId(rs.getInt("timeEntryDisputeId"));
	    	 model.setApprovalStatus(rs.getString("approvalStatus"));
	    	 
	    	 list.add(model);

	    }	    
	    ps.close();
	    rs.close();      
	    return list;
	}
	
	public  List<TimeEntryDispute> getAllTimeEntryDisputeByEmpId(int empId) throws SQLException, Exception {
		StringBuffer sql = new StringBuffer();
    	
    	sql.append("SELECT ee.timeEntryDisputeId, ee.approvalStatus, ee.shiftId, ee.timeEntryId, e.empId, (e.lastname + ',' + e.firstname) as name, ee.timeIn, ee.timeOut, ss.description, ss.shiftType ");
    	sql.append("FROM empTimeEntryDispute ee, employee e, shiftingSchedule ss ");
    	sql.append("WHERE ee.empId = e.empId AND ee.shiftId = ss.shiftingScheduleId AND ee.empId = ");
    	sql.append(empId);
    	sql.append(" ORDER BY ee.timeIn");
    	
    	
		PreparedStatement ps = conn.prepareStatement(sql.toString());

	    ResultSet rs = ps.executeQuery();
	    List<TimeEntryDispute> list = new ArrayList<TimeEntryDispute>();
	    
	    while (rs.next()) {
	    	TimeEntryDispute model = new TimeEntryDispute();
	    	 model.setTimeEntryId(rs.getInt("timeEntryId"));
	    	 model.setEmpId(rs.getInt("empId"));
	    	 model.setEmpName(rs.getString("name"));
	    	 model.setShiftScheduleDesc(rs.getString("description"));
	    	 model.setShiftType(rs.getString("shiftType"));	    
	    	 model.setTimeIn(rs.getString("timeIn"));
	    	 model.setTimeOut(rs.getString("timeOut"));
	    	 model.setShiftScheduleId(rs.getInt("shiftId"));
	    	 model.setShiftId(rs.getInt("shiftId"));
	    	 model.setScheduleDate(model.getTimeIn().substring(0, 10));
	    	 
	    	 model.setTimeEntryDisputeId(rs.getInt("timeEntryDisputeId"));
	    	 model.setApprovalStatus(rs.getString("approvalStatus"));
	    	 
	    	 list.add(model);

	    }	    
	    ps.close();
	    rs.close();      
	    return list;   
	}
	
	//TODO
	/**
	 * P - PENDING STATUS
	 * SA - SUPERVISOR APPROVED
	 * HA - HR APPROVED
	 */
	public void updateTimeEntryDispute(String newStatus, int timeEntryDisputeId) throws SQLException, Exception {
		StringBuffer sql = new StringBuffer();
		
		sql.append("UPDATE  empTimeEntryDispute set approvalStatus = '");
		sql.append(newStatus);		
		sql.append("' WHERE timeEntryDisputeId = ");
		sql.append(timeEntryDisputeId);
		
		
		System.out.print("UPDATE SQL: " + sql.toString());
  		PreparedStatement ps  = conn.prepareStatement(sql.toString());
  		
  		if(newStatus.equals("HA")){
  			TimeEntryDispute timeEntryDispute = getTimeEntryDispute(timeEntryDisputeId);
  			
  			Resolution resolution = new Resolution();
  			
  			resolution.setTimeEntryId(timeEntryDispute.getTimeEntryId());
  			resolution.setShiftScheduleId(timeEntryDispute.getShiftScheduleId());
  			resolution.setTimeInHrs(timeEntryDispute.getTimeIn());
  			resolution.setTimeOutHrs(timeEntryDispute.getTimeOut());
  			resolution.setEmpId(timeEntryDispute.getEmpId());
  			resolution.setResolutionRemarks(timeEntryDispute.getResolutionRemarks());
  			resolution.setResolvedBy(timeEntryDispute.getResolvedBy());
  			
  			
  			
  			resolveTimeEntryUsingAssignedTimeFromDispute(resolution);
  		}
  		
        
        ps.executeUpdate();		          
       
        conn.commit();
         
        ps.close();
	}
	
	private TimeEntryDispute getTimeEntryDispute(int timeEntryDisputeId)  throws SQLException, Exception {
		StringBuffer sql = new StringBuffer();
    	
    	sql.append("SELECT * ");
    	sql.append("FROM empTimeEntryDispute ");
    	sql.append("WHERE timeEntryDisputeId = ");
    	sql.append(timeEntryDisputeId);    	
    	
		PreparedStatement ps = conn.prepareStatement(sql.toString());

	    ResultSet rs = ps.executeQuery();
	    List<TimeEntryDispute> list = new ArrayList<TimeEntryDispute>();
	    TimeEntryDispute model = new TimeEntryDispute();
	    
	    if (rs.next()) {
	    	
	    	 model.setTimeEntryId(rs.getInt("timeEntryId"));
	    	 model.setEmpId(rs.getInt("empId"));
	    	 
	    	 //model.setShiftScheduleDesc(rs.getString("description"));
	    	 //model.setShiftType(rs.getString("shiftType"));	    
	    	 model.setTimeIn(rs.getString("timeIn"));
	    	 model.setTimeOut(rs.getString("timeOut"));
	    	 model.setShiftScheduleId(rs.getInt("shiftId"));
	    	 model.setShiftId(rs.getInt("shiftId"));
	    	 model.setResolutionRemarks(rs.getString("resolutionRemarks"));
	    	 model.setResolvedBy(rs.getInt("resolvedBy"));
	    	 model.setTimeEntryDisputeId(rs.getInt("timeEntryDisputeId"));
	    	 model.setApprovalStatus(rs.getString("approvalStatus"));
	    	 model.setScheduleDate(model.getTimeIn().substring(0, 10));
	    	 
	    	 list.add(model);

	    }	    
	    ps.close();
	    rs.close();      
	    return model; 
	}
	
	public void disputeTimeEntryBySupervisor(Resolution resolution) throws SQLException, Exception {
		resolution.setTimeInHrs(resolution.getClockDate() + " " + resolution.getTimeInHrs());
		resolution.setTimeOutHrs(resolution.getClockDate() + " " + resolution.getTimeOutHrs());
		
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("INSERT INTO empTimeEntryDispute (timeEntryId, shiftId, timeIn, timeOut, empId, approvalStatus, resolutionRemarks, resolvedBy) VALUES (");
		sql.append(resolution.getTimeEntryId());
		sql.append(",");
		sql.append(resolution.getShiftScheduleId());
		sql.append(",'");
		sql.append(resolution.getTimeInHrs());
		sql.append("','");
		sql.append(resolution.getTimeOutHrs());
		sql.append("',");
		sql.append(resolution.getEmpId());
		sql.append(",'SA','");
		sql.append(resolution.getResolutionRemarks());
		sql.append("',");
		sql.append(resolution.getResolvedBy());
		sql.append(")");
		
		System.out.print("INSERT SQL: " + sql.toString());
  		PreparedStatement ps  = conn.prepareStatement(sql.toString());
  		
        
        ps.executeUpdate();		          
        
        conn.commit();
        
        ps.close();
	}
	
	public void disputeTimeEntry(Resolution resolution) throws SQLException, Exception {
		resolution.setTimeInHrs(resolution.getClockDate() + " " + resolution.getTimeInHrs());
		resolution.setTimeOutHrs(resolution.getClockDate() + " " + resolution.getTimeOutHrs());
		
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("INSERT INTO empTimeEntryDispute (timeEntryId, shiftId, timeIn, timeOut, empId, approvalStatus, resolutionRemarks, resolvedBy) VALUES (");
		sql.append(resolution.getTimeEntryId());
		sql.append(",");
		sql.append(resolution.getShiftScheduleId());
		sql.append(",'");
		sql.append(resolution.getTimeInHrs());
		sql.append("','");
		sql.append(resolution.getTimeOutHrs());
		sql.append("',");
		sql.append(resolution.getEmpId());
		sql.append(",'P','");
		sql.append(resolution.getResolutionRemarks());
		sql.append("',");
		sql.append(resolution.getResolvedBy());
		sql.append(")");
		
		System.out.print("INSERT SQL: " + sql.toString());
  		PreparedStatement ps  = conn.prepareStatement(sql.toString());
  		
        
        ps.executeUpdate();		          
        
        conn.commit();
        
        ps.close();
	}
	
	public void resolveTimeEntryUsingAssignedTime(Resolution resolution) throws SQLException, Exception {
//		if("L".equals(resolution.getResolutionType())){
			//combine getTimeEntryAssigned to scheduled date
			resolution.setTimeInHrs(resolution.getClockDate() + " " + resolution.getTimeInHrs());
			resolution.setTimeOutHrs(resolution.getClockDate() + " " + resolution.getTimeOutHrs());
			
			
			//timeIn
			if(resolution.getTimeEntryId() == 0){
				StringBuffer sql = new StringBuffer();
				
				sql.append("INSERT INTO empTimeEntry (shiftId, timeIn, timeOut, empId, inputMethodTimeIn, resolutionRemarks, resolvedBy) VALUES (");
				sql.append(resolution.getShiftScheduleId());
				sql.append(",'");
				sql.append(resolution.getTimeInHrs());
				sql.append("','");
				sql.append(resolution.getTimeOutHrs());
				sql.append("',");
				sql.append(resolution.getEmpId());
				sql.append(",9999,'");
				sql.append(resolution.getResolutionRemarks());
				sql.append("',");
				sql.append(resolution.getResolvedBy());
				sql.append(")");
				
				System.out.print("INSERT SQL: " + sql.toString());
		  		PreparedStatement ps  = conn.prepareStatement(sql.toString());
		  		
		        
		        ps.executeUpdate();		          
		        
		        conn.commit();
		        
		        ps.close();
		        
			} else {
				
				//Error need to get timeEntryId from UI if TimeOut
				StringBuffer sql = new StringBuffer();
				
				sql.append("UPDATE  empTimeEntry set timeIn = '");
				sql.append(resolution.getTimeInHrs());
				sql.append("', timeOut = '");
				sql.append(resolution.getTimeOutHrs());
				sql.append("', inputMethodTimeOut = 9999, resolutionRemarks = '");
				sql.append(resolution.getResolutionRemarks());
				sql.append("', resolvedBy = ");
				sql.append(resolution.getResolvedBy());
				sql.append(" WHERE timeEntryId = ");
				sql.append(resolution.getTimeEntryId());
				
				
				System.out.print("UPDATE SQL: " + sql.toString());
		  		PreparedStatement ps  = conn.prepareStatement(sql.toString());
		  		
		        
		        ps.executeUpdate();		          
		       
		        conn.commit();
		         
		        ps.close();
		         
			}
//		}
	}
	
	private void resolveTimeEntryUsingAssignedTimeFromDispute(Resolution resolution) throws SQLException, Exception {

			
			
			//timeIn
			if(resolution.getTimeEntryId() == 0){
				StringBuffer sql = new StringBuffer();
				
				sql.append("INSERT INTO empTimeEntry (shiftId, timeIn, timeOut, empId, inputMethodTimeIn, resolutionRemarks, resolvedBy) VALUES (");
				sql.append(resolution.getShiftScheduleId());
				sql.append(",'");
				sql.append(resolution.getTimeInHrs());
				sql.append("','");
				sql.append(resolution.getTimeOutHrs());
				sql.append("',");
				sql.append(resolution.getEmpId());
				sql.append(",9999,'");
				sql.append(resolution.getResolutionRemarks());
				sql.append("',");
				sql.append(resolution.getResolvedBy());
				sql.append(")");
				
				System.out.print("INSERT SQL: " + sql.toString());
		  		PreparedStatement ps  = conn.prepareStatement(sql.toString());
		  		
		        
		        ps.executeUpdate();		          
		        
		        conn.commit();
		        
		        ps.close();
		        
			} else {
				
				//Error need to get timeEntryId from UI if TimeOut
				StringBuffer sql = new StringBuffer();
				
				sql.append("UPDATE  empTimeEntry set timeIn = '");
				sql.append(resolution.getTimeInHrs());
				sql.append("', timeOut = '");
				sql.append(resolution.getTimeOutHrs());
				sql.append("', inputMethodTimeOut = 9999, resolutionRemarks = '");
				sql.append(resolution.getResolutionRemarks());
				sql.append("', resolvedBy = ");
				sql.append(resolution.getResolvedBy());
				sql.append(" WHERE timeEntryId = ");
				sql.append(resolution.getTimeEntryId());
				
				
				System.out.print("UPDATE SQL: " + sql.toString());
		  		PreparedStatement ps  = conn.prepareStatement(sql.toString());
		  		
		        
		        ps.executeUpdate();		          
		       
		        conn.commit();
		         
		        ps.close();
		         
			}

	}

	

}
