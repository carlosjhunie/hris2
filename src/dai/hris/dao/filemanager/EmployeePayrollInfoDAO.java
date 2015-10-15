package dai.hris.dao.filemanager;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.DBConstants;
import dai.hris.model.EmployeePayrollInfo;

public class EmployeePayrollInfoDAO {
	Connection conn = null;
	
	public EmployeePayrollInfoDAO() {
		
    	try {

    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("EmployeePayrollInfoDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("EmployeePayrollInfoDAO :" + e.getMessage());
		}
	}
	


	public EmployeePayrollInfo getEmployeePayrollInfoByEmpId(int empId) throws SQLException, Exception {			    		
		String sql = " select * from empPayrollInfo where empId = ?";
		EmployeePayrollInfo employeePayrollInfo = null;
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, empId);
		
	    ResultSet rs = ps.executeQuery();
	    
	    while (rs.next()) {
	    	employeePayrollInfo = new EmployeePayrollInfo();
	    	
	    	employeePayrollInfo.setBan(rs.getString("ban"));
	    	employeePayrollInfo.setBankNameBan(rs.getString("bankNameBan"));
	    	employeePayrollInfo.setCola(rs.getBigDecimal("cola"));
	    	employeePayrollInfo.setDailyRate(rs.getBigDecimal("dailyRate"));
	    	employeePayrollInfo.setEmpId(rs.getInt("empId"));
	    	employeePayrollInfo.setEmpPayrollInfoId(rs.getInt("empPayrollInfoId"));
	    	employeePayrollInfo.setFoodAllowance(rs.getBigDecimal("foodAllowance"));
	    	employeePayrollInfo.setHourlyRate(rs.getBigDecimal("hourlyRate"));
	    	employeePayrollInfo.setMonthlyRate(rs.getBigDecimal("monthlyRate"));
	    	employeePayrollInfo.setShiftingScheduleId(rs.getInt("shiftingScheduleId"));
	    	employeePayrollInfo.setPayrollType(rs.getString("payrollType"));	    	
	    	employeePayrollInfo.setTaxShield(rs.getBigDecimal("taxShield"));
	    	employeePayrollInfo.setTransAllowance(rs.getBigDecimal("transAllowance"));
	    	
	    	employeePayrollInfo.setGsisEmployeeShare(rs.getBigDecimal("gsisEmployeeShare"));
	    	employeePayrollInfo.setGsisEmployerShare(rs.getBigDecimal("gsisEmployerShare"));
	    	employeePayrollInfo.setPagibigEmployeeShare(rs.getBigDecimal("pagibigEmployeeShare"));
	    	employeePayrollInfo.setPagibigEmployerShare(rs.getBigDecimal("pagibigEmployerShare"));
	    	employeePayrollInfo.setPhilhealthEmployeeShare(rs.getBigDecimal("philhealthEmployeeShare"));
	    	employeePayrollInfo.setPhilhealthEmployerShare(rs.getBigDecimal("philhealthEmployerShare"));
	    	
	    }
	    
	    ps.close();
	    rs.close();      
	    return employeePayrollInfo;		
	}
	
	public boolean isEmployeePayrollInfoExist(int empId) throws SQLException, Exception {			    		
		String sql = " select * from empPayrollInfo where empId = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, empId);
		
	    ResultSet rs = ps.executeQuery();
	    
	    if (rs.next()) {
	    	return true;
	    }
	    
	    ps.close();
	    rs.close();      
	    return false;		
	}
	

	public EmployeePayrollInfo getEmployeePayrollInfo(int empId) throws SQLException, Exception {			    		
		String sql = "select * from empPayrollInfo where empId = ?";	
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, empId);
	
	    ResultSet rs = ps.executeQuery();
	    EmployeePayrollInfo employeePayrollInfo = new EmployeePayrollInfo();
	    
	    while (rs.next()) {
	    	employeePayrollInfo = new EmployeePayrollInfo();
	    	employeePayrollInfo.setShiftingScheduleId(rs.getInt("shiftingScheduleId"));
	    	employeePayrollInfo.setBan(rs.getString("ban"));
	    	employeePayrollInfo.setBankNameBan(rs.getString("bankNameBan"));
	    	employeePayrollInfo.setCola(rs.getBigDecimal("cola"));
	    	employeePayrollInfo.setDailyRate(rs.getBigDecimal("dailyRate"));
	    	employeePayrollInfo.setEmpId(rs.getInt("empId"));
	    	employeePayrollInfo.setEmpPayrollInfoId(rs.getInt("empPayrollInfoId"));
	    	employeePayrollInfo.setFoodAllowance(rs.getBigDecimal("foodAllowance"));
	    	employeePayrollInfo.setHourlyRate(rs.getBigDecimal("hourlyRate"));
	    	employeePayrollInfo.setMonthlyRate(rs.getBigDecimal("monthlyRate"));	    	
	    	employeePayrollInfo.setPayrollType(rs.getString("payrollType"));	    	
	    	employeePayrollInfo.setTaxShield(rs.getBigDecimal("taxShield"));
	    	employeePayrollInfo.setTransAllowance(rs.getBigDecimal("transAllowance"));
	    	employeePayrollInfo.setGsisEmployeeShare(rs.getBigDecimal("gsisEmployeeShare"));
	    	employeePayrollInfo.setGsisEmployerShare(rs.getBigDecimal("gsisEmployerShare"));
	    	employeePayrollInfo.setPagibigEmployeeShare(rs.getBigDecimal("pagibigEmployeeShare"));
	    	employeePayrollInfo.setPagibigEmployerShare(rs.getBigDecimal("pagibigEmployerShare"));
	    	employeePayrollInfo.setPhilhealthEmployeeShare(rs.getBigDecimal("philhealthEmployeeShare"));
	    	employeePayrollInfo.setPhilhealthEmployerShare(rs.getBigDecimal("philhealthEmployerShare"));
	    }
	    
