package dai.hris.dao.filemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.DBConstants;
import dai.hris.model.EmployeeWorkHistory;

/**
 * 
 * @author danielpadilla
 *
 */
public class EmployeeWorkHistoryDAO {
	Connection conn = null;
	
	public EmployeeWorkHistoryDAO() {
		
    	try {

    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("EmployeeWorkHistoryDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("EmployeeWorkHistoryDAO :" + e.getMessage());
		}
	}
	

	/**
	 * tested 062315 TG
	 * @param empWorkHistoryId
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public EmployeeWorkHistory getEmployeeWorkHistoryByEmpWorkHistoryId(int empWorkHistoryId) throws SQLException, Exception {			    		
		String sql = "SELECT empWorkHistoryId, empId, yrsService, employerName, address, countryId, industry, position, remarks, salary, salaryGrade, stepIncrement FROM empWorkHistory where empWorkHistoryId = ?";
		EmployeeWorkHistory employeeWorkHistory = null;
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, empWorkHistoryId);
		
	    ResultSet rs = ps.executeQuery();
	    
	    while (rs.next()) {
	    	employeeWorkHistory = new EmployeeWorkHistory();
	    	employeeWorkHistory.setAddress(rs.getString("address"));
	    	employeeWorkHistory.setCountryId(rs.getInt("countryId"));
	    	employeeWorkHistory.setEmpId(rs.getInt("empId"));
	    	employeeWorkHistory.setEmployerName(rs.getString("employerName"));
	    	employeeWorkHistory.setEmpWorkHistoryId(rs.getInt("empWorkHistoryId"));
	    	employeeWorkHistory.setIndustry(rs.getString("industry"));
	    	employeeWorkHistory.setPosition(rs.getString("position"));
	    	employeeWorkHistory.setRemarks(rs.getString("remarks"));
	    	employeeWorkHistory.setYrsService(rs.getInt("yrsService"));
	    	employeeWorkHistory.setSalary(rs.getBigDecimal("salary"));
	    	employeeWorkHistory.setSalaryGrade(rs.getString("salaryGrade"));
	    	employeeWorkHistory.setStepIncrement(rs.getString("stepIncrement"));
	    }
	    
	    ps.close();
	    rs.close();      
	    return employeeWorkHistory;		
	}
	

	/**
	 * tested 062315 TG
	 * @param empId
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public ArrayList<EmployeeWorkHistory> getEmployeeWorkHistoryListByEmpId(int empId) throws SQLException, Exception {			    		
		String sql = "SELECT empWorkHistoryId, empId, yrsService, employerName, address, countryId, industry, position, remarks, salary, salaryGrade, stepIncrement FROM empWorkHistory where empId = ?";		
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, empId);
	
	    ResultSet rs = ps.executeQuery();
	    ArrayList<EmployeeWorkHistory> list = new ArrayList<EmployeeWorkHistory>();
	    
	    while (rs.next()) {
	    	EmployeeWorkHistory employeeWorkHistory = new EmployeeWorkHistory();
	    	employeeWorkHistory.setAddress(rs.getString("address"));
	    	employeeWorkHistory.setCountryId(rs.getInt("countryId"));
	    	employeeWorkHistory.setEmpId(rs.getInt("empId"));
	    	employeeWorkHistory.setEmployerName(rs.getString("employerName"));
	    	employeeWorkHistory.setEmpWorkHistoryId(rs.getInt("empWorkHistoryId"));
	    	employeeWorkHistory.setIndustry(rs.getString("industry"));
	    	employeeWorkHistory.setPosition(rs.getString("position"));
	    	employeeWorkHistory.setRemarks(rs.getString("remarks"));
	    	employeeWorkHistory.setYrsService(rs.getInt("yrsService"));
	    	employeeWorkHistory.setSalary(rs.getBigDecimal("salary"));
	    	employeeWorkHistory.setSalaryGrade(rs.getString("salaryGrade"));
	    	employeeWorkHistory.setStepIncrement(rs.getString("stepIncrement"));
	    	list.add(employeeWorkHistory);
	    }
	    
	    ps.close();
	    rs.close();      
	    return list;		
	}
	    
	/**
	 * tested ok 062315 TG
	 * @param employeeOutOfOffice
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public int add(EmployeeWorkHistory employeeWorkHistory) throws SQLException, Exception {
		String sql = "INSERT INTO empWorkHistory (empId, yrsService, employerName, address, countryId, industry, position, remarks, salary, salaryGrade, stepIncrement) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	    ps.setInt(1, employeeWorkHistory.getEmpId());
	    ps.setInt(2, employeeWorkHistory.getYrsService());
	    ps.setString(3, employeeWorkHistory.getEmployerName());
	    ps.setString(4, employeeWorkHistory.getAddress());
	    ps.setInt(5, employeeWorkHistory.getCountryId());
	    ps.setString(6, employeeWorkHistory.getIndustry());
	    ps.setString(7, employeeWorkHistory.getPosition());
	    ps.setString(8, employeeWorkHistory.getRemarks());
	    ps.setBigDecimal(9, employeeWorkHistory.getSalary());
	    ps.setString(10, employeeWorkHistory.getSalaryGrade());
	    ps.setString(11, employeeWorkHistory.getStepIncrement());
	    
	    int count = ps.executeUpdate();
	    int status = 0;  
	    ResultSet keyResultSet = ps.getGeneratedKeys();
	     int generatedAutoIncrementId = 0;
	     if (keyResultSet.next()) {
	      	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
	      	employeeWorkHistory.setEmpWorkHistoryId(generatedAutoIncrementId);
	      	conn.commit();
	      }
		
	     ps.close();
	     keyResultSet.close();
		 if (count == 1) {
			 status = generatedAutoIncrementId;
		 }		
	    return status;
	}

	    
	/*do not add delete method*/
	
	

    public int update(EmployeeWorkHistory employeeWorkHistory) throws SQLException, Exception {		
		String sql = "UPDATE empWorkHistory SET empId=?, yrsService=?, employerName=?, address=?, countryId=?, industry=?, position=?, remarks=?, salary=?, salaryGrade=?, stepIncrement=? WHERE empWorkHistoryId = ?";
		PreparedStatement ps  = conn.prepareStatement(sql);
	    ps.setInt(1, employeeWorkHistory.getEmpId());
	    ps.setInt(2, employeeWorkHistory.getYrsService());
	    ps.setString(3, employeeWorkHistory.getEmployerName());
	    ps.setString(4, employeeWorkHistory.getAddress());
	    ps.setInt(5, employeeWorkHistory.getCountryId());
	    ps.setString(6, employeeWorkHistory.getIndustry());
	    ps.setString(7, employeeWorkHistory.getPosition());
	    ps.setString(8, employeeWorkHistory.getRemarks());
	    ps.setBigDecimal(9, employeeWorkHistory.getSalary());
	    ps.setString(10, employeeWorkHistory.getSalaryGrade());
	    ps.setString(11, employeeWorkHistory.getStepIncrement());
	    ps.setInt(9, employeeWorkHistory.getEmpWorkHistoryId());
		int count = ps.executeUpdate();
		conn.commit();
		ps.close();
		return count;

 	}
    
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}
}
