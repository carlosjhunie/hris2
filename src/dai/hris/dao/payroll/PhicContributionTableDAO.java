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
import dai.hris.model.PhicContributionTable;

public class PhicContributionTableDAO {
	private Connection conn;

	public PhicContributionTableDAO() {
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

	public int saveOrUpdate(PhicContributionTable entry) throws SQLException {
		int res = -1;
		if (entry.getPhicContributionTableId() > 0) {
			//update
			String sql = "update PhicContributionTable set "
					+ " salaryBase=?, employeeShare=?, employerShare=?, totalMonthlyPremium=?, updatedAt=? "
					+ " where phicContributionTableId=?";
			PreparedStatement ps  = conn.prepareStatement(sql);
			int index = 1;
			ps.setBigDecimal(index++, entry.getSalaryBase());
			ps.setBigDecimal(index++, entry.getEmployeeShare());
			ps.setBigDecimal(index++, entry.getEmployerShare());
			ps.setBigDecimal(index++, entry.getTotalMonthlyPremium());
			ps.setDate(index++, new Date(System.currentTimeMillis()));
			ps.setInt(index++, entry.getPhicContributionTableId());
			res = ps.executeUpdate();
			conn.commit();
			ps.close();
		} else {
			//insert
			String sql = "insert into PhicContributionTable (salaryBase,employeeShare,employerShare,totalMonthlyPremium,createdAt) "
					+ " values (?,?,?,?,?)";
			PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			int index = 1;
			ps.setBigDecimal(index++, entry.getSalaryBase());
			ps.setBigDecimal(index++, entry.getEmployeeShare());
			ps.setBigDecimal(index++, entry.getEmployerShare());
			ps.setBigDecimal(index++, entry.getTotalMonthlyPremium());
			ps.setDate(index++, new Date(System.currentTimeMillis()));
			res = ps.executeUpdate();
			ResultSet keyResultSet = ps.getGeneratedKeys(); 
			int generatedAutoIncrementId = 0; 
			if (keyResultSet.next()) {
				generatedAutoIncrementId = (int) keyResultSet.getInt(1);
				entry.setPhicContributionTableId(generatedAutoIncrementId);
				conn.commit(); 
			}
			keyResultSet.close();
			ps.close();
		}
		return res;
	}
	
	public PhicContributionTable getPhicContributionBySalary(BigDecimal in) throws SQLException {
		PhicContributionTable phic = null;
		String sql = "select top 1 * from PhicContributionTable "
				+ " where salaryBase <= ? order by salaryBase desc";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setBigDecimal(1, in);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			phic = new PhicContributionTable();
			phic.setPhicContributionTableId(rs.getInt("phicContributionTableId"));
			phic.setSalaryBase(rs.getBigDecimal("salaryBase"));
			phic.setEmployeeShare(rs.getBigDecimal("employeeShare"));
			phic.setEmployerShare(rs.getBigDecimal("employerShare"));
			phic.setTotalMonthlyPremium(rs.getBigDecimal("totalMonthlyPremium"));
			phic.setCreatedAt(rs.getDate("createdAt"));
			phic.setUpdatedAt(rs.getDate("updatedAt"));
		}
		rs.close();
		return phic;
	}
	
	public List<PhicContributionTable> getAll() throws SQLException {
		List<PhicContributionTable> resultList = new ArrayList<PhicContributionTable>();
		String sql = "select * from PhicContributionTable";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			PhicContributionTable phic = new PhicContributionTable();
			phic.setPhicContributionTableId(rs.getInt("phicContributionTableId"));
			phic.setSalaryBase(rs.getBigDecimal("salaryBase"));
			phic.setEmployeeShare(rs.getBigDecimal("employeeShare"));
			phic.setEmployerShare(rs.getBigDecimal("employerShare"));
			phic.setTotalMonthlyPremium(rs.getBigDecimal("totalMonthlyPremium"));
			phic.setCreatedAt(rs.getDate("createdAt"));
			phic.setUpdatedAt(rs.getDate("updatedAt"));
			resultList.add(phic);
		}
		rs.close();
		return resultList;
	}
	
	public void closeConnection() throws SQLException {
		conn.close();
	}
}
