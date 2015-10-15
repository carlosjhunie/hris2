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
import dai.hris.model.Allowance;

public class AllowanceDAO {
	private Connection conn;

	public AllowanceDAO() {
		try {
			/* MS SQL CODE */    		
			Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
			conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
			conn.setAutoCommit(false);
		} catch (SQLException sqle) {
			System.out.println("AllowanceDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("AllowanceDAO :" + e.getMessage());
		}
	}

	public int saveOrUpdate(Allowance aw) throws SQLException {
		int result = -1;
		if (aw.getAllowanceId() > 0) {
			String sql = "update Allowances set "
					+ " allowanceName=?, allowanceDescription=?, amount=?, isTaxable=?, updatedAt=? "
					+ " where allowanceId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, aw.getAllowanceName());
			ps.setString(2, aw.getAllowanceDescription());
			ps.setBigDecimal(3, aw.getAmount());
			ps.setBoolean(4, aw.isTaxable());
			ps.setDate(5, new Date(System.currentTimeMillis()));
			ps.setInt(6, aw.getAllowanceId());
			result = ps.executeUpdate();
			ps.close();
		} else {
			String sql = "insert into Allowances (allowanceName, allowanceDescription, amount, isTaxable, createdAt) "
					+ " values (?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, aw.getAllowanceName());
			ps.setString(2, aw.getAllowanceDescription());
			ps.setBigDecimal(3, aw.getAmount());
			ps.setBoolean(4, aw.isTaxable());
			ps.setDate(5, new Date(System.currentTimeMillis()));
			result = ps.executeUpdate();
		}
		return result;
	}
	
	public Allowance getAllowanceById(int awId) throws SQLException {
		Allowance aw = null;
		String sql = "select * from Allowance where allowanceId=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, awId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			aw = new Allowance();
			aw.setAllowanceId(rs.getInt("allowanceId"));
			aw.setAllowanceName(rs.getString("allowanceName"));
			aw.setAllowanceDescription(rs.getString("allowanceDescription"));
			aw.setAmount(rs.getBigDecimal("amount"));
			aw.setTaxable(rs.getBoolean("isTaxable"));
			aw.setCreatedAt(rs.getDate("createdAt"));
			aw.setUpdatedAt(rs.getDate("updatedAt"));
		}
		return aw;
	}
	
	public List<Allowance> getAll() throws SQLException {
		List<Allowance> resultList = new ArrayList<Allowance>();
		String sql = "select * from SalaryGrade";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Allowance aw = new Allowance();
			aw.setAllowanceId(rs.getInt("allowanceId"));
			aw.setAllowanceName(rs.getString("allowanceName"));
			aw.setAllowanceDescription(rs.getString("allowanceDescription"));
			aw.setAmount(rs.getBigDecimal("amount"));
			aw.setCreatedAt(rs.getDate("createdAt"));
			aw.setUpdatedAt(rs.getDate("updatedAt"));
			resultList.add(aw);
		}
		return resultList;
	}
	
	public void closeConnection() throws SQLException, Exception {
		conn.close();
	}
}
