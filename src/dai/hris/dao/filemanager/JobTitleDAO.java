package dai.hris.dao.filemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.DBConstants;
import dai.hris.model.JobTitle;

public class JobTitleDAO {
	
Connection conn = null;
    
    public JobTitleDAO() {
        
    	try {

    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("JobTitleDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("JobTitleDAO :" + e.getMessage());
		}
    	
    }
    
    public  ArrayList<JobTitle> getAll() throws SQLException, Exception {

		String sql = "SELECT   jobTitleId,  jobTitle FROM jobTitle";		
		PreparedStatement ps = conn.prepareStatement(sql.toString());

	    ResultSet rs = ps.executeQuery();
	    ArrayList<JobTitle> list = new ArrayList<JobTitle>();
	    
	    while (rs.next()) {
	    	 JobTitle jobTitle = new JobTitle();
	    	 jobTitle.setJobTitleId(rs.getInt("jobTitleId"));
	    	 jobTitle.setJobTitle(rs.getString("jobTitle"));	    	 
	    	 list.add(jobTitle);

	    }
	    
	    ps.close();
	    rs.close();      
	    return list;     

	}
    
    
    public  void add(JobTitle jobTitle) throws SQLException, Exception {
  		String sql = "INSERT INTO jobTitle (jobTitle) VALUES (?)";
  		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, jobTitle.getJobTitle());
        
        ps.executeUpdate();
          
        ResultSet keyResultSet = ps.getGeneratedKeys();
         int generatedAutoIncrementId = 0;
         if (keyResultSet.next()) {
          	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
          	jobTitle.setJobTitleId(generatedAutoIncrementId);
          	conn.commit();
          }

          ps.close();
          keyResultSet.close();

  	}
    
    public  void delete(JobTitle jobTitle) throws SQLException, Exception {
		String sql = "DELETE from jobTitle where jobTitleId = ?";
		PreparedStatement ps  = conn.prepareStatement(sql);
        ps.setInt(1, jobTitle.getJobTitleId());
        ps.executeUpdate();
        conn.commit();
        ps.close();

	}
    
    public  void update(JobTitle jobTitle) throws SQLException, Exception {
		
 		String sql = "UPDATE jobTitle set jobTitle = ? where jobTitleId = ?";
 		PreparedStatement ps  = conn.prepareStatement(sql);
 		ps.setString(1, jobTitle.getJobTitle());
 		
 		ps.setInt(2, jobTitle.getJobTitleId());
 		
         
         ps.executeUpdate();
         conn.commit();
         ps.close();

 	}
    
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}

}
