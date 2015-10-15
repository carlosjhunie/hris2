package dai.hris.dao.tardiness;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dai.hris.dao.DBConstants;

public class TardinessDAO {
	
	Connection conn = null;
	
	public TardinessDAO() {
		try {
    		
    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("TardinessDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("TardinessDAO :" + e.getMessage());
		}
	}
	
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}
    
    public int  getTotalNumberOfTardiness(int empId, Date dateFrom, Date dateTo) throws SQLException, Exception {

		String sql = "select tardiness from (   select     case when (SUM( datediff(minute,dt.corrected_shift_timein , et.timeIn ) )) < -30  then -30  else   (SUM( datediff(minute,dt.corrected_shift_timein , et.timeIn ) ))   end +       SUM( datediff(minute, et.timeOut, dt.shift_corrected_timeout_datetime  )) as 'tardiness'   from (     select es.empId, ss.shiftType,  es.scheduleDate,     ss.timein , ss.timeout, es.shiftingScheduleId,  ( CAST(es.scheduleDate as datetime) + CAST(ss.timein as datetime)   )  as 'shift_timein_datetime',   ( CAST(es.scheduleDate as datetime) + CAST(ss.timeout as datetime)  )  as 'shift_timeout_datetime',    case when ss.shiftType = 'G' then   CAST(es.scheduleDate as datetime)  + cast(dateadd( MINUTE, 30,  ss.timeIn) as time) else 	( CAST(es.scheduleDate as datetime) + CAST(ss.timein as datetime)   ) end AS 'corrected_shift_timein',     case  when   ( CAST(es.scheduleDate as datetime) + CAST(ss.timeout as datetime)  ) <    ( CAST(es.scheduleDate as datetime) + CAST(ss.timein as datetime)  ) then        dateadd( day, 1,  ( CAST(es.scheduleDate as datetime) + CAST(ss.timeout as datetime)  ) )    else  ( CAST(es.scheduleDate as datetime) + CAST(ss.timeout as datetime)  )   END  as 'shift_corrected_timeout_datetime' from  shiftingSchedule ss,  empSchedule es       where       ss.shiftingScheduleId = es.shiftingScheduleId )  dt,   empTimeEntry et  where et.empId  = dt.empId and et.timeIn is not null and et.timeOut is not null and et.shiftId = dt.shiftingScheduleId and dt.scheduleDate between ? and  ? and et.empId = ? ) tardiness_table  where tardiness > 0 ";		
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setDate(1, dateFrom);
		ps.setDate(2, dateTo);
		ps.setInt(3, empId);
		
	    ResultSet rs = ps.executeQuery();
	   
	    int noOfAbsent = 0;
	    if (rs.next()) {
	    	noOfAbsent = rs.getInt("tardiness");
	    }
	    
	    ps.close();
	    rs.close();      
	    return noOfAbsent;     

	}

    

}
