package dai.hris.dao.filemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.DBConstants;
import dai.hris.model.Department;

public class DepartmentDAO {
	
Connection conn = null;
    
    public DepartmentDAO() {
        
    	try {

    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("DepartmentDAO :" + sqle.getMessage());
			
		} catch (Exception e) {
			System.out.println("DepartmentDAO :" + e.getMessage());
		
		}
    	
    }
    
    public  ArrayList<Department> getAll() throws SQLException, Exception {

		String sql = "SELECT   departmentId,  departmentName FROM Department";		
		PreparedStatement ps = conn.prepareStatement(sql.toString());

	    ResultSet rs = ps.executeQuery();
	    ArrayList<Department> list = new ArrayList<Department>();
	    
	    while (rs.next()) {
	    	 Department Department = new Department();
	    	 Department.setDepartmentId(rs.getInt("departmentId"));
	    	 Department.setDepartmentName(rs.getString("departmentName"));	    	 
	    	 list.add(Department);

	    }
	    
	    ps.close();
	    rs.close();      
	    return list;     

	}
    
    
    public  void add(Department Department) throws SQLException, Exception {
  		String sql = "INSERT INTO Department (departmentName) VALUES (?)";
  		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, Department.getDepartmentName());
        
        ps.executeUpdate();
          
        ResultSet keyResultSet = ps.getGeneratedKeys();
         int generatedAutoIncrementId = 0;
         if (keyResultSet.next()) {
          	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
          	Department.setDepartmentId(generatedAutoIncrementId);
          	conn.commit();
          }

          ps.close();
          keyResultSet.close();

  	}
    
    public  void delete(Department Department) throws SQLException, Exception {
		String sql = "DELETE from Department where departmentId = ?";
		PreparedStatement ps  = conn.prepareStatement(sql);
        ps.setInt(1, Department.getDepartmentId());
        ps.executeUpdate();
        conn.commit();
        ps.close();

	}
    
    public  void update(Department Department) throws SQLException, Exception {
		
 		String sql = "UPDATE Department set departmentName = ? where departmentId = ?";
 		PreparedStatement ps  = conn.prepareStatement(sql);
 		ps.setString(1, Department.getDepartmentName()); 		
 		ps.setInt(2, Department.getDepartmentId());
 		
         
         ps.executeUpdate();
         conn.commit();
         ps.close();

 	}
    
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}

}
