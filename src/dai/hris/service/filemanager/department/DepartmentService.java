package dai.hris.service.filemanager.department;

import java.sql.SQLException;
import java.util.ArrayList;


import dai.hris.dao.filemanager.DepartmentDAO;
import dai.hris.model.Department;


public class DepartmentService implements IDepartmentService {

	@Override
	public ArrayList<Department> getAll() throws SQLException, Exception {
		
		DepartmentDAO dao = new DepartmentDAO();
		ArrayList<Department> list = dao.getAll();
		dao.closeConnection();
		return list;
	}

	@Override
	public void add(Department department) throws SQLException, Exception {
		DepartmentDAO dao = new DepartmentDAO();
		dao.add(department);
		dao.closeConnection();		

	}

	@Override
	public void delete(Department Department) throws SQLException, Exception {
		DepartmentDAO dao = new DepartmentDAO();
		dao.delete(Department);
		dao.closeConnection();

	}

	@Override
	public void update(Department department) throws SQLException, Exception {
		DepartmentDAO dao = new DepartmentDAO();
		dao.update(department);
		dao.closeConnection();

	}

}
