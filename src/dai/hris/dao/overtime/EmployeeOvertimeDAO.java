package dai.hris.dao.overtime;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import dai.hris.dao.DBConstants;
import dai.hris.model.EmployeeOutOfOffice;
import dai.hris.model.EmployeeOvertime;
import dai.hris.model.Leave;
import dai.hris.model.PayrollPeriod;

public class EmployeeOvertimeDAO {
	Connection conn = null;
	
	public EmployeeOvertimeDAO() {
		
    	try {

    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("EmployeeOvertimeDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("EmployeeOvertimeDAO :" + e.getMessage());
		}
	}
	
/**
 * TG
 * @param empOTId
 * @return
 * @throws SQLException
 * @throws Exception
 */
	public EmployeeOvertime getEmployeeOutOfOfficeByEmpOvertimeId(int empOTId) throws SQLException, Exception {			    		
		String sql = "SELECT * FROM empOvertime where empOvertimeId = ?";
		EmployeeOvertime employeeOvertime = null;
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, empOTId);
		ResultSet rs = ps.executeQuery();	    
	    while (rs.next()) {
	    	employeeOvertime = new EmployeeOvertime();
	    	employeeOvertime.setEmpOvertimeId(rs.getInt("empOvertimeId"));
	    	employeeOvertime.setEmpId(rs.getInt("empId"));
	    	employeeOvertime.setDateRendered(rs.getString("dateRendered"));
	    	employeeOvertime.setNoOfHours(rs.getInt("noOfHours"));
	    	employeeOvertime.setStatus(rs.getInt("status"));
	    	employeeOvertime.setRemarks(rs.getString("remarks"));
	    	employeeOvertime.setApprovedBy(rs.getInt("approvedBy"));
	    	employeeOvertime.setSecondaryApprover(rs.getInt("secondaryApprover"));
	    	employeeOvertime.setCreatedBy(rs.getInt("createdBy"));
	    	employeeOvertime.setCreatedDate(rs.getDate("createdDate"));
	    }
	    
	    ps.close();
	    rs.close();      
	    return employeeOvertime;
	}
	

/**
 * TG
 * @param empId
 * @return
 * @throws SQLException
 * @throws Exception
 */
	public ArrayList<EmployeeOvertime> getEmployeeOvertimeByEmpId(int empId) throws SQLException, Exception {			    		
		String sql = "SELECT * FROM empOvertime where empId = ?";
		EmployeeOvertime employeeOvertime = null;
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, empId);
	
	    ResultSet rs = ps.executeQuery();
	    ArrayList<EmployeeOvertime> list = new ArrayList<EmployeeOvertime>();
	    
	    while (rs.next()) {	    	
	    	employeeOvertime = new EmployeeOvertime();
	    	employeeOvertime.setEmpOvertimeId(rs.getInt("empOvertimeId"));
	    	employeeOvertime.setEmpId(rs.getInt("empId"));
	    	employeeOvertime.setDateRendered(rs.getString("dateRendered"));
	    	employeeOvertime.setNoOfHours(rs.getInt("noOfHours"));
	    	employeeOvertime.setStatus(rs.getInt("status"));
	    	employeeOvertime.setRemarks(rs.getString("remarks"));
	    	employeeOvertime.setApprovedBy(rs.getInt("approvedBy"));
	    	employeeOvertime.setSecondaryApprover(rs.getInt("secondaryApprover"));
	    	employeeOvertime.setCreatedBy(rs.getInt("createdBy"));
	    	employeeOvertime.setCreatedDate(rs.getDate("createdDate"));
	    	list.add(employeeOvertime);
	    }
	    
	    ps.close();
	    rs.close();
 	    list.sort(Comparator.comparing(EmployeeOvertime::getCreatedDate));
	    return list;		
	}
	    
