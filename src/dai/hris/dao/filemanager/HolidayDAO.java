package dai.hris.dao.filemanager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dai.hris.dao.DBConstants;
import dai.hris.model.Holiday;


public class HolidayDAO {
	Connection conn = null;
	public HolidayDAO() {
		  
    	try {

    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("HolidayDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("HolidayDAO :" + e.getMessage());
		}
	}
	
	  public ArrayList<Holiday> getAll() throws SQLException, Exception {

			String sql = "SELECT * FROM holiday";		
			PreparedStatement ps = conn.prepareStatement(sql.toString());

		    ResultSet rs = ps.executeQuery();
		    ArrayList<Holiday> list = new ArrayList<Holiday>();
		    
		    while (rs.next()) {
		    	Holiday holiday = new Holiday();
		    	holiday.setHolidayId(rs.getInt("holidayId"));
		    	holiday.setHolidayName(rs.getString("holidayName"));
		    	holiday.setHolidayDate(rs.getString("holidayDate"));  
		    	holiday.setHolidayType(rs.getString("holidayType"));
		    	holiday.setCreatedBy(rs.getInt("createdBy"));
		    	holiday.setCreatedDate(rs.getDate("createdDate"));  	 	
		    	list.add(holiday);
		    }
		    
		    ps.close();
		    rs.close();      
		    return list;     

		}
	    
	    //TODO Fix HolidayDAO convert holidayDate to dateTime
	    public int add(Holiday holiday) throws SQLException, Exception {
	  		String sql = "INSERT INTO holiday (holidayName, holidayDate, holidayType, createdBy, createdDate) VALUES (?,?,?,?,?)";
	  		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	        ps.setString(1, holiday.getHolidayName());
	        ps.setString(2, holiday.getHolidayDate());
	        ps.setString(3, holiday.getHolidayType());
	        ps.setInt(4, holiday.getCreatedBy());
	        ps.setDate(5, holiday.getCreatedDate());
	        
	        
	        ps.executeUpdate();
	          
	        ResultSet keyResultSet = ps.getGeneratedKeys();
	         int generatedAutoIncrementId = 0;
	         if (keyResultSet.next()) {
	          	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
	          	holiday.setHolidayId(generatedAutoIncrementId);
	          	conn.commit();
	          }

	          ps.close();
	          keyResultSet.close();
	          	         
	         return generatedAutoIncrementId;

	  	}
	    
	    public int update(Holiday holiday) throws SQLException, Exception {
			
	 		String sql = "UPDATE holiday set holidayName=?, holidayDate=?, holidayType=? where holidayId = ?";
	 		PreparedStatement ps  = conn.prepareStatement(sql);
	 		ps.setString(1, holiday.getHolidayName());	 		
	 		ps.setString(2, holiday.getHolidayDate());
	 		ps.setString(3, holiday.getHolidayType());	
	 		ps.setInt(4, holiday.getHolidayId());	
	         
	         int count = ps.executeUpdate();
	         conn.commit();
	         ps.close();
	         return count;

	 	}
	    
	    public List<Holiday> getHolidaysByPayPeriodRange(Date dateFrom, Date dateTo) throws SQLException, Exception {
	    	
	    	
	    	String sql = "SELECT * FROM holiday where holidayDate between ? and ?";		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, dateFrom);
			ps.setDate(2, dateTo);

		    ResultSet rs = ps.executeQuery();
		    ArrayList<Holiday> list = new ArrayList<Holiday>();
		    
		    while (rs.next()) {
		    	Holiday holiday = new Holiday();
		    	holiday.setHolidayId(rs.getInt("holidayId"));
		    	holiday.setHolidayName(rs.getString("holidayName"));
		    	holiday.setHolidayDate(rs.getString("holidayDate"));  
		    	holiday.setHolidayType(rs.getString("holidayType"));
		    	holiday.setCreatedBy(rs.getInt("createdBy"));
		    	holiday.setCreatedDate(rs.getDate("createdDate"));  	 	
		    	list.add(holiday);
		    }
		    
		    ps.close();
		    rs.close();      
		    return list;
	    	
	    	
	    }
	    
	    public void closeConnection() throws SQLException, Exception {
			conn.close();
		}

}
