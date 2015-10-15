package dai.hris.dao.payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dai.hris.dao.DBConstants;
import dai.hris.model.YearEndBonusCashGift;

public class YearEndBonusCashGiftDAO {

	private Connection conn = null;

	public YearEndBonusCashGiftDAO() {    	
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

	public void add(YearEndBonusCashGift bonus) throws SQLException, Exception {
		String sql = "insert into YearEndBonusCashGift "
				+ " (salaryGrade, STEP, basicSalary, cashGift, total, basicSalaryOct31, cashGift1, "
				+ " firstHalf13thMonth, firstHalfCashGift, secondHalf13thMonth, secondHalfCashGift, "
				+ " totalYearEndBonusCashGift, eamcCoopDeduction, netAmountDue, year, empId) "
				+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setInt(1, bonus.getSalaryGrade());
        ps.setString(2, bonus.getSTEP());
        ps.setBigDecimal(3, bonus.getBasicSalary());
        ps.setBigDecimal(4, bonus.getCashGift());
        ps.setBigDecimal(5, bonus.getTotal());
        ps.setBigDecimal(6, bonus.getBasicSalaryOct31());
        ps.setBigDecimal(7, bonus.getCashGift1());
        ps.setBigDecimal(8, bonus.getFirstHalf13thMonth());
        ps.setBigDecimal(9, bonus.getFirstHalfCashGift());
        ps.setBigDecimal(10, bonus.getSecondHalf13thMonth());
        ps.setBigDecimal(11, bonus.getSecondHalfCashGift());
        ps.setBigDecimal(12, bonus.getTotalYearEndBonusCashGift());
        ps.setBigDecimal(13, bonus.getEamcCoopDeduction());
        ps.setBigDecimal(14, bonus.getNetAmountDue());
        ps.setInt(15, bonus.getYear());
        ps.setInt(16, bonus.getEmpId());
        ps.executeUpdate();
          
        ResultSet keyResultSet = ps.getGeneratedKeys();
        int generatedAutoIncrementId = 0;
        if (keyResultSet.next()) {
        	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
        	conn.commit();
        }

        ps.close();
        keyResultSet.close();
        conn.close();
	}

	public void update(YearEndBonusCashGift bonus) throws SQLException, Exception {
		String sql = "update YearEndBonusCashGift set "
				+ " salaryGrade=?, STEP=?, basicSalary=?, cashGift=?, total=?, basicSalaryOct31=?, cashGift1=?, "
				+ " firstHalf13thMonth=?, firstHalfCashGift=?, secondHalf13thMonth=?, secondHalfCashGift=?, "
				+ " totalYearEndBonusCashGift=?, eamcCoopDeduction=?, netAmountDue=?, year=?, empId=? "
				+ " where yearEndBonusId=?";
		PreparedStatement ps  = conn.prepareStatement(sql);
		ps.setInt(1, bonus.getSalaryGrade());
        ps.setString(2, bonus.getSTEP());
        ps.setBigDecimal(3, bonus.getBasicSalary());
        ps.setBigDecimal(4, bonus.getCashGift());
        ps.setBigDecimal(5, bonus.getTotal());
        ps.setBigDecimal(6, bonus.getBasicSalaryOct31());
        ps.setBigDecimal(7, bonus.getCashGift1());
        ps.setBigDecimal(8, bonus.getFirstHalf13thMonth());
        ps.setBigDecimal(9, bonus.getFirstHalfCashGift());
        ps.setBigDecimal(10, bonus.getSecondHalf13thMonth());
        ps.setBigDecimal(11, bonus.getSecondHalfCashGift());
        ps.setBigDecimal(12, bonus.getTotalYearEndBonusCashGift());
        ps.setBigDecimal(13, bonus.getEamcCoopDeduction());
        ps.setBigDecimal(14, bonus.getNetAmountDue());
        ps.setInt(15, bonus.getYear());
        ps.setInt(16, bonus.getEmpId());
        ps.setInt(17, bonus.getYearEndBonusId());
 		ps.executeUpdate();
 		
 		conn.commit();
 		ps.close();
 		conn.close();
	}

	public void delete(YearEndBonusCashGift bonus) throws SQLException, Exception {
		String sql = "delete from YearEndBonusCashGift where yearEndBonusId=?";
		PreparedStatement ps  = conn.prepareStatement(sql);
		ps.setInt(1, bonus.getYearEndBonusId());
        ps.executeUpdate();
        
        conn.commit();
        ps.close();
        conn.close();
	}
	
	public void closeConnection() throws SQLException, Exception {
		conn.close();
	}
	
	public int[] batchUpdate(List<YearEndBonusCashGift> yebList) throws SQLException, Exception {
		int[] result = null;
		String sql = "update YearEndBonusCashGift set "
				+ " salaryGrade=?, STEP=?, basicSalary=?, cashGift=?, total=?, basicSalaryOct31=?, cashGift1=?, "
				+ " firstHalf13thMonth=?, firstHalfCashGift=?, secondHalf13thMonth=?, secondHalfCashGift=?, "
				+ " totalYearEndBonusCashGift=?, eamcCoopDeduction=?, netAmountDue=?, year=?, empId=? "
				+ " where yearEndBonusId=?";
		PreparedStatement ps  = conn.prepareStatement(sql);
		for (int i = 0; i < yebList.size(); i++) {
			YearEndBonusCashGift bonus = yebList.get(i);
			ps.setInt(1, bonus.getSalaryGrade());
			ps.setString(2, bonus.getSTEP());
			ps.setBigDecimal(3, bonus.getBasicSalary());
			ps.setBigDecimal(4, bonus.getCashGift());
			ps.setBigDecimal(5, bonus.getTotal());
			ps.setBigDecimal(6, bonus.getBasicSalaryOct31());
			ps.setBigDecimal(7, bonus.getCashGift1());
			ps.setBigDecimal(8, bonus.getFirstHalf13thMonth());
			ps.setBigDecimal(9, bonus.getFirstHalfCashGift());
			ps.setBigDecimal(10, bonus.getSecondHalf13thMonth());
			ps.setBigDecimal(11, bonus.getSecondHalfCashGift());
			ps.setBigDecimal(12, bonus.getTotalYearEndBonusCashGift());
			ps.setBigDecimal(13, bonus.getEamcCoopDeduction());
			ps.setBigDecimal(14, bonus.getNetAmountDue());
			ps.setInt(15, bonus.getYear());
			ps.setInt(16, bonus.getEmpId());
			ps.setInt(17, bonus.getYearEndBonusId());
			ps.addBatch();
		}
		result = ps.executeBatch();
		conn.commit();
		ps.close();
		return result;
	}
	
    public int[] batchInsert(List<YearEndBonusCashGift> yebList) throws SQLException, Exception {
    	int[] result = null;
    	String sql = "insert into YearEndBonusCashGift "
				+ " (salaryGrade, STEP, basicSalary, cashGift, total, basicSalaryOct31, cashGift1, "
				+ " firstHalf13thMonth, firstHalfCashGift, secondHalf13thMonth, secondHalfCashGift, "
				+ " totalYearEndBonusCashGift, eamcCoopDeduction, netAmountDue, year, empId) "
				+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < yebList.size(); i++) {
        	YearEndBonusCashGift bonus = yebList.get(i);
        	ps.setInt(1, bonus.getSalaryGrade());
        	ps.setString(2, bonus.getSTEP());
        	ps.setBigDecimal(3, bonus.getBasicSalary());
        	ps.setBigDecimal(4, bonus.getCashGift());
        	ps.setBigDecimal(5, bonus.getTotal());
        	ps.setBigDecimal(6, bonus.getBasicSalaryOct31());
        	ps.setBigDecimal(7, bonus.getCashGift1());
        	ps.setBigDecimal(8, bonus.getFirstHalf13thMonth());
        	ps.setBigDecimal(9, bonus.getFirstHalfCashGift());
        	ps.setBigDecimal(10, bonus.getSecondHalf13thMonth());
        	ps.setBigDecimal(11, bonus.getSecondHalfCashGift());
        	ps.setBigDecimal(12, bonus.getTotalYearEndBonusCashGift());
        	ps.setBigDecimal(13, bonus.getEamcCoopDeduction());
        	ps.setBigDecimal(14, bonus.getNetAmountDue());
        	ps.setInt(15, bonus.getYear());
        	ps.setInt(16, bonus.getEmpId());
        	ps.addBatch();
    }
        result = ps.executeBatch();
		conn.commit();
		ps.close();
		return result;
    }
    
