package dai.hris.service.loan;

import java.sql.SQLException;
import java.util.ArrayList;


import dai.hris.dao.loan.LoanPaymentsDAO;
import dai.hris.model.LoanPayments;

public class LoanPaymentsService implements ILoanPaymentsService {

	public LoanPaymentsService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<LoanPayments> getAllLoanPaymentsByLoanEntryId(int loanEntryId)
			throws SQLException, Exception {
		LoanPaymentsDAO dao = new LoanPaymentsDAO();
		ArrayList<LoanPayments> list = dao.getAllLoanPaymentsByLoanEntryId(loanEntryId);
		dao.closeConnection();
		return list;
	}
	
	public void add(LoanPayments loanPayments) throws SQLException, Exception{
		LoanPaymentsDAO dao = new LoanPaymentsDAO();
		dao.add(loanPayments);
		dao.closeConnection();
	}
	
	public void update(LoanPayments loanPayments) throws SQLException, Exception{
		LoanPaymentsDAO dao = new LoanPaymentsDAO();
		dao.update(loanPayments);
		dao.closeConnection();
	}
}
