package dai.hris.service.filemanager.employeepayrollinfo;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.model.Deduction;
import dai.hris.model.EmployeeDeduction;
import dai.hris.model.EmployeeIncome;
import dai.hris.model.EmployeePayrollInfo;
import dai.hris.model.Income;

public interface IIncomeDeductionService {
	public ArrayList<Deduction> getAllDeduction(int empId) throws SQLException, Exception;	
	public ArrayList<Income> getAllIncome(int empId) throws SQLException, Exception;	
}
