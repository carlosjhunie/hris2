package dai.hris.dao.filemanager.employeeoutofoffice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.DBConstants;
import dai.hris.model.EmployeeOutOfOffice;
import dai.hris.model.Leave;

public class EmployeeOutOfOfficeDAO {
	Connection conn = null;
	
	public EmployeeOutOfOfficeDAO() {
		
    	try {

    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("EmployeeOutOfOfficeDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("EmployeeOutOfOfficeDAO :" + e.getMessage());
		}
	}
	

	/**
	 * tested ok 062315 TG
	 * @param empOOOId
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public EmployeeOutOfOffice getEmployeeOutOfOfficeByEmpOOOId(int empOOOId) throws SQLException, Exception {			    		
		String sql = "SELECT * FROM empOutOfOffice where empOOOId = ?";
		EmployeeOutOfOffice employeeOutOfOffice = null;
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, empOOOId);
		
	    ResultSet rs = ps.executeQuery();
	    
	    while (rs.next()) {
	    	employeeOutOfOffice = new EmployeeOutOfOffice();
	    	employeeOutOfOffice.setEmpOOOId(rs.getInt("empOOOId"));
	    	employeeOutOfOffice.setEmpId(rs.getInt("empId"));
	    	employeeOutOfOffice.setDateFrom(rs.getString("dateFrom"));
	    	employeeOutOfOffice.setDateTo(rs.getString("dateTo"));	    	
	    	employeeOutOfOffice.setProvider(rs.getString("provider"));
	    	employeeOutOfOffice.setRemarks(rs.getString("remarks"));
	    	employeeOutOfOffice.setNoOfHours(rs.getInt("noOfHours"));
	    	employeeOutOfOffice.setTitleActivity(rs.getString("titleActivity"));	    	
	    	employeeOutOfOffice.setStatus(rs.getInt("status"));
	    	employeeOutOfOffice.setApprovedBy(rs.getInt("approvedBy"));
	    	employeeOutOfOffice.setSecondaryApprover(rs.getInt("secondaryApprover"));
	    	employeeOutOfOffice.setCreatedBy(rs.getInt("createdBy"));
	    	employeeOutOfOffice.setCreatedDate(rs.getDate("createdDate"));
	    }
	    
	    ps.close();
	    rs.close();      
	    return employeeOutOfOffice;		
	}
	
	/**
	 * tested ok 062315 TG
	 * @param empId
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public ArrayList<EmployeeOutOfOffice> getEmployeeOutOfOfficeListByEmpId(int empId) throws SQLException, Exception {			    		
		String sql = "SELECT * FROM empOutOfOffice where empId = ?";
		EmployeeOutOfOffice employeeOutOfOffice = null;
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, empId);
	
	    ResultSet rs = ps.executeQuery();
	    ArrayList<EmployeeOutOfOffice> list = new ArrayList<EmployeeOutOfOffice>();
	    
	    while (rs.next()) {	    	
	    	employeeOutOfOffice = new EmployeeOutOfOffice();
	    	employeeOutOfOffice.setEmpOOOId(rs.getInt("empOOOId"));
	    	employeeOutOfOffice.setEmpId(rs.getInt("empId"));
	    	employeeOutOfOffice.setDateFrom(rs.getString("dateFrom"));
	    	employeeOutOfOffice.setDateTo(rs.getString("dateTo"));	    	
	    	employeeOutOfOffice.setProvider(rs.getString("provider"));
	    	employeeOutOfOffice.setRemarks(rs.getString("remarks"));
	    	employeeOutOfOffice.setNoOfHours(rs.getInt("noOfHours"));
	    	employeeOutOfOffice.setTitleActivity(rs.getString("titleActivity"));	    	
	    	employeeOutOfOffice.setStatus(rs.getInt("status"));
	    	employeeOutOfOffice.setApprovedBy(rs.getInt("approvedBy"));
	    	employeeOutOfOffice.setSecondaryApprover(rs.getInt("secondaryApprover"));
	    	employeeOutOfOffice.setCreatedBy(rs.getInt("createdBy"));
	    	employeeOutOfOffice.setCreatedDate(rs.getDate("createdDate"));
	    	list.add(employeeOutOfOffice);
	    }
	    
	    ps.close();
	    rs.close();      
	    return list;		
	}
	    
	/**
	 * tested ok 062315 TG
	 * @param employeeOutOfOffice
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public int add(EmployeeOutOfOffice employeeOutOfOffice) throws SQLException, Exception {
		String sql = "INSERT INTO empOutOfOffice (empId, dateFrom, dateTo, titleActivity, provider, remarks, status, approvedBy, secondaryApprover, "
						+ "createdBy, createdDate, noOfHours) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	    ps.setInt(1, employeeOutOfOffice.getEmpId());
	    ps.setString(2, employeeOutOfOffice.getDateFrom());
	    ps.setString(3, employeeOutOfOffice.getDateTo());
	    ps.setString(4, employeeOutOfOffice.getTitleActivity());
	    ps.setString(5, employeeOutOfOffice.getProvider());
	    ps.setString(6, employeeOutOfOffice.getRemarks());
	    ps.setInt(7, employeeOutOfOffice.getStatus());
	    ps.setInt(8, employeeOutOfOffice.getApprovedBy());
	    ps.setInt(9, employeeOutOfOffice.getSecondaryApprover());
	    ps.setInt(10, employeeOutOfOffice.getCreatedBy());
	    ps.setDate(11, employeeOutOfOffice.getCreatedDate());
	    ps.setInt(12, employeeOutOfOffice.getNoOfHours());
	    
	    ps.executeUpdate();
	    ResultSet keyResultSet = ps.getGeneratedKeys();
	     int generatedAutoIncrementId = 0;
	     if (keyResultSet.next()) {
	      	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
	      	employeeOutOfOffice.setEmpOOOId(generatedAutoIncrementId);
	      	conn.commit();
	      }
		
	     ps.close();
	     keyResultSet.close();

	     return generatedAutoIncrementId;
	}

	    
	/*do not add delete method*/
	
	
	/**
	 * tested ok 062315 TG
	 * @param employeeOutOfOffice
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
    public int update(EmployeeOutOfOffice employeeOutOfOffice) throws SQLException, Exception {		
		String sql = "UPDATE empOutOfOffice SET empId=?, dateFrom=?, dateTo=?, titleActivity=?, provider=?, remarks=?, approvedBy=?, secondaryApprover=?, "
						+ "status=?, noOfHours=? WHERE empOOOId = ?";
		PreparedStatement ps  = conn.prepareStatement(sql);
	    ps.setInt(1, employeeOutOfOffice.getEmpId());
	    ps.setString(2, employeeOutOfOffice.getDateFrom());
	    ps.setString(3, employeeOutOfOffice.getDateTo());
	    ps.setString(4, employeeOutOfOffice.getTitleActivity());
	    ps.setString(5, employeeOutOfOffice.getProvider());
	    ps.setString(6, employeeOutOfOffice.getRemarks());
	    ps.setInt(7, employeeOutOfOffice.getApprovedBy());
	    ps.setInt(8, employeeOutOfOffice.getSecondaryApprover());
	    ps.setInt(9, employeeOutOfOffice.getStatus());
	    ps.setInt(10, employeeOutOfOffice.getNoOfHours());
	    ps.setInt(11, employeeOutOfOffice.getEmpOOOId());
		int count = ps.executeUpdate();
		conn.commit();
		ps.close();
		
		return count;

 	}
    
    /**
	 * tested ok 062315 TG
	 * @param employeeOutOfOffice
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
    public int approveOOO(EmployeeOutOfOffice employeeOutOfOffice) throws SQLException, Exception {		
		String sql = "UPDATE empOutOfOffice SET remarks=?, approvedBy=?, "
						+ "status=? WHERE empOOOId = ?";
		PreparedStatement ps  = conn.prepareStatement(sql);
	    
	    ps.setString(1, employeeOutOfOffice.getRemarks());
	    ps.setInt(2, employeeOutOfOffice.getApprovedBy());	    
	    ps.setInt(3, employeeOutOfOffice.getStatus());	    
	    ps.setInt(4, employeeOutOfOffice.getEmpOOOId());
		int count = ps.executeUpdate();
		conn.commit();
		ps.close();
		
		return count;

 	}
    
    public ArrayList<EmployeeOutOfOffice> getAllEmployeeOOOForSvApprovalBySuperVisorId(int superVisorId) throws SQLException, Exception {
    	EmployeeOutOfOffice employeeOOO = null;
 		ArrayList<EmployeeOutOfOffice> employeeOOOList = new ArrayList<EmployeeOutOfOffice>();
 		String sql = "SELECT eOOO.* FROM employee e, empOutOfOffice eOOO "+
 						"WHERE e.empId = eOOO.empId " +
						"AND (e.superVisor1Id = ? OR e.superVisor2Id = ? OR e.superVisor3Id=?) " +
						"AND (eOOO.approvedBy = 0  AND eOOO.secondaryApprover = 0)";
 		PreparedStatement ps = conn.prepareStatement(sql.toString());
 		ps.setInt(1, superVisorId);
 		ps.setInt(2, superVisorId);
 		ps.setInt(3, superVisorId);
 		System.out.println(superVisorId);

 	    ResultSet rs = ps.executeQuery();
 	    
 	    while (rs.next()) {
 	    	employeeOOO = new EmployeeOutOfOffice();
 	    	employeeOOO.setApprovedBy(rs.getInt("approvedBy"));
 	    	employeeOOO.setCreatedBy(rs.getInt("createdBy"));
 	    	employeeOOO.setCreatedDate(rs.getDate("createdDate"));
 	    	employeeOOO.setDateFrom(rs.getString("dateFrom"));
 	    	employeeOOO.setDateTo(rs.getString("dateTo"));
 	    	employeeOOO.setEmpId(rs.getInt("empId"));
 	    	employeeOOO.setEmpOOOId(rs.getInt("empOOOId"));
 	    	employeeOOO.setNoOfHours(rs.getInt("noOfHours"));
 	    	employeeOOO.setProvider(rs.getString("provider"));
 	    	employeeOOO.setRemarks(rs.getString("remarks"));
 	    	employeeOOO.setSecondaryApprover(rs.getInt("secondaryApprover"));
 	    	employeeOOO.setStatus(rs.getInt("status"));
 	    	employeeOOO.setTitleActivity(rs.getString("titleActivity"));
 	    	
 	    	employeeOOOList.add(employeeOOO);
 		}
 	    sql = null;
 	    ps.close();
 	    rs.close();	 
 	    return employeeOOOList;
    }
    
    
    
    
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}
}
