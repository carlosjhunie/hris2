package dai.hris.service.filemanager.jobtitle;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.model.JobTitle;



public interface IJobTitleService {
	
	public  ArrayList<JobTitle> getAll() throws SQLException, Exception;
	public  void add(JobTitle jobTitle) throws SQLException, Exception;
	public  void delete(JobTitle jobTitle) throws SQLException, Exception;
	public  void update(JobTitle jobTitle) throws SQLException, Exception;

}
