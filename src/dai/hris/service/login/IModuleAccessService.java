package dai.hris.service.login;

import java.sql.SQLException;

import dai.hris.model.ModuleAccess;

public interface IModuleAccessService {

	public void saveOrUpdate(ModuleAccess ma) throws SQLException;
	public ModuleAccess getModuleAccessByEmpId(int empId) throws SQLException;
}
