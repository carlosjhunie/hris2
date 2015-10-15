package dai.hris.service.loan;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.loan.LoanEntryDAO;
import dai.hris.model.LoanEntry;

public class LoanEntryService implements ILoanEntryService {

	public LoanEntryService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<LoanEntry> getAllLoanEntryByEmpId(int empId)
			throws SQLException, Exception {
		LoanEntryDAO dao = new LoanEntryDAO();
		ArrayList<LoanEntry> list = dao.getAllLoanEntryByEmpId(empId);
		dao.closeConnection();
		return list;
	}
	
	public void add(LoanEntry loanEntry) throws SQLException, Exception{
		LoanEntryDAO dao = new LoanEntryDAO();
		dao.add(loanEntry);
		dao.closeConnection();
	}
	
	public void update(LoanEntry loanEntry) throws SQLException, Exception{
		LoanEntryDAO dao = new LoanEntryDAO();
		dao.update(loanEntry);
		dao.closeConnection();
	}
}
