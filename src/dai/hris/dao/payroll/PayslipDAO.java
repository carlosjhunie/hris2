package dai.hris.dao.payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dai.hris.dao.DBConstants;
import dai.hris.model.Payslip;

public class PayslipDAO {
	private Connection conn;

	public PayslipDAO() {
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
	
	public int saveOrUpdate(Payslip ps) throws SQLException {
		int res = -1;
		
		return res;
	}
	
	public Payslip getPayslipByEmpId(int empId) throws SQLException {
		Payslip payslip = null;
		
		return payslip;
	}

}
