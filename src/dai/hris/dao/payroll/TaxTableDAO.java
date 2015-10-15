package dai.hris.dao.payroll;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dai.hris.dao.DBConstants;
import dai.hris.model.TaxTable;

public class TaxTableDAO {
	private Connection conn;

	public TaxTableDAO() {
		try {
			/* MS SQL CODE */    		
			Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
			conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
			conn.setAutoCommit(false);
		} catch (SQLException sqle) {
			System.out.println("TaxTableDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("TaxTableDAO :" + e.getMessage());
		}
	}

	public int saveOrUpdate(TaxTable entry) throws SQLException {
		int result = -1;
		if (entry.getTaxTableId() > 0) {
			//update
			String sql = "update TaxTable set "
					+ " taxStatus=?, salaryBase=?, taxExemption=?, taxAmount=?, taxRate=?, payrollType=?, updatedAt=? "
					+ " where taxTableId=?";
			PreparedStatement ps  = conn.prepareStatement(sql);
			int idx = 1;
			ps.setString(idx++, entry.getTaxStatus());
			ps.setBigDecimal(idx++, entry.getSalaryBase());
			ps.setBigDecimal(idx++, entry.getTaxExemption());
			ps.setBigDecimal(idx++, entry.getTaxAmount());
			ps.setInt(idx++, entry.getTaxRate());
			ps.setString(idx++, entry.getPayrollType());
			ps.setDate(idx++, new Date(System.currentTimeMillis()));
			ps.setInt(idx++, entry.getTaxTableId());
			result = ps.executeUpdate();
			conn.commit();
			ps.close();
		} else {
			//insert
			String sql = "insert into TaxTable (taxStatus, salaryBase, taxExemption, taxAmount, taxRate, payrollType, createdAt) "
					+ " values (?,?,?,?,?,?)";
			PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			int idx = 1;
			ps.setString(idx++, entry.getTaxStatus());
			ps.setBigDecimal(idx++, entry.getSalaryBase());
			ps.setBigDecimal(idx++, entry.getTaxExemption());
			ps.setBigDecimal(idx++, entry.getTaxAmount());
			ps.setInt(idx++, entry.getTaxRate());
			ps.setString(idx++, entry.getPayrollType());
			ps.setDate(idx++, new Date(System.currentTimeMillis()));
			result = ps.executeUpdate();
			ResultSet keyResultSet = ps.getGeneratedKeys(); 
			int generatedAutoIncrementId = 0; 
			if (keyResultSet.next()) {
				generatedAutoIncrementId = (int) keyResultSet.getInt(1);
				entry.setTaxTableId(generatedAutoIncrementId);
				conn.commit(); 
			}
			keyResultSet.close();
			ps.close();
		}
		return result;
	}
	
	public List<TaxTable> getAll() throws SQLException {
		List<TaxTable> resultList = new ArrayList<TaxTable>();
		String sql = "select * from TaxTable";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			TaxTable entry = new TaxTable();
			entry.setTaxTableId(rs.getInt("taxTableId"));
			entry.setTaxStatus(rs.getString("taxStatus"));
			entry.setSalaryBase(rs.getBigDecimal("salaryBase"));
			entry.setTaxExemption(rs.getBigDecimal("taxExemption"));
			entry.setTaxAmount(rs.getBigDecimal("taxAmount"));
			entry.setTaxRate(rs.getInt("taxRate"));
			entry.setPayrollType(rs.getString("payrollType"));
			entry.setCreatedAt(rs.getDate("createdAt"));
			entry.setUpdatedAt(rs.getDate("updatedAt"));
			resultList.add(entry);
		}
		rs.close();
		return resultList;
	}
	
	public TaxTable getTaxTableBySalaryAndTaxStatusAndPayrollType(BigDecimal in, String taxStatus, String payrollType) throws SQLException {
		TaxTable entry = null;
		String sql = "select  top 1 * from TaxTable "
				+ " where taxStatus = ? and payrollType = ? and salaryBase <= ? order by salaryBase desc";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, taxStatus);
		ps.setString(2, payrollType);
		ps.setDouble(3, in.doubleValue());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			entry = new TaxTable();
			entry.setTaxTableId(rs.getInt("taxTableId"));
			entry.setTaxStatus(rs.getString("taxStatus"));
			entry.setSalaryBase(rs.getBigDecimal("salaryBase"));
			entry.setTaxExemption(rs.getBigDecimal("taxExemption"));
			entry.setTaxAmount(rs.getBigDecimal("taxAmount"));
			entry.setTaxRate(rs.getInt("taxRate"));
			entry.setPayrollType(rs.getString("payrollType"));
			entry.setCreatedAt(rs.getDate("createdAt"));
			entry.setUpdatedAt(rs.getDate("updatedAt"));
		}
		rs.close();
		return entry;
	}
	
	public void closeConnection() throws SQLException {
		conn.close();
	}
}
