package dai.hris.dao.filemanager.junit.test;

//import static org.junit.Assert.*;

import java.sql.SQLException;

//import org.junit.Test;

import dai.hris.dao.filemanager.EmployeeFamilyMemberDAO;
import dai.hris.model.EmployeeFamilyMember;

public class EmployeeFamilyMemberDAOTest {

	
	/*
	 * Passed - 062315
	 */
	//@Test
	public void testGetEmpFamilyMember() throws SQLException, Exception {

		
		//working 062315 TG 
		/*
		EmpFamilyMembersDAO empFMDAO = new EmpFamilyMembersDAO();
		ArrayList<EmpFamilyMembers> empFMList = empFMDAO.getEmployeeFamilyMembersByEmpId(1);
		assertEquals("Uncle", empFMList.get(0).getRelation());
		*/
		
		/* working 062315 TG 
		EmpFamilyMembersDAO empFMDAO = new EmpFamilyMembersDAO();
		EmpFamilyMembers empFM = empFMDAO.getEmployeeFamilyMembersByempFamilyMemberId(1);
		assertEquals("Uncle", empFM.getRelation());
		*/
		
		/* add, update working 062315 TG 
		 * 
		EmployeeFamilyMemberDAO empFMDAO = new EmployeeFamilyMemberDAO();
		EmployeeFamilyMember empFM = new EmployeeFamilyMember();
		empFM.setEmpFamilyMemberId(2);
		empFM.setAge(20);
		empFM.setBirthdate("10/10/10");
		empFM.setContactNum("0299999999");
		empFM.setEmpId(2);
		empFM.setGender("M");
		empFM.setName("Robin Padilla");
		empFM.setRelation("Uncle");
		empFM.setRemarks("Uncle Robin");
		assertEquals(1, empFMDAO.update(empFM));
		*/
		
		
	}
	

	
	
	
	

}
