package dai.hris.dao.filemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.DBConstants;
import dai.hris.model.Division;

public class DivisionDAO {
	
Connection conn = null;
    
    public DivisionDAO() {
        
    	try {

    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("DivisionDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("DivisionDAO :" + e.getMessage());
		}
    	
    }
    
    public  ArrayList<Division> getAll() throws SQLException, Exception {

		String sql = "SELECT   divisionId,  divisionName FROM division";		
		PreparedStatement ps = conn.prepareStatement(sql.toString());

	    ResultSet rs = ps.executeQuery();
	    ArrayList<Division> list = new ArrayList<Division>();
	    
	    while (rs.next()) {
	    	 Division division = new Division();
	    	 division.setDivisionId(rs.getInt("divisionId"));
	    	 division.setDivisionName(rs.getString("divisionName"));	    	 
	    	 list.add(division);

	    }
	    
	    ps.close();
	    rs.close();      
	    return list;     

	}
    
    
    public  void add(Division division) throws SQLException, Exception {
  		String sql = "INSERT INTO division (divisionName) VALUES (?)";
  		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, division.getDivisionName());
        
        ps.executeUpdate();
          
        ResultSet keyResultSet = ps.getGeneratedKeys();
         int generatedAutoIncrementId = 0;
         if (keyResultSet.next()) {
          	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
          	division.setDivisionId(generatedAutoIncrementId);
          	conn.commit();
          }

          ps.close();
          keyResultSet.close();

  	}
    
    public  void delete(Division division) throws SQLException, Exception {
		String sql = "DELETE from division where divisionId = ?";
		PreparedStatement ps  = conn.prepareStatement(sql);
        ps.setInt(1, division.getDivisionId());
        ps.executeUpdate();
        conn.commit();
        ps.close();

	}
    
    public  void update(Division division) throws SQLException, Exception {
		
 		String sql = "UPDATE division set divisionName = ? where divisionId = ?";
 		PreparedStatement ps  = conn.prepareStatement(sql);
 		ps.setString(1, division.getDivisionName());
 		
 		ps.setInt(2, division.getDivisionId());
 		
         
         ps.executeUpdate();
         conn.commit();
         ps.close();

 	}
    
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}

}
