package dai.hris.model;

/**
 * @author daniel padilla
	From dbo.educationalBkgrnd table
	
	[educBkgrndId] [int] IDENTITY(1,1) NOT NULL,
	[empId] [int] NOT NULL,
	[school] [varchar](50) NULL,
	[course] [varchar](50) NULL,
	[yearGraduated] [varchar](50) NULL,
	[remarks] [varchar](50) NULL
		
*/


public class EducationalBackground {
	private int educBkgrndId;
	private int empId;
	private String school;
	private String course;	
	private String yearAttended;
	private String yearGraduated;
	private String remarks;
	
	
	public String getYearAttended() {
		return yearAttended;
	}
	public void setYearAttended(String yearAttended) {
		this.yearAttended = yearAttended;
	}
	public int getEducBkgrndId() {
		return educBkgrndId;
	}
	public void setEducBkgrndId(int educBkgrndId) {
		this.educBkgrndId = educBkgrndId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}	
	public String getYearGraduated() {
		return yearGraduated;
	}
	public void setYearGraduated(String yearGraduated) {
		this.yearGraduated = yearGraduated;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