/**
 * TG
 * @param employeeOvertime
 * @return
 * @throws SQLException
 * @throws Exception
 */
	public int add(EmployeeOvertime employeeOvertime) throws SQLException, Exception {
		String sql = "INSERT INTO empOvertime(empId, dateRendered, noOfHours, remarks, status, approvedBy, secondaryApprover, createdBy, createdDate) "
						+ "VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	    ps.setInt(1, employeeOvertime.getEmpId());
	    ps.setString(2, employeeOvertime.getDateRendered());
	    ps.setInt(3, employeeOvertime.getNoOfHours());
	    ps.setString(4, employeeOvertime.getRemarks());
	    ps.setInt(5,employeeOvertime.getStatus());
	    ps.setInt(6, employeeOvertime.getApprovedBy());
	    ps.setInt(7, employeeOvertime.getSecondaryApprover());
	    ps.setInt(8, employeeOvertime.getCreatedBy());
	    ps.setDate(9, employeeOvertime.getCreatedDate());
	    
	    ps.executeUpdate();
	    ResultSet keyResultSet = ps.getGeneratedKeys();
	     int generatedAutoIncrementId = 0;
	     if (keyResultSet.next()) {
	      	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
	      	employeeOvertime.setEmpOvertimeId(generatedAutoIncrementId);
	      	conn.commit();
	      }
		
	     ps.close();
	     keyResultSet.close();

	     return generatedAutoIncrementId;
	}

	    
	/*do not add delete method*/
	
	
/**\
 * TG
 * @param employeeOvertime
 * @return
 * @throws SQLException
 * @throws Exception
 */
    public int update(EmployeeOvertime employeeOvertime) throws SQLException, Exception {		
		String sql = "UPDATE empOvertime SET dateRendered=?, noOfHours=?, remarks=?, status=?, approvedBy=?, secondaryApprover=? "
						+ "WHERE empOvertimeId = ?";
		PreparedStatement ps  = conn.prepareStatement(sql);
	    ps.setString(1, employeeOvertime.getDateRendered());
	    ps.setInt(2, employeeOvertime.getNoOfHours());
	    ps.setString(3, employeeOvertime.getRemarks());
	    ps.setInt(4, employeeOvertime.getStatus());
	    ps.setInt(5, employeeOvertime.getApprovedBy());
	    ps.setInt(6, employeeOvertime.getSecondaryApprover());
	    ps.setInt(7, employeeOvertime.getEmpOvertimeId());

		int count = ps.executeUpdate();
		conn.commit();
		ps.close();
		
		return count;

 	}


    public ArrayList<EmployeeOvertime> getAllOvertimeForSvApprovalBySuperVisorId(int superVisorId) throws SQLException, Exception {
    	EmployeeOvertime employeeOvertime = null;
 		ArrayList<EmployeeOvertime> list = new ArrayList<EmployeeOvertime>();
 		
 		String sql="SELECT o.* FROM employee e, empOvertime o " + 
 						"WHERE e.empId = o.empId " +
						"AND (e.superVisor1Id = ? OR e.superVisor2Id = ? OR e.superVisor3Id= ?) " + 
						"AND (o.approvedBy = 0  AND o.secondaryApprover = 0)";
 		
 		PreparedStatement ps = conn.prepareStatement(sql.toString());
 		ps.setInt(1, superVisorId);
 		ps.setInt(2, superVisorId);
 		ps.setInt(3, superVisorId);
 		System.out.println(superVisorId);

 	    ResultSet rs = ps.executeQuery();
 	    
 	    while (rs.next()) {
 	    	employeeOvertime = new EmployeeOvertime();
 	    	employeeOvertime.setEmpOvertimeId(rs.getInt("empOvertimeId"));
 	    	employeeOvertime.setEmpId(rs.getInt("empId"));
 	    	employeeOvertime.setNoOfHours(rs.getInt("noOfHours"));
 	    	employeeOvertime.setDateRendered(rs.getString("dateRendered")); 
 	    	employeeOvertime.setRemarks(rs.getString("remarks"));
 	    	employeeOvertime.setApprovedBy(rs.getInt("approvedBy"));
 	    	employeeOvertime.setSecondaryApprover(rs.getInt("secondaryApprover"));
 	    	employeeOvertime.setStatus(rs.getInt("status"));
 	    	employeeOvertime.setCreatedBy(rs.getInt("createdBy"));
 	    	employeeOvertime.setCreatedDate(rs.getDate("createdDate"));
 	    	list.add(employeeOvertime);
 		}
 	    sql = null;
 	    ps.close();
 	    rs.close();	 
 	    list.sort(Comparator.comparing(EmployeeOvertime::getCreatedDate));
 	    return list;

 	} 
    
    
    
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}
}
