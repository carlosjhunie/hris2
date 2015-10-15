package dai.hris.dao.filemanager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import dai.hris.dao.DBConstants;
import dai.hris.model.EmployeeTimeEntry;

public class EmployeeTimeEntryDAO {	
	Connection conn = null;
	
	public EmployeeTimeEntryDAO() {

		try {

			/* MS SQL CODE */
			Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
			conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);
			conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("EmployeeTimeEntryDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("EmployeeTimeEntryDAO :" + e.getMessage());
		}
	}

	
	/**
	 * get all employees from employee table
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 * 
	 * 
	 * TODO if employee is regular shift, this will work
	 * if shifting, need to change the logic
	 */
	
	public EmployeeTimeEntry getEmployeeTimeEntryForCurrentDayByEmpNo(int empNo) throws SQLException, Exception {

	  String sql = "SELECT dailyTimeEntryId, empNo, empLoginTS, empLoginProtocol ,empLogoutTS, empLogoutProtocol, createTS," +
			  		"createdBy ,lastUpdatedTS, lastUpdatedBy, comments FROM dailytimetentry " +
			  		"WHERE CAST(empLoginTs AS DATE) = CAST(CURRENT_TIMESTAMP AS DATE) and empNo = ?";
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, empNo);

		ResultSet rs = ps.executeQuery();
		EmployeeTimeEntry empTimeEntry = null;
		while (rs.next()) {
			empTimeEntry = new EmployeeTimeEntry();
			empTimeEntry.setComments(rs.getString("comments"));
			empTimeEntry.setCreatedBy(rs.getString("createdBy"));
			empTimeEntry.setCreateTS(rs.getTimestamp("createTS"));
			empTimeEntry.setDailyTimeEntryId(rs.getLong("dailyTimeEntryId"));
			empTimeEntry.setEmpLoginProtocol(rs.getString("empLoginProtocol"));
			empTimeEntry.setEmpLoginTS(rs.getTimestamp("empLoginTS"));
			empTimeEntry.setEmpLogoutProtocol(rs.getString("empLogoutProtocol"));
			empTimeEntry.setEmpLogoutTS(rs.getTimestamp("emplogoutTS"));
			empTimeEntry.setEmpNo(rs.getInt("empNo"));
			empTimeEntry.setLastUpdatedBy(rs.getString("lastUpdatedBy"));
			empTimeEntry.setLastUpdatedTS(rs.getTimestamp("lastUpdatedTS"));
		}

		ps.close();
		rs.close();
		return empTimeEntry;

	}
	
	public EmployeeTimeEntry getEmployeeTimeEntryByShiftTimeInAndEmpId(int empId, java.util.Date shiftTimeIn) throws SQLException, Exception {
		
		
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD");
		String empLoginDate = df.format(shiftTimeIn);
		
		String sql = "select * from dailytimetentry where empId = ? and to_char(empLoginDate, 'YYYY-MM-DD') = ?";
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, empId);
		ps.setString(2, empLoginDate);
		

			ResultSet rs = ps.executeQuery();
			EmployeeTimeEntry empTimeEntry = null;
			if (rs.next()) {
				empTimeEntry = new EmployeeTimeEntry();
				empTimeEntry.setDailyTimeEntryId(rs.getLong("dailyTimeEntryId"));
				
			}

			ps.close();
			rs.close();
			return empTimeEntry;

		}
		
	
	
	
	/**
	 * THis is used FOR POLLING
	 * get topmost row based on last_updt_ts (either for login or logout)
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public EmployeeTimeEntry getMostRecentEmployeeTimeEntry() throws SQLException, Exception {

	  String sql = "SELECT TOP 1 " +
					"dte.dailyTimeEntryId, dte.empNo, dte.empLoginTS, dte.empLoginProtocol ,dte.empLogoutTS, " +
					"dte.empLogoutProtocol, dte.createTS, dte.createdBy, dte.lastUpdatedTS, dte.lastUpdatedBy, dte.comments, " +
					"emp.firstname, emp.lastname, emp.deptId " +
					"FROM dailytimetentry dte " +
					"INNER JOIN " +
					    "(SELECT empNo, MAX(lastUpdatedTS) AS maxDateTime " +
					    "FROM dailytimetentry " +
					    "GROUP BY empNo) grouped_dte " +
					"ON dte.empNo = grouped_dte.empNo " +
					"INNER JOIN employee emp " +
						"ON emp.empNo = dte.empNo " +
					"AND dte.lastUpdatedTS = grouped_dte.maxDateTime ";
		PreparedStatement ps = conn.prepareStatement(sql.toString());


		ResultSet rs = ps.executeQuery();
		EmployeeTimeEntry empTimeEntry = null;
		while (rs.next()) {
			empTimeEntry = new EmployeeTimeEntry();
			empTimeEntry.setComments(rs.getString("comments"));
			empTimeEntry.setCreatedBy(rs.getString("createdBy"));
			empTimeEntry.setCreateTS(rs.getTimestamp("createTS"));
			empTimeEntry.setDailyTimeEntryId(rs.getLong("dailyTimeEntryId"));
			empTimeEntry.setEmpLoginProtocol(rs.getString("empLoginProtocol"));
			empTimeEntry.setEmpLoginTS(rs.getTimestamp("empLoginTS"));
			empTimeEntry.setEmpLogoutProtocol(rs.getString("empLogoutProtocol"));
			empTimeEntry.setEmpLogoutTS(rs.getTimestamp("emplogoutTS"));
			empTimeEntry.setEmpNo(rs.getInt("empNo"));
			empTimeEntry.setLastUpdatedBy(rs.getString("lastUpdatedBy"));
			empTimeEntry.setLastUpdatedTS(rs.getTimestamp("lastUpdatedTS"));
			//below are fields used for login/logout confirmation display
			empTimeEntry.setFirstname(rs.getString("firstname"));
			empTimeEntry.setLastname(rs.getString("lastname"));
			empTimeEntry.setDeptId(rs.getInt("deptId"));
			
		}

		ps.close();
		rs.close();
		return empTimeEntry;

	}
	
	

	public void closeConnection() throws SQLException, Exception {
		conn.close();
	}
}
