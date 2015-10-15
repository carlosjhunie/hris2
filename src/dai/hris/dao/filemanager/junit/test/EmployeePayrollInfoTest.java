package dai.hris.dao.filemanager.junit.test;

//import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.SQLException;

//import org.junit.Test;



import dai.hris.model.EmployeePayrollInfo;
import dai.hris.service.filemanager.employeepayrollinfo.EmployeePayrollInfoService;
import dai.hris.service.filemanager.employeepayrollinfo.IEmployeePayrollInfoService;

public class EmployeePayrollInfoTest {

	
	/*
	 * Passed - 062315
	 */
//	@Test
	public void testEmployeePayrollInfo() throws SQLException, Exception {

		IEmployeePayrollInfoService iEPIS = new EmployeePayrollInfoService();
		EmployeePayrollInfo ePI = new EmployeePayrollInfo();
		
		//assertEquals("BAN", iEPIS.getEmployeePayrollInfoByEmpPayrollInfoId(1).getBan());  //ok TG
		//assertEquals("BAN", iEPIS.getEmployeePayrollInfoListByEmpId(1).get(0).getBan());	//ok TG
		
		
		ePI.setBan("BAN");
		ePI.setCola(new BigDecimal("300.90"));
		ePI.setDailyRate(new BigDecimal("300.90"));
		ePI.setEmpId(2);
		ePI.setEmpPayrollInfoId(1);
		ePI.setFoodAllowance(new BigDecimal("300.90"));
		ePI.setHourlyRate(new BigDecimal("300.90"));
		ePI.setMonthlyRate(new BigDecimal("300.90"));
		
		ePI.setPayrollType("CONTRACTUAL");
		
		ePI.setTaxShield(new BigDecimal("300.90"));
		ePI.setTransAllowance(new BigDecimal("300.90"));
		
		
		//assertEquals(true, iEPIS.add(ePI));  //ok TG
//		assertEquals(true, iEPIS.update(ePI));  //ok TG

	   // assertEquals("PHoenix", eOOODAO.getEmployeeOutOfOfficeByEmpOOOId(1).getProvider());  //ok TG

	}
	
	
	
	

}
