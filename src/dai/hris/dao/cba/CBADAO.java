package dai.hris.dao.cba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.DBConstants;
import dai.hris.model.EmployeeCBA;


public class CBADAO {
	Connection conn = null;
	
	public CBADAO() {
		
    	try {

    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("CBADAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("CBADAO :" + e.getMessage());
		}
	}
	
/**
 * TG
 * @param cbaId
 * @return
 * @throws SQLException
 * @throws Exception
 */
	public EmployeeCBA getEmployeeCbaByCbaId(int cbaId) throws SQLException, Exception {			    		
		String sql = "select * from cba where cbaId = ?";
		EmployeeCBA empCBA = null;
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, cbaId);
		ResultSet rs = ps.executeQuery();	    
	    while (rs.next()) {
	    	empCBA = new EmployeeCBA();
	    	empCBA.setCbaId(rs.getInt("cbaId"));
	    	empCBA.setEmpId(rs.getInt("empId"));
	    	empCBA.setMyInputs(rs.getString("myInputs"));
	    	empCBA.setPerformanceYear(rs.getInt("performanceYear"));
	    	empCBA.setRemarks(rs.getString("remarks"));
	    }
	    
	    ps.close();
	    rs.close();      
	    return empCBA;
	}
	

/**
 * TG
 * @param empId
 * @return
 * @throws SQLException
 * @throws Exception
 */
	public ArrayList<EmployeeCBA> getEmployeeCBAByEmpId(int empId) throws SQLException, Exception {			    		
		String sql = "SELECT * FROM cba where empId = ? order by performanceYear desc";
		EmployeeCBA empCBA = null;
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, empId);
	
	    ResultSet rs = ps.executeQuery();
	    ArrayList<EmployeeCBA> list = new ArrayList<EmployeeCBA>();
	    
	    while (rs.next()) {	    	
	    	empCBA = new EmployeeCBA();
	    	empCBA.setCbaId(rs.getInt("cbaId"));
	    	empCBA.setEmpId(rs.getInt("empId"));
	    	empCBA.setMyInputs(rs.getString("myInputs"));
	    	empCBA.setPerformanceYear(rs.getInt("performanceYear"));
	    	empCBA.setRemarks(rs.getString("remarks"));
	    	list.add(empCBA);
	    }
	    
	    ps.close();
	    rs.close();
	    return list;		
	}
	    
/**
 * TG
 * @param empCBA
 * @return
 * @throws SQLException
 * @throws Exception
 */
	public int add(EmployeeCBA empCBA) throws SQLException, Exception {
		String sql = "INSERT INTO cba(empId, performanceYear, myInputs, remarks) "
						+ "VALUES (?,?,?,?)";
		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	    ps.setInt(1, empCBA.getEmpId());
	    ps.setInt(2, empCBA.getPerformanceYear());
	    ps.setString(3, empCBA.getMyInputs());
	    ps.setString(4, empCBA.getRemarks());
	    
	    ps.executeUpdate();
	    ResultSet keyResultSet = ps.getGeneratedKeys();
	     int generatedAutoIncrementId = 0;
	     if (keyResultSet.next()) {
	      	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
	      	empCBA.setCbaId(generatedAutoIncrementId);
	      	conn.commit();
	      }
		
	     ps.close();
	     keyResultSet.close();

	     return generatedAutoIncrementId;
	}

	    
	/*do not add delete method*/
	
	
/**
 * TG
 * @param empCBA
 * @return
 * @throws SQLException
 * @throws Exception
 */
    public int update(EmployeeCBA empCBA) throws SQLException, Exception {		
		String sql = "UPDATE cba SET empId=?, performanceYear=?, myInputs=?, remarks=? where cbaId=?";
		PreparedStatement ps  = conn.prepareStatement(sql);
	    ps.setInt(1, empCBA.getEmpId());
	    ps.setInt(2, empCBA.getPerformanceYear());
	    ps.setString(3, empCBA.getMyInputs());
	    ps.setString(4, empCBA.getRemarks());
	    ps.setInt(5, empCBA.getCbaId());
		int count = ps.executeUpdate();
		conn.commit();
		ps.close();
		
		return count;

 	}

    
    
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}
}
