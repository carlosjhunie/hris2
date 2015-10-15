package dai.hris.dao.payroll;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import dai.hris.dao.DBConstants;
import dai.hris.model.PayrollPeriod;

public class PayrollPeriodDAO {

	private Connection conn = null;

	public PayrollPeriodDAO() {    	
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

	public int saveOrUpdate(PayrollPeriod payrollPeriod) throws SQLException {
		int res = -1;
		if (payrollPeriod.getPayrollPeriodId() > 0) {
			String sql = "update PayrollPeriod set "
					+ " payYear=?, payMonth=?, payrollType=?, fromDate=?, toDate=?, payDate=?, payrollCode=?, numWorkDays=?, payPeriod=?, deductGovtFlag=?, isForSecondHalf=? "
					+ " where payrollPeriodId = ?";
			PreparedStatement ps  = conn.prepareStatement(sql);
			int index = 1;
			ps.setInt(index++, payrollPeriod.getPayYear());
	        ps.setInt(index++, payrollPeriod.getPayMonth());
	        ps.setString(index++, payrollPeriod.getPayrollType());
	        ps.setDate(index++, payrollPeriod.getFromDate());
	        ps.setDate(index++, payrollPeriod.getToDate());
	        ps.setDate(index++, payrollPeriod.getPayDate());
	        ps.setString(index++, payrollPeriod.getPayrollCode());
	        ps.setInt(index++, payrollPeriod.getNumWorkDays());
	        ps.setString(index++, payrollPeriod.getPayPeriod());
	        ps.setString(index++, payrollPeriod.getDeductGovtFlag().trim());
	        ps.setString(index++, payrollPeriod.getIsForSecondHalf());
	 		ps.setInt(index++, payrollPeriod.getPayrollPeriodId());
	 		res = ps.executeUpdate();
	 		conn.commit();
	 		ps.close();
		} else {
			
	 		String sql = "insert into PayrollPeriod "
					+ "(payYear, payMonth, payrollType, fromDate, toDate, payDate, payrollCode, numWorkDays, payPeriod, deductGovtFlag, isForSecondHalf, createdAt) " 
					+ " values (?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			int index = 1;
			ps.setInt(index++, payrollPeriod.getPayYear());
			ps.setInt(index++, payrollPeriod.getPayMonth());
			ps.setString(index++, payrollPeriod.getPayrollType());
			ps.setDate(index++, payrollPeriod.getFromDate());
			ps.setDate(index++, payrollPeriod.getToDate());
			ps.setDate(index++, payrollPeriod.getPayDate());
			ps.setString(index++, payrollPeriod.getPayrollCode());
			ps.setInt(index++, payrollPeriod.getNumWorkDays());
			ps.setString(index++, payrollPeriod.getPayPeriod());
			ps.setString(index++, payrollPeriod.getDeductGovtFlag().trim());
			ps.setString(index++, payrollPeriod.getIsForSecondHalf());
			ps.setDate(index++, new Date(System.currentTimeMillis()));
			res = ps.executeUpdate();
			ResultSet keyResultSet = ps.getGeneratedKeys();
			int generatedAutoIncrementId = 0;
			if (keyResultSet.next()) {
				generatedAutoIncrementId = (int) keyResultSet.getInt(1);
				payrollPeriod.setPayrollPeriodId(generatedAutoIncrementId);
				conn.commit();
			}
			keyResultSet.close();
			ps.close();
		}
		return res;
	}
	
	public PayrollPeriod getPayrollPeriodById(int id) throws SQLException {
		PayrollPeriod res = null;
		String sql = "select * from PayrollPeriod where payrollPeriodId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			res = new PayrollPeriod();
			res.setPayrollPeriodId(id);
			res.setPayYear(rs.getInt("payYear"));
			res.setPayMonth(rs.getInt("payMonth"));
			res.setPayrollType(rs.getString("payrollType"));
			res.setFromDate(rs.getDate("fromDate"));
			res.setToDate(rs.getDate("toDate"));
			res.setPayDate(rs.getDate("payDate"));
			res.setPayrollCode(rs.getString("payrollCode"));
			res.setNumWorkDays(rs.getInt("numWorkDays"));
			res.setPayPeriod(rs.getString("payPeriod"));
			res.setCreatedAt(rs.getDate("createdAt"));
			res.setUpdatedAt(rs.getDate("updatedAt"));
			res.setDeductGovtFlag(rs.getString("deductGovtFlag"));
			res.setIsForSecondHalf(rs.getString("isForSecondHalf"));
			res.setStatus(rs.getString("status"));
		}
		conn.close();
		return res;
	}
	
