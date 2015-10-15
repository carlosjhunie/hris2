package dai.hris.service.filemanager.employeetype;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.filemanager.EmployeeTypeDAO;
import dai.hris.model.EmployeeType;


public class EmployeeTypeService implements IEmployeeTypeService {

	@Override
	public ArrayList<EmployeeType> getAll() throws SQLException, Exception {
		
		EmployeeTypeDAO dao = new EmployeeTypeDAO();
		ArrayList<EmployeeType> list = dao.getAll();
		dao.closeConnection();
		return list;
	}

	@Override
	public void add(EmployeeType employeeType) throws SQLException, Exception {
		EmployeeTypeDAO dao = new EmployeeTypeDAO();
		dao.add(employeeType);
		dao.closeConnection();
	}

	/*
	@Override
	public void delete(EmployeeType employeeType) throws SQLException, Exception {
		EmployeeTypeDAO dao = new EmployeeTypeDAO();
		dao.delete(employeeType);
		dao.closeConnection();

	}
	*/

	@Override
	public void update(EmployeeType employeeType) throws SQLException, Exception {
		EmployeeTypeDAO dao = new EmployeeTypeDAO();
		dao.update(employeeType);
		dao.closeConnection();

	}

}
