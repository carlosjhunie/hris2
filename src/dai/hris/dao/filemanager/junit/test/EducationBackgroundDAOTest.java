package dai.hris.dao.filemanager.junit.test;

//import static org.junit.Assert.*;

import java.sql.SQLException;

//import org.junit.Test;

import dai.hris.dao.filemanager.EducationalBackgroundDAO;
import dai.hris.model.EducationalBackground;

public class EducationBackgroundDAOTest {

	
	/*
	 * Passed - 060715
	 */
	//@Test
	public void testGetEducationBackground() throws SQLException, Exception {
		//fail("Not yet implemented");
		EducationalBackgroundDAO eBackDAO = new EducationalBackgroundDAO();
		//assertEquals("UST", empBackDAO.getAll().get(0).getSchool());
		//assertEquals("UST", eBackDAO.getEmployeeEducationalBackgroundByEducBkgrndId(1).getSchool());  //ok TG
		//assertEquals("UST", eBackDAO.getEmployeeEducationalBackgroundByEmpId(1).get(0).getSchool());  //ok TG
		
		
		//update test
		EducationalBackground eBack = new EducationalBackground();
		eBack.setCourse("ChE");
		eBack.setEducBkgrndId(4);
		eBack.setEmpId(3);
		eBack.setRemarks("Galing ni Dennis Padilla");
		eBack.setSchool("Chico Escolar Univ");
		eBack.setYearAttended("1007");
		eBack.setYearGraduated("2015");
		//eBackDAO.update(eBack);
		//assertEquals(1, eBackDAO.update(eBack));
		
		//System.out.println(empBackDAO.getEmployee(1).getDateOfBirth());
		eBackDAO.closeConnection();
	}
	
	
	
	

}
