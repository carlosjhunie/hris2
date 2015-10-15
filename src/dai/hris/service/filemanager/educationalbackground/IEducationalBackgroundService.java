package dai.hris.service.filemanager.educationalbackground;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.model.EducationalBackground;

public interface IEducationalBackgroundService {
	public EducationalBackground getEmployeeEducationalBackgroundByEducBkgrndId(int educBkgrndId)
			throws SQLException, Exception;

	public ArrayList<EducationalBackground> getEmployeeEducationalBackgroundListByEmpId(
			int empId) throws SQLException, Exception;

	public int add(EducationalBackground educationalBackground)
			throws SQLException, Exception;

	public int update(EducationalBackground educationalBackground)
			throws SQLException, Exception;
}