    public List<YearEndBonusCashGift> getAllByEmployeeId(int empId) throws SQLException, Exception {
    	List<YearEndBonusCashGift> resultList = new ArrayList<YearEndBonusCashGift>();
    	String sql = "select * from YearEndBonusCashGift where empId = ?";
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setInt(1, empId);
    	
    	ResultSet rs = ps.executeQuery();
    	while (rs.next()) {
    		YearEndBonusCashGift yeb = new YearEndBonusCashGift();
    		yeb.setYearEndBonusId(rs.getInt("yearEndBonusCashGiftId"));
    		yeb.setSalaryGrade(rs.getInt("salaryGrade"));
    		yeb.setSTEP(rs.getString("STEP"));
    		yeb.setBasicSalary(rs.getBigDecimal("basicSalary"));
    		yeb.setCashGift(rs.getBigDecimal("cashGift"));
    		yeb.setTotal(rs.getBigDecimal("total")); 
    		yeb.setBasicSalaryOct31(rs.getBigDecimal("basicSalaryOct31"));
    		yeb.setCashGift1(rs.getBigDecimal("cashGift1"));
    		yeb.setFirstHalf13thMonth(rs.getBigDecimal("firstHalf13thMonth"));
    		yeb.setFirstHalfCashGift(rs.getBigDecimal("firstHalfCashGift"));
    		yeb.setSecondHalf13thMonth(rs.getBigDecimal("secondHalf13thMonth"));      
    		yeb.setSecondHalfCashGift(rs.getBigDecimal("secondHalfCashGift"));       
    		yeb.setTotalYearEndBonusCashGift(rs.getBigDecimal("totalYearEndBonusCashGift"));
    		yeb.setEamcCoopDeduction(rs.getBigDecimal("eamcCoopDeduction"));
    		yeb.setNetAmountDue(rs.getBigDecimal("netAmountDue"));
    		yeb.setYear(rs.getInt("year"));
    		yeb.setEmpId(rs.getInt("empId"));
    		resultList.add(yeb);
    	}
    	rs.close();
    	ps.close();
    	return resultList;
    }
    
