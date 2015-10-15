package dai.hris.dao.filemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.DBConstants;
import dai.hris.model.Country;

public class CountryDAO {
	
Connection conn = null;
    
    public CountryDAO() {
        
    	try {

    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("CountryDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("CountryDAO :" + e.getMessage());
		}
    	
    }
    
    public  ArrayList<Country> getAll() throws SQLException, Exception {

		String sql = "SELECT   countryId,  countryName FROM country";		
		PreparedStatement ps = conn.prepareStatement(sql.toString());

	    ResultSet rs = ps.executeQuery();
	    ArrayList<Country> list = new ArrayList<Country>();
	    
	    while (rs.next()) {
	    	 Country country = new Country();
	    	 country.setCountryId(rs.getInt("countryId"));
	    	 country.setCountryName(rs.getString("countryName"));	    	 
	    	 list.add(country);

	    }
	    
	    ps.close();
	    rs.close();      
	    return list;     

	}
    
    
    public  void add(Country country) throws SQLException, Exception {
  		String sql = "INSERT INTO country (countryName) VALUES (?)";
  		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, country.getCountryName());
        
        ps.executeUpdate();
          
        ResultSet keyResultSet = ps.getGeneratedKeys();
         int generatedAutoIncrementId = 0;
         if (keyResultSet.next()) {
          	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
          	country.setCountryId(generatedAutoIncrementId);
          	conn.commit();
          }

          ps.close();
          keyResultSet.close();

  	}
    
    public  void delete(Country country) throws SQLException, Exception {
		String sql = "DELETE from country where countryId = ?";
		PreparedStatement ps  = conn.prepareStatement(sql);
        ps.setInt(1, country.getCountryId());
        ps.executeUpdate();
        conn.commit();
        ps.close();

	}
    
    public  void update(Country country) throws SQLException, Exception {
		
 		String sql = "UPDATE country set countryName = ? where countryId = ?";
 		PreparedStatement ps  = conn.prepareStatement(sql);
 		ps.setString(1, country.getCountryName());
 		
 		ps.setInt(2, country.getCountryId());
 		
         
         ps.executeUpdate();
         conn.commit();
         ps.close();

 	}
    
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}

}
