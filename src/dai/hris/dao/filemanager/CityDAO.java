package dai.hris.dao.filemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.DBConstants;
import dai.hris.model.City;


public class CityDAO {
	Connection conn = null;
	public CityDAO() {
		  
    	try {

    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("CityDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("CityDAO :" + e.getMessage());
		}
	}
	
	  public ArrayList<City> getAll() throws SQLException, Exception {

			String sql = "SELECT * FROM city";		
			PreparedStatement ps = conn.prepareStatement(sql.toString());

		    ResultSet rs = ps.executeQuery();
		    ArrayList<City> list = new ArrayList<City>();
		    
		    while (rs.next()) {
		    	 City city = new City();
		    	 city.setCityId(rs.getInt("cityId"));
		    	 city.setCityName(rs.getString("cityName"));	    	 
		    	 list.add(city);
		    }
		    
		    ps.close();
		    rs.close();
		    return list;
		}
	    
	    
	    public  void add(City city) throws SQLException, Exception {
	  		String sql = "INSERT INTO city (cityName) VALUES (?)";
	  		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	        ps.setString(1, city.getCityName());
	        
	        ps.executeUpdate();
	          
	        ResultSet keyResultSet = ps.getGeneratedKeys();
	         int generatedAutoIncrementId = 0;
	         if (keyResultSet.next()) {
	          	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
	          	city.setCityId(generatedAutoIncrementId);
	          	conn.commit();
	          }

	          ps.close();
	          keyResultSet.close();

	  	}
	    
	    public  void delete(City city) throws SQLException, Exception {
			String sql = "DELETE from city where cityId = ?";
			PreparedStatement ps  = conn.prepareStatement(sql);
	        ps.setInt(1, city.getCityId());
	        ps.executeUpdate();
	        conn.commit();
	        ps.close();

		}
	    
	    public  void update(City city) throws SQLException, Exception {
			
	 		String sql = "UPDATE city set cityName = ? where cityId = ?";
	 		PreparedStatement ps  = conn.prepareStatement(sql);
	 		ps.setString(1, city.getCityName());	 		
	 		ps.setInt(2, city.getCityId());
	 		
	         
	         ps.executeUpdate();
	         conn.commit();
	         ps.close();

	 	}
	    
	    public void closeConnection() throws SQLException, Exception {
			conn.close();
		}

}
