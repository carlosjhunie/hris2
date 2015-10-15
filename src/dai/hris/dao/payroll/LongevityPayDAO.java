package dai.hris.dao.payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dai.hris.dao.DBConstants;
import dai.hris.model.LongevityPay;

public class LongevityPayDAO {

	private Connection conn = null;

	public LongevityPayDAO() {    	
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

	public void add(LongevityPay lp) throws SQLException, Exception {
		String sql = "insert into LongevityPay "
				+ " (salaryGrade, basicSalary, netAmountDue, year, month, remarks, empId) "
				+ " values (?,?,?,?,?,?,?) ";
		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setInt(1, lp.getSalaryGrade());
        ps.setBigDecimal(2, lp.getBasicSalary());
        ps.setBigDecimal(3, lp.getNetAmountDue());
        ps.setInt(4, lp.getYear());
        ps.setInt(5, lp.getMonth());
        ps.setString(6, lp.getRemarks());
        ps.setInt(7, lp.getEmpId());
        ps.executeUpdate();
          
        ResultSet keyResultSet = ps.getGeneratedKeys();
        int generatedAutoIncrementId = 0;
        if (keyResultSet.next()) {
        	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
        	conn.commit();
        }

        ps.close();
        keyResultSet.close();
        conn.close();
	}

	public void update(LongevityPay lp) throws SQLException, Exception {
		String sql = "update LongevityPay set "
				+ " salaryGrade=?, basicSalary=?, netAmountDue=?, year=?, month=?, remarks=?, empId=? "
				+ " where longevityPayId=?";
		PreparedStatement ps  = conn.prepareStatement(sql);
		ps.setInt(1, lp.getSalaryGrade());
        ps.setBigDecimal(2, lp.getBasicSalary());
        ps.setBigDecimal(3, lp.getNetAmountDue());
        ps.setInt(4, lp.getYear());
        ps.setInt(5, lp.getMonth());
        ps.setString(6, lp.getRemarks());
        ps.setInt(7, lp.getEmpId());
        ps.setInt(8, lp.getLongevityPayId());
 		ps.executeUpdate();
 		
 		conn.commit();
 		ps.close();
 		conn.close();
	}

	public void delete(LongevityPay lp) throws SQLException, Exception {
		String sql = "delete from LongevityPay where longevityPayId=?";
		PreparedStatement ps  = conn.prepareStatement(sql);
		ps.setInt(1, lp.getLongevityPayId());
        ps.executeUpdate();
        
        conn.commit();
        ps.close();
        conn.close();
	}
	
	public void closeConnection() throws SQLException, Exception {
		conn.close();
	}
	
	public int[] batchUpdate(List<LongevityPay> lpList) throws SQLException, Exception {
		int[] result = null;
		String sql = "update LongevityPay set "
				+ " salaryGrade=?, basicSalary=?, netAmountDue=?, year=?, month=?, remarks=?, empId=? "
				+ " where longevityPayId=?";
		PreparedStatement ps  = conn.prepareStatement(sql);
		for (int i = 0; i < lpList.size(); i++) {
			LongevityPay lp = lpList.get(i);
			ps.setInt(1, lp.getSalaryGrade());
			ps.setBigDecimal(2, lp.getBasicSalary());
			ps.setBigDecimal(3, lp.getNetAmountDue());
			ps.setInt(4, lp.getYear());
			ps.setInt(5, lp.getMonth());
			ps.setString(6, lp.getRemarks());
			ps.setInt(7, lp.getEmpId());
			ps.setInt(8, lp.getLongevityPayId());
			ps.addBatch();
		}
		result = ps.executeBatch();
		conn.commit();
		ps.close();
		return result;
	}
	
    public int[] batchInsert(List<LongevityPay> lpList) throws SQLException, Exception {
    	int[] result = null;
    	String sql = "insert into LongevityPay "
				+ " (salaryGrade, basicSalary, netAmountDue, year, month, remarks, empId) "
				+ " values (?,?,?,?,?,?,?) ";
		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		for (int i = 0; i < lpList.size(); i++) {
			LongevityPay lp = lpList.get(i);
			ps.setInt(1, lp.getSalaryGrade());
			ps.setBigDecimal(2, lp.getBasicSalary());
			ps.setBigDecimal(3, lp.getNetAmountDue());
			ps.setInt(4, lp.getYear());
			ps.setInt(5, lp.getMonth());
			ps.setString(6, lp.getRemarks());
			ps.setInt(7, lp.getEmpId());
			ps.addBatch();
		}
		result = ps.executeBatch();
		conn.commit();
		ps.close();
		return result;
    }
    
    public List<LongevityPay> getAllByEmployeeId(int empId) throws SQLException, Exception {
    	List<LongevityPay> resultList = new ArrayList<LongevityPay>();
    	String sql = "select * from LongevityPay where empId = ?";
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setInt(1, empId);
    	
    	ResultSet rs = ps.executeQuery();
    	while (rs.next()) {
    		LongevityPay lp = new LongevityPay();
    		lp.setSalaryGrade(rs.getInt("salaryGrade"));
    		lp.setBasicSalary(rs.getBigDecimal("basicSalary"));
    		lp.setNetAmountDue(rs.getBigDecimal("netAmountDue"));
    		lp.setYear(rs.getInt("year"));
    		lp.setMonth(rs.getInt("month"));
    		lp.setRemarks(rs.getString("remarks"));
    		lp.setEmpId(rs.getInt("empId"));
    		resultList.add(lp);
    	}
    	rs.close();
    	ps.close();
    	return resultList;
    }
    
    public List<LongevityPay> getAllByJobTitleId(int jtId) throws SQLException, Exception {
    	List<LongevityPay> resultList = new ArrayList<LongevityPay>();
    	String sql = "select * from LongevityPay "
    			+ " where empId in "
    			+ " (select empId from Employee where jobTitleId = ?) ";
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setInt(1, jtId);
    	
    	ResultSet rs = ps.executeQuery();
    	while (rs.next()) {
    		LongevityPay lp = new LongevityPay();
    		lp.setSalaryGrade(rs.getInt("salaryGrade"));
    		lp.setBasicSalary(rs.getBigDecimal("basicSalary"));
    		lp.setNetAmountDue(rs.getBigDecimal("netAmountDue"));
    		lp.setYear(rs.getInt("year"));
    		lp.setMonth(rs.getInt("month"));
    		lp.setRemarks(rs.getString("remarks"));
    		lp.setEmpId(rs.getInt("empId"));
    		resultList.add(lp);
    	}
    	rs.close();
    	ps.close();
    	return resultList;
    }
}
