package dai.hris.service.loan;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.model.City;
import dai.hris.model.LoanEntry;
import dai.hris.model.LoanPayments;

public interface ILoanPaymentsService {
	public ArrayList<LoanPayments> getAllLoanPaymentsByLoanEntryId(int loanEntryId) throws SQLException, Exception;
	public  void add(LoanPayments loanPayments) throws SQLException, Exception;
	public  void update(LoanPayments loanPayments) throws SQLException, Exception;
}