	public List<PayrollPeriod> getAll() throws SQLException {
		List<PayrollPeriod> result = new ArrayList<PayrollPeriod>();
		String sql = "select * from PayrollPeriod";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			PayrollPeriod pp = new PayrollPeriod();
			pp.setPayrollPeriodId(rs.getInt("payrollPeriodId"));
			pp.setPayYear(rs.getInt("payYear"));
			pp.setPayMonth(rs.getInt("payMonth"));
			pp.setPayrollType(rs.getString("payrollType"));
			pp.setFromDate(rs.getDate("fromDate"));
			pp.setToDate(rs.getDate("toDate"));
			pp.setPayDate(rs.getDate("payDate"));
			pp.setPayrollCode(rs.getString("payrollCode"));
			pp.setNumWorkDays(rs.getInt("numWorkDays"));
			pp.setPayPeriod(rs.getString("payPeriod"));
			pp.setLockedAt(rs.getDate("lockedAt"));
			pp.setCreatedAt(rs.getDate("createdAt"));
			pp.setUpdatedAt(rs.getDate("updatedAt"));
			pp.setDeductGovtFlag(rs.getString("deductGovtFlag"));
			pp.setIsForSecondHalf(rs.getString("isForSecondHalf"));
			pp.setStatus(rs.getString("status"));
			result.add(pp);
		}
		conn.close();
		//The Java 8 way of doing this is to use List.sort as follows:
		//personList.sort(Comparator.comparing(Person::getName));
		result.sort(Comparator.comparing(PayrollPeriod::getCreatedAt));
		System.out.println(result.size());
		return result;
	}
	
	public void closeConnection() throws SQLException {
		conn.close();
	}
	
	public int[] batchUpdate(List<PayrollPeriod> ppList) throws SQLException {
		int[] result = null;
		String sql = "update PayrollPeriod set "
				+ " payYear=?, payMonth=?, payrollType=?, fromDate=?, toDate=?, payDate=?, payrollCode=?, numWorkDays=?, payPeriod=?, deductGovtFlag=?, isForSecondHalf=?"
				+ " where payrollPeriodId = ?";
		PreparedStatement ps  = conn.prepareStatement(sql);
		for (int i = 0; i < ppList.size(); i++) {
			int index = 1;
			PayrollPeriod payrollPeriod = ppList.get(i);
			ps.setInt(index++, payrollPeriod.getPayYear());
			ps.setInt(index++, payrollPeriod.getPayMonth());
			ps.setString(index++, payrollPeriod.getPayrollType());
			ps.setDate(index++, payrollPeriod.getFromDate());
			ps.setDate(index++, payrollPeriod.getToDate());
			ps.setDate(index++, payrollPeriod.getPayDate());
			ps.setString(index++, payrollPeriod.getPayrollCode());
			ps.setInt(index++, payrollPeriod.getNumWorkDays());
			ps.setString(index++, payrollPeriod.getPayPeriod());
			ps.setString(index++, payrollPeriod.getDeductGovtFlag().trim());
			ps.setString(index++, payrollPeriod.getIsForSecondHalf());
			ps.setInt(index++, payrollPeriod.getPayrollPeriodId());
			ps.addBatch();
		}
 		result = ps.executeBatch();
 		
 		conn.commit();
 		ps.close();
		return result;
	}
	
    public int[] batchInsert(List<PayrollPeriod> ppList) throws SQLException {
    	int[] result = null;
    	String sql = "insert into PayrollPeriod "
				+ "(payYear, payMonth, payrollType, fromDate, toDate, payDate, payrollCode, numWorkDays, deductGovtFlag, payPeriod, isForSecondHalf, payPeriod) " 
				+ " values (?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		for (int i = 0; i < ppList.size(); i++) {
			int index = 1;
			PayrollPeriod payrollPeriod = ppList.get(i);
			ps.setInt(index++, payrollPeriod.getPayYear());
			ps.setInt(index++, payrollPeriod.getPayMonth());
			ps.setString(index++, payrollPeriod.getPayrollType());
			ps.setDate(index++, payrollPeriod.getFromDate());
			ps.setDate(index++, payrollPeriod.getToDate());
			ps.setDate(index++, payrollPeriod.getPayDate());
			ps.setString(index++, payrollPeriod.getPayrollCode());
			ps.setInt(index++, payrollPeriod.getNumWorkDays());
			ps.setString(index++, payrollPeriod.getDeductGovtFlag().trim());
			ps.setString(index++, payrollPeriod.getIsForSecondHalf());
			ps.setString(index++, payrollPeriod.getPayPeriod());
			ps.addBatch();
		}
 		result = ps.executeBatch();
 		conn.commit();
 		ps.close();
		return result;
    }
    
    
    public ArrayList<PayrollPeriod> getAll(boolean includeLocked) throws SQLException{
    	ArrayList<PayrollPeriod> result = new ArrayList<PayrollPeriod>();
		//include locked = all
		String sql = "select * from PayrollPeriod";
		if (includeLocked == false) {
			sql = "select * from PayrollPeriod where lockedAt is NULL";
		}

		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			PayrollPeriod pp = new PayrollPeriod();
			pp.setPayrollPeriodId(rs.getInt("payrollPeriodId"));
			pp.setPayYear(rs.getInt("payYear"));
			pp.setPayMonth(rs.getInt("payMonth"));
			pp.setPayrollType(rs.getString("payrollType"));
			pp.setFromDate(rs.getDate("fromDate"));
			pp.setToDate(rs.getDate("toDate"));
			pp.setPayDate(rs.getDate("payDate"));
			pp.setPayrollCode(rs.getString("payrollCode"));
			pp.setNumWorkDays(rs.getInt("numWorkDays"));
			pp.setPayPeriod(rs.getString("payPeriod"));
			pp.setLockedAt(rs.getDate("lockedAt"));
			pp.setCreatedAt(rs.getDate("createdAt"));
			pp.setUpdatedAt(rs.getDate("updatedAt"));
			pp.setDeductGovtFlag(rs.getString("deductGovtFlag"));
			pp.setIsForSecondHalf(rs.getString("isForSecondHalf"));
			pp.setStatus(rs.getString("status"));
			result.add(pp);
		}
		conn.close();
		result.sort(Comparator.comparing(PayrollPeriod::getCreatedAt));
		System.out.println(result.size());
		return result;
    }
    
    
    public ArrayList<PayrollPeriod> getAllGenerated() throws SQLException{
    	ArrayList<PayrollPeriod> result = new ArrayList<PayrollPeriod>();
		String sql ="select * from PayrollPeriod where status = 'G' ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			PayrollPeriod pp = new PayrollPeriod();
			pp.setPayrollPeriodId(rs.getInt("payrollPeriodId"));
			pp.setPayYear(rs.getInt("payYear"));
			pp.setPayMonth(rs.getInt("payMonth"));
			pp.setPayrollType(rs.getString("payrollType"));
			pp.setFromDate(rs.getDate("fromDate"));
			pp.setToDate(rs.getDate("toDate"));
			pp.setPayDate(rs.getDate("payDate"));
			pp.setPayrollCode(rs.getString("payrollCode"));
			pp.setNumWorkDays(rs.getInt("numWorkDays"));
			pp.setPayPeriod(rs.getString("payPeriod"));
			pp.setLockedAt(rs.getDate("lockedAt"));
			pp.setCreatedAt(rs.getDate("createdAt"));
			pp.setUpdatedAt(rs.getDate("updatedAt"));
			pp.setDeductGovtFlag(rs.getString("deductGovtFlag"));
			pp.setIsForSecondHalf(rs.getString("isForSecondHalf"));
			pp.setStatus(rs.getString("status"));
			result.add(pp);
		}
		conn.close();
		result.sort(Comparator.comparing(PayrollPeriod::getCreatedAt));
		System.out.println(result.size());
		return result;
    }
    
    /*public List<PayrollPeriod> getAllByEmployeeId(int empId) throws SQLException {
		List<PayrollPeriod> resultList = new ArrayList<PayrollPeriod>();
		String sql = "select * from PayrollPeriod where employeeId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, empId);
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			PayrollPeriod payrollPeriod = new PayrollPeriod();
			payrollPeriod.setPayrollPeriodId(rs.getInt("payrollPeriodId"));
			payrollPeriod.setPayYear(rs.getInt("payYear"));
			payrollPeriod.setPayMonth(rs.getInt("payMonth"));
			payrollPeriod.setPayrollType(rs.getString("payrollType"));
			payrollPeriod.setFromDate(rs.getDate("fromDate"));
			payrollPeriod.setToDate(rs.getDate("toDate"));
			payrollPeriod.setPayPeriod(rs.getString("payPeriod"));
			payrollPeriod.setPayDate(rs.getDate("payDate"));
			payrollPeriod.setPayrollCode(rs.getString("payrollCode"));
			resultList.add(payrollPeriod);
		}
		rs.close();
		return resultList;
	}
	
	public List<PayrollPeriod> getAllByJobTitleId(int jtId) throws SQLException {
    	List<PayrollPeriod> resultList = new ArrayList<PayrollPeriod>();
    	String sql = "select * from PayrollPeriod "
    			+ "where employeeId in "
    			+ " (select empId from Employee where jobTitleId = ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, jtId);
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			PayrollPeriod payrollPeriod = new PayrollPeriod();
			payrollPeriod.setPayrollPeriodId(rs.getInt("payrollPeriodId"));
			payrollPeriod.setPayYear(rs.getInt("payYear"));
			payrollPeriod.setPayMonth(rs.getInt("payMonth"));
			payrollPeriod.setPayrollType(rs.getString("payrollType"));
			payrollPeriod.setFromDate(rs.getDate("fromDate"));
			payrollPeriod.setToDate(rs.getDate("toDate"));
			payrollPeriod.setPayPeriod(rs.getString("payPeriod"));
			payrollPeriod.setPayDate(rs.getDate("payDate"));
			payrollPeriod.setPayrollCode(rs.getString("payrollCode"));
			resultList.add(payrollPeriod);
		}
		rs.close();
    	return resultList;
    }*/
}
