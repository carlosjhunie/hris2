package dai.hris.dao.absent;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dai.hris.dao.DBConstants;

public class AbsentDAO {
	
	Connection conn = null;
	
	public AbsentDAO() {
		try {
    		
    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("AbsentDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("AbsentDAO :" + e.getMessage());
		}
	}
	
	
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}
    
    
    //TODO: RJC FOR THE FUTURE
    public void insertAbsent(Date dateFrom, Date dateTo) throws SQLException, Exception {

    	String sql = "insert into absent(empId, absentDate)  select empId,scheduleDate from empschedule es where  scheduleDate between ? and ? and  es.empScheduleId not in( select dt.empScheduleId from ( select es.empScheduleId, es.empId,  es.scheduleDate,     ss.timein , ss.timeout, es.shiftingScheduleId,  ( CAST(es.scheduleDate as datetime) + CAST(ss.timein as datetime)   )  as 'timein_datetime',   ( CAST(es.scheduleDate as datetime) + CAST(ss.timeout as datetime)  )  as 'timeout_datetime',   case  when   ( CAST(es.scheduleDate as datetime) + CAST(ss.timeout as datetime)  ) <    ( CAST(es.scheduleDate as datetime) + CAST(ss.timein as datetime)  ) then        dateadd( day, 1,  ( CAST(es.scheduleDate as datetime) + CAST(ss.timeout as datetime)  ) )    else  ( CAST(es.scheduleDate as datetime) + CAST(ss.timeout as datetime)  )   END  as 'corrected_timeout_datetime'   from  shiftingSchedule ss,  empSchedule es   where   ss.shiftingScheduleId = es.	shiftingScheduleId   ) dt,  empTimeEntry et     where et.empId  = dt.empId and et.timeIn is not null and et.timeOut is not null and et.shiftId = dt.shiftingScheduleId and et.timeIn    between  dateadd(minute , -120, cast (dt.timein_datetime as datetime)) and dateadd(minute, 120, cast(dt.corrected_timeout_datetime as datetime))  and scheduleDate between ? and ? ) ";		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setDate(1, dateFrom);
		ps.setDate(2, dateTo);
		ps.setDate(3, dateFrom);
		ps.setDate(4, dateTo);
		
		ps.executeUpdate();
		conn.commit();
		
	    ps.close();
	       	
    }
    // TODO:  RJC FOR THE FUTURE 
    /*
    public int  getNumberOfAbsent(int empId, Date dateFrom, Date dateTo) throws SQLException, Exception {

		String sql = "SELECT COUNT(empId) as noOfAbsent FROM absent   where empId = ? and absentDate between ? and ?";		
		PreparedStatement ps = conn.prepareStatement(sql.toString());

	    ResultSet rs = ps.executeQuery();
	    ArrayList<Country> list = new ArrayList<Country>();
	    int noOfAbsent = 0;
	    if (rs.next()) {
	    	noOfAbsent = rs.getInt(rs.getInt("noOfAbsent"));
	    }
	    
	    ps.close();
	    rs.close();      
	    return noOfAbsent;     

	}
    */
    
    public int  getNumberOfAbsent(int empId, Date dateFrom, Date dateTo) throws SQLException, Exception {

		String sql = "select COUNT(*) AS noOfAbsent from empschedule es where  scheduleDate between ? and ? and  es.empScheduleId not in( select dt.empScheduleId from ( select es.empScheduleId, es.empId,  es.scheduleDate,     ss.timein , ss.timeout, es.shiftingScheduleId,  ( CAST(es.scheduleDate as datetime) + CAST(ss.timein as datetime)   )  as 'timein_datetime',   ( CAST(es.scheduleDate as datetime) + CAST(ss.timeout as datetime)  )  as 'timeout_datetime',   case  when   ( CAST(es.scheduleDate as datetime) + CAST(ss.timeout as datetime)  ) <    ( CAST(es.scheduleDate as datetime) + CAST(ss.timein as datetime)  ) then        dateadd( day, 1,  ( CAST(es.scheduleDate as datetime) + CAST(ss.timeout as datetime)  ) )    else  ( CAST(es.scheduleDate as datetime) + CAST(ss.timeout as datetime)  )   END  as 'corrected_timeout_datetime'   from  shiftingSchedule ss,  empSchedule es   where   ss.shiftingScheduleId = es.	shiftingScheduleId   ) dt,  empTimeEntry et     where et.empId  = dt.empId and et.timeIn is not null and et.timeOut is not null and et.shiftId = dt.shiftingScheduleId and et.timeIn    between  dateadd(minute , -120, cast (dt.timein_datetime as datetime)) and dateadd(minute, 120, cast(dt.corrected_timeout_datetime as datetime))  and scheduleDate between ? and ? and  es.empId = ? ) and   es.empId = ?";		
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setDate(1, dateFrom);
		ps.setDate(2, dateTo);
		ps.setDate(3, dateFrom);
		ps.setDate(4, dateTo);
		ps.setInt(5, empId);
		ps.setInt(6, empId);
		

	    ResultSet rs = ps.executeQuery();
	   
	    int noOfAbsent = 0;
	    if (rs.next()) {
	    	noOfAbsent = rs.getInt("noOfAbsent");
	    }
	    
	    ps.close();
	    rs.close();      
	    return noOfAbsent;     

	}

    
    
    
    

}
