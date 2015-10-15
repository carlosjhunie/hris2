package dai.hris.service.payroll;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dai.hris.action.filemanager.util.EmployeeUtil;
import dai.hris.dao.absent.AbsentDAO;
import dai.hris.dao.filemanager.EmployeeIncomeDeductionDAO;
import dai.hris.dao.filemanager.HolidayDAO;
import dai.hris.dao.loan.LoanEntryDAO;
import dai.hris.dao.loan.LoanPaymentsDAO;
import dai.hris.dao.payroll.PayrollRunDAO;
import dai.hris.dao.tardiness.TardinessDAO;
import dai.hris.dao.timeentry.TimeEntryDAO;
import dai.hris.model.EmployeeOvertime;
import dai.hris.model.EmployeePayrollRunExt;
import dai.hris.model.Holiday;
import dai.hris.model.LoanEntry;
import dai.hris.model.PhicContributionTable;
import dai.hris.model.TaxTable;
import dai.hris.service.payroll.impl.PhicContributionTableService;
import dai.hris.service.payroll.impl.TaxTableService;


public class PayrollRunService implements IPayrollRunService {	
	
	/***
	 * get overtime for employee from a set date range
	 */
	public ArrayList<EmployeeOvertime> getEmployeeRenderedOvertimeWithinPayPeriod(int empId, Date payPeriodFromDate, Date payPeriodToDate) throws SQLException, Exception {
		PayrollRunDAO payrollRunDAO = new PayrollRunDAO();
		ArrayList<EmployeeOvertime> eoList = payrollRunDAO.getEmployeeRenderedOvertimeWithinPayPeriod(empId, payPeriodFromDate, payPeriodToDate);
		payrollRunDAO.closeConnection();
		return eoList;
	}	
	
	/**
	 * pass payrollPeriodId to generate payroll run
	 */
	public List<EmployeePayrollRunExt> generatePayrollByPayrollPeriod (int payrollPeriodId, String payrollType, int loggedEmpId) throws SQLException, Exception {
		PayrollRunDAO payrollRunDAO = new PayrollRunDAO();
		List<EmployeePayrollRunExt> employeePayrollRunEmpList = null;
		try {
			employeePayrollRunEmpList = payrollRunDAO.getBasicDataForEmployeeListByPayPeriod(payrollPeriodId, payrollType);
			for(EmployeePayrollRunExt eprE: employeePayrollRunEmpList) {
				if (PayrollConstants.REGULAR.equalsIgnoreCase(eprE.getEmployeeTypeName())) {
					generateRegularEmployeePayrollRun(eprE, payrollRunDAO, loggedEmpId);
				} else if (PayrollConstants.PROBATIONARY.equalsIgnoreCase(eprE.getEmployeeTypeName())) {
					generateProbationaryEmployeePayrollRun(eprE, payrollRunDAO, loggedEmpId);
				} else if (PayrollConstants.CONTRACTUAL.equalsIgnoreCase(eprE.getEmployeeTypeName())) {
					generateContractualEmployeePayrollRun(eprE, payrollRunDAO, loggedEmpId);
				} else {
					//add some implementation here
				}			
			}	
		
			payrollRunDAO.updatePayrollPeriodStatus(payrollPeriodId, "G", loggedEmpId); //update to Generated status	
			System.out.println("update to (G)enerated done for payrollPeriodId: " + payrollPeriodId);
		} catch (Exception e) {
			e.printStackTrace();
			payrollRunDAO.updatePayrollPeriodStatus(payrollPeriodId, "N", 0);
			System.err.println("Cannot change payroll period status to (G)enerated. Set back value to (N)ew.");
		}
		payrollRunDAO.closeConnection();
		return employeePayrollRunEmpList;		
	}
	
	/**
	 * Get Basic PAY
	 * TODO - weekly rate needs to be verified
	 * TODO: need to clarify: Contractual, and if basic pay, it should be daily
	 * @param employeePayrollRunExt
	 * @return
	 */
	private BigDecimal getBasicPay(EmployeePayrollRunExt employeePayrollRunExt) {
		BigDecimal basicPay = BigDecimal.ZERO;
		if (employeePayrollRunExt.getPayrollType().equals(PayrollConstants.WEEKLY)) {
			basicPay =  employeePayrollRunExt.getMonthlyRate().divide(new BigDecimal(4), BigDecimal.ROUND_HALF_UP);
		} else if (employeePayrollRunExt.getPayrollType().equals(PayrollConstants.SEMI_MONTHLY)) {
			basicPay =  employeePayrollRunExt.getMonthlyRate().divide(new BigDecimal(2), BigDecimal.ROUND_HALF_UP);
		} else if (employeePayrollRunExt.getPayrollType().equals(PayrollConstants.MONTHLY)) {
			basicPay =  employeePayrollRunExt.getMonthlyRate();
		}
		employeePayrollRunExt.setBasicPay(basicPay);
		return basicPay;
	}
	
	
	/**
	 * get the Employee share for GSIS, PHIC, HDMF
	 * @param employeePayrollRunExt
	 * @return
	 */
	private BigDecimal getStandardGovtDeductions(EmployeePayrollRunExt employeePayrollRunExt) {
		BigDecimal standardGovtDeductions = BigDecimal.ZERO;
		
		if(employeePayrollRunExt.getGsisEmployeeShare() != null){
			standardGovtDeductions = standardGovtDeductions.add(employeePayrollRunExt.getGsisEmployeeShare());
		}
		
		if(employeePayrollRunExt.getPhilhealthEmployeeShare() != null){
			standardGovtDeductions = standardGovtDeductions.add(employeePayrollRunExt.getPhilhealthEmployeeShare());
		}
		
		if(employeePayrollRunExt.getPagibigEmployeeShare() != null){
			standardGovtDeductions = standardGovtDeductions.add(employeePayrollRunExt.getPagibigEmployeeShare());
		}
		
		
		return standardGovtDeductions;
	}
	
