package dai.hris.dao.filemanager.junit.test;

//import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

//import org.junit.Test;

import dai.hris.dao.filemanager.EmployeeWorkHistoryDAO;
import dai.hris.model.EmployeeWorkHistory;

public class EmployeeWorkHistoryDAOTest {

	
	/*
	 * Passed - 062315
	 */
//	@Test
	public void testGetEmpFamilyMember() throws SQLException, Exception {

		
		//working 062315 TG 
		/*
		EmployeeWorkHistoryDAO eWHDAO = new EmployeeWorkHistoryDAO();
		ArrayList<EmployeeWorkHistory> eWHList = eWHDAO.getEmployeeWorkHistoryByEmpId(1);
		assertEquals(20, eWHList.get(0).getYrsService());
		*/
		
		//working 062315 TG 
		/*
		EmployeeWorkHistoryDAO eWHDAO = new EmployeeWorkHistoryDAO();
		EmployeeWorkHistory eWH = eWHDAO.getEmployeeWorkHistoryByEmpWorkHistoryId(1);
		assertEquals(20, eWH.getYrsService());
		*/
		
		/* add, update working 062315 TG 
		 */
		/*
		EmployeeWorkHistoryDAO eWHDAO = new EmployeeWorkHistoryDAO();
		EmployeeWorkHistory eWH = new EmployeeWorkHistory();
		eWH.setAddress("Dyan lang pow");
		eWH.setCountryId(1);
		eWH.setEmpId(3);
		eWH.setEmployerName("Acc");
		eWH.setEmpWorkHistoryId(1);
		eWH.setIndustry("mining");
		eWH.setPosition("exec");
		eWH.setRemarks("ganda dito");
		eWH.setYrsService(1);
		//assertEquals(1, eWHDAO.add(eWH));
		assertEquals(1, eWHDAO.update(eWH));
		*/
		
		
		
	}
	

	
	
	
	

}
