package dai.hris.service.loan;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.model.City;
import dai.hris.model.LoanEntry;

public interface ILoanEntryService {
	public ArrayList<LoanEntry> getAllLoanEntryByEmpId(int empId) throws SQLException, Exception;
	public  void add(LoanEntry loanEntry) throws SQLException, Exception;
	public  void update(LoanEntry loanEntry) throws SQLException, Exception;
}