	private BigDecimal getTardinessAndAbsences(EmployeePayrollRunExt employeePayrollRunExt) {
		BigDecimal tardinessAndAbsences = BigDecimal.ZERO;
		
		if(employeePayrollRunExt.getTardiness() != null){
			tardinessAndAbsences = tardinessAndAbsences.add(employeePayrollRunExt.getTardiness());
		}
		
		if(employeePayrollRunExt.getAbsences() != null){
			tardinessAndAbsences = tardinessAndAbsences.add(employeePayrollRunExt.getAbsences());
		}
		
		return tardinessAndAbsences;
	}
	
	
	/**
	 * REGULAR EMPLOYEES
	 * @param regularEmpExt
	 * 
	 * 
	 */
	private void generateRegularEmployeePayrollRun(EmployeePayrollRunExt regularEmpExt, PayrollRunDAO payrollRunDAO, int loggedEmpId) {
		ITaxTableService taxTableService = new TaxTableService();	
		BigDecimal deductions = BigDecimal.ZERO;
		BigDecimal basicIncome = BigDecimal.ZERO;
		BigDecimal taxableIncome = BigDecimal.ZERO;
		BigDecimal witholdingTax = BigDecimal.ZERO;
		BigDecimal netPay = BigDecimal.ZERO;
		BigDecimal grossPay =  BigDecimal.ZERO;
		try {
			/*
			 * as per discussion with team, comment out overtime, since hospital uses offsetting
			 * regularEmpExt.setOvertime(getOvertimePay(regularEmpExt.getEmpId(), regularEmpExt.getFromDate(), regularEmpExt.getToDate()));
			 * 
			 * comment out holiday
			 * regularEmpExt.setHolidayPay(getHolidayPay(regularEmpExt, regularEmpExt.getFromDate(), regularEmpExt.getToDate()));		
			 */
			
				
			//regularEmpExt.setNightDiff(getNightDiffPay(regularEmpExt, regularEmpExt.getFromDate(), regularEmpExt.getToDate()));
			//ROY: NO TARDINESS and Absences deduction for regular employee
			//regularEmpExt.setTardiness(getTardiness(regularEmpExt.getEmpId(), regularEmpExt.getFromDate(), regularEmpExt.getToDate()));
			//regularEmpExt.setAbsences(getAbsences(regularEmpExt.getEmpId(), regularEmpExt.getFromDate(), regularEmpExt.getToDate()));
			
			regularEmpExt.setPagibigEmployeeShare(getPagibigShare(regularEmpExt));
			regularEmpExt.setPagibigEmployerShare(getPagibigShare(regularEmpExt));
			regularEmpExt.setGsisEmployeeShare(getGSISEmployeeShare(regularEmpExt));
			regularEmpExt.setGsisEmployerShare(getGSISEmployerShare(regularEmpExt));
			regularEmpExt.setPhilhealthEmployeeShare(getPhilHealthShare(regularEmpExt));
			regularEmpExt.setPhilhealthEmployerShare(getPhilHealthShare(regularEmpExt));
			regularEmpExt.setOtherTaxableIncome(getOtherTaxableIncome(regularEmpExt));
			regularEmpExt.setNontaxableIncome(getNonTaxableIncome(regularEmpExt));
			regularEmpExt.setOtherDeductions(getOtherDeductions(regularEmpExt));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//basicIncome = basicPay + OT + HP + ND 
		
		basicIncome = basicIncome.add(getBasicPay(regularEmpExt));
				
//		if(regularEmpExt.getOvertime() != null){
//			basicIncome = basicIncome.add(regularEmpExt.getOvertime());
//		}
		
//		if(regularEmpExt.getHolidayPay() != null){
//			basicIncome = basicIncome.add(regularEmpExt.getHolidayPay());
//		}
		
//		if(regularEmpExt.getNightDiff() != null){
//			basicIncome = basicIncome.add(regularEmpExt.getNightDiff());
//		}
		
		
		
		
		if (PayrollConstants.YES.equals(regularEmpExt.getDeductGovtFlag())) {  //deduct govt deductions?
			if(getTardinessAndAbsences(regularEmpExt) != null){
				deductions = getStandardGovtDeductions(regularEmpExt).add(getTardinessAndAbsences(regularEmpExt));
			}			
		} else {
			//TODO
			//CHECK This
			deductions = getTardinessAndAbsences(regularEmpExt);
		}

		/**
		 * TODO need to check how to compute for total deductions
		 */
		regularEmpExt.setTotalDeductions(deductions);
		
		
		//Taxable income = basicIncome - (SSS/PhilHealth/Pag-IBIG deductions + Tardiness + Absences)
		if(regularEmpExt.getOtherTaxableIncome() != null){
			taxableIncome = basicIncome.add(regularEmpExt.getOtherTaxableIncome()).subtract(deductions);
		} else {
			taxableIncome = basicIncome.subtract(deductions);
		}
		
		
		regularEmpExt.setTaxableIncome(taxableIncome);
		
		System.out.println("Start for empid: " 					+ regularEmpExt.getEmpId());		
		System.out.println("     Employee is " 					+ regularEmpExt.getEmployeeTypeName());
		System.out.println("     Payroll Type:   " 				+ regularEmpExt.getPayrollType());
		System.out.println("      Basic Income (basic pay, overtime,  holiday,  night differential):   " + basicIncome.toPlainString());	
		System.out.println("        Basic Pay:   " 				+ getBasicPay(regularEmpExt));
		System.out.println("        Holiday:   " 					+ regularEmpExt.getHolidayPay());
		System.out.println("        Night Diff:   " 				+ regularEmpExt.getNightDiff());
		System.out.println("        Overtime:   " 					+ regularEmpExt.getOvertime());
		System.out.println("      Other Taxable Income:   " 		+ regularEmpExt.getOtherTaxableIncome());
		System.out.println("      Non-Taxable Income:   " 		+ regularEmpExt.getNontaxableIncome());
		System.out.println("      Deduct Govt Deductions? " 		+ regularEmpExt.getDeductGovtFlag());
		System.out.println("      Standard Govt Deductions: " 	+ getStandardGovtDeductions(regularEmpExt));
		System.out.println("     Taxable income: " 				+ taxableIncome.toPlainString());
		System.out.println(" ");
		System.out.println("     Tardiness:   " 				+ regularEmpExt.getTardiness());
		System.out.println("     Absences:   " 					+ regularEmpExt.getAbsences());
		System.out.println("     Other Deductions:   " 					+ regularEmpExt.getOtherDeductions());
		System.out.println("     Deductions (govt, tardiness, absences):     " + deductions.toPlainString());

		
		/**
		 * TODO:
		 *need to determine kelan second cutoff (dun bawas sss) - this is impt for semi monthly
		 *when is the crediting of allowances?
		 *do we need to factor in tax shield?
		 *
		 *need to factor in empIncome and empDeduction
		 *
		 *where is philhealth share? in empDeduction? need to factor in too
		 */
		
		
		TaxTable employeeTaxTable = null;

		try {
			employeeTaxTable = taxTableService.getTaxTableBySalaryAndTaxStatusAndPayrollType(taxableIncome, regularEmpExt.getTaxStatus(), regularEmpExt.getPayrollType());
			//Tax =	1,875 + [(24,006.20-17,917) X .25]
			if(employeeTaxTable != null){
				witholdingTax = employeeTaxTable.getTaxAmount().add((taxableIncome.subtract(employeeTaxTable.getSalaryBase()).multiply(new BigDecimal(employeeTaxTable.getTaxPercentage()))));
			}
			
			
			System.out.println("     Witholding Tax: " +  witholdingTax.toPlainString());
			regularEmpExt.setWithholdingTax(witholdingTax);
			//netPay = taxable income + non taxable income - witholding tax - other deductions
			
			if(regularEmpExt.getNontaxableIncome() != null){
				netPay = taxableIncome.add(regularEmpExt.getNontaxableIncome()).subtract(witholdingTax);
			} else {
				netPay = taxableIncome.subtract(witholdingTax);
			}
			
			if(regularEmpExt.getOtherDeductions() != null){
				netPay = netPay.subtract(regularEmpExt.getOtherDeductions());
			}
			
			//Deduct Loans then Save Loan Deduction as payment added by Ian			
			netPay = netPay.subtract(getEmployeeLoans(regularEmpExt.getEmpId()));
			regularEmpExt.setLoans(getEmployeeLoans(regularEmpExt.getEmpId()));
			
			regularEmpExt.setNetPay(netPay);
			
		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			sqle.printStackTrace();
		} catch (Exception e)	{
			e.printStackTrace();
		}

		//TODO this is wrong
		//gross pay is basicIncome + non-taxable income
		grossPay =  basicIncome.add(regularEmpExt.getNontaxableIncome());
		regularEmpExt.setGrossPay(grossPay);
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("     Gross Pay: " + regularEmpExt.getGrossPay().toPlainString());
		System.out.println("     Net Pay: " + regularEmpExt.getNetPay().toPlainString());
		System.out.println("End for empid: " + regularEmpExt.getEmpId());
		regularEmpExt.setCreateDate(EmployeeUtil.getCurrentSystemDateSqlFormat());
		regularEmpExt.setCreatedBy(Integer.toString(loggedEmpId)); 
		
		try {
			int employeePayrollRunId = payrollRunDAO.getEmployeePayrollRunId(regularEmpExt.getPayrollPeriodId(), regularEmpExt.getEmpId());
			if (employeePayrollRunId > 0) {
				regularEmpExt.setEmployeePayrollRunId(employeePayrollRunId);
				payrollRunDAO.update(regularEmpExt);
			} else {
				payrollRunDAO.add(regularEmpExt);
			}			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private BigDecimal getEmployeeLoans(int empId) throws SQLException, Exception {
		BigDecimal loanAmount = BigDecimal.ZERO;
		LoanEntryDAO LoanEntryDAO = new LoanEntryDAO();
		LoanPaymentsDAO loanPaymentsDAO = new LoanPaymentsDAO();
		
		List<LoanEntry> loanEntryList = LoanEntryDAO.getAllLoanEntryByEmpId(empId);
		
		for (LoanEntry loanEntry : loanEntryList) {
			if("Y".equals(loanEntry.getDeductionFlagActive())){

				/**
				 * TODO: for subtraction of loans (automatic)
				 * pwede tayong mag create ng stand-alone executable batch file para marun ng windows scheduler
				 * na magpapasok ng entry sa LoanPayment table every month
				 * at mag uupdate din sa LoanEntry table
				 * 
				 */
				
				BigDecimal loanPayments = loanPaymentsDAO.getTotalLoanPaymentsByLoanEntryId(loanEntry.getLoanEntryId());
				int compareResult = loanPayments.compareTo(loanEntry.getLoanAmount());		
				System.out.println("loanPayments: " + loanPayments.toPlainString());
				System.out.println("loanEntry.getLoanAmount(): " + loanEntry.getLoanAmount().toPlainString());
				System.out.println("compareResult: " + compareResult);
				
				if(compareResult == 0){
					continue;
				} else if(compareResult == 1){
					continue;
				} else {
					
				}
			}			
		}
		
		
		return loanAmount;
	}
	
	
	private BigDecimal getAbsences(int empId, Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * PROBATIONARY EMPLOYEE
	 * @param probationaryEmpExt
	 */
	private void generateProbationaryEmployeePayrollRun(EmployeePayrollRunExt probationaryEmpExt, PayrollRunDAO payrollRunDAO, int loggedEmpId) {
		ITaxTableService taxTableService = new TaxTableService();	
		BigDecimal deductions = BigDecimal.ZERO;
		BigDecimal basicIncome = BigDecimal.ZERO;
		BigDecimal taxableIncome = BigDecimal.ZERO;
		BigDecimal witholdingTax = BigDecimal.ZERO;
		BigDecimal netPay = BigDecimal.ZERO;
		BigDecimal grossPay =  BigDecimal.ZERO;
		try {
			/*
			 *
			 * as per discussion with team, comment out overtime, since hospital uses offsetting
			 * probationaryEmpExt.setOvertime(getOvertimePay(probationaryEmpExt.getEmpId(), probationaryEmpExt.getFromDate(), probationaryEmpExt.getToDate()));
			 *
			 * comment out holiday
			 * probationaryEmpExt.setHolidayPay(getHolidayPay(probationaryEmpExt, probationaryEmpExt.getFromDate(), probationaryEmpExt.getToDate()));	
			 */

		
			//probationaryEmpExt.setNightDiff(getNightDiffPay(probationaryEmpExt, probationaryEmpExt.getFromDate(), probationaryEmpExt.getToDate()));
			probationaryEmpExt.setTardiness(getTardiness(probationaryEmpExt));
			probationaryEmpExt.setAbsences(getAbsences(probationaryEmpExt));
			
			probationaryEmpExt.setPagibigEmployeeShare(getPagibigShare(probationaryEmpExt));
			probationaryEmpExt.setPagibigEmployerShare(getPagibigShare(probationaryEmpExt));
			probationaryEmpExt.setGsisEmployeeShare(getGSISEmployeeShare(probationaryEmpExt));
			probationaryEmpExt.setGsisEmployerShare(getGSISEmployerShare(probationaryEmpExt));			
			probationaryEmpExt.setPhilhealthEmployeeShare(getPhilHealthShare(probationaryEmpExt));
			probationaryEmpExt.setPhilhealthEmployerShare(getPhilHealthShare(probationaryEmpExt));
			probationaryEmpExt.setOtherTaxableIncome(getOtherTaxableIncome(probationaryEmpExt));
			probationaryEmpExt.setNontaxableIncome(getNonTaxableIncome(probationaryEmpExt));
			probationaryEmpExt.setOtherDeductions(getOtherDeductions(probationaryEmpExt));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//basicIncome = basicPay + OT + HP + ND 		
		basicIncome = basicIncome.add(getBasicPay(probationaryEmpExt));
		
		if(probationaryEmpExt.getOvertime() != null){
			basicIncome = basicIncome.add(probationaryEmpExt.getOvertime());
		}
		
		if(probationaryEmpExt.getHolidayPay() != null){
			basicIncome = basicIncome.add(probationaryEmpExt.getHolidayPay());
		}
		
		if(probationaryEmpExt.getNightDiff() != null){
			basicIncome = basicIncome.add(probationaryEmpExt.getNightDiff());
		}
		
		if (PayrollConstants.YES.equals(probationaryEmpExt.getDeductGovtFlag())) {  //deduct govt deductions?
			deductions = getStandardGovtDeductions(probationaryEmpExt).add(getTardinessAndAbsences(probationaryEmpExt));
		} else {
			deductions = getTardinessAndAbsences(probationaryEmpExt);
		}

		/**
		 * TODO need to check how to compute for total deductions
		 */
		probationaryEmpExt.setTotalDeductions(deductions);
		
		
		//Taxable income = basicIncome - (SSS/PhilHealth/Pag-IBIG deductions + Tardiness + Absences)
		if(probationaryEmpExt.getOtherTaxableIncome() != null){
			taxableIncome = basicIncome.add(probationaryEmpExt.getOtherTaxableIncome()).subtract(deductions);
		} else {
			taxableIncome = basicIncome.subtract(deductions);
		}
		
		
		
		probationaryEmpExt.setTaxableIncome(taxableIncome);
		
		System.out.println("Start for empid: " 					+ probationaryEmpExt.getEmpId());
		System.out.println("     Employee is " 					+ probationaryEmpExt.getEmployeeTypeName());
		System.out.println("     Payroll Type:   " 				+ probationaryEmpExt.getPayrollType());
		System.out.println("      Basic Income (basic pay, overtime,  holiday,  night differential):   " + basicIncome.toPlainString());	
		System.out.println("        Basic Pay:   " 				+ getBasicPay(probationaryEmpExt));
		System.out.println("        Holiday:   " 					+ probationaryEmpExt.getHolidayPay());
		System.out.println("        Night Diff:   " 				+ probationaryEmpExt.getNightDiff());
		System.out.println("        Overtime:   " 					+ probationaryEmpExt.getOvertime());
		System.out.println("      Other Taxable Income:   " 		+ probationaryEmpExt.getOtherTaxableIncome());
		System.out.println("      Non-Taxable Income:   " 		+ probationaryEmpExt.getNontaxableIncome());
		System.out.println("      Deduct Govt Deductions? " 		+ probationaryEmpExt.getDeductGovtFlag());
		System.out.println("      Standard Govt Deductions: " 	+ getStandardGovtDeductions(probationaryEmpExt));
		System.out.println("     Taxable income: " 				+ taxableIncome.toPlainString());
		System.out.println(" ");
		System.out.println("     Tardiness:   " 				+ probationaryEmpExt.getTardiness());
		System.out.println("     Absences:   " 					+ probationaryEmpExt.getAbsences());
		System.out.println("     Other Deductions:   " 					+ probationaryEmpExt.getOtherDeductions());
		System.out.println("     Deductions (govt, tardiness, absences):     " + deductions.toPlainString());

		
		/**
		 * TODO:
		 *need to determine kelan second cutoff (dun bawas sss) - this is impt for semi monthly
		 *when is the crediting of allowances?
		 *do we need to factor in tax shield?
		 *
		 *need to factor in empIncome and empDeduction
		 */
		
		
		TaxTable employeeTaxTable = null;

		try {
			employeeTaxTable = taxTableService.getTaxTableBySalaryAndTaxStatusAndPayrollType(taxableIncome, probationaryEmpExt.getTaxStatus(), probationaryEmpExt.getPayrollType());
			//Tax =	1,875 + [(24,006.20-17,917) X .25]
			witholdingTax = employeeTaxTable.getTaxAmount().add((taxableIncome.subtract(employeeTaxTable.getSalaryBase()).multiply(new BigDecimal(employeeTaxTable.getTaxPercentage()))));
			System.out.println("     Witholding Tax: " +  witholdingTax.toPlainString());
			probationaryEmpExt.setWithholdingTax(witholdingTax);
			//netPay = taxable income + non taxable income - witholding tax - other deductions
			netPay = taxableIncome.add(probationaryEmpExt.getNontaxableIncome()).subtract(witholdingTax).subtract(probationaryEmpExt.getOtherDeductions());
			probationaryEmpExt.setNetPay(netPay);
			
		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			sqle.printStackTrace();
		} catch (Exception e)	{
			e.printStackTrace();
		}

		
		//TODO This is wrong
		//gross pay is basicIncome + non-taxable income
		grossPay =  basicIncome.add(probationaryEmpExt.getNontaxableIncome());
		probationaryEmpExt.setGrossPay(grossPay);
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("     Gross Pay: " + probationaryEmpExt.getGrossPay().toPlainString());
		System.out.println("     Net Pay: " + probationaryEmpExt.getNetPay().toPlainString());
		System.out.println("End for empid: " + probationaryEmpExt.getEmpId());
		probationaryEmpExt.setCreateDate(EmployeeUtil.getCurrentSystemDateSqlFormat());
		probationaryEmpExt.setCreatedBy(Integer.toString(loggedEmpId)); 
		
		try {
			int employeePayrollRunId = payrollRunDAO.getEmployeePayrollRunId(probationaryEmpExt.getPayrollPeriodId(), probationaryEmpExt.getEmpId());
			if (employeePayrollRunId > 0) {
				probationaryEmpExt.setEmployeePayrollRunId(employeePayrollRunId);
				payrollRunDAO.update(probationaryEmpExt);
			} else {
				payrollRunDAO.add(probationaryEmpExt);
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
	
	/**
	 * 
	 * @param contractualEmpExt
	 */
	private void generateContractualEmployeePayrollRun(EmployeePayrollRunExt contractualEmpExt, PayrollRunDAO payrollRunDAO, int loggedEmpId) {
		BigDecimal deductions = BigDecimal.ZERO;
		BigDecimal basicIncome = BigDecimal.ZERO;
		BigDecimal taxableIncome = BigDecimal.ZERO;
		BigDecimal grossPay =  BigDecimal.ZERO;
		try {
			/*
			 * as per discussion with team, comment out overtime, since hospital uses offsetting
			 * contractualEmpExt.setOvertime(getOvertimePay(contractualEmpExt.getEmpId(), contractualEmpExt.getFromDate(), contractualEmpExt.getToDate()));
			 * 
			 * comment out holiday
			 * contractualEmpExt.setHolidayPay(getHolidayPay(contractualEmpExt, contractualEmpExt.getFromDate(), contractualEmpExt.getToDate()));	
			 */
			
		
			contractualEmpExt.setNightDiff(getNightDiffPay(contractualEmpExt, contractualEmpExt.getFromDate(), contractualEmpExt.getToDate()));
			contractualEmpExt.setTardiness(getTardiness(contractualEmpExt));
			contractualEmpExt.setAbsences(getAbsences(contractualEmpExt));
			
			//not applicable for contractual: gsis, pag ibig, philhealth
			contractualEmpExt.setOtherTaxableIncome(getOtherTaxableIncome(contractualEmpExt));
			contractualEmpExt.setNontaxableIncome(getNonTaxableIncome(contractualEmpExt));
			contractualEmpExt.setOtherDeductions(getOtherDeductions(contractualEmpExt));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//basicIncome = basicPay + OT + HP + ND 
		basicIncome = basicIncome.add(getBasicPay(contractualEmpExt));
		
		if(contractualEmpExt.getOvertime() != null){
			basicIncome = basicIncome.add(contractualEmpExt.getOvertime());
		}
		
		if(contractualEmpExt.getHolidayPay() != null){
			basicIncome = basicIncome.add(contractualEmpExt.getHolidayPay());
		}
		
		if(contractualEmpExt.getNightDiff() != null){
			basicIncome = basicIncome.add(contractualEmpExt.getNightDiff());
		}
		
		
		
		deductions = getTardinessAndAbsences(contractualEmpExt);


		/**
		 * TODO need to check how to compute for total deductions
		 */
		contractualEmpExt.setTotalDeductions(deductions);
		
		
		//Taxable income = basicIncome - (SSS/PhilHealth/Pag-IBIG deductions + Tardiness + Absences)
		//TODO Error changing code. Other taxble income is not applicable to contractors
		
		taxableIncome = basicIncome.subtract(deductions);
		contractualEmpExt.setTaxableIncome(taxableIncome);
		
		System.out.println("Start for empid: " 					+ contractualEmpExt.getEmpId());
		System.out.println("     Employee is " 					+ contractualEmpExt.getEmployeeTypeName());
		System.out.println("     Payroll Type:   " 				+ contractualEmpExt.getPayrollType());
		System.out.println("      Basic Income (basic pay, overtime,  holiday,  night differential):   " + basicIncome.toPlainString());	
		System.out.println("        Basic Pay:   " 				+ getBasicPay(contractualEmpExt));
		System.out.println("        Holiday:   " 					+ contractualEmpExt.getHolidayPay());
		System.out.println("        Night Diff:   " 				+ contractualEmpExt.getNightDiff());
		System.out.println("        Overtime:   " 					+ contractualEmpExt.getOvertime());
		System.out.println("      Other Taxable Income:   " 		+ contractualEmpExt.getOtherTaxableIncome());
		System.out.println("      Non-Taxable Income:   " 		+ contractualEmpExt.getNontaxableIncome());
		System.out.println("      Deduct Govt Deductions? " 		+ contractualEmpExt.getDeductGovtFlag());
		System.out.println("      Standard Govt Deductions: " 	+ getStandardGovtDeductions(contractualEmpExt));
		System.out.println("     Taxable income: " 				+ taxableIncome.toPlainString());
		System.out.println(" ");
		System.out.println("     Tardiness:   " 				+ contractualEmpExt.getTardiness());
		System.out.println("     Absences:   " 					+ contractualEmpExt.getAbsences());
		System.out.println("     Other Deductions:   " 					+ contractualEmpExt.getOtherDeductions());
		System.out.println("     Deductions (govt, tardiness, absences):     " + deductions.toPlainString());

		contractualEmpExt.setWithholdingTax(BigDecimal.ZERO);
		contractualEmpExt.setNetPay(taxableIncome);
		
		//gross pay is basicIncome + non-taxable income
		grossPay =  basicIncome.add(contractualEmpExt.getNontaxableIncome());
		contractualEmpExt.setGrossPay(grossPay);
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("     Gross Pay: " + contractualEmpExt.getGrossPay().toPlainString());
		System.out.println("     Net Pay: " + contractualEmpExt.getNetPay().toPlainString());
		System.out.println("End for empid: " + contractualEmpExt.getEmpId());
		contractualEmpExt.setCreateDate(EmployeeUtil.getCurrentSystemDateSqlFormat());
		contractualEmpExt.setCreatedBy(Integer.toString(loggedEmpId)); 
		
		try {
			int employeePayrollRunId = payrollRunDAO.getEmployeePayrollRunId(contractualEmpExt.getPayrollPeriodId(), contractualEmpExt.getEmpId());
			if (employeePayrollRunId > 0) {
				contractualEmpExt.setEmployeePayrollRunId(employeePayrollRunId);
				payrollRunDAO.update(contractualEmpExt);
			} else {
				payrollRunDAO.add(contractualEmpExt);
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean lockPayrollByPayrollPeriod (int payrollPeriodId) {
		//should generate first before locking
		return true;
	}
	
	
	
	
	
	
	private BigDecimal getOvertimePay(int empId, Date dateFrom, Date dateTo)  throws SQLException, Exception {
		return null;
	}
	
	//Ian
	private BigDecimal getHolidayPay(EmployeePayrollRunExt empInfo, Date dateFrom, Date dateTo)  throws SQLException, Exception {
		
		HolidayDAO holidayDAO = new HolidayDAO();
		TimeEntryDAO timeEntryDAO = new TimeEntryDAO();
		BigDecimal holidayPay = new BigDecimal(0);
		
		//get dates of holiday put in list
		List<Holiday> holidayList = holidayDAO.getHolidaysByPayPeriodRange(dateFrom, dateTo);
		
		if(!holidayList.isEmpty()){
			Iterator<Holiday> iHoliday = holidayList.iterator();
			
			while(iHoliday.hasNext()){
				Holiday holiday = iHoliday.next();				
				
				if(timeEntryDAO.isEmployeePresentInTheHoliday(empInfo.getEmpId(), holiday.getHolidayDate())){
					if(PayrollConstants.HOLIDAY_REGULAR_INDICATOR.equals(holiday.getHolidayType())){
						holidayPay = holidayPay.add(empInfo.getDailyRate().multiply(new BigDecimal(PayrollConstants.HOLIDAY_LEGAL_RATE)));
					} else if(PayrollConstants.HOLIDAY_SPECIAL_INDICATOR.equals(holiday.getHolidayType())){
						holidayPay = holidayPay.add(empInfo.getDailyRate().multiply(new BigDecimal(PayrollConstants.HOLIDAY_SPECIAL_RATE)));
					}					
				}			
			}
			
			
		}
		
		holidayDAO.closeConnection();
		timeEntryDAO.closeConnection();		
		return holidayPay;
	}
	
	//Ian
	private BigDecimal getNightDiffPay(EmployeePayrollRunExt empInfo, Date dateFrom, Date dateTo)  throws SQLException, Exception {
		List<Holiday> holidayList = null;
		HolidayDAO holidayDAO = new HolidayDAO();
		TimeEntryDAO timeEntryDAO = new TimeEntryDAO();
		BigDecimal nightDiffPay = new BigDecimal(0);
		
		//get dates of holiday put in list
		try {
		holidayList = holidayDAO.getHolidaysByPayPeriodRange(dateFrom, dateTo);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return nightDiffPay;
		}
		
		
		
		if(!holidayList.isEmpty() || null != holidayList){
			Iterator<Holiday> iHoliday = holidayList.iterator();
			
			while(iHoliday.hasNext()){
				Holiday holiday = iHoliday.next();				
				
				if(timeEntryDAO.isEmployeePresentInTheHoliday(empInfo.getEmpId(), holiday.getHolidayDate())){
					if(PayrollConstants.HOLIDAY_REGULAR_INDICATOR.equals(holiday.getHolidayType())){
						nightDiffPay = nightDiffPay.add(empInfo.getDailyRate().multiply(new BigDecimal(PayrollConstants.HOLIDAY_LEGAL_RATE)));
					} else if(PayrollConstants.HOLIDAY_SPECIAL_INDICATOR.equals(holiday.getHolidayType())){
						nightDiffPay = nightDiffPay.add(empInfo.getDailyRate().multiply(new BigDecimal(PayrollConstants.HOLIDAY_SPECIAL_RATE)));
					}					
				}			
			}
			
			
		}
		
		holidayDAO.closeConnection();
		timeEntryDAO.closeConnection();
		return nightDiffPay;
	}
	
	//Roy
	private BigDecimal getTardiness(EmployeePayrollRunExt emp)  throws SQLException, Exception {
		int empId = emp.getEmpId();
		Date dateFrom = emp.getFromDate();
		Date dateTo = emp.getToDate();
		
		TardinessDAO dao = new TardinessDAO();
		int sumOfTardinessInMins = dao.getTotalNumberOfTardiness(empId, dateFrom, dateTo);
		BigDecimal totalTardinessMinsDeduction = emp.getHolidayPay().divide(new BigDecimal(60)).multiply(new BigDecimal(sumOfTardinessInMins));
		
		return totalTardinessMinsDeduction != null ? totalTardinessMinsDeduction : new BigDecimal(0);
		
	}
	
	//Roy
	private BigDecimal getAbsences(EmployeePayrollRunExt emp)  throws SQLException, Exception {
	
		int empId = emp.getEmpId();
		Date dateFrom = emp.getFromDate();
		Date dateTo = emp.getToDate();
		AbsentDAO absentDAO = new AbsentDAO();
		
		int noOfAbsent = absentDAO.getNumberOfAbsent(empId, dateFrom, dateTo);
		BigDecimal totalAbsentDeduction = emp.getDailyRate().multiply(new BigDecimal(noOfAbsent));
		absentDAO.closeConnection();
		return totalAbsentDeduction != null ? totalAbsentDeduction : new BigDecimal(0);
	}
	
	/*** start GSIS ****/
	//assumed basic pay = monthly rate
	private BigDecimal getGSISEmployeeShare(EmployeePayrollRunExt emp){
		BigDecimal gsisEmployeeShare = BigDecimal.ZERO;
		gsisEmployeeShare = emp.getMonthlyRate().multiply(new BigDecimal(0.09));
		return gsisEmployeeShare != null ? gsisEmployeeShare : new BigDecimal(0);
	}
	
	private BigDecimal getGSISEmployerShare(EmployeePayrollRunExt emp){
		BigDecimal gsisEmployerShare = BigDecimal.ZERO;
		gsisEmployerShare = emp.getMonthlyRate().multiply(new BigDecimal(0.12));
		return gsisEmployerShare != null ? gsisEmployerShare : new BigDecimal(0);
	}
	/*** end GSIS ****/
	
	/*** start Pag Ibig ****/
	//defaulted to 2% for Employee, 2% for Employer - note na may option na ngayon na pede iincrease yung employee share
	//assumed basic pay = monthly rate
	private BigDecimal getPagibigShare(EmployeePayrollRunExt emp){
		BigDecimal pagibigShare = BigDecimal.ZERO;
		pagibigShare = emp.getMonthlyRate().multiply(new BigDecimal(0.02));
		return pagibigShare != null ? pagibigShare : new BigDecimal(0);
	}	
	
	/*** end Pag Ibig ****/
	
	/*** start GSIS ****/
	//employee and employer share are the same
	private BigDecimal getPhilHealthShare(EmployeePayrollRunExt emp)  throws SQLException, Exception {
		PhicContributionTableService phicService= new PhicContributionTableService();
		PhicContributionTable phicTable=  phicService.getPhicContributionBySalary(emp.getMonthlyRate());
		
		if(phicTable == null){
			return new BigDecimal(0);
		}
		
		return phicTable.getEmployeeShare() != null ? phicTable.getEmployeeShare() : new BigDecimal(0);		
	}	
	/*** end GSIS ****/
	
	private BigDecimal getOtherTaxableIncome(EmployeePayrollRunExt emp)  throws SQLException, Exception {
		EmployeeIncomeDeductionDAO dao = new EmployeeIncomeDeductionDAO();
//		BigDecimal result = dao.getTaxableIncomeTotal(emp.getEmpId(), emp.getIsForSecondHalf());
		BigDecimal result = dao.getTaxableIncomeTotal(emp.getEmpId());
		dao.closeConnection();
		return result != null ? result : new BigDecimal(0);
	}
	
	
	private BigDecimal getNonTaxableIncome(EmployeePayrollRunExt emp)  throws SQLException, Exception {
		EmployeeIncomeDeductionDAO dao = new EmployeeIncomeDeductionDAO();
		BigDecimal result = dao.getNonTaxableIncomeTotal(emp.getEmpId(), emp.getIsForSecondHalf());
		dao.closeConnection();
		return result != null ? result : new BigDecimal(0);
	}
	
	
	private BigDecimal getOtherDeductions(EmployeePayrollRunExt emp)  throws SQLException, Exception {
		EmployeeIncomeDeductionDAO dao = new EmployeeIncomeDeductionDAO();
		BigDecimal result = dao.getDeductionTotal(emp.getEmpId(), emp.getIsForSecondHalf());
		dao.closeConnection();
		return result != null ? result : new BigDecimal(0);
	}

	@Override
	public void updatePayrollPeriodStatusToLocked(int payrollPeriodId, int loggedEmpId)	throws SQLException, Exception {
		PayrollRunDAO payrollRunDAO = new PayrollRunDAO();
		
		payrollRunDAO.updatePayrollPeriodStatus(payrollPeriodId, "L", loggedEmpId);
		
		payrollRunDAO.closeConnection();
		
	}
	
	
}



