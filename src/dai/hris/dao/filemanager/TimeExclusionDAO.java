package dai.hris.dao.filemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.DBConstants;
import dai.hris.model.TimeExclusion;

public class TimeExclusionDAO {
	
Connection conn = null;
    
    public TimeExclusionDAO() {
        
    	try {

    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("TimeExclusionDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("TimeExclusionDAO :" + e.getMessage());
		}
    	
    }
    
    /**
     * 
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public  ArrayList<TimeExclusion> getAll() throws SQLException, Exception {

		String sql = "SELECT timeExId, empNo, dateFrom, dateTo, purpose, provider, remarks, approvedBy from timeExclusions";		
		PreparedStatement ps = conn.prepareStatement(sql.toString());

	    ResultSet rs = ps.executeQuery();
	    ArrayList<TimeExclusion> list = new ArrayList<TimeExclusion>();
	    
	    while (rs.next()) {
	    	TimeExclusion timeExclusion = new TimeExclusion();
	    	timeExclusion.setApprovedBy(rs.getInt("approvedBy"));
	    	timeExclusion.setDateFrom(rs.getDate("dateFrom"));
	    	timeExclusion.setDateTo(rs.getDate("dateTo"));
	    	timeExclusion.setEmpNo(rs.getInt("empNo"));
	    	timeExclusion.setPurpose(rs.getString("purpose"));
	    	timeExclusion.setProvider(rs.getString("provider"));
	    	timeExclusion.setRemarks(rs.getString("remarks"));
	    	timeExclusion.setTimeExId(rs.getInt("timeExId"));    	 
	    	list.add(timeExclusion);

	    }
	    
	    ps.close();
	    rs.close();      
	    return list;     

	}
    
    /**
     * 
     * @param timeExclusion
     * @throws SQLException
     * @throws Exception
     */
    public  void add(TimeExclusion timeExclusion) throws SQLException, Exception {
  		String sql = "INSERT INTO timeExclusions (empNo, dateFrom, dateTo, purpose, provider, remarks, approvedBy) VALUES (?, ?, ?, ?, ?, ?, ?)";
  		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        
        ps.setInt(1, timeExclusion.getEmpNo());
        ps.setDate(2, timeExclusion.getDateFrom());
		ps.setDate(3, timeExclusion.getDateTo());
		ps.setString(4, timeExclusion.getPurpose());
		ps.setString(5, timeExclusion.getProvider());
		ps.setString(6, timeExclusion.getRemarks());
		ps.setInt(7, timeExclusion.getApprovedBy());

        ps.executeUpdate();
          
        ResultSet keyResultSet = ps.getGeneratedKeys();
         int generatedAutoIncrementId = 0;
         if (keyResultSet.next()) {
          	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
          	timeExclusion.setTimeExId(generatedAutoIncrementId);
          	conn.commit();
          }

          ps.close();
          keyResultSet.close();

  	}
    
  
    /**
     * 
     * @param timeExclusion
     * @throws SQLException
     * @throws Exception
     */
    public  void delete(TimeExclusion timeExclusion) throws SQLException, Exception {
		String sql = "DELETE from timeExclusions where timeExId = ?";
		PreparedStatement ps  = conn.prepareStatement(sql);
        ps.setInt(1, timeExclusion.getTimeExId());
        ps.executeUpdate();
        conn.commit();
        ps.close();

	}
    
    
    /**
     * 
     * @param timeExclusion
     * @throws SQLException
     * @throws Exception
     */
    public  void update(TimeExclusion timeExclusion) throws SQLException, Exception {
		
 		String sql = "UPDATE timeExclusions set " +
 					 "empNo=?, dateFrom=?, dateTo=?, purpose=?, provider=?, remarks=?, approvedBy=? " +
 					 "where timeExId = ?";
 		PreparedStatement ps  = conn.prepareStatement(sql); 		
        ps.setInt(1, timeExclusion.getEmpNo());
        ps.setDate(2, timeExclusion.getDateFrom());
		ps.setDate(3, timeExclusion.getDateTo());
		ps.setString(4, timeExclusion.getPurpose());
		ps.setString(5, timeExclusion.getProvider());
		ps.setString(6, timeExclusion.getRemarks());
		ps.setInt(7, timeExclusion.getApprovedBy());
 		
         
         ps.executeUpdate();
         conn.commit();
         ps.close();

 	}
    
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}

}
