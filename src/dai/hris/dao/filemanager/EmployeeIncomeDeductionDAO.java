package dai.hris.dao.filemanager;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.DBConstants;
import dai.hris.model.EmployeeDeduction;
import dai.hris.model.EmployeeIncome;

/**
 * get for income and deductions tied to employee!
 * @author playerone
 *
 */
public class EmployeeIncomeDeductionDAO {
	Connection conn = null;
	
	public EmployeeIncomeDeductionDAO() {
		
    	try {

    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("EmployeeIncomeDeductionDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("EmployeeIncomeDeductionDAO :" + e.getMessage());
		}
	}
	
	


	public ArrayList<EmployeeDeduction> getEmployeeDeductionByEmpId(int empId) throws SQLException, Exception {			    		
		//String sql = "SELECT ei.empDeductionId, ei.empId, ei.deductionId, i.deductionName, ei.getActive, ei.remarks " +
		//				"FROM empDeduction ei, deduction i  WHERE ei.deductionId = i.deductionId and ei.empId = ?";
		String sql = "SELECT * FROM empDeduction WHERE empId = ?";
		ArrayList<EmployeeDeduction> list = new ArrayList<EmployeeDeduction>();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, empId);		
	    ResultSet rs = ps.executeQuery();	    
	    while (rs.next()) {
	    	EmployeeDeduction employeeDeduction = new EmployeeDeduction();
	    	employeeDeduction.setActive(rs.getInt("active"));
	    	employeeDeduction.setDeductionId(rs.getInt("deductionId"));
	    	employeeDeduction.setEmpDeductionId(rs.getInt("empDeductionId"));
	    	employeeDeduction.setEmpId(rs.getInt("empId"));
	    	employeeDeduction.setRemarks(rs.getString("remarks"));
	    	//employeeDeduction.setDeductionName(rs.getString("deductionName"));
	    	list.add(employeeDeduction);
	    }	    
	    ps.close();
	    rs.close();      
	    return list;		
	}
	
	
	public ArrayList<EmployeeIncome> getEmployeeIncomeByEmpId(int empId) throws SQLException, Exception {			    		
		//String sql = "SELECT ei.empIncomeId, ei.empId, ei.incomeId, i.incomeName, ei.getActive, ei.remarks " +
		//				"FROM empIncome ei, income i  WHERE ei.incomeId = i.incomeId and ei.empId = ?";
		String sql = "SELECT * FROM empIncome WHERE empId = ?";
		ArrayList<EmployeeIncome> list = new ArrayList<EmployeeIncome>();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, empId);		
	    ResultSet rs = ps.executeQuery();	    
	    while (rs.next()) {
	    	EmployeeIncome employeeIncome = new EmployeeIncome();
	    	employeeIncome.setActive(rs.getInt("active"));
	    	employeeIncome.setIncomeId(rs.getInt("incomeId"));
	    	employeeIncome.setEmpIncomeId(rs.getInt("empIncomeId"));
	    	employeeIncome.setEmpId(rs.getInt("empId"));
	    	employeeIncome.setRemarks(rs.getString("remarks"));
	    	//employeeIncome.setIncomeName(rs.getString("incomeName"));
	    	list.add(employeeIncome);
	    }	    
	    ps.close();
	    rs.close();      
	    return list;		
	}
	
	
    

	public int addIncome(EmployeeIncome employeeIncome) throws SQLException, Exception {
		String sql = "INSERT INTO empIncome (empId, incomeId, active, remarks) VALUES(?,?,?,?)";
		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	    
		int index = 1;		
		ps.setInt(index++, employeeIncome.getEmpId());
	    ps.setInt(index++, employeeIncome.getIncomeId());
	    ps.setInt(index++, employeeIncome.getActive());
	    ps.setString(index++, employeeIncome.getRemarks());
   
	    int count = ps.executeUpdate();
	    int status = 0;  
	    ResultSet keyResultSet = ps.getGeneratedKeys();
	    int generatedAutoIncrementId = 0;
	    if (keyResultSet.next()) {
	      	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
	      	employeeIncome.setEmpIncomeId(generatedAutoIncrementId);
	      	conn.commit();
	    }
		
	    ps.close();
	    keyResultSet.close();
		if (count == 1) {
			status = generatedAutoIncrementId;
		}		
	    return status;
	}
	
	public int addDeduction(EmployeeDeduction employeeDeduction) throws SQLException, Exception {
		String sql = "INSERT INTO empDeduction (empId, deductionId, active, remarks) VALUES(?,?,?,?)";
		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	    
		int index = 1;		
		ps.setInt(index++, employeeDeduction.getEmpId());
	    ps.setInt(index++, employeeDeduction.getDeductionId());
	    ps.setInt(index++, employeeDeduction.getActive());
	    ps.setString(index++, employeeDeduction.getRemarks());
   
	    int count = ps.executeUpdate();
	    int status = 0;  
	    ResultSet keyResultSet = ps.getGeneratedKeys();
	    int generatedAutoIncrementId = 0;
	    if (keyResultSet.next()) {
	      	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
	      	employeeDeduction.setEmpDeductionId(generatedAutoIncrementId);
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
	
    public int updateIncome(EmployeeIncome employeeIncome) throws SQLException, Exception {		
		String sql = "UPDATE empIncome SET empId=?, incomeId=?, active=?, remarks=? WHERE empIncomeId=?";
		PreparedStatement ps  = conn.prepareStatement(sql);
	    
		int index = 1;		
		ps.setInt(index++, employeeIncome.getEmpId());
	    ps.setInt(index++, employeeIncome.getIncomeId());
	    ps.setInt(index++, employeeIncome.getActive());
	    ps.setString(index++, employeeIncome.getRemarks());
	    ps.setInt(index++, employeeIncome.getEmpIncomeId());
	    
		int count = ps.executeUpdate();
		conn.commit();
		ps.close();
		return count;
 	}
    
    public int updateDeduction(EmployeeDeduction employeeDeduction) throws SQLException, Exception {		
		String sql = "UPDATE empDeduction SET empId=?, deductionId=?, active=?, remarks=? WHERE empDeductionId=?";
		PreparedStatement ps  = conn.prepareStatement(sql);
	    
		int index = 1;		
		ps.setInt(index++, employeeDeduction.getEmpId());
	    ps.setInt(index++, employeeDeduction.getDeductionId());
	    ps.setInt(index++, employeeDeduction.getActive());
	    ps.setString(index++, employeeDeduction.getRemarks());
	    ps.setInt(index++, employeeDeduction.getEmpDeductionId());
	    
		int count = ps.executeUpdate();
		conn.commit();
		ps.close();
		return count;
 	}
    
//    public BigDecimal getTaxableIncomeTotal(int empId, String isForSecondHalf) throws SQLException {
    public BigDecimal getTaxableIncomeTotal(int empId) throws SQLException {
    	BigDecimal total = new BigDecimal("0");
    	String sql = "select * from empIncome empin, income inc "
    			+ " where empin.incomeId=inc.incomeId "
    			+ " and inc.isTaxable='Y' "
    			+ " and empin.empId=? ";
    			//+ " and inc.isForSecondHalf=?";
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setInt(1, empId);
    	//ps.setString(2, isForSecondHalf);
    	ResultSet rs = ps.executeQuery();
    	while(rs.next()) {
    		total = total.add(rs.getBigDecimal("amount"));
    	}
    	rs.close();
    	ps.close();
    	return total;
    }
    
    public BigDecimal getNonTaxableIncomeTotal(int empId, String isForSecondHalf) throws SQLException {
    	BigDecimal total = new BigDecimal("0");
    	String sql = "select * from empIncome empin, income inc "
    			+ " where empin.incomeId=inc.incomeId "
    			+ " and inc.isTaxable='N' "
    			+ " and empin.empId=? "
    			+ " and inc.isForSecondHalf=?";
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setInt(1, empId);
    	ps.setString(2, isForSecondHalf);
    	ResultSet rs = ps.executeQuery();
    	while(rs.next()) {
    		total = total.add(rs.getBigDecimal("amount"));
    	}
    	rs.close();
    	ps.close();
    	return total;
    }
    
    public BigDecimal getDeductionTotal(int empId, String isForSecondHalf) throws SQLException {
    	BigDecimal total = new BigDecimal("0");
    	String sql = "select * from empDeduction empdd, deduction dd "
    			+ " where empdd.deductionId=dd.deductionId "
    			+ " and empdd.empId=? "
    			+ " and dd.isForSecondHalf=?";
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setInt(1, empId);
    	ps.setString(2, isForSecondHalf);
    	ResultSet rs = ps.executeQuery();
    	while(rs.next()) {
    		total = total.add(rs.getBigDecimal("amount"));
    	}
    	rs.close();
    	ps.close();
    	return total;
    }
    
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}
}
