package dai.hris.dao.filemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dai.hris.action.filemanager.util.EmployeeUtil;
import dai.hris.dao.DBConstants;
import dai.hris.model.EmployeeNotification;

/**
 * 
 * @author Ian Orozco
 *
 */
public class EmpNotificationDAO {
	Connection conn = null;
	EmployeeUtil util = new EmployeeUtil();
	public EmpNotificationDAO() {
		
    	try {

    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("EmpNotificationDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("EmpNotificationDAO :" + e.getMessage());
		}
	}
	


	public EmployeeNotification getEmployeeNotificationByEmployeeNotificationId(int empNotificationId) throws SQLException, Exception {			    		
		String sql = "SELECT * FROM employeeNotification where empNotificationId = ?";
		EmployeeNotification employeeNotification = null;
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, empNotificationId);
		
	    ResultSet rs = ps.executeQuery();
	    
	    while (rs.next()) {
	    	employeeNotification = new EmployeeNotification();
	    	employeeNotification.setCcRecipient(rs.getString("ccRecipient"));
	    	employeeNotification.setCreatedBy(rs.getInt("createdBy"));
	    	employeeNotification.setEmpNotificationId(rs.getInt("empNotificationId"));
	    	employeeNotification.setFromSender(rs.getString("fromSender"));
	    	employeeNotification.setFiledDate(util.sqlDateToString(rs.getDate("filedDate")));
	    	employeeNotification.setMessage(rs.getString("message"));
	    	employeeNotification.setRemarks(rs.getString("remarks"));
	    	employeeNotification.setSubject(rs.getString("subject"));
	    	employeeNotification.setToRecipientEmpId(rs.getInt("toRecipientEmpId"));
	    }
	    
	    ps.close();
	    rs.close();      
	    return employeeNotification;		
	}
	

	/**
	 * get all memos that were produced by "createdBy" empId (assumed to be supervisor or admin)
	 * @param createdByEmpId
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public ArrayList<EmployeeNotification> getEmployeeNotificationListByCreatedByEmpId(int createdByEmpId) throws SQLException, Exception {			    		
		String sql = "SELECT * FROM employeeNotification where createdBy = ?";		
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, createdByEmpId);
	
	    ResultSet rs = ps.executeQuery();
	    ArrayList<EmployeeNotification> list = new ArrayList<EmployeeNotification>();
	    
	    while (rs.next()) {
	    	EmployeeNotification employeeNotification = new EmployeeNotification();
	    	employeeNotification.setCcRecipient(rs.getString("ccRecipient"));
	    	employeeNotification.setCreatedBy(rs.getInt("createdBy"));
	    	employeeNotification.setEmpNotificationId(rs.getInt("empNotificationId"));
	    	employeeNotification.setFromSender(rs.getString("fromSender"));
	    	employeeNotification.setFiledDate(util.sqlDateToString(rs.getDate("filedDate")));
	    	employeeNotification.setMessage(rs.getString("message"));
	    	employeeNotification.setRemarks(rs.getString("remarks"));
	    	employeeNotification.setSubject(rs.getString("subject"));
	    	employeeNotification.setToRecipientEmpId(rs.getInt("toRecipientEmpId"));
	    	list.add(employeeNotification);
	    }
	    
	    ps.close();
	    rs.close();      
	    return list;		
	}
	
	
	/**
	 * get all memos that are intended for the recipient empId
	 * @param createdByEmpId
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public ArrayList<EmployeeNotification> getEmployeeNotificationListByToRecipientEmpId(int memoRecipientEmpId) throws SQLException, Exception {			    		
		String sql = "SELECT * FROM employeeNotification where toRecipientEmpId = ?";		
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, memoRecipientEmpId);
	
	    ResultSet rs = ps.executeQuery();
	    ArrayList<EmployeeNotification> list = new ArrayList<EmployeeNotification>();
	    
	    while (rs.next()) {
	    	EmployeeNotification employeeNotification = new EmployeeNotification();
	    	employeeNotification.setCcRecipient(rs.getString("ccRecipient"));
	    	employeeNotification.setCreatedBy(rs.getInt("createdBy"));
	    	employeeNotification.setEmpNotificationId(rs.getInt("empNotificationId"));
	    	employeeNotification.setFromSender(rs.getString("fromSender"));
	    	employeeNotification.setFiledDate(util.sqlDateToString(rs.getDate("filedDate")));
	    	employeeNotification.setMessage(rs.getString("message"));
	    	employeeNotification.setRemarks(rs.getString("remarks"));
	    	employeeNotification.setSubject(rs.getString("subject"));
	    	employeeNotification.setToRecipientEmpId(rs.getInt("toRecipientEmpId"));
	    	list.add(employeeNotification);
	    }
	    
	    ps.close();
	    rs.close();      
	    return list;		
	}
	    

	/**
	 * 
	 * @param employeeMemo
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public boolean add(EmployeeNotification employeeNotification) throws SQLException, Exception {
		String sql = "INSERT INTO employeeNotification (filedDate, toRecipientEmpId, ccRecipient, createdBy, fromSender, message, remarks, subject) VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setDate(1, util.convertToSqlDate(employeeNotification.getFiledDate()));
	    ps.setInt(2, employeeNotification.getToRecipientEmpId());
	    ps.setString(3, employeeNotification.getCcRecipient());
	    ps.setInt(4, employeeNotification.getCreatedBy());
	    ps.setString(5, employeeNotification.getFromSender());
	    ps.setString(6, employeeNotification.getMessage());
	    ps.setString(7, employeeNotification.getRemarks());
	    ps.setString(8, employeeNotification.getSubject());
	    
	    int count = ps.executeUpdate();
	    boolean status = false;  
	    ResultSet keyResultSet = ps.getGeneratedKeys();
	     int generatedAutoIncrementId = 0;
	     if (keyResultSet.next()) {
	      	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
	      	employeeNotification.setEmpNotificationId(generatedAutoIncrementId);
	      	conn.commit();
	      }
		
	     ps.close();
	     keyResultSet.close();
		 if (count == 1) {
			 status = true;
		 }		
	    return status;
	}

	    
	/*do not add delete method*/
	
	/**
	 * 
	 * @param employeeMemo
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
    public boolean update(EmployeeNotification employeeNotification) throws SQLException, Exception {		
		String sql = "UPDATE employeeNotification SET filedDate=?, toRecipientEmpId=?, ccRecipient=?, createdBy=?, fromSender=?, message=?, remarks=?, subject=? WHERE empNotificationId = ?";
		PreparedStatement ps  = conn.prepareStatement(sql);
	    ps.setDate(1, util.convertToSqlDate(employeeNotification.getFiledDate()));
	    ps.setInt(2, employeeNotification.getToRecipientEmpId());
	    ps.setString(3, employeeNotification.getCcRecipient());
	    ps.setInt(4, employeeNotification.getCreatedBy());
	    ps.setString(5, employeeNotification.getFromSender());
	    ps.setString(6, employeeNotification.getMessage());
	    ps.setString(7, employeeNotification.getRemarks());
	    ps.setString(8, employeeNotification.getSubject());
	    ps.setInt(9, employeeNotification.getEmpNotificationId());
		int count = ps.executeUpdate();
		boolean status = false;
		conn.commit();
		ps.close();
		if (count == 1) {
			status = true;
		}			
		return status;

 	}
    
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}
}
