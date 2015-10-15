package dai.hris.service.login;

import java.sql.SQLException;

import dai.hris.dao.login.ModuleAccessDAO;
import dai.hris.model.ModuleAccess;

public class ModuleAccessService implements IModuleAccessService {

	@Override
	public void saveOrUpdate(ModuleAccess ma) throws SQLException {
		ModuleAccessDAO dao = new ModuleAccessDAO();
		dao.saveOrUpdate(ma);
		dao.closeConnection();
	}

	@Override
	public ModuleAccess getModuleAccessByEmpId(int empId) throws SQLException {
		ModuleAccessDAO dao = new ModuleAccessDAO();
		ModuleAccess ma = dao.getModuleAccessByEmpId(empId);
		dao.closeConnection();
		return ma;
	}

}
