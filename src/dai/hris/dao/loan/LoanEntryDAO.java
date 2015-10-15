package dai.hris.dao.loan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.action.filemanager.util.EmployeeUtil;
import dai.hris.dao.DBConstants;
import dai.hris.model.LoanEntry;

public class LoanEntryDAO {
	
	Connection conn = null;
	EmployeeUtil util = new EmployeeUtil();
    
    public LoanEntryDAO() {    	
    	
    	try {
    		
    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("LoanEntryDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("LoanEntryDAO :" + e.getMessage());
		}
    	
    }
	
    
    public ArrayList<LoanEntry> getAllLoanEntryByEmpId(int empId) throws SQLException, Exception {
    	LoanEntry loanEntry = null;
		ArrayList<LoanEntry> list = new ArrayList<LoanEntry>();
		String sql = "SELECT * FROM loanEntry where empId = ?";
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, empId);

	    ResultSet rs = ps.executeQuery();
	    
	    while (rs.next()) {
	    	loanEntry = new LoanEntry();
	    	loanEntry.setLoanEntryId(rs.getInt("loanEntryId"));	    	
	    	loanEntry.setEmpId(rs.getInt("empId"));
	    	loanEntry.setDateFile(util.sqlDateToString(rs.getDate("dateFile")));
	     	loanEntry.setLoanTypeId(rs.getInt("loanTypeId"));
	    	loanEntry.setReferenceNo(rs.getString("referenceNo"));
	    	loanEntry.setLoanAmount(rs.getBigDecimal("loanAmount"));
	    	loanEntry.setMonthlyAmortization(rs.getBigDecimal("monthlyAmortization"));
	    	loanEntry.setStartDateToPay(util.sqlDateToString(rs.getDate("startDateToPay")));
	    	loanEntry.setEndDateToPay(util.sqlDateToString(rs.getDate("endDateToPay")));
	    	loanEntry.setPNNo(rs.getString("PNNo"));
	    	loanEntry.setPNDate(rs.getString("PNDate"));
	    	loanEntry.setPeriodFrom(rs.getString("periodFrom"));
	    	loanEntry.setPeriodTo(rs.getString("periodTo"));
	    	loanEntry.setRemarks(rs.getString("remarks"));
	    	loanEntry.setDeductionFlagActive(rs.getString("deductionFlagActive"));
	    	list.add(loanEntry);
		}
	    sql = null;
	    ps.close();
	    rs.close();	 
	    return list;

	}
    
    public  void add(LoanEntry loanEntry) throws SQLException, Exception {
  		String sql = "INSERT INTO loanEntry (empId,dateFile,loanTypeId,referenceNo,loanAmount,monthlyAmortization,startDateToPay,endDateToPay,PNNo,PNDate,periodFrom,periodTo,remarks,deductionFlagActive) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
  		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
  		int index = 1;
  		
        ps.setInt(index++, loanEntry.getEmpId());
        ps.setDate(index++, util.convertToSqlDate(loanEntry.getDateFile()));
        ps.setInt(index++, loanEntry.getLoanTypeId());
        ps.setString(index++, loanEntry.getReferenceNo());
        ps.setBigDecimal(index++, loanEntry.getLoanAmount());
        ps.setBigDecimal(index++, loanEntry.getMonthlyAmortization());
        ps.setDate(index++, util.convertToSqlDate(loanEntry.getStartDateToPay()));
        ps.setDate(index++, util.convertToSqlDate(loanEntry.getEndDateToPay()));
        ps.setString(index++, loanEntry.getPNNo());
        ps.setString(index++, loanEntry.getPNDate());
        //ps.setString(index++, loanEntry.getPeriodFrom()); //temp comment, replace with NA
        //ps.setString(index++, loanEntry.getPeriodTo());	//temp comment, replace with NA
        ps.setString(index++, "NA");
        ps.setString(index++, "NA");
        ps.setString(index++, loanEntry.getRemarks());
        ps.setString(index++, loanEntry.getDeductionFlagActive());

        ps.executeUpdate();
          
        ResultSet keyResultSet = ps.getGeneratedKeys();
         int generatedAutoIncrementId = 0;
         if (keyResultSet.next()) {
          	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
          	loanEntry.setLoanEntryId(generatedAutoIncrementId);
          	conn.commit();
          }

          ps.close();
          keyResultSet.close();

  	}
    
    public  void update(LoanEntry loanEntry) throws SQLException, Exception {
    	
  		String sql = "UPDATE loanEntry set dateFile = ?,loanTypeId = ?,referenceNo = ?,loanAmount = ?,monthlyAmortization = ?,startDateToPay = ?,endDateToPay = ?,PNNo = ?,PNDate = ?,periodFrom = ?,periodTo = ?, remarks = ?, deductionFlagActive = ? where loanEntryId = ?";
  		PreparedStatement ps  = conn.prepareStatement(sql);
  		int index = 1;
  		 ps.setDate(index++, util.convertToSqlDate(loanEntry.getDateFile()));
         ps.setInt(index++, loanEntry.getLoanTypeId());
         ps.setString(index++, loanEntry.getReferenceNo());
         ps.setBigDecimal(index++, loanEntry.getLoanAmount());
         ps.setBigDecimal(index++, loanEntry.getMonthlyAmortization());
         ps.setDate(index++, util.convertToSqlDate(loanEntry.getStartDateToPay()));
         ps.setDate(index++, util.convertToSqlDate(loanEntry.getEndDateToPay()));
         ps.setString(index++, loanEntry.getPNNo());
         ps.setString(index++, loanEntry.getPNDate());
         //ps.setString(index++, loanEntry.getPeriodFrom());	//temp comment, replace with NA
         //ps.setString(index++, loanEntry.getPeriodTo());		//temp comment, replace with NA
         ps.setString(index++, "NA");
         ps.setString(index++, "NA");
         ps.setString(index++, loanEntry.getRemarks());
         ps.setString(index++, loanEntry.getDeductionFlagActive());
  		ps.setInt(index++, loanEntry.getLoanEntryId());
  		ps.executeUpdate();
        conn.commit();
        ps.close();

  	}
    
 
   
    
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}
	
	
	

}
