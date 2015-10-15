package dai.hris.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Payslip implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5027585243743799098L;
	
	private int payrollPeriodId;
	private int empId;

	private int payslipId;
	private BigDecimal basicSalary;
	private BigDecimal dailyRate;
	private BigDecimal hourlyRate;
	
	private TaxTable taxTable;
	private List<Income> allowanceNonTaxableList;
	private List<Income> allowanceTaxableList;
	private List<Deduction> deductionList;
	private List<LoanEntry> loansList;
	
	private BigDecimal grossTaxableIncome;
	private BigDecimal netIncome;
	

	
	
}
