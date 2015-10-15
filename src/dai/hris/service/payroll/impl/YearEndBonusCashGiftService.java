package dai.hris.service.payroll.impl;

import java.sql.SQLException;
import java.util.List;

import dai.hris.dao.payroll.YearEndBonusCashGiftDAO;
import dai.hris.model.YearEndBonusCashGift;
import dai.hris.service.payroll.IYearEndBonusCashGiftService;

public class YearEndBonusCashGiftService implements
		IYearEndBonusCashGiftService {

	@Override
	public void add(YearEndBonusCashGift bonus) throws SQLException, Exception {
		YearEndBonusCashGiftDAO dao = new YearEndBonusCashGiftDAO();
		dao.add(bonus);
		dao.closeConnection();
	}

	@Override
	public void update(YearEndBonusCashGift bonus) throws SQLException,
			Exception {
		YearEndBonusCashGiftDAO dao = new YearEndBonusCashGiftDAO();
		dao.update(bonus);
		dao.closeConnection();
	}

	@Override
	public void delete(YearEndBonusCashGift bonus) throws SQLException,
			Exception {
		YearEndBonusCashGiftDAO dao = new YearEndBonusCashGiftDAO();
		dao.delete(bonus);
		dao.closeConnection();
	}

	@Override
	public int[] batchUpdate(List<YearEndBonusCashGift> yebList)
			throws SQLException, Exception {
		YearEndBonusCashGiftDAO dao = new YearEndBonusCashGiftDAO();
		int[] result = dao.batchUpdate(yebList);
		dao.closeConnection();
		return result;
	}

	@Override
	public int[] batchInsert(List<YearEndBonusCashGift> yebList)
			throws SQLException, Exception {
		YearEndBonusCashGiftDAO dao = new YearEndBonusCashGiftDAO();
		int[] result = dao.batchInsert(yebList);
		dao.closeConnection();
		return result;
	}

	@Override
	public List<YearEndBonusCashGift> getAllByEmployeeId(int empId)
			throws SQLException, Exception {
		YearEndBonusCashGiftDAO dao = new YearEndBonusCashGiftDAO();
		List<YearEndBonusCashGift> result = dao.getAllByEmployeeId(empId);
		dao.closeConnection();
		return result;
	}

	@Override
	public List<YearEndBonusCashGift> getAllByJobTitleId(int jtId)
			throws SQLException, Exception {
		YearEndBonusCashGiftDAO dao = new YearEndBonusCashGiftDAO();
		List<YearEndBonusCashGift> result = dao.getAllByJobTitleId(jtId);
		dao.closeConnection();
		return result;
	}

}
