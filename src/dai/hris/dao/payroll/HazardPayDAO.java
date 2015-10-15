package dai.hris.dao.payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dai.hris.dao.DBConstants;
import dai.hris.model.HazardPay;

public class HazardPayDAO {

	private Connection conn = null;

	public HazardPayDAO() {    	
		try {
			/* MS SQL CODE */    		
			Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
			conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
			conn.setAutoCommit(false);
		} catch (SQLException sqle) {
			System.out.println("PayrollPeriodDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("PayrollPeriodDAO :" + e.getMessage());
		}
	}

	public void add(HazardPay hazardPay) throws SQLException, Exception {
		String sql = "insert into HazardPay "
				+ " (salaryGrade, basicSalary, hazardPay, withHoldingTax, eamcDeduction, netAmountDue, year, month, remarks, empId) "
				+ " values (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setInt(1, hazardPay.getSalaryGrade());
        ps.setBigDecimal(2, hazardPay.getBasicSalary());
        ps.setBigDecimal(3, hazardPay.getHazardPay());
        ps.setBigDecimal(4, hazardPay.getWithHoldingTax());
        ps.setBigDecimal(5, hazardPay.getEamcDeduction());
        ps.setBigDecimal(6, hazardPay.getNetAmountDue());
        ps.setInt(7, hazardPay.getYear());
        ps.setInt(8, hazardPay.getMonth());
        ps.setString(9, hazardPay.getRemarks());
        ps.setInt(10, hazardPay.getEmpId());
        ps.executeUpdate();
          
        ResultSet keyResultSet = ps.getGeneratedKeys();
        int generatedAutoIncrementId = 0;
        if (keyResultSet.next()) {
        	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
        	conn.commit();
        }

        keyResultSet.close();
        ps.close();
        conn.close();
	}

	public void update(HazardPay hazardPay) throws SQLException, Exception {
		String sql = "update HazardPay set "
				+ " salaryGrade=?, basicSalary=?, hazardPay=?, withHoldingTax=?, eamcDeduction=?, netAmountDue=?, year=?, month=?, remarks=?, empId=? "
				+ " where hazardPayId=?";
		PreparedStatement ps  = conn.prepareStatement(sql);
		ps.setInt(1, hazardPay.getSalaryGrade());
        ps.setBigDecimal(2, hazardPay.getBasicSalary());
        ps.setBigDecimal(3, hazardPay.getHazardPay());
        ps.setBigDecimal(4, hazardPay.getWithHoldingTax());
        ps.setBigDecimal(5, hazardPay.getEamcDeduction());
        ps.setBigDecimal(6, hazardPay.getNetAmountDue());
        ps.setInt(7, hazardPay.getYear());
        ps.setInt(8, hazardPay.getMonth());
        ps.setString(9, hazardPay.getRemarks());
        ps.setInt(10, hazardPay.getEmpId());
        ps.setInt(11, hazardPay.getHazardPayId());
 		ps.executeUpdate();
 		
 		conn.commit();
 		ps.close();
 		conn.close();
	}

	public void delete(HazardPay hazardPay) throws SQLException, Exception {
		String sql = "delete from HazardPay where hazardPayId=?";
		PreparedStatement ps  = conn.prepareStatement(sql);
		ps.setInt(1, hazardPay.getHazardPayId());
        ps.executeUpdate();
        
        conn.commit();
        ps.close();
        conn.close();
	}
	
	public void closeConnection() throws SQLException, Exception {
		conn.close();
	}
	
	public int[] batchUpdate(List<HazardPay> hpList) throws SQLException, Exception {
		int[] result = null;
		String sql = "update HazardPay set "
				+ " salaryGrade=?, basicSalary=?, hazardPay=?, withHoldingTax=?, eamcDeduction=?, netAmountDue=?, year=?, month=?, remarks=?, empId=? "
				+ " where hazardPayId=?";
		PreparedStatement ps  = conn.prepareStatement(sql);
		for (int i = 0; i < hpList.size(); i++) {
			HazardPay hp = hpList.get(i);
			ps.setInt(1, hp.getSalaryGrade());
			ps.setBigDecimal(2, hp.getBasicSalary());
			ps.setBigDecimal(3, hp.getHazardPay());
			ps.setBigDecimal(4, hp.getWithHoldingTax());
			ps.setBigDecimal(5, hp.getEamcDeduction());
			ps.setBigDecimal(6, hp.getNetAmountDue());
			ps.setInt(7, hp.getYear());
			ps.setInt(8, hp.getMonth());
			ps.setString(9, hp.getRemarks());
			ps.setInt(10, hp.getEmpId());
			ps.setInt(11, hp.getHazardPayId());
			ps.addBatch();
		}
		result = ps.executeBatch();
		conn.commit();
        ps.close();
		return result;
	}
	
    public int[] batchInsert(List<HazardPay> hpList) throws SQLException, Exception {
    	int[] result = null;
    	String sql = "insert into HazardPay "
				+ " (salaryGrade, basicSalary, hazardPay, withHoldingTax, eamcDeduction, netAmountDue, year, month, remarks, empId) "
				+ " values (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		for (int i =0; i < hpList.size(); i++) {
			HazardPay hp = hpList.get(i);
			ps.setInt(1, hp.getSalaryGrade());
			ps.setBigDecimal(2, hp.getBasicSalary());
			ps.setBigDecimal(3, hp.getHazardPay());
			ps.setBigDecimal(4, hp.getWithHoldingTax());
			ps.setBigDecimal(5, hp.getEamcDeduction());
			ps.setBigDecimal(6, hp.getNetAmountDue());
			ps.setInt(7, hp.getYear());
			ps.setInt(8, hp.getMonth());
			ps.setString(9, hp.getRemarks());
			ps.setInt(10, hp.getEmpId());
			ps.addBatch();
		}
		result = ps.executeBatch();
		conn.commit();
        ps.close();
		return result;
    }
    
