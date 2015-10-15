package dai.hris.dao.filemanager.junit.test;

//import static org.junit.Assert.*;


import java.sql.Date;
import java.sql.SQLException;

//import org.junit.Test;

import dai.hris.dao.filemanager.EmployeeDAO;
import dai.hris.model.Employee;

public class EmployeeDAOTest {

	
	/*
	 * Passed - 060715
	 */
	//@Test
	public void testGetEmployee() throws SQLException, Exception {
		
		
		//working test - TG
		
		/* getAll tested 062315
		EmployeeDAO edao = new EmployeeDAO();		
		assertEquals(1, edao.getAll().get(0).getEmpId());
		edao.closeConnection();
		*/
		
		
		/*
		 * getByEmpId, add, update tested 062315
		 */
		
		
		Employee emp = new Employee();
		EmployeeDAO edao = new EmployeeDAO();
		
		emp.setEmpNo("EMP-003");
		emp.setLastname("PADILllLo");
		emp.setFirstname("KATHRYN");
		emp.setMiddlename("BRYAN");
		//emp.setDateOfBirth(new Date(10,10,10));
		emp.setGender("M");
		emp.setCivilStatus("SINGLE");
		emp.setNationality("FILIPINO");
		emp.setStreet("SAN JOSE");
		emp.setCityId(1);
		emp.setEmail("Kathnielpp@noemail.com");
		emp.setMobileNo("0999999999");
		emp.setTelNo("029999999");
		emp.setBirthPlace("MANILA");
		emp.setProvinceId(1);
		emp.setZipCode("12121");
		emp.setJobTitleId(1);
		emp.setDepartmentId(1);
		emp.setDivisionId(1);
		emp.setEmployeeTypeId(1);
		emp.setEmpStatus("SINGLE");
		//emp.setEmploymentDate(new Date(10,10,10));
		emp.setEndOfContract(null);
		emp.setSss("2121`");
		emp.setGsis("22");
		emp.setHdmf("31313");
		emp.setTin("");
		emp.setPhic("31312312");
		emp.setTaxstatus("SINGLE");
		emp.setPicLoc("");
		emp.setSuperVisor1Id(1);
		emp.setSuperVisor2Id(2);
		emp.setSuperVisor3Id(2);
		emp.setUsername("kathy");
		emp.setPassword("spring123");
		emp.setCreatedBy("kathp");
		emp.setCreationDate(new Date(10,10,10));
		emp.setEmpId(10);
		
		System.out.println(edao.update(emp));
		//assertEquals("EMP-002", edao.getEmployee(1).getEmpNo());
		
		
		
	}
	

	
	
	
	

}
