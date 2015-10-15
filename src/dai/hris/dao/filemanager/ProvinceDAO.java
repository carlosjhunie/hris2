package dai.hris.dao.filemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.DBConstants;
import dai.hris.model.Province;

public class ProvinceDAO {
	
Connection conn = null;
    
    public ProvinceDAO() {
        
    	try {

    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("ProvinceDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("ProvinceDAO :" + e.getMessage());
		}
    	
    }
    
    public  ArrayList<Province> getAll() throws SQLException, Exception {

		String sql = "SELECT   provinceId,  provinceName FROM province";		
		PreparedStatement ps = conn.prepareStatement(sql.toString());

	    ResultSet rs = ps.executeQuery();
	    ArrayList<Province> list = new ArrayList<Province>();
	    
	    while (rs.next()) {
	    	 Province province = new Province();
	    	 province.setProvinceId(rs.getInt("provinceId"));
	    	 province.setProvinceName(rs.getString("provinceName"));	    	 
	    	 list.add(province);

	    }
	    
	    ps.close();
	    rs.close();      
	    return list;     

	}
    
    
    public  void add(Province province) throws SQLException, Exception {
  		String sql = "INSERT INTO province (provinceName) VALUES (?)";
  		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, province.getProvinceName());
        
        ps.executeUpdate();
          
        ResultSet keyResultSet = ps.getGeneratedKeys();
         int generatedAutoIncrementId = 0;
         if (keyResultSet.next()) {
          	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
          	province.setProvinceId(generatedAutoIncrementId);
          	conn.commit();
          }

          ps.close();
          keyResultSet.close();

  	}
    
    public  void delete(Province province) throws SQLException, Exception {
		String sql = "DELETE from province where provinceId = ?";
		PreparedStatement ps  = conn.prepareStatement(sql);
        ps.setInt(1, province.getProvinceId());
        ps.executeUpdate();
        conn.commit();
        ps.close();

	}
    
    public  void update(Province province) throws SQLException, Exception {
		
 		String sql = "UPDATE province set provinceName = ? where provinceId = ?";
 		PreparedStatement ps  = conn.prepareStatement(sql);
 		ps.setString(1, province.getProvinceName());
 		
 		ps.setInt(2, province.getProvinceId());
 		
         
         ps.executeUpdate();
         conn.commit();
         ps.close();

 	}
    
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}

}
