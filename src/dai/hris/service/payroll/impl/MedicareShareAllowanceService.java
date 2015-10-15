package dai.hris.service.payroll.impl;

import java.sql.SQLException;
import java.util.List;

import dai.hris.dao.payroll.MedicareShareAllowanceDAO;
import dai.hris.model.MedicareShareAllowance;
import dai.hris.service.payroll.IMedicareShareAllowanceService;

public class MedicareShareAllowanceService implements
		IMedicareShareAllowanceService {

	@Override
	public void add(MedicareShareAllowance msa) throws SQLException, Exception {
		MedicareShareAllowanceDAO dao = new MedicareShareAllowanceDAO();
		dao.add(msa);
		dao.closeConnection();
	}

	@Override
	public void update(MedicareShareAllowance msa) throws SQLException,
			Exception {
		MedicareShareAllowanceDAO dao = new MedicareShareAllowanceDAO();
		dao.update(msa);
		dao.closeConnection();
	}

	@Override
	public void delete(MedicareShareAllowance msa) throws SQLException,
			Exception {
		MedicareShareAllowanceDAO dao = new MedicareShareAllowanceDAO();
		dao.delete(msa);
		dao.closeConnection();
	}

	@Override
	public int[] batchUpdate(List<MedicareShareAllowance> msaList)
			throws SQLException, Exception {
		MedicareShareAllowanceDAO dao = new MedicareShareAllowanceDAO();
		int[] result = dao.batchUpdate(msaList);
		dao.closeConnection();
		return result;
	}

	@Override
	public int[] batchInsert(List<MedicareShareAllowance> msaList)
			throws SQLException, Exception {
		MedicareShareAllowanceDAO dao = new MedicareShareAllowanceDAO();
		int[] result = dao.batchInsert(msaList);
		dao.closeConnection();
		return result;
	}

	@Override
	public List<MedicareShareAllowance> getAllByEmployeeId(int empId)
			throws SQLException, Exception {
		MedicareShareAllowanceDAO dao = new MedicareShareAllowanceDAO();
		List<MedicareShareAllowance> result = dao.getAllByEmployeeId(empId);
		dao.closeConnection();
		return result;
	}

	@Override
	public List<MedicareShareAllowance> getAllByJobTitleId(int jtId)
			throws SQLException, Exception {
		MedicareShareAllowanceDAO dao = new MedicareShareAllowanceDAO();
		List<MedicareShareAllowance> result = dao.getAllByJobTitleId(jtId);
		dao.closeConnection();
		return result;
	}

}