    public List<HazardPay> getAllByEmployeeId(int empId) throws SQLException, Exception {
    	List<HazardPay> resultList = new ArrayList<HazardPay>();
    	String sql = "select * from HazardPay where empId = ?";
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setInt(1, empId);
    	
    	ResultSet rs = ps.executeQuery();
    	while (rs.next()) {
    		HazardPay hp = new HazardPay();
    		hp.setHazardPayId(rs.getInt("hazardPayId"));
    		hp.setSalaryGrade(rs.getInt("salaryGrade"));
    		hp.setBasicSalary(rs.getBigDecimal("basicSalary"));
    		hp.setHazardPay(rs.getBigDecimal("hazardPay"));
    		hp.setWithHoldingTax(rs.getBigDecimal("withHoldingTax"));
    		hp.setEamcDeduction(rs.getBigDecimal("eamcDeduction"));
    		hp.setNetAmountDue(rs.getBigDecimal("netAmountDue"));
    		hp.setYear(rs.getInt("year"));
    		hp.setMonth(rs.getInt("month"));
    		hp.setRemarks(rs.getString("remarks"));
    		hp.setEmpId(rs.getInt("empId"));
    		resultList.add(hp);
    	}
    	rs.close();
    	ps.close();
    	return resultList;
    }
    
    public List<HazardPay> getAllByDepartmentId(int depId) throws SQLException, Exception {
    	List<HazardPay> resultList = new ArrayList<HazardPay>();
    	String sql = "select * from HazardPay "
    			+ " where empId in"
    			+ " (select empId from Employee where jobTitleId = ?) ";
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setInt(1, depId);
    	
    	ResultSet rs = ps.executeQuery();
    	while (rs.next()) {
    		HazardPay hp = new HazardPay();
    		hp.setHazardPayId(rs.getInt("hazardPayId"));
    		hp.setSalaryGrade(rs.getInt("salaryGrade"));
    		hp.setBasicSalary(rs.getBigDecimal("basicSalary"));
    		hp.setHazardPay(rs.getBigDecimal("hazardPay"));
    		hp.setWithHoldingTax(rs.getBigDecimal("withHoldingTax"));
    		hp.setEamcDeduction(rs.getBigDecimal("eamcDeduction"));
    		hp.setNetAmountDue(rs.getBigDecimal("netAmountDue"));
    		hp.setYear(rs.getInt("year"));
    		hp.setMonth(rs.getInt("month"));
    		hp.setRemarks(rs.getString("remarks"));
    		hp.setEmpId(rs.getInt("empId"));
    		resultList.add(hp);
    	}
    	rs.close();
    	ps.close();
    	return resultList;
    }
}
