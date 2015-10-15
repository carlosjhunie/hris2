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
import dai.hris.model.CaseRatePayment;

public class CaseRatePaymentDAO {

	private Connection conn = null;

	public CaseRatePaymentDAO() {    	
		try {
			/* MS SQL CODE */    		
			Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
			conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
			conn.setAutoCommit(false);
		} catch (SQLException sqle) {
			System.out.println("CaseRatePaymentDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("CaseRatePaymentDAO :" + e.getMessage());
		}
	}

	public void saveOrUpdate(CaseRatePayment caseRatePayment) throws SQLException {
		if (caseRatePayment.getCaseRatePaymentId() > 0) {
			String sql = "update CaseRatePayment set "
					+ " officialReceiptDate=?, grossAmount=?, withHoldingTax=?, finalTax=?, netAmountDue=?, year=?, month=?, batch=?, patientId=?, patientName=?, remarks=?, empId=?"
					+ " where caseRatePaymentId = ?";
			PreparedStatement ps  = conn.prepareStatement(sql);
			int idx = 1;
			ps.setDate(idx++, caseRatePayment.getOfficialReceiptDate());
	        ps.setBigDecimal(idx++, caseRatePayment.getGrossAmount());
	        ps.setBigDecimal(idx++, caseRatePayment.getWithHoldingTax());
	        ps.setBigDecimal(idx++, caseRatePayment.getFinalTax());
	        ps.setBigDecimal(idx++, caseRatePayment.getNetAmountDue());
	        ps.setInt(idx++,  caseRatePayment.getYear());
	        ps.setInt(idx++, caseRatePayment.getMonth());
	        ps.setString(idx++, caseRatePayment.getBatch());
	        ps.setString(idx++, caseRatePayment.getPatientId());
	        ps.setString(idx++, caseRatePayment.getPatientName());
	        ps.setString(idx++, caseRatePayment.getRemarks());
	        ps.setInt(idx++, caseRatePayment.getEmpId());
	        ps.setInt(idx++, caseRatePayment.getCaseRatePaymentId());
	 		ps.executeUpdate();
	 		conn.commit();
	 		ps.close();
		} else {
			String sql = "insert into CaseRatePayment "
					+ "(officialReceiptDate, grossAmount, withHoldingTax, finalTax, netAmountDue, year, month, batch, patientId, patientName, remarks, empId, createdAt) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			int idx = 1;
			ps.setDate(idx++, caseRatePayment.getOfficialReceiptDate());
	        ps.setBigDecimal(idx++, caseRatePayment.getGrossAmount());
	        ps.setBigDecimal(idx++, caseRatePayment.getWithHoldingTax());
	        ps.setBigDecimal(idx++, caseRatePayment.getFinalTax());
	        ps.setBigDecimal(idx++, caseRatePayment.getNetAmountDue());
	        ps.setInt(idx++,  caseRatePayment.getYear());
	        ps.setInt(idx++, caseRatePayment.getMonth());
	        ps.setString(idx++, caseRatePayment.getBatch());
	        ps.setString(idx++, caseRatePayment.getPatientId());
	        ps.setString(idx++, caseRatePayment.getPatientName());
	        ps.setString(idx++, caseRatePayment.getRemarks());
	        ps.setInt(idx++, caseRatePayment.getEmpId());
	        ps.setDate(idx++, caseRatePayment.getOfficialReceiptDate());
	        ps.executeUpdate();
	          
	        ResultSet keyResultSet = ps.getGeneratedKeys();
	        int generatedAutoIncrementId = 0;
	        if (keyResultSet.next()) {
	        	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
	        	caseRatePayment.setCaseRatePaymentId(generatedAutoIncrementId);
	        	conn.commit();
	        }

	        keyResultSet.close();
	        ps.close();
		}
	}

	public void closeConnection() throws SQLException {
		conn.close();
	}
	
	public List<CaseRatePayment> getAllByEmployeeId(int empId) throws SQLException {
    	List<CaseRatePayment> resultList = new ArrayList<CaseRatePayment>();
    	String sql = "select * from CaseRatePayment where empId = ?";
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setInt(1, empId);
    	
    	ResultSet rs = ps.executeQuery();
    	while (rs.next()) {
    		CaseRatePayment crp =  new CaseRatePayment();
    		crp.setCaseRatePaymentId(rs.getInt("caseRatePaymentId"));
    		crp.setOfficialReceiptDate(rs.getDate("officialReceiptDate"));
    		crp.setGrossAmount(rs.getBigDecimal("grossAmount"));
    		crp.setWithHoldingTax(rs.getBigDecimal("withHoldingTax"));
    		crp.setFinalTax(rs.getBigDecimal("finalTax"));
    		crp.setNetAmountDue(rs.getBigDecimal("netAmountDue"));
    		crp.setYear(rs.getInt("year"));
    		crp.setMonth(rs.getInt("month"));
    		crp.setBatch(rs.getString("batch"));
    		crp.setPatientId(rs.getString("patientId"));
    		crp.setPatientName(rs.getString("patientName"));
    		crp.setRemarks(rs.getString("remarks"));
    		crp.setEmpId(rs.getInt("empId"));
    		resultList.add(crp);
    	}
    	rs.close();
    	ps.close();
    	return resultList;
    }
    
	public List<CaseRatePayment> getAllByEmpIdAndOrDateRange(int empId, Date from, Date to) throws SQLException {
    	List<CaseRatePayment> resultList = new ArrayList<CaseRatePayment>();
    	String sql = "select * from CaseRatePayment "
    			+ " where empId=? and officialReceiptDate between ? and ? ";
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setInt(1, empId);
    	ps.setDate(2, from);
    	ps.setDate(3, to);
    	ResultSet rs = ps.executeQuery();
    	while(rs.next()) {
    		CaseRatePayment crp =  new CaseRatePayment();
    		crp.setCaseRatePaymentId(rs.getInt("caseRatePaymentId"));
    		crp.setOfficialReceiptDate(rs.getDate("officialReceiptDate"));
    		crp.setGrossAmount(rs.getBigDecimal("grossAmount"));
    		crp.setWithHoldingTax(rs.getBigDecimal("withHoldingTax"));
    		crp.setFinalTax(rs.getBigDecimal("finalTax"));
    		crp.setNetAmountDue(rs.getBigDecimal("netAmountDue"));
    		crp.setYear(rs.getInt("year"));
    		crp.setMonth(rs.getInt("month"));
    		crp.setBatch(rs.getString("batch"));
    		crp.setPatientId(rs.getString("patientId"));
    		crp.setPatientName(rs.getString("patientName"));
    		crp.setRemarks(rs.getString("remarks"));
    		crp.setEmpId(rs.getInt("empId"));
    		resultList.add(crp);
    	}
    	rs.close();
    	ps.close();
    	return resultList;
    }
    
}
