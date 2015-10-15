package dai.hris.service.login;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import dai.hris.action.filemanager.util.EmployeeUtil;
import dai.hris.dao.login.LoginDAO;
import dai.hris.model.Employee;

public class LoginService implements ILoginService {
	static Logger logger = Logger.getLogger(LoginService.class.getClass());
	
	public LoginService(){
		
	}		
	
	public boolean checkCredentials(String username, String password) throws SQLException, Exception {		
		LoginDAO loginDAO = new LoginDAO();		
		
		password = EmployeeUtil.encodePassword(password);
		boolean isFound = loginDAO.checkLoginCredentials(username, password);
		loginDAO.closeConnection();		
		return isFound;
	}
	
	public Employee getEmployee(String username) throws SQLException, Exception {		
		LoginDAO loginDAO = new LoginDAO();
		Employee emp = loginDAO.getEmployee(username);	
		loginDAO.closeConnection();
		return emp;		
	}
	
	public void updateLoginActivity(int userId, int departmentId, String transType) throws SQLException, Exception {		
		LoginDAO loginDAO = new LoginDAO();
		loginDAO.updateLoginActivity(userId, departmentId, transType);	
		loginDAO.closeConnection();				
	}
}
