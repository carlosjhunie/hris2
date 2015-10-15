package dai.hris.service.login;

import java.sql.SQLException;
import dai.hris.model.Employee;

public interface ILoginService {
	public Employee getEmployee(String username) throws SQLException, Exception;
	public boolean checkCredentials(String username, String password) throws SQLException, Exception;	
	public void updateLoginActivity(int userId, int departmentId, String transType) throws SQLException, Exception;
}
