package dai.hris.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModuleAccess implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7581776027269604512L;

	private int moduleAccessId;
	private Date createdAt;
	private Date updatedAt;
	private int empId;
	private String fileManagement;
	private String employee;
	private String timeManagement;
	private String payroll;
	private String employeesLoan;
	private String payrollReports;
	private List<String> fileManagementList;
	private List<String> employeeList;
	private List<String> timeManagementList;
	private List<String> payrollList;
	private List<String> employeesLoanList;
	private List<String> payrollReportsList;
	public int getModuleAccessId() {
		return moduleAccessId;
	}
	public void setModuleAccessId(int moduleAccessId) {
		this.moduleAccessId = moduleAccessId;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getFileManagement() {
		if (fileManagement == null && fileManagementList != null) {
			StringBuilder sb = new StringBuilder();
			for (String s : fileManagementList) {
				sb.append(s);
				sb.append(",");
			}
			fileManagement = sb.toString();
		}
		return fileManagement;
	}
	public void setFileManagement(String fileManagement) {
		this.fileManagement = fileManagement;
		if ((fileManagementList == null || fileManagementList.isEmpty()) && this.fileManagement != null) {
			String[] strArray = this.fileManagement.split(",");
			fileManagementList = new ArrayList<String>(Arrays.asList(strArray));
		}
	}
	public String getEmployee() {
		if (employee == null && employeeList != null) {
			StringBuilder sb = new StringBuilder();
			for (String s : employeeList) {
				sb.append(s);
				sb.append(",");
			}
			employee = sb.toString();
		}
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
		if ((employeeList == null || employeeList.isEmpty()) && this.employee != null) {
			String[] strArray = this.employee.split(",");
			employeeList = new ArrayList<String>(Arrays.asList(strArray));
		}
	}
	public String getTimeManagement() {
		if (timeManagement == null && timeManagementList != null) {
			StringBuilder sb = new StringBuilder();
			for (String s : timeManagementList) {
				sb.append(s);
				sb.append(",");
			}
			timeManagement = sb.toString();
		}
		return timeManagement;
	}
	public void setTimeManagement(String timeManagement) {
		this.timeManagement = timeManagement;
		if ((timeManagementList == null || timeManagementList.isEmpty()) && this.timeManagement != null) {
			String[] strArray = this.timeManagement.split(",");
			timeManagementList = new ArrayList<String>(Arrays.asList(strArray));
		}
	}
	public String getPayroll() {
		if (payroll == null && payrollList != null) {
			StringBuilder sb = new StringBuilder();
			for (String s : payrollList) {
				sb.append(s);
				sb.append(",");
			}
			payroll = sb.toString();
		}
		return payroll;
	}
	public void setPayroll(String payroll) {
		this.payroll = payroll;
		if ((payrollList == null || payrollList.isEmpty()) && this.payroll != null) {
			String[] strArray = this.payroll.split(",");
			payrollList = new ArrayList<String>(Arrays.asList(strArray));
		}
	}
	public String getEmployeesLoan() {
		if (employeesLoan == null && employeesLoanList != null) {
			StringBuilder sb = new StringBuilder();
			for (String s : employeesLoanList) {
				sb.append(s);
				sb.append(",");
			}
			employeesLoan = sb.toString();
		}
		return employeesLoan;
	}
	public void setEmployeesLoan(String employeesLoan) {
		this.employeesLoan = employeesLoan;
		if ((employeesLoanList == null || employeesLoanList.isEmpty()) && this.employeesLoan != null) {
			String[] strArray = this.employeesLoan.split(",");
			employeesLoanList = new ArrayList<String>(Arrays.asList(strArray));
		}
	}
	public String getPayrollReports() {
		if (payrollReports == null && payrollReportsList != null) {
			StringBuilder sb = new StringBuilder();
			for (String s : payrollReportsList) {
				sb.append(s);
				sb.append(",");
			}
			payrollReports = sb.toString();
		}
		return payrollReports;
	}
	public void setPayrollReports(String payrollReports) {
		this.payrollReports = payrollReports;
		if ((payrollReportsList == null || payrollReportsList.isEmpty()) && this.payrollReports != null) {
			String[] strArray = this.payrollReports.split(",");
			payrollReportsList = new ArrayList<String>(Arrays.asList(strArray));
		}
	}
	public List<String> getFileManagementList() {
		if ((fileManagementList == null || fileManagementList.isEmpty()) && fileManagement != null) {
			String[] strArray = fileManagement.split(",");
			fileManagementList = new ArrayList<String>(Arrays.asList(strArray));
		}
		return fileManagementList;
	}
	public void setFileManagementList(List<String> fileManagementList) {
		this.fileManagementList = fileManagementList;
	}
	public List<String> getEmployeeList() {
		if ((employeeList == null || employeeList.isEmpty()) && employee != null) {
			String[] strArray = employee.split(",");
			employeeList = new ArrayList<String>(Arrays.asList(strArray));
		}
		return employeeList;
	}
	public void setEmployeeList(List<String> employeeList) {
		this.employeeList = employeeList;
	}
	public List<String> getTimeManagementList() {
		if ((timeManagementList == null || timeManagementList.isEmpty()) && timeManagement != null) {
			String[] strArray = timeManagement.split(",");
			timeManagementList = new ArrayList<String>(Arrays.asList(strArray));
		}
		return timeManagementList;
	}
	public void setTimeManagementList(List<String> timeManagementList) {
		this.timeManagementList = timeManagementList;
	}
	public List<String> getPayrollList() {
		if ((payrollList == null || payrollList.isEmpty()) && payroll != null) {
			String[] strArray = payroll.split(",");
			payrollList = new ArrayList<String>(Arrays.asList(strArray));
		}
		return payrollList;
	}
	public void setPayrollList(List<String> payrollList) {
		this.payrollList = payrollList;
	}
	public List<String> getEmployeesLoanList() {
		if ((employeesLoanList == null || employeesLoanList.isEmpty()) && employeesLoan != null) {
			String[] strArray = employeesLoan.split(",");
			employeesLoanList = new ArrayList<String>(Arrays.asList(strArray));
		}
		return employeesLoanList;
	}
	public void setEmployeesLoanList(List<String> employeesLoanList) {
		this.employeesLoanList = employeesLoanList;
	}
	public List<String> getPayrollReportsList() {
		if ((payrollReportsList == null || payrollReportsList.isEmpty()) && payrollReports != null) {
			String[] strArray = payrollReports.split(",");
			payrollReportsList = new ArrayList<String>(Arrays.asList(strArray));
		}
		return payrollReportsList;
	}
	public void setPayrollReportsList(List<String> payrollReportsList) {
		this.payrollReportsList = payrollReportsList;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + empId;
		result = prime * result
				+ ((employee == null) ? 0 : employee.hashCode());
		result = prime * result
				+ ((employeeList == null) ? 0 : employeeList.hashCode());
		result = prime * result
				+ ((employeesLoan == null) ? 0 : employeesLoan.hashCode());
		result = prime
				* result
				+ ((employeesLoanList == null) ? 0 : employeesLoanList
						.hashCode());
		result = prime * result
				+ ((fileManagement == null) ? 0 : fileManagement.hashCode());
		result = prime
				* result
				+ ((fileManagementList == null) ? 0 : fileManagementList
						.hashCode());
		result = prime * result + moduleAccessId;
		result = prime * result + ((payroll == null) ? 0 : payroll.hashCode());
		result = prime * result
				+ ((payrollList == null) ? 0 : payrollList.hashCode());
		result = prime * result
				+ ((payrollReports == null) ? 0 : payrollReports.hashCode());
		result = prime
				* result
				+ ((payrollReportsList == null) ? 0 : payrollReportsList
						.hashCode());
		result = prime * result
				+ ((timeManagement == null) ? 0 : timeManagement.hashCode());
		result = prime
				* result
				+ ((timeManagementList == null) ? 0 : timeManagementList
						.hashCode());
		result = prime * result
				+ ((updatedAt == null) ? 0 : updatedAt.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModuleAccess other = (ModuleAccess) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (empId != other.empId)
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (employeeList == null) {
			if (other.employeeList != null)
				return false;
		} else if (!employeeList.equals(other.employeeList))
			return false;
		if (employeesLoan == null) {
			if (other.employeesLoan != null)
				return false;
		} else if (!employeesLoan.equals(other.employeesLoan))
			return false;
		if (employeesLoanList == null) {
			if (other.employeesLoanList != null)
				return false;
		} else if (!employeesLoanList.equals(other.employeesLoanList))
			return false;
		if (fileManagement == null) {
			if (other.fileManagement != null)
				return false;
		} else if (!fileManagement.equals(other.fileManagement))
			return false;
		if (fileManagementList == null) {
			if (other.fileManagementList != null)
				return false;
		} else if (!fileManagementList.equals(other.fileManagementList))
			return false;
		if (moduleAccessId != other.moduleAccessId)
			return false;
		if (payroll == null) {
			if (other.payroll != null)
				return false;
		} else if (!payroll.equals(other.payroll))
			return false;
		if (payrollList == null) {
			if (other.payrollList != null)
				return false;
		} else if (!payrollList.equals(other.payrollList))
			return false;
		if (payrollReports == null) {
			if (other.payrollReports != null)
				return false;
		} else if (!payrollReports.equals(other.payrollReports))
			return false;
		if (payrollReportsList == null) {
			if (other.payrollReportsList != null)
				return false;
		} else if (!payrollReportsList.equals(other.payrollReportsList))
			return false;
		if (timeManagement == null) {
			if (other.timeManagement != null)
				return false;
		} else if (!timeManagement.equals(other.timeManagement))
			return false;
		if (timeManagementList == null) {
			if (other.timeManagementList != null)
				return false;
		} else if (!timeManagementList.equals(other.timeManagementList))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ModuleAccess [moduleAccessId=" + moduleAccessId
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", empId=" + empId + ", fileManagement=" + fileManagement
				+ ", employee=" + employee + ", timeManagement="
				+ timeManagement + ", payroll=" + payroll + ", employeesLoan="
				+ employeesLoan + ", payrollReports=" + payrollReports
				+ ", fileManagementList=" + fileManagementList
				+ ", employeeList=" + employeeList + ", timeManagementList="
				+ timeManagementList + ", payrollList=" + payrollList
				+ ", employeesLoanList=" + employeesLoanList
				+ ", payrollReportsList=" + payrollReportsList + "]";
	}
	
}
