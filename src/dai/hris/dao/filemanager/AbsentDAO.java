package dai.hris.dao.filemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.DBConstants;
import dai.hris.model.Absent;

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
    

    public  ArrayList<Absent> getAll() throws SQLException, Exception {

		String sql = "SELECT absentId, empNo, absentDate, superVisorId, remarks from absent";		
		PreparedStatement ps = conn.prepareStatement(sql.toString());

	    ResultSet rs = ps.executeQuery();
	    ArrayList<Absent> list = new ArrayList<Absent>();
	    
	    while (rs.next()) {
	    	Absent absent = new Absent();
	    	absent.setAbsentdate(rs.getDate("absentDate"));
	    	absent.setAbsentId(rs.getInt("absentId"));
	    	absent.setEmpNo(rs.getInt("empNo"));
	    	absent.setRemarks(rs.getString("remarks"));
	    	absent.setSuperVisorId(rs.getInt("superVisorId"));
	    	list.add(absent);

	    }
	    
	    ps.close();
	    rs.close();      
	    return list;     

	}
    

    public  void add(Absent absent) throws SQLException, Exception {
  		String sql = "INSERT INTO absent (empNo, absentDate, superVisorId, remarks) VALUES (?, ?, ?, ?)";
  		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        
        ps.setInt(1, absent.getEmpNo());
        ps.setDate(2, absent.getAbsentdate());
		ps.setInt(3, absent.getSuperVisorId());
		ps.setString(4, absent.getRemarks());
        ps.executeUpdate();
          
        ResultSet keyResultSet = ps.getGeneratedKeys();
         int generatedAutoIncrementId = 0;
         if (keyResultSet.next()) {
          	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
          	absent.setAbsentId(generatedAutoIncrementId);
          	conn.commit();
          }

          ps.close();
          keyResultSet.close();

  	}
    
  

    public  void delete(Absent absent) throws SQLException, Exception {
		String sql = "DELETE from absent where absentId = ?";
		PreparedStatement ps  = conn.prepareStatement(sql);
        ps.setInt(1, absent.getAbsentId());
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
    public  void update(Absent absent) throws SQLException, Exception {
		
 		String sql = "UPDATE absent set " +
 					 "empNo=?, absentDate=?, superVisorId=?, remarks=? " +
 					 "where absentId = ?";
 		PreparedStatement ps  = conn.prepareStatement(sql); 		
        ps.setInt(1, absent.getEmpNo());
        ps.setDate(2, absent.getAbsentdate());
		ps.setInt(3, absent.getSuperVisorId());
		ps.setString(4, absent.getRemarks());
 		
         
         ps.executeUpdate();
         conn.commit();
         ps.close();

 	}
    
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}

}
