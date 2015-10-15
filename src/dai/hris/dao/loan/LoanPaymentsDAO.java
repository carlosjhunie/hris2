package dai.hris.dao.loan;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




import dai.hris.action.filemanager.util.EmployeeUtil;
import dai.hris.dao.DBConstants;
import dai.hris.model.LoanPayments;


public class LoanPaymentsDAO {
	
	Connection conn = null;
	EmployeeUtil util = new EmployeeUtil();
    
    public LoanPaymentsDAO() {    	
    	
    	try {
    		
    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("LoanPaymentsDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("LoanPaymentsDAO :" + e.getMessage());
		}
    	
    }
	
    
    public ArrayList<LoanPayments> getAllLoanPaymentsByLoanEntryId(int loanEntryId) throws SQLException, Exception {
    	LoanPayments loanPayments = null;
		ArrayList<LoanPayments> list = new ArrayList<LoanPayments>();
		String sql = "SELECT * FROM loanPayments where loanEntryId = ?";
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, loanEntryId);

	    ResultSet rs = ps.executeQuery();
	    
	    while (rs.next()) {
	    	
	    	loanPayments = new LoanPayments();
	    	loanPayments.setLoanEntryId(rs.getInt("loanEntryId"));
	    	loanPayments.setLoanPaymentId(rs.getInt("loanPaymentId"));
	    	loanPayments.setPaidAmount(rs.getBigDecimal("paidAmount"));
	    	loanPayments.setPaymentDate(util.sqlDateToString(rs.getDate("paymentDate")));
	    	loanPayments.setRemarks(rs.getString("remarks"));   	
	    	loanPayments.setPayrollPeriodId(rs.getInt("payrollPeriodId"));
	    	list.add(loanPayments);
		}
	    sql = null;
	    ps.close();
	    rs.close();	 
	    return list;

	}
    
    public BigDecimal getTotalLoanPaymentsByLoanEntryId(int loanEntryId) throws SQLException, Exception {
    	BigDecimal loanPayments = BigDecimal.ZERO;
		
		String sql = "SELECT sum(paidAmount) sumPaidAmount FROM loanPayments where loanEntryId = ?";
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, loanEntryId);

	    ResultSet rs = ps.executeQuery();
	    
	    if (rs.next()) {
	    	loanPayments = rs.getBigDecimal("sumPaidAmount");	    	
		}
	    sql = null;
	    ps.close();
	    rs.close();	 
	    return loanPayments;

	}
    
    public  void add(LoanPayments loanPayments) throws SQLException, Exception {
  		String sql = "INSERT INTO loanPayments (loanEntryId,paidAmount,paymentDate,remarks,payrollPeriodId) VALUES (?,?,?,?,?)";
  		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
  		int index = 1;
  		
        ps.setInt(index++, loanPayments.getLoanEntryId());
        ps.setBigDecimal(index++, loanPayments.getPaidAmount());        
        ps.setDate(index++, util.convertToSqlDate(loanPayments.getPaymentDate()));
        ps.setString(index++, loanPayments.getRemarks());
        ps.setInt(index++, loanPayments.getPayrollPeriodId());
        
        
  

        ps.executeUpdate();
          
        ResultSet keyResultSet = ps.getGeneratedKeys();
         int generatedAutoIncrementId = 0;
         if (keyResultSet.next()) {
          	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
          	loanPayments.setLoanPaymentId(generatedAutoIncrementId);
          	conn.commit();
          }

          ps.close();
          keyResultSet.close();

  	}
    
    public  void update(LoanPayments loanPayments) throws SQLException, Exception {
    	
  		String sql = "UPDATE loanPayments set paidAmount = ?,paymentDate = ?,remarks = ? where loanPaymentId = ?";
  		PreparedStatement ps  = conn.prepareStatement(sql);
  		int index = 1;
  		
  		ps.setBigDecimal(index++, loanPayments.getPaidAmount());  		
  		ps.setDate(index++, util.convertToSqlDate(loanPayments.getPaymentDate()));
  		ps.setString(index++, loanPayments.getRemarks());  		 
        ps.setInt(index++, loanPayments.getLoanPaymentId());

  		ps.executeUpdate();
        conn.commit();
        ps.close();

  	}
    
 
   
    
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}
	
	
	

}
