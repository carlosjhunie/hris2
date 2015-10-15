package dai.hris.dao.login;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dai.hris.dao.DBConstants;
import dai.hris.model.ModuleAccess;

public class ModuleAccessDAO {
	private Connection conn;

	public ModuleAccessDAO() {
		try {
			Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
			conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
			conn.setAutoCommit(false);
		} catch (SQLException sqle) {
			System.out.println("ModuleAccessDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("ModuleAccessDAO :" + e.getMessage());
		}
	}
	
	public void saveOrUpdate(ModuleAccess ma) throws SQLException {
		if (ma.getModuleAccessId() > 0) {
			String sql = "update moduleAccess set "
					+ " updatedAt=?, fileManagement=?, employee=?, timeManagement=?, payroll=?, employeesLoan=?, payrollReports=? "
					+ " where moduleAccessId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			int idx = 1;
			ps.setDate(idx++, new Date(System.currentTimeMillis()));
			ps.setString(idx++, ma.getFileManagement());
			ps.setString(idx++, ma.getEmployee());
			ps.setString(idx++, ma.getTimeManagement());
			ps.setString(idx++, ma.getPayroll());
			ps.setString(idx++, ma.getEmployeesLoan());
			ps.setString(idx++, ma.getPayrollReports());
			ps.setInt(idx++, ma.getModuleAccessId());
			ps.executeUpdate();
			conn.commit();
			ps.close();
		} else {
			String sql = "insert into moduleAccess "
					+ " (createdAt,empId,fileManagement,employee,timeManagement,payroll,employeesLoan,payrollReports) "
					+ " values(?,?,?,?,?,?,?,?) ";
			PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			int idx = 1;
			ps.setDate(idx++, new Date(System.currentTimeMillis()));
			ps.setInt(idx++, ma.getEmpId());
			ps.setString(idx++, ma.getFileManagement());
			ps.setString(idx++, ma.getEmployee());
			ps.setString(idx++, ma.getTimeManagement());
			ps.setString(idx++, ma.getPayroll());
			ps.setString(idx++, ma.getEmployeesLoan());
			ps.setString(idx++, ma.getPayrollReports());
			ps.executeUpdate();
			ResultSet keyResultSet = ps.getGeneratedKeys(); 
			int generatedAutoIncrementId = 0; 
			if (keyResultSet.next()) {
				generatedAutoIncrementId = (int) keyResultSet.getInt(1);
				ma.setModuleAccessId(generatedAutoIncrementId);
				conn.commit(); 
			}
			keyResultSet.close();
			ps.close();
		}
	}

	public void closeConnection() throws SQLException {
		conn.close();
	}
	
	public ModuleAccess getModuleAccessByEmpId(int empId) throws SQLException {
		ModuleAccess ma = null;
		String sql = "select * from moduleAccess where empId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, empId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ma = new ModuleAccess();
			ma.setModuleAccessId(rs.getInt("moduleAccessId"));
			ma.setCreatedAt(rs.getDate("createdAt"));
			ma.setUpdatedAt(rs.getDate("updatedAt"));
			ma.setEmpId(rs.getInt("empId"));
			ma.setFileManagement(rs.getString("fileManagement"));
			ma.setEmployee(rs.getString("employee"));
			ma.setTimeManagement(rs.getString("timeManagement"));
			ma.setPayroll(rs.getString("payroll"));
			ma.setEmployeesLoan(rs.getString("employeesLoan"));
			ma.setPayrollReports(rs.getString("payrollReports"));
		}
		return ma;
	}
}
