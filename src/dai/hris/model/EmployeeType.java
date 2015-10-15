package dai.hris.model;

/*
 * 	[employeeTypeId] [int] IDENTITY(1,1) NOT NULL,
	[employeeType] [varchar](50) NULL,
	[createdBy] [int] NULL,
	[createdDate] [datetime] NULL,
 */


import java.io.Serializable;

public class EmployeeType  implements Serializable {
	private static final long serialVersionUID = 1L; 
	private int employeeTypeId;
	private String employeeTypeName;
	
	
	public int getEmployeeTypeId() {
		return employeeTypeId;
	}
	public void setEmployeeTypeId(int employeeTypeId) {
		this.employeeTypeId = employeeTypeId;
	}
	public String getEmployeeTypeName() {
		return employeeTypeName;
	}
	public void setEmployeeTypeName(String employeeTypeName) {
		this.employeeTypeName = employeeTypeName;
	}

	

}
