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
import dai.hris.model.SalaryGrade;

public class SalaryGradeDAO {
	private Connection conn;

	public SalaryGradeDAO() {
		try {
			/* MS SQL CODE */    		
			Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
			conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
			conn.setAutoCommit(false);
		} catch (SQLException sqle) {
			System.out.println("SalaryGradeDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("SalaryGradeDAO :" + e.getMessage());
		}
	}

	public int saveOrUpdate(SalaryGrade sg) throws SQLException {
		int result = -1;
		if (sg.getSalaryGradeId() > 0) {
			String sql = "update SalaryGrade set "
					+ " salaryGradeNumber=?, step=?, basicSalary=?, updatedAt=? "
					+ " whre salaryGradeId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, sg.getSalaryGradeNumber());
			ps.setInt(2, sg.getStep());
			ps.setBigDecimal(3, sg.getBasicSalary());
			ps.setDate(4, new Date(System.currentTimeMillis()));
			ps.setInt(5, sg.getSalaryGradeId());
			result = ps.executeUpdate();
			ps.close();
		} else {
			String sql = "insert into SalaryGrade (salaryGradeNumber, step, basicSalary, createdAt) "
					+ " values (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, sg.getSalaryGradeNumber());
			ps.setInt(2, sg.getStep());
			ps.setBigDecimal(3, sg.getBasicSalary());
			ps.setDate(4, new Date(System.currentTimeMillis()));
			result = ps.executeUpdate();
			ps.close();
		}
		return result;
	}
	
	public SalaryGrade getSalaryGradeById(int sgId) throws SQLException {
		SalaryGrade sg = null;
		String sql = "select * from SalaryGrade where salaryGradeId=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, sgId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			sg = new SalaryGrade();
			sg.setSalaryGradeId(rs.getInt("salaryGradeId"));
			sg.setSalaryGradeNumber(rs.getInt("salaryGradeNumber"));
			sg.setStep(rs.getInt("step"));
			sg.setBasicSalary(rs.getBigDecimal("basicSalary"));
			sg.setCreatedAt(rs.getDate("createdAt"));
			sg.setUpdatedAt(rs.getDate("updatedAt"));
		}
		return sg;
	}
	
	public List<SalaryGrade> getAll() throws SQLException {
		List<SalaryGrade> resultList = new ArrayList<SalaryGrade>();
		String sql = "select * from SalaryGrade";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			SalaryGrade sg = new SalaryGrade();
			sg.setSalaryGradeId(rs.getInt("salaryGradeId"));
			sg.setSalaryGradeNumber(rs.getInt("salaryGradeNumber"));
			sg.setStep(rs.getInt("step"));
			sg.setBasicSalary(rs.getBigDecimal("basicSalary"));
			sg.setCreatedAt(rs.getDate("createdAt"));
			sg.setUpdatedAt(rs.getDate("updatedAt"));
			resultList.add(sg);
		}
		return resultList;
	}
	
	public void closeConnection() throws SQLException, Exception {
		conn.close();
	}
}
