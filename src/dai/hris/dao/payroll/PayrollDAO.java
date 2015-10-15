package dai.hris.dao.payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dai.hris.dao.DBConstants;
import dai.hris.model.EmployeePayrollRunExt;
import dai.hris.model.TaxTable;

public class PayrollDAO {
	private Connection conn;

	public PayrollDAO() {
		try {
			/* MS SQL CODE */    		
			Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
			conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
			conn.setAutoCommit(false);
		} catch (SQLException sqle) {
			System.out.println("PayrollDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("PayrollDAO :" + e.getMessage());
		}
	}	

	
	
}
