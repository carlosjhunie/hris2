package dai.hris.service.payroll.impl;

import java.sql.SQLException;
import java.util.List;

import dai.hris.dao.payroll.HazardPayDAO;
import dai.hris.model.HazardPay;
import dai.hris.service.payroll.IHazardPayService;

public class HazardPayService implements IHazardPayService {

	@Override
	public void add(HazardPay hp) throws SQLException, Exception {
		HazardPayDAO dao = new HazardPayDAO();
		dao.add(hp);
		dao.closeConnection();
	}

	@Override
	public void update(HazardPay hp) throws SQLException, Exception {
		HazardPayDAO dao = new HazardPayDAO();
		dao.update(hp);
		dao.closeConnection();
	}

	@Override
	public void delete(HazardPay hp) throws SQLException, Exception {
		HazardPayDAO dao = new HazardPayDAO();
		dao.delete(hp);
		dao.closeConnection();
	}

	@Override
	public int[] batchUpdate(List<HazardPay> hpList) throws SQLException,
			Exception {
		HazardPayDAO dao = new HazardPayDAO();
		int[] result = dao.batchUpdate(hpList);
		dao.closeConnection();
		return result;
	}

	@Override
	public int[] batchInsert(List<HazardPay> hpList) throws SQLException,
			Exception {
		HazardPayDAO dao = new HazardPayDAO();
		int[] result = dao.batchInsert(hpList);
		dao.closeConnection();
		return result;
	}

	@Override
	public List<HazardPay> getAllByEmployeeId(int empId) throws SQLException,
			Exception {
		HazardPayDAO dao = new HazardPayDAO();
		List<HazardPay> result = dao.getAllByEmployeeId(empId);
		dao.closeConnection();
		return result;
	}

	@Override
	public List<HazardPay> getAllByJobTitleId(int jtId) throws SQLException,
			Exception {
		HazardPayDAO dao = new HazardPayDAO();
		List<HazardPay> result = dao.getAllByDepartmentId(jtId);
		dao.closeConnection();
		return result;
	}

}
