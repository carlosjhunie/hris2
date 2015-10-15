package dai.hris.service.payroll.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dai.hris.dao.payroll.PayrollPeriodDAO;
import dai.hris.model.PayrollPeriod;
import dai.hris.service.payroll.IPayrollPeriodService;

public class PayrollPeriodService implements IPayrollPeriodService {

	@Override
	public int saveOrUpdate(PayrollPeriod payrollPeriod) throws SQLException {
		PayrollPeriodDAO dao = new PayrollPeriodDAO();
		int res = dao.saveOrUpdate(payrollPeriod);
		dao.closeConnection();
		return res;
	}

	@Override
	public PayrollPeriod getPayrollPeriodById(int id) throws SQLException {
		PayrollPeriodDAO dao = new PayrollPeriodDAO();
		PayrollPeriod result = dao.getPayrollPeriodById(id);
		dao.closeConnection();
		return result;
	}

	@Override
	public List<PayrollPeriod> getAll() throws SQLException {
		PayrollPeriodDAO dao = new PayrollPeriodDAO();
		List<PayrollPeriod> list = dao.getAll();
		dao.closeConnection();
		return list;
	}
	
	@Override
	public int[] batchUpdate(List<PayrollPeriod> ppList) throws SQLException {
		PayrollPeriodDAO dao = new PayrollPeriodDAO();
		int[] result = dao.batchUpdate(ppList);
		dao.closeConnection();
		return result;
	}

	@Override
	public int[] batchInsert(List<PayrollPeriod> ppList) throws SQLException {
		PayrollPeriodDAO dao = new PayrollPeriodDAO();
		int[] result = dao.batchInsert(ppList);
		dao.closeConnection();
		return result;
	}

	@Override
	public ArrayList<PayrollPeriod> getAll(boolean includeLocked)
			throws SQLException {
		PayrollPeriodDAO dao = new PayrollPeriodDAO();
		ArrayList<PayrollPeriod> list = dao.getAll(includeLocked);
		dao.closeConnection();
		return list;
	}
	
	public ArrayList<PayrollPeriod> getAllGenerated()
			throws SQLException {
		PayrollPeriodDAO dao = new PayrollPeriodDAO();
		ArrayList<PayrollPeriod> list = dao.getAllGenerated();
		dao.closeConnection();
		return list;
	}
	
	

	/*@Override
	public List<PayrollPeriod> getAllByEmployeeId(int employeeId) throws SQLException {
		PayrollPeriodDAO dao = new PayrollPeriodDAO();
		List<PayrollPeriod> result = dao.getAllByEmployeeId(employeeId);
		dao.closeConnection();
		return result;
	}

	@Override
	public List<PayrollPeriod> getAllByJobTitleId(int jtId) throws SQLException {
		PayrollPeriodDAO dao = new PayrollPeriodDAO();
		List<PayrollPeriod> result = dao.getAllByJobTitleId(jtId);
		dao.closeConnection();
		return result;
	}*/

}
