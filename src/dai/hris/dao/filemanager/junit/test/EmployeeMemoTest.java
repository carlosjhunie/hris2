package dai.hris.dao.filemanager.junit.test;

//import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;

//import org.junit.Test;







import dai.hris.model.EmployeeMemo;
import dai.hris.service.filemanager.employeememo.EmployeeMemoService;
import dai.hris.service.filemanager.employeememo.IEmployeeMemoService;


public class EmployeeMemoTest {

	
	//@Test
	public void testEmployeeMemo() throws SQLException, Exception {

		IEmployeeMemoService employeeMemoService = new EmployeeMemoService();
		EmployeeMemo empMemo = new EmployeeMemo();
		
		//assertEquals(3, employeeMemoService.getEmployeeMemoByEmployeeMemoId(1).getCcRecipientEmpId());  //ok TG
		//assertEquals(3, employeeMemoService.getEmployeeMemoListByCreatedByEmpId(1).get(0).getCcRecipientEmpId());	//ok TG
		//assertEquals(1, employeeMemoService.getEmployeeMemoListByToRecipientEmpId(2).get(0).getCreatedBy());	//ok TG
		
		
		//empMemo.setCcRecipientEmpId(5);
		empMemo.setCreatedBy(1);
		empMemo.setEmployeeMemoId(2);
		empMemo.setFromSender("HRRDDDD");
		//empMemo.setMemofiledDate(new Date(10,10,10));
		empMemo.setMessage("yehey");
		empMemo.setRemarks("remarkssss");
		empMemo.setSubject("passing of NBA PBA law");
		empMemo.setToRecipientEmpId(3);
		//System.out.println(employeeMemoService.add(empMemo));  ok TG
		//System.out.println(employeeMemoService.update(empMemo));  ok TG
	}
	
	
	
	

}
