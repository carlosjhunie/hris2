package dai.hris.dao.payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dai.hris.dao.DBConstants;
import dai.hris.model.MedicareShareAllowance;

public class MedicareShareAllowanceDAO {

	private Connection conn = null;

	public MedicareShareAllowanceDAO() {    	
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

	public void add(MedicareShareAllowance msa) throws SQLException, Exception {
		String sql = "insert into MedicareShareAllowance "
				+ " (numDays, ratePerDay, netAmountDue, date, remarks, empId) "
				+ " values (?,?,?,?,?,?)";
		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setInt(1, msa.getNumDays());
        ps.setInt(2, msa.getRatePerDay());
        ps.setBigDecimal(3, msa.getNetAmountDue());
        ps.setDate(4, msa.getDate());
        ps.setString(5, msa.getRemarks());
        ps.setInt(6, msa.getEmpId());
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

	public void update(MedicareShareAllowance msa) throws SQLException, Exception {
		String sql = "update MedicareShareAllowance set "
				+ " numDays=?, ratePerDay=?, netAmountDue=?, date=?, remarks=?, empId=? "
				+ " where medicareShareAllowanceId=?";
		PreparedStatement ps  = conn.prepareStatement(sql);
		ps.setInt(1, msa.getNumDays());
        ps.setInt(2, msa.getRatePerDay());
        ps.setBigDecimal(3, msa.getNetAmountDue());
        ps.setDate(4, msa.getDate());
        ps.setString(5, msa.getRemarks());
        ps.setInt(6, msa.getEmpId());
        ps.setInt(7, msa.getMedicareShareAllowanceId());
 		ps.executeUpdate();
 		
 		conn.commit();
 		ps.close();
 		conn.close();
	}

	public void delete(MedicareShareAllowance msa) throws SQLException, Exception {
		String sql = "delete from MedicareShareAllowance where medicareShareAllowanceId=?";
		PreparedStatement ps  = conn.prepareStatement(sql);
		ps.setInt(1, msa.getMedicareShareAllowanceId());
        ps.executeUpdate();
        
        conn.commit();
        ps.close();
        conn.close();
	}
	
	public void closeConnection() throws SQLException, Exception {
		conn.close();
	}
	
	public int[] batchUpdate(List<MedicareShareAllowance> msaList) throws SQLException, Exception {
		int[] result = null;
		String sql = "update MedicareShareAllowance set "
				+ " numDays=?, ratePerDay=?, netAmountDue=?, date=?, remarks=?, empId=? "
				+ " where medicareShareAllowanceId=?";
		PreparedStatement ps  = conn.prepareStatement(sql);
		for (int i = 0; i < msaList.size(); i++) {
			MedicareShareAllowance msa = msaList.get(i);
			ps.setInt(1, msa.getNumDays());
			ps.setInt(2, msa.getRatePerDay());
			ps.setBigDecimal(3, msa.getNetAmountDue());
			ps.setDate(4, msa.getDate());
			ps.setString(5, msa.getRemarks());
			ps.setInt(6, msa.getEmpId());
			ps.setInt(7, msa.getMedicareShareAllowanceId());
			ps.addBatch();
		}
 		result = ps.executeBatch();
 		
 		conn.commit();
 		ps.close();
		return result;
	}
	
    public int[] batchInsert(List<MedicareShareAllowance> msaList) throws SQLException, Exception {
    	int[] result = null;
    	String sql = "insert into MedicareShareAllowance "
				+ " (numDays, ratePerDay, netAmountDue, date, remarks, empId) "
				+ " values (?,?,?,?,?,?)";
		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < msaList.size(); i++) {
        	MedicareShareAllowance msa = msaList.get(i);
        	ps.setInt(1, msa.getNumDays());
        	ps.setInt(2, msa.getRatePerDay());
        	ps.setBigDecimal(3, msa.getNetAmountDue());
        	ps.setDate(4, msa.getDate());
        	ps.setString(5, msa.getRemarks());
        	ps.setInt(6, msa.getEmpId());
        	ps.addBatch();
        }
        result = ps.executeBatch();
        conn.commit();
        ps.close();
		return result;
    }
    
    public List<MedicareShareAllowance> getAllByEmployeeId(int empId) throws SQLException, Exception {
    	List<MedicareShareAllowance> resultList = new ArrayList<MedicareShareAllowance>();
    	String sql = "select * from PayrollPeriod where employeeId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, empId);
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			MedicareShareAllowance msa = new MedicareShareAllowance();
			msa.setMedicareShareAllowanceId(rs.getInt("medicareShareAllowanceId"));
			msa.setNumDays(rs.getInt("numDays"));
			msa.setRatePerDay(rs.getInt("ratePerDay"));
			msa.setNetAmountDue(rs.getBigDecimal("netAmountDue"));
			msa.setDate(rs.getDate("date"));
			msa.setRemarks(rs.getString("remarks"));
			msa.setEmpId(rs.getInt("empId"));
		}
		rs.close();
		conn.close();
    	return resultList;
    }
    
    public List<MedicareShareAllowance> getAllByJobTitleId(int jtId) throws SQLException, Exception {
    	List<MedicareShareAllowance> resultList = new ArrayList<MedicareShareAllowance>();
    	String sql = "select * from PayrollPeriod "
    			+ " where employeeId in "
    			+ " (select empId from Employee where jobTitleId = ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, jtId);
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			MedicareShareAllowance msa = new MedicareShareAllowance();
			msa.setMedicareShareAllowanceId(rs.getInt("medicareShareAllowanceId"));
			msa.setNumDays(rs.getInt("numDays"));
			msa.setRatePerDay(rs.getInt("ratePerDay"));
			msa.setNetAmountDue(rs.getBigDecimal("netAmountDue"));
			msa.setDate(rs.getDate("date"));
			msa.setRemarks(rs.getString("remarks"));
			msa.setEmpId(rs.getInt("empId"));
		}
		rs.close();
		conn.close();
    	return resultList;
    }
}
