package dai.hris.model;

public class EmployeeIncome {

	// link employee to income table
	private int empIncomeId;
	private int empId;
	private int incomeId;
	private int active; //0 inactive, 1 active
	private String remarks;

	public int getEmpIncomeId() {
		return empIncomeId;
	}

	public void setEmpIncomeId(int empIncomeId) {
		this.empIncomeId = empIncomeId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
	}


	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

}
