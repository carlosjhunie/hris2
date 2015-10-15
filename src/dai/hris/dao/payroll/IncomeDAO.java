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
import dai.hris.model.Income;

public class IncomeDAO {
	private Connection conn;

	public IncomeDAO() {
		try {
			/* MS SQL CODE */    		
			Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
			conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
			conn.setAutoCommit(false);
		} catch (SQLException sqle) {
			System.out.println("IncomeDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("IncomeDAO :" + e.getMessage());
		}
	}
	
	public int saveOrUpdate(Income in) throws SQLException {
		int res = -1;
		if (in.getIncomeId() > 0) {
			String sql = "update Income set "
					+ " incomeName=?, amount=?, isTaxable=?, employeeType=?,payrollType=?,isForSecondHalf=?,updatedAt=? "
					+ " where incomeId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			int index = 1;
			ps.setString(index++, in.getIncomeName());
			ps.setBigDecimal(index++, in.getAmount());
			ps.setString(index++, in.getIsTaxable());
			ps.setString(index++, in.getEmployeeType());
			ps.setString(index++, in.getPayrollType());
			ps.setString(index++, in.getIsForSecondHalf());
			ps.setDate(index++, new Date(System.currentTimeMillis()));
			ps.setInt(index++, in.getIncomeId());
			res = ps.executeUpdate();
			conn.commit();
			ps.close();
		} else {
			String sql = "insert into Income "
					+ " (incomeName,amount,isTaxable,employeeType,payrollType,isForSecondHalf,createdAt) "
					+ " values (?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			int index = 1;
			ps.setString(index++, in.getIncomeName());
			ps.setBigDecimal(index++, in.getAmount());
			ps.setString(index++, in.getIsTaxable());
			ps.setString(index++, in.getEmployeeType());
			ps.setString(index++, in.getPayrollType());
			ps.setString(index++, in.getIsForSecondHalf());
			ps.setDate(index++, new Date(System.currentTimeMillis()));
			res = ps.executeUpdate();
			ResultSet keyResultSet = ps.getGeneratedKeys(); 
			int generatedAutoIncrementId = 0; 
			if (keyResultSet.next()) {
				generatedAutoIncrementId = (int) keyResultSet.getInt(1);
				in.setIncomeId(generatedAutoIncrementId);
				conn.commit(); 
			}
			keyResultSet.close();
			ps.close();
		}
		return res;
	}
	
	public Income getIncomeById(int inId) throws SQLException {
		Income in = null;
		String sql  = "select * from Income where incomeId=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, inId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			in = new Income();
			in.setIncomeId(rs.getInt("incomeId"));
			in.setIncomeName(rs.getString("incomeName"));
			in.setAmount(rs.getBigDecimal("amount"));
			in.setIsTaxable(rs.getString("isTaxable"));
			in.setEmployeeType(rs.getString("employeeType"));
			in.setPayrollType(rs.getString("payrollType"));
			in.setIsForSecondHalf(rs.getString("isForSecondHalf"));
			in.setCreatedAt(rs.getDate("createdAt"));
			in.setUpdatedAt(rs.getDate("updatedAt"));
		}
		rs.close();
		return in;
	}
	
	public List<Income> getAll() throws SQLException {
		List<Income> resultList = new ArrayList<Income>();
		String sql  = "select * from Income";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Income in = new Income();
			in.setIncomeId(rs.getInt("incomeId"));
			in.setIncomeName(rs.getString("incomeName"));
			in.setAmount(rs.getBigDecimal("amount"));
			in.setIsTaxable(rs.getString("isTaxable"));
			in.setEmployeeType(rs.getString("employeeType"));
			in.setPayrollType(rs.getString("payrollType"));
			in.setIsForSecondHalf(rs.getString("isForSecondHalf"));
			in.setCreatedAt(rs.getDate("createdAt"));
			in.setUpdatedAt(rs.getDate("updatedAt"));
			resultList.add(in);
		}
		rs.close();
		return resultList;
	}

	public void closeConnection() throws SQLException {
		conn.close();
	}
}
