package dai.hris.service.filemanager.jobtitle;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.filemanager.JobTitleDAO;
import dai.hris.model.JobTitle;


public class JobTitleService implements IJobTitleService {

	@Override
	public ArrayList<JobTitle> getAll() throws SQLException, Exception {
		
		JobTitleDAO dao = new JobTitleDAO();
		ArrayList<JobTitle> list = dao.getAll();
		dao.closeConnection();
		return list;
	}

	@Override
	public void add(JobTitle jobTitle) throws SQLException, Exception {
		JobTitleDAO dao = new JobTitleDAO();
		dao.add(jobTitle);
		dao.closeConnection();

	}

	@Override
	public void delete(JobTitle jobTitle) throws SQLException, Exception {
		JobTitleDAO dao = new JobTitleDAO();
		dao.delete(jobTitle);
		dao.closeConnection();

	}

	@Override
	public void update(JobTitle jobTitle) throws SQLException, Exception {
		JobTitleDAO dao = new JobTitleDAO();
		dao.update(jobTitle);
		dao.closeConnection();

	}

}
