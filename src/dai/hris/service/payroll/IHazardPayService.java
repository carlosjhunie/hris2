package dai.hris.service.payroll;

import java.sql.SQLException;
import java.util.List;

import dai.hris.model.HazardPay;

public interface IHazardPayService {
	public void add(HazardPay hp) throws SQLException, Exception;
    public void update(HazardPay hp) throws SQLException, Exception;
    public void delete(HazardPay hp) throws SQLException, Exception;
    public int[] batchUpdate(List<HazardPay> hpList) throws SQLException, Exception;
    public int[] batchInsert(List<HazardPay> hpList) throws SQLException, Exception;
    public List<HazardPay> getAllByEmployeeId(int empId) throws SQLException, Exception;
    public List<HazardPay> getAllByJobTitleId(int jtId) throws SQLException, Exception;
}
