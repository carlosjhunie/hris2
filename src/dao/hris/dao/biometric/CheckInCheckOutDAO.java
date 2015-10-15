package dao.hris.dao.biometric;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.action.filemanager.util.EmployeeUtil;
import dai.hris.dao.DBConstants;
import dai.hris.model.CheckInCheckOut;
import dai.hris.model.ShiftingSchedule;

public class CheckInCheckOutDAO {
	
	Connection conn = null;
	EmployeeUtil util = new EmployeeUtil();
	
	public CheckInCheckOutDAO() {
		try {
    		
    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL_BIOMETRIC);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("CheckInCheckOutDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("CheckInCheckOutDAO :" + e.getMessage());
		}
	}
	
    public  CheckInCheckOut getCheckInCheckOutLatestBySN(String sn) throws SQLException, Exception {

		String sql = "SELECT top 1 a.checkInCheckOutID, b.BADGENUMBER, a.CHECKTIME, a.VERIFYCODE, a.sn   FROM CHECKINOUT a, USERINFO b  where a.USERID = b.USERID  and a.readflag = 'N' and a.sn = ? order by a.CHECKTIME desc ";		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,sn);
	    ResultSet rs = ps.executeQuery();
	    CheckInCheckOut model = new CheckInCheckOut();
	    
	    if (rs.next()) {
	    	model.setCheckInCheckOutID(rs.getInt("checkInCheckOutID"));
	    	model.setBADGENUMBER(rs.getString("BADGENUMBER"));
	    	model.setCHECKTIME(rs.getTimestamp("CHECKTIME"));
	    	model.setVerifyCode(rs.getInt("VERIFYCODE"));
	    	model.setSn(rs.getString("sn"));
	    }	    
	    ps.close();
	    rs.close();      
	    return model;     

	}
    

    
    public  void updateReadFlagToY(int checkInCheckOutID) throws SQLException, Exception {
    	
  		String sql = "UPDATE  CHECKINOUT set readflag = 'Y'  where checkInCheckOutID =?";
  		PreparedStatement ps  = conn.prepareStatement(sql);
  		int index = 1;
  		
        ps.setInt(index++, checkInCheckOutID);
       
        
        ps.executeUpdate();       
        conn.commit();        
        ps.close();
          

  	}
    
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}

	

}
