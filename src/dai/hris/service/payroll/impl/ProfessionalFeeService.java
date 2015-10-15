package dai.hris.service.payroll.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import dai.hris.dao.payroll.ProfessionalFeeDAO;
import dai.hris.model.ProfessionalFee;
import dai.hris.service.payroll.IProfessionalFeeService;

public class ProfessionalFeeService implements IProfessionalFeeService {

	@Override
	public void saveOrUpdate(ProfessionalFee pf) throws SQLException {
		ProfessionalFeeDAO dao = new ProfessionalFeeDAO();
		dao.saveOrUpdate(pf);
		dao.closeConnection();
	}

	@Override
	public List<ProfessionalFee> getAllByEmployeeId(int empId) throws SQLException {
		ProfessionalFeeDAO dao = new ProfessionalFeeDAO();
		List<ProfessionalFee> result = dao.getAllByEmployeeId(empId);
		dao.closeConnection();
		return result;
	}

	@Override
	public List<ProfessionalFee> getAllByEmpIdAndOrDateRange(int empId, Date from, Date to)
			throws SQLException {
		ProfessionalFeeDAO dao = new ProfessionalFeeDAO();
		List<ProfessionalFee> result = dao.getAllByEmpIdAndOrDateRange(empId, from, to);
		dao.closeConnection();
		return result;
	}

}
