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
import dai.hris.model.EmployeeOvertime;
import dai.hris.model.EmployeePayrollRunExt;


public class PayrollRunDAO {

	private Connection conn = null;

	public PayrollRunDAO() {    	
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
	
	//get employee payroll run that is not locked.
	public int getEmployeePayrollRunId(int payrollPeriodId, int empId) throws SQLException {
		int index = 1;
		int payrollRunId = 0;
		String sql = "select employeePayrollRunId from employeePayrollRun where payrollPeriodId=? and empId=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(index++, payrollPeriodId);
		ps.setInt(index++, empId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			payrollRunId = rs.getInt("employeePayrollRunId");			
		}
		ps.close();
		rs.close();
		return payrollRunId;

	}

	
	public List<EmployeePayrollRunExt> getBasicDataForEmployeeListByPayPeriod(int payPeriod, String payrollType) throws SQLException {
		ArrayList<EmployeePayrollRunExt> resultList = new ArrayList<EmployeePayrollRunExt>();
		StringBuffer sql = new StringBuffer();
		sql.append("	 SELECT ");
		sql.append(" 		e.empId, e.empNo, e.lastname, e.firstname, e.middlename, e.dateOfBirth, e.gender, e.civilStatus, e.email, e.mobileNo, e.telNo,  ");
		sql.append("		e.jobTitleId, jt.jobTitle, e.departmentId, d.departmentName, e.divisionId, dv.divisionName, e.employeeTypeId, et.employeeTypeName,  ");
		sql.append(" 		e.empStatus, e.sss, e.gsis, e.hdmf, e.tin, e.phic, e.taxstatus, e.employmentDate, e.endOfContract, e.superVisor1Id, e.superVisor2Id, e.superVisor3Id, ");
		
		sql.append(" 		epi.monthlyRate, epi.dailyRate, epi.hourlyRate, epi.shiftingScheduleId, epi.foodAllowance, ");
		sql.append(" 		epi.cola, epi.taxShield, epi.transAllowance, epi.payrollType, epi.ban, epi.bankNameBan, ");
		sql.append(" 		epi.gsisEmployeeShare, epi.gsisEmployerShare, epi.philhealthEmployeeShare, epi.philhealthEmployerShare, ");
		sql.append(" 		epi.pagibigEmployeeShare, epi.pagibigEmployerShare, ");
		 
		sql.append("		pp.payrollPeriodId, pp.payYear, pp.payMonth, pp.payrollType, pp.fromDate, pp.toDate, pp.payDate, pp.payrollCode, pp.numWorkDays, pp.payPeriod, pp.deductGovtFlag, pp.isForSecondHalf ");
		
		sql.append(" 	FROM employee e, empPayrollInfo epi, payrollPeriod pp, jobTitle jt, department d, division dv, employeeType et ");
		sql.append("	WHERE e.empId = epi.empId ");
		sql.append("		AND e.jobTitleId = jt.jobTitleId ");
		sql.append("		AND e.departmentId = d.departmentId ");
		sql.append("		AND e.divisionId = dv.divisionId ");
		sql.append("		AND e.employeeTypeId = et.employeeTypeId ");
		sql.append("		AND pp.payrollType = epi.payrollType ");
		sql.append("		AND lockedAt IS NULL ");
		sql.append("		AND pp.payrollPeriodId = ? ");
		sql.append("		AND pp.payrollType = ? ");
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, payPeriod);
		ps.setString(2, payrollType);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			//do not populate computed fields
			EmployeePayrollRunExt eprE = new EmployeePayrollRunExt();
			eprE.setEmpId(rs.getInt("empId"));
			eprE.setEmpNo(rs.getString("empNo"));
			eprE.setFirstname(rs.getString("firstname"));
			eprE.setMiddlename(rs.getString("middlename"));
			eprE.setLastname(rs.getString("lastname"));
			eprE.setGender(rs.getString("gender"));
			eprE.setEmpStatus(rs.getString("empStatus"));
			eprE.setTaxStatus(rs.getString("taxStatus"));
			//eprE.setBasicPay(rs.getBigDecimal("basicPay"));
			eprE.setCivilStatus(rs.getString("civilStatus"));
			eprE.setDateOfBirth(rs.getString("dateOfBirth"));
			eprE.setJobTitleId(rs.getInt("jobTitleId"));
			eprE.setJobTitle(rs.getString("jobTitle"));
			eprE.setDepartmentId(rs.getInt("departmentId"));
			eprE.setDepartmentName(rs.getString("departmentName"));
			eprE.setDivisionId(rs.getInt("divisionId"));
			eprE.setDivisionName(rs.getString("divisionName"));
			eprE.setEmail(rs.getString("email"));
			eprE.setTelNo(rs.getString("telNo"));
			eprE.setMobileNo(rs.getString("mobileNo"));
			eprE.setEmployeeTypeId(rs.getInt("employeeTypeId"));
			eprE.setEmployeeTypeName(rs.getString("employeeTypeName"));
			eprE.setEmploymentDate(rs.getString("employmentDate"));
			eprE.setEndOfContract(rs.getString("endOfContract"));
			
			
			eprE.setDailyRate(rs.getBigDecimal("dailyRate"));
			eprE.setMonthlyRate(rs.getBigDecimal("monthlyRate"));
			eprE.setHourlyRate(rs.getBigDecimal("hourlyRate"));
			eprE.setShiftingScheduleId(rs.getInt("shiftingScheduleId"));
			eprE.setCola(rs.getBigDecimal("cola"));
			eprE.setFoodAllowance(rs.getBigDecimal("foodAllowance"));
			eprE.setTaxShield(rs.getBigDecimal("taxShield"));
			eprE.setTransAllowance(rs.getBigDecimal("transAllowance"));
			eprE.setBan(rs.getString("ban"));
			eprE.setBankNameBan(rs.getString("bankNameBan"));
			

			eprE.setPayrollPeriodId(rs.getInt("payrollPeriodId"));
			eprE.setPayDate(rs.getDate("payDate"));
			eprE.setPayPeriod(rs.getString("payPeriod"));
			eprE.setFromDate(rs.getDate("fromDate"));
			eprE.setToDate(rs.getDate("toDate"));
			eprE.setPayrollCode(rs.getString("payrollCode"));
			eprE.setPayrollType(rs.getString("payrollType"));
			eprE.setPayMonth(rs.getInt("payMonth"));
			eprE.setPayYear(rs.getInt("payYear"));
			eprE.setNumWorkDays(rs.getInt("numWorkDays"));
			eprE.setDeductGovtFlag(rs.getString("deductGovtFlag"));
			eprE.setIsForSecondHalf(rs.getString("isForSecondHalf"));
			
			
			eprE.setGsisEmployeeShare(rs.getBigDecimal("gsisEmployeeShare"));
			eprE.setGsisEmployerShare(rs.getBigDecimal("gsisEmployerShare"));
			eprE.setPagibigEmployeeShare(rs.getBigDecimal("pagibigEmployeeShare"));
			eprE.setPagibigEmployerShare(rs.getBigDecimal("pagibigEmployerShare"));
			eprE.setPhilhealthEmployeeShare(rs.getBigDecimal("philhealthEmployeeShare"));
			eprE.setPhilhealthEmployerShare(rs.getBigDecimal("philhealthEmployerShare"));


			//eprE.setGrossPay(rs.getBigDecimal("grossPay"));
			//eprE.setGsisEmployeeShare(rs);
			resultList.add(eprE);
			//add ArrayList sorting?
			
			
		}
		rs.close();
		return resultList;
	}
	
	
	
	   
    public int add(EmployeePayrollRunExt eprE) throws SQLException, Exception {
		
    	StringBuffer sql = new StringBuffer();
    	sql.append("     INSERT INTO employeePayrollRun (empId,taxStatus,basicPay,absences,tardiness,undertime,overtime, ");
    	sql.append("     		 leaveWOPay,nightDiff,holidayPay,taxableIncome,nontaxableIncome,grossPay, gsisEmployeeShare, gsisEmployerShare, ");
    	sql.append("     		 philhealthEmployeeShare,philhealthEmployerShare,pagibigEmployeeShare,pagibigEmployerShare, ");
    	sql.append("     		 loans,otherDeductions,withholdingTax,totalDeductions,netPay,payrollCode,payrollPeriodId,payrollRunStatus, ");
    	sql.append("     createdBy,createDate,updatedBy,updatedDate,lockedBy,lockedDate) ");
    	sql.append("     VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		PreparedStatement ps = conn.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS); 
		int index = 1;
		ps.setInt(index++, eprE.getEmpId());
		ps.setString(index++, eprE.getTaxStatus());
		ps.setDouble(index++, eprE.getBasicPay().doubleValue());  //populate this - value either from monthly, semi monthly, or daily rate
//		ps.setDouble(index++, eprE.getAbsences().doubleValue());
//		ps.setDouble(index++, eprE.getTardiness().doubleValue());
//		ps.setDouble(index++, eprE.getUndertime().doubleValue());
//		ps.setDouble(index++, eprE.getOvertime().doubleValue());
		
		ps.setDouble(index++, 0);
		ps.setDouble(index++, 0);
		ps.setDouble(index++, 0);
		ps.setDouble(index++, 0);
		
//		ps.setDouble(index++, eprE.getLeaveWOPay().doubleValue());
//		ps.setDouble(index++, eprE.getNightDiff().doubleValue());
//		ps.setDouble(index++, eprE.getHolidayPay().doubleValue());
		
		ps.setDouble(index++, 0);
		ps.setDouble(index++, 0);
		ps.setDouble(index++, 0);
		
		ps.setDouble(index++, eprE.getTaxableIncome().doubleValue());		
//		ps.setDouble(index++, eprE.getNontaxableIncome().doubleValue());
		ps.setDouble(index++, 0);
		
		ps.setDouble(index++, eprE.getGrossPay().doubleValue());
		ps.setDouble(index++, eprE.getGsisEmployeeShare().doubleValue());
		ps.setDouble(index++, eprE.getGsisEmployerShare().doubleValue());
				
		ps.setDouble(index++, eprE.getPhilhealthEmployeeShare().doubleValue());
		ps.setDouble(index++, eprE.getPhilhealthEmployerShare().doubleValue());		
		ps.setDouble(index++, eprE.getPagibigEmployeeShare().doubleValue());
		ps.setDouble(index++, eprE.getPagibigEmployerShare().doubleValue());
		
//		ps.setDouble(index++, eprE.getLoans().doubleValue());
		ps.setDouble(index++, 0);
		
//		ps.setDouble(index++, eprE.getOtherDeductions().doubleValue());
		ps.setDouble(index++, 0);
		
		ps.setDouble(index++, eprE.getWithholdingTax().doubleValue());
		ps.setDouble(index++, eprE.getTotalDeductions().doubleValue());
		ps.setDouble(index++, eprE.getNetPay().doubleValue());
		ps.setString(index++, eprE.getPayrollCode());
		ps.setInt(index++, eprE.getPayrollPeriodId());
		ps.setString(index++, eprE.getPayrollRunStatus());
		
		ps.setString(index++, eprE.getCreatedBy());
		ps.setDate(index++, eprE.getCreateDate());
		ps.setString(index++, eprE.getUpdatedBy());
		ps.setDate(index++, eprE.getUpdatedDate());
		ps.setString(index++, eprE.getLockedBy());
		ps.setDate(index++, eprE.getLockedDate());
		
		
		ps.executeUpdate();
		 ResultSet keyResultSet = ps.getGeneratedKeys(); 
		 int generatedAutoIncrementId = 0; 
		 	if (keyResultSet.next()) {
		 		generatedAutoIncrementId = (int) keyResultSet.getInt(1);
		 		eprE.setEmployeePayrollRunId(generatedAutoIncrementId);
		 		conn.commit(); 
		 	}
		 
		 ps.close();
		 keyResultSet.close();
		 return generatedAutoIncrementId;


	
}
	
	
	
    public int update(EmployeePayrollRunExt eprE) throws SQLException, Exception {
		
    	StringBuffer sql = new StringBuffer();
    	sql.append("     UPDATE employeePayrollRun SET empId=?,taxStatus=?,basicPay=?,absences=?,tardiness=?,undertime=?,overtime=?, ");
    	sql.append("     		 leaveWOPay=?,nightDiff=?,holidayPay=?,taxableIncome=?,nontaxableIncome=?,grossPay=?, gsisEmployeeShare=?, gsisEmployerShare=?, ");
    	sql.append("     		 philhealthEmployeeShare=?,philhealthEmployerShare=?,pagibigEmployeeShare=?,pagibigEmployerShare=?, ");
    	sql.append("     		 loans=?,otherDeductions=?,withholdingTax=?,totalDeductions=?,netPay=?,payrollCode=?,payrollPeriodId=?,payrollRunStatus=?, ");
    	sql.append("     createdBy=?,createDate=?,updatedBy=?,updatedDate=?,lockedBy=?,lockedDate=? ");
    	sql.append("     WHERE employeePayrollRunId = ? ");
		PreparedStatement ps = conn.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS); 
		int index = 1;
		ps.setInt(index++, eprE.getEmpId());
		ps.setString(index++, eprE.getTaxStatus());
		ps.setDouble(index++, eprE.getBasicPay().doubleValue());  //populate this - value either from monthly, semi monthly, or daily rate
//		ps.setDouble(index++, eprE.getAbsences().doubleValue());
//		ps.setDouble(index++, eprE.getTardiness().doubleValue());
//		ps.setDouble(index++, eprE.getUndertime().doubleValue());
//		ps.setDouble(index++, eprE.getOvertime().doubleValue());
		
		ps.setDouble(index++, 0);
		ps.setDouble(index++, 0);
		ps.setDouble(index++, 0);
		ps.setDouble(index++, 0);
		
//		ps.setDouble(index++, eprE.getLeaveWOPay().doubleValue());
//		ps.setDouble(index++, eprE.getNightDiff().doubleValue());
//		ps.setDouble(index++, eprE.getHolidayPay().doubleValue());
		
		ps.setDouble(index++, 0);
		ps.setDouble(index++, 0);
		ps.setDouble(index++, 0);
		
		ps.setDouble(index++, eprE.getTaxableIncome().doubleValue());		
		
//		ps.setDouble(index++, eprE.getNontaxableIncome().doubleValue());
		ps.setDouble(index++, 0);
		
		ps.setDouble(index++, eprE.getGrossPay().doubleValue());
		ps.setDouble(index++, eprE.getGsisEmployeeShare().doubleValue());
		ps.setDouble(index++, eprE.getGsisEmployerShare().doubleValue());
				
		ps.setDouble(index++, eprE.getPhilhealthEmployeeShare().doubleValue());
		ps.setDouble(index++, eprE.getPhilhealthEmployerShare().doubleValue());		
		ps.setDouble(index++, eprE.getPagibigEmployeeShare().doubleValue());
		ps.setDouble(index++, eprE.getPagibigEmployerShare().doubleValue());
		
//		ps.setDouble(index++, eprE.getLoans().doubleValue());
//		ps.setDouble(index++, eprE.getOtherDeductions().doubleValue());
		
		ps.setDouble(index++, 0);
		ps.setDouble(index++, 0);
		
		ps.setDouble(index++, eprE.getWithholdingTax().doubleValue());
		ps.setDouble(index++, eprE.getTotalDeductions().doubleValue());
		ps.setDouble(index++, eprE.getNetPay().doubleValue());
		ps.setString(index++, eprE.getPayrollCode());
		ps.setInt(index++, eprE.getPayrollPeriodId());
		ps.setString(index++, eprE.getPayrollRunStatus());
		
		ps.setString(index++, eprE.getCreatedBy());
		ps.setDate(index++, eprE.getCreateDate());
		ps.setString(index++, eprE.getUpdatedBy());
		ps.setDate(index++, eprE.getUpdatedDate());
		ps.setString(index++, eprE.getLockedBy());
		ps.setDate(index++, eprE.getLockedDate());
		ps.setInt(index++, eprE.getEmployeePayrollRunId());
		
		
		ps.executeUpdate();
		 ResultSet keyResultSet = ps.getGeneratedKeys(); 
		 int generatedAutoIncrementId = 0; 
		 	if (keyResultSet.next()) {
		 		generatedAutoIncrementId = (int) keyResultSet.getInt(1);
		 		eprE.setEmployeePayrollRunId(generatedAutoIncrementId);
		 		conn.commit(); 
		 	}
		 
		 ps.close();
		 keyResultSet.close();
		 return generatedAutoIncrementId;


	
}	
    
    
    public void updatePayrollPeriodStatus(int payrollPeriodId, String status, int empId) throws SQLException, Exception {
		
    	StringBuffer sql = new StringBuffer();
    	sql.append("UPDATE payrollPeriod SET status = ?, updatedAt = GETDATE()  WHERE payrollPeriodId = ?");
		PreparedStatement ps = conn.prepareStatement(sql.toString()); 
		int index = 1;
		ps.setString(index++, status);
		ps.setInt(index++, payrollPeriodId);		
		ps.executeUpdate();	 

		
		StringBuffer sql2 = new StringBuffer();
    	sql2.append("UPDATE employeePayrollRun SET payrollRunStatus = ? , updatedDate = GETDATE(), updatedBy = ? WHERE payrollPeriodId = ?");
		PreparedStatement ps2 = conn.prepareStatement(sql2.toString()); 
		index = 1;
		ps2.setString(index++, status);
		ps2.setInt(index++, empId);
		ps2.setInt(index++, payrollPeriodId);		
		ps2.executeUpdate();	
		
		conn.commit();
		 
		ps.close();		
		ps2.close();		
	
    }	
    
    public ArrayList<EmployeeOvertime> getEmployeeRenderedOvertimeWithinPayPeriod(int empId, Date payPeriodFromDate, Date payPeriodToDate) throws SQLException {
		ArrayList<EmployeeOvertime> resultList = new ArrayList<EmployeeOvertime>();
		EmployeeOvertime eo = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select e.* from empovertime e, payrollPeriod p ");
		sql.append("  where lockedAt is NULL ");
		sql.append("  and empId = ?  and e.status = 2 ");  //approved
		sql.append("  and dateRendered >= p.fromDate and dateRendered <= p.toDate ");  //is String implicitly converted to date?
		int index = 0;
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(index++, empId);
		ps.setDate(index++, payPeriodFromDate);
		ps.setDate(index++, payPeriodToDate);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			eo = new EmployeeOvertime();
			eo.setApprovedBy(rs.getInt("approvedBy"));
			eo.setCreatedBy(rs.getInt("createdBy"));
			eo.setCreatedDate(rs.getDate("createdDate"));
			eo.setDateRendered(rs.getString("dateRendered"));
			eo.setEmpId(rs.getInt("empId"));
			eo.setEmpOvertimeId(rs.getInt("empOvertimeId"));
			eo.setNoOfHours(rs.getInt("noOfHours"));
			eo.setRemarks(rs.getString("remarks"));
			eo.setSecondaryApprover(rs.getInt("secondaryApprover"));
			eo.setStatus(rs.getInt("status"));
			resultList.add(eo);
				
		}
		ps.close();
		rs.close();
		return resultList;	
    }
	
	
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}

	
	
}
