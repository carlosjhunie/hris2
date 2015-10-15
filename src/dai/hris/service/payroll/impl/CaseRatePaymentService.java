package dai.hris.service.payroll.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import dai.hris.dao.payroll.CaseRatePaymentDAO;
import dai.hris.model.CaseRatePayment;
import dai.hris.service.payroll.ICaseRatePaymentService;

public class CaseRatePaymentService implements ICaseRatePaymentService {
	
	@Override
	public void saveOrUpdate(CaseRatePayment crp) throws SQLException {
		CaseRatePaymentDAO crpDAO = new CaseRatePaymentDAO();
		crpDAO.saveOrUpdate(crp);
		crpDAO.closeConnection();
	}

	@Override
	public List<CaseRatePayment> getAllByEmployeeId(int empId) throws SQLException {
		CaseRatePaymentDAO crpDAO = new CaseRatePaymentDAO();
		List<CaseRatePayment> resultList = crpDAO.getAllByEmployeeId(empId);
		crpDAO.closeConnection();
		return resultList;
	}

	@Override
	public List<CaseRatePayment> getAllByEmpIdAndOrDateRange(int empId,
			Date from, Date to) throws SQLException {
		CaseRatePaymentDAO dao = new CaseRatePaymentDAO();
		List<CaseRatePayment> result = dao.getAllByEmpIdAndOrDateRange(empId, from, to);
		dao.closeConnection();
		return result;
	}

}
