package dai.hris.dao.payroll;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dai.hris.dao.DBConstants;
import dai.hris.model.Deduction;

public class DeductionDAO {
	private Connection conn;

	public DeductionDAO() {
		try {
			/* MS SQL CODE */    		
			Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
			conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
			conn.setAutoCommit(false);
		} catch (SQLException sqle) {
			System.out.println("DeductionDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("DeductionDAO :" + e.getMessage());
		}
	}

	public int saveOrUpdate(Deduction dd) throws SQLException {
		int res = -1;
		if (dd.getDeductionId() > 0) {
			String sql = "update Deduction set "
					+ " deductionName=?, deductionDescription=?, amount=?, employeeType=?,payrollType=?,isForSecondHalf=?, updatedAt=? "
					+ " where deductionId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			int index = 1;
			ps.setString(index++, dd.getDeductionName());
			ps.setBigDecimal(index++, dd.getAmount());
			ps.setString(index++, dd.getEmployeeType());
			ps.setString(index++, dd.getPayrollType());
			ps.setString(index++, dd.getIsForSecondHalf());
			ps.setDate(index++, new Date(System.currentTimeMillis()));
			ps.setInt(index++, dd.getDeductionId());
			res = ps.executeUpdate();
			conn.commit();
			ps.close();
		} else {
			String sql = "insert into Deduction (deductionName,amount,employeeType,payrollType,isForSecondHalf,createdAt) "
					+ " values (?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			int index = 1;
			ps.setString(index++, dd.getDeductionName());
			ps.setBigDecimal(index++, dd.getAmount());
			ps.setString(index++, dd.getEmployeeType());
			ps.setString(index++, dd.getPayrollType());
			ps.setString(index++, dd.getIsForSecondHalf());
			ps.setDate(index++, new Date(System.currentTimeMillis()));
			res = ps.executeUpdate();
			ResultSet keyResultSet = ps.getGeneratedKeys(); 
			int generatedAutoIncrementId = 0; 
			if (keyResultSet.next()) {
				generatedAutoIncrementId = (int) keyResultSet.getInt(1);
				dd.setDeductionId(generatedAutoIncrementId);
				conn.commit(); 
			}
			keyResultSet.close();
			ps.close();
		}
		return res;
	}
	
	public Deduction getDeductionById(int ddId) throws SQLException {
		Deduction dd = null;
		String sql = "select * from Deduction where deductionId=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, ddId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
		    dd = new Deduction();
		    dd.setDeductionId(rs.getInt("deductionId"));
			dd.setDeductionName(rs.getString("deductionName"));
			dd.setAmount(rs.getBigDecimal("amount"));
			dd.setEmployeeType(rs.getString("employeeType"));
			dd.setPayrollType(rs.getString("payrollType"));
			dd.setIsForSecondHalf(rs.getString("isForSecondHalf"));
			dd.setCreatedAt(rs.getDate("createdAt"));
			dd.setUpdatedAt(rs.getDate("updatedAt"));
		}
		rs.close();
		return dd;
	}
	
	public List<Deduction> getAll() throws SQLException {
		List<Deduction> resultList = new ArrayList<Deduction>();
		String sql = "select * from Deduction";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Deduction dd = new Deduction();
			dd.setDeductionId(rs.getInt("deductionId"));
			dd.setDeductionName(rs.getString("deductionName"));
			dd.setAmount(rs.getBigDecimal("amount"));
			dd.setEmployeeType(rs.getString("employeeType"));
			dd.setPayrollType(rs.getString("payrollType"));
			dd.setIsForSecondHalf(rs.getString("isForSecondHalf"));
			dd.setCreatedAt(rs.getDate("createdAt"));
			dd.setUpdatedAt(rs.getDate("updatedAt"));
			resultList.add(dd);
		}
		rs.close();
		return resultList;
	}
	
	public void closeConnection() throws SQLException {
		conn.close();
	}
}
