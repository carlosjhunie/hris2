package dai.hris.model;

public class EmployeeDeduction {

	// link employee to deduction table
	private int empDeductionId;
	private int empId;
	private int deductionId;
	private String remarks;
	private int active; //0 inactive, 1 active
	
	//private String deductionName;

	public int getEmpDeductionId() {
		return empDeductionId;
	}

	public void setEmpDeductionId(int empDeductionId) {
		this.empDeductionId = empDeductionId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getDeductionId() {
		return deductionId;
	}

	public void setDeductionId(int deductionId) {
		this.deductionId = deductionId;
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
