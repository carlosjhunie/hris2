package dai.hris.dao.filemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.DBConstants;
import dai.hris.model.LoanType;

public class LoanTypeDAO {
	
Connection conn = null;
    
    public LoanTypeDAO() {
        
    	try {

    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("LoanTypeDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("LoanTypeDAO :" + e.getMessage());
		}
    	
    }
    
    public  ArrayList<LoanType> getAll() throws SQLException, Exception {

		String sql = "SELECT   loanTypeId,  loanTypeName FROM loanType";		
		PreparedStatement ps = conn.prepareStatement(sql.toString());

	    ResultSet rs = ps.executeQuery();
	    ArrayList<LoanType> list = new ArrayList<LoanType>();
	    
	    while (rs.next()) {
	    	 LoanType loanType = new LoanType();
	    	 loanType.setLoanTypeId(rs.getInt("loanTypeId"));
	    	 loanType.setLoanTypeName(rs.getString("loanTypeName"));	    	 
	    	 list.add(loanType);

	    }
	    
	    ps.close();
	    rs.close();      
	    return list;     

	}
    
    
    public  void add(LoanType loanType) throws SQLException, Exception {
  		String sql = "INSERT INTO loanType (loanTypeName) VALUES (?)";
  		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, loanType.getLoanTypeName());
        
        ps.executeUpdate();
          
        ResultSet keyResultSet = ps.getGeneratedKeys();
         int generatedAutoIncrementId = 0;
         if (keyResultSet.next()) {
          	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
          	loanType.setLoanTypeId(generatedAutoIncrementId);
          	conn.commit();
          }

          ps.close();
          keyResultSet.close();

  	}
    
    public  void delete(LoanType loanType) throws SQLException, Exception {
		String sql = "DELETE from loanType where loanTypeId = ?";
		PreparedStatement ps  = conn.prepareStatement(sql);
        ps.setInt(1, loanType.getLoanTypeId());
        ps.executeUpdate();
        conn.commit();
        ps.close();

	}
    
    public  void update(LoanType loanType) throws SQLException, Exception {
		
 		String sql = "UPDATE loanType set loanTypeName = ? where loanTypeId = ?";
 		PreparedStatement ps  = conn.prepareStatement(sql);
 		ps.setString(1, loanType.getLoanTypeName());
 		
 		ps.setInt(2, loanType.getLoanTypeId());
 		
         
         ps.executeUpdate();
         conn.commit();
         ps.close();

 	}
    
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}

}
