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
import dai.hris.model.ProfessionalFee;

public class ProfessionalFeeDAO {

	private Connection conn = null;

	public ProfessionalFeeDAO() {    	
		try {
			/* MS SQL CODE */    		
			Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
			conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
			conn.setAutoCommit(false);
		} catch (SQLException sqle) {
			System.out.println("ProfessionalFeeDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("ProfessionalFeeDAO :" + e.getMessage());
		}
	}

	public void saveOrUpdate(ProfessionalFee pf) throws SQLException {
		if (pf.getProfessionalFeeId() > 0) {
			String sql = "update ProfessionalFee set "
					+ " officialReceiptNumber=?, officialReceiptDate=?, grossAmount=?, withHoldingTax=?, finalTax=?, netAmountDue=?, patientId=?, patientName=?, remarks=?, empId=?, updatedAt=? "
					+ " where professionalFeeId=?";
			PreparedStatement ps  = conn.prepareStatement(sql);
			int idx = 1;
			ps.setString(idx++, pf.getOfficialReceiptNumber());
	        ps.setDate(idx++, pf.getOfficialReceiptDate());
	        ps.setBigDecimal(idx++, pf.getGrossAmount());
	        ps.setBigDecimal(idx++, pf.getWithHoldingTax());
	        ps.setBigDecimal(idx++, pf.getFinalTax());
	        ps.setBigDecimal(idx++, pf.getNetAmountDue());
	        ps.setString(idx++, pf.getPatientId());
	        ps.setString(idx++, pf.getPatientName());
	        ps.setString(idx++, pf.getRemarks());
	        ps.setInt(idx++, pf.getEmpId());
	        ps.setDate(idx++, new Date(System.currentTimeMillis()));
	        ps.setInt(idx++, pf.getProfessionalFeeId());
			ps.executeUpdate();
	 		conn.commit();
	 		ps.close();
		} else {
			String sql = "insert into ProfessionalFee "
					+ " (officialReceiptNumber, officialReceiptDate, grossAmount, withHoldingTax, finalTax, netAmountDue, patientId, patientName, remarks, empId, createdAt) "
					+ " values (?,?,?,?,?,?,?,?,?,?,?) ";
			PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			int idx = 1;
	        ps.setString(idx++, pf.getOfficialReceiptNumber());
	        ps.setDate(idx++, pf.getOfficialReceiptDate());
	        ps.setBigDecimal(idx++, pf.getGrossAmount());
	        ps.setBigDecimal(idx++, pf.getWithHoldingTax());
	        ps.setBigDecimal(idx++, pf.getFinalTax());
	        ps.setBigDecimal(idx++, pf.getNetAmountDue());
	        ps.setString(idx++, pf.getPatientId());
	        ps.setString(idx++, pf.getPatientName());
	        ps.setString(idx++, pf.getRemarks());
	        ps.setInt(idx++, pf.getEmpId());
	        ps.setDate(idx++, new Date(System.currentTimeMillis()));
	        ps.executeUpdate();
	        ResultSet keyResultSet = ps.getGeneratedKeys();
	        int generatedAutoIncrementId = 0;
	        if (keyResultSet.next()) {
	        	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
	        	pf.setProfessionalFeeId(generatedAutoIncrementId);
	        	conn.commit();
	        }
	        keyResultSet.close();
	        ps.close();
		}
	}

	public void delete(ProfessionalFee pf) throws SQLException {
		String sql = "delete from ProfessionalFee where professionalFeeId=?";
		PreparedStatement ps  = conn.prepareStatement(sql);
		ps.setInt(1, pf.getProfessionalFeeId());
		ps.executeUpdate();
        conn.commit();
        ps.close();
	}
	
	public void closeConnection() throws SQLException {
		conn.close();
	}
	
	public List<ProfessionalFee> getAllByEmployeeId(int empId) throws SQLException {
    	List<ProfessionalFee> resultList = new ArrayList<ProfessionalFee>();
    	String sql = "select * from ProfessionalFee where empId = ?";
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setInt(1, empId);
    	
    	ResultSet rs = ps.executeQuery();
    	while (rs.next()) {
    		ProfessionalFee pf = new ProfessionalFee();
    		pf.setProfessionalFeeId(rs.getInt("professionalFeeId"));
    		pf.setOfficialReceiptNumber(rs.getString("officialReceiptNumber"));
    		pf.setOfficialReceiptDate(rs.getDate("officialReceiptDate"));
    		pf.setGrossAmount(rs.getBigDecimal("grossAmount"));
    		pf.setWithHoldingTax(rs.getBigDecimal("withHoldingTax"));
    		pf.setFinalTax(rs.getBigDecimal("finalTax"));
    		pf.setNetAmountDue(rs.getBigDecimal("netAmountDue"));
    		pf.setPatientId(rs.getString("patientId"));
    		pf.setPatientName(rs.getString("patientName"));
    		pf.setRemarks(rs.getString("remarks"));
    		pf.setEmpId(rs.getInt("empId"));
    		resultList.add(pf);
    	}
    	rs.close();
    	ps.close();
    	return resultList;
    }
    
    public List<ProfessionalFee> getAllByEmpIdAndOrDateRange(int empId, Date from, Date to) throws SQLException {
    	List<ProfessionalFee> resultList = new ArrayList<ProfessionalFee>();
    	String sql = "select * from ProfessionalFee "
    			+ " where empId=? and officialReceiptDate between ? and ? ";
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setInt(1, empId);
    	ps.setDate(2, from);
    	ps.setDate(3, to);
    	ResultSet rs = ps.executeQuery();
    	while(rs.next()) {
    		ProfessionalFee pf = new ProfessionalFee();
    		pf.setProfessionalFeeId(rs.getInt("professionalFeeId"));
    		pf.setOfficialReceiptNumber(rs.getString("orNumber"));
    		pf.setOfficialReceiptDate(rs.getDate("orDate"));
    		pf.setGrossAmount(rs.getBigDecimal("grossAmount"));
    		pf.setWithHoldingTax(rs.getBigDecimal("withHoldingTax"));
    		pf.setFinalTax(rs.getBigDecimal("finalTax"));
    		pf.setNetAmountDue(rs.getBigDecimal("netAmountDue"));
    		pf.setPatientId(rs.getString("patientId"));
    		pf.setPatientName(rs.getString("patientName"));
    		pf.setRemarks(rs.getString("remarks"));
    		pf.setEmpId(empId);
    		pf.setCreatedAt(rs.getDate("createdAt"));
    		pf.setUpdatedAt(rs.getDate("updatedAt"));
    		resultList.add(pf);
    	}
    	rs.close();
    	ps.close();
    	return resultList;
    }
    
}
