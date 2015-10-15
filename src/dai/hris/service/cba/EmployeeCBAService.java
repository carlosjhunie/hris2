package dai.hris.service.cba;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.cba.CBADAO;
import dai.hris.model.EmployeeCBA;

public class EmployeeCBAService implements IEmployeeCBAService {

	public EmployeeCBA getEmployeeCbaByCbaId(int cbaId) throws SQLException,
			Exception {
		
		CBADAO dao = new CBADAO();
		EmployeeCBA empCBA = dao.getEmployeeCbaByCbaId(cbaId);
		dao.closeConnection();
		return empCBA;
	}

	public ArrayList<EmployeeCBA> getEmployeeCBAByEmpId(int empId)
			throws SQLException, Exception {
		CBADAO dao = new CBADAO();
		ArrayList<EmployeeCBA> empCBAList = dao.getEmployeeCBAByEmpId(empId);
		dao.closeConnection();
		return empCBAList;
	}

	public int add(EmployeeCBA empCBA) throws SQLException, Exception {
		CBADAO dao = new CBADAO();
		int ctr = dao.add(empCBA);
		dao.closeConnection();
		return ctr;

	}

	public int update(EmployeeCBA empCBA) throws SQLException, Exception {
		CBADAO dao = new CBADAO();
		int ctr = dao.update(empCBA);
		dao.closeConnection();
		return ctr;
	}

}
