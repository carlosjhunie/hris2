package dai.hris.model;

import java.io.Serializable;


public class EmployeeFamilyMember implements Serializable{

	public EmployeeFamilyMember() {
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L; 
	
	
	private int empFamilyMemberId;
	private int empId;
	private String name;
	private String gender;
	private String relation;
	private String birthdate;
	private int age;
	private String remarks;
	private String contactNum;
	
	
	public int getEmpFamilyMemberId() {
		return empFamilyMemberId;
	}
	public void setEmpFamilyMemberId(int empFamilyMemberId) {
		this.empFamilyMemberId = empFamilyMemberId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getContactNum() {
		return contactNum;
	}
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	
	
}
