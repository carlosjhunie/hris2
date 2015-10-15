package dai.hris.model;

import java.io.Serializable;

public class LoanType  implements Serializable {
	private static final long serialVersionUID = 1L; 
	private int loanTypeId;
	private String loanTypeName;
	
	
	public int getLoanTypeId() {
		return loanTypeId;
	}
	public void setLoanTypeId(int loanTypeId) {
		this.loanTypeId = loanTypeId;
	}
	public String getLoanTypeName() {
		return loanTypeName;
	}
	public void setLoanTypeName(String loanTypeName) {
		this.loanTypeName = loanTypeName;
	}

	

}
