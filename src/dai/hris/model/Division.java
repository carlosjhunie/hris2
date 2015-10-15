package dai.hris.model;

import java.io.Serializable;

public class Division  implements Serializable {
	private static final long serialVersionUID = 1L; 
	private int divisionId;
	private String divisionName;
	
	
	public int getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(int divisionId) {
		this.divisionId = divisionId;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	

}
