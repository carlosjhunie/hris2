package dai.hris.service.filemanager.loantype;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.filemanager.LoanTypeDAO;
import dai.hris.model.LoanType;


public class LoanTypeService implements ILoanTypeService {

	@Override
	public ArrayList<LoanType> getAll() throws SQLException, Exception {
		
		LoanTypeDAO dao = new LoanTypeDAO();
		ArrayList<LoanType> list = dao.getAll();
		dao.closeConnection();
		return list;
	}

	@Override
	public void add(LoanType loanType) throws SQLException, Exception {
		LoanTypeDAO dao = new LoanTypeDAO();
		dao.add(loanType);
		dao.closeConnection();
		
		

	}

	@Override
	public void delete(LoanType loanType) throws SQLException, Exception {
		LoanTypeDAO dao = new LoanTypeDAO();
		dao.delete(loanType);
		dao.closeConnection();

	}

	@Override
	public void update(LoanType loanType) throws SQLException, Exception {
		LoanTypeDAO dao = new LoanTypeDAO();
		dao.update(loanType);
		dao.closeConnection();

	}

}
