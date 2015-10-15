package dai.hris.dao.filemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.DBConstants;
import dai.hris.model.LeaveType;

public class LeaveTypeDAO {
	
Connection conn = null;
    
    public LeaveTypeDAO() {
        
    	try {

    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("LeaveTypeDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("LeaveTypeDAO :" + e.getMessage());
		}
    	
    }
    
    public  ArrayList<LeaveType> getAll() throws SQLException, Exception {

		String sql = "SELECT   leaveTypeId,  leaveTypeName FROM leaveType";		
		PreparedStatement ps = conn.prepareStatement(sql.toString());

	    ResultSet rs = ps.executeQuery();
	    ArrayList<LeaveType> list = new ArrayList<LeaveType>();
	    
	    while (rs.next()) {
	    	 LeaveType leaveType = new LeaveType();
	    	 leaveType.setLeaveTypeId(rs.getInt("leaveTypeId"));
	    	 leaveType.setLeaveTypeName(rs.getString("leaveTypeName"));	    	 
	    	 list.add(leaveType);

	    }
	    
	    ps.close();
	    rs.close();      
	    return list;     

	}
    
    
    public LeaveType getLeaveTypeByLeaveTypeId(int leaveTypeId) throws SQLException, Exception {

		String sql = "SELECT   leaveTypeId,  leaveTypeName FROM leaveType where leaveTypeId=?";		
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, leaveTypeId);

	    ResultSet rs = ps.executeQuery();
	    LeaveType leaveType = null;
	    
	    while (rs.next()) {
	    	 leaveType = new LeaveType();
	    	 leaveType.setLeaveTypeId(rs.getInt("leaveTypeId"));
	    	 leaveType.setLeaveTypeName(rs.getString("leaveTypeName"));	    	 
	    	 

	    }
	    
	    ps.close();
	    rs.close();
	    
	    return leaveType;	    

	}
    
    
    public  void add(LeaveType leaveType) throws SQLException, Exception {
  		String sql = "INSERT INTO leaveType (leaveTypeName) VALUES (?)";
  		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, leaveType.getLeaveTypeName());
        
        ps.executeUpdate();
          
        ResultSet keyResultSet = ps.getGeneratedKeys();
         int generatedAutoIncrementId = 0;
         if (keyResultSet.next()) {
          	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
          	leaveType.setLeaveTypeId(generatedAutoIncrementId);
          	conn.commit();
          }

          ps.close();
          keyResultSet.close();

  	}
    
    public  void delete(LeaveType leaveType) throws SQLException, Exception {
		String sql = "DELETE from leaveType where leaveTypeId = ?";
		PreparedStatement ps  = conn.prepareStatement(sql);
        ps.setInt(1, leaveType.getLeaveTypeId());
        ps.executeUpdate();
        conn.commit();
        ps.close();

	}
    
    public  void update(LeaveType leaveType) throws SQLException, Exception {
		
 		String sql = "UPDATE leaveType set leaveTypeName = ? where leaveTypeId = ?";
 		PreparedStatement ps  = conn.prepareStatement(sql);
 		ps.setString(1, leaveType.getLeaveTypeName());
 		
 		ps.setInt(2, leaveType.getLeaveTypeId());
 		
         
         ps.executeUpdate();
         conn.commit();
         ps.close();

 	}
    
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}

}
