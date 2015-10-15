package dai.hris.dao.shiftingSchedule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.action.filemanager.util.EmployeeUtil;
import dai.hris.dao.DBConstants;
import dai.hris.model.ShiftingSchedule;

public class ShiftingScheduleDAO {
	
	Connection conn = null;
	EmployeeUtil util = new EmployeeUtil();
	
	public ShiftingScheduleDAO() {
		try {
    		
    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("LoanEntryDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("LoanEntryDAO :" + e.getMessage());
		}
	}
	
    public  ArrayList<ShiftingSchedule> getAll() throws SQLException, Exception {

		String sql = "SELECT   *  FROM shiftingSchedule";		
		PreparedStatement ps = conn.prepareStatement(sql);

	    ResultSet rs = ps.executeQuery();
	    ArrayList<ShiftingSchedule> list = new ArrayList<ShiftingSchedule>();
	    
	    while (rs.next()) {
	    	 ShiftingSchedule model = new ShiftingSchedule();
	    	 model.setShiftingScheduleId(rs.getInt("shiftingScheduleId"));
	    	 model.setShiftType(rs.getString("shiftType"));	    
	    	 model.setTimeIn(util.sqlTimeToString(rs.getTime("timeIn")));
	    	 model.setTimeOut(util.sqlTimeToString(rs.getTime("timeOut")));
	    	 model.setDescription(rs.getString("description"));	    
	    	 model.setNoOfHours(rs.getInt("noOfHours"));
	    	 list.add(model);

	    }	    
	    ps.close();
	    rs.close();      
	    return list;     

	}
    
    public  void add(ShiftingSchedule shiftingSchedule) throws SQLException, Exception {
  		String sql = "INSERT INTO shiftingSchedule (shiftType,timeIn,timeOut, description, noOfHours) VALUES (?,?,?,?,?)";
  		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
  		int index = 1;
  		
        ps.setString(index++, shiftingSchedule.getShiftType());
        ps.setTime(index++, util.convertToSqlTime(shiftingSchedule.getTimeIn()));
        ps.setTime(index++, util.convertToSqlTime(shiftingSchedule.getTimeOut()));
        ps.setString(index++, shiftingSchedule.getDescription());
        ps.setInt(index++, shiftingSchedule.getNoOfHours());

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
    	
    	
  		String sql = "UPDATE  shiftingSchedule set shiftType = ? ,timeIn = ?,timeOut = ? , description = ?, noOfHours = ? where shiftingScheduleId =?";
  		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
  		int index = 1;
  		
        ps.setString(index++, shiftingSchedule.getShiftType());
        ps.setTime(index++, util.convertToSqlTime(shiftingSchedule.getTimeIn()));
        ps.setTime(index++, util.convertToSqlTime(shiftingSchedule.getTimeOut()));
        ps.setString(index++, shiftingSchedule.getDescription());
        ps.setInt(index++, shiftingSchedule.getNoOfHours());
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

	

}
