package dai.hris.service.filemanager.loantype;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.model.LoanType;



public interface ILoanTypeService {
	
	public  ArrayList<LoanType> getAll() throws SQLException, Exception;
	public  void add(LoanType LoanType) throws SQLException, Exception;
	public  void delete(LoanType LoanType) throws SQLException, Exception;
	public  void update(LoanType LoanType) throws SQLException, Exception;

}