	    ps.close();
	    rs.close();      
	    return employeePayrollInfo;		
	}
	    

	public int add(EmployeePayrollInfo employeePayrollInfo) throws SQLException, Exception {
		String sql = "INSERT INTO empPayrollInfo (empId, monthlyRate, dailyRate, hourlyRate, shiftingScheduleId, foodAllowance, "
				+ "	cola, taxShield, transAllowance, payrollType, ban, bankNameBan, gsisEmployeeShare, gsisEmployerShare, pagibigEmployeeShare, pagibigEmployerShare, "
				+ " philhealthEmployeeShare, philhealthEmployerShare) VALUES (?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	    
		int index = 1;
		
		ps.setInt(index++, employeePayrollInfo.getEmpId());
	    ps.setBigDecimal(index++, employeePayrollInfo.getMonthlyRate());
	    ps.setBigDecimal(index++, employeePayrollInfo.getDailyRate());
	    ps.setBigDecimal(index++, employeePayrollInfo.getHourlyRate());
	    ps.setInt(index++, employeePayrollInfo.getShiftingScheduleId());
	    ps.setBigDecimal(index++, employeePayrollInfo.getFoodAllowance());	    
	    ps.setBigDecimal(index++, employeePayrollInfo.getCola());
	    ps.setBigDecimal(index++, employeePayrollInfo.getTaxShield());
	    ps.setBigDecimal(index++, employeePayrollInfo.getTransAllowance());	    
	    ps.setString(index++, employeePayrollInfo.getPayrollType());
	    ps.setString(index++, employeePayrollInfo.getBan());
	    ps.setString(index++, employeePayrollInfo.getBankNameBan());
	    ps.setBigDecimal(index++, employeePayrollInfo.getGsisEmployeeShare());
		ps.setBigDecimal(index++, employeePayrollInfo.getGsisEmployerShare());
		ps.setBigDecimal(index++, employeePayrollInfo.getPagibigEmployeeShare());
		ps.setBigDecimal(index++, employeePayrollInfo.getPagibigEmployerShare());
		ps.setBigDecimal(index++, employeePayrollInfo.getPhilhealthEmployeeShare());
		ps.setBigDecimal(index++, employeePayrollInfo.getPhilhealthEmployerShare());
	    
	    
	    int count = ps.executeUpdate();
	    int status = 0;  
	    ResultSet keyResultSet = ps.getGeneratedKeys();
	    int generatedAutoIncrementId = 0;
	    if (keyResultSet.next()) {
	      	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
	      	employeePayrollInfo.setEmpPayrollInfoId(generatedAutoIncrementId);
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
	
	
	/**
	 * tested ok 062315 TG
	 * @param employeeOutOfOffice
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
    public int update(EmployeePayrollInfo employeePayrollInfo) throws SQLException, Exception {		
		String sql = "UPDATE empPayrollInfo SET monthlyRate=?, dailyRate=?, hourlyRate=?, shiftingScheduleId=?, foodAllowance=?, "
				+ "		cola=?, taxShield=?, transAllowance=?, payrollType=?, ban=?, bankNameBan=?, gsisEmployeeShare=?, gsisEmployerShare=?, pagibigEmployeeShare=?, pagibigEmployerShare=?, "
				+ "     philhealthEmployeeShare=?, philhealthEmployerShare=? WHERE empId = ?";
		PreparedStatement ps  = conn.prepareStatement(sql);
	    
		int index = 1;
		
	    ps.setBigDecimal(index++, employeePayrollInfo.getMonthlyRate());
	    ps.setBigDecimal(index++, employeePayrollInfo.getDailyRate());
	    ps.setBigDecimal(index++, employeePayrollInfo.getHourlyRate());
	    ps.setInt(index++, employeePayrollInfo.getShiftingScheduleId());
	    ps.setBigDecimal(index++, employeePayrollInfo.getFoodAllowance());	    
	    ps.setBigDecimal(index++, employeePayrollInfo.getCola());
	    ps.setBigDecimal(index++, employeePayrollInfo.getTaxShield());
	    ps.setBigDecimal(index++, employeePayrollInfo.getTransAllowance());	    
	    ps.setString(index++, employeePayrollInfo.getPayrollType());
	    ps.setString(index++, employeePayrollInfo.getBan());
	    ps.setString(index++, employeePayrollInfo.getBankNameBan());
	    ps.setBigDecimal(index++, employeePayrollInfo.getGsisEmployeeShare());
		ps.setBigDecimal(index++, employeePayrollInfo.getGsisEmployerShare());
		ps.setBigDecimal(index++, employeePayrollInfo.getPagibigEmployeeShare());
		ps.setBigDecimal(index++, employeePayrollInfo.getPagibigEmployerShare());
		ps.setBigDecimal(index++, employeePayrollInfo.getPhilhealthEmployeeShare());
		ps.setBigDecimal(index++, employeePayrollInfo.getPhilhealthEmployerShare());
	    ps.setInt(index++, employeePayrollInfo.getEmpId());	    
		int count = ps.executeUpdate();
		conn.commit();
		ps.close();
		return count;

 	}
    
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}
}
