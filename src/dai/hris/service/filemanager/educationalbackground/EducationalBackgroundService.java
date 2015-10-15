package dai.hris.service.filemanager.educationalbackground;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.filemanager.EducationalBackgroundDAO;
import dai.hris.model.EducationalBackground;

/**
 * 
 * @author danielpadilla
 *
 */
public class EducationalBackgroundService implements IEducationalBackgroundService {

	public EducationalBackground getEmployeeEducationalBackgroundByEducBkgrndId(int educBkgrndId)
			throws SQLException, Exception {
		EducationalBackgroundDAO eBackDAO = new EducationalBackgroundDAO();
		EducationalBackground employeeEducBackground = eBackDAO.getEmployeeEducationalBackgroundByEducBkgrndId(educBkgrndId);
		eBackDAO.closeConnection();
		return employeeEducBackground;
	}

	
	public ArrayList<EducationalBackground> getEmployeeEducationalBackgroundListByEmpId(int empId) throws SQLException, Exception {
		EducationalBackgroundDAO eBackDAO = new EducationalBackgroundDAO();
		ArrayList<EducationalBackground> employeeEducBackgroundList = eBackDAO.getEmployeeEducationalBackgroundListByEmpId(empId);
		eBackDAO.closeConnection();
		return employeeEducBackgroundList;
	}

	
	public int add(EducationalBackground educationalBackground)
			throws SQLException, Exception {
		int status;
		EducationalBackgroundDAO eBackDAO = new EducationalBackgroundDAO();
		status = eBackDAO.add(educationalBackground);
		eBackDAO.closeConnection();
		return status;		
	}

	
	public int update(EducationalBackground educationalBackground)
			throws SQLException, Exception {
		int status;
		EducationalBackgroundDAO eBackDAO = new EducationalBackgroundDAO();
		status = eBackDAO.update(educationalBackground);
		eBackDAO.closeConnection();
		return status;		//will not return empId
	}

}