    public List<YearEndBonusCashGift> getAllByJobTitleId(int jtId) throws SQLException, Exception {
    	List<YearEndBonusCashGift> resultList = new ArrayList<YearEndBonusCashGift>();
    	String sql = "select * from YearEndBonusCashGift "
    			+ " where empId in "
    			+ " (select empId from Employee where jobTitleId = ?)";
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setInt(1, jtId);
    	
    	ResultSet rs = ps.executeQuery();
    	while (rs.next()) {
    		YearEndBonusCashGift yeb = new YearEndBonusCashGift();
    		yeb.setYearEndBonusId(rs.getInt("yearEndBonusCashGiftId"));
    		yeb.setSalaryGrade(rs.getInt("salaryGrade"));
    		yeb.setSTEP(rs.getString("STEP"));
    		yeb.setBasicSalary(rs.getBigDecimal("basicSalary"));
    		yeb.setCashGift(rs.getBigDecimal("cashGift"));
    		yeb.setTotal(rs.getBigDecimal("total")); 
    		yeb.setBasicSalaryOct31(rs.getBigDecimal("basicSalaryOct31"));
    		yeb.setCashGift1(rs.getBigDecimal("cashGift1"));
    		yeb.setFirstHalf13thMonth(rs.getBigDecimal("firstHalf13thMonth"));
    		yeb.setFirstHalfCashGift(rs.getBigDecimal("firstHalfCashGift"));
    		yeb.setSecondHalf13thMonth(rs.getBigDecimal("secondHalf13thMonth"));      
    		yeb.setSecondHalfCashGift(rs.getBigDecimal("secondHalfCashGift"));       
    		yeb.setTotalYearEndBonusCashGift(rs.getBigDecimal("totalYearEndBonusCashGift"));
    		yeb.setEamcCoopDeduction(rs.getBigDecimal("eamcCoopDeduction"));
    		yeb.setNetAmountDue(rs.getBigDecimal("netAmountDue"));
    		yeb.setYear(rs.getInt("year"));
    		yeb.setEmpId(rs.getInt("empId"));
    		resultList.add(yeb);
    	}
    	rs.close();
    	ps.close();
    	return resultList;
    }

}
